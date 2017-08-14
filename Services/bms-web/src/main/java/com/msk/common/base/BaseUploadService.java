package com.msk.common.base;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.scheduling.annotation.Async;

public abstract class BaseUploadService<T> {
    private static Map<String,String> TASK_ID_MAP = new ConcurrentHashMap<>();
    private Map<String,String> param;

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    @Async
    public void process(String taskId,Workbook workbook) throws InterruptedException {
        TASK_ID_MAP.put(taskId,"文件数据读取中......");
        List<T> excelData = this.createExcelData(workbook);
        TASK_ID_MAP.put(taskId,"数据处理中......");
        this.doHandle(excelData);
        TASK_ID_MAP.remove(taskId);


    }
    public abstract  List<T> createExcelData(Workbook workbook);
    public abstract void doHandle(List<T> excelData);
    public static String getTaskMessage(String taskId){
        String message = TASK_ID_MAP.get(taskId);
        if(StringUtil.isEmpty(message)){
            return StringConst.EMPTY;
        }
        return message;
    }







}
