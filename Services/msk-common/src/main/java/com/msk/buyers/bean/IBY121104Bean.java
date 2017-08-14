package com.msk.buyers.bean;

import com.msk.common.base.BaseBean;

/**
 * 菜场Bean.
 *
 * @author yang_yang
 */
public class IBY121104Bean extends BaseBean {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** 菜场ID */
    private String marketId;
    /** 菜场编码 */
    private String marketCode;
    /** 菜场名称 */
    private String marketName;
    /** 菜场地址 */
    private String marketAddress;
    /** 物流区编码 */
    private String lgcsCode;
    /** 城市（地区）编码 */
    private String cityCode;
    /** 区（县）编码 */
    private String districtCode;

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketAddress() {
        return marketAddress;
    }

    public void setMarketAddress(String marketAddress) {
        this.marketAddress = marketAddress;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
}
