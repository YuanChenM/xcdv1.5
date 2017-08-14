package com.msk.product.bean;

import com.msk.core.entity.PdTspStdItem;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12. xhy
 */
public class PD141404TspBean extends PdTspStdItem {

    private static final long serialVersionUID = 1L;

    private List<PD141404TspChildBean> pdTspChildList;

    /**
     * Getter method for property <tt>pdTspChildList</tt>.
     *
     * @return property value of pdTspChildList
     */
    public List<PD141404TspChildBean> getPdTspChildList() {
        return pdTspChildList;
    }

    /**
     * Setter method for property <tt>pdTspChildList</tt>.
     *
     * @param pdTspChildList value to be assigned to property pdTspChildList
     */
    public void setPdTspChildList(List<PD141404TspChildBean> pdTspChildList) {
        this.pdTspChildList = pdTspChildList;
    }
}
