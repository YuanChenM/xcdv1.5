package com.msk.product.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141101RsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年1月11日 下午13:17:56
 *          查询所有产品类别
 */
@Service
public class IPD141101Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     *
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_FIND_ALL_PD_INFO = "findListClasses";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 数据库连接查询操作
     *
     * @return IPD141101RsResult 产品类别数据
     * @author xhy
     */
    @Transactional(readOnly = true)
    public List<IPD141101RsParam> findListPd() {
        List<IPD141101RsParam> findList = super.findList(SqlId.SQL_ID_FIND_ALL_PD_INFO, null);
        return findList;
    }


}
