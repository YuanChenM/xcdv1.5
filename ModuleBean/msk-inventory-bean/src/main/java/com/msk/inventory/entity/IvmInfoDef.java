/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.inventory.entity;

import com.msk.comm.entity.BaseEntity;

/**
 * <p>表ivm_info_def对应的IvmInfoDef。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class IvmInfoDef extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** CODE_TYPE */
    private String codeType;
    /** CODE_VALUE */
    private String codeValue;
    /** CODE_DESC */
    private String codeDesc;
    /** REMARK */
    private String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public IvmInfoDef() {

    }

    /**
     * <p>CODE_TYPE。</p>
     *
     * @return the CODE_TYPE
     */
    public String getCodeType() {
        return codeType;
    }

    /**
     * <p>CODE_TYPE。</p>
     *
     * @param codeType CODE_TYPE。
     */
    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    /**
     * <p>CODE_VALUE。</p>
     *
     * @return the CODE_VALUE
     */
    public String getCodeValue() {
        return codeValue;
    }

    /**
     * <p>CODE_VALUE。</p>
     *
     * @param codeValue CODE_VALUE。
     */
    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    /**
     * <p>CODE_DESC。</p>
     *
     * @return the CODE_DESC
     */
    public String getCodeDesc() {
        return codeDesc;
    }

    /**
     * <p>CODE_DESC。</p>
     *
     * @param codeDesc CODE_DESC。
     */
    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    /**
     * <p>REMARK。</p>
     *
     * @return the REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p>REMARK。</p>
     *
     * @param remark REMARK。
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
