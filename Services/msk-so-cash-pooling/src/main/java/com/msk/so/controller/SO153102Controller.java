package com.msk.so.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.base.BaseController;
import com.msk.so.bean.ISO151416OrderRestResult;
import com.msk.so.bean.SO153101Bean;
import com.msk.so.bean.SO153102Bean;
import com.msk.so.bean.SO153102Param;
import com.msk.so.bean.SoCpRefundBean;
import com.msk.so.bean.SoCpRunningBean;
import com.msk.so.logic.SO153101Logic;
import com.msk.so.logic.SO153102Logic;
import com.msk.so.utils.SOControllerUtil;

/**
 * 买家订单详情
 *
 * @author yang_yang
 * @version 1.0
 */
@Controller
@RequestMapping("SO153102")
public class SO153102Controller extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(SO153102Controller.class);

	@Autowired
	private SO153101Logic so153101Logic;

	@Autowired
	private SO153102Logic so153102Logic;

	/**
	 * 买家交易详细信息
	 *
	 * @param model
	 * @param buyerBillId
	 * @return
	 */
	@RequestMapping(value = "init", method = RequestMethod.POST)
	public String init(Model model, String buyerBillId, String srcPage, String ver) {
		logger.debug("买家交易详细信息");

		// 订单信息

		// 买家交易详细信息
		SO153101Bean soBuyerBill = this.so153101Logic.findBuyerById(buyerBillId);

		// 交易流水
		List<SoCpRunningBean> soCpRunningList = this.so153102Logic.findSORunningByBillId(buyerBillId);
		/** 添加买家资金池详细页面合计 modify by renyi on 2016/8/11 start */
		if (CollectionUtils.isNotEmpty(soCpRunningList)) {
			SO153102Bean totalPaidAmountBean = this.so153102Logic.getTotalPaidAmountInfo(buyerBillId);
			model.addAttribute("totalPaidAmountBean", totalPaidAmountBean);
		}
		/** 添加买家资金池详细页面合计 modify by renyi on 2016/8/11 end */

		// 订单信息
		ISO151416OrderRestResult orderInfo = new ISO151416OrderRestResult();

		if (soBuyerBill != null) {
			BaseParam param = new BaseParam();
			param.getFilterMap().put("transCode", soBuyerBill.getTransCode());
			/** 添加买家资金池详细列表更新功能 modify by lihuiqian on 2016/8/31 start */
			model.addAttribute("transCode", soBuyerBill.getTransCode());
			/** 添加买家资金池详细列表更新功能 modify by lihuiqian on 2016/8/31 end */
			// 退款
			List<SoCpRefundBean> soCpRefundList = this.so153102Logic.findSORefund(param);
			model.addAttribute("soRefundList", soCpRefundList);
			/** 添加买家资金池详细页面合计 modify by renyi on 2016/8/11 start */
			if (CollectionUtils.isNotEmpty(soCpRefundList)) {
				SO153102Bean totalRefundAmountBean = this.so153102Logic
						.getTotalRefundAmountInfo(soBuyerBill.getTransCode());
				model.addAttribute("totalRefundAmountBean", totalRefundAmountBean);
			}
			/** 添加买家资金池详细页面合计 modify by renyi on 2016/8/11 end */

			// 调接口 获取订单信息
			orderInfo = SOControllerUtil.getOrderBean(soBuyerBill.getTransCode());
		}

		model.addAttribute("order", orderInfo);
		model.addAttribute("soBuyerBill", soBuyerBill);
		model.addAttribute("soRunningList", soCpRunningList);
		model.addAttribute("srcPage", srcPage);
		model.addAttribute("buyerBillId", buyerBillId);
		/** 添加买家资金池详细列表更新功能 modify by lihuiqian on 2016/8/31 start */
		model.addAttribute("ver", ver);
		/** 添加买家资金池详细列表更新功能 modify by lihuiqian on 2016/8/31 end */

		// String printUrl =
		// SystemServerManager.PrintServerManager.getPrintPdf() +
		// "cashPooling/SO153102";
		// String callBackUrl =
		// SystemServerManager.SoCashPoolingServerManage.getQueryBuyerPDF();
		// model.addAttribute("printUrl",
		// "http://10.20.16.101:9390/msk-print/print/pdf/cashPooling/SO153102");
		// model.addAttribute("callBackUrl",
		// "http://10.20.16.101:8080/api/buyer/pdf");
		// model.addAttribute("printUrl", printUrl);
		// model.addAttribute("callBackUrl", callBackUrl);
		return "so/SO153102";
	}

	/**
	 * 删除支付明细
	 * 
	 * @param param
	 */
	@RequestMapping(value = "deleteRunning", method = RequestMethod.POST)
	@ResponseBody
	public void deleteRunning(SO153102Param param) {
		param.setEmplId(getLoginUser().getEmplId());
		so153102Logic.deleteSoRunning(param);
	}

	/**
	 * 删除应付款明细
	 *
	 * @param param
	 */
	@RequestMapping(value = "deleteRefund", method = RequestMethod.POST)
	@ResponseBody
	public void deleteRefund(SO153102Param param) {
		param.setEmplId(getLoginUser().getEmplId());
		so153102Logic.deleteSoRefund(param);
	}

}
