package com.msk.ssc.logic;


import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.SscConstant;
import com.msk.ssc.bean.SSC11301Param;
import com.msk.ssc.bean.SSC11301RsBean;
import com.msk.ssc.bean.SSC11302RsBeen;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 中标确认书一览Logic
 * <p/>
 * Created by yuan_zhifei on 2016/06/28.
 */
@Service
public class SSC11301Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SSC11302Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_ID_FIND_NO_RELATED_BID_BASE = "findNoRelatedBidBase";
        String SQL_ID_GET_BID_BASE_BY_BID_PROJECT_NO = "getBidBaseByBidProjectNo";
    }

    /**
     * 查询未关联合同的中标产品
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11301RsBean> findNoRelatedBidBase(SSC11301Param param){
        param.setBidStatus(String.valueOf(SscConstant.BidStatus.CANCEL));
        return super.findList(SqlId.SQL_ID_FIND_NO_RELATED_BID_BASE,param);
    }


    /**
     * 根据中标编号查询中标产品是否存在
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11301RsBean checkBidBaseExist(SSC11301Param param){
        param.setBidStatus(String.valueOf(SscConstant.BidStatus.CANCEL));
        List<SSC11301RsBean> list = super.findList(SqlId.SQL_ID_FIND_NO_RELATED_BID_BASE,param);
        if(CollectionUtils.isNotEmpty(list)){
            return list.get(0);
        }else{
            return null;
        }
    }

}


