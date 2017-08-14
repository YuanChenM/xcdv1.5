package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.common.properties.ConfigServerProperties;
import com.msk.inventory.bean.IvmInventoryByProdBean;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.entity.IvmInventoryByProd;
import com.msk.inventory.service.IInventoryByProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng_xu on 2016/8/16.
 */
@RestController
@RequestMapping("api")
public class InventoryByProdRestController {
    @Autowired
    private ConfigServerProperties configServerProperties;
    @Autowired
    private IInventoryByProdService inventoryByProdService;

    /**
     * 插入库存按产品信息数据
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventoryByProd/insert",method = RequestMethod.POST)
    public RestResponse<IvmInventoryByProd> saveInventoryByProd(
            @RequestBody RestRequest<IvmInventoryByProdBean> requestBody){
        inventoryByProdService.insertOneInventoryByProd(requestBody.getParam());
        return null;
    }

    /**
     * 查询库存按产品信息数据
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventoryByProd/selectByCondition",method = RequestMethod.POST)
    public List<IvmInventoryByProdBean> queryInventoryByProdList(
            @RequestBody RestRequest<IvmInventoryByProdBean> requestBody){
        List<IvmInventoryByProdBean> list = new ArrayList<IvmInventoryByProdBean>();
        list = inventoryByProdService.selectInventoryByProdList(requestBody.getParam());
        return list;
    }

    /**
     * 查询库存按产品信息数据总数
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventoryByProd/countByCondition",method = RequestMethod.POST)
    public int selectInventoryByProdCount( @RequestBody RestRequest<IvmInventoryByProdBean> requestBody){
        int pageCount = inventoryByProdService.selectInventoryByProdCount(requestBody.getParam());
        return pageCount;
    }

    /**
     * 更改库存按产品信息数据
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventoryByProd/update",method = RequestMethod.POST)
    public RestResponse updateInventoryByProd(@RequestBody RestRequest<IvmInventoryByProdBean> requestBody){
        inventoryByProdService.updateInventoryByProd(requestBody.getParam());
        return null;
    }

    /**
     * 调用存储过程TOUCH_ALL_IV_PROD汇总库存
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventoryByProd/touchAllIvProd",method = RequestMethod.POST)
    public RestResponse touchAllIvProd(@RequestBody RestRequest<IvmInventoryByProdBean> requestBody) {
        inventoryByProdService.touchAllIvProd(requestBody.getParam());
        return null;
    }

    /**
     * 入库同步库存
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventoryByProd/touchIvProdByInbound",method = RequestMethod.POST)
    public RestResponse touchIvProdByInbound(@RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        inventoryByProdService.touchIvProdByInbound(requestBody.getParam());
        return null;
    }

    /**
     * 出库同步库存
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventoryByProd/touchIvProdByOutbound",method = RequestMethod.POST)
    public RestResponse touchIvProdByOutbound(@RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        inventoryByProdService.touchIvProdByOutbound(requestBody.getParam());
        return null;
    }
}
