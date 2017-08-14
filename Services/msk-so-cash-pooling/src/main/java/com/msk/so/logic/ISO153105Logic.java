package com.msk.so.logic;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.msk.cashPooling.bean.ISO153105Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.config.CodeMasterManager;
import com.msk.so.bean.ISO151416OrderRestResult;
import com.msk.so.bean.ISO153105Result;
import com.msk.so.bean.SO153101Bean;
import com.msk.so.bean.SoCpRefundBean;
import com.msk.so.bean.SoCpRunningBean;
import com.msk.so.utils.CheckUtil;
import com.msk.so.utils.SOControllerUtil;

/**
 * Created by zhang_chi on 2016/8/2.
 */
@Service
public class ISO153105Logic extends BaseLogic {

	private static Logger logger = LoggerFactory.getLogger(ISO153105Logic.class);

	@Autowired
	private SO153101Logic so153101Logic;

	@Autowired
	private SO153102Logic so153102Logic;

	/**
	 * 查询买家详情打印PDF
	 *
	 * @param param
	 * @return
	 */
	@Transactional(readOnly = true)
	public ISO153105Result queryBuyerPDF(RsRequest<ISO153105Param> param) {
		ISO153105Result result = new ISO153105Result();
		ISO153105Param iso153105Param = param.getParam();
		if (iso153105Param == null) {
			return null;
		}
		String buyerBillId = StringUtil.toSafeString(iso153105Param.getBuyerBillId());
		if (StringUtil.isEmpty(buyerBillId)) {
			return null;
		}

		String templateCode = StringUtil.toSafeString(iso153105Param.getTemplateCode());
		if (StringUtil.isEmpty(templateCode)) {
			return null;
		}

		if ("SO153102".equals(templateCode)) {
			// 买家计费明细
			SO153101Bean soBuyerBill = querySoBuyerBill(buyerBillId, true);
			result.setSoBuyerBill(soBuyerBill);
			// 支付明细
			List<SoCpRunningBean> soCpRunningList = querySoCpRunningList(buyerBillId);
			result.setSoCpRunningList(soCpRunningList);

			// // 统计金额
			// if (CollectionUtils.isNotEmpty(soCpRunningList)) {
			// SO153102Bean so153102Bean
			// =this.so153102Logic.getTotalPaidAmountInfo(buyerBillId);
			// result.setSo153102Bean(so153102Bean);
			// }

			// 订单交易信息
			ISO151416OrderRestResult orderInfo = new ISO151416OrderRestResult();
			if (soBuyerBill != null) {
				String transCode = soBuyerBill.getTransCode();
				// 调接口 获取订单信息
				orderInfo = queryISO151416RsResult(transCode);
			}
			String srcPage = iso153105Param.getSrcPage();
			if ("SO153101".equals(srcPage)) {
				result.setTitle("买家详情");
			} else if ("SO153121".equals(srcPage)) {
				result.setTitle("买手详情");
			}
			result.setOrder(orderInfo);
		} else {
			// 买家计费明细
			SO153101Bean soBuyerBill = querySoBuyerBill(buyerBillId, false);
			if (soBuyerBill != null) {
				String transCode = soBuyerBill.getTransCode();
				// 应退款明细
				List<SoCpRefundBean> soCpRefundList = querySoCpRefundList(transCode);
				result.setSoCpRefundList(soCpRefundList);
				// // 统计金额
				// if (CollectionUtils.isNotEmpty(soCpRefundList)) {
				// SO153102Bean so153102Bean
				// =this.so153102Logic.getTotalRefundAmountInfo(transCode);
				// result.setSo153102Bean(so153102Bean);
				// }
			}
		}
		return result;
	}

	/**
	 * 获取订单信息
	 *
	 * @param transCode
	 * @return
	 */
	@Transactional(readOnly = true)
	private ISO151416OrderRestResult queryISO151416RsResult(String transCode) {
		// 调接口 获取订单信息
		ISO151416OrderRestResult orderInfo = SOControllerUtil.getOrderBean(transCode);
		if (null != orderInfo) {
			// 设置订单状态
			orderInfo.setOrderStatusStr("");
			Map<String, String> orderStatusMap = CodeMasterManager.findCodeMasterMap("OrderStatus");
			Integer orderStatus = orderInfo.getOrderStatus();
			if (orderStatus != null) {
				String value = orderStatusMap.get(String.valueOf(orderStatus));
				if (!StringUtil.isEmpty(value)) {
					orderInfo.setOrderStatusStr(value);
				}
			}
			// 设置时间
			SimpleDateFormat smd = new SimpleDateFormat(CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
			if (null != orderInfo.getOrderTime()) {
				orderInfo.setOrderTimeStr(smd.format(orderInfo.getOrderTime()));
			}
			if (null != orderInfo.getFinishTime()) {
				orderInfo.setFinishTimeStr(smd.format(orderInfo.getFinishTime()));
			}
		} else {
			orderInfo = new ISO151416OrderRestResult();
		}
		return orderInfo;
	}

	/**
	 * 买家计费明细
	 *
	 * @param buyerBillId
	 * @return
	 */
	@Transactional(readOnly = true)
	private SO153101Bean querySoBuyerBill(String buyerBillId, boolean checkFlag) {
		SO153101Bean soBuyerBill = this.so153101Logic.findBuyerById(buyerBillId);
		if (null != soBuyerBill) {
			if (checkFlag) {
				// 设置对账标志
				Map<String, String> statementFlgMap = CodeMasterManager.findCodeMasterMap("StatementFlg");
				String statementFlg = soBuyerBill.getStatementFlg();
				if (!StringUtil.isEmpty(statementFlg)) {
					String value = statementFlgMap.get(statementFlg);
					if (!StringUtil.isEmpty(value)) {
						soBuyerBill.setStatementFlgStr(value);
					}
				}
				// 设置结算状态
				Map<String, String> settlementStatusMap = CodeMasterManager.findCodeMasterMap("SettlementStatus");
				String settlementStatus = soBuyerBill.getSettlementStatus();
				if (!StringUtil.isEmpty(settlementStatus)) {
					String value = settlementStatusMap.get(settlementStatus);
					if (!StringUtil.isEmpty(value)) {
						soBuyerBill.setSettlementStatusStr(value);
					}
				}
				// 设置 冲抵核销标志
				Map<String, String> matchVerFlgMap = CodeMasterManager.findCodeMasterMap("MatchVerFlg");
				String matchVerFlg = soBuyerBill.getMatchVerFlg();
				if (!StringUtil.isEmpty(matchVerFlg)) {
					String value = matchVerFlgMap.get(matchVerFlg);
					if (!StringUtil.isEmpty(value)) {
						soBuyerBill.setMatchVerFlgStr(value);
					}
				}
				// 设置 支付类型
				Map<String, String> paymentTypeMap = CodeMasterManager.findCodeMasterMap("PaymentType");
				String paymentType = soBuyerBill.getPaymentType();
				if (!StringUtil.isEmpty(paymentType)) {
					String value = paymentTypeMap.get(paymentType);
					if (!StringUtil.isEmpty(value)) {
						soBuyerBill.setPaymentTypeStr(value);
					}
				}
			}
		} else {
			soBuyerBill = new SO153101Bean();
		}
		return soBuyerBill;
	}

	/**
	 * 支付明细
	 *
	 * @param buyerBillId
	 * @return
	 */
	@Transactional(readOnly = true)
	private List<SoCpRunningBean> querySoCpRunningList(String buyerBillId) {
		List<SoCpRunningBean> soCpRunningList = this.so153102Logic.findSORunningByBillId(buyerBillId);
		if (CollectionUtils.isNotEmpty(soCpRunningList)) {
			// 设置时间
			SimpleDateFormat smd = new SimpleDateFormat(CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
			// 支付方式
			Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
			for (SoCpRunningBean bean : soCpRunningList) {
				// 设置支付方式
				String paidType = bean.getPaidType() + "";
				if (!StringUtil.isEmpty(paidType)) {
					String value = paidTypeMap.get(paidType);
					if (!StringUtil.isEmpty(value)) {
						bean.setPaidTypeStr(value);
					}
				}
				// 设置支付日期
				if (null != bean.getPaidTime()) {
					bean.setPaidTimeStr(smd.format(bean.getPaidTime()));
				}
			}
		}
		return soCpRunningList;
	}

	/**
	 * 应退款明细
	 *
	 * @param transCode
	 * @return
	 */
	@Transactional(readOnly = true)
	private List<SoCpRefundBean> querySoCpRefundList(String transCode) {
		BaseParam params = new BaseParam();
		params.getFilterMap().put("transCode", transCode);
		// 应退款明细
		List<SoCpRefundBean> soCpRefundList = this.so153102Logic.findSORefund(params);
		if (CollectionUtils.isNotEmpty(soCpRefundList)) {
			// 设置时间
			SimpleDateFormat smd = new SimpleDateFormat(CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
			// 退回费用类型
			Map<String, String> refundTypeMap = CodeMasterManager.findCodeMasterMap("RefundType");
			for (SoCpRefundBean bean : soCpRefundList) {
				// 设置 退回费用类型
				String refundType = bean.getRefundType() + "";
				if (!StringUtil.isEmpty(refundType)) {
					String value = refundTypeMap.get(refundType);
					if (!StringUtil.isEmpty(value)) {
						bean.setRefundTypeStr(value);
					}
				}
				// 设置 退款日期
				if (null != bean.getRefundTime()) {
					bean.setRefundTimeStr(smd.format(bean.getRefundTime()));
				}
			}
		}
		return soCpRefundList;
	}

}
