package com.msk.br.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.Date;
import java.util.List;

/**
 * Created by tao_zhifa on 2016/10/24.
 */
public class IBR121415RsParam extends BaseParam {

    /** ID */
    private Long id;
    /** 冻品管家主键 */
    private String houseCode;
    /** 买手店主键 */
    private String slCode;
    /** BUYER_ID */
    private String buyerId;
    /** 0：无关系，1：锁定期，2：专属会员 */
    private String relationType;
    /** 关系建立时间 */
    private java.util.Date startTime;
    /** 关系结束时间 */
    private java.util.Date endTime;
    private String result;

    private List<IBR121415RsParam> relationList;

    public List<IBR121415RsParam> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<IBR121415RsParam> relationList) {
        this.relationList = relationList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
