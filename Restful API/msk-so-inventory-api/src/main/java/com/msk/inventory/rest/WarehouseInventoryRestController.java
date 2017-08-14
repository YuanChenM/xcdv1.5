package com.msk.inventory.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.msk.inventory.bean.IvmwarehouseInvListBean;
import com.msk.inventory.entity.IvmWarehouseInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.common.properties.ConfigServerProperties;
import com.msk.inventory.bean.IvmWarehouseHandlerBean;
import com.msk.inventory.bean.IvmWarehouseInventoryBean;
import com.msk.inventory.bean.IvmWarehouseResultBean;
import com.msk.inventory.service.IWarehouseInventoryService;

/**
 * Created by duan_kai on 2016/9/3.
 */
@RestController
@RequestMapping("api")
public class WarehouseInventoryRestController {

    @Autowired
    private ConfigServerProperties configServerProperties;

    @Autowired
    private IWarehouseInventoryService warehouseInventoryService;

    /**
     * 同步库存，批量
     *
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/warehouseInventory/testSynByList",
            method = RequestMethod.POST)
    public RestResponse<IvmWarehouseInventoryBean> saveTransactionLog(
            @RequestBody RestRequest<IvmWarehouseInventoryBean> requestBody) {
        /**
         * 开发已完成80%，待与美迪福库存接口联调测试
         */
        String urlStr = "http://114.55.4.220:81/fujisnapshot";
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            String xmlInfo = getXmlInfo(requestBody.getParam());
            out.write(new String(xmlInfo.getBytes("utf-8")));
            out.flush();
            out.close();

            List<IvmWarehouseResultBean> resultList = getProductList(con.getInputStream());
            IvmWarehouseInventoryBean ivWareHouseparam = new IvmWarehouseInventoryBean();
            List<IvmWarehouseInventoryBean> sqlsList = new ArrayList<IvmWarehouseInventoryBean>();
            if (resultList.size() > 0) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                Date nowDate = new Date(System.currentTimeMillis());
                String synchroNo = formatter.format(nowDate);
                for (int i = 0; i < resultList.size(); i++) {
                    IvmWarehouseInventoryBean param = new IvmWarehouseInventoryBean();
                    param.setSynchroNo(synchroNo);
                    param.setSynchroDate(nowDate);
                    param.setSku(resultList.get(i).getPdCode());
                    param.setWhId(Long.parseLong("1"));
                    param.setWhCode("1");
                    param.setLogicArea(resultList.get(i).getArea());
                    param.setOwnerId(Long.parseLong(resultList.get(i).getSupplierCode()));
                    param.setOwnerCode("1");
                    param.setSlId(Long.parseLong(resultList.get(i).getConsignee()));
                    param.setSlCode("");
                    param.setIvType("53");
                    param.setQty(resultList.get(i).getQuantity());
                    sqlsList.add(param);
                }
            }
            warehouseInventoryService.insertByList(ivWareHouseparam);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<IvmWarehouseResultBean> getProductList(InputStream xmlStream) {
        SAXParser parser = null;
        try {
            // 构建SAXParser
            parser = SAXParserFactory.newInstance().newSAXParser();
            // 实例化 Defaulthandler对象
            IvmWarehouseHandlerBean handler = new IvmWarehouseHandlerBean();
            // 调用parse()方法转换
            parser.parse(xmlStream, handler);
            List<IvmWarehouseResultBean> list = handler.getIvmWarehouseResult();
            return list;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getXmlInfo(IvmWarehouseInventoryBean ivWareVBean) {
        StringBuilder sb = new StringBuilder();
        sb.append("<param>");
        List<IvmwarehouseInvListBean> ivWareBeanList = ivWareVBean.getSqlList();
        if (ivWareBeanList.size() > 0) {
            for (int i = 0; i < ivWareBeanList.size(); i++) {
                String consignee = ivWareBeanList.get(i).getConsignee();
                String area = ivWareBeanList.get(i).getArea();
                String supplierCode = ivWareBeanList.get(i).getSupplierCode();
                String pdCode = ivWareBeanList.get(i).getPdCode();
                sb.append("<productList>");
                sb.append("<consignee>" + consignee + "</consignee>");
                sb.append("<area>" + area + "</area>");
                sb.append("<supplierCode>" + supplierCode + "</supplierCode>");
                sb.append("<pdCode>" + pdCode + "</pdCode>");
                sb.append("</productList>");
            }
        }
        sb.append("</param>");
        return sb.toString();
    }
}
