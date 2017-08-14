package com.msk.ds.logic;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.DsPdVirtualStockActual;
import com.msk.core.entity.DsPdVirtualStockActualHistory;
import com.msk.ds.bean.ActualInput;
import com.msk.ds.bean.SC183103Param;
import com.msk.ds.bean.SC183103ProductParam;
import com.msk.ds.rest.comm.RestUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DS174101Logic.
 *
 * @author pxg
 * @version 1.0
 */
@Service
public class SC183103Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC183103Logic.class);
    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private DistSuppChainLogic distSuppChainLogic;

    /**
     * 1:供货商月度管控画面，2:供应商产品计划调整一览画面
     */
    static final String STRING_ONE = "1";

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        String SQL_GET_ACTUAL_CHANGE_PRODUCT_LIST = "findProductList";
        String SQL_GET_ACTUAL_CHANGE_PRODUCT_LIST_BY_ID = "findProductListById";
        String SQL_MODIFY_DELETE_FLAG = "modifyDeleteFlag";
        String SQL_MODIFY_CHANGE_PRODUCT = "modifyData";
        String SQL_FIND_ONE_ACTUAL_BY_INDEX = "findOneActualByIndex";
        String SQL_SAVE_ACTUAL_HISTORY = "saveActualHistory";
        String SQL_MODIFY_ACTUAL_BY_INDEX = "modifyActualByIndex";
    }

    /**
     * 获取期下拉框数据
     *
     * @param sc183103Param param
     * @return SC183103Param
     */
    @Transactional
    public ActualInput getHalfName(SC183103Param sc183103Param) {
        //传出对象
        ActualInput actualInput = new ActualInput();
        //产品名称
        String productName = null;
        //点击的半旬Code
        String currentHalfCode = null;
        //点击的计划类型
        String planType = null;
        //点击的调整时间
        String adjustDate = null;
        //点击的按钮名称(XXX_ProductName_HalfCode_PlanType)
        String buttonName = sc183103Param.getButtonName();
        if ("1".equals(sc183103Param.getEntryMark()) && buttonName != null) {

            //点击的按钮对应的产品
            productName = buttonName.split("_")[NumberConst.IntDef.INT_ONE];
            //点击的按钮是第几个半旬
            currentHalfCode = buttonName.split("_")[NumberConst.IntDef.INT_TWO];
            //点击的按钮是所在的计划类型
            planType = buttonName.split("_")[NumberConst.IntDef.INT_THREE];
            //调整时间
            adjustDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, DateTimeUtil.getCustomerDate());
            //添加检索条件
            sc183103Param.setCurrentHalfCode(currentHalfCode);
            sc183103Param.setProductName(productName);
            sc183103Param.setPlanType(planType);
        } else if ("2".equals(sc183103Param.getEntryMark())) {
            productName = sc183103Param.getProductName();
            currentHalfCode = sc183103Param.getCurrentHalfCode();
            planType = sc183103Param.getPlanType();
            //Modify for 3019 at 2016/09/28 by likai Start
            adjustDate = sc183103Param.getAdjustDate().substring(0, 10).replace("/","-");
            //Modify for 3019 at 2016/09/28 by likai End
            actualInput.setSuppDsId(sc183103Param.getSuppDsId());
            actualInput.setVirtualStockActualId(sc183103Param.getVirtualStockActualId());
        } else {
            //selectChange的情况下
            productName = sc183103Param.getProductName();
            planType = sc183103Param.getPlanType();
            currentHalfCode = sc183103Param.getCurrentHalfCode();
            adjustDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, DateTimeUtil.getCustomerDate());
        }
        //获取传出数据
        actualInput.setDistMonth(sc183103Param.getDistMonth());
        actualInput.setAreaName(sc183103Param.getAreaName());
        actualInput.setAreaCode(sc183103Param.getAreaCode());
        actualInput.setSupplierName(sc183103Param.getSupplierName());
        actualInput.setSupplierCode(sc183103Param.getSupplierCode());
        actualInput.setProductName(productName);
        actualInput.setHalfCode(currentHalfCode);
        actualInput.setPlanType(planType);
        actualInput.setAdjustDate(adjustDate);
        actualInput.setEntryMark(sc183103Param.getEntryMark());
        //获取计划类型下拉数据
        actualInput.setPlanList(getPlanTypeList(sc183103Param, planType));
        //获得显示的半旬
        int year = Integer.parseInt(sc183103Param.getDistMonth().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_FOUR));
        int month = Integer.parseInt(sc183103Param.getDistMonth().substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX));
        String halfName = null;
        if ("1".equals(currentHalfCode) || "2".equals(currentHalfCode)) {
            if (month == NumberConst.IntDef.INT_ONE) {
                halfName = distSuppChainLogic.getHalfName(NumberConst.IntDef.INT_TWELVE, Integer.parseInt(currentHalfCode), year - NumberConst.IntDef.INT_ONE);

            } else {
                halfName = distSuppChainLogic.getHalfName(month - NumberConst.IntDef.INT_ONE, Integer.parseInt(currentHalfCode), year);
            }
        } else {
            halfName = distSuppChainLogic.getHalfName(month, Integer.parseInt(currentHalfCode), year);
        }
        actualInput.setHalfName(halfName);
        //获得调整前后的数据
        List<ActualInput> actualChangeProductList = findActualChangeProduct(sc183103Param);
        //获得调整前的数据合计
        BigDecimal sumChangeBeforeNum = BigDecimal.ZERO;
        if (!CollectionUtils.isEmpty(actualChangeProductList)) {
            for (int i = 0; i < actualChangeProductList.size(); i++) {
                ActualInput actualInput1 = actualChangeProductList.get(i);
                String productFullName = actualInput1.getClassName() + actualInput1.getBreedName() + actualInput1.getFeatureName() + actualInput1.getGradeName();
                String productFullCode = actualInput1.getClassCode() + actualInput1.getBreedCode() + actualInput1.getFeatureCode() + actualInput1.getGradeCode();
                actualInput1.setProductName(productFullName);
//                actualInput1.setProductCode(productFullCode);
                sumChangeBeforeNum = sumChangeBeforeNum.add(actualChangeProductList.get(i).getChangeBeforeNum());
            }
        }
        actualInput.setSumChangeBeforeNum(sumChangeBeforeNum);
        actualInput.setPlanChangeProductList(actualChangeProductList);

        return actualInput;
    }

    /**
     * 获取变更前数据
     *
     * @param sc183103Param sc183103Param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ActualInput> findActualChangeProduct(SC183103Param sc183103Param) {
        List<ActualInput> actualChangeProductList = null;
        if ("1".equals(sc183103Param.getEntryMark())) {
            sc183103Param.setProductName(DbUtils.buildLikeCondition(sc183103Param.getProductName(), DbUtils.LikeMode.FRONT));
            actualChangeProductList = this.findList(SqlId.SQL_GET_ACTUAL_CHANGE_PRODUCT_LIST, sc183103Param);
        } else {
            actualChangeProductList = this.findList(SqlId.SQL_GET_ACTUAL_CHANGE_PRODUCT_LIST_BY_ID, sc183103Param);
        }

        setProductInfo(actualChangeProductList);
        return actualChangeProductList;
    }

    /**
     * 设置产品信息
     *
     * @param actualChangeProductList
     */
    private void setProductInfo(List<ActualInput> actualChangeProductList) {
        if (!CollectionUtils.isEmpty(actualChangeProductList)) {
            for (ActualInput data : actualChangeProductList) {
                //根据产品Code获取产品信息
                PDInfoParam pdInfoParam = new PDInfoParam();
                pdInfoParam.setPdCode(data.getProductCode());
                PDInfoResult product = RestUtil.getProductInfo(pdInfoParam);
                if (product == null) {
                    logger.info("产品编号:" + data.getProductCode() + "没有相关产品");
                    throw new BusinessException("产品编号:" + data.getProductCode() + "没有相关产品");
                }
                if (product.getClassesName() != null) {
                    data.setProductName(product.getClassesName());
                }
                if (product.getBreedName() != null) {
                    data.setBreedName(product.getBreedName());
                }
                if (product.getFeatureName() != null) {
                    data.setFeatureName(product.getFeatureName());
                }
                if (product.getGradeName() != null) {
                    data.setGradeName(product.getGradeName());
                }
            }
        }
    }

    /**
     * 调整数据保存到DB
     *
     * @param param
     */
    @Transactional
    public void saveChangeNum(SC183103Param param) {
        Map<String, Object> paramMap = param.getFilterMap();
        SC183103Param param1 = null;
        for (SC183103ProductParam entity : param.getProductParamList()) {
            //Add for 3487 at 2016/10/27 by likai Start
            checkSaveNum(entity);
            //Add for 3487 at 2016/10/27 by likai End
            param1 = new SC183103Param();
            param1.setDistMonth(paramMap.get("distMonth").toString());
            param1.setAreaCode(paramMap.get("areaCode").toString());
            param1.setSupplierCode(paramMap.get("supplierCode").toString());
            param1.setPlanType(paramMap.get("planType").toString());
            param1.setCurrentHalfCode(paramMap.get("halfCode").toString());
            param1.setProductCode(entity.getProductCode());
            param1.setNormsCode(entity.getNormsCode());
            param1.setAdjustDate(entity.getAdjustDate());
            param1.setChangeBeforeNum(entity.getChangeBeforeNum());
            param1.setChangeOverNum(entity.getChangeOverNum());
            //Modify for 2989 at 2016/09/29 by likai Start
//            long virtualStockActualId = this.commonLogic.maxId("DS_PD_VIRTUAL_STOCK_ACTUAL", "VIRTUAL_STOCK_ACTUAL_ID");
//            param1.setVirtualStockActualId(virtualStockActualId);
//            param1.setCrtId(param.getCrtId());
            param1.setUpdId(param.getUpdId());
            param1.setUpdTime(DateTimeUtil.getCustomerDate());
//            param1.setCrtTime(DateTimeUtil.getCustomerDate());
            if (STRING_ONE.equals(paramMap.get("entryMark").toString())) {
                DsPdVirtualStockActual origActual = this.findOne(SqlId.SQL_FIND_ONE_ACTUAL_BY_INDEX, param1);
                if (origActual == null) {
                    throw new BusinessException("查询数据异常，请和管理员联系");
                }
                DsPdVirtualStockActualHistory dsPdVirtualStockActualHistory = new DsPdVirtualStockActualHistory();
                long virtualStockActualHistoryId = this.commonLogic.maxId("DS_PD_VIRTUAL_STOCK_ACTUAL_HISTORY", "VIRTUAL_STOCK_ACTUAL_HISTORY_ID");
                BeanUtils.copyProperties(origActual, dsPdVirtualStockActualHistory);
                dsPdVirtualStockActualHistory.setVirtualStockActualHistoryId(virtualStockActualHistoryId);
                if (super.save(SqlId.SQL_SAVE_ACTUAL_HISTORY, dsPdVirtualStockActualHistory) == NumberConst.IntDef.INT_ZERO) {
                    throw new BusinessException("产品虚拟库存实际历史表插入失败，请和管理员联系");
                }
                if (super.modify(SqlId.SQL_MODIFY_ACTUAL_BY_INDEX, param1) == NumberConst.IntDef.INT_ZERO) {
                    throw new BusinessException("产品虚拟库存实际表更新失败，请和管理员联系");
                }
//                if (super.save(param1) == NumberConst.IntDef.INT_ZERO) {
//                    throw new BusinessException("更新失败，请和管理员联系");
//                }
//                if (super.modify(SqlId.SQL_MODIFY_DELETE_FLAG, param1) == NumberConst.IntDef.INT_ZERO) {
//                    throw new BusinessException("更新失败，请和管理员联系");
//                };
            } else {
                param1.setSuppDsId(Long.parseLong(paramMap.get("suppDsId").toString()));
                param1.setVirtualStockActualId(Long.parseLong(paramMap.get("virtualStockActualId").toString()));
                if (super.modify(SqlId.SQL_MODIFY_CHANGE_PRODUCT, param1) == NumberConst.IntDef.INT_ZERO) {
                    throw new BusinessException("产品虚拟库存实际表更新失败，请和管理员联系");
                }
            }
            //Modify for 2989 at 2016/09/29 by likai End

        }
    }

    //Add for 3487 at 2016/10/27 by likai Start
    public void checkSaveNum(SC183103ProductParam productParam) {
        if (null != productParam){
            BigDecimal pdOutNum = DecimalUtil.getBigDecimal(productParam.getPdOutNw());
            if (BigDecimal.ZERO == pdOutNum) {
                throw new BusinessException("产品编码_" + productParam.getProductCode() + "的净重必须为非零数字!");
            }else {
                BigDecimal changeNum = DecimalUtil.multiply(productParam.getChangeNum(), new BigDecimal(NumberConst.IntDef.INT_HUNDRED)
                ).setScale(NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP);
                BigDecimal pdOutNwRound = DecimalUtil.multiply(pdOutNum, new BigDecimal(NumberConst.IntDef.INT_HUNDRED)
                ).setScale(NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP);

                BigDecimal remainder = changeNum.remainder(pdOutNwRound);
                if (BigDecimal.ZERO != remainder) {
                    throw new BusinessException("产品编码_" + productParam.getProductCode() + "的调整值必须为净重的整数倍,请重新调整!");
                }
                BigDecimal changeOverNum = productParam.getChangeOverNum();
                if (new BigDecimal(99999999999999.99).compareTo(changeOverNum) == NumberConst.IntDef.INT_N_ONE) {
                    throw new BusinessException("产品编码_" + productParam.getProductCode() + "的调整后的值超出最大长度:14!");
                }

            }

        }
    }
    //Add for 3487 at 2016/10/27 by likai End
    /**
     * 获取计划类型下拉数据
     *
     * @param sc183103Param 传入对象
     * @param planType      点击的计划类型code
     * @return 计划类型List
     */

    public List<ActualInput> getPlanTypeList(SC183103Param sc183103Param, String planType) {
        List<ActualInput> planList = new ArrayList<>();
        if ("1".equals(sc183103Param.getEntryMark())) {
            ActualInput actualInput1 = new ActualInput();
            actualInput1.setPlanType("1");
            actualInput1.setPlanTypeName("生产期产品");
            ActualInput actualInput2 = new ActualInput();
            actualInput2.setPlanType("2");
            actualInput2.setPlanTypeName("待运库产品");
            ActualInput actualInput3 = new ActualInput();
            actualInput3.setPlanType("3");
            actualInput3.setPlanTypeName("在途产品");
            ActualInput actualInput4 = new ActualInput();
            actualInput4.setPlanType("4");
            actualInput4.setPlanTypeName("入销售库产品");
            planList.add(actualInput1);
            planList.add(actualInput2);
            planList.add(actualInput3);
            planList.add(actualInput4);
        } else if ("2".equals(sc183103Param.getEntryMark())) {
            ActualInput planAdjust = new ActualInput();
            String planTypeName = null;
            switch (planType) {
                case "1":
                    planTypeName = "生产期产品";
                    break;
                case "2":
                    planTypeName = "待运库产品";
                    break;
                case "3":
                    planTypeName = "在途产品";
                    break;
                case "4":
                    planTypeName = "入销售库产品";
                    break;
            }
            planAdjust.setPlanType(planType);
            planAdjust.setPlanTypeName(planTypeName);
            planList.add(planAdjust);
        }
        return planList;
    }

}
