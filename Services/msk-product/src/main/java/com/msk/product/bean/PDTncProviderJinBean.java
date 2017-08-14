package com.msk.product.bean;

import com.msk.core.entity.PdTncStdDiscussProvider;

import java.util.List;

/**
 * 
 * PDtncMarkeyBean. 供应商习惯性标准
 * @author xhy
 */
public class PDTncProviderJinBean extends PdTncStdDiscussProvider {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private List<PDtncProviderBean> pdTncProList ;
    private List<PDtncProviderBean> pdTncProNoList ;

    /**
     * Getter method for property <tt>pdTncProList</tt>.
     *
     * @return property value of pdTncProList
     */
    public List<PDtncProviderBean> getPdTncProList() {
        return pdTncProList;
    }

    /**
     * Setter method for property <tt>pdTncProList</tt>.
     *
     * @param pdTncProList value to be assigned to property pdTncProList
     */
    public void setPdTncProList(List<PDtncProviderBean> pdTncProList) {
        this.pdTncProList = pdTncProList;
    }

    /**
     * Getter method for property <tt>pdTncProNoList</tt>.
     *
     * @return property value of pdTncProNoList
     */
    public List<PDtncProviderBean> getPdTncProNoList() {
        return pdTncProNoList;
    }

    /**
     * Setter method for property <tt>pdTncProNoList</tt>.
     *
     * @param pdTncProNoList value to be assigned to property pdTncProNoList
     */
    public void setPdTncProNoList(List<PDtncProviderBean> pdTncProNoList) {
        this.pdTncProNoList = pdTncProNoList;
    }
}
