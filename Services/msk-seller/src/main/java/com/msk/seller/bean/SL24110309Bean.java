package com.msk.seller.bean;

import com.msk.common.base.BaseBean;

public class SL24110309Bean extends BaseBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private String slSCId; // 生产商id
    private String slSCName; // 生产商名字
    private String slSCClass; // 生产商类型
    private String slSCQualification; // 行政区别
   



    public String getSlSCName() {
        return this.slSCName;
    }

    public void setSlSCName(String slSCName) {
        this.slSCName = slSCName;
    }

    public String getSlSCClass() {
        return this.slSCClass;
    }

    public void setSlSCClass(String slSCClass) {
        this.slSCClass = slSCClass;
    }

    public String getSlSCQualification() {
        return this.slSCQualification;
    }

    public void setSlSCQualification(String slSCQualification) {
        this.slSCQualification = slSCQualification;
    }

    public String getSlSCId() {
        return this.slSCId;
    }

    public void setSlSCId(String slSCId) {
        this.slSCId = slSCId;
    }
}
