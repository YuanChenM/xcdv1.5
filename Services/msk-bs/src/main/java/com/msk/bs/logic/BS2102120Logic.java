package com.msk.bs.logic;


import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2102125Bean;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gao_min on 2016/9/14.
 */
@Service
public class BS2102120Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_ID_FINDSLBSACCOUNT = "findSlBsAccount";
        String SQL_ID_FIND_BS_BASICINFO = "findBsBasicInfo";
        String SQL_ID_FIND_BS_SEX = "findBsSexBySlCode";
        String SQL_ID_FIND_BANK_INFO = "findBankInfoBySlCode";
        String SQL_ID_FIND_SLBSLEVELNAME = "findLevelName";
    }

    /**
     * 根据slBsAccount 查询买手帐户信息
     *
     * @param slBsAccount
     * @return
     */
    @Transactional(readOnly = true)
    public BS2102125Bean findSlBsAccount(SlAccount slBsAccount) {
        return this.findOne(SqlId.SQL_ID_FINDSLBSACCOUNT, slBsAccount);
    }

    /**
     * 根据slBsAccount 查询买手基本信息
     *
     * @param bsBasicInfo
     * @return
     */
    @Transactional(readOnly = true)
    public BS2102125Bean findBsBasicInfo(BS2102125Bean bsBasicInfo) {
        return this.findOne(SqlId.SQL_ID_FIND_BS_BASICINFO, bsBasicInfo);
    }

    /**
     * 根据slCode 查询买手性别
     *
     * @param bsBasicInfo
     * @return
     */
    @Transactional(readOnly = true)
    public BS2102125Bean findBsSexInfo(BS2102125Bean bsBasicInfo) {
        return this.findOne(SqlId.SQL_ID_FIND_BS_SEX, bsBasicInfo);
    }

    /**
     * 根据slCode 查询买手绑定银行账户信息
     *
     * @param bsBasicInfo
     * @return
     */
    @Transactional(readOnly = true)
    public BS2102125Bean findBankInfoBySlCode(BS2102125Bean bsBasicInfo) {
        return this.findOne(SqlId.SQL_ID_FIND_BANK_INFO, bsBasicInfo);
    }

    /**
     * 根据买手职业类型编码查询买手分类信息
     *
     * @param slHouseType
     * @return
     */
    @Transactional(readOnly = true)
    public SlHouseType findLevelName(SlHouseType slHouseType) {
        return this.findOne(SqlId.SQL_ID_FIND_SLBSLEVELNAME, slHouseType);
    }
}
