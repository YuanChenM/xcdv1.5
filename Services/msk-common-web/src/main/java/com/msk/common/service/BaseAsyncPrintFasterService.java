package com.msk.common.service;

import com.msk.common.service.bean.AsyncPDFInfoRequestEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 提供异步打印任务的服务基类。
 * Created by ye_wenchen on 2016/7/12.
 */
interface BaseAsyncPrintFasterService {
    /**
     * 获取用于生成 PDF 的数据。
     *
     * @return
     */
    public abstract List<Map<String, ?>> getDataSourceFaster(Object param);


    /**
     * 启动任务，并返回任务taskID。
     */
    public void startTaskFaster(AsyncPDFInfoRequestEntity p_info, List<Map<String, ?>> p_dataSource) throws IOException;


}