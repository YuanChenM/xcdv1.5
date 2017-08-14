package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fjm on 2016/3/9.
 */
@ApiModel(value = "IPD141122RsParam", description = "param")
@JsonIgnoreProperties(value={"delFlg","crtId","crtTime","updId","updTime","ver","actId","actTime"})
public class IPD141122RsParam extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "类别编码")
    private String classesCode;
    @ApiModelProperty(value = "品种编码")
    private String machiningCode;
    @ApiModelProperty(value = "特征编码")
    private String breedCode;







    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }


}
