package com.msk.ssc.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SscContractBasic;

/**
 * Created by liu_yan2 on 2016/8/8.
 */
public class SSC11304RsParam extends SscContractBasic {

    /** 实际生产开始日期（可以早于合同生效日期） */
    private String realProduceStartDateStr;

    /** 实际生产结束日期（可以晚于最迟交货日期） */
    private String realProduceEndDateStr;

    public String getRealProduceStartDateStr() {
        return realProduceStartDateStr;
    }

    public void setRealProduceStartDateStr(String realProduceStartDateStr) {
        this.realProduceStartDateStr = realProduceStartDateStr;
    }

    public String getRealProduceEndDateStr() {
        return realProduceEndDateStr;
    }

    public void setRealProduceEndDateStr(String realProduceEndDateStr) {
        this.realProduceEndDateStr = realProduceEndDateStr;
    }
}
