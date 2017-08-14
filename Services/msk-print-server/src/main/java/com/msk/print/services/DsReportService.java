package com.msk.print.services;



import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.msk.comm.consts.SystemConst;
import com.msk.print.bean.*;
import com.msk.print.utils.RestClientUtil;
import com.msk.print.utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by li_kai1 on 16/8/5.
 */
public class DsReportService implements ReportDataService{
    private Map<String,Object> reportParam;
    private Collection<Serializable> reportData;

    @Override
    public void init(String templateCode, PrintParam callbackParam) {
        RsRequest<ISC182102Param> requestParam = new RsRequest<ISC182102Param>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        ISC182102Param isc182102Param = new ISC182102Param();
        JSONObject jsStr = JSONObject.parseObject(callbackParam.getCallBackParam());
        if (jsStr != null){
            isc182102Param.setDeliveryStockId(StringUtil.toSafeString(jsStr.get("deliveryStockId")));
            isc182102Param.setEdit(jsStr.getBoolean("isEdit"));
            isc182102Param.setSourceFlg(StringUtil.toSafeString(jsStr.get("sourceFlg")));
        }
        requestParam.setParam(isc182102Param);
        //url
        String url  = callbackParam.getPrintCallBackUrl();
        //调用接口
        RsResponse<ISC182102RsResult> response= RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<ISC182102RsResult>>() {
        });
        if (response.getResult() != null && SystemConst.RsStatus.SUCCESS.equals(response.getStatus())){
            this.reportParam = new HashMap<>();
            reportParam.put("182101Bean",response.getResult().getSc182102Info());

          //  reportParam.put("delivery",response.getResult().getDeliveryReq());
            this.reportData = new ArrayList<Serializable>();
            reportData.addAll(response.getResult().getDetailList());
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