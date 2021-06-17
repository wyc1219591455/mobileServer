package com.justech.mobile.mobileserver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.justech.mobile.mobileserver.To.ImageTo;
import com.justech.mobile.mobileserver.util.CumstonException;
import com.justech.mobile.mobileserver.util.HttpUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName FileUploadService
 * @Author: xiaofeng.yang
 * @Date: Create in 9:32 2019/7/25
 * @Description : TODO
 * @Version: 1.0
 */
@Service
public class FileUploadService {
    @Value("${file.server.url}/upload")
    private String fileServer;
    @Autowired
    private HttpUtil httpUtil;
    @Value("${file.server.url}/delete")
    private String deletefileServer;
    public ImageTo uploadFile(byte[] imageBytes, String fileName, String contentType) throws CumstonException {
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
            throw new CumstonException(e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return JSONObject.parseObject(res,ImageTo.class);
    }
    public void  deleteFile(String path){
        HttpPost httpGet=new HttpPost(deletefileServer+"?path="+path);
        try {
          CloseableHttpResponse httpResponse= httpUtil.doPost(httpGet);
            System.out.println("============");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
