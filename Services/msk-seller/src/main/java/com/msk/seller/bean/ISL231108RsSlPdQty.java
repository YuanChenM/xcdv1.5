package com.msk.seller.bean;

import java.util.List;

import com.msk.core.entity.SlPdTncStdNew;

/**
 * Created by gyh on 2016/2/23.
 */
public class ISL231108RsSlPdQty {
    private String slCode;//卖家编码
    private Integer srodEpId;//生产商企业ID
    private Integer srandEpId;//品牌商企业ID
    private Integer srandId;//产品品牌ID
    private String sdClassesCode;//产品类别
    private String sdBreedCode;//产品品种
    private String sdFeatureCode;//产品特征
    private String distFlg;//是否参与神农客分销，0:否，1:是
    private String distMskFlg;//是否参与美侍客分销，0:否，1:是
    private String slQltStd;//卖家产品质量标准
    private Integer slQltGradeCode;//产品质量标准定级
    private String updId;//修改者ID
    private Integer ver;//版本号
    private List<SlPdTncStdNew> slPdQtyStdList;//TODO 卖家产品质量标准值信息List

    /**
     * 获得slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * 设置slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * 获得srodEpId
     */
    public Integer getSrodEpId() {
        return srodEpId;
    }

    /**
     * 设置srodEpId
     */
    public void setSrodEpId(Integer srodEpId) {
        this.srodEpId = srodEpId;
    }

    /**
     * 获得srandEpId
     */
    public Integer getSrandEpId() {
        return srandEpId;
    }

    /**
     * 设置srandEpId
     */
    public void setSrandEpId(Integer srandEpId) {
        this.srandEpId = srandEpId;
    }

    /**
     * 获得srandId
     */
    public Integer getSrandId() {
        return srandId;
    }

    /**
     * 设置srandId
     */
    public void setSrandId(Integer srandId) {
        this.srandId = srandId;
    }

    /**
     * 获得sdClassesCode
     */
    public String getSdClassesCode() {
        return sdClassesCode;
    }

    /**
     * 设置sdClassesCode
     */
    public void setSdClassesCode(String sdClassesCode) {
        this.sdClassesCode = sdClassesCode;
    }

    /**
     * 获得sdBreedCode
     */
    public String getSdBreedCode() {
        return sdBreedCode;
    }

    /**
     * 设置sdBreedCode
     */
    public void setSdBreedCode(String sdBreedCode) {
        this.sdBreedCode = sdBreedCode;
    }

    /**
     * 获得sdFeatureCode
     */
    public String getSdFeatureCode() {
        return sdFeatureCode;
    }

    /**
     * 设置sdFeatureCode
     */
    public void setSdFeatureCode(String sdFeatureCode) {
        this.sdFeatureCode = sdFeatureCode;
    }

    /**
     * 获得distFlg
     */
    public String getDistFlg() {
        return distFlg;
    }

    /**
     * 设置distFlg
     */
    public void setDistFlg(String distFlg) {
        this.distFlg = distFlg;
    }

    /**
     * 获得distMskFlg
     */
    public String getDistMskFlg() {
        return distMskFlg;
    }

    /**
     * 设置distMskFlg
     */
    public void setDistMskFlg(String distMskFlg) {
        this.distMskFlg = distMskFlg;
    }

    /**
     * 获得slQltStd
     */
    public String getSlQltStd() {
        return slQltStd;
    }

    /**
     * 设置slQltStd
     */
    public void setSlQltStd(String slQltStd) {
        this.slQltStd = slQltStd;
    }

    /**
     * 获得slQltGradeCode
     */
    public Integer getSlQltGradeCode() {
        return slQltGradeCode;
    }

    /**
     * 设置slQltGradeCode
     */
    public void setSlQltGradeCode(Integer slQltGradeCode) {
        this.slQltGradeCode = slQltGradeCode;
    }

    /**
     * 获得updId
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * 设置updId
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    /**
     * 获得ver
     */
    public Integer getVer() {
        return ver;
    }

    /**
     * 设置ver
     */
    public void setVer(Integer ver) {
        this.ver = ver;
    }

    /**
     * 获得slPdQtyStdList
     */
    public List<SlPdTncStdNew> getSlPdQtyStdList() {
        return slPdQtyStdList;
    }

    /**
     * 设置slPdQtyStdList
     */
    public void setSlPdQtyStdList(List<SlPdTncStdNew> slPdQtyStdList) {
        this.slPdQtyStdList = slPdQtyStdList;
    }
}
