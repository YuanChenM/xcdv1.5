package com.msk.common.aspect;

import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.datasource.DatabaseContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * DynamicDataSourceAspect
 * @author jiang_nan
 * @version 1.0
 **/
@Aspect
public class DynamicDataSourceAspect extends com.hoperun.plug.spring.aspect.DynamicDataSourceAspect{
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    /**
     * Aspect Pointcut
     */
    @Pointcut("execution(public * com.msk.*.*.*Logic.*(..))")
    @Override
    public void aspectPointcut() {
        logger.debug("Dynamic Data Source Aspect Logic Aspect.");
    }

    @After("aspectPointcut()")
    public void clearDataSource(){
        DatabaseContextHolder.clearCustomerType();
    }

    @Override
    public void setDynamicDataSource(JoinPoint jp) {
        String customerType = DatabaseContextHolder.getCustomerType();
        if(!StringUtils.isEmpty(customerType)){
            return;
        }

        logger.debug("Dynamic Data Source Aspect");
        Signature signature = jp.getSignature();
        Class<?> clazz = this.getClazz(signature);
        String clazzName = clazz.getSimpleName();
        logger.debug("class name:"+clazz.getSimpleName());
        //获得方法名称
        String methodName = this.getMethodName(signature);
        //方法列表
        Method[] methodList = this.methodList(signature);
        for (Method method : methodList) {
            String declaredMethodName = method.getName();
            if(declaredMethodName.equals(methodName)){
                this.setDataSource(method,clazzName);
                break;
            }
        }
    }

    @Override
    public void setDataSource(Method method, String className) {
        String methodName = method.getName();
        String key = className+ StringConst.DOT+methodName;
        String dataSourceName = this.getDataSource(key);
        if(!StringUtil.isEmpty(dataSourceName)){
            logger.info("DataSourceName:"+dataSourceName);
            DatabaseContextHolder.setCustomerType(dataSourceName);
            return;
        }
        Transactional transactional = method.getAnnotation(Transactional.class);
        if(transactional!=null){
            boolean readOnly = transactional.readOnly();
            if(readOnly){
                dataSourceName = DatabaseContextHolder.SLAVE_DATA_SOURCE;
            }else{
                dataSourceName =  DatabaseContextHolder.MASTER_DATA_SOURCE;
            }
        }else{
            dataSourceName =  DatabaseContextHolder.MASTER_DATA_SOURCE;
        }
        logger.info("DataSourceName:"+dataSourceName);
        DatabaseContextHolder.setCustomerType(dataSourceName);
        this.setDataSourceMap(key,dataSourceName);


    }
}
