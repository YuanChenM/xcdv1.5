package com.msk.ssc.rest.common;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.ssc.bean.SSC11311Param;
import com.msk.ssc.bean.SSC11311Result;
import com.msk.stock.bean.StockResult;
import com.msk.stock.bean.StockRsParamList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu_yan2 on 2016/7/12.
 */
public class SSCRestUtil {

    private static Logger logger = LoggerFactory.getLogger(SSCRestUtil.class);

    /**
     * 修改销售库存
     * @param param
     * @return
     */
    public static RsResponse<StockResult> saveStockOfSupplierList(StockRsParamList param){
        RsRequest<StockRsParamList> request = new RsRequest<StockRsParamList>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //String url = "http://localhost:8080/msk-so-stock/api/so/saveStockOfSupplierList";
        String url = SystemServerManager.SoStockServerManage.getSaveStockOfSupplierList();
        RsResponse<StockResult> stockResult = RestClientUtil.post(url, request, new TypeReference<RsResponse<StockResult>>() {
        });
        return stockResult;
    }

    /**
     * 调用接口获取产品信息以及体积
     * @param param
     * @return
     */
    public static List<PDInfoResult> getProductInfoList(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.PdServerManager.getFindProductInfos();;
        RsResponse<ProductBeanResult> rs  = RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {});
        List<PDInfoResult> res = new ArrayList<PDInfoResult>();
        if (rs.getResult() == null) {
            logger.info("产品编号没有相关产品");
        }else {
            res = rs.getResult().getResult();
            logger.info("产品接口信息调取成功");
        }
        return res;
    }
}
