package com.msk.seller.bean;

import java.util.List;

/**
 * Created by gyh on 2016/2/23.
 */
public class ISL231106RsSlPdQty {
    private String slCode;//卖家编码
    /** 卖家产品ID */
    private Integer slPdId;
    private String slQltStd;//卖家产品质量标准
    private Integer slQltGradeCode;//产品质量标准定级
    private String loginId;//创建者ID
    private String delFlg;//删除
    private Integer ver;//版本号
    private List<ISL231106RsPdQltStd> slPdMctStdList;//卖家产品加工技术标准值信息List

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
     * Getter method for property <tt>slQltStd</tt>.
     *
     * @return property value of slQltStd
     */
    public String getSlQltStd() {
        return slQltStd;
    }

    /**
     * Setter method for property <tt>slQltStd</tt>.
     *
     * @param slQltStd value to be assigned to property slQltStd
     */
    public void setSlQltStd(String slQltStd) {
        this.slQltStd = slQltStd;
    }

    /**
     * Getter method for property <tt>slQltGradeCode</tt>.
     *
     * @return property value of slQltGradeCode
     */
    public Integer getSlQltGradeCode() {
        return slQltGradeCode;
    }

    /**
     * Setter method for property <tt>slQltGradeCode</tt>.
     *
     * @param slQltGradeCode value to be assigned to property slQltGradeCode
     */
    public void setSlQltGradeCode(Integer slQltGradeCode) {
        this.slQltGradeCode = slQltGradeCode;
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
     * Getter method for property <tt>ver</tt>.
     *
     * @return property value of ver
     */
    public Integer getVer() {
        return ver;
    }

    /**
     * Setter method for property <tt>ver</tt>.
     *
     * @param ver value to be assigned to property ver
     */
    public void setVer(Integer ver) {
        this.ver = ver;
    }

    /**
     * Getter method for property <tt>slPdMctStdList</tt>.
     *
     * @return property value of slPdMctStdList
     */
    public List<ISL231106RsPdQltStd> getSlPdMctStdList() {
        return slPdMctStdList;
    }

    /**
     * Setter method for property <tt>slPdMctStdList</tt>.
     *
     * @param slPdMctStdList value to be assigned to property slPdMctStdList
     */
    public void setSlPdMctStdList(List<ISL231106RsPdQltStd> slPdMctStdList) {
        this.slPdMctStdList = slPdMctStdList;
    }
}
