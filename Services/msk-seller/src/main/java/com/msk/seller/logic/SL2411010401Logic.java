package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlEpHonor;
import com.hoperun.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/1/28.
 */
@Service
public class SL2411010401Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        static  final String SQL_ID_UPDATE_SLEPHONOR = "updateSlEpHonor";
    }

    @Transactional
    public void updateEpHonor(SlEpHonor slEpHonor) {
        slEpHonor.setUpdTime(DateTimeUtil.getCustomerDate());
        int result = super.modify(SqlId.SQL_ID_UPDATE_SLEPHONOR,slEpHonor);
        if(result==0){
            throw new BusinessException("企业荣誉更新操作失败，请核实数据");
        }
    }
}
