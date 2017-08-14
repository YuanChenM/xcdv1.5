package com.msk.buyers.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.common.bean.RsPageParam;

/**
 * Created by guan_zhongheng on 2016/8/19.
 */
public class BY121322Param extends BasePageParam {
    /**买家id**/
    private String  buyerId;
    /**物流区编码**/
    private String  lgcsAreaCode;
    /**地区编码**/
    private String cityCode;
    /**区编码**/
    private String districtCode;
    /**营销/销售开始时间**/
    private String startTime;
    /**营销/销售结束时间**/
    private String endTime;
    /**0:营销期；1：销售期**/
    private String searchType;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

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

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
