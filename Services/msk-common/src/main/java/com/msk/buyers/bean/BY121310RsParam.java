package com.msk.buyers.bean;

import com.hoperun.core.bean.BasePageParam;

import java.util.Map;

/**
 * 买家买家池归属
 * Created by tao_zhifa on 2016/7/11.
 */
public class BY121310RsParam extends BasePageParam {

    private Long Id;
    private String buyerId;
    private String classCode;
    private String className;
    private String machiningCode;
    private String machiningName;
    private Map<String,String> machiningMap;
    private Map<String,String> classesMap;
    private String machiningCodeU;
    // 判断颜色
    private String flag;
    /** 画面选中的销售产品 */
    private String[] buyerPdCla;
    /**画面选中的销售二级 */
    private String[] buyerPdMac;
    /** 是否选择 */
    private String isChecked;

    public Map<String, String> getMachiningMap() {
        return machiningMap;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setMachiningMap(Map<String, String> machiningMap) {
        this.machiningMap = machiningMap;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public Map<String, String> getClassesMap() {
        return classesMap;
    }

    public void setClassesMap(Map<String, String> classesMap) {
        this.classesMap = classesMap;
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



    public String getMachiningCodeU() {
        return machiningCodeU;
    }

    public void setMachiningCodeU(String machiningCodeU) {
        this.machiningCodeU = machiningCodeU;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
