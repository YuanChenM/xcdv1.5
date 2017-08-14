/**
 * @screen common
 * @author yuan_chen
 */
package com.msk.common.utils;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.bean.PriceCycle;

import java.util.Calendar;
import java.util.Date;

/**
 * PriceCycleUtils.
 */
public class PriceCycleUtils {

    /**
     * 价盘周期(5天)取得
     * ver1.0
     * 此版本仅能取得当前价盘周期
     *
     * @param currentDate Date
     * @param amount int
     * @return 价盘周期
     */
    public static PriceCycle getPriceCycle(Date currentDate, int amount) {
        PriceCycle cycle = new PriceCycle();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        //取得code的前四位(年+月)
        String cycleCode = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YY_MM, currentDate);

        //int mouthAmount = NumberConst.IntDef.INT_ZERO;
        int dayAmount = NumberConst.IntDef.INT_ZERO;

        // 取得当前日期的号
        int dayOfMouth = DateTimeUtil.getDayOfMonth(currentDate);
        dayOfMouth = dayOfMouth - 1;

        dayAmount = dayOfMouth / NumberConst.IntDef.INT_FIVE;
        int dayOfCycle = dayOfMouth % NumberConst.IntDef.INT_FIVE;

        //设置价盘周期的属性
        cycle.setDayOfCycle(dayOfCycle + NumberConst.IntDef.INT_ONE);
        if (dayAmount == NumberConst.IntDef.INT_SIX){
            cycleCode = cycleCode + String.valueOf(dayAmount);
        }else{
            dayAmount = dayAmount +NumberConst.IntDef.INT_ONE;
            cycleCode = cycleCode + String.valueOf(dayAmount);
        }
        cycle.setDayAmount(dayAmount);
        cycle.setCycleCode(cycleCode);

        // 获得当前价盘的开始日
        calendar.set(Calendar.DAY_OF_MONTH, (dayAmount-1) * NumberConst.IntDef.INT_FIVE + NumberConst.IntDef.INT_ONE);
        cycle.setStartDate(calendar.getTime());

        if (dayAmount == NumberConst.IntDef.INT_SIX) {
            // 若为当月第六个价盘，则取得月末
            cycle.setEndDate(DateTimeUtil.getMaxMonthDate(calendar.getTime()));
        } else {

            // 若不为当月第六个价盘，则通过开始日+9获得价盘结束日
            calendar.add(Calendar.DAY_OF_MONTH, NumberConst.IntDef.INT_FOUR);
            cycle.setEndDate(calendar.getTime());
        }

        return cycle;
    }

    /**
     * 价盘周期(5天)取得
     * ver1.0
     * 此版本仅能取得下一个价盘周期
     *
     * @param currentDate Date
     * @param amount int
     * @return 下一个价盘周期
     */
    public static PriceCycle getNextPriceCycle(Date currentDate, int amount) {
        PriceCycle cycle = new PriceCycle();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        //取得code的前四位(年+月)
        String cycleCode = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YY_MM, currentDate);
        String nextCycleCode = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YY_MM,
                                    DateTimeUtil.addMonth(DateTimeUtil.getCustomerDate(), 1));
        int dayAmount = NumberConst.IntDef.INT_ZERO;

        // 取得当前日期的号
        int dayOfMouth = DateTimeUtil.getDayOfMonth(currentDate);
        dayOfMouth = dayOfMouth - 1;

        //整除
        dayAmount = dayOfMouth / NumberConst.IntDef.INT_FIVE + 1;
        //取余
        int dayOfCycle = dayOfMouth % NumberConst.IntDef.INT_FIVE;

        //设置价盘周期的属性
        cycle.setDayOfCycle(dayOfCycle + NumberConst.IntDef.INT_ONE);

        if (dayAmount == NumberConst.IntDef.INT_SIX){
            dayAmount = NumberConst.IntDef.INT_ONE;
            cycleCode = nextCycleCode + String.valueOf(NumberConst.IntDef.INT_ONE);
        }else{
            dayAmount = dayAmount +NumberConst.IntDef.INT_ONE;
            cycleCode = cycleCode + String.valueOf(dayAmount);
        }
        cycle.setDayAmount(dayAmount);
        cycle.setCycleCode(cycleCode);

        if (dayAmount == NumberConst.IntDef.INT_SIX) {
            // 获得当前价盘的开始日
            calendar.setTime(DateTimeUtil.addMonth(DateTimeUtil.getCustomerDate(), 1));
            calendar.set(Calendar.DAY_OF_MONTH, (dayAmount - 1) * NumberConst.IntDef.INT_FIVE + NumberConst.IntDef.INT_ONE);
            cycle.setStartDate(calendar.getTime());
            // 若为当月第六个价盘，则取得月末
            cycle.setEndDate(DateTimeUtil.getMaxMonthDate(calendar.getTime()));
        } else {
            // 获得当前价盘的开始日
            calendar.setTime(currentDate);
            calendar.set(Calendar.DAY_OF_MONTH, (dayAmount - 1) * NumberConst.IntDef.INT_FIVE + NumberConst.IntDef.INT_ONE);
            cycle.setStartDate(calendar.getTime());
            // 若不为当月第六个价盘，则通过开始日+9获得价盘结束日
            calendar.add(Calendar.DAY_OF_MONTH, NumberConst.IntDef.INT_FOUR);
            cycle.setEndDate(calendar.getTime());
        }

        return cycle;
    }
//    /**
//     * 价盘周期(10天)取得
//     *
//     * @param currentDate Date
//     * @param amount int
//     * @return 价盘周期
//     */
//    public static PriceCycle getPriceCycle(Date currentDate, int amount) {
//        PriceCycle cycle = new PriceCycle();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(currentDate);
//
//        int mouthAmount = NumberConst.IntDef.INT_ZERO;
//        int dayAmount = NumberConst.IntDef.INT_ZERO;
//
//        // 取得当前日期的号
//        int dayOfMouth = DateTimeUtil.getDayOfMonth(currentDate);
//
//        // 如果需要取得之前的价盘周期
//        if (amount < NumberConst.IntDef.INT_ZERO) {
//
//            // 当前日期若在当月第一个价盘周期中
//            if (NumberConst.IntDef.INT_TEN >= dayOfMouth) {
//                mouthAmount = (amount - NumberConst.IntDef.INT_TWO) / NumberConst.IntDef.INT_THREE;
//                dayAmount = NumberConst.IntDef.INT_TWO - ((amount - NumberConst.IntDef.INT_TWO) % NumberConst.IntDef.INT_THREE);
//
//                // 当前日期若在当月第二个价盘周期中
//            } else if (NumberConst.IntDef.INT_TEN < dayOfMouth || NumberConst.IntDef.INT_TWENTY >= dayOfMouth) {
//                mouthAmount = (amount - NumberConst.IntDef.INT_ONE) / NumberConst.IntDef.INT_THREE;
//                dayAmount = NumberConst.IntDef.INT_TWO - ((amount - NumberConst.IntDef.INT_ONE) % NumberConst.IntDef.INT_THREE);
//
//                // 当前日期若在当月第三个价盘周期中
//            } else {
//                mouthAmount = amount / NumberConst.IntDef.INT_THREE;
//                dayAmount = NumberConst.IntDef.INT_TWO - amount % NumberConst.IntDef.INT_THREE;
//            }
//
//            // 如果需要取得之后的价盘周期
//        } else if (amount > NumberConst.IntDef.INT_ZERO) {
//
//            // 当前日期若在当月第一个价盘周期中
//            if (NumberConst.IntDef.INT_TEN >= dayOfMouth) {
//                mouthAmount = amount / NumberConst.IntDef.INT_THREE;
//                dayAmount = amount % NumberConst.IntDef.INT_THREE;
//
//                // 当前日期若在当月第二个价盘周期中
//            } else if (NumberConst.IntDef.INT_TEN < dayOfMouth || NumberConst.IntDef.INT_TWENTY >= dayOfMouth) {
//                mouthAmount = (amount + NumberConst.IntDef.INT_ONE) / NumberConst.IntDef.INT_THREE;
//                dayAmount = (amount + NumberConst.IntDef.INT_ONE) % NumberConst.IntDef.INT_THREE;
//
//                // 当前日期若在当月第三个价盘周期中
//            } else {
//                mouthAmount = (amount + NumberConst.IntDef.INT_TWO) / NumberConst.IntDef.INT_THREE;
//                dayAmount = (amount + NumberConst.IntDef.INT_TWO) % NumberConst.IntDef.INT_THREE;
//            }
//
//            // 如果需要取得当前的价盘周期
//        } else {
//            mouthAmount = NumberConst.IntDef.INT_ZERO;
//
//            // 当前日期若在当月第一个价盘周期中
//            if (NumberConst.IntDef.INT_TEN >= dayOfMouth) {
//                dayAmount = NumberConst.IntDef.INT_ZERO;
//
//                // 当前日期若在当月第二个价盘周期中
//            } else if (NumberConst.IntDef.INT_TEN < dayOfMouth || NumberConst.IntDef.INT_TWENTY >= dayOfMouth) {
//                dayAmount = NumberConst.IntDef.INT_ONE;
//
//                // 当前日期若在当月第三个价盘周期中
//            } else {
//                dayAmount = NumberConst.IntDef.INT_TWO;
//            }
//        }
//
//        // 获得第amount个价盘的月份
//        calendar.add(Calendar.MONTH, mouthAmount);
//
//        // 获得第amount个价盘的开始日
//        calendar.set(Calendar.DAY_OF_MONTH, dayAmount * NumberConst.IntDef.INT_TEN + NumberConst.IntDef.INT_ONE);
//        cycle.setStartDate(calendar.getTime());
//
//        if (dayAmount == NumberConst.IntDef.INT_TWO) {
//
//            // 若为当月第三个价盘，则取得月末
//            cycle.setEndDate(DateTimeUtil.getMaxMonthDate(calendar.getTime()));
//        } else {
//
//            // 若不为当月第三个价盘，则通过开始日+9获得价盘结束日
//            calendar.add(Calendar.DAY_OF_MONTH, NumberConst.IntDef.INT_NINE);
//            cycle.setEndDate(calendar.getTime());
//        }
//
//        return cycle;
//    }

}
