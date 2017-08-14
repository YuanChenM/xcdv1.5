package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101115RsBean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 买手处理类
 * Created by ni_shaotang on 2016/11/3.
 */
@Service
public class BS2101109Logic extends BaseLogic {

    interface SqlID {
        // 新增买手基本信息.
        static String SQL_ID_SAVE_BS_BASIC_INFO = "saveBsBasicInfo";
        // 修改买手基本信息
        static String SQL_ID_UPDATE_BS_BUYER_INFO = "updateBsBasicInfo";
        //新增买手基本信息历史记录
        static String SQL_ID_SAVE_BS_BASIC_INFO_HIS = "saveBsBasicInfoHis";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    @Autowired
    private CommonLogic commonLogic;
    /**
     * 新增买手账户信息
     * @param account
     * @return
     */
    @Transactional
    public int saveBsAccount(BsAccount account){
        return this.save(account);
    }

    /**
     * 新增买手基本信息
     * @param basicInfo
     * @return
     */
    @Transactional
    public int saveBsBasicInfo(BsBasicInfo basicInfo){
        return this.save(SqlID.SQL_ID_SAVE_BS_BASIC_INFO,basicInfo);
    }

    /**
     * 修改买手账户信息
     * @param account
     * @return
     */
    @Transactional
    public int updateBsAccount(BsAccount account){
        return this.modify(account);
    }

    /**
     * 修改买手基本信息
     * @param basicInfo
     * @return
     */
    @Transactional
    public int updateBsBasicInfo(BsBasicInfo basicInfo){

        IBS2101115RsBean bsBasicInfoHis = this.findOne("findOne",basicInfo);
        bsBasicInfoHis.setHisId(commonLogic.maxId("BS_BASIC_INFO_HIS", "HIS_ID"));
        this.save(SqlID.SQL_ID_SAVE_BS_BASIC_INFO_HIS, bsBasicInfoHis);//新增买手基本信息历史记录

        return this.modify(SqlID.SQL_ID_UPDATE_BS_BUYER_INFO,basicInfo);
    }

}
