package com.msk.bs.bean;

import com.msk.core.entity.SlBsBuyerLeagues;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gyh on 2016/4/12.
 */
@ApiModel(value = "IBS210111101RsResult",description = "买手联盟List")
public class IBS210111101RsResult extends SlBsBuyerLeagues {
    @ApiModelProperty(value = "产品类别名")
    private String classesName;
    @ApiModelProperty(value = "产品二级分类名")
    private String machiningName;
    @ApiModelProperty(value = "产品品种名")
    private String breedName;
    @ApiModelProperty(value = "产品特征名")
    private String featureName;
    @ApiModelProperty(value = "净重名")
    private String weightName;
    @ApiModelProperty(value = "净重数值")
    private String weightVal;
    @ApiModelProperty(value = "产品等级名")
    private String gradeName;
    @ApiModelProperty(value = "联盟主买手信息")
    private IBS210111102RsResult ownerSlInfo;
    @ApiModelProperty(value = "联盟方买手信息")
    private IBS210111102RsResult allianceSlInfo;

    /**
     * Getter method for property <tt>classesName</tt>.
     *
     * @return property value of classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * Setter method for property <tt>classesName</tt>.
     *
     * @param classesName value to be assigned to property classesName
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * Getter method for property <tt>machiningName</tt>.
     *
     * @return property value of machiningName
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * Setter method for property <tt>machiningName</tt>.
     *
     * @param machiningName value to be assigned to property machiningName
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * Getter method for property <tt>breedName</tt>.
     *
     * @return property value of breedName
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * Setter method for property <tt>breedName</tt>.
     *
     * @param breedName value to be assigned to property breedName
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * Getter method for property <tt>featureName</tt>.
     *
     * @return property value of featureName
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * Setter method for property <tt>featureName</tt>.
     *
     * @param featureName value to be assigned to property featureName
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * Getter method for property <tt>weightName</tt>.
     *
     * @return property value of weightName
     */
    public String getWeightName() {
        return weightName;
    }

    /**
     * Setter method for property <tt>weightName</tt>.
     *
     * @param weightName value to be assigned to property weightName
     */
    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    /**
     * Getter method for property <tt>weightVal</tt>.
     *
     * @return property value of weightVal
     */
    public String getWeightVal() {
        return weightVal;
    }

    /**
     * Setter method for property <tt>weightVal</tt>.
     *
     * @param weightVal value to be assigned to property weightVal
     */
    public void setWeightVal(String weightVal) {
        this.weightVal = weightVal;
    }

    /**
     * Getter method for property <tt>gradeName</tt>.
     *
     * @return property value of gradeName
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * Setter method for property <tt>gradeName</tt>.
     *
     * @param gradeName value to be assigned to property gradeName
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * Getter method for property <tt>ownerSlInfo</tt>.
     *
     * @return property value of ownerSlInfo
     */
    public IBS210111102RsResult getOwnerSlInfo() {
        return ownerSlInfo;
    }

    /**
     * Setter method for property <tt>ownerSlInfo</tt>.
     *
     * @param ownerSlInfo value to be assigned to property ownerSlInfo
     */
    public void setOwnerSlInfo(IBS210111102RsResult ownerSlInfo) {
        this.ownerSlInfo = ownerSlInfo;
    }

    /**
     * Getter method for property <tt>allianceSlInfo</tt>.
     *
     * @return property value of allianceSlInfo
     */
    public IBS210111102RsResult getAllianceSlInfo() {
        return allianceSlInfo;
    }

    /**
     * Setter method for property <tt>allianceSlInfo</tt>.
     *
     * @param allianceSlInfo value to be assigned to property allianceSlInfo
     */
    public void setAllianceSlInfo(IBS210111102RsResult allianceSlInfo) {
        this.allianceSlInfo = allianceSlInfo;
    }
}
