package com.msk.ds.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by zhang_xi on 2016/3/9.
 */
@XmlType(propOrder = {"receiptLine","consignee","name","area","company","companytype","companyName","sku","skuDesc","skuGroup","groupName","grade","featureName","uom",
        "grossWeight","netWeight","length","width","height","volume","qtyExpected","qtyOriginal","inventorystatus","coo"})
public class SC182102XmlDetailParam {
    /** 入库单行号*/
    String receiptLine;
    /** 卖家编码*/
    String consignee;
    /** 卖家名称*/
    String name;
    /** 销售区域*/
    String area;
    /** 供应商编码*/
    String company;
    /** 供应商名称*/
    String companyName;
    /** 商品货号*/
    String sku;
    /** 商品名称*/
    String skuDesc;
    /** 产品一级分类*/
    String skuGroup;
    /** 产品一级分类名称*/
    String groupName;
    /** 计量单位*/
    String uom;
    /** 毛重量*/
    String grossWeight;
    /** 净重量*/
    String netWeight;
    /** 长度 厘米*/
    String length;
    /** 宽度 厘米*/
    String width;
    /** 高度 厘米*/
    String height;
    /** 体积*/
    String volume;
    /** 实际发货数量*/
    String qtyExpected;
    /** 计划发货数量*/
    String qtyOriginal;
    /**等级*/
    String grade;
    /**产品规格*/
    String featureName;
    private String companytype;
    /** AVAILABLE代表良品，
     DAMAGED代表不良品，
     RETURNED代表问题品，
     以后还可以根据业务增加新增其他字母 */
    private String inventorystatus;
    /** #1919 add by likai on 2016/8/15 start */
    /** 原产地+动检证 */
    private String coo;
    /** #1919 add by likai on 2016/8/15 end */

    @XmlElement(name ="RECEIPTLINE",defaultValue = "")
    public String getReceiptLine() {
        return receiptLine;
    }

    public void setReceiptLine(String receiptLine) {
        this.receiptLine = receiptLine;
    }
    @XmlElement(name ="CONSIGNEE",defaultValue = "0000000")
    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
    @XmlElement(name ="NAME",defaultValue = "神农客")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name ="AREA",defaultValue = "")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    @XmlElement(name ="COMPANY",defaultValue = "")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    @XmlElement(name ="COMPANYNAME",defaultValue = "")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @XmlElement(name ="SKU",defaultValue = "")
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
    @XmlElement(name ="SKUDESC",defaultValue = "")
    public String getSkuDesc() {
        return skuDesc;
    }

    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
    }
    @XmlElement(name ="SKUGROUP",defaultValue = "")
    public String getSkuGroup() {
        return skuGroup;
    }

    public void setSkuGroup(String skuGroup) {
        this.skuGroup = skuGroup;
    }
    @XmlElement(name ="GROUPNAME",defaultValue = "")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    @XmlElement(name ="UOM",defaultValue = "")
    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
    @XmlElement(name ="GROSSWEIGHT",defaultValue = "")
    public String getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }
    @XmlElement(name ="NETWEIGHT",defaultValue = "")
    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }
    @XmlElement(name ="LENGTH",defaultValue = "")
    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
    @XmlElement(name ="WIDTH",defaultValue = "")
    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
    @XmlElement(name ="HEIGHT",defaultValue = "")
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    @XmlElement(name ="VOLUME",defaultValue = "")
    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
    @XmlElement(name ="QTYEXPECTED",defaultValue = "")
    public String getQtyExpected() {
        return qtyExpected;
    }

    public void setQtyExpected(String qtyExpected) {
        this.qtyExpected = qtyExpected;
    }
    @XmlElement(name ="QTYORIGINAL",defaultValue = "")
    public String getQtyOriginal() {
        return qtyOriginal;
    }

    public void setQtyOriginal(String qtyOriginal) {
        this.qtyOriginal = qtyOriginal;
    }
    @XmlElement(name ="CIMPANYTYPE",defaultValue = "SUPPLY")
    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }
    @XmlElement(name ="INVENTORYSTATUS",defaultValue = "AVAILABLE")
    public String getInventorystatus() {
        return inventorystatus;
    }

    public void setInventorystatus(String inventorystatus) {
        this.inventorystatus = inventorystatus;
    }
    @XmlElement(name ="GRADE",defaultValue = "")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    @XmlElement(name ="FEATURENAME",defaultValue = "")
    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }
    @XmlElement(name ="COO",defaultValue = "")
    public String getCoo() {
        return coo;
    }

    public void setCoo(String coo) {
        this.coo = coo;
    }
}
