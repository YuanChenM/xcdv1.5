package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by liu_yan2 on 2016/5/27.
 */
public class ISL231205RsParam extends BaseParam {

    /**结构等级*/
    private int treeLevel;
    /**卖家编码*/
    private String slCode;
    /**父节点编码*/
    private String parentCode;
    /**产品类别*/
    private String pdClassesCode;
    /**产品二级分类编码*/
    private String machiningCode;
    /**产品品种*/
    private String pdBreedCode;
    /**产品特征*/
    private String pdFeatureCode;

    public int getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(int treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
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

    public String getPdFeatureCode() {
        return pdFeatureCode;
    }

    public void setPdFeatureCode(String pdFeatureCode) {
        this.pdFeatureCode = pdFeatureCode;
    }

    public String getPdBreedCode() {
        return pdBreedCode;
    }

    public void setPdBreedCode(String pdBreedCode) {
        this.pdBreedCode = pdBreedCode;
    }

}
