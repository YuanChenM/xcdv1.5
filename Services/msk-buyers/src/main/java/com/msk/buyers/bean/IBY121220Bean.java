package com.msk.buyers.bean;


import com.msk.common.base.BaseBean;

/**
 * 产品品种调研状态Bean.
 *
 * @author yuan_chen
 */
public class IBY121220Bean extends BaseBean {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** 买家ID */
    private String buyerId;
    /** 产品类别 */
    private String classesCode;
    /** 产品加工类型 */
    private String machiningCode;
    /** 产品品种 */
    private String breedCode;
    /** 产品品种名称 */
    private String breedName;
    /** 是否为新品种 0:不是,1:是 */
    private String isStandard;
    /** 产品品种 调研状态 */
    private String researchStatus;

    /**
     * Getter method for property <tt>buyerId</tt>.
     *
     * @return property value of buyerId
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * Setter method for property <tt>buyerId</tt>.
     *
     * @param buyerId value to be assigned to property buyerId
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * Getter method for property <tt>classesCode</tt>.
     *
     * @return property value of classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * Setter method for property <tt>classesCode</tt>.
     *
     * @param classesCode value to be assigned to property classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
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
     * Getter method for property <tt>breedCode</tt>.
     *
     * @return property value of breedCode
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * Setter method for property <tt>breedCode</tt>.
     *
     * @param breedCode value to be assigned to property breedCode
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * Getter method for property <tt>breedName</tt>.
     *
     * @return property value of breedName
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * Setter method for property <tt>breedName</tt>.
     *
     * @param breedName value to be assigned to property breedName
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * Getter method for property <tt>isStandard</tt>.
     *
     * @return property value of isStandard
     */
    public String getIsStandard() {
        return isStandard;
    }

    /**
     * Setter method for property <tt>isStandard</tt>.
     *
     * @param isStandard value to be assigned to property isStandard
     */
    public void setIsStandard(String isStandard) {
        this.isStandard = isStandard;
    }

    /**
     * Getter method for property <tt>researchStatus</tt>.
     *
     * @return property value of researchStatus
     */
    public String getResearchStatus() {
        return researchStatus;
    }

    /**
     * Setter method for property <tt>researchStatus</tt>.
     *
     * @param researchStatus value to be assigned to property researchStatus
     */
    public void setResearchStatus(String researchStatus) {
        this.researchStatus = researchStatus;
    }

}
