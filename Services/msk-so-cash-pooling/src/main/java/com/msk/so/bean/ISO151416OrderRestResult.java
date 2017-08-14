package com.msk.so.bean;

import java.util.Date;

import com.msk.core.entity.BaseEntity;

/**
 * 订单明细查询接口Result
 * 
 * @author li_huiqian
 * @version 1.0
 */
public class ISO151416OrderRestResult extends BaseEntity {

	private static final long serialVersionUID = 7797651725748551051L;

	/** 订单ID */
	private Long orderId;

	/** 订单编码 */
	private String orderCode;

	/** 订单创建时间 */
	private Date orderTime;

	/** 订单创建时间 */
	private String orderTimeStr;

	/** 订单状态 */
	private Integer orderStatus;

	/** 订单状态 */
	private String orderStatusStr;

	/** 订单完结时间 */
	private Date finishTime;

	/** 订单完结时间 */
	private String finishTimeStr;

	/** 收货信息 */
	private ISO151416ReceiveInfoRestResult receiveInfo;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public ISO151416ReceiveInfoRestResult getReceiveInfo() {
		return receiveInfo;
	}

	public void setReceiveInfo(ISO151416ReceiveInfoRestResult receiveInfo) {
		this.receiveInfo = receiveInfo;
	}

	public String getOrderTimeStr() {
		return orderTimeStr;
	}

	public void setOrderTimeStr(String orderTimeStr) {
		this.orderTimeStr = orderTimeStr;
	}

	public String getOrderStatusStr() {
		return orderStatusStr;
	}

	public void setOrderStatusStr(String orderStatusStr) {
		this.orderStatusStr = orderStatusStr;
	}

	public String getFinishTimeStr() {
		return finishTimeStr;
	}

	public void setFinishTimeStr(String finishTimeStr) {
		this.finishTimeStr = finishTimeStr;
	}

}
