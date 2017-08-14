package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装参数
 * IPD14110101RsParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141110RsParam", description = "param")
@JsonIgnoreProperties(value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId" })
public class IPD141110RsParam extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "产品1级分类编码")
    private String pdTypeCode1;
    @ApiModelProperty(value = "产品1级分类名称")
    private String  pdTypeName1;
    @ApiModelProperty(value = "加工程度对象")
    private List<IPD14111001RsParam> pdType2List = new ArrayList<IPD14111001RsParam>();

    /**
     * 获得pdTypeCode1
     */
    public String getPdTypeCode1() {
        return pdTypeCode1;
    }

    /**
     * 设置pdTypeCode1
     */
    public void setPdTypeCode1(String pdTypeCode1) {
        this.pdTypeCode1 = pdTypeCode1;
    }

    /**
     * 获得pdType2List
     */
    public List<IPD14111001RsParam> getPdType2List() {
        return pdType2List;
    }

    /**
     * 设置pdType2List
     */
    public void setPdType2List(List<IPD14111001RsParam> pdType2List) {
        this.pdType2List = pdType2List;
    }

    /**
     * 获得pdTypeName1
     */
    public String getPdTypeName1() {
        return pdTypeName1;
    }

    /**
     * 设置pdTypeName1
     */
    public void setPdTypeName1(String pdTypeName1) {
        this.pdTypeName1 = pdTypeName1;
    }
}