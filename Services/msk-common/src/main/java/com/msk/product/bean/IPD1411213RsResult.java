package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gao_min on 2016/10/9.
 */
@ApiModel(value = "IPD1411213RsResult", description = "result")
public class IPD1411213RsResult extends BaseEntity {

    @ApiModelProperty(value = "举报单号")
    private String reportId;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
}
