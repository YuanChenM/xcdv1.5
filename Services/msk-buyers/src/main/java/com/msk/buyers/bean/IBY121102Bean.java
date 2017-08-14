package com.msk.buyers.bean;

import com.msk.common.base.BaseBean;

/**
 * Created by tao_zhifa on 2016/8/18.
 */
public class IBY121102Bean extends BaseBean {
    private String marketingTools;
    private String isChecked;
    private String setMarketingToolsName;

    private String telMarketingStartTime;

    private String telMarketingEndTime;

    public String getSetMarketingToolsName() {
        return setMarketingToolsName;
    }

    public void setSetMarketingToolsName(String setMarketingToolsName) {
        this.setMarketingToolsName = setMarketingToolsName;
    }

    public String getTelMarketingStartTime() {
        return telMarketingStartTime;
    }

    public void setTelMarketingStartTime(String telMarketingStartTime) {
        this.telMarketingStartTime = telMarketingStartTime;
    }

    public String getTelMarketingEndTime() {
        return telMarketingEndTime;
    }

    public void setTelMarketingEndTime(String telMarketingEndTime) {
        this.telMarketingEndTime = telMarketingEndTime;
    }

    public String getMarketingTools() {
        return marketingTools;
    }

    public void setMarketingTools(String marketingTools) {
        this.marketingTools = marketingTools;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
