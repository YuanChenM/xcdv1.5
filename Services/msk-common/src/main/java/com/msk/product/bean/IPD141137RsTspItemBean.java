package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141137RsTspItemBean.储存运输指标信息同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141137RsTspItemBean", description = "tspList")
public class IPD141137RsTspItemBean extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "储存运输指标ID")
    private String tspStdClaId;
    @ApiModelProperty(value = "储存运输标准指标")
    private String tspStdClaName;
    @ApiModelProperty(value = "储存运输指标列表")
    private List<IPD141137RsTspBean> tspStdSublist;

    /**
     * Getter method for property <tt>tspStdClaId</tt>.
     *
     * @return property value of tspStdClaId
     */
    public String getTspStdClaId() {
        return tspStdClaId;
    }

    /**
     * Setter method for property <tt>tspStdClaId</tt>.
     *
     * @param tspStdClaId value to be assigned to property tspStdClaId
     */
    public void setTspStdClaId(String tspStdClaId) {
        this.tspStdClaId = tspStdClaId;
    }

    /**
     * Getter method for property <tt>tspStdClaName</tt>.
     *
     * @return property value of tspStdClaName
     */
    public String getTspStdClaName() {
        return tspStdClaName;
    }

    /**
     * Setter method for property <tt>tspStdClaName</tt>.
     *
     * @param tspStdClaName value to be assigned to property tspStdClaName
     */
    public void setTspStdClaName(String tspStdClaName) {
        this.tspStdClaName = tspStdClaName;
    }

    /**
     * Getter method for property <tt>tspStdSublist</tt>.
     *
     * @return property value of tspStdSublist
     */
    public List<IPD141137RsTspBean> getTspStdSublist() {
        return tspStdSublist;
    }

    /**
     * Setter method for property <tt>tspStdSublist</tt>.
     *
     * @param tspStdSublist value to be assigned to property tspStdSublist
     */
    public void setTspStdSublist(List<IPD141137RsTspBean> tspStdSublist) {
        this.tspStdSublist = tspStdSublist;
    }
}