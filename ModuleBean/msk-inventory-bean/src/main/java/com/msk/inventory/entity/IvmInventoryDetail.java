/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.inventory.entity;

import com.msk.comm.entity.BaseEntity;

/**
 * <p>表ivm_inventory_detail对应的IvmInventoryDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class IvmInventoryDetail extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** IV_D_ID */
    private Long ivDId;
    /** LOAD_NO */
    private String loadNo;
    /** LOAD_SEQ */
    private Integer loadSeq;
    /** PM_ID */
    private Long pmId;
    /** PM_CODE */
    private String pmCode;
    /** WH_ID */
    private Long whId;
    /** WH_CODE */
    private String whCode;
    /** OWNER_ID */
    private Long ownerId;
    /** OWNER_CODE */
    private String ownerCode;
    /** <AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>卖家ID */
    private String beloneXml;
    /** SUPPLIER_ID */
    private Long supplierId;
    /** SUPPLIER_CODE */
    private String supplierCode;
    /** COO */
    private String coo;
    /** INBOUND_ID */
    private Long inboundId;
    /** INBOUND_NO */
    private String inboundNo;
    /** INBOUND_DETAIL_ID */
    private Long inboundDetailId;
    /** 采购入库，调整入库，退货入库，样品入库 */
    private String inboundType;
    /** PO_ID */
    private Long poId;
    /** PO_NO */
    private String poNo;
    /** PO_DETAIL_ID */
    private Long poDetailId;
    /** ASN_ID */
    private Long asnId;
    /** ASN_NO */
    private String asnNo;
    /** ASN_DETAIL_ID */
    private Long asnDetailId;
    /** RI_ID */
    private Long riId;
    /** RI_NO */
    private String riNo;
    /** RI_DETAIL_ID */
    private Long riDetailId;
    /** PUCHARSE_BATCH */
    private String pucharseBatch;
    /** <INBAT>内部批次 */
    private String inExternalXml;
    /** BUY_PRICE */
    private java.math.BigDecimal buyPrice;
    /** QTY */
    private java.math.BigDecimal qty;
    /** UOM */
    private String uom;
    /** RECV_DATE */
    private java.util.Date recvDate;
    /** RECV_TIME */
    private java.util.Date recvTime;
    /** EXPIRATION_DATE */
    private java.util.Date expirationDate;
    /** <ID>占用ID<REF>占用参考 */
    private String allocatedXml;
    /** OUTBOUND_ID */
    private Long outboundId;
    /** OUTBOUND_NO */
    private String outboundNo;
    /** OUTBOUND_DETAIL_ID */
    private Long outboundDetailId;
    /** 采购出库，调整出库，退货出库，样品出库 */
    private String outboundType;
    /** CO_ID */
    private Long coId;
    /** CO_NO */
    private String coNo;
    /** CO_DETAIL_ID */
    private Long coDetailId;
    /** SO_ID */
    private Long soId;
    /** SO_NO */
    private String soNo;
    /** SO_DETAIL_ID */
    private Long soDetailId;
    /** DI_ID */
    private Long diId;
    /** DI_NO */
    private String diNo;
    /** DI_DETAIL_ID */
    private Long diDetailId;
    /** OUT_EXTERNAL_XML */
    private String outExternalXml;
    /** SELL_PRICE */
    private java.math.BigDecimal sellPrice;
    /** DISPATCHED_DATE */
    private java.util.Date dispatchedDate;
    /** DISPATCHED_TIME */
    private java.util.Date dispatchedTime;
    /** DELIVER_TIME */
    private java.util.Date deliverTime;
    /** IMP */
    private java.math.BigDecimal imp;
    /** EMP */
    private java.math.BigDecimal emp;
    /** OMP */
    private java.math.BigDecimal omp;
    /** 问题，良品，不良品 */
    private String ivFlag;
    /** FLAG_C_TIME */
    private java.util.Date flagCTime;
    /** FLAG_C_REASON */
    private String flagCReason;
    /** 采购在途，入库中，在库，出库中，已发货，已交付 */
    private String ivStatus;
    /**
     * <p>默认构造函数。</p>
     */
    public IvmInventoryDetail() {

    }

    /**
     * <p>IV_D_ID。</p>
     *
     * @return the IV_D_ID
     */
    public Long getIvDId() {
        return ivDId;
    }

    /**
     * <p>IV_D_ID。</p>
     *
     * @param ivDId IV_D_ID。
     */
    public void setIvDId(Long ivDId) {
        this.ivDId = ivDId;
    }

    /**
     * <p>LOAD_NO。</p>
     *
     * @return the LOAD_NO
     */
    public String getLoadNo() {
        return loadNo;
    }

    /**
     * <p>LOAD_NO。</p>
     *
     * @param loadNo LOAD_NO。
     */
    public void setLoadNo(String loadNo) {
        this.loadNo = loadNo;
    }

    /**
     * <p>LOAD_SEQ。</p>
     *
     * @return the LOAD_SEQ
     */
    public Integer getLoadSeq() {
        return loadSeq;
    }

    /**
     * <p>LOAD_SEQ。</p>
     *
     * @param loadSeq LOAD_SEQ。
     */
    public void setLoadSeq(Integer loadSeq) {
        this.loadSeq = loadSeq;
    }

    /**
     * <p>PM_ID。</p>
     *
     * @return the PM_ID
     */
    public Long getPmId() {
        return pmId;
    }

    /**
     * <p>PM_ID。</p>
     *
     * @param pmId PM_ID。
     */
    public void setPmId(Long pmId) {
        this.pmId = pmId;
    }

    /**
     * <p>PM_CODE。</p>
     *
     * @return the PM_CODE
     */
    public String getPmCode() {
        return pmCode;
    }

    /**
     * <p>PM_CODE。</p>
     *
     * @param pmCode PM_CODE。
     */
    public void setPmCode(String pmCode) {
        this.pmCode = pmCode;
    }

    /**
     * <p>WH_ID。</p>
     *
     * @return the WH_ID
     */
    public Long getWhId() {
        return whId;
    }

    /**
     * <p>WH_ID。</p>
     *
     * @param whId WH_ID。
     */
    public void setWhId(Long whId) {
        this.whId = whId;
    }

    /**
     * <p>WH_CODE。</p>
     *
     * @return the WH_CODE
     */
    public String getWhCode() {
        return whCode;
    }

    /**
     * <p>WH_CODE。</p>
     *
     * @param whCode WH_CODE。
     */
    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    /**
     * <p>OWNER_ID。</p>
     *
     * @return the OWNER_ID
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * <p>OWNER_ID。</p>
     *
     * @param ownerId OWNER_ID。
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * <p>OWNER_CODE。</p>
     *
     * @return the OWNER_CODE
     */
    public String getOwnerCode() {
        return ownerCode;
    }

    /**
     * <p>OWNER_CODE。</p>
     *
     * @param ownerCode OWNER_CODE。
     */
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    /**
     * <p><AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>卖家ID。</p>
     *
     * @return the <AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>卖家ID
     */
    public String getBeloneXml() {
        return beloneXml;
    }

    /**
     * <p><AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>卖家ID。</p>
     *
     * @param beloneXml <AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>卖家ID。
     */
    public void setBeloneXml(String beloneXml) {
        this.beloneXml = beloneXml;
    }

    /**
     * <p>SUPPLIER_ID。</p>
     *
     * @return the SUPPLIER_ID
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * <p>SUPPLIER_ID。</p>
     *
     * @param supplierId SUPPLIER_ID。
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * <p>SUPPLIER_CODE。</p>
     *
     * @return the SUPPLIER_CODE
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>SUPPLIER_CODE。</p>
     *
     * @param supplierCode SUPPLIER_CODE。
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * <p>COO。</p>
     *
     * @return the COO
     */
    public String getCoo() {
        return coo;
    }

    /**
     * <p>COO。</p>
     *
     * @param coo COO。
     */
    public void setCoo(String coo) {
        this.coo = coo;
    }

    /**
     * <p>INBOUND_ID。</p>
     *
     * @return the INBOUND_ID
     */
    public Long getInboundId() {
        return inboundId;
    }

    /**
     * <p>INBOUND_ID。</p>
     *
     * @param inboundId INBOUND_ID。
     */
    public void setInboundId(Long inboundId) {
        this.inboundId = inboundId;
    }

    /**
     * <p>INBOUND_NO。</p>
     *
     * @return the INBOUND_NO
     */
    public String getInboundNo() {
        return inboundNo;
    }

    /**
     * <p>INBOUND_NO。</p>
     *
     * @param inboundNo INBOUND_NO。
     */
    public void setInboundNo(String inboundNo) {
        this.inboundNo = inboundNo;
    }

    /**
     * <p>INBOUND_DETAIL_ID。</p>
     *
     * @return the INBOUND_DETAIL_ID
     */
    public Long getInboundDetailId() {
        return inboundDetailId;
    }

    /**
     * <p>INBOUND_DETAIL_ID。</p>
     *
     * @param inboundDetailId INBOUND_DETAIL_ID。
     */
    public void setInboundDetailId(Long inboundDetailId) {
        this.inboundDetailId = inboundDetailId;
    }

    /**
     * <p>采购入库，调整入库，退货入库，样品入库。</p>
     *
     * @return the 采购入库，调整入库，退货入库，样品入库
     */
    public String getInboundType() {
        return inboundType;
    }

    /**
     * <p>采购入库，调整入库，退货入库，样品入库。</p>
     *
     * @param inboundType 采购入库，调整入库，退货入库，样品入库。
     */
    public void setInboundType(String inboundType) {
        this.inboundType = inboundType;
    }

    /**
     * <p>PO_ID。</p>
     *
     * @return the PO_ID
     */
    public Long getPoId() {
        return poId;
    }

    /**
     * <p>PO_ID。</p>
     *
     * @param poId PO_ID。
     */
    public void setPoId(Long poId) {
        this.poId = poId;
    }

    /**
     * <p>PO_NO。</p>
     *
     * @return the PO_NO
     */
    public String getPoNo() {
        return poNo;
    }

    /**
     * <p>PO_NO。</p>
     *
     * @param poNo PO_NO。
     */
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    /**
     * <p>PO_DETAIL_ID。</p>
     *
     * @return the PO_DETAIL_ID
     */
    public Long getPoDetailId() {
        return poDetailId;
    }

    /**
     * <p>PO_DETAIL_ID。</p>
     *
     * @param poDetailId PO_DETAIL_ID。
     */
    public void setPoDetailId(Long poDetailId) {
        this.poDetailId = poDetailId;
    }

    /**
     * <p>ASN_ID。</p>
     *
     * @return the ASN_ID
     */
    public Long getAsnId() {
        return asnId;
    }

    /**
     * <p>ASN_ID。</p>
     *
     * @param asnId ASN_ID。
     */
    public void setAsnId(Long asnId) {
        this.asnId = asnId;
    }

    /**
     * <p>ASN_NO。</p>
     *
     * @return the ASN_NO
     */
    public String getAsnNo() {
        return asnNo;
    }

    /**
     * <p>ASN_NO。</p>
     *
     * @param asnNo ASN_NO。
     */
    public void setAsnNo(String asnNo) {
        this.asnNo = asnNo;
    }

    /**
     * <p>ASN_DETAIL_ID。</p>
     *
     * @return the ASN_DETAIL_ID
     */
    public Long getAsnDetailId() {
        return asnDetailId;
    }

    /**
     * <p>ASN_DETAIL_ID。</p>
     *
     * @param asnDetailId ASN_DETAIL_ID。
     */
    public void setAsnDetailId(Long asnDetailId) {
        this.asnDetailId = asnDetailId;
    }

    /**
     * <p>RI_ID。</p>
     *
     * @return the RI_ID
     */
    public Long getRiId() {
        return riId;
    }

    /**
     * <p>RI_ID。</p>
     *
     * @param riId RI_ID。
     */
    public void setRiId(Long riId) {
        this.riId = riId;
    }

    /**
     * <p>RI_NO。</p>
     *
     * @return the RI_NO
     */
    public String getRiNo() {
        return riNo;
    }

    /**
     * <p>RI_NO。</p>
     *
     * @param riNo RI_NO。
     */
    public void setRiNo(String riNo) {
        this.riNo = riNo;
    }

    /**
     * <p>RI_DETAIL_ID。</p>
     *
     * @return the RI_DETAIL_ID
     */
    public Long getRiDetailId() {
        return riDetailId;
    }

    /**
     * <p>RI_DETAIL_ID。</p>
     *
     * @param riDetailId RI_DETAIL_ID。
     */
    public void setRiDetailId(Long riDetailId) {
        this.riDetailId = riDetailId;
    }

    /**
     * <p>PUCHARSE_BATCH。</p>
     *
     * @return the PUCHARSE_BATCH
     */
    public String getPucharseBatch() {
        return pucharseBatch;
    }

    /**
     * <p>PUCHARSE_BATCH。</p>
     *
     * @param pucharseBatch PUCHARSE_BATCH。
     */
    public void setPucharseBatch(String pucharseBatch) {
        this.pucharseBatch = pucharseBatch;
    }

    /**
     * <p><INBAT>内部批次。</p>
     *
     * @return the <INBAT>内部批次
     */
    public String getInExternalXml() {
        return inExternalXml;
    }

    /**
     * <p><INBAT>内部批次。</p>
     *
     * @param inExternalXml <INBAT>内部批次。
     */
    public void setInExternalXml(String inExternalXml) {
        this.inExternalXml = inExternalXml;
    }

    /**
     * <p>BUY_PRICE。</p>
     *
     * @return the BUY_PRICE
     */
    public java.math.BigDecimal getBuyPrice() {
        return buyPrice;
    }

    /**
     * <p>BUY_PRICE。</p>
     *
     * @param buyPrice BUY_PRICE。
     */
    public void setBuyPrice(java.math.BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * <p>QTY。</p>
     *
     * @return the QTY
     */
    public java.math.BigDecimal getQty() {
        return qty;
    }

    /**
     * <p>QTY。</p>
     *
     * @param qty QTY。
     */
    public void setQty(java.math.BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * <p>UOM。</p>
     *
     * @return the UOM
     */
    public String getUom() {
        return uom;
    }

    /**
     * <p>UOM。</p>
     *
     * @param uom UOM。
     */
    public void setUom(String uom) {
        this.uom = uom;
    }

    /**
     * <p>RECV_DATE。</p>
     *
     * @return the RECV_DATE
     */
    public java.util.Date getRecvDate() {
        return recvDate;
    }

    /**
     * <p>RECV_DATE。</p>
     *
     * @param recvDate RECV_DATE。
     */
    public void setRecvDate(java.util.Date recvDate) {
        this.recvDate = recvDate;
    }

    /**
     * <p>RECV_TIME。</p>
     *
     * @return the RECV_TIME
     */
    public java.util.Date getRecvTime() {
        return recvTime;
    }

    /**
     * <p>RECV_TIME。</p>
     *
     * @param recvTime RECV_TIME。
     */
    public void setRecvTime(java.util.Date recvTime) {
        this.recvTime = recvTime;
    }

    /**
     * <p>EXPIRATION_DATE。</p>
     *
     * @return the EXPIRATION_DATE
     */
    public java.util.Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * <p>EXPIRATION_DATE。</p>
     *
     * @param expirationDate EXPIRATION_DATE。
     */
    public void setExpirationDate(java.util.Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * <p><ID>占用ID<REF>占用参考。</p>
     *
     * @return the <ID>占用ID<REF>占用参考
     */
    public String getAllocatedXml() {
        return allocatedXml;
    }

    /**
     * <p><ID>占用ID<REF>占用参考。</p>
     *
     * @param allocatedXml <ID>占用ID<REF>占用参考。
     */
    public void setAllocatedXml(String allocatedXml) {
        this.allocatedXml = allocatedXml;
    }

    /**
     * <p>OUTBOUND_ID。</p>
     *
     * @return the OUTBOUND_ID
     */
    public Long getOutboundId() {
        return outboundId;
    }

    /**
     * <p>OUTBOUND_ID。</p>
     *
     * @param outboundId OUTBOUND_ID。
     */
    public void setOutboundId(Long outboundId) {
        this.outboundId = outboundId;
    }

    /**
     * <p>OUTBOUND_NO。</p>
     *
     * @return the OUTBOUND_NO
     */
    public String getOutboundNo() {
        return outboundNo;
    }

    /**
     * <p>OUTBOUND_NO。</p>
     *
     * @param outboundNo OUTBOUND_NO。
     */
    public void setOutboundNo(String outboundNo) {
        this.outboundNo = outboundNo;
    }

    /**
     * <p>OUTBOUND_DETAIL_ID。</p>
     *
     * @return the OUTBOUND_DETAIL_ID
     */
    public Long getOutboundDetailId() {
        return outboundDetailId;
    }

    /**
     * <p>OUTBOUND_DETAIL_ID。</p>
     *
     * @param outboundDetailId OUTBOUND_DETAIL_ID。
     */
    public void setOutboundDetailId(Long outboundDetailId) {
        this.outboundDetailId = outboundDetailId;
    }

    /**
     * <p>采购出库，调整出库，退货出库，样品出库。</p>
     *
     * @return the 采购出库，调整出库，退货出库，样品出库
     */
    public String getOutboundType() {
        return outboundType;
    }

    /**
     * <p>采购出库，调整出库，退货出库，样品出库。</p>
     *
     * @param outboundType 采购出库，调整出库，退货出库，样品出库。
     */
    public void setOutboundType(String outboundType) {
        this.outboundType = outboundType;
    }

    /**
     * <p>CO_ID。</p>
     *
     * @return the CO_ID
     */
    public Long getCoId() {
        return coId;
    }

    /**
     * <p>CO_ID。</p>
     *
     * @param coId CO_ID。
     */
    public void setCoId(Long coId) {
        this.coId = coId;
    }

    /**
     * <p>CO_NO。</p>
     *
     * @return the CO_NO
     */
    public String getCoNo() {
        return coNo;
    }

    /**
     * <p>CO_NO。</p>
     *
     * @param coNo CO_NO。
     */
    public void setCoNo(String coNo) {
        this.coNo = coNo;
    }

    /**
     * <p>CO_DETAIL_ID。</p>
     *
     * @return the CO_DETAIL_ID
     */
    public Long getCoDetailId() {
        return coDetailId;
    }

    /**
     * <p>CO_DETAIL_ID。</p>
     *
     * @param coDetailId CO_DETAIL_ID。
     */
    public void setCoDetailId(Long coDetailId) {
        this.coDetailId = coDetailId;
    }

    /**
     * <p>SO_ID。</p>
     *
     * @return the SO_ID
     */
    public Long getSoId() {
        return soId;
    }

    /**
     * <p>SO_ID。</p>
     *
     * @param soId SO_ID。
     */
    public void setSoId(Long soId) {
        this.soId = soId;
    }

    /**
     * <p>SO_NO。</p>
     *
     * @return the SO_NO
     */
    public String getSoNo() {
        return soNo;
    }

    /**
     * <p>SO_NO。</p>
     *
     * @param soNo SO_NO。
     */
    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    /**
     * <p>SO_DETAIL_ID。</p>
     *
     * @return the SO_DETAIL_ID
     */
    public Long getSoDetailId() {
        return soDetailId;
    }

    /**
     * <p>SO_DETAIL_ID。</p>
     *
     * @param soDetailId SO_DETAIL_ID。
     */
    public void setSoDetailId(Long soDetailId) {
        this.soDetailId = soDetailId;
    }

    /**
     * <p>DI_ID。</p>
     *
     * @return the DI_ID
     */
    public Long getDiId() {
        return diId;
    }

    /**
     * <p>DI_ID。</p>
     *
     * @param diId DI_ID。
     */
    public void setDiId(Long diId) {
        this.diId = diId;
    }

    /**
     * <p>DI_NO。</p>
     *
     * @return the DI_NO
     */
    public String getDiNo() {
        return diNo;
    }

    /**
     * <p>DI_NO。</p>
     *
     * @param diNo DI_NO。
     */
    public void setDiNo(String diNo) {
        this.diNo = diNo;
    }

    /**
     * <p>DI_DETAIL_ID。</p>
     *
     * @return the DI_DETAIL_ID
     */
    public Long getDiDetailId() {
        return diDetailId;
    }

    /**
     * <p>DI_DETAIL_ID。</p>
     *
     * @param diDetailId DI_DETAIL_ID。
     */
    public void setDiDetailId(Long diDetailId) {
        this.diDetailId = diDetailId;
    }

    /**
     * <p>OUT_EXTERNAL_XML。</p>
     *
     * @return the OUT_EXTERNAL_XML
     */
    public String getOutExternalXml() {
        return outExternalXml;
    }

    /**
     * <p>OUT_EXTERNAL_XML。</p>
     *
     * @param outExternalXml OUT_EXTERNAL_XML。
     */
    public void setOutExternalXml(String outExternalXml) {
        this.outExternalXml = outExternalXml;
    }

    /**
     * <p>SELL_PRICE。</p>
     *
     * @return the SELL_PRICE
     */
    public java.math.BigDecimal getSellPrice() {
        return sellPrice;
    }

    /**
     * <p>SELL_PRICE。</p>
     *
     * @param sellPrice SELL_PRICE。
     */
    public void setSellPrice(java.math.BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * <p>DISPATCHED_DATE。</p>
     *
     * @return the DISPATCHED_DATE
     */
    public java.util.Date getDispatchedDate() {
        return dispatchedDate;
    }

    /**
     * <p>DISPATCHED_DATE。</p>
     *
     * @param dispatchedDate DISPATCHED_DATE。
     */
    public void setDispatchedDate(java.util.Date dispatchedDate) {
        this.dispatchedDate = dispatchedDate;
    }

    /**
     * <p>DISPATCHED_TIME。</p>
     *
     * @return the DISPATCHED_TIME
     */
    public java.util.Date getDispatchedTime() {
        return dispatchedTime;
    }

    /**
     * <p>DISPATCHED_TIME。</p>
     *
     * @param dispatchedTime DISPATCHED_TIME。
     */
    public void setDispatchedTime(java.util.Date dispatchedTime) {
        this.dispatchedTime = dispatchedTime;
    }

    /**
     * <p>DELIVER_TIME。</p>
     *
     * @return the DELIVER_TIME
     */
    public java.util.Date getDeliverTime() {
        return deliverTime;
    }

    /**
     * <p>DELIVER_TIME。</p>
     *
     * @param deliverTime DELIVER_TIME。
     */
    public void setDeliverTime(java.util.Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    /**
     * <p>IMP。</p>
     *
     * @return the IMP
     */
    public java.math.BigDecimal getImp() {
        return imp;
    }

    /**
     * <p>IMP。</p>
     *
     * @param imp IMP。
     */
    public void setImp(java.math.BigDecimal imp) {
        this.imp = imp;
    }

    /**
     * <p>EMP。</p>
     *
     * @return the EMP
     */
    public java.math.BigDecimal getEmp() {
        return emp;
    }

    /**
     * <p>EMP。</p>
     *
     * @param emp EMP。
     */
    public void setEmp(java.math.BigDecimal emp) {
        this.emp = emp;
    }

    /**
     * <p>OMP。</p>
     *
     * @return the OMP
     */
    public java.math.BigDecimal getOmp() {
        return omp;
    }

    /**
     * <p>OMP。</p>
     *
     * @param omp OMP。
     */
    public void setOmp(java.math.BigDecimal omp) {
        this.omp = omp;
    }

    /**
     * <p>问题，良品，不良品。</p>
     *
     * @return the 问题，良品，不良品
     */
    public String getIvFlag() {
        return ivFlag;
    }

    /**
     * <p>问题，良品，不良品。</p>
     *
     * @param ivFlag 问题，良品，不良品。
     */
    public void setIvFlag(String ivFlag) {
        this.ivFlag = ivFlag;
    }

    /**
     * <p>FLAG_C_TIME。</p>
     *
     * @return the FLAG_C_TIME
     */
    public java.util.Date getFlagCTime() {
        return flagCTime;
    }

    /**
     * <p>FLAG_C_TIME。</p>
     *
     * @param flagCTime FLAG_C_TIME。
     */
    public void setFlagCTime(java.util.Date flagCTime) {
        this.flagCTime = flagCTime;
    }

    /**
     * <p>FLAG_C_REASON。</p>
     *
     * @return the FLAG_C_REASON
     */
    public String getFlagCReason() {
        return flagCReason;
    }

    /**
     * <p>FLAG_C_REASON。</p>
     *
     * @param flagCReason FLAG_C_REASON。
     */
    public void setFlagCReason(String flagCReason) {
        this.flagCReason = flagCReason;
    }

    /**
     * <p>采购在途，入库中，在库，出库中，已发货，已交付。</p>
     *
     * @return the 采购在途，入库中，在库，出库中，已发货，已交付
     */
    public String getIvStatus() {
        return ivStatus;
    }

    /**
     * <p>采购在途，入库中，在库，出库中，已发货，已交付。</p>
     *
     * @param ivStatus 采购在途，入库中，在库，出库中，已发货，已交付。
     */
    public void setIvStatus(String ivStatus) {
        this.ivStatus = ivStatus;
    }

}
