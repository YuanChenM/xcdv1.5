package com.msk.br.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121414RsParam;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tao_zhifa on 2016/9/28.
 */
@Service
public class IBR121414Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface sqlId{
        String  NAME_COUNT = "nameCount";
        String UPDATE_DEL_FLG = "updateDelFlg";
        String DELETE_MACHIN = "deleteMachin";
    }

    @Transactional(readOnly = true)
    public int  nameCount(IBR121414RsParam param){
        return super.getCount(sqlId.NAME_COUNT,param);
    }

    @Transactional
    public int  updateDelFlg(IBR121414RsParam param){
        return super.modify(sqlId.UPDATE_DEL_FLG,param);
    }

    @Transactional
    public int deleteMachin(IBR121414RsParam param){
        return super.modify(sqlId.DELETE_MACHIN,param);
    }
}
