package com.justech.mobile.mobileserver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.justech.mobile.mobileserver.To.ImageTo;
import com.justech.mobile.mobileserver.To.UserOfErpEntity;
import com.justech.mobile.mobileserver.entity.Suggestion;
import com.justech.mobile.mobileserver.repository.SuggestionRepostitory;
import com.justech.mobile.mobileserver.repository.UserRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SuggestionService
 * @Author: xiaofeng.yang
 * @Date: Create in 14:30 2019/7/16
 * @Description : TODO
 * @Version: 1.0
 */
@Service
public class SuggestionService {
    @Value("${file.server.url}/upload")
    private String fileServer;
    @Autowired
    private UserRepostitory userRepostitory;

    @Autowired
    private SuggestionRepostitory suggestionRepostitory;
    public void addSuggestion(Suggestion suggestion){
        suggestionRepostitory.save(suggestion);
    }
    public List<Suggestion> getSuggestionList(){
        return suggestionRepostitory.findAll();
    }
    public Page<Suggestion> getSuggestionList(int pageSize, int pageNo){
        Sort sort = new Sort(Sort.Direction.DESC, "createTime").and(new Sort(Sort.Direction.ASC,
                "id"));
        return suggestionRepostitory.findAll(PageRequest.of(pageNo,pageSize,sort)).map(this::convert);
    }

    private Suggestion convert(Suggestion suggestion){
        UserOfErpEntity user=userRepostitory.readOnlyUserInfo(suggestion.getSuggester());
        if (user!=null)
            suggestion.setSuggester(user.getUserName()+"("+suggestion.getSuggester()+")");
        return suggestion;
    }
    /*
     * @Name addSuggestionWithIamge
     * @Author xiaofeng.yang
     * @Description 上传评论包含图片
     * @Date 10:53 2019/7/22
     * @Param [suggestion, multipartFiles]
     * @return void
     **/
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void addSuggestionWithIamge(Suggestion suggestion, MultipartFile[] multipartFiles) throws IOException {

        List<String> imageUris=new ArrayList<>();
        for (MultipartFile file:multipartFiles){
            imageUris.add(uploadFile(file.getBytes(),file.getName(),file.getContentType()).getPath());
        }
        suggestion.setImageUris(imageUris);
        suggestionRepostitory.save(suggestion);

    }
    private ImageTo uploadFile(byte[] imageBytes,String fileName,String contentType) {
        HttpURLConnection conn = null;
        String res = "";
        try {
            //获取文件流上传文件
            String BOUNDARY = "----------------------------083645689176130792354351";
            URL url = new URL(fileServer);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type", "multipart/form-data; boundary=" + BOUNDARY);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setDoOutput(true);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
            strBuf.append("Content-Disposition: form-data; name=\""
                    + "output" + "\"\r\n\r\n");
            strBuf.append("json");
            strBuf.append("\r\n").append("--").append(BOUNDARY)
                    .append("\r\n");
            strBuf.append("Content-Disposition: form-data; name=\""
                    + "file" + "\"; filename=\"" + fileName + "\"\r\n");
            strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
            out.write(strBuf.toString().getBytes());
            out.write(imageBytes);
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            StringBuffer strBufResponse = new StringBuffer();
            if (conn.getResponseCode() != 200) {
                throw new Exception("upload file error:" + conn.getResponseCode());
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBufResponse.append(line).append("\n");
            }
            res = strBufResponse.toString();
            if (!JSON.isValidObject(res)) {
                throw new Exception("upload file error:" + res);
            }
            reader.close();
            reader = null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return JSONObject.parseObject(res,ImageTo.class);
    }
}
