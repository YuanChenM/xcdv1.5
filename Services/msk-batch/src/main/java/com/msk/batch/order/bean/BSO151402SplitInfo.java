package com.msk.batch.order.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SoOrderShipDetail;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/23.
 */
public class BSO151402SplitInfo extends BaseEntity{

    List<SoOrderShipDetail> soOrderShipDetails;

    BSO151402ModifyStockParam stockParam;

    public List<SoOrderShipDetail> getSoOrderShipDetails() {
        return soOrderShipDetails;
    }

    public void setSoOrderShipDetails(List<SoOrderShipDetail> soOrderShipDetails) {
        this.soOrderShipDetails = soOrderShipDetails;
    }

    public BSO151402ModifyStockParam getStockParam() {
        return stockParam;
    }

    public void setStockParam(BSO151402ModifyStockParam stockParam) {
        this.stockParam = stockParam;
    }
}
