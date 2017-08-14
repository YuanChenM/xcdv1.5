/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_dist_regu_chap对应的SlDistReguChap。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlDistReguChap extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 章节ID */
    private Integer chapId;
    /** 类别 */
    private String chapClass;
    /** 章节编号 */
    private Integer chapNo;
    /** 章节标题 */
    private String chapTitle;
    /**
     * <p>默认构造函数。</p>
     */
    public SlDistReguChap() {

    }

    /**
     * <p>章节ID。</p>
     *
     * @return the 章节ID
     */
    public Integer getChapId() {
        return chapId;
    }

    /**
     * <p>章节ID。</p>
     *
     * @param chapId 章节ID。
     */
    public void setChapId(Integer chapId) {
        this.chapId = chapId;
    }

    /**
     * <p>类别。</p>
     *
     * @return the 类别
     */
    public String getChapClass() {
        return chapClass;
    }

    /**
     * <p>类别。</p>
     *
     * @param chapClass 类别。
     */
    public void setChapClass(String chapClass) {
        this.chapClass = chapClass;
    }

    /**
     * <p>章节编号。</p>
     *
     * @return the 章节编号
     */
    public Integer getChapNo() {
        return chapNo;
    }

    /**
     * <p>章节编号。</p>
     *
     * @param chapNo 章节编号。
     */
    public void setChapNo(Integer chapNo) {
        this.chapNo = chapNo;
    }

    /**
     * <p>章节标题。</p>
     *
     * @return the 章节标题
     */
    public String getChapTitle() {
        return chapTitle;
    }

    /**
     * <p>章节标题。</p>
     *
     * @param chapTitle 章节标题。
     */
    public void setChapTitle(String chapTitle) {
        this.chapTitle = chapTitle;
    }

}
