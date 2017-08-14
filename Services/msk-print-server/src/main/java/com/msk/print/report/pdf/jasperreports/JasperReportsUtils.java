package com.msk.print.report.pdf.jasperreports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import java.io.*;
import java.util.*;

/**
 * 提供数据源已PDF形式导出的工具类。
 * Created by jackjiang on 16/7/7.
 */
public class JasperReportsUtils {
    public static void exportReportToPdfStream(Map<String,Object> paramMap, Collection<?> detailData, InputStream inputStream,OutputStream outputStream)
    {
        try
        {
            JasperReport report = JasperCompileManager.compileReport(inputStream);

            JasperPrint print = JasperFillManager.fillReport(report,paramMap,new JRBeanCollectionDataSource(detailData));
            JasperExportManager.exportReportToPdfStream(print,outputStream);
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }

    public static byte[] exportReportToPdfStream(Map<String,Object> paramMap, List<Map<String, ?>> detailData, InputStream inputStream) throws JRException
    {
        byte[] result;
        JasperReport report = JasperCompileManager.compileReport(inputStream);
        JasperPrint print = JasperFillManager.fillReport(report,paramMap,new JRMapCollectionDataSource(detailData));
        result = JasperExportManager.exportReportToPdf(print);

        return result;
    }

    /**
     *  通过jsper文件来生成pdf
     *  @param paramMap paramMap
     *  @param detailData detailData
     *  @param inputStream inputStream
     *  @param outputStream outputStream
     *  @return StringRedisTemplate
     */
    public static void exportReportToPdfStreamByJasper(Map<String,Object> paramMap, Collection<?> detailData, InputStream inputStream,OutputStream outputStream)
    {
        try
        {
            JasperPrint print = JasperFillManager.fillReport(inputStream, paramMap, new JRBeanCollectionDataSource(
                    detailData));
            JasperExportManager.exportReportToPdfStream(print,outputStream);
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }


    public static byte[] exportReportToPdfStreamByJasper(Map<String,Object> paramMap, List<Map<String, ?>> detailData, InputStream inputStream) throws JRException
    {
        byte[] result;
        JasperPrint print = JasperFillManager.fillReport(inputStream, paramMap, new JRBeanCollectionDataSource(
                detailData));
        result = JasperExportManager.exportReportToPdf(print);

        return result;
    }
}