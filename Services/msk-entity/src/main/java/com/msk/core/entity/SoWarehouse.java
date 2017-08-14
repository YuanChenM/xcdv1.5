/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_warehouse对应的SoWarehouse。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoWarehouse extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 仓库编码 */
    private String warehouseCode;
    /** 仓库名称 */
    private String warehouseName;
    /** 物流区编码 */
    private String areaCode;
    /** 物流区名称 */
    private String areaName;
    /**
     * <p>默认构造函数。</p>
     */
    public SoWarehouse() {

    }

    /**
     * <p>仓库编码。</p>
     *
     * @return the 仓库编码
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * <p>仓库编码。</p>
     *
     * @param warehouseCode 仓库编码。
     */
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    /**
     * <p>仓库名称。</p>
     *
     * @return the 仓库名称
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * <p>仓库名称。</p>
     *
     * @param warehouseName 仓库名称。
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param areaCode 物流区编码。
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @return the 物流区名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @param areaName 物流区名称。
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

}
