package com.msk.seller.bean;

/**
 * Created by Administrator on 2016/3/25.
 */
public class SlEpAgentAuthBean extends com.msk.core.entity.SlEpAgentAuth {

    //生产商、OEM标示
    private Integer markFlg;
    //企业名称
    private String epName;

    /**
     * Getter method for property <tt>markFlg</tt>.
     *
     * @return property value of markFlg
     */
    public Integer getMarkFlg() {
        return markFlg;
    }

    /**
     * Setter method for property <tt>markFlg</tt>.
     *
     * @param markFlg value to be assigned to property markFlg
     */
    public void setMarkFlg(Integer markFlg) {
        this.markFlg = markFlg;
    }

    /**
     * Getter method for property <tt>epName</tt>.
     *
     * @return property value of epName
     */
    public String getEpName() {
        return epName;
    }

    /**
     * Setter method for property <tt>epName</tt>.
     *
     * @param epName value to be assigned to property epName
     */
    public void setEpName(String epName) {
        this.epName = epName;
    }
}
