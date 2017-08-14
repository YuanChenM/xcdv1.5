package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by liu_tao2 on 2016/5/27.
 */
public class ISL231206RsParam extends BaseParam {

    /**
     * 卖家编码
     */
    private String slCode;

    /**
     * 父节点编码
     */
    private String pdClassesCode;

    /**
     * 产品二级分类编码
     */
    private String machiningCode;

    /**
     * 产品品种
     */
    private String pdBreedCode;

    /**
     * 产品特征
     */
    private String pdFeatureCode;

    /**
     * 净重编码
     */
    private String weightCode;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getPdClassesCode() {
        return pdClassesCode;
    }

    public void setPdClassesCode(String pdClassesCode) {
        this.pdClassesCode = pdClassesCode;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getPdBreedCode() {
        return pdBreedCode;
    }

    public void setPdBreedCode(String pdBreedCode) {
        this.pdBreedCode = pdBreedCode;
    }

    public String getPdFeatureCode() {
        return pdFeatureCode;
    }

    public void setPdFeatureCode(String pdFeatureCode) {
        this.pdFeatureCode = pdFeatureCode;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }
}
