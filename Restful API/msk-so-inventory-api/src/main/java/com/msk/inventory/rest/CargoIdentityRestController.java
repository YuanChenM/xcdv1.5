package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.common.properties.ConfigServerProperties;
import com.msk.common.utils.DateTimeUtil;
import com.msk.inventory.bean.IvmCargoIdentityBean;
import com.msk.inventory.entity.IvmCargoIdentity;
import com.msk.inventory.service.ICargoIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zheng_xu on 2016/8/15.
 */
@RestController
@RequestMapping("api")
public class CargoIdentityRestController {
    @Autowired
    private ConfigServerProperties configServerProperties;
    @Autowired
    private ICargoIdentityService cargoIdentityService;

    /**
     * 保存一条货品身份数据
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/cargoIdentity/_save",method = RequestMethod.POST)
    public RestResponse<IvmCargoIdentity> saveCargoIdentity(@RequestBody RestRequest<IvmCargoIdentityBean> requestBody){
        cargoIdentityService.insertOneCargoIdentity(requestBody.getParam());
        return null;
    }

    /**
     * 查询货品身份数据
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/cargoIdentity/select",method = RequestMethod.POST)
    public List<IvmCargoIdentity> selectCargoIdentityList(@RequestBody RestRequest<IvmCargoIdentityBean> requestBody){
        List<IvmCargoIdentity> list = new ArrayList<IvmCargoIdentity>();
        list = cargoIdentityService.selectCargoIdentityList(requestBody.getParam());
        return list;
    }

    /**
     * 查询货品身份数据总数
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/cargoIdentity/countByCondition",method = RequestMethod.POST)
    public int selectCargoIdentityCount(@RequestBody RestRequest<IvmCargoIdentityBean> requestBody){
        int pageCount = cargoIdentityService.selectCargoIdentityCount(requestBody.getParam());
        return pageCount;
    }

    /**
     * 更改货品身份数据
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/cargoIdentity/updateSeq",method = RequestMethod.POST)
    public RestResponse updateCargoIdentity(@RequestBody RestRequest<IvmCargoIdentityBean> requestBody){
         cargoIdentityService.updateCargoIdentity(requestBody.getParam());
         return null;
    }

    /**
     * 测试货品身份分配
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/cargoIdentity/testAssignSeq",method = RequestMethod.POST)
    public String getString(@RequestBody RestRequest<IvmCargoIdentityBean> requestBody){
        Date time = DateTimeUtil.getCustomerDate();
        return cargoIdentityService.getLoadNo(time);
    }
}
