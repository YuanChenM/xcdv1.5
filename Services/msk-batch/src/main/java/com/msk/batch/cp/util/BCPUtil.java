package com.msk.batch.cp.util;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.msk.batch.cp.bean.BCP153102Bean;
import com.msk.batch.cp.bean.BCP153102Param;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhang_chi on 2016/8/18.
 */
public class BCPUtil {


    /**
     * 接口查询卖家信息
     * @return
     */
    public static List<BCP153102Bean> getSellerData() {
        // 设置请求参数
        RsRequest<BCP153102Param> requestParam = new RsRequest<BCP153102Param>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        requestParam.setParam(null);
        // url
        // String url = "http://127.0.0.1:8080/msk-seller/api/sl/slInfo/slBaseInfo/search";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlBaseInfoSearchServices();
        // 请求接口
        RsResponse<BCP153102Bean[]> response = RestClientUtil.post(url, requestParam,
                new TypeReference<RsResponse<BCP153102Bean[]>>() {
                });
        if (null != response.getResult() && "S".equals(response.getStatus())) {
            List<BCP153102Bean> paramList = Arrays.asList(response.getResult());
            return  paramList;
        }else {
            return new ArrayList<BCP153102Bean>();
        }
    }

    /**
     * 接口查询买手信息
     * @return
     */
    public static List<BCP153102Bean> getBuyerData() {
        // 设置请求参数
        RsRequest<BCP153102Param> requestParam = new RsRequest<BCP153102Param>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        BCP153102Param bcp153102Param = new BCP153102Param();
        // 0:注册了账号的所有买手 1：账号和基本信息都注册了的买手
        bcp153102Param.setSlCodeFlag(1);
        requestParam.setParam(bcp153102Param);
        // url
        //String url = "http://10.20.16.101:8080/api/bs/bsInfo/_search";
        String url = SystemServerManager.BsServerManage.getSearchBsInfo();
        // 请求接口
        RsResponse<BCP153102Bean> response = RestClientUtil.post(url, requestParam,
                new TypeReference<RsResponse<BCP153102Bean>>() {
                });
        if (null != response.getResult() && "S".equals(response.getStatus())) {
            List<BCP153102Bean> paramList = response.getResult().getBuyershopList();
            if(CollectionUtils.isNotEmpty(paramList)){
                return  paramList;
            }else {
                return new ArrayList<BCP153102Bean>();
            }
        }else {
            return new ArrayList<BCP153102Bean>();
        }
    }


}
