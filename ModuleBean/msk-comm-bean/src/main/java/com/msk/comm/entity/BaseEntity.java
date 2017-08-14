package com.msk.comm.entity;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
@MappedSuperclass
@Inheritance(strategy= InheritanceType.JOINED)
public class BaseEntity implements Serializable{
    /** 删除标志 */
    private String delFlg;
    /** 创建者ID */
    private String crtId;
    /** 创建日时 */
    private Date crtTime;
    /** 更新者ID */
    private String updId;
    /** 更新日时 */
    private Date updTime;
    /** 版本号 */
    private Integer ver;
    /** 生效者ID */
    private String actId;
    /** 生效日时 */
    private Date actTime;

    /**
     * Get the delFlg.
     *
     * @return delFlg
     *
     * @author administator
     */
    public String getDelFlg() {
        return this.delFlg;
    }

    /**
     * Set the delFlg.
     *
     * @param delFlg delFlg
     *
     * @author administator
     */
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * Get the crtId.
     *
     * @return crtId
     *
     * @author administator
     */
    public String getCrtId() {
        return this.crtId;
    }

    /**
     * Set the crtId.
     *
     * @param crtId crtId
     *
     * @author administator
     */
    public void setCrtId(String crtId) {
        this.crtId = crtId;
    }

    /**
     * Get the crtTime.
     *
     * @return crtTime
     *
     * @author administator
     */
    public Date getCrtTime() {
        return this.crtTime;
    }

    /**
     * Set the crtTime.
     *
     * @param crtTime crtTime
     *
     * @author administator
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * Get the updId.
     *
     * @return updId
     *
     * @author administator
     */
    public String getUpdId() {
        return this.updId;
    }

    /**
     * Set the updId.
     *
     * @param updId updId
     *
     * @author administator
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    /**
     * Get the updTime.
     *
     * @return updTime
     *
     * @author administator
     */
    public Date getUpdTime() {
        return this.updTime;
    }

    /**
     * Set the updTime.
     *
     * @param updTime updTime
     *
     * @author administator
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * Get the ver.
     *
     * @return ver
     *
     * @author administator
     */
    public Integer getVer() {
        return this.ver;
    }

    /**
     * Set the ver.
     *
     * @param ver ver
     *
     * @author administator
     */
    public void setVer(Integer ver) {
        this.ver = ver;
    }

    /**
     * Get the actId.
     *
     * @return actId
     *
     * @author administator
     */
    public String getActId() {
        return this.actId;
    }

    /**
     * Set the actId.
     *
     * @param actId actId
     *
     * @author administator
     */
    public void setActId(String actId) {
        this.actId = actId;
    }

    /**
     * Get the actTime.
     *
     * @return actTime
     *
     * @author administator
     */
    public Date getActTime() {
        return this.actTime;
    }

    /**
     * Set the actTime.
     *
     * @param actTime actTime
     *
     * @author administator
     */
    public void setActTime(Date actTime) {
        this.actTime = actTime;
    }
}
