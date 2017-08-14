package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

/**
 * 物流区供应商结果
 *
 * Created by yang_chunyan on 2016/6/8.
 */
public class ISL231198RsResult extends BaseEntity {

    /**卖家编码*/
    private String slCode;

    /**卖家名称*/
    private String slName;

    /**区域编码*/
    private String lgcsCode;

    /**区域名称*/
    private String lgcsName;


    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }
}
