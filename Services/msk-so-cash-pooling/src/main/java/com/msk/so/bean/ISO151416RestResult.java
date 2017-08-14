package com.msk.so.bean;

import java.util.List;

import com.msk.core.entity.BaseEntity;

/**
 * 订单明细查询接口Result
 * 
 * @author li_huiqian
 * @version 1.0
 */
public class ISO151416RestResult extends BaseEntity {

	private static final long serialVersionUID = 7797651725748551051L;

	private List<ISO151416OrderRestResult> orders;

	public List<ISO151416OrderRestResult> getOrders() {
		return orders;
	}

	public void setOrders(List<ISO151416OrderRestResult> orders) {
		this.orders = orders;
	}

}
