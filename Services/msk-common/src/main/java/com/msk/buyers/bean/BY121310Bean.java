package com.msk.buyers.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.core.entity.BaseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 买家买家池归属
 * Created by tao_zhifa on 2016/7/11.
 */
public class BY121310Bean extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /** MODIFY_TIME */
    private java.util.Date modifyTime;
    private Long Id;
    private String cityCode;
    private String marketingsStatus;
    private String superiorType;
    private String lgcsAreaCode;
    private String buyerId;
    private String classesCode;
    private String classesName;
    private String machiningCode;
    private String machiningName;
    private Map<String,String> machiningMap;
    private Map<String,String> classesMap;
    private String machiningCodeU;
    /** BUYER_CODE */
    private String buyerCode;
    // 判断颜色
    private String flag;
    /** 画面选中的销售产品 */
    private String[] buyerPdCla;
    /**画面选中的销售二级 */
    private String[] buyerPdMac;
    /** 是否选择 */
    private String isChecked;
    private String marketExceptionStatus;
    private List<BY121310Bean> pageResult;

    public String getBuyerCode() {
        return buyerCode;
    }

    public String getMarketExceptionStatus() {
        return marketExceptionStatus;
    }

    public void setMarketExceptionStatus(String marketExceptionStatus) {
        this.marketExceptionStatus = marketExceptionStatus;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getSuperiorType() {
        return superiorType;
    }

    public String getMarketingsStatus() {
        return marketingsStatus;
    }

    public void setMarketingsStatus(String marketingsStatus) {
        this.marketingsStatus = marketingsStatus;
    }

    public void setSuperiorType(String superiorType) {
        this.superiorType = superiorType;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public List<BY121310Bean> getPageResult() {
        return pageResult;
    }

    public void setPageResult(List<BY121310Bean> pageResult) {
        this.pageResult = pageResult;
    }

    public Map<String, String> getMachiningMap() {
        return machiningMap;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
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

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public void setMachiningMap(Map<String, String> machiningMap) {
        this.machiningMap = machiningMap;
    }

    public Map<String, String> getClassesMap() {
        return classesMap;
    }

    public void setClassesMap(Map<String, String> classesMap) {
        this.classesMap = classesMap;
    }

    public String getMachiningCodeU() {
        return machiningCodeU;
    }

    public void setMachiningCodeU(String machiningCodeU) {
        this.machiningCodeU = machiningCodeU;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String[] getBuyerPdCla() {
        return buyerPdCla;
    }

    public void setBuyerPdCla(String[] buyerPdCla) {
        this.buyerPdCla = buyerPdCla;
    }

    public String[] getBuyerPdMac() {
        return buyerPdMac;
    }

    public void setBuyerPdMac(String[] buyerPdMac) {
        this.buyerPdMac = buyerPdMac;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
