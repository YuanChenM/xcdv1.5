package com.msk.seller.bean;

import com.msk.core.entity.SlPdBrand;

import java.util.List;

/**
 * Created by zhang_chi on 2016/4/28.
 */
public class ISL231153RsResult {
    /** 状态 */
    private String status;
    /** 返回代码 */
    private String returnCode;
    /** 结果消息 */
    private String message;

    private List<SlPdBrand> slPdBrandList;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SlPdBrand> getSlPdBrandList() {
        return slPdBrandList;
    }

    public void setSlPdBrandList(List<SlPdBrand> slPdBrandList) {
        this.slPdBrandList = slPdBrandList;
    }
}
