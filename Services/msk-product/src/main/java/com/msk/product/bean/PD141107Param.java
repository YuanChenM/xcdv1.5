package com.msk.product.bean;


import com.hoperun.core.bean.BasePageParam;

/**
 * 技术标准Param
 * @author yuan_chen
 */
public class PD141107Param extends BasePageParam {
    private static final long serialVersionUID = 1L;

    /** 产品标准ID */
    private Integer pdStdId;
    /** 类别编码 */
    private String classesCode;
    /** 品种编码 */
    private String breedCode;
    /** 类别名称 */
    private String classesName;
    /** 品种名称 */
    private String breedName;
    /** 卖家编码 */
    private String sellerCode;

    /** 技术标准项数组 */
    private String [] pdTncTdItemIdArray;
    /** 技术标准项对应数值1 */
    private String [] content1Array;
    /** 技术标准项对应数值2 */
    private String [] content2Array;
    /** 技术标准项对应数值3 */
    private String [] content3Array;
    /** 技术标准项是否为技术标准项数组 */
    private String [] checkArray;
    
    /**
     * <p>
     * 默认构造函数。
     * </p>
     */
    public PD141107Param() {

    }

    /**
     * Get the classesCode.
     *
     * @return classesCode
     *
     * @author yuan_chen
     */
    public String getClassesCode() {
        return this.classesCode;
    }

    /**
     * Set the classesCode.
     *
     * @param classesCode classesCode
     *
     * @author yuan_chen
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Get the breedCode.
     *
     * @return breedCode
     *
     * @author yuan_chen
     */
    public String getBreedCode() {
        return this.breedCode;
    }

    /**
     * Set the breedCode.
     *
     * @param breedCode breedCode
     *
     * @author yuan_chen
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * Get the sellerCode.
     *
     * @return sellerCode
     *
     * @author yuan_chen
     */
    public String getSellerCode() {
        return this.sellerCode;
    }

    /**
     * Set the sellerCode.
     *
     * @param sellerCode sellerCode
     *
     * @author yuan_chen
     */
    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
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
     * Get the classesName.
     *
     * @return classesName
     *
     * @author gyh
     */
    public String getClassesName() {
        return this.classesName;
    }

    /**
     * Set the classesName.
     *
     * @param classesName classesName
     *
     * @author gyh
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * Get the breedName.
     *
     * @return breedName
     *
     * @author gyh
     */
    public String getBreedName() {
        return this.breedName;
    }

    /**
     * Set the breedName.
     *
     * @param breedName breedName
     *
     * @author gyh
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
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
     * Get the pdStdId.
     *
     * @return pdStdId
     *
     * @author gyh
     */
    public Integer getPdStdId() {
        return this.pdStdId;
    }

    /**
     * Set the pdStdId.
     *
     * @param pdStdId pdStdId
     *
     * @author gyh
     */
    public void setPdStdId(Integer pdStdId) {
        this.pdStdId = pdStdId;
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
