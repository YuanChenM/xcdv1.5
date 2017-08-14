package com.msk.seller.bean;

import java.util.Date;
import java.util.List;

import com.msk.common.base.BaseBean;

/**
 * 企业产品品牌描述
 * SL24110308Bean.
 *
 * @author chen_xin
 */
public class SL24110308Bean extends BaseBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    // 企业id
    private Integer epId;
    // 企业名称
    private String epName;
    // 品牌id
    private Integer brandId;
    // 品牌名称
    private String brandName;
    // 商标注册证
    private String brandNo;
    // 发证日期
    private Date brandTermBegin;
    // 证书结束日期
    private Date brandTermEnd;
    /**品牌证书图片路径*/
    private String brandPath;
    /**品牌包装图片路径*/
    private String brandPacPath;
    /**荣誉证书*/
    private List<SL2411030801Bean> sl2411030801BeanList;

    /**
     * Getter method for property <tt>brandPath</tt>.
     *
     * @return property value of brandPath
     */
    public String getBrandPath() {
        return brandPath;
    }

    /**
     * Setter method for property <tt>brandPath</tt>.
     *
     * @param brandPath value to be assigned to property brandPath
     */
    public void setBrandPath(String brandPath) {
        this.brandPath = brandPath;
    }

    /**
     * Getter method for property <tt>brandPacPath</tt>.
     *
     * @return property value of brandPacPath
     */
    public String getBrandPacPath() {
        return brandPacPath;
    }

    /**
     * Setter method for property <tt>brandPacPath</tt>.
     *
     * @param brandPacPath value to be assigned to property brandPacPath
     */
    public void setBrandPacPath(String brandPacPath) {
        this.brandPacPath = brandPacPath;
    }

    /**
     * Get the epId.
     *
     * @return epId
     *
     * @author Administrator
     */
    public Integer getEpId() {
        return this.epId;
    }
    /**
     * Set the epId.
     *
     * @param epId epId
     *
     * @author Administrator
     */
    public void setEpId(Integer epId) {
        this.epId = epId;
    }
    /**
     * Get the epName.
     *
     * @return epName
     *
     * @author Administrator
     */
    public String getEpName() {
        return this.epName;
    }
    /**
     * Set the epName.
     *
     * @param epName epName
     *
     * @author Administrator
     */
    public void setEpName(String epName) {
        this.epName = epName;
    }
    /**
     * Get the brandId.
     *
     * @return brandId
     *
     * @author Administrator
     */
    public Integer getBrandId() {
        return this.brandId;
    }
    /**
     * Set the brandId.
     *
     * @param brandId brandId
     *
     * @author Administrator
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
    /**
     * Get the brandName.
     *
     * @return brandName
     *
     * @author Administrator
     */
    public String getBrandName() {
        return this.brandName;
    }
    /**
     * Set the brandName.
     *
     * @param brandName brandName
     *
     * @author Administrator
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    /**
     * Get the brandNo.
     *
     * @return brandNo
     *
     * @author Administrator
     */
    public String getBrandNo() {
        return this.brandNo;
    }
    /**
     * Set the brandNo.
     *
     * @param brandNo brandNo
     *
     * @author Administrator
     */
    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }
    /**
     * Get the brandTermBegin.
     *
     * @return brandTermBegin
     *
     * @author Administrator
     */
    public Date getBrandTermBegin() {
        return this.brandTermBegin;
    }
    /**
     * Set the brandTermBegin.
     *
     * @param brandTermBegin brandTermBegin
     *
     * @author Administrator
     */
    public void setBrandTermBegin(Date brandTermBegin) {
        this.brandTermBegin = brandTermBegin;
    }
    /**
     * Get the brandTermEnd.
     *
     * @return brandTermEnd
     *
     * @author Administrator
     */
    public Date getBrandTermEnd() {
        return this.brandTermEnd;
    }
    /**
     * Set the brandTermEnd.
     *
     * @param brandTermEnd brandTermEnd
     *
     * @author Administrator
     */
    public void setBrandTermEnd(Date brandTermEnd) {
        this.brandTermEnd = brandTermEnd;
    }

    /**
     * Getter method for property <tt>sl2411030801BeanList</tt>.
     *
     * @return property value of sl2411030801BeanList
     */
    public List<SL2411030801Bean> getSl2411030801BeanList() {
        return sl2411030801BeanList;
    }

    /**
     * Setter method for property <tt>sl2411030801BeanList</tt>.
     *
     * @param sl2411030801BeanList value to be assigned to property sl2411030801BeanList
     */
    public void setSl2411030801BeanList(List<SL2411030801Bean> sl2411030801BeanList) {
        this.sl2411030801BeanList = sl2411030801BeanList;
    }
}
