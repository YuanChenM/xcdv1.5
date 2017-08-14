package com.msk.org.bean.result;


import com.msk.common.bean.result.BaseResult;

/**
 * Created by jackjiang on 16/8/21.
 */
public class EmployVisitPageResult extends BaseResult {
    private String systemCode;
    private String pageCode;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }
}
