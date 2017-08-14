package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * SP171191Bean
 */
public class SP171191Bean extends BaseEntity {


    private  Long  waygradePdId;// id

    private String  lgcsCode;// 物流区编码

    private String lgcsName="";//物流区名称

    private String  pdCode;// 产品编码

    private String pdName="";// 产品名称

    private String wayCode="";// 通道code

    private String wayName="";// 通道名称

    private  String pdGrage="";//产品等级

    private  String marketingName="";//营销状态

    private String unit="";// 单位编号

    private String units="";// 单位

    private  String  minVal="";// 最小单位值

    private String systemType;// 使用系统


    private String lgcsCodePdCod;


    public Long getWaygradePdId() {
        return waygradePdId;
    }

    public void setWaygradePdId(Long waygradePdId) {
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

    public String getMinVal() {
        return minVal;
    }

    public void setMinVal(String minVal) {
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

    public String getPdGrage() {
        return pdGrage;
    }

    public void setPdGrage(String pdGrage) {
        this.pdGrage = pdGrage;
    }


    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }


    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getLgcsCodePdCod() {
        return lgcsCodePdCod;
    }

    public void setLgcsCodePdCod(String lgcsCodePdCod) {
        this.lgcsCodePdCod = lgcsCodePdCod;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }
}
