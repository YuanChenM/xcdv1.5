package com.msk.br.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/9/28.
 */
public class IBR121414RsParam extends BaseParam{

    /** ID */
    private Long id;
    /** CLASSES_CODE */
    private String classesCode;
    /** CLASSES_NAME */
    private String classesName;
    /** MACHINING_CODE */
    private String machiningCode;
    /** MACHINING_NAME */
    private String machiningName;
    /** MACHINING_CODE_U */
    private String machiningCodeU;
    /** MACHINING_NAME_U */
    private String machiningNameU;
    /** BY_POOL_MACHINING_CODE */
    private String byPoolMachiningCode;
    private List<IBR121414RsParam> productList;
    /** 画面选中的销售产品 */
    private String[] buyerPdCla;
    /**画面选中的销售二级 */
    private String[] buyerPdMac;
    /** 是否选择 */
    private String isChecked;

    private String hideMachinCheck;
    private String hideClassesCheck;

    public String getHideMachinCheck() {
        return hideMachinCheck;
    }

    public void setHideMachinCheck(String hideMachinCheck) {
        this.hideMachinCheck = hideMachinCheck;
    }

    public String getHideClassesCheck() {
        return hideClassesCheck;
    }

    public void setHideClassesCheck(String hideClassesCheck) {
        this.hideClassesCheck = hideClassesCheck;
    }

    public String[] getBuyerPdCla() {
        return buyerPdCla;
    }

    public List<IBR121414RsParam> getProductList() {
        return productList;
    }

    public void setProductList(List<IBR121414RsParam> productList) {
        this.productList = productList;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getByPoolMachiningCode() {
        return byPoolMachiningCode;
    }

    public void setByPoolMachiningCode(String byPoolMachiningCode) {
        this.byPoolMachiningCode = byPoolMachiningCode;
    }
}
