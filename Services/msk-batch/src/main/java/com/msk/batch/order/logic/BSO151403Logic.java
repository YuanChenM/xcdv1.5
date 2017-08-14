package com.msk.batch.order.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.order.bean.*;
import com.msk.batch.order.commUtils.BatchCommRestUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.consts.OrderConst;
import com.msk.common.logic.CommonLogic;
import com.msk.common.xml.JaxbXmlWrite;
import com.msk.seller.bean.SlPdArtnoBean;
import com.msk.utils.WmsFtpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * *BSO152403Logic
 * *@author wang_jianzhou
 * *@version 1.0
 **/
@Service
public class BSO151403Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSO151403Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author yuan_chen
     */
    interface SqlId {
        static final String SQLID_FIND_SUB_ORDER_LIST = "findSubOrderList";
        static final String SQL_ID_FIND_SUB_ORDER_DETAIL = "findSubOrderDetailList";
        static final String SQL_ID_UPDATE_SUB_DETAIL_ORDER_STATUS = "updateSubDetailOrderStatus";
        static final String SQL_ID_UPDATE_ORDER_SHIP_DETAIL_STATUS = "updateOrderShipDetailStatus";
        static final String SQL_ID_SAVE_SHIP_INFO = "saveShipInfo";
        static final String SQL_ID_GET_MAX_ORDER_SHIP_CODE = "getMaxOrderShipCode";
        static final String SQL_ID_MODIFY_SUPP_SHIP_ID = "modifySuppShipId";
        static final String SQL_ID_MODIFY_SUPP_SKU = "modifySuppSku";
        static final String SQL_ID_MODIFY_SUB_ORDER_STATUS = "modifySubOrderStatus";
        static final String SQL_ID_MODIFY_SUB_ORDER_STATUS_STATUS = "modifySubOrderStatusStatus";
        static final String SQL_ID_SAVE_SUB_ORDER_STATUS_STATUS = "saveSubOrderStatusStatus";
        static final String SQL_ID_MODIFY_ORDER_STATUS = "modifyOrderStatus";
        static final String SQL_ID_MODIFY_ORDER_STATUS_STATUS = "modifyOrderStatusStatus";
        static final String SQL_ID_SAVE_ORDER_STATUS_STATUS = "saveOrderStatusStatus";
        static final String SQL_ID_MODIFY_ORDER_DETAIL_STATUS = "modifyOrderDetailStatus";
    }

    @Autowired
    private CommonLogic commonLogic;


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 根据订单信息进行分单操作
     *
     * @param bso151403Param param
     */
    @Transactional
    public void findOrderList(BSO151403Param bso151403Param) {
        logger.debug("WMS生成配送单开始");
        /** CONFIRM:已确认 */
        bso151403Param.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.CONFIRM);
        //买手囤货订单
        bso151403Param.setOrderType1(OrderConst.OrderType.BUYER_STOCKPILING_ORDER);
        //第三方买手囤货订单
        bso151403Param.setOrderType2(OrderConst.OrderType.THIRD_BUYER_ORDER);
        //当前日期
        bso151403Param.setNowDate(DateTimeUtil.getCustomerDate());

        List<BSO151403Result> results = super.findList(SqlId.SQLID_FIND_SUB_ORDER_LIST, bso151403Param);

        if (!CollectionUtils.isEmpty(results)) {
            for (BSO151403Result result : results) {
                try {
                    createXml(result);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    continue;
                }
            }
        }
    }

    /**
     * 生成xml并且更新状态为待发货
     *
     * @param bso151403Result bso151403Result
     */
    @Transactional
    private void createXml(BSO151403Result bso151403Result) {
        logger.info("生成XML");
        BSO151403ReceiverInfo receiver = new BSO151403ReceiverInfo();
        receiver.setReceiverName(bso151403Result.getReceiverName());
        receiver.setReceiverTel(bso151403Result.getReceiverTel());
        receiver.setReceiverWeixin(bso151403Result.getReceiverWeixin());
        receiver.setReceiverQq(bso151403Result.getReceiverQq());
        receiver.setReceiverMail(bso151403Result.getReceiverMail());
        receiver.setReceiverProvince(bso151403Result.getReceiverProvince());
        receiver.setReceiverCity(bso151403Result.getReceiverCity());
        receiver.setReceiverDistrict(bso151403Result.getReceiverDistrict());
        receiver.setReceiverAddr(bso151403Result.getReceiverAddr());
        receiver.setReceiverAddr2(bso151403Result.getReceiverAddr2());
        receiver.setReceiverAddrKey(bso151403Result.getReceiverAddrKey());
        if (bso151403Result.getReceiverDeType() == null) {
            receiver.setReceiverDeType("");
        } else {
            receiver.setReceiverDeType(bso151403Result.getReceiverDeType());
        }

        bso151403Result.setReceiver(receiver);

        BSO151403Param bso151403Param = new BSO151403Param();
        bso151403Param.setOrderId(bso151403Result.getOrderId());
        bso151403Param.setOrderStatus(OrderCodeMasterDef.SubOrderDetailStatus.CONFIRM);
        bso151403Param.setNowDate(DateTimeUtil.getCustomerDate());
        bso151403Param.setSubOrderId(bso151403Result.getSubOrderId());
        //查询产品信息
        List<BSO151403DetailResult> productDetailList = super.findList(SqlId.SQL_ID_FIND_SUB_ORDER_DETAIL, bso151403Param);

        if (CollectionUtils.isEmpty(productDetailList)) {
            throw new BusinessException("没有对应的产品信息，请检查！！！");
        }

        for(BSO151403DetailResult productDetail : productDetailList){
            productDetail.setSellerCode(bso151403Result.getSellerCode());
            productDetail.setInventoryStatus("AVAILABLE");
        }
        saveShipInfo(bso151403Result,productDetailList);

        getDetail(bso151403Result, productDetailList);

        List<BSO151403Result> xmlList = new ArrayList<BSO151403Result>();
        bso151403Result.setCrtId(null);
        bso151403Result.setCrtTime(null);
        bso151403Result.setUpdId(null);
        bso151403Result.setUpdTime(null);
        for(BSO151403DetailResult detail : bso151403Result.getProductDetailList()){
            detail.setUpdId(null);
            detail.setUpdTime(null);
        }
        //发货单版本号
        bso151403Result.setVer(NumberConst.IntDef.INT_ONE);
        xmlList.add(bso151403Result);
        BSO151403XMLTemplate xmlTemplate = new BSO151403XMLTemplate();
        xmlTemplate.setData(xmlList);

        JaxbXmlWrite<BSO151403Result> xmlWrite = new JaxbXmlWrite<BSO151403Result>(xmlTemplate);
        InputStream inputStream = xmlWrite.createListTemplateXml(BSO151403XMLTemplate.class);
        boolean result = WmsFtpUtils.uploadXml("/", "SO" + bso151403Result.getShipCode() + ".xml", inputStream);
        if (logger.isDebugEnabled()) {
            if (result) {
                logger.debug(bso151403Result.getShipCode() + ".xml" + "文件上传成功");
            } else {
                logger.debug(bso151403Result.getShipCode() + ".xml" + "文件上传失败");
            }
        }
    }

    /**
     * 插入发货单主表数据并且更新供货明细表的发货单号
     *
     * @param bso151403Result
     */
    @Transactional
    public void saveShipInfo(BSO151403Result bso151403Result, List<BSO151403DetailResult> productDetailList) {
        logger.info("插入发货单");
        bso151403Result.setNowDate(DateTimeUtil.getCustomerDate());
        bso151403Result.setShipId(commonLogic.maxId("so_order_ship", "SHIP_ID"));
        bso151403Result.setShipCode(getShipCode(bso151403Result));
        bso151403Result.setShipStatus(OrderCodeMasterDef.ShipStatus.WAIT_SEND);
        bso151403Result.setCrtTime(DateTimeUtil.getCustomerDate());
        bso151403Result.setCrtId("BSO151403Batch");
        bso151403Result.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.CONFIRM);

        this.save(SqlId.SQL_ID_SAVE_SHIP_INFO, bso151403Result);

        logger.info("更新供货明细ShipId");
        for(BSO151403DetailResult bso151403DetailResult : productDetailList){
            bso151403Result.setSubOrderDetailId(bso151403DetailResult.getSubOrderDetailId());
            bso151403Result.setShipDetailId(bso151403DetailResult.getOrderLine());
            bso151403Result.setUpdId("BSO151403Batch");
            bso151403Result.setUpdTime(DateTimeUtil.getCustomerDate());
            this.modify(SqlId.SQL_ID_MODIFY_SUPP_SHIP_ID, bso151403Result);
        }

    }

    /**
     * 得到发货单的货号
     *
     * @param bso151403Result
     * @return
     */
    @Transactional
    public String getShipCode(BSO151403Result bso151403Result) {
        logger.info("生成发货单编码");
        BaseParam param = new BaseParam();
        param.setFilter("orderId", bso151403Result.getOrderId().toString());
        String maxShipCode = (String)super.findObject(SqlId.SQL_ID_GET_MAX_ORDER_SHIP_CODE, param);
        Integer shipCount = StringUtil.isEmpty(maxShipCode) ?
                NumberConst.IntDef.INT_ONE :
                Integer.valueOf(maxShipCode.split(StringConst.MIDDLE_LINE)[maxShipCode.split(StringConst.MIDDLE_LINE).length - NumberConst.IntDef.INT_ONE]) + NumberConst.IntDef.INT_ONE;
        return bso151403Result.getOrderCode() + StringConst.MIDDLE_LINE + shipCount;
    }

    /**
     * 查询明细定单并更新明细状态和供应商明细状态
     */
    @Transactional
    private void getDetail(BSO151403Result bso151403Result, List<BSO151403DetailResult> productDetailList) {

        List<BSO151403DetailResult> bso151403DetailResults = new ArrayList<BSO151403DetailResult>();
        bso151403DetailResults = this.getSkus(bso151403Result,productDetailList);
        if(!CollectionUtils.isEmpty(bso151403DetailResults)){
            for (BSO151403DetailResult productDetail : bso151403DetailResults) {
                productDetail.setDistrictCode(bso151403Result.getDistrictCode());
                productDetail.setSellerCode(bso151403Result.getSellerCode());
                productDetail.setOrderSource(bso151403Result.getOrderSource());
                productDetail.setUnit("CASE");
                //productDetail.setSku(getSkuCode(productDetail, bso151403Result));
                productDetail.setUpdTime(DateTimeUtil.getCustomerDate());
                productDetail.setUpdId("BSO151403Batch");
                if(StringUtil.isEmpty(productDetail.getSku())){
                    productDetail.setSku("");
                }
                this.modify(SqlId.SQL_ID_MODIFY_SUPP_SKU, productDetail);
            }
        }

        bso151403Result.setProductDetailList(productDetailList);

        //要求到货日期
        bso151403Result.setStrForecastReceiveTime(
                DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD,
                        bso151403Result.getForecastReceiveTime()));
        // 计划发货日期
        bso151403Result.setStrForecastSendTime(
                DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD,
                        bso151403Result.getForecastSendTime()));
        //生成配送单编码
        bso151403Result.setDeliveryOrderCode(BatchCommRestUtils.getDeliveryCode());

        // 所有订单详细信息处理成果,更新订单状态为待发货
        updateSubDetailOrderStatus(bso151403Result);
        updateOrderShipDetailStatus(bso151403Result);
        updateSubOrderStatus(bso151403Result);
        modifyOrderDetailStatus(bso151403Result);
        updateOrderStatus(bso151403Result);
        bso151403Result.setVer(bso151403Result.getVer() + NumberConst.IntDef.INT_ONE);
    }

    /**
     * 更新分批订单明细状态
     *
     * @param bso151403Result
     * @return
     */
    @Transactional
    private int updateSubDetailOrderStatus(BSO151403Result bso151403Result) {
        logger.debug("更新分批订单明细状态");
        bso151403Result.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.WAIT_SEND);
        bso151403Result.setUpdTime(DateTimeUtil.getCustomerDate());
        bso151403Result.setUpdId("BSO151403Batch");
        return super.modify(SqlId.SQL_ID_UPDATE_SUB_DETAIL_ORDER_STATUS, bso151403Result);
    }


    /**
     * 更新订单发货明细状态
     *
     * @param bso151403Result
     * @return
     */
    @Transactional
    private int updateOrderShipDetailStatus(BSO151403Result bso151403Result) {
        logger.debug("更新发货明细状态");
        bso151403Result.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.WAIT_SEND);
        bso151403Result.setUpdTime(DateTimeUtil.getCustomerDate());
        bso151403Result.setUpdId("BSO151403Batch");
        return super.modify(SqlId.SQL_ID_UPDATE_ORDER_SHIP_DETAIL_STATUS, bso151403Result);
    }

    /**
     * 更新分批订单状态
     *
     * @param bso151403Result bso151403Result
     */
    @Transactional
    public void updateSubOrderStatus(BSO151403Result bso151403Result){
        logger.debug("更新分批订单状态");
        bso151403Result.setOrderStatus(OrderCodeMasterDef.SubOrderStatus.WAIT_SEND);
        bso151403Result.setUpdTime(DateTimeUtil.getCustomerDate());
        bso151403Result.setUpdId("BSO151403Batch");
        super.modify(SqlId.SQL_ID_MODIFY_SUB_ORDER_STATUS, bso151403Result);
        logger.debug("更新分批订单状态履历");
        super.modify(SqlId.SQL_ID_MODIFY_SUB_ORDER_STATUS_STATUS, bso151403Result);
        bso151403Result.setStatusId(commonLogic.maxId("SO_SUB_ORDER_STATUS", "STATUS_ID"));
        logger.debug("插入分批订单状态履历");
        super.save(SqlId.SQL_ID_SAVE_SUB_ORDER_STATUS_STATUS,bso151403Result);
    }

    /**
     * 更新原始订单明细状态
     *
     * @param bso151403Result bso151403Result
     */
    @Transactional
    public void modifyOrderDetailStatus (BSO151403Result bso151403Result){
        logger.debug("更新订单明细状态");
        bso151403Result.setDetailStatus(OrderCodeMasterDef.OrderDetailStatus.WAIT_SEND);
        bso151403Result.setUpdTime(DateTimeUtil.getCustomerDate());
        bso151403Result.setUpdId("BSO151403Batch");
        super.modify(SqlId.SQL_ID_MODIFY_ORDER_DETAIL_STATUS, bso151403Result);
    }

    /**
     * 更新原始订单状态
     *
     * @param bso151403Result bso151403Result
     */
    @Transactional
    private void updateOrderStatus(BSO151403Result bso151403Result) {
        logger.debug("更新订单状态");
        bso151403Result.setOrderStatus(OrderCodeMasterDef.OrderStatus.WAIT_SEND);
        bso151403Result.setUpdTime(DateTimeUtil.getCustomerDate());
        bso151403Result.setUpdId("BSO151403Batch");
        super.modify(SqlId.SQL_ID_MODIFY_ORDER_STATUS, bso151403Result);
        logger.debug("更新订单状态履历");
        super.modify(SqlId.SQL_ID_MODIFY_ORDER_STATUS_STATUS, bso151403Result);
        bso151403Result.setStatusId(commonLogic.maxId("SO_ORDER_STATUS", "STATUS_ID"));
        logger.debug("插入订单状态履历");
        super.save(SqlId.SQL_ID_SAVE_ORDER_STATUS_STATUS, bso151403Result);
    }

    /**
     * 批量得到货号
     *
     * @param bso151403Result
     * @param productDetailList
     * @return
     */
    public List<BSO151403DetailResult> getSkus(BSO151403Result bso151403Result, List<BSO151403DetailResult> productDetailList){
        //设置供应平台
        String salesPlatform = bso151403Result.getSalePlatForm();
        String districtCode = bso151403Result.getDistrictCode();

        //调用产品拆分接口，查询sku接口
        SlPdArtnoBean slpdartnos = BatchCommRestUtils.getSlPdArtNoList(productDetailList, salesPlatform,districtCode);
        List<SlPdArtnoBean> slPdArtnoBeens = new ArrayList<SlPdArtnoBean>();
        if(null == slpdartnos){
            return null;
        }
        if(!CollectionUtils.isEmpty(slpdartnos.getProducts())){
            slPdArtnoBeens = slpdartnos.getProducts();
        }
        //设置订单供货明细表里SKU
        List<BSO151403DetailResult> newProductDetailList = new ArrayList<BSO151403DetailResult>();
        //用于把pdCode，slCode作为key和sku配对
        Map<String,String> map = new HashMap<String,String>();
        for(SlPdArtnoBean slPdArtnoBeen : slPdArtnoBeens){
            String pdCode = slPdArtnoBeen.getClassesCode() + slPdArtnoBeen.getMachiningCode() +slPdArtnoBeen.getBreedCode()
                    + slPdArtnoBeen.getFeatureCode() + slPdArtnoBeen.getWeightCode() + slPdArtnoBeen.getGradeCode();
            String sku = slPdArtnoBeen.getSkuCode();
            String slPdKey = slPdArtnoBeen.getSlCode() + pdCode;
            map.put(slPdKey,sku);
        }

        //设置SKU
        for(BSO151403DetailResult productDetail : productDetailList){
            //根据pdCode和supplierCode设置sku
            String suppPdKey = productDetail.getSupplierCode() + productDetail.getPdCode();
            productDetail.setSku(map.get(suppPdKey));
            newProductDetailList.add(productDetail);
        }
        return newProductDetailList;
    }
}
