package com.msk.ds.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.DsConst;
import com.msk.common.consts.StockConst;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.DsDeliveryStock;
import com.msk.core.entity.DsDeliveryStockDetail;
import com.msk.core.entity.DsPdVirtualStockActual;
import com.msk.ds.bean.*;
import com.msk.ds.consts.BusinessConst;
import com.msk.ds.rest.comm.RestUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.seller.bean.SlPdArtnoBean;
import com.msk.stock.bean.StockRsParam;
import com.msk.stock.bean.StockRsParamList;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ISC1892I1I1Logic.
 *
 * @author xu_wei
 */
@Service
public class ISC1892I1Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISC1892I1Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SqlId.
     *
     * @author pxg
     */
    interface SqlId {
        String FIND_PRODUCT_LIST = "findProductList";

        String MODIFY_ACTUAL = "modifyActual";

        String FIND_DETAIL_LIST = "findDetailList";

        String SAVE_ACTUAL_STOCK = "saveActualStock";

        String EDIT_RECEIVE_NUM = "editReceiveNum";

        String FIND_SSC_DELIVERY = "findSscDeliveryOne";

        String FIND_SSC_DELIVERY_LIST = "findSscDeliveryList";

        String EDIT_SSC_RECEIVE_NUM = "editSscReceiveNum";
    }

    /**
     * 修改实际发货数量
     *
     * @param param param
     * @return
     */
    @Transactional
    public ISC1892I1RsResult modifyStock(RsRequest<ISC1892I1Param> param, StringBuffer outOfSendNumMessage) {
        logger.info("解析美的福回传参数");
        Date currentDate = DateTimeUtil.getCustomerDate();
        ISC1892I1RsResult isc1892I1RsResult = new ISC1892I1RsResult();

        //收货判断条件
        boolean flagLivery = true;
        BaseParam stockBaseParam = new BaseParam();
        stockBaseParam.getFilterMap().put("deliveryStockId", param.getParam().getDeliveryStockId());
        DsDeliveryStock deliveryStockBean = super.findOne(stockBaseParam);
        if (deliveryStockBean == null) {
            throw new BusinessException("发货入库管理信息:" + param.getParam().getDeliveryStockId() + "没有相关入库信息");
        }
        //查询条件
        ISC1892I1RsParam sc1892I1Param = new ISC1892I1RsParam();
        sc1892I1Param.setDistMonth(deliveryStockBean.getDistMonth());
        sc1892I1Param.setLgcsCode(deliveryStockBean.getLgcsCode());
        sc1892I1Param.setAreaCode(deliveryStockBean.getLgcsCode());
        sc1892I1Param.setSuppCode(deliveryStockBean.getSuppCode());
        sc1892I1Param.setHalfCode(deliveryStockBean.getHalfCode());
        sc1892I1Param.setStockMemo(param.getParam().getStockMemo());
        // create by likai on 2016/8/15 判断是否大于10位
        Date formatInputDate = DateTimeUtil.parseDate(param.getParam().getInputDate(), "yyyy/MM/dd HH:mm:ss");
        String parseInputDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(formatInputDate);
        sc1892I1Param.setInputDate(parseInputDate.substring(0, 10));

        sc1892I1Param.setPdStockType(NumberConst.IntDef.INT_THREE);
        sc1892I1Param.setDeliveryStockId(param.getParam().getDeliveryStockId());
        //产品相关的信息
        List<ISC1892I1RsParam> isc1892I1Product = param.getParam().getProductList();
        for (ISC1892I1RsParam isc1892I1Param : isc1892I1Product) {
            //库号
//            String sku = isc1892I1Param.getPdCode();
            /** #1919,create by likai on 2016/8/16 start */
            String sku = isc1892I1Param.getSkuCode();
            /** #1919,create by likai on 2016/8/16 end */
            /** slCodeDis(1-7) */
            String slCodeDis = sku.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_SEVEN);
            /** slPdArtno(8-12) */
            String slPdArtno = sku.substring(NumberConst.IntDef.INT_SEVEN, NumberConst.IntDef.INT_TWELVE);
            //修改后
            String pdCode = null;
            SlPdArtnoBean slPdArtnoBean = new SlPdArtnoBean();
            slPdArtnoBean.setSlPdArtno(slPdArtno);
            slPdArtnoBean.setSlCodeDis(slCodeDis);
            SlPdArtnoBean slPdArtnoBean1 = RestUtil.getSlPdArtNo(slPdArtnoBean);
            if (slPdArtnoBean1 != null
                    && !StringUtil.isEmpty(slPdArtnoBean1.getClassesCode())
                    && !StringUtil.isEmpty(slPdArtnoBean1.getMachiningCode())
                    && !StringUtil.isEmpty(slPdArtnoBean1.getBreedCode())
                    && !StringUtil.isEmpty(slPdArtnoBean1.getFeatureCode())
                    && !StringUtil.isEmpty(slPdArtnoBean1.getWeightCode())
                    && !StringUtil.isEmpty(slPdArtnoBean1.getGradeCode())) {
                pdCode = slPdArtnoBean1.getClassesCode() + slPdArtnoBean1.getMachiningCode() + slPdArtnoBean1.getBreedCode()
                        + slPdArtnoBean1.getFeatureCode() + slPdArtnoBean1.getWeightCode() + slPdArtnoBean1.getGradeCode();
            }
            //实际收货箱数
            int receiveActualNum = isc1892I1Param.getReceiveActualNum().intValue();
            if (logger.isDebugEnabled()) {
                logger.debug(sc1892I1Param.getDeliveryStockId()+"入库单下的sku:"+isc1892I1Param.getSkuCode()
                        + ",实际收货箱数:" + receiveActualNum + ",pdCode:" + pdCode);
            }

            //产品编码
            sc1892I1Param.setPdCode(pdCode);

            //实际表ds_pd_virtual_stock_actual
            List<DsPdVirtualStockActual> productList = this.findList(SqlId.FIND_PRODUCT_LIST, sc1892I1Param);

            //细节表ds_delivery_stock_detail
            sc1892I1Param.getFilterMap().put("sourceFlg",DsConst.DeliverySourceFlg.DELIVERY_FROM_DS);
            List<ISC1892I1Detail> detailtList = this.findList(SqlId.FIND_DETAIL_LIST, sc1892I1Param);
            /** 设置产品信息和包装信息 create by likai 2016/7/28 start */
            setProductInfo(detailtList);
            /** 设置产品信息和包装信息 create by likai 2016/7/28 end */
            logger.info("收货数据处理");
            for (ISC1892I1Detail detailtInfo : detailtList) {
                //实际发货箱数
                int sendActualNum = detailtInfo.getActualDeliveryNum().intValue();
                //实际收货箱数
                long receiveActualTail = receiveActualNum;
                //实际发货箱数=0的场合，跳出本次循环
                if (sendActualNum == 0) {
                    continue;
                }
                //实际发货箱数不能够小于实际收货箱数
                if (sendActualNum < receiveActualNum) {
//                    throw new BusinessException(detailtInfo.getPdCode() + "实际发货箱数不能够小于实际收货箱数");
                    outOfSendNumMessage.append(detailtInfo.getPdCode() + "-实际收货箱数已超过实际发货箱数,");
                } else if (sendActualNum == receiveActualNum) {
                    sendActualNum = 0;
                } else {
                    sendActualNum = sendActualNum - receiveActualNum;
                    flagLivery = false;
                }

                for (DsPdVirtualStockActual stockActualParam : productList) {
                    if (stockActualParam != null) {
                        if (stockActualParam.getPdCode().equals(detailtInfo.getPdCode())) {
                            //实际发货箱数等于实际收货箱数
                            if (receiveActualNum == 0) {
                                break;
                            }
                            //变化值
                            int changeNum = 0;
                            if (stockActualParam.getNewActualNum().intValue() <= receiveActualNum) {
                                receiveActualNum -= stockActualParam.getNewActualNum().intValue();
                                changeNum = -stockActualParam.getNewActualNum().intValue();
                            } else {
                                changeNum = changeNum - receiveActualNum;
                                receiveActualNum = 0;
                            }

                            //修改实际表中库存类型为3的数据到4类型的实际数量
                            //3
                            BaseParam baseParam = new BaseParam();
                            baseParam.getFilterMap().put("newActualNum", changeNum * stockActualParam.getOutNw().doubleValue());
                            baseParam.getFilterMap().put("distMonth", sc1892I1Param.getDistMonth());
                            baseParam.getFilterMap().put("areaCode", sc1892I1Param.getLgcsCode());
                            baseParam.getFilterMap().put("supplierCode", sc1892I1Param.getSuppCode());
                            baseParam.getFilterMap().put("pdStockType", NumberConst.IntDef.INT_THREE);
                            baseParam.getFilterMap().put("halfCode", sc1892I1Param.getHalfCode());
                            baseParam.getFilterMap().put("pdCode", stockActualParam.getPdCode());
                            baseParam.getFilterMap().put("inputDate", stockActualParam.getInputDate());
                            baseParam.setUpdId(param.getLoginId());
                            baseParam.setUpdTime(currentDate);
                            super.modify(SqlId.MODIFY_ACTUAL, baseParam);

                            //4
                            baseParam.getFilterMap().put("newActualNum", -changeNum * stockActualParam.getOutNw().doubleValue());
                            baseParam.getFilterMap().put("pdStockType", NumberConst.IntDef.INT_FOUR);
                            //Add for 2989 at 2016/09/29 by likai Start
                            baseParam.getFilterMap().put("inputDate",null);
                            //Add for 2989 at 2016/09/29 by likai End
                            //ds_pd_virtual_stock_actual
                            int updateNums = super.modify(SqlId.MODIFY_ACTUAL, baseParam);
                            if (updateNums <= 0) {
                                long virtualStockActualId = this.commonLogic.maxId("DS_PD_VIRTUAL_STOCK_ACTUAL", "VIRTUAL_STOCK_ACTUAL_ID");
                                baseParam.getFilterMap().put("virtualStockActualId", String.valueOf(virtualStockActualId));
                                baseParam.getFilterMap().put("pdStockTypeFm", NumberConst.IntDef.INT_THREE);
                                baseParam.getFilterMap().put("pdStockTypeTo", NumberConst.IntDef.INT_FOUR);
                                baseParam.setCrtId(sc1892I1Param.getCrtId());
                                baseParam.setCrtTime(currentDate);
                                //ds_pd_virtual_stock_actual
                                save(SqlId.SAVE_ACTUAL_STOCK, baseParam);
                            }
                        }
                    }
                }
                //发货入库通知单ID
                BaseParam baseParam = new BaseParam();
                baseParam.getFilterMap().put("deliveryStockId", detailtInfo.getDeliveryStockId());
                baseParam.getFilterMap().put("pdCode", detailtInfo.getPdCode());
                baseParam.getFilterMap().put("normsCode", detailtInfo.getNormsCode());
                baseParam.getFilterMap().put("actualReceiveNum", receiveActualTail);
                baseParam.getFilterMap().put("memo", sc1892I1Param.getStockMemo());
                baseParam.getFilterMap().put("sourceFlg", detailtInfo.getSourceFlg());
                baseParam.setCrtId(param.getParam().getCrtId());
                baseParam.setCrtTime(currentDate);
                baseParam.setUpdId(param.getParam().getCrtId());
                baseParam.setUpdTime(currentDate);
                super.modify(SqlId.EDIT_RECEIVE_NUM, baseParam);
                //防止出现continue 赋值不成功,注释
//                detailtInfo.setActualReceiveNum(new BigDecimal(receiveActualTail));
                //防止出现continue 赋值不成功,注释
                //ds_pd_virtual_stock_actual
                //save(SqlId.SAVE_ACTUAL_STOCK, baseParam);
            }
            //修改销售库存
            logger.info("修改销售库存开始");
            List<StockRsParam> stockParamList = new ArrayList<StockRsParam>();
            for (ISC1892I1Detail detail : detailtList) {
                StockRsParam stockRsParam = new StockRsParam();
                /** 库存接口补全参数及添加异常   modify by li_kai1 2016/7/28 start */
                stockRsParam.setLgcsCode(deliveryStockBean.getLgcsCode());
                stockRsParam.setLgcsName(detail.getLgcsName());
                stockRsParam.setWarehouseCode(StockConst.WarehouseCode.SH_CODE);
                stockRsParam.setWarehouseName(StockConst.WarehouseName.SH_NAME);
                stockRsParam.setSupplyPlatform(String.valueOf(NumberConst.IntDef.INT_ONE));
                stockRsParam.setStockType(String.valueOf(StockConst.StockType.NORMAL_STOCK));
                stockRsParam.setSlCode(StockConst.SL_Name.SL_CODE);
                stockRsParam.setSlName(StockConst.SL_Name.SL_NAME);
                stockRsParam.setSupplierCode(deliveryStockBean.getSuppCode());
                stockRsParam.setSupplierName(detail.getSupplierName());
                stockRsParam.setClassesCode(detail.getClassesCode());
                stockRsParam.setClassesName(detail.getClassesName());
                stockRsParam.setBreedCode(detail.getBreedCode());
                stockRsParam.setBreedName(detail.getBreedName());
                stockRsParam.setFeatureCode(detail.getFeatureCode());
                stockRsParam.setFeatureName(detail.getFeatureName());
                stockRsParam.setNormsCode(detail.getNormsCode());
                stockRsParam.setNormsName(detail.getNormsName());
                stockRsParam.setUnit("箱");
                stockRsParam.setPackingVolume(StringUtil.toString(detail.getVolume()));
                stockRsParam.setWeight(StringUtil.toString(detail.getNetWeight()));
                stockRsParam.setVolume(StringUtil.toString(detail.getVolume()));
                stockRsParam.setPdLevel(detail.getGradeCode());
                stockRsParam.setPdName(detail.getPdName());
                stockRsParam.setPdCode(detail.getPdCode());
                //防止出现上面continue 赋值不成功,改为和原代码一致
//                stockRsParam.setStockNum(detail.getActualReceiveNum());
                stockRsParam.setStockNum(isc1892I1Param.getReceiveActualNum());
                //防止出现上面continue 赋值不成功,改为和原代码一致
                stockRsParam.setFlowId(String.valueOf(deliveryStockBean.getDeliveryStockId()));

                // 库存接口新增必填参数enabledStockQty,默认设置为0
                stockRsParam.setEnabledStockQty(BigDecimal.ZERO);

                stockParamList.add(stockRsParam);
            }

            StockRsParamList stockRsParamList = new StockRsParamList();
            stockRsParamList.setPdList(stockParamList);

            boolean stockResult = RestUtil.saveStockOfSupplierList(stockRsParamList);
            if (!stockResult) {
                throw new BusinessException("修改销售库存失败!");
            }
            logger.info("修改销售库存结束");
            /** 库存接口补全参数及添加异常   modify by li_kai1 2016/7/28 end */
        }

        //已收货
        if (flagLivery == true) {
            deliveryStockBean.setDeliveryStockStatus(BusinessConst.DeliveryStockStatus.Delivery);
        } else {
            //部分收货
            deliveryStockBean.setDeliveryStockStatus(BusinessConst.DeliveryStockStatus.PartialDelivery);
        }
        deliveryStockBean.setStockMemo(sc1892I1Param.getStockMemo());
        deliveryStockBean.setUpdId(sc1892I1Param.getCrtId());
        deliveryStockBean.setUpdTime(currentDate);
        deliveryStockBean.setSourceFlg(DsConst.DeliverySourceFlg.DELIVERY_FROM_DS);
        /** #1779 modify by likai on 2016/8/11 start */
        Date receiveTime = DateTimeUtil.parseDate(param.getParam().getInputDate(), "yyyy/MM/dd HH:mm:ss");
        if (DateTimeUtil.isValidDate(receiveTime)) {
            deliveryStockBean.setDeliveryReceiveStockTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(receiveTime));
        }
        /** #1779 modify by likai on 2016/8/11 end */
        super.modify(deliveryStockBean);

        isc1892I1RsResult.setDeliveryStockStatus(deliveryStockBean.getDeliveryStockStatus());
        return isc1892I1RsResult;
    }

    /**
     * 获取产品相关信息
     *
     * @param list 数据列表
     */
    private void setProductInfo(List<ISC1892I1Detail> list) {
        logger.info("设置产品参数");
        for (ISC1892I1Detail dsDeliveryStockDetail : list) {
            //根据产品Code获取产品信息
            PDInfoParam pdInfoParam = new PDInfoParam();
            pdInfoParam.setPdCode(dsDeliveryStockDetail.getPdCode());
            pdInfoParam.setIsPackage("YES");
            PDInfoResult product = RestUtil.getProductInfo(pdInfoParam);
            dsDeliveryStockDetail.setPdName(product.getPdName());
            dsDeliveryStockDetail.setClassesCode(product.getClassesCode());
            dsDeliveryStockDetail.setClassesName(product.getClassesName());
            dsDeliveryStockDetail.setBreedCode(product.getBreedCode());
            dsDeliveryStockDetail.setBreedName(product.getBreedName());
            dsDeliveryStockDetail.setFeatureCode(product.getFeatureCode());
            dsDeliveryStockDetail.setFeatureName(product.getFeatureName());
            dsDeliveryStockDetail.setGradeCode(product.getGradeCode());
            dsDeliveryStockDetail.setGradeName(product.getGradeName());
            dsDeliveryStockDetail.setNormsCode(product.getNormsCode());
            dsDeliveryStockDetail.setNormsName(product.getNormsName());
            dsDeliveryStockDetail.setVolume(product.getNormsVolume());
            dsDeliveryStockDetail.setNetWeight(product.getWeightVal());
            /*sc182102Param.setProductName(product.getClassesName());
            sc182102Param.setProductTypeName(product.getBreedName());
            sc182102Param.setProductFeature(product.getFeatureName());
            sc182102Param.setProductLevel(product.getGradeName());*/

        }
    }

    /**
     * 判断发货入库单来源
     */
    public DsDeliveryStock judgeDelivery(ISC1892I1Param param) {
        logger.info("判断发货入库单来源");
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("deliveryStockId", param.getDeliveryStockId());
        DsDeliveryStock dsDeliveryStock = this.findOne(SqlId.FIND_SSC_DELIVERY, baseParam);

        if (null != dsDeliveryStock && DsConst.DeliverySourceFlg.DELIVERY_FROM_SSC.equals(dsDeliveryStock.getSourceFlg())) {
            return dsDeliveryStock;
        } else {
            return null;
        }
    }

    /**
     * 设置回传参数
     */
    public void setSscParam(ISC1892I1Param param) {
        logger.info("设置回传参数(格式化日期/sku>转>pdCode)");
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("deliveryStockId", param.getDeliveryStockId());
        //处理日期格式
        Date inputTime = DateTimeUtil.parseDate(param.getInputDate(), "yyyy/MM/dd HH:mm:ss");
        if (DateTimeUtil.isValidDate(inputTime)) {
            param.setInputDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(inputTime));
        }
        List<DsDeliveryStockDetail> detailList = this.findList(SqlId.FIND_SSC_DELIVERY_LIST, baseParam);
        Map<String, String> detailMap = new HashMap<String, String>();
        if (CollectionUtils.isNotEmpty(detailList)) {
            for (DsDeliveryStockDetail detail : detailList) {

                detailMap.put(detail.getSku(), detail.getPdCode());
            }
        }
        for (ISC1892I1RsParam isc1892I1RsParam : param.getProductList()) {
            isc1892I1RsParam.setPdCode(detailMap.get(isc1892I1RsParam.getSkuCode()));
        }

    }

    /**
     * 扫码处理:1,本地DB处理;2,调采供链接口处理
     */
    @Transactional
    public ISC1892I1RsResult modifySscDelivery (ISC1892I1Param isc1892I1Param) {
        logger.info("平台供应链本地处理采供链存储的数据开始");
        Date currentDate = DateTimeUtil.getCustomerDate();
        BaseParam stockBaseParam = new BaseParam();
        stockBaseParam.getFilterMap().put("deliveryStockId", isc1892I1Param.getDeliveryStockId());
        DsDeliveryStock deliveryStockBean = super.findOne(stockBaseParam);
        if (deliveryStockBean == null) {
            throw new BusinessException("采供链发货入库管理信息:" + isc1892I1Param.getFilterMap().get("sscDeliveryStockId")
                    + "没有相关入库信息");
        }
        deliveryStockBean.setDeliveryStockStatus(BusinessConst.DeliveryStockStatus.Delivery);
        deliveryStockBean.setStockMemo(isc1892I1Param.getStockMemo());
//        deliveryStockBean.setUpdId(isc1892I1Param.getCrtId());
        deliveryStockBean.setUpdTime(currentDate);
        deliveryStockBean.setSourceFlg(DsConst.DeliverySourceFlg.DELIVERY_FROM_SSC);
        deliveryStockBean.setDeliveryReceiveStockTime(isc1892I1Param.getInputDate());
        super.modify(deliveryStockBean);
        //修改发货入库单管理表
        List<ISC1892I1RsParam> productList = isc1892I1Param.getProductList();
        if (CollectionUtils.isNotEmpty(productList)) {
            for (ISC1892I1RsParam product : productList) {
                BaseParam baseParam = new BaseParam();
                baseParam.getFilterMap().put("deliveryStockId", isc1892I1Param.getDeliveryStockId());
                baseParam.getFilterMap().put("memo", isc1892I1Param.getStockMemo());
                baseParam.getFilterMap().put("actualReceiveNum", product.getReceiveActualNum());
                baseParam.getFilterMap().put("pdCode", product.getPdCode());
                baseParam.getFilterMap().put("updId", isc1892I1Param.getCrtId());
                baseParam.getFilterMap().put("updTime", currentDate);
                baseParam.getFilterMap().put("sourceFlg", DsConst.DeliverySourceFlg.DELIVERY_FROM_SSC);
                super.modify(SqlId.EDIT_SSC_RECEIVE_NUM, baseParam);
            }
        }
        logger.info("sscDeliveryStockId>替换为>deliveryStockId传给采供链");
        Long sscDeliveryStockId = StringUtil.toLong(isc1892I1Param.getFilterMap().get("sscDeliveryStockId"));
        isc1892I1Param.setDeliveryStockId(sscDeliveryStockId);
        logger.info("平台供应链本地处理采供链存储的数据结束,开始调用采供链接口处理业务!");
        return RestUtil.saveSscIntoBasic(isc1892I1Param);
    }

}
