package com.msk.so.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseUploadLogic;
import com.msk.so.bean.SO153171Bean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ren_yi 改善bug1968 on 2016/8/25
 */
@Service("SO153171UploadLogic")
public class SO153171UploadLogic extends BaseUploadLogic<SO153171Bean> {


    private static Logger logger = LoggerFactory.getLogger(SO153171UploadLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    @Override
    public List<SO153171Bean> createExcelData(Workbook workbook) {
        return this.readExcel(workbook);
    }

    @Autowired
    public SO153171Logic so153171Logic;
    /**
     * 读取Excel数据
     * @param workbook
     * @return
     */
    public   List<SO153171Bean> readExcel(Workbook workbook){
        Sheet sheet = workbook.getSheetAt(NumberConst.IntDef.INT_ZERO);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        Row row = sheet.getRow(NumberConst.IntDef.INT_ZERO);
        // 总列数
        int colNum = row.getPhysicalNumberOfCells();
        SO153171Bean excelBean =null;
        List<SO153171Bean> tempList = new ArrayList<SO153171Bean>();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = NumberConst.IntDef.INT_ONE; i <= rowNum; i++) {
            excelBean = new SO153171Bean();
            row = sheet.getRow(i);
            if(row == null || row.equals("")){
                continue;
            }
            int cellIndex = NumberConst.IntDef.INT_ONE;
            // 用户编码
            String userNo = getCellValue(row.getCell(cellIndex++));
            if(!StringUtil.isNullOrEmpty(userNo)){
                excelBean.setUserNo(userNo);
            }
            // 用户角色
            String userRoleName = getCellValue(row.getCell(cellIndex++));
            if(!StringUtil.isNullOrEmpty(userRoleName)){
                excelBean.setUserRoleName(userRoleName);
            }
            // 用户名称
            String userName = getCellValue(row.getCell(cellIndex++));
            if(!StringUtil.isNullOrEmpty(userName)){
                excelBean.setUserName(userName);
            }
            // 启用日期
            String commDate = getCellValue(row.getCell(cellIndex++));
            if(!StringUtil.isNullOrEmpty(commDate)){
                excelBean.setCommDate(commDate);
            }
            // 上个结束日
            String lastPeriodEnd = getCellValue(row.getCell(cellIndex++));
            if(!StringUtil.isNullOrEmpty(lastPeriodEnd)) {
                excelBean.setLastPeriodEnd(lastPeriodEnd);
            }
            // 账期
            String period = getCellValue(row.getCell(cellIndex++));
            if(!StringUtil.isNullOrEmpty(period)){
                excelBean.setPeriod(StringUtil.toInteger(period));
            }
            tempList.add(excelBean);
        }
        return tempList;
    }

    private String getCellValue(Cell cell) {
        DecimalFormat df = new DecimalFormat("#");
        if (cell == null)
            return "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD,
                            DateUtil.getJavaDate(cell.getNumericCellValue()));
                }
                return df.format(cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_BLANK:
                return "";
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() + "";
            case Cell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue() + "";
        }
        return "";
    }

    /**
     *更新数据
     * @param excelData 读入的Excel数据列表
     * @return 新增数量
     */
    public int saveOrUpdate(List<SO153171Bean> excelData){
        int result = NumberConst.IntDef.INT_ZERO;
        if (CollectionUtils.isNotEmpty(excelData)&& excelData.size() > NumberConst.IntDef.INT_ZERO) {
            for (SO153171Bean so153171Bean : excelData) {
                if (so153171Bean != null && !StringUtil.isNullOrEmpty(so153171Bean.getCommDate())
                        && so153171Bean.getPeriod() != null) {
                    so153171Bean.setUpdId(getParam().get("userId"));
                    result += so153171Logic.modify(so153171Bean);
                }
            }
        }
        return result;
    }

    /**
     * 对于Excel 数据处理的业务数据
     * @param excelData
     */
    @Override
    @Transactional
    public  void doHandle(List<SO153171Bean> excelData){
        saveOrUpdate(excelData);
    }





}
