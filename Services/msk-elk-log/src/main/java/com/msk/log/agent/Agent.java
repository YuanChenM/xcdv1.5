package com.msk.log.agent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.hoperun.core.utils.StringUtil;
import com.msk.common.config.ConfigManager;
import com.msk.log.query.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.msk.log.common.Common;

public class Agent {
    // http://10.20.16.78:9200/error/logs/_search
//    private final static String url = ConfigManager.getLogAnalysisService();

    private final static String service = "/_search";
    private final static int SIZE = 1000;

    // 时差
    private final static int TIME_DIFF = -8;


    /**
     * 根据各个模块名，去获得各模块接口日志查询url
     *
     * @param method 模块名
     * @return
     */
    public static String getUrl(String method,String url) {
        //只是查询数量计算
        if ((Common.REQUEST_LEVEL.ACCOUNT).equals(method)) {
            return url + service + "?search_type=count";
        }
        //查询错误日志详情
        return url + service;

    }

    /**
     * 根据日志内容和日志级别解析日志内容
     *
     * @param message  日志内容
     * @param logLevel 日志级别
     * @return
     */
    public static MessageParam formatMessage(String message, String logLevel) {
        MessageParam messageParam = new MessageParam();
        if (Common.LOG_LEVEL.ERROR_LEVEL.equals(logLevel)) {
            String[] messages = message.split("\n");
            // 获得异常原因
            messageParam.setErrorMessage(message.substring(
                    message.indexOf("异常原因:") + 5, message.indexOf("请求URL:")));
            // 截取请求uri
            messageParam.setRequestUri(message.substring(
                    message.indexOf("请求URL:") + 6, message.indexOf("请求参数:")));
            // 截取请求参数
            messageParam.setRequestParam(message.substring(
                    message.indexOf("请求参数:") + 5, message.length() - 1));
        } else {
            messageParam.setRequestTime(message.substring(
                    message.indexOf("响应时间:") + 5, message.indexOf("返回数据:")));
        }
        return messageParam;
    }

    /**
     * 根据模块名，查询条件，接口url查询
     *
     * @param query
     * @param url
     * @return
     * @throws java.io.IOException
     */
    public static LogResult query(String query, String url)
            throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity stringEntity = new StringEntity(query);

        stringEntity.setContentType("application/json; charset=UTF-8");
        // 必须设置
        httpPost.addHeader("Accept-Encoding", "gzip, deflate");


        httpPost.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        String result = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
        String formatedResult = result.replace("@", "");
        LogResult logResult = JsonUtil.jsonToObject(formatedResult,
                LogResult.class);
        return logResult;
    }


    /**
     * @param logLevel 日志级
     * @param url      接口url
     * @return
     */
    public static String getRequestParam(Map<String, String> map, String logLevel,
                                         String url) {
        QueryParam queryParam = new QueryParam();
        QueryMatch queryMatch = new QueryMatch();
        Filter filter = new Filter();
        Range range = new Range();
        Bool bool = new Bool();
        SearchTime timestamp = new SearchTime();
        //设置日志查询结束时间
        timestamp.setTo(map.get("to"));
        timestamp.setFrom(map.get("from"));
        range.setTimestamp(timestamp);
        filter.setRange(range);
        queryParam.setFilter(filter);
        //日志级别设置
        MessageMatch messageMatch = new MessageMatch();
        messageMatch.setMessage(logLevel);
        List<Should> list = new ArrayList<Should>();
        Should should = new Should();
        should.setMatch(messageMatch);
        list.add(should);

        //url
        Should urlShould = new Should();

        MessageMatch urlMessageMatch = new MessageMatch();
        //根据传入接口的url，拼接请求
        if (!StringUtil.isEmpty(url)) {
            urlMessageMatch.setMessage(url);
            urlShould.setMatch(urlMessageMatch);
            list.add(urlShould);
            url = url.replaceFirst("/", "");
            String[] urls = url.split("/");
            for (String key : urls) {
                Should urlSplitShould = new Should();
                MessageMatch urlSplitMessageMatch = new MessageMatch();
                urlSplitMessageMatch.setMessage(key);
                urlSplitShould.setMatch(urlSplitMessageMatch);
                list.add(urlSplitShould);
            }
        }
        bool.setShould(list);
        bool.setMinimum_should_match(list.size());
        queryMatch.setBool(bool);
        queryParam.setQuery(queryMatch);
        queryParam.setFilter(filter);
        queryParam.setSize(SIZE);
        return JsonUtil.objectToJson(queryParam).replace("timestamp", "@timestamp");

    }

}
