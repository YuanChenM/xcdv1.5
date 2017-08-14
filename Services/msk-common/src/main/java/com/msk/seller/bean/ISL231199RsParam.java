package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsPageParam;

/**
 * Created by geng_xingdong on 2016/6/16.
 */
public class ISL231199RsParam extends RsPageParam {


    /** 区域code*/
    private String lgcsCode;

    /** 卖家编码*/
    private String slCode;

    private Integer startSize;

    public Integer getStartSize() {
        return startSize;
    }

    public void setStartSize(Integer startSize) {
        this.startSize = startSize;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }
}