/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_product对应的SlProduct。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlProduct extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 卖家ID */
    private String slCode;
    /** 卖家产品ID */
    private Integer slPdId;
    /** 生产商企业ID */
    private Integer prodEpId;
    /** 品牌商企业ID */
    private Integer brandEpId;
    /** 产品品牌ID */
    private Integer brandId;
    /** 产品类别 */
    private String pdClassesCode;
    /** 产品二级分类编码 */
    private String machiningCode;
    /** 产品品种 */
    private String pdBreedCode;
    /** 产品特征 */
    private String pdFeatureCode;
    /** 净重编码 */
    private String weightCode;
    /** 0:否,1:是 */
    private String distFlg;
    /** 0:否,1:是 */
    private String distmskFlg;
    /** 美侍客分销折扣率 */
    private java.math.BigDecimal distmskRate;
    /** 卖家产品技术标准 */
    private String slTncStd;
    /** 产品技术标准定级(加工质量标准) */
    private Integer slTncGradeCode;
    /** 产品技术标准定级不通过理由 */
    private String tncNgReason;
    /** 产品技术标准定级状态 */
    private Integer tncAuditStatus;
    /** 产品技术标准定级人 */
    private String tncAuditor;
    /** 产品技术标准定级日期 */
    private java.util.Date tncAuditDate;
    /** 产品技术标准定级监控人审核意见 */
    private Integer tncMonitorResult;
    /** 产品技术标准定级监控人 */
    private String tncMonitorAuditor;
    /** 产品技术标准定级监控人审核日期 */
    private java.util.Date tncMonitorDate;
    /** 卖家产品质量标准（加工技术标准） */
    private String slQltStd;
    /** 产品质量标准定级 */
    private Integer slQltGradeCode;
    /** 产品质量标准定级不通过理由 */
    private String qltNgReason;
    /** 产品质量标准定级状态 */
    private Integer qltAuditStatus;
    /** 产品质量标准定级人 */
    private String qltAuditor;
    /** 产品质量标准定级日期 */
    private java.util.Date qltAuditDate;
    /** 产品质量标准定级监控人审核意见 */
    private Integer qltMonitorResult;
    /** 产品质量标准定级监控人 */
    private String qltMonitorAuditor;
    /** 产品质量标准定级监控人审核日期 */
    private java.util.Date qltMonitorDate;
    /** 1 申请中 2论证中 3禁止准入 4试销 5正式上线 6下线  7黑名单 */
    private String status;
    /** 产品状态审核意见 */
    private Integer statusMonitorResult;
    /** 产品状态审核人 */
    private String statusMonitorAuditor;
    /** 产品状态审核日期 */
    private java.util.Date statusMonitorDate;
    /** 产品类别名称 */
    private String pdClassesName;
    /** 产品二级分类名称 */
    private String machiningName;
    /** 产品品种名称 */
    private String pdBreedName;
    /** 产品特征名称 */
    private String pdFeatureName;
    /** 产品净重名称 */
    private String weightName;
    /**
     * <p>默认构造函数。</p>
     */
    public SlProduct() {

    }

    /**
     * <p>卖家ID。</p>
     *
     * @return the 卖家ID
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>卖家ID。</p>
     *
     * @param slCode 卖家ID。
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
     * <p>产品二级分类编码。</p>
     *
     * @return the 产品二级分类编码
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>产品二级分类编码。</p>
     *
     * @param machiningCode 产品二级分类编码。
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
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
     * <p>净重编码。</p>
     *
     * @return the 净重编码
     */
    public String getWeightCode() {
        return weightCode;
    }

    /**
     * <p>净重编码。</p>
     *
     * @param weightCode 净重编码。
     */
    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * <p>0:否,1:是。</p>
     *
     * @return the 0:否,1:是
     */
    public String getDistFlg() {
        return distFlg;
    }

    /**
     * <p>0:否,1:是。</p>
     *
     * @param distFlg 0:否,1:是。
     */
    public void setDistFlg(String distFlg) {
        this.distFlg = distFlg;
    }

    /**
     * <p>0:否,1:是。</p>
     *
     * @return the 0:否,1:是
     */
    public String getDistmskFlg() {
        return distmskFlg;
    }

    /**
     * <p>0:否,1:是。</p>
     *
     * @param distmskFlg 0:否,1:是。
     */
    public void setDistmskFlg(String distmskFlg) {
        this.distmskFlg = distmskFlg;
    }

    /**
     * <p>美侍客分销折扣率。</p>
     *
     * @return the 美侍客分销折扣率
     */
    public java.math.BigDecimal getDistmskRate() {
        return distmskRate;
    }

    /**
     * <p>美侍客分销折扣率。</p>
     *
     * @param distmskRate 美侍客分销折扣率。
     */
    public void setDistmskRate(java.math.BigDecimal distmskRate) {
        this.distmskRate = distmskRate;
    }

    /**
     * <p>卖家产品技术标准。</p>
     *
     * @return the 卖家产品技术标准
     */
    public String getSlTncStd() {
        return slTncStd;
    }

    /**
     * <p>卖家产品技术标准。</p>
     *
     * @param slTncStd 卖家产品技术标准。
     */
    public void setSlTncStd(String slTncStd) {
        this.slTncStd = slTncStd;
    }

    /**
     * <p>产品技术标准定级(加工质量标准)。</p>
     *
     * @return the 产品技术标准定级(加工质量标准)
     */
    public Integer getSlTncGradeCode() {
        return slTncGradeCode;
    }

    /**
     * <p>产品技术标准定级(加工质量标准)。</p>
     *
     * @param slTncGradeCode 产品技术标准定级(加工质量标准)。
     */
    public void setSlTncGradeCode(Integer slTncGradeCode) {
        this.slTncGradeCode = slTncGradeCode;
    }

    /**
     * <p>产品技术标准定级不通过理由。</p>
     *
     * @return the 产品技术标准定级不通过理由
     */
    public String getTncNgReason() {
        return tncNgReason;
    }

    /**
     * <p>产品技术标准定级不通过理由。</p>
     *
     * @param tncNgReason 产品技术标准定级不通过理由。
     */
    public void setTncNgReason(String tncNgReason) {
        this.tncNgReason = tncNgReason;
    }

    /**
     * <p>产品技术标准定级状态。</p>
     *
     * @return the 产品技术标准定级状态
     */
    public Integer getTncAuditStatus() {
        return tncAuditStatus;
    }

    /**
     * <p>产品技术标准定级状态。</p>
     *
     * @param tncAuditStatus 产品技术标准定级状态。
     */
    public void setTncAuditStatus(Integer tncAuditStatus) {
        this.tncAuditStatus = tncAuditStatus;
    }

    /**
     * <p>产品技术标准定级人。</p>
     *
     * @return the 产品技术标准定级人
     */
    public String getTncAuditor() {
        return tncAuditor;
    }

    /**
     * <p>产品技术标准定级人。</p>
     *
     * @param tncAuditor 产品技术标准定级人。
     */
    public void setTncAuditor(String tncAuditor) {
        this.tncAuditor = tncAuditor;
    }

    /**
     * <p>产品技术标准定级日期。</p>
     *
     * @return the 产品技术标准定级日期
     */
    public java.util.Date getTncAuditDate() {
        return tncAuditDate;
    }

    /**
     * <p>产品技术标准定级日期。</p>
     *
     * @param tncAuditDate 产品技术标准定级日期。
     */
    public void setTncAuditDate(java.util.Date tncAuditDate) {
        this.tncAuditDate = tncAuditDate;
    }

    /**
     * <p>产品技术标准定级监控人审核意见。</p>
     *
     * @return the 产品技术标准定级监控人审核意见
     */
    public Integer getTncMonitorResult() {
        return tncMonitorResult;
    }

    /**
     * <p>产品技术标准定级监控人审核意见。</p>
     *
     * @param tncMonitorResult 产品技术标准定级监控人审核意见。
     */
    public void setTncMonitorResult(Integer tncMonitorResult) {
        this.tncMonitorResult = tncMonitorResult;
    }

    /**
     * <p>产品技术标准定级监控人。</p>
     *
     * @return the 产品技术标准定级监控人
     */
    public String getTncMonitorAuditor() {
        return tncMonitorAuditor;
    }

    /**
     * <p>产品技术标准定级监控人。</p>
     *
     * @param tncMonitorAuditor 产品技术标准定级监控人。
     */
    public void setTncMonitorAuditor(String tncMonitorAuditor) {
        this.tncMonitorAuditor = tncMonitorAuditor;
    }

    /**
     * <p>产品技术标准定级监控人审核日期。</p>
     *
     * @return the 产品技术标准定级监控人审核日期
     */
    public java.util.Date getTncMonitorDate() {
        return tncMonitorDate;
    }

    /**
     * <p>产品技术标准定级监控人审核日期。</p>
     *
     * @param tncMonitorDate 产品技术标准定级监控人审核日期。
     */
    public void setTncMonitorDate(java.util.Date tncMonitorDate) {
        this.tncMonitorDate = tncMonitorDate;
    }

    /**
     * <p>卖家产品质量标准（加工技术标准）。</p>
     *
     * @return the 卖家产品质量标准（加工技术标准）
     */
    public String getSlQltStd() {
        return slQltStd;
    }

    /**
     * <p>卖家产品质量标准（加工技术标准）。</p>
     *
     * @param slQltStd 卖家产品质量标准（加工技术标准）。
     */
    public void setSlQltStd(String slQltStd) {
        this.slQltStd = slQltStd;
    }

    /**
     * <p>产品质量标准定级。</p>
     *
     * @return the 产品质量标准定级
     */
    public Integer getSlQltGradeCode() {
        return slQltGradeCode;
    }

    /**
     * <p>产品质量标准定级。</p>
     *
     * @param slQltGradeCode 产品质量标准定级。
     */
    public void setSlQltGradeCode(Integer slQltGradeCode) {
        this.slQltGradeCode = slQltGradeCode;
    }

    /**
     * <p>产品质量标准定级不通过理由。</p>
     *
     * @return the 产品质量标准定级不通过理由
     */
    public String getQltNgReason() {
        return qltNgReason;
    }

    /**
     * <p>产品质量标准定级不通过理由。</p>
     *
     * @param qltNgReason 产品质量标准定级不通过理由。
     */
    public void setQltNgReason(String qltNgReason) {
        this.qltNgReason = qltNgReason;
    }

    /**
     * <p>产品质量标准定级状态。</p>
     *
     * @return the 产品质量标准定级状态
     */
    public Integer getQltAuditStatus() {
        return qltAuditStatus;
    }

    /**
     * <p>产品质量标准定级状态。</p>
     *
     * @param qltAuditStatus 产品质量标准定级状态。
     */
    public void setQltAuditStatus(Integer qltAuditStatus) {
        this.qltAuditStatus = qltAuditStatus;
    }

    /**
     * <p>产品质量标准定级人。</p>
     *
     * @return the 产品质量标准定级人
     */
    public String getQltAuditor() {
        return qltAuditor;
    }

    /**
     * <p>产品质量标准定级人。</p>
     *
     * @param qltAuditor 产品质量标准定级人。
     */
    public void setQltAuditor(String qltAuditor) {
        this.qltAuditor = qltAuditor;
    }

    /**
     * <p>产品质量标准定级日期。</p>
     *
     * @return the 产品质量标准定级日期
     */
    public java.util.Date getQltAuditDate() {
        return qltAuditDate;
    }

    /**
     * <p>产品质量标准定级日期。</p>
     *
     * @param qltAuditDate 产品质量标准定级日期。
     */
    public void setQltAuditDate(java.util.Date qltAuditDate) {
        this.qltAuditDate = qltAuditDate;
    }

    /**
     * <p>产品质量标准定级监控人审核意见。</p>
     *
     * @return the 产品质量标准定级监控人审核意见
     */
    public Integer getQltMonitorResult() {
        return qltMonitorResult;
    }

    /**
     * <p>产品质量标准定级监控人审核意见。</p>
     *
     * @param qltMonitorResult 产品质量标准定级监控人审核意见。
     */
    public void setQltMonitorResult(Integer qltMonitorResult) {
        this.qltMonitorResult = qltMonitorResult;
    }

    /**
     * <p>产品质量标准定级监控人。</p>
     *
     * @return the 产品质量标准定级监控人
     */
    public String getQltMonitorAuditor() {
        return qltMonitorAuditor;
    }

    /**
     * <p>产品质量标准定级监控人。</p>
     *
     * @param qltMonitorAuditor 产品质量标准定级监控人。
     */
    public void setQltMonitorAuditor(String qltMonitorAuditor) {
        this.qltMonitorAuditor = qltMonitorAuditor;
    }

    /**
     * <p>产品质量标准定级监控人审核日期。</p>
     *
     * @return the 产品质量标准定级监控人审核日期
     */
    public java.util.Date getQltMonitorDate() {
        return qltMonitorDate;
    }

    /**
     * <p>产品质量标准定级监控人审核日期。</p>
     *
     * @param qltMonitorDate 产品质量标准定级监控人审核日期。
     */
    public void setQltMonitorDate(java.util.Date qltMonitorDate) {
        this.qltMonitorDate = qltMonitorDate;
    }

    /**
     * <p>1 申请中 2论证中 3禁止准入 4试销 5正式上线 6下线  7黑名单。</p>
     *
     * @return the 1 申请中 2论证中 3禁止准入 4试销 5正式上线 6下线  7黑名单
     */
    public String getStatus() {
        return status;
    }

    /**
     * <p>1 申请中 2论证中 3禁止准入 4试销 5正式上线 6下线  7黑名单。</p>
     *
     * @param status 1 申请中 2论证中 3禁止准入 4试销 5正式上线 6下线  7黑名单。
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * <p>产品状态审核意见。</p>
     *
     * @return the 产品状态审核意见
     */
    public Integer getStatusMonitorResult() {
        return statusMonitorResult;
    }

    /**
     * <p>产品状态审核意见。</p>
     *
     * @param statusMonitorResult 产品状态审核意见。
     */
    public void setStatusMonitorResult(Integer statusMonitorResult) {
        this.statusMonitorResult = statusMonitorResult;
    }

    /**
     * <p>产品状态审核人。</p>
     *
     * @return the 产品状态审核人
     */
    public String getStatusMonitorAuditor() {
        return statusMonitorAuditor;
    }

    /**
     * <p>产品状态审核人。</p>
     *
     * @param statusMonitorAuditor 产品状态审核人。
     */
    public void setStatusMonitorAuditor(String statusMonitorAuditor) {
        this.statusMonitorAuditor = statusMonitorAuditor;
    }

    /**
     * <p>产品状态审核日期。</p>
     *
     * @return the 产品状态审核日期
     */
    public java.util.Date getStatusMonitorDate() {
        return statusMonitorDate;
    }

    /**
     * <p>产品状态审核日期。</p>
     *
     * @param statusMonitorDate 产品状态审核日期。
     */
    public void setStatusMonitorDate(java.util.Date statusMonitorDate) {
        this.statusMonitorDate = statusMonitorDate;
    }

    /**
     * <p>产品类别名称。</p>
     *
     * @return the 产品类别名称
     */
    public String getPdClassesName() {
        return pdClassesName;
    }

    /**
     * <p>产品类别名称。</p>
     *
     * @param pdClassesName 产品类别名称。
     */
    public void setPdClassesName(String pdClassesName) {
        this.pdClassesName = pdClassesName;
    }

    /**
     * <p>产品二级分类名称。</p>
     *
     * @return the 产品二级分类名称
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * <p>产品二级分类名称。</p>
     *
     * @param machiningName 产品二级分类名称。
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * <p>产品品种名称。</p>
     *
     * @return the 产品品种名称
     */
    public String getPdBreedName() {
        return pdBreedName;
    }

    /**
     * <p>产品品种名称。</p>
     *
     * @param pdBreedName 产品品种名称。
     */
    public void setPdBreedName(String pdBreedName) {
        this.pdBreedName = pdBreedName;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @return the 产品特征名称
     */
    public String getPdFeatureName() {
        return pdFeatureName;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @param pdFeatureName 产品特征名称。
     */
    public void setPdFeatureName(String pdFeatureName) {
        this.pdFeatureName = pdFeatureName;
    }

    /**
     * <p>产品净重名称。</p>
     *
     * @return the 产品净重名称
     */
    public String getWeightName() {
        return weightName;
    }

    /**
     * <p>产品净重名称。</p>
     *
     * @param weightName 产品净重名称。
     */
    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

}
