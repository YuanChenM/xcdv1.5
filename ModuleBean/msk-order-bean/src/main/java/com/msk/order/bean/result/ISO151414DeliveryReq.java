package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

/**
 * Created by liu_tao2 on 2016/8/1.
 */
public class ISO151414DeliveryReq extends BaseResult {
    /** 动检证要求，1:要 */
    private String vicFlg;
    /** 装卸要求 */
    private String unloadReq;
    /** 包装要求 */
    private String packingReq;
    /** 距离门店最近停车距离(米) */
    private Integer parkingDistance;
    /** 其它配送服务要求 */
    private String otherDeliveryReq;
    /** 本次配送服务要求 */
    private String thisDeliveryReq;

    public String getVicFlg() {
        return vicFlg;
    }

    public void setVicFlg(String vicFlg) {
        this.vicFlg = vicFlg;
    }

    public String getUnloadReq() {
        return unloadReq;
    }

    public void setUnloadReq(String unloadReq) {
        this.unloadReq = unloadReq;
    }

    public String getPackingReq() {
        return packingReq;
    }

    public void setPackingReq(String packingReq) {
        this.packingReq = packingReq;
    }

    public Integer getParkingDistance() {
        return parkingDistance;
    }

    public void setParkingDistance(Integer parkingDistance) {
        this.parkingDistance = parkingDistance;
    }

    public String getOtherDeliveryReq() {
        return otherDeliveryReq;
    }

    public void setOtherDeliveryReq(String otherDeliveryReq) {
        this.otherDeliveryReq = otherDeliveryReq;
    }

    public String getThisDeliveryReq() {
        return thisDeliveryReq;
    }

    public void setThisDeliveryReq(String thisDeliveryReq) {
        this.thisDeliveryReq = thisDeliveryReq;
    }
}
