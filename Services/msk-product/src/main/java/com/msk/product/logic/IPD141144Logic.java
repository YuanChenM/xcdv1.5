package com.msk.product.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.order.bean.StockParam;
import com.msk.product.bean.IPD141144RsParam;
import com.msk.product.bean.IPD141144RsProductsResult;
import com.msk.product.bean.IPD141144RsResult;
import com.msk.product.utils.RestUtil;
import com.msk.stock.bean.Stock;
import com.msk.stock.bean.StockRsParam;
import com.msk.stock.bean.StockRsParamList;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 卖家产品库存查询
 * xhy
 */
@Service
public class IPD141144Logic extends BaseLogic {

    interface SqlId {
        static final String SQL_ID_SP = "findOneSp";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 原种种源信息同步接口
     *
     * @return
     */
    @Transactional(readOnly = true)
    public IPD141144RsResult findListSl(IPD141144RsParam param) {
        if (param == null) param = new IPD141144RsParam();

        /*if(param.getSellerType()== NumberConst.IntDef.INT_ONE){
            IPD141144RsResult result = super.findOne(param);
            if(result!=null&&result.getProducts().size()> NumberConst.IntDef.INT_ZERO)return result;*/

        StockRsParamList rsParam = new StockRsParamList();
        List<StockRsParam> pdList = new ArrayList<>();
        StockRsParam rsp = new StockRsParam();
        rsp.setSlCode(param.getSellerCode());
        rsp.setPdCode(param.getPdCode());
        rsp.setQueryType("sp");
        rsp.setSupplyPlatform(param.getPlatformType());
        rsp.setLgcsCode(String.valueOf(param.getDistrictCode()));
        pdList.add(rsp);
        rsParam.setPdList(pdList);
        IPD141144RsResult result = new IPD141144RsResult();
        List<IPD141144RsProductsResult> results = new ArrayList<>();
        if (param.getSellerType() == NumberConst.IntDef.INT_ONE) {
            List<Stock> stockList = RestUtil.findSlStock(rsParam);
            if(!CollectionUtils.isEmpty(stockList)){
                Stock isBean = stockList.get(NumberConst.IntDef.INT_ZERO);
                result.setSellerCode(isBean.getSlCode());
                result.setDistrictCode(StringUtils.hasLength(isBean.getLgcsCode()) ? Integer.valueOf(isBean.getLgcsCode()) : null);
                //订单供货量接口查询 TODO
                for (Stock bean : stockList) {
                    IPD141144RsProductsResult rsResult = new IPD141144RsProductsResult();
                    rsResult.setDistrictCode(null);
                    rsResult.setSlCode(null);
                    rsResult.setPdCode(bean.getPdCode());
                    if (null == bean.getStockQty() || bean.getStockQty().compareTo(BigDecimal.ZERO) == NumberConst.IntDef.INT_N_ONE){
                        rsResult.setStockCnt(BigDecimal.ZERO);
                    }else{
                        rsResult.setStockCnt(bean.getStockQty());
                    }

                    results.add(rsResult);
                }
                result.setProducts(results);
                return result;
            }
        } else if (param.getSellerType() == NumberConst.IntDef.INT_TWO) {
            List<Stock> stocks = RestUtil.findSPStock(rsParam);
            if(!CollectionUtils.isEmpty(stocks)) {
                Stock stock = stocks.get(NumberConst.IntDef.INT_ZERO);
                result.setSellerCode(stock.getSlCode());
                result.setDistrictCode(StringUtils.hasLength(stock.getLgcsCode()) ? Integer.valueOf(stock.getLgcsCode()) : null);
                for (Stock bean : stocks) {
                    IPD141144RsProductsResult rsResult = new IPD141144RsProductsResult();
                    rsResult.setDistrictCode(null);
                    rsResult.setSlCode(null);
                    rsResult.setPdCode(bean.getPdCode());
                    if (null == bean.getStockQty() || bean.getStockQty().compareTo(BigDecimal.ZERO) == NumberConst.IntDef.INT_N_ONE){
                        rsResult.setStockCnt(BigDecimal.ZERO);
                    }else{
                        rsResult.setStockCnt(stock.getStockQty());
                    }
                    results.add(rsResult);
                }
                result.setProducts(results);
                return result;
            }
        }
        return null;
    }

}
