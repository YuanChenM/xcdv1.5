package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141136RsBean;
import com.msk.product.bean.IPD141136RsParam;
import com.msk.product.bean.IPD141136RsResult;
import com.msk.product.bean.IPD141136RsSftItemBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全质量信息指标同步接口
 * xhy
 */
@Service
public class IPD141136Logic extends BaseLogic {

    interface SqlId {
        static final String SQL_ID_COUNT = "count";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 安全质量信息指标同步接口
     *
     * @return
     */
    @Transactional(readOnly = true)
    public IPD141136RsResult findSftList(IPD141136RsParam param) {
        if (param == null) param = new IPD141136RsParam();
        IPD141136RsResult result = new IPD141136RsResult();

        BaseParam param1 = new BaseParam();
        result.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, param));
        result.setPageNo(param.getPageNo());

        List<IPD141136RsBean> results = new ArrayList<IPD141136RsBean>();

        if (result.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            results = super.findPageList(param);
            if (results != null && results.size() > NumberConst.IntDef.INT_ZERO) {
                for (IPD141136RsBean beans : results) {
                    param1.setFilter("standardId", beans.getStandardId());
                    beans.setStandardId(null);
                    List<IPD141136RsSftItemBean> itemBeans = super.findList(param1);
                    beans.setSftList(itemBeans);
                }
            }
            result.setTotalPage(result.getTotalCount(), param.getPageCount());
        }
        result.setSearchList(results);
        return result;
    }


}
