package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101109RsParam;
import com.msk.bs.bean.IBS2101109RsResult;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlHouseAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/3/30.
 */
@Service
public class IBS2101109RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional
    public IBS2101109RsResult updatePsd(IBS2101109RsParam param) {
        IBS2101109RsResult result = new IBS2101109RsResult();
        BaseParam baseParam = new BaseParam();
        String houseAccount = param.getHouseAccount();
        String oldAccountPsd = param.getOldAccountPsd();
        //String newAccountPsd = param.getNewAccountPsd();
        baseParam.setFilter("houseAccount", houseAccount);
        baseParam.setFilter("oldAccountPsd",oldAccountPsd);
        SlHouseAccount slHouseAccount = super.findOne(baseParam);
        if(null == slHouseAccount){
            throw new BusinessException("您要修改的冻品管家账户不存在");
        }
        if (!oldAccountPsd.equals(slHouseAccount.getAccountPsd())) {
            throw new BusinessException("旧密码输入有误，请输入正确的旧密码!");
        }
        super.modify(param);
        return result;
    }

}
