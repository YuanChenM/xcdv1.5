/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_house_education_his对应的SlHouseEducationHis</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlHouseEducationHis extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 教育履历ID */
    private Long eduHisId;
    /** 教育ID */
    private Long eduId;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** 用于登录的卖家账号 */
    private String houseCode;
    /** 学习开始时间 */
    private java.util.Date eduStart;
    /** 学习结束时间 */
    private java.util.Date eduEnd;
    /** 教育单位 */
    private String eduComp;
    /** 学历 */
    private String eduRecord;
    /** 学位 */
    private String eduDegree;
    /** 0:新增；1：编辑；2：删除 */
    private String actFlg;
    /**
     * <p>默认构造函数</p>
     */
    public SlHouseEducationHis() {

    }

    /**
     * <p>教育履历ID</p>
     *
     * @return the 教育履历ID
     */
    public Long getEduHisId() {
        return eduHisId;
    }

    /**
     * <p>教育履历ID</p>
     *
     * @param eduHisId 教育履历ID
     */
    public void setEduHisId(Long eduHisId) {
        this.eduHisId = eduHisId;
    }

    /**
     * <p>教育ID</p>
     *
     * @return the 教育ID
     */
    public Long getEduId() {
        return eduId;
    }

    /**
     * <p>教育ID</p>
     *
     * @param eduId 教育ID
     */
    public void setEduId(Long eduId) {
        this.eduId = eduId;
    }

    /**
     * <p>区划(6)+顺序码(4)</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>区划(6)+顺序码(4)</p>
     *
     * @param slCode 区划(6)+顺序码(4)
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>用于登录的卖家账号</p>
     *
     * @return the 用于登录的卖家账号
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>用于登录的卖家账号</p>
     *
     * @param houseCode 用于登录的卖家账号
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>学习开始时间</p>
     *
     * @return the 学习开始时间
     */
    public java.util.Date getEduStart() {
        return eduStart;
    }

    /**
     * <p>学习开始时间</p>
     *
     * @param eduStart 学习开始时间
     */
    public void setEduStart(java.util.Date eduStart) {
        this.eduStart = eduStart;
    }

    /**
     * <p>学习结束时间</p>
     *
     * @return the 学习结束时间
     */
    public java.util.Date getEduEnd() {
        return eduEnd;
    }

    /**
     * <p>学习结束时间</p>
     *
     * @param eduEnd 学习结束时间
     */
    public void setEduEnd(java.util.Date eduEnd) {
        this.eduEnd = eduEnd;
    }

    /**
     * <p>教育单位</p>
     *
     * @return the 教育单位
     */
    public String getEduComp() {
        return eduComp;
    }

    /**
     * <p>教育单位</p>
     *
     * @param eduComp 教育单位
     */
    public void setEduComp(String eduComp) {
        this.eduComp = eduComp;
    }

    /**
     * <p>学历</p>
     *
     * @return the 学历
     */
    public String getEduRecord() {
        return eduRecord;
    }

    /**
     * <p>学历</p>
     *
     * @param eduRecord 学历
     */
    public void setEduRecord(String eduRecord) {
        this.eduRecord = eduRecord;
    }

    /**
     * <p>学位</p>
     *
     * @return the 学位
     */
    public String getEduDegree() {
        return eduDegree;
    }

    /**
     * <p>学位</p>
     *
     * @param eduDegree 学位
     */
    public void setEduDegree(String eduDegree) {
        this.eduDegree = eduDegree;
    }

    /**
     * <p>0:新增；1：编辑；2：删除</p>
     *
     * @return the 0:新增；1：编辑；2：删除
     */
    public String getActFlg() {
        return actFlg;
    }

    /**
     * <p>0:新增；1：编辑；2：删除</p>
     *
     * @param actFlg 0:新增；1：编辑；2：删除
     */
    public void setActFlg(String actFlg) {
        this.actFlg = actFlg;
    }

}
