package com.msk.product.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * PD141108 Controller Param
 * @author yuan_chen
 *
 */
public class RealityMeasureParam extends BaseParam {
    
    private static final long serialVersionUID = 1L;
    private String pdCode;//产品编号
    private String sellerCode;//卖家编号
    private String pdBatchCode;//批次编号
    private String classesCode;//分类编号
    private String breedCode;//品种编号
    private String pdStdId;//产品标准ID
    private String pdRltMsrId;//实际测量ID

    /**
     * Get the pdCode.
     *
     * @return pdCode
     *
     * @author yuan_chen
     */
    public String getPdCode() {
        return this.pdCode;
    }
    /**
     * Set the pdCode.
     *
     * @param pdCode pdCode
     *
     * @author yuan_chen
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
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
     * Get the pdBatchCode.
     *
     * @return pdBatchCode
     *
     * @author yuan_chen
     */
    public String getPdBatchCode() {
        return this.pdBatchCode;
    }
    /**
     * Set the pdBatchCode.
     *
     * @param pdBatchCode pdBatchCode
     *
     * @author yuan_chen
     */
    public void setPdBatchCode(String pdBatchCode) {
        this.pdBatchCode = pdBatchCode;
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
     * Get the pdStdId.
     *
     * @return pdStdId
     *
     * @author yuan_chen
     */
    public String getPdStdId() {
        return this.pdStdId;
    }
    /**
     * Set the pdStdId.
     *
     * @param pdStdId pdStdId
     *
     * @author yuan_chen
     */
    public void setPdStdId(String pdStdId) {
        this.pdStdId = pdStdId;
    }
    /**
     * Get the pdRltMsrId.
     *
     * @return pdRltMsrId
     *
     * @author yuan_chen
     */
    public String getPdRltMsrId() {
        return this.pdRltMsrId;
    }
    /**
     * Set the pdRltMsrId.
     *
     * @param pdRltMsrId pdRltMsrId
     *
     * @author yuan_chen
     */
    public void setPdRltMsrId(String pdRltMsrId) {
        this.pdRltMsrId = pdRltMsrId;
    } 
}
