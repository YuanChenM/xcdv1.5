package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141143OrderLevel;
import com.msk.product.bean.IPD141143Orders;
import com.msk.product.bean.IPD141143RsParam;
import com.msk.product.bean.IPD141143RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 加工技术标准指标信息同步接口
 * xhy
 */
@Service
public class IPD141143Logic extends BaseLogic {

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
    public IPD141143RsResult findListOrderLevel(IPD141143RsParam param) {
        if (param == null) param = new IPD141143RsParam();

        IPD141143RsResult result = new IPD141143RsResult();

        BaseParam param1 = new BaseParam();
        result.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, param));
        result.setPageNo(param.getPageNo());
        List<IPD141143Orders> results = new ArrayList<IPD141143Orders>();
        if (result.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            results = super.findPageList(param);
            if (results != null && results.size() > NumberConst.IntDef.INT_ZERO) {
                for (IPD141143Orders beans : results) {
                    param1.setFilter("classesCode", beans.getClassesCode());
                    param1.setFilter("machiningCode", beans.getMachiningCode());
                    param1.setFilter("breedCode", beans.getBreedCode());
                    param1.setFilter("featureCode", beans.getFeatureCode());
                    param1.setFilter("weightCode", beans.getWeightCode());
                    param1.setFilter("gradeCode", beans.getGradeCode());
                    param1.setFilter("logiAreaCode", beans.getLogiAreaCode());
                    List<IPD141143OrderLevel> itemBeans = super.findList(param1);
                    beans.setClassesCode(null);
                    beans.setMachiningCode(null);
                    beans.setBreedCode(null);
                    beans.setFeatureCode(null);
                    beans.setWeightCode(null);
                    beans.setWaylist(itemBeans);
                }
            }
            result.setTotalPage(result.getTotalCount(), param.getPageCount());
        }
        result.setSearchList(results);
        return result;
    }


}
