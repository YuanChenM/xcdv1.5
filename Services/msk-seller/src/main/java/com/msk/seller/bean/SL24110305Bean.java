package com.msk.seller.bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msk.common.base.BaseBean;

public class SL24110305Bean extends BaseBean {

    /**
     * 企业实验室、检测设备及产品质量控制系统描述
     *  logger */
    private static Logger logger = LoggerFactory.getLogger(SL24110305Bean.class);
    //页面包含实验室
    private List<SL2411030501Bean> sL2411030501BeanList;
    //页面包含检测设备
    //private List<SL2411030502Bean> sL2411030502BeanList;
   
//    public List<SL2411030502Bean> getsL2411030502BeanList() {
//        return this.sL2411030502BeanList;
//    }
//    public void setsL2411030502BeanList(List<SL2411030502Bean> sL2411030502BeanList) {
//        this.sL2411030502BeanList = sL2411030502BeanList;
//    }
    public List<SL2411030501Bean> getsL2411030501BeanList() {
        return this.sL2411030501BeanList;
    }
    public void setsL2411030501BeanList(List<SL2411030501Bean> sL2411030501BeanList) {
        this.sL2411030501BeanList = sL2411030501BeanList;
    }
}
