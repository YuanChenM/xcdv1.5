package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141129RsParam;
import com.msk.product.bean.IPD141129RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年3月15日 上午
 *          产品特征接口查询
 */
@Service
public class IPD141129Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 产品品种接口查询
     *
     * @param param
     * @return IPD141129RsResult
     */
    @Transactional(readOnly = true)
    public List<IPD141129RsResult> findListFeature(IPD141129RsParam param) {
        if (param == null) param = new IPD141129RsParam();
        BaseParam param1 = new BaseParam();
        param1.setFilter("classesCode", param.getClassesCode());
        param1.setFilter("machiningCode", param.getMachiningCode());
        param1.setFilter("breedCode", param.getBreedCode());
        return super.findList(param1);
    }
}