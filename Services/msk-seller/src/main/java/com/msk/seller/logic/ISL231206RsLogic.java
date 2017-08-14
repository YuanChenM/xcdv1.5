package com.msk.seller.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.seller.bean.ISL231206RsBean;
import com.msk.seller.bean.ISL231206RsParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/5/27.
 */
@Service
public class ISL231206RsLogic extends BaseLogic{

    /**
     * 根据卖家产品查询产品生产商
     * @param isl231206RsParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<ISL231206RsBean> getSlPropInfo(ISL231206RsParam isl231206RsParam){
        return this.findList(SqlId.SQL_ID_GET_SL_PROP_INFO, isl231206RsParam);
    }

    interface SqlId{
        String SQL_ID_GET_SL_PROP_INFO = "getSlPropInfo";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}
