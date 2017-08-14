package com.msk.common.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.msk.common.excel.ExcelWrite;
import com.msk.common.excel.JxlsExcelWrite;

/**
 * Download Base Controller.<br/>
 * Example:<br/>
 * @Controller<br/>
 * public class TestDownloadController extends BaseDownloadController{<br/>
 *
 *      @RequestMapping(value = "/test/download",method = RequestMethod.POST)<br/>
 *      public void download(HttpServletRequest request,HttpServletResponse response){<br/>
 *          Map&gt;String,Object&lt; dataMap = new HashMap&gt;&lt;<br/>
 *          dataMap.put("header",xxxx);
 *          dataMap.put("list",List Data);
 *          this.downloadExcel(dataMap,request,response,"测试");<br/>
 *      }<br/>
 *
 *      public InputStream getInputStream(){<br/>
 *          //TODO 各自模块获得Excel模板的InputStream
 *
 *      }
 *
 * }
 * @author jiang_nan
 */
public abstract class BaseDownloadController extends BaseController{
    /**
     * Get Excel Template Input Stream
     * @return Excel Template Input Stream
     */
    public abstract InputStream getInputStream();

    /**
     * Get Excel Output Stream
     * @param response Http Servlet Response
     * @return Excel Output Stream
     */
    private OutputStream getOutputStream(HttpServletResponse response){
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    /**
     * Set Excel Download Header.
     * @param response Http Servlet Response
     * @param fileName Excel File Name
     */
    private void setExcelDownloadHeader(HttpServletResponse response,String fileName){
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Download Excel.
     * @param dataMap Excel Download Data
     * @param response Http Servlet Response
     * @param fileName Excel File Name
     */
    public void downloadExcel(Map<String,Object> dataMap,HttpServletResponse response,String fileName){
        this.setExcelDownloadHeader(response,fileName);
        ExcelWrite excelWrite = new JxlsExcelWrite(this.getInputStream(),this.getOutputStream(response));
        excelWrite.excelWrite(dataMap);
    }
}
