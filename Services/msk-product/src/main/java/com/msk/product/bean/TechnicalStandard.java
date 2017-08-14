package com.msk.product.bean;

import com.msk.core.entity.PdTncStd;

/**
 * 技术标准分类
 * 
 * @author yuan_chen
 */
public class TechnicalStandard extends PdTncStd {
    /** */
    private static final long serialVersionUID = 1L;

    /** A1技术标准内容 */
    private String content1;
    /** A1准入日 */
    private java.util.Date eftDate1;
    /** A2技术标准内容 */
    private String content2;
    /** A2准入日 */
    private java.util.Date eftDate2;
    /** A3技术标准内容 */
    private String content3;
    /** A3准入日 */
    private java.util.Date eftDate3;

    /**
     * <p>
     * 默认构造函数。
     * </p>
     */
    public TechnicalStandard() {}

    /**
     * <p>
     * 技术标准内容。
     * </p>
     *
     * @return the 技术标准内容
     */
    public String getContent1() {
        return content1;
    }

    /**
     * <p>
     * 技术标准内容。
     * </p>
     *
     * @param content 技术标准内容。
     */
    public void setContent1(String content) {
        this.content1 = content;
    }

    /**
     * <p>
     * 准入日。
     * </p>
     *
     * @return the 准入日
     */
    public java.util.Date getEftDate1() {
        return eftDate1;
    }

    /**
     * <p>
     * 准入日。
     * </p>
     *
     * @param eftDate 准入日。
     */
    public void setEftDate1(java.util.Date eftDate) {
        this.eftDate1 = eftDate;
    }

    /**
     * <p>
     * 技术标准内容。
     * </p>
     *
     * @return the 技术标准内容
     */
    public String getContent2() {
        return content2;
    }

    /**
     * <p>
     * 技术标准内容。
     * </p>
     *
     * @param content 技术标准内容。
     */
    public void setContent2(String content) {
        this.content2 = content;
    }

    /**
     * <p>
     * 准入日。
     * </p>
     *
     * @return the 准入日
     */
    public java.util.Date getEftDate2() {
        return eftDate2;
    }

    /**
     * <p>
     * 准入日。
     * </p>
     *
     * @param eftDate 准入日。
     */
    public void setEftDate2(java.util.Date eftDate) {
        this.eftDate2 = eftDate;
    }

    /**
     * <p>
     * 技术标准内容。
     * </p>
     *
     * @return the 技术标准内容
     */
    public String getContent3() {
        return content3;
    }

    /**
     * <p>
     * 技术标准内容。
     * </p>
     *
     * @param content 技术标准内容。
     */
    public void setContent3(String content) {
        this.content3 = content;
    }

    /**
     * <p>
     * 准入日。
     * </p>
     *
     * @return the 准入日
     */
    public java.util.Date getEftDate3() {
        return eftDate3;
    }

    /**
     * <p>
     * 准入日。
     * </p>
     *
     * @param eftDate 准入日。
     */
    public void setEftDate3(java.util.Date eftDate) {
        this.eftDate3 = eftDate;
    }
}
