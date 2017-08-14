package com.msk.seller.bean;


import com.msk.common.base.BaseBean;

/**
 * 品牌荣誉
 * SL2411030801Bean.
 *
 * @author chen_xin
 */
public class SL2411030801Bean extends BaseBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    // 企业id
    private Integer epId;
    // 品牌id
    private Integer brandId;
    // 荣誉ID
    private Integer honorId;
    //荣誉证书编号
    private String honorNo;
    // 发证日期
    private String certDate;
    // 发证单位
    private String certIssuer;
    // 产品荣誉图片
    private String brandHonorPath;

    /**
     * Getter method for property <tt>brandHonorPath</tt>.
     *
     * @return property value of brandHonorPath
     */
    public String getBrandHonorPath() {
        return brandHonorPath;
    }

    /**
     * Setter method for property <tt>brandHonorPath</tt>.
     *
     * @param brandHonorPath value to be assigned to property brandHonorPath
     */
    public void setBrandHonorPath(String brandHonorPath) {
        this.brandHonorPath = brandHonorPath;
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
     * Get the honorId.
     *
     * @return honorId
     *
     * @author Administrator
     */
    public Integer getHonorId() {
        return this.honorId;
    }
    /**
     * Set the honorId.
     *
     * @param honorId honorId
     *
     * @author Administrator
     */
    public void setHonorId(Integer honorId) {
        this.honorId = honorId;
    }
    /**
     * Get the honorNo.
     *
     * @return honorNo
     *
     * @author Administrator
     */
    public String getHonorNo() {
        return this.honorNo;
    }
    /**
     * Set the honorNo.
     *
     * @param honorNo honorNo
     *
     * @author Administrator
     */
    public void setHonorNo(String honorNo) {
        this.honorNo = honorNo;
    }
    /**
     * Get the certDate.
     *
     * @return certDate
     *
     * @author Administrator
     */
    public String getCertDate() {
        return this.certDate;
    }
    /**
     * Set the certDate.
     *
     * @param certDate certDate
     *
     * @author Administrator
     */
    public void setCertDate(String certDate) {
        this.certDate = certDate;
    }
    /**
     * Get the certIssuer.
     *
     * @return certIssuer
     *
     * @author Administrator
     */
    public String getCertIssuer() {
        return this.certIssuer;
    }
    /**
     * Set the certIssuer.
     *s
     * @param certIssuer certIssuer
     *
     * @author Administrator
     */
    public void setCertIssuer(String certIssuer) {
        this.certIssuer = certIssuer;
    }
   
}
