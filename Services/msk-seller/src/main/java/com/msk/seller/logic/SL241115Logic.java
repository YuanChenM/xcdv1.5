package com.msk.seller.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.common.service.ExcelAsyncPrintService;
import com.msk.seller.bean.SL241101Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guan_zhongheng on 2016/9/12.
 */
@Component("sl241115Logic")
public class SL241115Logic extends ExcelAsyncPrintFasterService {

    @Autowired
    private Sl241101Logic sl241101Logic;

    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object param) {
        List<Map<String, ?>> listParam = new ArrayList<>();
        BasePageParam basePageParam = new BasePageParam();
        basePageParam.setPaging(false);
        // 设置FilterMap
        basePageParam.setFilterMap((Map<String, Object>) param);
        DbUtils.buildLikeCondition(basePageParam, "slCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slCodeDis", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "slShowName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "slTel", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "slContact", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "cityName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "lgcsAreaName", DbUtils.LikeMode.PARTIAL);
        String slMainClass= StringUtil.toSafeString(basePageParam.getFilterMap().get("slMainClass"));
        if(!StringUtil.isNullOrEmpty(slMainClass)){
            String[] slMainClasss=slMainClass.split(",");
            basePageParam.getFilterMap().put("slMainClasss",slMainClasss);
        }
        String ddjsh = (String) basePageParam.getFilterMap().get("ddjsh");
        if(!StringUtil.isNullOrEmpty(ddjsh)){
            String[] ddjshAry = ddjsh.split(",");
            if (ddjshAry.length == NumberConst.IntDef.INT_TWO) {
                basePageParam.setFilter("ddjsh", null);
            }
        }
        String ddjkr = (String) basePageParam.getFilterMap().get("ddjkr");
        if(!StringUtil.isNullOrEmpty(ddjkr)){
            String[] ddjkrAry = ddjkr.split(",");
            if (ddjkrAry.length == NumberConst.IntDef.INT_TWO) {
                basePageParam.setFilter("ddjkr", null);
            }
        }
        basePageParam.setFilter("delFlg", "0");
        List<SL241101Bean> list = sl241101Logic.findPageInfo(basePageParam);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("list", list);
        paramMap.put("sheetName","template");
        listParam.add(paramMap);
        return listParam;
    }
}
