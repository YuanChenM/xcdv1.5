package com.msk.ssc.bean;

import com.msk.core.entity.SlProduct;
import org.springframework.web.multipart.MultipartFile;

public class SSC11329Bean extends SlProduct {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
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
