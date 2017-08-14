package com.msk.product.bean;


import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.PdFedStd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yang_chunyan on 2016/4/29.
 */
@ApiModel(value = "FedStdBean", description = "产品种源标准子节点")
public class FedStdBean extends PdFedStd {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品标准ID")
    private Long standardId;

    @ApiModelProperty(value = "饲养标准项目ID")
    private String fedStdItemId;

    @ApiModelProperty(value = "等级")
    private String levelId;

    @ApiModelProperty(value = "父节点ID")
    private String parentId;

    @ApiModelProperty(value = "饲养标准项目名称")
    private String fedStdItemName;

    @ApiModelProperty(value = "优良")
    private String fedGoodVal;

    @ApiModelProperty(value = "一般")
    private String fedNormalVal;

    @ApiModelProperty(value = "差")
    private String fedBadVal;

    @ApiModelProperty(value = "保存的饲养标准项目id")
    private String [] fedStdItemIdArray;

    @ApiModelProperty(value = "保存优良值")
    private String [] fedGoodValArray;

    @ApiModelProperty(value = "保存一般值")
    private String [] fedNormalValArray;

    @ApiModelProperty(value = "差保存值")
    private String [] fedBadValArray;

    @ApiModelProperty(value = "0:是目录节点,1:不是目录节点")
    private String isCatalog;

    @ApiModelProperty(value = "产品种源标准子节点")
    private List<FedStdBean> pdFedStds;

    /**
     * Getter method for property <tt>standardId</tt>.
     *
     * @return property value of standardId
     */
    public Long getStandardId() {
        return standardId;
    }

    /**
     * Setter method for property <tt>standardId</tt>.
     *
     * @param standardId value to be assigned to property standardId
     */
    public void setStandardId(Long standardId) {
        this.standardId = standardId;
    }

    /**
     * Getter method for property <tt>fedStdItemId</tt>.
     *
     * @return property value of fedStdItemId
     */
    public String getFedStdItemId() {
        return fedStdItemId;
    }

    /**
     * Setter method for property <tt>fedStdItemId</tt>.
     *
     * @param fedStdItemId value to be assigned to property fedStdItemId
     */
    public void setFedStdItemId(String fedStdItemId) {
        this.fedStdItemId = fedStdItemId;
    }

    /**
     * Getter method for property <tt>fedStdItemName</tt>.
     *
     * @return property value of fedStdItemName
     */
    public String getFedStdItemName() {
        return fedStdItemName;
    }

    /**
     * Setter method for property <tt>fedStdItemName</tt>.
     *
     * @param fedStdItemName value to be assigned to property fedStdItemName
     */
    public void setFedStdItemName(String fedStdItemName) {
        this.fedStdItemName = fedStdItemName;
    }

    /**
     * Getter method for property <tt>fedGoodVal</tt>.
     *
     * @return property value of fedGoodVal
     */
    public String getFedGoodVal() {
        return fedGoodVal;
    }

    /**
     * Setter method for property <tt>fedGoodVal</tt>.
     *
     * @param fedGoodVal value to be assigned to property fedGoodVal
     */
    public void setFedGoodVal(String fedGoodVal) {
        this.fedGoodVal = fedGoodVal;
    }

    /**
     * Getter method for property <tt>fedNormalVal</tt>.
     *
     * @return property value of fedNormalVal
     */
    public String getFedNormalVal() {
        return fedNormalVal;
    }

    /**
     * Setter method for property <tt>fedNormalVal</tt>.
     *
     * @param fedNormalVal value to be assigned to property fedNormalVal
     */
    public void setFedNormalVal(String fedNormalVal) {
        this.fedNormalVal = fedNormalVal;
    }

    /**
     * Getter method for property <tt>fedBadVal</tt>.
     *
     * @return property value of fedBadVal
     */
    public String getFedBadVal() {
        return fedBadVal;
    }


    private String goodVal;

    private String normalVal;

    private String badVal;

    /**
     * Setter method for property <tt>fedBadVal</tt>.
     *
     * @param fedBadVal value to be assigned to property fedBadVal
     */
    public void setFedBadVal(String fedBadVal) {
        this.fedBadVal = fedBadVal;
    }

    /**
     * Getter method for property <tt>fedStdItemIdArray</tt>.
     *
     * @return property value of fedStdItemIdArray
     */
    public String[] getFedStdItemIdArray() {
        return fedStdItemIdArray;
    }

    /**
     * Setter method for property <tt>fedStdItemIdArray</tt>.
     *
     * @param fedStdItemIdArray value to be assigned to property fedStdItemIdArray
     */
    public void setFedStdItemIdArray(String[] fedStdItemIdArray) {
        this.fedStdItemIdArray = fedStdItemIdArray;
    }

    /**
     * Getter method for property <tt>fedGoodValArray</tt>.
     *
     * @return property value of fedGoodValArray
     */
    public String[] getFedGoodValArray() {
        return fedGoodValArray;
    }

    /**
     * Setter method for property <tt>fedGoodValArray</tt>.
     *
     * @param fedGoodValArray value to be assigned to property fedGoodValArray
     */
    public void setFedGoodValArray(String[] fedGoodValArray) {
        this.fedGoodValArray = fedGoodValArray;
    }

    /**
     * Getter method for property <tt>fedNormalValArray</tt>.
     *
     * @return property value of fedNormalValArray
     */
    public String[] getFedNormalValArray() {
        return fedNormalValArray;
    }

    /**
     * Setter method for property <tt>fedNormalValArray</tt>.
     *
     * @param fedNormalValArray value to be assigned to property fedNormalValArray
     */
    public void setFedNormalValArray(String[] fedNormalValArray) {
        this.fedNormalValArray = fedNormalValArray;
    }

    /**
     * Getter method for property <tt>fedBadValArray</tt>.
     *
     * @return property value of fedBadValArray
     */
    public String[] getFedBadValArray() {
        return fedBadValArray;
    }

    /**
     * Setter method for property <tt>fedBadValArray</tt>.
     *
     * @param fedBadValArray value to be assigned to property fedBadValArray
     */
    public void setFedBadValArray(String[] fedBadValArray) {
        this.fedBadValArray = fedBadValArray;
    }

    public String getGoodVal() {
        return goodVal;
    }

    public void setGoodVal(String goodVal) {
        this.goodVal = goodVal;
    }

    public String getNormalVal() {
        return normalVal;
    }

    public void setNormalVal(String normalVal) {
        this.normalVal = normalVal;
    }

    public String getBadVal() {
        return badVal;
    }

    public void setBadVal(String badVal) {
        this.badVal = badVal;
    }

    public String getIsCatalog() {
        return isCatalog;
    }

    public void setIsCatalog(String isCatalog) {
        this.isCatalog = isCatalog;
    }

    public List<FedStdBean> getPdFedStds() {
        return pdFedStds;
    }

    public void setPdFedStds(List<FedStdBean> pdFedStds) {
        this.pdFedStds = pdFedStds;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
