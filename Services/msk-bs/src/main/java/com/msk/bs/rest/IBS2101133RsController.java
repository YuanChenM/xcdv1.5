package com.msk.bs.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBS2101134RsBean;
import com.msk.bs.bean.IBS2101134RsParam;
import com.msk.bs.bean.IBS2101134RsResult;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.OrderConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.OrderConst;
import com.msk.common.utils.RestClientUtil;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by whc on 2016/10/18.
 */
@RestController
@Api(description = "查询委托订单详情RestController", tags = {"IBS2101133RsController"})
public class IBS2101133RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBS2101133RsController.class);

    @ApiOperation(value = "委托订单详情", notes = "委托订单详情接口")
    @RequestMapping(value = "/bs/orderDetail/_search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBS2101134RsResult> findOrderDetial(@RequestBody RsRequest<IBS2101134RsParam> request) {
        logger.debug("委托订单详情！");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");

        String url = SystemServerManager.SoOrderServerManage.getQueryBySdoDetail();
        RsResponse<IBS2101134RsResult> orders =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<IBS2101134RsResult>>() {
                });

        if(null != orders && null != orders.getResult() && !CollectionUtils.isEmpty(orders.getResult().getOrders())){

            Map<String,String> buyerType = CodeMasterManager.findCodeMasterMap(OrderConst.OrderBuyerType.TYPE);
            Map<String,String> orderSource = CodeMasterManager.findCodeMasterMap(OrderConstant.OrderSource.TYPE);
            Map<String,String> paymentType = CodeMasterManager.findCodeMasterMap(OrderConstant.PaymentType.TYPE);

            IBS2101134RsBean order = orders.getResult().getOrders().get(0);
            if(!StringUtil.isNullOrEmpty(order.getDistrictCode())){
                //获取物流区name
                DistrictParam param = new DistrictParam();
                String[] lgcsAreaCodes = new String[]{order.getDistrictCode()};
                param.setLgcsAreaCodes(lgcsAreaCodes);
                List<LgcsAreaBean> lgcsAreaList =  CommRestUtil.getLogisticsAreaList(param);
                if(!CollectionUtils.isEmpty(lgcsAreaList)){
                    orders.getResult().getOrders().get(0).setDistrictName(lgcsAreaList.get(0).getLgcsAreaName());
                }
            }
            if(!CollectionUtils.isEmpty(buyerType) && !StringUtil.isNullOrEmpty(order.getOrderSource())){
                if(buyerType.containsKey(order.getOrderSource())){
                    orders.getResult().getOrders().get(0).setOrderSourceName(buyerType.get(order.getOrderSource()));
                }
            }
            if(!CollectionUtils.isEmpty(orderSource) && null != order.getOrderType()){
                if(orderSource.containsKey(String.valueOf(order.getOrderType()))){
                    orders.getResult().getOrders().get(0).setOrderTypeName(orderSource.get(String.valueOf(order.getOrderType())));
                }
            }
            if(!CollectionUtils.isEmpty(paymentType) && null != order.getPaymentType()){
                if(paymentType.containsKey(String.valueOf(order.getPaymentType()))){
                    orders.getResult().getOrders().get(0).setPaymentTypeName(paymentType.get(String.valueOf(order.getPaymentType())));
                }
            }
        }
        return orders;
    }
}
