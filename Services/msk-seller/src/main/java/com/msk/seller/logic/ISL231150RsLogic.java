package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEpBrand;
import com.msk.core.entity.SlPdBrand;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.ISL231153RsParam;
import com.msk.seller.bean.ISL231153RsResult;
import com.msk.seller.bean.ISlPdBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cx on 2016/2/19.
 */
@Service
public class ISL231150RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 增加卖家产品品牌接口
     * @param islPdBrand
     * @return
     */
    @Transactional
    public int saveSLPdBrand(ISlPdBrand islPdBrand){
        islPdBrand.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(islPdBrand);
    }

    /**
     * 查询是否存在SlEpBrand
     * @param baseParam
     * @return
     */
    @Transactional(readOnly = true)
    public SlEpBrand findSlEpBrandYesOrON(BaseParam baseParam){
        return super.findOne(baseParam);
    }

    /**
     * 修改卖家产品品牌接口
     * @param slPdBrand
     * @return
     */
    @Transactional
    public int updateSlPdBrand(SlPdBrand slPdBrand){
        slPdBrand.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(slPdBrand);
    }

    /**
     * 删除卖家产品品牌接口
     * @param slPdBrand
     * @return
     */
    @Transactional
    public int removeSLPdBrandc(SlPdBrand slPdBrand){
        return super.remove(slPdBrand);
    }


    /**
     * 查询卖家产品品牌
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ISL231153RsResult findSLPdBrandcAllList(RsRequest<ISL231153RsParam> param){
        BaseParam baseParam = new  BaseParam();
        baseParam.setFilter("slCode", StringUtil.toSafeString(param.getParam().getSlCode()));
        baseParam.setFilter("brandEpId",StringUtil.toSafeString(param.getParam().getBrandEpId()));
        baseParam.setFilter("brandId",StringUtil.toSafeString(param.getParam().getBrandId()));
        List<SlPdBrand> list = new ArrayList<SlPdBrand>();
        ISL231153RsResult iSL231153RsResult = new ISL231153RsResult();
        list = super.findList(baseParam);
        iSL231153RsResult.setSlPdBrandList(list);
        return iSL231153RsResult;
    }
}
