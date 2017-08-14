package com.msk.batch;

import com.hoperun.plug.spring.datasource.DynamicDataSource;
import com.msk.common.logic.ConfigLogic;
import com.msk.core.entity.CommConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by jackjiang on 16/7/5.
 */
public class StartOnlineBatch extends StartBatch{
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BaseBatch.class);
    private static boolean LOAD_DATA_SOURCE_END = Boolean.FALSE;


    /**
     * 启动操作
     *
     * @param args args
     */
    public static void main(String[] args) {
        ApplicationContext context = getOnlineApplicationContext();
        DynamicDataSource dynamicDataSource = context.getBean("dynamicDataSource",DynamicDataSource.class);
        Map<Object, Object> dataSourceMap = initDataSource(context);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.afterPropertiesSet();
        LOAD_DATA_SOURCE_END = Boolean.TRUE;
    }
    public static boolean isLoadDataSourceEnd(){
        return LOAD_DATA_SOURCE_END;
    }
    /**
     * 获得Online Application Context
     * @return Application Context
     */
    public static ApplicationContext getOnlineApplicationContext() {
        ApplicationContext context = new FileSystemXmlApplicationContext(
                new String[] { "classpath:/spring/application-context.xml", "classpath:/spring/mybatis-context.xml","classpath:/spring/application-qbScheduler.xml" });
        return context;
    }
}
