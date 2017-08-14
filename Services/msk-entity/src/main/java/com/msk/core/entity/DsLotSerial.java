/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ds_lot_serial对应的DsLotSerial。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class DsLotSerial extends BaseEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 批次流水ID */
    private java.lang.Long lotId;
    /** 流水ID */
    private java.lang.String serialId;
    /** 阅读码 */
    private java.lang.String lotCode;
    /** 批次码 */
    private java.lang.String readCode;
    /**
     * <p>默认构造函数。</p>
     */
    public DsLotSerial() {

    }

    /**
     * <p>批次流水ID。</p>
     *
     * @return the 批次流水ID
     */
    public java.lang.Long getLotId() {
        return lotId;
    }

    /**
     * <p>批次流水ID。</p>
     *
     * @param lotId 批次流水ID。
     */
    public void setLotId(java.lang.Long lotId) {
        this.lotId = lotId;
    }

    /**
     * <p>流水ID。</p>
     *
     * @return the 流水ID
     */
    public java.lang.String getSerialId() {
        return serialId;
    }

    /**
     * <p>流水ID。</p>
     *
     * @param serialId 流水ID。
     */
    public void setSerialId(java.lang.String serialId) {
        this.serialId = serialId;
    }

    /**
     * <p>阅读码。</p>
     *
     * @return the 阅读码
     */
    public java.lang.String getLotCode() {
        return lotCode;
    }

    /**
     * <p>阅读码。</p>
     *
     * @param lotCode 阅读码。
     */
    public void setLotCode(java.lang.String lotCode) {
        this.lotCode = lotCode;
    }

    /**
     * <p>批次码。</p>
     *
     * @return the 批次码
     */
    public java.lang.String getReadCode() {
        return readCode;
    }

    /**
     * <p>批次码。</p>
     *
     * @param readCode 批次码。
     */
    public void setReadCode(java.lang.String readCode) {
        this.readCode = readCode;
    }

}
