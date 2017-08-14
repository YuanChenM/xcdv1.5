package com.msk.seller.bean;

/**
 * Created by gyh on 2016/2/26.
 */
public class ISL231106RsSlPdPkg{
    private String slCode;//买家编码
    private Integer slPdId;//卖家产品ID
    private Integer slPdPkgId;//卖家产品ID
    private Integer standardId;//标准产品ID
    private String pkgCode;//卖家产品包装Id
    private String loginId;//创建者ID/更新者ID
    private String delFlg;//删除标志

    /**
     * Getter method for property <tt>delFlg</tt>.
     *
     * @return property value of delFlg
     */
    public String getDelFlg() {
        return delFlg;
    }

    /**
     * Setter method for property <tt>delFlg</tt>.
     *
     * @param delFlg value to be assigned to property delFlg
     */
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>slPdId</tt>.
     *
     * @return property value of slPdId
     */
    public Integer getSlPdId() {
        return slPdId;
    }

    /**
     * Setter method for property <tt>slPdId</tt>.
     *
     * @param slPdId value to be assigned to property slPdId
     */
    public void setSlPdId(Integer slPdId) {
        this.slPdId = slPdId;
    }

    /**
     * Getter method for property <tt>slPdPkgId</tt>.
     *
     * @return property value of slPdPkgId
     */
    public Integer getSlPdPkgId() {
        return slPdPkgId;
    }

    /**
     * Setter method for property <tt>slPdPkgId</tt>.
     *
     * @param slPdPkgId value to be assigned to property slPdPkgId
     */
    public void setSlPdPkgId(Integer slPdPkgId) {
        this.slPdPkgId = slPdPkgId;
    }

    /**
     * Getter method for property <tt>standardId</tt>.
     *
     * @return property value of standardId
     */
    public Integer getStandardId() {
        return standardId;
    }

    /**
     * Setter method for property <tt>standardId</tt>.
     *
     * @param standardId value to be assigned to property standardId
     */
    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    /**
     * Getter method for property <tt>pkgCode</tt>.
     *
     * @return property value of pkgCode
     */
    public String getPkgCode() {
        return pkgCode;
    }

    /**
     * Setter method for property <tt>pkgCode</tt>.
     *
     * @param pkgCode value to be assigned to property pkgCode
     */
    public void setPkgCode(String pkgCode) {
        this.pkgCode = pkgCode;
    }

    /**
     * Getter method for property <tt>loginId</tt>.
     *
     * @return property value of loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Setter method for property <tt>loginId</tt>.
     *
     * @param loginId value to be assigned to property loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
