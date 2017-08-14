package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.seller.bean.ISL231185RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhang_chi on 2016/4/28.
 */
@Service
public class ISL231185RsLogic extends BaseLogic{

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_FIND_SL_CODE = "findSLCode";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public List<ISL231185RsResult> querySLCode() {
        BaseParam param = new BaseParam();
        List<ISL231185RsResult> SLCodeList = super.findList(SqlId.SQL_ID_FIND_SL_CODE, param);
        return SLCodeList;
    }
}
