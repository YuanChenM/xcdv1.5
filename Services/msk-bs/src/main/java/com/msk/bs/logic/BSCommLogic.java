package com.msk.bs.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.MdCity;
import com.msk.core.entity.MdDistrict;
import com.msk.core.entity.MdProvince;
import com.msk.district.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 省市区联动CommLogic
 */
public class BSCommLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }


    /**
     * 查询全部省份，用于联动
     * @param baseParam 参数为空
     * @return 返回省全部
     */
    @Transactional(readOnly = true)
    public List<MdProvince> findMdProvinces(BaseParam baseParam) {
        List<MdProvince> list = new ArrayList<>();
        DistrictParam districtParam = new DistrictParam();
        RsResponse<DistrictResult> result = getProvinceList(districtParam);
        List<ProvinceBean> proList = result.getResult().getProvinceList();
        for(ProvinceBean bean : proList){
            MdProvince pro = new MdProvince();
            pro.setProvinceCode(bean.getProvinceCode());
            pro.setProvinceName(bean.getProvinceName());
            list.add(pro);
        }
        return list;
    }

    /**
     * 根据市Code查询所有的区县
     * @param cityCode 市Code
     * @return 该市Code对应下的所有的区 县
     */
    @Transactional(readOnly = true)
    public List<MdDistrict> findDistrictList(String cityCode) {
        if (StringUtil.isNullOrEmpty(cityCode)) {
            return new ArrayList<MdDistrict>();
        }
        DistrictParam districtParam = new DistrictParam();
        districtParam.setCityCode(cityCode);
        List<MdDistrict>  mdDistrictList=new ArrayList<>();
        RsResponse<DistrictResult> result =getDistrictList(districtParam);
        List<DistrictBean> districtBeans=result.getResult().getDistrictList();
        for(DistrictBean bean:districtBeans){
            MdDistrict mdDistrict=new MdDistrict();
            mdDistrict.setDistrictCode(bean.getDistrictCode());
            mdDistrict.setDistrictName(bean.getDistrictName());
            mdDistrictList.add(mdDistrict);
        }
        return mdDistrictList;
    }


    /**
     * 根据省Code查询地市全部，用于联动
     * @param provinceCode
     * @return 地市全部
     */
    @Transactional(readOnly = true)
    public List<MdCity> findCityList(String provinceCode) {
        if (StringUtil.isNullOrEmpty(provinceCode)) {
            return new ArrayList<MdCity>();
        }
        DistrictParam districtParam = new DistrictParam();
        districtParam.setProvinceCode(provinceCode);
        List<MdCity>  mdCityList = new ArrayList<>();
        RsResponse<DistrictResult> result = getCityList(districtParam);
        List<CityBean> cityBeans=result.getResult().getCityList();
        for(CityBean bean : cityBeans){
            MdCity mdCity = new MdCity();
            mdCity.setCityCode(bean.getCityCode());
            mdCity.setCityName(bean.getCityName());
            mdCityList.add(mdCity);
        }
        return mdCityList;
    }




    /**根据省Code查询省*/
    @Transactional(readOnly = true)
    public MdProvince findProvince(String provinceCode) {
        MdProvince mdProvince = new MdProvince();
        DistrictParam districtParam = new DistrictParam();
        districtParam.setProvinceCode(provinceCode);
        RsResponse<DistrictResult> result = getProvinceList(districtParam);
        List<CityBean> cityBeans = result.getResult().getCityList();
        for(CityBean bean : cityBeans){
            mdProvince.setAreaId(bean.getLgcsAreaId());
            mdProvince.setProvinceName(bean.getProvinceName());
            mdProvince.setProvinceId(bean.getProvinceId());
        }
        return mdProvince;
    }
    /**
     * 根据areaId 查询大区编码
     * @param areaId 大区ID
     * @return 返回大区编码
     */
    /**
    public String findAreaCode(Long areaId) {
        BaseParam base = new BaseParam();
        base.setFilter("areaId",areaId.toString());
        MdArea mdArea = super.findOne(SqlId.SQL_ID_FINDMDAREA,base);
       return mdArea.getAreaCode();
    }
     */

    /**
     * 根据市cityCode查询市
     * @param cityCode 市Code
     * @return MdCity
     */
    @Transactional(readOnly = true)
    public MdCity findCity(String cityCode) {
        MdCity mdCity = new MdCity();
        DistrictParam districtParam = new DistrictParam();
        String[] cityCodes = new String[]{cityCode};
        districtParam.setCityCodes(cityCodes);
        districtParam.setFlag(0);
        RsResponse<DistrictResult> result = getCityList(districtParam);
        List<CityBean> cityBeans=result.getResult().getCityList();
        for(CityBean bean : cityBeans){
            mdCity.setLgcsAreaId(bean.getLgcsAreaId());
            mdCity.setCityId(bean.getCityId());
            mdCity.setCityName(bean.getCityName());
        }
        return mdCity;
    }

    /**
     * 根据区县Code查询区县
     * @param cityCode
     * @return MdCity
     */
    @Transactional(readOnly = true)
    public MdDistrict findDistrict(String cityCode) {
        MdDistrict mdDistrict = new MdDistrict();
        DistrictParam districtParam = new DistrictParam();
        districtParam.setCityCode(cityCode);
        RsResponse<DistrictResult> result = getDistrictList(districtParam);
        List<DistrictBean> districtBeans=result.getResult().getDistrictList();
        for(DistrictBean bean : districtBeans){
            mdDistrict.setDistrictId(bean.getDistrictId());
            mdDistrict.setDistrictCode(bean.getDistrictCode());
            mdDistrict.setDistrictName(bean.getDistrictName());
        }
        return mdDistrict;
    }


    /**
     * 查询所有的省信息、指定省中的城市信息(接口)
     * @return
     */
    public static RsResponse<DistrictResult> getProvinceList(DistrictParam districtParam){
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(districtParam);
        String url = ConfigManager.getMskDistrictService()+ConfigManager.getDistrictQueryProvinceService();
        RsResponse<DistrictResult> provinceList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        return  provinceList;
    }

    /**
     * 获取页面下拉框中的城市集合(接口)
     *
     * @param districtParam
     * @return
     */
    public static RsResponse<DistrictResult> getCityList(DistrictParam districtParam){
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(districtParam);
        // "http://localhost:8080/msk-district/api/district/query/city"
        String url = ConfigManager.getMskDistrictService()+ConfigManager.getDistrictQueryCityService();
        RsResponse<DistrictResult> cityList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        return  cityList;
    }


    /**
     * 获取下拉框区县集合（接口）
     *
     * @param districtParam
     * @return
     */
    public RsResponse<DistrictResult> getDistrictList(DistrictParam districtParam) {
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(districtParam);
        // "http://localhost:8080/msk-district/api/district/query/district"
        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryDistrictService();
        RsResponse<DistrictResult> districtList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        return districtList;
    }

    /**
     * 获取物流区下拉框数据接口（接口）
     *
     * @param districtParam
     * @return
     */
    public RsResponse<DistrictResult> getLogisticsAreaList(DistrictParam districtParam) {
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId(districtParam.getActId());
        request.setSiteCode("1");
        request.setParam(districtParam);
        // "http://localhost:8080/msk-district/api/district/query/lgcsArea"
        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<DistrictResult> logisticsAreaList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        return logisticsAreaList;
    }


}
