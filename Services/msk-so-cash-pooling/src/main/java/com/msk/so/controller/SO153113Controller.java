package com.msk.so.controller;


import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.core.entity.SoCpSelCharging;
import com.msk.so.bean.SOCp153111Bean;
import com.msk.so.logic.SO153111Logic;
import com.msk.so.logic.SO153113Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 卖家计费明细页面
 * Created by wu_honglei on 2016/5/5.
 */

@Controller
@RequestMapping("SO153113")
public class SO153113Controller {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SO153113Controller.class);

    @Autowired
    private SO153111Logic so153111Logic;

    @Autowired
    private SO153113Logic so153113Logic;


    /**
     * 卖家计费初始化页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(Model model, String sellerBillId, String srcPage){
        logger.debug("进入卖家计费明细页面");

//        //订单信息(假数据)
//        OrderBean orderInfo = new OrderBean();
//        orderInfo.setOrderCode("123456");
//        orderInfo.setOrderStatus("已完成");
//        orderInfo.setOrderSendTime(new Date());
//        orderInfo.setOrderReceiveTime(new Date());
//        orderInfo.setOrderTime(new Date());
//        orderInfo.setFinishTime(new Date());
//        orderInfo.setOrderReceiveAddr("南京市雨花台区");
//        orderInfo.setRemark("备注");
//        model.addAttribute("order",orderInfo);
//        model.addAttribute("paidTime","2016-05-13 20:12:13");



        //卖家计费单详细信息
        SOCp153111Bean soSellerBill = this.so153111Logic.findSellerById(sellerBillId);
        model.addAttribute("soSellerBill",soSellerBill);
        model.addAttribute("sellerBillIdHide",sellerBillId);
        model.addAttribute("srcPage", srcPage);
        return "so/SO153113";
    }




    /**
     * 卖家查询获得数据
     * @param pageParam BasePageParam
     * @return 卖家产品JSON数据
     */
    @RequestMapping(value = "search/{sellerBillIdHide}",method = RequestMethod.POST)
    public @ResponseBody
    PageResult<SoCpSelCharging> search(@PathVariable("sellerBillIdHide") String sellerBillIdHide,BasePageParam pageParam, Model model){
        logger.debug("卖家交易费用一览");
        DbUtils.buildLikeCondition(pageParam, "businessAssistantCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "businessAssistantName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "businessAssistantRole", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "transCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "remark", DbUtils.LikeMode.FRONT);

        pageParam.getFilterMap().put("sellerBillId",sellerBillIdHide);
        //交易类型
        String transType = StringUtil.toSafeString(pageParam.getFilterMap().get("transType"));
        if (!StringUtil.isNullOrEmpty(transType)) {
            String[] transTypes = transType.split(",");
            pageParam.getFilterMap().put("transTypes", transTypes);
        }

       //计费标志
        String chargeFlg = StringUtil.toSafeString(pageParam.getFilterMap().get("chargeFlg"));
        if (!StringUtil.isNullOrEmpty(chargeFlg)) {
            String[] chargeFlgs = chargeFlg.split(",");
            pageParam.getFilterMap().put("chargeFlgs", chargeFlgs);
        }

        return this.so153113Logic.findSelChargingPage(pageParam);
    }

    /**
     * 卖家计费项目明细详情
     */
    @RequestMapping(value = "detail",method = RequestMethod.POST)
    public String detail(Model model,String selChargingId){
        logger.debug("进入卖家计费明细详情页面");
        //卖家计费单详细信息
        SoCpSelCharging soCpSelCharging = this.so153113Logic.findSelChargingById(selChargingId);
        model.addAttribute("soSelCharging", soCpSelCharging);
        return "so/SO153114";
    }

}
