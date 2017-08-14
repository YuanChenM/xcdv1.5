package com.msk.ssc.bean;

import com.msk.core.entity.SscIntoDetail;

import java.math.BigDecimal;

/**
 * Created by liu_yan2 on 2016/7/4.
 */
public class SSC11310RsBean extends SscIntoDetail{

//    /**一级分类名称*/
//    private String classesName;
//    /**二级分类名称*/
//    private String machiningName;
//    /**品种名称*/
//    private String breedName;
//    /**特征名称*/
//    private String featureName;
    /**净重名称*/
    private String weightName;

    /**产品名称*/
    private String productName;

    /**等级名称*/
    private String gradeName;

    /**计划发货产品箱数*/
    private String productPlanBox;

    /**计划发货产品重量*/
    private String productPlanWeight;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getProductPlanBox() {
        return productPlanBox;
    }

    public void setProductPlanBox(String productPlanBox) {
        this.productPlanBox = productPlanBox;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getProductPlanWeight() {
        return productPlanWeight;
    }

    public void setProductPlanWeight(String productPlanWeight) {
        this.productPlanWeight = productPlanWeight;
    }
}
