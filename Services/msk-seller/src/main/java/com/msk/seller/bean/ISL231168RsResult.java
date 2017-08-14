package com.msk.seller.bean;

import java.util.List;

/**
 * Created by cx on 2016/2/24.
 */
public class ISL231168RsResult {
    /**
     * 卖家产品类别List
     */
    private List<ISL23116601> pdClassesCodeList;

    public List<ISL23116601> getPdClassesCodeList() {
        return pdClassesCodeList;
    }

    public void setPdClassesCodeList(List<ISL23116601> pdClassesCodeList) {
        this.pdClassesCodeList = pdClassesCodeList;
    }
}
