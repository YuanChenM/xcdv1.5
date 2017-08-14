package com.msk.stock.validator;


import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.stock.bean.ISO151433RsParam;
import com.msk.stock.bean.StockRsParam;

import java.util.List;

/**
 * @author zhang_qiang1
 */
public class ISO151433Validator extends BaseValidator<RsRequest<ISO151433RsParam>> {
    @Override
    public void validatorData(RsRequest<ISO151433RsParam> request) {
        ISO151433RsParam iso151433RsParam=request.getParam();
        this.validatorRequired("参数不能为空",iso151433RsParam);
        List<StockRsParam>pdList=iso151433RsParam.getPdList();
        this.validatorRequired("pdList不能为空", pdList);
        if(pdList.size()>0){
            int line=0;
            for(StockRsParam stockRsParam:pdList){
               line= line+1;
                this.validatorRequired("pdList 中第"+line+"个StockRsParam  pdTypeCode不能为空",stockRsParam.getPdTypeCode());
                this.validatorRequired("pdList 中第"+line+"个StockRsParam  lgcsCode不能为空",stockRsParam.getLgcsCode());
                this.validatorRequired("pdList 中第"+line+"个StockRsParam  salePlatform不能为空",stockRsParam.getSalePlatform());
            }
        }else {
          this.validatorRequired("pdList<StockRsParam>参数不能为空", null);
        }
    }
}
