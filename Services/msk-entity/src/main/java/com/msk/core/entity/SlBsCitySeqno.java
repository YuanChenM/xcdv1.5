/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_bs_city_seqno对应的SlBsCitySeqno。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlBsCitySeqno extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 地区编码 */
    private String cityCode;
    /** 已注册买手数 */
    private Long bsCount;
    /**
     * <p>默认构造函数。</p>
     */
    public SlBsCitySeqno() {

    }

    /**
     * <p>地区编码。</p>
     *
     * @return the 地区编码
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @param cityCode 地区编码。
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * <p>已注册买手数。</p>
     *
     * @return the 已注册买手数
     */
    public Long getBsCount() {
        return bsCount;
    }

    /**
     * <p>已注册买手数。</p>
     *
     * @param bsCount 已注册买手数。
     */
    public void setBsCount(Long bsCount) {
        this.bsCount = bsCount;
    }

}
