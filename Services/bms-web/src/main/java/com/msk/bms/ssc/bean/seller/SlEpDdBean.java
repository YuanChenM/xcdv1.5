package com.msk.bms.ssc.bean.seller;

import com.msk.core.entity.SlEpDd;

/**
 * 企业检测设备bean
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
