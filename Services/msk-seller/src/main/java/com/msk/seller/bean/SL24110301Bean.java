package com.msk.seller.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.msk.common.base.BaseBean;

/**
 * 
 * SL24110301Bean.
 *
 * @author Administrator
 */
public class SL24110301Bean extends BaseBean{
/**
 * 
 * pxg
 * 
 * 企业基本资质
 */
    private static final long serialVersionUID = 1L;
    /** 企业ID */
    private Integer epId;
    /** 企业名称 */
    private String epName;
    /** 三证合一营业执照标志 */
    private String licType;
    /** 营业执照_名称 */
    private String licName;
    /** 营业执照_注册号 */
    private String licNo;
    /** 营业执照_住所 */
    private String licAddr;
    /** 营业执照_公司类型 */
    private String licBusiType;
    /** 营业执照_经营范围 */
    private String licBusiScope;
    /** 营业执照_法定代表人姓名 */
    private String licLegalPerson;
    /** 营业执照_注册资本 */
    private java.math.BigDecimal licRegCapital;
    /** 营业执照_实收资本 */
    private java.math.BigDecimal licPaidinCapital;
    /** 营业执照_成立日期 */
    private Date licCrtDate;
    /** 营业执照_营业期限开始 */
    private Date licTermBegin;
    /** 营业执照_营业期限截止 */
    private Date licTermEnd;
    /** 营业执照_营业期限长期标志 */
    private String licTermUnliimited;
    /** 税务登记证_税务登记证号 */
    private String taxNo;
    /** 税务登记证_一般增值税纳税人资格认定编号 */
    private String taxVatNo;
    /** 组织机构代码证_代码 */
    private String orgNo;
    /** 组织机构代码证_有效期开始 */
    private Date orgTermBegin;
    /** 组织机构代码证_有效期截止 */
    private Date orgTermEnd;
    /** 银行开户许可证_法定代表人 */
    private String balLegalPerson;
    /** 银行开户许可证_开户银行 */
    private String balBank;
    /** 银行开户许可证_帐号 */
    private String balAccount;
    
  //营业执照资质描述
    private String licSpeck;
    //税务登记证资质描述
    private String taxSpeck;
    //组织机构代码证资质描述
    private String orgSpeck;
    //银行开户许可证资质描述
    private String balSpeck;
    //三证合一营业执照资质描述
    private String licTypeSpeck;
    //食品流通许可证_许可证编号
    private String fdlNo;
    //食品流通许可证_有效期开始
    private Date fdlTermBegin;
    //食品流通许可证_有效期截止
    private Date fdlTermEnd;
    private String fdlTermEnds;
    //分销授权单位
    private String atuhEpName;
    //OEM委托授权单位
    private String atuhEpName2;
    //营业执照图片路径
    private String licPath;
    //税务登记证图片路径
    private String taxPath;
    //组织代码证
    private String orgNoPath;
    //银行开户许可证
    private String balPath;
    //三证合一营业执照
    private String licTypePath;
    //食品流通许可证
    private String fdlPath;
    //代理及分销授权书
    private String authPath;
    //OEM委托授权
    private String oemAuthPath;
    /**
     * <p>默认构造函数。</p>
     */
    public SL24110301Bean() {

    }

    /**
     * <p>企业ID。</p>
     *
     * @return the 企业ID
     */
    public Integer getEpId() {
        return epId;
    }

    /**
     * <p>企业ID。</p>
     *
     * @param epId 企业ID。
     */
    public void setEpId(Integer epId) {
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
     * <p>三证合一营业执照标志。</p>
     *
     * @return the 三证合一营业执照标志
     */
    public String getLicType() {
        return licType;
    }

    /**
     * <p>三证合一营业执照标志。</p>
     *
     * @param licType 三证合一营业执照标志。
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
     * <p>营业执照_公司类型。</p>
     *
     * @return the 营业执照_公司类型
     */
    public String getLicBusiType() {
        return licBusiType;
    }

    /**
     * <p>营业执照_公司类型。</p>
     *
     * @param licBusiType 营业执照_公司类型。
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
     * <p>营业执照_法定代表人姓名。</p>
     *
     * @return the 营业执照_法定代表人姓名
     */
    public String getLicLegalPerson() {
        return licLegalPerson;
    }

    /**
     * <p>营业执照_法定代表人姓名。</p>
     *
     * @param licLegalPerson 营业执照_法定代表人姓名。
     */
    public void setLicLegalPerson(String licLegalPerson) {
        this.licLegalPerson = licLegalPerson;
    }

    /**
     * <p>营业执照_注册资本。</p>
     *
     * @return the 营业执照_注册资本
     */
    public java.math.BigDecimal getLicRegCapital() {
        return licRegCapital;
    }

    /**
     * <p>营业执照_注册资本。</p>
     *
     * @param licRegCapital 营业执照_注册资本。
     */
    public void setLicRegCapital(java.math.BigDecimal licRegCapital) {
        this.licRegCapital = licRegCapital;
    }

    /**
     * <p>营业执照_实收资本。</p>
     *
     * @return the 营业执照_实收资本
     */
    public java.math.BigDecimal getLicPaidinCapital() {
        return licPaidinCapital;
    }

    /**
     * <p>营业执照_实收资本。</p>
     *
     * @param licPaidinCapital 营业执照_实收资本。
     */
    public void setLicPaidinCapital(java.math.BigDecimal licPaidinCapital) {
        this.licPaidinCapital = licPaidinCapital;
    }

    /**
     * <p>营业执照_成立日期。</p>
     *
     * @return the 营业执照_成立日期
     */
    public Date getLicCrtDate() {
        return licCrtDate;
    }

    /**
     * <p>营业执照_成立日期。</p>
     *
     * @param licCrtDate 营业执照_成立日期。
     */
    public void setLicCrtDate(Date licCrtDate) {
        this.licCrtDate = licCrtDate;
    }

    /**
     * <p>营业执照_营业期限开始。</p>
     *
     * @return the 营业执照_营业期限开始
     */
    public Date getLicTermBegin() {
        return licTermBegin;
    }

    /**
     * <p>营业执照_营业期限开始。</p>
     *
     * @param licTermBegin 营业执照_营业期限开始。
     */
    public void setLicTermBegin(Date licTermBegin) {
        this.licTermBegin = licTermBegin;
    }

    /**
     * <p>营业执照_营业期限截止。</p>
     *
     * @return the 营业执照_营业期限截止
     */
    public Date getLicTermEnd() {
        return licTermEnd;
    }

    /**
     * <p>营业执照_营业期限截止。</p>
     *
     * @param licTermEnd 营业执照_营业期限截止。
     */
    public void setLicTermEnd(Date licTermEnd) {
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
    public Date getOrgTermBegin() {
        return orgTermBegin;
    }

    /**
     * <p>组织机构代码证_有效期开始。</p>
     *
     * @param orgTermBegin 组织机构代码证_有效期开始。
     */
    public void setOrgTermBegin(Date orgTermBegin) {
        this.orgTermBegin = orgTermBegin;
    }

    /**
     * <p>组织机构代码证_有效期截止。</p>
     *
     * @return the 组织机构代码证_有效期截止
     */
    public Date getOrgTermEnd() {
        return orgTermEnd;
    }

    /**
     * <p>组织机构代码证_有效期截止。</p>
     *
     * @param orgTermEnd 组织机构代码证_有效期截止。
     */
    public void setOrgTermEnd(Date orgTermEnd) {
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
     * Get the licSpeck.
     *
     * @return licSpeck
     *
     * @author Administrator
     */
    public String getLicSpeck() {
        return this.licSpeck;
    }

    /**
     * Set the licSpeck.
     *
     * @param licSpeck licSpeck
     *
     * @author Administrator
     */
    public void setLicSpeck(String licSpeck) {
        this.licSpeck = licSpeck;
    }

    /**
     * Get the taxSpeck.
     *
     * @return taxSpeck
     *
     * @author Administrator
     */
    public String getTaxSpeck() {
        return this.taxSpeck;
    }

    /**
     * Set the taxSpeck.
     *
     * @param taxSpeck taxSpeck
     *
     * @author Administrator
     */
    public void setTaxSpeck(String taxSpeck) {
        this.taxSpeck = taxSpeck;
    }

    /**
     * Get the orgSpeck.
     *
     * @return orgSpeck
     *
     * @author Administrator
     */
    public String getOrgSpeck() {
        return this.orgSpeck;
    }

    /**
     * Set the orgSpeck.
     *
     * @param orgSpeck orgSpeck
     *
     * @author Administrator
     */
    public void setOrgSpeck(String orgSpeck) {
        this.orgSpeck = orgSpeck;
    }

    /**
     * Get the balSpeck.
     *
     * @return balSpeck
     *
     * @author Administrator
     */
    public String getBalSpeck() {
        return this.balSpeck;
    }

    /**
     * Set the balSpeck.
     *
     * @param balSpeck balSpeck
     *
     * @author Administrator
     */
    public void setBalSpeck(String balSpeck) {
        this.balSpeck = balSpeck;
    }

    /**
     * Get the licTypeSpeck.
     *
     * @return licTypeSpeck
     *
     * @author Administrator
     */
    public String getLicTypeSpeck() {
        return this.licTypeSpeck;
    }

    /**
     * Set the licTypeSpeck.
     *
     * @param licTypeSpeck licTypeSpeck
     *
     * @author Administrator
     */
    public void setLicTypeSpeck(String licTypeSpeck) {
        this.licTypeSpeck = licTypeSpeck;
    }

    /**
     * Get the fdlNo.
     *
     * @return fdlNo
     *
     * @author Administrator
     */
    public String getFdlNo() {
        return this.fdlNo;
    }

    /**
     * Set the fdlNo.
     *
     * @param fdlNo fdlNo
     *
     * @author Administrator
     */
    public void setFdlNo(String fdlNo) {
        this.fdlNo = fdlNo;
    }

    /**
     * Get the fdlTermBegin.
     *
     * @return fdlTermBegin
     *
     * @author Administrator
     */
    public Date getFdlTermBegin() {
        return this.fdlTermBegin;
    }

    /**
     * Set the fdlTermBegin.
     *
     * @param fdlTermBegin fdlTermBegin
     *
     * @author Administrator
     */
    public void setFdlTermBegin(Date fdlTermBegin) {
        this.fdlTermBegin = fdlTermBegin;
    }

    /**
     * Get the fdlTermEnd.
     *
     * @return fdlTermEnd
     *
     * @author Administrator
     */
    public Date getFdlTermEnd() {
        return this.fdlTermEnd;
    }

    /**
     * Set the fdlTermEnd.
     *
     * @param fdlTermEnd fdlTermEnd
     *
     * @author Administrator
     */
    public void setFdlTermEnd(Date fdlTermEnd) {
        this.fdlTermEnd = fdlTermEnd;
    }

    /**
     * Get the atuhEpName.
     *
     * @return atuhEpName
     *
     * @author Administrator
     */
    public String getAtuhEpName() {
        return this.atuhEpName;
    }

    /**
     * Set the atuhEpName.
     *
     * @param atuhEpName atuhEpName
     *
     * @author Administrator
     */
    public void setAtuhEpName(String atuhEpName) {
        this.atuhEpName = atuhEpName;
    }

    /**
     * Get the atuhEpName2.
     *
     * @return atuhEpName2
     *
     * @author Administrator
     */
    public String getAtuhEpName2() {
        return this.atuhEpName2;
    }

    /**
     * Set the atuhEpName2.
     *
     * @param atuhEpName2 atuhEpName2
     *
     * @author Administrator
     */
    public void setAtuhEpName2(String atuhEpName2) {
        this.atuhEpName2 = atuhEpName2;
    }

    /**
     * Get the licPath.
     *
     * @return licPath
     *
     * @author Administrator
     */
    public String getLicPath() {
        return this.licPath;
    }

    /**
     * Set the licPath.
     *
     * @param licPath licPath
     *
     * @author Administrator
     */
    public void setLicPath(String licPath) {
        this.licPath = licPath;
    }

    /**
     * Get the taxPath.
     *
     * @return taxPath
     *
     * @author Administrator
     */
    public String getTaxPath() {
        return this.taxPath;
    }

    /**
     * Set the taxPath.
     *
     * @param taxPath taxPath
     *
     * @author Administrator
     */
    public void setTaxPath(String taxPath) {
        this.taxPath = taxPath;
    }

    /**
     * Get the orgNoPath.
     *
     * @return orgNoPath
     *
     * @author Administrator
     */
    public String getOrgNoPath() {
        return this.orgNoPath;
    }

    /**
     * Set the orgNoPath.
     *
     * @param orgNoPath orgNoPath
     *
     * @author Administrator
     */
    public void setOrgNoPath(String orgNoPath) {
        this.orgNoPath = orgNoPath;
    }

    /**
     * Get the balPath.
     *
     * @return balPath
     *
     * @author Administrator
     */
    public String getBalPath() {
        return this.balPath;
    }

    /**
     * Set the balPath.
     *
     * @param balPath balPath
     *
     * @author Administrator
     */
    public void setBalPath(String balPath) {
        this.balPath = balPath;
    }

    /**
     * Get the licTypePath.
     *
     * @return licTypePath
     *
     * @author Administrator
     */
    public String getLicTypePath() {
        return this.licTypePath;
    }

    /**
     * Set the licTypePath.
     *
     * @param licTypePath licTypePath
     *
     * @author Administrator
     */
    public void setLicTypePath(String licTypePath) {
        this.licTypePath = licTypePath;
    }

    /**
     * Get the fdlPath.
     *
     * @return fdlPath
     *
     * @author Administrator
     */
    public String getFdlPath() {
        return this.fdlPath;
    }

    /**
     * Set the fdlPath.
     *
     * @param fdlPath fdlPath
     *
     * @author Administrator
     */
    public void setFdlPath(String fdlPath) {
        this.fdlPath = fdlPath;
    }

    /**
     * Get the authPath.
     *
     * @return authPath
     *
     * @author Administrator
     */
    public String getAuthPath() {
        return this.authPath;
    }

    /**
     * Set the authPath.
     *
     * @param authPath authPath
     *
     * @author Administrator
     */
    public void setAuthPath(String authPath) {
        this.authPath = authPath;
    }

    /**
     * Get the oemAuthPath.
     *
     * @return oemAuthPath
     *
     * @author Administrator
     */
    public String getOemAuthPath() {
        return this.oemAuthPath;
    }

    /**
     * Set the oemAuthPath.
     *
     * @param oemAuthPath oemAuthPath
     *
     * @author Administrator
     */
    public void setOemAuthPath(String oemAuthPath) {
        this.oemAuthPath = oemAuthPath;
    }

    /**
     * Get the fdlTermEnds.
     *
     * @return fdlTermEnds
     *
     * @author Administrator
     */
    public String getFdlTermEnds() {
        return this.fdlTermEnds;
    }

    /**
     * Set the fdlTermEnds.
     *
     * @param fdlTermEnds fdlTermEnds
     *
     * @author Administrator
     */
    public void setFdlTermEnds(String fdlTermEnds) {
        this.fdlTermEnds = fdlTermEnds;
    }

}
