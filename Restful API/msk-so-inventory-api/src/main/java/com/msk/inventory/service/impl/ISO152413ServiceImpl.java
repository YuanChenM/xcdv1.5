package com.msk.inventory.service.impl;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.utils.PartCodeUtil;
import com.msk.common.utils.SplitListParamUtil;
import com.msk.inventory.bean.ISO152413ParamBean;
import com.msk.inventory.bean.ISO152413PdStockResultBean;
import com.msk.inventory.bean.InventoryViewBean;
import com.msk.inventory.service.IISO152413Service;
import com.msk.inventory.service.IInventoryViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfan on 16/8/23.
 */
@Service
public class ISO152413ServiceImpl extends BaseService implements IISO152413Service {

    @Autowired
    private IInventoryViewService iInventoryViewService;

    @Transactional
    public List<ISO152413PdStockResultBean> findSlProductIvList(ISO152413ParamBean sqlBean) {
        if (sqlBean.getProducts().size() > 0 && sqlBean.getPageNo() != 0 && sqlBean.getPageCount() != 0
                && sqlBean.isPaging()) {
            throw  new BusinessException("无法针对多个货品明细条件进行分页查询！");
        }
        List<ISO152413PdStockResultBean> iso151413PdStockResultBeanList = new ArrayList<ISO152413PdStockResultBean>();
        InventoryViewBean ivBean = new InventoryViewBean();

        if (sqlBean.getSalePlatform() == null || sqlBean.getSalePlatform().equals("")) {
            throw new BusinessException("销售平台不能为空");
        }
        if (sqlBean.getSlCode() == null || sqlBean.getSlCode().equals("")) {
            throw new BusinessException("卖家或者买手编码不能为空");
        }
        if (sqlBean.getLgcsCode() == null || sqlBean.getLgcsCode().equals("")) {
            throw new BusinessException("物流区编码不能为空");
        }

        int listSize = sqlBean.getProducts().size();
        int pageSize = 5;// 设置list可查询长度为2
        try {
        if (listSize > 0 && listSize > pageSize) {
                List<List> iso152413ProductParamList = SplitListParamUtil.splitParam(sqlBean, pageSize, "products");
                for (int i = 0; i < iso152413ProductParamList.size(); i++) {
                    ivBean.setPlatform(sqlBean.getSalePlatform());
                    ivBean.setSlId(sqlBean.getSlCode());
                    ivBean.setLogicArea(sqlBean.getLgcsCode());
                    ivBean.setProducts(iso152413ProductParamList.get(i));

                    List<InventoryViewBean> ivBeanList = iInventoryViewService.queryOwnerPdListByCondition(ivBean);
                    List<ISO152413PdStockResultBean> iso151413NewResultList = new ArrayList<ISO152413PdStockResultBean>();
                    if (ivBeanList.size() != 0) {
                        for (int j = 0; j < ivBeanList.size(); j++) {
                            ISO152413PdStockResultBean pdStock = new ISO152413PdStockResultBean();
                            InventoryViewBean iviewBean = ivBeanList.get(j);
                            PartCodeUtil.parseCodeForPdCode(pdStock, iviewBean.getPdCode());
                            pdStock.setUnit("箱");
                            pdStock.setPdName(iviewBean.getPdName());
                            pdStock.setSupplierCode(iviewBean.getOwnerCode());
                            pdStock.setSupplierName(iviewBean.getOwnerName());
                            pdStock.setStockQty(iviewBean.getOnhandQty());
                            pdStock.setAvailableQty(iviewBean.getAvailableQty());
                            pdStock.setAllocatedQty(iviewBean.getAllocatedQty());
                            iso151413NewResultList.add(pdStock);
                        }
                    }
                    if (iso151413NewResultList.size() != 0) {
                        for (int j = 0; j < iso151413NewResultList.size(); j++) {
                            iso151413PdStockResultBeanList.add(iso151413NewResultList.get(j));
                        }
                }
            }
        } else {
            sqlBean.setPageNo((sqlBean.getPageNo()-1)*sqlBean.getPageCount());
            for (int i = 0; i < sqlBean.getProducts().size(); i++) {
                ivBean.setPlatform(sqlBean.getSalePlatform());
                ivBean.setSlId(sqlBean.getSlCode());
                ivBean.setLogicArea(sqlBean.getLgcsCode());
                    ivBean.setPdCode(sqlBean.getProducts().get(i).getPdCode());
                ivBean.setPdName(sqlBean.getProducts().get(i).getPdName());
                ivBean.setOwnerCode(sqlBean.getProducts().get(i).getSupplierCode());

                List<InventoryViewBean> ivBeanList = iInventoryViewService.queryOwnerPdListByCondition(ivBean);
                if (ivBeanList.size() != 0) {
                    for (int j = 0; j < ivBeanList.size(); j++) {
                        ISO152413PdStockResultBean pdStock = new ISO152413PdStockResultBean();
                            InventoryViewBean iviewBean = ivBeanList.get(j);
                            PartCodeUtil.parseCodeForPdCode(pdStock, iviewBean.getPdCode());
                            pdStock.setUnit("箱");
                        pdStock.setPdName(ivBeanList.get(j).getPdName());
                        pdStock.setSupplierCode(ivBeanList.get(j).getOwnerCode());
                            pdStock.setSupplierName(ivBeanList.get(j).getOwnerName());
                        pdStock.setStockQty(ivBeanList.get(j).getOnhandQty());
                        pdStock.setAvailableQty(ivBeanList.get(j).getAvailableQty());
                        pdStock.setAllocatedQty(ivBeanList.get(j).getAllocatedQty());
                        iso151413PdStockResultBeanList.add(pdStock);
                    }
                }
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("供应商产品库存查询失败");
        }
        return iso151413PdStockResultBeanList;
    }
}
