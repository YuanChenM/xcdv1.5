package com.msk.so.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
/** Modify for Bug #3717 by li_huiqian on 20161124 start**/
import com.hoperun.core.consts.StringConst;
/** Modify for Bug #3717 by li_huiqian on 20161124 end**/
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.excel.ExcelWrite;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.so.bean.SO153121Bean;
import com.msk.so.bean.SOCp153151Bean;
import com.msk.so.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang_yang
 */
@Service
public class SO153151Logic extends ExcelAsyncPrintFasterService {

    private static Logger logger = LoggerFactory.getLogger(SO153151Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SqlId sql定义
     */
    interface SqlId {
        String SQL_ID_GET_TOTAL_INFO = "getTotalInfo";
    }

    /**
     * 支付一览
     *
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SOCp153151Bean> findSO153151List(BasePageParam pageParam) {

        logger.info("支付一览");
        // 设置参数
        pageParam = setBasePageParam(pageParam);
        PageResult<SOCp153151Bean> result = this.findPage(pageParam, SOCp153151Bean.class);

        if (result.getRecordsTotal() != NumberConst.IntDef.INT_ZERO) {
            /* 计算当前页合计 */
            List<SOCp153151Bean> list = result.getData();
            // 当前页支付金额合计
            BigDecimal currentPaid = BigDecimal.ZERO;
            for (int index = 0; index < list.size(); index++) {
                // 数据库中为NOT NULL
                BigDecimal paidAmount = list.get(index).getPaidAmount();
                currentPaid = currentPaid.add(paidAmount);
            }
            result.getData().get(0).setCurrentPaid(currentPaid);

            /* 计算总合计 */
            SOCp153151Bean totalBean = this.getTotalInfo(pageParam);
            result.getData().get(0).setTotalPaid(totalBean.getTotalPaid());
        }

        return result;
    }

//    /**  页面添加导出按钮 modify by renyi on 2016/8/10 start */
//    /**
//     * 导出
//     *
//     * @return
//     */
//    @Transactional(readOnly = true)
//
//    public void export(OutputStream ouputStream, BasePageParam pageParam) throws FileNotFoundException {
//        InputStream in = SO153151Logic.class.getResourceAsStream("/temp/reportTemp153151.xlsx");
//        ExcelWrite excelWrite = new JxlsExcelWrite(in, new BufferedOutputStream(ouputStream));
//        pageParam.setPaging(false);
//
//        List<SOCp153151Bean> dataList = this.findPageList(pageParam, SOCp153151Bean.class);//  从数据查询的数据
//        if (!CollectionUtils.isEmpty(dataList)) {
//            // 支付方式
//            Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
//            // 金额类型
//            Map<String, String> amountTypeMap = CodeMasterManager.findCodeMasterMap("AmountType");
//            for (SOCp153151Bean bean : dataList) {
//                // 支付方式
//                if (!StringUtil.isNullOrEmpty(String.valueOf(bean.getPaidType()))) {
//                    String paidType = paidTypeMap.get(bean.getPaidType() + "");
//                    if (!StringUtil.isEmpty("paidType")) {
//                        bean.setPaidTypeStr(paidType);
//                    }
//                }
//
//                // 金额类型
//                if (!StringUtil.isNullOrEmpty(StringUtil.toString(bean.getAmountType()))) {
//                    String amountType = amountTypeMap.get(bean.getAmountType() + "");
//                    if (!StringUtil.isEmpty("amountType")) {
//                        bean.setAmountTypeStr(amountType);
//                    }
//                }
//
//            }
//        }
//
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("list", dataList);
//        excelWrite.excelWrite(paramMap);
//    }
//    /**  页面添加导出按钮 modify by renyi on 2016/8/10 end */


    /**
     * 设置参数
     *
     * @param pageParam
     * @return
     */
    public BasePageParam setBasePageParam(BasePageParam pageParam) {
        // 验证日期
        boolean flag = CheckUtil.checkDate(StringUtil.toSafeString(pageParam.getFilterMap().get("paidTimeStart"))
                , StringUtil.toSafeString(pageParam.getFilterMap().get("paidTimeEnd")));
        if (!flag) {
            throw new BusinessException("日期选择不合理");
        }
        //订单号
        DbUtils.buildLikeCondition(pageParam, "transCode", DbUtils.LikeMode.FRONT);
        //退款编码
        DbUtils.buildLikeCondition(pageParam, "refundCode", DbUtils.LikeMode.FRONT);
        //支付流水号
        DbUtils.buildLikeCondition(pageParam, "paidSeq", DbUtils.LikeMode.FRONT);
        //备注
        DbUtils.buildLikeCondition(pageParam, "remark", DbUtils.LikeMode.PARTIAL);
        
        /** Modify for Bug #3717 by li_huiqian on 20161124 start**/
        String refundCode = pageParam.getFilterMap().get("refundCode").toString();
        if (refundCode.contains(StringConst.UNDERLINE)) {
        	refundCode = refundCode.replace(StringConst.EXCLAMATION + StringConst.UNDERLINE, "\\" + StringConst.UNDERLINE);
        }
        pageParam.getFilterMap().put("refundCode", refundCode);
        /** Modify for Bug #3717 by li_huiqian on 20161124 end**/

        String transType = StringUtil.toSafeString(pageParam.getFilterMap().get("transType"));
        if (!StringUtil.isNullOrEmpty(transType)) {
            String[] transTypes = transType.split(",");
            pageParam.getFilterMap().put("transTypes", transTypes);
        }
        String amountType = StringUtil.toSafeString(pageParam.getFilterMap().get("amountType"));
        if (!StringUtil.isNullOrEmpty(amountType)) {
            String[] amountTypes = amountType.split(",");
            pageParam.getFilterMap().put("amountTypes", amountTypes);
        }
        String paidType = StringUtil.toSafeString(pageParam.getFilterMap().get("paidType"));
        if (!StringUtil.isNullOrEmpty(paidType)) {
            String[] paidTypes = paidType.split(",");
            pageParam.getFilterMap().put("paidTypes", paidTypes);
        }
        return pageParam;
    }

    /**
     * 获取总合计
     *
     * @param pageParam
     * @return 总合计
     */
    @Transactional(readOnly = true)
    public SOCp153151Bean getTotalInfo(BasePageParam pageParam) {
        return super.findOne(SqlId.SQL_ID_GET_TOTAL_INFO, pageParam);
    }

    /**
     * 导出为Excel文件
     *
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
        setBasePageParam(pageParam);

        BigDecimal totalPaid = BigDecimal.ZERO;

        List<SOCp153151Bean> dataList = this.findPageList(pageParam, SOCp153151Bean.class);//  从数据查询的数据
        if (!CollectionUtils.isEmpty(dataList)) {
            // 支付方式
            Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
            // 金额类型
            Map<String, String> amountTypeMap = CodeMasterManager.findCodeMasterMap("AmountType");
            // xlsx序列
            Integer xlsxNo = NumberConst.IntDef.INT_ONE;
            for (SOCp153151Bean bean : dataList) {
                // 设置xlsx序列
                bean.setXlsxNo(xlsxNo++);
                // 支付日期 转换 日期->String
                String paidTimeStr = DateTimeUtil.formatDate(CheckUtil.FORMAT_YYYYMMDD_HHMMSS, bean.getPaidTime());
                bean.setPaidTimeStr(paidTimeStr);
                // 支付方式
                if (!StringUtil.isNullOrEmpty(String.valueOf(bean.getPaidType()))) {
                    String paidType = paidTypeMap.get(bean.getPaidType() + "");
                    if (!StringUtil.isEmpty("paidType")) {
                        bean.setPaidTypeStr(paidType);
                    }
                }
                // 金额类型
                if (!StringUtil.isNullOrEmpty(StringUtil.toString(bean.getAmountType()))) {
                    String amountType = amountTypeMap.get(bean.getAmountType() + "");
                    if (!StringUtil.isEmpty("amountType")) {
                        bean.setAmountTypeStr(amountType);
                    }
                }
            }

            /* 计算总合计 */
            SOCp153151Bean totalBean = this.getTotalInfo(pageParam);
            totalPaid = totalBean.getTotalPaid();
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("list", dataList);
        paramMap.put("totalPaid", totalPaid);

        List<Map<String, ?>> listParam = new ArrayList<>();
        paramMap.put("sheetName", "支付一览列表数据");
        listParam.add(paramMap);
        return listParam;
    }

}
