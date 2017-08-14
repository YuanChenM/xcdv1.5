package com.msk.common.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.utils.SpringContextUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.common.base.BaseUploadLogic;

@Controller
@RequestMapping("comm")
public class UploadController extends BaseUploadController {

    @RequestMapping(value = "excel/{serviceName}/upload", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    String uploadExcel(@PathVariable("serviceName") String serviceName, MultipartFile file, HttpServletRequest request) throws IOException, InterruptedException {
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        Map<String, String> paramMap = this.requestParamMapToParamMap(requestParameterMap);

        UUID uuid = UUID.randomUUID();
        String taskId = uuid.toString();
        InputStream inputStream = this.getInputStream(file);
        Workbook workbook = new XSSFWorkbook(inputStream);
        BaseUploadLogic uploadService = SpringContextUtil.getBean(serviceName, BaseUploadLogic.class);
        uploadService.setParam(paramMap);
        uploadService.process(taskId, workbook, inputStream);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("taskId", taskId);

        return jsonObject.toString();
    }

    @RequestMapping(value = "/excel/upload/process/listen/{taskId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, String> uploadProcessListen(@PathVariable("taskId") String taskId, long timeout) {
        long startTime = System.currentTimeMillis();
        Map<String, String> message;
        while (true) {
            message = BaseUploadLogic.getTaskMessage(taskId);
            if ("S".equals(message.get(taskId)) || !StringUtil.isEmpty(message.get("errorMessage"))) {
                break;
            }
            long endTime = System.currentTimeMillis();
            if (endTime - startTime >= timeout) {
                break;
            }
        }
        return message;
    }

    private Map<String, String> requestParamMapToParamMap(Map<String, String[]> requestParameterMap) {
        if (CollectionUtils.isEmpty(requestParameterMap)) {
            return new HashMap<>();
        }
        Map<String, String> paramMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : requestParameterMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            String paramValue = StringConst.EMPTY;
            int i = 0;
            for (String value : values) {
                if (i != NumberConst.IntDef.INT_ZERO) {
                    paramValue += ",";
                }
                paramValue += value;
                i++;
            }
            paramMap.put(key, paramValue);
        }
        return paramMap;
    }


    @RequestMapping(value = "test", method = RequestMethod.POST)
    public String testPage() {


        return "test";
    }

}
