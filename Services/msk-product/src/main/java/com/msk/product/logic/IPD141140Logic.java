package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141140RsBean;
import com.msk.product.bean.IPD141140RsParam;
import com.msk.product.bean.IPD141140RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年3月15日 上午
 *          产品包装一览查询接口
 */
@Service
public class IPD141140Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        static final String SQL_ID_COUNT = "count";
    }

    /**
     * 产品包装一览查询接口
     *
     * @param param
     * @return IPD141140RsResult
     */
    @Transactional(readOnly = true)
    public IPD141140RsResult findListMat(IPD141140RsParam param) {
        if (param == null) param = new IPD141140RsParam();
        IPD141140RsResult result = new IPD141140RsResult();

        result.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, param));
        result.setPageNo(param.getPageNo());

        List<IPD141140RsBean> results = new ArrayList<IPD141140RsBean>();

        BaseParam param1 = new BaseParam();
        if (result.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            results = super.findPageList(param);
            for(IPD141140RsBean bean:results){
                if(bean.getClassestreeCode().length()== NumberConst.IntDef.INT_THREE){
                    param1.setFilter("classestreeCode",bean.getClassesCode());
                    bean.setClassesCode(bean.getClassestreeCode().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO));
                    bean.setMachiningCode(bean.getClassestreeCode().substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE));
                }
                bean.setClassestreeCode(null);
            }
            result.setTotalPage(result.getTotalCount(), param.getPageCount());
        }
        result.setSearchList(results);
        return result;
    }
}