/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_market_terminal_file_info对应的ByMarketTerminalFileInfo</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByMarketTerminalFileInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** 批发市场ID */
    private String marketId;
    /** 文件类型(1:文件类0:图片) */
    private String fileType;
    /** 图片类型(1:证件类0:非证件类) */
    private String picType;
    /** 文件服务器标识符 */
    private String fileServerId;
    /** 文件名称 */
    private String fileName;
    /** 文件后缀名 */
    private String fileSuff;
    /**
     * <p>默认构造函数</p>
     */
    public ByMarketTerminalFileInfo() {

    }

    /**
     * <p>ID</p>
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID</p>
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>批发市场ID</p>
     *
     * @return the 批发市场ID
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * <p>批发市场ID</p>
     *
     * @param marketId 批发市场ID
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    /**
     * <p>文件类型(1:文件类0:图片)</p>
     *
     * @return the 文件类型(1:文件类0:图片)
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * <p>文件类型(1:文件类0:图片)</p>
     *
     * @param fileType 文件类型(1:文件类0:图片)
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * <p>图片类型(1:证件类0:非证件类)</p>
     *
     * @return the 图片类型(1:证件类0:非证件类)
     */
    public String getPicType() {
        return picType;
    }

    /**
     * <p>图片类型(1:证件类0:非证件类)</p>
     *
     * @param picType 图片类型(1:证件类0:非证件类)
     */
    public void setPicType(String picType) {
        this.picType = picType;
    }

    /**
     * <p>文件服务器标识符</p>
     *
     * @return the 文件服务器标识符
     */
    public String getFileServerId() {
        return fileServerId;
    }

    /**
     * <p>文件服务器标识符</p>
     *
     * @param fileServerId 文件服务器标识符
     */
    public void setFileServerId(String fileServerId) {
        this.fileServerId = fileServerId;
    }

    /**
     * <p>文件名称</p>
     *
     * @return the 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * <p>文件名称</p>
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * <p>文件后缀名</p>
     *
     * @return the 文件后缀名
     */
    public String getFileSuff() {
        return fileSuff;
    }

    /**
     * <p>文件后缀名</p>
     *
     * @param fileSuff 文件后缀名
     */
    public void setFileSuff(String fileSuff) {
        this.fileSuff = fileSuff;
    }

}
