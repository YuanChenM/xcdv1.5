package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * ISO151405_产品销量查询接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151405RestParam extends BaseParam {
    // 产品编码列表
    private List<ISO151405PdCodeListRestParam> pdCodeList;
    // 是否返回模糊值
    private Integer isReturnFuzzy;
    // 模糊值对比界限值
    private Integer fuzzyValueLimit;

    public Integer getIsReturnFuzzy() {
        return isReturnFuzzy;
    }

    public void setIsReturnFuzzy(Integer isReturnFuzzy) {
        this.isReturnFuzzy = isReturnFuzzy;
    }

    public Integer getFuzzyValueLimit() {
        return fuzzyValueLimit;
    }

    public void setFuzzyValueLimit(Integer fuzzyValueLimit) {
        this.fuzzyValueLimit = fuzzyValueLimit;
    }

    public List<ISO151405PdCodeListRestParam> getPdCodeList() {
        return pdCodeList;
    }

    public void setPdCodeList(List<ISO151405PdCodeListRestParam> pdCodeList) {
        this.pdCodeList = pdCodeList;
    }
}
