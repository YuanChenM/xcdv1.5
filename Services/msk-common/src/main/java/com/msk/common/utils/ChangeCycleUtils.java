package com.msk.common.utils;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.bean.ChangeCycle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhang_jian4 on 2016/10/10.
 */
public class ChangeCycleUtils {

    /**
     * 获取指定自然半旬
     * @param currentDate 下单日
     * @param amount (第几个自然半旬，下订单日为第0个自然半旬)
     * @return ChangeCycle
     *
     * 连续半旬不能超过6个，即amount < 6
     *
     */
    public static ChangeCycle getChangeCycle(Date currentDate,int amount){
        ChangeCycle cycle = new ChangeCycle();

        //获取当前日期的年月（2016-10）
        String cycleCode = DateTimeUtil.formatDate("yyyy-MM", currentDate);
        //获取下个月的年月（2016-11）
        String nextCycleCode = DateTimeUtil.formatDate("yyyy-MM",
                DateTimeUtil.addMonth(currentDate, 1)); // 1611
        //获取当前日期的日号
        int dayOfMouth = DateTimeUtil.getDayOfMonth(currentDate);

        int dayCount = (dayOfMouth - 1) / NumberConst.IntDef.INT_FIVE + amount;
        int dayCountUpdate = dayCount > 5 ? (dayCount -6) : dayCount ;
        int s = dayCountUpdate * NumberConst.IntDef.INT_FIVE + 1;
        //整除
        String dayStart = String.format("%02d", s);
        String dayEnd = "01";
        if(s==26){
            // 第6个半旬，获取月末日期
            SimpleDateFormat format = new SimpleDateFormat("dd");
            Calendar ca = Calendar.getInstance();
            if(dayCount > 5){
                ca.setTime(DateTimeUtil.addMonth(currentDate, 1));
            }else{
                ca.setTime(currentDate);
            }
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            dayEnd = format.format(ca.getTime());
        }else{
            dayEnd = String.format("%02d", s+4);
        }
        if(dayCount > 5){
            cycle.setStartDateStr(nextCycleCode + "-" + dayStart);
            cycle.setEndDateStr(nextCycleCode + "-" + dayEnd);
        }else{
            cycle.setStartDateStr(cycleCode + "-" + dayStart);
            cycle.setEndDateStr(cycleCode + "-" + dayEnd);
        }
        cycle.setStartDate(DateTimeUtil.parseDate(cycle.getStartDateStr(),"yyyy-MM-dd"));
        cycle.setEndDate(DateTimeUtil.parseDate(cycle.getEndDateStr(), "yyyy-MM-dd"));
        return cycle;
    }

    /**
     * 根据连续半旬区间获取开始和结束时间
     * @param currentDate 下单日
     * @param startNum (从第几个自然半旬开始)
     * @param endNum (在第几个自然半旬结束)
     * @return
     */
    public static ChangeCycle getChangeCycleByRange(Date currentDate,int startNum, int endNum){
        ChangeCycle cycle = getChangeCycle(currentDate,startNum);
        ChangeCycle cycle2 = getChangeCycle(currentDate,endNum);
        cycle.setEndDateStr(cycle2.getEndDateStr());
        cycle.setEndDate(cycle2.getEndDate());
        return cycle;
    }

}
