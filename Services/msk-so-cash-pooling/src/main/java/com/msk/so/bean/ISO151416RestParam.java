package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * 订单明细查询接口Param
 * 
 * @author li_huiqian
 *
 */
public class ISO151416RestParam extends BaseParam {
	
	private static final long serialVersionUID = 3300193728707503612L;
	
	/** 订单编码 */
	private String orderCode;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	
	
}
