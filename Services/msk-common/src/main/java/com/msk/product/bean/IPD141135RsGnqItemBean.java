package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141135RsGnqItemBean.通用质量信息指标同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141135RsGnqItemBean", description = "searchList")
public class IPD141135RsGnqItemBean extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类通用质量准指标ID")
    private String gnqStdClaId;
    @ApiModelProperty(value = "分类通用质量标准指标")
    private String gnqStdClaName;
    @ApiModelProperty(value = "具体通用质量指标列表")
    private List<IPD141135RsGnqBean> gnqStdSublist;


    /**
     * Getter method for property <tt>gnqStdClaId</tt>.
     *
     * @return property value of gnqStdClaId
     */
    public String getGnqStdClaId() {
        return gnqStdClaId;
    }

    /**
     * Setter method for property <tt>gnqStdClaId</tt>.
     *
     * @param gnqStdClaId value to be assigned to property gnqStdClaId
     */
    public void setGnqStdClaId(String gnqStdClaId) {
        this.gnqStdClaId = gnqStdClaId;
    }

    /**
     * Getter method for property <tt>gnqStdClaName</tt>.
     *
     * @return property value of gnqStdClaName
     */
    public String getGnqStdClaName() {
        return gnqStdClaName;
    }

    /**
     * Setter method for property <tt>gnqStdClaName</tt>.
     *
     * @param gnqStdClaName value to be assigned to property gnqStdClaName
     */
    public void setGnqStdClaName(String gnqStdClaName) {
        this.gnqStdClaName = gnqStdClaName;
    }

    /**
     * Getter method for property <tt>gnqStdSublist</tt>.
     *
     * @return property value of gnqStdSublist
     */
    public List<IPD141135RsGnqBean> getGnqStdSublist() {
        return gnqStdSublist;
    }

    /**
     * Setter method for property <tt>gnqStdSublist</tt>.
     *
     * @param gnqStdSublist value to be assigned to property gnqStdSublist
     */
    public void setGnqStdSublist(List<IPD141135RsGnqBean> gnqStdSublist) {
        this.gnqStdSublist = gnqStdSublist;
    }
}