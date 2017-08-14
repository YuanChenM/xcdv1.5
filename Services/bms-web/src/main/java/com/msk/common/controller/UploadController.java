package com.msk.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.utils.SpringContextUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.common.base.BaseUploadService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("comm")
public class UploadController extends BaseUploadController {

    @RequestMapping(value = "excel/{serviceName}/upload",method = RequestMethod.POST)
    public @ResponseBody String uploadExcel(@PathVariable("serviceName") String serviceName, MultipartFile file, HttpServletRequest request) throws IOException, InterruptedException {
        Map<String,String[]> requestParameterMap = request.getParameterMap();
        Map<String,String> paramMap = this.requestParamMapToParamMap(requestParameterMap);

        UUID uuid = UUID.randomUUID();
        String taskId = uuid.toString();
        Workbook workbook = this.createWorkbook(file);
        BaseUploadService uploadService = SpringContextUtil.getBean(serviceName,BaseUploadService.class);
        uploadService.setParam(paramMap);
        uploadService.process(taskId,workbook);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("taskId",taskId);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/excel/upload/process/listen/{taskId}",method = RequestMethod.GET)
    public @ResponseBody String uploadProcessListen(@PathVariable("taskId")String taskId,long timeout){
        long startTime = System.currentTimeMillis();
        String message;
        while (true){
            message = BaseUploadService.getTaskMessage(taskId);
            if(StringUtil.isEmpty(message)){
                break;
            }
            long endTime = System.currentTimeMillis();
            if(endTime-startTime>=timeout){
                break;
            }
        }
        return message;
    }

    private Map<String,String> requestParamMapToParamMap(Map<String,String[]> requestParameterMap){
        if(CollectionUtils.isEmpty(requestParameterMap)){
            return new HashMap<>();
        }
        Map<String,String> paramMap = new HashMap<>();
        for(Map.Entry<String,String[]> entry : requestParameterMap.entrySet()){
            String key = entry.getKey();
            String [] values = entry.getValue();
            String paramValue = StringConst.EMPTY;
            int i = 0;
            for (String value : values){
                if(i != NumberConst.IntDef.INT_ZERO){
                    paramValue += ",";
                }
                paramValue += value;
                i++;
            }
            paramMap.put(key,paramValue);
        }
        return paramMap;
    }


}
