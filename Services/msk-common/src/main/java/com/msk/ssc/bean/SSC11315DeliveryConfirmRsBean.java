package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryConfirmBasic;
import java.util.List;

/**
 * Created by sun_jiaju on 2016/7/5.
 */
public class SSC11315DeliveryConfirmRsBean extends SscDeliveryConfirmBasic {
    // 合同名称
    private String contractName;
    // 发货预入库单ID
    private Long deliveryPreIntoId;
    // 发货确认产品信息
    private List<SSC11315DeliveryConfirmDetailRsBean> deliveryConfirmDetailist;

    //本批已发车次
    private  Integer shippedVehicleNumber;

    private String lgcsAreaCode;
    //物流区名称
    private String lgcsAreaName;


    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public List<SSC11315DeliveryConfirmDetailRsBean> getDeliveryConfirmDetailist() {
        return deliveryConfirmDetailist;
    }

    public void setDeliveryConfirmDetailist(List<SSC11315DeliveryConfirmDetailRsBean> deliveryConfirmDetailist) {
        this.deliveryConfirmDetailist = deliveryConfirmDetailist;
    }

    public Long getDeliveryPreIntoId() {
        return deliveryPreIntoId;
    }

    public void setDeliveryPreIntoId(Long deliveryPreIntoId) {
        this.deliveryPreIntoId = deliveryPreIntoId;
    }

    public Integer getShippedVehicleNumber() {
        return shippedVehicleNumber;
    }

    public void setShippedVehicleNumber(Integer shippedVehicleNumber) {
        this.shippedVehicleNumber = shippedVehicleNumber;
    }
}
