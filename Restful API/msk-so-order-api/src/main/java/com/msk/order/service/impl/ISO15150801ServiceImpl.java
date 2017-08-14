package com.msk.order.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtils;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO15150801RestParam;
import com.msk.order.bean.result.SO15150801PdStockListResult;
import com.msk.order.bean.result.SO15150801ProductStockBean;
import com.msk.order.service.ISO15150801Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ISO15150801_选择画面后台IMPL
 * Created by wang_jianzhou on 2016/8/2.
 */
@Service
public class ISO15150801ServiceImpl implements ISO15150801Service {
    private static Logger logger = LoggerFactory.getLogger(ISO15150801ServiceImpl.class);
    /**
     * 获取卖家可用库存
     *
     * @return
     */
    @Transactional(readOnly = true)
    public SO15150801ProductStockBean findSellerStockPage(ISO15150801RestParam param) {
        //获取卖家可用库存
        RestRequest<ISO15150801RestParam> request = new RestRequest<ISO15150801RestParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("101");
        SO15150801PdStockListResult pdStockResult = new SO15150801PdStockListResult();
        List<SO15150801PdStockListResult> resultList = new ArrayList<SO15150801PdStockListResult>();

        if(!StringUtil.isEmpty(param.getPdCode())){
            pdStockResult.setPdCode(param.getPdCode());
        }
        if(!StringUtil.isEmpty(param.getPdName())){
            pdStockResult.setPdName(param.getPdName());
        }
        if(!StringUtil.isEmpty(param.getUnit())){
            pdStockResult.setUnit(param.getUnit());
        }

        resultList.add(pdStockResult);
        param.setProducts(resultList);
        request.setParam(param);
        //调用库存接口
        String url = SystemServerManager.SoInventoryServerManager.getFindSlProductIvList();
        RestResponse<SO15150801ProductStockBean> productSlStock =
                RestClientUtils.post(url, request, new TypeReference<RestResponse<SO15150801ProductStockBean>>() {
                });
        SO15150801ProductStockBean result = new SO15150801ProductStockBean();
        result = productSlStock.getResult();
        return result;
    }

    /**
     * 获取供应商可用库存
     *
     * @return
     */
    @Transactional(readOnly = true)
    public SO15150801ProductStockBean findSuppStockPage(ISO15150801RestParam param) {
        //获取供应商可用库存
        RestRequest<ISO15150801RestParam> request = new RestRequest<ISO15150801RestParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");

        SO15150801PdStockListResult pdStockResult = new SO15150801PdStockListResult();
        List<SO15150801PdStockListResult> resultList = new ArrayList<SO15150801PdStockListResult>();
        if(!StringUtil.isEmpty(param.getPdCode())){
            pdStockResult.setPdCode(param.getPdCode());
        }
        if(!StringUtil.isEmpty(param.getPdName())){
            pdStockResult.setPdName(param.getPdName());
        }

        resultList.add(pdStockResult);
        param.setProducts(resultList);
        request.setParam(param);
        //调用库存接口
        String url = SystemServerManager.SoInventoryServerManager.getFindSpProductIvList();//"http://localhost:8888/msk-inventory/api/inv/spProductInv/list";
        RestResponse<SO15150801ProductStockBean> productSuppStock =
                RestClientUtils.post(url, request, new TypeReference<RestResponse<SO15150801ProductStockBean>>() {
                });
        SO15150801ProductStockBean result = new SO15150801ProductStockBean();
        result = productSuppStock.getResult();
        return result;
    }

}
