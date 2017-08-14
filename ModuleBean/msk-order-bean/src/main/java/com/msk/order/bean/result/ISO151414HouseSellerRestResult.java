package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

/**
 * Created by liu_tao2 on 2016/8/12.
 */
public class ISO151414HouseSellerRestResult extends BaseResult {
    /** 买家ID */
    private String buyerId;

    /** 买手编码 */
    private String slCode;

    /** 买手ID */
    private String slId;

    /** 买手名称 */
    private String slName;

    /** 管家id */
    private String houseId;

    /** 管家编号 */
    private String houseCode;

    /** 管家名称 */
    private String houseName;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
