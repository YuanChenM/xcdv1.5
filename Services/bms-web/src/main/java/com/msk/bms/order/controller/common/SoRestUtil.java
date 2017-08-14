package com.msk.bms.order.controller.common;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.CommCodeMasterConst;
import com.msk.common.consts.OrderConst;
import com.msk.common.utils.RestClientUtils;
import com.msk.order.bean.param.*;
import com.msk.order.bean.result.DistrictResult;
import com.alibaba.fastjson.TypeReference;
import com.msk.order.bean.result.ISO151414OrderResult;
import com.msk.order.bean.result.SO151502RestGetDisSellerCodesResult;
import com.msk.order.bean.result.SO15150801ProductStockBean;
import com.msk.order.entity.SoOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.msk.common.utils.RestClientUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


/**
 * Created by wang_jianzhou on 2016/8/10.
 */
public class SoRestUtil {
    private static Logger logger = LoggerFactory.getLogger(SoRestUtil.class);

    private static boolean isDebugger = false;

    /**
     * 查询物流区信息
     * @param ISO151414DistrictParam
     * @return
     */
    @Transactional
    public static RsResponse<DistrictResult> getLogisticsAreaList(ISO151414DistrictParam ISO151414DistrictParam){
        logger.info("调用物流区接口");
       ISO151414DistrictParam param = new ISO151414DistrictParam();
        RsRequest<ISO151414DistrictParam> request = new RsRequest<ISO151414DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("101");
        request.setParam(param);
        String districturl = SystemServerManager.DistrictServerManage.getDistrictQueryLgcsArea();
        //url = "http://localhost:8080/msk-district/api/district/query/lgcsArea";
        RsResponse<com.msk.order.bean.result.DistrictResult> lgcsAreaBeanList =
                RestClientUtil.post(districturl, request, new TypeReference<RsResponse<com.msk.order.bean.result.DistrictResult>>() {
                });
        logger.info("调用物流区接口结束");
        return  lgcsAreaBeanList;
    }

    /**
     * 根据买家/买手编码查询买家/买手ID
     * @param param
     * @return 买家/买手ID
     */
    @Transactional
    public static String getBuyerIdByBuyerCode(SO151508Param param,String url){
        RestRequest<SO151508Param> restRequest = new RestRequest<SO151508Param>();
        restRequest.setAuth("MSK00001");
        restRequest.setLoginId("msk01");
        restRequest.setSiteCode("101");
        restRequest.setParam(param);
        RestResponse<SO151508Param> rsResponse = RestClientUtils.post(url, restRequest, new TypeReference<RestResponse<SO151508Param>>() {
        });
        SO151508Param so151508Param = rsResponse.getResult();
        if(null != so151508Param && (!StringUtil.isEmpty(so151508Param.getBuyerId()))){
          return so151508Param.getBuyerId();
        }
        if(null != so151508Param && !CollectionUtils.isEmpty(so151508Param.getBuyershopList())){
            return so151508Param.getBuyershopList().get(NumberConst.IntDef.INT_ZERO).getSlCode();
        }
        return null;
    }

    /**
     * 调用创建订单接口
     * @param orderType 订单类型
     * @param iso151414BaseOrderParam 接口入参
     * @return 买家ID
     */
    @Transactional
    public static String createOrder(Integer orderType,ISO151414BaseOrderParam iso151414BaseOrderParam,String loginId){
        RestRequest<ISO151414BaseOrderParam> request = new RestRequest<ISO151414BaseOrderParam>();
        request.setAuth("MSK00001");
        request.setLoginId(loginId);
        request.setSiteCode(CommCodeMasterConst.SystemCode.SYSTEM_CODE_ORDER);
        request.setParam(iso151414BaseOrderParam);
        //调用创建订单接口
        String url = null;
        //分销订单
        if(orderType == OrderConst.OrderType.DISTRIBUTION_ORDER || orderType == OrderConst.OrderType.BUYER_SALE_ORDER){
            url = SystemServerManager.SoOrderApiServerManager.getCreateDistributeSdo();
            //url = "http://localhost:8888/msk-order-api/api/so/sdo/distribution/create";
        }
        //第三方订单
        if(orderType == OrderConst.OrderType.THIRD_PARTY_ORDER || orderType ==  OrderConst.OrderType.THIRD_BUYER_SALE_ORDER){
            url = SystemServerManager.SoOrderApiServerManager.getCreateThirdPartySdo();
            //url = "http://localhost:8888/msk-order-api/api/so/sdo/thirdparty/create";
        }
        //第三方买手囤货订单
        if(orderType == OrderConst.OrderType.THIRD_BUYER_ORDER){
            url = SystemServerManager.SoOrderApiServerManager.getCreateThirdBuyerSdo();
            //url = "http://localhost:8888/msk-order-api/api/so/sdo/thirdbuyer/create";
        }
        //买手囤货订单
        if(orderType == OrderConst.OrderType.BUYER_STOCKPILING_ORDER){
            url = SystemServerManager.SoOrderApiServerManager.getCreateBuyerSdo();
            //url = "http://localhost:8888/msk-order-api/api/so/sdo/buyer/create";
        }
        RestResponse<ISO151414OrderResult> rsResponse = RestClientUtils.post(url, request, new TypeReference<RestResponse<ISO151414OrderResult>>() {
        });

        if(!rsResponse.getStatus().equals("S")){
            return rsResponse.getMessage();
        }
        return "S";

    }

    /**
     * 调用查询卖家产品接口
     * @param pageParam 接口入参
     */
    public static RestResponse<SO15150801ProductStockBean> searchSlProduct(BasePageParam pageParam){
        String url = SystemServerManager.SoOrderApiServerManager.getQuerySellerProductInfo(); //"http://localhost:8889/msk-order-api/api/so/seller/product/_search";
        RestRequest<BasePageParam> request = new RestRequest<BasePageParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(pageParam);
        RestResponse<SO15150801ProductStockBean> rsResponse = RestClientUtils.post(url, request, new TypeReference<RestResponse<SO15150801ProductStockBean>>() {
        });
        return rsResponse;
    }

    /**
     * 调用查询供应商产品接口
     * @param pageParam 接口入参
     */
    public static RestResponse<SO15150801ProductStockBean> searchSpProduct(BasePageParam pageParam){
        String url = SystemServerManager.SoOrderApiServerManager.getQuerySupplierProductInfo();//"http://localhost:8889/msk-order-api/api/so/supplier/product/_search";
        RestRequest<BasePageParam> request = new RestRequest<BasePageParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("102");
        request.setParam(pageParam);
        RestResponse<SO15150801ProductStockBean> restResponse = RestClientUtils.post(url, request, new TypeReference<RestResponse<SO15150801ProductStockBean>>() {
        });
        return restResponse;
    }

    /**
     * 调用价盘接口查询产品单价
     * @param param 接口入参
     */
    public static RsResponse<SO15150801ProductStockBean> searchPdPrice(SO15150801Param param){
        RsRequest<SO15150801Param> request = new RsRequest<SO15150801Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SnkPriceServerManage.getGetPriceWayList();
        RsResponse<SO15150801ProductStockBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SO15150801ProductStockBean>>() {
        });
        return rsResponse;
    }



    /**
     * 调用卖家系统中 查询 供应商对应的 显示编码
     * @param param 接口入参
     */
    public static RsResponse<SO151502RestGetDisSellerCodesResult> getDisSellerCodes(SO151502RestGetDisSellerCodeSParam param){
        RsRequest<SO151502RestGetDisSellerCodeSParam> request = new RsRequest<SO151502RestGetDisSellerCodeSParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SellerServerManage.getSellerCodeSearch();
        RsResponse<SO151502RestGetDisSellerCodesResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SO151502RestGetDisSellerCodesResult>>() {
        });
        return rsResponse;
    }


}
