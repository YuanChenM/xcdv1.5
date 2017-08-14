/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_pd_lgcs对应的SlPdLgcs。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlPdLgcs extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 物流区ID */
    private Long lgcsId;
    /** 卖家编码 */
    private String slCode;
    /** 物流区编码 */
    private String lgcsCode;
    /** 类别编码 */
    private String classesCode;
    /** 品种编码 */
    private String breedCode;
    /** 特征编码 */
    private String featureCode;
    /** 等级编码 */
    private String gradeCode;
    /** 包装编码 */
    private String pkgCode;
    /**
     * <p>默认构造函数。</p>
     */
    public SlPdLgcs() {

    }

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
     * <p>卖家编码。</p>
     *
     * @return the 卖家编码
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>卖家编码。</p>
     *
     * @param slCode 卖家编码。
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
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
     * <p>等级编码。</p>
     *
     * @return the 等级编码
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>等级编码。</p>
     *
     * @param gradeCode 等级编码。
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
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

}
