package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by peng_hao on 2016/6/8.
 */
@ApiModel(value = "ISP171184Bean", description = "searchList")
public class ISP171184Bean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品ID")
    private String productId;

    @ApiModelProperty(value = "产品等级编码")
    private String  gradeCode;

    @ApiModelProperty(value = "物流区编码")
    private String  logiAreaCode;

    @ApiModelProperty(value = "价格列表")
    private List<ISP171184WayBean> waylist;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getLogiAreaCode() {
        return logiAreaCode;
    }

    public void setLogiAreaCode(String logiAreaCode) {
        this.logiAreaCode = logiAreaCode;
    }

    public List<ISP171184WayBean> getWaylist() {
        return waylist;
    }

    public void setWaylist(List<ISP171184WayBean> waylist) {
        this.waylist = waylist;
    }
}
