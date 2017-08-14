package com.msk.product.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * 技术标准Param
 * @author yuan_chen
 */
public class PD141149Param extends BaseParam {

    private static final long serialVersionUID = 1L;

    private Long standardId;
    /** 技术标准项数组 */
    private String [] pdTncTdItemIdArray;
    /** 技术标准项对应数值1 */
    private String [] content1Array;
    /** 技术标准项对应数值2 */
    private String [] content2Array;
    /** 技术标准项对应数值3 */
    private String [] content3Array;
    /** 技术标准项对应数值3 */
    private String [] remarkArray;
    /** 技术标准项是否为技术标准项数组 */
    private String [] checkArray;

    /**
     * Getter method for property <tt>standardId</tt>.
     *
     * @return property value of standardId
     */
    public Long getStandardId() {
        return standardId;
    }

    /**
     * Setter method for property <tt>standardId</tt>.
     *
     * @param standardId value to be assigned to property standardId
     */
    public void setStandardId(Long standardId) {
        this.standardId = standardId;
    }

    /**
     * <p>
     * 默认构造函数。
     * </p>
     */
    public PD141149Param() {

    }

    /**
     * Getter method for property <tt>remarkArray</tt>.
     *
     * @return property value of remarkArray
     */
    public String[] getRemarkArray() {
        return remarkArray;
    }

    /**
     * Setter method for property <tt>remarkArray</tt>.
     *
     * @param remarkArray value to be assigned to property remarkArray
     */
    public void setRemarkArray(String[] remarkArray) {
        this.remarkArray = remarkArray;
    }



    /**
     * Get the content1Array.
     *
     * @return content1Array
     *
     * @author yuan_chen
     */
    public String[] getContent1Array() {
        return this.content1Array;
    }

    /**
     * Set the content1Array.
     *
     * @param content1Array content1Array
     *
     * @author yuan_chen
     */
    public void setContent1Array(String[] content1Array) {
        this.content1Array = content1Array;
    }

    /**
     * Get the content2Array.
     *
     * @return content2Array
     *
     * @author yuan_chen
     */
    public String[] getContent2Array() {
        return this.content2Array;
    }

    /**
     * Set the content2Array.
     *
     * @param content2Array content2Array
     *
     * @author yuan_chen
     */
    public void setContent2Array(String[] content2Array) {
        this.content2Array = content2Array;
    }

    /**
     * Get the content3Array.
     *
     * @return content3Array
     *
     * @author yuan_chen
     */
    public String[] getContent3Array() {
        return this.content3Array;
    }

    /**
     * Set the content3Array.
     *
     * @param content3Array content3Array
     *
     * @author yuan_chen
     */
    public void setContent3Array(String[] content3Array) {
        this.content3Array = content3Array;
    }


    /**
     * Get the checkArray.
     *
     * @return checkArray
     *
     * @author gyh
     */
    public String[] getCheckArray() {
        return this.checkArray;
    }

    /**
     * Set the checkArray.
     *
     * @param checkArray checkArray
     *
     * @author gyh
     */
    public void setCheckArray(String[] checkArray) {
        this.checkArray = checkArray;
    }


    /**
     * Get the pdTncTdItemIdArray.
     *
     * @return pdTncTdItemIdArray
     *
     * @author gyh
     */
    public String[] getPdTncTdItemIdArray() {
        return this.pdTncTdItemIdArray;
    }

    /**
     * Set the pdTncTdItemIdArray.
     *
     * @param pdTncTdItemIdArray pdTncTdItemIdArray
     *
     * @author gyh
     */
    public void setPdTncTdItemIdArray(String[] pdTncTdItemIdArray) {
        this.pdTncTdItemIdArray = pdTncTdItemIdArray;
    }
}
