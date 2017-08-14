package com.msk.product.bean;

/**
 * SlCodeNatureBean.卖家属性码组成bean
 *
 * @author gyh
 */
public class SlCodeNatureBean {
    /**卖家类型*/
    private String slMainClass;
    /**卖家编码*/
    private String slCodeDis;
    /** 品牌ID */
    private Long brandId;
    /** 0:卖家独立品牌,1 :神农先生联合,2:神农客联合3:神农人家联合 */
    private String brandClass;
    //生产商编码
    private String slCodeManufacture;

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
}
