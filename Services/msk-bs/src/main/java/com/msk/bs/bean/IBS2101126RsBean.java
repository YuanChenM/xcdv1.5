/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>表sl_house_bypraise_rec对应的IBS2101125RsBean。</p>
 *冻品管家点赞表
 * @author 自动生成
 * @version 1.00
 */
@ApiModel(value = "IBS2101126RsBean",description = "买手列表")
public class IBS2101126RsBean extends BaseEntity {

    @ApiModelProperty(value = "买手账号")
    private  String slAcount;

    @ApiModelProperty(value = "买手id")
    private  String slCode;

    @ApiModelProperty(value = "买手编码")
    private  String slCodeDis;

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
}
