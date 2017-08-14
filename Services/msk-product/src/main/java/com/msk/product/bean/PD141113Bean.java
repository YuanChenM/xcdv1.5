package com.msk.product.bean;


import com.msk.common.base.BaseBean;

/**
 * 
 * PD141113Bean
 * @author gyh
 */
public class PD141113Bean extends BaseBean {
    private static final long serialVersionUID = 1L;
    /* 产品标准ID */
    private Integer pdStdId;
    /* 产品类别编码 */
    private String classesCode;
    /* 产品品种编码 */
    private String breedCode;
    /* 产品品种名称 */
    private String breedName;
    /* 产品类别名称 */
    private String classesName;
    /* 产品特征编码 */
    private String featureCode;
    /* 产品特征名称 */
    private String featureName;
    /* 质量标准有无 */
    private String qltFlg;
    /* 技术标准有无 */
    private String tncFlg;
    /* 包装规格有无 */
    private String norFlg;

    /**
     * @return the classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * @param classesCode the classesCode to set
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * @return the breedCode
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * @param breedCode the breedCode to set
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * @return the breedName
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * @param breedName the breedName to set
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * @return the classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * @param classesName the classesName to set
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * Get the pdStdId.
     *
     * @return pdStdId
     *
     * @author gyh
     */
    public Integer getPdStdId() {
        return this.pdStdId;
    }

    /**
     * Set the pdStdId.
     *
     * @param pdStdId pdStdId
     *
     * @author gyh
     */
    public void setPdStdId(Integer pdStdId) {
        this.pdStdId = pdStdId;
    }

    /**
     * Get the featureCode.
     *
     * @return featureCode
     *
     * @author gyh
     */
    public String getFeatureCode() {
        return this.featureCode;
    }

    /**
     * Set the featureCode.
     *
     * @param featureCode featureCode
     *
     * @author gyh
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * Get the featureName.
     *
     * @return featureName
     *
     * @author gyh
     */
    public String getFeatureName() {
        return this.featureName;
    }

    /**
     * Set the featureName.
     *
     * @param featureName featureName
     *
     * @author gyh
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * Get the qltFlg.
     *
     * @return qltFlg
     *
     * @author gyh
     */
    public String getQltFlg() {
        return this.qltFlg;
    }

    /**
     * Set the qltFlg.
     *
     * @param qltFlg qltFlg
     *
     * @author gyh
     */
    public void setQltFlg(String qltFlg) {
        this.qltFlg = qltFlg;
    }

    /**
     * Get the tncFlg.
     *
     * @return tncFlg
     *
     * @author gyh
     */
    public String getTncFlg() {
        return this.tncFlg;
    }

    /**
     * Set the tncFlg.
     *
     * @param tncFlg tncFlg
     *
     * @author gyh
     */
    public void setTncFlg(String tncFlg) {
        this.tncFlg = tncFlg;
    }

    /**
     * Get the norFlg.
     *
     * @return norFlg
     *
     * @author gyh
     */
    public String getNorFlg() {
        return this.norFlg;
    }

    /**
     * Set the norFlg.
     *
     * @param norFlg norFlg
     *
     * @author gyh
     */
    public void setNorFlg(String norFlg) {
        this.norFlg = norFlg;
    }

}
