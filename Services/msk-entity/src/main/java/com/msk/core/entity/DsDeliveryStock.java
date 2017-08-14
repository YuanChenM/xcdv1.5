/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ds_delivery_stock对应的DsDeliveryStock。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class DsDeliveryStock extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 发货入库管理ID */
    private Long deliveryStockId;
    /** 分销月度 */
    private String distMonth;
    /** 物流区编号 */
    private String lgcsCode;
    /** 供应商编号 */
    private String suppCode;
    /** 半旬码 */
    private String halfCode;
    /** 1:未申请,2:申请中,3:已确认,4:待收货,5:已收货 */
    private String deliveryStockStatus;
    /** 实际到货时间 */
    private String deliveryReceiveStockTime;
    /** 发货仓库地点 */
    private String deliveryWarehouseAddr;
    /** 发货责任人 */
    private String deliveryResponName;
    /** 发货责任人联系电话 */
    private String deliveryResponTel;
    /** 发货执行人 */
    private String deliveryExecuteName;
    /** 发货执行人联系电话 */
    private String deliveryExecuteTel;
    /** 运输单位名称 */
    private String transportUnitName;
    /** 运输单位责任人 */
    private String transportUnitResponName;
    /** 运输单位责任人联系电话 */
    private String transportUnitResponTel;
    /** 运输单位执行人 */
    private String transportUnitExecuteName;
    /** 运输单位执行人联系电话 */
    private String transportUnitExecuteTel;
    /** 发货入库时间要求 */
    private String deliveryStockTimeReq;
    /** 运抵仓库地址 */
    private String stockAddr;
    /** 运抵仓库仓管负责人 */
    private String stockResponName;
    /** 运抵仓库仓管负责人联系电话 */
    private String stockResponTel;
    /** 运抵仓库验收负责人 */
    private String stockExecuteName;
    /** 运抵仓库验收负责人联系电话 */
    private String stockExecuteTel;
    /** 发货备注 */
    private String deliveryMemo;
    /** 收货备注 */
    private String stockMemo;
    /** 1代表平台供应链,2代表卖家采供链 */
    private String sourceFlg;
    /** 采供链发货入库单Id */
    private Long sscDeliveryStockId;
    /** 生产商Id */
    private String pdProduceerId;
    /** 生产商名称 */
    private String pdProduceerName;
    /**
     * <p>默认构造函数。</p>
     */
    public DsDeliveryStock() {

    }

    /**
     * <p>发货入库管理ID。</p>
     *
     * @return the 发货入库管理ID
     */
    public Long getDeliveryStockId() {
        return deliveryStockId;
    }

    /**
     * <p>发货入库管理ID。</p>
     *
     * @param deliveryStockId 发货入库管理ID。
     */
    public void setDeliveryStockId(Long deliveryStockId) {
        this.deliveryStockId = deliveryStockId;
    }

    /**
     * <p>分销月度。</p>
     *
     * @return the 分销月度
     */
    public String getDistMonth() {
        return distMonth;
    }

    /**
     * <p>分销月度。</p>
     *
     * @param distMonth 分销月度。
     */
    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    /**
     * <p>物流区编号。</p>
     *
     * @return the 物流区编号
     */
    public String getLgcsCode() {
        return lgcsCode;
    }

    /**
     * <p>物流区编号。</p>
     *
     * @param lgcsCode 物流区编号。
     */
    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    /**
     * <p>供应商编号。</p>
     *
     * @return the 供应商编号
     */
    public String getSuppCode() {
        return suppCode;
    }

    /**
     * <p>供应商编号。</p>
     *
     * @param suppCode 供应商编号。
     */
    public void setSuppCode(String suppCode) {
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

    /**
     * <p>半旬码。</p>
     *
     * @param halfCode 半旬码。
     */
    public void setHalfCode(String halfCode) {
        this.halfCode = halfCode;
    }

    /**
     * <p>1:未申请,2:申请中,3:已确认,4:待收货,5:已收货。</p>
     *
     * @return the 1:未申请,2:申请中,3:已确认,4:待收货,5:已收货
     */
    public String getDeliveryStockStatus() {
        return deliveryStockStatus;
    }

    /**
     * <p>1:未申请,2:申请中,3:已确认,4:待收货,5:已收货。</p>
     *
     * @param deliveryStockStatus 1:未申请,2:申请中,3:已确认,4:待收货,5:已收货。
     */
    public void setDeliveryStockStatus(String deliveryStockStatus) {
        this.deliveryStockStatus = deliveryStockStatus;
    }

    /**
     * <p>实际到货时间。</p>
     *
     * @return the 实际到货时间
     */
    public String getDeliveryReceiveStockTime() {
        return deliveryReceiveStockTime;
    }

    /**
     * <p>实际到货时间。</p>
     *
     * @param deliveryReceiveStockTime 实际到货时间。
     */
    public void setDeliveryReceiveStockTime(String deliveryReceiveStockTime) {
        this.deliveryReceiveStockTime = deliveryReceiveStockTime;
    }

    /**
     * <p>发货仓库地点。</p>
     *
     * @return the 发货仓库地点
     */
    public String getDeliveryWarehouseAddr() {
        return deliveryWarehouseAddr;
    }

    /**
     * <p>发货仓库地点。</p>
     *
     * @param deliveryWarehouseAddr 发货仓库地点。
     */
    public void setDeliveryWarehouseAddr(String deliveryWarehouseAddr) {
        this.deliveryWarehouseAddr = deliveryWarehouseAddr;
    }

    /**
     * <p>发货责任人。</p>
     *
     * @return the 发货责任人
     */
    public String getDeliveryResponName() {
        return deliveryResponName;
    }

    /**
     * <p>发货责任人。</p>
     *
     * @param deliveryResponName 发货责任人。
     */
    public void setDeliveryResponName(String deliveryResponName) {
        this.deliveryResponName = deliveryResponName;
    }

    /**
     * <p>发货责任人联系电话。</p>
     *
     * @return the 发货责任人联系电话
     */
    public String getDeliveryResponTel() {
        return deliveryResponTel;
    }

    /**
     * <p>发货责任人联系电话。</p>
     *
     * @param deliveryResponTel 发货责任人联系电话。
     */
    public void setDeliveryResponTel(String deliveryResponTel) {
        this.deliveryResponTel = deliveryResponTel;
    }

    /**
     * <p>发货执行人。</p>
     *
     * @return the 发货执行人
     */
    public String getDeliveryExecuteName() {
        return deliveryExecuteName;
    }

    /**
     * <p>发货执行人。</p>
     *
     * @param deliveryExecuteName 发货执行人。
     */
    public void setDeliveryExecuteName(String deliveryExecuteName) {
        this.deliveryExecuteName = deliveryExecuteName;
    }

    /**
     * <p>发货执行人联系电话。</p>
     *
     * @return the 发货执行人联系电话
     */
    public String getDeliveryExecuteTel() {
        return deliveryExecuteTel;
    }

    /**
     * <p>发货执行人联系电话。</p>
     *
     * @param deliveryExecuteTel 发货执行人联系电话。
     */
    public void setDeliveryExecuteTel(String deliveryExecuteTel) {
        this.deliveryExecuteTel = deliveryExecuteTel;
    }

    /**
     * <p>运输单位名称。</p>
     *
     * @return the 运输单位名称
     */
    public String getTransportUnitName() {
        return transportUnitName;
    }

    /**
     * <p>运输单位名称。</p>
     *
     * @param transportUnitName 运输单位名称。
     */
    public void setTransportUnitName(String transportUnitName) {
        this.transportUnitName = transportUnitName;
    }

    /**
     * <p>运输单位责任人。</p>
     *
     * @return the 运输单位责任人
     */
    public String getTransportUnitResponName() {
        return transportUnitResponName;
    }

    /**
     * <p>运输单位责任人。</p>
     *
     * @param transportUnitResponName 运输单位责任人。
     */
    public void setTransportUnitResponName(String transportUnitResponName) {
        this.transportUnitResponName = transportUnitResponName;
    }

    /**
     * <p>运输单位责任人联系电话。</p>
     *
     * @return the 运输单位责任人联系电话
     */
    public String getTransportUnitResponTel() {
        return transportUnitResponTel;
    }

    /**
     * <p>运输单位责任人联系电话。</p>
     *
     * @param transportUnitResponTel 运输单位责任人联系电话。
     */
    public void setTransportUnitResponTel(String transportUnitResponTel) {
        this.transportUnitResponTel = transportUnitResponTel;
    }

    /**
     * <p>运输单位执行人。</p>
     *
     * @return the 运输单位执行人
     */
    public String getTransportUnitExecuteName() {
        return transportUnitExecuteName;
    }

    /**
     * <p>运输单位执行人。</p>
     *
     * @param transportUnitExecuteName 运输单位执行人。
     */
    public void setTransportUnitExecuteName(String transportUnitExecuteName) {
        this.transportUnitExecuteName = transportUnitExecuteName;
    }

    /**
     * <p>运输单位执行人联系电话。</p>
     *
     * @return the 运输单位执行人联系电话
     */
    public String getTransportUnitExecuteTel() {
        return transportUnitExecuteTel;
    }

    /**
     * <p>运输单位执行人联系电话。</p>
     *
     * @param transportUnitExecuteTel 运输单位执行人联系电话。
     */
    public void setTransportUnitExecuteTel(String transportUnitExecuteTel) {
        this.transportUnitExecuteTel = transportUnitExecuteTel;
    }

    /**
     * <p>发货入库时间要求。</p>
     *
     * @return the 发货入库时间要求
     */
    public String getDeliveryStockTimeReq() {
        return deliveryStockTimeReq;
    }

    /**
     * <p>发货入库时间要求。</p>
     *
     * @param deliveryStockTimeReq 发货入库时间要求。
     */
    public void setDeliveryStockTimeReq(String deliveryStockTimeReq) {
        this.deliveryStockTimeReq = deliveryStockTimeReq;
    }

    /**
     * <p>运抵仓库地址。</p>
     *
     * @return the 运抵仓库地址
     */
    public String getStockAddr() {
        return stockAddr;
    }

    /**
     * <p>运抵仓库地址。</p>
     *
     * @param stockAddr 运抵仓库地址。
     */
    public void setStockAddr(String stockAddr) {
        this.stockAddr = stockAddr;
    }

    /**
     * <p>运抵仓库仓管负责人。</p>
     *
     * @return the 运抵仓库仓管负责人
     */
    public String getStockResponName() {
        return stockResponName;
    }

    /**
     * <p>运抵仓库仓管负责人。</p>
     *
     * @param stockResponName 运抵仓库仓管负责人。
     */
    public void setStockResponName(String stockResponName) {
        this.stockResponName = stockResponName;
    }

    /**
     * <p>运抵仓库仓管负责人联系电话。</p>
     *
     * @return the 运抵仓库仓管负责人联系电话
     */
    public String getStockResponTel() {
        return stockResponTel;
    }

    /**
     * <p>运抵仓库仓管负责人联系电话。</p>
     *
     * @param stockResponTel 运抵仓库仓管负责人联系电话。
     */
    public void setStockResponTel(String stockResponTel) {
        this.stockResponTel = stockResponTel;
    }

    /**
     * <p>运抵仓库验收负责人。</p>
     *
     * @return the 运抵仓库验收负责人
     */
    public String getStockExecuteName() {
        return stockExecuteName;
    }

    /**
     * <p>运抵仓库验收负责人。</p>
     *
     * @param stockExecuteName 运抵仓库验收负责人。
     */
    public void setStockExecuteName(String stockExecuteName) {
        this.stockExecuteName = stockExecuteName;
    }

    /**
     * <p>运抵仓库验收负责人联系电话。</p>
     *
     * @return the 运抵仓库验收负责人联系电话
     */
    public String getStockExecuteTel() {
        return stockExecuteTel;
    }

    /**
     * <p>运抵仓库验收负责人联系电话。</p>
     *
     * @param stockExecuteTel 运抵仓库验收负责人联系电话。
     */
    public void setStockExecuteTel(String stockExecuteTel) {
        this.stockExecuteTel = stockExecuteTel;
    }

    /**
     * <p>发货备注。</p>
     *
     * @return the 发货备注
     */
    public String getDeliveryMemo() {
        return deliveryMemo;
    }

    /**
     * <p>发货备注。</p>
     *
     * @param deliveryMemo 发货备注。
     */
    public void setDeliveryMemo(String deliveryMemo) {
        this.deliveryMemo = deliveryMemo;
    }

    /**
     * <p>收货备注。</p>
     *
     * @return the 收货备注
     */
    public String getStockMemo() {
        return stockMemo;
    }

    /**
     * <p>收货备注。</p>
     *
     * @param stockMemo 收货备注。
     */
    public void setStockMemo(String stockMemo) {
        this.stockMemo = stockMemo;
    }

    /**
     * <p>1代表平台供应链,2代表卖家采供链。</p>
     *
     * @return the 1代表平台供应链,2代表卖家采供链
     */
    public String getSourceFlg() {
        return sourceFlg;
    }

    /**
     * <p>1代表平台供应链,2代表卖家采供链。</p>
     *
     * @param sourceFlg 1代表平台供应链,2代表卖家采供链。
     */
    public void setSourceFlg(String sourceFlg) {
        this.sourceFlg = sourceFlg;
    }

    /**
     * <p>采供链发货入库单Id。</p>
     *
     * @return the 采供链发货入库单Id
     */
    public Long getSscDeliveryStockId() {
        return sscDeliveryStockId;
    }

    /**
     * <p>采供链发货入库单Id。</p>
     *
     * @param sscDeliveryStockId 采供链发货入库单Id。
     */
    public void setSscDeliveryStockId(Long sscDeliveryStockId) {
        this.sscDeliveryStockId = sscDeliveryStockId;
    }

    /**
     * <p>生产商Id。</p>
     *
     * @return the 生产商Id
     */
    public String getPdProduceerId() {
        return pdProduceerId;
    }

    /**
     * <p>生产商Id。</p>
     *
     * @param pdProduceerId 生产商Id。
     */
    public void setPdProduceerId(String pdProduceerId) {
        this.pdProduceerId = pdProduceerId;
    }

    /**
     * <p>生产商名称。</p>
     *
     * @return the 生产商名称
     */
    public String getPdProduceerName() {
        return pdProduceerName;
    }

    /**
     * <p>生产商名称。</p>
     *
     * @param pdProduceerName 生产商名称。
     */
    public void setPdProduceerName(String pdProduceerName) {
        this.pdProduceerName = pdProduceerName;
    }

}
