package com.msk.stock.util;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import com.msk.stock.bean.StockResult;
import com.msk.stock.bean.StockRsParam;
import com.msk.stock.bean.StockRsParamList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang_chi on 2016/5/27.
 */
public class ControllerUtil {

    /**
     * 调接口查询卖家(显示)编码
     * @param isl231193RsParam
     * @return
     */
    public static List<ISL231193RsResult> getSlCode(ISL231193RsParam isl231193RsParam,int type){
        RsRequest<ISL231193RsParam> requestParam = new RsRequest<ISL231193RsParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        requestParam.setParam(isl231193RsParam);
        String url = "";
        if(NumberConst.IntDef.INT_ONE == type ){
            //url=localhost:8888/api/
           url = ConfigManager.getMskSellerService()+ConfigManager.getSellerSlSellerCodeSearchServices();
        }else if(NumberConst.IntDef.INT_TWO == type){
            //url=localhost:8888/api/
            url = ConfigManager.getMskSellerService()+ConfigManager.getSellerSlEpNameSearchServices();
        }
        RsResponse<ISL231193RsResult> response= RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<ISL231193RsResult>>() {
        });
        ISL231193RsResult  isl231193RsResult  =  response.getResult();
        if(null != isl231193RsResult){
            return  isl231193RsResult.getIsl231193RsList();
        }else{
            return new ArrayList<ISL231193RsResult>();
        }
    }



}
