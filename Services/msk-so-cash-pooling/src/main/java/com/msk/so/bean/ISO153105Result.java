package com.msk.so.bean;

import java.util.List;

import com.msk.core.entity.BaseEntity;

/**
 * Created by zhang_chi on 2016/8/1.
 */
public class ISO153105Result extends BaseEntity {

	/** 标题 */
	private String title;

	/** 订单信息 */
	private ISO151416OrderRestResult order;

	/** 买家交易详细信息 */
	private SO153101Bean soBuyerBill;

	/** 交易流水 */
	private List<SoCpRunningBean> soCpRunningList;

	/** 退款 */
	private List<SoCpRefundBean> soCpRefundList;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ISO151416OrderRestResult getOrder() {
		return order;
	}

	public void setOrder(ISO151416OrderRestResult order) {
		this.order = order;
	}

	public SO153101Bean getSoBuyerBill() {
		return soBuyerBill;
	}

	public void setSoBuyerBill(SO153101Bean soBuyerBill) {
		this.soBuyerBill = soBuyerBill;
	}

	public List<SoCpRunningBean> getSoCpRunningList() {
		return soCpRunningList;
	}

	public void setSoCpRunningList(List<SoCpRunningBean> soCpRunningList) {
		this.soCpRunningList = soCpRunningList;
	}

	public List<SoCpRefundBean> getSoCpRefundList() {
		return soCpRefundList;
	}

	public void setSoCpRefundList(List<SoCpRefundBean> soCpRefundList) {
		this.soCpRefundList = soCpRefundList;
	}
}
