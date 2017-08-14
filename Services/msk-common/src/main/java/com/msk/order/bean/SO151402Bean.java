package com.msk.order.bean;

import com.msk.core.entity.SoOrderDetail;
import com.msk.core.entity.SoOrderDetailAvailability;
import com.msk.core.entity.SoOrderShipDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * 分销订单(订购产品)明细
 * OM141102Bean
 *
 * @author jiang_nan
 * @version 1.0
 *          修改者 gyh
 *          日期 2015.12.31
 **/
public class SO151402Bean extends SoOrderDetail {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** 订单总金额 */
    private BigDecimal pdPriceAll;
    /** 外包装规格 */
    private String normsOut;
    /**明细状态*/
    private String detailStatusName;
    /**退货标志*/
    private String returnFlg;
    /**分批发货*/
    private String splitDeliveryFlgName;
    /**价盘名称*/
    private String priceCycleName;
    /**订单类型*/
    private String orderType;
    /**订单状态*/
    private String orderStatus;
    /** 订单明细供应商表 */
    private List<SoOrderShipDetail> soOrderShipDetails;


    /**
     * Get the soOrderDetailAvailabilities.
     *
     * @return soOrderDetailAvailabilities
     *
     * @author gyh
     */
    public List<SoOrderShipDetail> getSoOrderShipDetails() {
        return soOrderShipDetails;
    }

    public void setSoOrderShipDetails(List<SoOrderShipDetail> soOrderShipDetails) {
        this.soOrderShipDetails = soOrderShipDetails;
    }

    /**
     * Get the normsOut.
     *
     * @return normsOut
     *
     * @author gyh
     */
    public String getNormsOut() {
        return this.normsOut;
    }

    /**
     * Set the normsOut.
     *
     * @param normsOut normsOut
     *
     * @author gyh
     */
    public void setNormsOut(String normsOut) {
        this.normsOut = normsOut;
    }

    /**
     * Get the pdPriceAll.
     *
     * @return pdPriceAll
     *
     * @author gyh
     */
    public BigDecimal getPdPriceAll() {
        return this.pdPriceAll;
    }

    /**
     * Set the pdPriceAll.
     *
     * @param pdPriceAll pdPriceAll
     *
     * @author gyh
     */
    public void setPdPriceAll(BigDecimal pdPriceAll) {
        this.pdPriceAll = pdPriceAll;
    }

    /**
     * Get the detailStatusName.
     *
     * @return detailStatusName
     *
     * @author gyh
     */
    public String getDetailStatusName() {
        return this.detailStatusName;
    }

    /**
     * Set the detailStatusName.
     *
     * @param detailStatusName detailStatusName
     *
     * @author gyh
     */
    public void setDetailStatusName(String detailStatusName) {
        this.detailStatusName = detailStatusName;
    }

    /**
     * Getter method for property <tt>returnFlg</tt>.
     *
     * @return property value of returnFlg
     */
    public String getReturnFlg() {
        return returnFlg;
    }

    /**
     * Setter method for property <tt>returnFlg</tt>.
     *
     * @param returnFlg value to be assigned to property returnFlg
     */
    public void setReturnFlg(String returnFlg) {
        this.returnFlg = returnFlg;
    }

    /**
     * Getter method for property <tt>splitDeliveryFlgName</tt>.
     *
     * @return property value of splitDeliveryFlgName
     */
    public String getSplitDeliveryFlgName() {
        return splitDeliveryFlgName;
    }

    /**
     * Setter method for property <tt>splitDeliveryFlgName</tt>.
     *
     * @param splitDeliveryFlgName value to be assigned to property splitDeliveryFlgName
     */
    public void setSplitDeliveryFlgName(String splitDeliveryFlgName) {
        this.splitDeliveryFlgName = splitDeliveryFlgName;
    }

    /**
     * Getter method for property <tt>priceCycleName</tt>.
     *
     * @return property value of priceCycleName
     */
    public String getPriceCycleName() {
        return priceCycleName;
    }

    /**
     * Setter method for property <tt>priceCycleName</tt>.
     *
     * @param priceCycleName value to be assigned to property priceCycleName
     */
    public void setPriceCycleName(String priceCycleName) {
        this.priceCycleName = priceCycleName;
    }

    /**
     * Getter method for property <tt>orderType</tt>.
     *
     * @return property value of orderType
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Setter method for property <tt>orderType</tt>.
     *
     * @param orderType value to be assigned to property orderType
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * Getter method for property <tt>orderStatus</tt>.
     *
     * @return property value of orderStatus
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Setter method for property <tt>orderStatus</tt>.
     *
     * @param orderStatus value to be assigned to property orderStatus
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }



}
