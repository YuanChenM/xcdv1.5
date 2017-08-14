package com.msk.config.logic;

import com.msk.comm.consts.NumberConst;
import com.msk.comm.consts.StringConst;
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
public class ConfigConstantUpload {

    /**
     *  上传数据
     *  @param workbook excel
     *  @param listAll
     *  @param redisExtendsUtils
     */
    public static void uploadProperties(Workbook workbook, List<TreeBean> listAll, RedisExtendsUtils redisExtendsUtils){
        Sheet sheetConfig = workbook.getSheet("ConfigConstant");
        String configId = UploadUtil.setRootNode(sheetConfig, listAll, redisExtendsUtils);
        int rowsConfig = sheetConfig.getLastRowNum();
        //默认从第三行第三列开始
        for(int index = 2; index <= rowsConfig; index++){
            Row row = sheetConfig.getRow(index);
            Cell cellThree = row.getCell(NumberConst.IntDef.INT_TWO);
            Cell cellFour = row.getCell(NumberConst.IntDef.INT_THREE);
            Cell cellFive = row.getCell(NumberConst.IntDef.INT_FOUR);
            Cell cellSix = row.getCell(NumberConst.IntDef.INT_FIVE);
            if(StringUtils.isEmpty(UploadUtil.getStringCellValue(cellThree))){
                continue;
            }

            String environmentString = UploadUtil.getStringCellValue(cellThree);
            String environmentId = "";
            for(TreeBean bean : listAll){
                if(configId.equals(bean.getpId()) && environmentString.equals(bean.getName())){
                    environmentId = bean.getId();
                    break;
                }
            }
            //添加环境节点
            if(StringUtils.isEmpty(environmentId)){
                Map<String,Object> map = new HashMap<String,Object>();
                map.put(MainController.NAME, environmentString);
                map.put(MainController.VALUE, UploadUtil.getStringCellValue(cellSix));
                map.put(MainController.PID, configId);
                environmentId = UploadUtil.setNode(configId, listAll, map, redisExtendsUtils);
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
                if(!StringUtils.isEmpty(modelString) && StringUtils.isEmpty(modelId)){
                    Map<String,Object> mapModel = new HashMap<String,Object>();
                    mapModel.put(MainController.NAME, modelString);
                    mapModel.put(MainController.VALUE, UploadUtil.getStringCellValue(cellSix));
                    mapModel.put(MainController.PID, environmentId);
                    modelId = UploadUtil.setNode(environmentId, listAll, mapModel, redisExtendsUtils);
                }
                String keyString = UploadUtil.getStringCellValue(cellFive);
                //添加叶子节点
                if(!StringUtils.isEmpty(keyString)){
                    redisExtendsUtils.save(MainController.TREE_ZERO + modelId + StringConst.COLON  + keyString, UploadUtil.getStringCellValue(cellSix));
                    List<String> checkList = redisExtendsUtils.getList(MainController.TREE_ZERO + modelId);
                    if(!checkList.contains(MainController.TREE_ZERO + modelId + StringConst.COLON  + UploadUtil.getStringCellValue(cellFive))){
                        redisExtendsUtils.rpush(MainController.TREE_ZERO + modelId, MainController.TREE_ZERO + modelId + StringConst.COLON  + UploadUtil.getStringCellValue(cellFive));
                    }
                }
            }
        }
    }


}
