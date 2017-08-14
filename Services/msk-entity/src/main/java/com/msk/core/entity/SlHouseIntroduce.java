/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_house_introduce对应的SlHouseIntroduce</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlHouseIntroduce extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 自我介绍ID */
    private Long intId;
    /** 用于登录的卖家账号 */
    private String houseCode;
    /** 个人感悟 */
    private String introduce;
    /** 服务承诺 */
    private String serviceCommit;
    /** 文件上传方式（0：文件服务器；1：ftp服务器） */
    private String uploadUrl1Type;
    /** 证件照正面 */
    private String uploadUrl1;
    /** 文件上传方式（0：文件服务器；1：ftp服务器） */
    private String uploadUrl2Type;
    /** 证件照反面 */
    private String uploadUrl2;
    /** 文件上传方式（0：文件服务器；1：ftp服务器） */
    private String uploadUrl3Type;
    /** 工作照 */
    private String uploadUrl3;
    /**
     * <p>默认构造函数</p>
     */
    public SlHouseIntroduce() {

    }

    /**
     * <p>自我介绍ID</p>
     *
     * @return the 自我介绍ID
     */
    public Long getIntId() {
        return intId;
    }

    /**
     * <p>自我介绍ID</p>
     *
     * @param intId 自我介绍ID
     */
    public void setIntId(Long intId) {
        this.intId = intId;
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
     * <p>个人感悟</p>
     *
     * @return the 个人感悟
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * <p>个人感悟</p>
     *
     * @param introduce 个人感悟
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * <p>服务承诺</p>
     *
     * @return the 服务承诺
     */
    public String getServiceCommit() {
        return serviceCommit;
    }

    /**
     * <p>服务承诺</p>
     *
     * @param serviceCommit 服务承诺
     */
    public void setServiceCommit(String serviceCommit) {
        this.serviceCommit = serviceCommit;
    }

    /**
     * <p>文件上传方式（0：文件服务器；1：ftp服务器）</p>
     *
     * @return the 文件上传方式（0：文件服务器；1：ftp服务器）
     */
    public String getUploadUrl1Type() {
        return uploadUrl1Type;
    }

    /**
     * <p>文件上传方式（0：文件服务器；1：ftp服务器）</p>
     *
     * @param uploadUrl1Type 文件上传方式（0：文件服务器；1：ftp服务器）
     */
    public void setUploadUrl1Type(String uploadUrl1Type) {
        this.uploadUrl1Type = uploadUrl1Type;
    }

    /**
     * <p>证件照正面</p>
     *
     * @return the 证件照正面
     */
    public String getUploadUrl1() {
        return uploadUrl1;
    }

    /**
     * <p>证件照正面</p>
     *
     * @param uploadUrl1 证件照正面
     */
    public void setUploadUrl1(String uploadUrl1) {
        this.uploadUrl1 = uploadUrl1;
    }

    /**
     * <p>文件上传方式（0：文件服务器；1：ftp服务器）</p>
     *
     * @return the 文件上传方式（0：文件服务器；1：ftp服务器）
     */
    public String getUploadUrl2Type() {
        return uploadUrl2Type;
    }

    /**
     * <p>文件上传方式（0：文件服务器；1：ftp服务器）</p>
     *
     * @param uploadUrl2Type 文件上传方式（0：文件服务器；1：ftp服务器）
     */
    public void setUploadUrl2Type(String uploadUrl2Type) {
        this.uploadUrl2Type = uploadUrl2Type;
    }

    /**
     * <p>证件照反面</p>
     *
     * @return the 证件照反面
     */
    public String getUploadUrl2() {
        return uploadUrl2;
    }

    /**
     * <p>证件照反面</p>
     *
     * @param uploadUrl2 证件照反面
     */
    public void setUploadUrl2(String uploadUrl2) {
        this.uploadUrl2 = uploadUrl2;
    }

    /**
     * <p>文件上传方式（0：文件服务器；1：ftp服务器）</p>
     *
     * @return the 文件上传方式（0：文件服务器；1：ftp服务器）
     */
    public String getUploadUrl3Type() {
        return uploadUrl3Type;
    }

    /**
     * <p>文件上传方式（0：文件服务器；1：ftp服务器）</p>
     *
     * @param uploadUrl3Type 文件上传方式（0：文件服务器；1：ftp服务器）
     */
    public void setUploadUrl3Type(String uploadUrl3Type) {
        this.uploadUrl3Type = uploadUrl3Type;
    }

    /**
     * <p>工作照</p>
     *
     * @return the 工作照
     */
    public String getUploadUrl3() {
        return uploadUrl3;
    }

    /**
     * <p>工作照</p>
     *
     * @param uploadUrl3 工作照
     */
    public void setUploadUrl3(String uploadUrl3) {
        this.uploadUrl3 = uploadUrl3;
    }

}
