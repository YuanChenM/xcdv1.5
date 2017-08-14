package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gao_min on 2016/10/9.
 */
@ApiModel(value = "IPD1411212RsBean", description = "bean")
public class IPD1411212RsBean extends BaseEntity {

    @ApiModelProperty(value = "举报类型CODE")
    private String reportType;

    @ApiModelProperty(value = "举报类型标题")
    private String reportTitle;

    @ApiModelProperty(value = "举报类型描述")
    private String reportDescription;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }
}
