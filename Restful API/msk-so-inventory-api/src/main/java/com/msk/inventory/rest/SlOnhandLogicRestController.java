package com.msk.inventory.rest;

import com.msk.comm.bean.RestResponse;
import com.msk.common.bean.RestRequest;
import com.msk.inventory.bean.IvmSlOnhandLogicBean;
import com.msk.inventory.service.ISlOnhandLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng_xu on 2016/9/7.
 */
@RestController
@RequestMapping("api")
public class SlOnhandLogicRestController {
    @Autowired
    private ISlOnhandLogicService slOnhandLogicService;

    @RequestMapping(value = "/sl_OnhandLogic/save",
        method = RequestMethod.POST)
    public RestResponse<IvmSlOnhandLogicBean> insertAllocatedLogic(
        @RequestBody RestRequest<IvmSlOnhandLogicBean> requestBody) {
        slOnhandLogicService.insertSlOnhandLogic(requestBody.getParam());
        return null;
    }

    @RequestMapping(value = "/sl_OnhandLogic/update",
        method = RequestMethod.POST)
    public RestResponse updateAllocatedLogicByCondition(@RequestBody RestRequest<IvmSlOnhandLogicBean> requestBody) {
        slOnhandLogicService.updateSlOnhandLogicByCondition(requestBody.getParam());
        return null;
    }

    @RequestMapping(value = "/sl_OnhandLogic/select",
        method = RequestMethod.POST)
    public List<IvmSlOnhandLogicBean> selectAllocatedLogicList(
        @RequestBody RestRequest<IvmSlOnhandLogicBean> requestBody) {
        List<IvmSlOnhandLogicBean> slOnhandLogicList = new ArrayList<IvmSlOnhandLogicBean>();
        slOnhandLogicList = slOnhandLogicService.getSlOnhandLogicList(requestBody.getParam());
        return slOnhandLogicList;
    }

    @RequestMapping(value = "/sl_OnhandLogic/count",
        method = RequestMethod.POST)
    public int getCountAllocatedLogic(@RequestBody RestRequest<IvmSlOnhandLogicBean> requestBody) {
        int pageCount = slOnhandLogicService.getCountSlOnhandLogic(requestBody.getParam());
        return pageCount;
    }
}
