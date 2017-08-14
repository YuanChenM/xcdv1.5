package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/12.
 */
public class ProductInfoParam extends BaseParam{

    /**产品编码Codes */
    private List<String> pdCodes;

    /**产品包装Codes */
    private List<String> normCodes;

    /** 一级分类名称*/
    private String classesName;

    /** 二级分类名称*/
    private String machiningName;

    /** 品种名称*/
    private String breedName;

    /** 特征名称*/
    private String featureName;

    /**产品类型 classes——1、 machining——2、 breed——3、 feature——4、 weight——5、 grade——6、 获取productInfo —— 7*/
    private Integer type;

    /** 是否要查询产品等级*/
    private String gradeFlag;

    public List<String> getPdCodes() {
        return pdCodes;
    }

    public void setPdCodes(List<String> pdCodes) {
        this.pdCodes = pdCodes;
    }

    public List<String> getNormCodes() {
        return normCodes;
    }

    public void setNormCodes(List<String> normCodes) {
        this.normCodes = normCodes;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGradeFlag() {
        return gradeFlag;
    }

    public void setGradeFlag(String gradeFlag) {
        this.gradeFlag = gradeFlag;
    }
}
