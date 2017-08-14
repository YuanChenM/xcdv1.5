package com.msk.seller.logic;


import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.seller.bean.ISL231101RsParam;
import com.msk.seller.bean.ISL231101RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gyh on 2016/1/14.
 */
@Service
public class ISL231101RsLogic extends BaseLogic {
    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
          static final String SQL_ID_FIND_SL_IFNO = "findSlInfo";
          static final String SQL_ID_FIND_EP_NAME = "findEPNameBySLCode";
    }

    /**
    * 获得卖家信息（真数据）
    * @param param param
    * @return 卖家信息
    */
    @Transactional(readOnly = true)
    public List<ISL231101RsResult> findSlInfo(ISL231101RsParam param){
        return this.findList(SqlId.SQL_ID_FIND_SL_IFNO, param);
    }

    /**
     * 获得生产商信息（真数据）
     * @param param param
     * @return 生产商信息
     */
    @Transactional(readOnly = true)
    public List<ISL231101RsResult> findEPName(BasePageParam param){
        return this.findList(SqlId.SQL_ID_FIND_EP_NAME, param);
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
