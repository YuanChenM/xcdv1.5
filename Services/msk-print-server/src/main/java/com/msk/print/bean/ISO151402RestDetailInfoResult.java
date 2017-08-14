package com.msk.print.bean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151402RestDetailInfoResult implements Serializable {
    /**
     * 分批订单id
     */
    private Long subOrderId;
    /**
     * 订单明细id
     */
    private String orderDetailId;
    /**
     * 订单明细类型  1:正常订单 2:非正常订单 3:促销订单
     */
    private String orderDetailType;
    /**
     *1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单
      */
    private String orderDetailLevel;
    /**
     * 产品类型
     */
    private String classesCode;
    /**
     * 产品类型名称
     */
    private String classesName;
    /**
     * 产品品种
     */
    private String breedCode;
    /**
     * 产品品种名称
     */
    private String breedName;
    /**
     * 特征编码
     */
    private String featureCode;
    /**
     * 特征名称
     */
    private String featureName;
    /**
     * 产品包装编码
     */
    private String normsCode;
    /**
     * 产品包装名称
     */
    private String normsName;
    /**
     * 产品编码
     */
    private String pdCode;
    /**
     * 产品名称
     */
    private String pdName;
    /**
     * 产品等级
     */
    private String pdLevel;
    /**
     * 产品等级编码
     */
    private String pdGradeCode;
    /**
     * 产品等级名称
     */
    private String pdGradeName;
    /**
     * 产品单位
     */
    private String unit;
    /**
     * 单箱体积
     */
    private BigDecimal packingVolume;
    /*
    重量
     */
    private BigDecimal weight;
    /**
     * 体积
     */
    private BigDecimal volume;
    /**
     * 1:同意
     */
    private String agreeJoint;
    /**
     * 1:是
     */
    private  String isJoint;
    /**
     * 买家确认拼货，1:是
     */
    private String buyersConfirmShipments;
    /**
     * 产品单价
     */
    private String priceCycle;
    /**\
     * 价盘周期
     */
    private BigDecimal pdPrice;
    /**
     * 订单数量
     */
    private BigDecimal orderQty;
    /**
     * 供货明细中的供货量
     */
    private BigDecimal suppQty;
    /**
     *发货数量
     */
    private BigDecimal sendQty;
    /**
     * 收货数量
     */
    private BigDecimal receiveQty;
    /**
     * 取消数量
     */
    private BigDecimal cancelQty;
    /**
     * 退货数量
     */
    private BigDecimal returnQty;
    /**
     * 拒收数量
     */
    private BigDecimal rejectionQty;
    /**
     * 明细状态
     */
    private String detailStatus = "";
    /**
     * 取消原因
     */
    private String cancelReason;
    /**
     * 期望配送日
     */
    private String proDate = "";
    /**
     * 分批订单明细id
     */

    private Long subOrderDetailId;
    /**
     * 供应商编码
     */
    private String supplierCode;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 订单明细金额
     */
    private BigDecimal amount;
    private List<ISO151402RestAvailabilityInfoResult> availabilityInfo;


    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderDetailType() {
        return orderDetailType;
    }

    public void setOrderDetailType(String orderDetailType) {
        this.orderDetailType = orderDetailType;
    }

    public String getOrderDetailLevel() {
        return orderDetailLevel;
    }

    public void setOrderDetailLevel(String orderDetailLevel) {
        this.orderDetailLevel = orderDetailLevel;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getPdLevel() {
        return pdLevel;
    }

    public void setPdLevel(String pdLevel) {
        this.pdLevel = pdLevel;
    }

    public String getPdGradeCode() {
        return pdGradeCode;
    }

    public void setPdGradeCode(String pdGradeCode) {
        this.pdGradeCode = pdGradeCode;
    }

    public String getPdGradeName() {
        return pdGradeName;
    }

    public void setPdGradeName(String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPackingVolume() {
        return packingVolume;
    }

    public void setPackingVolume(BigDecimal packingVolume) {
        this.packingVolume = packingVolume;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getAgreeJoint() {
        return agreeJoint;
    }

    public void setAgreeJoint(String agreeJoint) {
        this.agreeJoint = agreeJoint;
    }

    public String getBuyersConfirmShipments() {
        return buyersConfirmShipments;
    }

    public void setBuyersConfirmShipments(String buyersConfirmShipments) {
        this.buyersConfirmShipments = buyersConfirmShipments;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Long getSubOrderDetailId() {
        return subOrderDetailId;
    }

    public void setSubOrderDetailId(Long subOrderDetailId) {
        this.subOrderDetailId = subOrderDetailId;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    public String getPriceCycle() {
        return priceCycle;
    }

    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    public BigDecimal getSuppQty() {
        return suppQty;
    }

    public void setSuppQty(BigDecimal suppQty) {
        this.suppQty = suppQty;
    }

    public String getProDate() {
        return proDate;
    }

    public void setProDate(String proDate) {
        this.proDate = proDate;
    }

    public BigDecimal getSendQty() {
        return sendQty;
    }

    public void setSendQty(BigDecimal sendQty) {
        this.sendQty = sendQty;
    }

    public BigDecimal getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    public BigDecimal getCancelQty() {
        return cancelQty;
    }

    public void setCancelQty(BigDecimal cancelQty) {
        this.cancelQty = cancelQty;
    }

    public BigDecimal getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    public BigDecimal getRejectionQty() {
        return rejectionQty;
    }

    public void setRejectionQty(BigDecimal rejectionQty) {
        this.rejectionQty = rejectionQty;
    }

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<ISO151402RestAvailabilityInfoResult> getAvailabilityInfo() {
        return availabilityInfo;
    }

    public void setAvailabilityInfo(List<ISO151402RestAvailabilityInfoResult> availabilityInfo) {
        this.availabilityInfo = availabilityInfo;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getIsJoint() {
        return isJoint;
    }

    public void setIsJoint(String isJoint) {
        this.isJoint = isJoint;
    }
}
