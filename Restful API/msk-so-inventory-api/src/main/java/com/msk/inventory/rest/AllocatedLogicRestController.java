package com.msk.inventory.rest;

import com.msk.comm.bean.RestResponse;
import com.msk.common.bean.RestRequest;
import com.msk.inventory.bean.IvmAllocatedLogicBean;
import com.msk.inventory.service.IIvmAllocatedLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng_xu on 2016/9/2.
 */
@RestController
@RequestMapping("api")
public class AllocatedLogicRestController {
    @Autowired
    private IIvmAllocatedLogicService ivmAllocatedLogicService;

    @RequestMapping(value = "/allocatedLogic/save", method = RequestMethod.POST)
    public RestResponse<IvmAllocatedLogicBean> insertAllocatedLogic(
        @RequestBody RestRequest<IvmAllocatedLogicBean> requestBody) {
        ivmAllocatedLogicService.insertAllocatedLogic(requestBody.getParam());
        return null;
    }

    @RequestMapping(value = "/allocatedLogic/update", method = RequestMethod.POST)
    public RestResponse updateAllocatedLogicByCondition(@RequestBody RestRequest<IvmAllocatedLogicBean> requestBody){
        ivmAllocatedLogicService.updateAllocatedLogicByCondition(requestBody.getParam());
        return null;
    }

    @RequestMapping(value = "/allocatedLogic/select", method = RequestMethod.POST)
    public List<IvmAllocatedLogicBean> selectAllocatedLogicList(@RequestBody RestRequest<IvmAllocatedLogicBean> requestBody){
        List<IvmAllocatedLogicBean> allocatedLogicList = new ArrayList<IvmAllocatedLogicBean>();
        allocatedLogicList = ivmAllocatedLogicService.selectAllocatedLogicList(requestBody.getParam());
        return allocatedLogicList;
    }

    @RequestMapping(value = "/allocatedLogic/count", method = RequestMethod.POST)
    public int getCountAllocatedLogic(@RequestBody RestRequest<IvmAllocatedLogicBean> requestBody){
        int pageCount = ivmAllocatedLogicService.getCountAllocatedLogic(requestBody.getParam());
        return pageCount;
    }

}
