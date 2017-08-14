/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_ep_manager对应的SlEpManager。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlEpManager extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 企业ID */
    private Long epId;
    /** 管理成员ID */
    private Long memberId;
    /** 职务 */
    private String memberDuties;
    /** 姓名 */
    private String memberName;
    /** 年龄 */
    private Integer memberAge;
    /** 文化程度 */
    private String memberEduc;
    /** 联系电话 */
    private String memberTel;
    /**
     * <p>默认构造函数。</p>
     */
    public SlEpManager() {

    }

    /**
     * <p>企业ID。</p>
     *
     * @return the 企业ID
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * <p>企业ID。</p>
     *
     * @param epId 企业ID。
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * <p>管理成员ID。</p>
     *
     * @return the 管理成员ID
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * <p>管理成员ID。</p>
     *
     * @param memberId 管理成员ID。
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * <p>职务。</p>
     *
     * @return the 职务
     */
    public String getMemberDuties() {
        return memberDuties;
    }

    /**
     * <p>职务。</p>
     *
     * @param memberDuties 职务。
     */
    public void setMemberDuties(String memberDuties) {
        this.memberDuties = memberDuties;
    }

    /**
     * <p>姓名。</p>
     *
     * @return the 姓名
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * <p>姓名。</p>
     *
     * @param memberName 姓名。
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * <p>年龄。</p>
     *
     * @return the 年龄
     */
    public Integer getMemberAge() {
        return memberAge;
    }

    /**
     * <p>年龄。</p>
     *
     * @param memberAge 年龄。
     */
    public void setMemberAge(Integer memberAge) {
        this.memberAge = memberAge;
    }

    /**
     * <p>文化程度。</p>
     *
     * @return the 文化程度
     */
    public String getMemberEduc() {
        return memberEduc;
    }

    /**
     * <p>文化程度。</p>
     *
     * @param memberEduc 文化程度。
     */
    public void setMemberEduc(String memberEduc) {
        this.memberEduc = memberEduc;
    }

    /**
     * <p>联系电话。</p>
     *
     * @return the 联系电话
     */
    public String getMemberTel() {
        return memberTel;
    }

    /**
     * <p>联系电话。</p>
     *
     * @param memberTel 联系电话。
     */
    public void setMemberTel(String memberTel) {
        this.memberTel = memberTel;
    }

}
