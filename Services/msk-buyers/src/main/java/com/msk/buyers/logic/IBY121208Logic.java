package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerRecAddr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * IBY121208Logic.
 *
 * @author zhou_yajun
 */
@Service
public class IBY121208Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121208Logic.class);

    @Autowired
    private CommonLogic commonLogic;
    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        //买家收货地址获取
        static String SQL_FIND_RECEIVE_ADDR = "findReceiveAddr";
        //买家收货地址更新
        static String SQL_BUYER_RECEIVE_ADDR_MODIFY = "buyerReceiveAddrModify";
        //买家收货地址插入
        static String SQL_BUYER_RECEIVE_ADDR_INSERT = "buyerReceiveAddrInsert";
        //买家收货地址删除
        static String SQL_BUYER_RECEIVE_ADDR_Delete = "buyerReceiveAddrDelete";
        //接口买家收货地址获取
        static String SQL_FIND_RECEIVE_ADDR_LIST = "findReceiveAddrList";
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
     * 买家收货地址更新接口
     * @param param
     * @return
     */
    @Transactional
    public int buyerReceiveAddrModify(List<ByBuyerRecAddr> param,List<ByBuyerRecAddr> addResult){
        int resultCount = NumberConst.IntDef.INT_ZERO;
        ByBuyerRecAddr newAddress;
        //根据传入参数获取买家收货时间段
        if(!CollectionUtils.isEmpty(param)){
            for (int i = 0; i < param.size(); i++) {
                BaseParam inParam = new BaseParam();
                inParam.setFilter("buyerId", param.get(i).getBuyerId());
                if(null == param.get(i).getId()){
                    inParam.setFilter("id", "");
                    inParam.setFilter("receiveAddr",param.get(i).getReceiveAddr());
                }else{
                    inParam.setFilter("id", String.valueOf(param.get(i).getId()));
                }
                ByBuyerRecAddr receiveAddr = super.findOne(SqlId.SQL_FIND_RECEIVE_ADDR, inParam);
                //获取到数据则更新表
                if(null != receiveAddr){
                    param.get(i).setId(receiveAddr.getId());
                    param.get(i).setUpdTime(DateTimeUtil.getCustomerDate());
                    resultCount += super.modify(SqlId.SQL_BUYER_RECEIVE_ADDR_MODIFY,param.get(i));
                }else {
                    //未获取到数据则插入表
                    resultCount += buyerReceiveAddrSave(param.get(i));
                    newAddress = new ByBuyerRecAddr();
                    newAddress.setId(param.get(i).getId());
                    newAddress.setBuyerId(param.get(i).getBuyerId());
                    newAddress.setReceiveAddr(param.get(i).getReceiveAddr());
                    addResult.add(newAddress);
                }
            }
        }
        return resultCount;
    }
    /**
     * 通路注册时新增收货地址
     * @param param
     * @return
     */
    @Transactional
    public ByBuyerRecAddr buyerEmployeePhoneInsert(ByBuyerRecAddr param){
        //通路注册新增收货地址
        buyerReceiveAddrSave(param);
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId", param.getBuyerId());
        inParam.setFilter("receiveAddr", param.getReceiveAddr());
        ByBuyerRecAddr recAddr = super.findOne(SqlId.SQL_FIND_RECEIVE_ADDR, inParam);
        return recAddr;
    }


    /**
     * 买家收货地址信息插入
     * @param insertParam
     * @return
     */
    @Transactional
    public int buyerReceiveAddrSave(ByBuyerRecAddr insertParam){
        Long id = commonLogic.maxId("by_buyer_rec_addr","ID");
        insertParam.setId(id);
        insertParam.setCrtId(insertParam.getUpdId());
        insertParam.setCrtTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
        insertParam.setActId(insertParam.getUpdId());
        insertParam.setActTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.save(SqlId.SQL_BUYER_RECEIVE_ADDR_INSERT,insertParam);
    }
    /**
     * 买家收货地址信息查询
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByBuyerRecAddr> buyerReceiveAddrFind(ByBuyerRecAddr param){
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId",param.getBuyerId());
        if(null == param.getId()){
            inParam.setFilter("id", "");
        }else{
            inParam.setFilter("id", String.valueOf(param.getId()));
        }
        List<ByBuyerRecAddr> receiveAddrList = super.findList(SqlId.SQL_FIND_RECEIVE_ADDR,inParam);
        return receiveAddrList;
    }

    /**
     * 买家收货地址删除
     * @param param
     * @return
     */
    @Transactional
    public int buyerReceiveAddrDelete(ByBuyerRecAddr param){
        param.setId(param.getId());
        param.setUpdId(param.getUpdId());
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.save(SqlId.SQL_BUYER_RECEIVE_ADDR_Delete,param);
    }


    /***
     * 接口获取收货地址信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByBuyerRecAddr> buyerReceiveAddrList(BuyerRelationParam param){
        List<ByBuyerRecAddr> receiveAddrList = super.findList(SqlId.SQL_FIND_RECEIVE_ADDR_LIST,param);
        return receiveAddrList;
    }
}
