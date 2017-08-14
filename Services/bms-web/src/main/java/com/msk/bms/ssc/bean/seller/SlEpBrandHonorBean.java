package com.msk.bms.ssc.bean.seller;


import com.msk.core.entity.SlEpBrandHonor;

/**
 * 企业产品荣誉Bean
 */
public class SlEpBrandHonorBean extends SlEpBrandHonor {


    // 产品荣誉图片
    private String brandHonorPath;

    // 发证时间字符串
    private String crtDateStr;

    public String getCrtDateStr() {
        return crtDateStr;
    }

    public void setCrtDateStr(String crtDateStr) {
        this.crtDateStr = crtDateStr;
    }

    public String getBrandHonorPath() {
        return brandHonorPath;
    }

    public void setBrandHonorPath(String brandHonorPath) {
        this.brandHonorPath = brandHonorPath;
    }
}
