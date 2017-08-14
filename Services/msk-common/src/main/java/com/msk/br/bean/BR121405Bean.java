package com.msk.br.bean;

import com.msk.core.entity.BrFileBuyerPool;

public class BR121405Bean extends BrFileBuyerPool {

    private String fileStartTimeStr;
    private String fileEndTimeStr;
    private String fileCreateTimeStr;
    private String marketLevel;
    /**
     * Getter method for property <tt>fileStartTimeStr</tt>.
     *
     * @return property value of fileStartTimeStr
     */
    public String getFileStartTimeStr() {
        return fileStartTimeStr;
    }

    /**
     * Setter method for property <tt>fileStartTimeStr</tt>.
     *
     * @param fileStartTimeStr value to be assigned to property fileStartTimeStr
     */
    public void setFileStartTimeStr(String fileStartTimeStr) {
        this.fileStartTimeStr = fileStartTimeStr;
    }

    /**
     * Getter method for property <tt>fileEndTimeStr</tt>.
     *
     * @return property value of fileEndTimeStr
     */
    public String getFileEndTimeStr() {
        return fileEndTimeStr;
    }

    /**
     * Setter method for property <tt>fileEndTimeStr</tt>.
     *
     * @param fileEndTimeStr value to be assigned to property fileEndTimeStr
     */
    public void setFileEndTimeStr(String fileEndTimeStr) {
        this.fileEndTimeStr = fileEndTimeStr;
    }

    /**
     * Getter method for property <tt>fileCreateTimeStr</tt>.
     *
     * @return property value of fileCreateTimeStr
     */
    public String getFileCreateTimeStr() {
        return fileCreateTimeStr;
    }

    /**
     * Setter method for property <tt>fileCreateTimeStr</tt>.
     *
     * @param fileCreateTimeStr value to be assigned to property fileCreateTimeStr
     */
    public void setFileCreateTimeStr(String fileCreateTimeStr) {
        this.fileCreateTimeStr = fileCreateTimeStr;
    }

    /**
     * Getter method for property <tt>marketLevel</tt>.
     *
     * @return property value of marketLevel
     */
    public String getMarketLevel() {
        return marketLevel;
    }

    /**
     * Setter method for property <tt>marketLevel</tt>.
     *
     * @param marketLevel value to be assigned to property marketLevel
     */
    public void setMarketLevel(String marketLevel) {
        this.marketLevel = marketLevel;
    }
}
