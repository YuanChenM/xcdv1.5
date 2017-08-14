package com.msk.so.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.bs.bean.IBS2101116Bean;
import com.msk.bs.bean.IBS2101116Param;
import com.msk.bs.bean.IBS2101116Result;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.bean.ISO151403RsParam;
import com.msk.order.bean.ISO151403RsResult;
import com.msk.order.bean.ISO151404RsParam;
import com.msk.order.bean.ISO151404RsResult;
import com.msk.order.bean.SellerListResult;
import com.msk.so.bean.ISO151416OrderRestResult;
import com.msk.so.bean.ISO151416RestParam;
import com.msk.so.bean.ISO151416RestResult;

/**
 * Created by zhang_chi on 2016/6/7.
 */
public class SOControllerUtil {

    /**
     * 获取 订单信息
     *
     * @param orderCode
     * @return
     */
    public static ISO151416OrderRestResult getOrderBean(String orderCode) {
        RsRequest<ISO151416RestParam> requestParam = new RsRequest<ISO151416RestParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");

        ISO151416RestParam param = new ISO151416RestParam();
        param.setOrderCode(orderCode);
        requestParam.setParam(param);

        String url = SystemServerManager.SoOrderServerManage.getQuerySdoDetail();
        // String url = "http://10.20.16.84:8080/msk-web/api/v1/so/sdo/detail";
        RsResponse<ISO151416RestResult> response = RestClientUtil.post(url, requestParam,
            new TypeReference<RsResponse<ISO151416RestResult>>() {});
        if (SystemConst.RsStatus.FAIL.equals(response.getStatus())) {
            throw new BusinessException(response.getReturnCode(), response.getMessage());
        }

        ISO151416RestResult result = response.getResult();
        if (result.getOrders().size() > NumberConst.IntDef.INT_ONE) {
            throw new BusinessException("数据异常，订单号：" + orderCode + " 对应多个订单");
        }

        ISO151416OrderRestResult order = null;
        if (result.getOrders().size() == NumberConst.IntDef.INT_ONE) {
            order = result.getOrders().get(NumberConst.IntDef.INT_ZERO);
        } else {
            order = new ISO151416OrderRestResult();
        }
        return order;
    }

    /**
     * 根据 订单号 获取 供应商信息
     *
     * @param transCode
     * @return
     */
    public static List<SellerListResult> getSellerListResult(String transCode) {
        RsRequest<ISO151403RsParam> requestParam = new RsRequest<ISO151403RsParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        ISO151403RsParam iso151403RsParam = new ISO151403RsParam();
        iso151403RsParam.setTransCode(transCode);
        requestParam.setParam(iso151403RsParam);
        String url = SystemServerManager.SoOrderServerManage.getQueryReceiptInfo();
        // String url = "http://10.20.16.84:8080/msk-web/api/v1/so/seller/search";
        RsResponse<ISO151403RsResult> response = RestClientUtil.post(url, requestParam,
            new TypeReference<RsResponse<ISO151403RsResult>>() {});
        ISO151403RsResult iso151403RsResult = response.getResult();
        if (null != iso151403RsResult) {
            List<SellerListResult> sellerList = iso151403RsResult.getSellerList();
            if (!CollectionUtils.isEmpty(sellerList)) {
                return sellerList;
            } else {
                return new ArrayList<SellerListResult>();
            }
        } else {
            return new ArrayList<SellerListResult>();
        }
    }

    /**
     * 验证订单号，退货号
     *
     * @param backNo
     * @param transCode
     * @return
     */
    public static Integer checkCodeBackNo(String backNo, String transCode) {
        RsRequest<ISO151404RsParam> requestParam = new RsRequest<ISO151404RsParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        ISO151404RsParam iso151404RsParam = new ISO151404RsParam();
        iso151404RsParam.setBackNo(backNo);
        iso151404RsParam.setTransCode(transCode);
        requestParam.setParam(iso151404RsParam);
        String url = SystemServerManager.SoOrderServerManage.getCheckReturnCode();
        // String url = "http://10.20.16.84:8080/msk-web/api/v1/so/returnCode/check";
        RsResponse<ISO151404RsResult> response = RestClientUtil.post(url, requestParam,
            new TypeReference<RsResponse<ISO151404RsResult>>() {});
        ISO151404RsResult iso151413RsResult = response.getResult();
        return iso151413RsResult.getCount();
    }

    /**
     * 查询买家对应的买手信息
     *
     * @param buyerIdList
     * @return
     */
    public static List<IBS2101116Bean> searchBuyerList(List<String> buyerIdList) {
        RsRequest<IBS2101116Param> requestParam = new RsRequest<IBS2101116Param>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        IBS2101116Param ibs2101116Param = new IBS2101116Param();
        ibs2101116Param.setBuyerIdList(buyerIdList);
        requestParam.setParam(ibs2101116Param);
        String url = SystemServerManager.BsServerManage.getSearchBuyerId();
        // String url = "http://10.20.16.152:8888/msk-bs/api/bs/searchBuyerId";
        RsResponse<IBS2101116Result> response = RestClientUtil.post(url, requestParam,
            new TypeReference<RsResponse<IBS2101116Result>>() {});
        IBS2101116Result ibs2101116Result = response.getResult();
        if (null != ibs2101116Result) {
            if (CollectionUtils.isNotEmpty(ibs2101116Result.getResultList())) {
                return ibs2101116Result.getResultList();
            } else {
                return new ArrayList<IBS2101116Bean>();
            }
        } else {
            return new ArrayList<IBS2101116Bean>();
        }
    }

}
