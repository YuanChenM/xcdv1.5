package com.msk.ssc.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.List;

/**
 * Created by liu_yan2 on 2016/8/8.
 */
public class SSC11326RsParam extends BaseParam {

    /** 合同编码 */
    private String contractCode;

    /** 类型（1生产期管控 2运输期管控） */
    private java.lang.String type;

    /** 批量保存生产期/待运期产品管控 */
    private List<SSC11326RsBean> ssc11326RsBeans;

    /** 实际生产开始日期（可以早于合同生效日期） */
    private String realProduceStartDate;

    /** 实际生产结束日期（可以晚于最迟交货日期） */
    private String realProduceEndDate;

    private String jsonStr;

    /** 运输期产品管控信息 */
    private List<SSC1132601RsBean> deliveryPdControls;

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SSC11326RsBean> getSsc11326RsBeans() {
        return ssc11326RsBeans;
    }

    public void setSsc11326RsBeans(List<SSC11326RsBean> ssc11326RsBeans) {
        this.ssc11326RsBeans = ssc11326RsBeans;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getRealProduceStartDate() {
        return realProduceStartDate;
    }

    public void setRealProduceStartDate(String realProduceStartDate) {
        this.realProduceStartDate = realProduceStartDate;
    }

    public String getRealProduceEndDate() {
        return realProduceEndDate;
    }

    public void setRealProduceEndDate(String realProduceEndDate) {
        this.realProduceEndDate = realProduceEndDate;
    }

    public List<SSC1132601RsBean> getDeliveryPdControls() {
        return deliveryPdControls;
    }

    public void setDeliveryPdControls(List<SSC1132601RsBean> deliveryPdControls) {
        this.deliveryPdControls = deliveryPdControls;
    }
}
