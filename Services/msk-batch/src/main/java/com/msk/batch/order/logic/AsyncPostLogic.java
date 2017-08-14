package com.msk.batch.order.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.order.bean.BSO151402OccupyStockParam;
import com.msk.batch.order.bean.BSO151402OrderInfo;
import com.msk.batch.order.bean.BSO151402Param;
import com.msk.batch.order.bean.BSO151402StockResult;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.OrderCodeMasterDef;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.AsyncPostCallBack;
import com.msk.common.utils.RestClientUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liutao on 2016/9/21.
 */
public class AsyncPostLogic extends BaseLogic {

    @Autowired
    private BSO151402Logic bso151402Logic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 供应商占用库存接口
     *
     * @param stockParam
     */
    public void updateStockInfo(BSO151402OccupyStockParam stockParam,final BSO151402Param param) {
        RsRequest<BSO151402OccupyStockParam> request = new RsRequest<>();
        request.setParam(stockParam);
        String url = SystemServerManager.SoInventoryServerManager.getAllocateOwnerInventory();
        TypeReference<RsResponse<BSO151402StockResult>> tTypeReference = new TypeReference<RsResponse<BSO151402StockResult>>() {
        };
        RestClientUtil.asyncPost(url, request, new AsyncPostCallBack<RsResponse<BSO151402StockResult>>() {

            @Override
            public void callBack(RsResponse<BSO151402StockResult> result) {
                if (result.getStatus().equals("F")) {
                    BSO151402OrderInfo orderInfo = new BSO151402OrderInfo();
                    BeanUtils.copyProperties(param, orderInfo);
                    orderInfo.setOrderStatus(OrderCodeMasterDef.OrderStatus.DISTRIBUTION_FAIL);
                    orderInfo.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatus.DISTRIBUTION_FAIL);
                    orderInfo.setSoOrderDetailStatus(OrderCodeMasterDef.OrderDetailStatus.WAIT_DISTRIBUTION);
                    orderInfo.setSubOrderDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.WAIT_DISTRIBUTION);
                    orderInfo.setOrderShipDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.DISTRIBUTION_FAIL);
                    bso151402Logic.saveOrderStatus(orderInfo);
                    bso151402Logic.saveSubOrderStatus(orderInfo);
                    bso151402Logic.modifyOrderDetailStatus(orderInfo);
                    bso151402Logic.modifySubOrderDetailStatus(orderInfo);
                    bso151402Logic.modifyOrderShipDetailStatus(orderInfo);
                }
            }
        }, tTypeReference);
    }
}
