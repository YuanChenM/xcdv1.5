package com.msk.seller.bean;

import com.msk.core.entity.SlEpCert;
import com.msk.core.entity.SlEpCertItem;

import java.util.List;

/**
 * Created by Administrator on 2016/3/16.
 */
public class SlEpCertParam extends SlEpCert {

    //企业证照项目信息List
    private List<SlEpCertItem> certItemList;
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
