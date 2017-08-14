package com.msk.price.excel.read;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.utils.PoiUtils;
import com.msk.price.bean.SP171170Bean;
import com.msk.price.bean.SP171170ExcelBean;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by zhu_kai1 on 2016/9/23.
 */
public class SP171170ReadExcelService {

    private static Logger logger = LoggerFactory.getLogger(SP171170ReadExcelService.class);

    // 从模板的Index 为5，第6行开始遍历获取数据
    private static final int ROW_START_INDEX = 5;
    // 导入excel时，从第九列（从0开始）开始获取大宗超级的公斤价
    private static final int CELL_START_NUM = 10;
    // 截止到28列为结束
    private static final int CELL_END_NUM = 30;

    private Workbook workbook;
    private Set<String> dataErrorMessage;  // 每个sheet中数据的正确性错误信息
    private Set<String> formatErrorMessage;// 每个sheet的模板格式错误信息
    private Set<String> endErrorMessage;   // 每个sheet的截止符（end）的错误信息
    // 需求等级信息
    private Map<String, String> temp;

    public SP171170ReadExcelService(Workbook workbook, Map<String, String> temp) {
        this.workbook = workbook;
        this.dataErrorMessage = new TreeSet<>();
        this.formatErrorMessage = new TreeSet<>();
        this.endErrorMessage = new TreeSet<>();
        this.temp = temp;
    }

    public Set<String> getDataErrorMessage() {
        return dataErrorMessage;
    }

    public Set<String> getFormatErrorMessage() {
        return formatErrorMessage;
    }

    public Set<String> getEndErrorMessage() {
        return endErrorMessage;
    }

    public List<SP171170ExcelBean> readExcel() {
        int sheetsNumber = workbook.getNumberOfSheets();
        List<SP171170ExcelBean> excelList = new ArrayList<>();
        for (int i = 0; i < sheetsNumber; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            this.checkSheetTitle(sheet);
            this.checkSheetFormat(sheet);
        }
        if (CollectionUtils.isEmpty(formatErrorMessage)) {
            for (int j = 0; j < sheetsNumber; j++) {
                Sheet sheet = workbook.getSheetAt(j);
                excelList.addAll(this.readProduct(sheet));
            }
        }
        return excelList;
    }

    /**
     * 获取产品信息
     *
     * @param sheet
     * @return
     */
    private List<SP171170ExcelBean> readProduct(Sheet sheet) {
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        SP171170ExcelBean excelBean = null;
        List<SP171170ExcelBean> tempList = new ArrayList<>();
        // regexNum 校验格式为YY-MM(D-D日)或者YY-MM(DD-月底)  例如 16-10（1-5日）、16-08（20-25日）、16-10（26-月底）
        String regexNum = "\\d{2}\\-(0?[1-9]|1[0-2])\\(((0?[1-9])|((1|2)[0-9])|30|31)\\-([月][底]|((0?[1-9])|((1|2)[0-9])|30|31)+[日])\\)";
        // regexStrs 校验格式为中文、英文、下划线+YY-MM(D-D日)或者YY-MM(DD-月底)  例如：价盘详情16-10（20-25日）、价盘详情xx16-10（20-25日）
        String regexStrs = "[\\w\\u4e00-\\u9fa5]{0,}" + regexNum;
        String regexStr = "(" + regexNum + ")|(" + regexStrs + ")$";
        Pattern p = Pattern.compile(regexStr);
        String pricecyclePeriod = null;
        try {
            String priceTitle = PoiUtils.getCellValue(sheet, NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_ZERO);
            if (p.matcher(priceTitle).matches()) {
                pricecyclePeriod = getPricecyclePeriod(priceTitle);
            } else {
                formatErrorMessage.add("工作表【" + sheet.getSheetName() + "】的第1行的标题格式不对,请下载对应周期的模板！</br>");
            }
        } catch (Exception e) {
            formatErrorMessage.add("工作表【" + sheet.getSheetName() + "】的第1行的标题格式不对,请下载对应周期的模板！</br>");
        }
        Boolean endFlag = false;
        // 从模板的第5行开始遍历获取数据
        for (int i = ROW_START_INDEX; i <= rowNum; i++) {
            excelBean = new SP171170ExcelBean();
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            if (PoiUtils.getCellValue(sheet, row.getRowNum(), 0).toUpperCase().equals("END")) {
                endFlag = true;
                break;
            }
            //价盘周期
            if (!StringUtil.isNullOrEmpty(pricecyclePeriod)) {
                excelBean.setPricecyclePeriod(pricecyclePeriod);
            } else {
                formatErrorMessage.add("工作表【" + sheet.getSheetName() + "】的第1行的标题格式不对,请下载对应周期的模板！</br>");
            }
            int cellIndex = NumberConst.IntDef.INT_ZERO;
            // 区域
            String logiareaName = PoiUtils.getCellValue(sheet, row.getRowNum(), cellIndex++);
            if (!StringUtil.isNullOrEmpty(logiareaName)) {
                excelBean.setLgcsAreaName(logiareaName);
            }
            // 产品编码
            String pdcode = PoiUtils.getCellValue(sheet, row.getRowNum(), cellIndex++);
            if (!StringUtil.isNullOrEmpty(pdcode)) {
                excelBean.setPdCode(pdcode);
            }
            // 产品一级分类
            String classesName = PoiUtils.getCellValue(sheet, row.getRowNum(), cellIndex++);
            // 产品二级分类
            String machiningName = PoiUtils.getCellValue(sheet, row.getRowNum(), cellIndex++);
            // 品种
            String breedName = PoiUtils.getCellValue(sheet, row.getRowNum(), cellIndex++);
            // 特征
            String featureName = PoiUtils.getCellValue(sheet, row.getRowNum(), cellIndex++);
            // 净重
            String weights = PoiUtils.getCellValue(sheet, row.getRowNum(), cellIndex++);
            // 等级
            String gradeName = PoiUtils.getCellValue(sheet, row.getRowNum(), cellIndex++);
            // 需求等级
            String wayName = PoiUtils.getCellValue(sheet, row.getRowNum(), cellIndex++);
            // 营销状态
            String marketingName = PoiUtils.getCellValue(sheet, row.getRowNum(), cellIndex++);
            if (!StringUtil.isNullOrEmpty(marketingName)) {
                excelBean.setMarketingName(marketingName);
            }
            if (!StringUtil.isNullOrEmpty(wayName)) {
                excelBean.setWayName(wayName);
            }
            if (!StringUtil.isNullOrEmpty(classesName)) {
                excelBean.setClassesName(classesName);
            }
            if (!StringUtil.isNullOrEmpty(machiningName)) {
                excelBean.setMachiningName(machiningName);
            }
            if (!StringUtil.isNullOrEmpty(breedName)) {
                excelBean.setBreedName(breedName);
            }
            if (!StringUtil.isNullOrEmpty(featureName)) {
                excelBean.setFeatureName(featureName);
            }
            if (!StringUtil.isNullOrEmpty(weights)) {
                excelBean.setWeightName(weights);
            }
            if (!StringUtil.isNullOrEmpty(gradeName)) {
                excelBean.setGradeName(gradeName);
            }
            excelBean.setSheetName(sheet.getSheetName());
            // 构造营销状态的数据
            boolean priceFlag = checkExcelData(sheet, row, excelBean);

            constructPriceData(sheet, row, excelBean);
            tempList.add(excelBean);
        }
        if (!endFlag) {
            endErrorMessage.add("工作表【" + sheet.getSheetName() + "】没有截止符(END),请核实!</br>");
        }
        return tempList;
    }


    /**
     * 校验excel价盘价格是否正确
     *
     * @param sheet
     * @param row
     * @return
     */
    private boolean checkExcelData(Sheet sheet, Row row, SP171170ExcelBean excelBean) {
        // 验证数字和-
        String regexStr = "\\d+(\\.\\d+)?$|\\-";
        Pattern p = Pattern.compile(regexStr);
        String errorMsg = "";
        boolean contains = temp.containsKey(excelBean.getWayName());
        // 需求等级
        if (!contains) {
            errorMsg = "工作表【" + excelBean.getSheetName() + "】的需求等级【" + excelBean.getWayName() + "】不存在，请核实！</br>";
            dataErrorMessage.add(errorMsg);
        }

        for (int cellNum = CELL_START_NUM; cellNum < CELL_END_NUM; cellNum++) {
            int errorNum = row.getRowNum() + 1;
            int errorCellNum = cellNum + 1;
            // 按正常逻辑从第1列到29列应该都有数据，偌某一列没有数据，那么需要报错提示
            if (null == row.getCell(cellNum)) {
                errorMsg = "工作表【" + sheet.getSheetName() + "】的第" + errorNum + "行的第" + errorCellNum + "列数据只能为数字或'-'</br>";
                dataErrorMessage.add(errorMsg);
                continue;
            }
            String value = PoiUtils.getCellValue(sheet, row.getRowNum(), cellNum);
            if (!p.matcher(value).matches()) {
                logger.info(sheet.getSheetName() + "的" + errorNum + "行的" + errorCellNum + "列的数据值为{}", value);
                errorMsg = "工作表【" + sheet.getSheetName() + "】的第" + errorNum + "行的第" + errorCellNum + "列数据只能为数字或'-'</br>";
                dataErrorMessage.add(errorMsg);
                continue;
            }
        }
        if (!StringUtil.isNullOrEmpty(errorMsg)) {
            return false;
        }
        return true;
    }

    /**
     * 校验表头数据
     *
     * @param sheet
     * @return
     */
    private boolean checkSheetTitle(Sheet sheet) {
        Row row = sheet.getRow(NumberConst.IntDef.INT_ZERO);
        //价盘周期（价盘周期是从excel的标题中截取出来的）
        String pricecyclePeriod = null;
        String titleMsg = "";
        if (row != null) {
            try {
                // regexNum 校验格式为YY-MM(D-D日)或者YY-MM(DD-月底)  例如 16-10（1-5日）、16-08（20-25日）、16-10（26-月底）
                String regexNum = "\\d{2}\\-(0?[1-9]|1[0-2])\\(((0?[1-9])|((1|2)[0-9])|30|31)\\-([月][底]|((0?[1-9])|((1|2)[0-9])|30|31)+[日])\\)";
                // regexStrs 校验格式为中文、英文、下划线+YY-MM(D-D日)或者YY-MM(DD-月底)  例如：价盘详情16-10（20-25日）、价盘详情xx16-10（20-25日）
                String regexStrs = "[\\w\\u4e00-\\u9fa5]{0,}" + regexNum;
                String regexStr = "(" + regexNum + ")|(" + regexStrs + ")$";
                Pattern p = Pattern.compile(regexStr);
                String priceTitle = PoiUtils.getCellValue(sheet, NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_ZERO);
                pricecyclePeriod = getPricecyclePeriod(priceTitle);
                if (!p.matcher(priceTitle).matches() || StringUtil.isNullOrEmpty(pricecyclePeriod)) {
                    formatErrorMessage.add("工作表【" + sheet.getSheetName() + "】的第1行的标题格式不对,请下载对应周期的模板！</br>");
                }
            } catch (Exception e) {
                titleMsg = "工作表【" + sheet.getSheetName() + "】第1行的标题格式不对,请下载对应周期的模板！</br>";
                formatErrorMessage.add(titleMsg);
            }
        } else {
            titleMsg = "工作表【" + sheet.getSheetName() + "】第1行的标题不能为空,请下载对应周期的模板！</br>";
            formatErrorMessage.add(titleMsg);
        }
        if (!StringUtil.isNullOrEmpty(titleMsg)) {
            return false;
        }
        return true;
    }

    private boolean checkSheetFormat(Sheet sheet) {
        String formatError = "";
        String area = "";
        String productCode = "";
        String pdClass = "";
        String pdMaching = "";
        String pdBreed = "";
        String featureName = "";
        String weights = "";
        String gradeName = "";
        String wayName = "";
        String marketingName = "";
        String wayGallery = "";
        Row row = sheet.getRow(NumberConst.IntDef.INT_ONE);
        if (null != row) {
            if (null != row.getCell(0) && row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING) {
                area = sheet.getRow(NumberConst.IntDef.INT_ONE).getCell(0).getStringCellValue();
            }
            if (null != row.getCell(1) && row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING) {
                productCode = sheet.getRow(NumberConst.IntDef.INT_ONE).getCell(1).getStringCellValue();
            }
            if (null != row.getCell(2) && row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
                pdClass = sheet.getRow(NumberConst.IntDef.INT_ONE).getCell(2).getStringCellValue();
            }
            if (null != row.getCell(3) && row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
                pdMaching = sheet.getRow(NumberConst.IntDef.INT_ONE).getCell(3).getStringCellValue();
            }
            if (null != row.getCell(4) && row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING) {
                pdBreed = sheet.getRow(NumberConst.IntDef.INT_ONE).getCell(4).getStringCellValue();
            }
            if (null != row.getCell(5) && row.getCell(5).getCellType() == Cell.CELL_TYPE_STRING) {
                featureName = sheet.getRow(NumberConst.IntDef.INT_ONE).getCell(5).getStringCellValue();
            }
            if (null != row.getCell(6) && row.getCell(6).getCellType() == Cell.CELL_TYPE_STRING) {
                weights = sheet.getRow(NumberConst.IntDef.INT_ONE).getCell(6).getStringCellValue();
            }
            if (null != row.getCell(7) && row.getCell(7).getCellType() == Cell.CELL_TYPE_STRING) {
                gradeName = sheet.getRow(NumberConst.IntDef.INT_ONE).getCell(7).getStringCellValue();
            }
            if (null != row.getCell(8) && row.getCell(8).getCellType() == Cell.CELL_TYPE_STRING) {
                wayName = sheet.getRow(NumberConst.IntDef.INT_ONE).getCell(8).getStringCellValue();
            }
            if (null != row.getCell(9) && row.getCell(9).getCellType() == Cell.CELL_TYPE_STRING) {
                marketingName = sheet.getRow(NumberConst.IntDef.INT_ONE).getCell(9).getStringCellValue();
            }
            if (null != row.getCell(10) && row.getCell(10).getCellType() == Cell.CELL_TYPE_STRING) {
                wayGallery = sheet.getRow(NumberConst.IntDef.INT_ONE).getCell(10).getStringCellValue();
            }
            if (!area.equals("区域")) {
                formatError += "A2单元格必须填区域";
            }
            if (!productCode.equals("产品编码")) {
                formatError += "B2单元格必须填产品编码";
            }
            if (!pdClass.equals("产品一级分类")) {
                formatError += "C2单元格必须填产品一级分类";
            }
            if (!pdMaching.equals("产品二级分类")) {
                formatError += "D2单元格必须填产品二级分类";
            }
            if (!pdBreed.equals("品种")) {
                formatError += "E2单元格必须填品种";
            }
            if (!featureName.equals("特征")) {
                formatError += "F2单元格必须填特征";
            }
            if (!weights.equals("净重（KG/箱）")) {
                formatError += "G2单元格必须填净重（KG/箱）";
            }
            if (!gradeName.equals("等级")) {
                formatError += "H2单元格必须填等级";
            }
            if (!wayName.equals("需求等级")) {
                formatError += "I2单元格必须填需求等级";
            }
            if (!marketingName.equals("营销状态")) {
                formatError += "J2单元格必须填营销状态";
            }
            if (!wayGallery.equals("营销通道")) {
                formatError += "K2单元格必须填营销通道";
            }
        }
        if (!StringUtil.isNullOrEmpty(formatError) || sheet.getRow(NumberConst.IntDef.INT_ONE) == null) {
            formatErrorMessage.add("工作表【" + sheet.getSheetName() + "】模板格式不对,请下载正确的模板!</br>");
            return false;
        }
        return true;
    }

    /**
     * 构造通道等级数据(价盘数据)
     *
     * @param row       行数据（IN）
     * @param excelBean （OUT）
     */
    private void constructPriceData(Sheet sheet, Row row, SP171170ExcelBean excelBean) {
        SP171170Bean tempSP171170Bean = null;
        List<SP171170Bean> list = new ArrayList<>();
        // 导入excel时，从第九列（从0开始）开始获取大宗超级的公斤价
        // 获取大宗超级订单往后的公斤价,格式为大宗公斤价、大宗箱价，大宗1级公斤价、大宗1级箱价....
        for (int cellNum = CELL_START_NUM; cellNum < CELL_END_NUM; cellNum += 2) {
            tempSP171170Bean = new SP171170Bean();
            tempSP171170Bean.setWayCode(temp.get(excelBean.getWayName()));
            tempSP171170Bean.setMarketingName(excelBean.getMarketingName());
            // 默认大宗超级订单（wayGradeCode=0）的,因为从第9列开始，11列为大宗1级（wayGradeCode=1），
            // 13列为大宗2级（wayGradeCode=2），以此类推..计算出每个等级的GradeCode
            int wayGradeCode = (cellNum - CELL_START_NUM) / 2;
            tempSP171170Bean.setWayName(excelBean.getWayName());
            tempSP171170Bean.setWayGradeCode(wayGradeCode);
            //公斤价
            BigDecimal priceofkg = StringUtil.toBigDecimal(PoiUtils.getCellValue(sheet, row.getRowNum(), cellNum));
            if (null != priceofkg) {
                tempSP171170Bean.setPriceofkg(priceofkg);
            }
            //箱价
            BigDecimal priceofbox = StringUtil.toBigDecimal(PoiUtils.getCellValue(sheet, row.getRowNum(), cellNum + 1));
            if (null != priceofbox) {
                tempSP171170Bean.setPriceofbox(priceofbox);
            }
            list.add(tempSP171170Bean);
        }
        excelBean.setSp171170BeanList(list);
    }

    /**
     * 截取模板的title以便获取价盘周期
     *
     * @param str
     * @return
     */
    public static String getPricecyclePeriod(String str) {
        String title = str.substring(str.indexOf("(") - 5, str.indexOf("("));
        String temp = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
        String value = "";
        String pricecyclePeriod = "";
        switch (temp) {
            case "1-5日":
                value = "1";
                break;
            case "6-10日":
                value = "2";
                break;
            case "11-15日":
                value = "3";
                break;
            case "16-20日":
                value = "4";
                break;
            case "21-25日":
                value = "5";
                break;
            case "26-月底":
                value = "6";
                break;
            default:
                value = "";
                break;
        }
        if (!StringUtil.isNullOrEmpty(value)) {
            pricecyclePeriod = title.split("-")[0] + title.split("-")[1] + value;
        }
        logger.info("价盘周期：{}", pricecyclePeriod);
        return pricecyclePeriod;
    }
}
