package com.msk.so.utils;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.SlSeller;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhang_chi on 2016/6/13.
 */
public class SORestUtil {

    /**
     * 调接口查询卖家(显示)编码
     *
     * @param slCode
     * @return
     */
    public static String getSlCodeDis(String slCode, String roleType) {
        RsRequest<ISL231193RsParam> requestParam = new RsRequest<ISL231193RsParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        ISL231193RsParam isl231193RsParam = new ISL231193RsParam();
        String url = "";
        // 查买手
        if (CapitalPoolConst.RoleType.ROLE_BUYERE.equals(roleType)) {
            List<SlSeller> slCodeList = new ArrayList<SlSeller>();
            SlSeller slSeller = new SlSeller();
            slSeller.setSlCode(slCode);
            slCodeList.add(slSeller);
            isl231193RsParam.setCondition(slCodeList);
            url = SystemServerManager.BsServerManage.getSearchBsInfo();
            // 0:注册了账号的所有买手 1：账号和基本信息都注册了的买手
            isl231193RsParam.setSlCodeFlag(1);
        }
        // 查卖家
        else {
            List<String> slCodeList = new ArrayList<String>();
            slCodeList.add(slCode);
            isl231193RsParam.setSlCodeList(slCodeList);
            url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlSellerCodeSearchServices();
        }
        requestParam.setParam(isl231193RsParam);
        RsResponse<ISL231193RsResult> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<ISL231193RsResult>>() {
        });
        ISL231193RsResult isl231193RsResult = response.getResult();
        if (null != isl231193RsResult) {
            List<ISL231193RsResult> results = new ArrayList<ISL231193RsResult>();
            // 处理买手
            if (CapitalPoolConst.RoleType.ROLE_BUYERE.equals(roleType)) {
                results = isl231193RsResult.getBuyershopList();
            }
            // 处理卖家
            else {
                results = isl231193RsResult.getIsl231193RsList();
            }
            if (CollectionUtils.isNotEmpty(results)) {
                ISL231193RsResult ISL231193RsResult = results.get(0);
                return ISL231193RsResult.getSlCodeDis();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 调接口查询卖家(显示)编码Map(Code, CodeDis)
     *
     * @param slCodeSeList
     * @param slCodeByList
     * @param roleType
     * @return
     */
    public static HashMap<String, String> getSlCodeDisList(List<String> slCodeSeList, List<SlSeller> slCodeByList, String roleType) {
        HashMap<String, String> slCodeDisMap = new HashMap<String, String>();
        RsRequest<ISL231193RsParam> requestParam = new RsRequest<ISL231193RsParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        ISL231193RsParam isl231193RsParam = new ISL231193RsParam();
        if (CollectionUtils.isNotEmpty(slCodeSeList)) {
            isl231193RsParam.setSlCodeList(slCodeSeList);
        }
        if (CollectionUtils.isNotEmpty(slCodeByList)) {
            isl231193RsParam.setCondition(slCodeByList);
        }
        requestParam.setParam(isl231193RsParam);
        String url = "";
        // 查买手
        if (CapitalPoolConst.RoleType.ROLE_BUYERE.equals(roleType)) {
            url = SystemServerManager.BsServerManage.getSearchBsInfo();
            // 0:注册了账号的所有买手 1：账号和基本信息都注册了的买手
            isl231193RsParam.setSlCodeFlag(1);
        }
        // 查卖家
        else {
            url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlSellerCodeSearchServices();
        }
        RsResponse<ISL231193RsResult> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<ISL231193RsResult>>() {
        });
        ISL231193RsResult isl231193RsResult = response.getResult();
        if (null != isl231193RsResult) {
            List<ISL231193RsResult> results = new ArrayList<ISL231193RsResult>();
            // 处理买手
            if (CapitalPoolConst.RoleType.ROLE_BUYERE.equals(roleType)) {
                results = isl231193RsResult.getBuyershopList();
            }
            // 处理卖家
            else {
                results = isl231193RsResult.getIsl231193RsList();
            }
            for (ISL231193RsResult result : results) {
                slCodeDisMap.put(result.getSlCode(), result.getSlCodeDis());
            }
        }
        return slCodeDisMap;
    }


}
