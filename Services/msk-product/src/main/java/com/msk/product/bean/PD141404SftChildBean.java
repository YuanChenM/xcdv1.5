package com.msk.product.bean;

import com.msk.core.entity.PdSftStd;

/**
 * Created by Administrator on 2016/4/12. xhy
 */
public class PD141404SftChildBean extends PdSftStd {

    private static final long serialVersionUID = 1L;

    private String sftStdItemName;

    private String resultFlg;

    private String resultInfo; //检测描述


    /**
     * Getter method for property <tt>sftStdItemName</tt>.
     *
     * @return property value of sftStdItemName
     */
    public String getSftStdItemName() {
        return sftStdItemName;
    }

    /**
     * Setter method for property <tt>sftStdItemName</tt>.
     *
     * @param sftStdItemName value to be assigned to property sftStdItemName
     */
    public void setSftStdItemName(String sftStdItemName) {
        this.sftStdItemName = sftStdItemName;
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
