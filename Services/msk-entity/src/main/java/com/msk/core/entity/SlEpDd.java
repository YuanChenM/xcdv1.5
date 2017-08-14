/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_ep_dd对应的SlEpDd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlEpDd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** EP_ID */
    private Long epId;
    /** DD_ID */
    private Long ddId;
    /** DD_NAME */
    private String ddName;
    /** DD_EQUIPMENT */
    private String ddEquipment;
    /**
     * <p>默认构造函数。</p>
     */
    public SlEpDd() {

    }

    /**
     * <p>EP_ID。</p>
     *
     * @return the EP_ID
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * <p>EP_ID。</p>
     *
     * @param epId EP_ID。
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * <p>DD_ID。</p>
     *
     * @return the DD_ID
     */
    public Long getDdId() {
        return ddId;
    }

    /**
     * <p>DD_ID。</p>
     *
     * @param ddId DD_ID。
     */
    public void setDdId(Long ddId) {
        this.ddId = ddId;
    }

    /**
     * <p>DD_NAME。</p>
     *
     * @return the DD_NAME
     */
    public String getDdName() {
        return ddName;
    }

    /**
     * <p>DD_NAME。</p>
     *
     * @param ddName DD_NAME。
     */
    public void setDdName(String ddName) {
        this.ddName = ddName;
    }

    /**
     * <p>DD_EQUIPMENT。</p>
     *
     * @return the DD_EQUIPMENT
     */
    public String getDdEquipment() {
        return ddEquipment;
    }

    /**
     * <p>DD_EQUIPMENT。</p>
     *
     * @param ddEquipment DD_EQUIPMENT。
     */
    public void setDdEquipment(String ddEquipment) {
        this.ddEquipment = ddEquipment;
    }

}
