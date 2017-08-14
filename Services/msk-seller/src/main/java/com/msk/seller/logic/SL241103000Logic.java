package com.msk.seller.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.district.bean.*;
import com.msk.seller.utils.SLControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjm on 2016/1/26.
 */
@Service
public class SL241103000Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }

    /**
     * 根据市代码，查询县。替代findDistrictList()。
     * Created by xia_xiaojie on 2016/6/15.
     * @param cityCode 市代码
     * @return [县]
     */
    public List<DistrictBean> findDistrictByCityCode(String cityCode) {
        if (!StringUtils.hasText(cityCode)) {
            return new ArrayList<DistrictBean>();
        }

        DistrictParam param = new DistrictParam();
        param.setCityCode(cityCode);
        DistrictResult result = SLControllerUtil.getDistricts(param);
        return result.getDistrictList();
    }

    /**
     * 查询省。替代findMdProvinces()。
     * Created by xia_xiaojie on 2016/6/15.
     * @param param 查询条件
     * @return [省]
     */
    public List<ProvinceBean> getProvinces(DistrictParam param) {
        param = (null == param) ? new DistrictParam() : param;
        DistrictResult result = SLControllerUtil.getProvinces(param);
        return result.getProvinceList();
    }

    /**
     * 根据省代码，查询市。替代findCityList()。
     * Created by xia_xiaojie on 2016/6/15.
     * @param provinceCode 省代码
     * @return [市]
     */
    public List<CityBean> findCityByProvinceCode(String provinceCode) {
        if (!StringUtils.hasText(provinceCode)) {
            return new ArrayList<CityBean>();
        }

        DistrictParam param = new DistrictParam();
        param.setProvinceCode(provinceCode);
        DistrictResult result = SLControllerUtil.getCities(param);
        return result.getCityList();
    }

}
