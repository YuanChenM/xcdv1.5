package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141128RsParam;
import com.msk.product.bean.IPD141128RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年3月15日 上午
 *          产品品种接口查询
 */
@Service
public class IPD141128Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 产品品种接口查询
     *
     * @param param
     * @return IPD141128RsResult
     */
    @Transactional(readOnly = true)
    public List<IPD141128RsResult> findListBreed(IPD141128RsParam param) {
        if (param == null) param = new IPD141128RsParam();
        BaseParam param1 = new BaseParam();
        param1.setFilter("classesCode", param.getClassesCode());
        param1.setFilter("machiningCode", param.getMachiningCode());
        return super.findList(param1);
    }
}