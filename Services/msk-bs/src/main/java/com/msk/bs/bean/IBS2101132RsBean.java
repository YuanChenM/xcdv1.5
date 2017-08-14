package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by gao_min on 2016/10/12.
 */
@ApiModel(value = "IBS2101132RsBean", description = "工作履历（服务履历）")
public class IBS2101132RsBean extends BaseEntity {

    @ApiModelProperty(value = "公司名称")
    private String workComp;

    @ApiModelProperty(value = "开始日期(yyyyMM)")
    private Date workStart;

    @ApiModelProperty(value = "结束日期(yyyyMM)")
    private Date workEnd;

    @ApiModelProperty(value = "职务")
    private String workPosition;

    @ApiModelProperty(value = "岗位")
    private String workStation;

    public String getWorkComp() {
        return workComp;
    }

    public void setWorkComp(String workComp) {
        this.workComp = workComp;
    }

    public Date getWorkStart() {
        return workStart;
    }

    public void setWorkStart(Date workStart) {
        this.workStart = workStart;
    }

    public Date getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(Date workEnd) {
        this.workEnd = workEnd;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getWorkStation() {
        return workStation;
    }

    public void setWorkStation(String workStation) {
        this.workStation = workStation;
    }
}
