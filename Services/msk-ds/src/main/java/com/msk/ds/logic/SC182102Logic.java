package com.msk.ds.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.DsConst;
import com.msk.common.consts.StockConst;
import com.msk.common.logic.CommonLogic;
import com.msk.common.xml.JaxbXmlWrite;
import com.msk.ds.bean.*;
import com.msk.ds.consts.BusinessConst;
import com.msk.ds.rest.comm.RestUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import com.msk.seller.bean.SlPdArtnoBean;
import com.msk.stock.bean.StockRsParam;
import com.msk.stock.bean.StockRsParamList;
import com.msk.utils.WmsFtpUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * yang_yang
 */
@Service
public class SC182102Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC183102Logic.class);

    @Autowired
    private DistSuppChainLogic distSuppChainLogic;

    @Autowired
    private CommonLogic commonLogic;

    /*@Autowired
    private StockLogic stockLogic;*/

    /**
     * SQL Map 中SQL ID定义
     *
     * @author pxg
     */
    private interface SqlId {
        String SQL_ID_FIND_DELIVERYNUMS = "getDeliveryNum";

        String FIND_PRODUCT_LIST = "findProductList";

        String FIND_PRODUCT_SUM_LIST = "findProductSumList";

        String FIND_DETAIL_LIST = "findDetailList";

        String MODIFY_ACTUAL = "modifyActual";

        String SAVE_STOCK_DETAIL = "saveStockDetail";

        String SAVE_ACTUAL_STOCK = "saveActualStock";

        String EDIT_RECEIVE_NUM = "editReceiveNum";

        String COUNT_DETAIL_CURRENT_TOTAL = "countDetailListTotal";

        String COUNT_ACTUAL_CURRENT_TOTAL = "countActualListTotal";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 检索发货入库通知明细List
     *
     * @param sc181102Param 入力条件
     * @param isEdit        True：编辑  False：新增
     * @return 入库明细
     */
    @Transactional(readOnly = true)
    public PageResult<SC182102Param> findPageList(SC181102Param sc181102Param, boolean isEdit) {
        PageResult<SC182102Param> pageResultList = new PageResult<SC182102Param>();

        //添加从实际表取值，修改从明细表取值
        String sql = SqlId.FIND_PRODUCT_SUM_LIST;
        String countSql = SqlId.COUNT_ACTUAL_CURRENT_TOTAL;
        if (isEdit) {
            sql = SqlId.FIND_DETAIL_LIST;
            countSql = SqlId.COUNT_DETAIL_CURRENT_TOTAL;
            if (logger.isDebugEnabled()) {
                logger.debug("入库通知单详细查询,参数deliveryStockId:"+sc181102Param.getFilterMap().get("deliveryStockId"));
            }
        }

        List<SC182102Param> productList = this.findList(sql, sc181102Param);

        setProductInfo(productList);

        pageResultList.setData(productList);
        /**start in 2016/8/16 by zhang_jiaxing*/
        if (pageResultList.getData().size() > 0) {
            SC182102Param current = findOne(countSql, sc181102Param);
            if (null != current) {
                List<SC182102Param> list = pageResultList.getData();
                list.get(0).setCurrentSendPlanQty(current.getCurrentSendPlanQty());
                list.get(0).setCurrentSendActualQty(current.getCurrentSendActualQty());
                list.get(0).setCurrentRecriveQty(current.getCurrentRecriveQty());
                list.get(0).setCurrentSendPlanNum(current.getCurrentSendPlanNum());
                list.get(0).setCurrentSendActualNum(current.getCurrentSendActualNum());
                list.get(0).setCurrentReceiveNum(current.getCurrentReceiveNum());
                list.get(0).setCurrentDifferQty(current.getCurrentDifferQty());
                list.get(0).setCurrentDifferNum(current.getCurrentDifferNum());
            }

        }
        /**end in 2016/8/16 by zhang_jiaxing*/
        return pageResultList;
    }

    /**
     * 发货
     *
     * @param sc182101Bean
     * @return
     */
    @Transactional
    public int saveStock(SC182101Bean sc182101Bean) {
        logger.info("发货操作开始");
        Date currentDate = DateTimeUtil.getCustomerDate();

        long deliveryStockId = this.commonLogic.maxId("DS_DELIVERY_STOCK", "DELIVERY_STOCK_ID");
        sc182101Bean.setDeliveryStockId(deliveryStockId);
        //发货后状态为"待收货"
        sc182101Bean.setDeliveryStockStatus(BusinessConst.DeliveryStockStatus.NoDelivery);
        sc182101Bean.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ZERO));
        sc182101Bean.setCrtTime(currentDate);
        sc182101Bean.setCrtId(sc182101Bean.getCrtId());
        sc182101Bean.setUpdTime(currentDate);
        sc182101Bean.setUpdId(sc182101Bean.getCrtId());

        this.save(sc182101Bean);

        //查询条件
        SC181102Param sc181102Param = new SC181102Param();

        sc181102Param.setDistMonth(sc182101Bean.getDistMonth());
        sc181102Param.setAreaCode(sc182101Bean.getLgcsCode());
        sc181102Param.setSupplierCode(sc182101Bean.getSuppCode());
        sc181102Param.setHalfCode(sc182101Bean.getHalfCode());
        //实际发货箱数
        String[] actualNums = sc182101Bean.getActualNums();
        //Add for LoggerPrint at 2016/09/23 by likai Start
        StringBuffer loggerContent = new StringBuffer("实际发货箱数:");
        for (String actualNumInLogger : actualNums) {
            loggerContent.append(actualNumInLogger + ",");
        }
        if (logger.isDebugEnabled()){
            logger.debug(loggerContent.toString());
        }
        //Add for LoggerPrint at 2016/09/23 by likai End

        //明细表
        List<SC182102Param> productSumList = this.findList(SqlId.FIND_PRODUCT_SUM_LIST, sc181102Param);

        setProductInfo(productSumList);

        List<SC182102Bean> detailList = new ArrayList<SC182102Bean>();
        for (int i = 0; i < productSumList.size(); i++) {

            if (null != StringUtil.toBigDecimal(actualNums[i]) && StringUtil.toBigDecimal(actualNums[i]).compareTo(new BigDecimal(0))!=0) {
                SC182102Param sc182102Param = productSumList.get(i);

                SC182102Bean sc182102Bean = new SC182102Bean();

                sc182102Bean.setDeliveryStockId(deliveryStockId);
                sc182102Bean.setPdCode(sc182102Param.getProductCode());
                sc182102Bean.setNormsCode(sc182102Param.getPackageCode());
                sc182102Bean.setApplyDeliveryNum(BigDecimal.ZERO);
                sc182102Bean.setConfirmDeliveryNum(BigDecimal.ZERO);
                sc182102Bean.setPlanDeliveryNum(sc182102Param.getSendNum());
                sc182102Bean.setActualDeliveryNum(new BigDecimal(actualNums[i]));
                sc182102Bean.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ZERO));
                sc182102Bean.setCrtId(sc182101Bean.getCrtId());
                sc182102Bean.setCrtTime(currentDate);
                sc182102Bean.setUpdId(sc182101Bean.getCrtId());
                sc182102Bean.setUpdTime(currentDate);
                sc182102Bean.setPdOutNw(sc182102Param.getNetWeight());
                detailList.add(sc182102Bean);
                //明细表插入
                this.save(SqlId.SAVE_STOCK_DETAIL, sc182102Bean);
            }

        }
        //分配库存（从2库移到3库）
        allocateActualStockNums(sc181102Param, sc182101Bean);

        //create Xml
        createXml(sc182101Bean, detailList);
        logger.info("发货操作结束");
        return NumberConst.IntDef.INT_ONE;
    }

    /**
     * 生成美迪福接口 XML文件
     *
     * @param bean
     * @param detailList
     */
    private void createXml(SC182101Bean bean, List<SC182102Bean> detailList) {
        logger.info("生成XML文件开始");
        Date currentDate = DateTimeUtil.getCustomerDate();
//        String fileName = "SC182102Template.ftl";
//        XmlWrite<SC182102XmlParam> xml = new XmlWrite<SC182102XmlParam>("/template", fileName);

        SC182102XmlParam param = new SC182102XmlParam();

        param.setReceipt(StringUtil.toSafeString(bean.getDeliveryStockId()));
        param.setNotes(StringUtil.toSafeString(bean.getDeliveryMemo()));
        param.setScheduledDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(currentDate));
        param.setStartReceiptDate(StringUtil.toSafeString(bean.getDeliveryStockTimeReq()));
        List<SC182102XmlDetailParam> xmlDetailList = new ArrayList<SC182102XmlDetailParam>();
        param.setDetailList(xmlDetailList);
        //入库单行号
        Integer index = NumberConst.IntDef.INT_ONE;

        Map<String, Object> productInfoMap = getPdDataMap(detailList, bean.getSuppCode());

        Map<String, PDInfoResult> pdCodeMap = (Map<String, PDInfoResult>) productInfoMap.get("pdCodeMap");
        Map<String, SlPdArtnoBean> slCodeMap = (Map<String, SlPdArtnoBean>) productInfoMap.get("slCodeMap");
        for (SC182102Bean detail : detailList) {
            SC182102XmlDetailParam xmlDetail = new SC182102XmlDetailParam();
            xmlDetail.setReceiptLine(index.toString());
            /** #1919 入库明细ID modify by likai on 2016/8/15 start */
//            xmlDetail.setReceiptLine(StringUtil.toSafeString(detail.getDeliveryStockId()));
            /** #1919 入库明细ID modify by likai on 2016/8/15 end */
            xmlDetail.setCompany(StringUtil.toSafeString(bean.getSuppCode()));
            xmlDetail.setArea(StringUtil.toSafeString(bean.getLgcsCode()));
            xmlDetail.setCompanyName(StringUtil.toSafeString(bean.getSuppName()));

            //根据pdCode获取产品信息
            PDInfoResult product = pdCodeMap.get(detail.getPdCode());
            if (null == product) {
                product = new PDInfoResult();
            }
            String key = product.getClassesCode() + product.getMachiningCode() + product.getBreedCode()
                    + product.getFeatureCode() + product.getWeightCode() + product.getGradeCode();
            SlPdArtnoBean slPdArtno = slCodeMap.get(key);
            if (null == slPdArtno) {
                slPdArtno = new SlPdArtnoBean();
            }

            if (null != slPdArtno && !StringUtil.isNullOrEmpty(slPdArtno.getSlCodeDis()) && !StringUtil.isNullOrEmpty(slPdArtno.getSlPdArtno())) {
                xmlDetail.setSku(slPdArtno.getSlCodeDis() + slPdArtno.getSlPdArtno());
            } else {
                xmlDetail.setSku("");
                throw new BusinessException("产品对应的库号不存在!");
            }

            //填充产品包装信息
//            setPdNormsStd(product);
            //商品名称
            StringBuffer productName = new StringBuffer();
            if (!StringUtil.isNullOrEmpty(product.getClassesName())) {
                productName.append(product.getClassesName());
            }
            if (!StringUtil.isNullOrEmpty(product.getMachiningName())) {
                productName.append("-").append(product.getMachiningName());
            }
            if (!StringUtil.isNullOrEmpty(product.getBreedName())) {
                productName.append("-").append(product.getBreedName());
            }
            if (!StringUtil.isNullOrEmpty(product.getFeatureName())) {
                productName.append("-").append(product.getFeatureName());
            }
            if (!StringUtil.isNullOrEmpty(product.getWeightName())) {
                productName.append("-").append(product.getWeightName());
            }
            if (!StringUtil.isNullOrEmpty(product.getGradeName())) {
                productName.append("-").append(product.getGradeName());
            }
//            xmlDetail.setSkuDesc(String.valueOf(productName));

            xmlDetail.setSkuDesc(StringUtil.toSafeString(productName.toString()));
            xmlDetail.setSkuGroup(StringUtil.toSafeString(product.getClassesCode()));
            xmlDetail.setGroupName(StringUtil.toSafeString(product.getClassesName()));
            xmlDetail.setUom("CASE");
            xmlDetail.setGrossWeight(StringUtil.toSafeString(product.getGrossweightOut()));
            xmlDetail.setNetWeight(StringUtil.toSafeString(product.getNetweightOut()));
            xmlDetail.setLength(StringUtil.toSafeString(product.getNormsLength()));
            xmlDetail.setWidth(StringUtil.toSafeString(product.getNormsWidth()));
            xmlDetail.setHeight(StringUtil.toSafeString(product.getNormsHeight()));
            xmlDetail.setVolume(StringUtil.toSafeString(product.getNormsVolume()));
            /**新增产品等级   2016-6-8,应上海需求*/
            xmlDetail.setGrade(StringUtil.toSafeString(product.getGradeName()));
            /**新增产品规格   2016-6-8,应上海需求*/
            xmlDetail.setFeatureName(StringUtil.toSafeString(product.getFeatureName()));

            xmlDetail.setQtyExpected(StringUtil.toSafeString(detail.getActualDeliveryNum()));
            xmlDetail.setQtyOriginal(StringUtil.toSafeString(detail.getPlanDeliveryNum()));
            /**
             * 设置默认值
             */
            xmlDetail.setConsignee("0000000");
            xmlDetail.setName("神农客");
            xmlDetail.setCompanytype("SUPPLY");
            xmlDetail.setInventorystatus("AVAILABLE");
            /** #1919  add by likai on 2016/8/15 start */
            //原产地+动检证
            xmlDetail.setCoo("");
            /** #1919  add by likai on 2016/8/15 end */
            index++;
            xmlDetailList.add(xmlDetail);
        }
//        param.setDetailList(xmlDetailList);
        //输出名
        String outputName = "SC182102" + DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYMMDD_HHMMSS, currentDate) + ".xml";

        List<SC182102XmlParam> xmlData = new ArrayList<SC182102XmlParam>();
        xmlData.add(param);

        List182102Template list182102Template = new List182102Template();
        list182102Template.setData(xmlData);

        JaxbXmlWrite<SC182102XmlParam> xmlWrite = new JaxbXmlWrite<>(list182102Template);
        InputStream inputStream = xmlWrite.createListTemplateXml(List182102Template.class);

        //上传生成的xml
        boolean result = WmsFtpUtils.uploadXml("/", outputName, inputStream);
        if (logger.isDebugEnabled()) {
            if (result) {
                logger.debug(outputName + "文件上传成功");
            } else {
                logger.debug(outputName + "文件上传失败");
                throw new BusinessException("文件上传失败");
            }
        }
        //文件服务器上传方式
//        File xmlFile = new File(uploadFile);
//        Map<String,File> fileMap = new HashMap<>();
//        fileMap.put("xmlFile",xmlFile);
//        Map<String,String> result = FileUploadUtil.uploadFiles(fileMap);
//        if (MapUtils.isEmpty(result)) {
//            throw new BusinessException("上传文件到服务器失败！");
//        }
    }

    /**
     * 分配库存（从2库移到3库）
     *
     * @param sc181102Param
     * @param sc182101Bean
     */
    @Transactional
    private void allocateActualStockNums(SC181102Param sc181102Param, SC182101Bean sc182101Bean) {
        logger.info("分配库存开始");
        Date currentDate = DateTimeUtil.getCustomerDate();

        //产品编码(2:待运库产品)
        sc181102Param.getFilterMap().put("pdStockType", String.valueOf(NumberConst.IntDef.INT_TWO));
        //实际表
        List<SC182102Param> productList = this.findList(SqlId.FIND_PRODUCT_LIST, sc181102Param);

        setProductInfo(productList);

        String[] actualNums = sc182101Bean.getActualNums();
        String[] productCodes = sc182101Bean.getProductCodes();

        //分配
        for (int i = 0; i < actualNums.length; i++) {
            BigDecimal actualNum = StringUtil.toBigDecimal(actualNums[i]);

            if (actualNum.compareTo(new BigDecimal(0))!=0) {
                String productCode = productCodes[i];
                BigDecimal actualWeight = new BigDecimal(1);

                for (int j = 0; j < productList.size(); j++) {
                    if (actualWeight.compareTo(new BigDecimal(0))==0) {
                        break;
                    }

                    SC182102Param sc182102Param = productList.get(j);
                    if (sc182102Param != null) {
                        if (sc182102Param.getProductCode().equals(productCode)) {
                            //变化值
                            BigDecimal newActualNum =new  BigDecimal(0);
                            if (actualWeight.compareTo(new BigDecimal(1)) ==0) {
                                actualWeight = sc182102Param.getNetWeight().multiply(actualNum);
                            }
                            if (sc182102Param.getSendNum().compareTo(actualWeight) !=1 ) {
                                actualWeight =actualWeight.subtract(sc182102Param.getSendNum()) ;
                                newActualNum = sc182102Param.getSendNum().multiply(new BigDecimal(-1));
                            } else {
                                newActualNum = actualWeight.multiply(new BigDecimal(-1));
                                actualWeight =new BigDecimal(0);
                            }

                            //修改实际表中库存类型为2的数据到3类型的实际数量
                            //2:待运库产品
                            BaseParam baseParam = new BaseParam();
                            baseParam.getFilterMap().put("distMonth", sc182101Bean.getDistMonth());
                            baseParam.getFilterMap().put("areaCode", sc182101Bean.getLgcsCode());
                            baseParam.getFilterMap().put("supplierCode", sc182101Bean.getSuppCode());
                            baseParam.getFilterMap().put("halfCode", sc182101Bean.getHalfCode());
                            baseParam.getFilterMap().put("pdCode", sc182102Param.getProductCode());
                            baseParam.getFilterMap().put("inputDate", sc182102Param.getInputDate());
                            baseParam.setUpdId(sc182101Bean.getCrtId());
                            baseParam.setUpdTime(currentDate);
                            baseParam.getFilterMap().put("newActualNum", newActualNum);
                            baseParam.getFilterMap().put("pdStockType", NumberConst.IntDef.INT_TWO);
                            super.modify(SqlId.MODIFY_ACTUAL, baseParam);

                            //3:在途产品(插入)
                            baseParam.getFilterMap().put("newActualNum", newActualNum.multiply(new BigDecimal(-1)));
                            baseParam.getFilterMap().put("pdStockType", NumberConst.IntDef.INT_THREE);
                            //Add for 2989 at 2016/09/29 by likai Start
                            baseParam.getFilterMap().put("inputDate",null);
                            //Add for 2989 at 2016/09/29 by likai End
                            int updateNums = super.modify(SqlId.MODIFY_ACTUAL, baseParam);
                            if (updateNums <= 0) {
                                long virtualStockActualId = this.commonLogic.maxId("DS_PD_VIRTUAL_STOCK_ACTUAL", "VIRTUAL_STOCK_ACTUAL_ID");
                                baseParam.getFilterMap().put("virtualStockActualId", String.valueOf(virtualStockActualId));
                                baseParam.getFilterMap().put("pdStockTypeFm", NumberConst.IntDef.INT_TWO);
                                baseParam.getFilterMap().put("pdStockTypeTo", NumberConst.IntDef.INT_THREE);
                                baseParam.setCrtId(sc182101Bean.getCrtId());
                                baseParam.setCrtTime(currentDate);
                                save(SqlId.SAVE_ACTUAL_STOCK, baseParam);
                            }
                        }
                    }
                }
            }

        }
        logger.info("分配库存结束");
    }

    /**
     * 修改实际数量
     *
     * @param
     * @return 结果
     */
    @Transactional
    public int modifyStock(SC182101Bean sc182101Bean) {
        logger.info("收货操作开始");
        Date currentDate = DateTimeUtil.getCustomerDate();

        //查询条件
        SC181102Param sc181102Param = new SC181102Param();
        sc181102Param.setDistMonth(sc182101Bean.getDistMonth());
        sc181102Param.setAreaCode(sc182101Bean.getLgcsCode());
        sc181102Param.setSupplierCode(sc182101Bean.getSuppCode());
        sc181102Param.setHalfCode(sc182101Bean.getHalfCode());
        sc181102Param.getFilterMap().put("pdStockType", String.valueOf(NumberConst.IntDef.INT_THREE));
        sc181102Param.getFilterMap().put("deliveryStockId", sc182101Bean.getDeliveryStockId());

        //实际表
        List<SC182102Param> productList = this.findList(SqlId.FIND_PRODUCT_LIST, sc181102Param);
        setProductInfo(productList);
        //细节表
        sc181102Param.getFilterMap().put("sourceFlg", DsConst.DeliverySourceFlg.DELIVERY_FROM_DS);
        List<SC182102Param> detailtList = this.findList(SqlId.FIND_DETAIL_LIST, sc181102Param);
        setProductInfo(detailtList);

        BigDecimal[] receiveActualNums = sc182101Bean.getReceiveActualNums();
        //Add fro LogPrint at 2016/09/22 by likai Start
        StringBuffer loggerInfo = new StringBuffer("实际收货箱数Parm:");
        for (BigDecimal receiveNumForLog : receiveActualNums) {
            loggerInfo.append(receiveNumForLog + ",");
        }
        if (logger.isDebugEnabled()) {
            logger.debug(loggerInfo.toString());
        }
        //Add fro LogPrint at 2016/09/22 by likai End
        //收货判断条件
        boolean flagLivery = true;
        for (int i = 0; i < detailtList.size(); i++) {

            SC182102Param detailInfo = detailtList.get(i);
            //实际发货数量
            BigDecimal actualNum = detailInfo.getSendActualNum();
            //实际收货箱数
            BigDecimal receiveActualTail = receiveActualNums[i];
            //Add for 3493 at 2016/26 by likai Start
            detailInfo.setReceiveNum(receiveActualTail);
            //Add for 3493 at 2016/26 by likai End
            //Modify for #2746 at 2016/09/20 by likai Start
//            if (actualNum != receiveActualTail) {
//                flagLivery = false;
//            }
            if (actualNum.compareTo(receiveActualTail) ==1) {
                flagLivery = false;
            }
            //Modify for #2746 at 2016/09/20 by likai End
            BigDecimal actualWeight = new BigDecimal(1);

            for (int j = 0; j < productList.size(); j++) {

                SC182102Param sc182102Param = productList.get(j);
                if (sc182102Param != null) {

                    if (sc182102Param.getProductCode().equals(detailInfo.getProductCode())) {

                        //变化值
                        BigDecimal newActualNum = new BigDecimal(NumberConst.IntDef.INT_ZERO);
                        if (actualWeight.compareTo(new BigDecimal(NumberConst.IntDef.INT_ONE))==0) {
                            actualWeight = sc182102Param.getNetWeight().multiply(receiveActualTail);
                        }
                        if (sc182102Param.getSendNum().compareTo(actualWeight) != 1) {
                            actualWeight = actualWeight.subtract(sc182102Param.getSendNum());
                            newActualNum = sc182102Param.getSendNum().multiply(new BigDecimal(-1));
                        } else {
                            newActualNum = actualWeight.multiply(new BigDecimal(-1));
                            actualWeight = new BigDecimal(NumberConst.IntDef.INT_ZERO);
                        }

                        //修改实际表中库存类型为3的数据到4类型的实际数量
                        //3
                        BaseParam baseParam = new BaseParam();
                        baseParam.getFilterMap().put("newActualNum", newActualNum);
                        baseParam.getFilterMap().put("distMonth", sc182101Bean.getDistMonth());
                        baseParam.getFilterMap().put("areaCode", sc182101Bean.getLgcsCode());
                        baseParam.getFilterMap().put("supplierCode", sc182101Bean.getSuppCode());
                        baseParam.getFilterMap().put("pdStockType", NumberConst.IntDef.INT_THREE);
                        baseParam.getFilterMap().put("halfCode", sc182101Bean.getHalfCode());
                        baseParam.getFilterMap().put("pdCode", sc182102Param.getProductCode());
                        baseParam.getFilterMap().put("inputDate", sc182102Param.getInputDate());

                        super.modify(SqlId.MODIFY_ACTUAL, baseParam);

                        //4
                        baseParam.getFilterMap().put("newActualNum", newActualNum.multiply(new BigDecimal(-1)));
                        baseParam.getFilterMap().put("pdStockType", NumberConst.IntDef.INT_FOUR);
                        //Add for 2989 at 2016/09/29 by likai Start
                        baseParam.getFilterMap().put("inputDate",null);
                        //Add for 2989 at 2016/09/29 by likai End

                        int updateNums = super.modify(SqlId.MODIFY_ACTUAL, baseParam);
                        if (updateNums <= 0) {
                            long virtualStockActualId = this.commonLogic.maxId("DS_PD_VIRTUAL_STOCK_ACTUAL", "VIRTUAL_STOCK_ACTUAL_ID");
                            baseParam.getFilterMap().put("virtualStockActualId", String.valueOf(virtualStockActualId));
                            baseParam.getFilterMap().put("pdStockTypeFm", NumberConst.IntDef.INT_THREE);
                            baseParam.getFilterMap().put("pdStockTypeTo", NumberConst.IntDef.INT_FOUR);
                            baseParam.setCrtId(sc182101Bean.getCrtId());
                            baseParam.setCrtTime(currentDate);
                            save(SqlId.SAVE_ACTUAL_STOCK, baseParam);
                        }
                    }
                }

            }

            BaseParam baseParam = new BaseParam();
            baseParam.getFilterMap().put("deliveryStockId", sc182101Bean.getDeliveryStockId());
            baseParam.getFilterMap().put("pdCode", detailInfo.getProductCode());
            baseParam.getFilterMap().put("normsCode", detailInfo.getPackageCode());
            baseParam.getFilterMap().put("actualDeliveryNum", detailInfo.getSendActualNum());
            baseParam.getFilterMap().put("actualReceiveNum", receiveActualTail);
            baseParam.getFilterMap().put("memo", sc182101Bean.getStockMemo());
            baseParam.setCrtId(sc182101Bean.getCrtId());
            baseParam.setCrtTime(currentDate);
            baseParam.setUpdId(sc182101Bean.getCrtId());
            baseParam.setUpdTime(currentDate);
            super.modify(SqlId.EDIT_RECEIVE_NUM, baseParam);
        }
        logger.info("修改销售库存开始");
        //修改销售库存
        List<StockRsParam> stockParamList = new ArrayList<StockRsParam>();
        for (SC182102Param detail : detailtList) {
            StockRsParam param = new StockRsParam();
            /** 库存接口补全参数   modify by li_kai1 2016/7/28 start */
            param.setLgcsCode(sc182101Bean.getLgcsCode());

            param.setLgcsName(detail.getLgcsName());

            param.setWarehouseCode(StockConst.WarehouseCode.SH_CODE);
            param.setWarehouseName(StockConst.WarehouseName.SH_NAME);
            param.setSupplyPlatform(String.valueOf(NumberConst.IntDef.INT_ONE));
            param.setStockType(String.valueOf(StockConst.StockType.NORMAL_STOCK));
            param.setSlCode(StockConst.SL_Name.SL_CODE);
            param.setSlName(StockConst.SL_Name.SL_NAME);
            param.setSupplierCode(sc182101Bean.getSuppCode());
            param.setSupplierName(detail.getSupplierName());
            param.setClassesCode(detail.getClassesCode());
            param.setClassesName(detail.getClassesName());
            param.setBreedCode(detail.getBreedCode());
            param.setBreedName(detail.getProductTypeName());
            param.setFeatureCode(detail.getFeatureCode());
            param.setFeatureName(detail.getProductFeature());
            param.setNormsCode(detail.getPackageCode());
            param.setNormsName(detail.getNormsName());
            param.setUnit("箱");
            param.setPackingVolume(StringUtil.toString(detail.getVolume()));
            param.setWeight(StringUtil.toString(detail.getNetWeight()));
            param.setVolume(StringUtil.toString(detail.getVolume()));
            param.setPdLevel(detail.getGradeCode());
            param.setPdName(detail.getProductName());
            param.setPdCode(detail.getProductCode());
            //Modify for 3493 at 2016/26 by likai Start
//            param.setStockNum(detail.getSendActualNum());
            param.setStockNum(detail.getReceiveNum());
            //Modify for 3493 at 2016/26 by likai End
            param.setFlowId(String.valueOf(sc182101Bean.getDeliveryStockId()));

            // 库存接口新增必填参数enabledStockQty,默认设置为0
            param.setEnabledStockQty(BigDecimal.ZERO);
            /** 库存接口补全参数   modify by li_kai1 2016/7/28 end */

            stockParamList.add(param);
        }

        StockRsParamList stockRsParamList = new StockRsParamList();
        stockRsParamList.setPdList(stockParamList);

        boolean stockResult = RestUtil.saveStockOfSupplierList(stockRsParamList);
        if (!stockResult) {
            throw new BusinessException("修改销售库存失败!");
        }
        logger.info("修改销售库存结束");
        //修改该通知单的状态
        SC182101Bean bean = super.findOne(sc181102Param);
        if (null == bean) {
            throw new BusinessException("不存在该发货入库单!");
        }
        bean.setDeliveryStockStatus(BusinessConst.DeliveryStockStatus.Delivery);
        //已收货
        if (flagLivery == true) {
            bean.setDeliveryStockStatus(BusinessConst.DeliveryStockStatus.Delivery);
            /** #1763 添加实际到货时间 modify by likai on 2016/8/15 start */
            bean.setDeliveryReceiveStockTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(currentDate));
            /** #1763 添加实际到货时间 modify by likai on 2016/8/15 end */

        } else {
            //部分收货
            bean.setDeliveryStockStatus(BusinessConst.DeliveryStockStatus.PartialDelivery);
            /** #1763 添加实际到货时间 modify by likai on 2016/8/15 start */
            bean.setDeliveryReceiveStockTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(currentDate));
            /** #1763 添加实际到货时间 modify by likai on 2016/8/15 end */

        }
        bean.setStockMemo(sc182101Bean.getStockMemo());
        bean.setUpdId(sc182101Bean.getCrtId());
        bean.setUpdTime(currentDate);
        super.modify(bean);
        logger.info("收货操作结束");
        return NumberConst.IntDef.INT_ONE;

    }

    /**
     * 获取分销月度
     *
     * @return DistSuppChain
     */
    public DistSuppChain getDistMonth() {
        /**传入对象的参数保留至传出对象*/
        DistSuppChain distSuppChain = new DistSuppChain();
        /*期*/
        List<DistSuppChain> distMonthList = distSuppChainLogic.findDistMonth();

        distSuppChain.setDistMonthList(distMonthList);

        return distSuppChain;
    }

    /**
     * 获取半旬信息
     *
     * @param sc181102Param 传入参数
     * @return
     */
    @Transactional
    public DistSuppChain getHalfName(SC181102Param sc181102Param) {
        DistSuppChain distSuppChain = new DistSuppChain();
        if (!StringUtil.isNullOrEmpty(sc181102Param.getDistMonth())) {
            //获取地区
            List<DistSuppChain> areaInfoList = distSuppChainLogic.findLogisticsAreaList(sc181102Param.getDistMonth());
            //获取供应商
            BaseParam param = new BaseParam();
            param.setFilter("distMonth", sc181102Param.getDistMonth());
            param.setFilter("logisAreaCode", sc181102Param.getAreaCode());
            param.setFilter("userType", sc181102Param.getUserType());
            /**为获取供应商自己信息修改如下    2016/6/11*/
            //param.setFilter("loginId",sc181102Param.getCrtId());
            ISL231193RsParam isl231193RsParam = new ISL231193RsParam();
            isl231193RsParam.setSlAccount(sc181102Param.getCrtId());
            ISL231193RsResult isl231193RsResult = RestUtil.queryslEpData(isl231193RsParam);
            if (isl231193RsResult.getSlCode() != null && !isl231193RsResult.getSlCode().equals("")) {
                param.setFilter("slCode", isl231193RsResult.getSlCode()); // 登录用户编号
            }

            List<DistSuppChain> suppInfoList = distSuppChainLogic.findSuppInfoList(param);
            distSuppChain.setProductName(sc181102Param.getProductName());
            distSuppChain.setAreaCode(sc181102Param.getAreaCode());
            distSuppChain.setSupplierCode(sc181102Param.getSupplierCode());
            distSuppChain.setSupplierInfoList(suppInfoList);
            distSuppChain.setAreaInfoList(areaInfoList);
            distSuppChain.setDistMonth(sc181102Param.getDistMonth());
        } else {
            //初期化时获取当前分销月份和地区
            String now = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, DateTimeUtil.getCustomerDate());
            String currentDistMonth = distSuppChainLogic.getNowHalfDate(now);
            distSuppChain.setDistMonth(currentDistMonth);
            //获取当前月对应的地区
            List<DistSuppChain> areaInfoList = distSuppChainLogic.findLogisticsAreaList(currentDistMonth);
            if (CollectionUtils.isEmpty(areaInfoList)) {
                throw new BusinessException(currentDistMonth + "月度没有可发货的产品");
            }
            //获取当前月对应的地区对应的供应商
            BaseParam param = new BaseParam();
            param.setFilter("distMonth", currentDistMonth);
            param.setFilter("logisAreaCode", sc181102Param.getAreaCode());
            param.setFilter("userType", sc181102Param.getUserType());
            /**为获取供应商自己信息修改如下    2016/6/11*/
            //param.setFilter("loginId",sc181102Param.getCrtId());
            ISL231193RsParam isl231193RsParam = new ISL231193RsParam();
            isl231193RsParam.setSlAccount(sc181102Param.getCrtId());
            ISL231193RsResult isl231193RsResult = RestUtil.queryslEpData(isl231193RsParam);
            if (!StringUtil.isNullOrEmpty(isl231193RsResult.getSlCode())) {
                param.setFilter("slCode", isl231193RsResult.getSlCode()); // 登录用户编号
            }

            List<DistSuppChain> suppInfoList = distSuppChainLogic.findSuppInfoList(param);
            if (CollectionUtils.isEmpty(suppInfoList)) {
                throw new BusinessException(currentDistMonth
                        + "分销月份和物流区域下没有供应商信息");
            }
            distSuppChain.setAreaInfoList(areaInfoList);
            distSuppChain.setSupplierInfoList(suppInfoList);
            distSuppChain.setAreaCode(areaInfoList.get(0).getAreaCode());
            sc181102Param.setAreaCode(areaInfoList.get(0).getAreaCode());
            distSuppChain.setSupplierCode(suppInfoList.get(0).getSupplierCode());
            sc181102Param.setSupplierCode(suppInfoList.get(0).getSupplierCode());

            sc181102Param.setDistMonth(currentDistMonth);
        }
        /*表头日期数据和当前日期所在的半旬*/
        int year = StringUtil.toInteger(sc181102Param.getDistMonth().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_FOUR));
        int month = StringUtil.toInteger(sc181102Param.getDistMonth().substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX));
        String dataSelect = getCurrentDateStr(month, year);
        String dataSelectBefore = "";
        if (month == NumberConst.IntDef.INT_ONE) {
            dataSelectBefore = getCurrentDateStr(NumberConst.IntDef.INT_TWELVE, year - NumberConst.IntDef.INT_ONE);
        } else {
            dataSelectBefore = getCurrentDateStr(month - NumberConst.IntDef.INT_ONE, year);
        }
        distSuppChain.setDataSelect(dataSelect);
        distSuppChain.setDataSelectBefore(dataSelectBefore);
        //当前半旬Code
        distSuppChain.setCurrentHalfCode(distSuppChainLogic.getNowHalfCode(sc181102Param.getDistMonth()));
        /*半旬名称List*/
        distSuppChain.setHalfNameList(distSuppChainLogic.getHalfNameList(sc181102Param.getDistMonth(), NumberConst.IntDef.INT_ZERO));

        return distSuppChain;
    }

    /**
     * 获得XX年XX月字符串
     *
     * @param month 月
     * @param year  年
     * @return string
     */
    public String getCurrentDateStr(int month, int year) {
        String dataStr = null;
        if (month < NumberConst.IntDef.INT_TEN) {
            dataStr = year + "年0" + month + "月";
        } else {
            dataStr = year + "年" + month + "月";
        }
        return dataStr;
    }

    /**
     * 取得发货次数
     *
     * @return 发货次数
     */
    @Transactional(readOnly = true)
    public Long getDeliveryNums(BaseParam bean) {
        Long deliveryNums = 0l;
        SC182101Bean res = super.findOne(SqlId.SQL_ID_FIND_DELIVERYNUMS, bean);
        if (res != null && res.getDeliveryNums() != null) {
            deliveryNums = res.getDeliveryNums() + 1;
        }
        return deliveryNums == 0l ? 1l : deliveryNums;
    }

    /**
     * 获取产品相关信息
     *
     * @param list 数据列表
     */
    private void setProductInfo(List<SC182102Param> list) {
        Map<String, PDInfoResult> productMap = getProductInfoList(list);
        for (SC182102Param sc182102Param : list) {
            PDInfoResult product = productMap.get(sc182102Param.getProductCode());

            if (null == product) {
                product = new PDInfoResult();
            }
            sc182102Param.setProductName(StringUtil.toSafeString(product.getPdName()));
            sc182102Param.setProductTypeName(StringUtil.toSafeString(product.getBreedName()));
            sc182102Param.setProductFeature(StringUtil.toSafeString(product.getFeatureName()));
            sc182102Param.setProductLevel(StringUtil.toSafeString(product.getGradeName()));
            sc182102Param.setProductSpecifical(StringUtil.toSafeString(product.getNormsName()));
            sc182102Param.setClassesName(StringUtil.toSafeString(product.getClassesName()));
            sc182102Param.setClassesCode(StringUtil.toSafeString(product.getClassesCode()));
            sc182102Param.setBreedCode(StringUtil.toSafeString(product.getBreedCode()));
            sc182102Param.setFeatureCode(StringUtil.toSafeString(product.getFeatureCode()));
            sc182102Param.setGradeCode(StringUtil.toSafeString(product.getGradeCode()));
            sc182102Param.setNormsName(StringUtil.toSafeString(product.getNormsName()));
            sc182102Param.setPackageCode(StringUtil.toSafeString(product.getNormsCode()));
            sc182102Param.setVolume(DecimalUtil.getBigDecimal(product.getNormsVolume()));
            sc182102Param.setNetWeight(DecimalUtil.getBigDecimal(product.getWeightVal()));

        }
    }

    /**
     * 获取产品信息 + 根据供应商编码，产品编码，平台编码获取货号  批量处理
     *
     * @param productList
     */
    private Map<String, Object> getPdDataMap(List<SC182102Bean> productList, String slCode) {
        Map<String, PDInfoResult> pdCodeMap = new HashMap<String, PDInfoResult>();
        Map<String, SlPdArtnoBean> slCodeMap = new HashMap<String, SlPdArtnoBean>();

        List<String> pdCodes = new ArrayList<String>();
        for (SC182102Bean detail : productList) {
            String pdcode = detail.getPdCode();
            if (!StringUtil.isNullOrEmpty(pdcode)) {
                pdCodes.add(pdcode);
            }
        }
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setPdCodes(pdCodes);
        pdInfoParam.setGradeFlag("YES");
        List<PDInfoResult> res = RestUtil.getProductInfoList(pdInfoParam);
        if (!org.springframework.util.CollectionUtils.isEmpty(res)) {
            List<SlPdArtnoBean> slPdArtnoBeanList = new ArrayList<SlPdArtnoBean>();
            for (PDInfoResult bean : res) {
                pdCodeMap.put(bean.getPdCode(), bean);

                // 准备数据
                SlPdArtnoBean slPdArtnoBean = new SlPdArtnoBean();
                slPdArtnoBean.setSlCode(slCode);
                slPdArtnoBean.setClassesCode(bean.getClassesCode());
                slPdArtnoBean.setMachiningCode(bean.getMachiningCode());
                slPdArtnoBean.setBreedCode(bean.getBreedCode());
                slPdArtnoBean.setFeatureCode(bean.getFeatureCode());
                slPdArtnoBean.setWeightCode(bean.getWeightCode());
                slPdArtnoBean.setGradeCode(bean.getGradeCode());
                slPdArtnoBean.setSalesPlatform(StringUtil.toString(NumberConst.IntDef.INT_ONE));
                slPdArtnoBeanList.add(slPdArtnoBean);
            }

            //根据供应商编码，产品编码，平台编码获取货号
            SlPdArtnoBean slPdArtnoBean = new SlPdArtnoBean();
            slPdArtnoBean.setSlPdArtnoBeanList(slPdArtnoBeanList);
            SlPdArtnoBean slPdArtno = RestUtil.getSlPdArtNo(slPdArtnoBean);
            List<SlPdArtnoBean> slPdArtnoBeanListR = slPdArtno.getSlPdArtnoBeanList();
            if (!org.springframework.util.CollectionUtils.isEmpty(slPdArtnoBeanListR)) {
                for (SlPdArtnoBean bean : slPdArtnoBeanListR) {
                    String key = bean.getClassesCode() + bean.getMachiningCode() + bean.getBreedCode()
                            + bean.getFeatureCode() + bean.getWeightCode() + bean.getGradeCode();
                    slCodeMap.put(key, bean);
                }
            }
        }
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("pdCodeMap", pdCodeMap);
        maps.put("slCodeMap", slCodeMap);
        return maps;
    }

    private Map<String, PDInfoResult> getProductInfoList(List<SC182102Param> paramList) {
        PDInfoParam pdInfoParam = new PDInfoParam();
        List<String> pdCodes = new ArrayList<String>();
        for (SC182102Param sc182102Param : paramList) {
            String pdCode = sc182102Param.getProductCode();
            if (!StringUtil.isNullOrEmpty(pdCode)) {
                pdCodes.add(pdCode);
            }
        }

        pdInfoParam.setGradeFlag("YES");
        pdInfoParam.setPdCodes(pdCodes);
        List<PDInfoResult> results = RestUtil.getProductInfoList(pdInfoParam);

        Map<String, PDInfoResult> productMap = new HashMap<String, PDInfoResult>();
        for (PDInfoResult pdInfoResult : results) {
            productMap.put(pdInfoResult.getPdCode(), pdInfoResult);
        }
        return productMap;
    }

}
