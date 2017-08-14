package com.msk.config.utils;

import com.msk.comm.consts.StringConst;
import com.msk.comm.util.DateTimeUtil;
import com.msk.config.bean.TreeBean;
import com.msk.config.controller.MainController;
import com.msk.config.dao.RedisExtendsUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by shi_yuxi on 2016/6/21.
 */
public class UploadUtil {
    //每层节点占用长度
    public static final int NODE_LENGTH = 4;

    /**
     *  添加根节点
     *  @param sheetProperties sheet页
     *  @param listAll
     *  @param redisExtendsUtils
     *  @return String 新增节点id
     */
    public static String setRootNode(Sheet sheetProperties, List<TreeBean> listAll, RedisExtendsUtils redisExtendsUtils){

        String typeId = null;
        //查询Properties节点id
        for(TreeBean bean : listAll){
            if((sheetProperties.getSheetName()).equals(bean.getName()) && StringUtils.isEmpty(bean.getpId())){
                typeId = bean.getId();
                break;
            }
        }

        if(StringUtils.isEmpty(typeId)){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put(MainController.NAME, sheetProperties.getSheetName());
            map.put(MainController.VALUE, StringConst.EMPTY);
            map.put(MainController.COMMENT, StringConst.EMPTY);
            map.put(MainController.PID, StringConst.EMPTY);
            List<String> typeIds = new ArrayList<String>();
            for(TreeBean bean : listAll){
                if(StringUtils.isEmpty(bean.getpId())){
                    typeIds.add(bean.getId());
                }
            }
            Collections.sort(typeIds);
            if(typeIds.size() == 0) {
                typeId = MainController.BEGIN;
                redisExtendsUtils.saveRedisMap(MainController.TREE + MainController.BEGIN, map);
            }else {
                //求出新增的跟节点id
                String bigRootId = typeIds.get(typeIds.size() -1);
                typeId = (Integer.parseInt(bigRootId) + 1) + StringConst.EMPTY;
                redisExtendsUtils.saveRedisMap(MainController.TREE + typeId, map);
            }
            // 节点归属于根节点。用于查询树
            redisExtendsUtils.sadd(MainController.TREE_HEIRS, typeId);
            //将新增节点添加到listall中
            TreeBean treeBean = new TreeBean();
            treeBean.setId(typeId);
            treeBean.setName((String) map.get(MainController.NAME));
            listAll.add(treeBean);
        }

        return typeId;
    }
    /**
     *  添加子节点
     *  @param typeId 父节点id
     *  @param listAll
     *  @param redisExtendsUtils
     *  @return String 新增节点id
     */
    public static String setNode(String typeId, List<TreeBean> listAll, Map<String, Object> map, RedisExtendsUtils redisExtendsUtils){
        List<String> typeIds = new ArrayList<String>();
        for(TreeBean bean : listAll){
            if(typeId.equals(bean.getpId())){
                typeIds.add(bean.getId());
            }
        }
        Collections.sort(typeIds);
        if(typeIds.size() == 0) {
            typeId = typeId + padLeft("1",NODE_LENGTH, "0");
            redisExtendsUtils.saveRedisMap(MainController.TREE + typeId, map);
        }else {
            //求出新增的跟节点id
            String bigRootId = typeIds.get(typeIds.size() -1);
            //数字+1 在左边用0补全四位数
            String temp = Integer.parseInt(bigRootId.substring(bigRootId.length() - NODE_LENGTH, bigRootId.length())) + 1 + "";
            typeId = bigRootId.substring(0, bigRootId.length() - NODE_LENGTH) + padLeft(temp,NODE_LENGTH, "0");
            redisExtendsUtils.saveRedisMap(MainController.TREE + typeId, map);
        }
        // 节点归属于根节点。用于查询树
        redisExtendsUtils.sadd(MainController.TREE_HEIRS, typeId);
        //将新增节点添加到listall中
        TreeBean treeBean = new TreeBean();
        treeBean.setId(typeId);
        treeBean.setName((String)map.get(MainController.NAME));
        treeBean.setpId((String)map.get(MainController.PID));
        treeBean.setValue((String) map.get(MainController.VALUE));
        treeBean.setComment((String) map.get(MainController.COMMENT));
        listAll.add(treeBean);
        return typeId;
    }


    /**
     * pad the pad to a string's left until full length.
     *
     * @param value a string value
     * @param length pad to length
     * @param pad the pad string
     * @return a string
     */
    public static String padLeft(String value, int length, String pad) {

        String tmpStr = value;
        if (tmpStr == null) {
            tmpStr = "";
        }

        String tmpPad = "";

        if (tmpStr.length() < length) {
            for (int i = 1; i <= length - tmpStr.length(); i++) {
                tmpPad = pad.concat(tmpPad);
            }
        }

        return tmpPad.concat(tmpStr);
    }

    /**
     *
     * <p>
     * get  cell value
     * </p>
     *
     * @param cell cell
     * @return string value
     */
    public static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                return "";
            case Cell.CELL_TYPE_BOOLEAN:
                if (cell.getBooleanCellValue()) {
                    return "TRUE";
                } else {
                    return "FALSE";
                }
            case Cell.CELL_TYPE_STRING: {
                if (cell.getStringCellValue() != null) {
                    return cell.getStringCellValue().trim();
                } else {
                    return "";
                }
            }
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.FORMAT_DDMMMYYYY, Locale.ENGLISH);
                    return sdf.format(cell.getDateCellValue());
                }
                return NumberToTextConverter.toText(cell.getNumericCellValue());
            case Cell.CELL_TYPE_FORMULA: {
                if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                        return sdf.format(cell.getDateCellValue());
                    }
                    return NumberToTextConverter.toText(cell.getNumericCellValue());
                } else if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_STRING) {
                    return cell.getStringCellValue();
                } else {
                    return cell.getCellFormula();
                }
            }
            default:
                return "";
        }
    }
}
