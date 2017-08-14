package com.msk.ssc.bean;

import com.msk.core.entity.SscIntoBasic;
import com.msk.core.entity.SscIntoDetail;

import java.util.List;

/**
 * Created by liu_yan2 on 2016/7/8.
 */
public class SSC11309Bean extends SscIntoBasic {

    private List<SscIntoDetail> sscIntoDetailList;

    public List<SscIntoDetail> getSscIntoDetailList() {
        return sscIntoDetailList;
    }

    public void setSscIntoDetailList(List<SscIntoDetail> sscIntoDetailList) {
        this.sscIntoDetailList = sscIntoDetailList;
    }
}
