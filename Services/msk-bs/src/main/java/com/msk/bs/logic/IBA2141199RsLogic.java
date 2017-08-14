package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/10/14.
 */
@Service
public class IBA2141199RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /***
     * 查询所有的城市信息
     * @param param
     * @return
     */
    public List<CityBean> getAllCityInfo(DistrictParam param){
        DbUtils.buildLikeCondition(param,"cityName",DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param,"shortSpell",DbUtils.LikeMode.PARTIAL);
        List<CityBean> cityBeanList = CommRestUtil.getProvinceCityList(param);
        return cityBeanList;
    }
}
