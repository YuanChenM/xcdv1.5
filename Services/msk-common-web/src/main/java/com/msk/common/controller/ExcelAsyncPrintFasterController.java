package com.msk.common.controller;

import com.hoperun.plug.spring.utils.SpringContextUtil;
import com.msk.common.bean.AsyncReportTaskInfo;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.service.AsyncPrintTaskService;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.common.service.bean.AsyncPDFInfoRequestEntity;
import com.msk.common.utils.HtmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by shi_yuxi on 2016/7/25.
 */
@Controller
@Scope("prototype")
@RequestMapping("excel/async/print")
public class ExcelAsyncPrintFasterController implements BaseAsyncPrintFasterController {

    // 任务未完成状态。
    private final static String TASK_STATUS_RUNNING = "0";
    private static Logger LOGGER = LoggerFactory.getLogger(ExcelAsyncPrintController.class);

    @Autowired
    AsyncPrintTaskService asyncPrintTaskService;

    @RequestMapping(value = "/start/faster", method = RequestMethod.POST)
    public void taskGenerateFaster(@RequestBody HashMap<String, Object> map, HttpServletRequest request, final HttpServletResponse response) {
        LOGGER.debug("===================taskGenerate================");

        final String templateCode = (String) map.get("templateCode");
        final String serviceName = (String) map.get("serviceName");
        final String modelName = (String) map.get("modelName");
        final Object param = map.get("param");
        //生成TaskID并返回。
        final String taskID = generateTaskID();
        Map<String, String> result = new HashMap<String, String>();
        result.put("taskID", taskID);
        result.put("downloadUrl", SystemServerManager.CommonServerManager.getFileServerDownloadProxy());
        HtmlUtil.writerSuccessJson(response, result);
        AsyncReportTaskInfo taskInfo = new AsyncReportTaskInfo(taskID, TASK_STATUS_RUNNING, "异步生成开始。");
        taskStatusUpdateFaster(taskInfo);
        // 调用start
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AsyncPDFInfoRequestEntity info = new AsyncPDFInfoRequestEntity();
                    info.setTaskID(taskID);
                    info.setTemplateCode(templateCode);
                    info.setServiceName(serviceName);
                    info.setParam(param);
                    info.setModelName(modelName);
                    startFaster(info);
                } catch (Exception e) {
                    // 错误信息写入redis。
                    AsyncReportTaskInfo taskInfo = new AsyncReportTaskInfo(taskID, "3", "异步生成失败，具体原因请参考：" + e.getMessage());
                    taskStatusUpdateFaster(taskInfo);
                    LOGGER.error("异步生成失败，具体原因请参考：" + e.getMessage());
//					HtmlUtil.writerFailJson(response, "异步生成失败，具体原因请参考：" + e.getMessage());
                }
            }
        }).start();
    }

    @Override
    public void startFaster(AsyncPDFInfoRequestEntity p_info) throws IOException {
        // 根据名称获取 service。（service 继承于 PDFAsyncService）
        ExcelAsyncPrintFasterService service = (ExcelAsyncPrintFasterService) SpringContextUtil.getBean(p_info.getServiceName(), ExcelAsyncPrintFasterService.class);
        // 执行业务获取数据。
        List<Map<String, ?>> dataSource = service.getDataSourceFaster(p_info.getParam());
        // 存入文件服务器,通知PDF打印服务器。
        service.startTaskFaster(p_info, dataSource);
    }

    @Override
    public void taskStatusUpdateFaster(AsyncReportTaskInfo p_taskInfo) {
        asyncPrintTaskService.updateTaskStatus(p_taskInfo);
    }

    @Override
    public AsyncReportTaskInfo taskStatusCheckFaster(String p_taskID) throws Exception {
        AsyncReportTaskInfo taskInfo = asyncPrintTaskService.checkTaskStatus(p_taskID);
        return taskInfo;
    }

    // 生成唯一标识。
    private static String generateTaskID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return temp;
    }
}
