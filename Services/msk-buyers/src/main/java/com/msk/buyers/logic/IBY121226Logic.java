package com.msk.buyers.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 查询物流区 城市 区县
 * Created by tao_zhifa on 2016/7/13.
 */
@Service
public class IBY121226Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121226Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 省份下拉菜单
     * @param
     * @return
     */
    public RsResponse<DistrictResult> getIBYProvinceBeanList(DistrictParam param){
        param = (null == param) ? new DistrictParam() : param;
        RsRequest<DistrictParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryProvinceService();
        RsResponse<DistrictResult> pro =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });

        if (pro.getResult() !=null){
            return pro;
        }else{
            return new RsResponse();
        }
    }

    /**
     * 获取物流区下拉框信息
     * @param param
     * @return
     */
    public RsResponse<DistrictResult> getIBYLogisticsAreaList(DistrictParam param){
        RsRequest<DistrictParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8780/msk-district/api/district/query/lgcsArea";
        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<DistrictResult> lgcsArea =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });

        if (lgcsArea.getResult() !=null){
            return lgcsArea;
        }else{
            return new RsResponse();
        }
    }

    /**
     * 获取城市下拉信息
     * @param param
     * @return
     */
    public RsResponse<DistrictResult> getIBYCityList(DistrictParam param){
        RsRequest<DistrictParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8780/msk-district/api/district/query/city";
        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryCityService();
        RsResponse<DistrictResult> city =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });

        if (city.getResult() !=null){
            return city;
        }else{
            return new RsResponse();
        }
    }

    /**
     * 获取区县下拉信息
     * @param param
     * @return
     */
    public RsResponse<DistrictResult> getIBYAreaList(DistrictParam param){
        RsRequest<DistrictParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8780/msk-district/api/district/query/district";
        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryDistrictService();
        RsResponse<DistrictResult> districtList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });

        if (districtList.getResult() !=null){
            return districtList;
        }else{
            return new RsResponse();
        }
    }


    public RsResponse<DistrictResult> getIBYCodesFromNames(DistrictParam param){
        RsRequest<DistrictParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8780/msk-district/api/district/query/getCodesFromNames";
        String url =  SystemServerManager.DistrictServerManage.getGetCodesFromNames();
        RsResponse<DistrictResult> districtList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });

        if (districtList.getResult() !=null){
            return districtList;
        }else{
            return new RsResponse();
        }
    }
}
