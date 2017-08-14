package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141138RsParam;
import com.msk.product.bean.IPD141138RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年3月15日 上午
 *          产品净重一览查询接口
 */
@Service
public class IPD141138Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 产品净重一览查询接口
     *
     * @param param
     * @return IPD141138RsResult
     */
    @Transactional(readOnly = true)
    public List<IPD141138RsResult> findListWeight(IPD141138RsParam param) {
        if (param == null) param = new IPD141138RsParam();
        BaseParam param1 = new BaseParam();
        param1.setFilter("classesCode", param.getClassesCode());
        param1.setFilter("machiningCode", param.getMachiningCode());
        param1.setFilter("breedCode", param.getBreedCode());
        param1.setFilter("featureCode", param.getFeatureCode());
        return super.findList(param1);
    }
}