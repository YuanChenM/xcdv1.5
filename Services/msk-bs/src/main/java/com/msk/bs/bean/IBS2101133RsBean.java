package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by gao_min on 2016/10/12.
 */
@ApiModel(value = "IBS2101133RsBean", description = "教育履历")
public class IBS2101133RsBean extends BaseEntity {

    @ApiModelProperty(value = "教育单位")
    private String eduComp;

    @ApiModelProperty(value = "开始日期(yyyyMM)")
    private Date eduStart;

    @ApiModelProperty(value = "结束日期(yyyyMM)")
    private Date eduEnd;


    public String getEduComp() {
        return eduComp;
    }

    public void setEduComp(String eduComp) {
        this.eduComp = eduComp;
    }

    public Date getEduStart() {
        return eduStart;
    }

    public void setEduStart(Date eduStart) {
        this.eduStart = eduStart;
    }

    public Date getEduEnd() {
        return eduEnd;
    }

    public void setEduEnd(Date eduEnd) {
        this.eduEnd = eduEnd;
    }
}
