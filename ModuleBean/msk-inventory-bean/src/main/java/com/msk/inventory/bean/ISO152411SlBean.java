package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseParam;

import java.io.Serializable;

/**
 * Created by wang_fan2 on 2016/9/9.
 */
public class ISO152411SlBean extends BaseParam implements Serializable {
    private String slCode;

    private String slCodeDis;

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
}
