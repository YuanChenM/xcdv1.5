package com.msk.bms.ssc.bean.seller;

import com.msk.core.entity.SlEpHonor;

/**
 * 企业荣誉Bean.
 */
public class SlEpHonorBean extends SlEpHonor {

    //资质描述
    private String aptitudeDesc;
    //荣誉图片路径
    private String imgUrl;

    public String getAptitudeDesc() {
        return aptitudeDesc;
    }

    public void setAptitudeDesc(String aptitudeDesc) {
        this.aptitudeDesc = aptitudeDesc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
