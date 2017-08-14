package com.msk.seller.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.BaseEntity;
import com.msk.seller.bean.ISL231207PageResult;
import com.msk.seller.bean.ISL231207Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhang_chi on 2016/9/12.
 */
@Service
public class ISL231207RsLogic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 分页获取所有企业信息列表
     * @param param
     * @param <T>
     * @return
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> queryEnterpriseInfo(ISL231207Param param,ISL231207PageResult pageResult) {
        param.setPaging(true);
        return super.findPageList(param, pageResult);
    }

}
