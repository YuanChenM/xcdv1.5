package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by zhu_kai1 on 2016/7/8.
 */
public class BS2101113Param  extends BaseParam {
    /**管家ID**/
    private java.lang.String houseCode;
    /**买手ID**/
    private java.lang.String slCode;
    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }
}
