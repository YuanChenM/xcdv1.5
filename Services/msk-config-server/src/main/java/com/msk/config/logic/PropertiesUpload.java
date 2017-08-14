package com.msk.config.logic;

import com.msk.comm.consts.NumberConst;
import com.msk.config.bean.TreeBean;
import com.msk.config.controller.MainController;
import com.msk.config.dao.RedisExtendsUtils;
import com.msk.config.utils.UploadUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shi_yuxi on 2016/6/21.
 */
public class PropertiesUpload {

    /**
     *  上传数据
     *  @param workbook excel
     *  @param listAll
     *  @param redisExtendsUtils
     */
    public static void uploadProperties(Workbook workbook, List<TreeBean> listAll, RedisExtendsUtils redisExtendsUtils){

        //sheet默认Properties
        Sheet sheetProperties = workbook.getSheet("Properties");
        //插入根节点
        String typeId = UploadUtil.setRootNode(sheetProperties, listAll, redisExtendsUtils);
        int rows = sheetProperties.getLastRowNum();
        //默认从第三行第三列开始
        for(int index = 2; index <= rows; index++){
            Row row = sheetProperties.getRow(index);
            Cell cellThree = row.getCell(NumberConst.IntDef.INT_TWO);
            Cell cellFour = row.getCell(NumberConst.IntDef.INT_THREE);
            Cell cellFive = row.getCell(NumberConst.IntDef.INT_FOUR);
            Cell cellSix = row.getCell(NumberConst.IntDef.INT_FIVE);
            Cell cellSeven = row.getCell(NumberConst.IntDef.INT_SIX);
            if(StringUtils.isEmpty(UploadUtil.getStringCellValue(cellThree))){
                continue;
            }
            //如果是默认的话
            if(MainController.DEFAULT.equals(UploadUtil.getStringCellValue(cellThree))){
                String defaultId = "";
                for(TreeBean bean : listAll){
                    if(typeId.equals(bean.getpId()) && MainController.DEFAULT.equals(bean.getName())){
                        defaultId = bean.getId();
                        break;
                    }
                }
                //数据库default不存在的情况
                if(StringUtils.isEmpty(defaultId)){
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put(MainController.NAME, MainController.DEFAULT);
                    map.put(MainController.VALUE, UploadUtil.getStringCellValue(cellSix));
                    map.put(MainController.PID, typeId);
                    map.put(MainController.COMMENT, UploadUtil.getStringCellValue(cellSeven));
                    defaultId = UploadUtil.setNode(typeId, listAll, map, redisExtendsUtils);
                }
                if(!StringUtils.isEmpty(UploadUtil.getStringCellValue(cellFive))){
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put(MainController.NAME, UploadUtil.getStringCellValue(cellFive));
                    map.put(MainController.VALUE, UploadUtil.getStringCellValue(cellSix));
                    map.put(MainController.COMMENT, UploadUtil.getStringCellValue(cellSeven));
                    map.put(MainController.PID, defaultId);
                    UploadUtil.setNode(defaultId, listAll, map, redisExtendsUtils);
                }
            }else{
                String environmentString = UploadUtil.getStringCellValue(cellThree);
                String environmentId = "";
                for(TreeBean bean : listAll){
                    if(typeId.equals(bean.getpId()) && environmentString.equals(bean.getName())){
                        environmentId = bean.getId();
                        break;
                    }
                }
                //添加环境节点
                if(StringUtils.isEmpty(environmentId)){
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put(MainController.NAME, environmentString);
                    map.put(MainController.VALUE, UploadUtil.getStringCellValue(cellSix));
                    map.put(MainController.COMMENT, UploadUtil.getStringCellValue(cellSeven));
                    map.put(MainController.PID, typeId);
                    environmentId = UploadUtil.setNode(typeId, listAll, map, redisExtendsUtils);
                }
                String modelString = UploadUtil.getStringCellValue(cellFour);
                String modelId = "";
                if(!StringUtils.isEmpty(modelString)){
                    for(TreeBean bean : listAll){
                        if(environmentId.equals(bean.getpId()) && modelString.equals(bean.getName())){
                            modelId = bean.getId();
                            break;
                        }
                    }
                    //添加模块节点
                    if(StringUtils.isEmpty(modelId)){
                        Map<String,Object> mapModel = new HashMap<String,Object>();
                        mapModel.put(MainController.NAME, modelString);
                        mapModel.put(MainController.VALUE, UploadUtil.getStringCellValue(cellSix));
                        mapModel.put(MainController.COMMENT, UploadUtil.getStringCellValue(cellSeven));
                        mapModel.put(MainController.PID, environmentId);
                        modelId = UploadUtil.setNode(environmentId, listAll, mapModel, redisExtendsUtils);
                    }
                    String keyString = UploadUtil.getStringCellValue(cellFive);
                    //添加叶子节点
                    if(!StringUtils.isEmpty(keyString)){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put(MainController.NAME, keyString);
                        map.put(MainController.VALUE, UploadUtil.getStringCellValue(cellSix));
                        map.put(MainController.COMMENT, UploadUtil.getStringCellValue(cellSeven));
                        map.put(MainController.PID, modelId);
                        UploadUtil.setNode(modelId, listAll, map, redisExtendsUtils);
                    }
                }
            }
        }
    }
}
