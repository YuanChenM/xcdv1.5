package com.msk.seller.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msk.common.base.BaseBean;

/**
 * 包装
 * SL2411030802Bean.
 *
 * @author chen_xin
 */
public class SL2411030802Bean extends BaseBean {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SL2411030802Bean.class);

    // 企业id
    private Integer companyId;
    // 证照  包装
    private String zhengZhao2;
    // 图片
    private String image2;
    public Integer getCompanyId() {
        return this.companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public String getZhengZhao2() {
        return this.zhengZhao2;
    }
    public void setZhengZhao2(String zhengZhao2) {
        this.zhengZhao2 = zhengZhao2;
    }
    public String getImage2() {
        return this.image2;
    }
    public void setImage2(String image2) {
        this.image2 = image2;
    }


}
