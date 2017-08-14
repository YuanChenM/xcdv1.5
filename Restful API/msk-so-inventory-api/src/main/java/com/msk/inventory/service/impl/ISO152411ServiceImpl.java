package com.msk.inventory.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.msk.comm.bean.RestResponse;
import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.bean.RestRequest;
import com.msk.common.utils.RestClientUtil;
import com.msk.inventory.bean.*;
import com.msk.inventory.service.IISO152411Service;
import com.msk.inventory.service.IInventoryViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng_xu on 2016/8/23.
 */
@Service
public class ISO152411ServiceImpl extends BaseService implements IISO152411Service {

    @Autowired
    private IInventoryViewService iInventoryViewService;

    public static void main(String[] args) {
        String userMoblie = "7894561238";
        // String slInfoUrl = SystemServerManager.BuyersReportServerManager.getFindSlcodeAndDisList();
        String slInfoUrl = "http://10.20.16.140:8099/msk-bs/api/bs/findSlcodeAndDisList";
        ISO152411RsParamBean iso152411RsParamBean = new ISO152411RsParamBean();
        List<ISO152411RsBean> iso152411RsBeanList = new ArrayList<ISO152411RsBean>();
        ISO152411RsBean iso152411RsBean = new ISO152411RsBean();
        iso152411RsBean.setSlTel(userMoblie);
        iso152411RsBeanList.add(iso152411RsBean);
        iso152411RsParamBean.setUserAccountList(iso152411RsBeanList);

        RestRequest rsRequest = new RestRequest();
        rsRequest.setAuth("MSK00001");
        rsRequest.setLoginId("msk01");
        rsRequest.setSiteCode("1");
        rsRequest.setParam(iso152411RsParamBean);
        RestResponse<ISO152411RsReslutBean> rsResponse = RestClientUtil.post(slInfoUrl, rsRequest,
            new TypeReference<RestResponse<ISO152411RsReslutBean>>() {});
        String slCode = rsResponse.getResult().getSlList().get(0).getSlCode();
        System.out.println(slCode);

    }

    @Transactional
    public List<ISO152411ResultBean> getProdBySlType(ISO152411ParamBean sqlBean) {

        if (sqlBean.getPlatformType() == null || sqlBean.getPlatformType().equals("")) {
            throw new BusinessException("平台类型不能为空");
        }
        if (sqlBean.getSellerCode() == null || sqlBean.getSellerCode().equals("")) {
            throw new BusinessException("卖家ID不能为空");
        }
        if (sqlBean.getDistrictCode() == null || sqlBean.getDistrictCode().equals("")) {
            throw new BusinessException("物流区域编码不能为空");
        }
        if (sqlBean.getSellerType() == null || sqlBean.getSellerType().equals("")) {
            throw new BusinessException("卖家类型不能为空");
        }

        List<ISO152411ResultBean> resultList = new ArrayList<ISO152411ResultBean>();// 返回结果集
        IvmInventoryByProdBean params = new IvmInventoryByProdBean();// 参数对象
        InventoryViewBean ivBean = new InventoryViewBean();

        if (sqlBean.getSellerType() == 1) {// 平台查询
            params = setParams(params, sqlBean);
            ivBean.setLogicArea(params.getLogicCode());
            ivBean.setPlatform(params.getPlatform());
            ivBean.setSlType(params.getSlType());
            ivBean.setSlId(params.getSlId());
            ivBean.setPdCode(params.getPdCode());
            ivBean.setOwnerCode(null);
            List<InventoryViewBean> ivBeanList = iInventoryViewService.queryMaxAvQtyPdListByCondition(ivBean);
            resultList.add(getResult(ivBeanList,params));
        } else if (sqlBean.getSellerType() == 2) {// 卖家查询
            params = setParams(params, sqlBean);
            ivBean.setLogicArea(params.getLogicCode());
            ivBean.setPlatform(params.getPlatform());
            ivBean.setSlType(params.getSlType());
            ivBean.setSlId(params.getSlId());
            ivBean.setPdCode(params.getPdCode());
            ivBean.setOwnerCode(sqlBean.getSellerCode());
            List<InventoryViewBean> ivBeanList = iInventoryViewService.queryMaxAvQtyPdListByCondition(ivBean);
            resultList.add(getResult(ivBeanList,params));
        } else {
            throw new BusinessException("卖家类型不符合规范");
        }
        return resultList;
    }

    public IvmInventoryByProdBean setParams(IvmInventoryByProdBean params, ISO152411ParamBean sqlBean) {
        String userMoblie = sqlBean.getUserMoblie();
        String slCode = "";
        if (userMoblie != null || !userMoblie.equals("")) {
       // String slInfoUrl = SystemServerManager.BuyersReportServerManager.getFindBrByPoolFileInfo();
        String slInfoUrl ="http://10.20.16.140:8099/msk-bs/api/bs/findSlcodeAndDisList";
        ISO152411RsParamBean iso152411RsParamBean = new ISO152411RsParamBean();
        List<ISO152411RsBean> iso152411RsBeanList = new ArrayList<ISO152411RsBean>();
        ISO152411RsBean iso152411RsBean = new ISO152411RsBean();
        iso152411RsBean.setSlTel(userMoblie);
        iso152411RsBeanList.add(iso152411RsBean);
        iso152411RsParamBean.setUserAccountList(iso152411RsBeanList);
        RestRequest rsRequest = new RestRequest();
        rsRequest.setAuth("MSK00001");
        rsRequest.setLoginId("msk01");
        rsRequest.setSiteCode("1");
        rsRequest.setParam(iso152411RsParamBean);
        RestResponse<ISO152411RsReslutBean> rsResponse = RestClientUtil.post(slInfoUrl, rsRequest,
            new TypeReference<RestResponse<ISO152411RsReslutBean>>() {});
        if (rsResponse != null) {
                if (rsResponse.getResult().getSlList() != null) {
                    slCode = ","+rsResponse.getResult().getSlList().get(0).getSlCode();
        }
            }
        }
        if (slCode != "") {
            params.setSlId("0000000" + slCode);// 卖家ID;
        } else {
            params.setSlId("0000000");// 卖家ID
        }
        params.setSlType(sqlBean.getSellerType() + "");// 卖家类型
        params.setPlatform(sqlBean.getPlatformType());// 平台类型
        params.setLogicCode(sqlBean.getDistrictCode() + "");// 区域编码
        params.setPdCode(sqlBean.getPdCode());
        return params;
    }

    public ISO152411ResultBean getResult(List<InventoryViewBean> ivBeanList,IvmInventoryByProdBean params) {
        ISO152411ResultBean resultBean = new ISO152411ResultBean();
        if (ivBeanList.size() > 0) {
            resultBean.setSellerCode(params.getSlId());
            resultBean.setDistrictCode(params.getLogicCode());
            List<ISO152411ResultBean.Products> productsList = new ArrayList<ISO152411ResultBean.Products>();
            for (int i = 0; i < ivBeanList.size(); i++) {
                ISO152411ResultBean.Products products = new ISO152411ResultBean().new Products();
                products.setPdCode(ivBeanList.get(i).getPdCode());
                products.setStockCnt(ivBeanList.get(i).getAvailableQty());
                products.setOnhandQty(ivBeanList.get(i).getOnhandQty());
                products.setAllocatedQty(ivBeanList.get(i).getAllocatedQty());
                productsList.add(products);
            }
            resultBean.setProducts(productsList);
        } else {
            throw new BusinessException("无可用库存");
        }
        return resultBean;
    }
}
