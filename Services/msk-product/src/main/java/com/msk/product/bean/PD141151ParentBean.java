package com.msk.product.bean;

import com.msk.core.entity.PdSftStdItem;

import java.util.List;

/**
 * PD141120Bean.
 *
 * @author gyh
 */
public class PD141151ParentBean extends PdSftStdItem {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private List<PD141151Param> sftList;

    /**
     * Getter method for property <tt>sftList</tt>.
     *
     * @return property value of sftList
     */
    public List<PD141151Param> getSftList() {
        return sftList;
    }

    /**
     * Setter method for property <tt>sftList</tt>.
     *
     * @param sftList value to be assigned to property sftList
     */
    public void setSftList(List<PD141151Param> sftList) {
        this.sftList = sftList;
    }
}
