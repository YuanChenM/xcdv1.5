package com.msk.common.xml;

import com.msk.common.xml.bean.EntityTemplate;
import com.msk.common.xml.bean.ListTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

public class JaxbXmlWrite<T>{
    private static Logger logger = LoggerFactory.getLogger(JaxbXmlWrite.class);
    private ListTemplate<T> listTemplate;
    private EntityTemplate<T> entityTemplate;
    public JaxbXmlWrite(ListTemplate<T> listTemplate){
        this.listTemplate = listTemplate;
    }
    public JaxbXmlWrite(EntityTemplate<T> entityTemplate){
        this.entityTemplate = entityTemplate;
    }

    public InputStream createEntityTemplateXml(Class entityTemplateClass){
        String xml = this.createXmlString(this.entityTemplate,entityTemplateClass);
        logger.info("输出XML:"+xml);
        return new ByteArrayInputStream(xml.getBytes());
    }

    public InputStream createListTemplateXml(Class listTemplateClass){
        String xml = this.createXmlString(this.listTemplate,listTemplateClass);
        logger.info("输出XML:"+xml);
        return new ByteArrayInputStream(xml.getBytes());
    }

    private String createXmlString(Object object,Class templateClass){
        try {
            JAXBContext jc = JAXBContext.newInstance(templateClass);
            Marshaller ms = jc.createMarshaller();
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ms.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter writer = new StringWriter();
            ms.marshal(object, writer);
            String xml = writer.toString();
            logger.info("输出XML:"+xml);
            return xml;
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.error("生成XML失败:"+e.getMessage());
        }
        return null;
    }



}
