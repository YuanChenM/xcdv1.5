package com.msk.product.bean;

import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gao_min on 2016/10/9.
 */
@ApiModel(value = "IPD1411214RsParam", description = "param")
public class IPD1411214RsParam extends RsPageParam {

    @ApiModelProperty(value = "用户类型")
    private String userType;

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "举报单号")
    private String reportId;

    @ApiModelProperty(value = "审核状态0:未审核；1：审核通过；2：驳回；9：关闭")
    private String reportStatus;

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

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }
}
