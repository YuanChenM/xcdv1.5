package com.msk.ssc.bean;

import com.msk.core.entity.SscDifferDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xia_xiaojie on 2016/7/5.
 */
public class SSC11312Bean extends SscDifferDetail {
    private List<SSC11312Bean> differDetails;

    private Long deliveryId;

    private String productGrade;            //产品等级
    private String productName;             //产品名称（一级分类/二级分类/品种/特征/净重）

    private String sendBoxStr;              //发货箱数
    private String receiveBoxStr;           //收货箱数
    private String sendWeightStr;           //发货重量
    private String receiveWeightStr;        //收货重量
    private String sendAmountStr;           //发货金额
    private String receiveAmountStr;        //收货金额
    private String sendPriceStr;            //发货单价
    private String receivePriceStr;         //收货单价

    private BigDecimal priceDiff;           //单价差
    private BigDecimal amountDiff;          //金额差
    private BigDecimal weightDiff;          //重量差
    private BigDecimal totalAmountDiff;     //金额总差
    private BigDecimal totalWeightDiff;     //重量总差

    private String priceDiffStr;
    private String amountDiffStr;
    private String weightDiffStr;
    private String totalAmountDiffStr;
    private String totalWeightDiffStr;

    private int totalSendBoxes;             //发货总箱数
    private int totalReceiveBoxes;          //收货总箱数
    private BigDecimal totalSendWeight;     //发货总重量
    private BigDecimal totalReceiveWeight;  //收货总重量
    private BigDecimal totalSendAmount;     //发货总金额
    private BigDecimal totalReceiveAmount;  //收货总金额

    private String totalSendBoxesStr;
    private String totalReceiveBoxesStr;
    private String totalSendWeightStr;
    private String totalReceiveWeightStr;
    private String totalSendAmountStr;
    private String totalReceiveAmountStr;


    /*
     * 以下字段用于导出excel展示
     */

    private String  breedName ;     //产品名称
    private String  featureName;    //产品特征名称
    private String  weightVal;      //产品净重
    private String  normsName;      //产品包装规格名称
    private String  gradeName;      //产品等级
    private String  brandName;      //产品品牌
    private String weightName;      //产品净重名称

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public List<SSC11312Bean> getDifferDetails() {
        return differDetails;
    }

    public void setDifferDetails(List<SSC11312Bean> differDetails) {
        this.differDetails = differDetails;
    }

    public String getProductGrade() {
        return productGrade;
    }

    public void setProductGrade(String productGrade) {
        this.productGrade = productGrade;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSendBoxStr() {
        return sendBoxStr;
    }

    public void setSendBoxStr(String sendBoxStr) {
        this.sendBoxStr = sendBoxStr;
    }

    public String getReceiveBoxStr() {
        return receiveBoxStr;
    }

    public void setReceiveBoxStr(String receiveBoxStr) {
        this.receiveBoxStr = receiveBoxStr;
    }

    public String getSendWeightStr() {
        return sendWeightStr;
    }

    public void setSendWeightStr(String sendWeightStr) {
        this.sendWeightStr = sendWeightStr;
    }

    public String getReceiveWeightStr() {
        return receiveWeightStr;
    }

    public void setReceiveWeightStr(String receiveWeightStr) {
        this.receiveWeightStr = receiveWeightStr;
    }

    public String getSendAmountStr() {
        return sendAmountStr;
    }

    public void setSendAmountStr(String sendAmountStr) {
        this.sendAmountStr = sendAmountStr;
    }

    public String getReceiveAmountStr() {
        return receiveAmountStr;
    }

    public void setReceiveAmountStr(String receiveAmountStr) {
        this.receiveAmountStr = receiveAmountStr;
    }

    public String getSendPriceStr() {
        return sendPriceStr;
    }

    public void setSendPriceStr(String sendPriceStr) {
        this.sendPriceStr = sendPriceStr;
    }

    public String getReceivePriceStr() {
        return receivePriceStr;
    }

    public void setReceivePriceStr(String receivePriceStr) {
        this.receivePriceStr = receivePriceStr;
    }

    public String getPriceDiffStr() {
        return priceDiffStr;
    }

    public void setPriceDiffStr(String priceDiffStr) {
        this.priceDiffStr = priceDiffStr;
    }

    public BigDecimal getAmountDiff() {
        return amountDiff;
    }

    public void setAmountDiff(BigDecimal amountDiff) {
        this.amountDiff = amountDiff;
    }

    public BigDecimal getWeightDiff() {
        return weightDiff;
    }

    public void setWeightDiff(BigDecimal weightDiff) {
        this.weightDiff = weightDiff;
    }

    public BigDecimal getTotalAmountDiff() {
        return totalAmountDiff;
    }

    public void setTotalAmountDiff(BigDecimal totalAmountDiff) {
        this.totalAmountDiff = totalAmountDiff;
    }

    public BigDecimal getTotalWeightDiff() {
        return totalWeightDiff;
    }

    public void setTotalWeightDiff(BigDecimal totalWeightDiff) {
        this.totalWeightDiff = totalWeightDiff;
    }

    public String getAmountDiffStr() {
        return amountDiffStr;
    }

    public void setAmountDiffStr(String amountDiffStr) {
        this.amountDiffStr = amountDiffStr;
    }

    public String getWeightDiffStr() {
        return weightDiffStr;
    }

    public void setWeightDiffStr(String weightDiffStr) {
        this.weightDiffStr = weightDiffStr;
    }

    public String getTotalAmountDiffStr() {
        return totalAmountDiffStr;
    }

    public void setTotalAmountDiffStr(String totalAmountDiffStr) {
        this.totalAmountDiffStr = totalAmountDiffStr;
    }

    public String getTotalWeightDiffStr() {
        return totalWeightDiffStr;
    }

    public void setTotalWeightDiffStr(String totalWeightDiffStr) {
        this.totalWeightDiffStr = totalWeightDiffStr;
    }

    public int getTotalSendBoxes() {
        return totalSendBoxes;
    }

    public void setTotalSendBoxes(int totalSendBoxes) {
        this.totalSendBoxes = totalSendBoxes;
    }

    public int getTotalReceiveBoxes() {
        return totalReceiveBoxes;
    }

    public void setTotalReceiveBoxes(int totalReceiveBoxes) {
        this.totalReceiveBoxes = totalReceiveBoxes;
    }

    public BigDecimal getTotalSendWeight() {
        return totalSendWeight;
    }

    public void setTotalSendWeight(BigDecimal totalSendWeight) {
        this.totalSendWeight = totalSendWeight;
    }

    public BigDecimal getTotalReceiveWeight() {
        return totalReceiveWeight;
    }

    public void setTotalReceiveWeight(BigDecimal totalReceiveWeight) {
        this.totalReceiveWeight = totalReceiveWeight;
    }

    public BigDecimal getTotalSendAmount() {
        return totalSendAmount;
    }

    public void setTotalSendAmount(BigDecimal totalSendAmount) {
        this.totalSendAmount = totalSendAmount;
    }

    public BigDecimal getTotalReceiveAmount() {
        return totalReceiveAmount;
    }

    public void setTotalReceiveAmount(BigDecimal totalReceiveAmount) {
        this.totalReceiveAmount = totalReceiveAmount;
    }

    public String getTotalSendBoxesStr() {
        return totalSendBoxesStr;
    }

    public void setTotalSendBoxesStr(String totalSendBoxesStr) {
        this.totalSendBoxesStr = totalSendBoxesStr;
    }

    public String getTotalReceiveBoxesStr() {
        return totalReceiveBoxesStr;
    }

    public void setTotalReceiveBoxesStr(String totalReceiveBoxesStr) {
        this.totalReceiveBoxesStr = totalReceiveBoxesStr;
    }

    public String getTotalSendWeightStr() {
        return totalSendWeightStr;
    }

    public void setTotalSendWeightStr(String totalSendWeightStr) {
        this.totalSendWeightStr = totalSendWeightStr;
    }

    public String getTotalReceiveWeightStr() {
        return totalReceiveWeightStr;
    }

    public void setTotalReceiveWeightStr(String totalReceiveWeightStr) {
        this.totalReceiveWeightStr = totalReceiveWeightStr;
    }

    public String getTotalSendAmountStr() {
        return totalSendAmountStr;
    }

    public void setTotalSendAmountStr(String totalSendAmountStr) {
        this.totalSendAmountStr = totalSendAmountStr;
    }

    public String getTotalReceiveAmountStr() {
        return totalReceiveAmountStr;
    }

    public void setTotalReceiveAmountStr(String totalReceiveAmountStr) {
        this.totalReceiveAmountStr = totalReceiveAmountStr;
    }

    public BigDecimal getPriceDiff() {
        return priceDiff;
    }

    public void setPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getWeightVal() {
        return weightVal;
    }

    public void setWeightVal(String weightVal) {
        this.weightVal = weightVal;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

}
