package com.msk.product.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.product.bean.PdTcProviderPackageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PD141154Logic
 * @author pxg
 */
@Service
public class PD141154Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_SAVE_ON_LINE = "findSaveOnLine";
    }

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 查询正式上线记录是否存在
     * @param pageParam pageParam
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<PdTcProviderPackageParam> queryProvider(BasePageParam pageParam){
        return super.findPage(pageParam, PdTcProviderPackageParam.class);
    }
}
