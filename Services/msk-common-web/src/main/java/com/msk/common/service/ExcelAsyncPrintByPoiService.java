package com.msk.common.service;

import com.msk.common.base.BaseLogic;
import com.msk.common.dao.RedisExtendsUtils;
import com.msk.common.service.bean.AsyncPDFInfoRequestEntity;
import com.msk.common.utils.FileUploadUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shi_yuxi on 2016/9/8.
 */
public abstract class ExcelAsyncPrintByPoiService extends ExcelAsyncPrintService  {

    private static Logger logger = LoggerFactory.getLogger(ExcelAsyncPrintService.class);

    // 任务未完成状态。
    private final static String TASK_STATUS_RUNNING = "0";
    // 任务完成状态。
    public final static String TASK_STATUS_COMPLETED = "1";

    @Autowired
    RedisExtendsUtils redisExtendsUtils;
    @Autowired
    AsyncPrintTaskService asyncPrintTaskService;
    @Override
    public  Map<String, ?> getDataSource(Object param){return null;}

    public abstract void handleWorkBook(Workbook workbook, Object param);
    public void startTask(AsyncPDFInfoRequestEntity p_info, Map<String, ?> p_dataSource) throws IOException {
        // 传输文件流至文件服务器获取文件名称。
        asyncPrintTaskService.updateTaskStatus(p_info.getTaskID(), TASK_STATUS_RUNNING, "数据抽取成功，正在通过文件服务器中转。");
        InputStream inputStream = ExcelAsyncPrintService.class.getResourceAsStream("/excel/template/" + p_info.getTemplateCode() + ".xlsx");
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Read the excel template file fail.", e);
        }
        //处理workbook
        handleWorkBook(workbook, p_info.getParam());
        ByteArrayOutputStream os = null;
        InputStream in = null;
        try {
            os = new ByteArrayOutputStream();
            workbook.write(os);
            byte[] b = os.toByteArray();
            in = new ByteArrayInputStream(b);

            Map<String, InputStream> map = new HashMap<String, InputStream>();
            map.put(p_info.getTaskID(), in);
            String fileCode = FileUploadUtil.uploadStreamFiles(map).get(p_info.getTaskID());
            asyncPrintTaskService.updateTaskStatus(p_info.getTaskID(), TASK_STATUS_COMPLETED, fileCode);
        } catch (IOException e) {
            throw e;
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    logger.error("下载处理失败：", e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("下载处理失败：", e);
                }
            }
        }

    }
}
