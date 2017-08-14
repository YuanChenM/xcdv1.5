package com.msk.price.logic;

import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.price.bean.SP171170Bean;
import com.msk.price.bean.SP171170Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhu_kai1 on 2016/9/20.
 */
@Component("SPExcelSheetLogic")
public class SPExcelSheetLogic extends ExcelAsyncPrintFasterService {

    private static Logger logger = LoggerFactory.getLogger(SPExcelSheetLogic.class);
    @Autowired
    private SP171170Logic sp171170Logic;

    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object param) {
        Map<String, String> temp = (Map) param;
        SP171170Param sp171170Param = new SP171170Param();
        sp171170Param.setLgcsAreaCode(temp.get("lgcsAreaCode"));
        sp171170Param.setClassesCode(temp.get("classesCode"));
        sp171170Param.setMachiningCode(temp.get("machiningCode"));
        sp171170Param.setPricecyclePeriod(temp.get("pricecyclePeriod"));
        sp171170Param.setTitle(temp.get("title"));
        sp171170Param.setBreedName(temp.get("breedName"));
        if (StringUtils.hasLength(sp171170Param.getBreedName())) {
            sp171170Param.setBreedName(DbUtils.buildLikeCondition(sp171170Param.getBreedName(), DbUtils.LikeMode.PARTIAL));
        }
        sp171170Param.setPaging(false);
        long startTime = System.currentTimeMillis();
        List<SP171170Bean> list = sp171170Logic.findPageList(sp171170Param, SP171170Bean.class);
        long endTime = System.currentTimeMillis();
        logger.info("查询sql数据花费时间:" + (endTime - startTime));
        // 构造不同通道数据
        Map<String, List<SP171170Bean>> resultMap = new HashMap<>();
        for (SP171170Bean bean : list) {
            String key = bean.getLogiareaCode() + "_" + bean.getWayCode() + "_" + bean.getWayGradeName() + "(" + bean.getLogiareaName() + ")";
            if (resultMap.containsKey(key)) {
                resultMap.get(key).add(bean);
            } else {
                List<SP171170Bean> tempList = new ArrayList<>();
                tempList.add(bean);
                resultMap.put(key, tempList);
            }
        }
        // 返回构造好的数据
        List<Map<String, ?>> listParam = new ArrayList<>();
        for (String key : resultMap.keySet()) {
            Map<String, Object> tempMap = new HashMap<>();
            String sheetName = key.split("_")[2];
            List<SP171170Bean> excelList = resultMap.get(key);
            tempMap.put("sheetName", sheetName);
            tempMap.put("supOrder", excelList.get(0).getSupOrder()); // 大宗超级通道
            tempMap.put("order1", excelList.get(0).getOrder1());// 大宗1级通道
            tempMap.put("order2", excelList.get(0).getOrder2());// 大宗2级通道
            tempMap.put("order3", excelList.get(0).getOrder3());// 大宗3级通道
            tempMap.put("order4", excelList.get(0).getOrder4());// 大额4级通道
            tempMap.put("order5", excelList.get(0).getOrder5());// 大额5级通道
            tempMap.put("order6", excelList.get(0).getOrder6());// 大额6级通道
            tempMap.put("order7", excelList.get(0).getOrder7());// 大额7级通道
            tempMap.put("order8", excelList.get(0).getOrder8());// 大额8级通道
            tempMap.put("order9", excelList.get(0).getOrder9());// 大额9级通道
            tempMap.put("list", excelList);
            tempMap.put("headEntity", "价盘详情" + temp.get("title"));
            listParam.add(tempMap);
        }
        // 没有数据时，需要构造空的值，不然excel不认，导致共通报错
        if (CollectionUtils.isEmpty(resultMap)) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("list", new ArrayList<SP171170Bean>());
            tempMap.put("sheetName", "sheet1");
            listParam.add(tempMap);
        }
        return listParam;
    }
}
