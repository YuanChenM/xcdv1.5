package com.msk.price.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.price.bean.ISP171186Bean;
import com.msk.price.bean.ISP171186Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ren_qiang on 2016/9/14.
 */
@Service
public class ISP171186Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISP171186Logic.class);
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_ID_GET_SUPPLIER_LIST_BY_PRICE_PERIOD = "getSupplierListByPricePeriod";
    }
    @Transactional(readOnly = true)
    public RsResponse<List<ISP171186Bean>> getSupplierListByPricePeriod(ISP171186Param param){
        RsResponse<List<ISP171186Bean>> rsResponse = new RsResponse<List<ISP171186Bean>>();
        List<ISP171186Bean> list = this.findList(SqlId.SQL_ID_GET_SUPPLIER_LIST_BY_PRICE_PERIOD,param);
        rsResponse.setResult(list);
        return rsResponse;
    }

}
