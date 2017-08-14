package com.msk.seller.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.BaseEntity;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.ProductPageResult;
import com.msk.seller.bean.ISL231194RsResult;
import com.msk.seller.bean.ISL231198RsPageResult;
import com.msk.seller.bean.ISL231198RsParam;
import com.msk.seller.bean.ISL231198RsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 物流区供应商列表查询Logic
 * Created by yangchunyan on 2016/6/8.
 */
@Service
public class ISL231198RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询物流区供应商信息
     * @param param
     * @param <T>
     * @return
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findLgcsSlPageList(ISL231198RsParam param,ISL231198RsPageResult pageResult) {
        param.setPaging(true);
        return super.findPageList(param, pageResult);
    }


}
