package com.msk.br.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/9/28.
 */
public class BR121414Param extends BaseParam {

    private String machiningCodeU;
    private String machiningNameU;
    private String classesCode;
    private String classesName;
    /** MACHINING_CODE */
    private String machiningCode;
    /** MACHINING_NAME */
    private String machiningName;
    private List<BR121414Param> productList;

    private List<BR121414Param> machiningList;
    /** 画面选中的销售产品 */
    private String[] buyerPdCla;
    /**画面选中的销售二级 */
    private String[] buyerPdMac;
    private String  produceName;
    /** 是否选择 */
    private String isChecked;
    private String hideMachinCheck;
    private String hideClassesCheck;
    public List<BR121414Param> getMachiningList() {
        return machiningList;
    }

    public String getHideClassesCheck() {
        return hideClassesCheck;
    }

    public void setHideClassesCheck(String hideClassesCheck) {
        this.hideClassesCheck = hideClassesCheck;
    }

    public String getProduceName() {
        return produceName;
    }

    public String getHideMachinCheck() {
        return hideMachinCheck;
    }

    public void setHideMachinCheck(String hideMachinCheck) {
        this.hideMachinCheck = hideMachinCheck;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public void setMachiningList(List<BR121414Param> machiningList) {
        this.machiningList = machiningList;
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

    public String[] getBuyerPdCla() {
        return buyerPdCla;
    }

    public String[] getBuyerPdMac() {
        return buyerPdMac;
    }

    public List<BR121414Param> getProductList() {
        return productList;
    }

    public void setProductList(List<BR121414Param> productList) {
        this.productList = productList;
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

    public void setBuyerPdCla(String[] buyerPdCla) {
        this.buyerPdCla = buyerPdCla;
    }

    public String getMachiningCodeU() {
        return machiningCodeU;
    }

    public void setMachiningCodeU(String machiningCodeU) {
        this.machiningCodeU = machiningCodeU;
    }

    public String getMachiningNameU() {
        return machiningNameU;
    }

    public void setMachiningNameU(String machiningNameU) {
        this.machiningNameU = machiningNameU;
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
}
