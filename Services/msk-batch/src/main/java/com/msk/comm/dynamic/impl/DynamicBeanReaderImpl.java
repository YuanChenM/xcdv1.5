package com.msk.comm.dynamic.impl;

import com.msk.comm.dynamic.DynamicBean;
import com.msk.comm.dynamic.DynamicBeanReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by jackjiang on 16/7/4.
 */
public class DynamicBeanReaderImpl implements DynamicBeanReader,ApplicationContextAware{
    private static Logger logger = LoggerFactory.getLogger(DynamicBeanReaderImpl.class);
    private ConfigurableApplicationContext applicationContext;
    private XmlBeanDefinitionReader beanDefinitionReader;

    /**
     * 初始化方法
     */
    public void init(){
        this.beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry)applicationContext.getBeanFactory());
        this.beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(applicationContext));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext)applicationContext;
    }

    @Override
    public void loadBean(DynamicBean dynamicBean) {
        long startTime = System.currentTimeMillis();
        String beanName = dynamicBean.getBeanName();
        if(applicationContext.containsBean(beanName)){
            logger.warn("bean【"+beanName+"】已经加载！");
            return;
        }
        beanDefinitionReader.loadBeanDefinitions(new DynamicResource(dynamicBean));
        logger.info("初始化bean【"+dynamicBean.getBeanName()+"】耗时"+(System.currentTimeMillis()-startTime)+"毫秒。");
    }
}
