/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.bms.ssc.bean.seller;

import com.msk.core.entity.SlEpWorkshop;

/**
 * 企业生产车间、设备、产品工艺流程描述bean
 */
public class SlEpWorkshopBean extends SlEpWorkshop{
    // 资质描述
    private String workshopDesc;
    //车间图片路径
    private String imgUrl;

    public String getWorkshopDesc() {
        return workshopDesc;
    }

    public void setWorkshopDesc(String workshopDesc) {
        this.workshopDesc = workshopDesc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
