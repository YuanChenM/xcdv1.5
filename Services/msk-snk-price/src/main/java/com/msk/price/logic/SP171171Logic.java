package com.msk.price.logic;

import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.price.bean.SP171170Bean;
import com.msk.price.bean.SP171170Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhu_kai1 on 2016/8/2.
 */
@Component("SP171171Logic")
public class SP171171Logic extends ExcelAsyncPrintFasterService {

    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object param) {
        Map<String,String> temp =(Map) param;
        List<SP171170Bean> list = new ArrayList<SP171170Bean>();
        SP171170Param sp171170Param = new SP171170Param();
        sp171170Param.setLgcsAreaCode(temp.get("lgcsAreaCode"));
        sp171170Param.setClassesCode(temp.get("classesCode"));
        sp171170Param.setMachiningCode(temp.get("machiningCode"));
        sp171170Param.setPricecyclePeriod(temp.get("pricecyclePeriod"));
        sp171170Param.setTitle(temp.get("title"));
        sp171170Param.setBreedName(temp.get("breedName"));
        sp171170Param.setPaging(false);

        Map<String, Object> paramMap = new HashMap<>();
        List<Map<String, ?>> tempList = new ArrayList<>();
        String title = "价盘详情" + sp171170Param.getTitle();
        paramMap.put("sheetName","sheet1");
        paramMap.put("headEntity", title);
        paramMap.put("list", list);
        tempList.add(paramMap);
        return  tempList;
    }
}
