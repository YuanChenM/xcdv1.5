package com.msk.product.bean;

import com.msk.common.base.BaseBean;
import com.msk.core.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ProductBean.产品编码拆分相关信息
 *
 * @author gyh
 */
@ApiModel(value = "ProductBean",description = "产品编码拆分相关信息")
public class ProductBean extends BaseBean implements Serializable {


    @ApiModelProperty(value = "产品编码")
    private String pdCode;

    @ApiModelProperty(value = "产品类别")
    private PdClasses pdClasses;

    @ApiModelProperty(value = "产品二级类别")
    private PdMachining pdMachining;

    @ApiModelProperty(value = "产品品种")
    private PdBreed pdBreed;

    @ApiModelProperty(value = "产品特征")
    private PdFeature pdFeature;

    @ApiModelProperty(value = "产品净重")
    private PdWeight pdWeight;
    /** 产品国籍 */
//    private PdCountry pdCountry;

    @ApiModelProperty(value = "产品等级")
    private PdGrade pdGrade;

    @ApiModelProperty(value = "pdNormsStd")
    private PdNormsStd pdNormsStd;


    /**
     * Getter method for property <tt>pdNormsStd</tt>.
     *
     * @return property value of pdNormsStd
     */
    public PdNormsStd getPdNormsStd() {
        return pdNormsStd;
    }

    /**
     * Setter method for property <tt>pdNormsStd</tt>.
     *
     * @param pdNormsStd value to be assigned to property pdNormsStd
     */
    public void setPdNormsStd(PdNormsStd pdNormsStd) {
        this.pdNormsStd = pdNormsStd;
    }

    /**
     * Getter method for property <tt>pdClasses</tt>.
     *
     * @return property value of pdClasses
     */
    public PdClasses getPdClasses() {
        return pdClasses;
    }

    /**
     * Setter method for property <tt>pdClasses</tt>.
     *
     * @param pdClasses value to be assigned to property pdClasses
     */
    public void setPdClasses(PdClasses pdClasses) {
        this.pdClasses = pdClasses;
    }

    /**
     * Getter method for property <tt>pdMachining</tt>.
     *
     * @return property value of pdMachining
     */
    public PdMachining getPdMachining() {
        return pdMachining;
    }

    /**
     * Setter method for property <tt>pdMachining</tt>.
     *
     * @param pdMachining value to be assigned to property pdMachining
     */
    public void setPdMachining(PdMachining pdMachining) {
        this.pdMachining = pdMachining;
    }

    /**
     * Getter method for property <tt>pdBreed</tt>.
     *
     * @return property value of pdBreed
     */
    public PdBreed getPdBreed() {
        return pdBreed;
    }

    /**
     * Setter method for property <tt>pdBreed</tt>.
     *
     * @param pdBreed value to be assigned to property pdBreed
     */
    public void setPdBreed(PdBreed pdBreed) {
        this.pdBreed = pdBreed;
    }

    /**
     * Getter method for property <tt>pdFeature</tt>.
     *
     * @return property value of pdFeature
     */
    public PdFeature getPdFeature() {
        return pdFeature;
    }

    /**
     * Setter method for property <tt>pdFeature</tt>.
     *
     * @param pdFeature value to be assigned to property pdFeature
     */
    public void setPdFeature(PdFeature pdFeature) {
        this.pdFeature = pdFeature;
    }

    /**
     * Getter method for property <tt>pdWeight</tt>.
     *
     * @return property value of pdWeight
     */
    public PdWeight getPdWeight() {
        return pdWeight;
    }

    /**
     * Setter method for property <tt>pdWeight</tt>.
     *
     * @param pdWeight value to be assigned to property pdWeight
     */
    public void setPdWeight(PdWeight pdWeight) {
        this.pdWeight = pdWeight;
    }


    /**
     * Getter method for property <tt>pdGrade</tt>.
     *
     * @return property value of pdGrade
     */
    public PdGrade getPdGrade() {
        return pdGrade;
    }

    /**
     * Setter method for property <tt>pdGrade</tt>.
     *
     * @param pdGrade value to be assigned to property pdGrade
     */
    public void setPdGrade(PdGrade pdGrade) {
        this.pdGrade = pdGrade;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }
}
