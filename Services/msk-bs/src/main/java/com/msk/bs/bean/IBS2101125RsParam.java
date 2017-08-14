/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>表sl_house_bypraise_rec对应的SlHouseBypraiseRec。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@ApiModel(value = "IBS2101125RsParam",description = "param")
public class IBS2101125RsParam extends BaseParam {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "投诉ID")
    private Long praiseId;
    @ApiModelProperty(value = "买手ID")
    private String slCode;
    @ApiModelProperty(value = "管家ID")
    private String houseCode;

    @ApiModelProperty(value = "买家ID")
    private String byId;

    @ApiModelProperty(value = "点赞数")
    private Long praiseTotal;

    @ApiModelProperty(value = "点赞年月(YYYYMM)")
    private String praiseYm;

    @ApiModelProperty(value = "备考")
    private String backUp;
    /**
     * <p>默认构造函数。</p>
     */
    public IBS2101125RsParam() {

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
