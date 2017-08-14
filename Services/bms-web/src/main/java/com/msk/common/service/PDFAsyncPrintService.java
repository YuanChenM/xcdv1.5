package com.msk.common.service;

import com.alibaba.fastjson.JSON;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.dao.RedisExtendsUtils;
import com.msk.common.service.bean.AsyncPDFInfoRequestEntity;
import com.msk.common.utils.FileUploadUtil;
import com.msk.common.utils.RestClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 提供处理 PDF 异步方式生成任务的服务。
 * Created by ye_wenchen on 2016/7/12.
 */
public abstract class PDFAsyncPrintService implements BaseAsyncPrintService {
    private static Logger LOGGER = LoggerFactory.getLogger(PDFAsyncPrintService.class);

    @Autowired
    RedisExtendsUtils redisExtendsUtils;
    @Autowired
    AsyncPrintTaskService asyncPrintTaskService;

    // 指定存储异步方式任务信息的 redis 操作库索引编号。
    private final static Integer REDIS_DATABASE_INDEX = 10;

    // 任务未完成状态。
    private final static String TASK_STATUS_RUNNING = "0";
    // 任务完成状态。
    public final static String TASK_STATUS_COMPLETED = "1";
    // 任务报错中断状态。
    private final static String TASK_STATUS_ERROR = "2";

    private final static String PRINTER_SERVICE_PDF_GENERATOR_URL = SystemServerManager.PrintServerManager.getAsyncGenerateSinglePDF();

    /*	String url=SystemServerManager.PrintServerManager.getAsyncGenerateSinglePDF();
        private final static String PRINTER_SERVICE_PDF_GENERATOR_URL="http://localhost:9390/msk-print/print/async/pdf";*/
    @Override
    public abstract Map<String, ?> getDataSource(Object param);

    @Override
    public void startTask(AsyncPDFInfoRequestEntity p_info, Map<String, ?> p_dataSource) throws IOException {
        //TODO:DataSource 存入文件服务器。
        // 获取文件流。
        asyncPrintTaskService.updateTaskStatus(p_info.getTaskID(), TASK_STATUS_RUNNING, "正在抽取报表数据。");

        String jsonData = JSON.toJSONString(p_dataSource);
        InputStream inputData = new ByteArrayInputStream(jsonData.getBytes());

        // 传输文件流至文件服务器获取文件名称。
        asyncPrintTaskService.updateTaskStatus(p_info.getTaskID(), TASK_STATUS_RUNNING, "数据抽取成功，正在通过文件服务器中转。");

        HashMap<String, InputStream> map = new HashMap<>();
        map.put(p_info.getTaskID(), inputData);
        String fileCode = FileUploadUtil.uploadStreamFiles(map).get(p_info.getTaskID());

        //调用服务，PDF开始打印生成。
        asyncPrintTaskService.updateTaskStatus(p_info.getTaskID(), TASK_STATUS_RUNNING, "正在调用打印服务器生成 PDF 文件。");

        // TODO:获取打印服务的URL。应维护在ConfigManager里。
        String printServiceURL = PRINTER_SERVICE_PDF_GENERATOR_URL;
        p_info.setFileCode(fileCode);
        RestClientUtil.asyncPost(printServiceURL, p_info);

        LOGGER.debug("===========打印任务数据生成完成，开始调用打印文件生成服务===========");
    }
}