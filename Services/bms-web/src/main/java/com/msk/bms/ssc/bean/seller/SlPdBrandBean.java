package com.msk.bms.ssc.bean.seller;


import com.msk.core.entity.SlPdBrand;

/**
 * 卖家产品品牌bean
 */
public class SlPdBrandBean extends SlPdBrand{

    // 企业名称
    private String epName;

    // 商标注册证
    private String brandNo;

    /**品牌证书图片路径*/
    private String brandPath;
    /**品牌包装图片路径*/
    private String brandPacPath;


    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    public String getBrandPath() {
        return brandPath;
    }

    public void setBrandPath(String brandPath) {
        this.brandPath = brandPath;
    }

    public String getBrandPacPath() {
        return brandPacPath;
    }

    public void setBrandPacPath(String brandPacPath) {
        this.brandPacPath = brandPacPath;
    }

}
