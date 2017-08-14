package com.msk.comm.aspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.datasource.DatabaseContextHolder;
import com.hoperun.plug.spring.datasource.DynamicDataSource;

/**
 * *BatchDynamicDataSourceAspect
 * *@author jiang_nan
 * *@version 1.0
 **/
@Component
@Aspect
public class BatchDynamicDataSourceAspect implements Ordered {
    private static Logger logger = LoggerFactory.getLogger(BatchDynamicDataSourceAspect.class);
    private static Map<String, String> DATA_SOURCE_MAP = new HashMap();
    @Autowired
    private DynamicDataSource dynamicDataSource;
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Pointcut("execution(public * com.msk.*.*.logic.*Logic.*(..))")
    public void aspectPointcut() {
        logger.debug("Dynamic Data Source Aspect Logic Aspect.");
    }

    @After("aspectPointcut()")
    public void setBatchDataSource(){
        DatabaseContextHolder.setCustomerType("masterDataSource");
    }
    @Before("aspectPointcut()")
    public void setDynamicDataSource(JoinPoint jp) {
        Signature signature = jp.getSignature();
        Class<?> clazz = this.getClazz(signature);
        String className = clazz.getName();
        String packageName = clazz.getPackage().getName();
        logger.debug("Package Name:"+packageName);
        if(packageName.equals("com.msk.comm.logic")){
            DatabaseContextHolder.setCustomerType("masterDataSource");
            return;
        }
        //获得方法名称
        String methodName = this.getMethodName(signature);
        Method[] methodList = this.methodList(signature);
        for (Method method : methodList) {
            String declaredMethodName = method.getName();
            if(declaredMethodName.equals(methodName)){
                this.setDataSource(method,className,packageName);
                break;
            }
        }
    }
    public Class<?> getClazz(Signature signature) {
        return signature.getDeclaringType();
    }
    public void setDataSource(Method method, String className,String packageName){
        String methodName = method.getName();
        String key = className+ StringConst.DOT+methodName;
        String dataSourceName = DATA_SOURCE_MAP.get(key);
        if(!StringUtil.isEmpty(dataSourceName)){
            DatabaseContextHolder.setCustomerType(dataSourceName);
            return;
        }
        Transactional transactional = method.getAnnotation(Transactional.class);
        if(transactional!=null){
            boolean readOnly = transactional.readOnly();
            if(readOnly){
                dataSourceName = packageName +".slave.datasource";
            }
        }
        if(StringUtil.isEmpty(dataSourceName)){
            dataSourceName = packageName +".master.datasource";
        }
        logger.debug(dataSourceName);
        DatabaseContextHolder.setCustomerType(dataSourceName);
        DATA_SOURCE_MAP.put(key,dataSourceName);
    }
    public String getMethodName(Signature signature) {
        return signature.getName();
    }
    public Method[] methodList(Signature signature) {
        Class clazz = this.getClazz(signature);
        return clazz.getDeclaredMethods();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
