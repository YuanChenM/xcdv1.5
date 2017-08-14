package com.msk.seller.bean;

import com.msk.core.entity.SlEpBrand;

import java.util.List;

/**
 * Created by zhang_chi on 2016/4/28.
 */
public class ISL231149RsResult {
    /** 状态 */
    private String status;
    /** 返回代码 */
    private String returnCode;
    /** 结果消息 */
    private String message;

    private List<SlEpBrand> slEpBrandList;

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

    public List<SlEpBrand> getSlEpBrandList() {
        return slEpBrandList;
    }

    public void setSlEpBrandList(List<SlEpBrand> slEpBrandList) {
        this.slEpBrandList = slEpBrandList;
    }
}
