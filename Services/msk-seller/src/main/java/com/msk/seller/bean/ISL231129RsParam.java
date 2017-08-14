package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.List;

/**
 *增加企业专业资质传入参数
 */

public class ISL231129RsParam extends BaseParam{
    private static final long serialVersionUID = 1L;
   /**企业ID*/
    private Integer epId;

    /**
     * 获得epId
     */
    public Integer getEpId() {
        return epId;
    }

    /**
     * 设置epId
     */
    public void setEpId(Integer epId) {
        this.epId = epId;
    }
}
