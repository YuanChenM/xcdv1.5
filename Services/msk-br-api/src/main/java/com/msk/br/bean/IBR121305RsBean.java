package com.msk.br.bean;


import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.BrHkGroup;

import java.util.List;

/**
 * Created by zhao_chen on 2016/7/18.
 */
public class IBR121305RsBean extends BrHkGroup {
    List<IBR121305RsParam> houseList;

    public List<IBR121305RsParam> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<IBR121305RsParam> houseList) {
        this.houseList = houseList;
    }
}
