package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlEpManager;
import com.msk.seller.bean.SL24110306Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fjm on 2016/1/30.
 */
@Service
public class SL241103008Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        String SQL_ID_SELECT_MID = "selectmaxmid";
        String SQL_ID_SELECT_MEMBERNAME = "selectmemberName";
        String SQL_ID_SAVE_MANAGER1 = "saveManager1";
        String SQL_ID_FIND_ONE1 = "findOne1";
    }
    //验证名字是否重复
    @Transactional(readOnly = true)
    public SL24110306Bean checkMemberDuties(Long epId,String memberDuties){
        BaseParam param = new BaseParam();
        param.setFilter("memberDuties", memberDuties);
        param.setFilter("epId",epId.toString());
        SL24110306Bean s = super.findOne(SqlId.SQL_ID_SELECT_MEMBERNAME, param);
        return s;
    }


    //通过epid查找memberid,返回集合
    @Transactional(readOnly = true)
    public SL24110306Bean maxManagerByEpId(Long epId){
        BaseParam param = new BaseParam();
        param.setFilter("epId", epId.toString());
        SL24110306Bean s = super.findOne(SqlId.SQL_ID_SELECT_MID, param);
        return s;
    }


    @Transactional
    public int saveManager(SL24110306Bean sL24110306Bean){
        sL24110306Bean.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(sL24110306Bean);
    }
    //保存企业团队管理接口
    @Transactional
    public int saveManagerPort(SlEpManager slEpManager){
        slEpManager.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(SqlId.SQL_ID_SAVE_MANAGER1,slEpManager);
    }

    //查询企业id
    @Transactional(readOnly = true)
    public SlEpManager findEpId1(BaseParam baseParam){
        return super.findOne(SqlId.SQL_ID_FIND_ONE1,baseParam);
    }

}
