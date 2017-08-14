/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_pd_pkg对应的SlPdPkg。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlPdPkg extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 卖家编码 */
    private String slCode;
    /** 卖家产品ID */
    private Integer slPdId;
    /** 卖家产品包装ID */
    private Integer slPdPkgId;
    /** 产品标准ID */
    private Integer standardId;
    /** (卖家编码+卖家产品ID)内顺序号 */
    private String pkgCode;
    /** 生产商企业ID */
    private Integer prodEpId;
    /** 品牌商企业ID */
    private Integer brandEpId;
    /** 产品品牌ID */
    private Integer brandId;
    /** 产品类别 */
    private String pdClassesCode;
    /** 产品品种 */
    private String pdBreedCode;
    /** 产品特征 */
    private String pdFeatureCode;
    /** 内包装_单个产品规格净重 */
    private String inSglNw;
    /** 内包装_单个产品规格净重误差范围 */
    private String inSglNwRange;
    /** 内包装_净重 */
    private java.math.BigDecimal inNw;
    /** 内包装_个数 */
    private String inNumber;
    /** 内包装_尺寸 */
    private String inSize;
    /** 内包装_材质及技术标准 */
    private String inMts;
    /** 外包装_规格 */
    private String outSpec;
    /** 外包装_净重 */
    private java.math.BigDecimal outNw;
    /** 外包装_毛重 */
    private String outGw;
    /** 外包装_尺寸 */
    private String outSize;
    /** 外包装_材质及技术标准 */
    private String outMts;
    /** 第十种包装信息 */
    private String pkgTen;
    /** 外包装长 */
    private java.math.BigDecimal outLength;
    /** 外包装宽 */
    private java.math.BigDecimal outWidth;
    /** 外包装高 */
    private java.math.BigDecimal outHeight;
    /** 外包装体积 */
    private java.math.BigDecimal outVolume;
    /** 0:待审核,1:审核完成,2:重新审核 */
    private Integer auditStatus;
    /** 1:同意,2:增加标准包装规格,3:要求执行神农客标准 */
    private Integer auditResult;
    /** 审核结果说明 */
    private String auditResultDesc;
    /** 审核人 */
    private String auditor;
    /** 审核日期 */
    private java.util.Date auditDate;
    /** 0:待审核,1:同意,2:不同意 */
    private Integer monitorResult;
    /** 监控人 */
    private String monitorAuditor;
    /** 监控人审核日期 */
    private java.util.Date monitorDate;
    /**
     * <p>默认构造函数。</p>
     */
    public SlPdPkg() {

    }

    /**
     * <p>卖家编码。</p>
     *
     * @return the 卖家编码
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>卖家编码。</p>
     *
     * @param slCode 卖家编码。
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>卖家产品ID。</p>
     *
     * @return the 卖家产品ID
     */
    public Integer getSlPdId() {
        return slPdId;
    }

    /**
     * <p>卖家产品ID。</p>
     *
     * @param slPdId 卖家产品ID。
     */
    public void setSlPdId(Integer slPdId) {
        this.slPdId = slPdId;
    }

    /**
     * <p>卖家产品包装ID。</p>
     *
     * @return the 卖家产品包装ID
     */
    public Integer getSlPdPkgId() {
        return slPdPkgId;
    }

    /**
     * <p>卖家产品包装ID。</p>
     *
     * @param slPdPkgId 卖家产品包装ID。
     */
    public void setSlPdPkgId(Integer slPdPkgId) {
        this.slPdPkgId = slPdPkgId;
    }

    /**
     * <p>产品标准ID。</p>
     *
     * @return the 产品标准ID
     */
    public Integer getStandardId() {
        return standardId;
    }

    /**
     * <p>产品标准ID。</p>
     *
     * @param standardId 产品标准ID。
     */
    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    /**
     * <p>(卖家编码+卖家产品ID)内顺序号。</p>
     *
     * @return the (卖家编码+卖家产品ID)内顺序号
     */
    public String getPkgCode() {
        return pkgCode;
    }

    /**
     * <p>(卖家编码+卖家产品ID)内顺序号。</p>
     *
     * @param pkgCode (卖家编码+卖家产品ID)内顺序号。
     */
    public void setPkgCode(String pkgCode) {
        this.pkgCode = pkgCode;
    }

    /**
     * <p>生产商企业ID。</p>
     *
     * @return the 生产商企业ID
     */
    public Integer getProdEpId() {
        return prodEpId;
    }

    /**
     * <p>生产商企业ID。</p>
     *
     * @param prodEpId 生产商企业ID。
     */
    public void setProdEpId(Integer prodEpId) {
        this.prodEpId = prodEpId;
    }

    /**
     * <p>品牌商企业ID。</p>
     *
     * @return the 品牌商企业ID
     */
    public Integer getBrandEpId() {
        return brandEpId;
    }

    /**
     * <p>品牌商企业ID。</p>
     *
     * @param brandEpId 品牌商企业ID。
     */
    public void setBrandEpId(Integer brandEpId) {
        this.brandEpId = brandEpId;
    }

    /**
     * <p>产品品牌ID。</p>
     *
     * @return the 产品品牌ID
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * <p>产品品牌ID。</p>
     *
     * @param brandId 产品品牌ID。
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * <p>产品类别。</p>
     *
     * @return the 产品类别
     */
    public String getPdClassesCode() {
        return pdClassesCode;
    }

    /**
     * <p>产品类别。</p>
     *
     * @param pdClassesCode 产品类别。
     */
    public void setPdClassesCode(String pdClassesCode) {
        this.pdClassesCode = pdClassesCode;
    }

    /**
     * <p>产品品种。</p>
     *
     * @return the 产品品种
     */
    public String getPdBreedCode() {
        return pdBreedCode;
    }

    /**
     * <p>产品品种。</p>
     *
     * @param pdBreedCode 产品品种。
     */
    public void setPdBreedCode(String pdBreedCode) {
        this.pdBreedCode = pdBreedCode;
    }

    /**
     * <p>产品特征。</p>
     *
     * @return the 产品特征
     */
    public String getPdFeatureCode() {
        return pdFeatureCode;
    }

    /**
     * <p>产品特征。</p>
     *
     * @param pdFeatureCode 产品特征。
     */
    public void setPdFeatureCode(String pdFeatureCode) {
        this.pdFeatureCode = pdFeatureCode;
    }

    /**
     * <p>内包装_单个产品规格净重。</p>
     *
     * @return the 内包装_单个产品规格净重
     */
    public String getInSglNw() {
        return inSglNw;
    }

    /**
     * <p>内包装_单个产品规格净重。</p>
     *
     * @param inSglNw 内包装_单个产品规格净重。
     */
    public void setInSglNw(String inSglNw) {
        this.inSglNw = inSglNw;
    }

    /**
     * <p>内包装_单个产品规格净重误差范围。</p>
     *
     * @return the 内包装_单个产品规格净重误差范围
     */
    public String getInSglNwRange() {
        return inSglNwRange;
    }

    /**
     * <p>内包装_单个产品规格净重误差范围。</p>
     *
     * @param inSglNwRange 内包装_单个产品规格净重误差范围。
     */
    public void setInSglNwRange(String inSglNwRange) {
        this.inSglNwRange = inSglNwRange;
    }

    /**
     * <p>内包装_净重。</p>
     *
     * @return the 内包装_净重
     */
    public java.math.BigDecimal getInNw() {
        return inNw;
    }

    /**
     * <p>内包装_净重。</p>
     *
     * @param inNw 内包装_净重。
     */
    public void setInNw(java.math.BigDecimal inNw) {
        this.inNw = inNw;
    }

    /**
     * <p>内包装_个数。</p>
     *
     * @return the 内包装_个数
     */
    public String getInNumber() {
        return inNumber;
    }

    /**
     * <p>内包装_个数。</p>
     *
     * @param inNumber 内包装_个数。
     */
    public void setInNumber(String inNumber) {
        this.inNumber = inNumber;
    }

    /**
     * <p>内包装_尺寸。</p>
     *
     * @return the 内包装_尺寸
     */
    public String getInSize() {
        return inSize;
    }

    /**
     * <p>内包装_尺寸。</p>
     *
     * @param inSize 内包装_尺寸。
     */
    public void setInSize(String inSize) {
        this.inSize = inSize;
    }

    /**
     * <p>内包装_材质及技术标准。</p>
     *
     * @return the 内包装_材质及技术标准
     */
    public String getInMts() {
        return inMts;
    }

    /**
     * <p>内包装_材质及技术标准。</p>
     *
     * @param inMts 内包装_材质及技术标准。
     */
    public void setInMts(String inMts) {
        this.inMts = inMts;
    }

    /**
     * <p>外包装_规格。</p>
     *
     * @return the 外包装_规格
     */
    public String getOutSpec() {
        return outSpec;
    }

    /**
     * <p>外包装_规格。</p>
     *
     * @param outSpec 外包装_规格。
     */
    public void setOutSpec(String outSpec) {
        this.outSpec = outSpec;
    }

    /**
     * <p>外包装_净重。</p>
     *
     * @return the 外包装_净重
     */
    public java.math.BigDecimal getOutNw() {
        return outNw;
    }

    /**
     * <p>外包装_净重。</p>
     *
     * @param outNw 外包装_净重。
     */
    public void setOutNw(java.math.BigDecimal outNw) {
        this.outNw = outNw;
    }

    /**
     * <p>外包装_毛重。</p>
     *
     * @return the 外包装_毛重
     */
    public String getOutGw() {
        return outGw;
    }

    /**
     * <p>外包装_毛重。</p>
     *
     * @param outGw 外包装_毛重。
     */
    public void setOutGw(String outGw) {
        this.outGw = outGw;
    }

    /**
     * <p>外包装_尺寸。</p>
     *
     * @return the 外包装_尺寸
     */
    public String getOutSize() {
        return outSize;
    }

    /**
     * <p>外包装_尺寸。</p>
     *
     * @param outSize 外包装_尺寸。
     */
    public void setOutSize(String outSize) {
        this.outSize = outSize;
    }

    /**
     * <p>外包装_材质及技术标准。</p>
     *
     * @return the 外包装_材质及技术标准
     */
    public String getOutMts() {
        return outMts;
    }

    /**
     * <p>外包装_材质及技术标准。</p>
     *
     * @param outMts 外包装_材质及技术标准。
     */
    public void setOutMts(String outMts) {
        this.outMts = outMts;
    }

    /**
     * <p>第十种包装信息。</p>
     *
     * @return the 第十种包装信息
     */
    public String getPkgTen() {
        return pkgTen;
    }

    /**
     * <p>第十种包装信息。</p>
     *
     * @param pkgTen 第十种包装信息。
     */
    public void setPkgTen(String pkgTen) {
        this.pkgTen = pkgTen;
    }

    /**
     * <p>外包装长。</p>
     *
     * @return the 外包装长
     */
    public java.math.BigDecimal getOutLength() {
        return outLength;
    }

    /**
     * <p>外包装长。</p>
     *
     * @param outLength 外包装长。
     */
    public void setOutLength(java.math.BigDecimal outLength) {
        this.outLength = outLength;
    }

    /**
     * <p>外包装宽。</p>
     *
     * @return the 外包装宽
     */
    public java.math.BigDecimal getOutWidth() {
        return outWidth;
    }

    /**
     * <p>外包装宽。</p>
     *
     * @param outWidth 外包装宽。
     */
    public void setOutWidth(java.math.BigDecimal outWidth) {
        this.outWidth = outWidth;
    }

    /**
     * <p>外包装高。</p>
     *
     * @return the 外包装高
     */
    public java.math.BigDecimal getOutHeight() {
        return outHeight;
    }

    /**
     * <p>外包装高。</p>
     *
     * @param outHeight 外包装高。
     */
    public void setOutHeight(java.math.BigDecimal outHeight) {
        this.outHeight = outHeight;
    }

    /**
     * <p>外包装体积。</p>
     *
     * @return the 外包装体积
     */
    public java.math.BigDecimal getOutVolume() {
        return outVolume;
    }

    /**
     * <p>外包装体积。</p>
     *
     * @param outVolume 外包装体积。
     */
    public void setOutVolume(java.math.BigDecimal outVolume) {
        this.outVolume = outVolume;
    }

    /**
     * <p>0:待审核,1:审核完成,2:重新审核。</p>
     *
     * @return the 0:待审核,1:审核完成,2:重新审核
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * <p>0:待审核,1:审核完成,2:重新审核。</p>
     *
     * @param auditStatus 0:待审核,1:审核完成,2:重新审核。
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * <p>1:同意,2:增加标准包装规格,3:要求执行神农客标准。</p>
     *
     * @return the 1:同意,2:增加标准包装规格,3:要求执行神农客标准
     */
    public Integer getAuditResult() {
        return auditResult;
    }

    /**
     * <p>1:同意,2:增加标准包装规格,3:要求执行神农客标准。</p>
     *
     * @param auditResult 1:同意,2:增加标准包装规格,3:要求执行神农客标准。
     */
    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    /**
     * <p>审核结果说明。</p>
     *
     * @return the 审核结果说明
     */
    public String getAuditResultDesc() {
        return auditResultDesc;
    }

    /**
     * <p>审核结果说明。</p>
     *
     * @param auditResultDesc 审核结果说明。
     */
    public void setAuditResultDesc(String auditResultDesc) {
        this.auditResultDesc = auditResultDesc;
    }

    /**
     * <p>审核人。</p>
     *
     * @return the 审核人
     */
    public String getAuditor() {
        return auditor;
    }

    /**
     * <p>审核人。</p>
     *
     * @param auditor 审核人。
     */
    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    /**
     * <p>审核日期。</p>
     *
     * @return the 审核日期
     */
    public java.util.Date getAuditDate() {
        return auditDate;
    }

    /**
     * <p>审核日期。</p>
     *
     * @param auditDate 审核日期。
     */
    public void setAuditDate(java.util.Date auditDate) {
        this.auditDate = auditDate;
    }

    /**
     * <p>0:待审核,1:同意,2:不同意。</p>
     *
     * @return the 0:待审核,1:同意,2:不同意
     */
    public Integer getMonitorResult() {
        return monitorResult;
    }

    /**
     * <p>0:待审核,1:同意,2:不同意。</p>
     *
     * @param monitorResult 0:待审核,1:同意,2:不同意。
     */
    public void setMonitorResult(Integer monitorResult) {
        this.monitorResult = monitorResult;
    }

    /**
     * <p>监控人。</p>
     *
     * @return the 监控人
     */
    public String getMonitorAuditor() {
        return monitorAuditor;
    }

    /**
     * <p>监控人。</p>
     *
     * @param monitorAuditor 监控人。
     */
    public void setMonitorAuditor(String monitorAuditor) {
        this.monitorAuditor = monitorAuditor;
    }

    /**
     * <p>监控人审核日期。</p>
     *
     * @return the 监控人审核日期
     */
    public java.util.Date getMonitorDate() {
        return monitorDate;
    }

    /**
     * <p>监控人审核日期。</p>
     *
     * @param monitorDate 监控人审核日期。
     */
    public void setMonitorDate(java.util.Date monitorDate) {
        this.monitorDate = monitorDate;
    }

}
