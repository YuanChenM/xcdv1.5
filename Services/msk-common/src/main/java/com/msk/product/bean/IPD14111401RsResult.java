package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

/**
 * 物流区返回数据list中具体的接收javaBean
 * Created by Administrator on 2016/1/26.
 */
public class IPD14111401RsResult extends BaseEntity {

    //物流区编号
    private String logiAreaCode;
    //物流区名称
    private String logiAreaName;

    public String getLogiAreaCode() {
        return logiAreaCode;
    }

    public void setLogiAreaCode(String logiAreaCode) {
        this.logiAreaCode = logiAreaCode;
    }

    public String getLogiAreaName() {
        return logiAreaName;
    }

    public void setLogiAreaName(String logiAreaName) {
        this.logiAreaName = logiAreaName;
    }

}
