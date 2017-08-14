/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_bs_house_grade对应的SlBsHouseGrade。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlBsHouseGrade extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 区划(6)+顺序码(4) */
    private java.lang.String slCode;
    /** 用于登录的卖家账号 */
    private java.lang.String houseCode;
    /** GREADE */
    private java.lang.Integer greade;
    /**
     * <p>默认构造函数。</p>
     */
    public SlBsHouseGrade() {

    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public java.lang.String getSlCode() {
        return slCode;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @param slCode 区划(6)+顺序码(4)。
     */
    public void setSlCode(java.lang.String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @return the 用于登录的卖家账号
     */
    public java.lang.String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param houseCode 用于登录的卖家账号。
     */
    public void setHouseCode(java.lang.String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>GREADE。</p>
     *
     * @return the GREADE
     */
    public java.lang.Integer getGreade() {
        return greade;
    }

    /**
     * <p>GREADE。</p>
     *
     * @param greade GREADE。
     */
    public void setGreade(java.lang.Integer greade) {
        this.greade = greade;
    }

}
