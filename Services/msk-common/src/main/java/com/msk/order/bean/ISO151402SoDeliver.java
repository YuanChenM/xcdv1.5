package com.msk.order.bean;

import com.msk.core.entity.SoDeliver;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liu_tao2 on 2016/4/19.
 */
public class ISO151402SoDeliver implements Serializable {

   private List<SoDeliver> soDelivers;

    public List<SoDeliver> getSoDelivers() {
        return soDelivers;
    }

    public void setSoDelivers(List<SoDeliver> soDelivers) {
        this.soDelivers = soDelivers;
    }
}
