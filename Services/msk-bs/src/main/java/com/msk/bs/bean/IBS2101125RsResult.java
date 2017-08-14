/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>表sl_house_bypraise_rec对应的IBS2101125RsBean。</p>
 *冻品管家点赞表
 * @author 自动生成
 * @version 1.00
 */
@ApiModel(value = "IBS2101125RsResult",description = "result")
public class IBS2101125RsResult extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 投诉ID */
    @ApiModelProperty(value = "投诉ID")
    private Long praiseId;
    /** SL_CODE */
    @ApiModelProperty(value = "买手ID")
    private String slCode;
    /** HOUSE_CODE */
    @ApiModelProperty(value = "管家ID")
    private String houseCode;
    /** 买家ID */
    @ApiModelProperty(value = "买家ID")
    private String byId;
    /** 点赞数 */
    @ApiModelProperty(value = "点赞数")
    private Long praiseTotal;
    /** 点赞年月(YYYYMM) */
    @ApiModelProperty(value = "点赞年月(YYYYMM)")
    private String praiseYm;
    /** 备考 */
    @ApiModelProperty(value = "备考")
    private String backUp;
    /**
     * <p>默认构造函数。</p>
     */
    public IBS2101125RsResult() {

    }

    /**
     * <p>投诉ID。</p>
     *
     * @return the 投诉ID
     */
    public Long getPraiseId() {
        return praiseId;
    }

    /**
     * <p>投诉ID。</p>
     *
     * @param praiseId 投诉ID。
     */
    public void setPraiseId(Long praiseId) {
        this.praiseId = praiseId;
    }

    /**
     * <p>SL_CODE。</p>
     *
     * @return the SL_CODE
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>SL_CODE。</p>
     *
     * @param slCode SL_CODE。
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>HOUSE_CODE。</p>
     *
     * @return the HOUSE_CODE
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>HOUSE_CODE。</p>
     *
     * @param houseCode HOUSE_CODE。
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>买家ID。</p>
     *
     * @return the 买家ID
     */
    public String getById() {
        return byId;
    }

    /**
     * <p>买家ID。</p>
     *
     * @param byId 买家ID。
     */
    public void setById(String byId) {
        this.byId = byId;
    }

    /**
     * <p>点赞数。</p>
     *
     * @return the 点赞数
     */
    public Long getPraiseTotal() {
        return praiseTotal;
    }

    /**
     * <p>点赞数。</p>
     *
     * @param praiseTotal 点赞数。
     */
    public void setPraiseTotal(Long praiseTotal) {
        this.praiseTotal = praiseTotal;
    }

    /**
     * <p>点赞年月(YYYYMM)。</p>
     *
     * @return the 点赞年月(YYYYMM)
     */
    public String getPraiseYm() {
        return praiseYm;
    }

    /**
     * <p>点赞年月(YYYYMM)。</p>
     *
     * @param praiseYm 点赞年月(YYYYMM)。
     */
    public void setPraiseYm(String praiseYm) {
        this.praiseYm = praiseYm;
    }

    /**
     * <p>备考。</p>
     *
     * @return the 备考
     */
    public String getBackUp() {
        return backUp;
    }

    /**
     * <p>备考。</p>
     *
     * @param backUp 备考。
     */
    public void setBackUp(String backUp) {
        this.backUp = backUp;
    }

}
