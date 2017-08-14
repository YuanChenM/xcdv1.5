package com.msk.bs.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.*;
import com.msk.bs.utils.CommRestUtil;
import com.msk.buyers.bean.IBY121223Param;
import com.msk.buyers.bean.IBY121223Result;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlBsBuyerHis;

/**
 * 买手店管家会员列表
 * @author cx
 */
@Service
public class BS2101104Logic extends BaseLogic {
    private Logger logger = LoggerFactory.getLogger(BS2101104Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
    super.setBaseDao(baseDao);
}

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId {
        //获取所有买手ID
        static final String SQL_ID_GET_ALL_BUYERID = "getAllBuyerId";
        // 校验买家是否已经绑定管家
        static final String SQL_FIND_HOUSE_INFO_BY_BUYERID = "findHouseInfoByBuyerId";
        //通过BUYER_ID查询绑定信息
        static final String SQL_FIND_BS_BUYER_BY_BUYERID = "findBsBuyerByBuyerId";
        //修改delflag
        static final String SQL_UPDATE_BS_BUYER_BY_BUYERID = "updateBuyerByBuyerId";
        //插入履历
        static final String SQL_SAVE_BS_BUYER_HIS = "saveBuyerHis";

        static final String SQL_FIND_BUYER_BY_BUYERID = "findBuyerByBuyerId";

    }

    /**
     * 公共买家池列表
     * @param param param
     * @return 结果
     */
    @Transactional(readOnly = true)
    public PageResult<IBS2101107Bean> findBS2101041List(BasePageParam param){
        PageResult<IBS2101107Bean> pageResult = new PageResult<IBS2101107Bean>();

        IBY121223Param byParam = new IBY121223Param();
        //填充参数
        byParam.setBuyerAddr(StringUtil.toSafeString(param.getFilterMap().get("buyerAddr")));
        byParam.setMarketingsStatusName(StringUtil.toSafeString(param.getFilterMap().get("marketingsStatusName")));
        byParam.setBuyerCode(StringUtil.toSafeString(param.getFilterMap().get("buyerCode")));
        byParam.setAccountName(StringUtil.toSafeString(param.getFilterMap().get("accountName")));
        byParam.setDistrictCode(StringUtil.toSafeString(param.getFilterMap().get("districtCode")));
        byParam.setProvinceCode(StringUtil.toSafeString(param.getFilterMap().get("provinceCode")));
        byParam.setCityCode(StringUtil.toSafeString(param.getFilterMap().get("cityCode")));
        byParam.setSuperiorName(StringUtil.toSafeString(param.getFilterMap().get("superiorName")));
        byParam.setTelNo(StringUtil.toSafeString(param.getFilterMap().get("telNo")));
        byParam.setBuyerName(StringUtil.toSafeString(param.getFilterMap().get("buyerName")));
        byParam.setBusiTel(StringUtil.toSafeString(param.getFilterMap().get("busiTel")));

        //查询公共买家池买家信息接口
        List<IBY121223Result> list = CommRestUtil.searchBuyer(byParam);

        List<IBY121223Result> newList = new ArrayList<IBY121223Result>();

        //获取所有买手ID
        List<BS2101104Bean> beanList = this.findList(SqlId.SQL_ID_GET_ALL_BUYERID,new BaseParam());

        List<String> buyIdList = new ArrayList<String>();

        for (BS2101104Bean bean :beanList) {
            buyIdList.add(bean.getBuyerId());
        }

        for (IBY121223Result result : list) {
            if(!CollectionUtils.isEmpty(buyIdList) && !buyIdList.contains(result.getBuyerId())){
                newList.add(result);
            }
        }

        if(!CollectionUtils.isEmpty(newList)){
            pageResult.setRecordsTotal(newList.size());
            if (pageResult.getRecordsTotal() != NumberConst.IntDef.INT_ZERO) {
                //分页
                if(newList.size() > param.getPageSize()){
                    int endPos = param.getStartPos() + param.getPageSize();
                    if(endPos > newList.size()){
                        endPos = newList.size();
                    }
                    newList = newList.subList(param.getStartPos(),endPos);
                }

                List<IBS2101107Bean> bsList = getBuyerPoolInfo(newList);

                pageResult.setData(bsList);
            } else {
                pageResult.setData(new ArrayList<IBS2101107Bean>());
            }
        }else if(CollectionUtils.isEmpty(beanList)){
            pageResult.setRecordsTotal(list.size());
            if (pageResult.getRecordsTotal() != NumberConst.IntDef.INT_ZERO) {
                //分页
                if (list.size() > param.getPageSize()) {
                    int endPos = param.getStartPos() + param.getPageSize();
                    if (endPos > list.size()) {
                        endPos = list.size();
                    }
                    list = list.subList(param.getStartPos(), endPos);
                }
                List<IBS2101107Bean> bsList = getBuyerPoolInfo(list);
                pageResult.setData(bsList);
            }else {
                pageResult.setData(new ArrayList<IBS2101107Bean>());
            }
        }else{
            pageResult.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
            pageResult.setData(new ArrayList<IBS2101107Bean>());
        }

        return pageResult;
    }

    private List<IBS2101107Bean> getBuyerPoolInfo(List<IBY121223Result> newList) {
        List<IBS2101107Bean> bsList = new ArrayList<IBS2101107Bean>();

        for (IBY121223Result result : newList) {
            IBS2101107Bean bean = new IBS2101107Bean();
            bean.setBuyerId(result.getBuyerId());
            bean.setBuyerCode(result.getBuyerCode());
            bean.setBuyerName(result.getBuyerName());
            bean.setBuyerShop(result.getBuyerShop());
            bean.setLgcsAreaCode(result.getLgcsAreaCode());
            bean.setLgcsAreaName(result.getLgcsAreaName());
            bean.setProvinceCode(result.getProvinceCode());
            bean.setProvinceName(result.getProvinceName());
            bean.setCityCode(result.getCityCode());
            bean.setCityName(result.getCityName());
            bean.setDistrictCode(result.getDistrictCode());
            bean.setDistrictName(result.getDistrictName());
            bean.setBuyerAddr(result.getBuyerAddr());
            bean.setDomainName(result.getDomainName());
            bean.setSuperiorName(result.getSuperiorName());
            bean.setSuperiorType(result.getSuperiorType());
            bean.setAccountName(result.getAccountName());
            bean.setBusiTel(result.getBusiTel());
            bean.setTelNo(result.getTelNo());
            bean.setMarketingsStatusName(result.getMarketingsStatusName());
            bean.setEmployeeName(result.getEmployeeName());
            if(StringUtil.isNullOrEmpty(result.getMarketName())){
                bean.setMarketName(StringConst.EMPTY);
            }else{
                bean.setMarketName(result.getMarketName());
            }
            bsList.add(bean);
        }
        return bsList;
    }


    /**
     * 最新版查询公共买家池信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<IBS2101107Bean> findPublicBuyerPoolInfo(BasePageParam param){
        PageResult<IBS2101107Bean> pageResult = new PageResult<IBS2101107Bean>();

        publicBuyerPoolRsParam poolRsParam = new  publicBuyerPoolRsParam();
        poolRsParam.setPageCount(param.getPageSize());
        poolRsParam.setPageNo((param.getStartPos() / param.getPageSize()) + 1);
        poolRsParam.setLgcsAreaCode(StringUtil.toSafeString(param.getFilterMap().get("lgcsAreaCode")));
        poolRsParam.setCityCode(StringUtil.toSafeString(param.getFilterMap().get("cityCode")));
        poolRsParam.setDistrictCode(StringUtil.toSafeString(param.getFilterMap().get("districtCode")));
        poolRsParam.setBuyerAddr(StringUtil.toSafeString(param.getFilterMap().get("buyerAddr")));
        poolRsParam.setBusiTel(StringUtil.toSafeString(param.getFilterMap().get("busiTel")));
        poolRsParam.setBuyerTypeName(StringUtil.toSafeString(param.getFilterMap().get("superiorName")));
        poolRsParam.setBuyerCode(StringUtil.toSafeString(param.getFilterMap().get("buyerCode")));
        /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc start */
        poolRsParam.setMarketingsStatus(StringUtil.toSafeString(param.getFilterMap().get("marketingsStatusName")));
        /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc end */
        poolRsParam.setAccountName(StringUtil.toSafeString(param.getFilterMap().get("accountName")));
        poolRsParam.setBuyerTel(StringUtil.toSafeString(param.getFilterMap().get("buyerTel")));
        poolRsParam.setBuyerName(StringUtil.toSafeString(param.getFilterMap().get("buyerName")));
        //查询公共买家池买家信息接口
        PageResult<publicBuyerPoolRsBean> list = CommRestUtil.searchPubliBuyerPool(poolRsParam);

        List<IBS2101107Bean> bsList = new ArrayList<IBS2101107Bean>();
        if(!CollectionUtils.isEmpty(list.getData())){
            for (publicBuyerPoolRsBean buyerPoolRsBean : list.getData()){
                IBS2101107Bean bean = new IBS2101107Bean();
                bean.setBuyerId(buyerPoolRsBean.getBuyerId());
                bean.setBuyerCode(buyerPoolRsBean.getBuyerCode());
                bean.setBuyerName(buyerPoolRsBean.getBuyerName());
                bean.setBuyerShop(buyerPoolRsBean.getBuyerShop());
                bean.setLgcsAreaCode(buyerPoolRsBean.getLgcsAreaCode());
                bean.setLgcsAreaName(buyerPoolRsBean.getLgcsAreaName());
                bean.setProvinceCode(buyerPoolRsBean.getProvinceCode());
                bean.setProvinceName(buyerPoolRsBean.getProvinceName());
                bean.setCityCode(buyerPoolRsBean.getCityCode());
                bean.setCityName(buyerPoolRsBean.getCityName());
                bean.setDistrictCode(buyerPoolRsBean.getDistrictCode());
                bean.setDistrictName(buyerPoolRsBean.getDistrictName());
                bean.setBuyerAddr(buyerPoolRsBean.getBuyerAddr());
                bean.setDomainName(buyerPoolRsBean.getDomainName());
                bean.setSuperiorName(buyerPoolRsBean.getBuyerTypeName());
                bean.setSuperiorType(buyerPoolRsBean.getBuyerType());
                bean.setAccountName(buyerPoolRsBean.getAccountName());
                //营业电话
                bean.setBusiTel(buyerPoolRsBean.getBusiTel());
                bean.setTelNo(buyerPoolRsBean.getTelNo());
                //买家电话
                bean.setBuyerTel(buyerPoolRsBean.getBuyerTel());
                bean.setMarketingsStatusName(buyerPoolRsBean.getMarketingsStatusName());
                bean.setEmployeeName(buyerPoolRsBean.getEmployeeName());
                bean.setMarketName(buyerPoolRsBean.getMarketName());
                bsList.add(bean);
            }
            //查询绑定的冻品管家信息
            if(!CollectionUtils.isEmpty(bsList)){
                BaseParam baseParam = new BaseParam();
                baseParam.setFilterObject("buyerList",bsList);
                List<IBS2101107Bean> bsBuyerList = this.findList(SqlId.SQL_FIND_BS_BUYER_BY_BUYERID,baseParam);

                for(int i=0;i<bsList.size();i++){
                    boolean isFlag = false;
                    for(IBS2101107Bean ibs2101107Bean : bsBuyerList){
                        if(bsList.get(i).getBuyerId().equals(ibs2101107Bean.getBuyerId())){
                            if(!StringUtil.isNullOrEmpty(ibs2101107Bean.getHouseAccount())){
                                isFlag = true;
                                bsList.get(i).setHouseShowName(ibs2101107Bean.getHouseShowName());
                            }
                        }
                    }
                    if(isFlag){
                        bsList.get(i).setBindStatus("0");
                    }else {
                        bsList.get(i).setBindStatus("1");
                        bsList.get(i).setHouseShowName("");
                    }
                }
            }
            pageResult.setRecordsTotal(list.getRecordsTotal());
            pageResult.setData(bsList);
        }else{
            pageResult.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
            pageResult.setData(new ArrayList<IBS2101107Bean>());
        }
        return pageResult;
    }



    /**
     * 查寻买家信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<BS2102126Bean> findBuyerList(BasePageParam param){
        PageResult<BS2102126Bean> pageResult = new PageResult<BS2102126Bean>();
        BS2102126Param bs2102126Param = new BS2102126Param();
        bs2102126Param.setPageCount(param.getPageSize());
        bs2102126Param.setPageNo((param.getStartPos() / param.getPageSize()) + 1);

        bs2102126Param.setLgcsAreaCode(StringUtil.toSafeString(param.getFilterMap().get("lgcsAreaCode")));
        bs2102126Param.setCityCode(StringUtil.toSafeString(param.getFilterMap().get("cityCode")));
        bs2102126Param.setDistrictCode(StringUtil.toSafeString(param.getFilterMap().get("districtCode")));
        bs2102126Param.setBuyerAddr(StringUtil.toSafeString(param.getFilterMap().get("buyerAddr")));
        //买家账号
        bs2102126Param.setAccountName(StringUtil.toSafeString(param.getFilterMap().get("accountName")));
        //注册手机
        bs2102126Param.setTelNo(StringUtil.toSafeString(param.getFilterMap().get("telNo")));
        //买家编码
        bs2102126Param.setBuyerCode(StringUtil.toSafeString(param.getFilterMap().get("buyerCode")));
        //买家名
        bs2102126Param.setBuyerName(StringUtil.toSafeString(param.getFilterMap().get("buyerName")));
        //买家类型
        bs2102126Param.setSuperiorName(StringUtil.toSafeString(param.getFilterMap().get("superiorName")));
        /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc 2016/11/1 start */
        //上线状态
        bs2102126Param.setMarketingsStatus(StringUtil.toSafeString(param.getFilterMap().get("marketingsStatusName")));
        /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc 2016/11/1 end */
        //联系电话
        bs2102126Param.setBusiTel(StringUtil.toSafeString(param.getFilterMap().get("busiTel")));


        PageResult<BS2102126Bean> list = CommRestUtil.searchBuyers(bs2102126Param);

        if(!CollectionUtils.isEmpty(list.getData())){
            List<BS2102126Bean> bsList = list.getData();
            //查询绑定的冻品管家信息
            BaseParam baseParam = new BaseParam();
            baseParam.setFilterObject("buyerList",bsList);
            List<IBS2101107Bean> bsBuyerList = this.findList(SqlId.SQL_FIND_BS_BUYER_BY_BUYERID,baseParam);

            for(int i=0;i<bsList.size();i++){
                boolean isFlag = false;
                for(IBS2101107Bean ibs2101107Bean : bsBuyerList){
                    if(bsList.get(i).getBuyerId().equals(ibs2101107Bean.getBuyerId())){
                        if(!StringUtil.isNullOrEmpty(ibs2101107Bean.getHouseAccount())){
                            isFlag = true;
                            bsList.get(i).setHouseShowName(ibs2101107Bean.getHouseShowName());
                        }
                    }
                }
                if(isFlag){
                    bsList.get(i).setBindStatus("0");
                }else {
                    bsList.get(i).setBindStatus("1");
                    bsList.get(i).setHouseShowName("");
                }
                toSafeString(bsList.get(i));
            }
            pageResult.setRecordsTotal(list.getRecordsTotal());
            pageResult.setData(bsList);
        }else{
            pageResult.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
            pageResult.setData(new ArrayList<BS2102126Bean>());
        }
        return pageResult;
    }


    private void toSafeString(BS2102126Bean bean){
        bean.setBuyerId(StringUtil.toSafeString(bean.getBuyerId()));
        bean.setBuyerCode(StringUtil.toSafeString(bean.getBuyerCode()));
        bean.setBuyerName(StringUtil.toSafeString(bean.getBuyerName()));
        bean.setTelNo(StringUtil.toSafeString(bean.getTelNo()));
        bean.setAccountName(StringUtil.toSafeString(bean.getAccountName()));
        bean.setLgcsAreaCode(StringUtil.toSafeString(bean.getLgcsAreaCode()));
        bean.setLgcsAreaName(StringUtil.toSafeString(bean.getLgcsAreaName()));
        bean.setCityCode(StringUtil.toSafeString(bean.getCityCode()));
        bean.setCityName(StringUtil.toSafeString(bean.getCityName()));
        bean.setDistrictCode(StringUtil.toSafeString(bean.getDistrictCode()));
        bean.setDistrictName(StringUtil.toSafeString(bean.getDistrictName()));
        bean.setBuyerAddr(StringUtil.toSafeString(bean.getBuyerAddr()));
        bean.setBusiTel(StringUtil.toSafeString(bean.getBusiTel()));
        bean.setBossName(StringUtil.toSafeString(bean.getBossName()));
        bean.setStoreNo(StringUtil.toSafeString(bean.getStoreNo()));
        bean.setSuperiorName(StringUtil.toSafeString(bean.getSuperiorName()));
        bean.setMarketId(StringUtil.toSafeString(bean.getMarketId()));
        bean.setMarketName(StringUtil.toSafeString(bean.getMarketName()));
        bean.setMarketingsStatusCode(StringUtil.toSafeString(bean.getMarketingsStatusCode()));
        bean.setSalestarget(StringUtil.toSafeString(bean.getSalestarget()));
        bean.setMarketingsStatusName(StringUtil.toSafeString(bean.getMarketingsStatusName()));
        bean.setHouseShowName(StringUtil.toSafeString(bean.getHouseShowName()));
        bean.setBindStatus(StringUtil.toSafeString(bean.getBindStatus()));
    }


    /**
     * 解除冻品管家
     * @param bean
     */
    @Transactional
    public int  unbundHouseAccount(IBS2101107Bean bean){
        BaseParam baseParam = new BaseParam();
        List<IBS2101107Bean> bsList = new ArrayList<>();
        bsList.add(bean);
        baseParam.setFilterObject("buyerList",bsList);
        List<IBS2101107Bean> bsBuyerList = this.findList(SqlId.SQL_FIND_BUYER_BY_BUYERID,baseParam);
        if(!CollectionUtils.isEmpty(bsBuyerList)){
            //修改delFlag 为 1
            logger.info("解除关系数量为"+bsBuyerList.size());
            int updateResult = this.modify(SqlId.SQL_UPDATE_BS_BUYER_BY_BUYERID,baseParam);
            logger.info("成功解除关系数量为"+updateResult);
            //插入履历信息
            SlBsBuyerHis buyerHis = null;
            List<SlBsBuyerHis> buyerHisList = new ArrayList<>();
            for(IBS2101107Bean ibs2101107Bean : bsBuyerList){
                buyerHis = new SlBsBuyerHis();
                buyerHis.setHisId(commonLogic.maxId("SL_BS_BUYER_HIS","HIS_ID"));
                buyerHis.setSlCode(ibs2101107Bean.getSlCode());
                buyerHis.setHouseAccount(ibs2101107Bean.getHouseCode());
                buyerHis.setBuyerId(ibs2101107Bean.getBuyerId());
                buyerHis.setStartTime(ibs2101107Bean.getStartTime());
                buyerHis.setEndTime(ibs2101107Bean.getEndTime());
                buyerHis.setApplySide(ibs2101107Bean.getApplySide());
                buyerHis.setApplyStatus(ibs2101107Bean.getApplyStatus());
                buyerHis.setApplyTime(ibs2101107Bean.getApplyTime());
                buyerHis.setBuyershopReason("2");
                buyerHis.setDelFlg("0");
                buyerHis.setCrtId(ibs2101107Bean.getCrtId());
                buyerHis.setCrtTime(ibs2101107Bean.getCrtTime() == null ? new Date() : ibs2101107Bean.getCrtTime());
                buyerHis.setUpdId(bean.getUpdId());
                buyerHis.setUpdTime(new Date());
                buyerHis.setActId(ibs2101107Bean.getActId());
                buyerHis.setActTime(ibs2101107Bean.getActTime() == null ? new Date() : ibs2101107Bean.getActTime());
                buyerHis.setVer(ibs2101107Bean.getVer() == null ? 0 : ibs2101107Bean.getVer());
                buyerHisList.add(buyerHis);
            }
            int saveResult = 0;
            if(!CollectionUtils.isEmpty(buyerHisList)){
                logger.info("插入履历数量为"+buyerHisList.size());
                saveResult = this.batchSave(buyerHisList);
                logger.info("成功插入履历数量为"+saveResult);
            }
            //同步解除买家接口数据
            this.asyncUnBindDate(buyerHisList);
            return updateResult+saveResult;
        }
        return 0;

    }



    //同步解除买家接口数据
    private void asyncUnBindDate(List<SlBsBuyerHis> slBsBuyerList){
        logger.info("同步解除买家接口数据开始");
        if(!CollectionUtils.isEmpty(slBsBuyerList)){
            IBS2101140RsParam ibs2101140RsParam = new IBS2101140RsParam();
            List<IBS2101140Bean>  relationList = new ArrayList<>();
            IBS2101140Bean ibs2101140Bean = null;
            for(SlBsBuyerHis bsBuyer : slBsBuyerList){
                ibs2101140RsParam.setSlCode(bsBuyer.getSlCode());
                ibs2101140RsParam.setHouseCode(bsBuyer.getHouseAccount());
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

    /**
     * 校验该买家是否已经绑定了管家
     * @param buyerId
     * @return
     */
    @Transactional(readOnly = true)
    public int checkHouseInfoByBuyerId(String buyerId){
        BaseParam param = new BaseParam();
        param.getFilterMap().put("buyerId",buyerId);
       return this.getCount(SqlId.SQL_FIND_HOUSE_INFO_BY_BUYERID,param);
    }
}

