package com.msk.bs.bean;

import com.msk.product.bean.IPD141144RsResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141144RsResult.卖家产品库存查询
 *
 * @author xhy 2016-4-8
 */
@ApiModel(value = "IBA2141107RsResult", description = "result")
public class IBA2141107RsResult extends IPD141144RsResult {

    @ApiModelProperty(value = "物流区域名称")
    private String districtName;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}