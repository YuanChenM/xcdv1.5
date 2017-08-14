package com.msk.print.services;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.msk.comm.consts.StringConst;
import com.msk.comm.consts.SystemConst;
import com.msk.print.bean.ISO151416OrderRestResult;
import com.msk.print.bean.ISO151416ReceiveInfoRestResult;
import com.msk.print.bean.ISO153105Param;
import com.msk.print.bean.ISO153105Result;
import com.msk.print.bean.ISO153202Param;
import com.msk.print.bean.ISO153202Result;
import com.msk.print.bean.PrintParam;
import com.msk.print.bean.RsRequest;
import com.msk.print.bean.RsResponse;
import com.msk.print.utils.RestClientUtil;
import com.msk.print.utils.StringUtil;

/**
 * Created by zhang_chi on 16/8/1.
 */
public class CashPoolingReportService implements ReportDataService {
    private Map<String, Object> reportParam;
    private Collection<Serializable> reportData;

    private static Logger LOGGER = LoggerFactory.getLogger(CashPoolingReportService.class);

    @Override
    public void init(String templateCode, PrintParam callbackParam) {
        /* 改善 #2107 modify by li_huiqian on 2016/8/25 start */
        if ("SO153102".equals(templateCode)) {
            // 处理买家
            dealCPSO153102(callbackParam, templateCode);
            // if("SO153102".equals(templateCode)){
            // //处理买家 前段
            // dealCPSO153102(callbackParam,templateCode);
            // }else if("SO153102B".equals(templateCode)){
            // //处理买家 后段
            // dealCPSO153102(callbackParam,templateCode);
            /* 改善 #2107 modify by li_huiqian on 2016/8/25 end */
        } else if ("SO153112".equals(templateCode)
                || !StringUtil.isEmpty(templateCode) && templateCode.startsWith("SO153112")) {
            // 处理卖家
            dealCPSO153112(callbackParam, templateCode);
        }
    }

    /**
     * 处理买家
     * 
     * @param callbackParam
     */
    private void dealCPSO153102(PrintParam callbackParam, String templateCode) {
        RsRequest<ISO153105Param> requestParam = new RsRequest<ISO153105Param>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        ISO153105Param iso153105Param = new ISO153105Param();
        JSONObject jsStr = JSONObject.parseObject(callbackParam.getCallBackParam());
        if (jsStr != null) {
            iso153105Param.setBuyerBillId(StringUtil.toSafeString(jsStr.get("buyerBillId")));
            iso153105Param.setSrcPage(StringUtil.toSafeString(jsStr.get("srcPage")));
            iso153105Param.setTemplateCode(templateCode);
        }
        requestParam.setParam(iso153105Param);
        // url
        String url = callbackParam.getPrintCallBackUrl();
        // 调用接口
        RsResponse<ISO153105Result> response = RestClientUtil.post(url, requestParam,
            new TypeReference<RsResponse<ISO153105Result>>() {});
        /* 改善 #2107 modify by li_huiqian on 2016/8/25 start */
        requestParam.getParam().setTemplateCode(templateCode + "B");
        RsResponse<ISO153105Result> responseB = RestClientUtil.post(url, requestParam,
            new TypeReference<RsResponse<ISO153105Result>>() {});
        if (response.getResult() != null
                || SystemConst.RsStatus.SUCCESS.equals(response.getStatus()) && responseB.getResult() != null
                || SystemConst.RsStatus.SUCCESS.equals(responseB.getStatus())) {
            this.reportData = new ArrayList<Serializable>();
            this.reportParam = new HashMap<>();

            if (response.getResult().getOrder() == null) {
                response.getResult().setOrder(new ISO151416OrderRestResult());
            }
            if (response.getResult().getOrder().getReceiveInfo() == null) {
                response.getResult().getOrder().setReceiveInfo(new ISO151416ReceiveInfoRestResult());
            }

            String province = response.getResult().getOrder().getReceiveInfo().getReceiverProvince();
            String city = response.getResult().getOrder().getReceiveInfo().getReceiverCity();
            String district = response.getResult().getOrder().getReceiveInfo().getReceiverDistrict();
            String addr = response.getResult().getOrder().getReceiveInfo().getReceiverAddr();
            String address = StringUtil.isEmpty(province) ? "" : (province + StringConst.COMMA + StringConst.BLANK);
            address += StringUtil.isEmpty(city) ? "" : (city + StringConst.COMMA + StringConst.BLANK);
            address += StringUtil.isEmpty(district) ? "" : (district + StringConst.COMMA + StringConst.BLANK);
            address += StringUtil.isEmpty(addr) ? "" : addr;

            String remark1 = response.getResult().getOrder().getReceiveInfo().getRemark();
            String remark2 = response.getResult().getOrder().getReceiveInfo().getRemark2();
            String remark3 = response.getResult().getOrder().getReceiveInfo().getRemark3();
            String remark4 = response.getResult().getOrder().getReceiveInfo().getRemark4();
            String remark = StringUtil.isEmpty(remark1) ? "" : (remark1 + StringConst.COMMA + StringConst.BLANK);
            remark += StringUtil.isEmpty(remark2) ? "" : (remark2 + StringConst.COMMA + StringConst.BLANK);
            remark += StringUtil.isEmpty(remark3) ? "" : (remark3 + StringConst.COMMA + StringConst.BLANK);
            remark += StringUtil.isEmpty(remark4) ? "" : remark4;

            reportParam.put("title", response.getResult().getTitle());
            reportParam.put("order", response.getResult().getOrder());
            reportParam.put("address", address);
            reportParam.put("remark", remark);
            reportParam.put("soBuyerBill", response.getResult().getSoBuyerBill());
            reportParam.put("refundList", responseB.getResult().getSoCpRefundList());
            reportData.addAll(response.getResult().getSoCpRunningList());
            // 获取子报表的文件流
            InputStream subInputStream = this.getClass()
                .getResourceAsStream("/pdf/template/cashPooling/SO153102B.jasper");
            reportParam.put("subInputStream", subInputStream);
            /* 改善 #2107 modify by li_huiqian on 2016/8/25 end */
        } else {
            this.reportParam = new HashMap<>();
            this.reportData = new ArrayList<Serializable>();
        }
    }

    /**
     * 处理麦家
     * 
     * @param callbackParam
     */
    private void dealCPSO153112(PrintParam callbackParam, String templateCode) {
        RsRequest<ISO153202Param> requestParam = new RsRequest<ISO153202Param>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        ISO153202Param iso153202Param = new ISO153202Param();
        JSONObject jsStr = JSONObject.parseObject(callbackParam.getCallBackParam());
        if (jsStr != null) {
            iso153202Param.setSellerBillId(StringUtil.toSafeString(jsStr.get("sellerBillId")));
            iso153202Param.setSrcPage(StringUtil.toSafeString(jsStr.get("srcPage")));
        }
        requestParam.setParam(iso153202Param);
        // url
        String url = callbackParam.getPrintCallBackUrl();
        // 设置URL 打印退款明细0
        if ("SO153112B".equals(templateCode)) {
            url = url + "B";
        }
        RsResponse<ISO153202Result> response = RestClientUtil.post(url, requestParam,
            new TypeReference<RsResponse<ISO153202Result>>() {});
        if (response.getResult() != null || SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            if ("SO153112B".equals(templateCode)) {
                this.reportData = new ArrayList<Serializable>();
                reportData.addAll(response.getResult().getSoCpRefundList());
                return;
            }
            this.reportParam = new HashMap<>();
            reportParam.put("soSellerBill", response.getResult().getSoSellerBill());
            reportParam.put("order", response.getResult().getOrder());
            this.reportData = new ArrayList<Serializable>();
            reportData.addAll(response.getResult().getSoCpRunningList());
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