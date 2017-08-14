package com.msk.ssc.bean;

import com.msk.core.entity.SscContractBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by xia_xiaojie on 2016/8/25.
 */
@ApiModel(value = "SSC11304ContractBean", description = "SscContractBasic实体的封装对象")
public class SSC11304ContractBean extends SscContractBasic {
    @ApiModelProperty(value = "合同生效日期")
    private String contractActDateStr;

    public String getContractActDateStr() {
        return contractActDateStr;
    }

    public void setContractActDateStr(String contractActDateStr) {
        this.contractActDateStr = contractActDateStr;
    }

}