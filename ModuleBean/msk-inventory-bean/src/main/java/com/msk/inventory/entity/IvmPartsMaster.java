/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.inventory.entity;

import com.msk.comm.entity.BaseEntity;

/**
 * <p>表ivm_parts_master对应的IvmPartsMaster。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class IvmPartsMaster extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** PM_ID */
    private Long pmId;
    /** PM_CODE */
    private String pmCode;
    /** PM_DESC */
    private String pmDesc;
    /** PM_NAME */
    private String pmName;
    /** <CLS_C><MACH_C><BRE_C><FEAT_C><WEI_C><GRAD_C><SL_C><SL_P_C> */
    private String pmExternalXml;
    /** 毛净重，规格等 */
    private String pmAttributeXml;
    /**
     * <p>默认构造函数。</p>
     */
    public IvmPartsMaster() {

    }

    /**
     * <p>PM_ID。</p>
     *
     * @return the PM_ID
     */
    public Long getPmId() {
        return pmId;
    }

    /**
     * <p>PM_ID。</p>
     *
     * @param pmId PM_ID。
     */
    public void setPmId(Long pmId) {
        this.pmId = pmId;
    }

    /**
     * <p>PM_CODE。</p>
     *
     * @return the PM_CODE
     */
    public String getPmCode() {
        return pmCode;
    }

    /**
     * <p>PM_CODE。</p>
     *
     * @param pmCode PM_CODE。
     */
    public void setPmCode(String pmCode) {
        this.pmCode = pmCode;
    }

    /**
     * <p>PM_DESC。</p>
     *
     * @return the PM_DESC
     */
    public String getPmDesc() {
        return pmDesc;
    }

    /**
     * <p>PM_DESC。</p>
     *
     * @param pmDesc PM_DESC。
     */
    public void setPmDesc(String pmDesc) {
        this.pmDesc = pmDesc;
    }

    /**
     * <p>PM_NAME。</p>
     *
     * @return the PM_NAME
     */
    public String getPmName() {
        return pmName;
    }

    /**
     * <p>PM_NAME。</p>
     *
     * @param pmName PM_NAME。
     */
    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    /**
     * <p><CLS_C><MACH_C><BRE_C><FEAT_C><WEI_C><GRAD_C><SL_C><SL_P_C>。</p>
     *
     * @return the <CLS_C><MACH_C><BRE_C><FEAT_C><WEI_C><GRAD_C><SL_C><SL_P_C>
     */
    public String getPmExternalXml() {
        return pmExternalXml;
    }

    /**
     * <p><CLS_C><MACH_C><BRE_C><FEAT_C><WEI_C><GRAD_C><SL_C><SL_P_C>。</p>
     *
     * @param pmExternalXml <CLS_C><MACH_C><BRE_C><FEAT_C><WEI_C><GRAD_C><SL_C><SL_P_C>。
     */
    public void setPmExternalXml(String pmExternalXml) {
        this.pmExternalXml = pmExternalXml;
    }

    /**
     * <p>毛净重，规格等。</p>
     *
     * @return the 毛净重，规格等
     */
    public String getPmAttributeXml() {
        return pmAttributeXml;
    }

    /**
     * <p>毛净重，规格等。</p>
     *
     * @param pmAttributeXml 毛净重，规格等。
     */
    public void setPmAttributeXml(String pmAttributeXml) {
        this.pmAttributeXml = pmAttributeXml;
    }

}
