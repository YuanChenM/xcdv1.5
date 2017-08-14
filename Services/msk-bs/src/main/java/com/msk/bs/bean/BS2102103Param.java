package com.msk.bs.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.core.entity.SlHouseManage;

import java.util.List;

/**
 * Created by wang_haichun on 2016/8/3.
 */
public class BS2102103Param extends BasePageParam {

    /** SL_CODE */
    private String slCode;
    /** HOUSE_CODE */
    private String houseCode;
    /** LGCS_AREA_CODE */
    private String lgcsAreaCode;
    /** LGCS_AREA_NAME */
    private String lgcsAreaName;

    private String yearMonth;

    private List<SlHouseManage> slHouseManageList;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public List<SlHouseManage> getSlHouseManageList() {
        return slHouseManageList;
    }

    public void setSlHouseManageList(List<SlHouseManage> slHouseManageList) {
        this.slHouseManageList = slHouseManageList;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
}
