package com.msk.order.validator;
import com.msk.common.bean.ExceptionMessage;
import com.msk.common.constant.NumberConstant;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151408RestParam;
import com.msk.order.bean.param.ISO151408RestProductListParam;
import com.msk.order.bean.param.ISO151408RestShipListParam;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/9/12.
 */
public class ISO151408RestValidator extends BaseCustomValidation {
    @Override
    public List<ExceptionMessage> validation(Object o) {
        List<ExceptionMessage> list = new ArrayList<>();
        ISO151408RestParam restParam = (ISO151408RestParam) o;
        Long orderId = restParam.getOrderId();
        if (!this.validatorRequired(orderId)) {
            list.add(new ExceptionMessage("订单id不能为空"));
        }
        Integer returnMode = restParam.getReturnMode();
        if (!this.validatorRequired(returnMode)) {
            list.add(new ExceptionMessage("退货模式不能为空"));
        }
        String applyMan = restParam.getApplyMan();
        if (!this.validatorRequired(applyMan)) {
            list.add(new ExceptionMessage("申请人名称不能为空"));
        }
        Date applyTime = restParam.getApplyTime();
        if (!this.validatorRequired(applyTime)) {
            list.add(new ExceptionMessage("申请时间不能为空"));
        }
        Integer returnReasonID = restParam.getReturnReasonID();
        if (!this.validatorRequired(returnReasonID)) {
            list.add(new ExceptionMessage("退货原因ID不能为空"));
        }
        String returnReasonName = restParam.getReturnReasonName();
        if (!this.validatorRequired(returnReasonName)) {
            list.add(new ExceptionMessage("退货原因名称不能为空"));
        }
        String buyerId = restParam.getBuyerId();
        if (!this.validatorRequired(buyerId)) {
            list.add(new ExceptionMessage("买家ID不能为空"));
        }
        String buyerCode = restParam.getBuyerCode();
        if (!this.validatorRequired(buyerCode)) {
            list.add(new ExceptionMessage("买家编码不能为空"));
        }
        Integer isPaid = restParam.getIsPaid();
        if (!this.validatorRequired(isPaid)) {
            list.add(new ExceptionMessage("isPaid不能为空"));
        }
        List<ISO151408RestShipListParam> shipList = restParam.getShipList();
        if (!CollectionUtils.isEmpty(shipList)) {//  不为空时
            for (ISO151408RestShipListParam ship : shipList) {
                List<ISO151408RestProductListParam> productList = ship.getProductList();
                if (CollectionUtils.isEmpty(productList)) {
                    list.add(new ExceptionMessage("迟收产品信息不能为空"));
                }
                for (ISO151408RestProductListParam product : productList) {
                    int rowNum = NumberConstant.IntDef.INT_ONE;
                    Long shipDetailId = product.getShipDetailId();
                    if (!this.validatorRequired(shipDetailId)) {
                        list.add(new ExceptionMessage("第" + rowNum + "行供货明细ID不能为空"));
                    }
                    String supplierCode = product.getSupplierCode();
                    if (!this.validatorRequired(supplierCode)) {
                        list.add(new ExceptionMessage("第" + rowNum + "行供应商编码不能为空"));
                    }
                    String pdCode = product.getPdCode();
                    if (!this.validatorRequired(pdCode)) {
                        list.add(new ExceptionMessage("第" + rowNum + "行产品编码不能为空"));
                    }
                    String skuCode = product.getSkuCode();
                    if (!this.validatorRequired(skuCode)) {
                        list.add(new ExceptionMessage("第" + rowNum + "行SKU编码不能为空"));
                    }
                    BigDecimal returnQty = product.getReturnQty();
                    if (!this.validatorRequired(returnQty)) {
                        list.add(new ExceptionMessage("第" + rowNum + "行退货数量不能为空"));
                    }
                    rowNum++;
                }
            }
        }
        return list;
    }
}
