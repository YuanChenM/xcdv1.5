package com.msk.order.bean;


import com.hoperun.core.bean.BaseParam;

import java.util.List;

/**
 * Created by jiang_tengfei on 2016/5/23.
 */
public class ISO151434RsParam extends BaseParam {
    //系统编码
    private Integer orderSource;

    //本月
    private String thisMonth;

    //01日
    private String thisMonth01;

    //15日
    private  String thisMonth15;
    // 区域编码
    private String districtCode;
    // 卖家编码
    private List<String> sellerCodeList;

    // 是否查询某区域下供应商的销售量  1-表示是，0-表示其他
    private int flag;
    public String getThisMonth01() { return thisMonth01; }

    public void setThisMonth01(String thisMonth01) { this.thisMonth01 = thisMonth01; }

    public String getThisMonth15() { return thisMonth15; }

    public void setThisMonth15(String thisMonth15) { this.thisMonth15 = thisMonth15; }

    public Integer getOrderSource() {  return orderSource; }

    public void setOrderSource(Integer orderSource) { this.orderSource = orderSource; }

    public String getThisMonth() { return thisMonth; }

    public void setThisMonth(String thisMonth) { this.thisMonth = thisMonth; }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public List<String> getSellerCodeList() {
        return sellerCodeList;
    }

    public void setSellerCodeList(List<String> sellerCodeList) {
        this.sellerCodeList = sellerCodeList;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
