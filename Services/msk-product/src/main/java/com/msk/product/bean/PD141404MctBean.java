package com.msk.product.bean;

import com.msk.core.entity.PdMctStd;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12. xhy
 */
public class PD141404MctBean extends PdMctStd {

    private static final long serialVersionUID = 1L;

    private String mctStdItemName;

    private String resultFlg;

    private String resultInfo; //检测描述

    private List<PD141148MctProBean> mctProList;

    /**
     * Getter method for property <tt>mctProList</tt>.
     *
     * @return property value of mctProList
     */
    public List<PD141148MctProBean> getMctProList() {
        return mctProList;
    }

    /**
     * Setter method for property <tt>mctProList</tt>.
     *
     * @param mctProList value to be assigned to property mctProList
     */
    public void setMctProList(List<PD141148MctProBean> mctProList) {
        this.mctProList = mctProList;
    }

    /**
     * Getter method for property <tt>mctStdItemName</tt>.
     *
     * @return property value of mctStdItemName
     */
    public String getMctStdItemName() {
        return mctStdItemName;
    }

    /**
     * Setter method for property <tt>mctStdItemName</tt>.
     *
     * @param mctStdItemName value to be assigned to property mctStdItemName
     */
    public void setMctStdItemName(String mctStdItemName) {
        this.mctStdItemName = mctStdItemName;
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
