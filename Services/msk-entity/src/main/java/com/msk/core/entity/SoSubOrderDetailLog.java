/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_sub_order_detail_log对应的SoSubOrderDetailLog。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoSubOrderDetailLog extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 分批订单明细日志顺序号 */
    private Long logSn;
    /** 分批订单明细ID */
    private Long subOrderDetailId;
    /** 分批订单ID */
    private Long subOrderId;
    /** 订单ID */
    private Long orderId;
    /** 订单明细ID */
    private Long orderDetailId;
    /** 订单明细类型-CodeMaster
            1:正常订单 2:非正常订单 3:促销订单 */
    private Integer orderDetailType;
    /** 订单明细等级-CodeMaster
            1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单 */
    private Integer orderDetailLevel;
    /** 产品类别编码，一级分类 */
    private String classesCode;
    /** 产品类别名称 */
    private String classesName;
    /** 产品品种 */
    private String breedCode;
    /** 产品品种名称 */
    private String breedName;
    /** 产品特征编码 */
    private String featureCode;
    /** 产品特征名称 */
    private String featureName;
    /** 产品包装编码 */
    private String normsCode;
    /** 产品包装名称 */
    private String normsName;
    /** 产品编码 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 产品等级 */
    private String pdLevel;
    /** 产品等级编码 */
    private String pdGradeCode;
    /** 产品等级名称 */
    private String pdGradeName;
    /** 产品单位 */
    private String unit;
    /** 单箱体积 */
    private java.math.BigDecimal packingVolume;
    /** 重量 */
    private java.math.BigDecimal weight;
    /** 体积 */
    private java.math.BigDecimal volume;
    /** 产品单价 */
    private java.math.BigDecimal pdPrice;
    /** 价盘周期 */
    private String priceCycle;
    /** 期望配送日 */
    private java.util.Date proDate;
    /** 订单数量 */
    private java.math.BigDecimal orderQty;
    /** 发货数量 */
    private java.math.BigDecimal sendQty;
    /** 收货数量 */
    private java.math.BigDecimal receiveQty;
    /** 取消数量 */
    private java.math.BigDecimal cancelQty;
    /** 退货数量 */
    private java.math.BigDecimal returnQty;
    /** 拒收数量 */
    private java.math.BigDecimal rejectionQty;
    /** 发货时间 */
    private java.util.Date sendTime;
    /** 收货时间 */
    private java.util.Date receivedTime;
    /** 明细状态,CodeMaster */
    private Integer detailStatus;
    /** 取消原因 */
    private String cancelReason;
    /**
     * <p>默认构造函数。</p>
     */
    public SoSubOrderDetailLog() {

    }

    /**
     * <p>分批订单明细日志顺序号。</p>
     *
     * @return the 分批订单明细日志顺序号
     */
    public Long getLogSn() {
        return logSn;
    }

    /**
     * <p>分批订单明细日志顺序号。</p>
     *
     * @param logSn 分批订单明细日志顺序号。
     */
    public void setLogSn(Long logSn) {
        this.logSn = logSn;
    }

    /**
     * <p>分批订单明细ID。</p>
     *
     * @return the 分批订单明细ID
     */
    public Long getSubOrderDetailId() {
        return subOrderDetailId;
    }

    /**
     * <p>分批订单明细ID。</p>
     *
     * @param subOrderDetailId 分批订单明细ID。
     */
    public void setSubOrderDetailId(Long subOrderDetailId) {
        this.subOrderDetailId = subOrderDetailId;
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
     * <p>订单明细ID。</p>
     *
     * @return the 订单明细ID
     */
    public Long getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * <p>订单明细ID。</p>
     *
     * @param orderDetailId 订单明细ID。
     */
    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * <p>订单明细类型-CodeMaster
            1:正常订单 2:非正常订单 3:促销订单。</p>
     *
     * @return the 订单明细类型-CodeMaster
            1:正常订单 2:非正常订单 3:促销订单
     */
    public Integer getOrderDetailType() {
        return orderDetailType;
    }

    /**
     * <p>订单明细类型-CodeMaster
            1:正常订单 2:非正常订单 3:促销订单。</p>
     *
     * @param orderDetailType 订单明细类型-CodeMaster
            1:正常订单 2:非正常订单 3:促销订单。
     */
    public void setOrderDetailType(Integer orderDetailType) {
        this.orderDetailType = orderDetailType;
    }

    /**
     * <p>订单明细等级-CodeMaster
            1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单。</p>
     *
     * @return the 订单明细等级-CodeMaster
            1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单
     */
    public Integer getOrderDetailLevel() {
        return orderDetailLevel;
    }

    /**
     * <p>订单明细等级-CodeMaster
            1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单。</p>
     *
     * @param orderDetailLevel 订单明细等级-CodeMaster
            1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单。
     */
    public void setOrderDetailLevel(Integer orderDetailLevel) {
        this.orderDetailLevel = orderDetailLevel;
    }

    /**
     * <p>产品类别编码，一级分类。</p>
     *
     * @return the 产品类别编码，一级分类
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>产品类别编码，一级分类。</p>
     *
     * @param classesCode 产品类别编码，一级分类。
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>产品类别名称。</p>
     *
     * @return the 产品类别名称
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * <p>产品类别名称。</p>
     *
     * @param classesName 产品类别名称。
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * <p>产品品种。</p>
     *
     * @return the 产品品种
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>产品品种。</p>
     *
     * @param breedCode 产品品种。
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>产品品种名称。</p>
     *
     * @return the 产品品种名称
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * <p>产品品种名称。</p>
     *
     * @param breedName 产品品种名称。
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @return the 产品特征编码
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @param featureCode 产品特征编码。
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @return the 产品特征名称
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @param featureName 产品特征名称。
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * <p>产品包装编码。</p>
     *
     * @return the 产品包装编码
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * <p>产品包装编码。</p>
     *
     * @param normsCode 产品包装编码。
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * <p>产品包装名称。</p>
     *
     * @return the 产品包装名称
     */
    public String getNormsName() {
        return normsName;
    }

    /**
     * <p>产品包装名称。</p>
     *
     * @param normsName 产品包装名称。
     */
    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    /**
     * <p>产品编码。</p>
     *
     * @return the 产品编码
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @param pdCode 产品编码。
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>产品名称。</p>
     *
     * @return the 产品名称
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * <p>产品名称。</p>
     *
     * @param pdName 产品名称。
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    /**
     * <p>产品等级。</p>
     *
     * @return the 产品等级
     */
    public String getPdLevel() {
        return pdLevel;
    }

    /**
     * <p>产品等级。</p>
     *
     * @param pdLevel 产品等级。
     */
    public void setPdLevel(String pdLevel) {
        this.pdLevel = pdLevel;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @return the 产品等级编码
     */
    public String getPdGradeCode() {
        return pdGradeCode;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @param pdGradeCode 产品等级编码。
     */
    public void setPdGradeCode(String pdGradeCode) {
        this.pdGradeCode = pdGradeCode;
    }

    /**
     * <p>产品等级名称。</p>
     *
     * @return the 产品等级名称
     */
    public String getPdGradeName() {
        return pdGradeName;
    }

    /**
     * <p>产品等级名称。</p>
     *
     * @param pdGradeName 产品等级名称。
     */
    public void setPdGradeName(String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    /**
     * <p>产品单位。</p>
     *
     * @return the 产品单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * <p>产品单位。</p>
     *
     * @param unit 产品单位。
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * <p>单箱体积。</p>
     *
     * @return the 单箱体积
     */
    public java.math.BigDecimal getPackingVolume() {
        return packingVolume;
    }

    /**
     * <p>单箱体积。</p>
     *
     * @param packingVolume 单箱体积。
     */
    public void setPackingVolume(java.math.BigDecimal packingVolume) {
        this.packingVolume = packingVolume;
    }

    /**
     * <p>重量。</p>
     *
     * @return the 重量
     */
    public java.math.BigDecimal getWeight() {
        return weight;
    }

    /**
     * <p>重量。</p>
     *
     * @param weight 重量。
     */
    public void setWeight(java.math.BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * <p>体积。</p>
     *
     * @return the 体积
     */
    public java.math.BigDecimal getVolume() {
        return volume;
    }

    /**
     * <p>体积。</p>
     *
     * @param volume 体积。
     */
    public void setVolume(java.math.BigDecimal volume) {
        this.volume = volume;
    }

    /**
     * <p>产品单价。</p>
     *
     * @return the 产品单价
     */
    public java.math.BigDecimal getPdPrice() {
        return pdPrice;
    }

    /**
     * <p>产品单价。</p>
     *
     * @param pdPrice 产品单价。
     */
    public void setPdPrice(java.math.BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    /**
     * <p>价盘周期。</p>
     *
     * @return the 价盘周期
     */
    public String getPriceCycle() {
        return priceCycle;
    }

    /**
     * <p>价盘周期。</p>
     *
     * @param priceCycle 价盘周期。
     */
    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    /**
     * <p>期望配送日。</p>
     *
     * @return the 期望配送日
     */
    public java.util.Date getProDate() {
        return proDate;
    }

    /**
     * <p>期望配送日。</p>
     *
     * @param proDate 期望配送日。
     */
    public void setProDate(java.util.Date proDate) {
        this.proDate = proDate;
    }

    /**
     * <p>订单数量。</p>
     *
     * @return the 订单数量
     */
    public java.math.BigDecimal getOrderQty() {
        return orderQty;
    }

    /**
     * <p>订单数量。</p>
     *
     * @param orderQty 订单数量。
     */
    public void setOrderQty(java.math.BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    /**
     * <p>发货数量。</p>
     *
     * @return the 发货数量
     */
    public java.math.BigDecimal getSendQty() {
        return sendQty;
    }

    /**
     * <p>发货数量。</p>
     *
     * @param sendQty 发货数量。
     */
    public void setSendQty(java.math.BigDecimal sendQty) {
        this.sendQty = sendQty;
    }

    /**
     * <p>收货数量。</p>
     *
     * @return the 收货数量
     */
    public java.math.BigDecimal getReceiveQty() {
        return receiveQty;
    }

    /**
     * <p>收货数量。</p>
     *
     * @param receiveQty 收货数量。
     */
    public void setReceiveQty(java.math.BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    /**
     * <p>取消数量。</p>
     *
     * @return the 取消数量
     */
    public java.math.BigDecimal getCancelQty() {
        return cancelQty;
    }

    /**
     * <p>取消数量。</p>
     *
     * @param cancelQty 取消数量。
     */
    public void setCancelQty(java.math.BigDecimal cancelQty) {
        this.cancelQty = cancelQty;
    }

    /**
     * <p>退货数量。</p>
     *
     * @return the 退货数量
     */
    public java.math.BigDecimal getReturnQty() {
        return returnQty;
    }

    /**
     * <p>退货数量。</p>
     *
     * @param returnQty 退货数量。
     */
    public void setReturnQty(java.math.BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    /**
     * <p>拒收数量。</p>
     *
     * @return the 拒收数量
     */
    public java.math.BigDecimal getRejectionQty() {
        return rejectionQty;
    }

    /**
     * <p>拒收数量。</p>
     *
     * @param rejectionQty 拒收数量。
     */
    public void setRejectionQty(java.math.BigDecimal rejectionQty) {
        this.rejectionQty = rejectionQty;
    }

    /**
     * <p>发货时间。</p>
     *
     * @return the 发货时间
     */
    public java.util.Date getSendTime() {
        return sendTime;
    }

    /**
     * <p>发货时间。</p>
     *
     * @param sendTime 发货时间。
     */
    public void setSendTime(java.util.Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * <p>收货时间。</p>
     *
     * @return the 收货时间
     */
    public java.util.Date getReceivedTime() {
        return receivedTime;
    }

    /**
     * <p>收货时间。</p>
     *
     * @param receivedTime 收货时间。
     */
    public void setReceivedTime(java.util.Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    /**
     * <p>明细状态,CodeMaster。</p>
     *
     * @return the 明细状态,CodeMaster
     */
    public Integer getDetailStatus() {
        return detailStatus;
    }

    /**
     * <p>明细状态,CodeMaster。</p>
     *
     * @param detailStatus 明细状态,CodeMaster。
     */
    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
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

}
