package com.msk.bs.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhu_kai1 on 2016/7/19.
 */
@ApiModel(value = "IBA2141110Result", description = "result")
public class IBA2141110Result extends IBA2141110Bean {

    @ApiModelProperty(value = "标志产品已下架，1-代表不存在或已下架")
    private String isOffTheShelf;

    /*价盘周期编号(共5位: 年(2位) + 月(2位) + 半旬号(1位)) */
    private String priceCycle;

    public String getIsOffTheShelf() {
        return isOffTheShelf;
    }

    public void setIsOffTheShelf(String isOffTheShelf) {
        this.isOffTheShelf = isOffTheShelf;
    }

    public String getPriceCycle() {
        return priceCycle;
    }

    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }
}
