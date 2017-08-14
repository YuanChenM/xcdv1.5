package com.msk.so.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.cashPooling.bean.SO153101Param;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.excel.ExcelWrite;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.common.service.ExcelAsyncPrintService;
import com.msk.so.bean.SO153101Bean;
import com.msk.so.utils.CheckUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang_yang on 2016/4/29.
 */
@Service
public class SO153101Logic extends ExcelAsyncPrintFasterService {

    private static Logger logger = LoggerFactory.getLogger(SO153101Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SqlId. sql定义
     */
    interface SqlId {
        String SQL_ID_FIND_BY_ID = "findById";
        String SQL_ID_GET_CURRENT_INFO = "getCurrentInfo";
    }

    /**
     * 买家资金池管理
     *
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SO153101Bean> findSO153101List(BasePageParam pageParam) {

        logger.info("买家资金池管理");

        // 验证日期
        boolean flag = CheckUtil.checkDate(StringUtil.toSafeString(pageParam.getFilterMap().get("timeStart"))
                , StringUtil.toSafeString(pageParam.getFilterMap().get("timeEnd")));
        if (!flag) {
            throw new BusinessException("日期选择不合理");
        }

        DbUtils.buildLikeCondition(pageParam, "businessAssistantCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "businessAssistantName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "transCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "orderIdStr", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "remark", DbUtils.LikeMode.FRONT);

        String supplyPlatform = StringUtil.toSafeString(pageParam.getFilterMap().get("supplyPlatform"));
        if (!StringUtil.isNullOrEmpty(supplyPlatform)) {
            String[] supplyPlatforms = supplyPlatform.split(",");
            pageParam.getFilterMap().put("supplyPlatforms", supplyPlatforms);
        }

        String transFlg = StringUtil.toSafeString(pageParam.getFilterMap().get("transFlg"));
        if (!StringUtil.isNullOrEmpty(transFlg)) {
            String[] transFlgs = transFlg.split(",");
            pageParam.getFilterMap().put("transFlgs", transFlgs);
        }

        String paymentType = StringUtil.toSafeString(pageParam.getFilterMap().get("paymentType"));
        if (!StringUtil.isNullOrEmpty(paymentType)) {
            String[] paymentTypes = paymentType.split(",");
            pageParam.getFilterMap().put("paymentTypes", paymentTypes);
        }

        String transType = StringUtil.toSafeString(pageParam.getFilterMap().get("transType"));
        if (!StringUtil.isNullOrEmpty(transType)) {
            String[] transTypes = transType.split(",");
            pageParam.getFilterMap().put("transTypes", transTypes);
        }

        String settlementStatus = StringUtil.toSafeString(pageParam.getFilterMap().get("settlementStatus"));
        if (!StringUtil.isNullOrEmpty(settlementStatus)) {
            String[] settlementStatuss = settlementStatus.split(",");
            pageParam.getFilterMap().put("settlementStatuss", settlementStatuss);
        }

        // 支付方式
        String paidType = StringUtil.toSafeString(pageParam.getFilterMap().get("paidType"));
        if (!StringUtil.isNullOrEmpty(paidType)) {
            String[] paidTypes = paidType.split(",");
            pageParam.getFilterMap().put("paidTypes", paidTypes);
        }

        // 买手
        String bsName = StringUtil.toSafeString(pageParam.getFilterMap().get("bsName"));
        if (!StringUtil.isNullOrEmpty(bsName)) {
            if("-".equals(bsName)){
                pageParam.getFilterMap().put("bsName", "");
            }else {
                DbUtils.buildLikeCondition(pageParam, "bsName", DbUtils.LikeMode.PARTIAL);
            }
        }

        PageResult<SO153101Bean> result = this.findPage(pageParam, SO153101Bean.class);
        if (result.getRecordsTotal() != NumberConst.IntDef.INT_ZERO) {
//            List<SO153101Bean> so153101BeanList = result.getData();
            // 准备数据
//            List<String> stringList = new ArrayList<String>();
//            for (SO153101Bean bean : so153101BeanList) {
//                String businessAssistantId = bean.getBusinessAssistantId();
//                stringList.add(businessAssistantId);
//            }

//            // 调接口 查询 买家对应的管家的买手姓名
//            List<IBS2101116Bean> resultList = SOControllerUtil.searchBuyerList(stringList);
//            Map<String, String> map = new HashMap<String, String>();
//            for (IBS2101116Bean bean : resultList) {
//                map.put(bean.getBuyerId(), bean.getSlName());
//            }

            // 组装数据
//            for (SO153101Bean bean : so153101BeanList) {
//                String businessAssistantId = bean.getBusinessAssistantId();
//                String value = map.get(businessAssistantId);
//                if (StringUtil.isEmpty(value)) {
//                    bean.setBsName("-");
//                } else {
//                    bean.setBsName(value);
//                }
//            }
//            result.setData(so153101BeanList);

            SO153101Bean currentBen = new SO153101Bean();
            currentBen = this.getCurrentInfo(pageParam);
            result.getData().get(0).setCurrentOrder(currentBen.getCurrentOrder());
            result.getData().get(0).setCurrentActual(currentBen.getCurrentActual());
            result.getData().get(0).setCurrentPaid(currentBen.getCurrentPaid());
            result.getData().get(0).setCurrentRelief(currentBen.getCurrentRelief());
            result.getData().get(0).setCurrentBalance(currentBen.getCurrentBalance());

            pageParam.setPaging(false);
            SO153101Bean totalBen = new SO153101Bean();
            totalBen = this.getCurrentInfo(pageParam);
            result.getData().get(0).setTotalOrder(totalBen.getCurrentOrder());
            result.getData().get(0).setTotalActual(totalBen.getCurrentActual());
            result.getData().get(0).setTotalPaid(totalBen.getCurrentPaid());
            result.getData().get(0).setTotalRelief(totalBen.getCurrentRelief());
            result.getData().get(0).setTotalBalance(totalBen.getCurrentBalance());
        }


        return result;
    }

    @Transactional(readOnly = true)
    public SO153101Bean findBuyerById(String buyerBillId) {
        logger.info("买家资金池管理详情");
        BaseParam param = new BaseParam();
        param.getFilterMap().put("buyerBillId", buyerBillId);
        return super.findOne(SO153101Logic.SqlId.SQL_ID_FIND_BY_ID, param);
    }

    @Transactional(readOnly = true)
    public SO153101Bean getCurrentInfo(BasePageParam pageParam) {
        return super.findOne(SO153101Logic.SqlId.SQL_ID_GET_CURRENT_INFO, pageParam);
    }


    /**
     * 导出
     *
     * @return
     */
    @Transactional(readOnly = true)
    public void export(OutputStream ouputStream, BasePageParam pageParam) throws IOException {
        InputStream in = SO153101Logic.class.getResourceAsStream("/temp/reportTemp153101.xlsx");
        ExcelWrite excelWrite = new JxlsExcelWrite(in, new BufferedOutputStream(ouputStream));
        pageParam.setPaging(false);

        List<SO153101Bean> dataList = super.findPageList(pageParam, SO153101Bean.class);
        BigDecimal currentOrder = BigDecimal.ZERO;
        BigDecimal currentActual = BigDecimal.ZERO;
        BigDecimal currentPaid = BigDecimal.ZERO;
        BigDecimal currentRelief = BigDecimal.ZERO;
        BigDecimal currentBalance = BigDecimal.ZERO;

        if (CollectionUtils.isNotEmpty(dataList)) {
            // 支付方式
            Map<String, String> paymentTypeMap = CodeMasterManager.findCodeMasterMap("PaymentType");
            // 支付方式
            Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
            // 结算状态
            Map<String, String> settlementStatusMap = CodeMasterManager.findCodeMasterMap("SettlementStatus");

            // 组装数据
            for (SO153101Bean bean : dataList) {
                // 支付类型
                if (!StringUtil.isNullOrEmpty(bean.getPaymentType())) {
                    String paymentType = paymentTypeMap.get(bean.getPaymentType());
                    if (null != paymentType) {
                        bean.setPaymentType(paymentType);
                    }
                }
                // 支付方式
                if (!StringUtil.isNullOrEmpty(bean.getPaidType())) {
                    String paidType = paidTypeMap.get(bean.getPaidType());
                    if (null != paidType) {
                        bean.setPaidType(paidType);
                    }
                }
                // 结算状态
                if (!StringUtil.isNullOrEmpty(bean.getSettlementStatus())) {
                    String settlementStatus = settlementStatusMap.get(bean.getSettlementStatus());
                    if (null != settlementStatus) {
                        bean.setSettlementStatus(settlementStatus);
                    }
                }
            }
            //合计
            SO153101Bean totalBen = this.getCurrentInfo(pageParam);
            currentOrder = totalBen.getCurrentOrder();
            currentActual = totalBen.getCurrentActual();
            currentPaid = totalBen.getCurrentPaid();
            currentRelief = totalBen.getCurrentRelief();
            currentBalance = totalBen.getCurrentBalance();
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("list", dataList);

        paramMap.put("currentOrder", currentOrder);
        paramMap.put("currentActual", currentActual);
        paramMap.put("currentPaid", currentPaid);
        paramMap.put("currentRelief", currentRelief);
        paramMap.put("currentBalance", currentBalance);
        excelWrite.excelWrite(paramMap);
    }

//    /**
//     * 导出为Excel文件
//     * @param param 参数的LinkedHashmap
//     * @return 导出所使用的Map
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public Map<String, ?> getDataSource(Object param) {
//        // 生产pageParam
//        SO153101Param pageParam = new SO153101Param();
//        // 设置FilterMap
//        pageParam.setFilterMap((Map<String, Object>) param);
//
//        // 验证日期
//        String timeStart = (String) pageParam.getFilterMap().get("timeStart");
//        String timeEnd = (String) pageParam.getFilterMap().get("timeEnd");
//        boolean createFlag = CheckUtil.checkDate(StringUtil.toSafeString(timeStart)
//                , StringUtil.toSafeString(timeEnd));
//        boolean tranFlag = CheckUtil.checkDate(StringUtil.toSafeString(timeStart)
//                , StringUtil.toSafeString(timeEnd));
//        if (!createFlag || !tranFlag) {
//            throw new BusinessException("日期选择不合理");
//        }
//
//        // FILTER：买家名称
//        DbUtils.buildLikeCondition(pageParam, "businessAssistantName", DbUtils.LikeMode.FRONT);
//        // FILTER: 买家编码
//        DbUtils.buildLikeCondition(pageParam, "businessAssistantCode", DbUtils.LikeMode.FRONT);
//        // FILTER: 订单编码
//        DbUtils.buildLikeCondition(pageParam, "transCode", DbUtils.LikeMode.FRONT);
//        // FILTER: 订单ID
//        DbUtils.buildLikeCondition(pageParam, "orderIdStr", DbUtils.LikeMode.FRONT);
//        // FILTER: 支付类型
//        String paymentTypeFilter = StringUtil.toSafeString(pageParam.getFilterMap().get("paymentType"));
//        if (!StringUtil.isNullOrEmpty(paymentTypeFilter)) {
//            String[] paymentTypes = paymentTypeFilter.split(",");
//            pageParam.getFilterMap().put("paymentTypes", paymentTypes);
//        }
//        // FILTER: 结算状态
//        String settlementStatusFilter = StringUtil.toSafeString(pageParam.getFilterMap().get("settlementStatus"));
//        if (!StringUtil.isNullOrEmpty(settlementStatusFilter)) {
//            String[] settlementStatuss = settlementStatusFilter.split(",");
//            pageParam.getFilterMap().put("settlementStatuss", settlementStatuss);
//        }
//        // FILTER: 支付方式
//        String paidTypeFilter = StringUtil.toSafeString(pageParam.getFilterMap().get("paidType"));
//        if (!StringUtil.isNullOrEmpty(paidTypeFilter)) {
//            String[] paidTypes = paidTypeFilter.split(",");
//            pageParam.getFilterMap().put("paidTypes", paidTypes);
//        }
//        // 买手
//        String bsName = StringUtil.toSafeString(pageParam.getFilterMap().get("bsName"));
//        if (!StringUtil.isNullOrEmpty(bsName)) {
//            if("-".equals(bsName)){
//                pageParam.getFilterMap().put("bsName", "");
//            }else {
//                DbUtils.buildLikeCondition(pageParam, "bsName", DbUtils.LikeMode.PARTIAL);
//            }
//        }
//
//        // 查询所有数据
//        List<SO153101Bean> dataList  = super.findPageList(pageParam, SO153101Bean.class);
//
//        // 合计数据初始化
//        BigDecimal currentOrder = BigDecimal.ZERO;
//        BigDecimal currentActual = BigDecimal.ZERO;
//        BigDecimal currentPaid = BigDecimal.ZERO;
//        BigDecimal currentRelief = BigDecimal.ZERO;
//        BigDecimal currentBalance = BigDecimal.ZERO;
//
//        // 当查询结果不为空的时候
//        if (CollectionUtils.isNotEmpty(dataList)) {
//            // 支付类型<编码, 名称>Map
//            Map<String, String> paymentTypeMap = CodeMasterManager.findCodeMasterMap("PaymentType");
//            // 支付方式<编码, 名称>Map
//            Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
//            // 结算状态<编码, 名称>Map
//            Map<String, String> settlementStatusMap = CodeMasterManager.findCodeMasterMap("SettlementStatus");
//
//            // 组装数据
//            for (SO153101Bean bean: dataList) {
//                // 支付类型 转换：编码->名称
//                if (!StringUtil.isNullOrEmpty(bean.getPaymentType())) {
//                    String paymentType = paymentTypeMap.get(bean.getPaymentType());
//                    if (null != paymentType) {
//                        bean.setPaymentType(paymentType);
//                    }
//                }
//                // 支付方式 转换：编码->名称
//                if (!StringUtil.isNullOrEmpty(bean.getPaidType())) {
//                    String paidType = paidTypeMap.get(bean.getPaidType());
//                    if (null != paidType) {
//                        bean.setPaidType(paidType);
//                    }
//                }
//                // 结算状态 转换：编码->名称
//                if (!StringUtil.isNullOrEmpty(bean.getSettlementStatus())) {
//                    String settlementStatus = settlementStatusMap.get(bean.getSettlementStatus());
//                    if (null != settlementStatus) {
//                        bean.setSettlementStatus(settlementStatus);
//                    }
//                }
//
//                // 计算合计订单金额
//                currentOrder = currentOrder.add(bean.getOrderAmount());
//                // 计算合计应收金额
//                currentActual = currentActual.add(bean.getActualDue());
//                // 计算合计已收金额
//                currentPaid = currentPaid.add(bean.getActualPaid());
//                // 计算合计减/退金额
//                currentRelief = currentRelief.add(bean.getReliefAmount());
//                // 计算合计结余金额
//                currentBalance = currentBalance.add(bean.getBalance());
//            }
//        }
//
//        // 设置paramMap
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("list", dataList);
//        paramMap.put("currentOrder", currentOrder);
//        paramMap.put("currentActual", currentActual);
//        paramMap.put("currentPaid", currentPaid);
//        paramMap.put("currentRelief", currentRelief);
//        paramMap.put("currentBalance", currentBalance);
//
//        return paramMap;
//    }

    /**
     * 导出为Excel文件
     * @param param 参数的LinkedHashmap
     * @return 导出所使用的Map
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, ?>> getDataSourceFaster(Object param) {
        // 生产pageParam
        SO153101Param pageParam = new SO153101Param();
        // 设置FilterMap
        pageParam.setFilterMap((Map<String, Object>) param);

        // 验证日期
        String timeStart = (String) pageParam.getFilterMap().get("timeStart");
        String timeEnd = (String) pageParam.getFilterMap().get("timeEnd");
        boolean createFlag = CheckUtil.checkDate(StringUtil.toSafeString(timeStart)
                , StringUtil.toSafeString(timeEnd));
        boolean tranFlag = CheckUtil.checkDate(StringUtil.toSafeString(timeStart)
                , StringUtil.toSafeString(timeEnd));
        if (!createFlag || !tranFlag) {
            throw new BusinessException("日期选择不合理");
        }

        // FILTER：买家名称
        DbUtils.buildLikeCondition(pageParam, "businessAssistantName", DbUtils.LikeMode.FRONT);
        // FILTER: 买家编码
        DbUtils.buildLikeCondition(pageParam, "businessAssistantCode", DbUtils.LikeMode.FRONT);
        // FILTER: 订单编码
        DbUtils.buildLikeCondition(pageParam, "transCode", DbUtils.LikeMode.FRONT);
        // FILTER: 订单ID
        DbUtils.buildLikeCondition(pageParam, "orderIdStr", DbUtils.LikeMode.FRONT);
        // FILTER: 支付类型
        String paymentTypeFilter = StringUtil.toSafeString(pageParam.getFilterMap().get("paymentType"));
        if (!StringUtil.isNullOrEmpty(paymentTypeFilter)) {
            String[] paymentTypes = paymentTypeFilter.split(",");
            pageParam.getFilterMap().put("paymentTypes", paymentTypes);
        }
        // FILTER: 结算状态
        String settlementStatusFilter = StringUtil.toSafeString(pageParam.getFilterMap().get("settlementStatus"));
        if (!StringUtil.isNullOrEmpty(settlementStatusFilter)) {
            String[] settlementStatuss = settlementStatusFilter.split(",");
            pageParam.getFilterMap().put("settlementStatuss", settlementStatuss);
        }
        // FILTER: 支付方式
        String paidTypeFilter = StringUtil.toSafeString(pageParam.getFilterMap().get("paidType"));
        if (!StringUtil.isNullOrEmpty(paidTypeFilter)) {
            String[] paidTypes = paidTypeFilter.split(",");
            pageParam.getFilterMap().put("paidTypes", paidTypes);
        }
        // 买手
        String bsName = StringUtil.toSafeString(pageParam.getFilterMap().get("bsName"));
        if (!StringUtil.isNullOrEmpty(bsName)) {
            if("-".equals(bsName)){
                pageParam.getFilterMap().put("bsName", "");
            }else {
                DbUtils.buildLikeCondition(pageParam, "bsName", DbUtils.LikeMode.PARTIAL);
            }
        }

        // 查询所有数据
        List<SO153101Bean> dataList  = super.findPageList(pageParam, SO153101Bean.class);

        // 合计数据初始化
        BigDecimal currentOrder = BigDecimal.ZERO;
        BigDecimal currentActual = BigDecimal.ZERO;
        BigDecimal currentPaid = BigDecimal.ZERO;
        BigDecimal currentRelief = BigDecimal.ZERO;
        BigDecimal currentBalance = BigDecimal.ZERO;

        // 当查询结果不为空的时候
        if (CollectionUtils.isNotEmpty(dataList)) {
            // 支付类型<编码, 名称>Map
            Map<String, String> paymentTypeMap = CodeMasterManager.findCodeMasterMap("PaymentType");
            // 支付方式<编码, 名称>Map
            Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
            // 结算状态<编码, 名称>Map
            Map<String, String> settlementStatusMap = CodeMasterManager.findCodeMasterMap("SettlementStatus");
            // xlsx序列
            Integer xlsxNo = NumberConst.IntDef.INT_ONE;
            // 组装数据
            for (SO153101Bean bean: dataList) {
                // 设置xlsx序列
                bean.setXlsxNo(xlsxNo++);
                // 支付日期 转换 日期->String
                String tranTimeStr = DateTimeUtil.formatDate(CheckUtil.FORMAT_YYYYMMDD_HHMMSS, bean.getTranTime());
                bean.setTranTimeStr(tranTimeStr);
                // 支付类型 转换：编码->名称
                if (!StringUtil.isNullOrEmpty(bean.getPaymentType())) {
                    String paymentType = paymentTypeMap.get(bean.getPaymentType());
                    if (null != paymentType) {
                        bean.setPaymentType(paymentType);
                    }
                }
                // 支付方式 转换：编码->名称
                if (!StringUtil.isNullOrEmpty(bean.getPaidType())) {
                    String paidType = paidTypeMap.get(bean.getPaidType());
                    if (null != paidType) {
                        bean.setPaidType(paidType);
                    }
                }
                // 结算状态 转换：编码->名称
                if (!StringUtil.isNullOrEmpty(bean.getSettlementStatus())) {
                    String settlementStatus = settlementStatusMap.get(bean.getSettlementStatus());
                    if (null != settlementStatus) {
                        bean.setSettlementStatus(settlementStatus);
                    }
                }

                // 计算合计订单金额
                currentOrder = currentOrder.add(bean.getOrderAmount());
                // 计算合计应收金额
                currentActual = currentActual.add(bean.getActualDue());
                // 计算合计已收金额
                currentPaid = currentPaid.add(bean.getActualPaid());
                // 计算合计减/退金额
                currentRelief = currentRelief.add(bean.getReliefAmount());
                // 计算合计结余金额
                currentBalance = currentBalance.add(bean.getBalance());
            }
        }

        // 设置paramMap
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("list", dataList);
        paramMap.put("currentOrder", currentOrder);
        paramMap.put("currentActual", currentActual);
        paramMap.put("currentPaid", currentPaid);
        paramMap.put("currentRelief", currentRelief);
        paramMap.put("currentBalance", currentBalance);

        List<Map<String,?>> listParam =  new ArrayList<>();
        paramMap.put("sheetName","买家列表数据");
        listParam.add(paramMap);
        return listParam;
    }

}
