package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * 查询产品主码
 * IPD141106RsParam.
 *
 * @author xhy
 */
@JsonIgnoreProperties(value={"delFlg","crtId","crtTime","updId","updTime","ver","actId","actTime"})
public class IPD141106RsParam extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String classesCode;//类别编码
    
    private String breedCode; // 品种编码
    
    private String featureCode; // 特征编码

    private String qltStdClaId; // 分类质量标准指标id

    private String qltStdClaName; // 分类质量标准指标名称

    private List<IPD141106SubRsResult> qltStdSublist; // 具体质量指标列表

    /**
     * Get the qltStdClaId.
     *
     * @return qltStdClaId
     *
     * @author xhy
     */
    public String getQltStdClaId() {
        return this.qltStdClaId;
    }

    /**
     * Set the qltStdClaId.
     *
     * @param qltStdClaId qltStdClaId
     *
     * @author xhy
     */
    public void setQltStdClaId(String qltStdClaId) {
        this.qltStdClaId = qltStdClaId;
    }

    /**
     * Get the qltStdClaName.
     *
     * @return qltStdClaName
     *
     * @author xhy
     */
    public String getQltStdClaName() {
        return this.qltStdClaName;
    }

    /**
     * Set the qltStdClaName.
     *
     * @param qltStdClaName qltStdClaName
     *
     * @author xhy
     */
    public void setQltStdClaName(String qltStdClaName) {
        this.qltStdClaName = qltStdClaName;
    }

    /**
     * Get the qltStdSublist.
     *
     * @return qltStdSublist
     *
     * @author xhy
     */
    public List<IPD141106SubRsResult> getQltStdSublist() {
        return this.qltStdSublist;
    }

    /**
     * Set the qltStdSublist.
     *
     * @param qltStdSublist qltStdSublist
     *
     * @author xhy
     */
    public void setQltStdSublist(List<IPD141106SubRsResult> qltStdSublist) {
        this.qltStdSublist = qltStdSublist;
    }

    /**
     * Get the classesCode.
     *
     * @return classesCode
     *
     * @author Administrator
     */
    public String getClassesCode() {
        return this.classesCode;
    }

    /**
     * Set the classesCode.
     *
     * @param classesCode classesCode
     *
     * @author Administrator
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Get the breedCode.
     *
     * @return breedCode
     *
     * @author Administrator
     */
    public String getBreedCode() {
        return this.breedCode;
    }

    /**
     * Set the breedCode.
     *
     * @param breedCode breedCode
     *
     * @author Administrator
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * Get the featureCode.
     *
     * @return featureCode
     *
     * @author Administrator
     */
    public String getFeatureCode() {
        return this.featureCode;
    }

    /**
     * Set the featureCode.
     *
     * @param featureCode featureCode
     *
     * @author Administrator
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

}