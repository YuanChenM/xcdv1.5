package com.msk.buyers.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.IBY121319RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询买家ID
 *
 * @author yuan_zhifei
 */
@Service
public class IBY121320Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     */
    interface SqlId {
        String SQL_FIND_BUYER_CODE = "findBuyerId";
    }

    //查询买家编码
    public List<ByBuyerBasicInfo> findBuyerId(IBY121319RsParam param) {
        return this.findList(SqlId.SQL_FIND_BUYER_CODE, param);
    }

}
