/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_mst_pd_cert对应的SlMstPdCert。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlMstPdCert extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品类别编码 */
    private String pdClassesCode;
    /** 产品加工程度编码 */
    private String machiningCode;
    /** 证照ID */
    private Long certId;
    /**
     * <p>默认构造函数。</p>
     */
    public SlMstPdCert() {

    }

    /**
     * <p>产品类别编码。</p>
     *
     * @return the 产品类别编码
     */
    public String getPdClassesCode() {
        return pdClassesCode;
    }

    /**
     * <p>产品类别编码。</p>
     *
     * @param pdClassesCode 产品类别编码。
     */
    public void setPdClassesCode(String pdClassesCode) {
        this.pdClassesCode = pdClassesCode;
    }

    /**
     * <p>产品加工程度编码。</p>
     *
     * @return the 产品加工程度编码
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>产品加工程度编码。</p>
     *
     * @param machiningCode 产品加工程度编码。
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>证照ID。</p>
     *
     * @return the 证照ID
     */
    public Long getCertId() {
        return certId;
    }

    /**
     * <p>证照ID。</p>
     *
     * @param certId 证照ID。
     */
    public void setCertId(Long certId) {
        this.certId = certId;
    }

}
