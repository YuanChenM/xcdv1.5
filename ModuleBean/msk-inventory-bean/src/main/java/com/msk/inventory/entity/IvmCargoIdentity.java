/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.inventory.entity;

import com.msk.comm.entity.BaseEntity;

/**
 * <p>表ivm_cargo_identity对应的IvmCargoIdentity。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class IvmCargoIdentity extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** YYMMDD */
    private String dateCode;
    /** SEQ */
    private Integer seq;
    /**
     * <p>默认构造函数。</p>
     */
    public IvmCargoIdentity() {

    }

    /**
     * <p>YYMMDD。</p>
     *
     * @return the YYMMDD
     */
    public String getDateCode() {
        return dateCode;
    }

    /**
     * <p>YYMMDD。</p>
     *
     * @param dateCode YYMMDD。
     */
    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    /**
     * <p>SEQ。</p>
     *
     * @return the SEQ
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * <p>SEQ。</p>
     *
     * @param seq SEQ。
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}
