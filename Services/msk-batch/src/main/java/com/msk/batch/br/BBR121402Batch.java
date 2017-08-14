package com.msk.batch.br;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.BaseBatch;
import com.msk.batch.br.bean.BBR12140201Param;
import com.msk.batch.br.logic.BBR121402Logic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by jiang_tengfei on 2016/6/14.
 */
@Component("BBR121402")
public class BBR121402Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121402Batch.class);

    @Autowired
    private BBR121402Logic bbr121402Logic;

    /**
     * 创建Param
     *
     * @param args String[]
     * @return baseParam baseParam
     */
    @Override
    public BaseParam createParam(String[] args) {
        BaseParam param = new BaseParam();
        //设置共通参数
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setCrtId("Batch");
        param.setCrtTime(currentDate);
        param.setActId("Batch");
        param.setActTime(currentDate);
        param.setUpdId("Batch");
        param.setUpdTime(currentDate);
        return param;
    }

    /**
     * 业务处理
     *
     * @param baseParam baseParam
     */
    @Override
    public void doOperate(BaseParam baseParam) {
        Date date = DateTimeUtil.getCustomerDate();
        //获取上个月最后一天
        //这个月第一天
        String firstDayStr = DateTimeUtil.formatDate("yyyy-MM-01 23:59:59",date);
        //减1天
        Date lastDayDate = DateTimeUtil.addDay(DateTimeUtil.parseDate(firstDayStr,"yyyy-MM-dd HH:mm:ss"),-1);
        //获取上月第一天
        //获取上个月
        Date lastMonthDate = DateTimeUtil.addMonth(date,-1);
        String startDate = DateTimeUtil.formatDate("yyyy-MM-01 00:00:00", lastMonthDate);
        Date firstDayDate = DateTimeUtil.parseDate(startDate, "yyyy-MM-dd HH:mm:ss");
        this.bbr121402Logic.deleteFileInfo(firstDayDate,lastDayDate);
        //1为查询所有分销物流区 2为查询所有分销地区 3查询所有区县菜场 4物流区菜场 4地区菜场
        for (int i = 1; i < 6; i++) {
            List<BBR12140201Param> bbr121402Param = this.bbr121402Logic.getParamsByFlag(i);
            if (!CollectionUtils.isEmpty(bbr121402Param)){
                for (BBR12140201Param param : bbr121402Param){
                    param.setLastMonthStartDay(firstDayDate);
                    param.setLastMonthEndDay(lastDayDate);
                    param.setYear(String.valueOf(DateTimeUtil.getYear(lastDayDate)));
                    param.setMonth(String.valueOf(DateTimeUtil.getMonthOfYear(lastMonthDate)));
                    this.bbr121402Logic.dataResolve(param,baseParam);
                }
            }
        }
    }
}
