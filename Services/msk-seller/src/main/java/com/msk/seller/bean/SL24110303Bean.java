package com.msk.seller.bean;

import java.util.List;

import com.msk.common.base.BaseBean;

/**
 * @author rwf
 * 企业基本能力描述
 * */
public class SL24110303Bean extends BaseBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * rwf
     * 
     * 企业基本能力描述
     */
    // 企业荣誉
    private List<SL2411030301Bean> sL2411030301BeanList;
    // 企业生产能力
    private List<SL2411030302Bean> sL2411030302BeanList;
    /**
     * Get the sL2411030301BeanList.
     *
     * @return sL2411030301BeanList
     *
     * @author rwf
     */
    public List<SL2411030301Bean> getsL2411030301BeanList() {
        return this.sL2411030301BeanList;
    }
    /**
     * Set the sL2411030301BeanList.
     *
     * @param sL2411030301BeanList sL2411030301BeanList
     *
     * @author rwf
     */
    public void setsL2411030301BeanList(List<SL2411030301Bean> sL2411030301BeanList) {
        this.sL2411030301BeanList = sL2411030301BeanList;
    }
    /**
     * Get the sL2411030302BeanList.
     *
     * @return sL2411030302BeanList
     *
     * @author rwf
     */
    public List<SL2411030302Bean> getsL2411030302BeanList() {
        return this.sL2411030302BeanList;
    }
    /**
     * Set the sL2411030302BeanList.
     *
     * @param sL2411030302BeanList sL2411030302BeanList
     *
     * @author rwf
     */
    public void setsL2411030302BeanList(List<SL2411030302Bean> sL2411030302BeanList) {
        this.sL2411030302BeanList = sL2411030302BeanList;
    }

   

}
