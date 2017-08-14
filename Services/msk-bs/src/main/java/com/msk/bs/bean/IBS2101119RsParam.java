package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;
import com.msk.core.entity.SlHouseAccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gyh on 2016/3/30.
 */
@ApiModel(value = "IBS2101119RsParam",description = "param")
public class IBS2101119RsParam extends RsPageParam {

    @ApiModelProperty(value = "物流区编码")
    private String  lgcsAreaCode;

    @ApiModelProperty(value = "地区编码")
    private  String cityCode;

    @ApiModelProperty(value = "冻品管家列表")
    private List<IBS2101120Param> houseList;

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public List<IBS2101120Param> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<IBS2101120Param> houseList) {
        this.houseList = houseList;
    }
}
