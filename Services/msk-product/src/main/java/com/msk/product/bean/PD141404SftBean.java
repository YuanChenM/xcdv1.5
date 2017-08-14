package com.msk.product.bean;

import com.msk.core.entity.PdSftStdItem;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12. xhy
 */
public class PD141404SftBean extends PdSftStdItem {

    private static final long serialVersionUID = 1L;


    private List<PD141404SftChildBean> pdSftChildList;

    /**
     * Getter method for property <tt>pdSftChildList</tt>.
     *
     * @return property value of pdSftChildList
     */
    public List<PD141404SftChildBean> getPdSftChildList() {
        return pdSftChildList;
    }

    /**
     * Setter method for property <tt>pdSftChildList</tt>.
     *
     * @param pdSftChildList value to be assigned to property pdSftChildList
     */
    public void setPdSftChildList(List<PD141404SftChildBean> pdSftChildList) {
        this.pdSftChildList = pdSftChildList;
    }
}
