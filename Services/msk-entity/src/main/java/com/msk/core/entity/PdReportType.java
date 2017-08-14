/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_report_type对应的PdReportType</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdReportType extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 举报类型编码 */
    private Long reportTypeCode;
    /** 举报类型标题 */
    private String reportTitle;
    /** 举报类型描述 */
    private String reportDescription;
    /**
     * <p>默认构造函数</p>
     */
    public PdReportType() {

    }

    /**
     * <p>举报类型编码</p>
     *
     * @return the 举报类型编码
     */
    public Long getReportTypeCode() {
        return reportTypeCode;
    }

    /**
     * <p>举报类型编码</p>
     *
     * @param reportTypeCode 举报类型编码
     */
    public void setReportTypeCode(Long reportTypeCode) {
        this.reportTypeCode = reportTypeCode;
    }

    /**
     * <p>举报类型标题</p>
     *
     * @return the 举报类型标题
     */
    public String getReportTitle() {
        return reportTitle;
    }

    /**
     * <p>举报类型标题</p>
     *
     * @param reportTitle 举报类型标题
     */
    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    /**
     * <p>举报类型描述</p>
     *
     * @return the 举报类型描述
     */
    public String getReportDescription() {
        return reportDescription;
    }

    /**
     * <p>举报类型描述</p>
     *
     * @param reportDescription 举报类型描述
     */
    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

}
