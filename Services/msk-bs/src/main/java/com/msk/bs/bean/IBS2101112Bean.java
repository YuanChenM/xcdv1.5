package com.msk.bs.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msk.common.consts.BsConst;
import com.msk.core.entity.SlBsBuyerHis;
import com.msk.core.entity.SlHouseAccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;
@ApiModel(value = "IBS2101112Bean",description = "买手买家列表")
public class IBS2101112Bean extends SlBsBuyerHis {

    @ApiModelProperty(value = "开始日时")
    private java.util.Date startTime;

    @ApiModelProperty(value = "结束日时")
    private java.util.Date endTime;
    @ApiModelProperty(value = "买手基本信息")
    private IBS210111201Bean buyerInfo;
    @ApiModelProperty(value = "冻品管家基本信息")
    private SlHouseAccount houseInfo;
    @ApiModelProperty(value = "管家分类集合")
    private List<IBS21011121Bean> houseTYPEList;
    /**
     * Getter method for property <tt>buyerInfo</tt>.
     *
     * @return property value of buyerInfo
     */
    public IBS210111201Bean getBuyerInfo() {
        return buyerInfo;
    }

    /**
     * Setter method for property <tt>buyerInfo</tt>.
     *
     * @param buyerInfo value to be assigned to property buyerInfo
     */
    public void setBuyerInfo(IBS210111201Bean buyerInfo) {
        this.buyerInfo = buyerInfo;
    }

    /**
     * Getter method for property <tt>houseInfo</tt>.
     *
     * @return property value of houseInfo
     */
    public SlHouseAccount getHouseInfo() {
        return houseInfo;
    }

    /**
     * Setter method for property <tt>houseInfo</tt>.
     *
     * @param houseInfo value to be assigned to property houseInfo
     */
    public void setHouseInfo(SlHouseAccount houseInfo) {
        this.houseInfo = houseInfo;
    }

    /**
     * Getter method for property <tt>startTime</tt>.
     *
     * @return property value of startTime
     */
    @Override
    @JsonFormat(timezone= BsConst.Default.TIMEZONE, pattern= BsConst.Default.FORMAT_TIME)
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setter method for property <tt>startTime</tt>.
     *
     * @param startTime value to be assigned to property startTime
     */
    @Override
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter method for property <tt>endTime</tt>.
     *
     * @return property value of endTime
     */
    @Override
    @JsonFormat(timezone= BsConst.Default.TIMEZONE, pattern= BsConst.Default.FORMAT_TIME)
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Setter method for property <tt>endTime</tt>.
     *
     * @param endTime value to be assigned to property endTime
     */
    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<IBS21011121Bean> getHouseTYPEList() {
        return houseTYPEList;
    }

    public void setHouseTYPEList(List<IBS21011121Bean> houseTYPEList) {
        this.houseTYPEList = houseTYPEList;
    }
}


















