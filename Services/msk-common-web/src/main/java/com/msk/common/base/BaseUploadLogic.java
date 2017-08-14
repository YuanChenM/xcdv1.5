package com.msk.common.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hoperun.core.exception.BusinessException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.scheduling.annotation.Async;

import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;

public abstract class BaseUploadLogic<T> extends BaseLogic {
    private static Map<String, String> TASK_ID_MAP = new ConcurrentHashMap<>();
    private Map<String, String> param;

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    @Async
    public void process(String taskId, Workbook workbook, InputStream inputStream) throws InterruptedException {
        try {
            if(TASK_ID_MAP.containsKey("errorMessage")){
                TASK_ID_MAP.remove("errorMessage");
            }
            TASK_ID_MAP.put(taskId, "文件数据读取中......");
            List<T> excelData = this.createExcelData(workbook);
            TASK_ID_MAP.put(taskId, "数据处理中......");
            this.doHandle(excelData);
            TASK_ID_MAP.put(taskId, "S");
            inputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BusinessException e){
            TASK_ID_MAP.put("errorMessage", e.getMessage());
            e.printStackTrace();
        }


    }

    public abstract List<T> createExcelData(Workbook workbook);

    public abstract void doHandle(List<T> excelData);

    public static Map<String, String> getTaskMessage(String taskId) {
        String message = TASK_ID_MAP.get(taskId);
        if (TASK_ID_MAP.size() == 0) {
            return null;
        }
        return TASK_ID_MAP;
    }


}
