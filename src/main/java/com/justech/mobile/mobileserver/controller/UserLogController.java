package com.justech.mobile.mobileserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.justech.mobile.mobileserver.common.EventClass;
import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.entity.JustechDept;
import com.justech.mobile.mobileserver.log.AccountLog;
import com.justech.mobile.mobileserver.log.DeviceInfo;
import com.justech.mobile.mobileserver.model.AccountLogReqModel;
import com.justech.mobile.mobileserver.model.bean.*;
import com.justech.mobile.mobileserver.repository.AccountLogRepository;
import com.justech.mobile.mobileserver.repository.DeviceInfoRepository;
import com.justech.mobile.mobileserver.util.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * ???????????????????????????
 */
@RestController
@Slf4j
public class UserLogController extends CommonController {

    @Autowired(required = false)
    AccountLogRepository accountLogRepository;

    @Autowired(required = false)
    DeviceInfoRepository deviceInfoRepository;

    @Autowired(required = false)
    ElasticsearchOperations elasticsearchOperations;

    @Autowired(required = false)
    ElasticsearchTemplate elasticsearchTemplate;

//    @Autowired
//    private TransportClient client;
    @Autowired(required = false)
    TransportClient client;

    /**
     * ????????????????????????
     *
     * @param log
     * @return
     */
    @RequestMapping(value = "/event", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response event(AccountLog log) {
        Response response = getResponse(ResultEnum.SUCCESS);
        if (!beanValidator(response, log)) return response;
        Date date = new Date();
        String uuid = UUID.randomUUID().toString();
        log.setId(uuid);
        log.setCreateTime(date);
        accountLogRepository.save(log);//??????????????????
        DeviceInfo deviceInfo = deviceInfoRepository.findByDeviceId(log.getDeviceId());
        if (deviceInfo == null) {
            deviceInfo = new DeviceInfo();
            deviceInfo.setDeviceId(log.getDeviceId());
            deviceInfo.setCreateTime(date);
            deviceInfo.setLastTime(date);
            deviceInfo.setId(UUID.randomUUID().toString());
        } else {
            deviceInfo.setLastTime(date);
        }
        deviceInfoRepository.save(deviceInfo);//??????????????????
        return response;
    }


    /**
     * ????????????????????????????????????????????????
     *
     * @param canUse
     * @return
     */
    @RequestMapping(value = "/forbidDevice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response forbidDevice(String deviceId, Boolean canUse) {
        Response response = getResponse(ResultEnum.SUCCESS);
        DeviceInfo deviceInfo = deviceInfoRepository.findByDeviceId(deviceId);
        if (deviceInfo != null) {
            deviceInfo.setCanUse(canUse);
            deviceInfoRepository.save(deviceInfo);
        } else {
            getResponse(ResultEnum.DEVICE_NOT_FIND);
        }
        return response;
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/findAccountLogs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findAccountLogs(AccountLogReqModel model) {
        Response response = getResponse(ResultEnum.SUCCESS);
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        Integer currentPage = 0;
        Integer currentPageSize = 10;
        if (model.getPageNo() != null) {
            currentPage = model.getPageNo();
        }
        if (model.getPageNo() != null) {
            currentPageSize = model.getPageSize();
        }
        if (currentPage < 0) currentPage = 0;
        Pageable pageable = PageRequest.of(currentPage, currentPageSize);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        Page<AccountLog> page;
        if (!StringUtils.isEmpty(model.getDeviceId())) {
            boolQuery.filter(QueryBuilders.matchQuery("deviceId", model.getDeviceId()));
        }
        if (!StringUtils.isBlank(model.getUserId())) {
            boolQuery.filter(QueryBuilders.matchQuery("userId", model.getUserId()));
        }
        if (!StringUtils.isBlank(model.getEventId())) {
            boolQuery.filter(QueryBuilders.matchQuery("eventId", model.getEventId()));
        }
        //????????? ????????????????????? ????????????????????????
        if (StringUtils.isNotBlank(model.getBeginTime()) && StringUtils.isNotBlank(model.getEndTime())) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date startTime = sdf.parse(model.getBeginTime());
                Date endTime = sdf.parse(model.getEndTime());
                boolQuery.filter(QueryBuilders.rangeQuery("createTime").from(startTime.getTime()).to(endTime.getTime()));
            } catch (ParseException e) {
                log.error("???????????????????????????{}???", e.getMessage());
            }
        }
        SortBuilder sortBuilder = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
        queryBuilder.withQuery(boolQuery).withPageable(pageable).withSort(sortBuilder);
        page = elasticsearchOperations.queryForPage(queryBuilder.build(), AccountLog.class);
        //-----------------??????????????????userName,phoneNum ??????------------
        Result result = new Result();
        List<AccountLog> content = page.getContent();
        List<AccountLogBean> resList = new ArrayList<>();
        AccountLogBean accountLogBean;
        String phoneNum;
        StringBuffer buffer;
        for (int i = 0; i < content.size(); i++) {
            accountLogBean = new AccountLogBean();
            BeanUtils.copyProperties(content.get(i), accountLogBean);
            phoneNum = accountLogBean.getPhoneNum();
            if (StringUtils.isNotBlank(phoneNum)) {
                buffer = new StringBuffer();
                buffer.append(phoneNum.substring(0, 3)).append("****").append(phoneNum.substring(phoneNum.length() - 4));
                accountLogBean.setPhoneNum(buffer.toString());
            }
            if (StringUtils.isNotBlank(accountLogBean.getUserId())) {
                //TODO
                String userName = queryUserNameByempno(accountLogBean.getUserId().toUpperCase());
                accountLogBean.setUserName(userName);
            } else if (StringUtils.isNotBlank(accountLogBean.getPhoneNum())) {
                //TODO
                String userName = queryUserNameByphone(accountLogBean.getPhoneNum());
                accountLogBean.setUserName(userName);
            } else {
                accountLogBean.setUserName("");
            }
            resList.add(accountLogBean);
        }
        //------------------------????????????-----------------------
        result.setContent(resList);
        result.setTotalElements(page.getTotalElements());
        result.setTotalPages(page.getTotalPages());
        response.setData(result);
        return response;
    }

    /**
     * ??????????????????(??????????????????)
     *
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/searchByDeviceId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response searchByDeviceId(String deviceId, Integer pageNo, Integer pageSize) {
        Response response = getResponse(ResultEnum.SUCCESS);
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        Integer currentPage = 0;
        Integer currentPageSize = 10;
        if (pageNo != null) {
            currentPage = pageNo;
        }
        if (pageSize != null) {
            currentPageSize = pageSize;
        }
        if (currentPage < 0) currentPage = 0;
        Pageable pageable = PageRequest.of(currentPage, currentPageSize);
        BoolQueryBuilder boolQuery;
        Page<DeviceInfo> page;
        if (StringUtils.isNotBlank(deviceId)) {
            boolQuery = QueryBuilders.boolQuery();
            boolQuery.must(QueryBuilders.matchQuery("deviceId", deviceId));
            SortBuilder sortBuilder = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
            queryBuilder.withQuery(boolQuery).withPageable(pageable).withSort(sortBuilder);
            page = elasticsearchOperations.queryForPage(queryBuilder.build(), DeviceInfo.class);
        } else {
            boolQuery = QueryBuilders.boolQuery();
            SortBuilder sortBuilder = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
            queryBuilder.withQuery(boolQuery).withPageable(pageable).withSort(sortBuilder);
            page = elasticsearchOperations.queryForPage(queryBuilder.build(), DeviceInfo.class);
        }
        response.setData(page);
        return response;
    }


    /**
     * ??????????????? -- ????????????????????????
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/userIn7dLog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response userIn7dLog() {
        Response res = getResponse(ResultEnum.SUCCESS);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -7);// ?????????1
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(QueryBuilders.rangeQuery("createTime").from(calendar.getTime().getTime()).to(new Date().getTime()));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withQuery(boolQuery)
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                .withIndices("accountlog").withTypes("accountlog")
                .addAggregation(AggregationBuilders.dateHistogram("dataAgg").field("createTime").dateHistogramInterval(DateHistogramInterval.DAY)
                        .subAggregation(AggregationBuilders.cardinality("user_count").field("userId").precisionThreshold(5000)))
                .build();
        // ???????????????
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, response -> response.getAggregations());
        Map<String, Aggregation> results = aggregations.asMap();
        Histogram histogram = (Histogram) results.get("dataAgg");
        // ???bucket list ????????? map ??? key -> ??????   value-> ????????????
        List<ActiveUserLogs> list = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        histogram.getBuckets().stream().forEach(t -> {
            ActiveUserLogs activeUserLogs = new ActiveUserLogs();
            Histogram.Bucket histogram1 = t;
            String format = sdf.format(Long.valueOf(histogram1.getKeyAsString()));
            activeUserLogs.setName(format);
//            activeUserLogs.setName(sdf.format(Long.valueOf(histogram1.getKeyAsString())));
            Cardinality cardinality = histogram1.getAggregations().get("user_count");
            activeUserLogs.setValue(cardinality.getValue());
            list.add(activeUserLogs);
        });
        res.setData(list);
        return res;
    }


    /**
     * ???????????????-- ??????????????????
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/selectActiveUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response activeUserSelect(String beginTime, String endTime) {
        Response res = getResponse(ResultEnum.SUCCESS);
        Date etime = null;
        Date stime = null;
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime)) {
            etime = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(etime);
            calendar.add(Calendar.DATE, -1);// ?????????1
            stime = calendar.getTime();
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                stime = sdf.parse(beginTime);
                etime = sdf.parse(endTime);
            } catch (ParseException e) {
                log.error("???????????????????????????{}???", e.getMessage());
            }
        }
        boolQuery.filter(QueryBuilders.rangeQuery("createTime").from(stime.getTime()).to(etime.getTime()));
        SortBuilder sortBuilder = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withQuery(boolQuery)
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                .withIndices("accountlog").withTypes("accountlog")
                .addAggregation(AggregationBuilders.terms("user_count").field("userId"))
                .withSort(sortBuilder)
                .build();
        // ???????????????
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, response -> response.getAggregations());
        Map<String, Aggregation> results = aggregations.asMap();
        StringTerms user_count = (StringTerms) results.get("user_count");
        Iterator<StringTerms.Bucket> iterator = user_count.getBuckets().iterator();
        List<SelectUserInfo> list = new ArrayList();
        SelectUserInfo selectUserInfo;
        Map map = null;
        while (iterator.hasNext()) {
            selectUserInfo = new SelectUserInfo();
            StringTerms.Bucket bucket = iterator.next();
            String empNo = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();

//            map = queryUserInRedis(empNo);
            map=queryUserByempNo(empNo);
            if (null != map) {

//                selectUserInfo.setDept(redisTemplate.opsForHash().entries("DEPT:" + map.get("FDeptID")).get("deptName").toString());
                JustechDept justechDept=findDeptById((String) map.get("FDeptID"));
                if (justechDept!=null){
                    selectUserInfo.setDept(justechDept.getName());
                }
                selectUserInfo.setEmpNo(map.get("FEmpID").toString());
                selectUserInfo.setUserName(map.get("FEmpName").toString());
                selectUserInfo.setCount(docCount);
            }
            list.add(selectUserInfo);

        }
        res.setData(list);
        return res;
    }


    /**
     * ??????????????????
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/requestAnalyze", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response requestAnalyze(String beginTime, String endTime) {
        Response res = getResponse(ResultEnum.SUCCESS);
        Date etime = null;
        Date stime = null;
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime)) {
            etime = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(etime);
            calendar.add(Calendar.DATE, -1);// ?????????1
            stime = calendar.getTime();
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                stime = sdf.parse(beginTime);
                etime = sdf.parse(endTime);
            } catch (ParseException e) {
                log.error("???????????????????????????{}???", e.getMessage());
            }
        }
        boolQuery.filter(QueryBuilders.rangeQuery("createTime").from(stime.getTime()).to(etime.getTime()));
        SortBuilder sortBuilder = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                .withIndices("accountlog").withTypes("accountlog")
                .addAggregation(AggregationBuilders.terms("log_event").field("eventId"))//.subAggregation(AggregationBuilders.terms("log_name").field("name"))
                .withSort(sortBuilder)
                .build();
        // ???????????????
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, response -> response.getAggregations());
        Map<String, Aggregation> results = aggregations.asMap();
        StringTerms user_count = (StringTerms) results.get("log_event");
        Iterator<StringTerms.Bucket> iterator = user_count.getBuckets().iterator();
        List<RequestAnalyze> list = new ArrayList<>();
        RequestAnalyze requestAnalyze;
        while (iterator.hasNext()) {
            requestAnalyze = new RequestAnalyze();
            StringTerms.Bucket bucket = iterator.next();
            String eventId = bucket.getKeyAsString().toUpperCase();
            long docCount = bucket.getDocCount();
            requestAnalyze.setName(EventClass.map.get(eventId));
            requestAnalyze.setRequestCounts(docCount);
            list.add(requestAnalyze);
        }
        res.setData(list);
        return res;
    }


    /**
     * ??????????????????
     *
     * @return
     */
    @RequestMapping(value = "/searchActive", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response searchActive() {
        Response response = getResponse(ResultEnum.SUCCESS);
        StatisticBean statisticBean = new StatisticBean();
        int onlineUser = findOnlineUser();//?????????????????? ,?????????????????????
        int userAmount = userAmount();
        statisticBean = system(statisticBean);
        statisticBean.setOnlineUserAmount(onlineUser);
        statisticBean.setUserAmmount(userAmount);
        BigDecimal androidPercent = new BigDecimal(Float.valueOf(statisticBean.getAndroidAmount()) / statisticBean.getDeviceAmount() * 100);
        BigDecimal iosPercent = new BigDecimal(Float.valueOf(statisticBean.getIosAmount()) / statisticBean.getDeviceAmount() * 100);
        //??????????????????
        BigDecimal androidDecimal = androidPercent.setScale(2, BigDecimal.ROUND_DOWN);
        if (androidDecimal.compareTo(androidPercent.setScale(0, BigDecimal.ROUND_DOWN)) != 0) {
            statisticBean.setAndroidPercent(androidDecimal.toPlainString() + "%");
        } else {
            statisticBean.setAndroidPercent(androidPercent.setScale(0, BigDecimal.ROUND_DOWN).toPlainString() + "%");
        }
        BigDecimal iosDecimal = iosPercent.setScale(2, BigDecimal.ROUND_DOWN);
        if (iosDecimal.compareTo(iosPercent.setScale(0, BigDecimal.ROUND_DOWN)) != 0) {
            statisticBean.setIosPercent(iosPercent.setScale(2, BigDecimal.ROUND_UP).toPlainString() + "%");
        } else {
            statisticBean.setIosPercent(iosPercent.setScale(0, BigDecimal.ROUND_UP).toPlainString() + "%");
        }
        response.setData(statisticBean);
        return response;
    }


    /**
     * ??????????????????
     *
     * @return
     */
    public int findOnlineUser() {
        String suffix = '*' + "token:" + '*';
        Set<String> keys = redisTemplate.keys(suffix);
        return keys.size();
    }


    /**
     * ?????????????????? --- ??????????????????
     *
     * @return
     */
    public int userAmount() {
        SearchRequestBuilder sbuilder = client.prepareSearch("accountlog").setTypes("accountlog");
        CardinalityAggregationBuilder aggregationBuilder = AggregationBuilders.cardinality("user_count").field("userId").precisionThreshold(5000);
        sbuilder.addAggregation(aggregationBuilder);
        SearchResponse result = sbuilder.execute().actionGet();
        Map<String, Aggregation> asMap = result.getAggregations().getAsMap();
        Aggregation user_count = asMap.get("user_count");
        JSONObject object = JSON.parseObject(user_count.toString());
        Object temp = object.get("user_count");
        JSONObject jsonObject = JSON.parseObject(temp.toString());
        int count = jsonObject.getIntValue("value");
        return count;
    }


    /**
     * ?????????????????? --- ??????ios/android ??????
     *
     * @return
     */
    public StatisticBean system(StatisticBean statisticBean) {
        SortBuilder sortBuilder = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
        CardinalityAggregationBuilder aggregationBuilder = AggregationBuilders.cardinality("user_deviceId").field("deviceId").precisionThreshold(5000);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                .withIndices("accountlog").withTypes("accountlog")
                .addAggregation(AggregationBuilders.terms("user_deviceType").field("deviceType").subAggregation(aggregationBuilder))//.subAggregation(AggregationBuilders.terms("log_name").field("name"))
                .withSort(sortBuilder)
                .build();
        // ???????????????
        int deviceAmount = 0;
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, response -> response.getAggregations());
        Map<String, Aggregation> results = aggregations.asMap();
        StringTerms user_count = (StringTerms) results.get("user_deviceType");
        Iterator<StringTerms.Bucket> iterator = user_count.getBuckets().iterator();
        while (iterator.hasNext()) {
            StringTerms.Bucket bucket = iterator.next();
            bucket.getDocCount();//????????????
            Map<String, Aggregation> tempRes = bucket.getAggregations().getAsMap();
            InternalCardinality user_deviceType = (InternalCardinality) tempRes.get("user_deviceId");
            user_deviceType.getValue();
            if ("ios".equals(bucket.getKeyAsString())) {
                deviceAmount = deviceAmount + (int) user_deviceType.getValue();
                statisticBean.setIosAmount((int) user_deviceType.getValue());
            } else if ("android".equals(bucket.getKeyAsString())) {
                deviceAmount = deviceAmount + (int) user_deviceType.getValue();
                statisticBean.setAndroidAmount((int) user_deviceType.getValue());
            }
            statisticBean.setDeviceAmount(deviceAmount);
        }
        return statisticBean;
    }

}
