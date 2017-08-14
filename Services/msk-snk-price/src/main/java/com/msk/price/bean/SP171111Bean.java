package com.msk.price.bean;

import com.msk.common.base.BaseBean;
import java.util.Date;
/**
 * Created by peng_hao
 */
public class SP171111Bean extends BaseBean{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /*报价ID*/
    private String priceId;
    /*发布年月周期 */
    private String pricePeriod;
    /*发布年月周期开始日 */
    private Date priceStartdate;
    /*发布年月周期结束日 */
    private Date priceEnddate;
    /*物流区CODE */
    private String lgcsCode;
    /*产品编码 */
    private String pdCode;
    /*产品名称 */
    private String pdName;
    /*产品一级分类编码 */
    private String classesCode;
    /*产品二级分类编码 */
    private String machiningCode;
    /*品种编码 */
    private String breedCode;
    /*特征编码 */
    private String featureCode;
    /*净重编码 */
    private String weightCode;
    /*等级编码 */
    private String gradeCode;
    /*产品一级分类编码 */
    private String classesName;
    /*产品二级分类编码 */
    private String machiningName;
    /*品种名称 */
    private String breedName;
    /*特征名称 */
    private String featureName;
    /*净重名称 */
    private String weightName;
    /*等级名称 */
    private String gradeName;
    /*卖家Account*/
    private String slAccount;
    /*卖家id*/
    private String slId;
    /*卖家code*/
    private String slCode;
    /*企业Id*/
    private String epId;
    /*企业名*/
    private String epName;
    /*分销通道级别*/
    private String wayGradeCode;
    /*分销通道级别对应价格*/
    private String wayGradePrice;

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public Date getPriceStartdate() {
        return priceStartdate;
    }

    public void setPriceStartdate(Date priceStartdate) {
        this.priceStartdate = priceStartdate;
    }

    public Date getPriceEnddate() {
        return priceEnddate;
    }

    public void setPriceEnddate(Date priceEnddate) {
        this.priceEnddate = priceEnddate;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
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

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getWayGradeCode() {
        return wayGradeCode;
    }

    public void setWayGradeCode(String wayGradeCode) {
        this.wayGradeCode = wayGradeCode;
    }

    public String getWayGradePrice() {
        return wayGradePrice;
    }

    public void setWayGradePrice(String wayGradePrice) {
        this.wayGradePrice = wayGradePrice;
    }
}
