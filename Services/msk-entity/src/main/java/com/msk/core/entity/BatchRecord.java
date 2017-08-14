/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表batch_record对应的BatchRecord。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BatchRecord extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 运行ID */
    private Long runId;
    /** 批处理编码 */
    private String batchCode;
    /** 运行状态 */
    private Integer status;
    /** 运行结果 */
    private String result;
    /**执行类型*/
    private String executeModel;
    /**执行参数*/
    private String parameter;
    /**
     * <p>默认构造函数。</p>
     */
    public BatchRecord() {

    }

    /**
     * <p>运行ID。</p>
     *
     * @return the 运行ID
     */
    public Long getRunId() {
        return runId;
    }

    /**
     * <p>运行ID。</p>
     *
     * @param runId 运行ID。
     */
    public void setRunId(Long runId) {
        this.runId = runId;
    }

    /**
     * <p>批处理编码。</p>
     *
     * @return the 批处理编码
     */
    public String getBatchCode() {
        return batchCode;
    }

    /**
     * <p>批处理编码。</p>
     *
     * @param batchCode 批处理编码。
     */
    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    /**
     * <p>运行状态。</p>
     *
     * @return the 运行状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <p>运行状态。</p>
     *
     * @param status 运行状态。
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <p>运行结果。</p>
     *
     * @return the 运行结果
     */
    public String getResult() {
        return result;
    }

    /**
     * <p>运行结果。</p>
     *
     * @param result 运行结果。
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获得executeModel
     **/
    public String getExecuteModel() {
        return executeModel;
    }

    /**
     * 设置executeModel
     *
     * @param executeModel executeModel
     **/
    public void setExecuteModel(String executeModel) {
        this.executeModel = executeModel;
    }

    /**
     * 获得parameter
     **/
    public String getParameter() {
        return parameter;
    }

    /**
     * 设置parameter
     *
     * @param parameter parameter
     **/
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
