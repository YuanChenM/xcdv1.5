package com.msk.product.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141141RsParam;
import com.msk.product.bean.IPD141141RsResult;
import com.msk.product.bean.LogiAreaPdBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * IPD141141Logic.
 *
 * @author xhy
 */
public class IPD141141Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141141Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_COUNT = "count";
    }

    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 物流区产品信息查询
     *
     * @param param param
     * @return 分页结果
     */
    @Transactional(readOnly = true)
    public IPD141141RsResult findRsResult(IPD141141RsParam param) {
        logger.info("物流区产品等级信息查询");
        if (param == null) {
            param = new IPD141141RsParam();
        }
        IPD141141RsResult rsResult = new IPD141141RsResult();
        List<LogiAreaPdBean> logiAreaPdBeans = new ArrayList<LogiAreaPdBean>();

        rsResult.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, param));
        rsResult.setPageNo(param.getPageNo());
        if (rsResult.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            logiAreaPdBeans = super.findPageList(param);
            rsResult.setTotalPage(rsResult.getTotalCount(), param.getPageCount());
        }

        rsResult.setLogiAreaList(logiAreaPdBeans);
        return rsResult;
    }
}
