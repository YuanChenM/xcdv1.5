package com.msk.price.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by zhu_kai on 2016/6/16.
 */
public class LgcsProductParam extends BaseParam {
    /**
     *产品编码
     */
    private String pdNode;
    /**
     *学名
     */
    private String scientificName;
    /**
     *俗名
     */
    private String localName;
    /**
     *销售名
     */
    private String salesName;
    /**
     *产品名称
     */
    private String pdName;
    /**
     *产品一级分类编码
     */
    private String classesCode;

    public String getPdNode() {
        return pdNode;
    }

    public void setPdNode(String pdNode) {
        this.pdNode = pdNode;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }
}
