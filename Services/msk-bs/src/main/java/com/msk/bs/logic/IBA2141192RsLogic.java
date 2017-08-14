package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBA2141192Param;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ni_shaotang on 2016/10/27.
 */
@Service
public class IBA2141192RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlID{
        /**查询冻品管家账号信息是否存在**/
        static String FIND_HOUSEACCOUNT_YESORNO ="findHouseAccountYesOrNO";
        /**查询买手账号信息是否存在**/
        static String FIND_SLACCOUNT_ISEXIST ="findSlAccountIsExist";
        /**修改买手密码**/
        static String SQL_ID_MODIFY_BS_PWD ="modifyBsPwd";
    }
    /**
     * 更新管家密码
     * @param param
     * @return
     */
    @Transactional
    public int updateHousePwd(IBA2141192Param param){
        return this.modify(param);
    }
    /**
     * 更新买手密码
     * @param param
     * @return
     */
    @Transactional
    public int updateBsPwd(IBA2141192Param param){
        return this.modify(SqlID.SQL_ID_MODIFY_BS_PWD,param);
    }

    /**
     * 查询冻品管家账号信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public int findHouseAccount(IBA2141192Param param){
        return this.getCount(SqlID.FIND_HOUSEACCOUNT_YESORNO,param);
    }

    /**
     * 查询买手账号信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public int findBuyerAccount(IBA2141192Param param){
        return this.getCount(SqlID.FIND_SLACCOUNT_ISEXIST,param);
    }
}
