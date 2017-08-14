package com.msk.price.logic;

import com.hoperun.core.exception.BusinessException;
import com.msk.common.service.ExcelAsyncPrintByPoiService;
import com.msk.price.bean.SP171170Bean;
import com.msk.price.bean.SP171170Param;
import com.msk.price.utils.ExcelCopySheetUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhu_kai1 on 2016/9/9.
 */
@Component("SP171172Logic")
public class SP171172Logic extends ExcelAsyncPrintByPoiService {

    @Autowired
    private SP171170Logic sp171170Logic;

    @Override
    public void handleWorkBook(Workbook workbook, Object param) {
        Map<String,String> temp =(Map) param;
        SP171170Param sp171170Param = new SP171170Param();
        sp171170Param.setLgcsAreaCode(temp.get("lgcsAreaCode"));
        sp171170Param.setClassesCode(temp.get("classesCode"));
        sp171170Param.setMachiningCode(temp.get("machiningCode"));
        sp171170Param.setPricecyclePeriod(temp.get("pricecyclePeriod"));
        sp171170Param.setTitle(temp.get("title"));
        sp171170Param.setBreedName(temp.get("breedName"));
        sp171170Param.setPaging(false);
        List<SP171170Bean> list = sp171170Logic.findPageList(sp171170Param, SP171170Bean.class);

        // 构造不同通道数据
        Map<String,List<SP171170Bean>> resultMap = new HashMap<>();
        for (SP171170Bean bean : list){
            String key = bean.getLogiareaCode()+"_"+bean.getWayCode()+"_"+bean.getWayGradeName()+"("+bean.getLogiareaName()+")";
            if(resultMap.containsKey(key)){
                resultMap.get(key).add(bean);
            }else{
                List<SP171170Bean>  tempList = new ArrayList<>();
                tempList.add(bean);
                resultMap.put(key,tempList);
            }
        }

        Sheet sourceSheet = workbook.getSheetAt(0);
        for (String key : resultMap.keySet()){
            String sheetName = key.split("_")[2];
            Sheet sheet = workbook.createSheet(sheetName);
            try {
                // 复制sheet
               ExcelCopySheetUtil.copySheet(sheet,sourceSheet,workbook,workbook,true);
            } catch (Exception e) {
               throw new BusinessException(e.getMessage());
            }
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cell.setCellValue("价盘详情"+temp.get("title"));
            List<SP171170Bean> excelList = resultMap.get(key);
            // 显示通道箱数区间
            int rowIndex=3;// 从第3行
            getCell(workbook, sheet.getRow(rowIndex), 9, excelList.get(0).getSupOrder(), cellStyle); // 大宗超级通道

            getCell(workbook, sheet.getRow(rowIndex), 11, excelList.get(0).getOrder1(), cellStyle);// 大宗1级通道

            getCell(workbook, sheet.getRow(rowIndex), 13, excelList.get(0).getOrder2(), cellStyle);// 大宗2级通道

            getCell(workbook, sheet.getRow(rowIndex), 15, excelList.get(0).getOrder3(), cellStyle);// 大宗3级通道

            getCell(workbook, sheet.getRow(rowIndex), 17, excelList.get(0).getOrder4(), cellStyle);// 大额4级通道

            getCell(workbook, sheet.getRow(rowIndex), 19, excelList.get(0).getOrder5(), cellStyle);// 大额5级通道

            getCell(workbook, sheet.getRow(rowIndex), 21, excelList.get(0).getOrder6(), cellStyle);// 大额6级通道

            getCell(workbook, sheet.getRow(rowIndex), 23, excelList.get(0).getOrder7(), cellStyle);// 大额7级通道

            getCell(workbook, sheet.getRow(rowIndex), 25, excelList.get(0).getOrder8(), cellStyle);// 大额8级通道

            getCell(workbook, sheet.getRow(rowIndex),27,excelList.get(0).getOrder9(),cellStyle);// 大额9级通道

            int n = 5;// 从第5行开始创建表格
            for(int i = 0;i<excelList.size();i++){
                int column = 0;// 从第0列开始赋值
                Row rowData = sheet.createRow(n++);
                createCell(workbook, rowData, column, excelList.get(i).getLogiareaName(), cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getPdCode(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getClassesName(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getMachiningName(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getBreedName(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getFeatureName(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getWeightName(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getGradeName(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getWayGradeName(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getSupPriceofkg(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getSupPriceofbox(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getOnePriceofkg(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getOnepriceofbox(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getTwoPriceofkg(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getTwoPriceofbox(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getThreePriceofkg(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getThreepriceofbox(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getFourPriceofkg(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getFourPriceofbox(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getFivePriceofkg(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getFivepriceofbox(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getSixPriceofkg(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getSixPriceofbox(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getSevenPriceofkg(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getSevenpriceofbox(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getEightPriceofkg(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getEightPriceofbox(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getNinePriceofkg(),cellStyle);
                createCell(workbook,rowData,++column,excelList.get(i).getNinepriceofbox(),cellStyle);
            }

        }
        // 将之前用来复制的sheet删除掉
        workbook.removeSheetAt(0);
    }

    public static void createCell(Workbook wb, Row row, int column, String value, CellStyle cellStyle, Font ... font) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        // 设置样式
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cell.setCellStyle(cellStyle);
    }

    public static void getCell(Workbook wb, Row row, int column, String value, CellStyle cellStyle, Font ... font) {
        Cell cell = row.getCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }
}
