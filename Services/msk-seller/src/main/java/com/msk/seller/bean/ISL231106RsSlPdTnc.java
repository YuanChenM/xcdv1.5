package com.msk.seller.bean;

import java.util.List;

/**
 * Created by gyh on 2016/2/23.
 */
public class ISL231106RsSlPdTnc {
    private String slCode;//卖家编码
    /** 卖家产品ID */
    private Integer slPdId;
    private String slTncStd;//卖家产品技术标准
    private Integer slTncGradeCode;//产品质量标准定级
    private String loginId;//创建者ID
    private String delFlg;//删除
    private Integer ver;//版本号
    private List<ISL231106RsPdTncStd> slPdTncStdList;//卖家产品质量标准值信息List

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
     * Getter method for property <tt>slTncStd</tt>.
     *
     * @return property value of slTncStd
     */
    public String getSlTncStd() {
        return slTncStd;
    }

    /**
     * Setter method for property <tt>slTncStd</tt>.
     *
     * @param slTncStd value to be assigned to property slTncStd
     */
    public void setSlTncStd(String slTncStd) {
        this.slTncStd = slTncStd;
    }

    /**
     * Getter method for property <tt>slTncGradeCode</tt>.
     *
     * @return property value of slTncGradeCode
     */
    public Integer getSlTncGradeCode() {
        return slTncGradeCode;
    }

    /**
     * Setter method for property <tt>slTncGradeCode</tt>.
     *
     * @param slTncGradeCode value to be assigned to property slTncGradeCode
     */
    public void setSlTncGradeCode(Integer slTncGradeCode) {
        this.slTncGradeCode = slTncGradeCode;
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
     * Getter method for property <tt>slPdTncStdList</tt>.
     *
     * @return property value of slPdTncStdList
     */
    public List<ISL231106RsPdTncStd> getSlPdTncStdList() {
        return slPdTncStdList;
    }

    /**
     * Setter method for property <tt>slPdTncStdList</tt>.
     *
     * @param slPdTncStdList value to be assigned to property slPdTncStdList
     */
    public void setSlPdTncStdList(List<ISL231106RsPdTncStd> slPdTncStdList) {
        this.slPdTncStdList = slPdTncStdList;
    }
}
