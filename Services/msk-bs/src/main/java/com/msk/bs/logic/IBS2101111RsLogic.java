package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bs.bean.*;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsPageParam;
import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gyh on 2016/4/12.
 */
public class IBS2101111RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findPageList(RsPageParam param, RsPageResult pageResult) {
        IBS2101111RsParam ibs2101111RsParam = (IBS2101111RsParam) param;
        /*DbUtils.buildLikeCondition(ibs2101111RsParam, "slContact", DbUtils.LikeMode.PARTIAL);*/
        param.getFilterMap().put("param",ibs2101111RsParam);
        if (param.getPageCount() == 0 || param.getPageNo() == 0) {
            param.setPaging(false);
        } else {
            param.setPaging(true);
        }
        param.setFilter("ownerSlCode", ibs2101111RsParam.getOwnerSlCode());
        param.setFilter("allianceSlCode", ibs2101111RsParam.getAllianceSlCode());
        // TODO 业务不明。
        return this.findPageList(param, pageResult);

    }

}
