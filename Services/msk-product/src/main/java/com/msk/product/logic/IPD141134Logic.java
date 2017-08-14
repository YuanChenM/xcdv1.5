package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141134RsBean;
import com.msk.product.bean.IPD141134RsParam;
import com.msk.product.bean.IPD141134RsResult;
import com.msk.product.bean.IPD141134RsTncBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 加工质量标准指标信息同步接口
 * xhy
 */
@Service
public class IPD141134Logic extends BaseLogic {

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
     * @return
     */
    @Transactional(readOnly = true)
    public IPD141134RsResult findTncList(IPD141134RsParam param) {
        if (param == null) param = new IPD141134RsParam();
        IPD141134RsResult result = new IPD141134RsResult();

        BaseParam param1 = new BaseParam();
        result.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, param));
        result.setPageNo(param.getPageNo());
        List<IPD141134RsBean> results = new ArrayList<IPD141134RsBean>();

        if (result.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            results = super.findPageList(param);
            if (results != null && results.size() > NumberConst.IntDef.INT_ZERO) {
                for (IPD141134RsBean beans : results) {
                    param1.setFilter("standardId", beans.getStandardId());
                    beans.setStandardId(null);
                    List<IPD141134RsTncBean> itemBeans = super.findList(param1);
                    beans.setTncList(itemBeans);
                }
            }
            result.setTotalPage(result.getTotalCount(), param.getPageCount());
        }
        result.setSearchList(results);
        return result;
    }


}
