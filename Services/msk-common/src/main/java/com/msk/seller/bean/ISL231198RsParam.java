package com.msk.seller.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.common.bean.RsPageParam;

/**
 * 物流区供应商参数
 *
 * Created by yang_chunyan on 2016/6/8.
 */
public class ISL231198RsParam extends RsPageParam{

    /**卖家名称*/
    private String slName;
    /**区域编码*/
    private String[] lgcsCode;

    private Integer startSize;

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String[] getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String[] lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public Integer getStartSize() {
        return startSize;
    }

    public void setStartSize(Integer startSize) {
        this.startSize = startSize;
    }
}
