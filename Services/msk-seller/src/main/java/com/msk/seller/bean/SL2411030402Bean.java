package com.msk.seller.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msk.common.base.BaseBean;
//库容概括
public class SL2411030402Bean extends BaseBean {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SL2411030402Bean.class);
    //文件图片
    private String s001;
    //资质描述
    private String s002;
    
    public String getS001() {
        return this.s001;
    }
    public void setS001(String s001) {
        this.s001 = s001;
    }
    public String getS002() {
        return this.s002;
    }
    public void setS002(String s002) {
        this.s002 = s002;
    }
    
}
