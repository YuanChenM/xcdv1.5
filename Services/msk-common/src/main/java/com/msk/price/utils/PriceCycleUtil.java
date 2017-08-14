package com.msk.price.utils;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.price.bean.PriceCycleParam;
import com.msk.price.bean.PriceCycleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class PriceCycleUtil {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(PriceCycleUtil.class);

    /**
     * 当前价盘周期(5天)取得
     * 此版本仅能取得当前价盘周期
     *
     * @return 价盘周期
     */
    public static PriceCycleResult getPriceCycle(PriceCycleParam param) {
        PriceCycleResult cycle = new PriceCycleResult();
        Date currentDate = param.getCurrentDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        //取得code的前四位(年+月)
        String cycleCode = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YY_MM, currentDate);

        //int mouthAmount = IntDef.INT_ZERO;
        int dayAmount = NumberConst.IntDef.INT_ZERO;

        // 取得当前日期的号
        int dayOfMouth = DateTimeUtil.getDayOfMonth(currentDate);
        dayOfMouth = dayOfMouth - 1;

        dayAmount = dayOfMouth / NumberConst.IntDef.INT_FIVE;
        int dayOfCycle = dayOfMouth % NumberConst.IntDef.INT_FIVE;

        //设置价盘周期的属性
        cycle.setDayOfCycle(dayOfCycle + NumberConst.IntDef.INT_ONE);
        if (dayAmount == NumberConst.IntDef.INT_SIX) {
            cycleCode = cycleCode + String.valueOf(dayAmount);
        } else {
            dayAmount = dayAmount + NumberConst.IntDef.INT_ONE;
            cycleCode = cycleCode + String.valueOf(dayAmount);
        }
        cycle.setDayAmount(dayAmount);
        cycle.setCycleCode(cycleCode);

        // 获得当前价盘的开始日
        calendar.set(Calendar.DAY_OF_MONTH, (dayAmount - 1) * NumberConst.IntDef.INT_FIVE + NumberConst.IntDef.INT_ONE);
        cycle.setStartDate(calendar.getTime());

        if (dayAmount == NumberConst.IntDef.INT_SIX) {
            // 若为当月第六个价盘，则取得月末
            cycle.setEndDate(DateTimeUtil.getMaxMonthDate(calendar.getTime()));
        } else {

            // 若不为当月第六个价盘，则通过开始日+4获得价盘结束日
            calendar.add(Calendar.DAY_OF_MONTH, NumberConst.IntDef.INT_FOUR);
            cycle.setEndDate(calendar.getTime());
        }

        return cycle;
    }

    /**
     * 前一个价盘周期(5天)取得
     * 此版本仅能取得上一个价盘周期
     *
     * @return 上一个价盘周期
     */
    public static PriceCycleResult getPrePriceCycle(PriceCycleParam param) {
        PriceCycleResult nowCycle = PriceCycleUtil.getPriceCycle(param);
        return getCycle(nowCycle.getStartDate(), NumberConst.IntDef.INT_N_ONE);
    }

    /**
     * 后一个价盘周期(5天)取得
     * ver1.0
     * 此版本仅能取得下一个价盘周期
     *
     * @return 下一个价盘周期
     */
    public static PriceCycleResult getNextPriceCycle(PriceCycleParam param) {
        PriceCycleResult nowCycle = PriceCycleUtil.getPriceCycle(param);
        return getCycle(nowCycle.getEndDate(), NumberConst.IntDef.INT_ONE);
    }

    private static PriceCycleResult getCycle(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);

        PriceCycleParam preParam = new PriceCycleParam();
        preParam.setCurrentDate(calendar.getTime());
        PriceCycleResult cycle = PriceCycleUtil.getPriceCycle(preParam);
        return cycle;

    }

    /**
     * 根据价盘周期code取得价盘周期
     *
     * @return 价盘周期
     */
    public static PriceCycleResult getPriceCycleByCode(PriceCycleParam param) {
        if (param.getPriceCode().length() != NumberConst.IntDef.INT_FIVE) {
            throw new BusinessException("价盘周期code必须为5位编码！");
        }
        int year = StringUtil.toInteger("20" + param.getPriceCode().substring(0, 2));
        int mouth = StringUtil.toInteger(param.getPriceCode().substring(2, 4));
        int day = StringUtil.toInteger(param.getPriceCode().substring(4, 5));
        mouth = mouth - NumberConst.IntDef.INT_ONE;
        day = (day - NumberConst.IntDef.INT_ONE) * NumberConst.IntDef.INT_FIVE + NumberConst.IntDef.INT_ONE;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, mouth, day);
        param.setCurrentDate(calendar.getTime());
        return PriceCycleUtil.getPriceCycle(param);
    }
}
