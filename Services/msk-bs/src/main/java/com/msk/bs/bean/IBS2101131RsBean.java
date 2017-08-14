package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gao_min on 2016/10/12.
 */
@ApiModel(value = "IBS2101131RsBean", description = "管家服务心得")
public class IBS2101131RsBean extends BaseEntity {

    @ApiModelProperty(value = "管家名字")
    private String houseKeeperName;

    @ApiModelProperty(value = "工作照")
    private String houseKeeperImg;

    @ApiModelProperty(value = "图片保存服务器类型：0：文件服务器；1：FTP服务器")
    private Integer houseKeeperImgServerType;

    @ApiModelProperty(value = "自我介绍")
    private String houseKeeperIntroduce;

    @ApiModelProperty(value = "服务心得")
    private String houseKeeperServiceCommit;

    @ApiModelProperty(value = "工作履历（服务履历）")
    private List<IBS2101132RsBean> houseWorkList;

    @ApiModelProperty(value = "教育履历")
    private List<IBS2101133RsBean> houseEduList;

    public String getHouseKeeperName() {
        return houseKeeperName;
    }

    public void setHouseKeeperName(String houseKeeperName) {
        this.houseKeeperName = houseKeeperName;
    }

    public String getHouseKeeperImg() {
        return houseKeeperImg;
    }

    public void setHouseKeeperImg(String houseKeeperImg) {
        this.houseKeeperImg = houseKeeperImg;
    }

    public Integer getHouseKeeperImgServerType() {
        return houseKeeperImgServerType;
    }

    public void setHouseKeeperImgServerType(Integer houseKeeperImgServerType) {
        this.houseKeeperImgServerType = houseKeeperImgServerType;
    }

    public String getHouseKeeperIntroduce() {
        return houseKeeperIntroduce;
    }

    public void setHouseKeeperIntroduce(String houseKeeperIntroduce) {
        this.houseKeeperIntroduce = houseKeeperIntroduce;
    }

    public String getHouseKeeperServiceCommit() {
        return houseKeeperServiceCommit;
    }

    public void setHouseKeeperServiceCommit(String houseKeeperServiceCommit) {
        this.houseKeeperServiceCommit = houseKeeperServiceCommit;
    }

    public List<IBS2101132RsBean> getHouseWorkList() {
        return houseWorkList;
    }

    public void setHouseWorkList(List<IBS2101132RsBean> houseWorkList) {
        this.houseWorkList = houseWorkList;
    }

    public List<IBS2101133RsBean> getHouseEduList() {
        return houseEduList;
    }

    public void setHouseEduList(List<IBS2101133RsBean> houseEduList) {
        this.houseEduList = houseEduList;
    }
}
