package com.msk.inventory.service.impl;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.ISO152414ParamBean;
import com.msk.inventory.bean.ISO152414ProductResultBean;
import com.msk.inventory.bean.InventoryViewBean;
import com.msk.inventory.service.IISO152414Service;
import com.msk.inventory.service.IInventoryViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duan_kai on 2016/9/5.
 */
@Service
public class ISO152414ServiceImpl extends BaseService implements IISO152414Service {

    @Autowired
    private IInventoryViewService iInventoryViewService;

    @Override
    public List<ISO152414ProductResultBean> findSlProductIvList(ISO152414ParamBean sqlBean) {
        if (sqlBean.getSellerCode() == null || sqlBean.getSellerCode().equals("")) {
            throw new BusinessException("卖家编码不能为空");
        }
        if (sqlBean.getSupplierCode() == null || sqlBean.getSupplierCode().equals("")) {
            throw new BusinessException("供应商编码不能为空");
        }

        List<ISO152414ProductResultBean> productResultList = new ArrayList<ISO152414ProductResultBean>();
        InventoryViewBean ivBean = new InventoryViewBean();
        ivBean.setSlId(sqlBean.getSellerCode());
        ivBean.setOwnerCode(sqlBean.getSupplierCode());
        ivBean.setLogicArea(sqlBean.getLgcsCode());

        List<InventoryViewBean> ivBeanList = iInventoryViewService.querySlPdListByCondition(ivBean);
        if(ivBeanList.size() != 0){
            for (int i = 0; i < ivBeanList.size(); i++) {
                ISO152414ProductResultBean productResul = new ISO152414ProductResultBean();
                productResul.setProductCode(ivBeanList.get(i).getPdCode());
                productResul.setProductName(ivBeanList.get(i).getPdName());
                productResul.setStock(ivBeanList.get(i).getAvailableQty());
                productResul.setAllocatedQty(ivBeanList.get(i).getAllocatedQty());
                productResul.setOnhandQty(ivBeanList.get(i).getOnhandQty());
                productResultList.add(productResul);
            }
        }else{
            throw new BusinessException("无该卖家（" + sqlBean.getSellerCode() + "）在物流区域（" + sqlBean.getLgcsCode() + "）中的货主（"
                    + sqlBean.getSupplierCode() + "）产品信息");
        }
        return productResultList;
    }
}
