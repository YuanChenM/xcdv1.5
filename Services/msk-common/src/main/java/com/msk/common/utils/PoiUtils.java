package com.msk.common.utils;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.comm.exception.SystemException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.NumberToTextConverter;



/**
 * POI Utils
 * 
 * @author jiang_nan
 *
 */
public class PoiUtils {
	/**
	 * 获得Excel的行信息
	 * 
	 * @param sheet
	 *            Excel Sheet
	 * @param row
	 *            行号
	 * @return 行信息
	 */
	public static Row getRow(Sheet sheet, int row) {
		if (!checkExcelRow(sheet, row)) {
			throw new SystemException();
		}
		return sheet.getRow(row);
	}

	/**
	 * 获得Excel的Cell。
	 * 
	 * @param sheet
	 *            Excel Sheet
	 * @param rowIndex
	 *            行号
	 * @param column
	 *            列号
	 * @return Cell
	 */
	public static Cell getCell(Sheet sheet, int rowIndex, int column) {
		if (!checkExcelColumn(sheet, rowIndex, column)) {
			throw new SystemException();
		}
		Row row = getRow(sheet, rowIndex);
		Cell cell = row.getCell(column);
		return cell;
	}

	/**
	 * Get Excel Cell,If Cell is not Find,Then Create Cell.
	 * 
	 * @param sheet
	 *            The Excel Sheet
	 * @param rowIndex
	 *            The Row Index
	 * @param column
	 *            The Excel Column
	 * @return The Excel Cell
	 */
	public static Cell loadCell(Sheet sheet, int rowIndex, int column) {
		if (!checkExcelColumn(sheet, rowIndex, column)) {
			throw new SystemException();
		}
		Row row = loadRow(sheet, rowIndex);
		Cell cell = loadCell(row, column);
		return cell;
	}

	/**
	 * Load Row，If row is not Find,then Create Row.
	 * 
	 * @param sheet
	 *            Excel Row
	 * @param rowIndex
	 *            Row Index
	 * @return The Create Row。
	 */
	public static Row loadRow(Sheet sheet, int rowIndex) {
		Row row = getRow(sheet, rowIndex);
		if (null == row) {
			row = sheet.createRow(rowIndex);
		}
		return row;
	}

	/**
	 * The Load Cell.If Cell is not Find.Then Create Cell.
	 * 
	 * @param row
	 *            The Excel Row
	 * @param column
	 *            The Column.
	 * @return The Excel Cell.
	 */
	public static Cell loadCell(Row row, int column) {
		Cell cell = row.getCell(column);
		if (null == cell) {
			cell = row.createCell(column);
		}
		return cell;
	}

	public static String getCellValue(Sheet sheet, int row, int column) {
		Cell cell = getCell(sheet, row, column);
		if (null == cell) {
			return StringConst.EMPTY;
		}
		String value = StringConst.EMPTY;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:// 字符串类型
			value = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:// Boolean类型
			if (cell.getBooleanCellValue()) {
				value = String.valueOf(Boolean.TRUE);
			} else {
				value = String.valueOf(Boolean.FALSE);
			}
			break;
		case Cell.CELL_TYPE_NUMERIC:// Number类型
			if (DateUtil.isCellDateFormatted(cell)) {//时间格式返回类型为YYYYMMDD
				value = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, cell.getDateCellValue());
			} else {
				value = NumberToTextConverter.toText(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_FORMULA:// 格式化类型
			value = getCellTypeFormulaValue(cell);
			break;
		default:
			break;
		}
		return value;
	}

	/**
	 * 获得Double类型的Cell Value.
	 * 
	 * @param sheet
	 *            The Excel Sheet.
	 * @param row
	 *            The Excel Row.
	 * @param column
	 *            The Excel Column
	 * @return The Double.
	 */
	public static double getNumericCellValue(Sheet sheet, int row, int column) {
		return StringUtil.toDouble(getCellValue(sheet, row, column));
	}

	/**
	 * 获得Cell Type Formula Value.
	 * 
	 * @param cell
	 *            The Excel Cell
	 * @return The Cell Value.
	 */
	private static String getCellTypeFormulaValue(Cell cell) {
		if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		}
		return StringConst.EMPTY;
	}

	/**
	 * Check Excel Row。
	 * 
	 * @param sheet
	 *            Excel Sheet
	 * @param row
	 *            Excel Row
	 * @return Boolean:true is Row,false is not Row
	 */
	private static boolean checkExcelRow(Sheet sheet, int row) {
		// 判断Excel版本
		// Excel 2003
		if (sheet instanceof HSSFSheet) {
			if (row < NumberConst.IntDef.INT_ZERO || row > NumberConst.ExcelMaxRowCount.EXCEL_2003_MAX_ROW_COUNT) {
				return Boolean.FALSE;
			}
		} else {// Excel 2003以外的
			if (row < NumberConst.IntDef.INT_ZERO || row > NumberConst.ExcelMaxRowCount.EXCEL_2007_MAX_ROW_COUNT) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	/**
	 * Check Excel Column
	 * 
	 * @param sheet
	 *            Excel Sheet
	 * @param row
	 *            Excel Row
	 * @param column
	 *            Excel Column
	 * @return Boolean:True is column,false is not Column
	 */
	private static boolean checkExcelColumn(Sheet sheet, int row, int column) {
		if (!checkExcelRow(sheet, row)) {
			return Boolean.FALSE;
		}
		if (sheet instanceof HSSFSheet) {
			if (column < NumberConst.IntDef.INT_ZERO || column > NumberConst.ExcelMaxColumnCount.EXCEL_2003_MAX_COLUMN_COUNT) {
				return Boolean.FALSE;
			}
		} else {// Excel 2003以外的
			if (column < NumberConst.IntDef.INT_ZERO || column > NumberConst.ExcelMaxColumnCount.EXCEL_2007_MAX_COLUMN_COUNT) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

}
