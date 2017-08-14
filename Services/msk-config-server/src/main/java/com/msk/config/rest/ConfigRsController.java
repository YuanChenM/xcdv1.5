package com.msk.config.rest;

import com.msk.comm.consts.NumberConst;
import com.msk.comm.consts.StringConst;
import com.msk.comm.util.StringUtil;
import com.msk.config.bean.ConfigParam;
import com.msk.config.bean.RsRequest;
import com.msk.config.bean.RsResponse;
import com.msk.config.bean.TreeBean;
import com.msk.config.controller.MainController;
import com.msk.config.dao.RedisExtendsUtils;
import com.msk.config.logic.SystemMenuLogic;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * *RedisWebService
 * *@author shi_yuxi
 * *@version 1.0
 */
@RestController
@RequestMapping("api")
public class ConfigRsController {

    @Autowired
    private RedisExtendsUtils redisExtendsUtils;

    @Autowired
    private MainController configTree;

    @Autowired
    private SystemMenuLogic systemMenuLogic;

    private static String COM = "default";

//    private static String MENUS = "list";
//
//    private static String NAME = "name";
//
//    private static String ALL = "ALL";


    @RequestMapping(value = "/load/properties", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    RsResponse<Map<String, String>> sendTreeNode(@RequestBody RsRequest<ConfigParam> request) throws Exception{
        redisExtendsUtils.setDatabase(NumberConst.IntDef.INT_TWELVE);
        RsResponse<Map<String, String>> response = new RsResponse<Map<String, String>>();
        List<TreeBean> result = null;
        Map<String, String> map = new HashMap<String, String>();
        result = getConfig(request.getParam());
        for(TreeBean tb : result){
            map.put(tb.getName(), tb.getValue());
        }
        response.setResult(map);
        response.setStatus("S");
        response.setReturnCode("200");
        response.setMessage("成功！");
        return response;
    }

    /**
     * 求出congi配置
     *
     * @param config
     * @return 结果
     */
    public List<TreeBean> getConfig(ConfigParam config) throws Exception {
        redisExtendsUtils.setDatabase(NumberConst.IntDef.INT_TWELVE);
        List<TreeBean> listAll = configTree.getTreeService();
        List<TreeBean> listDef = new ArrayList<TreeBean>();
        List<TreeBean> listNum = new ArrayList<TreeBean>();
        List<TreeBean> listT = new ArrayList<TreeBean>();
        List<TreeBean> listDefRoot = new ArrayList<TreeBean>();
        List<TreeBean> listResult = new ArrayList<TreeBean>();
        String comName = "";
        String modelName = "";
        String type = "";
        if (StringUtils.isEmpty(config.getModelName())
                || StringUtils.isEmpty(config.getEnvironment())
                || StringUtils.isEmpty(config.getType())) {
            throw new Exception("参数错误！");
        } else {
            modelName = config.getModelName();
            comName = COM;
            type = config.getType();
        }
        String typeId = null;
        //查询type节点id
        for(TreeBean bean : listAll){
            if(type.equals(bean.getName()) && StringUtils.isEmpty(bean.getpId())){
                typeId = bean.getId();
                break;
            }
        }
        if(StringUtils.isEmpty(typeId)){
            throw new Exception("根节点不存在！");
        }
        String environmentId = null;
        //查询环境节点id
        for (TreeBean bean : listAll) {
            if (config.getEnvironment().equals(bean.getName()) && typeId.equals(bean.getpId())) {
                environmentId = bean.getId();
                break;
            }
        }
        // 单独查询所有的默认配置
        if (!COM.equals(config.getEnvironment())) {
            String comPId = null;
            String numPId = null;
            // 根据参数查询id
            if (StringUtils.isEmpty(environmentId)) {
                throw new Exception();
            }
            for (TreeBean bean : listAll) {
                if (comName.equals(bean.getName()) && environmentId.equals(bean.getpId())) {
                    comPId = bean.getId();
                }
                if (modelName.equals(bean.getName()) && environmentId.equals(bean.getpId())) {
                    numPId = bean.getId();
                }
            }
            // 根据id查询出配置
            if (StringUtils.isEmpty(numPId)) {
                throw new Exception();
            }
            // 环境下默认配置
            if (!StringUtils.isEmpty(comPId)) {
                for (TreeBean bean : listAll) {
                    if (comPId.equals(bean.getpId())) {
                        listDef.add(bean);
                    }
                }
            }

            // 模块配置
            if (!COM.equals(modelName)) {
                for (TreeBean bean : listAll) {
                    if (numPId.equals(bean.getpId())) {
                        listNum.add(bean);
                    }
                }
            }
        }
        // 所有环境默认配置
        String allDef = null;
        for (TreeBean bean : listAll) {
            if (COM.equals(bean.getName()) && typeId.equals(bean.getpId())) {
                allDef = bean.getId();
                break;
            }
        }
        String numDef = null;
        if (allDef != null) {
            for (TreeBean bean : listAll) {
                if (allDef.equals(bean.getpId())) {
                    if(modelName.equals(bean.getName())){
                        numDef = bean.getId();
                    }
                    //求出根目录下所有叶子
                    listDefRoot.add(bean);
                    for(TreeBean tb : listAll){
                        if(bean.getId().equals(tb.getpId())){
                            listDefRoot.remove(bean);
                        }
                    }
                }
            }
            if (numDef != null) {
                for (TreeBean bean : listAll) {
                    if (numDef.equals(bean.getpId())) {
                        listT.add(bean);
                    }
                }
            }
        }

        Map<String, TreeBean> map = new HashMap<String, TreeBean>();
        //根目录下所有叶子
        for(TreeBean bean : listDefRoot){
            map.put(bean.getName(), bean);
        }
        for (TreeBean bean : listT) {
            map.put(bean.getName(), bean);
        }
        for (TreeBean bean : listDef) {
            map.put(bean.getName(), bean);
        }
        for (TreeBean bean : listNum) {
            map.put(bean.getName(), bean);
        }
        for (Map.Entry<String, TreeBean> entry : map.entrySet()) {
            entry.getValue().setId(null);
            entry.getValue().setpId(null);
            entry.getValue().setComment(null);
            listResult.add(entry.getValue());
        }
        return listResult;
    }

    @RequestMapping(value = "/load/code/master", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody RsResponse<Map<String,String>> sendCodeMaster(@RequestBody RsRequest<ConfigParam> request) throws Exception{
//        List<TreeBean> listAll = configTree.getTreeService();
        ConfigParam config = request.getParam();
//        if (StringUtils.isEmpty(config.getKey())
//                || StringUtils.isEmpty(config.getType())) {
//            throw new Exception();
//        }
//        String typeId = null;
//        //查询type节点id
//        for(TreeBean bean : listAll){
//            if(config.getType().equals(bean.getName()) && StringUtils.isEmpty(bean.getpId())){
//                typeId = bean.getId();
//                break;
//            }
//        }
//        if(StringUtils.isEmpty(typeId)){
//            throw new Exception("根节点不存在");
//        }
//        String modelId = null;
//        List<String> modelIds = new ArrayList<String>();
//        for(TreeBean bean : listAll){
//            if(typeId.equals(bean.getpId())){
//                modelIds.add(bean.getId());
//                if(!StringUtils.isEmpty(config.getModelName()) && config.getModelName().equals(bean.getName())){
//                    modelId = bean.getId();
//                }
//            }
//        }
//        //如果不传模块
//        if(StringUtils.isEmpty(config.getModelName())){
//            String key = null;
//            for(String id : modelIds){
//                for(TreeBean bean : listAll){
//                    if(config.getKey().equals(bean.getName()) && id.equals(bean.getpId())){
//                        key = bean.getName();
//                        break;
//                    }
//                }
//                if(!StringUtils.isEmpty(key)){
//                    Map<String,String> map = redisExtendsUtils.getRedisMapValue(MainController.TREE_ZERO + id + StringConst.COLON  + key);
//                    RsResponse<Map<String, String>> response = new RsResponse<Map<String, String>>();
//                    response.setResult(map);
//                    response.setStatus("S");
//                    response.setMessage("成功！");
//                    response.setReturnCode("200");
//                    return response;
//                }
//            }
//            throw new Exception("叶子节点不存在！");
//        }
//        if(StringUtils.isEmpty(modelId)){
//            throw new Exception("模块不存在！");
//        }
//        String keyName = null;
//        for(TreeBean bean : listAll){
//            if(config.getKey().equals(bean.getName()) && modelId.equals(bean.getpId())){
//                keyName = bean.getName();
//            }
//        }
//        if(StringUtils.isEmpty(keyName)){
//            throw new Exception("叶子节点不存在！");
//        }
//        Map<String,String> map = redisExtendsUtils.getRedisMapValue(MainController.TREE_ZERO + modelId + StringConst.COLON  + keyName);
        if(StringUtils.isEmpty(config.getKey())){
            throw new Exception("key不能为空！");
        }
        redisExtendsUtils.setDatabase(NumberConst.IntDef.INT_ZERO);
        Map<String,String> map = redisExtendsUtils.getRedisMapValue(config.getKey());
        RsResponse<Map<String, String>> response = new RsResponse<Map<String, String>>();
        response.setResult(map);
        response.setStatus("S");
        response.setMessage("成功！");
        response.setReturnCode("200");
        return response;
    }

    @RequestMapping(value = "/load/config/constant", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody RsResponse<Map<String,String>> sendUrl(@RequestBody RsRequest<ConfigParam> request) throws Exception{
        redisExtendsUtils.setDatabase(NumberConst.IntDef.INT_TWELVE);
        List<TreeBean> listAll = configTree.getTreeService();
        Map<String, String> map = new HashMap<>();
        ConfigParam config = request.getParam();
        if (StringUtils.isEmpty(config.getEnvironment())
                || StringUtils.isEmpty(config.getType())
                || StringUtils.isEmpty(config.getModelName())) {
            throw new Exception();
        }
        String typeId = null;
        //查询type节点id
        for(TreeBean bean : listAll){
            if(config.getType().equals(bean.getName()) && StringUtils.isEmpty(bean.getpId())){
                typeId = bean.getId();
                break;
            }
        }
        if(StringUtils.isEmpty(typeId)){
            throw new Exception("根节点不存在！");
        }
        String defKeyName = "";
        //求出default环境下数据
        String defEnvironmentId = null;
        for(TreeBean bean : listAll){
            if(COM.equals(bean.getName()) && typeId.equals(bean.getpId())){
                defEnvironmentId = bean.getId();
            }
        }
        //默认环境节点存在时，才继续
        String defModelId = null;
        if(!StringUtil.isEmpty(defEnvironmentId)){
            for(TreeBean bean : listAll){
                if(config.getModelName().equals(bean.getName()) && defEnvironmentId.equals(bean.getpId())){
                    defModelId = bean.getId();
                }
            }
            //默认环境下模块节点存在是才继续
            if(!StringUtil.isEmpty(defModelId)){
                if(!StringUtil.isEmpty(config.getKey())){
                    for(TreeBean bean : listAll){
                        if(config.getKey().equals(bean.getName()) && defModelId.equals(bean.getpId())){
                            defKeyName = bean.getName();
                        }
                    }
                }
            }
        }

        String environmentId = null;
        String environmentUrl = null;
        for(TreeBean bean : listAll){
            if(config.getEnvironment().equals(bean.getName()) && typeId.equals(bean.getpId())){
                environmentId = bean.getId();
                environmentUrl = bean.getValue();
            }
        }
        if(StringUtils.isEmpty(environmentId)){
            throw new Exception("环境节点不存在！");
        }
        String modelId = null;
        String modelUrl = null;
        for(TreeBean bean : listAll){
            if(config.getModelName().equals(bean.getName()) && environmentId.equals(bean.getpId())){
                modelId = bean.getId();
                modelUrl = bean.getValue();
            }
        }
        if(StringUtils.isEmpty(modelId)){
            throw new Exception("模块节点不存在！");
        }
        String keyName = "";
        if(!StringUtil.isEmpty(config.getKey())){
            for(TreeBean bean : listAll){
                if(config.getKey().equals(bean.getName()) && modelId.equals(bean.getpId())){
                    keyName = bean.getName();
                }
            }
        }else{
            keyName = "key";
        }
        if(StringUtils.isEmpty(defKeyName) && StringUtils.isEmpty(keyName)){
            throw new Exception("叶子节点不存在！");
        }
        String defValue = redisExtendsUtils.get(MainController.TREE_ZERO + defModelId + StringConst.COLON  + defKeyName);
        String value = redisExtendsUtils.get(MainController.TREE_ZERO + modelId + StringConst.COLON  + keyName);
        if("key".equals(keyName) && value == null){
            map.put(keyName, value);
        }else if(defValue != null && value == null){
            map.put(defKeyName, defValue);
        }else if(defValue == null && value != null){
            map.put(keyName, value);
        }else if(defValue != null && value != null){
            map.put(keyName, value);
        }else{
            map.put(defKeyName, defValue);
        }
        map.put(config.getEnvironment(), environmentUrl);
        map.put(config.getModelName(), modelUrl);
        RsResponse<Map<String, String>> response = new RsResponse<Map<String, String>>();
        response.setResult(map);
        response.setStatus("S");
        response.setMessage("成功！");
        response.setReturnCode("200");
        return response;
    }

    @RequestMapping(value = "/load/config/constant/list", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody RsResponse<List<Map<String,String>>> sendUrls(@RequestBody RsRequest<ConfigParam> request) throws Exception{
        redisExtendsUtils.setDatabase(NumberConst.IntDef.INT_TWELVE);
        List<TreeBean> listAll = configTree.getTreeService();
        ConfigParam config = request.getParam();
        if (StringUtils.isEmpty(config.getType()) || COM.equals(config.getEnvironment())) {
            throw new Exception("参数不正确！");
        }
        String typeId = null;
        //查询type节点id
        for(TreeBean bean : listAll){
            if(config.getType().equals(bean.getName()) && StringUtils.isEmpty(bean.getpId())){
                typeId = bean.getId();
                break;
            }
        }
        if(StringUtils.isEmpty(typeId)){
            throw new Exception("根节点不存在！");
        }
        //查询环境数据
        List<Map<TreeBean, List<TreeBean>>> mapList = new ArrayList<Map<TreeBean, List<TreeBean>>>();
        if(!StringUtils.isEmpty(config.getEnvironment())){
            for(TreeBean bean : listAll){
                if(config.getEnvironment().equals(bean.getName()) && typeId.equals(bean.getpId())){
                    Map<TreeBean, List<TreeBean>> map = new LinkedHashMap<TreeBean, List<TreeBean>>();
                    map.put(bean, new ArrayList<TreeBean>());
                    mapList.add(map);
                }
                if(COM.equals(bean.getName()) && typeId.equals(bean.getpId())){
                    Map<TreeBean, List<TreeBean>> map = new LinkedHashMap<TreeBean, List<TreeBean>>();
                    map.put(bean, new ArrayList<TreeBean>());
                    mapList.add(map);
                }
            }
            if(mapList.size() == NumberConst.IntDef.INT_ZERO){
                throw new Exception("环境节点不存在！");
            }
        }
        else{
            for(TreeBean bean : listAll){
                if(typeId.equals(bean.getpId())){
                    Map<TreeBean, List<TreeBean>> map = new LinkedHashMap<TreeBean, List<TreeBean>>();
                    map.put(bean, new ArrayList<TreeBean>());
                    mapList.add(map);
                }
            }
        }
        //查询模块数据
        if(!StringUtils.isEmpty(config.getModelName())){
            boolean flag = false;
            for(Map<TreeBean, List<TreeBean>> env : mapList){
                for(Map.Entry<TreeBean, List<TreeBean>> model : env.entrySet()){
                    for(TreeBean bean : listAll){
                        if(config.getModelName().equals(bean.getName()) && (model.getKey().getId()).equals(bean.getpId())){
                            model.getValue().add(bean);
                            flag = true;
                        }
                    }
                }
            }
            if(!flag){
                throw new Exception("模块节点不存在！");
            }
        }
        else{
            for(Map<TreeBean, List<TreeBean>> env : mapList){
                for(Map.Entry<TreeBean, List<TreeBean>> model : env.entrySet()){
                    for(TreeBean bean : listAll){
                        if((model.getKey().getId()).equals(bean.getpId())){
                            model.getValue().add(bean);
                        }
                    }
                }
            }
        }

        List<Map<String, String>> modelList = new ArrayList<Map<String, String>>();
        List<Map<String, String>> modelDefList = new ArrayList<Map<String, String>>();
        for(Map<TreeBean, List<TreeBean>> env : mapList) {
            for (Map.Entry<TreeBean, List<TreeBean>> model : env.entrySet()) {
                for(TreeBean s : model.getValue()){
                    Map<String, String> temp = new LinkedHashMap<String,String>();
                    temp.put("envName", model.getKey().getName());
                    temp.put("env", model.getKey().getValue());
                    temp.put("model", s.getValue());
                    temp.put("modelId", s.getId());
                    temp.put("modelName", s.getName());
                    if(COM.equals(model.getKey().getName())){
                        modelDefList.add(temp);
                    }else{
                        modelList.add(temp);
                    }
                }
            }
        }
        if(modelList.size() == 0){
            throw new Exception("环境节点不存在！");
        }
        List<Map<String,String>> resultMap = new ArrayList<Map<String, String>>();
        if(StringUtils.isEmpty(config.getKey())){
            for(Map<String, String> model : modelList){
                List<String> listModelUrl = new ArrayList<String>();
                for(TreeBean bean : listAll){
                    if((model.get("modelId")).equals(bean.getpId())){
                        Map<String,String> map = new LinkedHashMap<String, String>();
                        map.put(bean.getName(), bean.getValue());
                        map.put(model.get("envName"), model.get("env"));
                        map.put(model.get("modelName"), model.get("model"));
                        resultMap.add(map);
                        listModelUrl.add(bean.getName());
                    }
                }
                for(Map<String, String> modelDef : modelDefList){
                    for(TreeBean bean : listAll){
                        if((modelDef.get("modelId")).equals(bean.getpId()) && model.get("modelName").equals(modelDef.get("modelName")) && !listModelUrl.contains(bean.getName())){
                            Map<String,String> map = new LinkedHashMap<String, String>();
                            map.put(bean.getName(), bean.getValue());
                            map.put(model.get("envName"), model.get("env"));
                            map.put(model.get("modelName"), model.get("model"));
                            resultMap.add(map);
                        }
                    }
                }
            }
        }
        else{
            boolean flag = false;
            for(Map<String, String> model : modelList){
                List<String> listModelUrl = new ArrayList<String>();
                for(TreeBean bean : listAll){
                    if((model.get("modelId")).equals(bean.getpId()) && config.getKey().equals(bean.getName())){
                        Map<String,String> map = new LinkedHashMap<String, String>();
                        map.put(bean.getName(), bean.getValue());
                        map.put(model.get("envName"), model.get("env"));
                        map.put(model.get("modelName"), model.get("model"));
                        resultMap.add(map);
                        listModelUrl.add(bean.getName());
                        flag = true;
                    }
                }
                for(Map<String, String> modelDef : modelDefList){
                    for(TreeBean bean : listAll){
                        if((modelDef.get("modelId")).equals(bean.getpId()) && model.get("modelName").equals(modelDef.get("modelName")) && !listModelUrl.contains(bean.getName()) && config.getKey().equals(bean.getName())){
                            Map<String,String> map = new LinkedHashMap<String, String>();
                            map.put(bean.getName(), bean.getValue());
                            map.put(model.get("envName"), model.get("env"));
                            map.put(model.get("modelName"), model.get("model"));
                            resultMap.add(map);
                            flag = true;
                        }
                    }
                }
            }
            if(!flag){
                throw new Exception("key值不存在！");
            }
        }
        RsResponse<List<Map<String,String>>> response = new RsResponse<List<Map<String,String>>>();
        response.setResult(resultMap);
        response.setStatus("S");
        response.setMessage("成功！");
        response.setReturnCode("200");
        return response;
    }

    @RequestMapping(value = "/load/system/menu", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody RsResponse<TreeBean> sendSystemMenu(@RequestBody RsRequest<ConfigParam> request) throws Exception{
        redisExtendsUtils.setDatabase(NumberConst.IntDef.INT_TWELVE);
        List<TreeBean> listAll = configTree.getTreeService();
        ConfigParam config = request.getParam();
        if (StringUtils.isEmpty(config.getEnvironment())
                || StringUtils.isEmpty(config.getType())
                || StringUtils.isEmpty(config.getAll())
                || StringUtils.isEmpty(config.getSystemRootCode())
                || StringUtils.isEmpty(config.getSystemCode())) {
            throw new Exception("参数不能为空！");
        }
        String typeId = null;
        //查询type节点id
        for(TreeBean bean : listAll){
            if(config.getType().equals(bean.getName()) && StringUtils.isEmpty(bean.getpId())){
                typeId = bean.getId();
                break;
            }
        }
        if(StringUtils.isEmpty(typeId)){
            throw new Exception("根节点不存在！");
        }
        String baseUrl = null;
        String environmentId = null;
        for(TreeBean bean : listAll){
            if(config.getEnvironment().equals(bean.getName()) && typeId.equals(bean.getpId())){
                environmentId = bean.getId();
                baseUrl = bean.getValue();
            }
        }
        if(StringUtils.isEmpty(environmentId)){
            throw new Exception("环境节点不存在！");
        }
        String allId = null;
        for(TreeBean bean : listAll){
            if(config.getAll().equalsIgnoreCase(bean.getName()) && environmentId.equals(bean.getpId())){
                allId = bean.getId();
            }
        }
        if(StringUtils.isEmpty(allId)){
            throw new Exception("ALL节点不存在！");
        }
        String systemRootCodeId = null;
        for(TreeBean bean : listAll){
            if(config.getSystemRootCode().equals(bean.getName()) && allId.equals(bean.getpId())){
                systemRootCodeId = bean.getId();
            }
        }
        if(StringUtils.isEmpty(systemRootCodeId)){
            throw new Exception("跟目录不存在！");
        }
        //返回结果
        TreeBean result = new TreeBean();
        String systemCodeId = null;
        for(TreeBean bean : listAll){
            if(config.getSystemCode().equals(bean.getName()) && systemRootCodeId.equals(bean.getpId())){
                systemCodeId = bean.getId();
                BeanUtils.copyProperties(bean, result);
            }
        }
        if(StringUtils.isEmpty(systemCodeId)){
            throw new Exception("系统代码不存在!");
        }
        result.setBaseUrl(baseUrl);
        result.setId(null);
        result.setpId(null);
        result.setType(null);
        systemMenuLogic.getSystemMenu(systemCodeId, listAll, result);
        RsResponse<TreeBean> response = new RsResponse<TreeBean>();
        response.setResult(result);
        response.setStatus("S");
        response.setMessage("成功！");
        response.setReturnCode("200");
        return response;
    }
}
