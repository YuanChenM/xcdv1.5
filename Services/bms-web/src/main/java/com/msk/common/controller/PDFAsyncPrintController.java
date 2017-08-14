package com.msk.common.controller;

import com.hoperun.plug.spring.utils.SpringContextUtil;
import com.msk.common.bean.AsyncReportTaskInfo;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.service.AsyncPrintTaskService;
import com.msk.common.service.PDFAsyncPrintService;
import com.msk.common.service.bean.AsyncPDFInfoRequestEntity;
import com.msk.common.utils.HtmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 提供 PDF 异步数据抽取和下载的接口类。
 * Created by ye_wenchen on 2016/7/12.
 */
@Controller
@Scope("prototype")
@RequestMapping("/async/print")
public class PDFAsyncPrintController implements BaseAsyncPrintController {
    private static Logger LOGGER = LoggerFactory.getLogger(PDFAsyncPrintController.class);

    // 任务未完成状态。
    private final static String TASK_STATUS_RUNNING = "0";
    @Autowired
    AsyncPrintTaskService asyncPrintTaskService;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public void taskGenerate(@RequestBody HashMap<String, Object> map, HttpServletRequest request, final HttpServletResponse response) {
        LOGGER.debug("===================taskGenerate================");
        final String templateCode = (String) map.get("templateCode");
        final String serviceName = (String) map.get("serviceName");
        final String modelName = (String) map.get("modelName");
        final Object param = map.get("param");
        final boolean isOpen = (Boolean) map.get("isOpen");
        //生成TaskID并返回。
        final String taskID = generateTaskID();
        Map<String, String> result = new HashMap<String, String>();
        result.put("taskID", taskID);
        //直接打开或者直接下载
        if (isOpen) {
            result.put("downloadUrl", ConfigManager.getMskFileDownLoadServices());
        } else {
            result.put("downloadUrl", SystemServerManager.CommonServerManager.getFileServerDownloadProxy());
        }

        HtmlUtil.writerSuccessJson(response, result);
        AsyncReportTaskInfo taskInfo = new AsyncReportTaskInfo(taskID, TASK_STATUS_RUNNING, "异步生成开始。");
        taskStatusUpdate(taskInfo);
        // 调用start
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AsyncPDFInfoRequestEntity info = new AsyncPDFInfoRequestEntity();
                    info.setTaskID(taskID);
                    info.setTemplateCode(templateCode);
                    info.setServiceName(serviceName);
                    info.setModelName(modelName);
                    info.setParam(param);
                    start(info);
                } catch (IOException e) {
                    // 错误信息写入redis。
                    AsyncReportTaskInfo taskInfo = new AsyncReportTaskInfo(taskID, "3", "异步生成失败，具体原因请参考：" + e.getMessage());
                    taskStatusUpdate(taskInfo);
//					HtmlUtil.writerFailJson(response, "异步生成失败，具体原因请参考：" + e.getMessage());
                }
            }
        }).start();
    }

    @RequestMapping(value = "/api/update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void taskStatusUpdate(@RequestBody AsyncReportTaskInfo taskInfo, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 状态更新。
            taskStatusUpdate(taskInfo);
            HtmlUtil.writerSuccessJson(response);
        } catch (Exception e) {
            HtmlUtil.writerFailJson(response, "任务状态更新失败，具体原因请参考：" + e.getMessage());
        }

    }

    @RequestMapping(value = "/check/{taskID}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void taskStatusCheck(@PathVariable("taskID") String taskID, @RequestParam(value = "timeout", required = false) long timeout, HttpServletRequest request, HttpServletResponse response) {
        try {
            LOGGER.info("查询下载状态check begin。");
            long begin = System.currentTimeMillis();
            while (true) {
                LOGGER.info("查询下载状态check 进入循环。");
                long end = System.currentTimeMillis();
                long secods = end - begin;
                AsyncReportTaskInfo taskInfo = taskStatusCheck(taskID);

                //设置超时时间
                if (secods > timeout) {
                    LOGGER.info("查询下载超时！");
                    HtmlUtil.writerSuccessJson(response, taskInfo);
                    break;
                }
                if (PDFAsyncPrintService.TASK_STATUS_COMPLETED.equals(taskInfo.getStatus())) {
                    LOGGER.info("查询下载状态成功！");
                    HtmlUtil.writerSuccessJson(response, taskInfo);
                    break;
                } else {
                    LOGGER.info("没有查询到结果暂停！");
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            LOGGER.info("下载发生异常！");
            HtmlUtil.writerFailJson(response, "任务状态获取失败，具体原因请参考：" + e.getMessage());
        }
    }

    @Override
    public void start(AsyncPDFInfoRequestEntity p_info) throws IOException {
        // 根据名称获取 service。（service 继承于 PDFAsyncService）
        PDFAsyncPrintService service = (PDFAsyncPrintService) SpringContextUtil.getBean(p_info.getServiceName(), PDFAsyncPrintService.class);
        // 执行业务获取数据。
        Map<String, ?> dataSource = service.getDataSource(p_info.getParam());
        // 存入文件服务器,通知PDF打印服务器。
        service.startTask(p_info, dataSource);
    }

    @Override
    public void taskStatusUpdate(AsyncReportTaskInfo p_taskInfo) {
        asyncPrintTaskService.updateTaskStatus(p_taskInfo);
    }

    @Override
    public AsyncReportTaskInfo taskStatusCheck(String p_taskID) throws Exception {
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