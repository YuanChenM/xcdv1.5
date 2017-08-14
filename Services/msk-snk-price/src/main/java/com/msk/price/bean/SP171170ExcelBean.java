package com.msk.price.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/7/30.
 */
public class SP171170ExcelBean extends BaseParam{
    private List<SP171170Bean> sp171170BeanList;
    /**价盘周期**/
    private  String pricecyclePeriod;
    private String message;
    /**物流区Name**/
    private String lgcsAreaName;
    private String lgcsAreaCode;
    /**品种**/
    private String breedName;
    /**通道等级名称**/
    private  String wayGradeName;
    /**营销状态**/
    private  String wayName;
    private  String marketingName;
    private String wayCode;
    /**产品code**/
    private String pdCode;
    /**产品净重名称**/
    private  String weightName;
    /** 产品类别名称 */
    private String classesName;
    /** 二级分类名称 */
    private String machiningName;
    /** 产品特征名称 */
    private String featureName;
    /**产品等级名称**/
    private String gradeName;
    /**sheetName**/
    private String sheetName;

    private  List<SP171170ExcelBean> excelBeans;

    public List<SP171170Bean> getSp171170BeanList() {
        return sp171170BeanList;
    }

    public void setSp171170BeanList(List<SP171170Bean> sp171170BeanList) {
        this.sp171170BeanList = sp171170BeanList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWayGradeName() {
        return wayGradeName;
    }

    public void setWayGradeName(String wayGradeName) {
        this.wayGradeName = wayGradeName;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
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

    public List<SP171170ExcelBean> getExcelBeans() {
        return excelBeans;
    }

    public void setExcelBeans(List<SP171170ExcelBean> excelBeans) {
        this.excelBeans = excelBeans;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getPricecyclePeriod() {
        return pricecyclePeriod;
    }

    public void setPricecyclePeriod(String pricecyclePeriod) {
        this.pricecyclePeriod = pricecyclePeriod;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }
}
