package com.msk.inventory.service.impl;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.ISO152417ParamBean;
import com.msk.inventory.bean.ISO152417SupplierBean;
import com.msk.inventory.service.IISO152417Service;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zheng_xu on 2016/9/12.
 */
@Service
public class ISO152417ServiceImpl extends BaseService implements IISO152417Service {
    @Override
    public List<ISO152417SupplierBean> getOwnersInHistory(ISO152417ParamBean paramBean) {
        if (paramBean.getSalePlatform() == null || paramBean.getSalePlatform().equals("")) {
            throw new BusinessException("销售平台不能为空");
        }
        List<ISO152417SupplierBean> supplierList = this.findList("getOwnersInHistory", paramBean);
        return supplierList;
    }
}
