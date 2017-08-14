package com.msk.order.bean.param;


import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

import java.util.Date;
import java.util.List;

/**
 * 创建退货单Param
 *
 * @author zhangqiang1
 */
public class ISO151408RestParam extends BaseParam {
    // 订单ID
    private Long orderId;
    // 退货模式：1-全部，2-部分
    private Integer returnMode;
    // 申请人名称

    private String applyMan;
    // 申请时间

    private String applyTime;
    // 申请备注
    private String applyRemark;
    // 退货原因ID

    private Integer returnReasonID;
    // 退货原因名称

    private String returnReasonName;
    // 退货原因照片1文件
    private String image1;
    // 退货原因照片2文件
    private String image2;
    // 退货原因照片3文件
    private String image3;
    // 退货原因照片4文件
    private String image4;
    // 退货原因照片5文件
    private String image5;
    // 买家编码

    private String buyerCode;
    // 买家ID

    private String buyerId;
    // 收货人姓名
    private String receiverName;
    // 收货人电话
    private String receiverTel;
    // 是否已付款

    private Integer isPaid;
    // 发货明细
    private List<ISO151408RestShipListParam> shipList;

    public Integer getReturnMode() {
        return returnMode;
    }

    public void setReturnMode(Integer returnMode) {
        this.returnMode = returnMode;
    }

    public String getApplyMan() {
        return applyMan;
    }

    public void setApplyMan(String applyMan) {
        this.applyMan = applyMan;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    public Integer getReturnReasonID() {
        return returnReasonID;
    }

    public void setReturnReasonID(Integer returnReasonID) {
        this.returnReasonID = returnReasonID;
    }

    public String getReturnReasonName() {
        return returnReasonName;
    }

    public void setReturnReasonName(String returnReasonName) {
        this.returnReasonName = returnReasonName;
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

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public Integer getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    public List<ISO151408RestShipListParam> getShipList() {
        return shipList;
    }

    public void setShipList(List<ISO151408RestShipListParam> shipList) {
        this.shipList = shipList;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
