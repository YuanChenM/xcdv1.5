package com.msk.inventory.rest;

import com.msk.comm.bean.param.BasePageParam;
import com.msk.comm.exception.BusinessException;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.constant.SystemConstant;
import com.msk.inventory.bean.SO152502Bean;
import com.msk.inventory.bean.SO152502ResultBean;
import com.msk.inventory.service.SO152502Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by zheng_xu on 2016/9/13.
 */
@RestController
@RequestMapping("api")
public class SO152502RestController {

    @Autowired
    private SO152502Service so152502Service;

    @RequestMapping(value = "/inv/getSellerInventoryList", method = RequestMethod.POST)
    public RestResponse<SO152502ResultBean> getSellerInventoryList(@RequestBody RestRequest<BasePageParam> requestBody) {
        RestResponse<SO152502ResultBean> response = new RestResponse<SO152502ResultBean>();
        SO152502ResultBean so152502ResultBean = new SO152502ResultBean();

        SO152502Bean so152502Bean = new SO152502Bean();
        Map<String, Object> filterMap = requestBody.getParam().getFilterMap();
        if (filterMap.isEmpty()) {
            throw new BusinessException("");
        }
        so152502Bean.setLgcsCode(filterMap.get("lgcsCode").toString());
        so152502Bean.setWarehouseCode(filterMap.get("warehouseCode").toString());
        so152502Bean.setSlCodeDis(filterMap.get("slCodeDis").toString());
        so152502Bean.setLgcsName(filterMap.get("lgcsName").toString());
        so152502Bean.setWarehouseName(filterMap.get("warehouseName").toString());
        so152502Bean.setSlName(filterMap.get("slName").toString());
        so152502Bean.setPdCode(filterMap.get("pdCode").toString());
        so152502Bean.setSalePlatform(filterMap.get("salePlatform").toString());
        so152502Bean.setPdName(filterMap.get("pdName").toString());
        so152502Bean.setStartPos(requestBody.getParam().getStartPos());
        so152502Bean.setEndPos(requestBody.getParam().getEndPos());
        List<SO152502Bean> so152502BeanList = so152502Service.getSellerInventoryList(so152502Bean);
        so152502ResultBean.setSo152502BeanList(so152502BeanList);
        response.setResult(so152502ResultBean);
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("查询成功！");
        return response;
    }
}
