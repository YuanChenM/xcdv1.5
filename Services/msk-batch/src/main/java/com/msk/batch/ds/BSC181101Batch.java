package com.msk.batch.ds;

import com.alibaba.fastjson.JSON;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.BaseBatch;
import com.msk.batch.ds.bean.BSC181101Param;
import com.msk.batch.ds.logic.BSC181101Logic;
import com.msk.common.bean.RsRequest;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * BSC181101Batch 获得供应商分销数量Batch
 *
 * @author yang_yang
 */
@Component("BSC181101")
public class BSC181101Batch extends BaseBatch {

    /**ACCEPT:application/json*/
    private static final String ACCEPT = "application/json";
    /**CONTENT_TYPE:application/json; charset=utf-8*/
    private static final String CONTENT_TYPE = "application/json; charset=utf-8";
    /**UTF8:UTF-8*/
    private static final String UTF8="UTF-8";

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BSC181101Batch.class);

    @Autowired
    private BSC181101Logic bSC181101Logic;

    /**Http Client Builder*/
    private static HttpClientBuilder httpClientBuilder;

    static{
        // 长连接保持30秒
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(1000);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(1000);
        httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        // 重试次数，默认是3次，没有开启
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true));
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
    }

    @Override
    public BaseParam createParam(String[] args){
        logger.info("BSC181101Batch创建param");
        BSC181101Param param = new BSC181101Param();

        SystemServerManager.setModelName("SNK-PRICE");
//        String url = SystemServerManager.SnkPriceServerManage.getGetSupDistributeDemand();
//        String url = "http://10.20.16.7:8088/msk-snk-price/api/sp/getSupDistributeDemand";
        String url = "http://localhost:8089/api/sp/getSupDistributeDemand";
        RsRequest<BSC181101Param> request = new RsRequest<BSC181101Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        /** modify by likai 2016/7/29 修改分销月度*/
        param.setDemandMonth(bSC181101Logic.getDistMonth());
        request.setParam(param);

        JSONObject resultJson = post(url,request);
        param.setJsonData(resultJson);

        return param;
    }

    /**
     * 业务处理
     *
     * @param baseParam baseParam
     */
    @Override
    public void doOperate(BaseParam baseParam){
        logger.debug("BSC181101Batch的doOperate");
        BSC181101Param bsc181101Param = (BSC181101Param)baseParam;
        bSC181101Logic.setSuppDistNum(bsc181101Param);
    }

    /**
     * Http Post请求方法
     * @param url 请求URL
     * @param param 请求参数
     * @return 返回JSON数据
     */
    public JSONObject post(String url, Object param){
        String jsonParam = JSON.toJSONString(param);
        logger.info("请求URL:"+url+"请求参数:"+jsonParam.replace("\n","").replace("\r","").replace(" ",""));
        try {
            HttpClient httpclient = httpClientBuilder.build();
            HttpPost method = new HttpPost(url);
            method.addHeader("Content-type",CONTENT_TYPE);
            method.setHeader("Accept", ACCEPT);
            method.setEntity(new StringEntity(jsonParam, Charset.forName(UTF8)));
            long startTime = System.currentTimeMillis();
            HttpResponse response = httpclient.execute(method);
            long endTime = System.currentTimeMillis();
            logger.debug("请求花费时间为:"+(endTime-startTime));
            String body = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK){
                logger.error("异常:"+url+" 参数:"+jsonParam.replace("\n","").replace("\r","").replace(" ",""));
                logger.debug("Http 返回失败，Http失败状态为："+statusCode);
                throw new SystemException("Http 返回失败，Http失败状态为："+statusCode);
            }
            logger.info("返回数据:"+body.replace("\n","").replace("\r","").replace(" ",""));
            JSONObject resultJsonData = new JSONObject(body);
            return resultJsonData;
        } catch (ClientProtocolException e) {
            logger.error("异常:"+url+" 参数:"+jsonParam.replace("\n","").replace("\r","").replace(" ",""));
            throw new SystemException(e.getMessage());
        } catch (IOException e) {
            logger.error("异常:"+url+" 参数:"+jsonParam.replace("\n","").replace("\r","").replace(" ",""));
            throw new SystemException(e.getMessage());
        }
    }

}
