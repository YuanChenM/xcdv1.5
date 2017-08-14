/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_report_info对应的PdReportInfo</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdReportInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 举报ID */
    private Long reportId;
    /** 用户类型（1:买家 2：买手 3：冻品管家） */
    private String userType;
    /** 用户ID */
    private String userId;
    /** 产品编码10位 */
    private String pdCode;
    /** 物流区编码 */
    private String lgcsCode;
    /** 举报类型 */
    private Long reportTypeCode;
    /** 举报描述 */
    private String reportDesc;
    /** 审核状态 */
    private String reportStatus;
    /** 举报凭证1 */
    private String reportImg1;
    /** 举报凭证2 */
    private String reportImg2;
    /** 举报凭证3 */
    private String reportImg3;
    /** 举报凭证4 */
    private String reportImg4;
    /** 举报凭证5 */
    private String reportImg5;
    /**
     * <p>默认构造函数</p>
     */
    public PdReportInfo() {

    }

    /**
     * <p>举报ID</p>
     *
     * @return the 举报ID
     */
    public Long getReportId() {
        return reportId;
    }

    /**
     * <p>举报ID</p>
     *
     * @param reportId 举报ID
     */
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    /**
     * <p>用户类型（1:买家 2：买手 3：冻品管家）</p>
     *
     * @return the 用户类型（1:买家 2：买手 3：冻品管家）
     */
    public String getUserType() {
        return userType;
    }

    /**
     * <p>用户类型（1:买家 2：买手 3：冻品管家）</p>
     *
     * @param userType 用户类型（1:买家 2：买手 3：冻品管家）
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * <p>用户ID</p>
     *
     * @return the 用户ID
     */
    public String getUserId() {return userId;}

    /**
     * <p>用户ID</p>
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {this.userId = userId;}

    /**
     * <p>产品编码10位</p>
     *
     * @return the 产品编码10位
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编码10位</p>
     *
     * @param pdCode 产品编码10位
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>物流区编码</p>
     *
     * @return the 物流区编码
     */
    public String getLgcsCode() {
        return lgcsCode;
    }

    /**
     * <p>物流区编码</p>
     *
     * @param lgcsCode 物流区编码
     */
    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    /**
     * <p>举报类型</p>
     *
     * @return the 举报类型
     */
    public Long getReportTypeCode() {
        return reportTypeCode;
    }

    /**
     * <p>举报类型</p>
     *
     * @param reportTypeCode 举报类型
     */
    public void setReportTypeCode(Long reportTypeCode) {
        this.reportTypeCode = reportTypeCode;
    }

    /**
     * <p>举报描述</p>
     *
     * @return the 举报描述
     */
    public String getReportDesc() {
        return reportDesc;
    }

    /**
     * <p>举报描述</p>
     *
     * @param reportDesc 举报描述
     */
    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    /**
     * <p>审核状态</p>
     *
     * @return the 审核状态
     */
    public String getReportStatus() {
        return reportStatus;
    }

    /**
     * <p>审核状态</p>
     *
     * @param reportStatus 审核状态
     */
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * <p>举报凭证1</p>
     *
     * @return the 举报凭证1
     */
    public String getReportImg1() {
        return reportImg1;
    }

    /**
     * <p>举报凭证1</p>
     *
     * @param reportImg1 举报凭证1
     */
    public void setReportImg1(String reportImg1) {
        this.reportImg1 = reportImg1;
    }

    /**
     * <p>举报凭证2</p>
     *
     * @return the 举报凭证2
     */
    public String getReportImg2() {
        return reportImg2;
    }

    /**
     * <p>举报凭证2</p>
     *
     * @param reportImg2 举报凭证2
     */
    public void setReportImg2(String reportImg2) {
        this.reportImg2 = reportImg2;
    }

    /**
     * <p>举报凭证3</p>
     *
     * @return the 举报凭证3
     */
    public String getReportImg3() {
        return reportImg3;
    }

    /**
     * <p>举报凭证3</p>
     *
     * @param reportImg3 举报凭证3
     */
    public void setReportImg3(String reportImg3) {
        this.reportImg3 = reportImg3;
    }

    /**
     * <p>举报凭证4</p>
     *
     * @return the 举报凭证4
     */
    public String getReportImg4() {
        return reportImg4;
    }

    /**
     * <p>举报凭证4</p>
     *
     * @param reportImg4 举报凭证4
     */
    public void setReportImg4(String reportImg4) {
        this.reportImg4 = reportImg4;
    }

    /**
     * <p>举报凭证5</p>
     *
     * @return the 举报凭证5
     */
    public String getReportImg5() {
        return reportImg5;
    }

    /**
     * <p>举报凭证5</p>
     *
     * @param reportImg5 举报凭证5
     */
    public void setReportImg5(String reportImg5) {
        this.reportImg5 = reportImg5;
    }

}
