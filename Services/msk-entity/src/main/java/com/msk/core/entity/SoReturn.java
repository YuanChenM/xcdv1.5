/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_return对应的SoReturn。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoReturn extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 退货单ID */
    private Long returnId;
    /** 退货单编码 */
    private String returnCode;
    /** 订单ID */
    private Long orderId;
    /** 分批订单ID */
    private Long subOrderId;
    /** 分批订单编码 */
    private String subOrderCode;
    /** 订单编码 */
    private String orderCode;
    /** 买家名称 */
    private String buyerName;
    /** 买家编码 */
    private String buyerCode;
    /** 卖家编码 */
    private String sellerCode;
    /** 卖家名称 */
    private String sellerName;
    /** 订单所属物流区域 */
    private String districtCode;
    /** 退货单系统来源-CodeMaster
            1:平台 2:呼叫中心 3:手机客户端 */
    private Integer returnSource;
    /** 退货单类型-CodeMaster
            1-迟收；2-拒收；3-退货 */
    private String returnType;
    /** 退货总金额 */
    private java.math.BigDecimal returnAmount;
    /** 退货方式-CodeMaster，1：全部 2：部分 */
    private String returnMode;
    /** 退货申请人 */
    private String applyMan;
    /** 退货申请时间 */
    private java.util.Date applyTime;
    /** 退货备注 */
    private String applyRemark;
    /** 退货确认人 */
    private String confirmMan;
    /** 退货确认时间 */
    private java.util.Date confirmTime;
    /** 退货确认备注 */
    private String confirmRemark;
    /** 退货检测确认人 */
    private String checkMan;
    /** 退货检测确认时间 */
    private java.util.Date checkTime;
    /** 退货检测确认备注 */
    private String checkRemark;
    /** 退货状态 */
    private Integer returnStatus;
    /** 取消原因 */
    private String cancelReason;
    /** 退货原因照片1 */
    private String image1;
    /** 退货原因照片2 */
    private String image2;
    /** 退货原因照片3 */
    private String image3;
    /** 退货原因照片4 */
    private String image4;
    /** 退货原因照片5 */
    private String image5;
    /**
     * <p>默认构造函数。</p>
     */
    public SoReturn() {

    }

    /**
     * <p>退货单ID。</p>
     *
     * @return the 退货单ID
     */
    public Long getReturnId() {
        return returnId;
    }

    /**
     * <p>退货单ID。</p>
     *
     * @param returnId 退货单ID。
     */
    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    /**
     * <p>退货单编码。</p>
     *
     * @return the 退货单编码
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * <p>退货单编码。</p>
     *
     * @param returnCode 退货单编码。
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * <p>订单ID。</p>
     *
     * @return the 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @param orderId 订单ID。
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @return the 分批订单ID
     */
    public Long getSubOrderId() {
        return subOrderId;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @param subOrderId 分批订单ID。
     */
    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    /**
     * <p>分批订单编码。</p>
     *
     * @return the 分批订单编码
     */
    public String getSubOrderCode() {
        return subOrderCode;
    }

    /**
     * <p>分批订单编码。</p>
     *
     * @param subOrderCode 分批订单编码。
     */
    public void setSubOrderCode(String subOrderCode) {
        this.subOrderCode = subOrderCode;
    }

    /**
     * <p>订单编码。</p>
     *
     * @return the 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * <p>订单编码。</p>
     *
     * @param orderCode 订单编码。
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * <p>买家名称。</p>
     *
     * @return the 买家名称
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * <p>买家名称。</p>
     *
     * @param buyerName 买家名称。
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * <p>买家编码。</p>
     *
     * @return the 买家编码
     */
    public String getBuyerCode() {
        return buyerCode;
    }

    /**
     * <p>买家编码。</p>
     *
     * @param buyerCode 买家编码。
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    /**
     * <p>卖家编码。</p>
     *
     * @return the 卖家编码
     */
    public String getSellerCode() {
        return sellerCode;
    }

    /**
     * <p>卖家编码。</p>
     *
     * @param sellerCode 卖家编码。
     */
    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    /**
     * <p>卖家名称。</p>
     *
     * @return the 卖家名称
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * <p>卖家名称。</p>
     *
     * @param sellerName 卖家名称。
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * <p>订单所属物流区域。</p>
     *
     * @return the 订单所属物流区域
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * <p>订单所属物流区域。</p>
     *
     * @param districtCode 订单所属物流区域。
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * <p>退货单系统来源-CodeMaster
            1:平台 2:呼叫中心 3:手机客户端。</p>
     *
     * @return the 退货单系统来源-CodeMaster
            1:平台 2:呼叫中心 3:手机客户端
     */
    public Integer getReturnSource() {
        return returnSource;
    }

    /**
     * <p>退货单系统来源-CodeMaster
            1:平台 2:呼叫中心 3:手机客户端。</p>
     *
     * @param returnSource 退货单系统来源-CodeMaster
            1:平台 2:呼叫中心 3:手机客户端。
     */
    public void setReturnSource(Integer returnSource) {
        this.returnSource = returnSource;
    }

    /**
     * <p>退货单类型-CodeMaster
            1-迟收；2-拒收；3-退货。</p>
     *
     * @return the 退货单类型-CodeMaster
            1-迟收；2-拒收；3-退货
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * <p>退货单类型-CodeMaster
            1-迟收；2-拒收；3-退货。</p>
     *
     * @param returnType 退货单类型-CodeMaster
            1-迟收；2-拒收；3-退货。
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    /**
     * <p>退货总金额。</p>
     *
     * @return the 退货总金额
     */
    public java.math.BigDecimal getReturnAmount() {
        return returnAmount;
    }

    /**
     * <p>退货总金额。</p>
     *
     * @param returnAmount 退货总金额。
     */
    public void setReturnAmount(java.math.BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    /**
     * <p>退货方式-CodeMaster，1：全部 2：部分。</p>
     *
     * @return the 退货方式-CodeMaster，1：全部 2：部分
     */
    public String getReturnMode() {
        return returnMode;
    }

    /**
     * <p>退货方式-CodeMaster，1：全部 2：部分。</p>
     *
     * @param returnMode 退货方式-CodeMaster，1：全部 2：部分。
     */
    public void setReturnMode(String returnMode) {
        this.returnMode = returnMode;
    }

    /**
     * <p>退货申请人。</p>
     *
     * @return the 退货申请人
     */
    public String getApplyMan() {
        return applyMan;
    }

    /**
     * <p>退货申请人。</p>
     *
     * @param applyMan 退货申请人。
     */
    public void setApplyMan(String applyMan) {
        this.applyMan = applyMan;
    }

    /**
     * <p>退货申请时间。</p>
     *
     * @return the 退货申请时间
     */
    public java.util.Date getApplyTime() {
        return applyTime;
    }

    /**
     * <p>退货申请时间。</p>
     *
     * @param applyTime 退货申请时间。
     */
    public void setApplyTime(java.util.Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * <p>退货备注。</p>
     *
     * @return the 退货备注
     */
    public String getApplyRemark() {
        return applyRemark;
    }

    /**
     * <p>退货备注。</p>
     *
     * @param applyRemark 退货备注。
     */
    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    /**
     * <p>退货确认人。</p>
     *
     * @return the 退货确认人
     */
    public String getConfirmMan() {
        return confirmMan;
    }

    /**
     * <p>退货确认人。</p>
     *
     * @param confirmMan 退货确认人。
     */
    public void setConfirmMan(String confirmMan) {
        this.confirmMan = confirmMan;
    }

    /**
     * <p>退货确认时间。</p>
     *
     * @return the 退货确认时间
     */
    public java.util.Date getConfirmTime() {
        return confirmTime;
    }

    /**
     * <p>退货确认时间。</p>
     *
     * @param confirmTime 退货确认时间。
     */
    public void setConfirmTime(java.util.Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * <p>退货确认备注。</p>
     *
     * @return the 退货确认备注
     */
    public String getConfirmRemark() {
        return confirmRemark;
    }

    /**
     * <p>退货确认备注。</p>
     *
     * @param confirmRemark 退货确认备注。
     */
    public void setConfirmRemark(String confirmRemark) {
        this.confirmRemark = confirmRemark;
    }

    /**
     * <p>退货检测确认人。</p>
     *
     * @return the 退货检测确认人
     */
    public String getCheckMan() {
        return checkMan;
    }

    /**
     * <p>退货检测确认人。</p>
     *
     * @param checkMan 退货检测确认人。
     */
    public void setCheckMan(String checkMan) {
        this.checkMan = checkMan;
    }

    /**
     * <p>退货检测确认时间。</p>
     *
     * @return the 退货检测确认时间
     */
    public java.util.Date getCheckTime() {
        return checkTime;
    }

    /**
     * <p>退货检测确认时间。</p>
     *
     * @param checkTime 退货检测确认时间。
     */
    public void setCheckTime(java.util.Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * <p>退货检测确认备注。</p>
     *
     * @return the 退货检测确认备注
     */
    public String getCheckRemark() {
        return checkRemark;
    }

    /**
     * <p>退货检测确认备注。</p>
     *
     * @param checkRemark 退货检测确认备注。
     */
    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    /**
     * <p>退货状态。</p>
     *
     * @return the 退货状态
     */
    public Integer getReturnStatus() {
        return returnStatus;
    }

    /**
     * <p>退货状态。</p>
     *
     * @param returnStatus 退货状态。
     */
    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    /**
     * <p>取消原因。</p>
     *
     * @return the 取消原因
     */
    public String getCancelReason() {
        return cancelReason;
    }

    /**
     * <p>取消原因。</p>
     *
     * @param cancelReason 取消原因。
     */
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    /**
     * <p>退货原因照片1。</p>
     *
     * @return the 退货原因照片1
     */
    public String getImage1() {
        return image1;
    }

    /**
     * <p>退货原因照片1。</p>
     *
     * @param image1 退货原因照片1。
     */
    public void setImage1(String image1) {
        this.image1 = image1;
    }

    /**
     * <p>退货原因照片2。</p>
     *
     * @return the 退货原因照片2
     */
    public String getImage2() {
        return image2;
    }

    /**
     * <p>退货原因照片2。</p>
     *
     * @param image2 退货原因照片2。
     */
    public void setImage2(String image2) {
        this.image2 = image2;
    }

    /**
     * <p>退货原因照片3。</p>
     *
     * @return the 退货原因照片3
     */
    public String getImage3() {
        return image3;
    }

    /**
     * <p>退货原因照片3。</p>
     *
     * @param image3 退货原因照片3。
     */
    public void setImage3(String image3) {
        this.image3 = image3;
    }

    /**
     * <p>退货原因照片4。</p>
     *
     * @return the 退货原因照片4
     */
    public String getImage4() {
        return image4;
    }

    /**
     * <p>退货原因照片4。</p>
     *
     * @param image4 退货原因照片4。
     */
    public void setImage4(String image4) {
        this.image4 = image4;
    }

    /**
     * <p>退货原因照片5。</p>
     *
     * @return the 退货原因照片5
     */
    public String getImage5() {
        return image5;
    }

    /**
     * <p>退货原因照片5。</p>
     *
     * @param image5 退货原因照片5。
     */
    public void setImage5(String image5) {
        this.image5 = image5;
    }

}
