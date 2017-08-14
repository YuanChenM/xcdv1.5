package com.msk.product.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * PD141121Bean.
 *
 * @author ren_qiang
 */
public class IPD141149Param extends BaseParam {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** 物流区ID */
    private Long lgcsId;
    /** 物流区编码 */
    private String lgcsCode;
    /** 物流区名称 */
    private String lgcsName;
    /** 类别编码 */
    private String classesCode;
    /** 二级分类编码 */
    private String machiningCode;
    /** 品种编码 */
    private String breedCode;
    /** 特征编码 */
    private String featureCode;
    /** 净重编码 */
    private String weightCode;
    /** 产品包装编码 */
    private String pkgCode;
    /** 产品等级编码 */
    private String gradeCode;
    /** 产品营销编码 */
    private String pdMarketCode;
    /***
     * 产品营销状态名称
     * */
    private String pdMarketName;
    /**
     * 类别编码+二级分类编码+品种编码+特征编码+净重编码+产品等级编码
     */
    private String proCode;

    /**
     * <p>物流区ID。</p>
     *
     * @return the 物流区ID
     */
    public Long getLgcsId() {
        return lgcsId;
    }

    /**
     * <p>物流区ID。</p>
     *
     * @param lgcsId 物流区ID。
     */
    public void setLgcsId(Long lgcsId) {
        this.lgcsId = lgcsId;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public String getLgcsCode() {
        return lgcsCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param lgcsCode 物流区编码。
     */
    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }
    /**
     * <p>物流区名称。</p>
     *
     * @return the 物流区名称
     */
    public String getLgcsName() {
        return lgcsName;
    }
    /**
     * <p>物流区名称。</p>
     *
     * @param lgcsName 物流区名称
     */
    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    /**
     * <p>类别编码。</p>
     *
     * @return the 类别编码
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>类别编码。</p>
     *
     * @param classesCode 类别编码。
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
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
     * <p>品种编码。</p>
     *
     * @return the 品种编码
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>品种编码。</p>
     *
     * @param breedCode 品种编码。
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>特征编码。</p>
     *
     * @return the 特征编码
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>特征编码。</p>
     *
     * @param featureCode 特征编码。
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
     * <p>产品包装编码。</p>
     *
     * @return the 产品包装编码
     */
    public String getPkgCode() {
        return pkgCode;
    }

    /**
     * <p>产品包装编码。</p>
     *
     * @param pkgCode 产品包装编码。
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
     * <p>产品营销编码。</p>
     *
     * @return the 产品营销编码
     */
    public String getPdMarketCode() {
        return pdMarketCode;
    }
    /**
     * <p>产品营销编码。</p>
     *
     * @param pdMarketCode 产品营销编码。
     */
    public void setPdMarketCode(String pdMarketCode) {
        this.pdMarketCode = pdMarketCode;
    }

    /**
     *获取产品运营状态名称
     * @return pdMarketName
     */
    public String getPdMarketName() {
        return pdMarketName;
    }

    /**
     * 设置产品运营状态名称
     * @param pdMarketName
     */
    public void setPdMarketName(String pdMarketName) {
        this.pdMarketName = pdMarketName;
    }

    /**
     *类别编码+二级分类编码+品种编码+特征编码+净重编码+产品等级编码
     * @return
     */
    public String getProCode() {
        return proCode;
    }

    /**
     * 类别编码+二级分类编码+品种编码+特征编码+净重编码+产品等级编码
     * @param proCode
     */
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }
}
