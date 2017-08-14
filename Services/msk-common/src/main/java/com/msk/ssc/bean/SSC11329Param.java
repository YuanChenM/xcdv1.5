package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.core.entity.SlProduct;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

public class SSC11329Param extends BasePageParam {
    private static final long serialVersionUID = 1L;

    private String epId;

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
    /**生产商名称*/
    private String prodEpName;
    //品牌
    private String brandEpName;
    //品牌名称
    private String brandName;
    //产品类别
    private String pdClassesName;
    //产品品种
    private String pdBreedName;
    //产品特征
    private String pdFeatureName;
    //品牌商和品牌id拼接
    private String concatInfo;
    //卖家名称
    private String slShowName;
    //盘装图
    private MultipartFile labFile1;
    //内袋图
    private MultipartFile labFile2;
    //外箱开箱图
    private MultipartFile labFile3;
    //外箱外观图
    private MultipartFile labFile4;
    /** 产品二级分类名称 */
    private String machiningName;
    /** 净重编码名称 */
    private String weightName;
    //产品状态
    private String statusName;
    //卖家产品履历表id
    private Long  hisId;
    //卖家货号码
    private String  slPdArtNo;
    //产品等级
    private String slTncGradeCodeName;


    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public Integer getSlPdId() {
        return slPdId;
    }

    public void setSlPdId(Integer slPdId) {
        this.slPdId = slPdId;
    }

    public Integer getProdEpId() {
        return prodEpId;
    }

    public void setProdEpId(Integer prodEpId) {
        this.prodEpId = prodEpId;
    }

    public Integer getBrandEpId() {
        return brandEpId;
    }

    public void setBrandEpId(Integer brandEpId) {
        this.brandEpId = brandEpId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getPdClassesCode() {
        return pdClassesCode;
    }

    public void setPdClassesCode(String pdClassesCode) {
        this.pdClassesCode = pdClassesCode;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getPdBreedCode() {
        return pdBreedCode;
    }

    public void setPdBreedCode(String pdBreedCode) {
        this.pdBreedCode = pdBreedCode;
    }

    public String getPdFeatureCode() {
        return pdFeatureCode;
    }

    public void setPdFeatureCode(String pdFeatureCode) {
        this.pdFeatureCode = pdFeatureCode;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getDistFlg() {
        return distFlg;
    }

    public void setDistFlg(String distFlg) {
        this.distFlg = distFlg;
    }

    public String getDistmskFlg() {
        return distmskFlg;
    }

    public void setDistmskFlg(String distmskFlg) {
        this.distmskFlg = distmskFlg;
    }

    public BigDecimal getDistmskRate() {
        return distmskRate;
    }

    public void setDistmskRate(BigDecimal distmskRate) {
        this.distmskRate = distmskRate;
    }

    public String getSlTncStd() {
        return slTncStd;
    }

    public void setSlTncStd(String slTncStd) {
        this.slTncStd = slTncStd;
    }

    public Integer getSlTncGradeCode() {
        return slTncGradeCode;
    }

    public void setSlTncGradeCode(Integer slTncGradeCode) {
        this.slTncGradeCode = slTncGradeCode;
    }

    public String getTncNgReason() {
        return tncNgReason;
    }

    public void setTncNgReason(String tncNgReason) {
        this.tncNgReason = tncNgReason;
    }

    public Integer getTncAuditStatus() {
        return tncAuditStatus;
    }

    public void setTncAuditStatus(Integer tncAuditStatus) {
        this.tncAuditStatus = tncAuditStatus;
    }

    public String getTncAuditor() {
        return tncAuditor;
    }

    public void setTncAuditor(String tncAuditor) {
        this.tncAuditor = tncAuditor;
    }

    public Date getTncAuditDate() {
        return tncAuditDate;
    }

    public void setTncAuditDate(Date tncAuditDate) {
        this.tncAuditDate = tncAuditDate;
    }

    public Integer getTncMonitorResult() {
        return tncMonitorResult;
    }

    public void setTncMonitorResult(Integer tncMonitorResult) {
        this.tncMonitorResult = tncMonitorResult;
    }

    public String getTncMonitorAuditor() {
        return tncMonitorAuditor;
    }

    public void setTncMonitorAuditor(String tncMonitorAuditor) {
        this.tncMonitorAuditor = tncMonitorAuditor;
    }

    public Date getTncMonitorDate() {
        return tncMonitorDate;
    }

    public void setTncMonitorDate(Date tncMonitorDate) {
        this.tncMonitorDate = tncMonitorDate;
    }

    public String getSlQltStd() {
        return slQltStd;
    }

    public void setSlQltStd(String slQltStd) {
        this.slQltStd = slQltStd;
    }

    public Integer getSlQltGradeCode() {
        return slQltGradeCode;
    }

    public void setSlQltGradeCode(Integer slQltGradeCode) {
        this.slQltGradeCode = slQltGradeCode;
    }

    public String getQltNgReason() {
        return qltNgReason;
    }

    public void setQltNgReason(String qltNgReason) {
        this.qltNgReason = qltNgReason;
    }

    public Integer getQltAuditStatus() {
        return qltAuditStatus;
    }

    public void setQltAuditStatus(Integer qltAuditStatus) {
        this.qltAuditStatus = qltAuditStatus;
    }

    public String getQltAuditor() {
        return qltAuditor;
    }

    public void setQltAuditor(String qltAuditor) {
        this.qltAuditor = qltAuditor;
    }

    public Date getQltAuditDate() {
        return qltAuditDate;
    }

    public void setQltAuditDate(Date qltAuditDate) {
        this.qltAuditDate = qltAuditDate;
    }

    public Integer getQltMonitorResult() {
        return qltMonitorResult;
    }

    public void setQltMonitorResult(Integer qltMonitorResult) {
        this.qltMonitorResult = qltMonitorResult;
    }

    public String getQltMonitorAuditor() {
        return qltMonitorAuditor;
    }

    public void setQltMonitorAuditor(String qltMonitorAuditor) {
        this.qltMonitorAuditor = qltMonitorAuditor;
    }

    public Date getQltMonitorDate() {
        return qltMonitorDate;
    }

    public void setQltMonitorDate(Date qltMonitorDate) {
        this.qltMonitorDate = qltMonitorDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusMonitorResult() {
        return statusMonitorResult;
    }

    public void setStatusMonitorResult(Integer statusMonitorResult) {
        this.statusMonitorResult = statusMonitorResult;
    }

    public String getStatusMonitorAuditor() {
        return statusMonitorAuditor;
    }

    public void setStatusMonitorAuditor(String statusMonitorAuditor) {
        this.statusMonitorAuditor = statusMonitorAuditor;
    }

    public Date getStatusMonitorDate() {
        return statusMonitorDate;
    }

    public void setStatusMonitorDate(Date statusMonitorDate) {
        this.statusMonitorDate = statusMonitorDate;
    }

    /**
     * Getter method for property <tt>slTncGradeCodeName</tt>.
     *
     * @return property value of slTncGradeCodeName
     */






    public String getSlTncGradeCodeName() {
        return slTncGradeCodeName;
    }

    /**
     * Setter method for property <tt>slTncGradeCodeName</tt>.
     *
     * @param slTncGradeCodeName value to be assigned to property slTncGradeCodeName
     */
    public void setSlTncGradeCodeName(String slTncGradeCodeName) {
        this.slTncGradeCodeName = slTncGradeCodeName;
    }

    /**
     * Getter method for property <tt>hisId</tt>.
     *
     * @return property value of hisId
     */
    public Long getHisId() {
        return hisId;
    }

    /**
     * Setter method for property <tt>hisId</tt>.
     *
     * @param hisId value to be assigned to property hisId
     */
    public void setHisId(Long hisId) {
        this.hisId = hisId;
    }

    /**
     * Getter method for property <tt>statusName</tt>.
     *
     * @return property value of statusName
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * Setter method for property <tt>statusName</tt>.
     *
     * @param statusName value to be assigned to property statusName
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * Getter method for property <tt>machiningName</tt>.
     *
     * @return property value of machiningName
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * Setter method for property <tt>machiningName</tt>.
     *
     * @param machiningName value to be assigned to property machiningName
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * Getter method for property <tt>weightName</tt>.
     *
     * @return property value of weightName
     */
    public String getWeightName() {
        return weightName;
    }

    /**
     * Setter method for property <tt>weightName</tt>.
     *
     * @param weightName value to be assigned to property weightName
     */
    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    /**
     * Getter method for property <tt>labFile1</tt>.
     *
     * @return property value of labFile1
     */
    public MultipartFile getLabFile1() {
        return labFile1;
    }

    /**
     * Setter method for property <tt>labFile1</tt>.
     *
     * @param labFile1 value to be assigned to property labFile1
     */
    public void setLabFile1(MultipartFile labFile1) {
        this.labFile1 = labFile1;
    }

    /**
     * Getter method for property <tt>labFile2</tt>.
     *
     * @return property value of labFile2
     */
    public MultipartFile getLabFile2() {
        return labFile2;
    }

    /**
     * Setter method for property <tt>labFile2</tt>.
     *
     * @param labFile2 value to be assigned to property labFile2
     */
    public void setLabFile2(MultipartFile labFile2) {
        this.labFile2 = labFile2;
    }

    /**
     * Getter method for property <tt>labFile3</tt>.
     *
     * @return property value of labFile3
     */
    public MultipartFile getLabFile3() {
        return labFile3;
    }

    /**
     * Setter method for property <tt>labFile3</tt>.
     *
     * @param labFile3 value to be assigned to property labFile3
     */
    public void setLabFile3(MultipartFile labFile3) {
        this.labFile3 = labFile3;
    }

    /**
     * Getter method for property <tt>labFile4</tt>.
     *
     * @return property value of labFile4
     */
    public MultipartFile getLabFile4() {
        return labFile4;
    }

    /**
     * Setter method for property <tt>labFile4</tt>.
     *
     * @param labFile4 value to be assigned to property labFile4
     */
    public void setLabFile4(MultipartFile labFile4) {
        this.labFile4 = labFile4;
    }

    /**
     * 获得brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 获得slShowName
     */
    public String getSlShowName() {
        return slShowName;
    }

    /**
     * 设置slShowName
     */
    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    /**
     * 获得concatInfo
     */
    public String getConcatInfo() {
        return concatInfo;
    }

    /**
     * 设置concatInfo
     */
    public void setConcatInfo(String concatInfo) {
        this.concatInfo = concatInfo;
    }

    /**
     * 获得serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 获得prodEpName
     */
    public String getProdEpName() {
        return prodEpName;
    }

    /**
     * 设置prodEpName
     */
    public void setProdEpName(String prodEpName) {
        this.prodEpName = prodEpName;
    }

    /**
     * 获得brandEpName
     */
    public String getBrandEpName() {
        return brandEpName;
    }

    /**
     * 设置brandEpName
     */
    public void setBrandEpName(String brandEpName) {
        this.brandEpName = brandEpName;
    }

    /**
     * 获得pdClassesName
     */
    public String getPdClassesName() {
        return pdClassesName;
    }

    /**
     * 设置pdClassesName
     */
    public void setPdClassesName(String pdClassesName) {
        this.pdClassesName = pdClassesName;
    }

    /**
     * 获得pdBreedName
     */
    public String getPdBreedName() {
        return pdBreedName;
    }

    /**
     * 设置pdBreedName
     */
    public void setPdBreedName(String pdBreedName) {
        this.pdBreedName = pdBreedName;
    }

    /**
     * 获得pdFeatureName
     */
    public String getPdFeatureName() {
        return pdFeatureName;
    }

    /**
     * 设置pdFeatureName
     */
    public void setPdFeatureName(String pdFeatureName) {
        this.pdFeatureName = pdFeatureName;
    }

    /**
     * Getter method for property <tt>slPdArtNo</tt>.
     *
     * @return property value of slPdArtNo
     */
    public String getSlPdArtNo() {
        return slPdArtNo;
    }

    /**
     * Setter method for property <tt>slPdArtNo</tt>.
     *
     * @param slPdArtNo value to be assigned to property slPdArtNo
     */
    public void setSlPdArtNo(String slPdArtNo) {
        this.slPdArtNo = slPdArtNo;
    }
}
