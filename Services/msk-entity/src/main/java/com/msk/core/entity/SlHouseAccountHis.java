/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_house_account_his对应的SlHouseAccountHis。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlHouseAccountHis extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 表ID */
    private Long id;
    /** 用于登录的卖家账号 */
    private String houseCode;
    /** 大区 */
    private String vareaCode;
    /** 物流区编码 */
    private String vlgcsAreaCode;
    /** 省编码 */
    private String vprovinceCode;
    /** 地区编码 */
    private String vcityCode;
    /** 区编码 */
    private String vdistrictCode;
    /** V_HOUSE_ADDRESS */
    private String vhouseAddress;
    /** 管家等级 */
    private String houseGreade;
    /** 管家星级 */
    private String houseStar;
    /** 用来记录该等级或者星级状态开始时间 */
    private java.util.Date statusStart;
    /** 用来记录此次变化内容是等级还是星级 */
    private String colName;
    /**
     * <p>默认构造函数。</p>
     */
    public SlHouseAccountHis() {

    }

    /**
     * <p>表ID。</p>
     *
     * @return the 表ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>表ID。</p>
     *
     * @param id 表ID。
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @return the 用于登录的卖家账号
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param houseCode 用于登录的卖家账号。
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>大区。</p>
     *
     * @return the 大区
     */
    public String getVareaCode() {
        return vareaCode;
    }

    /**
     * <p>大区。</p>
     *
     * @param vareaCode 大区。
     */
    public void setVareaCode(String vareaCode) {
        this.vareaCode = vareaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public String getVlgcsAreaCode() {
        return vlgcsAreaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param vlgcsAreaCode 物流区编码。
     */
    public void setVlgcsAreaCode(String vlgcsAreaCode) {
        this.vlgcsAreaCode = vlgcsAreaCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @return the 省编码
     */
    public String getVprovinceCode() {
        return vprovinceCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @param vprovinceCode 省编码。
     */
    public void setVprovinceCode(String vprovinceCode) {
        this.vprovinceCode = vprovinceCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @return the 地区编码
     */
    public String getVcityCode() {
        return vcityCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @param vcityCode 地区编码。
     */
    public void setVcityCode(String vcityCode) {
        this.vcityCode = vcityCode;
    }

    /**
     * <p>区编码。</p>
     *
     * @return the 区编码
     */
    public String getVdistrictCode() {
        return vdistrictCode;
    }

    /**
     * <p>区编码。</p>
     *
     * @param vdistrictCode 区编码。
     */
    public void setVdistrictCode(String vdistrictCode) {
        this.vdistrictCode = vdistrictCode;
    }

    /**
     * <p>V_HOUSE_ADDRESS。</p>
     *
     * @return the V_HOUSE_ADDRESS
     */
    public String getVhouseAddress() {
        return vhouseAddress;
    }

    /**
     * <p>V_HOUSE_ADDRESS。</p>
     *
     * @param vhouseAddress V_HOUSE_ADDRESS。
     */
    public void setVhouseAddress(String vhouseAddress) {
        this.vhouseAddress = vhouseAddress;
    }

    /**
     * <p>管家等级。</p>
     *
     * @return the 管家等级
     */
    public String getHouseGreade() {
        return houseGreade;
    }

    /**
     * <p>管家等级。</p>
     *
     * @param houseGreade 管家等级。
     */
    public void setHouseGreade(String houseGreade) {
        this.houseGreade = houseGreade;
    }

    /**
     * <p>管家星级。</p>
     *
     * @return the 管家星级
     */
    public String getHouseStar() {
        return houseStar;
    }

    /**
     * <p>管家星级。</p>
     *
     * @param houseStar 管家星级。
     */
    public void setHouseStar(String houseStar) {
        this.houseStar = houseStar;
    }

    /**
     * <p>用来记录该等级或者星级状态开始时间。</p>
     *
     * @return the 用来记录该等级或者星级状态开始时间
     */
    public java.util.Date getStatusStart() {
        return statusStart;
    }

    /**
     * <p>用来记录该等级或者星级状态开始时间。</p>
     *
     * @param statusStart 用来记录该等级或者星级状态开始时间。
     */
    public void setStatusStart(java.util.Date statusStart) {
        this.statusStart = statusStart;
    }

    /**
     * <p>用来记录此次变化内容是等级还是星级。</p>
     *
     * @return the 用来记录此次变化内容是等级还是星级
     */
    public String getColName() {
        return colName;
    }

    /**
     * <p>用来记录此次变化内容是等级还是星级。</p>
     *
     * @param colName 用来记录此次变化内容是等级还是星级。
     */
    public void setColName(String colName) {
        this.colName = colName;
    }

}
