package com.hoperun.plug.spring.interceptor;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PreferencesPlaceholderConfigurer;

/**
 * Property解密使用
 * @author Administrator
 *
 */
public class PropertyPlaceholderConfigurerEx extends PreferencesPlaceholderConfigurer {
	 /** logger */
    private static Logger logger = LoggerFactory.getLogger(PropertyPlaceholderConfigurerEx.class);
    /** whether the need for encryption */
    private boolean needEncrypt = true;
    /** the items these need crypt */
    private String[] encryptItems = {};
	private IPropertiesLoad  propertiesLoad;

	public IPropertiesLoad getPropertiesLoad() {
		return propertiesLoad;
	}

	public void setPropertiesLoad(IPropertiesLoad propertiesLoad) {
		this.propertiesLoad = propertiesLoad;
	}

	/**
	 * @return the needEncrypt
	 */
	public boolean isNeedEncrypt() {
		return needEncrypt;
	}
	/**
	 * @param needEncrypt the needEncrypt to set
	 */
	public void setNeedEncrypt(boolean needEncrypt) {
		this.needEncrypt = needEncrypt;
	}
	/**
	 * @return the encryptItems
	 */
	public String[] getEncryptItems() {
		return encryptItems;
	}
	/**
	 * @param encryptItems the encryptItems to set
	 */
	public void setEncryptItems(String[] encryptItems) {
		this.encryptItems = encryptItems;
	}
	/**
     * Visit each bean definition in the given bean factory and attempt to replace ${...} property
     * placeholders with values from the given properties.
     * 
     * @param beanFactory 工厂类
     * @param props 参数
     * @throws BeansException 异常
     */
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
        throws BeansException {
    	logger.debug("The Process Properties Method");
		if(this.propertiesLoad!=null){
			this.propertiesLoad.loadProperties(props);
		}
        super.processProperties(beanFactory, props);
    }
}
