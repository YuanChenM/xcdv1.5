package com.msk.batch.br.bean;

import com.msk.core.entity.SlBsBuyer;

/**
 * Created by yuan_zhifei on 2016/9/18
 */
public class IBBR12140306RsBean extends SlBsBuyer {
    /** Modif for Bug #3555 at 2016/11/03 by yuan_zhifei Start*/
    private  Long rsId;
    private  String houseShowName;
    private  String flag;

    public String getHouseShowName() {
        return houseShowName;
    }

    public void setHouseShowName(String houseShowName) {
        this.houseShowName = houseShowName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getRsId() {
        return rsId;
    }

    public void setRsId(Long rsId) {
        this.rsId = rsId;
    }
    /** Modif for Bug #3555 at 2016/11/03 by yuan_zhifei End*/
}
