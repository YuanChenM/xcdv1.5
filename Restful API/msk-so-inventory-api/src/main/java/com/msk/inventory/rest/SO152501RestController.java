package com.msk.inventory.rest;

import com.msk.comm.bean.param.BasePageParam;
import com.msk.comm.exception.BusinessException;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.constant.SystemConstant;
import com.msk.inventory.bean.SO152501Bean;
import com.msk.inventory.bean.SO152501ResultBean;
import com.msk.inventory.service.SO152501Service;
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
public class SO152501RestController {
    @Autowired
    private SO152501Service so152501Service;

    @RequestMapping(value = "/inv/getDistributionList",
        method = RequestMethod.POST)
    public RestResponse<SO152501ResultBean> getDistributionList(@RequestBody RestRequest<BasePageParam> requestBody) {
        RestResponse<SO152501ResultBean> response = new RestResponse<SO152501ResultBean>();
        SO152501ResultBean so152501ResultBean = new SO152501ResultBean();

        SO152501Bean so152501Bean = new SO152501Bean();
        Map<String, Object> filterMap = requestBody.getParam().getFilterMap();
        if (filterMap.isEmpty()) {
            throw new BusinessException("");
        }

        so152501Bean.setLgcsCode(filterMap.get("lgcsCode").toString());
        so152501Bean.setWarehouseCode(filterMap.get("warehouseCode").toString());
        so152501Bean.setSlCodeDis(filterMap.get("slCodeDis").toString());
        so152501Bean.setLgcsName(filterMap.get("lgcsName").toString());
        so152501Bean.setWarehouseName(filterMap.get("warehouseName").toString());
        so152501Bean.setSlName(filterMap.get("slName").toString());
        so152501Bean.setPdCode(filterMap.get("pdCode").toString());
        so152501Bean.setSupplierCode(filterMap.get("supplierCode").toString());
        so152501Bean.setSupplierName(filterMap.get("supplierName").toString());
        so152501Bean.setSupplyPlayFrom(filterMap.get("supplyPlayFrom").toString());
        so152501Bean.setPdName(filterMap.get("pdName").toString());
        so152501Bean.setStartPos(requestBody.getParam().getStartPos());
        so152501Bean.setEndPos(requestBody.getParam().getEndPos());
        List<SO152501Bean> so152501BeanList = so152501Service.getDistributionList(so152501Bean);
        so152501ResultBean.setSo152501BeanList(so152501BeanList);
        response.setResult(so152501ResultBean);
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("查询成功！");
        return response;
    }
}
