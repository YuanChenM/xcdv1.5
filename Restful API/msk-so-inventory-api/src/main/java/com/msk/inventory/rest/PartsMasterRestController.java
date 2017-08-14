package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.bean.IvmPartsMasterBean;
import com.msk.inventory.entity.IvmPartsMaster;
import com.msk.inventory.service.IPartsMasterService;
import com.msk.inventory.service.impl.PartsMasterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackjiang on 16/8/12.
 */
@RestController
@RequestMapping("api")
public class PartsMasterRestController {
    @Autowired
    private IPartsMasterService partsMasterService;

    /**
     * 保存一条货品信息数据
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/parts/insert",method = RequestMethod.POST)
    public RestResponse<IvmPartsMaster> savePartsMaster(@RequestBody RestRequest<IvmPartsMasterBean> requestBody){
        partsMasterService.insertOneParts(requestBody.getParam());
        return null;
    }

    /**
     * 查询货品信息列表数据
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/parts/selectByCondition",method = RequestMethod.POST)
    public List<IvmPartsMaster> selectPartsMasterList(@RequestBody RestRequest<IvmPartsMasterBean> requestBody){
        List<IvmPartsMaster> partsMasterList = new ArrayList<IvmPartsMaster>();
        partsMasterList=   partsMasterService.selectPartsMasterList(requestBody.getParam());
        return partsMasterList;
    }

    /**
     * 查询货品信息列表总数
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/parts/countByCondition",method = RequestMethod.POST)
    public int selectPartsMasterCount(@RequestBody RestRequest<IvmPartsMasterBean> requestBody){
        int pageCount= partsMasterService.selectPartsMasterCount(requestBody.getParam());
        return pageCount;
    }

    /**
     * 修改一条货品信息
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/parts/update",method = RequestMethod.POST)
    public RestResponse<IvmPartsMaster> updatePartsMaster(@RequestBody RestRequest<IvmPartsMasterBean> requestBody){
        partsMasterService.updatePartsMaster(requestBody.getParam());
        return null;
    }

    /**
     * 通过pdCode和skuCode进行货品验证
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/parts/isExistPartsMaster",method = RequestMethod.POST)
    public boolean isExistPartsMaster(@RequestBody RestRequest<IvmPartsMasterBean> requestBody){
        boolean isExistPartsMaster =  partsMasterService.isExistPartsMaster(requestBody.getParam());
        return isExistPartsMaster;
    }

}
