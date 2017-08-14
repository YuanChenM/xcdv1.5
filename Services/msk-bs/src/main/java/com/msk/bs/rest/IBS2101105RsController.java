package com.msk.bs.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.*;
import com.msk.bs.logic.BS2102103Logic;
import com.msk.bs.logic.IBS2101105RsLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.ProvinceBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by gyh on 2016/2/14.
 * 查询冻品管家信息
 */
@RestController
@Api(description = "查询冻品管家信息RestController", tags = {"IBS2101105RsController"})
public class IBS2101105RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBS2101105RsController.class);

    @Autowired
    private IBS2101105RsLogic ibs2101105RsLogic;

    @Autowired
    private BS2102103Logic bs2102103Logic;

    /**
     * 查询冻品管家信息接口
     *
     * @param param param
     * @return rs
     */
    @ApiOperation(value = "冻品管家信息", notes = "查询冻品管家信息接口")
    @RequestMapping(value = "/bs/slInfo/houseAccount/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBS210110501RsResult> search(@RequestBody RsRequest<IBS2101105RsParam> param) {
        logger.debug("查询冻品管家信息接口");
        RsResponse<IBS210110501RsResult> rs = new RsResponse<IBS210110501RsResult>();
        IBS210110501RsResult result = new IBS210110501RsResult();
        List<IBS2101105RsResult> houseList = ibs2101105RsLogic.findPageList(param.getParam(), result);
        if (!CollectionUtils.isEmpty(houseList) && houseList.size() > 0) {
            setHouseAccountInfo(houseList);
            result.setHouseList(houseList);
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询冻品管家信息接口成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("暂不存在数据");
        }
        return rs;
    }


    private void setHouseAccountInfo(List<IBS2101105RsResult> houseList){
        for(int i=0;i<houseList.size();i++){
            setPCDName(houseList.get(i));
            setLgcs(houseList.get(i));
            setHouseType(houseList.get(i));
        }
    }

    private void setHouseType(IBS2101105RsResult param){
        BS2102103Param bs2102103Param = new BS2102103Param();
        bs2102103Param.setHouseCode(param.getHouseCode());
        bs2102103Param.setSlCode(param.getSlCode());
        bs2102103Param.setLgcsAreaName(param.getVlgcsAreaName());
        bs2102103Param.setLgcsAreaCode(param.getVlgcsAreaCode());
        bs2102103Param.setPaging(false);
        PageResult<BS2102107Bean> pageResult = bs2102103Logic.findAllHouseManage(bs2102103Param);
        List<BS2102107Bean> slHouseTypeList = pageResult.getData();
        if(!CollectionUtils.isEmpty(slHouseTypeList)){
            List<IBS2101104SlHouseAccountParam> houseTYPEList = new ArrayList<>();
            IBS2101104SlHouseAccountParam houseAccountParam = null;
            for(BS2102107Bean bs2102107Bean : slHouseTypeList){
                houseAccountParam = new IBS2101104SlHouseAccountParam();
                houseAccountParam.setHkTypeCode(bs2102107Bean.getHouseCategoryCode());
                houseAccountParam.setHkSecdTypeCode(bs2102107Bean.getHouseReclassifyCode());
                houseTYPEList.add(houseAccountParam);
            }
            param.setHouseTYPEList(houseTYPEList);
            /*Modify Bug #3592  增加判断冻品管家分类查询  begin  whc 21016.11.08*/
            //为空 默认给一个管家分类
            if(StringUtil.isNullOrEmpty(param.getHouseCategory())){
                param.setHouseCategory(slHouseTypeList.get(0).getHouseReclassifyCode());
            }
            /*Modify Bug #3592  增加判断冻品管家分类查询  end  whc 21016.11.08*/
        }
    }


    private void setLgcs(IBS2101105RsResult param){
        if(!StringUtil.isNullOrEmpty(param.getVcityName()) && !StringUtil.isNullOrEmpty(param.getVcityCode())){
            CityBean vCityBean = getLgcs(param,param.getVcityName(),param.getVcityCode());
            if(vCityBean != null){
                param.setVareaCode(vCityBean.getAreaCode());
                param.setVareaName(vCityBean.getAreaName());
                param.setVlgcsAreaName(vCityBean.getLgcsAreaName());
                param.setVlgcsAreaCode(vCityBean.getLgcsAreaCode());
            }
        }

        if(!StringUtil.isNullOrEmpty(param.getCityName()) && !StringUtil.isNullOrEmpty(param.getCityCode())){
            CityBean cityBean = getLgcs(param,param.getCityName(),param.getCityCode());
            if(cityBean != null){
                param.setAreaCode(cityBean.getAreaCode());
                param.setAreaName(cityBean.getAreaName());
                param.setLgcsAreaName(cityBean.getLgcsAreaName());
                param.setLgcsAreaCode(cityBean.getLgcsAreaCode());
            }
        }
        if(!StringUtil.isNullOrEmpty(param.getrCityName()) && !StringUtil.isNullOrEmpty(param.getRcityCode())){
            CityBean rCityBean = getLgcs(param,param.getrCityName(),param.getRcityCode());
            if(rCityBean != null){
                param.setRareaCode(rCityBean.getAreaCode());
                param.setrAreaName(rCityBean.getAreaName());
                param.setrLgcsAreaName(rCityBean.getLgcsAreaName());
                param.setRlgcsAreaCode(rCityBean.getLgcsAreaCode());
            }
        }
    }


    private CityBean getLgcs(IBS2101105RsResult param,String name,String code){
        //获取物流区信息
        DistrictParam districtParam = new DistrictParam();
        districtParam.setCityName(name);
        districtParam.setCityCodes(new String[] {code });
        districtParam.setFlag(0);
        List<CityBean> lgcsAreaBeanList = CommRestUtil.getProvinceCityList(districtParam);
        if(!CollectionUtils.isEmpty(lgcsAreaBeanList)){
            return lgcsAreaBeanList.get(0);
        }
        return null;
    }


    private void setPCDName(IBS2101105RsResult param){
        //获取虚拟地址省市区信息
        if(!StringUtil.isNullOrEmpty(param.getVprovinceCode())){
            DistrictParam districtParam = new DistrictParam();
            // 获取省份信息
            districtParam.setProvinceCode(param.getVprovinceCode());
            List<ProvinceBean> provinceBeanList = CommRestUtil.getProvinceList(districtParam);
            if(!CollectionUtils.isEmpty(provinceBeanList)){
                param.setVprovinceName(this.getPCDName(provinceBeanList,param.getVprovinceCode()));
            }
            if(!StringUtil.isNullOrEmpty(param.getVcityCode())){
                //获取城市信息
                districtParam.setCityCode(param.getVcityCode());
                List<CityBean> cityBeanList =CommRestUtil.getProvinceCityList(districtParam);
                if(!CollectionUtils.isEmpty(cityBeanList)){
                    param.setVcityName(this.getPCDName(cityBeanList,param.getVcityCode()));
                }
                if(!StringUtil.isNullOrEmpty(param.getVdistrictCode())){
                    //获取区县信息
                    districtParam.setDistrictCode(param.getVdistrictCode());
                    List<DistrictBean> districtBeanList =CommRestUtil.getDistrictList(districtParam);
                    if(!CollectionUtils.isEmpty(districtBeanList)){
                        param.setVdistrictName(this.getPCDName(districtBeanList,param.getVdistrictCode()));
                    }
                }
            }
        }
        if(!StringUtil.isNullOrEmpty(param.getRprovinceCode())){
            DistrictParam districtParam = new DistrictParam();
            // 获取省份信息
            districtParam.setProvinceCode(param.getRprovinceCode());
            List<ProvinceBean> provinceBeanList = CommRestUtil.getProvinceList(districtParam);
            if(!CollectionUtils.isEmpty(provinceBeanList)){
                param.setrProvinceName(this.getPCDName(provinceBeanList,param.getRprovinceCode()));
            }
            if(!StringUtil.isNullOrEmpty(param.getRcityCode())){
                //获取城市信息
                districtParam.setCityCode(param.getRcityCode());
                List<CityBean> cityBeanList =CommRestUtil.getProvinceCityList(districtParam);
                if(!CollectionUtils.isEmpty(cityBeanList)){
                    param.setrCityName(this.getPCDName(cityBeanList,param.getRcityCode()));
                }
                if(!StringUtil.isNullOrEmpty(param.getRdistrictCode())){
                    //获取区县信息
                    districtParam.setDistrictCode(param.getRdistrictCode());
                    List<DistrictBean> districtBeanList =CommRestUtil.getDistrictList(districtParam);
                    if(!CollectionUtils.isEmpty(districtBeanList)){
                        param.setrDistrictName(this.getPCDName(districtBeanList,param.getRdistrictCode()));
                    }
                }
            }
        }
        if(!StringUtil.isNullOrEmpty(param.getProvinceCode())){
            DistrictParam districtParam = new DistrictParam();
            // 获取省份信息
            districtParam.setProvinceCode(param.getProvinceCode());
            List<ProvinceBean> provinceBeanList = CommRestUtil.getProvinceList(districtParam);
            if(!CollectionUtils.isEmpty(provinceBeanList)){
                param.setProvinceName(this.getPCDName(provinceBeanList,param.getProvinceCode()));
            }
            if(!StringUtil.isNullOrEmpty(param.getCityCode())){
                //获取城市信息
                districtParam.setCityCode(param.getCityCode());
                List<CityBean> cityBeanList =CommRestUtil.getProvinceCityList(districtParam);
                if(!CollectionUtils.isEmpty(cityBeanList)){
                    param.setCityName(this.getPCDName(cityBeanList,param.getCityCode()));
                }
                if(!StringUtil.isNullOrEmpty(param.getDistrictCode())){
                    //获取区县信息
                    districtParam.setDistrictCode(param.getDistrictCode());
                    List<DistrictBean> districtBeanList =CommRestUtil.getDistrictList(districtParam);
                    if(!CollectionUtils.isEmpty(districtBeanList)){
                        param.setDistrictName(this.getPCDName(districtBeanList,param.getDistrictCode()));
                    }
                }
            }
        }
    }

    private String getPCDName(List list, String code){
        for (Object obj :list){
            if(obj instanceof ProvinceBean){
                if(((ProvinceBean) obj).getProvinceCode().equals(code)){
                    return ((ProvinceBean) obj).getProvinceName();
                }
            }
            if(obj instanceof CityBean){
                if(((CityBean) obj).getCityCode().equals(code)){
                    return ((CityBean) obj).getCityName();
                }
            }
            if(obj instanceof DistrictBean){
                if(((DistrictBean) obj).getDistrictCode().equals(code)){
                    return ((DistrictBean) obj).getDistrictName();
                }
            }
        }
        return null;
    }

}
