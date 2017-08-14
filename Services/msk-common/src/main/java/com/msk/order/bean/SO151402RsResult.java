package com.msk.order.bean;

import com.msk.core.entity.SoOrderDetail;
import com.msk.core.entity.SoOrderDetailAvailability;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
public class SO151402RsResult implements Serializable {
   private List<SO151402Bean> soOrderDetails;

    public List<SO151402Bean> getSoOrderDetails() {
        return soOrderDetails;
    }

    public void setSoOrderDetails(List<SO151402Bean> soOrderDetails) {
        this.soOrderDetails = soOrderDetails;
    }
}
