package com.msk.seller.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.seller.bean.ISL231201RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ISL231201Logic.
 *
 * @author gyh
 */
@Service
public class ISL231201Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_FIND_SL_DIST_REGU_CHAP = "findSlDistReguChap";
    }

    /**
     * 根据类别查询章节
     * @param param 参数
     * @return 章节信息
     */
    @Transactional(readOnly = true)
    public List<ISL231201RsResult> findSlDistReguChap(BaseParam param) {
        return this.findList(SqlId.SQL_ID_FIND_SL_DIST_REGU_CHAP, param);
    }

}
