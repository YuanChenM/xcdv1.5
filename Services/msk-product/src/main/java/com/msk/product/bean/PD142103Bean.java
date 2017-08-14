package com.msk.product.bean;

/**
 * Created by Administrator on 2016/4/20.
 */
public class PD142103Bean extends PD14210201Bean {
    /**卖家类型*/
    private String slMainClass;
    /**卖家类型中文名*/
    private String slMainClassName;
    /**卖家地区*/
    private String slLicAddr;
    /**卖家名称*/
    private String slName;
    /**生产商地区*/
    private String sllfAddr;
    /**生产商名称*/
    private String sllfName;
    /**品牌类型*/
    private String brandClass;
    /**品牌类型名称*/
    private String brandClassName;
    /**品牌名称*/
    private String brandName;
    /**品牌顺序号/id*/
    private Long brandId;
    //卖家编码
    private String slCodeDis;
    //生产商编码
    private String slCodeManufacture;
    /** 产品类别 */
    private String pdClassesCode;
    /** 产品二级分类编码 */
    private String machiningCode;
    /** 产品品种 */
    private String pdBreedCode;
    /** 产品特征 */
    private String pdFeatureCode;
    /** 净重编码 */
    private String weightCode;
    /** 等级编码 */
    private String gradeCode;

    /**
     * Getter method for property <tt>slCodeManufacture</tt>.
     *
     * @return property value of slCodeManufacture
     */
    public String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    /**
     * Setter method for property <tt>slCodeManufacture</tt>.
     *
     * @param slCodeManufacture value to be assigned to property slCodeManufacture
     */
    public void setSlCodeManufacture(String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
    }

    /**
     * Getter method for property <tt>gradeCode</tt>.
     *
     * @return property value of gradeCode
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * Setter method for property <tt>gradeCode</tt>.
     *
     * @param gradeCode value to be assigned to property gradeCode
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * Getter method for property <tt>pdClassesCode</tt>.
     *
     * @return property value of pdClassesCode
     */
    public String getPdClassesCode() {
        return pdClassesCode;
    }

    /**
     * Setter method for property <tt>pdClassesCode</tt>.
     *
     * @param pdClassesCode value to be assigned to property pdClassesCode
     */
    public void setPdClassesCode(String pdClassesCode) {
        this.pdClassesCode = pdClassesCode;
    }

    /**
     * Getter method for property <tt>machiningCode</tt>.
     *
     * @return property value of machiningCode
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * Setter method for property <tt>machiningCode</tt>.
     *
     * @param machiningCode value to be assigned to property machiningCode
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * Getter method for property <tt>pdBreedCode</tt>.
     *
     * @return property value of pdBreedCode
     */
    public String getPdBreedCode() {
        return pdBreedCode;
    }

    /**
     * Setter method for property <tt>pdBreedCode</tt>.
     *
     * @param pdBreedCode value to be assigned to property pdBreedCode
     */
    public void setPdBreedCode(String pdBreedCode) {
        this.pdBreedCode = pdBreedCode;
    }

    /**
     * Getter method for property <tt>pdFeatureCode</tt>.
     *
     * @return property value of pdFeatureCode
     */
    public String getPdFeatureCode() {
        return pdFeatureCode;
    }

    /**
     * Setter method for property <tt>pdFeatureCode</tt>.
     *
     * @param pdFeatureCode value to be assigned to property pdFeatureCode
     */
    public void setPdFeatureCode(String pdFeatureCode) {
        this.pdFeatureCode = pdFeatureCode;
    }

    /**
     * Getter method for property <tt>weightCode</tt>.
     *
     * @return property value of weightCode
     */
    @Override
    public String getWeightCode() {
        return weightCode;
    }

    /**
     * Setter method for property <tt>weightCode</tt>.
     *
     * @param weightCode value to be assigned to property weightCode
     */
    @Override
    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * Getter method for property <tt>slCodeDis</tt>.
     *
     * @return property value of slCodeDis
     */
    public String getSlCodeDis() {
        return slCodeDis;
    }

    /**
     * Setter method for property <tt>slCodeDis</tt>.
     *
     * @param slCodeDis value to be assigned to property slCodeDis
     */
    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    /**
     * Getter method for property <tt>brandId</tt>.
     *
     * @return property value of brandId
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * Setter method for property <tt>brandId</tt>.
     *
     * @param brandId value to be assigned to property brandId
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * Getter method for property <tt>brandClassName</tt>.
     *
     * @return property value of brandClassName
     */
    public String getBrandClassName() {
        return brandClassName;
    }

    /**
     * Setter method for property <tt>brandClassName</tt>.
     *
     * @param brandClassName value to be assigned to property brandClassName
     */
    public void setBrandClassName(String brandClassName) {
        this.brandClassName = brandClassName;
    }

    /**
     * Getter method for property <tt>slMainClassName</tt>.
     *
     * @return property value of slMainClassName
     */
    public String getSlMainClassName() {
        return slMainClassName;
    }

    /**
     * Setter method for property <tt>slMainClassName</tt>.
     *
     * @param slMainClassName value to be assigned to property slMainClassName
     */
    public void setSlMainClassName(String slMainClassName) {
        this.slMainClassName = slMainClassName;
    }

    /**
     * Getter method for property <tt>slMainClass</tt>.
     *
     * @return property value of slMainClass
     */
    public String getSlMainClass() {
        return slMainClass;
    }

    /**
     * Setter method for property <tt>slMainClass</tt>.
     *
     * @param slMainClass value to be assigned to property slMainClass
     */
    public void setSlMainClass(String slMainClass) {
        this.slMainClass = slMainClass;
    }

    /**
     * Getter method for property <tt>slLicAddr</tt>.
     *
     * @return property value of slLicAddr
     */
    public String getSlLicAddr() {
        return slLicAddr;
    }

    /**
     * Setter method for property <tt>slLicAddr</tt>.
     *
     * @param slLicAddr value to be assigned to property slLicAddr
     */
    public void setSlLicAddr(String slLicAddr) {
        this.slLicAddr = slLicAddr;
    }

    /**
     * Getter method for property <tt>slName</tt>.
     *
     * @return property value of slName
     */
    public String getSlName() {
        return slName;
    }

    /**
     * Setter method for property <tt>slName</tt>.
     *
     * @param slName value to be assigned to property slName
     */
    public void setSlName(String slName) {
        this.slName = slName;
    }

    /**
     * Getter method for property <tt>sllfAddr</tt>.
     *
     * @return property value of sllfAddr
     */
    public String getSllfAddr() {
        return sllfAddr;
    }

    /**
     * Setter method for property <tt>sllfAddr</tt>.
     *
     * @param sllfAddr value to be assigned to property sllfAddr
     */
    public void setSllfAddr(String sllfAddr) {
        this.sllfAddr = sllfAddr;
    }

    /**
     * Getter method for property <tt>sllfName</tt>.
     *
     * @return property value of sllfName
     */
    public String getSllfName() {
        return sllfName;
    }

    /**
     * Setter method for property <tt>sllfName</tt>.
     *
     * @param sllfName value to be assigned to property sllfName
     */
    public void setSllfName(String sllfName) {
        this.sllfName = sllfName;
    }

    /**
     * Getter method for property <tt>brandClass</tt>.
     *
     * @return property value of brandClass
     */
    public String getBrandClass() {
        return brandClass;
    }

    /**
     * Setter method for property <tt>brandClass</tt>.
     *
     * @param brandClass value to be assigned to property brandClass
     */
    public void setBrandClass(String brandClass) {
        this.brandClass = brandClass;
    }

    /**
     * Getter method for property <tt>brandName</tt>.
     *
     * @return property value of brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Setter method for property <tt>brandName</tt>.
     *
     * @param brandName value to be assigned to property brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
