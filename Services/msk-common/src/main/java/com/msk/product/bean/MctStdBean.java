package com.msk.product.bean;

import com.msk.core.entity.PdMctStd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 *
 * SL241117Bean.
 * @author gyh
 */
@ApiModel(value = "MctStdBean", description = "产品技术标准")
public class MctStdBean extends PdMctStd {

    @ApiModelProperty(value = "加工技术标准项目ID")
    private String mctStdItemId;

    @ApiModelProperty(value = "加工技术标准项目名称")
    private String mctStdItemName;

    @ApiModelProperty(value = "层次ID")
    private String levelId;

    @ApiModelProperty(value = "父节点ID")
    private String parentId;

    @ApiModelProperty(value = "是否为目录节点")
    private String isCatalog;

    @ApiModelProperty(value = "子节点")
    private List<MctStdBean> pdMctStds;

    /**
     * Getter method for property <tt>pdMctStds</tt>.
     *
     * @return property value of pdMctStds
     */
    public List<MctStdBean> getPdMctStds() {
        return pdMctStds;
    }

    /**
     * Setter method for property <tt>pdMctStds</tt>.
     *
     * @param pdMctStds value to be assigned to property pdMctStds
     */
    public void setPdMctStds(List<MctStdBean> pdMctStds) {
        this.pdMctStds = pdMctStds;
    }

    /**
     * Getter method for property <tt>mctStdItemId</tt>.
     *
     * @return property value of mctStdItemId
     */
    @Override
    public String getMctStdItemId() {
        return mctStdItemId;
    }

    /**
     * Setter method for property <tt>mctStdItemId</tt>.
     *
     * @param mctStdItemId value to be assigned to property mctStdItemId
     */
    @Override
    public void setMctStdItemId(String mctStdItemId) {
        this.mctStdItemId = mctStdItemId;
    }

    /**
     * Getter method for property <tt>mctStdItemName</tt>.
     *
     * @return property value of mctStdItemName
     */
    public String getMctStdItemName() {
        return mctStdItemName;
    }

    /**
     * Setter method for property <tt>mctStdItemName</tt>.
     *
     * @param mctStdItemName value to be assigned to property mctStdItemName
     */
    public void setMctStdItemName(String mctStdItemName) {
        this.mctStdItemName = mctStdItemName;
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
