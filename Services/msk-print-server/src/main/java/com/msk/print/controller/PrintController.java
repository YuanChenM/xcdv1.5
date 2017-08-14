package com.msk.print.controller;

import com.msk.print.bean.AsyncPDFInfoRequestEntity;
import com.msk.print.bean.PrintParam;
import com.msk.print.report.pdf.jasperreports.JasperReportsUtils;
import com.msk.print.services.AsyncReportService;
import com.msk.print.services.ReportDataService;
import com.msk.print.services.ReportFactory;
import com.msk.print.utils.HtmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 打印服务的响应接口。
 * Created by jackjiang on 16/7/8.
 */
@Controller
@RequestMapping("print")
public class PrintController {

    private static Logger LOGGER = LoggerFactory.getLogger(PrintController.class);

    @Autowired
    AsyncReportService asyncReportService;

    @RequestMapping(value = "pdf/{module}/{templateCode}")
    public void printPdf(@PathVariable("module") String moduleName, @PathVariable("templateCode") String templateCode, HttpServletResponse response, HttpServletRequest request) throws IOException {
        PrintParam param = new PrintParam();
        param.setCallBackParam(request.getParameter("callBackParam"));
        param.setPrintCallBackUrl(request.getParameter("printCallBackUrl"));
        ReportDataService reportDataService = ReportFactory.createReportData(moduleName);
        reportDataService.init(templateCode, param);
        response.reset();
        response.setContentType("application/pdf");
        OutputStream output = response.getOutputStream();
        InputStream inputStream = PrintController.class.getResourceAsStream("/pdf/template/" + moduleName + "/" + templateCode + ".jasper");
        JasperReportsUtils.exportReportToPdfStreamByJasper(reportDataService.getReportParam(), reportDataService.getReportData(), inputStream, output);
        inputStream.close();
        output.close();

    }

    /**
     * 异步抽取数据并生成PDF。
     *
     * @param info
     * @param request
     * @param response
     */
    @RequestMapping(value = "async/pdf", method = RequestMethod.POST)
    public void asyncGenerateSinglePDF(@RequestBody AsyncPDFInfoRequestEntity info, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("============asyncGenerateSinglePDF============");

        try {
            asyncReportService.generatePDFData(info);
        } catch (Exception e) {
            HtmlUtil.writerFailJson(response, "异步生成PDF数据失败，具体原因请参考：" + e.getMessage());
            LOGGER.error("异步生成PDF数据失败", e);
        }
    }

    @RequestMapping(value = "async/excel", method = RequestMethod.POST)
    public void asyncGenerateSingleExcel(@RequestBody AsyncPDFInfoRequestEntity info, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("============asyncGenerateSingleExcel============");
        try {
            asyncReportService.generateExcelData(info);

        } catch (Exception e) {
            LOGGER.error("异步生成Excel数据失败", e);
        }
    }

//    /**
//     * 向excel中插入数据
//     *
//     * @param is   模板读入输入流
//     * @param data 输出流
//     */
//    private static Workbook exportExcelFile(InputStream is, Map data) {
//        XLSTransformer transformer = new XLSTransformer();
//        Workbook workbook = null;
//        try {
//            workbook = transformer.transformXLS(is, data);
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return workbook;
//    }

//    @RequestMapping(value = "async/excel/poi/{count}", method = RequestMethod.POST)
//    public void asyncGenerateSingleExcelByPoi(@PathVariable("count") Integer count, HttpServletRequest request, HttpServletResponse response)
//    {
//        LOGGER.info("excel 开始！");
//        InputStream inputStream = PrintController.class.getResourceAsStream("/excel/template/test.xlsx");
//        Workbook workbook = null;
//        try {
//            workbook = WorkbookFactory.create(inputStream);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            throw new RuntimeException("Read the excel template file fail.", e);
//        }
//        long begin = System.currentTimeMillis();
//        Sheet sheet = workbook.getSheetAt(1);
//        for(int i = 0; i < count; i++){
//            Row row = sheet.createRow(i);
//            row.createCell(0).setCellValue("1");
//            row.createCell(1).setCellValue("2");
//            row.createCell(2).setCellValue("3");
//            row.createCell(3).setCellValue("4");
//            row.createCell(4).setCellValue("5");
//            row.createCell(5).setCellValue("6");
//        }
//        long end = System.currentTimeMillis();
//        LOGGER.info("写数据总时间： " + (end - begin));
//
//        ByteArrayOutputStream os = null;
//        InputStream in = null;
//        try {
//            os = new ByteArrayOutputStream();
//            workbook.write(os);
//            byte[] b = os.toByteArray();
//            in = new ByteArrayInputStream(b);
//
//            Map<String, InputStream> map = new HashMap<String, InputStream>();
//            map.put("11111", in);
//            String fileCode = FileUploadUtil.uploadStreamFiles(map).get("11111");
//            LOGGER.info("文件code:" + fileCode);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (os != null) {
//                try {
//                    os.flush();
//                    os.close();
//                } catch (IOException e) {
//                    LOGGER.error("下载处理失败：", e);
//                }
//            }
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    LOGGER.error("下载处理失败：", e);
//                }
//            }
//        }
//
//    }

}