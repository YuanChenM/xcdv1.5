package com.msk.br.bean;

import com.hoperun.core.bean.BasePageParam;

/**
 * 买家标准产品池param
 * <p/>
 * Created by yuan_zhifei on 2016/07/08.
 */
public class BR121404RsParam extends BasePageParam {

    /**
     * 买家ID
     */
    private String buyerId;
    /**
     * 文件名
     */
    private String fileName;
    //产品加工类型名称
    private String machiningName;
    //文件所属月份
    private String month;
    //订单明细状态
    private String detailStatus;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }
}
