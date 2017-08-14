package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlSeller;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.ISL23116601;
import com.msk.seller.bean.ISL231167RsParam;
import com.msk.seller.bean.ISL231168RsParam;
import com.msk.seller.bean.ISL231168RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cx on 2016/2/24.
 */
@Service
public class ISL231166RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        static final String SQL_ID_FIND_SL_PD_CLASSES_ALL = "findSLPdClassesAll";
    }

    /**
     * 增加卖家产品类别接口
     *
     * @param iSL23116601
     * @return
     */
    @Transactional
    public int savaSLPdClasses(ISL23116601 iSL23116601) {
        iSL23116601.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(iSL23116601);
    }

    /**
     * 查询传过来的iscode是否存在sl_seller中
     *
     * @param baseParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<SlSeller> findIsCode(BaseParam baseParam) {
        return super.findList(baseParam);
    }

    /**
     * 增加卖家产品类别接口
     * @param param
     * @return
     */
    @Transactional
    public int removeSLPdClasses(RsRequest<ISL231167RsParam> param) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("slCode", StringUtil.toSafeString(param.getParam().getSlCode()));
        baseParam.setFilter("pdClassesCode", StringUtil.toSafeString(param.getParam().getPdClassesCode()));
        return super.remove(baseParam);
    }

    /**
     * 查询卖家产品类别接口
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ISL231168RsResult findSLPdClassesAllList(RsRequest<ISL231168RsParam> param){
        BaseParam baseParam = new BaseParam();
        ISL231168RsResult iSL231168RsResult = new ISL231168RsResult();
        baseParam.setFilter("slCode",StringUtil.toSafeString(param.getParam().getSlCode()));
        List<ISL23116601> list = super.findList(SqlId.SQL_ID_FIND_SL_PD_CLASSES_ALL,baseParam);
        iSL231168RsResult.setPdClassesCodeList(list);
        return iSL231168RsResult;
    }
}
