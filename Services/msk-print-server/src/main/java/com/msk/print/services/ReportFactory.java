package com.msk.print.services;

import com.msk.print.utils.StringUtil;
import org.springframework.stereotype.Component;

/**
 * Created by jackjiang on 16/7/8.
 */
@Component
public class ReportFactory {
    private static String ORDER_MODULE = "order";
    private static String CASHPOOLING_MODULE = "cashPooling";
    private static String DS_MODULE = "ds";


    public static ReportDataService createReportData(String moduleName) {
        ReportDataService reportDataService = null;
        if (ORDER_MODULE.equals(moduleName)) {
            reportDataService = new OrderReportService();
        } else if (CASHPOOLING_MODULE.equals(moduleName)) {
            reportDataService = new CashPoolingReportService();
        }else if (DS_MODULE.equals(moduleName)) {
            reportDataService = new DsReportService();
        }
        return reportDataService;
    }
}


