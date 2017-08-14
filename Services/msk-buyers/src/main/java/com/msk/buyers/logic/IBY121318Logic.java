package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByBuyerReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 买家报表信息.
 *
 * @author zhou_yajun
 */
@Service
public class IBY121318Logic extends BaseLogic {

    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String MODIFY_FILE_STATUS = "modifyFileStatus";
        String IS_EXIST = "isExist";
        String UPDATE_BY_BUYER_REPORT_MANAGER = "updateByBuyerReportManager";

    }
    /**
     * 修改文件状态到不成功
     * @param param
     * @return
     */
    @Transactional
    public int modifyFileStatus(BaseParam param){
        return super.modify(SqlId.MODIFY_FILE_STATUS,param);
    }

    @Transactional(readOnly = true)
    public int isExist(ByBuyerReportManager param) {
        return this.getCount(SqlId.IS_EXIST, param);
    }

    @Transactional
    public int updateByBuyerReportManager(ByBuyerReportManager param) {
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setUpdId("by");
        param.setUpdTime(currentDate);
        return this.modify(SqlId.UPDATE_BY_BUYER_REPORT_MANAGER, param);
    }
}
