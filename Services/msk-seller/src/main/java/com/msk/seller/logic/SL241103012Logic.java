package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.logic.CommonLogic;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.msk.seller.bean.SlEpDdBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SL241103012Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId {
        String SQL_ID_SAVE_SL_EP_DD = "saveSlEpDd";
    }

    //保存数据
    @Transactional
    public int saveData(Long epId,SlEpDdBean slEpDdBean) {
        slEpDdBean.setEpId(epId);
        slEpDdBean.setDdId(commonLogic.maxId("SL_EP_DD","DD_ID"));
        slEpDdBean.setVer(NumberConst.IntDef.INT_ONE);
        slEpDdBean.setDelFlg("0");
        slEpDdBean.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(SqlId.SQL_ID_SAVE_SL_EP_DD,slEpDdBean);
    }
}