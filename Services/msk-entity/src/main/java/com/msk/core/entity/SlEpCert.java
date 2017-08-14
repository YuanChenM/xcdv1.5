/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_ep_cert对应的SlEpCert。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlEpCert extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 企业ID */
    private Long epId;
    /** 证照顺序号 */
    private Long certSeq;
    /** 证照ID */
    private Long certId;
    /** 证照名称 */
    private String certName;
    /**
     * <p>默认构造函数。</p>
     */
    public SlEpCert() {

    }

    /**
     * <p>企业ID。</p>
     *
     * @return the 企业ID
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * <p>企业ID。</p>
     *
     * @param epId 企业ID。
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * <p>证照顺序号。</p>
     *
     * @return the 证照顺序号
     */
    public Long getCertSeq() {
        return certSeq;
    }

    /**
     * <p>证照顺序号。</p>
     *
     * @param certSeq 证照顺序号。
     */
    public void setCertSeq(Long certSeq) {
        this.certSeq = certSeq;
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

    /**
     * <p>证照名称。</p>
     *
     * @return the 证照名称
     */
    public String getCertName() {
        return certName;
    }

    /**
     * <p>证照名称。</p>
     *
     * @param certName 证照名称。
     */
    public void setCertName(String certName) {
        this.certName = certName;
    }

}
