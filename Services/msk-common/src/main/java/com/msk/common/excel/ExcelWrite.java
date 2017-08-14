package com.msk.common.excel;

import java.util.List;
import java.util.Map;

/**
 * Excel操作（写文件）
 * 
 * @author jiang_nan
 *
 */
public interface ExcelWrite {
    /**
     * 写Excel
     * 
     * @param paramMap Excel JSTL表达式的数据
     */
    public void excelWrite(Map<String, Object> paramMap);
    /**
     * 写Excel数据
     * @param list list数据
     */
    public void excelWrite(List<Object> list);

    /**
     * 写Excel
     * 
     * @param headEntity Head模块数据
     * @param list Detail模块的数据
     */
    public void excelWrite(Object headEntity, List<Object> list);
}
