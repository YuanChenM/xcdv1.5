package com.msk.product.bean;

import com.msk.core.entity.PdOrgStd;

/**
 * Created by Administrator on 2016/4/12. xhy
 */
public class PD141404OrgBean extends PdOrgStd {

    private static final long serialVersionUID = 1L;

    private String orgStdItemName;

    private String resultFlg;

    private String resultInfo; //检测描述


    /**
     * Getter method for property <tt>orgStdItemName</tt>.
     *
     * @return property value of orgStdItemName
     */
    public String getOrgStdItemName() {
        return orgStdItemName;
    }

    /**
     * Setter method for property <tt>orgStdItemName</tt>.
     *
     * @param orgStdItemName value to be assigned to property orgStdItemName
     */
    public void setOrgStdItemName(String orgStdItemName) {
        this.orgStdItemName = orgStdItemName;
    }

    /**
     * Getter method for property <tt>resultFlg</tt>.
     *
     * @return property value of resultFlg
     */
    public String getResultFlg() {
        return resultFlg;
    }

    /**
     * Setter method for property <tt>resultFlg</tt>.
     *
     * @param resultFlg value to be assigned to property resultFlg
     */
    public void setResultFlg(String resultFlg) {
        this.resultFlg = resultFlg;
    }

    /**
     * Getter method for property <tt>resultInfo</tt>.
     *
     * @return property value of resultInfo
     */
    public String getResultInfo() {
        return resultInfo;
    }

    /**
     * Setter method for property <tt>resultInfo</tt>.
     *
     * @param resultInfo value to be assigned to property resultInfo
     */
    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }


}
