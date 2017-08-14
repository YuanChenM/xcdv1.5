package com.msk.batch.bs.bean;

import java.util.List;

import com.hoperun.core.bean.BaseParam;
import com.msk.batch.bs.bean.BBS1101101Bean;

public class BBS1101102Param extends BaseParam{
    private List<BBS1101101Bean> houseAccountList;

    public List<BBS1101101Bean> getHouseAccountList() {
        return houseAccountList;
    }

    public void setHouseAccountList(List<BBS1101101Bean> houseAccountList) {
        this.houseAccountList = houseAccountList;
    }
}
