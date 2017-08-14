package com.msk.bs.bean;

import com.msk.core.entity.BsAccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yang_chunyan on 2016/5/7.
 */
@ApiModel(value = "IBA2141102RsResult", description = "result")
public class IBA2141102RsResult extends BsAccount {
    @ApiModelProperty(value = "slCode")
    private String slCode;
    @ApiModelProperty(value = "买手编码")
    private String slCodeDis;
    @ApiModelProperty(value = "物流区code")
    private String lgcsCode;
    @ApiModelProperty(value = "物流区名称")
    private String lgcsName;
    @ApiModelProperty(value = "省code")
    private String provinceCode;
    @ApiModelProperty(value = "城市code")
    private String cityCode;
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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
