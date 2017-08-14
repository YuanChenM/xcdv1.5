package com.msk.seller.bean;

import java.util.List;

import com.msk.common.base.BaseBean;

public class SL24110307Bean extends BaseBean{
/**
 * 
 * pxg
 * 
 * 卖家电商团队
 */
    //卖家编码
    private String slCode;
    //成员ID
    private String memberId;
    //是否负责人
    private String leaderFlg;
    //姓名
    private String memberName;
    //年龄
    private String memberAge;
    //文化程度
    private String memberEduc;
    //联系方式
    private String memberTel;
    //删除标志
    private String delFlg;
    //负责人
    private String leaderPath;
    //团队成员
    private List noLeaderPath;
    //团队成员描述
    private String noLeaderDeil;
    /** 创建者ID */
    private String crtEmplId;
    /** 创建日时 */
    private java.util.Date crtTime;
    /** 更新者ID */
    private String updEmplId;
    /** 更新日时 */
    private java.util.Date updTime;
    /** 生效者ID */
    private String actEmplId;
    /** 生效日时 */
    private java.util.Date actTime;
    /** 版本号 */
    private Integer ver;
    /**成员图片路径*/
    private String imagePath;

    /**
     * Getter method for property <tt>imagePath</tt>.
     *
     * @return property value of imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Setter method for property <tt>imagePath</tt>.
     *
     * @param imagePath value to be assigned to property imagePath
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSlCode() {
        return this.slCode;
    }
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }
    public String getMemberId() {
        return this.memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getLeaderFlg() {
        return this.leaderFlg;
    }
    public void setLeaderFlg(String leaderFlg) {
        this.leaderFlg = leaderFlg;
    }
    public String getMemberName() {
        return this.memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getMemberAge() {
        return this.memberAge;
    }
    public void setMemberAge(String memberAge) {
        this.memberAge = memberAge;
    }
    public String getMemberEduc() {
        return this.memberEduc;
    }
    public void setMemberEduc(String memberEduc) {
        this.memberEduc = memberEduc;
    }
    public String getMemberTel() {
        return this.memberTel;
    }
    public void setMemberTel(String memberTel) {
        this.memberTel = memberTel;
    }
    public String getDelFlg() {
        return this.delFlg;
    }
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }
    public String getCrtEmplId() {
        return this.crtEmplId;
    }
    public void setCrtEmplId(String crtEmplId) {
        this.crtEmplId = crtEmplId;
    }
    public java.util.Date getCrtTime() {
        return this.crtTime;
    }
    public void setCrtTime(java.util.Date crtTime) {
        this.crtTime = crtTime;
    }
    public String getUpdEmplId() {
        return this.updEmplId;
    }
    public void setUpdEmplId(String updEmplId) {
        this.updEmplId = updEmplId;
    }
    public java.util.Date getUpdTime() {
        return this.updTime;
    }
    public void setUpdTime(java.util.Date updTime) {
        this.updTime = updTime;
    }
    public String getActEmplId() {
        return this.actEmplId;
    }
    public void setActEmplId(String actEmplId) {
        this.actEmplId = actEmplId;
    }
    public java.util.Date getActTime() {
        return this.actTime;
    }
    public void setActTime(java.util.Date actTime) {
        this.actTime = actTime;
    }
    public Integer getVer() {
        return this.ver;
    }
    public void setVer(Integer ver) {
        this.ver = ver;
    }
    public String getLeaderPath() {
        return this.leaderPath;
    }
    public void setLeaderPath(String leaderPath) {
        this.leaderPath = leaderPath;
    }
    public List getNoLeaderPath() {
        return this.noLeaderPath;
    }
    public void setNoLeaderPath(List noLeaderPath) {
        this.noLeaderPath = noLeaderPath;
    }
    /**
     * Get the noLeaderDeil.
     *
     * @return noLeaderDeil
     *
     * @author Administrator
     */
    public String getNoLeaderDeil() {
        return this.noLeaderDeil;
    }
    /**
     * Set the noLeaderDeil.
     *
     * @param noLeaderDeil noLeaderDeil
     *
     * @author Administrator
     */
    public void setNoLeaderDeil(String noLeaderDeil) {
        this.noLeaderDeil = noLeaderDeil;
    }
}
