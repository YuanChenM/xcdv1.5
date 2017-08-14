package com.msk.product.bean;

import com.msk.core.entity.PdTncStdDiscussProvider;

/**
 * 
 * PDtncMarkeyBean. 供应商习惯性标准
 * @author xhy
 */
public class PDtncProviderBean extends PdTncStdDiscussProvider {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String proRaiseDateShow;

    private String proFixDateShow;

    private String getDivName;


    private String getNoProName;


    /**
     * Getter method for property <tt>getNoProName</tt>.
     *
     * @return property value of getNoProName
     */
    public String getGetNoProName() {
        return getNoProName;
    }

    /**
     * Setter method for property <tt>getNoProName</tt>.
     *
     * @param getNoProName value to be assigned to property getNoProName
     */
    public void setGetNoProName(String getNoProName) {
        this.getNoProName = getNoProName;
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
     * Getter method for property <tt>proRaiseDateShow</tt>.
     *
     * @return property value of proRaiseDateShow
     */
    public String getProRaiseDateShow() {
        return proRaiseDateShow;
    }

    /**
     * Setter method for property <tt>proRaiseDateShow</tt>.
     *
     * @param proRaiseDateShow value to be assigned to property proRaiseDateShow
     */
    public void setProRaiseDateShow(String proRaiseDateShow) {
        this.proRaiseDateShow = proRaiseDateShow;
    }

    /**
     * Getter method for property <tt>proFixDateShow</tt>.
     *
     * @return property value of proFixDateShow
     */
    public String getProFixDateShow() {
        return proFixDateShow;
    }

    /**
     * Setter method for property <tt>proFixDateShow</tt>.
     *
     * @param proFixDateShow value to be assigned to property proFixDateShow
     */
    public void setProFixDateShow(String proFixDateShow) {
        this.proFixDateShow = proFixDateShow;
    }
}
