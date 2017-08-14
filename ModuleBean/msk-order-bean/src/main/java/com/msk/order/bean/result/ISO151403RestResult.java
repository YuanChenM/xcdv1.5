package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * ISO151403_查询供应商列表接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151403RestResult extends BaseResult {
    private List<ISO151403SupplierRestResult> supplierList;

    public List<ISO151403SupplierRestResult> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<ISO151403SupplierRestResult> supplierList) {
        this.supplierList = supplierList;
    }
}
