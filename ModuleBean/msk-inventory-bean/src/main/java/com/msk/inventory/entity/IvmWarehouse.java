/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.inventory.entity;

import com.msk.comm.entity.BaseEntity;

/**
 * <p>表ivm_warehouse对应的IvmWarehouse。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class IvmWarehouse extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** WH_ID */
    private Long whId;
    /** WH_CODE */
    private String whCode;
    /** WH_NAME */
    private String whName;
    /** LOGIC_AREA */
    private String logicArea;
    /** AREA_NAME */
    private String areaName;
    /** WH_ADDRESS */
    private String whAddress;
    /**
     * <p>默认构造函数。</p>
     */
    public IvmWarehouse() {

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
     * <p>WH_NAME。</p>
     *
     * @return the WH_NAME
     */
    public String getWhName() {
        return whName;
    }

    /**
     * <p>WH_NAME。</p>
     *
     * @param whName WH_NAME。
     */
    public void setWhName(String whName) {
        this.whName = whName;
    }

    /**
     * <p>LOGIC_AREA。</p>
     *
     * @return the LOGIC_AREA
     */
    public String getLogicArea() {
        return logicArea;
    }

    /**
     * <p>LOGIC_AREA。</p>
     *
     * @param logicArea LOGIC_AREA。
     */
    public void setLogicArea(String logicArea) {
        this.logicArea = logicArea;
    }

    /**
     * <p>AREA_NAME。</p>
     *
     * @return the AREA_NAME
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * <p>AREA_NAME。</p>
     *
     * @param areaName AREA_NAME。
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * <p>WH_ADDRESS。</p>
     *
     * @return the WH_ADDRESS
     */
    public String getWhAddress() {
        return whAddress;
    }

    /**
     * <p>WH_ADDRESS。</p>
     *
     * @param whAddress WH_ADDRESS。
     */
    public void setWhAddress(String whAddress) {
        this.whAddress = whAddress;
    }

}
