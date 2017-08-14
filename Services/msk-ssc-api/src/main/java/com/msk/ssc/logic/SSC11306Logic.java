package com.msk.ssc.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.ssc.bean.SSC11306RsParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yang_yang
 */
@Service
public class SSC11306Logic extends BaseLogic {

    /** Logger */
    private Logger logger = LoggerFactory.getLogger(SSC11306Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional
    public int modifyOrderPd(String sql, SSC11306RsParam ssc11306RsParam){
        if(ssc11306RsParam.getVer() != null){
            //检查数据版本是否正确
            super.versionValidator("ssc_delivery_order_pd",new String[]{"DETAIL_ID"},new Object[]{ssc11306RsParam.getDetailId()},ssc11306RsParam.getVer());
        }
        return super.modify(sql,ssc11306RsParam);
    }

    @Transactional
    public int modifyOrderBasic(String sql, SSC11306RsParam ssc11306RsParam){
        if(ssc11306RsParam.getVer() != null){
            //检查数据版本是否正确
            super.versionValidator("ssc_delivery_order_basic",new String[]{"DELIVERY_ID"},new Object[]{ssc11306RsParam.getDeliveryId()},ssc11306RsParam.getVer());
        }
        return super.modify(sql,ssc11306RsParam);
    }




}