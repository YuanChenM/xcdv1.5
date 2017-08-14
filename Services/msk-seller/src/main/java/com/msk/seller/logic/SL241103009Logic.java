package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEcTeam;
import com.msk.core.entity.SlSeller;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.ISL231145RsParam;
import com.msk.seller.bean.ISL231145RsResult;
import com.msk.seller.bean.SL241103070Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SL241103009Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_ID_SELECT_EMID = "selectmaxmid";
        String SQL_ID_SELECT_EMLEADER = "selectteamLeader";
        /**
         * 保存电商团队接口
         */
        String SQL_ID_SAVE_ETEAM_PORT = "saveEteamPort";
        /**
         * 查询电商团队接口
         */
        String SQL_ID_FIND_SL_ECTEAM_ALL_LIST = "findSlEcTeamAllList";

    }

    //验证名字是否重复保存
    @Transactional(readOnly = true)
    public SL241103070Bean selectTeamLeader(String eleaderFlg,String slCode) {
        BaseParam param = new BaseParam();
        param.setFilter("eleaderFlg", eleaderFlg);
        param.setFilter("slCode", slCode);
        SL241103070Bean s = super.findOne(SqlId.SQL_ID_SELECT_EMLEADER, param);
        return s;
    }


    //通过slCode查找memberid,返回集合
    @Transactional(readOnly = true)
    public SL241103070Bean maxBrandByEpId(String slCode) {
        BaseParam param = new BaseParam();
        param.setFilter("slCode", slCode);
        SL241103070Bean s = super.findOne(SqlId.SQL_ID_SELECT_EMID, param);
        return s;
    }

    @Transactional
    public SlSeller findSLCode(BaseParam baseParam) {
        return super.findOne(baseParam);
    }

    @Transactional
    public int saveEteam(SL241103070Bean sL241103070Bean) {
        sL241103070Bean.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(sL241103070Bean);
    }

    /**
     * 保存电商团队接口
     *
     * @param slEcTeam
     * @return
     */
    @Transactional
    public int saveEteamPort(SlEcTeam slEcTeam) {
        slEcTeam.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(SqlId.SQL_ID_SAVE_ETEAM_PORT, slEcTeam);
    }

    /**
     * 查询电商团队接口
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ISL231145RsResult findSlEcTeamAllList(RsRequest<ISL231145RsParam> param) {
        ISL231145RsResult iSL231145RsResult = new ISL231145RsResult();
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("slCode", StringUtil.toSafeString(param.getParam().getSlCode()));
        baseParam.setFilter("memberId", StringUtil.toSafeString(param.getParam().getMemberId()));
        List<SlEcTeam>  slEcTeamList = super.findList(SqlId.SQL_ID_FIND_SL_ECTEAM_ALL_LIST,baseParam);
        iSL231145RsResult.setSlEcTeamList(slEcTeamList);
        return iSL231145RsResult;
    }
}