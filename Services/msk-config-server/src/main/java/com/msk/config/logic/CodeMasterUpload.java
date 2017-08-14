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
public class CodeMasterUpload {
    /**
     *  上传数据
     *  @param workbook excel
     *  @param listAll
     *  @param redisExtendsUtils
     */
    public static void uploadProperties(Workbook workbook, List<TreeBean> listAll, RedisExtendsUtils redisExtendsUtils){
        Sheet sheetCodeMaster = workbook.getSheet("codeMaster");
        String codeMasterId = UploadUtil.setRootNode(sheetCodeMaster, listAll, redisExtendsUtils);
        int rowsCodeMaster = sheetCodeMaster.getLastRowNum();
        //默认从第三行第三列开始
        for(int index = 2; index <= rowsCodeMaster; index++){
            Row row = sheetCodeMaster.getRow(index);
            Cell cellThree = row.getCell(NumberConst.IntDef.INT_TWO);
            Cell cellFour = row.getCell(NumberConst.IntDef.INT_THREE);
            Cell cellFive = row.getCell(NumberConst.IntDef.INT_FOUR);
            Cell cellSix = row.getCell(NumberConst.IntDef.INT_FIVE);
            if(StringUtils.isEmpty(UploadUtil.getStringCellValue(cellThree))
                    ||StringUtils.isEmpty(UploadUtil.getStringCellValue(cellFour))
                    ||StringUtils.isEmpty(UploadUtil.getStringCellValue(cellFive))
                    ||StringUtils.isEmpty(UploadUtil.getStringCellValue(cellSix))){
                continue;
            }else{
                String modelId = StringConst.EMPTY;
                String modelString = UploadUtil.getStringCellValue(cellThree);
                for(TreeBean bean : listAll){
                    if(codeMasterId.equals(bean.getpId()) && modelString.equals(bean.getName())){
                        modelId = bean.getId();
                        break;
                    }
                }
                if(StringUtils.isEmpty(modelId)){
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put(MainController.NAME, modelString);
                    map.put(MainController.VALUE, StringConst.EMPTY);
                    map.put(MainController.PID, codeMasterId);
                    modelId = UploadUtil.setNode(codeMasterId, listAll, map, redisExtendsUtils);
                }
                redisExtendsUtils.saveRedisMapOne(MainController.TREE_ZERO + modelId + StringConst.COLON  + UploadUtil.getStringCellValue(cellFour), UploadUtil.getStringCellValue(cellFive), UploadUtil.getStringCellValue(cellSix));
                List<String> checkList = redisExtendsUtils.getList(MainController.TREE_ZERO + modelId);
                if(!checkList.contains(MainController.TREE_ZERO + modelId + StringConst.COLON  + UploadUtil.getStringCellValue(cellFour))){
                    redisExtendsUtils.rpush(MainController.TREE_ZERO + modelId, MainController.TREE_ZERO + modelId + StringConst.COLON  + UploadUtil.getStringCellValue(cellFour));
                }

            }
        }
    }
}
