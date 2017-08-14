package com.msk.common.utils;

import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.msk.common.bean.ControllerResult;
import com.msk.common.bean.RsResponse;
import org.apache.commons.io.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by jackjiang on 16/6/30.
 */
public class RestTest {
    /**测试结果*/
    private Collection<File> restClassFileList;
    private String packageName;
    private URLClassLoader classLoader;
    private String testResource;
    private String baseUrl;
    public RestTest(Collection<File> restClassFileList,String packageName,URLClassLoader classLoader,String testResource,String baseUrl){
        this.restClassFileList = restClassFileList;
        this.packageName = packageName;
        this.classLoader = classLoader;
        this.testResource = testResource;
        this.baseUrl = baseUrl;
    }




    public List<ControllerResult> testRest(){
        List<ControllerResult> resultList = new ArrayList<>();
        for (File restClass: restClassFileList) {
            String restClassFileName = restClass.getName();

            String fileName = restClassFileName.replace(".class", "");

            //去除文件后缀,获得Class Name
            String className = packageName + "." + fileName;
            //通过反射获得Class
            Class<?> clazz = TestUtils.loadClass(classLoader,className);
            //获得Rest Controller注解
            RestController restAnnotation = clazz.getAnnotation(RestController.class);
            //判断当前是否有Rest Controller注解
            if (restAnnotation != null) {
                Method[] methodArray = clazz.getDeclaredMethods();
                for (Method method : methodArray) {
                    ControllerResult result = this.testRest(method,fileName);
                    if(result == null){
                        continue;
                    }
                    resultList.add(result);
                }
            }
        }
        return resultList;
    }
    private ControllerResult testRest(Method method,String fileName){
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if(requestMapping == null){
            return null;
        }
        ControllerResult result = new ControllerResult();
        //获得方法名称
        String methodName = method.getName();
        String url = requestMapping.value()[NumberConst.IntDef.INT_ZERO];
        //输入参数的命名规范和路径为/test/input/className/methodName.json
        String inputParamPatch = testResource + "/input/" + fileName + "/" + methodName + ".json";
        result.setInterName(methodName);
        result.setInterUrl(url);
        String isSussFlag = "失败";
        try {
            String data = fileToString(inputParamPatch, "UTF-8");
            JSONPObject jsonpObject = new JSONPObject(data.replace("\n","").replace("\r","").replace(" ",""));
            url = this.baseUrl + url;
            RsResponse<String> response = RestClientUtil.post(url, jsonpObject, new TypeReference<RsResponse<String>>() {});
            if ("F".equals(response.getStatus())) {
                result.setIsSussFlag(isSussFlag);
                result.setReason(response.getMessage());
                return result;
            }
            String responseResult = response.getResult().replace("\n","").replace("\r","").replace(" ","");
            String outFilePath = testResource + "/output/" + fileName + "/" + methodName + ".json";
            String outFileData = fileToString(outFilePath, "UTF-8").replace("\n","").replace("\r","").replace(" ","");
            if(!StringUtils.isEmpty(outFileData) && outFileData.equals(responseResult)){
                isSussFlag = "成功！";
            }
            else{
                result.setReason("返回结果和预期不一致！");
            }
        } catch (Exception e) {
            result.setIsSussFlag(isSussFlag);
            result.setReason(e.toString());
            return result;
        }
        return result;
    }

    /**
     * 读取文件到字符串中
     *
     * @param strFilePath 文件全路径(含文件名)
     * @param strCoding   编码格式
     * @return String
     */
    public static String fileToString(String strFilePath, String strCoding) throws Exception{
        StringBuffer strBuffResult = new StringBuffer();
        int i = 0;
        if (strCoding == null || strCoding.trim().length() <= 0) {
            strCoding = "UTF-8";
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(strFilePath), strCoding));
            while ((i = bufferedReader.read()) != -1) {
                strBuffResult.append((char) i);
            }
            bufferedReader.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            bufferedReader = null;
        }
        // 释放对象
        strCoding = null;
        strFilePath = null;
        return strBuffResult.toString();
    }
    public void writerFile(List<ControllerResult> listPage, URLClassLoader testClassLoader){
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("out", listPage);
        Writer w = null;
        File fileOut = new File(testResource + "/output" + "/"+System.currentTimeMillis()+".html");
        try {
            w = new OutputStreamWriter(org.apache.commons.io.FileUtils.openOutputStream(fileOut), "UTF-8");
            String templetPath = testClassLoader.getResource("resources/templet").getPath();
            templetPath = URLDecoder.decode(templetPath, "utf-8");
            FreemarkerUtil.processTemplate("out.ftl", root, w, templetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
