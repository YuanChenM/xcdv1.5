package com.msk.batch.br;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.BaseBatch;
import com.msk.batch.br.bean.*;
import com.msk.batch.br.logic.*;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrMPdClasses;
import com.msk.core.entity.BrOBuyerInfo;
import com.msk.core.entity.BrOClaMachiningInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yuan_zhifei on 2016/6/14.
 */
@Component("BBR121403")
public class BBR121403Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121403Batch.class);

    @Autowired
    private BBR12140301Logic bbr12140301Logic;
    @Autowired
    private BBR12140302Logic bbr12140302Logic;
    @Autowired
    private BBR12140303Logic bbr12140303Logic;
    @Autowired
    private BBR12140304Logic bbr12140304Logic;
    @Autowired
    private BBR12140305Logic bbr12140305Logic;
    @Autowired
    private BBR12140306Logic bbr12140306Logic;
    @Autowired
    private CommonLogic commonLogic;

    /**
     * 创建Param
     *
     * @param args String[]
     * @return baseParam baseParam
     */
    @Override
    public BaseParam createParam(String[] args) {
        BaseParam param = new BaseParam();
        //设置共通参数
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setCrtId("admin");
        param.setCrtTime(currentDate);
        param.setActId("admin");
        param.setActTime(currentDate);
        param.setUpdId("admin");
        param.setUpdTime(currentDate);
        return param;
    }

    /**
     * 业务处理
     *
     * @param param
     */
    @Override
    public void doOperate(BaseParam param) {
        logger.info("同步订单数据");
        this.updateSynchronizedOrderData(param);
        logger.info("同步买家数据");
        this.updateSynchronizedBuyerData(param);
        logger.info("同步卖家基本数据");
        this.updateSynchronizedSlProduct(param);
        logger.info("同步产品数据");
        this.updateSynchronizedProduct(param);
        logger.info("同步冻品管家基本信息！");
        this.addHkInfos(param);
        logger.info("更新冻品管家的分类管控信息！");
        this.updateHkInfos(param);
        logger.info("同步冻品管家与买家绑定最新关系！");
        this.updateHkBuyerRelationships(param);
    }

    /**
     * 同步订单数据
     */
    private void updateSynchronizedOrderData(BaseParam param) {
        logger.info("更新已同步的订单数据（订单状态或订单明细状态发生变化的数据）");
        this.bbr12140301Logic.modify(param);
        logger.info("插入新的订单数据");
        this.bbr12140301Logic.save(param);
    }

    /**
     * 同步买家数据
     */
    private void updateSynchronizedBuyerData(BaseParam param) {
        List<BrOBuyerInfo> buyerInfoList = new ArrayList<>();
        //获取买家上线状态对应的值
        Map<String, String> marketStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        /*int employeeCount = this.bbr12140302Logic.getEmployeeCount(param);
        int deliveryCount = this.bbr12140302Logic.getDeliveryCount(param);*/
        //if (employeeCount < 1 && deliveryCount < 1) {
        logger.info("查询买家买家池买家信息");
        buyerInfoList = this.bbr12140302Logic.findList(param);
        if (!CollectionUtils.isEmpty(buyerInfoList)) {
            this.bbr12140302Logic.deleteNeedFeaDetail(param);
            logger.info("清空买家经营产品类型数据");
            this.bbr12140302Logic.deleteBuyerPdCla(param);
            logger.info("清空买家基本信息数据");
            this.bbr12140302Logic.deleteBuyerInfo(param);
            logger.info("同步买家基本信息");
            for (int i = NumberConst.IntDef.INT_ZERO; i < buyerInfoList.size(); i++) {
                BrOBuyerInfo buyerInfo = buyerInfoList.get(i);
                Date currentDate = DateTimeUtil.getCustomerDate();
                buyerInfoList.get(i).setCrtTime(currentDate);
                buyerInfoList.get(i).setUpdTime(currentDate);
                buyerInfoList.get(i).setActTime(currentDate);
                buyerInfoList.get(i).setCrtId(param.getCrtId());
                buyerInfoList.get(i).setUpdId(param.getUpdId());
                buyerInfoList.get(i).setActId(param.getActId());
                if (BuyersConstant.MarketingsStatus.PreRegister.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                        BuyersConstant.MarketingsStatus.NoMarket.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                        BuyersConstant.MarketingsStatus.ActivePeriod.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                        BuyersConstant.MarketingsStatus.StablePeriodCentral.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                        BuyersConstant.MarketingsStatus.StablePeriodStandard.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                        BuyersConstant.MarketingsStatus.EarlyWarnPeriod.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                        BuyersConstant.MarketingsStatus.SalePublicBuyers.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                        BuyersConstant.MarketingsStatus.OutBusiness.equals(buyerInfoList.get(i).getMarketingsStatus())) {
                    buyerInfoList.get(i).setMarketingsStatusName(marketStatus.get(buyerInfoList.get(i).getMarketingsStatus()));
                } else {
                    buyerInfoList.get(i).setMarketingsStatusName("");
                }
                this.bbr12140302Logic.save(buyerInfo);
            }
            logger.info("同步买家经营产品类型数据");
            this.bbr12140302Logic.saveBuyerPdCla(param);
            logger.info("同步买家需求调研数据");
            this.bbr12140302Logic.saveNeedFeaDetail(param);
        }
        //}
    }

    /**
     * 同步卖家基本数据
     *
     * @param param
     * @return
     */
    private void updateSynchronizedSlProduct(BaseParam param) {
        logger.info("清除卖家基本数据");
        this.bbr12140303Logic.deleteSlProduct(param);
        logger.info("同步卖家基本数据");
        this.bbr12140303Logic.addSlProduct(param);
    }

    /**
     * 同步产品数据
     *
     * @param param
     * @return
     */
    private void updateSynchronizedProduct(BaseParam param) {
        logger.info("清除产品12级基本数据");
        this.bbr12140304Logic.deleteMachiningInfo(param);
        logger.info("清除产品34级基本数据");
        this.bbr12140304Logic.deleteBreedFeaInfo(param);
        logger.info("清除产品56级基本数据");
        this.bbr12140304Logic.deleteWeiNorInfo(param);
        logger.info("清空物流区产品原始数据");
        this.bbr12140304Logic.deletePdLgcsArea(param);
        logger.info("同步一级和二级分类的产品数据");
        this.bbr12140304Logic.addMachiningInfo(param);
        logger.info("同步三级分类的产品数据");
        this.bbr12140304Logic.addBreedFeaInfo(param);
        logger.info("同步五级和六级分类的产品数据");
        this.bbr12140304Logic.addWeiNorInfo(param);
        logger.info("同步物流区产品数据");
        this.bbr12140304Logic.addPdLgcsArea(param);
        logger.info("产品基础数据变更");
        this.updateBrMPdClasses(param);
    }

    /**
     * 产品基础数据变更
     */
    private void updateBrMPdClasses(BaseParam param) {
        logger.info("查询产品分类信息");
        List<BrOClaMachiningInfo> brOClaMachiningInfoList = this.bbr12140304Logic.findBrOClaMachiningInfos(param);
        logger.info("查询产品基础数据");
        List<BrMPdClasses> brMPdClassesList = this.bbr12140304Logic.findBrMPdClasses(param);
        List<BrMPdClasses> list = new ArrayList<>();
        int count = NumberConst.IntDef.INT_ZERO;
        int result = NumberConst.IntDef.INT_ZERO;
        boolean flag = false;
        if (!CollectionUtils.isEmpty(brOClaMachiningInfoList) && !CollectionUtils.isEmpty(brMPdClassesList)) {
            BrOClaMachiningInfo brOClaMachiningInfo = new BrOClaMachiningInfo();
            BrMPdClasses brMPdClasses = new BrMPdClasses();
            for (int i = NumberConst.IntDef.INT_ZERO; i < brOClaMachiningInfoList.size(); i++) {
                //产品分类信息数据中的产品一级分类二级分类组合
                brOClaMachiningInfo = brOClaMachiningInfoList.get(i);
                String claMachiningCode = brOClaMachiningInfo.getClassesCode() + brOClaMachiningInfo.getMachiningCode();
                for (int j = NumberConst.IntDef.INT_ZERO; j < brMPdClassesList.size(); j++) {
                    brMPdClasses = brMPdClassesList.get(j);
                    //产品基础数据中的产品一级分类二级分类组合
                    String mPdClassesCode = brMPdClasses.getClassesCode() + brMPdClasses.getMachiningCode();
                    if (!claMachiningCode.equals(mPdClassesCode)) {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    BrMPdClasses bean = new BrMPdClasses();
                    bean.setClassesCode(brOClaMachiningInfo.getClassesCode());
                    bean.setClassesName(brOClaMachiningInfo.getClassesName());
                    bean.setMachiningCode(brOClaMachiningInfo.getMachiningCode());
                    bean.setMachiningName(brOClaMachiningInfo.getMachiningName());
                    bean.setMachiningCodeU(brOClaMachiningInfo.getMachiningCode());
                    bean.setMachiningNameU(brOClaMachiningInfo.getMachiningName());
                    bean.setByPoolMachiningCode(brOClaMachiningInfo.getMachiningCode());
                    list.add(bean);
                } else {
                    //list.clear();
                    continue;
                }
            }
            if (!CollectionUtils.isEmpty(list)) {
                logger.info("产品基础数据更新开始！");
                for (BrMPdClasses mPdClasses : list) {
                    Long maxId = commonLogic.maxId("br_m_pd_classes", "ID");
                    Date currentDate = DateTimeUtil.getCustomerDate();
                    mPdClasses.setId(maxId);
                    mPdClasses.setActId(param.getActId());
                    mPdClasses.setActTime(currentDate);
                    mPdClasses.setUpdId(param.getUpdId());
                    mPdClasses.setUpdTime(currentDate);
                    mPdClasses.setCrtId(param.getCrtId());
                    mPdClasses.setCrtTime(currentDate);
                    result = this.bbr12140304Logic.updateBrMPdClasses(mPdClasses);
                    count++;
                    //更新买家池
                    param.setFilter("classesCode",mPdClasses.getClassesCode());
                    param.setFilter("machiningCodeU",mPdClasses.getMachiningCodeU());
                    int res = this.bbr12140304Logic.findBuyerPoolCount(param);
                    if(res > NumberConst.IntDef.INT_ZERO){
                        continue;
                    }else{
                        logger.info("保存物流区买家池！");
                        this.bbr12140304Logic.saveBuyerPoolLogisticsArea(param);
                        logger.info("保存物流区地区买家池！");
                        this.bbr12140304Logic.saveBuyerPoolLogisticsAreaRegion(param);
                    }
                }
            } else {
                logger.info("产品基础数据无数据可同步！");
            }
        } else if (!CollectionUtils.isEmpty(brOClaMachiningInfoList) && CollectionUtils.isEmpty(brMPdClassesList)) {
            BrMPdClasses mPdClasses = new BrMPdClasses();
            for (int i = 0; i < brOClaMachiningInfoList.size(); i++) {
                BrOClaMachiningInfo brOClaMachiningInfo = brOClaMachiningInfoList.get(i);
                Long maxId = commonLogic.maxId("br_m_pd_classes", "ID");
                Date currentDate = DateTimeUtil.getCustomerDate();
                mPdClasses.setId(maxId);
                mPdClasses.setActId(param.getActId());
                mPdClasses.setActTime(currentDate);
                mPdClasses.setUpdId(param.getUpdId());
                mPdClasses.setUpdTime(currentDate);
                mPdClasses.setCrtId(param.getCrtId());
                mPdClasses.setCrtTime(currentDate);
                mPdClasses.setClassesCode(brOClaMachiningInfo.getClassesCode());
                mPdClasses.setClassesName(brOClaMachiningInfo.getClassesName());
                mPdClasses.setMachiningCode(brOClaMachiningInfo.getMachiningCode());
                mPdClasses.setMachiningName(brOClaMachiningInfo.getMachiningName());
                mPdClasses.setMachiningCodeU(brOClaMachiningInfo.getMachiningCode());
                mPdClasses.setMachiningNameU(brOClaMachiningInfo.getMachiningName());
                mPdClasses.setByPoolMachiningCode(brOClaMachiningInfo.getMachiningCode());
                result = this.bbr12140304Logic.updateBrMPdClasses(mPdClasses);
                count++;
                //更新买家池
                param.setFilter("classesCode",mPdClasses.getClassesCode());
                param.setFilter("machiningCodeU",mPdClasses.getMachiningCodeU());
                int res = this.bbr12140304Logic.findBuyerPoolCount(param);
                if(res > NumberConst.IntDef.INT_ZERO){
                    continue;
                }else{
                    logger.info("保存物流区买家池！");
                    this.bbr12140304Logic.saveBuyerPoolLogisticsArea(param);
                    logger.info("保存物流区地区买家池！");
                    this.bbr12140304Logic.saveBuyerPoolLogisticsAreaRegion(param);
                }
            }
        } else {
            logger.info("产品基础数据无数据可同步！");
        }
    }

    /**
     * 同步冻品管家基本信息
     */
    private void addHkInfos(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<>();
        int result = 0;
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        //调用冻品管家账号信息接口
        String url = SystemServerManager.BsServerManage.getSearch();
        RsResponse<IBBR12140305RsResult> resultRsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBBR12140305RsResult>>() {
        });
        if (resultRsResponse.getResult() != null) {
            if (!CollectionUtils.isEmpty(resultRsResponse.getResult().getHouseList())) {
                logger.info("清除冻品管家基本信息！");
                this.bbr12140305Logic.remove(param);
                List<IBBR12140305RsBean> houseList = resultRsResponse.getResult().getHouseList();
                logger.info("同步冻品管家基本信息！");
                for (int i = NumberConst.IntDef.INT_ZERO; i < houseList.size(); i++) {
                    IBBR12140305RsBean hkBean = houseList.get(i);
                    Date currentDate = DateTimeUtil.getCustomerDate();
                    hkBean.setCrtTime(currentDate);
                    hkBean.setUpdTime(currentDate);
                    hkBean.setActTime(currentDate);
                    hkBean.setCrtId(param.getCrtId());
                    hkBean.setUpdId(param.getUpdId());
                    hkBean.setActId(param.getActId());
                    result = this.bbr12140305Logic.save(hkBean);
                    result++;
                }
                if (result > NumberConst.IntDef.INT_ZERO) {
                    logger.info("冻品管家账号信息插入成功！");
                } else {
                    logger.info("冻品管家账号信息无数据同步！");
                }
            } else {
                logger.info("冻品管家账号信息无数据同步！");
            }
        } else {
            logger.info("冻品管家账号信息无数据同步！");
        }
    }

    /**
     * 更新冻品管家的分类管控信息
     *
     * @param param
     */
    private void updateHkInfos(BaseParam param) {
        //查询管家ID
        List<BBR121403Bean> houseList = this.bbr12140305Logic.findList(param);
        int result = 0;
        BBR121403Param ibr121410RsParam = new BBR121403Param();
        //通过管家ID列表获得管家信息
        RsRequest<BaseParam> rsRequest = new RsRequest<>();
        ibr121410RsParam.setHouseList(houseList);
        rsRequest.setSiteCode("1");
        rsRequest.setAuth("MSK00001");
        rsRequest.setLoginId("msk01");
        rsRequest.setParam(ibr121410RsParam);
        //调用通过管家ID获得管家信息接口
        String hkUrl = SystemServerManager.BsServerManage.getGetHouseInfoById();
        RsResponse<IBBR12140305RsResult> rsResponse = RestClientUtil.post(hkUrl, rsRequest, new TypeReference<RsResponse<IBBR12140305RsResult>>() {
        });
        if (rsResponse.getResult() != null) {
            if (!CollectionUtils.isEmpty(rsResponse.getResult().getHouseList())) {
                List<IBBR12140305RsBean> list = rsResponse.getResult().getHouseList();
                logger.info("更新冻品管家基本信息");
                for (int i = NumberConst.IntDef.INT_ZERO; i < list.size(); i++) {
                    Date currentDate = DateTimeUtil.getCustomerDate();
                    IBBR12140305RsBean bean = list.get(i);
                    bean.setUpdId(param.getUpdId());
                    bean.setUpdTime(currentDate);
                    //更新冻品管家的分类管控信息
                    result = this.bbr12140305Logic.modify(bean);
                    result++;
                }
                if (result > NumberConst.IntDef.INT_ZERO) {
                    logger.info("冻品管家分类管控信息更新成功！");
                } else {
                    logger.info("冻品管家分类管控信息无数据更新！");
                }
            } else {
                logger.info("冻品管家分类管控信息无数据更新！");
            }
        } else {
            logger.info("冻品管家分类管控信息无数据更新！");
        }
    }

    /**
     * 同步冻品管家与买家绑定最新关系
     *
     * @param param
     */
    private void updateHkBuyerRelationships(BaseParam param) {
        int result = 0;
        RsRequest<BaseParam> request = new RsRequest<>();
        IBBR12140306RsParam ibs2101107RsParam = new IBBR12140306RsParam();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        ibs2101107RsParam.setBuyerFlag("1");
        request.setParam(ibs2101107RsParam);
        /** Modif for Bug #3555 at 2016/11/03 by yuan_zhifei Start*/
        String url = SystemServerManager.BsServerManage.getSearchHouseInfo();
        //String url = "http://10.20.16.49:8181/msk-bs/api/bs/searchHouseInfo";
        /** Modif for Bug #3555 at 2016/11/03 by yuan_zhifei End*/
        RsResponse<IBBR12140306RsResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBBR12140306RsResult>>() {
        });
        if (!rsResponse.getStatus().equals("F")) {
            if (rsResponse.getResult() != null) {
                /** Modif for Bug #3555 at 2016/11/03 by yuan_zhifei Start*/
                if (!CollectionUtils.isEmpty(rsResponse.getResult().getSlBsBuyerList())) {
                    List<IBBR12140306RsBean> list = rsResponse.getResult().getSlBsBuyerList();
                /** Modif for Bug #3555 at 2016/11/03 by yuan_zhifei End*/
                    logger.info("清除冻品管家与买家绑定最新关系数据！");
                    this.bbr12140306Logic.remove(param);
                    for (int i = NumberConst.IntDef.INT_ZERO; i < list.size(); i++) {
                        Long maxId = commonLogic.maxId("br_hk_info", "ID");
                        IBBR12140306RsBean bean = list.get(i);
                        Date currentDate = DateTimeUtil.getCustomerDate();
                        bean.setRsId(maxId);
                        bean.setCrtTime(currentDate);
                        bean.setUpdTime(currentDate);
                        bean.setActTime(currentDate);
                        bean.setCrtId(param.getCrtId());
                        bean.setUpdId(param.getUpdId());
                        bean.setActId(param.getActId());
                        result = this.bbr12140306Logic.save(bean);
                        result++;
                    }
                }
                if (result > NumberConst.IntDef.INT_ZERO) {
                    logger.info("冻品管家与买家绑定最新关系数据同步成功！");
                } else {
                    logger.info("冻品管家与买家绑定最新关系无数据同步！");
                }
            } else {
                logger.info("冻品管家与买家绑定最新关系无数据同步！");
            }
        } else {
            logger.info("查询冻品管家与买家关系接口调用失败！不同步数据！");
        }
    }
}
