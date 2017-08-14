package com.msk.inventory.service.impl;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.utils.PartCodeUtil;
import com.msk.common.utils.SplitListParamUtil;
import com.msk.inventory.bean.ISO152412ParamBean;
import com.msk.inventory.bean.ISO152412PdStockResultBean;
import com.msk.inventory.bean.InventoryViewBean;
import com.msk.inventory.service.IISO152412Service;
import com.msk.inventory.service.IInventoryViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duan_kai on 2016/8/25.
 */
@Service
public class ISO152412ServiceImpl extends BaseService implements IISO152412Service {

    @Autowired
    private IInventoryViewService iInventoryViewService;

    @Transactional
    public List<ISO152412PdStockResultBean> findSlProductIvList(ISO152412ParamBean sqlBean){
        if (sqlBean.getProducts().size() > 0 && sqlBean.getPageNo() != 0 && sqlBean.getPageCount() != 0
                && sqlBean.isPaging()) {
            throw new BusinessException("无法针对多个货品明细条件进行分页查询！");
        }
        if (sqlBean.getLgcsCode() == null || sqlBean.getLgcsCode().equals("")) {
            throw new BusinessException("物流区编码不能为空");
        }
        if (sqlBean.getSalePlatform() == null || sqlBean.getSalePlatform().equals("")) {
            throw new BusinessException("销售平台不能为空");
        }
        if (sqlBean.getSlCode() == null || sqlBean.getSlCode().equals("")) {
            throw new BusinessException("卖家或者买手编码不能为空");
        }

        List<ISO152412PdStockResultBean> iso152412PdStockResultBeanList = new ArrayList<ISO152412PdStockResultBean>();
        InventoryViewBean ivBean = new InventoryViewBean();

        int listSize = sqlBean.getProducts().size();
        int pageSize = 5;//设置list可查询长度为5
        try {
        if (listSize > 0 && listSize > pageSize) {
                List<List> iso152412ProductParamList = SplitListParamUtil.splitParam(sqlBean, pageSize, "products");
                for (int i = 0; i < iso152412ProductParamList.size(); i++) {
                    ivBean.setProducts(iso152412ProductParamList.get(i));
                    ivBean.setPlatform(sqlBean.getSalePlatform());
                    ivBean.setSlId(sqlBean.getSlCode());
                    ivBean.setLogicArea(sqlBean.getLgcsCode());

                    List<InventoryViewBean> ivBeanList = iInventoryViewService.querySlPdListByCondition(ivBean);

                    List<ISO152412PdStockResultBean> iso152412PdStockNewList = new ArrayList<ISO152412PdStockResultBean>();
                    if (ivBeanList.size() != 0) {
                        for (int j = 0; j < ivBeanList.size(); j++) {
                            ISO152412PdStockResultBean pdStockResult = new ISO152412PdStockResultBean();
                            InventoryViewBean iviewBean = ivBeanList.get(j);
                            pdStockResult.setUnit("箱");
                            PartCodeUtil.parseCodeForPdCode(pdStockResult, iviewBean.getPdCode());
                            pdStockResult.setPdName(iviewBean.getPdName());
                            pdStockResult.setOnhandQty(iviewBean.getOnhandQty());
                            pdStockResult.setAllocatedQty(iviewBean.getAllocatedQty());
                            pdStockResult.setAvailableQty(iviewBean.getAvailableQty());
                            iso152412PdStockNewList.add(pdStockResult);
                        }
                    }
                    if (iso152412PdStockNewList.size() != 0) {
                        for (int j = 0; j < iso152412PdStockNewList.size(); j++) {
                            iso152412PdStockResultBeanList.add(iso152412PdStockNewList.get(j));
                        }
                    }
                }
        } else {
            if(sqlBean.isPaging()){
            sqlBean.setPageNo((sqlBean.getPageNo()-1)*sqlBean.getPageCount());
            }
            for (int i = 0; i < sqlBean.getProducts().size(); i++) {
                ivBean.setPlatform(sqlBean.getSalePlatform());
                ivBean.setSlId(sqlBean.getSlCode());
                ivBean.setLogicArea(sqlBean.getLgcsCode());
                ivBean.setPdCode(sqlBean.getProducts().get(i).getPdCode());
                ivBean.setPdName(sqlBean.getProducts().get(i).getPdName());
                List<InventoryViewBean> ivBeanList = iInventoryViewService.querySlPdListByCondition(ivBean);
                if (ivBeanList.size() != 0) {
                    for (int j = 0; j < ivBeanList.size(); j++) {
                        ISO152412PdStockResultBean pdStockResult = new ISO152412PdStockResultBean();
                            InventoryViewBean iviewBean = ivBeanList.get(j);
                            pdStockResult.setUnit("箱");
                            PartCodeUtil.parseCodeForPdCode(pdStockResult, iviewBean.getPdCode());
                            pdStockResult.setPdName(iviewBean.getPdName());
                            pdStockResult.setOnhandQty(iviewBean.getOnhandQty());
                            pdStockResult.setAllocatedQty(iviewBean.getAllocatedQty());
                            pdStockResult.setAvailableQty(iviewBean.getAvailableQty());
                        iso152412PdStockResultBeanList.add(pdStockResult);
                    }
                }
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("卖家产品库存查询失败");
        }
        return iso152412PdStockResultBeanList;
    }
}
