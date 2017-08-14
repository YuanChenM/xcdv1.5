package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

/**
 * OEM供应商：OEM待申报产品一览
 */
public class MskSlInfoServiceParam extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String slAccount;

    private String slId;

    private String slCode;
    /**显示用Code*/
    private String slCodeDis;

    private String epId;

    private String epName;

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }
}
