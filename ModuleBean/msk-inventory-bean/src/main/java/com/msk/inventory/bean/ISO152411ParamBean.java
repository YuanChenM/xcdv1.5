package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseRestParam;

import java.util.List;

/**
 * Created by zheng_xu on 2016/8/22.
 */
public class ISO152411ParamBean extends BaseRestParam {
    //平台Code或者卖家供应商ID
    private String sellerCode;
    //对应电商平台类型（销售平台）
    private String platformType;
    //物流区域编码
    private Integer districtCode;
    //卖家类型
    private Integer sellerType;
    //产品编码
    private String pdCode;
    //专属买家手机号码
    private String userMoblie;


    public String getSellerCode() {
        return sellerCode;
    }

    public String getPlatformType() {
        return platformType;
    }

    public Integer getDistrictCode() {
        return districtCode;
    }

    public Integer getSellerType() {
        return sellerType;
    }

    public String getPdCode() {
        return pdCode;
    }

    public String getUserMoblie() {
        return userMoblie;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public void setSellerType(Integer sellerType) {
        this.sellerType = sellerType;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public void setUserMoblie(String userMoblie) {
        this.userMoblie = userMoblie;
    }

}
