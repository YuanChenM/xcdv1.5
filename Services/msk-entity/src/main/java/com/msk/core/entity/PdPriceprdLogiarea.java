/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_priceprd_logiarea对应的PdPriceprdLogiarea。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdPriceprdLogiarea extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 价盘ID */
    private Long pricecycleId;
    /** 价盘周期 */
    private String pricecyclePeriod;
    /** 产品编码 */
    private String pdtMixcode;
    /** 产品类别编码 */
    private String classesCode;
    /** 产品类别名称 */
    private String classesName;
    /** 二级分类编码 */
    private String machiningCode;
    /** 二级分类名称 */
    private String machiningName;
    /** 产品品种编码 */
    private String breedCode;
    /** 产品品种名称 */
    private String breedName;
    /** 产品特征编码 */
    private String featureCode;
    /** 产品特征名称 */
    private String featureName;
    /** 净重编码 */
    private String weightCode;
    /** 包装编码 */
    private String pkgCode;
    /** 产品等级编码 */
    private String gradeCode;
    /** 产品名称 */
    private String pdtName;
    /** 物流区编码 */
    private String logiareaCode;
    /** 物流区名称 */
    private String logiareaName;
    /** 订单等级编码 */
    private String orderlevelCode;
    /** 订单等级名称 */
    private String orderlevelName;
    /** 净重数值 */
    private java.math.BigDecimal netweight;
    /** 公斤价 */
    private java.math.BigDecimal priceofkg;
    /** 箱价 */
    private java.math.BigDecimal priceofbox;
    /**
     * <p>默认构造函数。</p>
     */
    public PdPriceprdLogiarea() {

    }

    /**
     * <p>价盘ID。</p>
     *
     * @return the 价盘ID
     */
    public Long getPricecycleId() {
        return pricecycleId;
    }

    /**
     * <p>价盘ID。</p>
     *
     * @param pricecycleId 价盘ID。
     */
    public void setPricecycleId(Long pricecycleId) {
        this.pricecycleId = pricecycleId;
    }

    /**
     * <p>价盘周期。</p>
     *
     * @return the 价盘周期
     */
    public String getPricecyclePeriod() {
        return pricecyclePeriod;
    }

    /**
     * <p>价盘周期。</p>
     *
     * @param pricecyclePeriod 价盘周期。
     */
    public void setPricecyclePeriod(String pricecyclePeriod) {
        this.pricecyclePeriod = pricecyclePeriod;
    }

    /**
     * <p>产品编码。</p>
     *
     * @return the 产品编码
     */
    public String getPdtMixcode() {
        return pdtMixcode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @param pdtMixcode 产品编码。
     */
    public void setPdtMixcode(String pdtMixcode) {
        this.pdtMixcode = pdtMixcode;
    }

    /**
     * <p>产品类别编码。</p>
     *
     * @return the 产品类别编码
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>产品类别编码。</p>
     *
     * @param classesCode 产品类别编码。
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
     * <p>二级分类编码。</p>
     *
     * @return the 二级分类编码
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>二级分类编码。</p>
     *
     * @param machiningCode 二级分类编码。
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>二级分类名称。</p>
     *
     * @return the 二级分类名称
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * <p>二级分类名称。</p>
     *
     * @param machiningName 二级分类名称。
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @return the 产品品种编码
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @param breedCode 产品品种编码。
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
     * <p>净重编码。</p>
     *
     * @return the 净重编码
     */
    public String getWeightCode() {
        return weightCode;
    }

    /**
     * <p>净重编码。</p>
     *
     * @param weightCode 净重编码。
     */
    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * <p>包装编码。</p>
     *
     * @return the 包装编码
     */
    public String getPkgCode() {
        return pkgCode;
    }

    /**
     * <p>包装编码。</p>
     *
     * @param pkgCode 包装编码。
     */
    public void setPkgCode(String pkgCode) {
        this.pkgCode = pkgCode;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @return the 产品等级编码
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @param gradeCode 产品等级编码。
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * <p>产品名称。</p>
     *
     * @return the 产品名称
     */
    public String getPdtName() {
        return pdtName;
    }

    /**
     * <p>产品名称。</p>
     *
     * @param pdtName 产品名称。
     */
    public void setPdtName(String pdtName) {
        this.pdtName = pdtName;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public String getLogiareaCode() {
        return logiareaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param logiareaCode 物流区编码。
     */
    public void setLogiareaCode(String logiareaCode) {
        this.logiareaCode = logiareaCode;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @return the 物流区名称
     */
    public String getLogiareaName() {
        return logiareaName;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @param logiareaName 物流区名称。
     */
    public void setLogiareaName(String logiareaName) {
        this.logiareaName = logiareaName;
    }

    /**
     * <p>订单等级编码。</p>
     *
     * @return the 订单等级编码
     */
    public String getOrderlevelCode() {
        return orderlevelCode;
    }

    /**
     * <p>订单等级编码。</p>
     *
     * @param orderlevelCode 订单等级编码。
     */
    public void setOrderlevelCode(String orderlevelCode) {
        this.orderlevelCode = orderlevelCode;
    }

    /**
     * <p>订单等级名称。</p>
     *
     * @return the 订单等级名称
     */
    public String getOrderlevelName() {
        return orderlevelName;
    }

    /**
     * <p>订单等级名称。</p>
     *
     * @param orderlevelName 订单等级名称。
     */
    public void setOrderlevelName(String orderlevelName) {
        this.orderlevelName = orderlevelName;
    }

    /**
     * <p>净重数值。</p>
     *
     * @return the 净重数值
     */
    public java.math.BigDecimal getNetweight() {
        return netweight;
    }

    /**
     * <p>净重数值。</p>
     *
     * @param netweight 净重数值。
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
     * <p>箱价。</p>
     *
     * @return the 箱价
     */
    public java.math.BigDecimal getPriceofbox() {
        return priceofbox;
    }

    /**
     * <p>箱价。</p>
     *
     * @param priceofbox 箱价。
     */
    public void setPriceofbox(java.math.BigDecimal priceofbox) {
        this.priceofbox = priceofbox;
    }

}
