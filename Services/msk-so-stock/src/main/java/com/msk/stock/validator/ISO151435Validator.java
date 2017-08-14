package com.msk.stock.validator;
import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.stock.bean.ISO151435RsParam;
import com.msk.stock.bean.StockSupplier;

import java.util.List;

/**
 * @author zhang_qiang1
 */
public class ISO151435Validator extends BaseValidator<RsRequest<ISO151435RsParam>> {
    @Override
    public void validatorData(RsRequest<ISO151435RsParam> request) {
        ISO151435RsParam iso151435RsParam=request.getParam();
        this.validatorRequired("slCode参数不能为空",iso151435RsParam.getSlCode());
        this.validatorRequired("supplyPatform参数不能为空",iso151435RsParam.getSupplyPatform());
        this.validatorRequired("orderType参数不能为空",iso151435RsParam.getOrderType());
        this.validatorRequired("buyerId参数不能为空",iso151435RsParam.getBuyerId());
        this.validatorRequired("buyerName参数不能为空",iso151435RsParam.getBuyerName());
        this.validatorRequired("lgcsCode参数不能为空",iso151435RsParam.getLgcsCode());
        List<StockSupplier>supplierList=iso151435RsParam.getSupplierList();
        if(supplierList.size()==0){
            this.validatorRequired("supplierList参数不能为空",supplierList);
        }else {
            for(StockSupplier supplier:supplierList){
                this.validatorRequired("orderQty参数不能为空",supplier.getOrderQty());
                this.validatorRequired("pdCode参数不能为空",supplier.getPdCode());
                this.validatorRequired("supplierCode参数不能为空",supplier.getSupplierCode());
            }
        }

    }
}
