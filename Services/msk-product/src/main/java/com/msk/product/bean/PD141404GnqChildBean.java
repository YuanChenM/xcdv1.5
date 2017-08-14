package com.msk.product.bean;

import com.msk.core.entity.PdGnqStd;

/**
 * Created by Administrator on 2016/4/12. xhy
 */
public class PD141404GnqChildBean extends PdGnqStd {

    private static final long serialVersionUID = 1L;

    private String gnqStdItemName;

    private String resultFlg;

    private String resultInfo; //检测描述


    /**
     * Getter method for property <tt>gnqStdItemName</tt>.
     *
     * @return property value of gnqStdItemName
     */
    public String getGnqStdItemName() {
        return gnqStdItemName;
    }

    /**
     * Setter method for property <tt>gnqStdItemName</tt>.
     *
     * @param gnqStdItemName value to be assigned to property gnqStdItemName
     */
    public void setGnqStdItemName(String gnqStdItemName) {
        this.gnqStdItemName = gnqStdItemName;
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
