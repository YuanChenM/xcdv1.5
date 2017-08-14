package com.msk.order.bean.result;


import com.msk.order.entity.SoReturnDetail;

import java.math.BigDecimal;

/**
 * ISO151506_退货单详情
 * Created by wang_shuai on 2016/8/3.
 */
public class SO15150704BeanResult extends SoReturnDetail {
    private String returnOrderGoodsId;// 货品编码

    private String goodsName; // 货品名称

    private String goodsStandard; // 货品规格

    private String packStandard; // 包装规格

    private String bulkOne;// 单件体积(/箱)

    private String unitPrice; // 单价(/箱)

    private String goodsNumber; // 数量

    private String goodsWeight; // 重量

    private String goodsBulkOnes; // 体积(箱)

    private String oneAllMoney; // 单种商品总价格

    private String allMoney; // 总价格

    private BigDecimal pdPrice;// 单价

    private BigDecimal orderQty;//数量

    public String getReturnOrderGoodsId() {
        return returnOrderGoodsId;
    }

    public void setReturnOrderGoodsId(String returnOrderGoodsId) {
        this.returnOrderGoodsId = returnOrderGoodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsStandard() {
        return goodsStandard;
    }

    public void setGoodsStandard(String goodsStandard) {
        this.goodsStandard = goodsStandard;
    }

    public String getPackStandard() {
        return packStandard;
    }

    public void setPackStandard(String packStandard) {
        this.packStandard = packStandard;
    }

    public String getBulkOne() {
        return bulkOne;
    }

    public void setBulkOne(String bulkOne) {
        this.bulkOne = bulkOne;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsBulkOnes() {
        return goodsBulkOnes;
    }

    public void setGoodsBulkOnes(String goodsBulkOnes) {
        this.goodsBulkOnes = goodsBulkOnes;
    }

    public String getOneAllMoney() {
        return oneAllMoney;
    }

    public void setOneAllMoney(String oneAllMoney) {
        this.oneAllMoney = oneAllMoney;
    }

    public String getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(String allMoney) {
        this.allMoney = allMoney;
    }

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }
}
