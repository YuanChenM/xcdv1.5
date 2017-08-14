package com.msk.ds.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * ISC1891I1RsResult.
 *
 * @author yuan_chen
 */
@ApiModel(value = "ISC1891I1RsResult" ,description = "查询供应计划尚未入库的供应量")
public class ISC1891I1RsResult extends RsPageResult {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public List<ISC1891I1RsResultInfo> getReturnInfos() {
        return returnInfos;
    }

    public void setReturnInfos(List<ISC1891I1RsResultInfo> returnInfos) {
        this.returnInfos = returnInfos;
    }

    /** 产品相关的信息 */
    @ApiModelProperty(value = "产品信息")
    private List<ISC1891I1RsResultInfo> returnInfos;

}
