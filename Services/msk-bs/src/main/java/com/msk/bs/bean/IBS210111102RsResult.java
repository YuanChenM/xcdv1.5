package com.msk.bs.bean;

import com.msk.core.entity.SlSeller;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gyh on 2016/4/12.
 */
@ApiModel(value = "IBS210111102RsResult",description = "联盟主买手信息")
public class IBS210111102RsResult extends SlSeller {
    @ApiModelProperty(value = "手机号码")
    private String slTel;
    @ApiModelProperty(value = "买手显示名称")
    private String slShowName;
    @ApiModelProperty(value = "联系人姓名")
    private String slContact;
    @ApiModelProperty(value = "大区名")
    private String areaName;
    @ApiModelProperty(value = "物流区名")
    private String lgcsAreaName;
    @ApiModelProperty(value = "省名")
    private String provinceName;
    @ApiModelProperty(value = "地区名")
    private String cityName;
    @ApiModelProperty(value = "区名")
    private String districtName;
    @ApiModelProperty(value = "买手身份证号")
    private String slIdcard;
    @ApiModelProperty(value = "合营优先顺方式")
    private Integer slSort;
    @ApiModelProperty(value = "买手地址")
    private String slAddress;

    /**
     * Getter method for property <tt>slContact</tt>.
     *
     * @return property value of slContact
     */
    public String getSlContact() {
        return slContact;
    }

    /**
     * Setter method for property <tt>slContact</tt>.
     *
     * @param slContact value to be assigned to property slContact
     */
    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    /**
     * Getter method for property <tt>slTel</tt>.
     *
     * @return property value of slTel
     */
    public String getSlTel() {
        return slTel;
    }

    /**
     * Setter method for property <tt>slTel</tt>.
     *
     * @param slTel value to be assigned to property slTel
     */
    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    /**
     * Getter method for property <tt>slShowName</tt>.
     *
     * @return property value of slShowName
     */
    public String getSlShowName() {
        return slShowName;
    }

    /**
     * Setter method for property <tt>slShowName</tt>.
     *
     * @param slShowName value to be assigned to property slShowName
     */
    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    /**
     * Getter method for property <tt>areaName</tt>.
     *
     * @return property value of areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * Setter method for property <tt>areaName</tt>.
     *
     * @param areaName value to be assigned to property areaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * Getter method for property <tt>lgcsAreaName</tt>.
     *
     * @return property value of lgcsAreaName
     */
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * Setter method for property <tt>lgcsAreaName</tt>.
     *
     * @param lgcsAreaName value to be assigned to property lgcsAreaName
     */
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    /**
     * Getter method for property <tt>provinceName</tt>.
     *
     * @return property value of provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * Setter method for property <tt>provinceName</tt>.
     *
     * @param provinceName value to be assigned to property provinceName
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * Getter method for property <tt>cityName</tt>.
     *
     * @return property value of cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Setter method for property <tt>cityName</tt>.
     *
     * @param cityName value to be assigned to property cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Getter method for property <tt>districtName</tt>.
     *
     * @return property value of districtName
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * Setter method for property <tt>districtName</tt>.
     *
     * @param districtName value to be assigned to property districtName
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * Getter method for property <tt>slIdcard</tt>.
     *
     * @return property value of slIdcard
     */
    public String getSlIdcard() {
        return slIdcard;
    }

    /**
     * Setter method for property <tt>slIdcard</tt>.
     *
     * @param slIdcard value to be assigned to property slIdcard
     */
    public void setSlIdcard(String slIdcard) {
        this.slIdcard = slIdcard;
    }

    /**
     * Getter method for property <tt>slSort</tt>.
     *
     * @return property value of slSort
     */
    public Integer getSlSort() {
        return slSort;
    }

    /**
     * Setter method for property <tt>slSort</tt>.
     *
     * @param slSort value to be assigned to property slSort
     */
    public void setSlSort(Integer slSort) {
        this.slSort = slSort;
    }

    /**
     * Getter method for property <tt>slAddress</tt>.
     *
     * @return property value of slAddress
     */
    public String getSlAddress() {
        return slAddress;
    }

    /**
     * Setter method for property <tt>slAddress</tt>.
     *
     * @param slAddress value to be assigned to property slAddress
     */
    public void setSlAddress(String slAddress) {
        this.slAddress = slAddress;
    }
}
