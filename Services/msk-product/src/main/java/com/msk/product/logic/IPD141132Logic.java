package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141132RsBean;
import com.msk.product.bean.IPD141132RsFedBean;
import com.msk.product.bean.IPD141132RsParam;
import com.msk.product.bean.IPD141132RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 饲养标准指标信息同步接口
 * xhy
 */
@Service
public class IPD141132Logic extends BaseLogic {

    interface SqlId {
        static final String SQL_ID_COUNT = "count";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 原种种源信息同步接口
     *
     * @return IPD141132RsResult
     */
    @Transactional(readOnly = true)
    public IPD141132RsResult findListFed(IPD141132RsParam param) {
        if (param == null) param = new IPD141132RsParam();
        IPD141132RsResult result = new IPD141132RsResult();
        BaseParam param1 = new BaseParam();
        result.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, param));
        result.setPageNo(param.getPageNo());
        List<IPD141132RsBean> results = new ArrayList<IPD141132RsBean>();

        if (result.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            results = super.findPageList(param);
            if (results != null && results.size() > NumberConst.IntDef.INT_ZERO) {
                for (IPD141132RsBean beans : results) {
                    param1.setFilter("standardId", beans.getStandardId());
                    beans.setStandardId(null);
                    List<IPD141132RsFedBean> itemBeans = super.findList(param1);
                    beans.setFedList(itemBeans);
                }
            }
            result.setTotalPage(result.getTotalCount(), param.getPageCount());
        }
        result.setSearchList(results);
        return result;
    }
}
