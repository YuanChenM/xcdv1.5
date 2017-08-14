package com.msk.order.service.impl;

import com.msk.common.constant.CommCodeMasterConst;
import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.StringConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.CapitalPoolConst;
import com.msk.common.constant.business.CommOrderConst;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.exception.BusinessException;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.DecimalUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.*;
import com.msk.order.bean.result.*;
import com.msk.order.entity.*;
import com.msk.order.repository.*;
import com.msk.order.service.*;
import com.msk.order.util.SoRestUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by liu_tao2 on 2016/8/11.
 */
@Service
public class ISO151414ServiceImpl extends BaseService<SoOrder, Long> implements ISO151414Service {

    @Autowired
    private AsyncPostService asyncPostService;

    @Override
    public BaseRepository<SoOrder, Long> getRepository() {
        return orderRepository;
    }

    @Autowired
    private SoOrderBuyerSeqRepository soOrderBuyerSeqRepository;

    @Autowired
    private SoOrderRepository orderRepository;

    @Autowired
    private SoOrderDetailRepository soOrderDetailRepository;

    @Autowired
    private SoOrderReceiveDemandRepository soOrderReceiveDemandRepository;

    @Autowired
    private SoOrderShipDetailRepository soOrderShipDetailRepository;

    @Autowired
    private SoSubOrderRepository soSubOrderRepository;

    @Autowired
    private SoSubOrderDetailRepository soSubOrderDetailRepository;

    @Autowired
    private SoSubOrderStatusRepository soSubOrderStatusRepository;

    @Autowired
    private SoOrderStatusRepository soOrderStatusRepository;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private SubOrderStatusService subOrderStatusService;

    @Override
    @Transactional
    public ISO151414OrderResult createDistributionOrder(ISO151414BaseOrderParam param) {
        SoOrder soOrder = this.getSoOrderInfoAndSaveBaseInfo(param);
        //判断这个买家是否有专属买手
        if (param.isHaveBuyerFlag()) {
            Map<String, Object> orderMap = getBuyerSaleInfo(param);
            createBuyerSalesOrder(param, orderMap, soOrder);
        } else {
            checkPlatFormStock(param);
            createOrder(param, soOrder);
        }

        saveCpTransaction(soOrder);
        return getOrderResult(soOrder);
    }

    /**
     * 检查平台或者卖家库存是否充足
     *
     * @param param
     */
    public void checkPlatFormStock(ISO151414BaseOrderParam param) {
        param.setHaveBuyerFlag(false);
        List<ISO151414OrderDetailParam> products = param.getProducts();
        if (CollectionUtils.isEmpty(products)) {
            throw new BusinessException("订单产品信息不能为空！！！");
        }

        //查询卖家产品库存列表
        ISO151414StockRestResult stockSlRestResult = SoRestUtil.getStockSlInfo(param);

        if (!checkStockQty(stockSlRestResult, products)) {
            throw new BusinessException("对应的卖家或者平台库存不足！！！");
        }
    }

    @Override
    @Transactional
    public ISO151414OrderResult createBuyerOrder(ISO151414BaseOrderParam param) {
        SoOrder soOrder = this.getSoOrderInfoAndSaveBaseInfo(param);
        checkPlatFormStock(param);
        createOrder(param, soOrder);
        saveCpTransaction(soOrder);
        return getOrderResult(soOrder);
    }

    @Override
    @Transactional
    public ISO151414OrderResult createThirdPartyOrder(ISO151414BaseOrderParam param) {
        SoOrder soOrder = this.getSoOrderInfoAndSaveBaseInfo(param);
        //判断这个买家是否有专属买手
        if (param.isHaveBuyerFlag()) {
            Map<String, Object> orderMap = getThirdBuyerSaleInfo(param);
            createThirdBuyerSalesOrder(param, soOrder, orderMap);
        } else {
            dealOrderShipDetailInfo(param);
            //断货处理
            dealProductStock(param);
            checkPlatFormStock(param);
            createOrder(param, soOrder);
        }
        saveCpTransaction(soOrder);
        return getOrderResult(soOrder);
    }

    @Override
    @Transactional
    public ISO151414OrderResult createThirdBuyerOrder(ISO151414BaseOrderParam param) {
        SoOrder soOrder = this.getSoOrderInfoAndSaveBaseInfo(param);
        dealOrderShipDetailInfo(param);
        checkPlatFormStock(param);
        this.createOrder(param, soOrder);
        saveCpTransaction(soOrder);
        return getOrderResult(soOrder);
    }

    /**
     * 处理第三方和第三方囤货供货明细数据
     *
     * @param param
     */
    @Transactional
    public void dealOrderShipDetailInfo(ISO151414BaseOrderParam param) {
        List<ISO151414OrderDetailParam> products = param.getProducts();
        if (CollectionUtils.isEmpty(products)) {
            throw new BusinessException("订单产品信息不能为空！！！");
        }

        for (ISO151414OrderDetailParam orderDetailParam : products) {
            List<SoOrderShipDetail> orderShipDetails = new ArrayList<>();
            SoOrderShipDetail soOrderShipDetail = new SoOrderShipDetail();
            soOrderShipDetail.setSourceSupplierCode(orderDetailParam.getSupplierCode());
            soOrderShipDetail.setSupplierCode(param.getSellerCode());
            soOrderShipDetail.setSupplierName(param.getSellerName());
            soOrderShipDetail.setPdCode(orderDetailParam.getPdCode());
            soOrderShipDetail.setPdName(orderDetailParam.getPdName());
            soOrderShipDetail.setSuppQty(orderDetailParam.getOrderQty());
            orderShipDetails.add(soOrderShipDetail);
            orderDetailParam.setOrderShipDetails(orderShipDetails);
        }
    }

    /**
     * 得到订单主表信息，
     * 插入相应的基础表（订单主表，订单主表状态表，收货要求表）
     * 遍历订单创建的基本信息
     *
     * @param param
     * @return
     */
    @Transactional
    public SoOrder getSoOrderInfoAndSaveBaseInfo(ISO151414BaseOrderParam param) {
        //根据付款类型处理标准订单的订单状态
        dealOrderStatusByPaymentType(param);
        //处理要插入表中的订单基本信息
        dealBaseOrderParam(param);
        //创建订单主表
        SoOrder soOrder = saveOrder(param);

        //创建订单状态表
        this.saveOrderStatus(soOrder);
        //创建订单收货要求表
        this.createOrderReceiveDemand(param, soOrder);

        return soOrder;
    }

    @Override
    @Transactional
    public void dealOrderSourceAndSalePlatform(String siteCode, ISO151414BaseOrderParam param) {
        if (CommCodeMasterConst.SystemCode.SYSTEM_CODE_SNK.equals(siteCode)) {
            param.setOrderSource(OrderCodeMasterDef.OrderSource.SNK);
            param.setSalePlatform(OrderCodeMasterDef.SalePlatform.YDP);
        } else if (CommCodeMasterConst.SystemCode.SYSTEM_CODE_MSK.equals(siteCode)) {
            param.setOrderSource(OrderCodeMasterDef.OrderSource.MSK);
            param.setSalePlatform(OrderCodeMasterDef.SalePlatform.YDPB2B);
        } else if (CommCodeMasterConst.SystemCode.SYSTEM_CODE_WSC.equals(siteCode)) {
            param.setOrderSource(OrderCodeMasterDef.OrderSource.WSC);
            param.setSalePlatform(OrderCodeMasterDef.SalePlatform.YDP);
            dealBuyerInfoByTel(param);
        } else if (CommCodeMasterConst.SystemCode.SYSTEM_CODE_BYAPP.equals(siteCode)) {
            param.setOrderSource(OrderCodeMasterDef.OrderSource.BYAPP);
            param.setSalePlatform(OrderCodeMasterDef.SalePlatform.YDP);
        } else if (CommCodeMasterConst.SystemCode.SYSTEM_CODE_ORDER.equals(siteCode)) {
            if(StringUtil.isEmpty(param.getSalePlatform())){
                throw new BusinessException("从后台下单订单来源不能为空");
            }
            if(null == param.getOrderSource() || param.getOrderSource() <= NumberConstant.IntDef.INT_ZERO){
                throw new BusinessException("从后台下单订单销售平台不能为空或者输入不正确");
            }
        } else {
            throw new BusinessException("订单来源不正确");
        }
    }

    @Override
    @Transactional
    public void getBuyerInfoByBuyerId(ISO151414BaseOrderParam param) {
        ISO151414ByBuyerInfoParam buyerInfoParam = new ISO151414ByBuyerInfoParam();
        if (StringUtil.isEmpty(param.getBuyersId())) {
            throw new BusinessException("订单下单买家ID不能为空");
        }

        if (null == param.getReceiverInfo()
                || StringUtil.isEmpty(param.getReceiverInfo().getReceiverAddr())
                || StringUtil.isEmpty(param.getReceiverInfo().getReceiverDistrict())
                || StringUtil.isEmpty(param.getReceiverInfo().getReceiverProvince())
                || StringUtil.isEmpty(param.getReceiverInfo().getReceiverCity())) {
            throw new BusinessException("收货人部分信息不能为空");
        }

        buyerInfoParam.setBuyerId(param.getBuyersId());
        buyerInfoParam.setDeliveryAddr(param.getReceiverInfo().getReceiverAddr());
        buyerInfoParam.setProvinceName(param.getReceiverInfo().getReceiverProvince());
        buyerInfoParam.setDistrictName(param.getReceiverInfo().getReceiverDistrict());
        buyerInfoParam.setCityName(param.getReceiverInfo().getReceiverCity());

        String buyerCode = SoRestUtil.getBuyerCodeByBuyerId(buyerInfoParam);

        if (StringUtil.isEmpty(buyerCode)) {
            throw new BusinessException("没有该买家，请检查");
        }

        param.setBuyersCode(buyerCode);
    }

    /**
     * 根据收货人手机号查询买家信息
     *
     * @param param
     */
    @Transactional
    public void dealBuyerInfoByTel(ISO151414BaseOrderParam param) {
        if (null == param.getReceiverInfo()) {
            throw new BusinessException("订单收货人要求不能为空，请检查！！！");
        }

        if (!StringUtil.isEmpty(param.getReceiverInfo().getReceiverTel())) {
            ISO151414ByBuyerInfoResult buyerBasicInfo = SoRestUtil.getBuyerInfo(param.getReceiverInfo().getReceiverTel());
            if (null != buyerBasicInfo) {
                if (StringUtil.isEmpty(buyerBasicInfo.getBuyerId()) && StringUtil.isEmpty(buyerBasicInfo.getBuyerCode()) && StringUtil.isEmpty(buyerBasicInfo.getBuyerName()) && StringUtil.isEmpty(buyerBasicInfo.getSuperiorType())) {
                    throw new BusinessException("请核对买家基本信息!");
                }
                param.setBuyersId(buyerBasicInfo.getBuyerId());
                param.setBuyersCode(buyerBasicInfo.getBuyerCode());
                param.setBuyersName(buyerBasicInfo.getBuyerName());
                param.setBuyersType(Integer.valueOf(buyerBasicInfo.getSuperiorType()));
            } else {
                throw new BusinessException("没有该手机号的买家基本信息");
            }
        } else {
            throw new BusinessException("请输入收货人手机号码");
        }
    }

    /**
     * 通过卖家或者买手判断创建分销和买手销售的数据
     *
     * @param param
     * @return
     */
    @Transactional
    public Map<String, Object> getBuyerSaleInfo(ISO151414BaseOrderParam param) {
        List<ISO151414OrderDetailParam> products = param.getProducts();
        if (CollectionUtils.isEmpty(products)) {
            throw new BusinessException("订单产品信息不能为空！！！");
        }

        //查询卖家产品库存列表
        ISO151414StockRestResult stockSlRestResult = SoRestUtil.getStockSlInfo(param);

        if (!checkStockQty(stockSlRestResult, products)) {
            return null;
        }

        ISO151414StockRestResult stockSpRestResult = SoRestUtil.getStockSpInfo(param);

        if (null == stockSpRestResult || CollectionUtils.isEmpty(stockSpRestResult.getPdStockList())) {
            return null;
        }

        Map<String, Object> orderMap = this.getSaleParam(param, stockSpRestResult);

        return orderMap;
    }

    /**
     * 根据买手或者卖家库存的到需要走分销和买手销售订单的数据
     *
     * @param param
     * @param stockSpRestResult
     * @return
     */
    @Transactional
    public Map<String, Object> getSaleParam(ISO151414BaseOrderParam param, ISO151414StockRestResult stockSpRestResult) {
        List<ISO151414OrderDetailParam> saleProduct = new ArrayList<>();
        List<ISO151414OrderDetailParam> standardProduct = new ArrayList<>();
        List<ISO151414OrderDetailParam> products = param.getProducts();
        List<ISO151414ProductStockInfo> pdStockList = stockSpRestResult.getPdStockList();
        Integer zero = NumberConstant.IntDef.INT_ZERO;

        for (ISO151414OrderDetailParam product : products) {
            BigDecimal orderQty = product.getOrderQty();
            List<SoOrderShipDetail> orderShipDetails = new ArrayList<>();

            for (ISO151414ProductStockInfo productStockInfo : pdStockList) {
                if (product.getPdCode().equals(productStockInfo.getPdCode()) && orderQty.compareTo(new BigDecimal(zero)) > zero) {
                    SoOrderShipDetail soOrderShipDetail = new SoOrderShipDetail();
                    BeanUtils.copyProperties(productStockInfo, soOrderShipDetail);
                    soOrderShipDetail.setSourceSupplierCode(productStockInfo.getSupplierCode());
                    if (orderQty.compareTo(productStockInfo.getAvailableQty()) >= zero) {
                        soOrderShipDetail.setSuppQty(productStockInfo.getAvailableQty());
                        orderQty = DecimalUtil.subtract(orderQty, productStockInfo.getAvailableQty());
                        productStockInfo.setAvailableQty(BigDecimal.ZERO);
                    } else {
                        soOrderShipDetail.setSuppQty(orderQty);
                        productStockInfo.setAvailableQty(DecimalUtil.subtract(productStockInfo.getAvailableQty(), orderQty));
                        orderQty = DecimalUtil.subtract(orderQty, orderQty);
                    }
                    orderShipDetails.add(soOrderShipDetail);
                }
            }

            if (orderQty.compareTo(new BigDecimal(zero)) == zero) {
                product.setOrderShipDetails(orderShipDetails);
                saleProduct.add(product);
            } else {
                standardProduct.add(product);
            }
        }

        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("sale", saleProduct);
        orderMap.put("standard", standardProduct);
        return orderMap;
    }

    /**
     * 通过卖家或者买手判断创建第三方和第三方买手销售的数据
     *
     * @param param
     * @return
     */
    @Transactional
    public Map<String, Object> getThirdBuyerSaleInfo(ISO151414BaseOrderParam param) {
        List<ISO151414OrderDetailParam> products = param.getProducts();
        if (CollectionUtils.isEmpty(products)) {
            throw new BusinessException("订单产品信息不能为空！！！");
        }

        //查询卖家产品库存列表
        ISO151414StockRestResult stockSlRestResult = SoRestUtil.getStockSlInfo(param);

        if (!checkStockQty(stockSlRestResult, products)) {
            return null;
        }

        ISO151414StockRestResult stockSpRestResult = SoRestUtil.getStockSpInfo(param);

        if (null == stockSpRestResult || CollectionUtils.isEmpty(stockSpRestResult.getPdStockList())) {
            return null;
        }

        return this.getThirdSaleParam(param, stockSpRestResult);
    }

    /**
     * 根据买手或者卖家库存的到需要走第三方和第三方买手销售订单的数据
     *
     * @param param
     * @param stockSpRestResult
     * @return
     */
    @Transactional
    public Map<String, Object> getThirdSaleParam(ISO151414BaseOrderParam param, ISO151414StockRestResult stockSpRestResult) {
        List<ISO151414OrderDetailParam> thirdSaleProduct = new ArrayList<>();
        List<ISO151414OrderDetailParam> thirdStandardProduct = new ArrayList<>();
        List<ISO151414OrderDetailParam> products = param.getProducts();
        List<ISO151414ProductStockInfo> pdStockList = stockSpRestResult.getPdStockList();
        Integer zero = NumberConstant.IntDef.INT_ZERO;

        for (ISO151414OrderDetailParam product : products) {
            BigDecimal orderQty = product.getOrderQty();
            List<SoOrderShipDetail> orderShipDetails = new ArrayList<>();
            List<SoOrderShipDetail> thirdStandardShipDetails = new ArrayList<>();
            for (ISO151414ProductStockInfo productStockInfo : pdStockList) {
                if (product.getPdCode().equals(productStockInfo.getPdCode())) {
                    SoOrderShipDetail soOrderShipDetail = new SoOrderShipDetail();
                    BeanUtils.copyProperties(productStockInfo, soOrderShipDetail);
                    soOrderShipDetail.setSourceSupplierCode(productStockInfo.getSupplierCode());
                    if (orderQty.compareTo(productStockInfo.getAvailableQty()) >= zero && orderQty.compareTo(BigDecimal.ZERO) > zero) {
                        soOrderShipDetail.setSuppQty(productStockInfo.getAvailableQty());
                        orderQty = DecimalUtil.subtract(orderQty, productStockInfo.getAvailableQty());
                    } else {
                        soOrderShipDetail.setSuppQty(orderQty);
                        orderQty = DecimalUtil.subtract(orderQty, orderQty);
                    }

                    if (orderQty.compareTo(BigDecimal.ZERO) >= zero) {
                        orderShipDetails.add(soOrderShipDetail);
                    }
                }
            }

            if (orderQty.compareTo(new BigDecimal(zero)) == zero) {
                product.setOrderShipDetails(orderShipDetails);
                thirdSaleProduct.add(product);
            } else {
                SoOrderShipDetail soOrderShipDetail = new SoOrderShipDetail();
                BeanUtils.copyProperties(product, soOrderShipDetail);
                soOrderShipDetail.setSourceSupplierCode(product.getSupplierCode());
                soOrderShipDetail.setSuppQty(product.getOrderQty());
                thirdStandardShipDetails.add(soOrderShipDetail);
                product.setOrderShipDetails(thirdStandardShipDetails);
                thirdStandardProduct.add(product);
            }
        }

        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("thirdSale", thirdSaleProduct);
        orderMap.put("thirdStandard", thirdStandardProduct);
        return orderMap;
    }

    /**
     * 检查卖家可用库存是否充足
     *
     * @param stockSlRestResult
     * @param products
     * @return
     */
    @Transactional
    public boolean checkStockQty(ISO151414StockRestResult stockSlRestResult, List<ISO151414OrderDetailParam> products) {
        if (null == stockSlRestResult || CollectionUtils.isEmpty(stockSlRestResult.getPdStockList())) {
            return false;
        }

        List<ISO151414ProductStockInfo> stockList = stockSlRestResult.getPdStockList();

        boolean stockFlag = false;

        for (ISO151414OrderDetailParam detailParam : products) {
            BigDecimal orderQty = detailParam.getOrderQty();
            for (ISO151414ProductStockInfo stockInfo : stockList) {
                if (detailParam.getPdCode().equals(stockInfo.getPdCode())) {
                    orderQty = DecimalUtil.subtract(orderQty, stockInfo.getAvailableQty());
                }
            }

            if (orderQty.compareTo(BigDecimal.ZERO) <= NumberConstant.IntDef.INT_ZERO) {
                stockFlag = true;
            }
        }
        return stockFlag;
    }

    /**
     * 创建买手销售订单
     *
     * @param param
     * @return
     */
    @Transactional
    public void createBuyerSalesOrder(ISO151414BaseOrderParam param, Map<String, Object> orderMap, SoOrder soOrder) {

        if (null == orderMap) {
            checkPlatFormStock(param);
            createOrder(param, soOrder);
            return;
        }

        List<ISO151414OrderDetailParam> saleProductList = (List) orderMap.get("sale");
        List<ISO151414OrderDetailParam> standardProductList = (List) orderMap.get("standard");
        if (CollectionUtils.isEmpty(saleProductList)) {
            checkPlatFormStock(param);
            createOrder(param, soOrder);
            return;
        }

        if (!CollectionUtils.isEmpty(standardProductList)) {
            ISO151414BaseOrderParam standardParam = new ISO151414BaseOrderParam();
            BeanUtils.copyProperties(param, standardParam);
            standardParam.setProducts(standardProductList);
            checkPlatFormStock(standardParam);
            createOrder(standardParam, soOrder);
        }
        ISO151414BaseOrderParam saleParam = new ISO151414BaseOrderParam();
        param.setHaveBuyerFlag(true);
        BeanUtils.copyProperties(param, saleParam);
        saleParam.setOrderType(OrderCodeMasterDef.OrderType.BUYER_SALE_ORDER);
        saleParam.setProducts(saleProductList);
        saleParam.setSellerCode(param.getBuyerSaleCode());
        saleParam.setSellerName(param.getBuyerSaleName());
        createOrder(saleParam, soOrder);
    }

    /**
     * 创建第三方买手销售订单
     *
     * @param param
     * @return
     */
    @Transactional
    public void createThirdBuyerSalesOrder(ISO151414BaseOrderParam param, SoOrder soOrder, Map<String, Object> orderMap) {

        if (null == orderMap) {
            dealOrderShipDetailInfo(param);
            //断货处理
            dealProductStock(param);
            checkPlatFormStock(param);
            createOrder(param, soOrder);
            return;
        }

        List<ISO151414OrderDetailParam> thirdSaleProductList = (List) orderMap.get("thirdSale");
        List<ISO151414OrderDetailParam> thirdStandardProductList = (List) orderMap.get("thirdStandard");

        if (CollectionUtils.isEmpty(thirdSaleProductList)) {
            dealOrderShipDetailInfo(param);
            //断货处理
            dealProductStock(param);
            checkPlatFormStock(param);
            createOrder(param, soOrder);
            return;
        }
        if (!CollectionUtils.isEmpty(thirdStandardProductList)) {
            ISO151414BaseOrderParam standardParam = new ISO151414BaseOrderParam();
            BeanUtils.copyProperties(param, standardParam);
            standardParam.setProducts(thirdStandardProductList);
            dealOrderShipDetailInfo(standardParam);
            //断货处理
            dealProductStock(standardParam);
            checkPlatFormStock(param);
            createOrder(standardParam, soOrder);
        }

        ISO151414BaseOrderParam thirdSaleParam = new ISO151414BaseOrderParam();
        param.setHaveBuyerFlag(true);
        BeanUtils.copyProperties(param, thirdSaleParam);
        thirdSaleParam.setOrderType(OrderCodeMasterDef.OrderType.THIRD_BUYER_SALE_ORDER);
        thirdSaleParam.setProducts(thirdSaleProductList);
        thirdSaleParam.setSellerCode(param.getBuyerSaleCode());
        thirdSaleParam.setSellerName(param.getBuyerSaleName());
        createOrder(thirdSaleParam, soOrder);
    }

    /**
     * 创建订单数据
     *
     * @param param
     * @return
     */
    @Transactional
    public void createOrder(ISO151414BaseOrderParam param, SoOrder soOrder) {

        List<ISO151414OrderDetailParam> products = param.getProducts();

        if (CollectionUtils.isEmpty(products)) {
            throw new BusinessException("订单产品信息不能为空！！！");
        }
        //创建分批订单主表
        SoSubOrder soSubOrder = this.saveSubOrder(soOrder, param);

        //处理冻品管家信息
        dealBuyerInfo(param,soSubOrder);

        //创建分批订单状态表
        this.saveSubOrderStatus(soSubOrder);

        this.saveDetailInfos(param, products, soSubOrder);

        //调用平台占用库存
        this.occupyStockInfo(products, soSubOrder, soOrder);
    }

    /**
     * 保存订单明细
     * 保存分批订单明细
     * 保存供货明细
     *
     * @param param
     * @param products
     * @param soSubOrder
     */
    @Transactional
    public void saveDetailInfos(ISO151414BaseOrderParam param, List<ISO151414OrderDetailParam> products, SoSubOrder soSubOrder) {
        int detailIndexNumber = products.size() + NumberConstant.IntDef.INT_ONE;
        int suppIndexNumber = getOrderShipDetailIndex(products);
        Long maxOrderDetailId = this.maxId("so_order_detail", detailIndexNumber);
        Long maxSubOrderDetailId = this.maxId("so_sub_order_detail", detailIndexNumber);
        Long maxOrderShipDetailId = this.maxId("so_order_ship_detail", suppIndexNumber);

        for (ISO151414OrderDetailParam orderDetailParam : products) {
            //创建订单明细
            SoOrderDetail soOrderDetail = this.saveOrderDetail(orderDetailParam, soSubOrder, maxOrderDetailId);
            //创建分批订单明细
            SoSubOrderDetail soSubOrderDetail = this.saveSubOrderDetail(soOrderDetail, soSubOrder, maxSubOrderDetailId);
            if (!checkOrderType(param)) {
                //插入供货明细表
                if (!CollectionUtils.isEmpty(orderDetailParam.getOrderShipDetails())) {
                    for (SoOrderShipDetail soOrderShipDetail : orderDetailParam.getOrderShipDetails()) {
                        saveOrderShipDetail(soSubOrderDetail, soOrderShipDetail, maxOrderShipDetailId);
                        maxOrderShipDetailId--;
                    }
                }
            }
            maxOrderDetailId--;
            maxSubOrderDetailId--;
        }
    }

    /**
     * 占用库存处理
     * @param products
     * @param soSubOrder
     * @param soOrder
     */
    @Transactional
    public void occupyStockInfo(List<ISO151414OrderDetailParam> products, SoSubOrder soSubOrder, SoOrder soOrder) {
        List<ISO151414StockProductInfo> pdList = new ArrayList<>();

        boolean isHaveSuppFlag = false;

        //占用卖家库存
        ISO151414OccupyStockParam stockParam = new ISO151414OccupyStockParam();

        if (soOrder.getSalePlatform().equals(OrderCodeMasterDef.SalePlatform.YDP)) {
            stockParam.setPlantFormId(CommCodeMasterConst.SystemCode.SYSTEM_CODE_SNK);
        } else {
            stockParam.setPlantFormId(CommCodeMasterConst.SystemCode.SYSTEM_CODE_MSK);
        }
        stockParam.setLgcsCode(soOrder.getDistrictCode());
        stockParam.setOrderId(soOrder.getOrderId());
        stockParam.setOrderCode(soOrder.getOrderCode());
        stockParam.setOrderTime(soOrder.getOrderTime());

        //卖家类型-CodeMaster 1平台 2买手
        if (soSubOrder.getOrderType().equals(OrderCodeMasterDef.OrderType.BUYER_SALE_ORDER)
                || soSubOrder.getOrderType().equals(OrderCodeMasterDef.OrderType.THIRD_BUYER_SALE_ORDER)) {
            stockParam.setSlType(OrderCodeMasterDef.SellerType.SALE);
        } else {
            stockParam.setSlType(OrderCodeMasterDef.SellerType.PLATFORM);
        }
        stockParam.setSlCode(soSubOrder.getSellerCode());


        for (ISO151414OrderDetailParam detailParam : products) {
            if (!CollectionUtils.isEmpty(detailParam.getOrderShipDetails())) {
                stockParam.setAllocateType(NumberConstant.IntDef.INT_ONE);
                isHaveSuppFlag = true;
                for (SoOrderShipDetail soOrderShipDetail : detailParam.getOrderShipDetails()) {
                    ISO151414StockProductInfo productInfo = new ISO151414StockProductInfo();
                    productInfo.setPdCode(detailParam.getPdCode());
                    productInfo.setSupplierCode(soOrderShipDetail.getSupplierCode());
                    productInfo.setSkuCode(soOrderShipDetail.getSkuCode());
                    productInfo.setInventoryStatus(InventoryCodeMasterDef.GoodType.GT_GOOD);
                    productInfo.setOccupyQty(soOrderShipDetail.getSuppQty());
                    pdList.add(productInfo);
                }
            } else {
                stockParam.setAllocateType(NumberConstant.IntDef.INT_TWO);
                ISO151414StockProductInfo productInfo = new ISO151414StockProductInfo();
                productInfo.setPdCode(detailParam.getPdCode());
                productInfo.setInventoryStatus(InventoryCodeMasterDef.GoodType.GT_GOOD);
                productInfo.setOccupyQty(detailParam.getOrderQty());
                pdList.add(productInfo);
            }
        }
        stockParam.setPdList(pdList);
        //有供货明细数据 先占用供应商库存 没有就占用卖家库存
        if (isHaveSuppFlag) {
            asyncPostService.occupyStockSpInfo(stockParam, soOrder);
        } else {
            this.occupyStockSlInfo(stockParam, soOrder);
        }
    }

    /**
     * 占用供应商库存
     *
     * @param stockParam
     */
    @Override
    @Transactional
    public void occupyStockSlInfo(ISO151414OccupyStockParam stockParam, SoOrder soOrder) {
        asyncPostService.occupyStockSlInfo(stockParam, soOrder);
    }

    /**
     * 库存占用后更新订单主表和拆分主表状态
     *
     * @param soOrder
     */
    @Override
    @Transactional
    public void modifyOrderStatusByOrderId(SoOrder soOrder) {
        Filter<SoOrder> orderFilter = new Filter<>();
        orderFilter.add("orderId", BaseOperatorEnum.EQUAL, soOrder.getOrderId());
        orderFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification spec = new CommonSpecification(orderFilter);
        SoOrder newSoOrder = this.orderRepository.findOne(spec);
        newSoOrder.setVer(soOrder.getVer() + NumberConstant.IntDef.INT_ONE);
        orderRepository.save(newSoOrder);
        this.orderStatusService.saveOrderStatusBySoOrderId(soOrder.getOrderId(), soOrder.getOrderStatus(), soOrder.getCrtId());
        subOrderStatusService.saveSubOrderStatusListByOrderId(soOrder.getOrderId(), soOrder.getOrderStatus(), soOrder.getCrtId());
    }

    /**
     * @param soOrder
     */
    @Override
    @Transactional
    public void cancelCpTransaction(SoOrder soOrder) {
        ISO151414CpTransactionParam cancelParam = new ISO151414CpTransactionParam();
        cancelParam.setInsertFlg(NumberConstant.IntDef.INT_ZERO);
        cancelParam.setTransCode(soOrder.getOrderCode());
        cancelParam.setOrderId(soOrder.getOrderId());
        cancelParam.setTransType(Integer.valueOf(CommOrderConst.SearchType.ORDER));
        cancelParam.setOperateDate(soOrder.getOrderTime());
        // 平台类型
        if (soOrder.getSalePlatform().equals(String.valueOf(NumberConstant.IntDef.INT_ONE))) {
            cancelParam.setSupplyPlatform(CapitalPoolConst.SupplyPlatform.SNK);
        } else {
            cancelParam.setSupplyPlatform(CapitalPoolConst.SupplyPlatform.MSK);
        }
        // 0：正常 1：交易关闭。
        cancelParam.setTransFlg(NumberConstant.IntDef.INT_ONE);
        cancelParam.setPaymentType(NumberConstant.IntDef.INT_TWO);
        cancelParam.setCrtId(soOrder.getCrtId());
        asyncPostService.sendCpTransaction(cancelParam);
    }

    /**
     * 检验断货处理
     *
     * @param param
     */
    @Transactional
    public void dealProductStock(ISO151414BaseOrderParam param) {
        List<ISO151414OrderDetailParam> products = param.getProducts();
        ISO151414SellerProductParam productParam = new ISO151414SellerProductParam();
        List<ISO151414SellerProductInfo> productList = new ArrayList<>();

        for (ISO151414OrderDetailParam productInfo : products) {
            ISO151414SellerProductInfo product = new ISO151414SellerProductInfo();
            product.setSlCode(param.getSellerCode());
            product.setPdCode(productInfo.getPdCode());
            productList.add(product);
        }
        productParam.setProducts(productList);
        productParam.setCrtId(param.getCrtId());

        SoRestUtil.dealSellerProductHis(productParam);
    }

    /**
     * 通过订单类型判断返回是否需要创建供货明细表数据的标志
     *
     * @param param
     * @return
     */
    @Transactional
    public boolean checkOrderType(ISO151414BaseOrderParam param) {
        return !(param.getOrderType().equals(OrderCodeMasterDef.OrderType.THIRD_PARTY_ORDER)
                || param.getOrderType().equals(OrderCodeMasterDef.OrderType.THIRD_BUYER_ORDER)
                || param.getOrderType().equals(OrderCodeMasterDef.OrderType.BUYER_SALE_ORDER)
                || param.getOrderType().equals(OrderCodeMasterDef.OrderType.THIRD_BUYER_SALE_ORDER));

    }

    /**
     * 创建供货明细表时的到要创建的个数
     *
     * @param productList
     * @return
     */
    @Transactional
    public int getOrderShipDetailIndex(List<ISO151414OrderDetailParam> productList) {
        int orderShipDetailIndex = NumberConstant.IntDef.INT_ZERO;

        for (ISO151414OrderDetailParam ISO151414OrderDetailParam : productList) {
            List<SoOrderShipDetail> orderShipDetails = ISO151414OrderDetailParam.getOrderShipDetails();
            if (!CollectionUtils.isEmpty(orderShipDetails)) {
                orderShipDetailIndex = orderShipDetailIndex + orderShipDetails.size() + NumberConstant.IntDef.INT_ONE;
            }
        }

        return orderShipDetailIndex;
    }

    /**
     * 根据付款类型处理标准订单的订单状态
     *
     * @param param
     */
    @Transactional
    public void dealOrderStatusByPaymentType(ISO151414BaseOrderParam param) {
        if (param.getPaymentType() == NumberConstant.IntDef.INT_ONE) {
            param.setOrderStatus(OrderCodeMasterDef.OrderStatus.OBLIGATION);
        } else if (param.getPaymentType() == NumberConstant.IntDef.INT_TWO) {
            param.setOrderStatus(OrderCodeMasterDef.OrderStatus.WAIT_SEND);
        } else {
            throw new BusinessException("付款类型不正确");
        }
    }

    /**
     * 查询订单返回信息
     *
     * @param soOrder
     * @return
     */
    @Transactional
    public ISO151414OrderResult getOrderResult(SoOrder soOrder) {
        ISO151414OrderResult result = new ISO151414OrderResult();
        BeanUtils.copyProperties(soOrder, result);
        return result;
    }

    /**
     * 处理要插入表中的订单基本信息
     *
     * @param param
     */
    @Transactional
    public void dealBaseOrderParam(ISO151414BaseOrderParam param) {
        List<ISO151414OrderDetailParam> products = param.getProducts();
        if (CollectionUtils.isEmpty(products)) {
            throw new BusinessException("创建时订单的产品信息不能为空");
        }

        //处理物流区信息
        dealLgcsAreaInfo(param);

        //处理价盘信息
        dealPriceInfo(param);

        //处理产品信息
        dealPdInfo(param);

        //处理买家所属菜场、市场名称
        dealMarketNameByBuyerId(param);

        //处理这个买家是否有专属买手
        if (!param.getOrderType().equals(OrderCodeMasterDef.OrderType.THIRD_BUYER_ORDER)
                && !param.getOrderType().equals(OrderCodeMasterDef.OrderType.BUYER_STOCKPILING_ORDER)) {
            haveBuyer(param);
        }

        param.setDelFlg(SystemConstant.DelFlg.ENABLE);
        param.setVer(NumberConstant.IntDef.INT_ONE);
    }

    /**
     * 处理冻品管家信息
     *
     * @param param
     */
    @Transactional
    public void dealBuyerInfo(ISO151414BaseOrderParam param,SoSubOrder soSubOrder) {
        Integer orderType = soSubOrder.getOrderType();
        if (orderType == OrderCodeMasterDef.OrderType.THIRD_BUYER_SALE_ORDER || orderType == OrderCodeMasterDef.OrderType.BUYER_SALE_ORDER) {
            ISO151414BsBuyerInfoResult result = SoRestUtil.getSlBuyers(param.getBuyersId());
            if (null != result && !CollectionUtils.isEmpty(result.getResultList())
                    && !StringUtil.isEmpty(result.getResultList().get(NumberConstant.IntDef.INT_ZERO).getSlCode())) {
                List<ISO151414HouseSellerRestResult> resultList = result.getResultList();
                ISO151414HouseSellerRestResult houseSellerRestResult = resultList.get(NumberConstant.IntDef.INT_ZERO);
                param.setSaId(houseSellerRestResult.getHouseCode());
                param.setSaName(houseSellerRestResult.getHouseName());
                if (!StringUtil.isEmpty(houseSellerRestResult.getHouseCode())) {
                    SoRestUtil.updateBuyerValidDate(param);
                }
            }
        }
    }

    /**
     * 处理产品信息
     *
     * @param param
     */
    @Transactional
    public void dealPdInfo(ISO151414BaseOrderParam param) {
        List<ISO151414OrderDetailParam> products = param.getProducts();
        if (CollectionUtils.isEmpty(products)) {
            throw new BusinessException("创建时订单的产品信息不能为空");
        }

        List<String> pdCodes = new ArrayList<>();
        ProductInfoParam pdInfoParam = new ProductInfoParam();
        for (ISO151414OrderDetailParam orderDetailParam : products) {
            pdCodes.add(orderDetailParam.getPdCode());
        }
        pdInfoParam.setPdCodes(pdCodes);
        List<ISO151414ProductStandardInfo> pdInfoResults = SoRestUtil.getProductList(pdInfoParam);
        if (CollectionUtils.isEmpty(pdInfoResults)) {
            throw new BusinessException("同步产品信息为空，请检查！！！");
        }

        for (ISO151414OrderDetailParam orderDetailParam : products) {
            for (ISO151414ProductStandardInfo result : pdInfoResults) {
                if (orderDetailParam.getPdCode().equals(result.getPdCode())) {
                    BeanUtils.copyProperties(result, orderDetailParam);
                    orderDetailParam.setUnit("箱");
                    BigDecimal packingVolume = result.getNormsVolume().divide(BigDecimal.valueOf(1000000));
                    BigDecimal qty = orderDetailParam.getOrderQty();
                    orderDetailParam.setPackingVolume(packingVolume);
                    orderDetailParam.setWeight((result.getWeightVal().multiply(qty)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    orderDetailParam.setVolume((packingVolume.multiply(qty)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    orderDetailParam.setPdGradeCode(result.getGradeCode());
                    orderDetailParam.setPdGradeName(result.getGradeName());
                }
            }
        }

        param.setProducts(products);
    }

    /**
     * 得到市场名称或者菜场名称
     *
     * @param param
     */
    @Transactional
    public void dealMarketNameByBuyerId(ISO151414BaseOrderParam param) {
        ISO151414ByBuyerInfoParam buyerInfoParam = new ISO151414ByBuyerInfoParam();
        buyerInfoParam.setBuyerId(param.getBuyersId());
        String marketName = SoRestUtil.getMarketNameByBuyerId(buyerInfoParam);
        param.setBuyerMarketName(marketName);
    }


    /**
     * 处理物流区信息
     *
     * @param param
     */
    @Transactional
    public void dealLgcsAreaInfo(ISO151414BaseOrderParam param) {
        ISO151414DistrictParam ISO151414DistrictParam = new ISO151414DistrictParam();
        if (param.getDistrictCode() == null) {
            throw new BusinessException("没有物流信息,请检查！！！");
        }
        String[] lgcsAreaCodes = {param.getDistrictCode()};
        ISO151414DistrictParam.setLgcsAreaCodes(lgcsAreaCodes);
        DistrictResult result = SoRestUtil.getLogisticsAreaList(ISO151414DistrictParam);
        if (result != null) {
            List<LgcsAreaBean> lgcsAreaList = result.getLgcsAreaList();
            if (!CollectionUtils.isEmpty(lgcsAreaList)) {
                param.setDistrictName(lgcsAreaList.get(NumberConstant.IntDef.INT_ZERO).getLgcsAreaName());
            }
        }
    }

    /**
     * 处理订单单价
     *
     * @param param
     */
    @Transactional
    public void dealPriceInfo(ISO151414BaseOrderParam param) {

        List<ISO151414OrderDetailParam> products = param.getProducts();
        ISO151414PriceWayParam priceWayParam = new ISO151414PriceWayParam();
        List<ISO151414PriceProductParam> productList = new ArrayList<>();

        for (ISO151414OrderDetailParam orderDetailParam : products) {
            ISO151414PriceProductParam priceProductParam = new ISO151414PriceProductParam();
            priceProductParam.setPdCode(orderDetailParam.getPdCode());
            priceProductParam.setDistrictCode(param.getDistrictCode());
            priceProductParam.setOrderQty(orderDetailParam.getOrderQty());
            productList.add(priceProductParam);
        }
        priceWayParam.setProductList(productList);
        ISO151414PriceWayResult priceResult = SoRestUtil.getPriceWayInfo(priceWayParam);

        if (null == priceResult || CollectionUtils.isEmpty(priceResult.getProductList())) {
            throw new BusinessException("查询价盘信息为空");
        }

        List<ISO151414PriceProductResult> results = priceResult.getProductList();

        BigDecimal orderAmount = BigDecimal.ZERO;
        String pdCodeStr = StringConstant.EMPTY;

        for (ISO151414OrderDetailParam orderDetailParam : products) {
            boolean priceFlag = false;
            for (ISO151414PriceProductResult result : results) {
                if (orderDetailParam.getPdCode().equals(result.getPdCode())) {
                    orderDetailParam.setPriceCycle(result.getPriceCycle());
                    orderDetailParam.setOrderDetailLevel(Integer.valueOf(result.getSellWayCode()));
                    orderDetailParam.setPdPrice(result.getPdBoxPrice());
                    orderAmount = orderAmount.add(orderDetailParam.getOrderQty().multiply(result.getPdBoxPrice()));
                    priceFlag = true;
                    break;
                }
            }
            if(!priceFlag){
                if(StringUtil.isEmpty(pdCodeStr)){
                    pdCodeStr = pdCodeStr + orderDetailParam.getPdCode();
                }else {
                    pdCodeStr = pdCodeStr + "," + orderDetailParam.getPdCode();
                }
            }
        }
        if(!StringUtil.isEmpty(pdCodeStr)){
            throw new BusinessException("产品编码：" + pdCodeStr + ";价盘信息不能为空");
        }
        param.setProducts(products);
        param.setOrderAmount(orderAmount);
    }

    /**
     * 处理这个买家是否有专属买手
     *
     * @param param
     * @return
     */
    @Transactional
    public void haveBuyer(ISO151414BaseOrderParam param) {
        ISO151414BsBuyerInfoResult buyerBean = SoRestUtil.getSlBuyers(param.getBuyersId());
        if (null == buyerBean) {
            param.setHaveBuyerFlag(false);
            return;
        }
        List<ISO151414HouseSellerRestResult> resultList = buyerBean.getResultList();

        if (CollectionUtils.isEmpty(resultList)) {
            param.setHaveBuyerFlag(false);
            return;
        }

        ISO151414HouseSellerRestResult sellerInfo = resultList.get(NumberConstant.IntDef.INT_ZERO);

        if (StringUtil.isEmpty(sellerInfo.getSlCode())) {
            param.setHaveBuyerFlag(false);
            return;
        }

        param.setBuyerSaleCode(sellerInfo.getSlId());
        param.setBuyerSaleName(sellerInfo.getSlName());
        param.setSaId(sellerInfo.getHouseId());
        param.setSaName(sellerInfo.getHouseName());
        param.setHaveBuyerFlag(true);
    }

    /**
     * 创建订单状态表
     *
     * @param soOrder
     */
    @Transactional
    public void saveOrderStatus(SoOrder soOrder) {
        SoOrderStatus soOrderStatus = new SoOrderStatus();
        BeanUtils.copyProperties(soOrder, soOrderStatus);
        soOrderStatus.setStatusId(this.maxId("so_order_status"));
        soOrderStatus.setOrderCode(soOrder.getOrderCode());
        soOrderStatus.setOrderStatus(soOrder.getOrderStatus());
        soOrderStatus.setCrtId(soOrder.getCrtId());
        soOrderStatus.setCrtTime(soOrder.getCrtTime());
        soOrderStatusRepository.save(soOrderStatus);
    }

    /**
     * 创建分批订单状态表
     *
     * @param soSubOrder
     */
    @Transactional
    public void saveSubOrderStatus(SoSubOrder soSubOrder) {
        SoSubOrderStatus soSubOrderStatus = new SoSubOrderStatus();
        BeanUtils.copyProperties(soSubOrder, soSubOrderStatus);
        soSubOrderStatus.setStatusId(this.maxId("so_sub_order_status"));
        soSubOrderStatusRepository.save(soSubOrderStatus);
    }

    /**
     * 创建收货要求信息
     *
     * @param param
     * @param soOrder
     */
    @Transactional
    public void createOrderReceiveDemand(ISO151414BaseOrderParam param, SoOrder soOrder) {
        SoOrderReceiveDemand soOrderReceiveDemand = dealOrderReceiveDemandInfo(param, soOrder);
        soOrderReceiveDemandRepository.save(soOrderReceiveDemand);
    }

    /**
     * 遍历收货要求信息
     *
     * @param param
     * @param soOrder
     * @return
     */
    @Transactional
    public SoOrderReceiveDemand dealOrderReceiveDemandInfo(ISO151414BaseOrderParam param, SoOrder soOrder) {
        ISO151414ReceiverInfo ISO151414ReceiverInfo = param.getReceiverInfo();
        ISO151414DeliveryReq ISO151414DeliveryReq = param.getDeliveryReq();
        if (null == ISO151414ReceiverInfo) {
            throw new BusinessException("订单收货人要求不能为空");
        }
        if (null == ISO151414DeliveryReq) {
            throw new BusinessException("订单配送要求不能为空");
        }

        SoOrderReceiveDemand soOrderReceiveDemand = new SoOrderReceiveDemand();
        soOrderReceiveDemand.setDemandId(this.maxId("so_order_receive_demand"));
        BeanUtils.copyProperties(soOrder, soOrderReceiveDemand);
        soOrderReceiveDemand.setReceiverName(ISO151414ReceiverInfo.getReceiverName());
        soOrderReceiveDemand.setReceiverTel(ISO151414ReceiverInfo.getReceiverTel());
        soOrderReceiveDemand.setReceiverQq(ISO151414ReceiverInfo.getReceiverQQ());
        soOrderReceiveDemand.setReceiverWeixin(ISO151414ReceiverInfo.getReceiverWeixin());
        soOrderReceiveDemand.setReceiverProvince(ISO151414ReceiverInfo.getReceiverProvince());
        soOrderReceiveDemand.setReceiverCity(ISO151414ReceiverInfo.getReceiverCity());
        soOrderReceiveDemand.setReceiverDistrict(ISO151414ReceiverInfo.getReceiverDistrict());
        soOrderReceiveDemand.setReceiverAddr(ISO151414ReceiverInfo.getReceiverAddr());
        soOrderReceiveDemand.setReceiverAddr2(ISO151414ReceiverInfo.getReceiverAddr2());
        soOrderReceiveDemand.setReceiverAddrKey(ISO151414ReceiverInfo.getReceiverAddrKey());
        soOrderReceiveDemand.setReceiveTime(ISO151414ReceiverInfo.getReceiveTime());
        soOrderReceiveDemand.setReceiveEarliest(ISO151414ReceiverInfo.getReceiveEarliest());
        soOrderReceiveDemand.setReceiveLast(ISO151414ReceiverInfo.getReceiveLast());
        soOrderReceiveDemand.setVicFlg(ISO151414DeliveryReq.getVicFlg());
        soOrderReceiveDemand.setUnloadReq(ISO151414DeliveryReq.getUnloadReq());
        soOrderReceiveDemand.setPackingReq(ISO151414DeliveryReq.getPackingReq());
        soOrderReceiveDemand.setParkingDistance(ISO151414DeliveryReq.getParkingDistance());
        soOrderReceiveDemand.setOtherDeliveryReq(ISO151414DeliveryReq.getOtherDeliveryReq());
        soOrderReceiveDemand.setThisDeliveryReq(ISO151414DeliveryReq.getThisDeliveryReq());
        soOrderReceiveDemand.setRemark(ISO151414ReceiverInfo.getRemark());
        soOrderReceiveDemand.setRemark2(ISO151414ReceiverInfo.getRemark2());
        soOrderReceiveDemand.setRemark3(ISO151414ReceiverInfo.getRemark3());
        soOrderReceiveDemand.setRemark4(ISO151414ReceiverInfo.getRemark4());
        return soOrderReceiveDemand;
    }

    /**
     * 创建订单主表信息
     *
     * @param param
     * @return
     */
    @Transactional
    public SoOrder saveOrder(ISO151414BaseOrderParam param) {
        SoOrder soOrder = dealOrderInfo(param);
        orderRepository.save(soOrder);
        return soOrder;
    }

    /**
     * 遍历订单主表信息
     *
     * @param param
     * @return
     */
    @Transactional
    public SoOrder dealOrderInfo(ISO151414BaseOrderParam param) {
        SoOrder soOrder = new SoOrder();
        BeanUtils.copyProperties(param, soOrder);
        soOrder.setOrderId(this.maxId("so_order"));
        soOrder.setOrderCode(this.getOrderCodeByOrderAndType(param));
        soOrder.setOrderViceCode(this.getOrderCodeByOrderTime(param.getCrtTime()));
        soOrder.setBuyerId(param.getBuyersId());
        soOrder.setBuyerCode(param.getBuyersCode());
        soOrder.setBuyerName(param.getBuyersName());
        soOrder.setBuyerType(param.getBuyersType());
        soOrder.setOrderTime(param.getCrtTime());
        soOrder.setPayStatus(OrderCodeMasterDef.PayStatus.NON_PAY);
        soOrder.setOrderStatus(OrderCodeMasterDef.OrderStatus.NEW);
        return soOrder;
    }

    /**
     * 创建分批订单主表信息
     *
     * @param soOrder
     * @return
     */
    @Transactional
    public SoSubOrder saveSubOrder(SoOrder soOrder, ISO151414BaseOrderParam param) {
        SoSubOrder soSubOrder = dealSubOrderInfo(soOrder);
        soSubOrder.setOrderType(param.getOrderType());
        soSubOrder.setSellerCode(param.getSellerCode());
        soSubOrder.setSellerName(param.getSellerName());
        soSubOrderRepository.save(soSubOrder);
        return soSubOrder;
    }

    /**
     * 遍历分批订单主表信息
     *
     * @param soOrder
     * @return
     */
    @Transactional
    public SoSubOrder dealSubOrderInfo(SoOrder soOrder) {
        SoSubOrder soSubOrder = new SoSubOrder();
        BeanUtils.copyProperties(soOrder, soSubOrder);
        soSubOrder.setSubOrderId(this.maxId("so_sub_order"));
        soSubOrder.setSubOrderCode(this.getSubOrderCode(soOrder.getOrderId(), soOrder.getOrderCode()));
//        if (soOrder.getOrderStatus().equals(OrderCodeMasterDef.OrderStatusDef.OBLIGATION)) {
//            soSubOrder.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatusDef.OBLIGATION);
//        } else if (soOrder.getOrderType().equals(OrderCodeMasterDef.OrderType.BUYER_STOCKPILING_ORDER)
//                || soOrder.getOrderType().equals(OrderCodeMasterDef.OrderType.DISTRIBUTION_ORDER)) {
//            soSubOrder.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatusDef.WAIT_DISTRIBUTION);
//        } else {
//            soSubOrder.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatusDef.CONFIRM);
//        }
        soSubOrder.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatus.NEW);
        soSubOrder.setSubPayStatus(soOrder.getPayStatus());
        return soSubOrder;
    }

    /**
     * 创建订单明细信息
     *
     * @param orderDetailParam
     * @param soSubOrder
     * @return
     */
    @Transactional
    public SoOrderDetail saveOrderDetail(ISO151414OrderDetailParam orderDetailParam, SoSubOrder soSubOrder, Long orderDetailId) {
        SoOrderDetail soOrderDetail = dealOrderDetailInfo(orderDetailParam, soSubOrder);
        soOrderDetail.setOrderDetailId(orderDetailId);
        soOrderDetailRepository.save(soOrderDetail);
        return soOrderDetail;
    }

    /**
     * 遍历订单明细信息
     *
     * @param orderDetailParam
     * @param soSubOrder
     * @return
     */
    @Transactional
    public SoOrderDetail dealOrderDetailInfo(ISO151414OrderDetailParam orderDetailParam, SoSubOrder soSubOrder) {
        SoOrderDetail soOrderDetail = new SoOrderDetail();
        BeanUtils.copyProperties(orderDetailParam, soOrderDetail);
        soOrderDetail.setOrderId(soSubOrder.getOrderId());
        soOrderDetail.setOrderCode(soSubOrder.getOrderCode());

        if (soSubOrder.getOrderType().equals(OrderCodeMasterDef.OrderType.DISTRIBUTION_ORDER)
                || soSubOrder.getOrderType().equals(OrderCodeMasterDef.OrderType.BUYER_STOCKPILING_ORDER)) {
            soOrderDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailStatus.WAIT_DISTRIBUTION);
        } else {
            soOrderDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailStatus.CONFIRM);
        }
        soOrderDetail.setCrtTime(soSubOrder.getCrtTime());
        soOrderDetail.setCrtId(soSubOrder.getCrtId());
        soOrderDetail.setDelFlg(soSubOrder.getDelFlg());
        soOrderDetail.setVer(soSubOrder.getVer());
        return soOrderDetail;
    }

    /**
     * 创建分批订单明细
     *
     * @param soOrderDetail
     * @param soSubOrder
     * @return
     */
    @Transactional
    public SoSubOrderDetail saveSubOrderDetail(SoOrderDetail soOrderDetail, SoSubOrder soSubOrder, Long subOrderDetailId) {
        SoSubOrderDetail soSubOrderDetail = dealSubOrderDetailInfo(soOrderDetail, soSubOrder);
        soSubOrderDetail.setSubOrderDetailId(subOrderDetailId);
        soSubOrderDetailRepository.save(soSubOrderDetail);
        return soSubOrderDetail;
    }

    /**
     * 遍历分批订单明细数据
     *
     * @param soOrderDetail
     * @param soSubOrder
     * @return
     */
    @Transactional
    public SoSubOrderDetail dealSubOrderDetailInfo(SoOrderDetail soOrderDetail, SoSubOrder soSubOrder) {
        SoSubOrderDetail soSubOrderDetail = new SoSubOrderDetail();
        BeanUtils.copyProperties(soOrderDetail, soSubOrderDetail);
        soSubOrderDetail.setSubOrderId(soSubOrder.getSubOrderId());
        soSubOrderDetail.setOrderId(soSubOrder.getOrderId());
        soSubOrderDetail.setOrderDetailType(NumberConstant.IntDef.INT_ONE);
        if (soSubOrder.getOrderType().equals(OrderCodeMasterDef.OrderType.DISTRIBUTION_ORDER)
                || soSubOrder.getOrderType().equals(OrderCodeMasterDef.OrderType.BUYER_STOCKPILING_ORDER)) {
            soSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.WAIT_DISTRIBUTION);
        } else {
            soSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.CONFIRM);
        }
        return soSubOrderDetail;
    }

    @Transactional
    public void saveOrderShipDetail(SoSubOrderDetail soSubOrderDetail, SoOrderShipDetail soOrderShipDetail, Long orderShipDetailId) {
        BeanUtils.copyProperties(soSubOrderDetail, soOrderShipDetail);
        soOrderShipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.CONFIRM);
        soOrderShipDetail.setShipDetailId(orderShipDetailId);
        soOrderShipDetailRepository.save(soOrderShipDetail);
    }

    /**
     * 异步调用资金池接口并且处理数据
     *
     * @param soOrder
     */
    @Transactional
    public void saveCpTransaction(SoOrder soOrder) {
        ISO151414CpTransactionParam cpTransactionParam = new ISO151414CpTransactionParam();
        cpTransactionParam.setInsertFlg(NumberConstant.IntDef.INT_ONE);
        cpTransactionParam.setTransCode(soOrder.getOrderCode());
        cpTransactionParam.setOrderId(soOrder.getOrderId());
        cpTransactionParam.setTransType(NumberConstant.IntDef.INT_ZERO);
        cpTransactionParam.setOperateDate(soOrder.getCrtTime());
        cpTransactionParam.setSupplyPlatform(Integer.valueOf(soOrder.getSalePlatform()));
        // 交易标志 0：正常 1：交易关闭
        cpTransactionParam.setTransFlg(NumberConstant.IntDef.INT_ZERO);
        cpTransactionParam.setBusinessMainRole(CommOrderConst.RoleType.ROLE_PLATFORM);

        // 分销订单,第三方订单 // 买手囤货订单,第三方买手囤货订单
        if (soOrder.getOrderType() == OrderCodeMasterDef.OrderType.DISTRIBUTION_ORDER
                || soOrder.getOrderType() == OrderCodeMasterDef.OrderType.THIRD_PARTY_ORDER) {
            cpTransactionParam.setBusinessAssistantRole(Integer.valueOf(CommOrderConst.RoleType.ROLE_BIDDER));
        } else if (soOrder.getOrderType() == OrderCodeMasterDef.OrderType.BUYER_STOCKPILING_ORDER
                || soOrder.getOrderType() == OrderCodeMasterDef.OrderType.THIRD_BUYER_ORDER) {
            cpTransactionParam.setBusinessAssistantRole(Integer.valueOf(CommOrderConst.RoleType.ROLE_BUYERE));
        }

        if (soOrder.getSalePlatform().equals(OrderCodeMasterDef.SalePlatform.YDP)) {
            cpTransactionParam.setBusinessMainId(CapitalPoolConst.OtherConst.SNK_ID);
            cpTransactionParam.setBusinessMainName("神农客");
        } else {
            cpTransactionParam.setBusinessMainId(CapitalPoolConst.OtherConst.SNK_ID);
            cpTransactionParam.setBusinessMainName("美侍客");
        }

        cpTransactionParam.setBusinessAssistantId(soOrder.getBuyerId());
        cpTransactionParam.setBusinessAssistantCode(soOrder.getBuyerCode());
        cpTransactionParam.setBusinessAssistantName(soOrder.getBuyerName());
        cpTransactionParam.setOrderAmount(soOrder.getOrderAmount());
        cpTransactionParam.setTranTime(soOrder.getCrtTime());
        // 支付类型 1:在线支付,2:线下支付
        cpTransactionParam.setPaymentType(soOrder.getPaymentType());
        cpTransactionParam.setCrtId(soOrder.getCrtId());
        asyncPostService.sendCpTransaction(cpTransactionParam);
    }

    /**
     * 根据订单时间生成单据辅码
     *
     * @param orderTime 时间
     * @return 单据辅码
     */
    @Transactional
    public String getOrderCodeByOrderTime(Date orderTime) {
        String yyMMdd = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, orderTime);
        //标准时间单元编码
        Calendar getOrderTime = Calendar.getInstance();
        getOrderTime.setTime(orderTime);
        int orderHour = getOrderTime.get(Calendar.HOUR_OF_DAY);
        int orderMin = getOrderTime.get(Calendar.MINUTE);
        if (orderMin > NumberConstant.IntDef.INT_THIRTY) {
            orderHour = orderHour * NumberConstant.IntDef.INT_TWO + NumberConstant.IntDef.INT_TWO;
        } else {
            orderHour = orderHour * NumberConstant.IntDef.INT_TWO + NumberConstant.IntDef.INT_ONE;
        }
        String standardTimeCode = yyMMdd.substring(NumberConstant.IntDef.INT_TWO, NumberConstant.IntDef.INT_EIGHT) + String.valueOf(orderHour);
        return standardTimeCode;
    }

    /**
     * 根据买家编号获得买家购物次数
     * 根据订单类型，买家编号，买家下单次数生成单据主码
     * @param param
     * @return
     */
    @Transactional
    public String getOrderCodeByOrderAndType(ISO151414BaseOrderParam param) {
        long buyerOrderCount = this.purchaseTimes(param);
        String orderMainCode = getOrderType(param.getOrderType()) + "-" + param.getBuyersCode() + "-" + String.valueOf(buyerOrderCount);
        return orderMainCode;
    }

    /**
     * 获得买家购买次数
     * @param param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private long purchaseTimes(ISO151414BaseOrderParam param) {
        Filter<SoOrderBuyerSeq> filter = new Filter();
        filter.add("buyerCode", BaseOperatorEnum.EQUAL, param.getBuyersCode());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification spec = new CommonSpecification(filter);
        SoOrderBuyerSeq soOrderBuyerSeq = soOrderBuyerSeqRepository.findOne(spec);
        if (null == soOrderBuyerSeq) {
            soOrderBuyerSeq = getSoOrderBuyerSeqInfo(param);
        } else {
            soOrderBuyerSeq.setBuyCount(soOrderBuyerSeq.getBuyCount() + NumberConstant.IntDef.INT_ONE);
        }

        soOrderBuyerSeqRepository.save(soOrderBuyerSeq);

        return soOrderBuyerSeq.getBuyCount();
    }

    /**
     * 遍历买家订单次数顺序表信息
     * @param param
     * @return
     */
    @Transactional
    public SoOrderBuyerSeq getSoOrderBuyerSeqInfo(ISO151414BaseOrderParam param) {
        SoOrderBuyerSeq soOrderBuyerSeq = new SoOrderBuyerSeq();
        soOrderBuyerSeq.setSeqId(this.maxId("so_order_buyer_seq"));
        soOrderBuyerSeq.setBuyerCode(param.getBuyersCode());
        soOrderBuyerSeq.setBuyCount(Long.valueOf(NumberConstant.IntDef.INT_ONE));
        soOrderBuyerSeq.setCrtTime(DateTimeUtil.getCustomerDate());
        soOrderBuyerSeq.setVer(NumberConstant.IntDef.INT_ONE);
        soOrderBuyerSeq.setDelFlg(NumberConstant.IntDef.INT_ZERO + StringConstant.EMPTY);
        soOrderBuyerSeq.setBuyerId(param.getBuyersId());
        soOrderBuyerSeq.setCrtId(param.getCrtId());
        return soOrderBuyerSeq;
    }

    /**
     * 获得订单类型编码
     *
     * @param type type
     * @return orderType orderType
     */
    private String getOrderType(int type) {
        String orderType = "";
        if (type == NumberConstant.IntDef.INT_ONE) {
            orderType = "001";
        } else if (type == NumberConstant.IntDef.INT_TWO) {
            orderType = "002";
        } else if (type == NumberConstant.IntDef.INT_THREE) {
            orderType = "003";
        }
        if (type == NumberConstant.IntDef.INT_FOUR) {
            orderType = "004";
        } else if (type == NumberConstant.IntDef.INT_FIVE) {
            orderType = "005";
        } else if (type == NumberConstant.IntDef.INT_SIX) {
            orderType = "006";
        } else if (type == NumberConstant.IntDef.INT_SEVEN) {
            orderType = "007";
        }
        return orderType;
    }

    /**
     * 通过订单Id得到分批订单编码
     *
     * @param orderId
     * @return
     */
    @Transactional
    public String getSubOrderCode(Long orderId, String orderCode) {
        Filter<SoSubOrder> filter = new Filter();
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification spec = new CommonSpecification(filter);
        List<SoSubOrder> soSubOrders = soSubOrderRepository.findAll(spec);
        if (CollectionUtils.isEmpty(soSubOrders)) {
            return orderCode + StringConstant.MIDDLE_LINE + NumberConstant.IntDef.INT_ONE;
        }

        Integer codeIndex = soSubOrders.size() + NumberConstant.IntDef.INT_ONE;
        return orderCode + StringConstant.MIDDLE_LINE + codeIndex;
    }
}
