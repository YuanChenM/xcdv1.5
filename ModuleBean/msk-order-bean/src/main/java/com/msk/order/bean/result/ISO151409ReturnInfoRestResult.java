package com.msk.order.bean.result;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/8/19.
 */
public class ISO151409ReturnInfoRestResult implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 退货单ID
     */
    private Integer returnId;
    /**
     * 退货单编码
     */
    private String returnCode;
    /**
     * 退货单创建时间
     */
    private Date crtTime;
    /**
     * 退货单状态
     */
    private Integer returnStatus;
    /**
     * 退货单来源,1:平台 2:呼叫中心 3:手机客户端
     */
    private Integer returnSource;
    /**
     * 退货类型,1:分销,2:第三方,3:大促会
     */
    private Integer returnType;
    /**
     * 退货单申请时间
     */
    private Date returnTime;
    /**
     * 退货总金额
     */
    private BigDecimal returnAmount;
    /**
     * 退货方式
     */
    private Integer returnMode;
    /**
     * 退货原因
     */
    private String returnReasonCode;
    /**
     * 退货原因问题描述
     */
    private String returnReasonDes;
    /**
     * 退货备注
     */
    private String remark;
    /**
     * 退货原因照片1（文件服务器绝对路径）
     */
    private String image1;
    /**
     * 退货原因照片2（文件服务器绝对路径）
     */
    private String image2;
    /**
     * 退货原因照片3（文件服务器绝对路径）
     */
    private String image3;
    /**
     * 退货原因照片4（文件服务器绝对路径）
     */
    private String image4;
    /**
     * 退货原因照片5（文件服务器绝对路径）
     */
    private String image5;
    /**
     * 是否已付款
     */
    private Integer iaPaid;
    /**
     * 是否已付款，1:已付款
     */
    private Integer isPaid;
    /**
     * 是否已退款
     */
    private Integer iaReturned;
    /**
     * 退款方式
     */
    private Integer refundMode;
    /**
     * 原订单ID
     */
    private Integer orderId;
    /**
     * 原订单编码
     */
    private String orderCode;
    /**
     * 原订单创建时间
     */
    private Date orderTime;

    /**
     * 版本号
     */
    private  Integer ver;

    /**
     *退货申请备注
     */
    private  String applyRemark;




    /**
     * 退货产品明细列表
     */
    private List<ISO151409ReturnDetailRestResult> returnDetails;


    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Integer getReturnSource() {
        return returnSource;
    }

    public void setReturnSource(Integer returnSource) {
        this.returnSource = returnSource;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public Integer getReturnMode() {
        return returnMode;
    }

    public void setReturnMode(Integer returnMode) {
        this.returnMode = returnMode;
    }

    public String getReturnReasonCode() {
        return returnReasonCode;
    }

    public void setReturnReasonCode(String returnReasonCode) {
        this.returnReasonCode = returnReasonCode;
    }

    public String getReturnReasonDes() {
        return returnReasonDes;
    }

    public void setReturnReasonDes(String returnReasonDes) {
        this.returnReasonDes = returnReasonDes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public Integer getIaPaid() {
        return iaPaid;
    }

    public void setIaPaid(Integer iaPaid) {
        this.iaPaid = iaPaid;
    }

    public Integer getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    public Integer getIaReturned() {
        return iaReturned;
    }

    public void setIaReturned(Integer iaReturned) {
        this.iaReturned = iaReturned;
    }

    public Integer getRefundMode() {
        return refundMode;
    }

    public void setRefundMode(Integer refundMode) {
        this.refundMode = refundMode;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
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


    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    public List<ISO151409ReturnDetailRestResult> getReturnDetails() {
        return returnDetails;
    }

    public void setReturnDetails(List<ISO151409ReturnDetailRestResult> returnDetails) {
        this.returnDetails = returnDetails;
    }
}
