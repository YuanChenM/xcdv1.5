package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by gyh on 2016/3/17.
 */
public class ISL231137RsParam extends BaseParam {
    private String flag;//1：卖家代理及分销授权:2：卖家OEM委托授权标志（废除）
    private String slCode;//卖家编码
    private Integer producerEpId;//生产商_企业ID

    /**
     * Getter method for property <tt>flag</tt>.
     *
     * @return property value of flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Setter method for property <tt>flag</tt>.
     *
     * @param flag value to be assigned to property flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>producerEpId</tt>.
     *
     * @return property value of producerEpId
     */
    public Integer getProducerEpId() {
        return producerEpId;
    }

    /**
     * Setter method for property <tt>producerEpId</tt>.
     *
     * @param producerEpId value to be assigned to property producerEpId
     */
    public void setProducerEpId(Integer producerEpId) {
        this.producerEpId = producerEpId;
    }
}
