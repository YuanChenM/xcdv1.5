package com.msk.bms.order.controller;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.bms.order.controller.common.SoRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.bean.ISO151502Bean;
import com.msk.order.bean.param.ISO151410RestPram;
import com.msk.order.bean.param.SO151502RestGetDisSellerCodeSParam;
import com.msk.order.bean.result.ISO151410RestResult;
import com.msk.order.bean.result.SO151502RestGetDisSellerCodesResult;
import com.msk.order.entity.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 订单详情
 * zhangqiang1
 */
@Controller
@RequestMapping("SO151502")
public class SO151502Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO151502Controller.class);

    /**
     * 订单明细页面
     *
     * @param subOrderId
     * @param orderId
     * @param model
     * @return
     * @ param  subOrderAmount 分批订单金额
     */
    @RequestMapping(value = "init/{orderId}/{subOrderId}",
            method = RequestMethod.POST)
    public String init(@PathVariable("orderId") Long orderId, @PathVariable("subOrderId") Long subOrderId, Model model) throws Exception {
        logger.debug("订单明细详细信息");
        SoOrder soOrder = this.getSoOrder(orderId);
        List<SoSubOrder> subOrders = soOrder.getSoSubOrders();
        SoSubOrder subOrder = this.getSubOrderBySubOrderId(subOrderId, subOrders);
        List<SoSubOrderDetail> subOrderDetailList = subOrder.getSoSubOrderDetailList();
        List<SoOrderDetail> orderDetailList = soOrder.getSoOrderDetailList();
        String subOrderIds = this.getOtherSubOrderIds(subOrder.getSubOrderId(), subOrders);
        if (!StringUtil.isEmpty(subOrderIds)) {
            model.addAttribute("subOrderIds", subOrderIds);
        }
        List<ISO151502Bean> iso151502BeanList = this.get151502BeanBySubOrderDetailList(subOrderDetailList, orderDetailList);
        if (!CollectionUtils.isEmpty(iso151502BeanList)) {
            this.setSupplierCodeToSlCodeDis(iso151502BeanList);
            model.addAttribute("orderDetailList", iso151502BeanList);
        }
        model.addAttribute("orderCode", soOrder.getOrderCode());
        model.addAttribute("so151502Bean", soOrder);
        model.addAttribute("orderId", orderId);
        model.addAttribute("subOrderId", subOrderId);
        return "order/SO151502";
    }


    /**
     * 订单取消接口
     *
     * @param pram
     * @return
     */
    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    @ResponseBody
    public RsResponse<ISO151410RestResult> cancel(ISO151410RestPram pram) {
        pram.setCancelType(NumberConst.IntDef.INT_ONE);
        SoOrder soOrder = this.getSoOrder(pram.getOrderId());
        pram.setVer(soOrder.getVer());
        pram.setOrderType(soOrder.getOrderType());
        RsResponse<ISO151410RestResult> rsResponse = this.doCancel(pram);
        return rsResponse;
    }

    /**
     * 获取主订单信息
     *
     * @param param
     * @return
     */
    public RsResponse<ISO151410RestResult> doCancel(ISO151410RestPram param) {
        logger.debug("获取主订单信息");
        RsRequest<ISO151410RestPram> request = new RsRequest<ISO151410RestPram>();
        String loginId = getLoginUser().getEmplId();
        request.setAuth("MSK00001");
        request.setLoginId(loginId);
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SoOrderApiServerManager.getCancelTotalSoOrder();
     /*   String localUrl="http://localhost:8889/msk-order-api/api/so/sdo/cancel";*/
        RsResponse<ISO151410RestResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISO151410RestResult>>() {
        });
        return rsResponse;
    }


    /**
     * 根据SoSubOrderDetail    拼凑数据
     *
     * @param subOrderDetailList
     * @return
     */
    private List<ISO151502Bean> get151502BeanBySubOrderDetailList(List<SoSubOrderDetail> subOrderDetailList, List<SoOrderDetail> orderDetailList) throws Exception {
        List<ISO151502Bean> iso151502BeanList = new ArrayList<>();
        for (SoSubOrderDetail subOrderDetail : subOrderDetailList) {
            ISO151502Bean bean = new ISO151502Bean();
            BeanUtils.copyProperties(bean, subOrderDetail);
            List<SoOrderShipDetail> orderShipDetailList = this.getOrderShipDetailListBySubOrderDetailId(subOrderDetail.getSubOrderDetailId(), orderDetailList);
            bean.setOrderShipDetailList(orderShipDetailList);
            iso151502BeanList.add(bean);
        }
        return iso151502BeanList;
    }


    /**
     * 通过subOrderId 获取shipDetail集合
     *
     * @param subOrderDetailId
     * @param orderDetailList
     * @return
     */
    private List<SoOrderShipDetail> getOrderShipDetailListBySubOrderDetailId(Long subOrderDetailId, List<SoOrderDetail> orderDetailList) {
        List<SoOrderShipDetail> list = new ArrayList<>();
        for (SoOrderDetail orderDetail : orderDetailList) {
            List<SoOrderShipDetail> shipDetailList = orderDetail.getSoOrderShipDetailList();
            for (SoOrderShipDetail orderShipDetail : shipDetailList) {
                if (orderShipDetail.getSubOrderDetailId().longValue() == subOrderDetailId.longValue()) {
                    list.add(orderShipDetail);
                }
            }
        }
        return list;
    }


    /**
     * 获取其他subOrderId 用于页面友好提示
     *
     * @param subOrderId
     * @param subOrders
     * @return
     */
    public String getOtherSubOrderIds(Long subOrderId, List<SoSubOrder> subOrders) {
        String subOrderIds = "";
        for (SoSubOrder subOrder : subOrders) {
            if (subOrderId.longValue() != subOrder.getSubOrderId().longValue()) {
                subOrderIds = subOrder.getSubOrderId() + "    ";
            }
        }
        return subOrderIds;
    }


    /**
     * 查询主订单
     *
     * @param
     * @return
     */
    public SoOrder getSoOrder(Long orderId) {
        logger.debug("获取主订单信息数据");
        RsRequest<Long> request = new RsRequest<Long>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(orderId);
        String url = SystemServerManager.SoOrderApiServerManager.getFindPageOrderDetail();
      /*  String localUrl="http://localhost:8889/msk-order-api/api/so/order/order/_get/by/orderid";*/
        RsResponse<SoOrder> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SoOrder>>() {
        });
        return rsResponse.getResult();
    }


    /**
     * 把分批订单明细中的supplierCode 转为 slCodeDis
     *
     * @param iso151502BeanList
     */
    private void setSupplierCodeToSlCodeDis(List<ISO151502Bean> iso151502BeanList) {
        List<SoOrderShipDetail> shipDetailList = new ArrayList<>();
        for (ISO151502Bean bean : iso151502BeanList) {
            List<SoOrderShipDetail> iso151502BeanShipDetailList = bean.getOrderShipDetailList();
            shipDetailList.addAll(iso151502BeanShipDetailList);
        }
        this.setSellerCodeDis(shipDetailList);
    }

    /**
     * 根据subOrderId  获取subOrder
     *
     * @param subOrderId
     * @param subOrderList
     * @return
     */
    public SoSubOrder getSubOrderBySubOrderId(Long subOrderId, List<SoSubOrder> subOrderList) {
        SoSubOrder needSubSoOrder = null;
        for (SoSubOrder subOrder : subOrderList) {
            if (subOrder.getSubOrderId().longValue() == subOrderId.longValue()) {
                needSubSoOrder = subOrder;
                break;
            }
        }
        return needSubSoOrder;
    }


    /**
     * 根据supplierCode  调用接口 获取sellerCodeDis
     *
     * @param shipDetailList
     */
    private void setSellerCodeDis(List<SoOrderShipDetail> shipDetailList) {
        if (!CollectionUtils.isEmpty(shipDetailList)) {
            SO151502RestGetDisSellerCodeSParam param = new SO151502RestGetDisSellerCodeSParam();
            List<String> sellCodes = new ArrayList<>();
            for (SoOrderShipDetail shipDetail : shipDetailList) {
                String supplierCode = shipDetail.getSupplierCode();
                if (!StringUtil.isEmpty(supplierCode)) {
                    sellCodes.add(supplierCode);
                }
            }
            param.setSlCodeList(sellCodes);
            Map<String, String> map = this.getSlCodeMap(param);
            for (SoOrderShipDetail shipDetail : shipDetailList) {
                String supplierCode = shipDetail.getSupplierCode();
                if (!StringUtil.isEmpty(supplierCode)) {
                    shipDetail.setSupplierCode(map.get(supplierCode));
                }
            }
        }
    }

    /**
     * 通过slCode list 获取 slCodeDis
     *
     * @param param
     * @return
     */
    private Map<String, String> getSlCodeMap(SO151502RestGetDisSellerCodeSParam param) {
        HashMap<String, String> map = new HashMap<>();
        RsResponse<SO151502RestGetDisSellerCodesResult> rsResponse = SoRestUtil.getDisSellerCodes(param);
        if (SystemConst.RsStatus.SUCCESS.equals(rsResponse.getStatus())) {
            SO151502RestGetDisSellerCodesResult result = rsResponse.getResult();
            List<SO151502RestGetDisSellerCodesResult> isl231193RsList = result.getIsl231193RsList();
            for (SO151502RestGetDisSellerCodesResult slCodeDisResult : isl231193RsList) {
                map.put(slCodeDisResult.getSlCode(), slCodeDisResult.getSlCodeDis());
            }
        }
        return map;
    }


}
