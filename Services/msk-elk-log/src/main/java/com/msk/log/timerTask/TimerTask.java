package com.msk.log.timerTask;


import com.msk.common.config.ConfigConstantDef;
import com.msk.common.excel.ExcelWrite;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.log.agent.Agent;
import com.msk.log.agent.LogMessage;
import com.msk.log.agent.LogResult;
import com.msk.log.agent.MessageParam;
import com.msk.log.common.Common;
import com.msk.log.common.RedisConfig;
import com.msk.log.common.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mao_yejun on 2016/6/7.
 */
public class TimerTask {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(TimerTask.class);
    // 时差
    private final static int TIME_DIFF = -8;
    private final static int BEFORE = -1;
    @Autowired
    private RedisConfig redisConfig;

    public void doDayTask() {
        logger.info("开始日分析任务......");
        redisConfig.setDatabase(14);
        String searchUrl = redisConfig.get(ConfigConstantDef.LOG_ANALYSIS_SERVICES);
        // 获得查询错误日志数的json日历时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        // 获得查询错误日志数的json日历时间
        Calendar calendarInfo = Calendar.getInstance();
        calendarInfo.set(Calendar.MINUTE, 0);
        calendarInfo.set(Calendar.SECOND, 0);

        List<Report> reportList = new ArrayList<Report>();
        //url的list
        List<String> urlList = getAllServices(redisConfig.getAllUrl());
        Map<String, String> queryErrorMap = getDayToFrom(calendar);
        Map<String, String> queryInfoMap = getDayToFrom(calendarInfo);
        for (String url : urlList) {
            //分析结果类
            Report report = new Report();
            report.setUrl(url);
            //查询错误级别的日志条数的参数
            String queryError = Agent.getRequestParam(queryErrorMap, Common.LOG_LEVEL.ERROR_LEVEL, url);
            //查询错误级别的日志条数的参数
            String queryInfo = Agent.getRequestParam(queryInfoMap, Common.LOG_LEVEL.INFO_LEVEL, url);
            try {
                //调用失败的次数返回结果
                LogResult errorResult = Agent.query(queryError, Agent.getUrl(Common.REQUEST_LEVEL.ACCOUNT,searchUrl));
                //查询成功条数返回结果
                LogResult infoResult = Agent.query(queryInfo, Agent.getUrl(Common.REQUEST_LEVEL.ACCOUNT,searchUrl));
                // 错误记录数
                report.setErrorAccount(errorResult.getHits().getTotal());
                //成功结果数
                report.setSuccessAccount(infoResult.getHits().getTotal());
                //查询1000条成功日志，计算平均请求时间
                LogResult detailResult = Agent.query(queryInfo, Agent.getUrl(Common.REQUEST_LEVEL.DETAIL,searchUrl));
                // 返回结果
                List<LogMessage> list = detailResult.getHits().getHits();
                MessageParam messageParam = new MessageParam();
                String message = null;
                //总花费时间字段
                int totalTime = 0;
                for (LogMessage logMessage : list) {
                    //获得日志打印内容
                    message = logMessage.get_source().getMessage().toString();
                    //info级别日志解析
                    messageParam = Agent.formatMessage(message, Common.LOG_LEVEL.INFO_LEVEL);
                    totalTime = Integer.valueOf(messageParam.getRequestTime()) + totalTime;

                }
                //当查询到日志条数大于0时，计算平均请求时间
                if (list.size() > 0) {
                    float avgTime = (float) totalTime / list.size();
                    report.setAvgTime(String.valueOf(avgTime));
                } else {
                    report.setAvgTime(String.valueOf(totalTime));
                }

                reportList.add(report);

            } catch (IOException e) {
                logger.info(e.getMessage());

            }
        }
        //把reportList写进excel
        try {
            InputStream in = TimerTask.class.getResourceAsStream("/temp/reportTemp.xlsx");

            File output = new File(makeBeforeDayDir() + getBeforeDayFileName() + ".xlsx");
            ExcelWrite excelWrite = new JxlsExcelWrite(in, new BufferedOutputStream(new FileOutputStream(output)));
            Map<String, Object> param = new HashMap<>();
            param.put("detail", reportList);
            excelWrite.excelWrite(param);
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage());
        }
    }


    public void doPeriodTask() {
        logger.info("开始阶段性错误日志查询....");
        redisConfig.setDatabase(14);
        String searchUrl = redisConfig.get(ConfigConstantDef.LOG_ANALYSIS_SERVICES);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        // 查询error级别的日志
        String query = Agent.getRequestParam(getPeriodToFrom(calendar),
                Common.LOG_LEVEL.ERROR_LEVEL, null);

        try {
            LogResult logResult = Agent.query(query,
                    Agent.getUrl(Common.REQUEST_LEVEL.DETAIL,searchUrl));

            // 所有日志文本，每次接口调用（每条记录包括接口调用参数url，以及返回情况）
            List<LogMessage> list = logResult.getHits().getHits();
            List<MessageParam> failMessageParams = new ArrayList<MessageParam>();

            String message = null;
            for (LogMessage logMessage : list) {
                MessageParam messageParam = new MessageParam();
                message = logMessage.get_source().getMessage().toString();
                messageParam = Agent.formatMessage(message, Common.LOG_LEVEL.ERROR_LEVEL);
                failMessageParams.add(messageParam);

            }
            if (failMessageParams != null && failMessageParams.size() > 0) {
                //写进excel
                InputStream in = TimerTask.class.getResourceAsStream("/temp/reportTemp1.xlsx");
                File output = new File(makeThisDayDir() + getHourFileName() + ".xlsx");
                ExcelWrite excelWrite = new JxlsExcelWrite(in, new BufferedOutputStream(new FileOutputStream(output)));
                Map<String, Object> param = new HashMap<>();
                param.put("detail", failMessageParams);
                excelWrite.excelWrite(param);
            }
        } catch (Exception e) {

            logger.info(e.getMessage());
        }

    }

    public static String getBeforeDayFileName() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        return dateFormat.format(calendar.getTime());
    }

    public static String getHourFileName() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH-mm-ss");
        return dateFormat.format(calendar.getTime());
    }

    public static String getThisDayFileName() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        return dateFormat.format(calendar.getTime());
    }

    public static String makeBeforeDayDir() {
        File file = new File("/xcdhome/logs/msk-elk-log/" + getBeforeDayFileName());
        if (!file.exists() && !file.isDirectory()) {
            logger.info("创建文件夹目录" + getBeforeDayFileName());
            file.mkdir();
        }
        return "/xcdhome/logs/msk-elk-log/" + getBeforeDayFileName() + "/";
    }


    public static String makeThisDayDir() {
        File file = new File("/xcdhome/logs/msk-elk-log/" + getThisDayFileName());
        if (!file.exists() && !file.isDirectory()) {
            logger.info("创建文件夹目录" + getThisDayFileName());
            file.mkdir();
        }
        return "/xcdhome/logs/msk-elk-log/" + getThisDayFileName() + "/";
    }

    public static List<String> getAllServices(Map<String, String> allUrl) {
        List<String> allServices = new ArrayList<String>();
        for (String key : allUrl.keySet()) {
            String value = allUrl.get(key);
            if (value.startsWith("/")) {
                allServices.add(value);
            }

        }
        return allServices;
    }

    public static Map<String, String> getDayToFrom(Calendar cal) {
        Map<String, String> map = new HashMap<String, String>();
        //elasticsearch会提前8个小时，先减去该误差
        cal.add(Calendar.HOUR_OF_DAY, TIME_DIFF);
        //转化为elasticsearch的时间类型
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'");
        String to = dateFormat.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, BEFORE);
        String from = dateFormat.format(cal.getTime());
        map.put("to", to);
        map.put("from", from);
        return map;
    }

    public static Map<String, String> getPeriodToFrom(Calendar cal) {
        Map<String, String> map = new HashMap<String, String>();
        //elasticsearch会提前8个小时，先减去该误差
        cal.add(Calendar.HOUR_OF_DAY, TIME_DIFF);
        //转化为elasticsearch的时间类型
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'");
        String to = dateFormat.format(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, BEFORE);
        String from = dateFormat.format(cal.getTime());
        map.put("to", to);
        map.put("from", from);
        return map;
    }
}
