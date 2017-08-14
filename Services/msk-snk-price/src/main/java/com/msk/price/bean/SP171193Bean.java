package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by wang_shuai on 2016/5/17.
 */
public class SP171193Bean extends BaseEntity {
    //id
    private long wayDetailId;
    //通道code
    private String wayCode;
    //通道名
    private String wayName;
    //通道等级code
    private String waygradeCode;
    //通道等级名称
    private String waygradeName;
    //通道等级箱数下限
    private int waygradeStart;
    //通道等级箱数上限
    private int waygradeEnd;
    //通道等级平衡系数
    private int waygradePercent;
    //分销通道code
    private String sellWayCode;

    public long getWayDetailId() {
        return wayDetailId;
    }

    public void setWayDetailId(long wayDetailId) {
        this.wayDetailId = wayDetailId;
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getWaygradeCode() {
        return waygradeCode;
    }

    public void setWaygradeCode(String waygradeCode) {
        this.waygradeCode = waygradeCode;
    }

    public String getWaygradeName() {
        return waygradeName;
    }

    public void setWaygradeName(String waygradeName) {
        this.waygradeName = waygradeName;
    }

    public int getWaygradeStart() {
        return waygradeStart;
    }

    public void setWaygradeStart(int waygradeStart) {
        this.waygradeStart = waygradeStart;
    }

    public int getWaygradeEnd() {
        return waygradeEnd;
    }

    public void setWaygradeEnd(int waygradeEnd) {
        this.waygradeEnd = waygradeEnd;
    }

    public int getWaygradePercent() {
        return waygradePercent;
    }

    public void setWaygradePercent(int waygradePercent) {
        this.waygradePercent = waygradePercent;
    }

    public String getSellWayCode() {
        return sellWayCode;
    }

    public void setSellWayCode(String sellWayCode) {
        this.sellWayCode = sellWayCode;
    }
}
