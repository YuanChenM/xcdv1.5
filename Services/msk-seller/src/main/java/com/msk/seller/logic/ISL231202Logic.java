package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.logic.CommonLogic;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlDistReguSug;
import com.msk.core.entity.SlDistSugHis;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231201RsResult;
import com.msk.seller.bean.ISL231202RsParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ISL231202Logic.
 *
 * @author gyh
 */
@Service
public class ISL231202Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    @Autowired
    private CommonLogic commonLogic;
    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_ADD_SL_DIST_REGU_SUG = "addSlDistReguSug";
        static final String SQL_ID_ADD_SL_DIST_SUG_HIS = "addSlDistSugHis";
    }
    @Transactional
    public RsResponse addSlDistSug(RsRequest<ISL231202RsParam> param){
        RsResponse rs=new RsResponse();
        for(SlDistReguSug slDistReguSug:param.getParam().getSlSugs()){
            //保存之前先移除
            super.remove(slDistReguSug);
            slDistReguSug.setCrtId(param.getLoginId());
            slDistReguSug.setCrtTime(DateTimeUtil.getCustomerDate());
            //保存到章程卖家意见表
            int result=super.save(SqlId.SQL_ID_ADD_SL_DIST_REGU_SUG,slDistReguSug);
            if(result>0){
                SlDistSugHis sug=new SlDistSugHis();
                BeanUtils.copyProperties(slDistReguSug, sug);
                sug.setSlSugHisId(commonLogic.maxId("SL_DIST_SUG_HIS","SL_SUG_HIS_ID"));
                //保存到章程卖家意见履历表
                sug.setCrtTime(DateTimeUtil.getCustomerDate());
                int result1=super.save(SqlId.SQL_ID_ADD_SL_DIST_SUG_HIS,sug);
                if(result1<1){
                    rs.setStatus(SystemConst.RsStatus.FAIL);
                    rs.setMessage("创建分销章程卖家意见失败！卖家编码为"+slDistReguSug.getSlCode()+"章节为"+slDistReguSug.getChapId());
                    return rs;
                }
            }else{
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("创建分销章程卖家意见失败！卖家编码为"+slDistReguSug.getSlCode()+"章节为"+slDistReguSug.getChapId());
                return rs;
            }
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("创建分销章程卖家意见成功！");
        return rs;
    }
}
