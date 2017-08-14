package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141137RsBean;
import com.msk.product.bean.IPD141137RsParam;
import com.msk.product.bean.IPD141137RsResult;
import com.msk.product.bean.IPD141137RsTspItemBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 储存运输指标信息同步接口
 * xhy
 */
@Service
public class IPD141137Logic extends BaseLogic {

    interface SqlId {
        static final String SQL_ID_COUNT = "count";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 储存运输指标信息同步接口
     *
     * @return
     */
    @Transactional(readOnly = true)
    public IPD141137RsResult findTspList(IPD141137RsParam param) {
        if (param == null) param = new IPD141137RsParam();
        IPD141137RsResult result = new IPD141137RsResult();

        BaseParam param1 = new BaseParam();
        result.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, param));
        result.setPageNo(param.getPageNo());

        List<IPD141137RsBean> results = new ArrayList<IPD141137RsBean>();

        if (result.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            results = super.findPageList(param);
            if (results != null && results.size() > NumberConst.IntDef.INT_ZERO) {
                for (IPD141137RsBean beans : results) {
                    param1.setFilter("standardId", beans.getStandardId());
                    beans.setStandardId(null);
                    List<IPD141137RsTspItemBean> itemBeans = super.findList(param1);
                    beans.setTspList(itemBeans);
                }
            }
            result.setTotalPage(result.getTotalCount(), param.getPageCount());
        }
        result.setSearchList(results);
        return result;
    }


}
