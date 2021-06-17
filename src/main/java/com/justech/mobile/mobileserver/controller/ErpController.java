package com.justech.mobile.mobileserver.controller;

import com.alibaba.fastjson.JSON;
import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.entity.Cater;
import com.justech.mobile.mobileserver.entity.CaterBean;
import com.justech.mobile.mobileserver.entity.CaterExBean;
import com.justech.mobile.mobileserver.entity.CaterExPro;
import com.justech.mobile.mobileserver.model.CaterReqModel;
import com.justech.mobile.mobileserver.util.AesUtil;
import com.justech.mobile.mobileserver.util.HttpUtil;
import com.justech.mobile.mobileserver.util.ResultEnum;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * erp 相关接口控制器
 */
@RestController
public class ErpController extends CommonController {
    private Logger logger = LoggerFactory.getLogger(ErpController.class);

    @Autowired
    private AesUtil aesUtil;

    @Autowired
    HttpUtil httpUtil;
    @Value("${erp.server.user.caterRecord.url}")
    private String caterRecordUrl;
    @Value("${erp.server.user.caterException.url}")
    private String caterExceptionUrl;


    /**
     * 报餐查询
     *
     * @param caterReqModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cater", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getCater(CaterReqModel caterReqModel) throws Exception {

        // 工号解密
        String no = aesUtil.decrypt(caterReqModel.getEmpNo());
        caterReqModel.setEmpNo(no);
        /**
         * 上月
         */
        /*LocalDate localDate = LocalDate.now();
        LocalDate startDay;
        if (localDate.getMonthValue() == 1) {
            LocalDate lastMonth = LocalDate.of(localDate.getYear() - 1, 12, 1);
            startDay = lastMonth.with(TemporalAdjusters.firstDayOfMonth());
        } else {
            LocalDate lastMonth = LocalDate.of(localDate.getYear(), localDate.getMonthValue() - 1, 1);
            startDay = lastMonth.with(TemporalAdjusters.lastDayOfMonth());
        }

        LocalDate endDay = localDate.with(TemporalAdjusters.lastDayOfMonth());

        caterReqModel.setFromDate(startDay.toString());
        caterReqModel.setToDate(endDay.toString());*/

        Response response = getResponse(ResultEnum.SUCCESS);
        long start = System.currentTimeMillis();
        if (!beanValidator(response, caterReqModel)) return response;
        String recordsUrl = caterRecordUrl + "?empNo={0}&fromDate={1}&toDate={2}";//报餐记录接口地址
        String exceptionUrl = caterExceptionUrl + "?empNo={0}&fromDate={1}&toDate={2}";//报餐异常接口地址


        recordsUrl = MessageFormat.format(recordsUrl, caterReqModel.getEmpNo(), caterReqModel.getFromDate(), caterReqModel.getToDate());
        exceptionUrl = MessageFormat.format(exceptionUrl, caterReqModel.getEmpNo(), caterReqModel.getFromDate(), caterReqModel.getToDate());

        Cater cater = new Cater();
        HttpGet httpGet = new HttpGet(recordsUrl);
        CloseableHttpResponse closeableHttpResponse = null;
        closeableHttpResponse = httpUtil.doGet(httpGet);
        if (200 == closeableHttpResponse.getStatusLine().getStatusCode()) {
            String s = EntityUtils.toString(closeableHttpResponse.getEntity(), "utf-8");
            EntityUtils.consume(closeableHttpResponse.getEntity());
            List<CaterBean> caterBeans = JSON.parseArray(s, CaterBean.class);
            cater.setRecords(caterBeans);
        } else {
            httpGet.abort();
        }
        httpGet = new HttpGet(exceptionUrl);
        closeableHttpResponse = httpUtil.doGet(httpGet);
        if (200 == closeableHttpResponse.getStatusLine().getStatusCode()) {
            String s = EntityUtils.toString(closeableHttpResponse.getEntity(), "utf-8");
            EntityUtils.consume(closeableHttpResponse.getEntity());
            List<CaterExBean> caterExBeans = JSON.parseArray(s, CaterExBean.class);
            List<CaterExPro> caterExProList = dealData(caterExBeans);//处理数据
            cater.setExceptionRecords(caterExProList);
        } else {
            httpGet.abort();
        }

        //System.err.println(cater);

        response.setData(cater);
        long end = System.currentTimeMillis();
        logger.info("报餐接口查询时间差:{},单位毫秒", (end - start));
        return response;
    }


    /**
     * 封装数据结构
     *
     * @param caterExBeans
     * @return
     * @throws Exception
     */
    public List<CaterExPro> dealData(List<CaterExBean> caterExBeans) throws Exception {
        Map<String, List<CaterExBean>> map = new HashMap();
        List<CaterExBean> list;
        List<CaterExBean> tempList;
        //数据按照日期合并
        for (CaterExBean caterExBean : caterExBeans) {
            if (map.containsKey(caterExBean.getDate())) {
                list = new ArrayList<>();
                list.addAll(map.get(caterExBean.getDate()));
                list.add(caterExBean);
                map.put(caterExBean.getDate(), list);
            } else {
                tempList = new ArrayList<>();
                tempList.add(caterExBean);
                map.put(caterExBean.getDate(), tempList);
            }
        }

        // 今天及今天以后的日期的报餐不显示
        List<CaterExPro> caterExProList = new ArrayList<>();
        CaterExPro caterExPro;
        for (Map.Entry<String, List<CaterExBean>> entry : map.entrySet()) {
            List<CaterExBean> values = entry.getValue();
            String key = entry.getKey();
            caterExPro = new CaterExPro();
            caterExPro.setDate(key);
            caterExPro.setCaterExBeans(values);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Long exTime = sdf.parse(key).getTime();
            Date date = new Date(System.currentTimeMillis());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Long currentTime = cal.getTime().getTime();
            if (exTime.compareTo(currentTime) < 0) {
                caterExProList.add(caterExPro);
            }
        }
        return caterExProList;
    }

}
