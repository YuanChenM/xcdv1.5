package com.msk.so.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msk.common.base.BaseController;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.pdf.ApachePdfBoxMergeFile;
import com.msk.common.pdf.MergeFile;
import com.msk.common.utils.PrinterServerUtils;

/**
 * Created by zhang_chi on 2016/8/15.
 */
@Controller
@RequestMapping("SOPdfPrint")
public class SOPdfPrintController extends BaseController {

    /**
     * 买家PDF打印
     *
     * @param callBackParam
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "pdfPrint",
            method = RequestMethod.POST)
    @ResponseBody
    public void pdfPrint(String callBackParam, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.reset();
        response.setContentType("application/pdf");
        OutputStream output = response.getOutputStream();

        // 请求url
        String printUrl = SystemServerManager.PrintServerManager.getPrintPdf() + "cashPooling/SO153102";
        // String printUrl = "http://localhost:9390/msk-print/print/pdf/cashPooling/SO153102";
        // 回调url
        String callBackUrl = SystemServerManager.SoCashPoolingServerManage.getQueryBuyerPDF();
        // String callBackUrl = "http://localhost:8080/msk-so-cp/api/buyer/pdf";
        // 准备条件
        Map<String, String> paramMap = new HashMap<String, String>();

        paramMap.put("callBackParam", callBackParam);
        paramMap.put("printCallBackUrl", callBackUrl);

        // 前段
        InputStream inputStream = PrinterServerUtils.loadPdf(printUrl, paramMap);

        /* 改善 #2107 modify by li_huiqian on 2016/8/25 start */
        // 后段
        // printUrl = printUrl + "B";
        // InputStream inputStreamB = PrinterServerUtils.loadPdf(printUrl,
        // paramMap);
        /* 改善 #2107 modify by li_huiqian on 2016/8/25 end */

        // 文件流写文件
        MergeFile mergeFile = new ApachePdfBoxMergeFile();
        /* 改善 #2107 modify by li_huiqian on 2016/8/25 start */
        mergeFile.mergeFile(output, inputStream);
        /* 改善 #2107 modify by li_huiqian on 2016/8/25 end */
    }

    /**
     * 卖家PDF打印
     *
     * @param callBackParam
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "sellerPdfPrint",
            method = RequestMethod.POST)
    @ResponseBody
    public void sellerPdfPrint(String callBackParam, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.reset();
        response.setContentType("application/pdf");
        OutputStream output = response.getOutputStream();

        // 请求url
        String printUrl = SystemServerManager.PrintServerManager.getPrintPdf() + "cashPooling/SO153112";
        //String printUrl = "http://localhost:9390/msk-print/print/pdf/cashPooling/SO153112";
        // 回调url
        String callBackUrl = SystemServerManager.SoCashPoolingServerManage.getQuerySellerPDF();

        // 准备条件
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("callBackParam", callBackParam);
        paramMap.put("printCallBackUrl", callBackUrl);

        // 前段
        InputStream inputStream = PrinterServerUtils.loadPdf(printUrl, paramMap);

        // 后段
        printUrl = printUrl + "B";
        InputStream inputStreamB = PrinterServerUtils.loadPdf(printUrl, paramMap);

        // 文件流写文件
        MergeFile mergeFile = new ApachePdfBoxMergeFile();
        mergeFile.mergeFile(output, inputStream, inputStreamB);
    }

}
