package com.msk.common.bean;

import java.io.Serializable;

/**
 * Created by shi_yuxi on 2016/8/19.
 */
public class UploadResponseBean implements Serializable {
    private String taskId;

    private String errorMessge;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getErrorMessge() {
        return errorMessge;
    }

    public void setErrorMessge(String errorMessge) {
        this.errorMessge = errorMessge;
    }
}
