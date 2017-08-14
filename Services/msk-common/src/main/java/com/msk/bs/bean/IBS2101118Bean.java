package com.msk.bs.bean;

import com.msk.core.entity.SlBsBuyer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhu_kai1 on 2016/8/18.
 */
@ApiModel(value = "IBS2101118Bean",description = "冻品管家营销列表")
public class IBS2101118Bean extends SlBsBuyer {

    @ApiModelProperty(value = "管家名称")
    private  String houseShowName;

    @ApiModelProperty(value = "买家上线概率评分")
    private  String buyerOnlineScore;

    @ApiModelProperty(value = "冻品管家变更原因")
    private  String  changeReason;

    @ApiModelProperty(value = "专属会员发展方式")
    private String developmentWay;

    public String getHouseShowName() {
        return houseShowName;
    }

    public void setHouseShowName(String houseShowName) {
        this.houseShowName = houseShowName;
    }

    public String getBuyerOnlineScore() {
        return buyerOnlineScore;
    }

    public void setBuyerOnlineScore(String buyerOnlineScore) {
        this.buyerOnlineScore = buyerOnlineScore;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getDevelopmentWay() {
        return developmentWay;
    }

    public void setDevelopmentWay(String developmentWay) {
        this.developmentWay = developmentWay;
    }
}
