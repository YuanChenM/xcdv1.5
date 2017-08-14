package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.*;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.bean.RsResponse;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.common.service.ExcelAsyncPrintService;
import com.msk.core.entity.SlHouseAccount;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhu_kai1 on 2016/8/3.
 */
@Service("BSExcelCommLogic")
public class BSExcelCommLogic extends ExcelAsyncPrintFasterService {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object param) {
        List<Map<String, ?>> listParam = new ArrayList<>();
        Map<String,String> temp =(Map) param;
        BSExcelCommParam excelCommParam = new BSExcelCommParam();
        excelCommParam.setLgcsAreaCode(temp.get("lgcsAreaCode"));
        excelCommParam.setCityCode(temp.get("cityCode"));
        excelCommParam.setCategoryCode(temp.get("categoryCode"));
        excelCommParam.setReclassifyCode(temp.get("reclassifyCode"));
        excelCommParam.setCreationEndTime(temp.get("creationEndTime"));
        excelCommParam.setBuyerType(temp.get("buyerType"));
        excelCommParam.setIsHk(temp.get("isHk"));
        if(StringUtils.hasLength(temp.get("isHk"))) {
            BR121305Param brParam = new BR121305Param();
            brParam.setLgcsAreaCode(temp.get("lgcsAreaCode"));
            brParam.setCityCode(temp.get("cityCode"));
            brParam.setClassesCode(temp.get("classesCode"));
            brParam.setMachiningCode(temp.get("machiningCode"));
            brParam.setBuyerType(temp.get("buyerType"));
            brParam.setCreationStartTime(temp.get("creationStartTime"));
            brParam.setCreationEndTime(temp.get("creationEndTime"));
            List<IBS121306RsBean> houseList = CommRestUtil.getHkGroupForHkInfo(brParam);
            List<SlHouseAccount> prams = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(houseList)) {
                for (IBS121306RsBean bean : houseList) {
                    SlHouseAccount bsParam = new SlHouseAccount();
                    bsParam.setSlCode(bean.getSlCode());
                    bsParam.setHouseCode(bean.getHouseCode());
                    prams.add(bsParam);
                }
                excelCommParam.setSlHouseAccountList(prams);
            }else{
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("headEntity", temp.get("title"));
                paramMap.put("sheetName","sheet1");
                //Modif for Bug#2530 at 2016/09/07 by zhu_kai1 Start
                paramMap.put("list", new ArrayList<BSExcelCommBean>());
                //Modif for Bug#2530 at 2016/09/07 by zhu_kai1 end
                listParam.add(paramMap);
                return  listParam;
            }
        }
        List<BSExcelCommBean> excelCommBeanList =  this.findPageList(excelCommParam, BSExcelCommBean.class);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("headEntity", temp.get("title"));
        paramMap.put("sheetName","sheet1");
        paramMap.put("list",  excelCommBeanList);
        listParam.add(paramMap);
        return listParam;
    }
}
