package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141111PriceCycle;
import com.msk.product.bean.IPD141111RsParam;
import com.msk.product.bean.IPD141111RsResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品当前价盘周期价盘查询Logic.
 *
 * @author zhou_Ling
 */
@Service
public class IPD141111Logic extends BaseLogic {
    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_ling
     */
    interface SqlId {
        static final String SQL_ID_FIND_PRICE_CYCLE = "findPriceCycle";
    }

    /**
     * 价盘周期查询
     *
     * @param param param
     * @return 价盘周期
     */
    @Transactional(readOnly = true)
    public List<IPD141111RsResult> findPriceCycle(IPD141111RsParam param) {

        BaseParam param1 = new BaseParam();
        if (param != null) {
            if (StringUtils.isNotBlank(param.getClassesCode()) &&
                    StringUtils.isNotBlank(param.getMachiningCode()) &&
                    StringUtils.isNotBlank(param.getBreedCode()) &&
                    StringUtils.isNotBlank(param.getFeatureCode()) &&
                    StringUtils.isNotBlank(param.getGradeCode()) &&
                    StringUtils.isNotBlank(param.getWeightCode()) &&
                    StringUtils.isNotBlank(param.getLogiAreaCode())) {
                param1.setFilter("classesCode", param.getClassesCode());
                param1.setFilter("machiningCode", param.getMachiningCode());
                param1.setFilter("breedCode", param.getBreedCode());
                param1.setFilter("featureCode", param.getFeatureCode());
                param1.setFilter("weightCode", param.getWeightCode());
                param1.setFilter("gradeCode", param.getGradeCode());
                param1.setFilter("logiAreaCode", param.getLogiAreaCode());
            }
        }
        // 查询价盘信息
        List<IPD141111RsResult> returnlist = super.findList(param1);
        if (returnlist.size() > NumberConst.IntDef.INT_ZERO) {
            for (IPD141111RsResult resultList : returnlist) {
                param1.setFilter("productId", resultList.getProductId());
                param1.setFilter("gradeCode", resultList.getGradeCode());
                param1.setFilter("logiAreaCode", resultList.getLogiAreaCode());
                param1.setFilter("pricePeriod", resultList.getPricePeriod());
                List<IPD141111PriceCycle> cyList = super.findList(SqlId.SQL_ID_FIND_PRICE_CYCLE, param1);
                resultList.setProductId(resultList.getProductId() + resultList.getGradeCode());
                resultList.setPriceList(cyList);
            }
        }
        return returnlist;
    }


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
