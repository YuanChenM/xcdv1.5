package com.msk.seller.bean;


import com.msk.core.entity.BaseEntity;


/**
 * 企业管理团队
 * SL24110306Bean.
 *
 * @author chen_xin
 */
public class SL24110306Bean extends BaseEntity {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    //企业id
    private Long epId;
   // 管理员id
    private Integer memberId;
    //职务
    private String memberDuties;
    //姓名
    private String memberName;
    //年龄
    private Integer memberAge;
    //联系方式
    private String memberTel;
    //图片
    private String image;
    //文化程度
    private String memberEduc;
    /**
     * Get the epId.
     *
     * @return epId
     *
     * @author Administrator
     */
    public Long getEpId() {
        return this.epId;
    }
    /**
     * Set the epId.
     *
     * @param epId epId
     *
     * @author Administrator
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }
    /**
     * Get the memberId.
     *
     * @return memberId
     *
     * @author Administrator
     */
    public Integer getMemberId() {
        return this.memberId;
    }
    /**
     * Set the memberId.
     *
     * @param memberId memberId
     *
     * @author Administrator
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
    /**
     * Get the memberDuties.
     *
     * @return memberDuties
     *
     * @author Administrator
     */
    public String getMemberDuties() {
        return this.memberDuties;
    }
    /**
     * Set the memberDuties.
     *
     * @param memberDuties memberDuties
     *
     * @author Administrator
     */
    public void setMemberDuties(String memberDuties) {
        this.memberDuties = memberDuties;
    }
    /**
     * Get the memberName.
     *
     * @return memberName
     *
     * @author Administrator
     */
    public String getMemberName() {
        return this.memberName;
    }
    /**
     * Set the memberName.
     *
     * @param memberName memberName
     *
     * @author Administrator
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    /**
     * Get the memberAge.
     *
     * @return memberAge
     *
     * @author Administrator
     */
    public Integer getMemberAge() {
        return this.memberAge;
    }
    /**
     * Set the memberAge.
     *
     * @param memberAge memberAge
     *
     * @author Administrator
     */
    public void setMemberAge(Integer memberAge) {
        this.memberAge = memberAge;
    }
    /**
     * Get the memberTel.
     *
     * @return memberTel
     *
     * @author Administrator
     */
    public String getMemberTel() {
        return this.memberTel;
    }
    /**
     * Set the memberTel.
     *
     * @param memberTel memberTel
     *
     * @author Administrator
     */
    public void setMemberTel(String memberTel) {
        this.memberTel = memberTel;
    }
    /**
     * Get the image.
     *
     * @return image
     *
     * @author Administrator
     */
    public String getImage() {
        return this.image;
    }
    /**
     * Set the image.
     *
     * @param image image
     *
     * @author Administrator
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     * Get the memberEduc.
     *
     * @return memberEduc
     *
     * @author Administrator
     */
    public String getMemberEduc() {
        return this.memberEduc;
    }
    /**
     * Set the memberEduc.
     *
     * @param memberEduc memberEduc
     *
     * @author Administrator
     */
    public void setMemberEduc(String memberEduc) {
        this.memberEduc = memberEduc;
    }
    
}