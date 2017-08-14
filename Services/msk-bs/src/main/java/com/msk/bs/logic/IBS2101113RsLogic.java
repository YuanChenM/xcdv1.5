package com.msk.bs.logic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101113RsParam;
import com.msk.bs.bean.IBS2101140Bean;
import com.msk.bs.bean.IBS2101140RsParam;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlBsBuyer;
import com.msk.core.entity.SlBsBuyerGet;
import com.msk.core.entity.SlBsBuyerGetHis;
import com.msk.core.entity.SlBsBuyerHis;
import com.msk.product.consts.TableNameDef;

/**
 * Created by ni_shaotang on 2016/7/8.
 */
@Service
public class IBS2101113RsLogic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(IBS2101113RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    @Autowired
    private CommonLogic commonLogic;

    /**
     * sqlid定义
     */
    interface SqlId {
        static final String SQL_ID_FIND_BUYER_GET_LIST = "findBuyerGetList";
        static final String SQL_ID_SAVE_BUYER_GET_HIS = "saveBuyerGetHis";
        static final String SQL_ID_MODIFY_BUYER = "modifyBuyer";
        static final String SQL_ID_MODIFY_BUYER_GET = "modifybuyerGet";
    }

    @Transactional
    public RsResponse releaseRelationship(IBS2101113RsParam params) {
        RsResponse response = new RsResponse();
        String message = "解除买手和冻品管家之间的关系";
        //解除买手和冻品管家之间的关系
        int buyerNum = this.modify(params);
        if (buyerNum > 0) {
            message += "成功。";
            int num = this.delExclusive(params);
            message += "并解除冻品管家和买家专属关系" + num + "个";
            //Delete for 抢单会员表删除 at 2016/09/13 by ni_shaotang Start
            /*int buyerGet = this.delBuyerGet(params);
            message += ",冻品管家抢单会员关系" + buyerGet + "个";*/
            //Delete for 抢单会员表删除 at 2016/09/13 by ni_shaotang End
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setReturnCode("1");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setReturnCode("0");
            message += "失败，该冻品管家不存在。";
        }
        response.setMessage(message);
        return response;
    }

    /**
     * 解除冻品管家和买家专属关系
     *
     * @param params
     * @return
     */
    @Transactional
    public int delExclusive(IBS2101113RsParam params) {
        //获取关系数据
        List<SlBsBuyer> list = this.findList(params);
        SlBsBuyerHis slBsBuyerHis = null;

        for (SlBsBuyer slBsBuyer : list) {
            slBsBuyerHis = new SlBsBuyerHis();
            slBsBuyerHis.setHisId(commonLogic.maxId(TableNameDef.SL_BS_BUYER_HIS, "HIS_ID"));
            slBsBuyerHis.setBuyerId(slBsBuyer.getBuyerId());
            slBsBuyerHis.setSlCode(slBsBuyer.getSlCode());
            slBsBuyerHis.setHouseAccount(slBsBuyer.getHouseCode());
            slBsBuyerHis.setStartTime(slBsBuyer.getStartTime());
            slBsBuyerHis.setEndTime(slBsBuyer.getEndTime());
            slBsBuyerHis.setApplySide(slBsBuyer.getApplySide());
            slBsBuyerHis.setApplyStatus(slBsBuyer.getApplyStatus());
            slBsBuyerHis.setApplyTime(slBsBuyer.getApplyTime());
            slBsBuyerHis.setBuyerReason("1");//默认是1
            slBsBuyerHis.setBuyershopReason("1");//默认是1
            slBsBuyerHis.setDelFlg("0");
            slBsBuyerHis.setCrtId(params.getCrtId());
            slBsBuyerHis.setCrtTime(params.getCrtTime());
            slBsBuyerHis.setUpdId(params.getUpdId());
            slBsBuyerHis.setUpdTime(params.getUpdTime());
            slBsBuyerHis.setActId(params.getActId());
            slBsBuyerHis.setActTime(params.getActTime());
            slBsBuyerHis.setVer(1);
            this.save(slBsBuyerHis);
        }
        //逻辑删除冻品管家和买家专属关系
        int num = this.modify(SqlId.SQL_ID_MODIFY_BUYER,params);

        //同步解除买家接口数据
        this.asyncUnBindDate(list);

        return num;
    }

    /**
     * 解除冻品管家抢单会员关系
     *
     * @param params
     * @return
     */
    @Transactional
    public int delBuyerGet(IBS2101113RsParam params) {
        //获取关系数据
        List<SlBsBuyerGet> list = this.findList(SqlId.SQL_ID_FIND_BUYER_GET_LIST,params);
        SlBsBuyerGetHis slBsBuyerGetHis = null;
        for (SlBsBuyerGet slBsBuyer : list) {
            slBsBuyerGetHis = new SlBsBuyerGetHis();
            slBsBuyerGetHis.setHisId(commonLogic.maxId(TableNameDef.SL_BS_BUYER_GET_HIS, "HIS_ID"));
            slBsBuyerGetHis.setBuyerId(slBsBuyer.getBuyerId());
            slBsBuyerGetHis.setSlCode(slBsBuyer.getSlCode());
            slBsBuyerGetHis.setHouseAccount(slBsBuyer.getHouseAccount());
            slBsBuyerGetHis.setStartTime(slBsBuyer.getStartTime());
            slBsBuyerGetHis.setEndTime(slBsBuyer.getEndTime());
            slBsBuyerGetHis.setReason("1");//默认是1
            slBsBuyerGetHis.setDelFlg("0");
            slBsBuyerGetHis.setCrtId(params.getCrtId());
            slBsBuyerGetHis.setCrtTime(params.getCrtTime());
            slBsBuyerGetHis.setUpdId(params.getUpdId());
            slBsBuyerGetHis.setUpdTime(params.getUpdTime());
            slBsBuyerGetHis.setActId(params.getActId());
            slBsBuyerGetHis.setActTime(params.getActTime());
            slBsBuyerGetHis.setVer(1);
            this.save(SqlId.SQL_ID_SAVE_BUYER_GET_HIS,slBsBuyerGetHis);
        }
        //逻辑删除冻品管家和买家专属关系
        int num = this.modify(SqlId.SQL_ID_MODIFY_BUYER_GET,params);
        return num;
    }


    //同步解除买家接口数据
    private void asyncUnBindDate(List<SlBsBuyer> slBsBuyerList){
        logger.info("同步解除买家接口数据开始");
        if(!CollectionUtils.isEmpty(slBsBuyerList)){
            IBS2101140RsParam ibs2101140RsParam = new IBS2101140RsParam();
            List<IBS2101140Bean>  relationList = new ArrayList<>();
            IBS2101140Bean ibs2101140Bean = null;
            for(SlBsBuyer bsBuyer : slBsBuyerList){
                ibs2101140RsParam.setSlCode(bsBuyer.getSlCode());
                ibs2101140RsParam.setHouseCode(bsBuyer.getHouseCode());
                ibs2101140Bean = new IBS2101140Bean();
                ibs2101140Bean.setBuyerId(bsBuyer.getBuyerId());
                ibs2101140Bean.setRelationType("0");
                relationList.add(ibs2101140Bean);
            }
            ibs2101140RsParam.setRelationList(relationList);
            CommRestUtil.updateBuyerHkRelationship(ibs2101140RsParam);
        }
        logger.info("同步解除买家接口数据结束");
    }
}


