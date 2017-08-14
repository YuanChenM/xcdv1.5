package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBA2141119Param;
import com.msk.bs.bean.IBA2141119Result;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhu_kai1 on 2016/7/18.
 */
@Service
public class IBA2141119RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        // 查询买手slcode
        static  String SELECT_HOUSE_ACCOUNT="selectHouseAccount";
    }

    /**
     * 根据登录的管家ID到表【冻品管家账户】中查出对应的买手ID
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public IBA2141119Result selectHouseAccount(IBA2141119Param param){
        return   this.findOne(SqlId.SELECT_HOUSE_ACCOUNT, param);
    }


}
