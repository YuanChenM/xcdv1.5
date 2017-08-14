package com.msk.batch.bean;
/**
 * *BatchParam
 * *@author jiang_nan
 * *@version 1.0
 **/
public class BatchParam extends BaseParam{
    private String batchNewStatus;
    private String result;
    private String batchCode;
    private String batchOldStatus;

    /**
     * 获取batchNewStatus
     *
     * @return batchNewStatus batchNewStatus
     */
    public String getBatchNewStatus() {
        return batchNewStatus;
    }

    /**
     * 设置batchNewStatus
     *
     * @param batchNewStatus batchNewStatus
     */
    public void setBatchNewStatus(String batchNewStatus) {
        this.batchNewStatus = batchNewStatus;
    }

    /**
     * 获取result
     *
     * @return result result
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置result
     *
     * @param result result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取batchCode
     *
     * @return batchCode batchCode
     */
    public String getBatchCode() {
        return batchCode;
    }

    /**
     * 设置batchCode
     *
     * @param batchCode batchCode
     */
    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    /**
     * 获取batchOldStatus
     *
     * @return batchOldStatus batchOldStatus
     */
    public String getBatchOldStatus() {
        return batchOldStatus;
    }

    /**
     * 设置batchOldStatus
     *
     * @param batchOldStatus batchOldStatus
     */
    public void setBatchOldStatus(String batchOldStatus) {
        this.batchOldStatus = batchOldStatus;
    }
}
