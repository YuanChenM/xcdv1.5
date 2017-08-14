/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 *
 * @author ren_qiang
 *
 */
@ApiModel(value = "IBS2101126RsResult",description = "result")
public class IBS2101126RsResult extends BaseEntity {

    @ApiModelProperty(value = "买手账号")
    private  String slAcount;

    @ApiModelProperty(value = "买手id")
    private  String slCode;

    @ApiModelProperty(value = "买手编码")
    private  String slCodeDis;

    @ApiModelProperty(value = "买手列表")
    private List<IBS2101126RsBean> slList;

    public String getSlAcount() {
        return slAcount;
    }

    public void setSlAcount(String slAcount) {
        this.slAcount = slAcount;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public List<IBS2101126RsBean> getSlList() {
        return slList;
    }

    public void setSlList(List<IBS2101126RsBean> slList) {
        this.slList = slList;
    }
}
