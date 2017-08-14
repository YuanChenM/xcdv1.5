package com.msk.ssc.logic;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.ssc.bean.SSC11314RsParam;
import com.msk.ssc.bean.SSC11316Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 业务逻辑类：增删改查预入库通知单
 * Created by xia_xiaojie on 2016/7/8.
 */
@Service
public class SSC11316Logic extends BaseLogic {

    /**
     * 注入DAO
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_ID_DELETE_PRE_INTO = "deletePreInto";//删除预入库通知单
    }

    /**
     * 删除预入库通知单
     * @param
     * @return
     */
    @Transactional
    public int deletePreInto(SSC11316Param ssc11316Param){
        //检查数据版本是否正确
        super.versionValidator("SSC_DELIVERY_PRE_INTO",new String[]{"DELIVERY_PRE_INTO_ID"},new Object[]{ssc11316Param.getDeliveryPreIntoId()},ssc11316Param.getVer());
        return super.modify(SqlId.SQL_ID_DELETE_PRE_INTO,ssc11316Param);
    }
}
