package com.msk.stock.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsPageParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/5/4.
 */
public class StockRsParam extends BaseParam {
    private Long   stockId;// 库存id  用于比对 是否有重复数据

    private String  supplierName;//供应商名称

    private String slCode;// 卖家编码

    private String  lgcsCode;//物流区编码

    private String lgcsName;// 物流去名称

    private String warehouseCode;//仓库编码

    private String warehouseName;//仓库名称

    private String pdCode;// 商品编码

    private String pdName;//产品名称

    private  String slName;// 卖家名称

    private  String  itemNo;// 货号

    private String classesCode;//产品类别

    private String classesName;//产品类别名称

    private String breedCode;//产品品种

    private String breedName;//产品品种名称

    private  String salePlatform;// 销售平台

    private String pdTypeCode;// pdCodeType  pdCode:10位    pdTypeCode 9位        pdCode-pdTypeCode=1位  代表 该产品的 分等次  甲乙丙丁

    private String supplierCode;// 供应商编码

    private String  queryType;// 查询 类型 sl: 卖家  sp ：供应商    其他是全部都查询

    private String supplyPlatform;// 供应商供货平台

    private BigDecimal stockQty;//商品库存

    private String featureCode;//特征编码

    private String featureName;//特征名称

    /** 产品包装编码 */
    private String normsCode;
    /** 产品包装名称 */
    private String normsName;
    /** 产品等级 */
    private String pdLevel;
    /** 产品单位 */
    private String unit;
    /** 单箱体积 */
    private String packingVolume;
    /** 重量 */
    private String weight;
    /** 体积 */
    private String volume;
    /** 数量 */
    private BigDecimal stockNum;
    /** 业务编码 */
    private String flowId;
    /** 变更类型1：增加，2：减少 */
    private String changeType;
    /** 备份日期 */
    private String historyDate;



    private String stockType;//1:正常仓别 2:非正常仓别

    private  BigDecimal enabledStockQty;// 该商品的有用库存

    private  String group;//  分组查询字段

    private  String orderBy;// 排序字段

    /**
     * sql  中 in
     */
    private List<String>slCodeList=new ArrayList<>();


    private List<String>supplierCodeList=new ArrayList<>();












    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public List<String> getSlCodeList() {
        return slCodeList;
    }

    public void setSlCodeList(List<String> slCodeList) {
        this.slCodeList = slCodeList;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<String> getSupplierCodeList() {
        return supplierCodeList;
    }

    public void setSupplierCodeList(List<String> supplierCodeList) {
        this.supplierCodeList = supplierCodeList;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getSupplyPlatform() {
        return supplyPlatform;
    }

    public void setSupplyPlatform(String supplyPlatform) {
        this.supplyPlatform = supplyPlatform;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }


    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getPdTypeCode() {
        return pdTypeCode;
    }

    public void setPdTypeCode(String pdTypeCode) {
        this.pdTypeCode = pdTypeCode;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }

    public BigDecimal getEnabledStockQty() {
        return enabledStockQty;
    }

    public void setEnabledStockQty(BigDecimal enabledStockQty) {
        this.enabledStockQty = enabledStockQty;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getPdLevel() {
        return pdLevel;
    }

    public void setPdLevel(String pdLevel) {
        this.pdLevel = pdLevel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPackingVolume() {
        return packingVolume;
    }

    public void setPackingVolume(String packingVolume) {
        this.packingVolume = packingVolume;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public BigDecimal getStockNum() {
        return stockNum;
    }

    public void setStockNum(BigDecimal stockNum) {
        this.stockNum = stockNum;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(String historyDate) {
        this.historyDate = historyDate;
    }
}
