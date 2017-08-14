package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

/**
 * Created by liu_tao2 on 2016/8/15.
 */
public class ISO151414ProductBaseInfo extends BaseResult {
    /** 一级分类编码 */
    private String classesCode;

    /** 一级分类名称 */
    private String classesName;

    /** 二级分类编码 */
    private String machiningCode;

    /** 二级分类名称 */
    private String machiningName;

    /** 品种编码 */
    private String breedCode;

    /** 品种名称 */
    private String breedName;

    /** 特征编码 */
    private String featureCode;

    /** 特征名称 */
    private String featureName;

    /** 包装编码 */
    private String normsCode;

    /** 包装名称 */
    private String normsName;

    /** 等级编码 */
    private String pdGradeCode;

    /** 等级名称 */
    private String pdGradeName;

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

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
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
}
