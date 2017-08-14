package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.ByBuyerType;

import java.util.List;

/**
 * Created by ren_qiang on 2016/9/13.
 */
public class BS2102124Result extends BaseEntity{

    private List<BS2102124Bean> bs2102124Beans;

    public List<BS2102124Bean> getBs2102124Beans() {
        return bs2102124Beans;
    }

    public void setBs2102124Beans(List<BS2102124Bean> bs2102124Beans) {
        this.bs2102124Beans = bs2102124Beans;
    }
}
