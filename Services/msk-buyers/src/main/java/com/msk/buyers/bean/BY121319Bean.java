package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByBuyerReportManager;


public class BY121319Bean extends ByBuyerBasicInfo {

    //营销工具是否被选中
    private String isChecked;

    private String byPhone;

    private String byWechat;

    private String getBYQq;

    private String marketingToolsName;

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public String getByPhone() {
        return byPhone;
    }

    public void setByPhone(String byPhone) {
        this.byPhone = byPhone;
    }

    public String getByWechat() {
        return byWechat;
    }

    public void setByWechat(String byWechat) {
        this.byWechat = byWechat;
    }

    public String getGetBYQq() {
        return getBYQq;
    }

    public void setGetBYQq(String getBYQq) {
        this.getBYQq = getBYQq;
    }

    public String getMarketingToolsName() {
        return marketingToolsName;
    }

    public void setMarketingToolsName(String marketingToolsName) {
        this.marketingToolsName = marketingToolsName;
    }
}
