package com.msk.ds.bean;

import com.hoperun.core.utils.StringUtil;
import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 收货入库通知单bean
 */
public class SC182101Bean extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 发货入库管理ID */
    private java.lang.Long deliveryStockId;
    /** 分销月度 */
    private java.lang.String distMonth;
    /** 物流区编号 */
    private java.lang.String lgcsCode;
    /** 供应商编号 */
    private java.lang.String suppCode;
    /** 半旬码 */
    private java.lang.String halfCode;
    /** 发货入库状态 */
    private java.lang.String deliveryStockStatus;
    /** 发货仓库地点 */
    private java.lang.String deliveryWarehouseAddr;
    /** 发货责任人 */
    private java.lang.String deliveryResponName;
    /** 发货责任人联系电话 */
    private java.lang.String deliveryResponTel;
    /** 发货执行人 */
    private java.lang.String deliveryExecuteName;
    /** 发货执行人联系电话 */
    private java.lang.String deliveryExecuteTel;
    /** 运输单位名称 */
    private java.lang.String transportUnitName;
    /** 运输单位责任人 */
    private java.lang.String transportUnitResponName;
    /** 运输单位责任人联系电话 */
    private java.lang.String transportUnitResponTel;
    /** 运输单位执行人 */
    private java.lang.String transportUnitExecuteName;
    /** 运输单位执行人联系电话 */
    private java.lang.String transportUnitExecuteTel;
    /** 发货备注 */
    private java.lang.String deliveryMemo;
    /** 收货备注 */
    private java.lang.String stockMemo;
    /** 发货入库时间要求 */
    private java.lang.String deliveryStockTimeReq;
    /** 运抵仓库地址 */
    private java.lang.String stockAddr;
    /** 运抵仓库仓管负责人 */
    private java.lang.String stockResponName;
    /** 运抵仓库仓管负责人联系电话 */
    private java.lang.String stockResponTel;
    /** 运抵仓库验收负责人 */
    private java.lang.String stockExecuteName;
    /** 运抵仓库验收负责人联系电话 */
    private java.lang.String stockExecuteTel;
    /**实际到货时间 */
    private String deliveryReceiveStockTime;

    private java.lang.String halfName;

    private java.lang.String lgcsName;

    private java.lang.String suppName;

    private java.lang.String statusName;

    private java.lang.String[] actualNums;

    private java.lang.String[] productCodes;

    private java.math.BigDecimal[] receiveActualNums;

    /**计划发货箱数 */
    private BigDecimal sendPlanNum;
    /**实际发货箱数 */
    private BigDecimal sendActualNum;
    /**实际收货箱数 */
    private BigDecimal receiveNum;
    /** 计划发货重量（KG）*/
    private BigDecimal sendPlanQty;
    /** 实际发货重量（KG）*/
    private BigDecimal sendActualQty;
    /** 实际收货重量（KG）*/
    private BigDecimal recriveQty;
    /** 差异箱数 */
    private BigDecimal differNum;
    /** 差异重量（KG）*/
    private BigDecimal differQty;

    /**计划发货箱数合计*/
    private BigDecimal currentSendPlanNum;

    /**实际发货箱数合计 */
    private BigDecimal currentSendActualNum;

    /**实际收货箱数合计 */
    private BigDecimal currentReceiveNum;
    /** 计划发货重量（KG）合计*/
    private BigDecimal currentSendPlanQty;


    /** 实际发货重量（KG）合计*/
    private BigDecimal currentSendActualQty;
    /** 实际收货重量（KG）合计*/
    private BigDecimal currentRecriveQty;
    /** 差异箱数 合计*/
    private BigDecimal currentDifferNum;
    /** 差异重量（KG）合计*/
    private BigDecimal currentDifferQty;

    /**计划发货箱数总计*/
    private BigDecimal totalSendPlanNum;

    /**实际发货箱数总计 */
    private BigDecimal totalSendActualNum;

    /**实际收货箱数总计 */
    private BigDecimal totalReceiveNum;
    /** 计划发货重量（KG）总计*/
    private BigDecimal totalSendPlanQty;


    /** 实际发货重量（KG）总计*/
    private BigDecimal totalSendActualQty;
    /** 实际收货重量（KG）总计*/
    private BigDecimal totalRecriveQty;
    /** 差异箱数 总计*/
    private BigDecimal totalDifferNum;
    /** 差异重量（KG）总计*/
    private BigDecimal totalDifferQty;

    //发货次数
    private Long deliveryNums;

    /** 生产商名称 */
    private String manuName;

    /** 入库单来源 */
    private String sourceFlg;

    /** 采供链发货入库单Id */
    private String sscDeliveryStockId;

    /**
     * <p>默认构造函数。</p>
     */
    public SC182101Bean() {

    }

    /**
     * <p>发货入库管理ID。</p>
     *
     * @return the 发货入库管理ID
     */
    public java.lang.Long getDeliveryStockId() {
        return deliveryStockId;
    }

    /**
     * <p>发货入库管理ID。</p>
     *
     * @param deliveryStockId 发货入库管理ID。
     */
    public void setDeliveryStockId(java.lang.Long deliveryStockId) {
        this.deliveryStockId = deliveryStockId;
    }

    /**
     * <p>分销月度。</p>
     *
     * @return the 分销月度
     */
    public java.lang.String getDistMonth() {
        return distMonth;
    }

    /**
     * <p>分销月度。</p>
     *
     * @param distMonth 分销月度。
     */
    public void setDistMonth(java.lang.String distMonth) {
        this.distMonth = distMonth;
    }

    /**
     * <p>物流区编号。</p>
     *
     * @return the 物流区编号
     */
    public java.lang.String getLgcsCode() {
        return lgcsCode;
    }

    /**
     * <p>物流区编号。</p>
     *
     * @param lgcsCode 物流区编号。
     */
    public void setLgcsCode(java.lang.String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    /**
     * <p>供应商编号。</p>
     *
     * @return the 供应商编号
     */
    public java.lang.String getSuppCode() {
        return suppCode;
    }

    /**
     * <p>供应商编号。</p>
     *
     * @param suppCode 供应商编号。
     */
    public void setSuppCode(java.lang.String suppCode) {
        this.suppCode = suppCode;
    }

    /**
     * <p>半旬码。</p>
     *
     * @return the 半旬码
     */
    public String getHalfCode() {
        return halfCode;
    }
    public boolean isLeapYear(int year){
        if(year % 100 == 0){
            if(year % 400 == 0){
                return true;
            }
        }else {
            if(year % 4 == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * <p>半旬码。</p>
     *
     * @param halfCode 半旬码。
     */
    public void setHalfCode(java.lang.String halfCode) {
        this.halfCode = halfCode;
    }

    /**
     * <p>发货入库状态。</p>
     *
     * @return the 发货入库状态
     */
    public java.lang.String getDeliveryStockStatus() {
        return deliveryStockStatus;
    }

    /**
     * <p>发货入库状态。</p>
     *
     * @param deliveryStockStatus 发货入库状态。
     */
    public void setDeliveryStockStatus(java.lang.String deliveryStockStatus) {
        this.deliveryStockStatus = deliveryStockStatus;
    }

    /**
     * <p>发货仓库地点。</p>
     *
     * @return the 发货仓库地点
     */
    public java.lang.String getDeliveryWarehouseAddr() {
        return deliveryWarehouseAddr;
    }

    /**
     * <p>发货仓库地点。</p>
     *
     * @param deliveryWarehouseAddr 发货仓库地点。
     */
    public void setDeliveryWarehouseAddr(java.lang.String deliveryWarehouseAddr) {
        this.deliveryWarehouseAddr = deliveryWarehouseAddr;
    }

    /**
     * <p>发货责任人。</p>
     *
     * @return the 发货责任人
     */
    public java.lang.String getDeliveryResponName() {
        return deliveryResponName;
    }

    /**
     * <p>发货责任人。</p>
     *
     * @param deliveryResponName 发货责任人。
     */
    public void setDeliveryResponName(java.lang.String deliveryResponName) {
        this.deliveryResponName = deliveryResponName;
    }

    /**
     * <p>发货责任人联系电话。</p>
     *
     * @return the 发货责任人联系电话
     */
    public java.lang.String getDeliveryResponTel() {
        return deliveryResponTel;
    }

    /**
     * <p>发货责任人联系电话。</p>
     *
     * @param deliveryResponTel 发货责任人联系电话。
     */
    public void setDeliveryResponTel(java.lang.String deliveryResponTel) {
        this.deliveryResponTel = deliveryResponTel;
    }

    /**
     * <p>发货执行人。</p>
     *
     * @return the 发货执行人
     */
    public java.lang.String getDeliveryExecuteName() {
        return deliveryExecuteName;
    }

    /**
     * <p>发货执行人。</p>
     *
     * @param deliveryExecuteName 发货执行人。
     */
    public void setDeliveryExecuteName(java.lang.String deliveryExecuteName) {
        this.deliveryExecuteName = deliveryExecuteName;
    }

    /**
     * <p>发货执行人联系电话。</p>
     *
     * @return the 发货执行人联系电话
     */
    public java.lang.String getDeliveryExecuteTel() {
        return deliveryExecuteTel;
    }

    /**
     * <p>发货执行人联系电话。</p>
     *
     * @param deliveryExecuteTel 发货执行人联系电话。
     */
    public void setDeliveryExecuteTel(java.lang.String deliveryExecuteTel) {
        this.deliveryExecuteTel = deliveryExecuteTel;
    }

    /**
     * <p>运输单位名称。</p>
     *
     * @return the 运输单位名称
     */
    public java.lang.String getTransportUnitName() {
        return transportUnitName;
    }

    /**
     * <p>运输单位名称。</p>
     *
     * @param transportUnitName 运输单位名称。
     */
    public void setTransportUnitName(java.lang.String transportUnitName) {
        this.transportUnitName = transportUnitName;
    }

    /**
     * <p>运输单位责任人。</p>
     *
     * @return the 运输单位责任人
     */
    public java.lang.String getTransportUnitResponName() {
        return transportUnitResponName;
    }

    /**
     * <p>运输单位责任人。</p>
     *
     * @param transportUnitResponName 运输单位责任人。
     */
    public void setTransportUnitResponName(java.lang.String transportUnitResponName) {
        this.transportUnitResponName = transportUnitResponName;
    }

    /**
     * <p>运输单位责任人联系电话。</p>
     *
     * @return the 运输单位责任人联系电话
     */
    public java.lang.String getTransportUnitResponTel() {
        return transportUnitResponTel;
    }

    /**
     * <p>运输单位责任人联系电话。</p>
     *
     * @param transportUnitResponTel 运输单位责任人联系电话。
     */
    public void setTransportUnitResponTel(java.lang.String transportUnitResponTel) {
        this.transportUnitResponTel = transportUnitResponTel;
    }

    /**
     * <p>运输单位执行人。</p>
     *
     * @return the 运输单位执行人
     */
    public java.lang.String getTransportUnitExecuteName() {
        return transportUnitExecuteName;
    }

    /**
     * <p>运输单位执行人。</p>
     *
     * @param transportUnitExecuteName 运输单位执行人。
     */
    public void setTransportUnitExecuteName(java.lang.String transportUnitExecuteName) {
        this.transportUnitExecuteName = transportUnitExecuteName;
    }

    /**
     * <p>运输单位执行人联系电话。</p>
     *
     * @return the 运输单位执行人联系电话
     */
    public java.lang.String getTransportUnitExecuteTel() {
        return transportUnitExecuteTel;
    }

    /**
     * <p>运输单位执行人联系电话。</p>
     *
     * @param transportUnitExecuteTel 运输单位执行人联系电话。
     */
    public void setTransportUnitExecuteTel(java.lang.String transportUnitExecuteTel) {
        this.transportUnitExecuteTel = transportUnitExecuteTel;
    }

    /**
     * <p>发货备注。</p>
     *
     * @return the 发货备注
     */
    public java.lang.String getDeliveryMemo() {
        return deliveryMemo;
    }

    /**
     * <p>发货备注。</p>
     *
     * @param deliveryMemo 发货备注。
     */
    public void setDeliveryMemo(java.lang.String deliveryMemo) {
        this.deliveryMemo = deliveryMemo;
    }

    /**
     * <p>收货备注。</p>
     *
     * @return the 收货备注
     */
    public java.lang.String getStockMemo() {
        return stockMemo;
    }

    /**
     * <p>收货备注。</p>
     *
     * @param stockMemo 收货备注。
     */
    public void setStockMemo(java.lang.String stockMemo) {
        this.stockMemo = stockMemo;
    }

    /**
     * <p>发货入库时间要求。</p>
     *
     * @return the 发货入库时间要求
     */
    public java.lang.String getDeliveryStockTimeReq() {
        return deliveryStockTimeReq;
    }

    /**
     * <p>发货入库时间要求。</p>
     *
     * @param deliveryStockTimeReq 发货入库时间要求。
     */
    public void setDeliveryStockTimeReq(java.lang.String deliveryStockTimeReq) {
        this.deliveryStockTimeReq = deliveryStockTimeReq;
    }

    /**
     * <p>运抵仓库地址。</p>
     *
     * @return the 运抵仓库地址
     */
    public java.lang.String getStockAddr() {
        return stockAddr;
    }

    /**
     * <p>运抵仓库地址。</p>
     *
     * @param stockAddr 运抵仓库地址。
     */
    public void setStockAddr(java.lang.String stockAddr) {
        this.stockAddr = stockAddr;
    }

    /**
     * <p>运抵仓库仓管负责人。</p>
     *
     * @return the 运抵仓库仓管负责人
     */
    public java.lang.String getStockResponName() {
        return stockResponName;
    }

    /**
     * <p>运抵仓库仓管负责人。</p>
     *
     * @param stockResponName 运抵仓库仓管负责人。
     */
    public void setStockResponName(java.lang.String stockResponName) {
        this.stockResponName = stockResponName;
    }

    /**
     * <p>运抵仓库仓管负责人联系电话。</p>
     *
     * @return the 运抵仓库仓管负责人联系电话
     */
    public java.lang.String getStockResponTel() {
        return stockResponTel;
    }

    /**
     * <p>运抵仓库仓管负责人联系电话。</p>
     *
     * @param stockResponTel 运抵仓库仓管负责人联系电话。
     */
    public void setStockResponTel(java.lang.String stockResponTel) {
        this.stockResponTel = stockResponTel;
    }

    /**
     * <p>运抵仓库验收负责人。</p>
     *
     * @return the 运抵仓库验收负责人
     */
    public java.lang.String getStockExecuteName() {
        return stockExecuteName;
    }

    /**
     * <p>运抵仓库验收负责人。</p>
     *
     * @param stockExecuteName 运抵仓库验收负责人。
     */
    public void setStockExecuteName(java.lang.String stockExecuteName) {
        this.stockExecuteName = stockExecuteName;
    }

    /**
     * <p>运抵仓库验收负责人联系电话。</p>
     *
     * @return the 运抵仓库验收负责人联系电话
     */
    public java.lang.String getStockExecuteTel() {
        return stockExecuteTel;
    }

    /**
     * <p>运抵仓库验收负责人联系电话。</p>
     *
     * @param stockExecuteTel 运抵仓库验收负责人联系电话。
     */
    public void setStockExecuteTel(java.lang.String stockExecuteTel) {
        this.stockExecuteTel = stockExecuteTel;
    }

    public String getHalfName() {
        if (!StringUtil.isNullOrEmpty(halfCode) && halfCode.equals("2")) {
            String month = this.getDistMonth();
            String yearn = month.substring(0, 4);
            int yearm = Integer.parseInt(yearn);
            String monthN = month.substring(month.length() - 1, month.length());
            switch (monthN) {
                case "1":
                case "3":
                case "5":
                case "7":
                case "8":
                case "10":
                case "12":
                    halfName = "26-31日";
                    break;
                case "2":
                    String x = isLeapYear(yearm) ? "29" : "28";
                    halfName = "26-" + x + "日";
                    break;
                case "4":
                case "6":
                case "9":
                case "11":
                    halfName = "26-30日";
                    break;
            }
            return halfName;
        }

        return halfName;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public String getSuppName() {
        return suppName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setHalfName(String halfName) {
        this.halfName = halfName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String[] getActualNums() {
        return actualNums;
    }

    public void setActualNums(String[] actualNums) {
        this.actualNums = actualNums;
    }

    public String[] getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(String[] productCodes) {
        this.productCodes = productCodes;
    }

    public Long getDeliveryNums() {
        return deliveryNums;
    }

    public void setDeliveryNums(Long deliveryNums) {
        this.deliveryNums = deliveryNums;
    }

    public BigDecimal[] getReceiveActualNums() {
        return receiveActualNums;
    }

    public void setReceiveActualNums(BigDecimal[] receiveActualNums) {
        this.receiveActualNums = receiveActualNums;
    }

    public BigDecimal getSendPlanNum() {
        return sendPlanNum;
    }

    public void setSendPlanNum(BigDecimal sendPlanNum) {
        this.sendPlanNum = sendPlanNum;
    }

    public BigDecimal getSendActualNum() {
        return sendActualNum;
    }

    public void setSendActualNum(BigDecimal sendActualNum) {
        this.sendActualNum = sendActualNum;
    }

    public BigDecimal getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(BigDecimal receiveNum) {
        this.receiveNum = receiveNum;
    }

    public BigDecimal getSendPlanQty() {
        return sendPlanQty;
    }

    public void setSendPlanQty(BigDecimal sendPlanQty) {
        this.sendPlanQty = sendPlanQty;
    }

    public BigDecimal getSendActualQty() {
        return sendActualQty;
    }

    public void setSendActualQty(BigDecimal sendActualQty) {
        this.sendActualQty = sendActualQty;
    }

    public BigDecimal getRecriveQty() {
        return recriveQty;
    }

    public void setRecriveQty(BigDecimal recriveQty) {
        this.recriveQty = recriveQty;
    }

    public BigDecimal getDifferNum() {
        return differNum;
    }

    public void setDifferNum(BigDecimal differNum) {
        this.differNum = differNum;
    }

    public BigDecimal getDifferQty() {
        return differQty;
    }

    public void setDifferQty(BigDecimal differQty) {
        this.differQty = differQty;
    }

    public String getManuName() {
        return manuName;
    }

    public void setManuName(String manuName) {
        this.manuName = manuName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getCurrentSendPlanNum() {
        return currentSendPlanNum;
    }

    public void setCurrentSendPlanNum(BigDecimal currentSendPlanNum) {
        this.currentSendPlanNum = currentSendPlanNum;
    }

    public BigDecimal getCurrentSendActualNum() {
        return currentSendActualNum;
    }

    public void setCurrentSendActualNum(BigDecimal currentSendActualNum) {
        this.currentSendActualNum = currentSendActualNum;
    }

    public BigDecimal getCurrentReceiveNum() {
        return currentReceiveNum;
    }

    public void setCurrentReceiveNum(BigDecimal currentReceiveNum) {
        this.currentReceiveNum = currentReceiveNum;
    }

    public BigDecimal getCurrentSendPlanQty() {
        return currentSendPlanQty;
    }

    public void setCurrentSendPlanQty(BigDecimal currentSendPlanQty) {
        this.currentSendPlanQty = currentSendPlanQty;
    }

    public BigDecimal getCurrentSendActualQty() {
        return currentSendActualQty;
    }

    public void setCurrentSendActualQty(BigDecimal currentSendActualQty) {
        this.currentSendActualQty = currentSendActualQty;
    }

    public BigDecimal getCurrentRecriveQty() {
        return currentRecriveQty;
    }

    public void setCurrentRecriveQty(BigDecimal currentRecriveQty) {
        this.currentRecriveQty = currentRecriveQty;
    }

    public BigDecimal getCurrentDifferNum() {
        return currentDifferNum;
    }

    public void setCurrentDifferNum(BigDecimal currentDifferNum) {
        this.currentDifferNum = currentDifferNum;
    }

    public BigDecimal getCurrentDifferQty() {
        return currentDifferQty;
    }

    public void setCurrentDifferQty(BigDecimal currentDifferQty) {
        this.currentDifferQty = currentDifferQty;
    }

    public BigDecimal getTotalSendPlanNum() {
        return totalSendPlanNum;
    }

    public void setTotalSendPlanNum(BigDecimal totalSendPlanNum) {
        this.totalSendPlanNum = totalSendPlanNum;
    }

    public BigDecimal getTotalSendActualNum() {
        return totalSendActualNum;
    }

    public void setTotalSendActualNum(BigDecimal totalSendActualNum) {
        this.totalSendActualNum = totalSendActualNum;
    }

    public BigDecimal getTotalReceiveNum() {
        return totalReceiveNum;
    }

    public void setTotalReceiveNum(BigDecimal totalReceiveNum) {
        this.totalReceiveNum = totalReceiveNum;
    }

    public BigDecimal getTotalSendPlanQty() {
        return totalSendPlanQty;
    }

    public void setTotalSendPlanQty(BigDecimal totalSendPlanQty) {
        this.totalSendPlanQty = totalSendPlanQty;
    }

    public BigDecimal getTotalSendActualQty() {
        return totalSendActualQty;
    }

    public void setTotalSendActualQty(BigDecimal totalSendActualQty) {
        this.totalSendActualQty = totalSendActualQty;
    }

    public BigDecimal getTotalRecriveQty() {
        return totalRecriveQty;
    }

    public void setTotalRecriveQty(BigDecimal totalRecriveQty) {
        this.totalRecriveQty = totalRecriveQty;
    }

    public BigDecimal getTotalDifferNum() {
        return totalDifferNum;
    }

    public void setTotalDifferNum(BigDecimal totalDifferNum) {
        this.totalDifferNum = totalDifferNum;
    }

    public BigDecimal getTotalDifferQty() {
        return totalDifferQty;
    }

    public void setTotalDifferQty(BigDecimal totalDifferQty) {
        this.totalDifferQty = totalDifferQty;
    }

    public String getDeliveryReceiveStockTime() {
        return deliveryReceiveStockTime;
    }

    public void setDeliveryReceiveStockTime(String deliveryReceiveStockTime) {
        this.deliveryReceiveStockTime = deliveryReceiveStockTime;
    }

    public String getSourceFlg() {
        return sourceFlg;
    }

    public void setSourceFlg(String sourceFlg) {
        this.sourceFlg = sourceFlg;
    }

    public String getSscDeliveryStockId() {
        return sscDeliveryStockId;
    }

    public void setSscDeliveryStockId(String sscDeliveryStockId) {
        this.sscDeliveryStockId = sscDeliveryStockId;
    }
}
