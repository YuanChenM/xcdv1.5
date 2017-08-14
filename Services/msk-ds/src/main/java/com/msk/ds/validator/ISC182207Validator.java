package com.msk.ds.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.ds.bean.ISC182207DetailRsParam;
import com.msk.ds.bean.ISC182207RsParam;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by li_kai1 on 2016/10/19.
 */
public class ISC182207Validator extends BaseValidator<RsRequest<ISC182207RsParam>> {

    @Override
    public void validatorData(RsRequest<ISC182207RsParam> requestParam){
        ISC182207RsParam param = requestParam.getParam();
        if (null != param) {
            this.validatorRequired("入库单号", param.getReceipt());
            this.validatorRequired("分销月度", param.getDistMonth());
            this.validatorRequired("物流区编码", param.getLgcsCode());
            this.validatorRequired("供应商编码", param.getSuppCode());
            this.validatorRequired("供应商名称", param.getSuppName());
            this.validatorRequired("生产商ID", param.getPdProduceerId());
            this.validatorRequired("生产商名称", param.getPdProduceerName());
            this.validatorRequired("半旬期", param.getHalfCode());
            this.validatorRequired("发货状态", param.getDeliveryStockStatus());
            this.validatorRequired("创建者ID", param.getCrtId());
            if (CollectionUtils.isNotEmpty(param.getProductList())) {
                for (ISC182207DetailRsParam detailParam : param.getProductList()) {
                    this.validatorRequired("产品编码", detailParam.getPdCode());
                    this.validatorRequired("包装编码", detailParam.getNormsCode());
                    this.validatorRequired("计划发货箱数", detailParam.getPlanDeliveryNum());
                    this.validatorRequired("实际发货箱数", detailParam.getActualDeliveryNum());
                    this.validatorRequired("申请发货箱数", detailParam.getApplyDeliveryNum());
                    this.validatorRequired("仓库确认可发货数量", detailParam.getConfirmDeliveryNum());
                    this.validatorRequired("产品外包装净重", detailParam.getPdOutNw());
                }
            }
        }

    }
}
