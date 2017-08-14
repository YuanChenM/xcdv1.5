package com.msk.batch;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.batch.BaseBatch;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.batch.BaseStartBatch;
import com.hoperun.plug.spring.datasource.DynamicDataSource;
import com.msk.comm.dynamic.DynamicBeanReader;
import com.msk.comm.dynamic.impl.DataSourceDynamicBean;
import com.msk.comm.dynamic.impl.DynamicBeanReaderImpl;
import com.msk.comm.interceptor.ConfigPropertiesLoad;
import com.msk.common.bean.ConfigParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.utils.RestClientUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * StartBatch
 * @author jiang_nan
 * @version 1.0
 **/
public class StartBatch extends BaseStartBatch{
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(StartBatch.class);

    public static Map<Object, Object> initDataSource(ApplicationContext context){
        Map<Object, Object> dataSourceMap = new HashMap<>();
        Map<String,String> DATA_SOURCE_NAME_MAP = ConfigPropertiesLoad.getDataSourceMap();

        for(Map.Entry<String,String> entry : DATA_SOURCE_NAME_MAP.entrySet()){
            String modelName = entry.getValue();
            if(StringUtil.isEmpty(modelName)){
                continue;
            }
            String environment = ConfigPropertiesLoad.getEnvironment();
            String configUrl = ConfigPropertiesLoad.getConfigLoadPropertiesUrl();
            RsRequest<ConfigParam> requestParam = new RsRequest<>();
            requestParam.setAuth(modelName);
            requestParam.setLoginId(modelName);
            requestParam.setSiteCode("1");
            ConfigParam param = new ConfigParam();
            param.setModelName(modelName);
            param.setEnvironment(environment);
            param.setType("Properties");
            requestParam.setParam(param);
            RsResponse<String> response = RestClientUtil.post(configUrl, requestParam,new TypeReference<RsResponse<String>>() {});
            String result = response.getResult();
            Map<String, String> resultMap = JSONArray.parseObject(result, Map.class);
            if (resultMap != null) {
                String packageName = entry.getKey();
                String driverClassName = resultMap.get("jdbc.driverClassName");
                logger.debug("Driver Class Name:"+driverClassName);
                String masterUsername = resultMap.get("masterDataSource.username");
                logger.debug("masterDataSource Name:"+masterUsername);
                String masterPassword = resultMap.get("masterDataSource.userpwd");
                logger.debug("masterDataSource password:"+masterPassword);
                logger.debug("Driver Class Name:"+driverClassName);
                String slaveUsername = resultMap.get("slaveDataSource.username");
                logger.debug("slaveDataSource Name:"+slaveUsername);
                String slavePassword = resultMap.get("slaveDataSource.userpwd");
                logger.debug("slaveDataSource password:"+slavePassword);
                String jdbcUrl = resultMap.get("jdbc.url");
                logger.debug("Jdbc Url:"+jdbcUrl);
                String readOnlyUrl = resultMap.get("jdbcReadOnly.url");
                logger.debug("Read Only Url:"+jdbcUrl);
                String masterDataSourceName = packageName+".logic.master.datasource";
                String slaveDataSourceName = packageName + ".logic.slave.datasource";
                DataSource masterDataSource = createDataSource(context,driverClassName,jdbcUrl,masterUsername,masterPassword,masterDataSourceName);
                dataSourceMap.put(masterDataSourceName,masterDataSource);
                DataSource slaveDataSource = createDataSource(context,driverClassName,readOnlyUrl,slaveUsername,slavePassword,slaveDataSourceName);
                dataSourceMap.put(slaveDataSourceName,slaveDataSource);
            }
        }

        DataSource masterDataSource = context.getBean("masterDataSource",DataSource.class);
        dataSourceMap.put("masterDataSource",masterDataSource);
        return dataSourceMap;
    }

    private static DataSource createDataSource(ApplicationContext context,String driverClassName,String url,String username,String password,String beanName){
        DataSourceDynamicBean dataSourceDynamicBean = new DataSourceDynamicBean(driverClassName,url,username,password,beanName);
        DynamicBeanReader dynamicBeanReader = context.getBean("dynamicBeanReader", DynamicBeanReaderImpl.class);
        dynamicBeanReader.loadBean(dataSourceDynamicBean);
        DataSource dataSource = context.getBean(beanName,BasicDataSource.class);
        return dataSource;
    }

    /**
     * 启动操作
     *
     * @param args args
     */
    public static void main(String[] args) {
        checkParam(args);
        ApplicationContext context = getApplicationContext();
        DynamicDataSource dynamicDataSource = context.getBean("dynamicDataSource",DynamicDataSource.class);
        Map<Object, Object> dataSourceMap = initDataSource(context);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.afterPropertiesSet();
        String batchId = args[NumberConst.IntDef.INT_ZERO];
        LockBatch lockBatch = context.getBean("lockBatch", LockBatch.class);
        lockBatch.lockBatch(batchId);
        BaseBatch batch = getBaseBatch(context, batchId);
        batch.execution(args);
    }

}
