/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_logiarea_orderlevel对应的PdLogiareaOrderlevel。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdLogiareaOrderlevel extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品类别编码 */
    private String classesCode;
    /** 产品品种编码 */
    private String breedCode;
    /** 二级分类编码 */
    private String machiningCode;
    /** 产品特征编码 */
    private String featureCode;
    /** 净重编码 */
    private String weightCode;
    /** 包装编码 */
    private String pkgCode;
    /** 产品等级编码 */
    private String gradeCode;
    /** 物流区编码 */
    private String logiareaCode;
    /** 订单等级CODE */
    private String orderlevelCode;
    /** 订单等级名称 */
    private String orderlevelName;
    /** 报价平衡系数 */
    private java.math.BigDecimal pricePercent;
    /** 箱数范围 */
    private String boxCntlvl;
    /** 箱数范围下限 */
    private Integer boxCntmin;
    /** 箱数范围上限 */
    private Integer boxCntmax;
    /**
     * <p>默认构造函数。</p>
     */
    public PdLogiareaOrderlevel() {

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
     * <p>订单等级CODE。</p>
     *
     * @return the 订单等级CODE
     */
    public String getOrderlevelCode() {
        return orderlevelCode;
    }

    /**
     * <p>订单等级CODE。</p>
     *
     * @param orderlevelCode 订单等级CODE。
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
     * <p>报价平衡系数。</p>
     *
     * @return the 报价平衡系数
     */
    public java.math.BigDecimal getPricePercent() {
        return pricePercent;
    }

    /**
     * <p>报价平衡系数。</p>
     *
     * @param pricePercent 报价平衡系数。
     */
    public void setPricePercent(java.math.BigDecimal pricePercent) {
        this.pricePercent = pricePercent;
    }

    /**
     * <p>箱数范围。</p>
     *
     * @return the 箱数范围
     */
    public String getBoxCntlvl() {
        return boxCntlvl;
    }

    /**
     * <p>箱数范围。</p>
     *
     * @param boxCntlvl 箱数范围。
     */
    public void setBoxCntlvl(String boxCntlvl) {
        this.boxCntlvl = boxCntlvl;
    }

    /**
     * <p>箱数范围下限。</p>
     *
     * @return the 箱数范围下限
     */
    public Integer getBoxCntmin() {
        return boxCntmin;
    }

    /**
     * <p>箱数范围下限。</p>
     *
     * @param boxCntmin 箱数范围下限。
     */
    public void setBoxCntmin(Integer boxCntmin) {
        this.boxCntmin = boxCntmin;
    }

    /**
     * <p>箱数范围上限。</p>
     *
     * @return the 箱数范围上限
     */
    public Integer getBoxCntmax() {
        return boxCntmax;
    }

    /**
     * <p>箱数范围上限。</p>
     *
     * @param boxCntmax 箱数范围上限。
     */
    public void setBoxCntmax(Integer boxCntmax) {
        this.boxCntmax = boxCntmax;
    }

}
