package com.msk.buyers.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.ByBuyerRecAddr;

import java.util.List;

/**
 * BY121307Bean.
 *
 * @author yuan_chen
 */
public class BY121307Bean extends BaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /** 买家基本信息*/
    private IBY121202RsParam buyerBasicInfo;
    /** 买家证照信息*/
    private BuyerLicenceBean buyerLicence;
    /** 买家收货地址信息*/
    private List<ByBuyerRecAddr> buyerRecAddrList;
    /** 画面选中的收货时间 */
    private String[] buyerRecTime;
    /** 画面选中的销售对象 */
    private String[] buyerSalestarget;
    /** 画面选中的销售产品 */
    private String[] buyerPdCla;
    /**画面选中的销售二级 */
    private String[] buyerPdMac;

    /**
     * Getter method for property <tt>buyerBasicInfo</tt>.
     *
     * @return property value of buyerBasicInfo
     */
    public IBY121202RsParam getBuyerBasicInfo() {
        return buyerBasicInfo;
    }

    /**
     * Setter method for property <tt>buyerBasicInfo</tt>.
     *
     * @param buyerBasicInfo value to be assigned to property buyerBasicInfo
     */
    public void setBuyerBasicInfo(IBY121202RsParam buyerBasicInfo) {
        this.buyerBasicInfo = buyerBasicInfo;
    }

    /**
     * Getter method for property <tt>buyerRecTime</tt>.
     *
     * @return property value of buyerRecTime
     */
    public String[] getBuyerRecTime() {
        return buyerRecTime;
    }

    /**
     * Setter method for property <tt>buyerRecTime</tt>.
     *
     * @param buyerRecTime value to be assigned to property buyerRecTime
     */
    public void setBuyerRecTime(String[] buyerRecTime) {
        this.buyerRecTime = buyerRecTime;
    }

    /**
     * Getter method for property <tt>buyerSalestarget</tt>.
     *
     * @return property value of buyerSalestarget
     */
    public String[] getBuyerSalestarget() {
        return buyerSalestarget;
    }

    /**
     * Setter method for property <tt>buyerSalestarget</tt>.
     *
     * @param buyerSalestarget value to be assigned to property buyerSalestarget
     */
    public void setBuyerSalestarget(String[] buyerSalestarget) {
        this.buyerSalestarget = buyerSalestarget;
    }

    /**
     * Getter method for property <tt>buyerPdCla</tt>.
     *
     * @return property value of buyerPdCla
     */
    public String[] getBuyerPdCla() {
        return buyerPdCla;
    }

    /**
     * Setter method for property <tt>buyerPdCla</tt>.
     *
     * @param buyerPdCla value to be assigned to property buyerPdCla
     */
    public void setBuyerPdCla(String[] buyerPdCla) {
        this.buyerPdCla = buyerPdCla;
    }

    /**
     * Getter method for property <tt>buyerLicence</tt>.
     *
     * @return property value of buyerLicence
     */
    public BuyerLicenceBean getBuyerLicence() {
        return buyerLicence;
    }

    public void setBuyerLicence(BuyerLicenceBean buyerLicence) {
        this.buyerLicence = buyerLicence;
    }

    /**
     * Getter method for property <tt>buyerRecAddrList</tt>.
     *
     * @return property value of buyerRecAddrList
     */
    public List<ByBuyerRecAddr> getBuyerRecAddrList() {
        return buyerRecAddrList;
    }

    /**
     * Setter method for property <tt>buyerRecAddrList</tt>.
     *
     * @param buyerRecAddrList value to be assigned to property buyerRecAddrList
     */
    public void setBuyerRecAddrList(List<ByBuyerRecAddr> buyerRecAddrList) {
        this.buyerRecAddrList = buyerRecAddrList;
    }
    /**
     * Getter method for property <tt>buyerPdMac</tt>.
     *
     * @return property value of buyerPdMac
     */
    public String[] getBuyerPdMac() { return buyerPdMac; }
    /**
     * Setter method for property <tt>buyerPdMac</tt>.
     *
     * @param buyerPdMac value to be assigned to property buyerPdMac
     */
    public void setBuyerPdMac(String[] buyerPdMac) { this.buyerPdMac = buyerPdMac; }
}
