package com.msk.district.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.district.bean.*;
import com.msk.district.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by liu_yan2 on 2016/4/27.
 */
@RestController
public class DistrictRsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(DistrictRsController.class);

    @Autowired
    private AreaLogic areaLogic;
    @Autowired
    private ProvinceLogic provinceLogic;
    @Autowired
    private LgcsAreaLogic lgcsAreaLogic;
    @Autowired
    private CityLogic cityLogic;
    @Autowired
    private DistrictLogic districtLogic;

    /** 0代表加载，1-代表不加载 */
    private final int LOAD = 0;
//    /** flag 为0 城市和区县包含省名称和省code*/
//    private final int FLAG_PROVINCE = 1;
//    /** flag 为1 城市和区县包含物流区名称和物流区code */
//    private final int FLAG_LGCSAREA = 0;
    /** 索引 */
    private final int firstIndex = 0;

    @RequestMapping(value = "/district/query/{interface}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<DistrictResult> getDistricts (@RequestBody RsRequest<DistrictParam> request, @PathVariable("interface") String type)
    {
        DistrictParam param = request.getParam();
        DistrictResult result = new DistrictResult();
        String failMsg = null;
        switch (type){
            // 查询大区信息
            case "area":
                failMsg = checkAreaParams(param);
                if (!StringUtil.isNullOrEmpty(failMsg)) {
                    return getFailRsResponse(failMsg);
                }
                List<AreaBean> areaList = this.areaLogic.getArea(param);
                result.setAreaList(areaList);

                if (param.getIsLoadProvince() == LOAD) {
                    loadProvince(param, result);
                }
                if (param.getIsLoadLgcsArea() == LOAD) {
                    loadLgcsAreaList(param, result);
                }
                return getSuccessRsResponse(result);
            // 查询物流区信息
            case "lgcsArea":
                failMsg = checkLgcsAreaParams(param);
                if (!StringUtil.isNullOrEmpty(failMsg)) {
                    return getFailRsResponse(failMsg);
                }
                List<LgcsAreaBean> lgcsAreaList = this.lgcsAreaLogic.getLgcsArea(param);
                result.setLgcsAreaList(lgcsAreaList);

                if (param.getIsLoadCity() == LOAD) {
                    loadCity(param, result);
                }
                return getSuccessRsResponse(result);
            // 查询省份信息
            case "province":
                failMsg = checkProvinceParams(param);
                if (!StringUtil.isNullOrEmpty(failMsg)) {
                    return getFailRsResponse(failMsg);
                }
                List<ProvinceBean> provinceList = this.provinceLogic.getProvince(param);
                result.setProvinceList(provinceList);

                if (param.getIsLoadCity() == LOAD) {
                    loadCity(param, result);
                }

                return getSuccessRsResponse(result);
            // 查询城市信息
            case "city":
                List<CityBean> cityList = this.cityLogic.getCity(param);
                result.setCityList(cityList);

                if (!checkIsEmptyArray(param.getCityCodes())){
                    return getSuccessRsResponse(result);
                }
                if (param.getIsLoadDistrict() == LOAD) {
                    loadDistrict(param, cityList);
                }
                return getSuccessRsResponse(result);
            // 查询区县信息
            case "district":

                failMsg = checkDistrictParams(param);
                if (!StringUtil.isNullOrEmpty(failMsg)) {
                    return getFailRsResponse(failMsg);
                }
                List<DistrictBean> districtList = this.districtLogic.getDistrict(param);
                result.setDistrictList(districtList);
                return getSuccessRsResponse(result);
            // 查询省行政区规划信息
            case "region":
                if (checkIsEmptyArray(param.getComposeCodes())){
                    return getFailRsResponse("缺少必要参数");
                }
                List<DistrictBean> fullNameList = this.districtLogic.findFullNameList(param);
                result.setDistrictList(fullNameList);
                return getSuccessRsResponse(result);
            // 根据行政区名称查询对应Code
            case "getCodesFromNames":
                List<DistrictBean> regionList = this.districtLogic.getCodesFromNames(param);
                result.setDistrictList(regionList);
                return getSuccessRsResponse(result);
            default:
                throw new BusinessException("接口参数不正确");
        }
    }

    /**
     * 验证大区接口参数
     * @param param
     * @return
     */
    private String checkAreaParams(DistrictParam param) {
        String[] areaCodes = param.getAreaCodes();
        String areaCode = param.getAreaCode();
        int isLoadProvince = param.getIsLoadProvince();
        int isLoadLgcsArea = param.getIsLoadLgcsArea();

        if (!checkIsEmptyArray(areaCodes) && !StringUtil.isNullOrEmpty(areaCode)) {
            return "areaCodes和areaCode不能同时存在";
        }

        if (!checkIsEmptyArray(areaCodes) && (isLoadLgcsArea == LOAD || isLoadProvince == LOAD) ) {
            return "不支持多个大区编码加载子物流区列表或省物流区列表";
        }
        return null;
    }

    /**
     * 验证省接口参数
     * @param param
     * @return
     */
    private String checkProvinceParams(DistrictParam param) {
        String[] provinceCodes = param.getProvinceCodes();
        String provinceCode = param.getProvinceCode();
        int isLoadCity = param.getIsLoadCity();
        int isLoadDistrict = param.getIsLoadDistrict();

        if (!checkIsEmptyArray(provinceCodes) && !StringUtil.isNullOrEmpty(provinceCode)) {
            return "provinceCodes和provinceCode不能同时存在";
        }

        if (!checkIsEmptyArray(provinceCodes) && (isLoadCity == LOAD || isLoadDistrict == LOAD) ) {
            return "不支持多个省编码加载城市或区县列表";
        }

        if (isLoadCity != LOAD && isLoadDistrict == LOAD) {
            return "必须加载城市，才能加载区县";
        }
        return null;
    }

    /**
     * 验证物流区接口参数
     * @param param
     * @return
     */
    private String checkLgcsAreaParams(DistrictParam param) {
        String[] lgcsAreaCodes = param.getLgcsAreaCodes();
        String lgcsAreaCode = param.getLgcsAreaCode();
        int isLoadCity = param.getIsLoadCity();
        int isLoadDistrict = param.getIsLoadDistrict();

        if (!checkIsEmptyArray(lgcsAreaCodes) && !StringUtil.isNullOrEmpty(lgcsAreaCode)) {
            return "lgcsAreaCodes和lgcsAreaCode不能同时存在";
        }

        if (!checkIsEmptyArray(lgcsAreaCodes) && (isLoadCity == LOAD || isLoadDistrict == LOAD) ) {
            return "不支持多个物流区编码加载城市或区县列表";
        }

        if (isLoadCity != LOAD && isLoadDistrict == LOAD) {
            return "必须加载城市，才能加载区县";
        }
        return null;
    }

    /**
     * 验证区县接口参数
     * @param param
     * @return
     */
    private String checkDistrictParams(DistrictParam param) {
        String[] composeCodes = param.getComposeCodes();
        String cityCode = param.getCityCode();
        if (!checkIsEmptyArray(composeCodes) && !StringUtil.isNullOrEmpty(cityCode)) {
            return "composeCodes和cityCode不能同时存在";
        }
        if (checkIsEmptyArray(composeCodes) && StringUtil.isNullOrEmpty(cityCode)){
            return "缺少城市编码";
        }
        return null;
    }

    /**根据已有大区编码，或默认第一个大区编码，查询子省份*/
    private DistrictResult loadProvince(DistrictParam param, DistrictResult result) {
        if (!CollectionUtils.isEmpty(result.getAreaList())) {
            String areaCode = param.getAreaCode();
            if (StringUtil.isNullOrEmpty(areaCode)) {
                areaCode = result.getAreaList().get(firstIndex).getAreaCode();
                param.setAreaCode(areaCode);
            }
            List<ProvinceBean> provinceList = provinceLogic.getProvince(param);
            for(AreaBean bean: result.getAreaList() ) {
                if (areaCode.equals(bean.getAreaCode())) {
                    bean.setProvinceList(provinceList);
                    return result;
                }
            }
        }
        return result;
    }

    /**根据已有大区编码，或默认第一个大区编码，查询子物流区*/
    private DistrictResult loadLgcsAreaList(DistrictParam param, DistrictResult result) {
        if (!CollectionUtils.isEmpty(result.getAreaList())) {
            String areaCode = param.getAreaCode();
            if (StringUtil.isNullOrEmpty(areaCode)) {
                areaCode = result.getAreaList().get(firstIndex).getAreaCode();
                param.setAreaCode(areaCode);
            }
            List<LgcsAreaBean> lgcsAreaList = lgcsAreaLogic.getLgcsArea(param);
            for(AreaBean bean: result.getAreaList() ) {
                if (areaCode.equals(bean.getAreaCode())) {
                    bean.setLgcsAreaList(lgcsAreaList);
                    return result;
                }
            }
        }
        return result;
    }

    /**根据已有物流区或省编码，或默认第一个大区或省编码，查询子城市*/
    private DistrictResult loadCity(DistrictParam param, DistrictResult result) {

        if (!CollectionUtils.isEmpty(result.getProvinceList())) {
            String provinceCode = param.getProvinceCode();
            if (StringUtil.isNullOrEmpty(provinceCode)) {
                provinceCode = result.getProvinceList().get(firstIndex).getProvinceCode();
                param.setProvinceCode(provinceCode);
            }
            List<CityBean> cityList = cityLogic.getCity(param);
            for(ProvinceBean bean: result.getProvinceList() ) {
                if (provinceCode.equals(bean.getProvinceCode())) {
                    if (param.getIsLoadDistrict() == LOAD) {
                        cityList = loadDistrict(param, cityList);
                    }
                    bean.setCityList(cityList);
                    return result;
                }
            }
            return result;
        }

        if (!CollectionUtils.isEmpty(result.getLgcsAreaList())) {
            String lgcsAreaCode = param.getLgcsAreaCode();
            if (StringUtil.isNullOrEmpty(lgcsAreaCode)) {
                lgcsAreaCode = result.getLgcsAreaList().get(firstIndex).getLgcsAreaCode();
                param.setLgcsAreaCode(lgcsAreaCode);
            }
            List<CityBean> cityList = cityLogic.getCity(param);
            for(LgcsAreaBean bean: result.getLgcsAreaList() ) {
                if (lgcsAreaCode.equals(bean.getLgcsAreaCode())) {
                    if (param.getIsLoadDistrict() == LOAD) {
                        cityList = loadDistrict(param, cityList);
                    }
                    bean.setCityList(cityList);
                    return result;
                }
            }
            return result;
        }
        return result;
    }

    /**根据已有城市编码，或默认第一个城市编码，查询子区县*/
    private List<CityBean> loadDistrict(DistrictParam param, List<CityBean> cityList) {
        if (CollectionUtils.isEmpty(cityList)) {
            return cityList;
        }
        String cityCode = param.getCityCode();
        if (StringUtil.isNullOrEmpty(cityCode)) {
            cityCode = cityList.get(firstIndex).getCityCode();
            param.setCityCode(cityCode);
        }
        List<DistrictBean> districtList = districtLogic.getDistrict(param);
        for(CityBean bean: cityList ) {
            if (cityCode.equals(bean.getCityCode())){
                bean.setDistrictList(districtList);
                return cityList;
            }
        }
        return cityList;
    }

    /**
     * 验证是数组否为空或null
     * @return
     */
    private boolean checkIsEmptyArray(String[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        return false;
    }

    private RsResponse<DistrictResult> getSuccessRsResponse(DistrictResult result) {
        RsResponse<DistrictResult> response = new RsResponse<DistrictResult>();
        response.setResult(result);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }

    private RsResponse<DistrictResult> getFailRsResponse(String message) {
        RsResponse<DistrictResult> response = new RsResponse<DistrictResult>();
        response.setStatus(SystemConst.RsStatus.FAIL);
        response.setMessage(message);
        response.setReturnCode("F000001");
        return response;
    }
}
