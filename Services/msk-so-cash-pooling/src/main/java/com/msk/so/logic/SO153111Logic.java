package com.msk.so.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.so.bean.SOCp153111Bean;
import com.msk.so.utils.CheckUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wu_honglei on 2016/4/29.
 */

@Service
public class SO153111Logic extends ExcelAsyncPrintFasterService {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO153111Logic.class);

    /**
     * SqlId. sql定义
     *
     */
    interface SqlId {
        String SQL_ID_FIND_BY_ID  = "findById";
        String SQL_ID_UPDATE_FLG = "updateFlg";
        String SQL_ID_GET_CURRENT_INFO = "getCurrentInfo";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }



    @Transactional(readOnly = true)
    public PageResult<SOCp153111Bean> findSellerBillingPage(BasePageParam param){
        logger.info("卖家资金池管理");
        PageResult<SOCp153111Bean> result = this.findPage(param, SOCp153111Bean.class);
        if (result.getRecordsTotal() != NumberConst.IntDef.INT_ZERO) {
            SOCp153111Bean currentBen = getCurrentInfo(param);
            result.getData().get(0).setCurrentBillAmount(currentBen.getCurrentBillAmount());
            result.getData().get(0).setCurrentRealReceiveable(currentBen.getCurrentRealReceiveable());
            result.getData().get(0).setCurrentRealSettlementAmount(currentBen.getCurrentRealSettlementAmount());
            result.getData().get(0).setCurrentAjustAmount(currentBen.getCurrentAjustAmount());
            result.getData().get(0).setCurrentUnSettlementAmount(currentBen.getCurrentUnSettlementAmount());

            param.setPaging(false);
            SOCp153111Bean totalBen = getCurrentInfo(param);
            result.getData().get(0).setTotalBillAmount(totalBen.getCurrentBillAmount());
            result.getData().get(0).setTotalRealReceiveable(totalBen.getCurrentRealReceiveable());
            result.getData().get(0).setTotalRealSettlementAmount(totalBen.getCurrentRealSettlementAmount());
            result.getData().get(0).setTotalAjustAmount(totalBen.getCurrentAjustAmount());
            result.getData().get(0).setTotalUnSettlementAmount(totalBen.getCurrentUnSettlementAmount());
        }
        return result;
    }

    /**
     * 卖家费用统计
     * @param pageParam
     * @return
     */
    @Transactional(readOnly = true)
    public SOCp153111Bean getCurrentInfo(BasePageParam pageParam) {
        return super.findOne(SqlId.SQL_ID_GET_CURRENT_INFO, pageParam);
    }

    @Transactional(readOnly = true)
    public SOCp153111Bean findSellerById(String  sellerBillId){
        logger.info("卖家资金池管理详情");
        BaseParam param = new BaseParam();
        param.getFilterMap().put("sellerBillId",sellerBillId);
        return  super.findOne(SqlId.SQL_ID_FIND_BY_ID,param);
    }

    @Transactional
    public int updateFlg(String sellerBillId,String settlementFlag ){
        BaseParam param = new BaseParam();
        param.getFilterMap().put("sellerBillId",sellerBillId);
        param.getFilterMap().put("settlementFlag",settlementFlag);
        return super.modify(SqlId.SQL_ID_UPDATE_FLG,param);
    }

    /**
     * 设置查询参数
     * @param pageParam BasePageParam
     * @return pageParam BasePageParam
     */
    public BasePageParam setParam(BasePageParam pageParam){
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

        // 卖家名称
        DbUtils.buildLikeCondition(pageParam, "businessMainName", DbUtils.LikeMode.PARTIAL);
        //DbUtils.buildLikeCondition(pageParam, "remark", DbUtils.LikeMode.FRONT);
        // 卖家编码
        DbUtils.buildLikeCondition(pageParam, "businessMainCode", DbUtils.LikeMode.FRONT);

        //
        String paymentType = StringUtil.toSafeString(pageParam.getFilterMap().get("paymentType"));
        if (!StringUtil.isNullOrEmpty(paymentType)) {
            String[] paymentTypes = paymentType.split(",");
            pageParam.getFilterMap().put("paymentTypes", paymentTypes);
        }
        // 计费类型
        String billType = StringUtil.toSafeString(pageParam.getFilterMap().get("billType"));
        if (!StringUtil.isNullOrEmpty(billType)) {
            String[] billTypes = billType.split(",");
            pageParam.getFilterMap().put("billTypes", billTypes);
        }
        // 	结算状态
        String settlementStatus = StringUtil.toSafeString(pageParam.getFilterMap().get("settlementStatus"));
        if (!StringUtil.isNullOrEmpty(settlementStatus)) {
            String[] settlementStatusArr = settlementStatus.split(",");
            pageParam.getFilterMap().put("settlementStatusArr", settlementStatusArr);
        }
        //  结算标志
        String settlementFlg = StringUtil.toSafeString(pageParam.getFilterMap().get("settlementFlg"));
        if (!StringUtil.isNullOrEmpty(settlementFlg)) {
            String[] settlementFlgs = settlementFlg.split(",");
            pageParam.getFilterMap().put("settlementFlgs", settlementFlgs);
        }
        // 支付方式
        String paidType = StringUtil.toSafeString(pageParam.getFilterMap().get("paidType"));
        if (!StringUtil.isNullOrEmpty(paidType)) {
            String[] paidTypes = paidType.split(",");
            pageParam.getFilterMap().put("paidTypes", paidTypes);
        }
        return pageParam;
    }

//    @Transactional
//    public void export(OutputStream ouputStream , BasePageParam pageParam) throws FileNotFoundException, NoSuchFieldException, IllegalAccessException {
//        InputStream in = SO153101Logic.class.getResourceAsStream("/temp/reportTemp153111.xlsx");
//        ExcelWrite excelWrite = new JxlsExcelWrite(in, new BufferedOutputStream(ouputStream));
//        pageParam.setPaging(false);
//
//        List<SOCp153111Bean> list = super.findPageList(pageParam, SOCp153111Bean.class);
//
//
////        PageResult<SOCp153111Bean> result = this.findPage(pageParam, SOCp153111Bean.class);
//
//        Class sc = SOCp153111Bean.class;
//        String[] s = new String[]{"paidType"};
//
//        //取得对应字段的map集合
//        Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
//        Map<String, String> billTypeMap = CodeMasterManager.findCodeMasterMap("BillType");
//        Map<String, String> settlementStatusMap = CodeMasterManager.findCodeMasterMap("SettlementStatus");
//        Map<String, String> settlementFlgMap = CodeMasterManager.findCodeMasterMap("SettlementFlg");
//
//        String str = null;
//        String value = null;
//        for(int i = 0;i < list.size();i++){
//
//            str = list.get(i).getPaidType().toString();
//            list.get(i).setPaidType("");
//            if(!StringUtil.isEmpty(str) && !CollectionUtils.isEmpty(paidTypeMap)){
//                value =  paidTypeMap.get(str);
//                if(!StringUtil.isEmpty(value)){
//                    list.get(i).setPaidType(value);
//                }
//            }
//
//            list.get(i).setBillTypeStr("");
//            str = list.get(i).getBillType()+"";
//            if(!StringUtil.isEmpty(str) && !CollectionUtils.isEmpty(billTypeMap)){
//                value =  billTypeMap.get(str);
//                if(!StringUtil.isEmpty(value)){
//                    list.get(i).setBillTypeStr(value);
//                }
//            }
//
//            list.get(i).setSettlementStatusStr("");
//            str = list.get(i).getSettlementStatus()+"";
//            if(!StringUtil.isEmpty(str) && !CollectionUtils.isEmpty(settlementStatusMap)){
//                value =  settlementStatusMap.get(str);
//                if(!StringUtil.isEmpty(value)){
//                    list.get(i).setSettlementStatusStr(value);
//                }
//            }
//
//            list.get(i).setSettlementFlgStr("");
//            str = list.get(i).getSettlementFlg()+"";
//            if(!StringUtil.isEmpty(str) && !CollectionUtils.isEmpty(settlementFlgMap)){
//                value =  settlementFlgMap.get(str);
//                if(!StringUtil.isEmpty(value)){
//                    list.get(i).setSettlementFlgStr(value);
//                }
//            }
//        }
//
//        Map<String, Object> paramMap = new HashMap<>();
//
//        /**zhang_jiaxing 2016/8/12 start*/
//        if (list.size() != NumberConst.IntDef.INT_ZERO) {
//
//            pageParam.setPaging(false);
//            SOCp153111Bean totalBen = getCurrentInfo(pageParam);
//            paramMap.put("currentbillAmount",totalBen.getCurrentBillAmount());
//            paramMap.put("ajustAmount",totalBen.getCurrentAjustAmount());
//            paramMap.put("realReceiveable",totalBen.getCurrentRealReceiveable());
//            paramMap.put("realSettlementAmount",totalBen.getCurrentRealSettlementAmount());
//            paramMap.put("unSettlementAmount",totalBen.getCurrentUnSettlementAmount());
//
//        }
//        /**zhang_jiaxing 2016/8/12 end*/
//        paramMap.put("list", list);
//
//        excelWrite.excelWrite(paramMap);
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
        BasePageParam pageParam = new BasePageParam();
        // 设置FilterMap
        pageParam.setFilterMap((Map<String, Object>) param);
        setParam(pageParam);
        // 查询所有数据
        List<SOCp153111Bean> dataList = this.findPageList(pageParam, SOCp153111Bean.class);

        // 合计数据初始化
        BigDecimal totalBillAmount = BigDecimal.ZERO;
        BigDecimal totalRealReceiveable = BigDecimal.ZERO;
        BigDecimal totalRealSettlementAmount = BigDecimal.ZERO;
        BigDecimal totalAjustAmount = BigDecimal.ZERO;
        BigDecimal totalUnSettlementAmount = BigDecimal.ZERO;

        // 当查询结果不为空的时候
        if (CollectionUtils.isNotEmpty(dataList)) {
            //取得对应字段的map集合
            Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
            Map<String, String> billTypeMap = CodeMasterManager.findCodeMasterMap("BillType");
            Map<String, String> settlementStatusMap = CodeMasterManager.findCodeMasterMap("SettlementStatus");
            Map<String, String> settlementFlgMap = CodeMasterManager.findCodeMasterMap("SettlementFlg");
            // xlsx序列
            Integer xlsxNo = NumberConst.IntDef.INT_ONE;
            // 组装数据
            for (SOCp153111Bean bean: dataList) {
                // 设置xlsx序列
                bean.setXlsxNo(xlsxNo++);
                // 支付方式 转换：编码->名称
                if (!StringUtil.isNullOrEmpty(bean.getPaidType())) {
                    String paidType = paidTypeMap.get(bean.getPaidType());
                    if (null != paidType) {
                        bean.setPaidType(paidType);
                    }
                }
                // 计费类型 转换：编码->名称
                if (!StringUtil.isNullOrEmpty(bean.getBillType()+"")) {
                    String billType = billTypeMap.get(bean.getBillType()+"");
                    if (null != billType) {
                        bean.setBillTypeStr(billType);
                    }
                }
                // 结算状态 转换：编码->名称
                if (!StringUtil.isNullOrEmpty(bean.getSettlementStatus()+"")) {
                    String settlementStatus = settlementStatusMap.get(bean.getSettlementStatus()+"");
                    if (null != settlementStatus) {
                        bean.setSettlementStatusStr(settlementStatus);
                    }
                }
                // 结算标志 转换：编码->名称
                if (!StringUtil.isNullOrEmpty(bean.getSettlementFlg()+"")) {
                    String settlementFlg = settlementFlgMap.get(bean.getSettlementFlg()+"");
                    if (null != settlementFlg) {
                        bean.setSettlementFlgStr(settlementFlg);
                    }
                }
            }
                pageParam.setPaging(false);
                SOCp153111Bean totalBen = getCurrentInfo(pageParam);
                // 计算 计费金额
                totalBillAmount = totalBen.getCurrentBillAmount();
                // 计算 应付金额
                totalRealReceiveable = totalBen.getCurrentRealReceiveable();
                // 计算 实付金额
                totalRealSettlementAmount = totalBen.getCurrentRealSettlementAmount();
                // 计算 减/退金额
                totalAjustAmount = totalBen.getCurrentAjustAmount();
                // 计算 结余金额
                totalUnSettlementAmount = totalBen.getCurrentUnSettlementAmount();
        }

        // 设置paramMap
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("list", dataList);
        paramMap.put("totalBillAmount", totalBillAmount);
        paramMap.put("totalRealReceiveable", totalRealReceiveable);
        paramMap.put("totalRealSettlementAmount", totalRealSettlementAmount);
        paramMap.put("totalAjustAmount", totalAjustAmount);
        paramMap.put("totalUnSettlementAmount", totalUnSettlementAmount);

        List<Map<String,?>> listParam =  new ArrayList<>();
        paramMap.put("sheetName","买家列表数据");
        listParam.add(paramMap);
        return listParam;
    }



}
