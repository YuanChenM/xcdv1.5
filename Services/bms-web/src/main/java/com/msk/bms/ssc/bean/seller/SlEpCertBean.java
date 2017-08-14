package com.msk.bms.ssc.bean.seller;

import com.msk.core.entity.SlEpCert;
import com.msk.core.entity.SlEpCertItem;

import java.util.List;

/**
 * 企业资质bean
 */
public class SlEpCertBean extends SlEpCert {

    //企业证照项目信息List
    private List<SlEpCertItem> certItemList;

    //路径
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Getter method for property <tt>certItemList</tt>.
     *
     * @return property value of certItemList
     */
    public List<SlEpCertItem> getCertItemList() {
        return certItemList;
    }

    /**
     * Setter method for property <tt>certItemList</tt>.
     *
     * @param certItemList value to be assigned to property certItemList
     */
    public void setCertItemList(List<SlEpCertItem> certItemList) {
        this.certItemList = certItemList;
    }


}
