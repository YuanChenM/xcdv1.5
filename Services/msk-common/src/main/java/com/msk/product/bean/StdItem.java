package com.msk.product.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 档案卡参数
 * Created by yang_chunyan on 2016/6/20.
 */
@ApiModel(value = "StdItem", description = "档案卡参数")
public class StdItem {

    @ApiModelProperty(value = "标准ID")
    private String standardId;

    @ApiModelProperty(value = "项目ID")
    private String stdItemId;

    @ApiModelProperty(value = "包装编码")
    private String normsCode;

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getStdItemId() {
        return stdItemId;
    }

    public void setStdItemId(String stdItemId) {
        this.stdItemId = stdItemId;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }
}
