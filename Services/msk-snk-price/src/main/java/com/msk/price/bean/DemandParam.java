package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * DemandParam
 * yang_yang
 */
@ApiModel(value = "DemandParam",description = "分销数量req")
public class DemandParam extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物流区编码")
    private String lgcsAreaCode;

    @ApiModelProperty(value = "产品质量标准定级")
    private String slQltGradeCode;

    @ApiModelProperty(value = "产品编码")
    private String pdCode;

    @ApiModelProperty(value = "供应商编码")
    private String slCode;

    @ApiModelProperty(value = "供应商类型")
    private String slmainClass;

    @ApiModelProperty(value = "分销月度")
    private String demandMonth;

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getSlQltGradeCode() {
        return slQltGradeCode;
    }

    public void setSlQltGradeCode(String slQltGradeCode) {
        this.slQltGradeCode = slQltGradeCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlmainClass() {
        return slmainClass;
    }

    public void setSlmainClass(String slmainClass) {
        this.slmainClass = slmainClass;
    }

    public String getDemandMonth() {
        return demandMonth;
    }

    public void setDemandMonth(String demandMonth) {
        this.demandMonth = demandMonth;
    }
}
