package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141136RsSftItemBean.安全指标信息同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141136RsBean", description = "sftList")
public class IPD141136RsSftItemBean extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "产品安全指标ID")
    private String sftStdClaId;
    @ApiModelProperty(value = "产品安全标准指标")
    private String sftStdClaName;
    @ApiModelProperty(value = "产品安全指标列表")
    private List<IPD141136RsSftBean> sftStdSublist;


    /**
     * Getter method for property <tt>sftStdClaId</tt>.
     *
     * @return property value of sftStdClaId
     */
    public String getSftStdClaId() {
        return sftStdClaId;
    }

    /**
     * Setter method for property <tt>sftStdClaId</tt>.
     *
     * @param sftStdClaId value to be assigned to property sftStdClaId
     */
    public void setSftStdClaId(String sftStdClaId) {
        this.sftStdClaId = sftStdClaId;
    }

    /**
     * Getter method for property <tt>sftStdClaName</tt>.
     *
     * @return property value of sftStdClaName
     */
    public String getSftStdClaName() {
        return sftStdClaName;
    }

    /**
     * Setter method for property <tt>sftStdClaName</tt>.
     *
     * @param sftStdClaName value to be assigned to property sftStdClaName
     */
    public void setSftStdClaName(String sftStdClaName) {
        this.sftStdClaName = sftStdClaName;
    }

    /**
     * Getter method for property <tt>sftStdSublist</tt>.
     *
     * @return property value of sftStdSublist
     */
    public List<IPD141136RsSftBean> getSftStdSublist() {
        return sftStdSublist;
    }

    /**
     * Setter method for property <tt>sftStdSublist</tt>.
     *
     * @param sftStdSublist value to be assigned to property sftStdSublist
     */
    public void setSftStdSublist(List<IPD141136RsSftBean> sftStdSublist) {
        this.sftStdSublist = sftStdSublist;
    }
}