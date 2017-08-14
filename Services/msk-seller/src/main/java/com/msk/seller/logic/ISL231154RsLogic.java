package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEpBrand;
import com.msk.core.entity.SlEpBrandHonor;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.ISL231155RsParam;
import com.msk.seller.bean.ISL231157RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cx on 2016/2/23.
 */
@Service
public class ISL231154RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询传过来的epId ,brandId 是否存在sl_ep_brand中
     *
     * @param baseParam
     * @return
     */
    @Transactional(readOnly = true)
    public SlEpBrand findSlEpBrand(BaseParam baseParam) {
        return super.findOne(baseParam);
    }

    /**
     * 增加企业产品品牌荣誉
     *
     * @param slEpBrandHonor
     * @return
     */
    @Transactional
    public int saveSLEpBrandHonor(SlEpBrandHonor slEpBrandHonor) {
        slEpBrandHonor.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(slEpBrandHonor);
    }

    /**
     * 修改企业产品品牌荣誉
     *
     * @param slEpBrandHonor
     * @return
     */
    @Transactional
    public int updateSlEpBrandHonor(SlEpBrandHonor slEpBrandHonor) {
        slEpBrandHonor.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(slEpBrandHonor);
    }

    /**
     * 删除企业产品品牌荣誉
     * @param param
     * @return
     */
    @Transactional
    public int removeSLEpBrandHonor(RsRequest<ISL231155RsParam> param) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("epId", StringUtil.toSafeString(param.getParam().getEpId()));
        baseParam.setFilter("brandId", StringUtil.toSafeString(param.getParam().getBrandId()));
        baseParam.setFilter("honorId", StringUtil.toSafeString(param.getParam().getHonorId()));
        return super.remove(baseParam);
    }

    /**
     * 查询企业产品品牌荣誉
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ISL231157RsResult findSLEpBrandHonorAllList(RsRequest<ISL231155RsParam> param){
        ISL231157RsResult iSL231157RsResult = new ISL231157RsResult();
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("epId",StringUtil.toSafeString(param.getParam().getEpId()));
        baseParam.setFilter("brandId",StringUtil.toSafeString(param.getParam().getBrandId()));
        baseParam.setFilter("honorId",StringUtil.toSafeString(param.getParam().getHonorId()));
        List<SlEpBrandHonor> list = new ArrayList<SlEpBrandHonor>();
        list = super.findList(baseParam);
        iSL231157RsResult.setSlEpBrandHonorList(list);
        return iSL231157RsResult;
    }
}
