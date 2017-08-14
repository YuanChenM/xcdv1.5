package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2101105Bean;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 买手店管家会员列表
 *
 * @author cx
 */
@Service
public class BS2101105Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        //获取所有买手ID
        static final String SQL_ID_GET_ALL_BUYERID = "getAllBuyerId";
        // 根据slcode查询对应的买手基本信息
        static final String FIND_BASIC_INFO_BY_SLCODE = "findBasicInfoBySlCode";
        // 根据slAccount和手机号码，校验买手唯一
        static final String CHECK_SL_TEL = "checkSlTel";
        //校验买手名称唯一
        static final String CHECK_SL_CONTACT = "checkSlContact";
        //校验买手账号唯一
        static final String CHECK_SL_ACCOUNT = "checkSlAccount";
        /*//校验买手名称是否和其他管家一致
        static final String FIND_HOUSE_SHOW_NAME = "findHouseShowName";*/
        //校验买手手机是否和其他管家一致
        static final String FIND_HOUSE_TEL = "findHouseTel";

    }

    /**
     * 根据slCode查询对应的买手基本信息
     *
     * @param baseParam
     * @return
     */
    @Transactional(readOnly = true)
    public BS2101105Bean findBasicInfoBySlCode(BaseParam baseParam) {
        return this.findOne(SqlId.FIND_BASIC_INFO_BY_SLCODE, baseParam);
    }


    /**
     * 根据买手账号、买手名称和手机号码，校验买手唯一
     *
     * @return
     */
    @Transactional(readOnly = true)
    public int checkBuyerByAccountTelContact(String type, String slAccount, String slTel, String slContact) {
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("slContact", slContact);
        baseParam.getFilterMap().put("slAccount", slAccount);
        baseParam.getFilterMap().put("slTel", slTel);
        baseParam.getFilterMap().put("type", type);
        int result;
        //买手账号唯一
        result = this.getCount(SqlId.CHECK_SL_ACCOUNT, baseParam);
        if (result > 0) {
            //该买手账号与其他买手账号或名称或手机相同
            result = -1;
        }
        //买手账号名称唯一
        if (result == 0) {
            //该买手名称与其他买手账号或名称或手机相同
            result = this.getCount(SqlId.CHECK_SL_CONTACT, baseParam);
        }
        //买手账号手机唯一
        if (result == 0) {
            //该买手手机与其他买手账号或名称或手机相同
            result = this.getCount(SqlId.CHECK_SL_TEL, baseParam);
        } else if (result > 0) {
            result = -2;
        }
        //校验买手名称是否和其他管家一致
        /*if (result == 0) {
            result = this.getCount(SqlId.FIND_HOUSE_SHOW_NAME, baseParam);
        } else if (result > 0) {
            result = -3;
        }*/
        //校验买手手机是否和其他管家一致
        if (result == 0) {
            result = this.getCount(SqlId.FIND_HOUSE_TEL, baseParam);
        } else if (result > 0) {
            result = -3;
        }
        if (result > 0) {
            result = -4;
        }
        return result;
    }
}
