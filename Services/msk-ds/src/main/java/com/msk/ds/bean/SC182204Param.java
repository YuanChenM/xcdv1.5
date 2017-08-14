package com.msk.ds.bean;

import com.hoperun.core.bean.BaseParam;
import java.util.List;

/**
 * yi_qixiang.
 */
public class SC182204Param extends BaseParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**分销月度 */
    private String distMonth;
    /**产品库存类型 */
    private String pdStockType;
    /**半旬码 */
    private String halfCode;

    /**销售区域 */
    private String lgcsCode;
    /**供应商 */
    private String suppCode;

    /**用户账户 */
    private String slAccount;

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    /**配料 */
    private String machiningCode;
    /**规格 */
    private String featureCode;
    /**净重 */
    private String weightCode;
    /**产品等级 */
    private String gradeCode;
    /**生产日期 */
    private String manuDate;
    /**保质期 */
    private String shelfLife;
    /**存储条件 */
    private String storCondition;
    /**产品标准号 */
    private String proStanCode;
    /**食品生产许可证 */
    private String foodManuLicen;
    /**委托方 */
    private String client;
    /**被委托方 */
    private String unClient;
    /**委托方地址 */
    private String clientAddr;
    /**被委托方地址 */
    private String unClientAddr;
    /**委托方电话 */
    private String clientPhone;
    /**被委托方电话 */
    private String unClientPhone;
    /**产地 */
    private String manuOrigin;
    /**品牌标志 */
    private String brandLogo;
    /**操作码 */
    private String proLotNum;
    /**阅读码 */
    private String readProLotNum;
    /**总箱数 */
    private String sumNewActNum;
    /**产品类别 */
    private String classesCode;
    /**产品品种 */
    private String breedCode;
    /**半旬期 */
    private String halfPeriod;
    /**包装编码 */
    private String normsCode;

    public void setReadProLotNum(String readProLotNum) {
        this.readProLotNum = readProLotNum;
    }

    public String getReadProLotNum() {
        return readProLotNum;
    }

    public void setHalfPeriod(String halfPeriod) {
        this.halfPeriod = halfPeriod;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public String getHalfPeriod() {
        return halfPeriod;
    }

    public String getNormsCode() {
        return normsCode;
    }

    private List<SC182204ProductParam> productParamList;

    public List<SC182204ProductParam> getProductParamList() {
        return productParamList;
    }

    public void setProductParamList(List<SC182204ProductParam> productParamList) {
        this.productParamList = productParamList;
    }

    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public void setPdStockType(String pdStockType) {
        this.pdStockType = pdStockType;
    }

    public void setHalfCode(String halfCode) {
        this.halfCode = halfCode;
    }


    public String getDistMonth() {
        return distMonth;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public String getPdStockType() {
        return pdStockType;
    }

    public String getHalfCode() {
        return halfCode;
    }


    public String getMachiningCode() {
        return machiningCode;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public String getManuDate() {
        return manuDate;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public String getStorCondition() {
        return storCondition;
    }

    public String getProStanCode() {
        return proStanCode;
    }

    public String getFoodManuLicen() {
        return foodManuLicen;
    }

    public String getClient() {
        return client;
    }

    public String getUnClient() {
        return unClient;
    }

    public String getClientAddr() {
        return clientAddr;
    }

    public String getUnClientAddr() {
        return unClientAddr;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public String getUnClientPhone() {
        return unClientPhone;
    }

    public String getManuOrigin() {
        return manuOrigin;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public String getProLotNum() {
        return proLotNum;
    }


    public String getClassesCode() {
        return classesCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public void setManuDate(String manuDate) {
        this.manuDate = manuDate;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public void setStorCondition(String storCondition) {
        this.storCondition = storCondition;
    }

    public void setProStanCode(String proStanCode) {
        this.proStanCode = proStanCode;
    }

    public void setFoodManuLicen(String foodManuLicen) {
        this.foodManuLicen = foodManuLicen;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setUnClient(String unClient) {
        this.unClient = unClient;
    }

    public void setClientAddr(String clientAddr) {
        this.clientAddr = clientAddr;
    }

    public void setUnClientAddr(String unClientAddr) {
        this.unClientAddr = unClientAddr;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public void setUnClientPhone(String unClientPhone) {
        this.unClientPhone = unClientPhone;
    }

    public void setManuOrigin(String manuOrigin) {
        this.manuOrigin = manuOrigin;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public void setProLotNum(String proLotNum) {
        this.proLotNum = proLotNum;
    }


    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getSumNewActNum() {
        return sumNewActNum;
    }

    public void setSumNewActNum(String sumNewActNum) {
        this.sumNewActNum = sumNewActNum;
    }
}
