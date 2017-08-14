package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101107Bean;
import com.msk.bs.bean.IBS2101107RsParam;
import com.msk.bs.bean.IBS2101107RsResult;
import com.msk.bs.utils.CommRestUtil;
import com.msk.buyers.bean.IBY121225Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
@Service
public class IBS2101107RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private BS2101103Logic bS2101103Logic;

    /**
     * 查询买手冻品管家的买家信息
     *
     * @param param
     * @return
     */
    public IBS2101107RsResult findSlExclusive(RsRequest<IBS2101107RsParam> param) {
        IBS2101107RsParam iBS2101107RsParam = param.getParam();
        IBS2101107RsResult iBS2101107RsResult = new IBS2101107RsResult();
        BaseParam params = new BaseParam();
        params.setFilter("slCode", StringUtil.toSafeString(iBS2101107RsParam.getSlCode()));
        params.setFilter("buyerId", StringUtil.toSafeString(iBS2101107RsParam.getBuyerId()));
        List<IBS2101107Bean> slBsBuyerList = super.findList(params);
        for (IBS2101107Bean slBsBuyer : slBsBuyerList) {
            slBsBuyer.setBuyerFlag(param.getParam().getBuyerFlag());
        }
        iBS2101107RsResult.setSlBuyerList(slBsBuyerList);
        return iBS2101107RsResult;
    }

    /**ADD  for改善 #3519 查询买手冻品管家的买家信息接口，查询条件不起作用 zhukai start**/
    /**
     * 买手下管家的买家信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public IBS2101107RsResult findBuyersInfo(IBS2101107RsParam param){
        IBS2101107RsResult rsResult = new IBS2101107RsResult();
        if (param.getPageCount() == 0 || param.getPageNo() == 0) {
            param.setPaging(false);
        } else {
            param.setPaging(true);
        }
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("applyStatus",param.getApplyStatus());
        baseParam.getFilterMap().put("slCode",param.getSlCode());
        baseParam.getFilterMap().put("houseCode",param.getHouseCode());
        baseParam.getFilterMap().put("buyerId", param.getBuyerId());
        // 查询该管家下对应的买家信息
        List<IBS2101107Bean> list = this.findList(baseParam);
        List<String> buyerIdList = new ArrayList<>();
        for (IBS2101107Bean  bean : list){
            buyerIdList.add(bean.getBuyerId());
        }
        if(!CollectionUtils.isEmpty(buyerIdList)){
            IBY121225Param rsParam = new IBY121225Param();
            rsParam.setPageNo(param.getPageNo());
            rsParam.setPageCount(param.getPageCount());
            rsParam.setPaging(param.isPaging());
            rsParam.setBuyerIdList(buyerIdList);
            rsParam.setSalesTargetType(param.getSalesTargetType());
            rsParam.setClassCode(param.getClassCode());
            rsParam.setBuyerCode(param.getBuyerCode());
            rsParam.setBuyerName(param.getBuyerName());
            rsParam.setProvinceCode(param.getProvinceCode());
            rsParam.setBuyerAddr(param.getBuyerAddr());
            rsParam.setBusiTel(param.getBusiTel());
            rsParam.setCityCode(param.getCityCode());
            rsParam.setDistrictCode(param.getDistrictCode());
            rsParam.setEmployeeName(param.getEmployeeName());
            rsParam.setBuyerShop(param.getBuyerShop());
            rsParam.setBuyerType(param.getBuyerType());
            rsParam.setMarketId(param.getMarketId());
            // 调用买家接口获取买家信息
            rsResult =  CommRestUtil.searchExclusive(rsParam);
        }
        if(null !=rsResult && !CollectionUtils.isEmpty(rsResult.getSlBuyerList())){
            for (IBS2101107Bean resultBean : rsResult.getSlBuyerList()){
                if(!CollectionUtils.isEmpty(list)){
                    for (IBS2101107Bean  ibs2101107Bean : list){
                        if(resultBean.getBuyerId().equals(ibs2101107Bean.getBuyerId())){
                            resultBean.setSlCode(ibs2101107Bean.getSlCode());
                            resultBean.setHouseCode(ibs2101107Bean.getHouseCode());
                            resultBean.setBuyerFlag(param.getBuyerFlag());
                            resultBean.setStartTime(ibs2101107Bean.getStartTime());
                            resultBean.setEndTime(ibs2101107Bean.getEndTime());
                            resultBean.setApplyTime(ibs2101107Bean.getApplyTime());
                            resultBean.setApplyStatus(ibs2101107Bean.getApplyStatus());
                            resultBean.setApplyStatusName(ibs2101107Bean.getApplyStatusName());
                            resultBean.setApplySide(ibs2101107Bean.getApplySide());
                            resultBean.setVer(ibs2101107Bean.getVer());
                            break;
                        }
                    }
                }
            }
            rsResult.setSlBuyerList(rsResult.getSlBuyerList());
        }
        return  rsResult;
    }
    /**ADD for 改善 #3519 查询买手冻品管家的买家信息接口，查询条件不起作用 zhukai end**/

   /* @Override
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findPageList(RsPageParam param, RsPageResult pageResult) {
        IBS2101107RsParam iBS2101107RsParam = (IBS2101107RsParam) param;
        if (param.getPageCount() == 0 || param.getPageNo() == 0) {
            param.setPaging(false);
        } else {
            param.setPaging(true);
        }

        param.setFilter("slCode", iBS2101107RsParam.getSlCode());
        param.setFilter("buyerId", iBS2101107RsParam.getBuyerId());
        param.setFilter("buyerFlag", iBS2101107RsParam.getBuyerFlag());
        param.setFilter("houseCode", iBS2101107RsParam.getHouseCode());
        param.setFilter("applyStatus", iBS2101107RsParam.getApplyStatus());

        List<IBS2101107Bean> beans = bS2101103Logic.findPageList(param, pageResult);

        IBY121225Param rsParam = new IBY121225Param();
        rsParam.setBuyerList(beans);
        rsParam.setSalesTargetType(iBS2101107RsParam.getSalesTargetType());
        rsParam.setClassCode(iBS2101107RsParam.getClassCode());
        rsParam.setBuyerCode(iBS2101107RsParam.getBuyerCode());
        rsParam.setBuyerName(iBS2101107RsParam.getBuyerName());
        rsParam.setLgcsAreaCode(iBS2101107RsParam.getProvinceCode());
        rsParam.setBuyerAddr(iBS2101107RsParam.getBuyerAddr());
        rsParam.setBusiTel(iBS2101107RsParam.getBusiTel());
        //rsParam.setProvinceCode(iBS2101107RsParam.getCityCode());
        rsParam.setCityCode(iBS2101107RsParam.getCityCode());
        rsParam.setDistrictCode(iBS2101107RsParam.getDistrictCode());
        rsParam.setEmployeeName(iBS2101107RsParam.getEmployeeName());
        rsParam.setBuyerShop(iBS2101107RsParam.getBuyerShop());
        rsParam.setBuyerType(iBS2101107RsParam.getBuyerType());
        rsParam.setMarketId(iBS2101107RsParam.getMarketId());

        List<IBS2101107Bean> list = CommRestUtil.searchExclusive(rsParam);

        List<String> buyerIdList = new ArrayList<String>();

        for (IBS2101107Bean bean : list) {
            buyerIdList.add(bean.getBuyerId());
        }

        BuyerRelationParam relationParam = new BuyerRelationParam();
        relationParam.setBuyerIdList(buyerIdList);
        //0-代表加载，1-代表不加载
        relationParam.setIsLoadSalesTarget(0);
        relationParam.setIsLoadPdClass(0);

        BuyerRelationResult relationResult = CommRestUtil.searchBuyerInfo(relationParam);

        List<ByBuyerSalestarget> saleList = relationResult.getSaleList();
        List<ByBuyerPdCla> productList = relationResult.getProductList();

        for (IBS2101107Bean bean : list) {
            //查询买家产品销售对象表
            List<ByBuyerSalestarget> byBuyerSalestargets = new ArrayList<ByBuyerSalestarget>();
            //查询买家经营产品类别表
            List<ByBuyerPdCla> byBuyerPdClas = new ArrayList<ByBuyerPdCla>();

            if(!CollectionUtils.isEmpty(saleList)){
                for(ByBuyerSalestarget byBuyerSalestarget : saleList){
                    if(byBuyerSalestarget != null && byBuyerSalestarget.getBuyerId().equals(bean.getBuyerId())){
                        byBuyerSalestargets.add(byBuyerSalestarget);
                    }
                }
            }
            if(!CollectionUtils.isEmpty(productList)){
                for(ByBuyerPdCla byBuyerPdCla : productList){
                    if(byBuyerPdCla != null && byBuyerPdCla.getBuyerId().equals(bean.getBuyerId())){
                        byBuyerPdClas.add(byBuyerPdCla);
                    }
                }
            }

            bean.setByBuyerSalestargetList(byBuyerSalestargets);
            bean.setByBuyerPdClaList(byBuyerPdClas);
        }

        return (List<T>) list;
    }*/

}


