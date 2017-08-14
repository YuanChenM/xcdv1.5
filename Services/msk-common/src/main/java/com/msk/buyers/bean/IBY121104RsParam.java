package com.msk.buyers.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * IBY121104RsParam.
 *
 * @author yang_yang
 */
public class IBY121104RsParam extends BaseParam {

    private static final long serialVersionUID = 1L;
    /** 物流区编码 */
    private String lgcsCode;
    /** 城市编码 */
    private String cityCode;
    /** 区编码 */
    private String districtCode;

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
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