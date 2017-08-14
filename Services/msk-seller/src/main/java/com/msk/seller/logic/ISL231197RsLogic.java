package com.msk.seller.logic;


import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.seller.bean.SlProductBean;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangchi on 2016/5/25.
 */
@Service
public class ISL231197RsLogic extends BaseLogic {

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional
    public int saveSlPdStatusHis(SlProductBean slProductBean) {
        Long hisId = commonLogic.maxId("SL_PRODUCT_STATUS_HIS", "HIS_ID");
        slProductBean.setHisId(hisId);
        slProductBean.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(slProductBean);
    }

}
