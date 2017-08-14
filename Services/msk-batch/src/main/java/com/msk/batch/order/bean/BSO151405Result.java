package com.msk.batch.order.bean;

import com.msk.core.entity.BaseEntity;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by wang_shuai on 2016/4/11.
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"returnId","returnCode","orderId","buyerCode","buyerType","buyerName","buyerId","sellerCode","ver","returnTime","returnType","returnDetailList"})
public class BSO151405Result extends BaseEntity {
    /** 退货单ID */
    private Long returnId;
    /** 退货单编码 */
    private String returnCode;
    /** 订单ID */
    private Long orderId;
    /** 买家编码冗余 */
    private String buyerCode;
    /** 买家类型*/
    private Integer buyerType;
    /** 买家名称,冗余 */
    private String buyerName;
    /**退货人ID*/
    private String buyerId;
    /** 卖家编码,冗余 */
    private String sellerCode;
    /**退货时间*/
    private String returnTime;
    /** 退货单类型-CodeMaster
     1-迟收；2-拒收；3-退货 */
    private String returnType;
    /**退货产品信息*/
    private List<BSO151405DetailResult> returnDetailList;

    @Override
    @XmlElement(name = "VER",defaultValue = "")
    public Integer getVer() {
        return super.getVer();
    }

    @XmlElement(name = "RETURNID",defaultValue = "")
    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }
    @XmlElement(name = "RETURNCODE",defaultValue = "")
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    @XmlElement(name = "ORDERID",defaultValue = "")
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    @XmlElement(name = "TARGETCOMPANY",defaultValue = "")
    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }
    @XmlElement(name = "COMPANYTYPE",defaultValue = "")
    public Integer getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(Integer buyerType) {
        this.buyerType = buyerType;
    }
    @XmlElement(name = "BUYERSNAME",defaultValue = "")
    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    @XmlElement(name = "BUYERSID",defaultValue = "")
    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
    @XmlElement(name = "CONSIGNEE",defaultValue = "")
    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }
    @XmlElement(name = "REQUESTEDDATE",defaultValue = "")
    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
    @XmlElement(name = "RETURNTYPE",defaultValue = "")
    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
    @XmlElementWrapper(name="LINES")
    @XmlElement(name="LINE")
    public List<BSO151405DetailResult> getReturnDetailList() {
        return returnDetailList;
    }

    public void setReturnDetailList(List<BSO151405DetailResult> returnDetailList) {
        this.returnDetailList = returnDetailList;
    }
}
