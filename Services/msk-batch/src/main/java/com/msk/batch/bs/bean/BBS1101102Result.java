package com.msk.batch.bs.bean;

import java.io.Serializable;
import java.util.List;
import com.msk.batch.bs.bean.BBS1101101Bean;

public class BBS1101102Result implements Serializable{
    private List<BBS1101101Bean> salesList;

    public List<BBS1101101Bean> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<BBS1101101Bean> salesList) {
        this.salesList = salesList;
    }
}
