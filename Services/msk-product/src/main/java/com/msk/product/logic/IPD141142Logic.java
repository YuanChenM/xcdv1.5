package com.msk.product.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141142RsBean;
import com.msk.product.bean.IPD141142RsParam;
import com.msk.product.bean.IPD141142RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用产品标准接口
 * xhy
 */
@Service
public class IPD141142Logic extends BaseLogic {

    interface SqlId {
        static final String SQL_ID_COUNT = "count";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 通用产品标准接口
     *
     * @return
     */
    @Transactional(readOnly = true)
    public IPD141142RsResult findPdStandardIdList(IPD141142RsParam param) {
        if (param == null) param = new IPD141142RsParam();
        IPD141142RsResult result = new IPD141142RsResult();

        result.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, param));
        result.setPageNo(param.getPageNo());

        List<IPD141142RsBean> results = new ArrayList<IPD141142RsBean>();

        if (result.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            results = super.findPageList(param);
            if(results!=null&&results.size()> NumberConst.IntDef.INT_ZERO)
            result.setTotalPage(result.getTotalCount(), param.getPageCount());
        }
        result.setSearchList(results);
        return result;

    }
}
