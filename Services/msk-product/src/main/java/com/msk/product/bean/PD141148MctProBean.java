package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.PdMctStdDiscussProvider;

/**
 * 返回参数产品类别列表
 * IPD141148RsParam.
 *
 * @author xhy
 */
@JsonIgnoreProperties(value={"delFlg","crtId","crtTime","updId","updTime","ver","actId","actTime"})
public class PD141148MctProBean extends PdMctStdDiscussProvider {

    private static final long serialVersionUID = 1L;
    private String fixDateShow;
    private String raiseDateShow;
    private String getDivName;


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
}