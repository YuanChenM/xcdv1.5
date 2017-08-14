package com.msk.price.excel.validator;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.price.bean.SP171170Bean;
import com.msk.price.bean.SP171170ExcelBean;
import com.msk.price.excel.read.SP171170ReadExcelService;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by zhu_kai1 on 2016/9/22.
 */
public class SP171170ExcelValidator{

    // 第6行开始遍历获取数据
    private static final int ROW_START_NUM = 6;

    /**
     * 校验excel数据
     */
    public  List<SP171170Bean> excelDataValidator(SP171170ReadExcelService readExcelService,List<SP171170ExcelBean> list,Map<String, SP171170Bean> excelMap){

        // 校验模板格式不对
        if(!readExcelService.getFormatErrorMessage().isEmpty()){
            StringBuffer sb = new StringBuffer();
            for (String key : readExcelService.getFormatErrorMessage()){
                sb.append(key);
            }
            throw new BusinessException(sb.toString());
        }
        // 校验是否有end截止符
        if(!readExcelService.getEndErrorMessage().isEmpty()){
            StringBuffer sb = new StringBuffer();
            for (String key : readExcelService.getEndErrorMessage()){
                sb.append(key);
            }
            throw new BusinessException(sb.toString());
        }

        // 校验excel同一区域下有多条相同产品编码的产品。
        Map<String,SP171170ExcelBean> checkRepeatPdMap = new HashMap<>();
        // 构造map<sheetName,sheet对应的数据>
        Map<String,List<SP171170ExcelBean>> tempMap = new HashMap<>();
        List<SP171170Bean>  outPriceList= new ArrayList<>();
        for (SP171170ExcelBean sp171170ExcelBean :list){
            String key  = sp171170ExcelBean.getLgcsAreaCode()+"_"+sp171170ExcelBean.getPdCode();
            if(checkRepeatPdMap.containsKey(key)){
                readExcelService.getDataErrorMessage().add("工作表【"+sp171170ExcelBean.getSheetName()+"】中产品编码（"+sp171170ExcelBean.getPdCode()+"）产品数据有重复，请核对！</br>");
            }else{
                checkRepeatPdMap.put(key, sp171170ExcelBean);
            }
            // tempMap中的每一条数据，就是对应的每个sheet的所有的产品信息
            if(tempMap.containsKey(sp171170ExcelBean.getSheetName())){
                tempMap.get(sp171170ExcelBean.getSheetName()).add(sp171170ExcelBean);
            }else {
                List<SP171170ExcelBean> temList  = new ArrayList<>();
                temList.add(sp171170ExcelBean);
                tempMap.put(sp171170ExcelBean.getSheetName(),temList);
            }
        }

        for (String sheetName:tempMap.keySet()){
            List<SP171170ExcelBean> inputData = tempMap.get(sheetName);
            // 遍历excel的数据
            for (int i =0 ; i < inputData.size(); i++) {
                SP171170ExcelBean  inputExcelRowData = inputData.get(i);
                // 从第6行开始校验数据的正确性
                int errorRowNum = i + ROW_START_NUM;

                if(excelMap.containsKey(inputExcelRowData.getPdCode() + inputExcelRowData.getLgcsAreaName())){
                    SP171170Bean  productSearchInfo = excelMap.get(inputExcelRowData.getPdCode() + inputExcelRowData.getLgcsAreaName());
                    if(StringUtil.isNullOrEmpty(inputExcelRowData.getClassesName()) || !inputExcelRowData.getClassesName().equals(productSearchInfo.getClassesName())){
                        readExcelService.getDataErrorMessage().add("工作表【"+sheetName+"】的第"+errorRowNum+"行(产品一级分类)数据有问题，请核对！</br>");
                    }
                    if(StringUtil.isNullOrEmpty(inputExcelRowData.getMachiningName()) || !inputExcelRowData.getMachiningName().equals(productSearchInfo.getMachiningName())){
                        readExcelService.getDataErrorMessage().add("工作表【"+sheetName+"】的第"+errorRowNum+"行(产品二级分类)数据有问题，请核对！</br>");
                    }
                    if(StringUtil.isNullOrEmpty(inputExcelRowData.getBreedName()) || !inputExcelRowData.getBreedName().equals(productSearchInfo.getBreedName())){
                        readExcelService.getDataErrorMessage().add("工作表【"+sheetName+"】的第"+errorRowNum+"行(品种)数据有问题，请核对！</br>");
                    }
                    if(StringUtil.isNullOrEmpty(inputExcelRowData.getFeatureName()) || !inputExcelRowData.getFeatureName().equals(productSearchInfo.getFeatureName())){
                        readExcelService.getDataErrorMessage().add( "工作表【"+sheetName+"】的第"+errorRowNum+"行(特征)数据有问题，请核对！</br>");
                    }
                    if(StringUtil.isNullOrEmpty(inputExcelRowData.getWeightName()) || !inputExcelRowData.getWeightName().equals(productSearchInfo.getWeightName())){
                        readExcelService.getDataErrorMessage().add("工作表【"+sheetName+"】的第"+errorRowNum+"行(净重)数据有问题，请核对！</br>");
                    }
                    if(StringUtil.isNullOrEmpty(inputExcelRowData.getGradeName()) || !inputExcelRowData.getGradeName().equals(productSearchInfo.getGradeName())){
                        readExcelService.getDataErrorMessage().add( "工作表【"+sheetName+"】的第"+errorRowNum+"行(等级)数据有问题，请核对！</br>");
                    }
                    if(!CollectionUtils.isEmpty(readExcelService.getDataErrorMessage())){
                        continue;
                    }
                    // 获取最后需要新增或者更新的数据
                    getSaveOrUpdateData(outPriceList, inputExcelRowData);
                }else{
                    readExcelService.getDataErrorMessage().add("工作表【"+sheetName+"】的第"+errorRowNum+"行数据（物流区和产品编码）有问题，该物流区没有对应的产品，请核对！</br>");
                }
            }
        }
        // 校验数据的正确性
        if(!readExcelService.getDataErrorMessage().isEmpty()){
            StringBuffer stringBuffer = new StringBuffer();
            if(!readExcelService.getDataErrorMessage().isEmpty()){
                for (String key : readExcelService.getDataErrorMessage()){
                    stringBuffer.append(key);
                }
            }
            throw new BusinessException(stringBuffer.toString());
        }
        return outPriceList;
    }

    /**
     * 构造最后需要新增或者更新的数据
     * @param outPriceList
     * @param inputExcelRowData
     */
    private  void getSaveOrUpdateData(List<SP171170Bean> outPriceList, SP171170ExcelBean inputExcelRowData) {
        //Check数据没有错误的场合，把数据形式组装成价盘表的样式
        for(SP171170Bean sp171170Bean : inputExcelRowData.getSp171170BeanList()){
            sp171170Bean.setPdCode(inputExcelRowData.getPdCode());
            sp171170Bean.setPricecyclePeriod(inputExcelRowData.getPricecyclePeriod());
            sp171170Bean.setLogiareaCode(inputExcelRowData.getLgcsAreaCode());
            sp171170Bean.setLogiareaName(inputExcelRowData.getLgcsAreaName());
            outPriceList.add(sp171170Bean);
        }
    }

}
