package com.msk.so.controller;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.base.BaseController;
import com.msk.so.bean.*;
import com.msk.so.logic.SO153111Logic;
import com.msk.so.logic.SO153112Logic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 卖家资详情
 * Created by wu_honglei on 2016/5/4.
 */

@Controller
@RequestMapping("SO153112")
public class SO153112Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO153112Controller.class);

    @Autowired
    private SO153111Logic so153111Logic;

    @Autowired
    private SO153112Logic so153112Logic;

    /**
     * 卖家详情化页面
     */
    @RequestMapping(value = "search",
        method = RequestMethod.POST)
    public String init(Model model, String sellerBillId, String srcPage, String ver) {
        logger.debug("进入卖家资金池详情页面");

        // 卖家计费单详细信息
        SOCp153111Bean soSellerBill = this.so153111Logic.findSellerById(sellerBillId);

        // 交易流水
        List<SoCpRunningBean> soCpRunningList = this.so153112Logic.findSORunningBeanByBillId(sellerBillId);
        /** 添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 start */
        if (CollectionUtils.isNotEmpty(soCpRunningList)) {
            SO153102Bean totalPaidAmountBean = this.so153112Logic.getTotalPaidAmountInfo(sellerBillId);
            model.addAttribute("totalPaidAmountBean", totalPaidAmountBean);
        }
        /** 添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 end */

        if (soSellerBill != null) {
            BaseParam param = new BaseParam();
            param.getFilterMap().put("startTime", soSellerBill.getStartDate());
            param.getFilterMap().put("endTime", soSellerBill.getEndDate());
            param.getFilterMap().put("sellerBillId", sellerBillId);
            // 退款
            /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 start */
            List<SOCp153112Bean> soCpRefundList = this.so153112Logic.findSORefund(param);
            /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 end */
            model.addAttribute("soRefundList", soCpRefundList);
            /** 添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 start */
            if (CollectionUtils.isNotEmpty(soCpRefundList)) {
                SO153102Bean totalRefundAmountBean = this.so153112Logic.getTotalRefundAmountInfo(param);
                model.addAttribute("totalRefundAmountBean", totalRefundAmountBean);
            }
            /** 添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 end */
        }

        // //订单信息(假数据)
        // OrderBean orderInfo = new OrderBean();
        // orderInfo.setOrderCode("123456");
        // orderInfo.setOrderStatus("已完成");
        // orderInfo.setOrderSendTime(new Date());
        // orderInfo.setOrderReceiveTime(new Date());
        // orderInfo.setOrderTime(new Date());
        // orderInfo.setFinishTime(new Date());
        // orderInfo.setOrderReceiveAddr("南京市雨花台区");
        // orderInfo.setRemark("备注");
        // model.addAttribute("order",orderInfo);
        // model.addAttribute("paidTime","2016-05-13 20:12:13");

        model.addAttribute("soSellerBill", soSellerBill);
        model.addAttribute("soRunningList", soCpRunningList);
        model.addAttribute("srcPage", srcPage);
        model.addAttribute("sellerBillId", sellerBillId);
        /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 start */
        model.addAttribute("ver", ver);
        /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 end */

        // String printUrl = SystemServerManager.PrintServerManager.getPrintPdf() + "cashPooling/SO153112";
        // String callBackUrl = SystemServerManager.SoCashPoolingServerManage.getQuerySellerPDF();
        // model.addAttribute("printUrl", printUrl);
        // model.addAttribute("callBackUrl", callBackUrl);
        return "so/SO153112";
    }

    /**
     * 删除支付明细
     *
     * @param param
     */
    @RequestMapping(value = "deleteRunning",
        method = RequestMethod.POST)
    @ResponseBody
    public void deleteRunning(SOCp153112Param param) {
        param.setEmplId(getLoginUser().getEmplId());
        so153112Logic.deleteSoRunning(param);
    }

    /**
     * 应退款明细
     *
     * @param param
     */
    @RequestMapping(value = "deleteRefund",
        method = RequestMethod.POST)
    @ResponseBody
    public void deleteRefund(SOCp153112Param param) {
        param.setEmplId(getLoginUser().getEmplId());
        so153112Logic.deleteSoRefund(param);
    }
}