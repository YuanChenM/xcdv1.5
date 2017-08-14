package com.msk.print.bean;

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
    private Long deliveryStockId;
    /** 分销月度 */
    private String distMonth;
    /** 物流区编号 */
    private String lgcsCode;
    /** 供应商编号 */
    private String suppCode;
    /** 半旬码 */
    private String halfCode;
    /** 发货入库状态 */
    private String deliveryStockStatus;
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
    /** 发货备注 */
    private String deliveryMemo;
    /** 收货备注 */
    private String stockMemo;
    /** 发货入库时间要求(计划到货时间) */
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


    private String halfName;

    private String lgcsName;

    private String suppName;

    private String statusName;

    private String[] actualNums;

    private String[] productCodes;

    private Integer[] receiveActualNums;

    //发货次数
    private Long deliveryNums;

    /** 应上海需求新增属性 modify by likai1 2016/8/2 start */
    /** 生产商编码 */
    private String manuCode;
    /** 生产商名称 */
    private String manuName;
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
    /** 实际库存的产品编码 */
    private String productCode;
    /** 实际库存的产品外包装规格*/
    private String productSpecifical;
    /** 实际库存的产品外包装净重 */
    private String netWeight;
    /** 实际库存的产品包装编码 */
    private String packageCode;
    /** 应上海需求新增属性 modify by likai1 2016/8/2 end */
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
    /*public String getHalfCode() {
        if (!StringUtil.isNullOrEmpty(halfCode) && halfCode.equals("26-N日")) {
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
                    halfCode = "26-31日";
                    break;
                case "2":
                    String x = isLeapYear(yearm) ? "29" : "28";
                    halfCode = "26-" + x + "日";
                    break;
                case "4":
                case "6":
                case "9":
                case "11":
                    halfCode = "26-30日";
                    break;
            }
            return halfCode;
        }
        return halfCode;
    }*/
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
    public void setHalfCode(String halfCode) {
        this.halfCode = halfCode;
    }

    /**
     * <p>发货入库状态。</p>
     *
     * @return the 发货入库状态
     */
    public String getDeliveryStockStatus() {
        return deliveryStockStatus;
    }

    /**
     * <p>发货入库状态。</p>
     *
     * @param deliveryStockStatus 发货入库状态。
     */
    public void setDeliveryStockStatus(String deliveryStockStatus) {
        this.deliveryStockStatus = deliveryStockStatus;
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

    public String getHalfName() {
        if (halfName!=null && !halfName.equals("") && halfName.equals("26-N日")) {
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

    public Integer[] getReceiveActualNums() {
        return receiveActualNums;
    }

    public void setReceiveActualNums(Integer[] receiveActualNums) {
        this.receiveActualNums = receiveActualNums;
    }

    public BigDecimal getDifferQty() {
        return differQty;
    }

    public void setDifferQty(BigDecimal differQty) {
        this.differQty = differQty;
    }

    public BigDecimal getDifferNum() {
        return differNum;
    }

    public void setDifferNum(BigDecimal differNum) {
        this.differNum = differNum;
    }

    public BigDecimal getRecriveQty() {
        return recriveQty;
    }

    public void setRecriveQty(BigDecimal recriveQty) {
        this.recriveQty = recriveQty;
    }

    public BigDecimal getSendActualQty() {
        return sendActualQty;
    }

    public void setSendActualQty(BigDecimal sendActualQty) {
        this.sendActualQty = sendActualQty;
    }

    public BigDecimal getSendPlanQty() {
        return sendPlanQty;
    }

    public void setSendPlanQty(BigDecimal sendPlanQty) {
        this.sendPlanQty = sendPlanQty;
    }

    public BigDecimal getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(BigDecimal receiveNum) {
        this.receiveNum = receiveNum;
    }

    public BigDecimal getSendActualNum() {
        return sendActualNum;
    }

    public void setSendActualNum(BigDecimal sendActualNum) {
        this.sendActualNum = sendActualNum;
    }

    public BigDecimal getSendPlanNum() {
        return sendPlanNum;
    }

    public void setSendPlanNum(BigDecimal sendPlanNum) {
        this.sendPlanNum = sendPlanNum;
    }

    public String getManuName() {
        return manuName;
    }

    public void setManuName(String manuName) {
        this.manuName = manuName;
    }

    public String getManuCode() {
        return manuCode;
    }

    public void setManuCode(String manuCode) {
        this.manuCode = manuCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductSpecifical() {
        return productSpecifical;
    }

    public void setProductSpecifical(String productSpecifical) {
        this.productSpecifical = productSpecifical;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }
}
