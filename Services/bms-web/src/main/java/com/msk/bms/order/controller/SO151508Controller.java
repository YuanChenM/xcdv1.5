package com.msk.bms.order.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.bms.order.controller.common.SoRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.order.bean.result.ISO151414DeliveryReq;
import com.msk.order.bean.result.ISO151414ReceiverInfo;
import com.msk.order.bean.param.ISO151414OrderDetailParam;
import com.msk.order.bean.param.ISO151414BaseOrderParam;
import com.msk.order.bean.param.ISO151414DistrictParam;
import com.msk.order.bean.param.SO151508Param;
import com.msk.order.bean.result.DistrictResult;
import com.msk.order.bean.result.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * SO151508_订单新增画面
 * Created by wang_jianzhou on 2016/8/1.
 */
@Controller
@RequestMapping("SO151508")
public class SO151508Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO151508Controller.class);

    @RequestMapping("init")
    public String init(Model model){
        //调用物流区域接口
        logger.info("调取物流区接口信息");
        ISO151414DistrictParam param = new ISO151414DistrictParam();
        RsResponse<DistrictResult> lgcsAreaBeanList = SoRestUtil.getLogisticsAreaList(param);
        List<LgcsAreaBean> districtList  = lgcsAreaBeanList.getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList",districtList);
        logger.info("调取物流区接口信息结束");
        return "order/SO151508";
    }

    /**
     * 订单数据插入操作
     * @param param 订单基本数据
     * @return 操作成功跳转到List页面
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public String save(SO151508Param param){
        String buyerId = getBuyerId(param);
        ISO151414BaseOrderParam iso151414BaseOrderParam = new ISO151414BaseOrderParam();
        if(StringUtil.isEmpty(buyerId)){
            return "该买家不存在!";
        }
        if(CollectionUtils.isEmpty(param.getOrderDetailList())){
            return "请选择产品!";
        }
        param.setBuyerId(buyerId);
        param.setBuyersId(buyerId);
        iso151414BaseOrderParam = this.setParam(param);
        String loginId = getLoginUser().getEmplId();
        String status = SoRestUtil.createOrder(param.getOrder().getOrderType(),iso151414BaseOrderParam,loginId);
        if("S".equals(status)){
            return null;
        }
        return "创建订单失败: " + status + "!";
    }

    /**
     * 根据买家编码/买手编码，查询买家ID/买手ID
     * @param param
     * @return buyerId 买家/买手ID
     */
    private String getBuyerId(SO151508Param param){
        String url = "";
        String buyerCode = param.getOrder().getBuyerCode();
        Integer orderType = param.getOrder().getOrderType();
        param.setBuyerCode(buyerCode);
        //"http://10.20.16.83:8884/msk-buyers/api/by/buyerId/queryByBuyerCode";
        url = SystemServerManager.BuyersServerManage.getQueryBuyerIdByBuyerCode();
        if(orderType.equals(NumberConst.IntDef.INT_FOUR) || orderType.equals(NumberConst.IntDef.INT_SEVEN)){
            //查询买手编码
            //"http://10.20.16.83:8880/msk-bs/api/bs/slInfo/slAccount/search"
            url = SystemServerManager.BsServerManage.getQueryShopAccount();
            param.setSlCodeDis(buyerCode);
        }
        String buyerId = SoRestUtil.getBuyerIdByBuyerCode(param,url);
        return buyerId;
    }

    /**
     * 设置参数
     * @param param 订单基本数据
     * @return 设置好的参数
     */
    private ISO151414BaseOrderParam setParam(SO151508Param param){

        ISO151414BaseOrderParam iso151414BaseOrderParam = new ISO151414BaseOrderParam();
        ISO151414ReceiverInfo iso151414ReceiverInfo = new ISO151414ReceiverInfo();
        ISO151414DeliveryReq iso151414DeliveryReq = new ISO151414DeliveryReq();


        iso151414ReceiverInfo.setReceiverName(param.getOrderReceiveDemand().getReceiverName());
        iso151414ReceiverInfo.setReceiverTel(param.getOrderReceiveDemand().getReceiverTel());
        iso151414ReceiverInfo.setReceiverQQ(param.getOrderReceiveDemand().getReceiverQq());
        iso151414ReceiverInfo.setReceiverWeixin(param.getOrderReceiveDemand().getReceiverWeixin());
        iso151414ReceiverInfo.setReceiverProvince(param.getOrderReceiveDemand().getReceiverProvince());
        iso151414ReceiverInfo.setReceiverCity(param.getOrderReceiveDemand().getReceiverCity());
        iso151414ReceiverInfo.setReceiverDistrict(param.getOrderReceiveDemand().getReceiverDistrict());
        iso151414ReceiverInfo.setReceiverAddr(param.getOrderReceiveDemand().getReceiverAddr());
        iso151414ReceiverInfo.setReceiveEarliest(param.getReceiveEarliest());
        iso151414ReceiverInfo.setReceiveLast(param.getReceiveLast());
        iso151414ReceiverInfo.setReceiveTime(param.getNewReceiveTime());
        iso151414DeliveryReq.setOtherDeliveryReq(param.getOrderReceiveDemand().getOtherDeliveryReq());
        iso151414DeliveryReq.setThisDeliveryReq(param.getOrderReceiveDemand().getThisDeliveryReq());
        iso151414DeliveryReq.setPackingReq(param.getOrderReceiveDemand().getPackingReq());
        iso151414DeliveryReq.setParkingDistance(param.getOrderReceiveDemand().getParkingDistance());
        iso151414DeliveryReq.setUnloadReq(param.getOrderReceiveDemand().getUnloadReq());
        iso151414DeliveryReq.setVicFlg(param.getOrderReceiveDemand().getVicFlg());

        List<ISO151414OrderDetailParam> detailParams = new ArrayList<>();

        for(int i = 0; i<param.getOrderDetailList().size(); i++){
            ISO151414OrderDetailParam iso151414OrderDetailParam = new ISO151414OrderDetailParam();
            if(!StringUtil.isEmpty(param.getOrderDetailList().get(i).getSupplierCode())){
                iso151414OrderDetailParam.setSupplierCode(param.getOrderDetailList().get(i).getSupplierCode());
                iso151414OrderDetailParam.setSupplierName(param.getOrderDetailList().get(i).getSupplierName());
            }

            iso151414OrderDetailParam.setPdCode(param.getOrderDetailList().get(i).getPdCode());
            iso151414OrderDetailParam.setPdName(param.getOrderDetailList().get(i).getPdName());
            iso151414OrderDetailParam.setOrderQty(param.getOrderDetailList().get(i).getActiveQty());
            iso151414OrderDetailParam.setOrderPrice(param.getOrderDetailList().get(i).getPrice().multiply(param.getOrderDetailList().get(i).getActiveQty()));
            iso151414OrderDetailParam.setSupplierCode(param.getOrderDetailList().get(i).getSupplierCode());
            iso151414OrderDetailParam.setPriceCycle(param.getOrderDetailList().get(i).getPriceCyclePeriod());
            iso151414OrderDetailParam.setProDate(DateTimeUtil.parseDate(param.getOrderDetailList().get(i).getProDate(),"yyyy-MM-dd"));
            detailParams.add(iso151414OrderDetailParam);
        }
        iso151414BaseOrderParam.setProducts(detailParams);
        iso151414BaseOrderParam.setReceiverInfo(iso151414ReceiverInfo);
        iso151414BaseOrderParam.setDeliveryReq(iso151414DeliveryReq);
        iso151414BaseOrderParam.setSellerCode(param.getOrder().getSellerCode());
        iso151414BaseOrderParam.setSellerName(param.getOrder().getSellerName());
        iso151414BaseOrderParam.setDistrictCode(param.getOrder().getDistrictCode());
        iso151414BaseOrderParam.setBuyersType(param.getOrder().getBuyerType());
        iso151414BaseOrderParam.setOrderAmount(param.getOrder().getOrderAmount());
        iso151414BaseOrderParam.setPaymentType(param.getOrder().getPaymentType());
        iso151414BaseOrderParam.setBuyersCode(param.getOrder().getBuyerCode());
        iso151414BaseOrderParam.setBuyersName(param.getOrder().getBuyerName());
        iso151414BaseOrderParam.setBuyersId(param.getBuyersId());
        iso151414BaseOrderParam.setOrderSource(param.getOrder().getOrderSource());
        iso151414BaseOrderParam.setSalePlatform(param.getOrder().getSalePlatform());
        return iso151414BaseOrderParam;
    }
}
