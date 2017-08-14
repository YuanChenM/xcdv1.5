package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.common.properties.ConfigServerProperties;
import com.msk.inventory.bean.IvmWarehouseBean;
import com.msk.inventory.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duan_kai on 2016/9/3.
 */
@RestController
@RequestMapping("api")
public class WarehouseRestController {

    @Autowired
    private ConfigServerProperties configServerProperties;

    @Autowired
    private IWarehouseService warehouseService;

    /**
     * 插入仓库信息
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/warehouse/insertOne",method = RequestMethod.POST)
    public RestResponse<IvmWarehouseBean> insertOneWarehouse(
            @RequestBody RestRequest<IvmWarehouseBean> requestBody){
        warehouseService.insertOne(requestBody.getParam());
        return null;
    }

    /**
     * 修改仓库信息
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/warehouse/updateOneByCode",method = RequestMethod.POST)
    public RestResponse<IvmWarehouseBean> updateWarehouseByCode(
            @RequestBody RestRequest<IvmWarehouseBean> requestBody){
        warehouseService.insertOne(requestBody.getParam());
        return null;
    }

    /**
     * 查询仓库信息列表数据
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/warehouse/selectByCondition",method = RequestMethod.POST)
    public List<IvmWarehouseBean> selectWarehouseByCondition(@RequestBody RestRequest<IvmWarehouseBean> requestBody){
        List<IvmWarehouseBean> partsMasterList = new ArrayList<IvmWarehouseBean>();
        partsMasterList=   warehouseService.selectListByCondition(requestBody.getParam());
        return partsMasterList;
    }

    /**
     * 查询仓库信息列表总数
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/warehouse/countByCondition",method = RequestMethod.POST)
    public int countWarehouseByCondition(@RequestBody RestRequest<IvmWarehouseBean> requestBody){
        int pageCount= warehouseService.countByCondition(requestBody.getParam());
        return pageCount;
    }
}
