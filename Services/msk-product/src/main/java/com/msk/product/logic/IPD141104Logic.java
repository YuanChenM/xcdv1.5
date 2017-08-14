package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141104RsParam;
import com.msk.product.bean.IPD141104RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年1月11日 下午15:17:56
 *          查询所有产品加工程度
 * 
 */
@Service
public class IPD141104Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * 
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_FIND_LIST_PD_MACHING = "findListMaching";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     *
     * @return IPD141104RsResult 产品加工程度数据
     * @author xhy
     */
    @Transactional(readOnly = true)
    public List<IPD141104RsResult> findListMaching(IPD141104RsParam param) {
        if (param == null) {
            param = new IPD141104RsParam();
        }
        BaseParam param1 = new BaseParam();
        param1.setFilter("classesCode",param.getClassesCode());
        List<IPD141104RsResult> findList = super.findList(SqlId.SQL_ID_FIND_LIST_PD_MACHING, param1);
        return findList;
    }
}
