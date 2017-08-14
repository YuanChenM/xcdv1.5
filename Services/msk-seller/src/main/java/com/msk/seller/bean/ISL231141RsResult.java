package com.msk.seller.bean;

import com.msk.core.entity.SlEpManager;

import java.util.List;

/**
 * Created by Administrator on 2016/2/17.
 */
public class ISL231141RsResult {
    /** 状态 */
    private String status;
    /** 返回代码 */
    private String returnCode;
    /** 结果消息 */
    private String message;

    private List<SlEpManager> slEpManagerList;

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

    public List<SlEpManager> getSlEpManagerList() {
        return slEpManagerList;
    }

    public void setSlEpManagerList(List<SlEpManager> slEpManagerList) {
        this.slEpManagerList = slEpManagerList;
    }
}
