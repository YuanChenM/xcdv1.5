package com.msk.stock.validator;


import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.stock.bean.StockRsParam;
import com.msk.stock.bean.StockRsParamList;

import java.util.List;

/**
 * @author zhang_qiang1
 */
public class ISO151432Validator extends BaseValidator<RsRequest<StockRsParamList>> {
    @Override
    public void validatorData(RsRequest<StockRsParamList> request) {
        StockRsParamList iso151432RsParam=request.getParam();
        this.validatorRequired("参数不能为空",iso151432RsParam);
        List<StockRsParam> pdList=iso151432RsParam.getPdList();
        if (pdList.size()==0){
            this.validatorRequired("pdList<StockRsParam> 参数不能为空",null);
        }


    }
}
