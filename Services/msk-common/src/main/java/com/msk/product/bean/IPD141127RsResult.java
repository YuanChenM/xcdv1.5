package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141127RsResult.产品卫生标准
 *
 * @author xhy
 */
@ApiModel(value = "IPD141127RsResult", description = "result")
public class IPD141127RsResult extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类质量标准指标id")
    private String  sftStdClaId;
    @ApiModelProperty(value = "分类质量标准指标名称")
    private String sftStdClaName;
    @ApiModelProperty(value = "具体质量指标列表")
    private List<IPD141127RsSftStdResult> sftStdSublist;


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
    public List<IPD141127RsSftStdResult> getSftStdSublist() {
        return sftStdSublist;
    }

    /**
     * Setter method for property <tt>sftStdSublist</tt>.
     *
     * @param sftStdSublist value to be assigned to property sftStdSublist
     */
    public void setSftStdSublist(List<IPD141127RsSftStdResult> sftStdSublist) {
        this.sftStdSublist = sftStdSublist;
    }
}