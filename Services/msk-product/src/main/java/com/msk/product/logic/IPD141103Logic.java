package com.msk.product.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141103RsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年1月11日 下午16:15:01
 *          查询销售订单状态
 * 
 */
@Service
public class IPD141103Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * 
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_FIND_LIST_PD_STATUS = "findListStatus";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     *
     * @return IPD141103RsResult 产品销售状态
     * @author xhy
     */
    @Transactional(readOnly = true)
    public List<IPD141103RsParam> findListSaleStatus() {
        List<IPD141103RsParam> findList = super.findList(SqlId.SQL_ID_FIND_LIST_PD_STATUS, null);
        return findList;
    }
}
