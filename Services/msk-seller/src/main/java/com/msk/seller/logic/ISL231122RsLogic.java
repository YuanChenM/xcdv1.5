package com.msk.seller.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlSeller;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.ISL231122RsParam;
import com.msk.seller.bean.ISL231122RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/15.
 */
@Service
public class ISL231122RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public ISL231122RsResult findAllList(RsRequest<ISL231122RsParam> param) {
        // 创建输入参数
        ISL231122RsParam iSL231122RsParam = param.getParam();
        // 创建输出参数
        ISL231122RsResult iSL231122RsResult = new ISL231122RsResult();
       // SlSeller  slSeller = new  SlSeller();
        BaseParam params = new BaseParam();
        params.setFilter("slAccount", StringUtil.toSafeString(iSL231122RsParam.getSlAccount()));
        params.setFilter("slTel", StringUtil.toSafeString(iSL231122RsParam.getSlTel()));
        params.setFilter("slCode", StringUtil.toSafeString(iSL231122RsParam.getSlCode()));
        params.setFilter("slAreaCode", StringUtil.toSafeString(iSL231122RsParam.getSlAreaCode()));
        params.setFilter("epName", StringUtil.toSafeString(iSL231122RsParam.getEpName()));
        List<SlSeller> slSellerList  = super.findList(params);
        iSL231122RsResult.setSlSellerList(slSellerList);
        return iSL231122RsResult;
    }
}
