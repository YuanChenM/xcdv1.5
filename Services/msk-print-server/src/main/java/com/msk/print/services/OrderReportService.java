package com.msk.print.services;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.msk.comm.consts.SystemConst;
import com.msk.comm.utils.RestClientUtil;
import com.msk.print.bean.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by jackjiang on 16/7/8.
 */
public class OrderReportService implements ReportDataService {
    private Map<String, Object> reportParam;
    private Collection<Serializable> reportData;

    private static Logger logger = LoggerFactory.getLogger(OrderReportService.class);

    @Override
    public void init(String templateCode, PrintParam callbackParam) {
        if ("PDF000002".equals(templateCode)) {
            this.doPDF000002(callbackParam);//处理现在的pdf 打印
        } else {
            this.doPDF000001(callbackParam);//处理之前的pdf 打印
        }
    }


    /**
     * 处理之前的pdf 打印
     *
     * @param callbackParam
     */
    private void doPDF000001(PrintParam callbackParam) {
        RsRequest<Map<String, Object>> requestParam = new RsRequest<>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        Map<String, Object> param = new HashMap<>();
        JSONObject jsStr = JSONObject.parseObject(callbackParam.getCallBackParam());
        if (jsStr != null) {
            param.put("orderId", jsStr.get("orderId"));
        }
        requestParam.setParam(param);
        //url
        String url = callbackParam.getPrintCallBackUrl();
        //调用接口
        RsResponse<OrderPrintBean> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<OrderPrintBean>>() {
        });
        if (response.getResult() != null || SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            this.reportParam = new HashMap<>();
            reportParam.put("order", response.getResult());
            reportParam.put("receive", response.getResult().getReceiveInfo());
            reportParam.put("delivery", response.getResult().getDeliveryReq());
            this.reportData = new ArrayList<Serializable>();
            reportData.addAll(response.getResult().getDetailInfo());
        } else {
            this.reportParam = new HashMap<>();
            this.reportData = new ArrayList<Serializable>();
        }
    }

    /**
     * 处理现在pdf 打印
     *
     * @param callbackParam
     */
    private void doPDF000002(PrintParam callbackParam) {
        RsRequest<ISO151402RestParam> requestParam = new RsRequest<>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        JSONObject jsStr = JSONObject.parseObject(callbackParam.getCallBackParam());
        ISO151402RestParam restParam = new ISO151402RestParam();
        if (jsStr != null) {
            restParam.setOrderId(Long.parseLong((String) jsStr.get("orderId")));// orderId
            restParam.setSubOrderId(Long.parseLong((String) jsStr.get("subOrderId")));// subOrderId
        }
        requestParam.setParam(restParam);
        //url
        String url = callbackParam.getPrintCallBackUrl();
        //调用接口
        RsResponse<ISO151402RestResult> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<ISO151402RestResult>>() {
        });
        if (response.getResult() != null || SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            try {
                this.reportParam = new HashMap<>();
                Map<String, String> order = BeanUtils.describe(response.getResult());
                Map<String, String> receive = BeanUtils.describe(response.getResult().getReceiveInfo());
                Map<String, String> delivery = BeanUtils.describe(response.getResult().getDeliveryReq());
                reportParam.put("order", order);
                reportParam.put("receive", receive);
                reportParam.put("delivery", delivery);
                reportParam.put("actualDeliverList", response.getResult().getActualDeliveryReqResultList());
                this.reportData = new ArrayList<Serializable>();
                List<ISO151402RestDetailInfoResult> detailInfoList = response.getResult().getDetailInfo();
                reportData.addAll(detailInfoList);
                // 获取子报表的文件流
                InputStream subInputStream = this.getClass().getResourceAsStream("/pdf/template/order/order_actual_deliver.jasper");
                reportParam.put("subInputStream", subInputStream);
            } catch (Exception e) {
                e.printStackTrace();
                logger.info(e.getMessage());
            }
        } else {
            this.reportParam = new HashMap<>();
            this.reportData = new ArrayList<Serializable>();
        }
    }


    @Override
    public Map<String, Object> getReportParam() {
        return this.reportParam;
    }

    @Override
    public Collection<Serializable> getReportData() {
        return this.reportData;
    }
}