package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2101104Bean;
import com.msk.bs.bean.IBS2101107Bean;
import com.msk.bs.bean.IBS2101107RsParam;
import com.msk.bs.utils.CommRestUtil;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.buyers.bean.BuyerRelationResult;
import com.msk.buyers.bean.IBY121223Param;
import com.msk.buyers.bean.IBY121223Result;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsPageParam;
import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.ByBuyerPdCla;
import com.msk.core.entity.ByBuyerSalestarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gyh on 2016/3/28.
 */
public class IBS2101108RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        //获取所有买手ID
        static final String SQL_ID_GET_ALL_BUYERID = "getAllBuyerId";
    }

    @Autowired
    private BS2101104Logic bs2101104Logic;

    @Override
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findPageList(RsPageParam param, RsPageResult pageResult) {
        IBS2101107RsParam iBS2101107RsParam = (IBS2101107RsParam) param;
        if (param.getPageCount() == 0 || param.getPageNo() == 0) {
            param.setPaging(false);
        } else {
            param.setPaging(true);
        }

        IBY121223Param byParam = new IBY121223Param();

        //填充参数
        byParam.setLgcsAreaCode(iBS2101107RsParam.getProvinceCode());
        byParam.setCityCode(iBS2101107RsParam.getCityCode());
        byParam.setDistrictCode(iBS2101107RsParam.getDistrictCode());
        byParam.setBuyerAddr(iBS2101107RsParam.getBuyerAddr());
        byParam.setSalesTargetType(iBS2101107RsParam.getSalesTargetType());
        byParam.setClassCode(iBS2101107RsParam.getClassCode());
        byParam.setBusiTel(iBS2101107RsParam.getBusiTel());
        byParam.setEmployeeName(iBS2101107RsParam.getEmployeeName());
        byParam.setBuyerName(iBS2101107RsParam.getBuyerName());
        byParam.setBuyerShop(iBS2101107RsParam.getBuyerShop());
        byParam.setBuyerCode(iBS2101107RsParam.getBuyerCode());
        byParam.setBuyerType(iBS2101107RsParam.getBuyerType());
        byParam.setMarketId(iBS2101107RsParam.getMarketId());

        //查询公共买家池买家信息接口
        List<IBY121223Result> list = CommRestUtil.searchBuyer(byParam);

        List<IBY121223Result> newList = new ArrayList<IBY121223Result>();

        //获取所有买手ID
        List<BS2101104Bean> beanList = bs2101104Logic.findList(SqlId.SQL_ID_GET_ALL_BUYERID,new BaseParam());

        List<String> buyerIdList = new ArrayList<String>();

        for (BS2101104Bean bean :beanList) {
            buyerIdList.add(bean.getBuyerId());
        }

        for (IBY121223Result result : list) {
            if(!CollectionUtils.isEmpty(buyerIdList) && !buyerIdList.contains(result.getBuyerId())){
                newList.add(result);
            }
        }

        pageResult.setTotalCount(newList.size());
        pageResult.setTotalPage(newList.size(),iBS2101107RsParam.getPageCount());
        pageResult.setPageNo(iBS2101107RsParam.getPageNo());

        List<IBS2101107Bean> bsList = new ArrayList<IBS2101107Bean>();

        if(!CollectionUtils.isEmpty(newList)) {
            if (newList.size() != NumberConst.IntDef.INT_ZERO) {
                //分页
                if (newList.size() > iBS2101107RsParam.getPageCount()) {
                    int endPos = iBS2101107RsParam.getStartPos() + iBS2101107RsParam.getPageCount();
                    if (endPos > newList.size()) {
                        endPos = newList.size();
                    }
                    newList = newList.subList(iBS2101107RsParam.getStartPos(), endPos);
                }

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

                    bsList.add(bean);
                }

            }
        }

        List<String> buyIdList = new ArrayList<String>();

        for (IBS2101107Bean bean : bsList) {
            buyIdList.add(bean.getBuyerId());
        }

        BuyerRelationParam relationParam = new BuyerRelationParam();
        relationParam.setBuyerIdList(buyIdList);
        //0-代表加载，1-代表不加载
        relationParam.setIsLoadSalesTarget(0);
        relationParam.setIsLoadPdClass(0);

        BuyerRelationResult relationResult = CommRestUtil.searchBuyerInfo(relationParam);

        List<ByBuyerSalestarget> saleList = relationResult.getSaleList();
        List<ByBuyerPdCla> productList = relationResult.getProductList();

        for (IBS2101107Bean bean : bsList) {
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

        return (List<T>) bsList;
    }
}
