package com.msk.product.bean;

import com.msk.core.entity.PdTncStdDiscussMarket;

/**
 * 
 * PDtncMarkeyBean. 市场需求标准
 * @author xhy
 */
public class PDtncMarkeyBean extends PdTncStdDiscussMarket {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String fixDateShow; //结束日
    private String raiseDateShow;//提出日

    private String getDivName;
    private String getDivJin;

    /**
     * Getter method for property <tt>getDivJin</tt>.
     *
     * @return property value of getDivJin
     */
    public String getGetDivJin() {
        return getDivJin;
    }

    /**
     * Setter method for property <tt>getDivJin</tt>.
     *
     * @param getDivJin value to be assigned to property getDivJin
     */
    public void setGetDivJin(String getDivJin) {
        this.getDivJin = getDivJin;
    }

    /**
     * Getter method for property <tt>getDivName</tt>.
     *
     * @return property value of getDivName
     */
    public String getGetDivName() {
        return getDivName;
    }

    /**
     * Setter method for property <tt>getDivName</tt>.
     *
     * @param getDivName value to be assigned to property getDivName
     */
    public void setGetDivName(String getDivName) {
        this.getDivName = getDivName;
    }

    /**
     * Getter method for property <tt>raiseDateShow</tt>.
     *
     * @return property value of raiseDateShow
     */
    public String getRaiseDateShow() {
        return raiseDateShow;
    }

    /**
     * Setter method for property <tt>raiseDateShow</tt>.
     *
     * @param raiseDateShow value to be assigned to property raiseDateShow
     */
    public void setRaiseDateShow(String raiseDateShow) {
        this.raiseDateShow = raiseDateShow;
    }

    /**
     * Getter method for property <tt>fixDateShow</tt>.
     *
     * @return property value of fixDateShow
     */
    public String getFixDateShow() {
        return fixDateShow;
    }

    /**
     * Setter method for property <tt>fixDateShow</tt>.
     *
     * @param fixDateShow value to be assigned to property fixDateShow
     */
    public void setFixDateShow(String fixDateShow) {
        this.fixDateShow = fixDateShow;
    }
}
