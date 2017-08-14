package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回参数产品类别列表
 * IPD141101RsParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141101RsParam", description = "返回参数产品类别列表")
@JsonIgnoreProperties(value={"delFlg","crtId","crtTime","updId","updTime","ver","actId","actTime"})
public class IPD141101RsParam extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类别编码")
    private String classesCode;

    @ApiModelProperty(value = "类别名称")
    private String classesName;

    /**
     * Getter method for property <tt>classesCode</tt>.
     *
     * @return property value of classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * Setter method for property <tt>classesCode</tt>.
     *
     * @param classesCode value to be assigned to property classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Getter method for property <tt>classesName</tt>.
     *
     * @return property value of classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * Setter method for property <tt>classesName</tt>.
     *
     * @param classesName value to be assigned to property classesName
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }
}