package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

/**
 * Created by liu_tao2 on 2016/8/1.
 */
public class ISO151414ReceiverInfo extends BaseResult {

    /** 收货人名称 */
    private String receiverName;
    /** 收货人QQ号 */
    private String receiverQQ;
    /** 收货人微信号 */
    private String receiverWeixin;
    /** 收货人电话 */
    private String receiverTel;
    /** 收货地址省份 */
    private String receiverProvince;
    /** 收货地址市 */
    private String receiverCity;
    /** 收货地址区 */
    private String receiverDistrict;
    /** 收货人详细地址 */
    private String receiverAddr;
    /** 辅助地址项目 */
    private String receiverAddr2;
    /** 收货人详细地址管理Key */
    private String receiverAddrKey;
    /** 习惯正常收货时间段编码，多个时候，以逗号分隔。 */
    private String receiveTime;
    /** 习惯收货最早时间要求 */
    private String receiveEarliest;
    /** 习惯收货最晚时间要求 */
    private String receiveLast;
    /** 备注 */
    private String remark;
    /** 备注2 */
    private String remark2;
    /** 备注3 */
    private String remark3;
    /** 备注4 */
    private String remark4;

    /** 距离门店最近停车距离(米) */
    private Integer parkingDistance;

    public Integer getParkingDistance() {
        return parkingDistance;
    }

    public void setParkingDistance(Integer parkingDistance) {
        this.parkingDistance = parkingDistance;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverQQ() {
        return receiverQQ;
    }

    public void setReceiverQQ(String receiverQQ) {
        this.receiverQQ = receiverQQ;
    }

    public String getReceiverWeixin() {
        return receiverWeixin;
    }

    public void setReceiverWeixin(String receiverWeixin) {
        this.receiverWeixin = receiverWeixin;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

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

    public String getReceiverAddr2() {
        return receiverAddr2;
    }

    public void setReceiverAddr2(String receiverAddr2) {
        this.receiverAddr2 = receiverAddr2;
    }

    public String getReceiverAddrKey() {
        return receiverAddrKey;
    }

    public void setReceiverAddrKey(String receiverAddrKey) {
        this.receiverAddrKey = receiverAddrKey;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiveEarliest() {
        return receiveEarliest;
    }

    public void setReceiveEarliest(String receiveEarliest) {
        this.receiveEarliest = receiveEarliest;
    }

    public String getReceiveLast() {
        return receiveLast;
    }

    public void setReceiveLast(String receiveLast) {
        this.receiveLast = receiveLast;
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
