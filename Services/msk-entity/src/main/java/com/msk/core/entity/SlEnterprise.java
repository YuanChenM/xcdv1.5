/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_enterprise对应的SlEnterprise。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlEnterprise extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 企业ID */
    private Long epId;
    /** 企业名称 */
    private String epName;
    /** 0:普通,1:三证合一营业执照 */
    private String licType;
    /** 营业执照_名称 */
    private String licName;
    /** 营业执照_注册号 */
    private String licNo;
    /** 营业执照_住所 */
    private String licAddr;
    /** 营业执照_经营类型 */
    private String licBusiType;
    /** 营业执照_经营范围 */
    private String licBusiScope;
    /** 营业执照_法人代表 */
    private String licLegalPerson;
    /** 单位为万元 */
    private java.math.BigDecimal licRegCapital;
    /** 单位为万元 */
    private java.math.BigDecimal licPaidinCapital;
    /** 营业执照_成立日期 */
    private java.util.Date licCrtDate;
    /** 营业执照_营业期限开始 */
    private java.util.Date licTermBegin;
    /** 营业执照_营业期限截止 */
    private java.util.Date licTermEnd;
    /** 营业执照_营业期限长期标志 */
    private String licTermUnliimited;
    /** 税务登记证_税务登记证号 */
    private String taxNo;
    /** 税务登记证_一般增值税纳税人资格认定编号 */
    private String taxVatNo;
    /** 组织机构代码证_代码 */
    private String orgNo;
    /** 组织机构代码证_有效期开始 */
    private java.util.Date orgTermBegin;
    /** 组织机构代码证_有效期截止 */
    private java.util.Date orgTermEnd;
    /** 银行开户许可证_法定代表人 */
    private String balLegalPerson;
    /** 银行开户许可证_开户银行 */
    private String balBank;
    /** 银行开户许可证_帐号 */
    private String balAccount;
    /** 食品流通许可证_许可证编号 */
    private String fdlNo;
    /** 食品流通许可证_有效期开始 */
    private java.util.Date fdlTermBegin;
    /** 食品流通许可证_有效期截止 */
    private java.util.Date fdlTermEnd;
    /**
     * <p>默认构造函数。</p>
     */
    public SlEnterprise() {

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
     * <p>企业名称。</p>
     *
     * @return the 企业名称
     */
    public String getEpName() {
        return epName;
    }

    /**
     * <p>企业名称。</p>
     *
     * @param epName 企业名称。
     */
    public void setEpName(String epName) {
        this.epName = epName;
    }

    /**
     * <p>0:普通,1:三证合一营业执照。</p>
     *
     * @return the 0:普通,1:三证合一营业执照
     */
    public String getLicType() {
        return licType;
    }

    /**
     * <p>0:普通,1:三证合一营业执照。</p>
     *
     * @param licType 0:普通,1:三证合一营业执照。
     */
    public void setLicType(String licType) {
        this.licType = licType;
    }

    /**
     * <p>营业执照_名称。</p>
     *
     * @return the 营业执照_名称
     */
    public String getLicName() {
        return licName;
    }

    /**
     * <p>营业执照_名称。</p>
     *
     * @param licName 营业执照_名称。
     */
    public void setLicName(String licName) {
        this.licName = licName;
    }

    /**
     * <p>营业执照_注册号。</p>
     *
     * @return the 营业执照_注册号
     */
    public String getLicNo() {
        return licNo;
    }

    /**
     * <p>营业执照_注册号。</p>
     *
     * @param licNo 营业执照_注册号。
     */
    public void setLicNo(String licNo) {
        this.licNo = licNo;
    }

    /**
     * <p>营业执照_住所。</p>
     *
     * @return the 营业执照_住所
     */
    public String getLicAddr() {
        return licAddr;
    }

    /**
     * <p>营业执照_住所。</p>
     *
     * @param licAddr 营业执照_住所。
     */
    public void setLicAddr(String licAddr) {
        this.licAddr = licAddr;
    }

    /**
     * <p>营业执照_经营类型。</p>
     *
     * @return the 营业执照_经营类型
     */
    public String getLicBusiType() {
        return licBusiType;
    }

    /**
     * <p>营业执照_经营类型。</p>
     *
     * @param licBusiType 营业执照_经营类型。
     */
    public void setLicBusiType(String licBusiType) {
        this.licBusiType = licBusiType;
    }

    /**
     * <p>营业执照_经营范围。</p>
     *
     * @return the 营业执照_经营范围
     */
    public String getLicBusiScope() {
        return licBusiScope;
    }

    /**
     * <p>营业执照_经营范围。</p>
     *
     * @param licBusiScope 营业执照_经营范围。
     */
    public void setLicBusiScope(String licBusiScope) {
        this.licBusiScope = licBusiScope;
    }

    /**
     * <p>营业执照_法人代表。</p>
     *
     * @return the 营业执照_法人代表
     */
    public String getLicLegalPerson() {
        return licLegalPerson;
    }

    /**
     * <p>营业执照_法人代表。</p>
     *
     * @param licLegalPerson 营业执照_法人代表。
     */
    public void setLicLegalPerson(String licLegalPerson) {
        this.licLegalPerson = licLegalPerson;
    }

    /**
     * <p>单位为万元。</p>
     *
     * @return the 单位为万元
     */
    public java.math.BigDecimal getLicRegCapital() {
        return licRegCapital;
    }

    /**
     * <p>单位为万元。</p>
     *
     * @param licRegCapital 单位为万元。
     */
    public void setLicRegCapital(java.math.BigDecimal licRegCapital) {
        this.licRegCapital = licRegCapital;
    }

    /**
     * <p>单位为万元。</p>
     *
     * @return the 单位为万元
     */
    public java.math.BigDecimal getLicPaidinCapital() {
        return licPaidinCapital;
    }

    /**
     * <p>单位为万元。</p>
     *
     * @param licPaidinCapital 单位为万元。
     */
    public void setLicPaidinCapital(java.math.BigDecimal licPaidinCapital) {
        this.licPaidinCapital = licPaidinCapital;
    }

    /**
     * <p>营业执照_成立日期。</p>
     *
     * @return the 营业执照_成立日期
     */
    public java.util.Date getLicCrtDate() {
        return licCrtDate;
    }

    /**
     * <p>营业执照_成立日期。</p>
     *
     * @param licCrtDate 营业执照_成立日期。
     */
    public void setLicCrtDate(java.util.Date licCrtDate) {
        this.licCrtDate = licCrtDate;
    }

    /**
     * <p>营业执照_营业期限开始。</p>
     *
     * @return the 营业执照_营业期限开始
     */
    public java.util.Date getLicTermBegin() {
        return licTermBegin;
    }

    /**
     * <p>营业执照_营业期限开始。</p>
     *
     * @param licTermBegin 营业执照_营业期限开始。
     */
    public void setLicTermBegin(java.util.Date licTermBegin) {
        this.licTermBegin = licTermBegin;
    }

    /**
     * <p>营业执照_营业期限截止。</p>
     *
     * @return the 营业执照_营业期限截止
     */
    public java.util.Date getLicTermEnd() {
        return licTermEnd;
    }

    /**
     * <p>营业执照_营业期限截止。</p>
     *
     * @param licTermEnd 营业执照_营业期限截止。
     */
    public void setLicTermEnd(java.util.Date licTermEnd) {
        this.licTermEnd = licTermEnd;
    }

    /**
     * <p>营业执照_营业期限长期标志。</p>
     *
     * @return the 营业执照_营业期限长期标志
     */
    public String getLicTermUnliimited() {
        return licTermUnliimited;
    }

    /**
     * <p>营业执照_营业期限长期标志。</p>
     *
     * @param licTermUnliimited 营业执照_营业期限长期标志。
     */
    public void setLicTermUnliimited(String licTermUnliimited) {
        this.licTermUnliimited = licTermUnliimited;
    }

    /**
     * <p>税务登记证_税务登记证号。</p>
     *
     * @return the 税务登记证_税务登记证号
     */
    public String getTaxNo() {
        return taxNo;
    }

    /**
     * <p>税务登记证_税务登记证号。</p>
     *
     * @param taxNo 税务登记证_税务登记证号。
     */
    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    /**
     * <p>税务登记证_一般增值税纳税人资格认定编号。</p>
     *
     * @return the 税务登记证_一般增值税纳税人资格认定编号
     */
    public String getTaxVatNo() {
        return taxVatNo;
    }

    /**
     * <p>税务登记证_一般增值税纳税人资格认定编号。</p>
     *
     * @param taxVatNo 税务登记证_一般增值税纳税人资格认定编号。
     */
    public void setTaxVatNo(String taxVatNo) {
        this.taxVatNo = taxVatNo;
    }

    /**
     * <p>组织机构代码证_代码。</p>
     *
     * @return the 组织机构代码证_代码
     */
    public String getOrgNo() {
        return orgNo;
    }

    /**
     * <p>组织机构代码证_代码。</p>
     *
     * @param orgNo 组织机构代码证_代码。
     */
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    /**
     * <p>组织机构代码证_有效期开始。</p>
     *
     * @return the 组织机构代码证_有效期开始
     */
    public java.util.Date getOrgTermBegin() {
        return orgTermBegin;
    }

    /**
     * <p>组织机构代码证_有效期开始。</p>
     *
     * @param orgTermBegin 组织机构代码证_有效期开始。
     */
    public void setOrgTermBegin(java.util.Date orgTermBegin) {
        this.orgTermBegin = orgTermBegin;
    }

    /**
     * <p>组织机构代码证_有效期截止。</p>
     *
     * @return the 组织机构代码证_有效期截止
     */
    public java.util.Date getOrgTermEnd() {
        return orgTermEnd;
    }

    /**
     * <p>组织机构代码证_有效期截止。</p>
     *
     * @param orgTermEnd 组织机构代码证_有效期截止。
     */
    public void setOrgTermEnd(java.util.Date orgTermEnd) {
        this.orgTermEnd = orgTermEnd;
    }

    /**
     * <p>银行开户许可证_法定代表人。</p>
     *
     * @return the 银行开户许可证_法定代表人
     */
    public String getBalLegalPerson() {
        return balLegalPerson;
    }

    /**
     * <p>银行开户许可证_法定代表人。</p>
     *
     * @param balLegalPerson 银行开户许可证_法定代表人。
     */
    public void setBalLegalPerson(String balLegalPerson) {
        this.balLegalPerson = balLegalPerson;
    }

    /**
     * <p>银行开户许可证_开户银行。</p>
     *
     * @return the 银行开户许可证_开户银行
     */
    public String getBalBank() {
        return balBank;
    }

    /**
     * <p>银行开户许可证_开户银行。</p>
     *
     * @param balBank 银行开户许可证_开户银行。
     */
    public void setBalBank(String balBank) {
        this.balBank = balBank;
    }

    /**
     * <p>银行开户许可证_帐号。</p>
     *
     * @return the 银行开户许可证_帐号
     */
    public String getBalAccount() {
        return balAccount;
    }

    /**
     * <p>银行开户许可证_帐号。</p>
     *
     * @param balAccount 银行开户许可证_帐号。
     */
    public void setBalAccount(String balAccount) {
        this.balAccount = balAccount;
    }

    /**
     * <p>食品流通许可证_许可证编号。</p>
     *
     * @return the 食品流通许可证_许可证编号
     */
    public String getFdlNo() {
        return fdlNo;
    }

    /**
     * <p>食品流通许可证_许可证编号。</p>
     *
     * @param fdlNo 食品流通许可证_许可证编号。
     */
    public void setFdlNo(String fdlNo) {
        this.fdlNo = fdlNo;
    }

    /**
     * <p>食品流通许可证_有效期开始。</p>
     *
     * @return the 食品流通许可证_有效期开始
     */
    public java.util.Date getFdlTermBegin() {
        return fdlTermBegin;
    }

    /**
     * <p>食品流通许可证_有效期开始。</p>
     *
     * @param fdlTermBegin 食品流通许可证_有效期开始。
     */
    public void setFdlTermBegin(java.util.Date fdlTermBegin) {
        this.fdlTermBegin = fdlTermBegin;
    }

    /**
     * <p>食品流通许可证_有效期截止。</p>
     *
     * @return the 食品流通许可证_有效期截止
     */
    public java.util.Date getFdlTermEnd() {
        return fdlTermEnd;
    }

    /**
     * <p>食品流通许可证_有效期截止。</p>
     *
     * @param fdlTermEnd 食品流通许可证_有效期截止。
     */
    public void setFdlTermEnd(java.util.Date fdlTermEnd) {
        this.fdlTermEnd = fdlTermEnd;
    }

}
