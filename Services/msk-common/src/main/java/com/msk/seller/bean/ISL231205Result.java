package com.msk.seller.bean;

import com.msk.common.base.BaseBean;

/**
 * Created by liu_yan2 on 2016/5/27.
 */
public class ISL231205Result extends BaseBean {
    /**等级编码*/
    private String levelCode;
    /**等级名称*/
    private String levelName;
    /**产品分类CODE*/
    private String classestreeCode;

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getClassestreeCode() {
        return classestreeCode;
    }

    public void setClassestreeCode(String classestreeCode) {
        this.classestreeCode = classestreeCode;
    }
}
