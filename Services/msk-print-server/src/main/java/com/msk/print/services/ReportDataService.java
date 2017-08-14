package com.msk.print.services;

import com.msk.print.bean.PrintParam;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Created by jackjiang on 16/7/8.
 */
public interface ReportDataService {
    public void init(String templateCode, PrintParam param);

    public Map<String,Object> getReportParam();
    public Collection<Serializable> getReportData();
}
