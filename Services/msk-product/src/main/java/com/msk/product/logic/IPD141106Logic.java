package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.IPD141106RsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年1月13日 下午9:07:11
 *          产品标准质量档案卡查询
 * 
 */
@Service
public class IPD141106Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * 
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_FIND_PD_STANDARD = "findPdStandard"; // 查询商品数据id
        static final String SQL_ID_FIND_LIST_PD_QLT_STD_ITEM = "findPdQltStdItem"; // 三级类目 产品质量标准子分类
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 数据库连接
     * 
     * @return IPD141106RsResult 产品标准质量档案卡查询
     * @author xhy
     */
    @Transactional(readOnly = true)
    public List<IPD141106RsParam> findListPdQltStd(RsRequest<IPD141106RsParam> param) {

        // 查询产品标准id
        BaseParam paramSt = new BaseParam();
        paramSt.setFilter("classesCode", param.getParam().getClassesCode());
        paramSt.setFilter("breedCode", param.getParam().getBreedCode());
        paramSt.setFilter("featureCode", param.getParam().getFeatureCode());
        PdStandard pd = super.findOne(SqlId.SQL_ID_FIND_PD_STANDARD, paramSt);
        // 根据产品标准id,查询产品质量标准分类表 (一级类目)
        Long standardId = pd.getStandardId();
        BaseParam paramCla = new BaseParam();
        paramCla.setFilter("standardId", standardId.toString());

        List<IPD141106RsParam> listItems = super.findList(SqlId.SQL_ID_FIND_LIST_PD_QLT_STD_ITEM, paramCla);
        return listItems;
    }
}