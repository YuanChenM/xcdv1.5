package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryPdControl;

/**
 * Created by liu_yan2 on 2016/8/11.
 */
public class SSC1132601RsBean extends SscDeliveryPdControl{

    /** 计划到货日期 */
    private String planArriveDateStr;
    /** 实际到货日期 */
    private String realArriveDateStr;
    /** 计划确定车辆时间 */
    private String planChooseVehicleDateStr;
    /** 实际确定车辆时间 */
    private String realChooseVehicleDateStr;
    /** 计划装车结束时间 */
    private String planIntoVehicleDateStr;
    /** 实际装车结束时间 */
    private String realIntoVehicleDateStr;
    /** 计划发货时间 */
    private String planOffDateStr;
    /** 实际发货时间 */
    private String realOffDateStr;

    public String getPlanArriveDateStr() {
        return planArriveDateStr;
    }

    public void setPlanArriveDateStr(String planArriveDateStr) {
        this.planArriveDateStr = planArriveDateStr;
    }

    public String getPlanChooseVehicleDateStr() {
        return planChooseVehicleDateStr;
    }

    public void setPlanChooseVehicleDateStr(String planChooseVehicleDateStr) {
        this.planChooseVehicleDateStr = planChooseVehicleDateStr;
    }

    public String getRealArriveDateStr() {
        return realArriveDateStr;
    }

    public void setRealArriveDateStr(String realArriveDateStr) {
        this.realArriveDateStr = realArriveDateStr;
    }

    public String getRealChooseVehicleDateStr() {
        return realChooseVehicleDateStr;
    }

    public void setRealChooseVehicleDateStr(String realChooseVehicleDateStr) {
        this.realChooseVehicleDateStr = realChooseVehicleDateStr;
    }

    public String getPlanIntoVehicleDateStr() {
        return planIntoVehicleDateStr;
    }

    public void setPlanIntoVehicleDateStr(String planIntoVehicleDateStr) {
        this.planIntoVehicleDateStr = planIntoVehicleDateStr;
    }

    public String getRealIntoVehicleDateStr() {
        return realIntoVehicleDateStr;
    }

    public void setRealIntoVehicleDateStr(String realIntoVehicleDateStr) {
        this.realIntoVehicleDateStr = realIntoVehicleDateStr;
    }

    public String getPlanOffDateStr() {
        return planOffDateStr;
    }

    public void setPlanOffDateStr(String planOffDateStr) {
        this.planOffDateStr = planOffDateStr;
    }

    public String getRealOffDateStr() {
        return realOffDateStr;
    }

    public void setRealOffDateStr(String realOffDateStr) {
        this.realOffDateStr = realOffDateStr;
    }
}
