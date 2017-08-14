package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * 具体质量指标
 * IPD141106RsParam.
 *
 * @author xhy
 */
@JsonIgnoreProperties(value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId", "actTime" })
public class IPD141106SubRsResult extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private String qltStdSubId; // 具体质量指标分类ID
    private String qltStdSubName; // 具体质量指标分类名称

    private List<IPD141106ItemRsResult> qltStdItemList;

    /**
     * Get the qltStdSubId.
     *
     * @return qltStdSubId
     *
     * @author xhy
     */
    public String getQltStdSubId() {
        return this.qltStdSubId;
    }

    /**
     * Set the qltStdSubId.
     *
     * @param qltStdSubId qltStdSubId
     *
     * @author xhy
     */
    public void setQltStdSubId(String qltStdSubId) {
        this.qltStdSubId = qltStdSubId;
    }

    /**
     * Get the qltStdSubName.
     *
     * @return qltStdSubName
     *
     * @author xhy
     */
    public String getQltStdSubName() {
        return this.qltStdSubName;
    }

    /**
     * Set the qltStdSubName.
     *
     * @param qltStdSubName qltStdSubName
     *
     * @author xhy
     */
    public void setQltStdSubName(String qltStdSubName) {
        this.qltStdSubName = qltStdSubName;
    }

    /**
     * Get the qltStdItemList.
     *
     * @return qltStdItemList
     *
     * @author xhy
     */
    public List<IPD141106ItemRsResult> getQltStdItemList() {
        return this.qltStdItemList;
    }

    /**
     * Set the qltStdItemList.
     *
     * @param qltStdItemList qltStdItemList
     *
     * @author xhy
     */
    public void setQltStdItemList(List<IPD141106ItemRsResult> qltStdItemList) {
        this.qltStdItemList = qltStdItemList;
    }

}