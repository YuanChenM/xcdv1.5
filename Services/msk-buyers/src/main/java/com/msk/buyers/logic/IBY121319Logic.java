package com.msk.buyers.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.IBY121319RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByBuyerDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 查询买家编码.
 *
 * @author yuan_zhifei
 */
@Service
public class IBY121319Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     */
    interface SqlId {
        String SQL_FIND_BUYER_CODE = "findBuyerCode";
        String SQL_FIND_SH_OD_DELIVERY_AREA = "findShOdDeliveryArea";
    }

    //查询买家编码
    public ByBuyerBasicInfo findBuyerCode(IBY121319RsParam param) {
        return this.findOne(SqlId.SQL_FIND_BUYER_CODE, param);
    }

    //查询配送区域环标码
    public ByBuyerDelivery findShOdDeliveryArea(IBY121319RsParam param) {
        return this.findOne(SqlId.SQL_FIND_SH_OD_DELIVERY_AREA, param);
    }
}
