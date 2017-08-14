package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlEpWorkshop;
import com.hoperun.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/1/28.
 */
@Service
public class SL2411010501Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        static  final String SQL_ID_UPDATE_SLEPWORKSHOP = "updateSlEpWorkshop";
        static  final String SQL_ID_DELETE_SLEPWORKSHOP="deleteSlEpWorkshop";
    }

    @Transactional
    public void updateSlEpWorkshop(SlEpWorkshop slEpWorkshop) {
        slEpWorkshop.setUpdTime(DateTimeUtil.getCustomerDate());
        int result = super.modify(SqlId.SQL_ID_UPDATE_SLEPWORKSHOP,slEpWorkshop);
        if(result==0){
            throw  new BusinessException("更新车间信息操作失败，请检查数据");
        }
    }

    @Transactional
    public void deleteSlEpWorkshop(SlEpWorkshop slEpWorkshop) {
        slEpWorkshop.setUpdTime(DateTimeUtil.getCustomerDate());
        int result=super.modify(SqlId.SQL_ID_DELETE_SLEPWORKSHOP,slEpWorkshop);
        if(result==0){
         throw new BusinessException("删除操作失败");
        }
    }

}
