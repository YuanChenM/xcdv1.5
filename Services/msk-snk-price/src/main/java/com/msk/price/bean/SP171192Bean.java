package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by wang_shuai on 2016/5/20.
 */
public class SP171192Bean extends BaseEntity {
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
    private BigDecimal waygradePercent;
    //分销通道code
    private String sellWayCode;

    private Integer saveOrUpFlag;

    private long wayId;

    private String systemType;

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public long getWayId() {
        return wayId;
    }

    public void setWayId(long wayId) {
        this.wayId = wayId;
    }

    public Integer getSaveOrUpFlag() {
        return saveOrUpFlag;
    }

    public void setSaveOrUpFlag(Integer saveOrUpFlag) {
        this.saveOrUpFlag = saveOrUpFlag;
    }

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

    public BigDecimal getWaygradePercent() {
        return waygradePercent;
    }

    public void setWaygradePercent(BigDecimal waygradePercent) {
        this.waygradePercent = waygradePercent;
    }

    public String getSellWayCode() {
        return sellWayCode;
    }

    public void setSellWayCode(String sellWayCode) {
        this.sellWayCode = sellWayCode;
    }
}

