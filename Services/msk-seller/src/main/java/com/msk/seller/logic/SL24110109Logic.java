package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlEcTeam;
import com.msk.seller.bean.SL241103070Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fjm on 2016/1/31.
 */
@Service
public class SL24110109Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }


    interface Sql{
        String SQL_ID_MODIFY_ONE="modifyOne";
        String SQL_ID_DELETE_ETEAM="deleteETeam";
        String SQL_ID_UPDATE_SL_ECTEAM_PORT = "updateSLEcTeamPort";
        String SQL_ID_REMOVE_ETEAM_PORT = "removeETeamPort";
    }

    /**
     * 修改卖家电商团队接口
     * @param slEcTeam
     */
    @Transactional
    public int updateSLEcTeamPort(SlEcTeam slEcTeam) {
        slEcTeam.setUpdTime(DateTimeUtil.getCustomerDate());
        return  super.modify(Sql.SQL_ID_UPDATE_SL_ECTEAM_PORT,slEcTeam);
    }
    /**
     * 删除卖家电商团队接口
     * @param slEcTeam
     */
    @Transactional
    public int removeETeamPort(SlEcTeam slEcTeam){
        return super.remove(Sql.SQL_ID_REMOVE_ETEAM_PORT,slEcTeam);
    }

    /**
     * 查询传入的iscode是否有对应的SlEcTeam存在接口
     * @param baseParam
     */
    @Transactional(readOnly = true)
    public List<SlEcTeam> findSLEcTeamYesOrOn(BaseParam baseParam){
        return super.findList(baseParam);
    }

    public int removeETeam(SL241103070Bean sL241103070Bean){
        return super.remove(Sql.SQL_ID_DELETE_ETEAM,sL241103070Bean);
    }

    @Transactional
    public int update(SL241103070Bean sL241103070Bean) {
        sL241103070Bean.setUpdTime(DateTimeUtil.getCustomerDate());
        return  super.modify(Sql.SQL_ID_MODIFY_ONE,sL241103070Bean);
    }
}
