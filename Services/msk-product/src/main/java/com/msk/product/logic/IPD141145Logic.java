package com.msk.product.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsPageParam;
import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BaseEntity;
import com.msk.product.bean.IPD141145RsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gyh on 2016/4/20.
 */
public class IPD141145Logic extends BaseLogic {

    @Override
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findPageList(RsPageParam param, RsPageResult pageResult) {
        IPD141145RsParam rsParam= (IPD141145RsParam) param;
        if (param.getPageCount() == 0 || param.getPageNo() == 0) {
            param.setPaging(false);
        } else {
            param.setPaging(true);
        }
        param.setFilter("sellerCode", rsParam.getSellerCode());
        return super.findPageList(param, pageResult);
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
