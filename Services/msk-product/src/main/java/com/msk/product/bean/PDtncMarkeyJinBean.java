package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * 
 * PDtncMarkeyBean. 市场需求标准
 * @author xhy
 */
public class PDtncMarkeyJinBean extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private List<PDtncMarkeyBean> listMar;
    private List<PDtncMarkeyBean> listMarJin;

    /**
     * Getter method for property <tt>listMar</tt>.
     *
     * @return property value of listMar
     */
    public List<PDtncMarkeyBean> getListMar() {
        return listMar;
    }

    /**
     * Setter method for property <tt>listMar</tt>.
     *
     * @param listMar value to be assigned to property listMar
     */
    public void setListMar(List<PDtncMarkeyBean> listMar) {
        this.listMar = listMar;
    }

    /**
     * Getter method for property <tt>listMarJin</tt>.
     *
     * @return property value of listMarJin
     */
    public List<PDtncMarkeyBean> getListMarJin() {
        return listMarJin;
    }

    /**
     * Setter method for property <tt>listMarJin</tt>.
     *
     * @param listMarJin value to be assigned to property listMarJin
     */
    public void setListMarJin(List<PDtncMarkeyBean> listMarJin) {
        this.listMarJin = listMarJin;
    }
}
