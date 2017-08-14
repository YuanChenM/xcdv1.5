package com.msk.product.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141102RsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年1月11日 下午14:21:44
 *          查询所有产品等级
 */
@Service
public class IPD141102Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     *
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_FIND_LIST_PD_GRADE = "findListGrade";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * @return IPD141102RsResult 产品等级数据
     * @author xhy
     */
    @Transactional(readOnly = true)
    public List<IPD141102RsParam> findListGrade() {
        List<IPD141102RsParam> findList = super.findList(SqlId.SQL_ID_FIND_LIST_PD_GRADE, null);
        return findList;
    }
}
