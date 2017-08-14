/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_mst_cert对应的SlMstCert。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlMstCert extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 证照ID */
    private Long certId;
    /** 证照名称 */
    private String certName;
    /** 0:不必须,1:必须,2:按产品类别 */
    private Integer reqFlg;
    /** 显示顺序 */
    private Integer sort;
    /**
     * <p>默认构造函数。</p>
     */
    public SlMstCert() {

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

    /**
     * <p>0:不必须,1:必须,2:按产品类别。</p>
     *
     * @return the 0:不必须,1:必须,2:按产品类别
     */
    public Integer getReqFlg() {
        return reqFlg;
    }

    /**
     * <p>0:不必须,1:必须,2:按产品类别。</p>
     *
     * @param reqFlg 0:不必须,1:必须,2:按产品类别。
     */
    public void setReqFlg(Integer reqFlg) {
        this.reqFlg = reqFlg;
    }

    /**
     * <p>显示顺序。</p>
     *
     * @return the 显示顺序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * <p>显示顺序。</p>
     *
     * @param sort 显示顺序。
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

}
