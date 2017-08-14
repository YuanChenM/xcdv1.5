package com.msk.bms.order.controller;

import com.msk.common.base.BaseController;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.pdf.ApachePdfBoxMergeFile;
import com.msk.common.pdf.MergeFile;
import com.msk.common.utils.PrinterServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * 详情页面 pdf打印
 */

@Controller
@RequestMapping("orderPDFPrint")
public class OrderPDFPrintController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(OrderPDFPrintController.class);

    @RequestMapping(value = "order",
            method = RequestMethod.POST)
    @ResponseBody
    public void pdfPrint(String callBackParam, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        logger.info("pdf 准备callBackParam， printCallBackUrl参数");
        response.reset();
        response.setContentType("application/pdf");
        OutputStream output = response.getOutputStream();
        String printUrl = SystemServerManager.PrintServerManager.getPrintPdf() + "order/PDF000002"; // 请求url
        String callBackUrl = SystemServerManager.SoOrderApiServerManager.getQuerySoDetailPrintInfo();
        // String localPrintUrl="http://localhost:9390/msk-print/print/pdf/"+"order/PDF000002";
      //  String localCallBackUrl ="http://localhost:8889/msk-order-api/api/so/detail/print"; // 回调url
        // 准备条件
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("callBackParam", callBackParam);
        paramMap.put("printCallBackUrl", callBackUrl);
        // 前段
        InputStream inputStream = PrinterServerUtils.loadPdf(printUrl, paramMap);
        // 文件流写文件
        MergeFile mergeFile = new ApachePdfBoxMergeFile();
        mergeFile.mergeFile(output, inputStream);
    }


}
