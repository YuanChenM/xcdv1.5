package com.hoperun.plug.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源设置拦截器。
 * 根据Logic中的Transactional注解区分获得DB的类型
 */
public abstract class DynamicDataSourceAspect implements Ordered{
	/**logger*/
	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

	private static Map<String,String> DATA_SOURCE_MAP = new HashMap<String, String>();

	public abstract void aspectPointcut();
	public Class<?> getClazz(Signature signature){
		return signature.getDeclaringType();
	}
	public String getMethodName(Signature signature){
		return signature.getName();
	}

	public Method[] methodList(Signature signature){
		Class<?> clazz = this.getClazz(signature);
		return clazz.getDeclaredMethods();
	}

	@Before("aspectPointcut()")
	public abstract void setDynamicDataSource(JoinPoint jp);
    /**
     * 必须添加，如果没不添加Order则无法在执行Spring AOP之前设置DataSource.
     * @return 0
     */
	public int getOrder() {
		return 0;
	}

	public abstract void setDataSource(Method method,String className);

	public void setDataSourceMap(String key,String value){
		DATA_SOURCE_MAP.put(key,value);
	}
	public String getDataSource(String key){
		return DATA_SOURCE_MAP.get(key);
	}

}
