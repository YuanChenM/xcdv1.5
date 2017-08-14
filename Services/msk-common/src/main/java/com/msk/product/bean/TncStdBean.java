package com.msk.product.bean;

import com.msk.core.entity.PdTncStd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by yang_chunyan on 2016/5/7.
 */
@ApiModel(value = "TncStdBean", description = "产品标准质量标准")
public class TncStdBean extends PdTncStd {

    @ApiModelProperty(value = "技术标准项目ID")
    private String tncStdItemId;

    @ApiModelProperty(value = "技术标准项目名称")
    private String tncStdItemName;

    @ApiModelProperty(value = "1:一级目录,2:二级目录,3:.. ")
    private String levelId;

    @ApiModelProperty(value = "父节点ID")
    private String parentId;

    @ApiModelProperty(value = "0:是目录节点,1:不是目录节点")
    private String isCatalog;


    @ApiModelProperty(value = "产品加工质量标准子节点")
    private List<TncStdBean> pdTncStds;
    //接口用
    @ApiModelProperty(value = "slTncStdVal")
    private String slTncStdVal;
    @ApiModelProperty(value = "tncStdItemValue1")
    private String tncStdItemValue1;
    @ApiModelProperty(value = "tncStdItemValue2")
    private String tncStdItemValue2;
    @ApiModelProperty(value = "tncStdItemValue3")
    private String tncStdItemValue3;

    /**
     * Getter method for property <tt>slTncStdVal</tt>.
     *
     * @return property value of slTncStdVal
     */
    public String getSlTncStdVal() {
        return slTncStdVal;
    }

    /**
     * Setter method for property <tt>slTncStdVal</tt>.
     *
     * @param slTncStdVal value to be assigned to property slTncStdVal
     */
    public void setSlTncStdVal(String slTncStdVal) {
        this.slTncStdVal = slTncStdVal;
    }

    /**
     * Getter method for property <tt>tncStdItemValue1</tt>.
     *
     * @return property value of tncStdItemValue1
     */
    public String getTncStdItemValue1() {
        return tncStdItemValue1;
    }

    /**
     * Setter method for property <tt>tncStdItemValue1</tt>.
     *
     * @param tncStdItemValue1 value to be assigned to property tncStdItemValue1
     */
    public void setTncStdItemValue1(String tncStdItemValue1) {
        this.tncStdItemValue1 = tncStdItemValue1;
    }

    /**
     * Getter method for property <tt>tncStdItemValue2</tt>.
     *
     * @return property value of tncStdItemValue2
     */
    public String getTncStdItemValue2() {
        return tncStdItemValue2;
    }

    /**
     * Setter method for property <tt>tncStdItemValue2</tt>.
     *
     * @param tncStdItemValue2 value to be assigned to property tncStdItemValue2
     */
    public void setTncStdItemValue2(String tncStdItemValue2) {
        this.tncStdItemValue2 = tncStdItemValue2;
    }

    /**
     * Getter method for property <tt>tncStdItemValue3</tt>.
     *
     * @return property value of tncStdItemValue3
     */
    public String getTncStdItemValue3() {
        return tncStdItemValue3;
    }

    /**
     * Setter method for property <tt>tncStdItemValue3</tt>.
     *
     * @param tncStdItemValue3 value to be assigned to property tncStdItemValue3
     */
    public void setTncStdItemValue3(String tncStdItemValue3) {
        this.tncStdItemValue3 = tncStdItemValue3;
    }

    /**
     * Getter method for property <tt>pdTncStds</tt>.
     *
     * @return property value of pdTncStds
     */
    public List<TncStdBean> getPdTncStds() {
        return pdTncStds;
    }

    /**
     * Setter method for property <tt>pdTncStds</tt>.
     *
     * @param pdTncStds value to be assigned to property pdTncStds
     */
    public void setPdTncStds(List<TncStdBean> pdTncStds) {
        this.pdTncStds = pdTncStds;
    }

    /**
     * Getter method for property <tt>tncStdItemId</tt>.
     *
     * @return property value of tncStdItemId
     */
    @Override
    public String getTncStdItemId() {
        return tncStdItemId;
    }

    /**
     * Setter method for property <tt>tncStdItemId</tt>.
     *
     * @param tncStdItemId value to be assigned to property tncStdItemId
     */
    @Override
    public void setTncStdItemId(String tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
    }

    /**
     * Getter method for property <tt>tncStdItemName</tt>.
     *
     * @return property value of tncStdItemName
     */
    public String getTncStdItemName() {
        return tncStdItemName;
    }

    /**
     * Setter method for property <tt>tncStdItemName</tt>.
     *
     * @param tncStdItemName value to be assigned to property tncStdItemName
     */
    public void setTncStdItemName(String tncStdItemName) {
        this.tncStdItemName = tncStdItemName;
    }

    /**
     * Getter method for property <tt>levelId</tt>.
     *
     * @return property value of levelId
     */
    public String getLevelId() {
        return levelId;
    }

    /**
     * Setter method for property <tt>levelId</tt>.
     *
     * @param levelId value to be assigned to property levelId
     */
    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    /**
     * Getter method for property <tt>parentId</tt>.
     *
     * @return property value of parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Setter method for property <tt>parentId</tt>.
     *
     * @param parentId value to be assigned to property parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * Getter method for property <tt>isCatalog</tt>.
     *
     * @return property value of isCatalog
     */
    public String getIsCatalog() {
        return isCatalog;
    }

    /**
     * Setter method for property <tt>isCatalog</tt>.
     *
     * @param isCatalog value to be assigned to property isCatalog
     */
    public void setIsCatalog(String isCatalog) {
        this.isCatalog = isCatalog;
    }

}
