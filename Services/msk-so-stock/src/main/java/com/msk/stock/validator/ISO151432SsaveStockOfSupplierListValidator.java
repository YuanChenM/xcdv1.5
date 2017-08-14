package com.msk.stock.validator;
import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.stock.bean.ISO151435RsParam;
import com.msk.stock.bean.StockRsParam;
import com.msk.stock.bean.StockRsParamList;
import com.msk.stock.bean.StockSupplier;

import java.util.List;

/**
 * @author zhang_qiang1
 */
public class ISO151432SsaveStockOfSupplierListValidator extends BaseValidator<RsRequest<StockRsParamList>> {
    @Override
    public void validatorData(RsRequest<StockRsParamList> request) {
        StockRsParamList stockRsParamList=request.getParam();
        this.validatorRequired("stockRsParamList不能为空",stockRsParamList);
        List<StockRsParam>pdList=stockRsParamList.getPdList();
        if(pdList.size()==0){
            this.validatorRequired("pdList参数不能为空",pdList);
        }else {
            for(StockRsParam stockRsParam:pdList){
                this.validatorRequired("logcsCode参数不能为空",stockRsParam.getLgcsCode());
                this.validatorRequired("pdCode参数不能为空",stockRsParam.getPdCode());
                this.validatorRequired("warehouseCode参数不能为空",stockRsParam.getWarehouseCode());
                this.validatorRequired("slCode参数不能为空",stockRsParam.getSlCode());
                this.validatorRequired("stockType参数不能为空",stockRsParam.getStockType());
                this.validatorRequired("supplierCode参数不能为空",stockRsParam.getSupplierCode());
                this.validatorRequired("stockNum参数不能为空",stockRsParam.getStockNum());
                this.validatorRequired("enabledStockQty参数不能为空",stockRsParam.getEnabledStockQty());
                this.validatorRequired("flowId参数不能为空",stockRsParam.getFlowId());
                this.validatorRequired("supplyPlatform参数不能为空",stockRsParam.getSupplyPlatform());
            }
        }

    }
}
