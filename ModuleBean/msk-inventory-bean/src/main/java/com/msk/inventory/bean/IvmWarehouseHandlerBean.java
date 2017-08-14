/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.inventory.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.jar.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 */
public class IvmWarehouseHandlerBean extends DefaultHandler {
    //将读取的内容存放到一个BSO152406Result对象中，存放到list集合中

    ArrayList<IvmWarehouseResultBean> list = new ArrayList<IvmWarehouseResultBean>();
    private String currentTag;
    private IvmWarehouseResultBean ivmWarehouseResult;

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentTag = qName;
        if("productList".equals(currentTag)) {
            ivmWarehouseResult = new IvmWarehouseResultBean();
        }
    }


    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if("consignee".equals(currentTag)) {
            String consignee = new String(ch,start,length);
            ivmWarehouseResult.setConsignee(consignee);
        }
        if("area".equals(currentTag)) {
            String area = new String(ch,start,length);
            ivmWarehouseResult.setArea(area);
        }
        if("supplierCode".equals(currentTag)) {
            String supplierCode = new String(ch,start,length);
            ivmWarehouseResult.setSupplierCode(supplierCode);
        }
        if("pdCode".equals(currentTag)) {
            String pdCode = new String(ch,start,length);
            ivmWarehouseResult.setPdCode(pdCode);
        }
        if("QUANTITY".equals(currentTag)) {
            String quantityStr = new String(ch,start,length);
            BigDecimal quantity = new BigDecimal(quantityStr);
            ivmWarehouseResult.setQuantity(quantity);
        }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if("productList".equals(qName)) {
            list.add(ivmWarehouseResult);
            ivmWarehouseResult = null;
        }
        currentTag = null;
    }


    public ArrayList<IvmWarehouseResultBean> getIvmWarehouseResult() {
        return list;
    }
}
