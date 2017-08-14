package com.msk.stock.validator;
import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.stock.bean.StockRsParam;
import com.msk.stock.bean.StockRsParamList;

import java.util.List;

/**
 * @author zhang_qiang1
 */
public class ISO151430Validator extends BaseValidator<RsRequest<StockRsParamList>> {
    @Override
    public void validatorData(RsRequest<StockRsParamList> request) {
        StockRsParamList iso151430RsParam=request.getParam();
        this.validatorRequired("参数不能为空",iso151430RsParam);
        List<StockRsParam> pdList=iso151430RsParam.getPdList();
        if (pdList.size()==0){
            this.validatorRequired("pdList<StockRsParam> 参数不能为空",null);
        }

    }
}
