package com.msk.seller.logic.comm;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlSeller;
import com.msk.seller.bean.param.ISLMultiSellerParam;
import com.msk.seller.bean.result.ISLMultiSellerResult;
import com.msk.seller.utils.SLCheckUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhang_chi on 2016/9/13.
 */
@Service
public class ISLMultiSellerLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     */
    interface SqlId {
        static final String SQL_ID_QUERY_SL_SELLER_DIS_QUA = "querySlSellerDisQua";
        static final String SQL_ID_QUERY_SL_SELLER_PRODUCT = "querySlSellerProduct";
    }

    /**
     * 查询供应商对应的分销资格
     *
     * @param islMultiSellerParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<ISLMultiSellerResult> querySlSellerDisQua(ISLMultiSellerParam islMultiSellerParam) {
        List<SlSeller> slSellers = islMultiSellerParam.getSellers();
        if (CollectionUtils.isEmpty(slSellers)) {
            throw new BusinessException("卖家查询信息不可为空");
        }
        BaseParam  baseParam  =  new BaseParam();
        baseParam.setFilterObject("slSellers", slSellers);
        List<ISLMultiSellerResult> islMultiSellerResultList = super.findList(SqlId.SQL_ID_QUERY_SL_SELLER_DIS_QUA,baseParam);
        return islMultiSellerResultList;
    }

    /**
     * 查询新增卖家对应产品信息
     *
     * @param islMultiSellerParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<ISLMultiSellerResult> querySlSellerProduct(ISLMultiSellerParam islMultiSellerParam) {
        String startPriceCycle = islMultiSellerParam.getStartPriceCycle();
        String endPriceCycle = islMultiSellerParam.getEndPriceCycle();
        if(StringUtil.isNullOrEmpty(startPriceCycle) || StringUtil.isNullOrEmpty(endPriceCycle)){
            throw new BusinessException("日期不可为空");
        }
        boolean  startPriceCycleFlag = SLCheckUtil.checkDate(startPriceCycle);
        boolean  endPriceCycleFlag = SLCheckUtil.checkDate(endPriceCycle);
        if(!startPriceCycleFlag || !endPriceCycleFlag){
            throw new BusinessException("日期格式不正确");
        }
        BaseParam  baseParam  =  new BaseParam();
        baseParam.setFilter("startPriceCycle", startPriceCycle);
        baseParam.setFilter("endPriceCycle", endPriceCycle);
        List<ISLMultiSellerResult> islMultiSellerResultList = super.findList(SqlId.SQL_ID_QUERY_SL_SELLER_PRODUCT,baseParam);
        return islMultiSellerResultList;
    }

}
