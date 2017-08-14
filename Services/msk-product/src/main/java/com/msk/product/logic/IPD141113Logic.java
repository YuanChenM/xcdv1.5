package com.msk.product.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141113RsParam;
import com.msk.product.bean.IPD141113RsResult;
import com.msk.product.bean.LogiAreaPdBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * IPD141113Logic.
 *
 * @author yuan_chen
 */
public class IPD141113Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141113Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_ling
     */
    interface SqlId {
        static final String SQL_ID_COUNT = "count";
    }

    /**
     * (non-Javadoc)
     *
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
    public IPD141113RsResult findRsResult(IPD141113RsParam param) {
        logger.info("物流区产品信息查询");
        if (param == null) {
            param = new IPD141113RsParam();
        }
        IPD141113RsResult rsResult = new IPD141113RsResult();
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
