package com.msk.comm.dynamic.impl;

import com.msk.comm.dynamic.DynamicBean;

/**
 * Created by jackjiang on 16/7/4.
 */
public class DataSourceDynamicBean extends DynamicBean{
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    public DataSourceDynamicBean(String beanName) {
        super(beanName);
    }

    public DataSourceDynamicBean(String driverClassName,String url,String username,String password,String beanName){
        this(beanName);
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * 设置driverClassName
     *
     * @param driverClassName driverClassName
     **/
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     * 设置url
     *
     * @param url url
     **/
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 设置username
     *
     * @param username username
     **/
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 设置password
     *
     * @param password password
     **/
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected String getBeanXml() {
        StringBuffer beanXml = new StringBuffer();
        beanXml.append(" <bean id=\""+this.getBeanName()+"\" class=\"org.apache.commons.dbcp.BasicDataSource\" destroy-method=\"close\">");
        beanXml.append("<property name=\"driverClassName\" value=\""+this.driverClassName+"\" />");
        beanXml.append("<property name=\"url\" value=\""+this.url+"\" />");
        beanXml.append("<property name=\"username\" value=\""+username+"\" />");
        beanXml.append("<property name=\"password\" value=\""+this.password+"\" />");
        beanXml.append("<property name=\"defaultAutoCommit\" value=\"false\" />");
        beanXml.append("<property name=\"initialSize\" value=\"2\" />");
        beanXml.append("<property name=\"maxActive\" value=\"10\" />");
        beanXml.append("<property name=\"maxWait\" value=\"60000\" />");
        beanXml.append("</bean>");
        return beanXml.toString();
    }
}
