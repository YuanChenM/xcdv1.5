package com.msk.bs.logic;

import java.util.List;

import com.hoperun.core.utils.StringUtil;
import com.msk.core.entity.BsBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlHouseType;

/**
 * Created by wang_haichun on 2016/8/4.
 */
@Service
public class BSBasicInfoLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){super.setBaseDao(baseDao);}


    interface SqlId{
        String SQL_ID_FIND_BASIC_INFO = "findBsBasicInfo";
    }


    @Transactional(readOnly = true)
    public BsBasicInfo findBsBasicInfo(String slCode){
        if(StringUtil.isNullOrEmpty(slCode)){
            return null;
        }
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("slCode", slCode);
        BsBasicInfo basicInfo = this.findOne(SqlId.SQL_ID_FIND_BASIC_INFO, baseParam);
        return basicInfo;
    }

}
