package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerRecTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * IBY121209Logic.
 *
 * @author zhou_yajun
 */
@Service
public class IBY121209Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121209Logic.class);

    @Autowired
    private CommonLogic commonLogic;
    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        //买家收货时间段获取
        static String SQL_FIND_RECEIVE_TIME = "findReceiveTime";
        //买家收货时间段更新
        static String SQL_BUYER_RECEIVE_TIME_MODIFY = "buyerReceiveTimeModify";
        //买家收货时间段插入
        static String SQL_BUYER_RECEIVE_TIME_INSERT = "buyerReceiveTimeInsert";
        //买家收货时间段删除
        static String SQL_BUYER_RECEIVE_TIME_DELETE = "buyerReceiveTimeDelete";
        //接口买家收货时间段获取
        static String SQL_FIND_RECEIVE_TIM_LIST = "findReceiveTimeList";
    }
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

    /**
     * 买家收货时间更新接口
     * @param param
     * @return
     */
    @Transactional
    public int buyerReceiveTimeModify(List<ByBuyerRecTime> param){
        int deleteResult = NumberConst.IntDef.INT_ZERO;
        //将DB中买家的数据DEL_FLG全部设置为1
        super.modify(SqlId.SQL_BUYER_RECEIVE_TIME_DELETE,param.get(NumberConst.IntDef.INT_ZERO));
        //根据传入参数获取买家收货地址
        if(!CollectionUtils.isEmpty(param)){
            for (int i = 0; i < param.size(); i++) {
                BaseParam inParam = new BaseParam();
                inParam.setFilter("buyerId", param.get(i).getBuyerId());
                inParam.setFilter("recPerType",param.get(i).getRecPerType());
                inParam.setFilter("timeDescribe",param.get(i).getTimeDescribe());
                inParam.setFilter("delFlg","1");
                ByBuyerRecTime receiveTime = super.findOne(SqlId.SQL_FIND_RECEIVE_TIME, inParam);
                //获取到数据则更新表
                if(null != receiveTime){
                    param.get(i).setId(receiveTime.getId());
                    param.get(i).setUpdTime(DateTimeUtil.getCustomerDate());
                    deleteResult += super.modify(SqlId.SQL_BUYER_RECEIVE_TIME_MODIFY,param.get(i));
                }else {
                    if(!StringUtil.isNullOrEmpty(param.get(i).getRecPerType()) && !StringUtil.isNullOrEmpty(param.get(i).getTimeDescribe())){
                        //未获取到数据则插入表
                        deleteResult += buyerReceiveTimeSave(param.get(i));
                    }
                }
            }
        }
        return deleteResult;
    }
    /**
     * 买家收货时间插入
     * @param insertParam
     * @return
     */
    @Transactional
    public int buyerReceiveTimeSave(ByBuyerRecTime insertParam){
        Long id = commonLogic.maxId("by_buyer_rec_time","ID");
        insertParam.setId(id);
        //后期需要删除
         /*insertParam.setCrtId(insertParam.getUpdId());
        insertParam.setCrtTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
        insertParam.setActId(insertParam.getUpdId());
        insertParam.setActTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());*/
        return super.save(SqlId.SQL_BUYER_RECEIVE_TIME_INSERT,insertParam);
    }
    /**
     * 买家收货地址信息查询
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByBuyerRecTime> buyerReceiveTimeFind(ByBuyerRecTime param){
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId",param.getBuyerId());
        inParam.setFilter("delFlg","0");
        List<ByBuyerRecTime> receiveTimeList = super.findList(SqlId.SQL_FIND_RECEIVE_TIME,inParam);
        return receiveTimeList;
    }

    /**
     * 接口买家收货地址信息查询
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByBuyerRecTime> buyerReceiveTimeList(BuyerRelationParam param){
        List<ByBuyerRecTime> receiveTimeList = super.findList(SqlId.SQL_FIND_RECEIVE_TIM_LIST,param);
        return receiveTimeList;
    }
}
