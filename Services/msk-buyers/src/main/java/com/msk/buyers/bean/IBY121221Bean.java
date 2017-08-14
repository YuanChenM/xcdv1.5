package com.msk.buyers.bean;


import com.msk.common.base.BaseBean;

/**
 * BY121301Bean.
 *
 * @author yuan_chen
 */
public class IBY121221Bean extends BaseBean {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** 调研类型 */
    private String researchType;
    /** 调研类型名称 */
    private String researchTypeName;
    /** 调研状态 */
    private String researchStatus;

    /**
     * Getter method for property <tt>researchType</tt>.
     *
     * @return property value of researchType
     */
    public String getResearchType() {
        return researchType;
    }

    /**
     * Setter method for property <tt>researchType</tt>.
     *
     * @param researchType value to be assigned to property researchType
     */
    public void setResearchType(String researchType) {
        this.researchType = researchType;
    }

    /**
     * Getter method for property <tt>researchTypeName</tt>.
     *
     * @return property value of researchTypeName
     */
    public String getResearchTypeName() {
        return researchTypeName;
    }

    /**
     * Setter method for property <tt>researchTypeName</tt>.
     *
     * @param researchTypeName value to be assigned to property researchTypeName
     */
    public void setResearchTypeName(String researchTypeName) {
        this.researchTypeName = researchTypeName;
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
