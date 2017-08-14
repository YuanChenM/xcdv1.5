package com.msk.batch.order.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.order.bean.BSO151404Bean;
import com.msk.batch.order.bean.BSO151404InvParamBean;
import com.msk.batch.order.bean.BSO151404InvParamList;
import com.msk.batch.order.bean.BSO151404Param;
import com.msk.comm.exception.BusinessException;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.bean.result.BaseResult;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.DecimalUtil;
import com.msk.common.utils.RestClientUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang_shuai on 2016/8/30.
 */
@Service
public class BSO151404Logic extends BaseLogic{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSO151404Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    @Autowired
    private CommonLogic commonLogic;

    public List<BSO151404Bean> getStockOrderList(){
        BaseParam baseParam = new BaseParam();
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("orderType1", OrderCodeMasterDef.OrderType.BUYER_STOCKPILING_ORDER);
        filterMap.put("orderType2", OrderCodeMasterDef.OrderType.THIRD_BUYER_ORDER);
        filterMap.put("status", OrderCodeMasterDef.OrderStatus.CONFIRM);
        baseParam.setFilterMap(filterMap);
        List<BSO151404Bean> bso151404BeanList = super.findList(SqlId.SQL_ID_GET_STOCK_ORDER_LIST,baseParam);
        return bso151404BeanList;
    }

    /**
     * 库存调拨
     */
    public void stockTransfer(BSO151404Bean bso151404Bean) {
        logger.debug("获取需要调拨的买手订单");
        List<BSO151404Bean> bso151404BeanList = null;
        BaseParam baseParam = new BaseParam();
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("orderId", bso151404Bean.getOrderId());
        baseParam.setFilterMap(filterMap);
        //获取需要调拨的买手订单列表
        bso151404BeanList = super.findList(SqlId.SQL_ID_SELECT_ORDER_NEEDS_TRANSFER, baseParam);

        //更新供应商库存和订单状态
        saveTransfer(bso151404BeanList);

    }

    /**
     * 保存到供应商库存,并更新订单状态
     *
     * @param bso151404BeanList
     */
    @Transactional
    private void saveTransfer(List<BSO151404Bean> bso151404BeanList) {
        logger.debug("更新供应商库存!");
        BSO151404InvParamList bso151404InvParamList = getModifyStockParamList(bso151404BeanList);
        if (CollectionUtils.isNotEmpty(bso151404InvParamList.getInvList())){
            RestRequest<BSO151404InvParamList> request = new RestRequest<BSO151404InvParamList>();
            request.setAuth("MSK00001");
            request.setLoginId("BSO151404Batch");
            request.setSiteCode("1");
            request.setParam(bso151404InvParamList);
            String url = SystemServerManager.SoInventoryServerManager.getAssignInventoryForSl();
            RestResponse<BaseResult> result = RestClientUtils.post(url, request, new TypeReference<RestResponse<BaseResult>>() {
            });
            if (result.getStatus().equals(SystemConstant.RsStatus.FAIL)){
                throw new BusinessException("更新库存失败");
            }
            logger.debug("库存调拨处理成功!");

        }

        //去除重复数据
        List<BSO151404Param> bso151404ParamList = new ArrayList<BSO151404Param>();
        BSO151404Param bso151404Param = null;
        for(int i=0;i<bso151404BeanList.size();i++){
            bso151404Param = new BSO151404Param();
            bso151404Param.setOrderId(bso151404BeanList.get(i).getOrderId());
            bso151404Param.setOrderCode(bso151404BeanList.get(i).getOrderCode());
            bso151404Param.setUpdId("BSO151404Batch");
            if(!bso151404ParamList.contains(bso151404Param)){
                bso151404ParamList.add(bso151404Param);
            }
        }

        saveStatus(bso151404ParamList);
    }

    /**
     * 获取(设置)更新供应商库存所需要的参数列表
     * @param bso151404BeanList
     * @return
     */
    private BSO151404InvParamList getModifyStockParamList(List<BSO151404Bean> bso151404BeanList){
        BSO151404InvParamList bso151404InvParamList = new BSO151404InvParamList();
        List<BSO151404InvParamBean> list = new ArrayList<>();
        for (BSO151404Bean bso151404Bean : bso151404BeanList){
            BSO151404InvParamBean bso151404InvParamBean = new BSO151404InvParamBean();
            bso151404InvParamBean.setSalePlatform(bso151404Bean.getSalePlatform());
            bso151404InvParamBean.setLogicCode(bso151404Bean.getDistrictCode());
            bso151404InvParamBean.setWarehouseCode(String.valueOf(NumberConstant.IntDef.INT_ZERO) + String.valueOf(NumberConstant.IntDef.INT_TWENTY_ONE));
            bso151404InvParamBean.setInventoryStatus(String.valueOf(InventoryCodeMasterDef.goodType.GT_GOOD));
            bso151404InvParamBean.setOutSlCode(bso151404Bean.getSourceSellerCode());
            bso151404InvParamBean.setOutSlName(bso151404Bean.getSourceSellerName());
            bso151404InvParamBean.setOutSlType(String.valueOf(NumberConstant.IntDef.INT_ONE));
            bso151404InvParamBean.setInSlCode(bso151404Bean.getSellerCode());
            bso151404InvParamBean.setInSlName(bso151404Bean.getSellerName());
            bso151404InvParamBean.setInSlType(String.valueOf(NumberConstant.IntDef.INT_TWO));
            bso151404InvParamBean.setSupplierCode(bso151404Bean.getSupplierCode());
            bso151404InvParamBean.setPdCode(bso151404Bean.getPdCode());
            bso151404InvParamBean.setOutboundQty(bso151404Bean.getSuppQty());
            bso151404InvParamBean.setOutboundPrice(DecimalUtil.multiply(bso151404Bean.getSuppQty(), bso151404Bean.getPdPrice()));
            bso151404InvParamBean.setFlowId(bso151404Bean.getOrderId());
            bso151404InvParamBean.setFlowNo(bso151404Bean.getOrderCode());
            list.add(bso151404InvParamBean);
        }
        bso151404InvParamList.setInvList(list);
        return bso151404InvParamList;
    }

    /**
     * 更新订单状态为完成(订单主表,订单明细表,订单供货明细信息表)
     * @param bso151404ParamList
     * @return
     */
    @Transactional
    private int saveStatus(List<BSO151404Param> bso151404ParamList){
        int returnInt = 0;
        for(int i=0; i< bso151404ParamList.size();i++) {
            BSO151404Param param = bso151404ParamList.get(i);
            param.setFilter("status", String.valueOf(OrderCodeMasterDef.OrderDetailAvailabilityStatus.ALL_RECEIPT));
            returnInt = super.modify(SqlId.SQL_ID_MODIFY_DETAIL_SHIP_STATUS, param);
            if (returnInt > 0) {
                param.setFilter("status", String.valueOf(OrderCodeMasterDef.OrderDetailStatus.ALL_RECEIPT));
                returnInt = super.modify(SqlId.SQL_ID_MODIFY_DETAIL_STATUS, param);
            } else {
                return returnInt;
            }
            if (returnInt > 0) {
                param.setFilter("status", String.valueOf(OrderCodeMasterDef.OrderStatus.ALL_RECEIPT));
                returnInt = super.modify(SqlId.SQL_ID_MODIFY_STATUS, param);
                saveOrderStatus(param);
            } else {
                return returnInt;
            }
            if (returnInt > 0) {
                param.setFilter("status", String.valueOf(OrderCodeMasterDef.SubOrderDetailStatus.ALL_RECEIPT));
                returnInt = super.modify(SqlId.SQL_ID_MODIFY_SUB_DETAIL_STATUS, param);
            }else {
                return returnInt;
            }
            if (returnInt > 0) {
                param.setFilter("status", String.valueOf(OrderCodeMasterDef.SubOrderStatus.ALL_RECEIPT));
                returnInt = super.modify(SqlId.SQL_ID_MODIFY_SUB_STATUS, param);
                saveSubOrderStatus(param);
            }else {
                return returnInt;
            }
        }
        return returnInt;
    }

    /**
     * 更新订单状态履历
     * @param param
     */
    @Transactional
    private void saveOrderStatus(BSO151404Param param){
        int delQty = super.modify(SqlId.SQL_ID_UPDATE_ORDER_STATUS, param);
        if (delQty != 0){
            param.setFilter("status",String.valueOf(OrderCodeMasterDef.OrderStatus.ALL_RECEIPT));
            param.setFilter("statusId",String.valueOf(commonLogic.maxId("so_order_status", "STATUS_ID")));
            param.setUpdTime(DateTimeUtil.getCustomerDate());
            super.save(SqlId.SQL_ID_SAVE_ORDER_STATUS,param);
        }
    }

    /**
     * 更新分批订单状态履历
     * @param param
     */
    @Transactional
    private void saveSubOrderStatus(BSO151404Param param){
        param.setFilter("orderType1",String.valueOf(OrderCodeMasterDef.OrderType.BUYER_STOCKPILING_ORDER));
        param.setFilter("orderType2",String.valueOf(OrderCodeMasterDef.OrderType.THIRD_BUYER_ORDER));
        BSO151404Bean bean = super.findOne(SqlId.SQL_ID_FIND_SUB_ORDER,param);
        int delQty = super.modify(SqlId.SQL_ID_UPDATE_SUB_ORDER_STATUS,bean);
        if (delQty != 0){
            param.setFilter("status",String.valueOf(OrderCodeMasterDef.SubOrderStatus.ALL_RECEIPT));
            param.setFilter("statusId",String.valueOf(commonLogic.maxId("so_sub_order_status", "STATUS_ID")));
            param.setSubOrderId(bean.getSubOrderId());
            param.setUpdTime(DateTimeUtil.getCustomerDate());
            super.save(SqlId.SQL_ID_SAVE_SUB_ORDER_STATUS,param);
        }

    }

    /**
     * SQL_ID
     */
    public interface SqlId {
        // 获取订单供货明细信息表(订单状态:已确认,订单类型:买手囤货订单,第三方买手囤货订单)
        static final String SQL_ID_SELECT_ORDER_NEEDS_TRANSFER = "selectOrderNeedsTransfer";
        //更新订单供货明细信息表状态
        static final String SQL_ID_MODIFY_DETAIL_SHIP_STATUS = "modifyDetailShipStatus";
        //更新订单明细表状态
        static final String SQL_ID_MODIFY_DETAIL_STATUS = "modifyDetailStatus";
        //更新订单主表状态
        static final String SQL_ID_MODIFY_STATUS = "modifyStatus";
        //更新订单明细表状态
        static final String SQL_ID_MODIFY_SUB_DETAIL_STATUS = "modifySubDetailStatus";
        //更新订单主表状态
        static final String SQL_ID_MODIFY_SUB_STATUS = "modifySubStatus";
        //删除已经存在的订单状态履历
        static final String SQL_ID_UPDATE_ORDER_STATUS  = "updateOrderStatus";
        //插入订单状态履历表
        static final String SQL_ID_SAVE_ORDER_STATUS = "saveOrderStatus";
        //查询分批订单id
        static final String SQL_ID_FIND_SUB_ORDER = "findSubOrder";
        //删除已经存在的分批订单状态履历
        static final String SQL_ID_UPDATE_SUB_ORDER_STATUS = "updateSubOrderStatus";
        //插入分批订单状态履历表
        static final String SQL_ID_SAVE_SUB_ORDER_STATUS = "saveSubOrderStatus";
        //得到满足跑囤货batch的订单数据
        static final String SQL_ID_GET_STOCK_ORDER_LIST = "getStockOrderList";
    }

}
