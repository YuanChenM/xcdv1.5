package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141131RsBean;
import com.msk.product.bean.IPD141131RsOrgBean;
import com.msk.product.bean.IPD141131RsParam;
import com.msk.product.bean.IPD141131RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 原种种源信息同步接口
 * Created by FjM on 2016/3/11. xhy
 */
@Service
public class IPD141131Logic extends BaseLogic {

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
    public IPD141131RsResult findListOrg(IPD141131RsParam param) {
        if (param == null) param = new IPD141131RsParam();
        IPD141131RsResult result = new IPD141131RsResult();
        BaseParam param1 = new BaseParam();
        result.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, param));
        result.setPageNo(param.getPageNo());

        List<IPD141131RsBean> results = new ArrayList<IPD141131RsBean>();

        if (result.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            results = super.findPageList(param);
            if (results != null && results.size() > NumberConst.IntDef.INT_ZERO) {
                for (IPD141131RsBean beans : results) {
                    param1.setFilter("standardId", beans.getStandardId());
                    beans.setStandardId(null);
                    List<IPD141131RsOrgBean> itemBeans = super.findList(param1);
                    beans.setOrgList(itemBeans);
                }
            }
            result.setTotalPage(result.getTotalCount(), param.getPageCount());
        }
        result.setSearchList(results);
        return result;
    }


}
