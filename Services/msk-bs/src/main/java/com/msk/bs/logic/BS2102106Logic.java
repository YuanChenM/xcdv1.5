package com.msk.bs.logic;

import com.msk.common.service.ExcelAsyncPrintService;

import java.util.Map;

/**
 * Created by yang_chunyan on 2016/5/7.
 */
public class BS2102106Logic extends ExcelAsyncPrintService {
    @Override
    public Map<String, ?> getDataSource(Object param) {
        Map<String,String> map = (Map)param;
        return null;
    }
}
