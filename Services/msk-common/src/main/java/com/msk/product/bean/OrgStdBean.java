package com.msk.product.bean;

import com.msk.core.entity.PdOrgStd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *
 * SL131122Bean.
 * @author gyh
 */
@ApiModel(value = "OrgStdBean", description = "原种种源标准")
public class OrgStdBean extends PdOrgStd {

    @ApiModelProperty(value = "种源标准项目ID")
    private String orgStdItemId;

    @ApiModelProperty(value = "种源标准项目名称")
    private String orgStdItemName;

    @ApiModelProperty(value = "层次ID")
    private String levelId;

    @ApiModelProperty(value = "父节点ID")
    private String parentId;

    @ApiModelProperty(value = "0:是目录节点,1:不是目录节点")
    private String isCatalog;

    @ApiModelProperty(value = "原种种源标准")
    private List<OrgStdBean> pdOrgStds;

    /**
     * Getter method for property <tt>orgStdItemId</tt>.
     *
     * @return property value of orgStdItemId
     */
    @Override
    public String getOrgStdItemId() {
        return orgStdItemId;
    }

    /**
     * Setter method for property <tt>orgStdItemId</tt>.
     *
     * @param orgStdItemId value to be assigned to property orgStdItemId
     */
    @Override
    public void setOrgStdItemId(String orgStdItemId) {
        this.orgStdItemId = orgStdItemId;
    }

    /**
     * Getter method for property <tt>orgStdItemName</tt>.
     *
     * @return property value of orgStdItemName
     */
    public String getOrgStdItemName() {
        return orgStdItemName;
    }

    /**
     * Setter method for property <tt>orgStdItemName</tt>.
     *
     * @param orgStdItemName value to be assigned to property orgStdItemName
     */
    public void setOrgStdItemName(String orgStdItemName) {
        this.orgStdItemName = orgStdItemName;
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

    /**
     * Getter method for property <tt>pdOrgStds</tt>.
     *
     * @return property value of pdOrgStds
     */
    public List<OrgStdBean> getPdOrgStds() {
        return pdOrgStds;
    }

    /**
     * Setter method for property <tt>pdOrgStds</tt>.
     *
     * @param pdOrgStds value to be assigned to property pdOrgStds
     */
    public void setPdOrgStds(List<OrgStdBean> pdOrgStds) {
        this.pdOrgStds = pdOrgStds;
    }
}
