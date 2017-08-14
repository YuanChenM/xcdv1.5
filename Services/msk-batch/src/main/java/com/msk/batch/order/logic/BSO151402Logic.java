package com.msk.batch.order.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.order.bean.*;
import com.msk.batch.order.commUtils.BatchCommRestUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.OrderCodeMasterDef;
import com.msk.common.consts.CommCodeMasterConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SoOrderShipDetail;
import com.msk.core.entity.SoSalesRanking;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/22.
 */
@Service
public class BSO151402Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSO151402Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private AsyncPostLogic asyncPostLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 得到可分销的订单数据
     *
     * @param param
     * @return
     */
    @Transactional
    public List<BSO151402OrderInfo> getOrdersByStatus(BSO151402Param param) {
        return super.findList(SqlId.SQL_ID_GET_ORDERS_BY_STATUS, param);
    }


    /**
     * 分单batch执行方法
     *
     * @param orderInfo
     */
    @Transactional
    public void distributionOrder(BSO151402OrderInfo orderInfo) {
        logger.debug("分单开始");
        BSO151402Param param = new BSO151402Param();
        orderInfo.setUpdTime(DateTimeUtil.getCustomerDate());
        BeanUtils.copyProperties(orderInfo, param);

        List<BSO151402OrderResult> orderResults = super.findList(SqlId.SQL_ID_GET_SUB_ORDER_DETAIL_INFO, param);

        if (CollectionUtils.isEmpty(orderResults)) {
            throw new BusinessException("订单" + orderInfo.getOrderId() + "没有对应的明细信息");
        }

        BSO151402StockResult stockResult = BatchCommRestUtils.getStockSpInfo(orderResults, param);

        if (null == stockResult || CollectionUtils.isEmpty(stockResult.getPdStockList())) {
            throw new BusinessException("订单" + orderInfo.getOrderId() + "对应的明细产品信息没有对应的库存信息");
        }

        List<BSO151402StockProductResultInfo> pdStockList = stockResult.getPdStockList();

        if (splitOrder(orderResults, pdStockList, param)) {
            //分销成功
            orderInfo.setOrderStatus(OrderCodeMasterDef.OrderStatus.CONFIRM);
            orderInfo.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatus.CONFIRM);
            orderInfo.setSoOrderDetailStatus(OrderCodeMasterDef.OrderDetailStatus.CONFIRM);
            orderInfo.setSubOrderDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.CONFIRM);
        } else {
            //分销失败
            orderInfo.setOrderStatus(OrderCodeMasterDef.OrderStatus.DISTRIBUTION_FAIL);
            orderInfo.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatus.DISTRIBUTION_FAIL);
            //断货处理
            dealProductStock(orderResults);
        }
        this.saveOrderStatus(orderInfo);
        this.saveSubOrderStatus(orderInfo);
        this.modifyOrderDetailStatus(orderInfo);
        this.modifySubOrderDetailStatus(orderInfo);
    }

    /**
     * 断货处理
     *
     * @param orderResults
     */
    public void dealProductStock(List<BSO151402OrderResult> orderResults) {
        BSO151402SellerProductParam productParam = new BSO151402SellerProductParam();
        List<BSO151402SellerProductInfo> productList = new ArrayList<>();

        for (BSO151402OrderResult orderResult : orderResults) {
            BSO151402SellerProductInfo product = new BSO151402SellerProductInfo();
            product.setSlCode(orderResult.getSellerCode());
            product.setPdCode(orderResult.getPdCode());
            productList.add(product);
        }
        productParam.setProducts(productList);

        BatchCommRestUtils.dealSellerProductHis(productParam);
    }

    /**
     * 更新订单状态表和订单状态
     *
     * @param orderInfo
     */
    public void saveOrderStatus(BSO151402OrderInfo orderInfo) {
        super.modify(SqlId.SQL_ID_MODIFY_SO_ORDER_STATUS, orderInfo);
        orderInfo.setStatusId(commonLogic.maxId("so_order_status", "STATUS_ID"));
        super.save(SqlId.SQL_ID_SAVE_SO_ORDER_STATUS, orderInfo);
        super.modify(SqlId.SQL_ID_MODIFY_SO_ORDER_INFO, orderInfo);
    }

    /**
     * 更新订单明细状态
     *
     * @param orderInfo
     */
    public void modifyOrderDetailStatus(BSO151402OrderInfo orderInfo) {
        if (null != orderInfo.getSoOrderDetailStatus() && orderInfo.getSoOrderDetailStatus() > NumberConst.IntDef.INT_ZERO) {
            super.modify(SqlId.SQL_ID_MODIFY_SO_ORDER_DETAIL_STATUS, orderInfo);
        }
    }

    /**
     * 更新拆分订单明细状态
     *
     * @param orderInfo
     */
    public void modifySubOrderDetailStatus(BSO151402OrderInfo orderInfo) {
        if (null != orderInfo.getSubOrderDetailStatus() && orderInfo.getSubOrderDetailStatus() > NumberConst.IntDef.INT_ZERO) {
            super.modify(SqlId.SQL_ID_MODIFY_SO_SUB_ORDER_DETAIL_STATUS, orderInfo);
        }
    }

    /**
     * 更新拆分状态表和拆分订单状态
     *
     * @param orderInfo
     */
    public void saveSubOrderStatus(BSO151402OrderInfo orderInfo) {
        super.modify(SqlId.SQL_ID_MODIFY_SO_SUB_ORDER_STATUS, orderInfo);
        orderInfo.setStatusId(commonLogic.maxId("so_sub_order_status", "STATUS_ID"));
        super.save(SqlId.SQL_ID_SAVE_SO_SUB_ORDER_STATUS, orderInfo);
        super.modify(SqlId.SQL_ID_MODIFY_SO_SUB_ORDER_STATUS_INFO, orderInfo);
    }

    /**
     * 分单
     * 1、先根据销售排行的数据判断是否满足一次性分单的数据
     * 2、如果不满足一次性分单的数据,进行拼货操作
     * 3、如果有分单数据,插入供货明细表 返回true
     * 4、如果没有分单数据 返回false
     *
     * @param orderResults
     * @param pdStockList
     * @param param
     * @return
     */
    @Transactional
    public boolean splitOrder(List<BSO151402OrderResult> orderResults, List<BSO151402StockProductResultInfo> pdStockList, BSO151402Param param) {

        List<SoOrderShipDetail> soOrderShipDetailList = new ArrayList<>();

        for (BSO151402OrderResult orderResult : orderResults) {

            if (OrderCodeMasterDef.SubOrderDetailStatus.CANCEL == orderResult.getSubOrderDetailStatus()) {
                //如果该明细为取消状态,则不进行分单
                continue;
            }

            // 取得同一物流区,同一订单等级,同一产品的排行
            List<SoSalesRanking> salesRankingList = this.getSalesRanking(orderResult);
            if (CollectionUtils.isEmpty(salesRankingList)) {
                return false;
            }
            boolean isSingle = false;
            //第一轮分单查看是否有在销售排行中能够一次性分单的
            SoOrderShipDetail soOrderShipDetail = checkFirstRankingAndGetSuppInfo(salesRankingList, orderResult, pdStockList);
            if (null == soOrderShipDetail) {
                isSingle = true;
            } else {
                soOrderShipDetailList.add(soOrderShipDetail);
            }

            //没有可以一次性配货的进行拼货
            if (isSingle) {
                List<SoOrderShipDetail> soOrderShipDetails = checkTwoRankingAndGetSuppInfos(orderResult, pdStockList);
                if (CollectionUtils.isEmpty(soOrderShipDetails)) {
                    return false;
                }
                soOrderShipDetailList.addAll(soOrderShipDetails);
            }
        }

        Long maxShipDetailId = commonLogic.maxIds("so_order_ship_detail", soOrderShipDetailList.size() + NumberConst.IntDef.INT_ONE);
        for (SoOrderShipDetail shipDetail : soOrderShipDetailList) {
            this.saveSoOrderShipDetail(shipDetail, maxShipDetailId);
            maxShipDetailId--;
        }
        //占用库存
        this.occupyStockInfo(soOrderShipDetailList, param);
        return true;
    }

    /**
     * 只占用供应商库存
     *
     * @param soOrderShipDetailList
     * @param param
     */
    public void occupyStockInfo(List<SoOrderShipDetail> soOrderShipDetailList, BSO151402Param param) {
        BSO151402OccupyStockParam stockParam = new BSO151402OccupyStockParam();
        List<BSO151402StockProductInfo> pdList = new ArrayList<>();

        stockParam.setPlantFormId(param.getSalePlatform());
        stockParam.setLgcsCode(param.getDistrictCode());
        stockParam.setOrderId(param.getOrderId());
        stockParam.setOrderCode(param.getOrderCode());
        stockParam.setOrderTime(param.getOrderTime());
        stockParam.setAllocateType(NumberConst.IntDef.INT_TWO);
        stockParam.setSlType(String.valueOf(NumberConst.IntDef.INT_ONE));
        stockParam.setSlCode(param.getSlCode());

        for (SoOrderShipDetail shipDetail : soOrderShipDetailList) {
            BSO151402StockProductInfo productInfo = new BSO151402StockProductInfo();
            BeanUtils.copyProperties(shipDetail, productInfo);
            productInfo.setInventoryStatus("31");
            productInfo.setOccupyQty(shipDetail.getSuppQty());
            pdList.add(productInfo);
        }
        stockParam.setPdList(pdList);

        asyncPostLogic.updateStockInfo(stockParam, param);
    }

    /**
     * 保存供货明细信息
     *
     * @param soOrderShipDetail
     * @param shipDetailId
     */
    @Transactional
    public void saveSoOrderShipDetail(SoOrderShipDetail soOrderShipDetail, Long shipDetailId) {
        soOrderShipDetail.setShipDetailId(shipDetailId);
        soOrderShipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.CONFIRM);
        soOrderShipDetail.setCrtTime(DateTimeUtil.getCustomerDate());
        super.save(SqlId.SQL_ID_SAVE_SO_SHIP_ORDER_DETAIL, soOrderShipDetail);
    }

    /**
     * 第一轮分单查看是否有在销售排行中能够一次性分单的
     *
     * @param salesRankingList
     * @param orderResult
     * @param pdStockList
     * @return
     */
    @Transactional
    public SoOrderShipDetail checkFirstRankingAndGetSuppInfo(List<SoSalesRanking> salesRankingList, BSO151402OrderResult orderResult, List<BSO151402StockProductResultInfo> pdStockList) {
        for (SoSalesRanking ranking : salesRankingList) {
            if (ranking.getOrderLevel() != orderResult.getOrderDetailLevel()) {
                continue;
            }
            SoOrderShipDetail soOrderShipDetail = checkStockAndGetSuppInfo(pdStockList, ranking, orderResult);
            if (null != soOrderShipDetail) {
                ranking.setWheelFrequency(ranking.getWheelFrequency() + NumberConst.IntDef.INT_ONE);
                ranking.setUpdTime(DateTimeUtil.getCustomerDate());
                ranking.setUpdId("BSO151402Batch");
                ranking.setVer(ranking.getVer() + NumberConst.IntDef.INT_ONE);
                super.modify(SqlId.SQL_ID_UPDATE_FREQUENCY, ranking);
                return soOrderShipDetail;
            }
        }

        return null;
    }

    /**
     * 没有可以一次性配货的进行拼货
     *
     * @param orderResult
     * @param pdStockList
     * @return
     */
    @Transactional
    public List<SoOrderShipDetail> checkTwoRankingAndGetSuppInfos(BSO151402OrderResult orderResult, List<BSO151402StockProductResultInfo> pdStockList) {
        List<SoOrderShipDetail> soOrderShipDetails = new ArrayList<>();
        BigDecimal orderQty = orderResult.getOrderQty();
        BigDecimal decimalZero = BigDecimal.ZERO;
        Integer intZero = NumberConst.IntDef.INT_ZERO;
        List<SoSalesRanking> saveRankingList = new ArrayList<>();
        List<SoSalesRanking> salesRankingList = this.getSalesRanking(orderResult);
        if (CollectionUtils.isEmpty(salesRankingList)) {
            logger.debug("查询销售排行为空");
            return null;
        }
        for (SoSalesRanking ranking : salesRankingList) {
            if (orderQty.compareTo(decimalZero) > intZero) {
                for (BSO151402StockProductResultInfo productResultInfo : pdStockList) {
                    if (productResultInfo.getSupplierCode().equals(ranking.getSupplierCode())
                            && productResultInfo.getPdCode().equals(ranking.getPdCode())
                            && orderQty.compareTo(BigDecimal.ZERO) > 0
                            && productResultInfo.getAvailableQty().compareTo(BigDecimal.ZERO) > 0) {
                        SoOrderShipDetail soOrderShipDetail = this.getSoOrderShipDetailInfo(productResultInfo, orderResult);
                        if (orderQty.compareTo(productResultInfo.getAvailableQty()) > intZero) {
                            soOrderShipDetail.setSuppQty(productResultInfo.getAvailableQty());
                            orderQty = DecimalUtil.subtract(orderQty, productResultInfo.getAvailableQty());
                            productResultInfo.setAvailableQty(BigDecimal.ZERO);
                        } else {
                            soOrderShipDetail.setSuppQty(orderQty);
                            productResultInfo.setAvailableQty(DecimalUtil.subtract(productResultInfo.getAvailableQty(), orderQty));
                            orderQty = decimalZero;
                        }
                        soOrderShipDetails.add(soOrderShipDetail);
                    }
                }
                saveRankingList.add(ranking);
            }
        }

        if (orderQty.compareTo(decimalZero) == intZero) {
            if (!CollectionUtils.isEmpty(saveRankingList)) {
                for (SoSalesRanking saveRanking : saveRankingList) {
                    if(saveRanking.getWheelFrequency() >= saveRanking.getDistQua()){
                        saveRanking.setWheelFrequency(NumberConst.IntDef.INT_ZERO);
                    }else {
                        saveRanking.setWheelFrequency(saveRanking.getWheelFrequency() + NumberConst.IntDef.INT_ONE);
                    }
                    saveRanking.setUpdTime(DateTimeUtil.getCustomerDate());
                    saveRanking.setUpdId("BSO151402Batch");
                    saveRanking.setVer(saveRanking.getVer() + NumberConst.IntDef.INT_ONE);
                    super.modify(SqlId.SQL_ID_UPDATE_FREQUENCY, saveRanking);
                }
            }
            return soOrderShipDetails;
        }

        return null;
    }

    /**
     * check单一供应商库存,若充足则返回数据,若不足则null
     *
     * @param pdStockList
     * @param ranking
     * @param orderResult
     * @return
     */
    @Transactional
    public SoOrderShipDetail checkStockAndGetSuppInfo(List<BSO151402StockProductResultInfo> pdStockList, SoSalesRanking ranking, BSO151402OrderResult orderResult) {
        logger.debug("check单一供应商库存,若充足则更新后返回,若不足则直接返回");

        //排行数据是否能够满足一次性分单
        for (BSO151402StockProductResultInfo stockProductInfo : pdStockList) {
            if (stockProductInfo.getSupplierCode().equals(ranking.getSupplierCode()) && stockProductInfo.getPdCode().equals(ranking.getPdCode())) {
                if (DecimalUtil.isGreater(stockProductInfo.getAvailableQty(), orderResult.getOrderQty())) {
                    SoOrderShipDetail soOrderShipDetail = this.getSoOrderShipDetailInfo(stockProductInfo, orderResult);
                    soOrderShipDetail.setSuppQty(orderResult.getOrderQty());
                    stockProductInfo.setAvailableQty(DecimalUtil.subtract(stockProductInfo.getAvailableQty(), orderResult.getOrderQty()));
                    return soOrderShipDetail;
                }
            }
        }
        return null;
    }

    /**
     * 遍历供货明细数据
     *
     * @param stockProductInfo
     * @param orderResult
     * @return
     */
    @Transactional
    public SoOrderShipDetail getSoOrderShipDetailInfo(BSO151402StockProductResultInfo stockProductInfo, BSO151402OrderResult orderResult) {
        SoOrderShipDetail soOrderShipDetail = new SoOrderShipDetail();
        soOrderShipDetail.setOrderId(orderResult.getOrderId());
        soOrderShipDetail.setSubOrderDetailId(orderResult.getSubOrderDetailId());
        soOrderShipDetail.setOrderDetailId(orderResult.getOrderDetailId());
        soOrderShipDetail.setSubOrderId(orderResult.getSubOrderId());
        soOrderShipDetail.setPdCode(orderResult.getPdCode());
        soOrderShipDetail.setPdName(orderResult.getPdName());
        soOrderShipDetail.setSupplierCode(stockProductInfo.getSupplierCode());
        soOrderShipDetail.setSupplierName(stockProductInfo.getSupplierName());
        soOrderShipDetail.setSourceSupplierCode(stockProductInfo.getSupplierCode());
        return soOrderShipDetail;
    }

    /**
     * 取得同一物流区,同一订单等级,同一产品的排行
     *
     * @param orderResult
     * @return
     */
    @Transactional
    public List<SoSalesRanking> getSalesRanking(BSO151402OrderResult orderResult) {

        BSO151402Param param = new BSO151402Param();
        param.setPdCode(orderResult.getPdCode());
        param.setOrderLevel(orderResult.getOrderDetailLevel());
        param.setDistrictCode(orderResult.getDistrictCode());
        param.setLgcsCode(orderResult.getDistrictCode());

        // 判断是否排行中所有供应商都已全部分销结束
        int count = super.getCount(SqlId.SQL_ID_COUNT_FREQUENCY, param);
        logger.debug("查询销售排行数量为：" + count);
        if (count == NumberConst.IntDef.INT_ZERO) {
            // 若全部分销完则初始化排行榜
            SoSalesRanking ranking = new SoSalesRanking();
            ranking.setLgcsCode(param.getDistrictCode());
            ranking.setOrderLevel(param.getOrderLevel());
            ranking.setPdCode(param.getPdCode());
            ranking.setWheelFrequency(NumberConst.IntDef.INT_ZERO);
            ranking.setUpdId("BSO151402Batch");
            ranking.setUpdTime(DateTimeUtil.getCustomerDate());
            super.modify(SqlId.SQL_ID_UPDATE_FREQUENCY, ranking);
        }

        // 获得当前排行
        return super.findList(param);
    }

    public void modifyOrderShipDetailStatus(BSO151402OrderInfo orderInfo) {
        this.modify(SqlId.SQL_ID_MODIFY_SO_ORDER_SHIP_DETAIL_STATUS, orderInfo);
    }

    interface SqlId {
        static final String SQL_ID_GET_ORDERS_BY_STATUS = "getOrdersByStatus";
        static final String SQL_ID_GET_SUB_ORDER_DETAIL_INFO = "getSubOrderDetailInfo";
        static final String SQL_ID_COUNT_FREQUENCY = "countFrequency";
        static final String SQL_ID_UPDATE_FREQUENCY = "updateFrequency";
        static final String SQL_ID_SAVE_SO_SHIP_ORDER_DETAIL = "saveSoShipOrderDetail";
        static final String SQL_ID_MODIFY_SO_ORDER_STATUS = "modifySoOrderStatus";
        static final String SQL_ID_MODIFY_SO_SUB_ORDER_STATUS = "modifySoSubOrderStatus";
        static final String SQL_ID_SAVE_SO_ORDER_STATUS = "saveSoOrderStatus";
        static final String SQL_ID_SAVE_SO_SUB_ORDER_STATUS = "saveSoSubOrderStatus";
        static final String SQL_ID_MODIFY_SO_ORDER_INFO = "modifySoOrderStatusInfo";
        static final String SQL_ID_MODIFY_SO_SUB_ORDER_STATUS_INFO = "modifySoSubOrderStatusInfo";
        static final String SQL_ID_MODIFY_SO_ORDER_DETAIL_STATUS = "modifySoOrderDetailStatus";
        static final String SQL_ID_MODIFY_SO_SUB_ORDER_DETAIL_STATUS = "modifySoSubOrderDetailStatus";
        static final String SQL_ID_MODIFY_SO_ORDER_SHIP_DETAIL_STATUS = "modifySoOrderShipDetailStatus";
    }


}
