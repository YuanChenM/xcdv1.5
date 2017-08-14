package com.msk.so.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.so.bean.SO153121Bean;
import com.msk.so.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 买手资金池
 * Created by wu_honglei on 2016/5/9.
 */

@Service
public class SO153121Logic extends ExcelAsyncPrintFasterService {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO153121Logic.class);

    /**
     * SqlId. sql定义
     */
    interface SqlId {
        String SQL_ID_GET_CURRENT_INFO = "getCurrentInfo";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 初始化单元格的 数据    标题  以及对应的数据
     */
    private static Map<String, String> cellMap = new LinkedHashMap<>();

    static {
        cellMap.put("businessMainCode", "卖家编码");
        cellMap.put("businessMainName", "卖家名称");
        cellMap.put("businessAssistantCode", "买手编码");
        cellMap.put("businessAssistantName", "买手名称");
        cellMap.put("transCode", "囤货/分销订单编码");
        cellMap.put("paidType", "支付方式");
        cellMap.put("orderAmount", "囤货/分销金额");
        cellMap.put("actualDue", "应收金额");
        cellMap.put("actualPaid", "已收金额");
        cellMap.put("actualReceiveable", "应付金额");
        cellMap.put("actualReceived", "已付金额");
        cellMap.put("reliefAmount", "减/退金额");
        cellMap.put("balance", "结余金额");
        cellMap.put("settlementStatus", "结算状态");
    }


//    /**
//     * 导出
//     *
//     * @return
//     */
//    @Transactional(readOnly = true)
//    public void export(BasePageParam pageParam, OutputStream ouputStream) throws IOException {
//        pageParam.setPaging(false);
//        InputStream in = SO153121Logic.class.getResourceAsStream("/temp/reportTemp153121.xlsx");
//        ExcelWrite excelWrite = new JxlsExcelWrite(in, new BufferedOutputStream(ouputStream));
//        List<SO153121Bean> dataList = this.findPageList(pageParam, SO153121Bean.class);//  从数据查询的数据
//        BigDecimal currentOrderAmount = BigDecimal.ZERO;
//        BigDecimal currentActualDue = BigDecimal.ZERO;
//        BigDecimal currentActualPaid = BigDecimal.ZERO;
//        BigDecimal currentActualReceiveable = BigDecimal.ZERO;
//        BigDecimal currentActualReceived = BigDecimal.ZERO;
//        BigDecimal currentReliefAmount = BigDecimal.ZERO;
//        BigDecimal currentBalance = BigDecimal.ZERO;
//
//
//        if (!CollectionUtils.isEmpty(dataList)) {
//            // 支付方式
//            Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
//            // 结算状态
//            Map<String, String> settlementStatusMap = CodeMasterManager.findCodeMasterMap("SettlementStatus");
//            for (SO153121Bean bean : dataList) {
//                // 支付方式
//                if (!StringUtil.isNullOrEmpty(bean.getPaidType())) {
//                    String paidType = paidTypeMap.get(bean.getPaidType());
//                    if (null != paidType) {
//                        bean.setPaidType(paidType);
//                    }
//                }
//                // 结算状态
//                String settlementStatus = settlementStatusMap.get(bean.getSettlementStatus() + "");
//                if (null != settlementStatus) {
//                    bean.setSettlementStatusStr(settlementStatus);
//                } else {
//                    bean.setSettlementStatusStr("");
//                }
//            }
//
//            // 查询合计项
//            SO153121Bean totalBen = getCurrentInfo(pageParam);
//            // 囤货/分销金额 合计
//            currentOrderAmount = totalBen.getCurrentOrderAmount();
//            // 应收金额 合计
//            currentActualDue =  totalBen.getCurrentActualDue();
//            // 已收金额 合计
//            currentActualPaid =  totalBen.getCurrentActualPaid();
//            // 应付金额 合计
//            currentActualReceiveable =   totalBen.getCurrentActualReceiveable();
//            // 已付金额 合计
//            currentActualReceived =   totalBen.getCurrentActualReceived();
//            //  减/退金额  合计
//            currentReliefAmount =   totalBen.getCurrentReliefAmount();
//            //  结余金额  合计
//            currentBalance =  totalBen.getCurrentBalance();
//        }
//
//
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("list", dataList);
//
//        paramMap.put("currentOrderAmount", currentOrderAmount);
//        paramMap.put("currentActualDue", currentActualDue);
//        paramMap.put("currentActualPaid", currentActualPaid);
//        paramMap.put("currentActualReceiveable", currentActualReceiveable);
//        paramMap.put("currentActualReceived", currentActualReceived);
//        paramMap.put("currentReliefAmount", currentReliefAmount);
//        paramMap.put("currentBalance", currentBalance);
//
//        excelWrite.excelWrite(paramMap);
//    }


//    /**
//     * @param basePageParam
//     * @return
//     * @throws Exception
//     */
//    @Transactional(readOnly = true)
//    public Workbook exportPOI(BasePageParam basePageParam) throws Exception {
//        basePageParam.setPaging(false);
//        // 支付方式
//        Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
//        // 结算状态
//        Map<String, String> settlementStatusMap = CodeMasterManager.findCodeMasterMap("SettlementStatus");
//
//        Workbook workBook = new SXSSFWorkbook(NumberConst.IntDef.INT_HUNDRED); // 创建一个工作簿
//        Sheet sheet = workBook.createSheet("买手明细表");// 创建一个工作表，名为：第一页
//        CellStyle contentStyle = workBook.createCellStyle();// 背景色  内容
//        contentStyle.setBorderBottom(CellStyle.BORDER_THIN); // 底部边框
//        contentStyle.setBorderRight(CellStyle.BORDER_THIN); // 右边边框
//        CellStyle tiltleStyle = workBook.createCellStyle();// 背景色  标题
//        tiltleStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
//        tiltleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        tiltleStyle.setAlignment(CellStyle.ALIGN_CENTER);// 字体居中
//        tiltleStyle.setBorderRight(contentStyle.getBorderRight());
//        tiltleStyle.setBorderBottom(contentStyle.getBorderBottom());
//        int cellSize = cellMap.size();
//        for (int i = 0; i < cellSize; i++) {//  设置多少列
//            sheet.setColumnWidth((short) i, 5000);
//        }
//        Row title = sheet.createRow((short) 0);// 创建一个单元格，从0开始
//        Cell cell[] = new Cell[cellSize];// 构造一个数组设置第一行之后的单元格
//        int cellTitleIndex = 0;
//        for (String titleName : cellMap.values()) {
//            cell[cellTitleIndex] = title.createCell(cellTitleIndex);
//            cell[cellTitleIndex].setCellStyle(tiltleStyle);
//            cell[cellTitleIndex].setCellValue(titleName);
//            cellTitleIndex++;
//        }
//        List<SO153121Bean> dataList = this.findPageList(basePageParam, SO153121Bean.class);//  从数据查询的数据
//        short fmt = HSSFDataFormat.getBuiltinFormat("0.00");
//        CellStyle style = workBook.createCellStyle();
//        style.setBorderBottom(CellStyle.BORDER_THIN);
//        style.setBorderLeft(CellStyle.BORDER_THIN);
//        style.setBorderTop(CellStyle.BORDER_THIN);
//        style.setBorderRight(CellStyle.BORDER_THIN);
//        if (!CollectionUtils.isEmpty(dataList)) {
//            // 循环list中的数据  一个对象一行数据
//            for (int i = 0; i < dataList.size(); i++) {
//                SO153121Bean bean = dataList.get(i);
//                Row dataRow = sheet.createRow(i + NumberConst.IntDef.INT_ONE);
//                Cell data[] = new Cell[cellSize];
//                int cellDataIndex = 0;
//                for (String tableProperty : cellMap.keySet()) {
//                    data[cellDataIndex] = dataRow.createCell(cellDataIndex);
//                    data[cellDataIndex].setCellStyle(contentStyle);
//                    cellDataIndex++;
//                }
//
//                int count = NumberConst.IntDef.INT_ZERO;
//                data[count].setCellValue(bean.getBusinessMainCode());  // 卖家编码
//                count++;
//                data[count].setCellValue(bean.getBusinessMainName());    // 卖家名称
//                count++;
//                data[count].setCellValue(bean.getBusinessAssistantCode());    // 买手编码
//                count++;
//                data[count].setCellValue(bean.getBusinessAssistantName());    // 买手名称
//                count++;
//                data[count].setCellValue(bean.getTransCode()); // 囤货/分销订单编码
//                // 支付方式
//                count++;
//                if (!StringUtil.isEmpty(bean.getPaidType())) {
//                    String paidType = paidTypeMap.get(bean.getPaidType());
//                    data[count].setCellValue(paidType);
//                } else {
//                    data[count].setCellValue("");
//                }
//                //  囤货/分销金额 数值类型
//                count++;
//                if (null == bean.getOrderAmount()) {
//                    data[count].setCellValue("");
//                } else {
//                    data[count].setCellValue(Double.parseDouble(bean.getOrderAmount() + ""));
//                    style.setDataFormat(fmt);
//                    data[count].setCellStyle(style);
//                }
//                //  应收金额 数值类型
//                count++;
//                if (null == bean.getActualDue()) {
//                    data[count].setCellValue("");
//                } else {
//                    data[count].setCellValue(Double.parseDouble(bean.getActualDue() + ""));
//                    style.setDataFormat(fmt);
//                    data[count].setCellStyle(style);
//                }
//                //  已收金额 数值类型
//                count++;
//                if (null == bean.getActualPaid()) {
//                    data[count].setCellValue("");
//                } else {
//                    data[count].setCellValue(Double.parseDouble(bean.getActualPaid() + ""));
//                    style.setDataFormat(fmt);
//                    data[count].setCellStyle(style);
//                }
//                //  应付金额 数值类型
//                count++;
//                if (null == bean.getActualReceiveable()) {
//                    data[count].setCellValue("");
//                } else {
//                    data[count].setCellValue(Double.parseDouble(bean.getActualReceiveable() + ""));
//                    style.setDataFormat(fmt);
//                    data[count].setCellStyle(style);
//                }
//                //  已付金额 数值类型
//                count++;
//                if (null == bean.getActualReceived()) {
//                    data[count].setCellValue("");
//                } else {
//                    data[count].setCellValue(Double.parseDouble(bean.getActualReceived() + ""));
//                    style.setDataFormat(fmt);
//                    data[count].setCellStyle(style);
//                }
//                //  减/退金额 数值类型
//                count++;
//                if (null == bean.getReliefAmount()) {
//                    data[count].setCellValue("");
//                } else {
//                    data[count].setCellValue(Double.parseDouble(bean.getReliefAmount() + ""));
//                    style.setDataFormat(fmt);
//                    data[count].setCellStyle(style);
//                }
//                //  结余金额 数值类型
//                count++;
//                if (null == bean.getBalance()) {
//                    data[count].setCellValue("");
//                } else {
//                    data[count].setCellValue(Double.parseDouble(bean.getBalance() + ""));
//                    style.setDataFormat(fmt);
//                    data[count].setCellStyle(style);
//                }
//                // 结算状态
//                count++;
//                String settlementStatus = settlementStatusMap.get(bean.getSettlementStatus() + "");
//                if (null != settlementStatus) {
//                    data[count].setCellValue(settlementStatus);
//                } else {
//                    data[count].setCellValue("");
//                }
//
//            }
//        }
//
//        // 查询合计项
//        SO153121Bean totalBen = getCurrentInfo(basePageParam);
//        // 合计行
//        Row lastRow = sheet.createRow(dataList.size() + NumberConst.IntDef.INT_ONE);
//        Cell data[] = new Cell[NumberConst.IntDef.INT_FOURTEEN];
//        int countSum = NumberConst.IntDef.INT_FIVE;
//        data[countSum] = lastRow.createCell(countSum);
//        data[countSum].setCellStyle(tiltleStyle);
//        data[countSum].setCellValue("合计:");
//        // 囤货/分销金额 合计
//        countSum++;
//        style.setDataFormat(fmt);
//        data[countSum] = lastRow.createCell(countSum);
//        data[countSum].setCellStyle(style);
//        data[countSum].setCellValue(Double.parseDouble(totalBen.getCurrentOrderAmount() + ""));
//        // 应收金额 合计
//        countSum++;
//        style.setDataFormat(fmt);
//        data[countSum] = lastRow.createCell(countSum);
//        data[countSum].setCellStyle(style);
//        data[countSum].setCellValue(Double.parseDouble(totalBen.getCurrentActualDue() + ""));
//        // 已收金额 合计
//        countSum++;
//        style.setDataFormat(fmt);
//        data[countSum] = lastRow.createCell(countSum);
//        data[countSum].setCellStyle(style);
//        data[countSum].setCellValue(Double.parseDouble(totalBen.getCurrentActualPaid() + ""));
//        // 应付金额 合计
//        countSum++;
//        style.setDataFormat(fmt);
//        data[countSum] = lastRow.createCell(countSum);
//        data[countSum].setCellStyle(style);
//        data[countSum].setCellValue(Double.parseDouble(totalBen.getCurrentActualReceiveable() + ""));
//        // 已付金额 合计
//        countSum++;
//        style.setDataFormat(fmt);
//        data[countSum] = lastRow.createCell(countSum);
//        data[countSum].setCellStyle(style);
//        data[countSum].setCellValue(Double.parseDouble(totalBen.getCurrentActualReceived() + ""));
//        //  减/退金额  合计
//        countSum++;
//        style.setDataFormat(fmt);
//        data[countSum] = lastRow.createCell(countSum);
//        data[countSum].setCellStyle(style);
//        data[countSum].setCellValue(Double.parseDouble(totalBen.getCurrentReliefAmount() + ""));
//        //  结余金额  合计
//        countSum++;
//        style.setDataFormat(fmt);
//        data[countSum] = lastRow.createCell(countSum);
//        data[countSum].setCellStyle(style);
//        data[countSum].setCellValue(Double.parseDouble(totalBen.getCurrentBalance() + ""));
//
//        return workBook;
//    }

    @Transactional(readOnly = true)
    public SO153121Bean getCurrentInfo(BasePageParam pageParam) {
        return super.findOne(SO153121Logic.SqlId.SQL_ID_GET_CURRENT_INFO, pageParam);
    }


    /**
     * 设置参数
     *
     * @param pageParam
     * @return
     */
    public BasePageParam setBasePageParam(BasePageParam pageParam) {
        // 验证日期
        boolean createFlag = CheckUtil.checkDate(StringUtil.toSafeString(pageParam.getFilterMap().get("createTimeStart"))
                , StringUtil.toSafeString(pageParam.getFilterMap().get("createTimeEnd")));
        boolean tranFlag = CheckUtil.checkDate(StringUtil.toSafeString(pageParam.getFilterMap().get("tranTimeStart"))
                , StringUtil.toSafeString(pageParam.getFilterMap().get("tranTimeEnd")));
        if (!createFlag || !tranFlag) {

            throw new BusinessException("日期选择不合理");
        }

        // 卖家名称
        String businessMainName = StringUtil.toSafeString(pageParam.getFilterMap().get("businessMainName"));
        pageParam.getFilterMap().put("businessMainName", CheckUtil.convert(businessMainName));
        // 卖家编码
        String businessMainCode = StringUtil.toSafeString(pageParam.getFilterMap().get("businessMainCode"));
        pageParam.getFilterMap().put("businessMainCode", CheckUtil.convert(businessMainCode));

        if ("-".equals(businessMainName) && "-".equals(businessMainCode)) {
            pageParam.getFilterMap().put("mainflag", true);
        }
        DbUtils.buildLikeCondition(pageParam, "businessMainName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "businessMainCode", DbUtils.LikeMode.FRONT);

        //囤货/分销订单编码
        DbUtils.buildLikeCondition(pageParam, "transCode", DbUtils.LikeMode.FRONT);

        // 买手编码和名称
        String businessAssistantName = StringUtil.toSafeString(pageParam.getFilterMap().get("businessAssistantName"));
        String businessAssistantCode = StringUtil.toSafeString(pageParam.getFilterMap().get("businessAssistantCode"));
        pageParam.getFilterMap().put("businessAssistantName", CheckUtil.convert(businessAssistantName));
        pageParam.getFilterMap().put("businessAssistantCode", CheckUtil.convert(businessAssistantCode));
        DbUtils.buildLikeCondition(pageParam, "businessAssistantName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "businessAssistantCode", DbUtils.LikeMode.FRONT);

        // 交易类型
//        String transType = StringUtil.toSafeString(pageParam.getFilterMap().get("transType"));
//        if (!StringUtil.isNullOrEmpty(transType)) {
//            String[] transTypes = transType.split(",");
//            pageParam.getFilterMap().put("transTypes", transTypes);
//        }

        // 结算状态
        String settlementStatus = StringUtil.toSafeString(pageParam.getFilterMap().get("settlementStatus"));
        if (!StringUtil.isNullOrEmpty(settlementStatus)) {
            String[] settlementStatusArr = settlementStatus.split(",");
            pageParam.getFilterMap().put("settlementStatusArr", settlementStatusArr);
        }

        // 支付方式
        String paidType = StringUtil.toSafeString(pageParam.getFilterMap().get("paidType"));
        if (!StringUtil.isNullOrEmpty(paidType)) {
            String[] paidTypes = paidType.split(",");
            pageParam.getFilterMap().put("paidTypes", paidTypes);
        }
        return pageParam;
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

        BigDecimal currentOrderAmount = BigDecimal.ZERO;
        BigDecimal currentActualDue = BigDecimal.ZERO;
        BigDecimal currentActualPaid = BigDecimal.ZERO;
        BigDecimal currentActualReceiveable = BigDecimal.ZERO;
        BigDecimal currentActualReceived = BigDecimal.ZERO;
        BigDecimal currentReliefAmount = BigDecimal.ZERO;
        BigDecimal currentBalance = BigDecimal.ZERO;

        List<SO153121Bean> dataList = super.findPageList(pageParam, SO153121Bean.class);
        if (!CollectionUtils.isEmpty(dataList)) {
            // 支付方式
            Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
            // 结算状态
            Map<String, String> settlementStatusMap = CodeMasterManager.findCodeMasterMap("SettlementStatus");
            // xlsx序列
            Integer xlsxNo = NumberConst.IntDef.INT_ONE;
            for (SO153121Bean bean : dataList) {
                // 设置xlsx序列
                bean.setXlsxNo(xlsxNo++);
                // 支付方式
                if (!StringUtil.isNullOrEmpty(bean.getPaidType())) {
                    String paidType = paidTypeMap.get(bean.getPaidType());
                    if (null != paidType) {
                        bean.setPaidType(paidType);
                    }
                }
                // 结算状态
                String settlementStatus = settlementStatusMap.get(bean.getSettlementStatus() + "");
                if (null != settlementStatus) {
                    bean.setSettlementStatusStr(settlementStatus);
                } else {
                    bean.setSettlementStatusStr("");
                }
            }

            // 查询合计项
            SO153121Bean totalBen = getCurrentInfo(pageParam);
            // 囤货/分销金额 合计
            currentOrderAmount = totalBen.getCurrentOrderAmount();
            // 应收金额 合计
            currentActualDue = totalBen.getCurrentActualDue();
            // 已收金额 合计
            currentActualPaid = totalBen.getCurrentActualPaid();
            // 应付金额 合计
            currentActualReceiveable = totalBen.getCurrentActualReceiveable();
            // 已付金额 合计
            currentActualReceived = totalBen.getCurrentActualReceived();
            //  减/退金额  合计
            currentReliefAmount = totalBen.getCurrentReliefAmount();
            //  结余金额  合计
            currentBalance = totalBen.getCurrentBalance();
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("list", dataList);

        paramMap.put("currentOrderAmount", currentOrderAmount);
        paramMap.put("currentActualDue", currentActualDue);
        paramMap.put("currentActualPaid", currentActualPaid);
        paramMap.put("currentActualReceiveable", currentActualReceiveable);
        paramMap.put("currentActualReceived", currentActualReceived);
        paramMap.put("currentReliefAmount", currentReliefAmount);
        paramMap.put("currentBalance", currentBalance);

        List<Map<String, ?>> listParam = new ArrayList<>();
        paramMap.put("sheetName", "买手列表数据");
        listParam.add(paramMap);
        return listParam;
    }


}
