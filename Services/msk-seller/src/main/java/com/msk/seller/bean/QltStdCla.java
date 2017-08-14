package com.msk.seller.bean;

import com.msk.core.entity.PdQltStdCla;

import java.util.List;

/**
 * QltStdCla Controller
 * @author pxg
 *
 */
public class QltStdCla extends PdQltStdCla{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private List<QltStdSubCla> qltStdSubClaList;
    /**
     * Get the qltStdSubClaList.
     *
     * @return qltStdSubClaList
     *
     * @author Administrator
     */
    public List<QltStdSubCla> getQltStdSubClaList() {
        return this.qltStdSubClaList;
    }
    /**
     * Set the qltStdSubClaList.
     *
     * @param qltStdSubClaList qltStdSubClaList
     *
     * @author Administrator
     */
    public void setQltStdSubClaList(List<QltStdSubCla> qltStdSubClaList) {
        this.qltStdSubClaList = qltStdSubClaList;
    }

}
