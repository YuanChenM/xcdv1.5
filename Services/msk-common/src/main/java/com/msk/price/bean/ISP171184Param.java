package com.msk.price.bean;

import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peng_hao on 2016/6/6.
 */
@ApiModel(value = "ISP171184Param", description = "价盘通道req")
public class ISP171184Param extends RsPageParam {

    @ApiModelProperty(value = "平台区分：1：神农客平台；2：美侍客平台；3：大促会平台")
    private String siteCode;

    @ApiModelProperty(value = "分配给各个平台的身份识别码")
    private String auth;

    @ApiModelProperty(value = "产品编号")
    private String pdCode;

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }
}
