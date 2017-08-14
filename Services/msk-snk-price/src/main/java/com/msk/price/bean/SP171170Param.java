package com.msk.price.bean;

import com.hoperun.core.bean.BasePageParam;

/**
 * Created by zhu_kai1 on 2016/6/23.
 */
public class SP171170Param extends BasePageParam{

    private String flag;
    /** 价盘周期 */
    private String pricecyclePeriod;
    /**产品一级分类code**/
    private String classesCode;
    /**产品二级分类**/
    private String machiningCode;
    /**物流区code**/
    private String lgcsAreaCode;
    /**公斤价**/
    private String priceofkg;
    /**品种**/
    private String breedName;
    private String title;
    /**价盘年月**/
    private String priceDate;
    /**价盘日期**/
    private String pricecycle;
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPricecyclePeriod() {
        return pricecyclePeriod;
    }

    public void setPricecyclePeriod(String pricecyclePeriod) {
        this.pricecyclePeriod = pricecyclePeriod;
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

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getPriceofkg() {
        return priceofkg;
    }

    public void setPriceofkg(String priceofkg) {
        this.priceofkg = priceofkg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(String priceDate) {
        this.priceDate = priceDate;
    }

    public String getPricecycle() {
        return pricecycle;
    }

    public void setPricecycle(String pricecycle) {
        this.pricecycle = pricecycle;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
}
