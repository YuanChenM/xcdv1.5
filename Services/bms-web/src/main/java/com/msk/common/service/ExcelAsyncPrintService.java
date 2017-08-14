package com.msk.common.service;

import com.msk.common.base.BaseLogic;
import com.msk.common.dao.RedisExtendsUtils;
import com.msk.common.service.bean.AsyncPDFInfoRequestEntity;
import com.msk.common.utils.FileUploadUtil;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
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
 * Created by shi_yuxi on 2016/7/25.
 */

public abstract class ExcelAsyncPrintService extends BaseLogic implements BaseAsyncPrintService {

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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    // 任务报错中断状态。
    private final static String TASK_STATUS_ERROR = "2";

    @Override
    public abstract Map<String, ?> getDataSource(Object param);

    @Override
    public void startTask(AsyncPDFInfoRequestEntity p_info, Map<String, ?> p_dataSource) throws IOException {
        // 传输文件流至文件服务器获取文件名称。
        asyncPrintTaskService.updateTaskStatus(p_info.getTaskID(), TASK_STATUS_RUNNING, "数据抽取成功，正在通过文件服务器中转。");
        InputStream inputStream = ExcelAsyncPrintService.class.getResourceAsStream("/excel/template/" + p_info.getTemplateCode() + ".xlsx");
        Workbook workbook = exportExcelFile(inputStream, p_dataSource);
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
