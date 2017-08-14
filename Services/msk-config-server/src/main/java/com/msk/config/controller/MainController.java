package com.msk.config.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msk.comm.consts.NumberConst;
import com.msk.comm.consts.StringConst;
import com.msk.config.bean.RsRequest;
import com.msk.config.bean.RsResponse;
import com.msk.config.bean.TreeBean;
import com.msk.config.dao.RedisExtendsUtils;
import com.msk.config.logic.CodeMasterUpload;
import com.msk.config.logic.ConfigConstantUpload;
import com.msk.config.logic.PropertiesUpload;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

/**
 * Created by shi_yuxi on 2016/6/8.
 */
@Controller
public class MainController {
    @Autowired
    private RedisExtendsUtils redisExtendsUtils;
    //db下tree容器
    public static final String TREE = "tree:";
    //db下0容器
    public static final String TREE_ZERO = "tree:0:";
    //db树name属性
    public static final String NAME = "name";
    //db树value属性
    public static final String VALUE = "value";
    //db树pId属性
    public static final String PID = "pId";
    //db树comment属性
    public static final String COMMENT = "comment";
    //树所有节点集合
    public static final String TREE_HEIRS = "tree:0:heirs";
    //树id起始id
    public static final String BEGIN = "1000";
    //默认设置name
    public static final String DEFAULT = "default";
    private static Logger logger = LoggerFactory.getLogger(MainController.class);
//    //redis hash类型前缀
//    public static final String HASH_PRE = "+H+";
//    //切分转移字符串
//    public static final String HASH_PRE_SPLIT = "\\+H\\+";
//
//    //redis string类型前缀
//    public static final String STRING_PRE = "+S+";
//
//    //切分转移字符串
//    public static final String STRING_PRE_SPLIT = "\\+S\\+";

    //树节点类型
    public interface NodeType {
        /**
         * properties
         */
        String PROPERTIES = "properties";
        /**
         * hash
         */
        String HASH = "hash";
        /**
         * string
         */
        String STRING = "string";
    }

    @RequestMapping(value = "getTree", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    RsResponse<String> getTree(@RequestBody RsRequest<TreeBean> request) throws Exception {
        redisExtendsUtils.setDatabase(NumberConst.IntDef.INT_TWELVE);
        List<TreeBean> result = getTreeService();
        RsResponse<String> response = new RsResponse<String>();

        ObjectMapper o = new ObjectMapper();
        String s = o.writeValueAsString(result);
        response.setResult(s);
        return response;
    }

    @RequestMapping(value = "setTreeNodes", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    RsResponse<String> setTreeNodes(@RequestBody RsRequest<List<TreeBean>> request) {
        redisExtendsUtils.setDatabase(NumberConst.IntDef.INT_TWELVE);
        RsResponse<String> response = new RsResponse<String>();
        List<TreeBean> list = request.getParam();
        if (list != null && list.size() != 0) {
            response.setStatus(setHset(list));
        } else {
            response.setStatus("F");
            response.setMessage("没有新节点！");
        }
        return response;
    }

    @RequestMapping(value = "delTreeNodes", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    RsResponse<String> delTreeNodes(@RequestBody RsRequest<List<TreeBean>> request) {
        redisExtendsUtils.setDatabase(NumberConst.IntDef.INT_TWELVE);
        RsResponse<String> response = new RsResponse<String>();
        List<TreeBean> list = request.getParam();
        if (list != null && list.size() != 0) {
            response.setStatus(delTreeNodesService(list));
        } else {
            response.setStatus("F");
            response.setMessage("没有选择节点！");
        }
        return response;
    }


    /**
     * 往树中添加数据
     *
     * @param list
     * @return
     */
    public String setHset(List<TreeBean> list) {
        String result = null;
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        for (TreeBean tb : list) {
            if (tb.getName() != null) {
                map.put(NAME, tb.getName());
            } else {
                map.put(NAME, StringConst.EMPTY);
            }
            if (tb.getValue() != null) {
                map.put(VALUE, tb.getValue());
            } else {
                map.put(VALUE, StringConst.EMPTY);
            }
            if (tb.getpId() != null) {
                map.put(PID, tb.getpId());
            } else {
                map.put(PID, StringConst.EMPTY);
            }
            if (tb.getComment() != null) {
                map.put(COMMENT, tb.getComment());
            } else {
                map.put(COMMENT, StringConst.EMPTY);
            }
            if (NodeType.PROPERTIES.equals(tb.getType())) {
                // 集合存储到数据库
                redisExtendsUtils.saveRedisMap(TREE + tb.getId(), map);
                // 节点归属于根节点。用于查询树
                redisExtendsUtils.sadd(TREE_HEIRS, tb.getId());
            } else if (NodeType.HASH.equals(tb.getType())) {
                redisExtendsUtils.saveRedisMap(TREE_ZERO + tb.getpId() + StringConst.COLON + tb.getName(), (Map) tb.getMap());
                List<String> checkList = redisExtendsUtils.getList(TREE_ZERO + tb.getpId());
                if (!checkList.contains(TREE_ZERO + tb.getpId() + StringConst.COLON + tb.getName())) {
                    redisExtendsUtils.rpush(TREE_ZERO + tb.getpId(), TREE_ZERO + tb.getpId() + StringConst.COLON + tb.getName());
                }
            } else if (NodeType.STRING.equals(tb.getType())) {
                redisExtendsUtils.set(TREE_ZERO + tb.getpId() + StringConst.COLON + tb.getName(), tb.getValue());
                List<String> checkList = redisExtendsUtils.getList(TREE_ZERO + tb.getpId());
                if (!checkList.contains(TREE_ZERO + tb.getpId() + StringConst.COLON + tb.getName())) {
                    redisExtendsUtils.rpush(TREE_ZERO + tb.getpId(), TREE_ZERO + tb.getpId() + StringConst.COLON + tb.getName());
                }
            }
        }
        result = "S";
        return result;
    }

    /**
     * 删除节点
     *
     * @param list
     * @return
     */
    public String delTreeNodesService(List<TreeBean> list) {
        String result = null;
        for (TreeBean tb : list) {
            if (NodeType.PROPERTIES.equals(tb.getType())) {
                // 删除节点数据
                redisExtendsUtils.delete(TREE + tb.getId());
                // 删除节点引用
                redisExtendsUtils.srem(TREE_HEIRS, tb.getId());
                //如果是叶子的根节点删除集合
                if (redisExtendsUtils.exist(TREE_ZERO + tb.getId())) {
                    redisExtendsUtils.delete(tb.getId());
                }
            } else if (NodeType.HASH.equals(tb.getType()) || NodeType.STRING.equals(tb.getType())) {
                // 删除节点数据
                redisExtendsUtils.delete(TREE_ZERO + tb.getpId() + StringConst.COLON + tb.getName());
                //移除引用
                if (redisExtendsUtils.exist(TREE_ZERO + tb.getpId())) {
                    redisExtendsUtils.rpop(TREE_ZERO + tb.getpId(), TREE_ZERO + tb.getpId() + StringConst.COLON + tb.getName());
                }

            }
        }
        result = "S";
        return result;
    }

    /**
     * 获取树
     *
     * @return
     */
    public List<TreeBean> getTreeService() throws Exception {
        List<TreeBean> result = new ArrayList<TreeBean>();
        List<String> resultId = redisExtendsUtils.sort(TREE_HEIRS);
        Collections.sort(resultId);
        List<String> resultSort = new ArrayList<String>();
        for (String s : resultId) {
            resultSort.add(TREE + s);
        }
        List<Map<String, String>> listNode = redisExtendsUtils.getTreeNode(resultSort);
        for (int i = 0; i < listNode.size(); i++) {
            TreeBean tb = new TreeBean();
            tb.setId(resultId.get(i));
            tb.setName(listNode.get(i).get(NAME));
            tb.setpId(listNode.get(i).get(PID));
            tb.setValue(listNode.get(i).get(VALUE));
            tb.setComment(listNode.get(i).get(COMMENT));
            tb.setType(NodeType.PROPERTIES);
            result.add(tb);
        }
        List<String> resultLeaf = new ArrayList<String>();
        for (String s : resultId) {
            resultLeaf.add(TREE_ZERO + s);
        }
        List<List<String>> listLeaf = redisExtendsUtils.getTreeLeaf(resultLeaf);
//        List<TreeBean> listHash = new ArrayList<TreeBean>();
        List<TreeBean> listString = new ArrayList<TreeBean>();
//        List<String> lhs = new ArrayList<String>();
        List<String> lss = new ArrayList<String>();
        for (int i = 0; i < listLeaf.size(); i++) {
            if (listLeaf.get(i).size() != 0) {
                for (String rs : listLeaf.get(i)) {
                    TreeBean tbHash = new TreeBean();
                    tbHash.setpId(resultId.get(i));
                    String name = rs.split(StringConst.COLON)[rs.split(StringConst.COLON).length - 1];
//                    if (name.startsWith(HASH_PRE)) {
//                        if(name.length() > NumberConst.IntDef.INT_THREE){
//                            name = name.substring(NumberConst.IntDef.INT_THREE, name.length());
//                            lhs.add(rs.split(HASH_PRE_SPLIT)[0] + rs.split(HASH_PRE_SPLIT)[1]);
//                            tbHash.setName(name);
//                        }
//                        tbHash.setType(NodeType.HASH);
//                        listHash.add(tbHash);
////                        Map<String, String> hash = redisExtendsUtils.getRedisMapValue(rs);
////                        tbHash.setMap((Map) hash);
//                    }
//                    if (name.startsWith(STRING_PRE)) {
                        tbHash.setName(name);
                        lss.add(rs);
                        tbHash.setType(NodeType.STRING);
                        listString.add(tbHash);
//                        tbHash.setValue(redisExtendsUtils.get(rs));
//                    }
                    //result.add(tbHash);
                }
            }
        }
//        List<Map<String, String>> resultLh = redisExtendsUtils.getTreeNode(lhs);
        List<String> resultLs = redisExtendsUtils.getStringBatch(lss);
//        for(int i = 0; i < listHash.size(); i++){
//            listHash.get(i).setMap(resultLh.get(i));
//        }
        for(int i = 0; i < listString.size(); i++){
            listString.get(i).setValue(resultLs.get(i));
        }
//        result.addAll(listHash);
        result.addAll(listString);
        return result;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public RsResponse<String> uploadExcel(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
         logger.info("开始导入数据。。。");
        redisExtendsUtils.setDatabase(NumberConst.IntDef.INT_TWELVE);
        redisExtendsUtils.flushdb();
        //整个树的数据
        redisExtendsUtils.setDatabase(NumberConst.IntDef.INT_TWELVE);
        List<TreeBean> listAll = this.getTreeService();
        MultipartFile file = multipartRequest.getFile("Filedata");
        InputStream is = file.getInputStream();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Read the excel template file fail.", e);
        }
        /** Properties sheet begin*/
        Sheet sheetProperties = workbook.getSheet("Properties");
        if (sheetProperties != null) {
            logger.info("开始导入Properties。");
            PropertiesUpload.uploadProperties(workbook, listAll, redisExtendsUtils);
        }
        /** Properties sheet end*/
        /** codemaster sheet begin*/
//        Sheet sheetCodeMaster = workbook.getSheet("CodeMaster");
//        if (sheetCodeMaster != null) {
//            CodeMasterUpload.uploadProperties(workbook, listAll, redisExtendsUtils);
//        }
        /** codemaster sheet end*/
        /** ConfigConstant sheet begin*/
        Sheet sheetConfig = workbook.getSheet("ConfigConstant");
        if (sheetConfig != null) {
            logger.info("开始导入ConfigConstant。");
            ConfigConstantUpload.uploadProperties(workbook, listAll, redisExtendsUtils);
        }
        /** ConfigConstant sheet begin*/
        RsResponse<String> rsResponse = new RsResponse<String>();
        rsResponse.setReturnCode("200");
        rsResponse.setStatus("S");
        return rsResponse;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "index";
    }

    @RequestMapping(value = "/load/properties", method = RequestMethod.GET)
    public String syncCodeMaster() {
        return "/properties/properties";
    }

}
