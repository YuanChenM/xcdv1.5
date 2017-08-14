/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_mst_cert_item对应的SlMstCertItem。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlMstCertItem extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 证照ID */
    private Long certId;
    /** 证照项目ID */
    private Long certItemId;
    /** 证照项目名称 */
    private String certItemName;
    /** 显示顺序 */
    private Integer sort;
    /**
     * <p>默认构造函数。</p>
     */
    public SlMstCertItem() {

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
     * <p>证照项目ID。</p>
     *
     * @return the 证照项目ID
     */
    public Long getCertItemId() {
        return certItemId;
    }

    /**
     * <p>证照项目ID。</p>
     *
     * @param certItemId 证照项目ID。
     */
    public void setCertItemId(Long certItemId) {
        this.certItemId = certItemId;
    }

    /**
     * <p>证照项目名称。</p>
     *
     * @return the 证照项目名称
     */
    public String getCertItemName() {
        return certItemName;
    }

    /**
     * <p>证照项目名称。</p>
     *
     * @param certItemName 证照项目名称。
     */
    public void setCertItemName(String certItemName) {
        this.certItemName = certItemName;
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
