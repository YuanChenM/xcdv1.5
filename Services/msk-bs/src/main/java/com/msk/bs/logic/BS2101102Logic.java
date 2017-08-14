package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS210110301Bean;
import com.msk.bs.bean.IBS2101105RsResult;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlHouseType;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 冻品管家列表
 *
 * @author cx
 */
@Service
public class BS2101102Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private BS2101107Logic bs2101107Logic;

    /**
     * 查询买手店列表
     *
     * @param param param
     * @return 结果
     */
    @Transactional(readOnly = true)
    public PageResult<IBS2101105RsResult> findBS2101102List(BaseParam param) {
        PageResult<IBS2101105RsResult> pageResult = new PageResult<IBS2101105RsResult>();
        pageResult.setRecordsTotal(this.getPageCount(param));
        //获取冻品管家列表
        SlHouseType slHouseType = new SlHouseType();
        List<SlHouseType> typeList = bs2101107Logic.getHouseTypeList(slHouseType);
        if (pageResult.getRecordsTotal() != NumberConst.IntDef.INT_ZERO && !StringUtil.isNullOrEmpty(String.valueOf(param.getFilterMap().get("slCode")))) {
            List<IBS2101105RsResult> list = this.findPageList(param, IBS2101105RsResult.class);

            String[] composeCodes = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                IBS2101105RsResult bean = list.get(i);
                //省编码+城市编码+区县编码
                StringBuffer composeCode = new StringBuffer();
                composeCode.append(bean.getVprovinceCode()).append(bean.getVcityCode()).append(bean.getVdistrictCode());
                composeCodes[i] = composeCode.toString();
            }

            DistrictParam districtParam = new DistrictParam();

            districtParam.setComposeCodes(composeCodes);
            String provinceCode = StringUtil.toSafeString(param.getFilterMap().get("provinceCode1"));
            districtParam.setProvinceCode(provinceCode);
            districtParam.setCityCode(StringUtil.toSafeString(param.getFilterMap().get("cityCode1")));
            districtParam.setDistrictCode(StringUtil.toSafeString(param.getFilterMap().get("districtCode1")));

            //调用行政区划接口
            List<DistrictBean> districtList = CommRestUtil.getRegion(districtParam);



            for (IBS2101105RsResult bean : list) {
                    bean.setCityName(StringConst.EMPTY);
                    bean.setVhouseAddress(bean.getCityName()+bean.getVhouseAddress());
                //填充行政区划
                for (DistrictBean disBean : districtList) {
                    if(!StringUtil.isNullOrEmpty(bean.getVprovinceCode()) && !StringUtil.isNullOrEmpty(bean.getVcityCode())
                            && !StringUtil.isNullOrEmpty(bean.getVdistrictCode())){
                        if (bean.getVprovinceCode().equals(disBean.getProvinceCode()) && bean.getVcityCode().equals(disBean.getCityCode())
                                && bean.getVdistrictCode().equals(disBean.getDistrictCode())) {
                            bean.setCityName(disBean.getProvinceName()+disBean.getCityName()+disBean.getDistrictName());
                            bean.setVhouseAddress(bean.getCityName()+bean.getVhouseAddress());
                        }
                    }
                }
                //填充买家类型
                if(!StringUtil.isNullOrEmpty(bean.getHouseCategory())){
                    for(SlHouseType houseType : typeList){
                        if(bean.getHouseCategory().equals(houseType.getTypeCode())){
                            bean.setHouseCategoryName(houseType.getTypeName());
                        }
                    }
                }
            }

            if (!StringUtil.isNullOrEmpty(provinceCode)) {
                //当有物流区名称查询且查询的行政区划为空，则列表页面无数据
                if (CollectionUtils.isEmpty(districtList)) {
                    list = new ArrayList<>();
                }
            }
            pageResult.setData(list);
        } else {
            pageResult.setData(new ArrayList<IBS2101105RsResult>());
        }
        return pageResult;
    }
}
