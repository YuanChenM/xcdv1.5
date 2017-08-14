package com.msk.print.bean;

import java.io.Serializable;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151402RestReceiveInfoResult implements Serializable {
    /**
     *收货要求ID
     */
    private String id = "";
    /**
     *收货人名称
     */
    private String receiverName = "";
    /**
     *收货人QQ号
     */
    private String receiverQq = "";
    /**收货人微信号
     *
     */
    private String receiverWeixin = "";
    /**
     *收货人电话
     */
    private String receiverTel = "";
    /**
     *收货人邮箱
     */
    private String receiverMail = "";
    /**
     *收货地址省份
     */
    private String receiverProvince = "";
    /**
     *收货地址市
     */
    private String receiverCity = "";
    /**
     *收货地址区
     */
    private String receiverDistrict = "";
    /**
     *收货人详细地址
     */
    private String receiverAddr = "";
    /**
     *辅助地址项目
     */
    private String receiverAddr2 = "";
    /**
     *备注
     */
    private String remark = "";
    /**
     *备注2
     */
    private String remark2 = "";
    /**
     *备注3
     */
    private String remark3 = "";
    /**
     *备注4
     */
    private String remark4 = "";
    /**
     *收货人详细地址管理Key
     */
    private String receiverAddrKey = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverQq() {
        return receiverQq;
    }

    public void setReceiverQq(String receiverQq) {
        this.receiverQq = receiverQq;
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

    public String getReceiverMail() {
        return receiverMail;
    }

    public void setReceiverMail(String receiverMail) {
        this.receiverMail = receiverMail;
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

    public String getReceiverAddrKey() {
        return receiverAddrKey;
    }

    public void setReceiverAddrKey(String receiverAddrKey) {
        this.receiverAddrKey = receiverAddrKey;
    }


}
