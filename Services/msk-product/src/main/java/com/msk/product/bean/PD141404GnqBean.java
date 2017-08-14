package com.msk.product.bean;

import com.msk.core.entity.PdGnqStd;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12. xhy
 */
public class PD141404GnqBean extends PdGnqStd {

    private static final long serialVersionUID = 1L;

    private String gnqStdItemName;


    private List<PD141404GnqChildBean> pdGnqChildList;

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
     * Getter method for property <tt>pdGnqChildList</tt>.
     *
     * @return property value of pdGnqChildList
     */
    public List<PD141404GnqChildBean> getPdGnqChildList() {
        return pdGnqChildList;
    }

    /**
     * Setter method for property <tt>pdGnqChildList</tt>.
     *
     * @param pdGnqChildList value to be assigned to property pdGnqChildList
     */
    public void setPdGnqChildList(List<PD141404GnqChildBean> pdGnqChildList) {
        this.pdGnqChildList = pdGnqChildList;
    }
}
