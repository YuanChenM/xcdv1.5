package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gao_min on 2016/10/9.
 */
@ApiModel(value = "IPD1411214RsBean", description = "举报一览")
public class IPD1411214RsBean extends BaseEntity {

    @ApiModelProperty(value = "举报单号")
    private String reportId;

    @ApiModelProperty(value = "物流区Code")
    private String lgcsCode;

    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @ApiModelProperty(value = "举报类型")
    private String reportType;

    @ApiModelProperty(value = "举报内容")
    private String reportDescription;

    @ApiModelProperty(value = "举报凭证")
    private List<String> reportImgList;

    @ApiModelProperty(value = "举报类型名称")
    private String reportTypeName;

    @ApiModelProperty(value = "审核状态0:未审核；1：审核通过；2：驳回；9：关闭")
    private String reportStatus;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public List<String> getReportImgList() {
        return reportImgList;
    }

    public void setReportImgList(List<String> reportImgList) {
        this.reportImgList = reportImgList;
    }

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }
}
