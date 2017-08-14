package com.msk.price.utils;

import com.hoperun.core.exception.BusinessException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhu_kai1 on 2016/9/9.
 *
 * POI工具类 功能点：
 * 1、实现excel的sheet复制，复制的内容包括单元的内容、样式、注释
 *
 */
public class ExcelCopySheetUtil {

        /**
         * 功能：拷贝sheet
         * @param targetSheet
         * @param sourceSheet
         * @param targetWork
         * @param sourceWork
         * @param copyStyle	boolean 是否拷贝样式
         */
        public static void copySheet(Sheet targetSheet, Sheet sourceSheet,
                                     Workbook targetWork, Workbook sourceWork, boolean copyStyle)throws Exception {

            if(targetSheet == null || sourceSheet == null || targetWork == null || sourceWork == null){
                throw new BusinessException("调用ExcelCopySheetUtil.copySheet()方法时，targetSheet、sourceSheet、targetWork、sourceWork都不能为空，故抛出该异常！");
            }

            //复制源表中的行
            int maxColumnNum = 0;

            Map styleMap = (copyStyle) ? new HashMap() : null;

            for (int i = sourceSheet.getFirstRowNum(); i <= sourceSheet.getLastRowNum(); i++) {
                Row sourceRow = sourceSheet.getRow(i);
                Row targetRow = targetSheet.createRow(i);

                if (sourceRow != null) {
                    copyRow(targetRow, sourceRow,
                            targetWork, sourceWork, styleMap);
                    if (sourceRow.getLastCellNum() > maxColumnNum) {
                        maxColumnNum = sourceRow.getLastCellNum();
                    }
                }
            }

            //复制源表中的合并单元格
            mergerRegion(targetSheet, sourceSheet);

            //设置目标sheet的列宽
            for (int i = 0; i <= maxColumnNum; i++) {
                targetSheet.setColumnWidth(i, sourceSheet.getColumnWidth(i));
            }
        }

        /**
         * 功能：拷贝row
         * @param targetRow
         * @param sourceRow
         * @param styleMap
         * @param targetWork
         * @param sourceWork
         */
        public static void copyRow(Row targetRow, Row sourceRow,
                                   Workbook targetWork, Workbook sourceWork, Map styleMap) throws Exception {
            if(targetRow == null || sourceRow == null || targetWork == null || sourceWork == null ){
                throw new BusinessException("调用ExcelCopySheetUtil.copyRow()方法时，targetRow、sourceRow、targetWork、sourceWork、targetPatriarch都不能为空，故抛出该异常！");
            }

            //设置行高
            targetRow.setHeight(sourceRow.getHeight());

            for (int i = sourceRow.getFirstCellNum(); i <= sourceRow.getLastCellNum(); i++) {
                Cell sourceCell = sourceRow.getCell(i);
                Cell targetCell = targetRow.getCell(i);

                if (sourceCell != null) {
                    if (targetCell == null) {
                        targetCell = targetRow.createCell(i);
                    }

                    //拷贝单元格，包括内容和样式
                    copyCell(targetCell, sourceCell, targetWork, sourceWork, styleMap);

                }
            }
        }

        /**
         * 功能：拷贝cell，依据styleMap是否为空判断是否拷贝单元格样式
         * @param targetCell			不能为空
         * @param sourceCell			不能为空
         * @param targetWork			不能为空
         * @param sourceWork			不能为空
         * @param styleMap				可以为空
         */
        public static void copyCell(Cell targetCell, Cell sourceCell, Workbook targetWork, Workbook sourceWork,Map styleMap) {
            if(targetCell == null || sourceCell == null || targetWork == null || sourceWork == null ){
                throw new BusinessException("调用ExcelCopySheetUtil.copyCell()方法时，targetCell、sourceCell、targetWork、sourceWork都不能为空，故抛出该异常！");
            }

            //处理单元格样式
            if(styleMap != null){
                if (targetWork == sourceWork) {
                    targetCell.setCellStyle(sourceCell.getCellStyle());
                } else {
                    String stHashCode = "" + sourceCell.getCellStyle().hashCode();
                    CellStyle targetCellStyle = (CellStyle) styleMap
                            .get(stHashCode);
                    if (targetCellStyle == null) {
                        targetCellStyle = targetWork.createCellStyle();
                        targetCellStyle.cloneStyleFrom(sourceCell.getCellStyle());
                        styleMap.put(stHashCode, targetCellStyle);
                    }

                    targetCell.setCellStyle(targetCellStyle);
                }
            }

            //处理单元格内容
            switch (sourceCell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    targetCell.setCellValue(sourceCell.getRichStringCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    targetCell.setCellValue(sourceCell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    targetCell.setCellType(Cell.CELL_TYPE_BLANK);
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    targetCell.setCellValue(sourceCell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    targetCell.setCellFormula(sourceCell.getCellFormula());
                    break;
                default:
                    break;
            }
        }


        /**
         * 功能：复制原有sheet的合并单元格到新创建的sheet
         *
         * @param sourceSheet
         */
        public static void mergerRegion(Sheet targetSheet, Sheet sourceSheet)throws Exception {
            if(targetSheet == null || sourceSheet == null){
                throw new BusinessException("调用ExcelCopySheetUtil.mergerRegion()方法时，targetSheet或者sourceSheet不能为空，故抛出该异常！");
            }

            for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
                CellRangeAddress oldRange = sourceSheet.getMergedRegion(i);
                CellRangeAddress newRange = new CellRangeAddress(
                        oldRange.getFirstRow(), oldRange.getLastRow(),
                        oldRange.getFirstColumn(), oldRange.getLastColumn());
                targetSheet.addMergedRegion(newRange);
            }
        }

}
