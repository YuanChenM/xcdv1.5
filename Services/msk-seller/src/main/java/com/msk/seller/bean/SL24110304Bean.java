package com.msk.seller.bean;

import java.util.List;

import com.msk.common.base.BaseBean;
/**
 * rwf
 * 企业生产车间、设备、产品工艺流程描述
 */
public class SL24110304Bean extends BaseBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    // 企业生产车间、设备、产品工艺流程描述包含车间概括和库容概括集合
    // 车间概括
    private List<SL2411030303Bean> sL2411030303BeanList;
    // 库容概括
    private List<SL2411030302Bean> sL2411030302BeanList;
    /**
     * Get the sL2411030303BeanList.
     *
     * @return sL2411030303BeanList
     *
     * @author rwf
     */
    public List<SL2411030303Bean> getsL2411030303BeanList() {
        return this.sL2411030303BeanList;
    }
    /**
     * Set the sL2411030303BeanList.
     *
     * @param sL2411030303BeanList sL2411030303BeanList
     *
     * @author rwf
     */
    public void setsL2411030303BeanList(List<SL2411030303Bean> sL2411030303BeanList) {
        this.sL2411030303BeanList = sL2411030303BeanList;
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
