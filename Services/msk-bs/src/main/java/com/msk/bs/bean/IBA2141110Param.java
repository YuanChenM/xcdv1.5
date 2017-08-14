package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/7/19.
 */
@ApiModel(value = "IBA2141110Param", description = "param")
public class IBA2141110Param extends BaseParam {

    @ApiModelProperty(value = "管家ID")
    private String buyersId;

    @ApiModelProperty(value = "购物状态")
    private String status;

    @ApiModelProperty(value = "更改状态")
    private String modifyStatus;

    @ApiModelProperty(value = "是否要查询产品等级")
    private String gradeFlag;

    @ApiModelProperty(value = "移除原因")
    private String removeReason;

    @ApiModelProperty(value = "产品code")
    private List<String> productCodeList;

    @ApiModelProperty(value = "管家-虚拟物流区Code，买手-物流区code")
    /**虚拟物流区Code**/
    private String lgcsCode;

    @ApiModelProperty(value = "2-代表管家，3-买手")
    /** 2-代表管家，3-买手**/
    private String buyersType;

    @ApiModelProperty(value = "购物车ID")
    private String carID;

    @ApiModelProperty(value = "购物车明细ID")
    private List<String> carDetailID;

    @ApiModelProperty(value = "订单列表标志  1为提交订单")
    private String isOrderFlag;

    @ApiModelProperty(value = "产品信息")
    private List<IBA2141110RsBean> productList;

    /**
     * 以下字段给app产品列表购买使用*
     */
    @ApiModelProperty(value = "产品code")
    private String productCode;

    @ApiModelProperty(value = "productNum")
    private String productNum;

    @ApiModelProperty(value = "productPrice")
    private String productPrice;

    public String getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(String buyersId) {
        this.buyersId = buyersId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGradeFlag() {
        return gradeFlag;
    }

    public void setGradeFlag(String gradeFlag) {
        this.gradeFlag = gradeFlag;
    }

    public String getRemoveReason() {
        return removeReason;
    }

    public void setRemoveReason(String removeReason) {
        this.removeReason = removeReason;
    }

    public List<String> getProductCodeList() {
        return productCodeList;
    }

    public void setProductCodeList(List<String> productCodeList) {
        this.productCodeList = productCodeList;
    }

    public String getModifyStatus() {
        return modifyStatus;
    }

    public void setModifyStatus(String modifyStatus) {
        this.modifyStatus = modifyStatus;
    }

    public String getBuyersType() {
        return buyersType;
    }

    public void setBuyersType(String buyersType) {
        this.buyersType = buyersType;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public List<String> getCarDetailID() {
        return carDetailID;
    }

    public void setCarDetailID(List<String> carDetailID) {
        this.carDetailID = carDetailID;
    }

    public String getIsOrderFlag() {
        return isOrderFlag;
    }

    public void setIsOrderFlag(String isOrderFlag) {
        this.isOrderFlag = isOrderFlag;
    }

    public List<IBA2141110RsBean> getProductList() {
        return productList;
    }

    public void setProductList(List<IBA2141110RsBean> productList) {
        this.productList = productList;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }
}
