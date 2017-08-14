package com.msk.product.bean;

import com.msk.core.entity.PdTncStd;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12. xhy
 */
public class PD141404TncBean extends PdTncStd {

    private static final long serialVersionUID = 1L;

    private String tncStdItemName;

    private String resultFlg;

    private String resultInfo; //检测描述

    /*是否为目录节点*/
    private String isCatalog;


    /*产品技术标准信息*/
    private List<PD141404TncBean> pdTncStdList;

    /*市场需求标准*/
    private List<PDtncMarkeyBean> pdTncMarkeyList;
    /*供应商习惯性标准*/
    private List<PDtncProviderBean> pdTncProviderList;

    private List<PDtncMarkeyBean> pdTncMarkeyNoList; //市场禁止日

    private List<PDtncProviderBean> pdTncProviderNoList; //供应商禁止日


    /**
     * Getter method for property <tt>isCatalog</tt>.
     *
     * @return property value of isCatalog
     */
    public String getIsCatalog() {
        return isCatalog;
    }

    /**
     * Setter method for property <tt>isCatalog</tt>.
     *
     * @param isCatalog value to be assigned to property isCatalog
     */
    public void setIsCatalog(String isCatalog) {
        this.isCatalog = isCatalog;
    }

    /**
     * Getter method for property <tt>pdTncStdList</tt>.
     *
     * @return property value of pdTncStdList
     */
    public List<PD141404TncBean> getPdTncStdList() {

        return pdTncStdList;
    }

    /**
     * Setter method for property <tt>pdTncStdList</tt>.
     *
     * @param pdTncStdList value to be assigned to property pdTncStdList
     */
    public void setPdTncStdList(List<PD141404TncBean> pdTncStdList) {
        this.pdTncStdList = pdTncStdList;
    }

    /**
     * Getter method for property <tt>pdTncMarkeyList</tt>.
     *
     * @return property value of pdTncMarkeyList
     */
    public List<PDtncMarkeyBean> getPdTncMarkeyList() {
        return pdTncMarkeyList;
    }

    /**
     * Setter method for property <tt>pdTncMarkeyList</tt>.
     *
     * @param pdTncMarkeyList value to be assigned to property pdTncMarkeyList
     */
    public void setPdTncMarkeyList(List<PDtncMarkeyBean> pdTncMarkeyList) {
        this.pdTncMarkeyList = pdTncMarkeyList;
    }

    /**
     * Getter method for property <tt>pdTncProviderList</tt>.
     *
     * @return property value of pdTncProviderList
     */
    public List<PDtncProviderBean> getPdTncProviderList() {
        return pdTncProviderList;
    }

    /**
     * Setter method for property <tt>pdTncProviderList</tt>.
     *
     * @param pdTncProviderList value to be assigned to property pdTncProviderList
     */
    public void setPdTncProviderList(List<PDtncProviderBean> pdTncProviderList) {
        this.pdTncProviderList = pdTncProviderList;
    }

    /**
     * Getter method for property <tt>pdTncMarkeyNoList</tt>.
     *
     * @return property value of pdTncMarkeyNoList
     */
    public List<PDtncMarkeyBean> getPdTncMarkeyNoList() {
        return pdTncMarkeyNoList;
    }

    /**
     * Setter method for property <tt>pdTncMarkeyNoList</tt>.
     *
     * @param pdTncMarkeyNoList value to be assigned to property pdTncMarkeyNoList
     */
    public void setPdTncMarkeyNoList(List<PDtncMarkeyBean> pdTncMarkeyNoList) {
        this.pdTncMarkeyNoList = pdTncMarkeyNoList;
    }

    /**
     * Getter method for property <tt>pdTncProviderNoList</tt>.
     *
     * @return property value of pdTncProviderNoList
     */
    public List<PDtncProviderBean> getPdTncProviderNoList() {
        return pdTncProviderNoList;
    }

    /**
     * Setter method for property <tt>pdTncProviderNoList</tt>.
     *
     * @param pdTncProviderNoList value to be assigned to property pdTncProviderNoList
     */
    public void setPdTncProviderNoList(List<PDtncProviderBean> pdTncProviderNoList) {
        this.pdTncProviderNoList = pdTncProviderNoList;
    }

    /**
     * Getter method for property <tt>tncStdItemName</tt>.
     *
     * @return property value of tncStdItemName
     */
    public String getTncStdItemName() {
        return tncStdItemName;
    }

    /**
     * Setter method for property <tt>tncStdItemName</tt>.
     *
     * @param tncStdItemName value to be assigned to property tncStdItemName
     */
    public void setTncStdItemName(String tncStdItemName) {
        this.tncStdItemName = tncStdItemName;
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
