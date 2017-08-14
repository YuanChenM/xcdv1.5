package com.msk.print.bean;

import com.msk.core.entity.BaseEntity;

/**
 * 订单明细查询接口Result
 * 
 * @author li_huiqian
 * @version 1.0
 */
public class ISO151416ReceiveInfoRestResult extends BaseEntity {

	private static final long serialVersionUID = 4570294248353861876L;

	/** 收货地址省份 */
	private String receiverProvince;

	/** 收货地址市 */
	private String receiverCity;

	/** 收货地址区 */
	private String receiverDistrict;

	/** 收货人详细地址 */
	private String receiverAddr;

	/** 备注 */
	private String remark;

	/** 备注2 */
	private String remark2;

	/** 备注3 */
	private String remark3;

	/** 备注4 */
	private String remark4;

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getReceiverAddr() {
		return receiverAddr;
	}

	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

}
