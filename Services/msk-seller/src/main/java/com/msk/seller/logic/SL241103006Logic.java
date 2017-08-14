package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlEpCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保存企业车间信息。更新企业生产能力信息
 * Created by Administrator on 2016/1/28.
 */
@Service
public class SL241103006Logic extends BaseLogic{

    interface SqlId{
        static final String SQL_ID_UPDATE_SLEpCap= "updateSlEpCapLab";
    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }
    //更新用户生产
    @Transactional
    public void updateSlEpCap(SlEpCap slEpCap) {
        slEpCap.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(SqlId.SQL_ID_UPDATE_SLEpCap,slEpCap);
    }
}
