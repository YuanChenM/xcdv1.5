package com.msk.seller.logic;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlDistReguSug;
import com.msk.core.entity.SlDistSugHis;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ISL231203Logic.
 *
 * @author gyh
 */
@Service
public class ISL231203Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private ISL231202Logic isl231202Logic;

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_MODIFY_SL_DIST_REGU_SUG = "modifySlDistReguSug";
        static final String SQL_ID_ADD_SL_DIST_SUG_HIS = "addSlDistSugHis";
    }

    @Transactional
    public RsResponse modifySlDistSug(RsRequest<SlDistReguSug> param) {
        RsResponse rs = new RsResponse();
        SlDistReguSug slDistReguSug = param.getParam();
        SlDistSugHis sug = new SlDistSugHis();
        BeanUtils.copyProperties(slDistReguSug,sug);
        sug.setSlSugHisId(commonLogic.maxId("SL_DIST_SUG_HIS", "SL_SUG_HIS_ID"));
        //保存到章程卖家意见履历表
        isl231202Logic.save(SqlId.SQL_ID_ADD_SL_DIST_SUG_HIS, sug);
        slDistReguSug.setUpdId(param.getLoginId());
        slDistReguSug.setUpdTime(DateTimeUtil.getCustomerDate());
        this.modify(SqlId.SQL_ID_MODIFY_SL_DIST_REGU_SUG,slDistReguSug);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("更新分销章程卖家意见成功！");
        return rs;
    }
}
