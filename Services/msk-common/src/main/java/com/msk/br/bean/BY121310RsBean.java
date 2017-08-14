package com.msk.br.bean;

import com.msk.common.base.BaseBean;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/7/11.
 */
public class BY121310RsBean extends BaseBean {
    private String buyerId;
    private String classesCode;
    private String classesName;
    private String machiningCode;
    private String machiningName;
    // 判断颜色
    private String flag;

    private List<BY121310RsBean> pageResult;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<BY121310RsBean> getPageResult() {
        return pageResult;
    }

    public void setPageResult(List<BY121310RsBean> pageResult) {
        this.pageResult = pageResult;
    }
}