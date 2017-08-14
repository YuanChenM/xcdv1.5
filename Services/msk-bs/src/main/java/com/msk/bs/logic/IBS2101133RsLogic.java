package com.msk.bs.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101138RsBean;
import com.msk.bs.bean.IBS2101139RsBean;
import com.msk.bs.bean.IBS2101140Bean;
import com.msk.bs.bean.IBS2101140RsParam;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlBsBuyer;
import com.msk.core.entity.SlBsBuyerHis;
import com.msk.core.entity.SlHouseAccount;

/**
 * Created by whc on 2016/10/13.
 */
@Service
public class IBS2101133RsLogic extends BaseLogic {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(IBS2101133RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId {
        // 查询管家信息
        static String FIND_HOUSE_ACCOUNT_BY_BUYER = "findHouseAccountByBuyer";
        //查询已经绑定过的buyer信息
        static String FIND_BUYER_EXIST = "findBuyerExist";
        //插入绑定冻品管家与买家关系
        static String SAVE_HK_BUYER = "saveHkBuyer";
        //查询delFlag为1的删除关系
        static String FIND_BUYER_IS_DEL = "findBuyerIsDel";
        //插入delFlag为1的删除关系履历信息
        static String SAVE_BUYER_HIS = "saveBuyerHis";
        //修改delFlag为1的关系
        static  String UPDATE_SL_BS_BUYER = "updateSlBsBuyer";
    }

    @Transactional(readOnly = true)
    public int findHouseAccount(SlHouseAccount houseAccount){
        return this.getCount(SqlId.FIND_HOUSE_ACCOUNT_BY_BUYER,houseAccount);
    }

    @Transactional(readOnly = true)
    public List<SlBsBuyer> findBuyerExist(IBS2101139RsBean bena){
        BaseParam baseParam = new BaseParam();
        baseParam.setFilterObject("hkBuyers",bena);
        return this.findList(SqlId.FIND_BUYER_EXIST,baseParam);
    }

    @Transactional
    public RsResponse saveBuyer(IBS2101139RsBean param){
        RsResponse rs = new RsResponse();
        BaseParam baseParam = new BaseParam();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SlBsBuyer buyer = null;
        List<IBS2101138RsBean> buyerIds = param.getBuyerIds();
        List<SlBsBuyer> buyerList = new ArrayList<>();
        for(IBS2101138RsBean bean : buyerIds){
            buyer = new SlBsBuyer();
            buyer.setSlCode(param.getSlCode());
            buyer.setHouseCode(param.getHouseCode());
            buyer.setBuyerId(bean.getBuyerId());
            try {
                buyer.setStartTime(StringUtil.isNullOrEmpty(bean.getStartTime()) == true ? null : sdf.parse(bean.getStartTime()));
                buyer.setEndTime(StringUtil.isNullOrEmpty(bean.getEndTime()) == true ? null : sdf.parse(bean.getEndTime()));
                buyer.setApplyTime(StringUtil.isNullOrEmpty(bean.getApplyTime()) == true ? buyer.getStartTime() : sdf.parse(bean.getApplyTime()));
            } catch (ParseException e) {
                logger.info("startTime:"+bean.getStartTime());
                logger.info("endTime:"+bean.getEndTime());
                logger.info("applyTime:"+bean.getApplyTime());
                logger.info("IBS2101133RsLogic里String转Date异常:"+e.getMessage());
            }
            buyer.setApplySide(bean.getApplySide());
            buyer.setApplyStatus(bean.getApplyStatus());
            buyer.setDelFlg("0");
            buyer.setCrtId(param.getCrtId());
            buyer.setCrtTime(new Date());
            buyer.setUpdId(param.getUpdId());
            buyer.setUpdTime(new Date());
            buyer.setActId(param.getActId());
            buyer.setActTime(new Date());
            buyer.setVer(0);
            buyerList.add(buyer);
        }
        baseParam.setFilterObject("buyerList",buyerList);
        //查询是否已经绑定过关系，有的话就修改

        //之前删除的关系数据，插入到履历表
        List<SlBsBuyer> slBsBuyerHis = new ArrayList<>();
        List<SlBsBuyer> slBsBuyerUpd = new ArrayList<>();

        //查询入参的buyerId里面删除了的数据集
        List<SlBsBuyer> buyerDelList = this.findList(SqlId.FIND_BUYER_IS_DEL,baseParam);
        if(!CollectionUtils.isEmpty(buyerDelList)){
            //这个是入参整理的数据
            Iterator<SlBsBuyer> iterator = buyerList.iterator();
            for(SlBsBuyer slBsBuyer : buyerDelList){
                boolean isFlag = false;
                while (iterator.hasNext()){
                    SlBsBuyer bsBuyer = iterator.next();
                    if(slBsBuyer.getBuyerId().equals(bsBuyer.getBuyerId())){
                        isFlag = true;
                        //删除该条数据，其他的数据为insert
                        slBsBuyerUpd.add(bsBuyer);
                        iterator.remove();
                    }
                }
                if(isFlag){
                    //记录该数据，为update
                    slBsBuyerHis.add(slBsBuyer);
                }
            }
        }
        //插入履历信息
        int modifyResult = 0;
        if(!CollectionUtils.isEmpty(slBsBuyerHis)){
            SlBsBuyerHis slBsBuyerHis1 = null;
            List<SlBsBuyerHis> buyerHisList = new ArrayList<>();
            for(SlBsBuyer bsBuyer : slBsBuyerHis){
                slBsBuyerHis1 = new SlBsBuyerHis();
                slBsBuyerHis1.setHisId(commonLogic.maxId("SL_BS_BUYER_HIS","HIS_ID"));
                slBsBuyerHis1.setSlCode(bsBuyer.getSlCode());
                slBsBuyerHis1.setHouseAccount(bsBuyer.getHouseCode());
                slBsBuyerHis1.setBuyerId(bsBuyer.getBuyerId());
                slBsBuyerHis1.setStartTime(bsBuyer.getStartTime());
                slBsBuyerHis1.setEndTime(bsBuyer.getEndTime());
                slBsBuyerHis1.setApplySide(bsBuyer.getApplySide());
                slBsBuyerHis1.setApplyStatus(bsBuyer.getApplyStatus());
                slBsBuyerHis1.setApplyTime(bsBuyer.getApplyTime());
                slBsBuyerHis1.setDelFlg(bsBuyer.getDelFlg());
                slBsBuyerHis1.setCrtId(bsBuyer.getCrtId());
                slBsBuyerHis1.setCrtTime(bsBuyer.getCrtTime());
                slBsBuyerHis1.setUpdId(bsBuyer.getUpdId());
                slBsBuyerHis1.setUpdTime(bsBuyer.getUpdTime());
                slBsBuyerHis1.setActId(bsBuyer.getActId());
                slBsBuyerHis1.setActTime(bsBuyer.getActTime());
                slBsBuyerHis1.setVer(bsBuyer.getVer());
                buyerHisList.add(slBsBuyerHis1);
            }
            BaseParam baseParam1 = new BaseParam();
            baseParam1.setFilterObject("buyerHisList",buyerHisList);
            int hisResult = this.save(SqlId.SAVE_BUYER_HIS,baseParam1);
            logger.info("插入履历信息:"+hisResult+"条");

            //修改删除过的数据

            for(SlBsBuyer slBsBuyer : slBsBuyerHis){
                for(SlBsBuyer buyerUpd : slBsBuyerUpd){
                    if(buyerUpd.getBuyerId().equals(slBsBuyer.getBuyerId())){
                        modifyResult += this.modify(SqlId.UPDATE_SL_BS_BUYER,buyerUpd);
                    }
                }

            }
            logger.info("修改buyer信息:"+modifyResult+"条");
            rs.setMessage("成功绑定"+modifyResult+"条记录");
            //同步绑定买家接口数据
            this.asyncBindDate(slBsBuyerHis);
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);

        if(!CollectionUtils.isEmpty(buyerList)){
            try{
                BaseParam baseParam1 = new BaseParam();
                baseParam1.setFilterObject("buyerList",buyerList);
                modifyResult += this.save(SqlId.SAVE_HK_BUYER,baseParam1);
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("成功绑定"+modifyResult+"条记录");
                //同步绑定买家接口数据
                this.asyncBindDate(buyerList);
            }catch (Exception e){
                logger.info("捕获插入异常信息---begin");
                logger.info(e.getMessage());
                logger.info("捕获插入异常信息---end");
                String message = e.getMessage();
                if(!StringUtil.isNullOrEmpty(message) && message.indexOf("PRIMARY") > -1){
                    rs.setMessage("绑定失败,存在一样的buyerId的数据");
                }else {
                    rs.setMessage("绑定失败");
                }
                rs.setStatus(SystemConst.RsStatus.FAIL);
            }
        }
        return rs;
    }


    //同步买家接口数据
    private void asyncBindDate(List<SlBsBuyer> slBsBuyerList){
        logger.info("同步绑定买家接口数据开始");
        if(!CollectionUtils.isEmpty(slBsBuyerList)){
            IBS2101140RsParam ibs2101140RsParam = new IBS2101140RsParam();
            List<IBS2101140Bean>  relationList = new ArrayList<>();
            IBS2101140Bean ibs2101140Bean = null;
            for(SlBsBuyer bsBuyer : slBsBuyerList){
                ibs2101140RsParam.setSlCode(bsBuyer.getSlCode());
                ibs2101140RsParam.setHouseCode(bsBuyer.getHouseCode());
                ibs2101140Bean = new IBS2101140Bean();
                ibs2101140Bean.setBuyerId(bsBuyer.getBuyerId());
                ibs2101140Bean.setRelationType(bsBuyer.getApplyStatus());
                relationList.add(ibs2101140Bean);
            }
            ibs2101140RsParam.setRelationList(relationList);
            CommRestUtil.addBuyerHkRelationship(ibs2101140RsParam);
        }
        logger.info("同步绑定买家接口数据结束");
    }

}
