package com.msk.ssc.bean;


import com.msk.core.entity.SscContractBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * Created by tao_zhifa on 2016/6/30.
 */
@ApiModel(value = "SSC11303RsBean", description = "SscContractBasic实体的封装对象")
public class SSC11303RsBean extends SscContractBasic{
    @ApiModelProperty(value = "合同生效期")
    private String contractActDateStr;

    @ApiModelProperty(value = "合同")
    private List<SscContractBasic> pageResult;


    public List<SscContractBasic> getPageResult() {
        return pageResult;
    }

    public void setPageResult(List<SscContractBasic> pageResult) {
        this.pageResult = pageResult;
    }

    public String getContractActDateStr() {
        return contractActDateStr;
    }

    public void setContractActDateStr(String contractActDateStr) {
        this.contractActDateStr = contractActDateStr;
    }

}