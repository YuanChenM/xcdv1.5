package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBA2141102Param;
import com.msk.bs.bean.IBA2141102RsResult;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.BsAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 买手app登录
 * Created by ni_shaotang on 2016/7/14.
 */
@Service
public class IBA2141102Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 根据手机号和密码获取买手账户信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public IBA2141102RsResult getBsAccount(IBA2141102Param param) {
        return this.findOne(param);
    }
}
