package com.msk.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XmlUtils {
    private static Logger logger = LoggerFactory.getLogger(XmlUtils.class);
    public static Map<String,String> createXml(InputStream inputStream){
        DocumentBuilder documentBuilder = null;
        DocumentBuilderFactory documentBuilderFactory = null;
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Map<String,String> map = new HashMap<>();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            Element element = document.getDocumentElement();
            logger.info("根元素{}",element.getNodeName());
            NodeList childNodes = element.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                String textContent = node.getTextContent();
                if(textContent == null || "".equals(textContent.trim())){
                    continue;
                }
                Node attributes = node.getAttributes().getNamedItem("id");
                String key = attributes.getTextContent();
                String value = textContent.trim();
                map.put(key,value);
                logger.info("Text Content:{}",value);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
