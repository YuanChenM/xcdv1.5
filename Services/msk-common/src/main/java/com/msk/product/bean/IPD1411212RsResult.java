package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gao_min on 2016/10/9.
 */
@ApiModel(value = "IPD1411212RsResult", description = "result")
public class IPD1411212RsResult extends BaseEntity {

    @ApiModelProperty(value = "reportList")
    private List<IPD1411212RsBean> reportList;

    public List<IPD1411212RsBean> getReportList() {
        return reportList;
    }

    public void setReportList(List<IPD1411212RsBean> reportList) {
        this.reportList = reportList;
    }
}
