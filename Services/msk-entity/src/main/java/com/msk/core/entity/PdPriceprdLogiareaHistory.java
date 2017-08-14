/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_priceprd_logiarea_history对应的PdPriceprdLogiareaHistory。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdPriceprdLogiareaHistory extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 价盘履历ID */
    private java.lang.Long historyId;
    /** 价盘周期 */
    private java.lang.String pricecyclePeriod;
    /** 产品编码 */
    private java.lang.String pdtMixcode;
    /** 产品类别编码 */
    private java.lang.String classesCode;
    /** 产品类别名称 */
    private java.lang.String classesName;
    /** 二级分类编码 */
    private java.lang.String machiningCode;
    /** 二级分类名称 */
    private java.lang.String machiningName;
    /** 产品品种编码 */
    private java.lang.String breedCode;
    /** 产品品种名称 */
    private java.lang.String breedName;
    /** 产品特征编码 */
    private java.lang.String featureCode;
    /** 产品特征名称 */
    private java.lang.String featureName;
    /** 净重编码 */
    private java.lang.String weightCode;
    /** 包装编码 */
    private java.lang.String pkgCode;
    /** 产品等级编码 */
    private java.lang.String gradeCode;
    /** 产品名称 */
    private java.lang.String pdtName;
    /** 物流区编码 */
    private java.lang.String logiareaCode;
    /** 物流区名称 */
    private java.lang.String logiareaName;
    /** 订单等级编码 */
    private java.lang.String orderlevelCode;
    /** 订单等级名称 */
    private java.lang.String orderlevelName;
    /** 净重（数值） */
    private java.math.BigDecimal netweight;
    /** 公斤价 */
    private java.math.BigDecimal priceofkg;
    /** 箱价（净重*公斤价） */
    private java.math.BigDecimal priceofbox;
    /**
     * <p>默认构造函数。</p>
     */
    public PdPriceprdLogiareaHistory() {

    }

    /**
     * <p>价盘履历ID。</p>
     *
     * @return the 价盘履历ID
     */
    public java.lang.Long getHistoryId() {
        return historyId;
    }

    /**
     * <p>价盘履历ID。</p>
     *
     * @param historyId 价盘履历ID。
     */
    public void setHistoryId(java.lang.Long historyId) {
        this.historyId = historyId;
    }

    /**
     * <p>价盘周期。</p>
     *
     * @return the 价盘周期
     */
    public java.lang.String getPricecyclePeriod() {
        return pricecyclePeriod;
    }

    /**
     * <p>价盘周期。</p>
     *
     * @param pricecyclePeriod 价盘周期。
     */
    public void setPricecyclePeriod(java.lang.String pricecyclePeriod) {
        this.pricecyclePeriod = pricecyclePeriod;
    }

    /**
     * <p>产品编码。</p>
     *
     * @return the 产品编码
     */
    public java.lang.String getPdtMixcode() {
        return pdtMixcode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @param pdtMixcode 产品编码。
     */
    public void setPdtMixcode(java.lang.String pdtMixcode) {
        this.pdtMixcode = pdtMixcode;
    }

    /**
     * <p>产品类别编码。</p>
     *
     * @return the 产品类别编码
     */
    public java.lang.String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>产品类别编码。</p>
     *
     * @param classesCode 产品类别编码。
     */
    public void setClassesCode(java.lang.String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>产品类别名称。</p>
     *
     * @return the 产品类别名称
     */
    public java.lang.String getClassesName() {
        return classesName;
    }

    /**
     * <p>产品类别名称。</p>
     *
     * @param classesName 产品类别名称。
     */
    public void setClassesName(java.lang.String classesName) {
        this.classesName = classesName;
    }

    /**
     * <p>二级分类编码。</p>
     *
     * @return the 二级分类编码
     */
    public java.lang.String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>二级分类编码。</p>
     *
     * @param machiningCode 二级分类编码。
     */
    public void setMachiningCode(java.lang.String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>二级分类名称。</p>
     *
     * @return the 二级分类名称
     */
    public java.lang.String getMachiningName() {
        return machiningName;
    }

    /**
     * <p>二级分类名称。</p>
     *
     * @param machiningName 二级分类名称。
     */
    public void setMachiningName(java.lang.String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @return the 产品品种编码
     */
    public java.lang.String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @param breedCode 产品品种编码。
     */
    public void setBreedCode(java.lang.String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>产品品种名称。</p>
     *
     * @return the 产品品种名称
     */
    public java.lang.String getBreedName() {
        return breedName;
    }

    /**
     * <p>产品品种名称。</p>
     *
     * @param breedName 产品品种名称。
     */
    public void setBreedName(java.lang.String breedName) {
        this.breedName = breedName;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @return the 产品特征编码
     */
    public java.lang.String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @param featureCode 产品特征编码。
     */
    public void setFeatureCode(java.lang.String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @return the 产品特征名称
     */
    public java.lang.String getFeatureName() {
        return featureName;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @param featureName 产品特征名称。
     */
    public void setFeatureName(java.lang.String featureName) {
        this.featureName = featureName;
    }

    /**
     * <p>净重编码。</p>
     *
     * @return the 净重编码
     */
    public java.lang.String getWeightCode() {
        return weightCode;
    }

    /**
     * <p>净重编码。</p>
     *
     * @param weightCode 净重编码。
     */
    public void setWeightgCode(java.lang.String weightCode) {
        this.weightCode = weightCode;
    }


    /**
     * <p>包装编码。</p>
     *
     * @return the 包装编码
     */
    public java.lang.String getPkgCode() {
        return pkgCode;
    }

    /**
     * <p>包装编码。</p>
     *
     * @param pkgCode 包装编码。
     */
    public void setPkgCode(java.lang.String pkgCode) {
        this.pkgCode = pkgCode;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @return the 产品等级编码
     */
    public java.lang.String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @param gradeCode 产品等级编码。
     */
    public void setGradeCode(java.lang.String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * <p>产品名称。</p>
     *
     * @return the 产品名称
     */
    public java.lang.String getPdtName() {
        return pdtName;
    }

    /**
     * <p>产品名称。</p>
     *
     * @param pdtName 产品名称。
     */
    public void setPdtName(java.lang.String pdtName) {
        this.pdtName = pdtName;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public java.lang.String getLogiareaCode() {
        return logiareaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param logiareaCode 物流区编码。
     */
    public void setLogiareaCode(java.lang.String logiareaCode) {
        this.logiareaCode = logiareaCode;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @return the 物流区名称
     */
    public java.lang.String getLogiareaName() {
        return logiareaName;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @param logiareaName 物流区名称。
     */
    public void setLogiareaName(java.lang.String logiareaName) {
        this.logiareaName = logiareaName;
    }

    /**
     * <p>订单等级编码。</p>
     *
     * @return the 订单等级编码
     */
    public java.lang.String getOrderlevelCode() {
        return orderlevelCode;
    }

    /**
     * <p>订单等级编码。</p>
     *
     * @param orderlevelCode 订单等级编码。
     */
    public void setOrderlevelCode(java.lang.String orderlevelCode) {
        this.orderlevelCode = orderlevelCode;
    }

    /**
     * <p>订单等级名称。</p>
     *
     * @return the 订单等级名称
     */
    public java.lang.String getOrderlevelName() {
        return orderlevelName;
    }

    /**
     * <p>订单等级名称。</p>
     *
     * @param orderlevelName 订单等级名称。
     */
    public void setOrderlevelName(java.lang.String orderlevelName) {
        this.orderlevelName = orderlevelName;
    }

    /**
     * <p>净重（数值）。</p>
     *
     * @return the 净重（数值）
     */
    public java.math.BigDecimal getNetweight() {
        return netweight;
    }

    /**
     * <p>净重（数值）。</p>
     *
     * @param netweight 净重（数值）。
     */
    public void setNetweight(java.math.BigDecimal netweight) {
        this.netweight = netweight;
    }

    /**
     * <p>公斤价。</p>
     *
     * @return the 公斤价
     */
    public java.math.BigDecimal getPriceofkg() {
        return priceofkg;
    }

    /**
     * <p>公斤价。</p>
     *
     * @param priceofkg 公斤价。
     */
    public void setPriceofkg(java.math.BigDecimal priceofkg) {
        this.priceofkg = priceofkg;
    }

    /**
     * <p>箱价（净重*公斤价）。</p>
     *
     * @return the 箱价（净重*公斤价）
     */
    public java.math.BigDecimal getPriceofbox() {
        return priceofbox;
    }

    /**
     * <p>箱价（净重*公斤价）。</p>
     *
     * @param priceofbox 箱价（净重*公斤价）。
     */
    public void setPriceofbox(java.math.BigDecimal priceofbox) {
        this.priceofbox = priceofbox;
    }

}
