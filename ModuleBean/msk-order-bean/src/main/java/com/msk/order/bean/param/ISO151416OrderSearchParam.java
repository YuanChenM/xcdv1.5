package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestPageParam;
import com.msk.order.entity.SoOrder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/8.
 */
public class ISO151416OrderSearchParam extends BaseRestPageParam {

    /**买家ID*/
    private String buyersId;
    /**买家Code*/
    private String buyersCode;

    /**卖家编码*/
    private String sellerCode;

    /**直销员*/
    private String sellers;

    /** 订单类型，1:分销,2:第三方,3:大促会，多选时逗号分隔。 */
    private String orderType;

    /** 1:新建,2:待付款,3:已付款,4:待审核,5:已审核,6:待分销,7:分销中,8:已确认,9:待发货,10:部分发货,11:部分收货,12:全部发货,13:全部收货,14:分销失败
     多选时逗号分隔。 */
    private String orderStatus;

    /** 订单时间开始 */
    private Date orderTimeFrom;

    /** 订单时间截止 */
    private Date orderTimeTo;

    /** 订单来源,系统编码 */
    private String orderSource;

    /** 订单区域，多选时逗号分隔 */
    private String districtCode;

    /** 付款类型，1:在线支付 2:线下支付  */
    private Integer paymentType;

    /** 订单金额下限 */
    private BigDecimal orderAmountMin;

    /** 订单金额上限 */
    private BigDecimal orderAmountMax;

    /** 订单等级，1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单
     多选时逗号分隔。 */
    private String orderLevel;

    /** 是否开票 */
    private Integer needInvoice;

    /** 退货标志，0.无退货,1.整单退货,2:部分退货，多选时逗号分隔。 */
    private String returnFlg;

    /** 是否自配送,0:否，1:是 */
    private Integer selfDeliveryFlg;

    /** 是否分批发货，0:否，1:是 */
    private Integer splitDeliveryFlg;

    /** 订单员 */
    private String orderTaker;

    /** 指定订单列表 */
    private List<SoOrder> orders;

    /** 删除标志（是否回收站订单,0：否，1:是） */
    private Integer delFlg;

    public Integer getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderTimeFrom() {
        return orderTimeFrom;
    }

    public void setOrderTimeFrom(Date orderTimeFrom) {
        this.orderTimeFrom = orderTimeFrom;
    }

    public Date getOrderTimeTo() {
        return orderTimeTo;
    }

    public void setOrderTimeTo(Date orderTimeTo) {
        this.orderTimeTo = orderTimeTo;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getOrderAmountMin() {
        return orderAmountMin;
    }

    public void setOrderAmountMin(BigDecimal orderAmountMin) {
        this.orderAmountMin = orderAmountMin;
    }

    public BigDecimal getOrderAmountMax() {
        return orderAmountMax;
    }

    public void setOrderAmountMax(BigDecimal orderAmountMax) {
        this.orderAmountMax = orderAmountMax;
    }

    public String getOrderLevel() {
        return orderLevel;
    }

    public void setOrderLevel(String orderLevel) {
        this.orderLevel = orderLevel;
    }

    public Integer getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(Integer needInvoice) {
        this.needInvoice = needInvoice;
    }

    public String getReturnFlg() {
        return returnFlg;
    }

    public void setReturnFlg(String returnFlg) {
        this.returnFlg = returnFlg;
    }

    public Integer getSelfDeliveryFlg() {
        return selfDeliveryFlg;
    }

    public void setSelfDeliveryFlg(Integer selfDeliveryFlg) {
        this.selfDeliveryFlg = selfDeliveryFlg;
    }

    public Integer getSplitDeliveryFlg() {
        return splitDeliveryFlg;
    }

    public void setSplitDeliveryFlg(Integer splitDeliveryFlg) {
        this.splitDeliveryFlg = splitDeliveryFlg;
    }

    public String getOrderTaker() {
        return orderTaker;
    }

    public void setOrderTaker(String orderTaker) {
        this.orderTaker = orderTaker;
    }

    public List<SoOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<SoOrder> orders) {
        this.orders = orders;
    }

    public String getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(String buyersId) {
        this.buyersId = buyersId;
    }

    public String getBuyersCode() {
        return buyersCode;
    }

    public void setBuyersCode(String buyersCode) {
        this.buyersCode = buyersCode;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSellers() {
        return sellers;
    }

    public void setSellers(String sellers) {
        this.sellers = sellers;
    }
}
