package com.msk.seller.bean;

import com.msk.core.entity.SlSeller;

import java.util.List;

/**
 * Created by zhou_yajun on 2016/5/25.
 */
public class ISL231204RsResult extends SlSeller {

    /** 品牌ID */
    private Long brandId;

    private List<ISL231204RsResult> isl231201List;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public List<ISL231204RsResult> getIsl231201List() {
        return isl231201List;
    }

    public void setIsl231201List(List<ISL231204RsResult> isl231201List) {
        this.isl231201List = isl231201List;
    }
}
