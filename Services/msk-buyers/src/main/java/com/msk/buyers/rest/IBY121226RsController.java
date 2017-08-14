package com.msk.buyers.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.logic.IBY121226Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.district.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao_zhifa on 2016/7/13.
 */
@RestController
public class IBY121226RsController extends BaseRsController{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121226RsController.class);

    @Autowired
    private IBY121226Logic iby121226Logic;

    /**
     * 查询省
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/getProvinceBean",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<DistrictResult> getProvinceBean(@RequestBody RsRequest<DistrictParam> param){
        RsResponse<DistrictResult> rsResponse = new RsResponse<>();
        DistrictResult rsResult = new DistrictResult();
        List<ProvinceBean> proviceBean = new ArrayList<ProvinceBean>();
        proviceBean = iby121226Logic.getIBYProvinceBeanList(null).getResult().getProvinceList();
        rsResult.setProvinceList(proviceBean);
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        rsResponse.setMessage("查询物流区成功!");
        rsResponse.setResult(rsResult);
        return rsResponse;
    }


    /**
     * 查询物流区
     *
     * @param param param
     * @return 结果
     */
    @RequestMapping(value = "/by/getLogisticsAreas",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<DistrictResult> getLogisticsAreas(@RequestBody RsRequest<DistrictParam> param){
        RsResponse<DistrictResult> rsResponse = new RsResponse<>();
        DistrictParam districtParam = new DistrictParam();
        districtParam.setAreaCode(param.getParam().getAreaCode());
        districtParam.setFlag(NumberConst.IntDef.INT_ZERO);
        List<LgcsAreaBean> Logisticslist = new ArrayList<LgcsAreaBean>();
        DistrictResult rsResult = new DistrictResult();
        if (StringUtil.isNullOrEmpty(param.getParam().getAreaCode())){
            logger.info("查询所有大区中的物流区");
            Logisticslist = iby121226Logic.getIBYLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        }else{
            logger.info("查询指定大区中的物流区");
            Logisticslist = iby121226Logic.getIBYLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        }
        rsResult.setLgcsAreaList(Logisticslist);
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        rsResponse.setMessage("查询物流区成功!");
        rsResponse.setResult(rsResult);
        return rsResponse;
    }


    /**
     * 查询城市
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/getCitys",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<DistrictResult> getCitys(@RequestBody RsRequest<DistrictParam> param){
        RsResponse<DistrictResult> rsResponse = new RsResponse<>();
        DistrictParam districtParam = new DistrictParam();
        districtParam.setLgcsAreaCode(param.getParam().getLgcsAreaCode());
        districtParam.setProvinceCode(param.getParam().getProvinceCode());
        districtParam.setFlag(param.getParam().getFlag());
        districtParam.setIsLoadProvince(NumberConst.IntDef.INT_ZERO);
        districtParam.setIsLoadCity(NumberConst.IntDef.INT_ZERO);

        List<CityBean> citylist = new ArrayList<CityBean>();
        DistrictResult rsResult = new DistrictResult();
        citylist = iby121226Logic.getIBYCityList(districtParam).getResult().getCityList();
        rsResult.setCityList(citylist);
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        rsResponse.setMessage("查询物流区成功!");
        rsResponse.setResult(rsResult);
        return rsResponse;
    }

    /**
     * 查询区县
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/getDistricts",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<DistrictResult> getDistricts(@RequestBody RsRequest<DistrictParam> param){
        RsResponse<DistrictResult> rsResponse = new RsResponse<>();
        DistrictParam districtParam = new DistrictParam();
        districtParam.setCityCode(param.getParam().getCityCode());
        districtParam.setIsLoadCity(NumberConst.IntDef.INT_ZERO);
        districtParam.setIsLoadDistrict(NumberConst.IntDef.INT_ZERO);
        List<DistrictBean> Districtslist = new ArrayList<>();
        DistrictResult rsResult = new DistrictResult();
        if (StringUtil.isNullOrEmpty(param.getParam().getAreaCode())){
            logger.info("查询所有区域");
            Districtslist = iby121226Logic.getIBYAreaList(districtParam).getResult().getDistrictList();
        }else{
            logger.info("查询指定城市中的区域");
            Districtslist = iby121226Logic.getIBYAreaList(districtParam).getResult().getDistrictList();
        }
        rsResult.setDistrictList(Districtslist);
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        rsResponse.setMessage("查询物流区成功!");
        rsResponse.setResult(rsResult);
        return rsResponse;
    }




    /**
     * 根据定位获取当前位置
     * 然后获取当前位置属于的物流区
     * @param
     * @return
     */
    @RequestMapping(value = "/by/getCodesFromNames",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<DistrictResult> localPosition(@RequestBody RsRequest<DistrictParam>rsrequest){
        RsResponse<DistrictResult> rs = new RsResponse<>();
        DistrictParam param = new DistrictParam();
        param.setCityName(rsrequest.getParam().getCityName());
        param.setDistrictName(rsrequest.getParam().getDistrictName());
        rs.setResult(iby121226Logic.getIBYCodesFromNames(param).getResult());
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        return rs;
    }
}
