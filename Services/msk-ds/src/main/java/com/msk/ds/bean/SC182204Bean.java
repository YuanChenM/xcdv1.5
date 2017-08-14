package com.msk.ds.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.product.bean.ProductBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * yi_qixiang.
 */
public class SC182204Bean extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**产品编码 */
    private String pdCode;

    /**数据库查出的总箱数 */
    private BigDecimal sumNewActNum;
    /**产品编码拆分类 */
    private ProductBean productBean;
    /**操作码 */
    private String proLotNum;
    /**阅读码 */
    private String readProLotNum;
    /**产品打印标识码 */
    private String productPrintNum;
    /**产品包装编码 */
    private String normsCode;
    /**半旬期 */
    private String halfPeriod;
    /**供应商名字 */
    private String suppName;
    /**供应商区域 */
    private String lgcsName;
    /**销售区域编号 */
    private String lgcsCode;
    /**供应商编号 */
    private String suppCode;
    /**生产商 */
    private String slCodeManufacture;
    /**品牌 */
    private String brandId;
    /**地区信息List*/
    private List<SC182204Bean> lgcsAreaList;
    /**供应商信息List*/
    private List<SC182204Bean> supplyList;

    public void setSlCodeManufacture(String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setReadProLotNum(String readProLotNum) {
        this.readProLotNum = readProLotNum;
    }

    public String getReadProLotNum() {
        return readProLotNum;
    }

    public void setLgcsAreaList(List<SC182204Bean> lgcsAreaList) {
        this.lgcsAreaList = lgcsAreaList;
    }

    public void setSupplyList(List<SC182204Bean> supplyList) {
        this.supplyList = supplyList;
    }

    public List<SC182204Bean> getLgcsAreaList() {
        return lgcsAreaList;
    }

    public List<SC182204Bean> getSupplyList() {
        return supplyList;
    }

    public String getHalfPeriod() {
        return halfPeriod;
    }

    public void setHalfPeriod(String halfPeriod) {
        this.halfPeriod = halfPeriod;
    }

    public String getProLotNum() {
        return proLotNum;
    }

    public void setProLotNum(String proLotNum) {
        this.proLotNum = proLotNum;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public String getSuppName() {
        return suppName;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public String getProductPrintNum() {
        return productPrintNum;
    }

    public void setProductPrintNum(String productPrintNum) {
        this.productPrintNum = productPrintNum;
    }

    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public BigDecimal getSumNewActNum() {
        return sumNewActNum;
    }

    public void setSumNewActNum(BigDecimal sumNewActNum) {
        this.sumNewActNum = sumNewActNum;
    }
}
