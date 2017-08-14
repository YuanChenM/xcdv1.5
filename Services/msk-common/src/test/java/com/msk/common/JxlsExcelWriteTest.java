package com.msk.common;

import com.msk.common.excel.ExcelWrite;
import com.msk.common.excel.JxlsExcelWrite;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackjiang on 16/6/6.
 */
public class JxlsExcelWriteTest {
    @Test
    public void testExcelWrite() throws FileNotFoundException {
       InputStream in =  JxlsExcelWriteTest.class.getResourceAsStream("Text.xlsx");
       File output = new File("/temp/Text.xlsx");
       ExcelWrite excelWrite = new JxlsExcelWrite(in,new BufferedOutputStream(new FileOutputStream(output)));
        Map<String,Object> paramMap = new HashMap<>();
        excelWrite.excelWrite(paramMap);
    }
}
