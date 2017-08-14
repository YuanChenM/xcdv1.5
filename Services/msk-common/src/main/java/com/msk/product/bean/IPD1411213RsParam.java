package com.msk.product.bean;

import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gao_min on 2016/10/9.
 */
@ApiModel(value = "IPD1411213RsParam", description = "param")
public class IPD1411213RsParam extends RsPageParam {

    @ApiModelProperty(value = "用户类型")
    private String userType;

    @ApiModelProperty(value = "用户ID")
    private String userId;

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

    @ApiModelProperty(value = "举报单号")
    private String reportId;

    @Override
    public String getUserType() {
        return userType;
    }

    @Override
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
}
