package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.Date;
import java.util.List;

/**
 * ISO151801_迟收退货数据接收接口
 * Created by chu_jian on 2016/8/2.
 */
public class ISO151801RestParam extends BaseParam {
    private Long orderId;
    private Long shipId;
    private String receiptDate;
    private Integer returnMode;
    private String applyMan;
    private String applyTime;
    private String applyRemark;
    private Integer returnReasonID;
    private String returnReasonName;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String buyerId;
    private String buyerCode;
    private String receiverName;
    private String receiverTel;
    private Integer isPaid;
    /**
     * 订单明细状态
     */
    private Integer detailStatus;
    /**
     * 供货明细状态
     */
    private Integer suppStatus;

    public Integer getSuppStatus() {
        return suppStatus;
    }

    public void setSuppStatus(Integer suppStatus) {
        this.suppStatus = suppStatus;
    }

    public Integer getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    private List<ISO151801RestShipParam> shipList;

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

    public List<ISO151801RestShipParam> getShipList() {
        return shipList;
    }

    public void setShipList(List<ISO151801RestShipParam> shipList) {
        this.shipList = shipList;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }
}
