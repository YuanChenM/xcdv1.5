package com.msk.comm.dynamic;

/**
 * Created by jackjiang on 16/7/4.
 */
public abstract class DynamicBean {
    private String beanName;

    public DynamicBean(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 获得beanName
     **/
    public String getBeanName() {
        return beanName;
    }

    protected abstract String getBeanXml();

    public String getSpringXml(){
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<beans xmlns=\"http://www.springframework.org/schema/beans\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
        xml.append("    xmlns:context=\"http://www.springframework.org/schema/context\" xmlns:tx=\"http://www.springframework.org/schema/tx\"\n");
        xml.append("    xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\n");
        xml.append("            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd\n");
        xml.append("            http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd\n");
        xml.append("        \">");
        xml.append(this.getBeanXml());
        xml.append("</beans>");
        return xml.toString();
    }

}
