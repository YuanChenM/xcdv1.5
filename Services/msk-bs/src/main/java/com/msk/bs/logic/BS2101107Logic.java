package com.msk.bs.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101104SlHouseAccount;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.*;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.district.bean.ProvinceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 冻品管家新增Logic
 *
 * @author cx
 */
@Service
public class BS2101107Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_ID_FINDHOUSEACCOUNT = "findHouseAccount";
        String SQL_ID_FIND_HOUSE_TYPE = "findHouseType";
        String SQL_ID_FIND_BASICINFO_BY_SLCODE = "findBasicInfoBySlCode";
        String SQL_ID_FIND_INTRODUCEINFO_BY_HOUSECODE = "findIntroduceInfoByHouseCode";

    }

    /**
     * 根据市Code查询市
     *
     * @param cityCode 市Code
     * @return MdCity
     */
    @Transactional(readOnly = true)
    public MdCity findCity(String cityCode) {
        MdCity mdCity = new MdCity();
        DistrictParam districtParam = new DistrictParam();
        String[] cityCodes = new String[]{cityCode};
        districtParam.setCityCodes(cityCodes);
        districtParam.setFlag(1);
        List<CityBean> cityBeans = CommRestUtil.getProvinceCityList(districtParam);
        for (CityBean bean : cityBeans) {
            mdCity.setLgcsAreaId(bean.getLgcsAreaId());
            mdCity.setCityId(bean.getCityId());
            mdCity.setCityCode(bean.getCityCode());
        }
        return mdCity;
    }

    /**
     * 根据省Code查询省
     */
    @Transactional(readOnly = true)
    public MdProvince findProvince(String provinceCode) {
        MdProvince mdProvince = new MdProvince();
        DistrictParam districtParam = new DistrictParam();
        districtParam.setProvinceCode(provinceCode);
        districtParam.setIsLoadCity(0);
        List<ProvinceBean> provinceBeans = CommRestUtil.getProvinceList(districtParam);
        for (ProvinceBean bean : provinceBeans) {
            mdProvince.setProvinceId(bean.getProvinceId());
            mdProvince.setProvinceCode(bean.getProvinceCode());
        }
        return mdProvince;
    }


    /**
     * 获取冻品管家类型列表
     *
     * @param slHouseType
     * @return
     */
    @Transactional(readOnly = true)
    public List<SlHouseType> getHouseTypeList(SlHouseType slHouseType) {
        BaseParam param = new BaseParam();
        param.getFilterMap().put("typeLever", slHouseType.getTypeLever());
        if (StringUtil.isNullOrEmpty(slHouseType.getTypeLever())) {
            param.getFilterMap().put("typeLever", "0");
        }
        if (!StringUtil.isNullOrEmpty(slHouseType.getParentTypeCode())) {
            param.getFilterMap().put("parentTypeCode", slHouseType.getParentTypeCode());
        }
        return this.findList(SqlId.SQL_ID_FIND_HOUSE_TYPE, param);
    }


    /**
     * 根據slCode houseCode 查询冻品管家信息
     *
     * @param slHouseAccount
     * @return
     */
    @Transactional(readOnly = true)
    public SlHouseAccount findHouseAccount(SlHouseAccount slHouseAccount) {
        return this.findOne(SqlId.SQL_ID_FINDHOUSEACCOUNT, slHouseAccount);
    }

    @Transactional(readOnly = true)
    public BsBasicInfo findBasicInfoBySlcode(SlHouseAccount slHouseAccount) {
        return this.findOne(SqlId.SQL_ID_FIND_BASICINFO_BY_SLCODE, slHouseAccount);
    }

    /**
     * 根据houseCode 查询冻品管家信息
     * sl_house_introduce 表
     *
     * @param houseIntroduce
     * @return
     */
    @Transactional(readOnly = true)
    public SlHouseIntroduce findIntroduceInfoByHouseCode(SlHouseIntroduce houseIntroduce) {
        return this.findOne(SqlId.SQL_ID_FIND_INTRODUCEINFO_BY_HOUSECODE, houseIntroduce);
    }

}
