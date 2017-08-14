package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121001Bean;
import com.msk.buyers.bean.BY121106Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.BuyersConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByMarketFood;
import com.msk.core.entity.ByMarketTerminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhou_yajun on 2016/9/1.
 */
public class BY121106Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId{
        static String FIND_BY_LIC_PICTURE = "findByLicPicture";
        static String ADD_BY_LIC_PICTURE = "addByLicPicture";
        static String MODIFY_BY_LIC_PICTURE = "modifyByLicPicture";
    }

    public void modifyByLicPicture(BY121106Bean byLicPicture){

        BY121106Bean byLic = this.findOne(SqlId.FIND_BY_LIC_PICTURE,byLicPicture);
        if(null == byLic){
            Long maxId = commonLogic.maxId("by_buyer_pictures","ID");
            byLicPicture.setId(maxId);
            byLicPicture.setUpdTime(DateTimeUtil.getCustomerDate());
            byLicPicture.setCrtTime(DateTimeUtil.getCustomerDate());
            byLicPicture.setActTime(DateTimeUtil.getCustomerDate());
            this.save(SqlId.ADD_BY_LIC_PICTURE,byLicPicture);
        }else{
            this.modify(SqlId.MODIFY_BY_LIC_PICTURE,byLicPicture);
        }
    }
}
