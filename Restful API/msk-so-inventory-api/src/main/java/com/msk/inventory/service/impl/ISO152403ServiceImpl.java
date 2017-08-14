package com.msk.inventory.service.impl;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.utils.SplitListParamUtil;
import com.msk.inventory.bean.ISO152403ParamBean;
import com.msk.inventory.bean.ISO152403StockParamBean;
import com.msk.inventory.bean.ISO152403StockResultBean;
import com.msk.inventory.bean.InventoryViewBean;
import com.msk.inventory.service.IISO152403Service;
import com.msk.inventory.service.IInventoryViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng_xu on 2016/8/24.
 */
@Service
public class ISO152403ServiceImpl extends BaseService implements IISO152403Service {

    @Autowired
    private IInventoryViewService iInventoryViewService;

    @Transactional
    public List<ISO152403StockResultBean> getIso152413StockList(ISO152403ParamBean sqlBean) {
        if (sqlBean.getPdList().size() > 0 && sqlBean.getPageNo() != 0 && sqlBean.getPageCount() != 0
                && sqlBean.isPaging()) {
            throw new BusinessException("无法针对多个货品明细条件进行分页查询！");
        }

        for (int i = 0; i < sqlBean.getPdList().size(); i++) {
            ISO152403StockParamBean stockBean = sqlBean.getPdList().get(i);
            if (stockBean.getLgcsCode() == null || stockBean.getLgcsCode().equals("")) {
                throw new BusinessException("物流区编码不能为空");
            }
            if (stockBean.getPdTypeCode() == null || stockBean.getPdTypeCode().equals("")) {
                throw new BusinessException("产品类型编码(pdCode前9位)不能为空");
            }
            if (stockBean.getSalePlatform() == null || stockBean.getSalePlatform().equals("")) {
                throw new BusinessException("平台编码不能为空");
            }
        }

        List<ISO152403StockResultBean> iso152403StockResultList = new ArrayList<ISO152403StockResultBean>();
        InventoryViewBean ivBean = new InventoryViewBean();
        int listSize = sqlBean.getPdList().size();
        int pageSize = 5;// 设置list可查询长度为5
        if (listSize > 0 && listSize > pageSize) {
            try {
                List<List> iso152403StockParamList = SplitListParamUtil.splitParam(sqlBean, pageSize, "pdList");
                for (int i = 0; i < iso152403StockParamList.size(); i++) {
                    ivBean.setIso03Products(iso152403StockParamList.get(i));
                    List<InventoryViewBean> ivBeanList = iInventoryViewService.querySlPdListByCondition(ivBean);
                    List<ISO152403StockResultBean> iso152403NewStockResultList = new ArrayList<ISO152403StockResultBean>();
                    if (ivBeanList.size() != 0) {
                        for (int j = 0; j < ivBeanList.size(); j++) {
                            ISO152403StockResultBean stockResult = new ISO152403StockResultBean();
                            stockResult.setPdName(ivBeanList.get(j).getPdName());
                            stockResult.setLgcsCode(ivBeanList.get(j).getLogicArea());
                            stockResult.setSalePlatform(ivBeanList.get(j).getPlatformName());
                            stockResult.setAllocatedQty(ivBeanList.get(j).getAllocatedQty());
                            stockResult.setPdTypeSumStock(ivBeanList.get(j).getAvailableQty());
                            stockResult.setOnhandQty(ivBeanList.get(j).getOnhandQty());
                            iso152403NewStockResultList.add(stockResult);
                        }
                    }
                    if (iso152403NewStockResultList.size() != 0) {
                        for (int j = 0; j < iso152403NewStockResultList.size(); j++) {
                            iso152403StockResultList.add(iso152403NewStockResultList.get(j));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("库存信息查询失败");
            }
        } else {
            for (int i = 0; i < sqlBean.getPdList().size(); i++) {
                ivBean.setPdCode(sqlBean.getPdList().get(i).getPdTypeCode());
                ivBean.setLogicArea(sqlBean.getPdList().get(i).getLgcsCode());
                ivBean.setPlatform(sqlBean.getPdList().get(i).getSalePlatform());

                List<InventoryViewBean> ivBeanList = iInventoryViewService.querySlPdListByCondition(ivBean);
                if (ivBeanList.size() != 0) {
                    for (int j = 0; j < ivBeanList.size(); j++) {
                        ISO152403StockResultBean stockResult = new ISO152403StockResultBean();
                        stockResult.setPdName(ivBeanList.get(j).getPdName());
                        stockResult.setLgcsCode(ivBeanList.get(j).getLogicArea());
                        stockResult.setSalePlatform(ivBeanList.get(j).getPlatformName());
                        stockResult.setAllocatedQty(ivBeanList.get(j).getAllocatedQty());
                        stockResult.setPdTypeSumStock(ivBeanList.get(j).getAvailableQty());
                        stockResult.setOnhandQty(ivBeanList.get(j).getOnhandQty());
                        iso152403StockResultList.add(stockResult);
                    }
                }
            }
        }
        return iso152403StockResultList;
    }
}
