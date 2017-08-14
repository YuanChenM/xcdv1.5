package com.msk.batch.ds.utils;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import com.msk.seller.bean.SlPdArtnoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yang_yang
 */
public class RestUtil {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(RestUtil.class);

    /**
     * 获取产品信息
     * @param param
     * @return
     */
    public static PDInfoResult getProductInfo(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //TODO
        //10.20.16.152:8888
        //String url = "http://localhost:8088/msk-product/api/findProductInfo";
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdInfoSearchService();
        RsResponse<PDInfoResult> pdInfoResult = RestClientUtil.post(url, request, new TypeReference<RsResponse<PDInfoResult>>() {});

        PDInfoResult product = pdInfoResult.getResult();
        if (product == null) {
            logger.info("产品编号:" + param.getPdCode() + "没有相关产品");
        }

        logger.info("产品接口信息调取成功");

        return product;
    }

    /**
     * 获取产品包装信息
     * @param param
     * @return
     */
    public static List<PDInfoResult> getPdNormsStd(PDInfoParam param){
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //TODO
        //String url = "http://localhost:8088/msk-product/api/findProductPackage";
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdPackageInfoSearchServices();

        RsResponse<ProductBeanResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {});

        if(null != response.getResult()) {
            logger.info("产品包装信息接口调取成功");
            ProductBeanResult productBeanResult = response.getResult();
            List<PDInfoResult> list = productBeanResult.getResult();
            return list;
        }
        return null;

    }

    /**
     * 批量查询卖家企业信息接口
     * @param param
     * @return
     */
    public static List<ISL231193RsResult> getEpDateList(ISL231193RsParam param){
        logger.info("调用批量查询卖家企业信息接口");
        RsRequest<ISL231193RsParam> request = new RsRequest<ISL231193RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //TODO
        //String url = "http://localhost:8081/msk-seller/api/sl/slInfo/slEpDataList/search";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlEpDataListSearchServices();
        RsResponse<ISL231193RsResult> rs =
                RestClientUtil.post(url,request,new TypeReference<RsResponse<ISL231193RsResult>>(){
                });
        List<ISL231193RsResult> list = rs.getResult().getIsl231193RsList();
        logger.info("调用批量查询卖家企业信息接口结束");
        return list;
    }

}
