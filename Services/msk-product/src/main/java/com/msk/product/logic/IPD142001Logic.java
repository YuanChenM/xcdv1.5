package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD142001RsParam;
import com.msk.product.bean.IPD142001RsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * IPD142001Logic.
 * 共同设定查询
 *
 * @author xhy
 */
public class IPD142001Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IPD142001Logic.class);


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
     * 共同设定查询
     *
     * @param param param
     * @return 分页结果
     */
    @Transactional(readOnly = true)
    public IPD142001RsResult findRsResultCom(IPD142001RsParam param) {
        logger.info("物流区产品等级信息查询");
        if (param == null) {
            param = new IPD142001RsParam();
        }
        BaseParam params = new BaseParam();
        params.setFilter("constantType", param.getConstantType());
        return super.findOne(params);
    }
}
