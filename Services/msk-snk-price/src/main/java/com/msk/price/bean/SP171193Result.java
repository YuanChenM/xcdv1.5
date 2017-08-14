package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by wang_shuai on 2016/5/18.
 */
public class SP171193Result extends BaseEntity {
    //id
    private long wayDetailId;
    //通道code
    private String wayCode;
    //通道名
    private String wayName;
    //超级大宗订单
    private String supOrder;
    //大宗订单1级
    private String order1;
    //大宗订单2级
    private String order2;
    //大额订单3级
    private String order3;
    //大额订单4级
    private String order4;
    //大额订单5级
    private String order5;
    //标准订单6级
    private String order6;
    //标准订单7级
    private String order7;
    //标准订单8级
    private String order8;
    //标准订单9级
    private String order9;
    //通道等级code
    private String waygradeCode;
    //通道等级名称
    private String waygradeName;

    public long getWayDetailId() {
        return wayDetailId;
    }

    public void setWayDetailId(long wayDetailId) {
        this.wayDetailId = wayDetailId;
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getSupOrder() {
        return supOrder;
    }

    public void setSupOrder(String supOrder) {
        this.supOrder = supOrder;
    }

    public String getOrder1() {
        return order1;
    }

    public void setOrder1(String order1) {
        this.order1 = order1;
    }

    public String getOrder2() {
        return order2;
    }

    public void setOrder2(String order2) {
        this.order2 = order2;
    }

    public String getOrder3() {
        return order3;
    }

    public void setOrder3(String order3) {
        this.order3 = order3;
    }

    public String getOrder4() {
        return order4;
    }

    public void setOrder4(String order4) {
        this.order4 = order4;
    }

    public String getOrder5() {
        return order5;
    }

    public void setOrder5(String order5) {
        this.order5 = order5;
    }

    public String getOrder6() {
        return order6;
    }

    public void setOrder6(String order6) {
        this.order6 = order6;
    }

    public String getOrder7() {
        return order7;
    }

    public void setOrder7(String order7) {
        this.order7 = order7;
    }

    public String getOrder8() {
        return order8;
    }

    public void setOrder8(String order8) {
        this.order8 = order8;
    }

    public String getOrder9() {
        return order9;
    }

    public void setOrder9(String order9) {
        this.order9 = order9;
    }

    public String getWaygradeCode() {
        return waygradeCode;
    }

    public void setWaygradeCode(String waygradeCode) {
        this.waygradeCode = waygradeCode;
    }

    public String getWaygradeName() {
        return waygradeName;
    }

    public void setWaygradeName(String waygradeName) {
        this.waygradeName = waygradeName;
    }
}
