package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlEcTeam;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.SL241103070Bean;
import com.msk.seller.bean.SlEpDdBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fjm on 2016/1/31.
 */
@Service
public class SL24110112Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }


    interface Sql{
        String SQL_ID_UPDATE_SLEPDD="updateSlEPDd";
        String SQL_ID_DELETE_SLEPDD="deleteSlEPDd";
    }

    /**
     * 修改企业检测设备
     */
    @Transactional
    public int updateSlEPDd(SlEpDdBean slEpDdBean) {
        slEpDdBean.setUpdTime(DateTimeUtil.getCustomerDate());
        return  super.modify(Sql.SQL_ID_UPDATE_SLEPDD,slEpDdBean);
    }

    /**
     * 删除企业检测设备
     */
    @Transactional
    public int delteSlEPDd(SlEpDdBean slEpDdBean) {
//        BaseParam param=new BaseParam();
//        param.setFilter("epId",StringUtil.toSafeString(slEpDdBean.getEpId()));
//        param.setFilter("ddId",StringUtil.toSafeString(slEpDdBean.getDdId()));
//        param.setFilter("delFlg","1");
        slEpDdBean.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(Sql.SQL_ID_DELETE_SLEPDD, slEpDdBean);
    }
}
