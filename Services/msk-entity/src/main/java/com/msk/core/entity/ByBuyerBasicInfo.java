/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_buyer_basic_info对应的ByBuyerBasicInfo</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByBuyerBasicInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** BUYER_ID */
    private String buyerId;
    /** BUYER_CODE */
    private String buyerCode;
    /** BUYER_NAME */
    private String buyerName;
    /** BUYER_ADDR */
    private String buyerAddr;
    /** 菜场或批发市场的ID */
    private String superiorId;
    /** SUPERIOR_QUA */
    private String superiorQua;
    /** 参考CONSTANT表 */
    private String superiorType;
    /** SUPERIOR_NAME */
    private String superiorName;
    /** 参考CONSTANT表 */
    private String superiorSubType;
    /** SUPERIOR_SUB_NAME */
    private String superiorSubName;
    /** 是否菜场买家（卤肉买家专用） */
    private String isMarketFlg;
    /** PROVINCE_CODE */
    private String provinceCode;
    /** PROVINCE_NAME */
    private String provinceName;
    /** LGCS_AREA_CODE */
    private String lgcsAreaCode;
    /** LGCS_AREA_NAME */
    private String lgcsAreaName;
    /** CITY_CODE */
    private String cityCode;
    /** CITY_NAME */
    private String cityName;
    /** DISTRICT_CODE */
    private String districtCode;
    /** DISTRICT_NAME */
    private String districtName;
    /** BUYER_WEBSITE */
    private String buyerWebsite;
    /** BUYER_WECHAT */
    private String buyerWechat;
    /** BUYER_SINGLE_WECHAT */
    private String buyerSingleWechat;
    /** BUYER_QQ */
    private String buyerQq;
    /** STORE_NO */
    private String storeNo;
    /** STORE_AREA */
    private java.math.BigDecimal storeArea;
    /** BUSI_TEL */
    private String busiTel;
    /** EMPLOYEES_NUM */
    private Integer employeesNum;
    /** 习惯收货时间段 */
    private String habitRecTime;
    /** 最早收货时间 */
    private String earliestRecTime;
    /** 最晚收货时间 */
    private String latestRecTime;
    /** 参考CONSTANT表，逗号隔开，多选 */
    private String paymentType;
    /** PLAN_ORDER_GAP */
    private String planOrderGap;
    /** PLAN_ORDER_NUM */
    private String planOrderNum;
    /** ACTUAL_ORDER_GAP */
    private String actualOrderGap;
    /** ACTUAL_ORDER_NUM */
    private String actualOrderNum;
    /** 营销/ 销售状态 */
    private String marketingsStatus;
    /** MARKET_EXCEPTION_STATUS */
    private String marketExceptionStatus;
    /** MARKET_EXCEPTION_REMARK */
    private String marketExceptionRemark;
    /** MARKETING_TOOLS */
    private String marketingTools;
    /** TEL_MARKETING_START_TIME */
    private String telMarketingStartTime;
    /** TEL_MARKETING_END_TIME */
    private String telMarketingEndTime;
    /** REGISTER_SOURCE */
    private String registerSource;
    /**
     * <p>默认构造函数</p>
     */
    public ByBuyerBasicInfo() {

    }

    /**
     * <p>BUYER_ID</p>
     *
     * @return the BUYER_ID
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @param buyerId BUYER_ID
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * <p>BUYER_CODE</p>
     *
     * @return the BUYER_CODE
     */
    public String getBuyerCode() {
        return buyerCode;
    }

    /**
     * <p>BUYER_CODE</p>
     *
     * @param buyerCode BUYER_CODE
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    /**
     * <p>BUYER_NAME</p>
     *
     * @return the BUYER_NAME
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * <p>BUYER_NAME</p>
     *
     * @param buyerName BUYER_NAME
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * <p>BUYER_ADDR</p>
     *
     * @return the BUYER_ADDR
     */
    public String getBuyerAddr() {
        return buyerAddr;
    }

    /**
     * <p>BUYER_ADDR</p>
     *
     * @param buyerAddr BUYER_ADDR
     */
    public void setBuyerAddr(String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    /**
     * <p>菜场或批发市场的ID</p>
     *
     * @return the 菜场或批发市场的ID
     */
    public String getSuperiorId() {
        return superiorId;
    }

    /**
     * <p>菜场或批发市场的ID</p>
     *
     * @param superiorId 菜场或批发市场的ID
     */
    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }

    /**
     * <p>SUPERIOR_QUA</p>
     *
     * @return the SUPERIOR_QUA
     */
    public String getSuperiorQua() {
        return superiorQua;
    }

    /**
     * <p>SUPERIOR_QUA</p>
     *
     * @param superiorQua SUPERIOR_QUA
     */
    public void setSuperiorQua(String superiorQua) {
        this.superiorQua = superiorQua;
    }

    /**
     * <p>参考CONSTANT表</p>
     *
     * @return the 参考CONSTANT表
     */
    public String getSuperiorType() {
        return superiorType;
    }

    /**
     * <p>参考CONSTANT表</p>
     *
     * @param superiorType 参考CONSTANT表
     */
    public void setSuperiorType(String superiorType) {
        this.superiorType = superiorType;
    }

    /**
     * <p>SUPERIOR_NAME</p>
     *
     * @return the SUPERIOR_NAME
     */
    public String getSuperiorName() {
        return superiorName;
    }

    /**
     * <p>SUPERIOR_NAME</p>
     *
     * @param superiorName SUPERIOR_NAME
     */
    public void setSuperiorName(String superiorName) {
        this.superiorName = superiorName;
    }

    /**
     * <p>参考CONSTANT表</p>
     *
     * @return the 参考CONSTANT表
     */
    public String getSuperiorSubType() {
        return superiorSubType;
    }

    /**
     * <p>参考CONSTANT表</p>
     *
     * @param superiorSubType 参考CONSTANT表
     */
    public void setSuperiorSubType(String superiorSubType) {
        this.superiorSubType = superiorSubType;
    }

    /**
     * <p>SUPERIOR_SUB_NAME</p>
     *
     * @return the SUPERIOR_SUB_NAME
     */
    public String getSuperiorSubName() {
        return superiorSubName;
    }

    /**
     * <p>SUPERIOR_SUB_NAME</p>
     *
     * @param superiorSubName SUPERIOR_SUB_NAME
     */
    public void setSuperiorSubName(String superiorSubName) {
        this.superiorSubName = superiorSubName;
    }

    /**
     * <p>是否菜场买家（卤肉买家专用）</p>
     *
     * @return the 是否菜场买家（卤肉买家专用）
     */
    public String getIsMarketFlg() {
        return isMarketFlg;
    }

    /**
     * <p>是否菜场买家（卤肉买家专用）</p>
     *
     * @param isMarketFlg 是否菜场买家（卤肉买家专用）
     */
    public void setIsMarketFlg(String isMarketFlg) {
        this.isMarketFlg = isMarketFlg;
    }

    /**
     * <p>PROVINCE_CODE</p>
     *
     * @return the PROVINCE_CODE
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * <p>PROVINCE_CODE</p>
     *
     * @param provinceCode PROVINCE_CODE
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * <p>PROVINCE_NAME</p>
     *
     * @return the PROVINCE_NAME
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * <p>PROVINCE_NAME</p>
     *
     * @param provinceName PROVINCE_NAME
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * <p>LGCS_AREA_CODE</p>
     *
     * @return the LGCS_AREA_CODE
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * <p>LGCS_AREA_CODE</p>
     *
     * @param lgcsAreaCode LGCS_AREA_CODE
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * <p>LGCS_AREA_NAME</p>
     *
     * @return the LGCS_AREA_NAME
     */
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * <p>LGCS_AREA_NAME</p>
     *
     * @param lgcsAreaName LGCS_AREA_NAME
     */
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    /**
     * <p>CITY_CODE</p>
     *
     * @return the CITY_CODE
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * <p>CITY_CODE</p>
     *
     * @param cityCode CITY_CODE
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * <p>CITY_NAME</p>
     *
     * @return the CITY_NAME
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * <p>CITY_NAME</p>
     *
     * @param cityName CITY_NAME
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * <p>DISTRICT_CODE</p>
     *
     * @return the DISTRICT_CODE
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * <p>DISTRICT_CODE</p>
     *
     * @param districtCode DISTRICT_CODE
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * <p>DISTRICT_NAME</p>
     *
     * @return the DISTRICT_NAME
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * <p>DISTRICT_NAME</p>
     *
     * @param districtName DISTRICT_NAME
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * <p>BUYER_WEBSITE</p>
     *
     * @return the BUYER_WEBSITE
     */
    public String getBuyerWebsite() {
        return buyerWebsite;
    }

    /**
     * <p>BUYER_WEBSITE</p>
     *
     * @param buyerWebsite BUYER_WEBSITE
     */
    public void setBuyerWebsite(String buyerWebsite) {
        this.buyerWebsite = buyerWebsite;
    }

    /**
     * <p>BUYER_WECHAT</p>
     *
     * @return the BUYER_WECHAT
     */
    public String getBuyerWechat() {
        return buyerWechat;
    }

    /**
     * <p>BUYER_WECHAT</p>
     *
     * @param buyerWechat BUYER_WECHAT
     */
    public void setBuyerWechat(String buyerWechat) {
        this.buyerWechat = buyerWechat;
    }

    /**
     * <p>BUYER_SINGLE_WECHAT</p>
     *
     * @return the BUYER_SINGLE_WECHAT
     */
    public String getBuyerSingleWechat() {
        return buyerSingleWechat;
    }

    /**
     * <p>BUYER_SINGLE_WECHAT</p>
     *
     * @param buyerSingleWechat BUYER_SINGLE_WECHAT
     */
    public void setBuyerSingleWechat(String buyerSingleWechat) {
        this.buyerSingleWechat = buyerSingleWechat;
    }

    /**
     * <p>BUYER_QQ</p>
     *
     * @return the BUYER_QQ
     */
    public String getBuyerQq() {
        return buyerQq;
    }

    /**
     * <p>BUYER_QQ</p>
     *
     * @param buyerQq BUYER_QQ
     */
    public void setBuyerQq(String buyerQq) {
        this.buyerQq = buyerQq;
    }

    /**
     * <p>STORE_NO</p>
     *
     * @return the STORE_NO
     */
    public String getStoreNo() {
        return storeNo;
    }

    /**
     * <p>STORE_NO</p>
     *
     * @param storeNo STORE_NO
     */
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    /**
     * <p>STORE_AREA</p>
     *
     * @return the STORE_AREA
     */
    public java.math.BigDecimal getStoreArea() {
        return storeArea;
    }

    /**
     * <p>STORE_AREA</p>
     *
     * @param storeArea STORE_AREA
     */
    public void setStoreArea(java.math.BigDecimal storeArea) {
        this.storeArea = storeArea;
    }

    /**
     * <p>BUSI_TEL</p>
     *
     * @return the BUSI_TEL
     */
    public String getBusiTel() {
        return busiTel;
    }

    /**
     * <p>BUSI_TEL</p>
     *
     * @param busiTel BUSI_TEL
     */
    public void setBusiTel(String busiTel) {
        this.busiTel = busiTel;
    }

    /**
     * <p>EMPLOYEES_NUM</p>
     *
     * @return the EMPLOYEES_NUM
     */
    public Integer getEmployeesNum() {
        return employeesNum;
    }

    /**
     * <p>EMPLOYEES_NUM</p>
     *
     * @param employeesNum EMPLOYEES_NUM
     */
    public void setEmployeesNum(Integer employeesNum) {
        this.employeesNum = employeesNum;
    }

    /**
     * <p>习惯收货时间段</p>
     *
     * @return the 习惯收货时间段
     */
    public String getHabitRecTime() {
        return habitRecTime;
    }

    /**
     * <p>习惯收货时间段</p>
     *
     * @param habitRecTime 习惯收货时间段
     */
    public void setHabitRecTime(String habitRecTime) {
        this.habitRecTime = habitRecTime;
    }

    /**
     * <p>最早收货时间</p>
     *
     * @return the 最早收货时间
     */
    public String getEarliestRecTime() {
        return earliestRecTime;
    }

    /**
     * <p>最早收货时间</p>
     *
     * @param earliestRecTime 最早收货时间
     */
    public void setEarliestRecTime(String earliestRecTime) {
        this.earliestRecTime = earliestRecTime;
    }

    /**
     * <p>最晚收货时间</p>
     *
     * @return the 最晚收货时间
     */
    public String getLatestRecTime() {
        return latestRecTime;
    }

    /**
     * <p>最晚收货时间</p>
     *
     * @param latestRecTime 最晚收货时间
     */
    public void setLatestRecTime(String latestRecTime) {
        this.latestRecTime = latestRecTime;
    }

    /**
     * <p>参考CONSTANT表，逗号隔开，多选</p>
     *
     * @return the 参考CONSTANT表，逗号隔开，多选
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * <p>参考CONSTANT表，逗号隔开，多选</p>
     *
     * @param paymentType 参考CONSTANT表，逗号隔开，多选
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * <p>PLAN_ORDER_GAP</p>
     *
     * @return the PLAN_ORDER_GAP
     */
    public String getPlanOrderGap() {
        return planOrderGap;
    }

    /**
     * <p>PLAN_ORDER_GAP</p>
     *
     * @param planOrderGap PLAN_ORDER_GAP
     */
    public void setPlanOrderGap(String planOrderGap) {
        this.planOrderGap = planOrderGap;
    }

    /**
     * <p>PLAN_ORDER_NUM</p>
     *
     * @return the PLAN_ORDER_NUM
     */
    public String getPlanOrderNum() {
        return planOrderNum;
    }

    /**
     * <p>PLAN_ORDER_NUM</p>
     *
     * @param planOrderNum PLAN_ORDER_NUM
     */
    public void setPlanOrderNum(String planOrderNum) {
        this.planOrderNum = planOrderNum;
    }

    /**
     * <p>ACTUAL_ORDER_GAP</p>
     *
     * @return the ACTUAL_ORDER_GAP
     */
    public String getActualOrderGap() {
        return actualOrderGap;
    }

    /**
     * <p>ACTUAL_ORDER_GAP</p>
     *
     * @param actualOrderGap ACTUAL_ORDER_GAP
     */
    public void setActualOrderGap(String actualOrderGap) {
        this.actualOrderGap = actualOrderGap;
    }

    /**
     * <p>ACTUAL_ORDER_NUM</p>
     *
     * @return the ACTUAL_ORDER_NUM
     */
    public String getActualOrderNum() {
        return actualOrderNum;
    }

    /**
     * <p>ACTUAL_ORDER_NUM</p>
     *
     * @param actualOrderNum ACTUAL_ORDER_NUM
     */
    public void setActualOrderNum(String actualOrderNum) {
        this.actualOrderNum = actualOrderNum;
    }

    /**
     * <p>营销/ 销售状态</p>
     *
     * @return the 营销/ 销售状态
     */
    public String getMarketingsStatus() {
        return marketingsStatus;
    }

    /**
     * <p>营销/ 销售状态</p>
     *
     * @param marketingsStatus 营销/ 销售状态
     */
    public void setMarketingsStatus(String marketingsStatus) {
        this.marketingsStatus = marketingsStatus;
    }

    /**
     * <p>MARKET_EXCEPTION_STATUS</p>
     *
     * @return the MARKET_EXCEPTION_STATUS
     */
    public String getMarketExceptionStatus() {
        return marketExceptionStatus;
    }

    /**
     * <p>MARKET_EXCEPTION_STATUS</p>
     *
     * @param marketExceptionStatus MARKET_EXCEPTION_STATUS
     */
    public void setMarketExceptionStatus(String marketExceptionStatus) {
        this.marketExceptionStatus = marketExceptionStatus;
    }

    /**
     * <p>MARKET_EXCEPTION_REMARK</p>
     *
     * @return the MARKET_EXCEPTION_REMARK
     */
    public String getMarketExceptionRemark() {
        return marketExceptionRemark;
    }

    /**
     * <p>MARKET_EXCEPTION_REMARK</p>
     *
     * @param marketExceptionRemark MARKET_EXCEPTION_REMARK
     */
    public void setMarketExceptionRemark(String marketExceptionRemark) {
        this.marketExceptionRemark = marketExceptionRemark;
    }

    /**
     * <p>MARKETING_TOOLS</p>
     *
     * @return the MARKETING_TOOLS
     */
    public String getMarketingTools() {
        return marketingTools;
    }

    /**
     * <p>MARKETING_TOOLS</p>
     *
     * @param marketingTools MARKETING_TOOLS
     */
    public void setMarketingTools(String marketingTools) {
        this.marketingTools = marketingTools;
    }

    /**
     * <p>TEL_MARKETING_START_TIME</p>
     *
     * @return the TEL_MARKETING_START_TIME
     */
    public String getTelMarketingStartTime() {
        return telMarketingStartTime;
    }

    /**
     * <p>TEL_MARKETING_START_TIME</p>
     *
     * @param telMarketingStartTime TEL_MARKETING_START_TIME
     */
    public void setTelMarketingStartTime(String telMarketingStartTime) {
        this.telMarketingStartTime = telMarketingStartTime;
    }

    /**
     * <p>TEL_MARKETING_END_TIME</p>
     *
     * @return the TEL_MARKETING_END_TIME
     */
    public String getTelMarketingEndTime() {
        return telMarketingEndTime;
    }

    /**
     * <p>TEL_MARKETING_END_TIME</p>
     *
     * @param telMarketingEndTime TEL_MARKETING_END_TIME
     */
    public void setTelMarketingEndTime(String telMarketingEndTime) {
        this.telMarketingEndTime = telMarketingEndTime;
    }

    /**
     * <p>REGISTER_SOURCE</p>
     *
     * @return the REGISTER_SOURCE
     */
    public String getRegisterSource() {
        return registerSource;
    }

    /**
     * <p>REGISTER_SOURCE</p>
     *
     * @param registerSource REGISTER_SOURCE
     */
    public void setRegisterSource(String registerSource) {
        this.registerSource = registerSource;
    }

}
