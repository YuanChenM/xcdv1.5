package com.msk.common.service;

import com.alibaba.fastjson.JSON;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.dao.RedisExtendsUtils;
import com.msk.common.service.bean.AsyncPDFInfoRequestEntity;
import com.msk.common.utils.FileUploadUtil;
import com.msk.common.utils.RestClientUtil;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shi_yuxi on 2016/7/25.
 */

public abstract class ExcelAsyncPrintFasterService extends BaseLogic implements BaseAsyncPrintFasterService {

    private static Logger logger = LoggerFactory.getLogger(ExcelAsyncPrintService.class);

    @Autowired
    RedisExtendsUtils redisExtendsUtils;
    @Autowired
    AsyncPrintTaskService asyncPrintTaskService;

    // 任务未完成状态。
    private final static String TASK_STATUS_RUNNING = "0";
    // 任务完成状态。
    public final static String TASK_STATUS_COMPLETED = "1";
    // src/main真实路径
    private String resource;

    private final static String PRINTER_SERVICE_EXCEL_GENERATOR_URL = SystemServerManager.PrintServerManager.getAsyncGenerateSingleExcel();

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    // 任务报错中断状态。
    private final static String TASK_STATUS_ERROR = "2";

    @Override
    public abstract List<Map<String, ?>> getDataSourceFaster(Object param);


    @Override
    public void startTaskFaster(AsyncPDFInfoRequestEntity p_info, List<Map<String, ?>> p_dataSource) throws IOException {
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
        asyncPrintTaskService.updateTaskStatus(p_info.getTaskID(), TASK_STATUS_RUNNING, "正在调用打印服务器生成 Excel 文件。");

        // TODO:获取打印服务的URL。应维护在ConfigManager里。
        String printServiceURL = PRINTER_SERVICE_EXCEL_GENERATOR_URL;
        p_info.setFileCode(fileCode);
        RestClientUtil.asyncPost(printServiceURL, p_info);

        logger.debug("===========打印任务数据生成完成，开始调用打印文件生成服务===========");
    }

    /**
     * 向excel中插入数据
     *
     * @param is   模板读入输入流
     * @param data 输出流
     */
    private static Workbook exportExcelFile(InputStream is, Map data) {
        XLSTransformer transformer = new XLSTransformer();
        Workbook workbook = null;
        try {
            workbook = transformer.transformXLS(is, data);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
