package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 供应商价格申报详细
 * SP171110Bean
 *
 * qi_dianyong
 */
public class SP171110Bean extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * 通道CODE
     */
    private String wayCode;
    /**
     * 营销编码
     */
    private String marketingName;

    /**
     * 价通道等级CODE
     */
    private String wayGradeCode;

    /**
     * 通道等级名称
     */
    private String wayGradeName;

    /**
     * 通道等级箱数下限
     */
    private int wayGradeStart;

    /**
     * 通道等级箱数上限
     */
    private int wayGradeEnd;

    /**
     * 通道级别价格平衡系数
     */
    private BigDecimal wayGradePercent;

    /**
     * 下限价
     */
    private  BigDecimal downPrice;

    /**
     * 价格
     */
    private BigDecimal wayGradePrice;
    /**防止丢失精度，计算箱价用**/
    private BigDecimal wayGradeTempPrice;
    /**箱价**/
    private BigDecimal wayGradePricefBox;

    /**
     * 上限价
     */
    private BigDecimal upPrice;

    /**
     * 前次价盘价格
     */
    private BigDecimal lastWayGradePrice;

    /**
     * 参与分销通道
     */
    private String isValid;

    /**
     * 参与分销通道保存db用
     */
    private String[] isValidArray;

    /**
     *分销通道code保存db用
     */
    private String[] wayCodeArray;

    /**
     *保存db用
     */
    private String[] marketingNameArray;

    /**
     *通道等级code保存db用
     */
    private String[] wayGradeCodeArray;

    /**
     *通道等级名称保存db用
     */
    private String[] wayGradeNameArray;

    /**
     *价盘通道级别对应价格保存db用
     */
    private BigDecimal[] wayGradePriceArray;

    /**
     *价盘通道级别对应价格下限保存db用
     */
    private BigDecimal[] downPriceArray;

    /**
     *价盘通道级别对应价格下限保存db用
     */
    private BigDecimal[] upPriceArray;

    /**
     *通道级别价格平衡系数保存db用
     */
    private BigDecimal[] wayGradePercentArray;

    /**
     * 价格保留4位小数点
     */
    private BigDecimal[] wayGradeTempPriceArray;
    /**
     *报价ID保存db用
     */
    private  long priceId;

    /**
     *报价明细ID保存db用
     */
    private long priceDetailId;

    /**
     *报价明细No保存db用
     */
    private String priceDetailNo;
    /**价盘周期**/
    private String pricePeriod;
    /**物流区编码**/
    private String lgcsCode;
    /**产品编码**/
    private String pdCode;
    /**上一个价盘周期**/
    private String lastPricePeriod;
    /**
     * 物流区名称
     */
    private String lgcsName;
    /**
     * 产品名称
     */
    private String pdName;

    /**
     * 产品一级分类code
     */
    private String classesCode;

    /**
     * 产品一级分类名称
     */
    private String classesName;

    /**
     * 产品二级分类Code
     */
    private String machiningCode;

    /**
     * 产品二级分类名称
     */
    private String machining;

    /**
     * 品种编码
     */
    private String breedCode;

    /**
     * 品种名称
     */
    private String breed;

    /**
     * 产品特征Code
     */
    private String featureCode;

    /**
     * 产品特征
     */
    private String feature;

    /**
     * 净重编码
     */
    private String weightCode;

    /**
     * 净重名称
     */
    private String weight;

    /**
     * 产品特征Code
     */
    private String gradeCode;

    /**
     * 产品特征
     */
    private String grade;

    /**
     * 价盘周期开始日期
     */
    private String pricePeriodStart;

    /**
     * 价盘周期结束日期
     */
    private String pricePeriodEnd;
    /**
     * Getter method for property <tt>wayCode</tt>.
     *
     * @return property value of wayCode
     */
    public String getWayCode() {
        return wayCode;
    }

    /**
     * Setter method for property <tt>wayCode</tt>.
     *
     * @param wayCode value to be assigned to property wayCode
     */
    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    /**
     * Getter method for property <tt>wayGradeCode</tt>.
     *
     * @return property value of wayGradeCode
     */
    public String getWayGradeCode() {
        return wayGradeCode;
    }

    /**
     * Setter method for property <tt>wayGradeCode</tt>.
     *
     * @param wayGradeCode value to be assigned to property wayGradeCode
     */
    public void setWayGradeCode(String wayGradeCode) {
        this.wayGradeCode = wayGradeCode;
    }

    /**
     * Getter method for property <tt>wayGradeName</tt>.
     *
     * @return property value of wayGradeName
     */
    public String getWayGradeName() {
        return wayGradeName;
    }

    /**
     * Setter method for property <tt>wayGradeName</tt>.
     *
     * @param wayGradeName value to be assigned to property wayGradeName
     */
    public void setWayGradeName(String wayGradeName) {
        this.wayGradeName = wayGradeName;
    }

    /**
     * Getter method for property <tt>wayGradeStart</tt>.
     *
     * @return property value of wayGradeStart
     */
    public int getWayGradeStart() {
        return wayGradeStart;
    }

    /**
     * Setter method for property <tt>wayGradeStart</tt>.
     *
     * @param wayGradeStart value to be assigned to property wayGradeStart
     */
    public void setWayGradeStart(int wayGradeStart) {
        this.wayGradeStart = wayGradeStart;
    }

    /**
     * Getter method for property <tt>wayGradeEnd</tt>.
     *
     * @return property value of wayGradeEnd
     */
    public int getWayGradeEnd() {
        return wayGradeEnd;
    }

    /**
     * Setter method for property <tt>wayGradeEnd</tt>.
     *
     * @param wayGradeEnd value to be assigned to property wayGradeEnd
     */
    public void setWayGradeEnd(int wayGradeEnd) {
        this.wayGradeEnd = wayGradeEnd;
    }

    /**
     * Getter method for property <tt>wayGradePercent</tt>.
     *
     * @return property value of wayGradePercent
     */
    public BigDecimal getWayGradePercent() {
        return wayGradePercent;
    }

    /**
     * Setter method for property <tt>wayGradePercent</tt>.
     *
     * @param wayGradePercent value to be assigned to property wayGradePercent
     */
    public void setWayGradePercent(BigDecimal wayGradePercent) {
        this.wayGradePercent = wayGradePercent;
    }

    /**
     * Getter method for property <tt>downPrice</tt>.
     *
     * @return property value of downPrice
     */
    public BigDecimal getDownPrice() {
        return downPrice;
    }

    /**
     * Setter method for property <tt>downPrice</tt>.
     *
     * @param downPrice value to be assigned to property downPrice
     */
    public void setDownPrice(BigDecimal downPrice) {
        this.downPrice = downPrice;
    }

    /**
     * Getter method for property <tt>wayGradePrice</tt>.
     *
     * @return property value of wayGradePrice
     */
    public BigDecimal getWayGradePrice() {
        return wayGradePrice;
    }

    /**
     * Setter method for property <tt>wayGradePrice</tt>.
     *
     * @param wayGradePrice value to be assigned to property wayGradePrice
     */
    public void setWayGradePrice(BigDecimal wayGradePrice) {
        this.wayGradePrice = wayGradePrice;
    }

    /**
     * Getter method for property <tt>upPrice</tt>.
     *
     * @return property value of upPrice
     */
    public BigDecimal getUpPrice() {
        return upPrice;
    }

    /**
     * Setter method for property <tt>upPrice</tt>.
     *
     * @param upPrice value to be assigned to property upPrice
     */
    public void setUpPrice(BigDecimal upPrice) {
        this.upPrice = upPrice;
    }

    /**
     * Getter method for property <tt>lastWayGradePrice</tt>.
     *
     * @return property value of lastWayGradePrice
     */
    public BigDecimal getLastWayGradePrice() {
        return lastWayGradePrice;
    }

    /**
     * Setter method for property <tt>lastWayGradePrice</tt>.
     *
     * @param lastWayGradePrice value to be assigned to property lastWayGradePrice
     */
    public void setLastWayGradePrice(BigDecimal lastWayGradePrice) {
        this.lastWayGradePrice = lastWayGradePrice;
    }

    /**
     * Getter method for property <tt>isValid</tt>.
     *
     * @return property value of isValid
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * Setter method for property <tt>isValid</tt>.
     *
     * @param isValid value to be assigned to property isValid
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    /**
     * Getter method for property <tt>isValidArray</tt>.
     *
     * @return property value of isValidArray
     */
    public String[] getIsValidArray() {
        return isValidArray;
    }

    /**
     * Setter method for property <tt>isValidArray</tt>.
     *
     * @param isValidArray value to be assigned to property isValidArray
     */
    public void setIsValidArray(String[] isValidArray) {
        this.isValidArray = isValidArray;
    }

    /**
     * Getter method for property <tt>wayCodeArray</tt>.
     *
     * @return property value of wayCodeArray
     */
    public String[] getWayCodeArray() {
        return wayCodeArray;
    }

    /**
     * Setter method for property <tt>wayCodeArray</tt>.
     *
     * @param wayCodeArray value to be assigned to property wayCodeArray
     */
    public void setWayCodeArray(String[] wayCodeArray) {
        this.wayCodeArray = wayCodeArray;
    }

    /**
     * Getter method for property <tt>wayGradeCodeArray</tt>.
     *
     * @return property value of wayGradeCodeArray
     */
    public String[] getWayGradeCodeArray() {
        return wayGradeCodeArray;
    }

    /**
     * Setter method for property <tt>wayGradeCodeArray</tt>.
     *
     * @param wayGradeCodeArray value to be assigned to property wayGradeCodeArray
     */
    public void setWayGradeCodeArray(String[] wayGradeCodeArray) {
        this.wayGradeCodeArray = wayGradeCodeArray;
    }

    /**
     * Getter method for property <tt>wayGradeNameArray</tt>.
     *
     * @return property value of wayGradeNameArray
     */
    public String[] getWayGradeNameArray() {
        return wayGradeNameArray;
    }

    /**
     * Setter method for property <tt>wayGradeNameArray</tt>.
     *
     * @param wayGradeNameArray value to be assigned to property wayGradeNameArray
     */
    public void setWayGradeNameArray(String[] wayGradeNameArray) {
        this.wayGradeNameArray = wayGradeNameArray;
    }

    /**
     * Getter method for property <tt>wayGradePriceArray</tt>.
     *
     * @return property value of wayGradePriceArray
     */
    public BigDecimal[] getWayGradePriceArray() {
        return wayGradePriceArray;
    }

    /**
     * Setter method for property <tt>wayGradePriceArray</tt>.
     *
     * @param wayGradePriceArray value to be assigned to property wayGradePriceArray
     */
    public void setWayGradePriceArray(BigDecimal[] wayGradePriceArray) {
        this.wayGradePriceArray = wayGradePriceArray;
    }

    /**
     * Getter method for property <tt>downPriceArray</tt>.
     *
     * @return property value of downPriceArray
     */
    public BigDecimal[] getDownPriceArray() {
        return downPriceArray;
    }

    /**
     * Setter method for property <tt>downPriceArray</tt>.
     *
     * @param downPriceArray value to be assigned to property downPriceArray
     */
    public void setDownPriceArray(BigDecimal[] downPriceArray) {
        this.downPriceArray = downPriceArray;
    }

    /**
     * Getter method for property <tt>upPriceArray</tt>.
     *
     * @return property value of upPriceArray
     */
    public BigDecimal[] getUpPriceArray() {
        return upPriceArray;
    }

    /**
     * Setter method for property <tt>upPriceArray</tt>.
     *
     * @param upPriceArray value to be assigned to property upPriceArray
     */
    public void setUpPriceArray(BigDecimal[] upPriceArray) {
        this.upPriceArray = upPriceArray;
    }

    /**
     * Getter method for property <tt>wayGradePercentArray</tt>.
     *
     * @return property value of wayGradePercentArray
     */
    public BigDecimal[] getWayGradePercentArray() {
        return wayGradePercentArray;
    }

    /**
     * Setter method for property <tt>wayGradePercentArray</tt>.
     *
     * @param wayGradePercentArray value to be assigned to property wayGradePercentArray
     */
    public void setWayGradePercentArray(BigDecimal[] wayGradePercentArray) {
        this.wayGradePercentArray = wayGradePercentArray;
    }

    /**
     * Getter method for property <tt>priceId</tt>.
     *
     * @return property value of priceId
     */
    public long getPriceId() {
        return priceId;
    }

    /**
     * Setter method for property <tt>priceId</tt>.
     *
     * @param priceId value to be assigned to property priceId
     */
    public void setPriceId(long priceId) {
        this.priceId = priceId;
    }

    /**
     * Getter method for property <tt>priceDetailId</tt>.
     *
     * @return property value of priceDetailId
     */
    public long getPriceDetailId() {
        return priceDetailId;
    }

    /**
     * Setter method for property <tt>priceDetailId</tt>.
     *
     * @param priceDetailId value to be assigned to property priceDetailId
     */
    public void setPriceDetailId(long priceDetailId) {
        this.priceDetailId = priceDetailId;
    }

    /**
     * Getter method for property <tt>priceDetailNo</tt>.
     *
     * @return property value of priceDetailNo
     */
    public String getPriceDetailNo() {
        return priceDetailNo;
    }

    /**
     * Setter method for property <tt>priceDetailNo</tt>.
     *
     * @param priceDetailNo value to be assigned to property priceDetailNo
     */
    public void setPriceDetailNo(String priceDetailNo) {
        this.priceDetailNo = priceDetailNo;
    }

    public BigDecimal getWayGradePricefBox() {
        return wayGradePricefBox;
    }

    public void setWayGradePricefBox(BigDecimal wayGradePricefBox) {
        this.wayGradePricefBox = wayGradePricefBox;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
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

    public String getLastPricePeriod() {
        return lastPricePeriod;
    }

    public void setLastPricePeriod(String lastPricePeriod) {
        this.lastPricePeriod = lastPricePeriod;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
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

    public String getMachining() {
        return machining;
    }

    public void setMachining(String machining) {
        this.machining = machining;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPricePeriodStart() {
        return pricePeriodStart;
    }

    public void setPricePeriodStart(String pricePeriodStart) {
        this.pricePeriodStart = pricePeriodStart;
    }

    public String getPricePeriodEnd() {
        return pricePeriodEnd;
    }

    public void setPricePeriodEnd(String pricePeriodEnd) {
        this.pricePeriodEnd = pricePeriodEnd;
    }

    public BigDecimal[] getWayGradeTempPriceArray() {
        return wayGradeTempPriceArray;
    }

    public void setWayGradeTempPriceArray(BigDecimal[] wayGradeTempPriceArray) {
        this.wayGradeTempPriceArray = wayGradeTempPriceArray;
    }

    public BigDecimal getWayGradeTempPrice() {
        return wayGradeTempPrice;
    }

    public void setWayGradeTempPrice(BigDecimal wayGradeTempPrice) {
        this.wayGradeTempPrice = wayGradeTempPrice;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public String[] getMarketingNameArray() {
        return marketingNameArray;
    }

    public void setMarketingNameArray(String[] marketingNameArray) {
        this.marketingNameArray = marketingNameArray;
    }
}

