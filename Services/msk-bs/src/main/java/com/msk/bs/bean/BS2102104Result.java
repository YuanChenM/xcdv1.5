package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by ren_qiang on 2016/8/1.
 */
public class BS2102104Result extends BaseEntity {

    private  boolean  flag;


    private String message;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
