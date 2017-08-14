package com.msk.buyers.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/7/5.
 */
public class BY121309Bean extends BaseEntity{
    private String flag;
    private String Id;
    private String classesName;
    private String machiningName;
    private String breedName;
    private String featureName;
    private String weightVal;
    private String normsName;
    private String gradeName;
    private List<BY121309Bean> pageResult;

    public String getWeightVal() {
        return weightVal;
    }

    public void setWeightVal(String weightVal) {
        this.weightVal = weightVal;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public List<BY121309Bean> getPageResult() {
        return pageResult;
    }

    public void setPageResult(List<BY121309Bean> pageResult) {
        this.pageResult = pageResult;
    }
}
