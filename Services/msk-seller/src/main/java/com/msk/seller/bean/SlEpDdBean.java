package com.msk.seller.bean;

import com.msk.core.entity.SlEpDd;

/**
 * Created by Administrator on 2016/3/29.
 */
public class SlEpDdBean extends SlEpDd {

    //检测设备图片路径
    private String slEpDdPath;

    /**
     * Getter method for property <tt>slEpDdPath</tt>.
     *
     * @return property value of slEpDdPath
     */
    public String getSlEpDdPath() {
        return slEpDdPath;
    }

    /**
     * Setter method for property <tt>slEpDdPath</tt>.
     *
     * @param slEpDdPath value to be assigned to property slEpDdPath
     */
    public void setSlEpDdPath(String slEpDdPath) {
        this.slEpDdPath = slEpDdPath;
    }
}
