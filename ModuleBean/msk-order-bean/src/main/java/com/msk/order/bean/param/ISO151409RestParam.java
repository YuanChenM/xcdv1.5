package com.msk.order.bean.param;
import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/8/19.
 */
public class ISO151409RestParam extends BaseParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 4217991662303194889L;
    /** 每页数量 */
   private Integer pageCount;
    /** 查询页数 */
    private Integer pageNo;
    /** 买家编码 */
    private String buyersCode;
    /** 买家Id */
    @Required(required=true,message="买家Id 不能为空！")
    private String buyersId;
    /** 卖家编码 */
    private String sellerCode;
    /** 退货单类型,1:分销,2:第三方,3:大促会，多选时逗号分隔 */
    private String returnType;
    /** 退货单状态 多选时逗号分隔 */
    private String returnStatus;
    /** 退货单申请时间开始 */
    private String  returnTimeFrom;
    /** 退货单申请时间截止 */
    private String  returnTimeTo;
    /** 退货单来源 */
    private String returnSource;
    /** 退货单区域 */
    private String districtCode;
    /** 退款方式 */
    private Integer refundMode;
    /** 退货单金额下限 */
    private BigDecimal returnAmountMin;
    /** 退货单金额上限 */
    private BigDecimal returnAmountMax;
    /** 是否已开发票 */
    private Integer isInvoice;
    /** 是否自配送 */
    private Integer selfDeliveryFlg;
    /** 直销员 */
    private String sellers;
    /** 订单员 */
    private String orderTaker;
    /** 订单明细类型 */
    private String orderDetailType;
    /** 订单等级 */
    private String orderDetailLevel;
    /** 指定退货单列表 */
    private List<ISO151409RestReturnListParam> returnList;


    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getBuyersCode() {
        return buyersCode;
    }

    public void setBuyersCode(String buyersCode) {
        this.buyersCode = buyersCode;
    }

    public String getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(String buyersId) {
        this.buyersId = buyersId;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnTimeTo() {
        return returnTimeTo;
    }

    public void setReturnTimeTo(String returnTimeTo) {
        this.returnTimeTo = returnTimeTo;
    }

    public String getReturnTimeFrom() {
        return returnTimeFrom;
    }

    public void setReturnTimeFrom(String returnTimeFrom) {
        this.returnTimeFrom = returnTimeFrom;
    }

    public String getReturnSource() {
        return returnSource;
    }

    public void setReturnSource(String returnSource) {
        this.returnSource = returnSource;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Integer getRefundMode() {
        return refundMode;
    }

    public void setRefundMode(Integer refundMode) {
        this.refundMode = refundMode;
    }

    public BigDecimal getReturnAmountMin() {
        return returnAmountMin;
    }

    public void setReturnAmountMin(BigDecimal returnAmountMin) {
        this.returnAmountMin = returnAmountMin;
    }

    public BigDecimal getReturnAmountMax() {
        return returnAmountMax;
    }

    public void setReturnAmountMax(BigDecimal returnAmountMax) {
        this.returnAmountMax = returnAmountMax;
    }

    public Integer getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Integer isInvoice) {
        this.isInvoice = isInvoice;
    }

    public Integer getSelfDeliveryFlg() {
        return selfDeliveryFlg;
    }

    public void setSelfDeliveryFlg(Integer selfDeliveryFlg) {
        this.selfDeliveryFlg = selfDeliveryFlg;
    }

    public String getSellers() {
        return sellers;
    }

    public void setSellers(String sellers) {
        this.sellers = sellers;
    }

    public String getOrderTaker() {
        return orderTaker;
    }

    public void setOrderTaker(String orderTaker) {
        this.orderTaker = orderTaker;
    }

    public String getOrderDetailType() {
        return orderDetailType;
    }

    public void setOrderDetailType(String orderDetailType) {
        this.orderDetailType = orderDetailType;
    }

    public String getOrderDetailLevel() {
        return orderDetailLevel;
    }

    public void setOrderDetailLevel(String orderDetailLevel) {
        this.orderDetailLevel = orderDetailLevel;
    }

    public List<ISO151409RestReturnListParam> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<ISO151409RestReturnListParam> returnList) {
        this.returnList = returnList;
    }
}
