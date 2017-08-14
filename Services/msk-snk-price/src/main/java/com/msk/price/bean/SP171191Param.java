package com.msk.price.bean;

import com.hoperun.core.bean.BaseParam;

import java.math.BigDecimal;

/**
 * SP171191Param
 */
public class SP171191Param extends BaseParam{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private  long waygradePdId;// id

    private String  lgcsCode;// 物流区编码

    private String lgcsName;//物流区名称

    private String  pdCode;// 产品编码

    private String wayCode;// 通道code

    private String wayName;// 通道标准名称

    private String pdGrade;// 等级
    private String units;// 单位

    private BigDecimal minVal;// 最小单位值

    private String systemType;// 使用系统


    public long getWaygradePdId() {
        return waygradePdId;
    }

    public void setWaygradePdId(long waygradePdId) {
        this.waygradePdId = waygradePdId;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public BigDecimal getMinVal() {
        return minVal;
    }

    public void setMinVal(BigDecimal minVal) {
        this.minVal = minVal;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getPdGrade() {
        return pdGrade;
    }

    public void setPdGrade(String pdGrade) {
        this.pdGrade = pdGrade;
    }
}
