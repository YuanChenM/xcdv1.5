package com.msk.buyers.bean;


import com.msk.core.entity.ByMarketTerminalFileInfo;

/**
 * BY121402Bean.
 *
 */
public class BY121402Bean extends ByMarketTerminalFileInfo
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** 文件名称 */
    private String fileName;
    private String marketId;
    private String fileType;
    private String picType;
    private String fileServerId;
    private String crtTimeStr;

    public BY121402Bean(){

    }
    /**
     * Getter method for property <tt>fileName</tt>.
     *
     * @return property value of fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Setter method for property <tt>fileName</tt>.
     *
     * @param fileName value to be assigned to property fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Getter method for property <tt>marketId</tt>.
     *
     * @return property value of marketId
     */
    public String getMarketId()
    {
        return marketId;
    }

    /**
     * Setter method for property <tt>marketId</tt>.
     *
     * @param marketId value to be assigned to property marketId
     */
    public void setMarketId(String marketId)
    {
        this.marketId = marketId;
    }

    /**
     * Getter method for property <tt>fileType</tt>.
     *
     * @return property value of fileType
     */
    public String getFileType()
    {
        return fileType;
    }

    /**
     * Setter method for property <tt>fileType</tt>.
     *
     * @param fileType value to be assigned to property fileType
     */
    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    /**
     * Getter method for property <tt>picType</tt>.
     *
     * @return property value of picType
     */
    public String getPicType()
    {
        return picType;
    }

    /**
     * Setter method for property <tt>picType</tt>.
     *
     * @param picType value to be assigned to property picType
     */
    public void setPicType(String picType)
    {
        this.picType = picType;
    }

    /**
     * Getter method for property <tt>fileServerId</tt>.
     *
     * @return property value of fileServerId
     */
    public String getFileServerId()
    {
        return fileServerId;
    }

    /**
     * Setter method for property <tt>fileServerId</tt>.
     *
     * @param fileServerId value to be assigned to property fileServerId
     */
    public void setFileServerId(String fileServerId)
    {
        this.fileServerId = fileServerId;
    }

    /**
     * Getter method for property <tt>crtTimeStr</tt>.
     *
     * @return property value of crtTimeStr
     */
    public String getCrtTimeStr()
    {
        return crtTimeStr;
    }

    /**
     * Setter method for property <tt>crtTimeStr</tt>.
     *
     * @param crtTimeStr value to be assigned to property crtTimeStr
     */
    public void setCrtTimeStr(String crtTimeStr)
    {
        this.crtTimeStr = crtTimeStr;
    }
}
