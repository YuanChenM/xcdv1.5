package com.msk.common.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * JXLS导出Excel文件
 * 
 * @author jiang_nan
 * 
 */
public class JxlsExcelWrite implements ExcelWrite {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(JxlsExcelWrite.class);
    /**模板读入输入流*/
    private InputStream in;
    /**输出流*/
    private OutputStream os;

    /**
     * 构造函数
     * @param in 模板读入输入流
     * @param os 输出流
     */
    public JxlsExcelWrite(InputStream in,OutputStream os){
        this.in = in;
        this.os = os;
    }
    @Override
    public void excelWrite(Map<String, Object> paramMap) {
        logger.debug("输出Excel");
        XLSTransformer transformer = new XLSTransformer();
        try {
            Workbook workbook = transformer.transformXLS(in, paramMap);
            workbook.write(os);
            os.flush();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void excelWrite(List<Object> list) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("list", list);
        this.excelWrite(paramMap);
    }

    @Override
    public void excelWrite(Object headEntity, List<Object> list) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("headEntity", headEntity);
        paramMap.put("list", list);
        this.excelWrite(paramMap);
    }
}
