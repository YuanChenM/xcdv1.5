package com.msk.bms.order.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.order.controller.common.SoRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RestResponse;
import com.msk.common.bean.RsResponse;
import com.msk.common.utils.DecimalUtil;
import com.msk.order.bean.param.ISO151414DistrictParam;
import com.msk.order.bean.param.SO15150801Param;
import com.msk.order.bean.result.DistrictResult;
import com.msk.order.bean.result.LgcsAreaBean;
import com.msk.order.bean.result.SO15150801PdStockListResult;
import com.msk.order.bean.result.SO15150801ProductStockBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * SO15150801_选择产品画面
 * Created by wang_jianzhou on 2016/8/1.
 */
@Controller
@RequestMapping("SO15150801")
public class SO15150801Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO15150801Controller.class);

    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(SO15150801ProductStockBean productStock, Model model){
        model.addAttribute("productStock",productStock);
        return "/order/SO15150801";
    }


    @RequestMapping(value = "search",method = RequestMethod.POST)
    public @ResponseBody
    PageResult<SO15150801PdStockListResult> search(BasePageParam pageParam, SO15150801ProductStockBean productStock){
        String lgcsName = getLgcsName(productStock);
        pageParam.setFilterObject("slCode",productStock.getSlCode());
        pageParam.setFilterObject("lgcsCode",productStock.getLgcsCode());
        //重新拼接销售平台
        pageParam.setFilterObject("salePlatform",productStock.getSalePlatform());
        RestResponse<SO15150801ProductStockBean> restResponse = SoRestUtil.searchSlProduct(pageParam);
        SO15150801ProductStockBean result = new SO15150801ProductStockBean();
        result = restResponse.getResult();
        PageResult<SO15150801PdStockListResult> pageResult = new PageResult<SO15150801PdStockListResult>();
        List<SO15150801PdStockListResult> pdStockList = null;
        if(null != result && null != result.getPdStockList()){
            pdStockList = setLgcsName(result.getPdStockList(),lgcsName);
            pageResult.setRecordsTotal(pdStockList.size());
        }else {
            pdStockList = new ArrayList<SO15150801PdStockListResult>();
            result = new SO15150801ProductStockBean();
            result.setPdStockList(pdStockList);
        }
        pageResult.setData(pdStockList);
        return pageResult;
    }

    @RequestMapping(value = "searchSp",method = RequestMethod.POST)
    public @ResponseBody PageResult<SO15150801PdStockListResult> searchSp(BasePageParam pageParam, SO15150801ProductStockBean productStock){
        logger.debug("数据加载");
        String lgcsName = getLgcsName(productStock);
        pageParam.getFilterMap().put("slCode", productStock.getSlCode());
        pageParam.getFilterMap().put("lgcsCode", productStock.getLgcsCode());
        //重新拼接销售平台
        pageParam.getFilterMap().put("salePlatform", productStock.getSalePlatform());
        pageParam.setFilterObject("lgcsName",productStock.getLgcsName());
        pageParam.setFilterObject("pdCode",productStock.getPdCode());
        pageParam.setFilterObject("pdName",productStock.getPdName());
        //调用接口
        RestResponse<SO15150801ProductStockBean> restResponse = SoRestUtil.searchSpProduct(pageParam);
        SO15150801ProductStockBean result = new SO15150801ProductStockBean();
        result = restResponse.getResult();
        PageResult<SO15150801PdStockListResult> pageResult = new PageResult<SO15150801PdStockListResult>();
        List<SO15150801PdStockListResult> pdStockList = null;
        if(null != result && null != result.getPdStockList()){
            pdStockList = setLgcsName(result.getPdStockList(),lgcsName);
            pageResult.setRecordsTotal(pdStockList.size());
        }else {
            pdStockList = new ArrayList<SO15150801PdStockListResult>();
            result = new SO15150801ProductStockBean();
            result.setPdStockList(pdStockList);
        }
        pageResult.setData(pdStockList);
        return pageResult;
    }

    @RequestMapping(value = "searchPdPrice",method = RequestMethod.POST)
    public @ResponseBody SO15150801PdStockListResult searchPdPrice(SO15150801ProductStockBean productStock){
        //调用价盘接口
        SO15150801Param param = new SO15150801Param();
        param.setLgcsCode(productStock.getLgcsCode());
        SO15150801PdStockListResult result = new SO15150801PdStockListResult();
        List<SO15150801PdStockListResult> resultList = new ArrayList<SO15150801PdStockListResult>();
        result.setPdCode(productStock.getPdCode());
        result.setOrderQty(productStock.getActiveQty());
        result.setLogiAreaCode(productStock.getLgcsCode());
        resultList.add(result);
        param.setProductList(resultList);
        RsResponse<SO15150801ProductStockBean> rsResponse = SoRestUtil.searchPdPrice(param);
        SO15150801ProductStockBean productPriceBean = new SO15150801ProductStockBean();
        SO15150801PdStockListResult newResult = new SO15150801PdStockListResult();
        List<SO15150801PdStockListResult> newResultList = null;
        if(null != rsResponse){
            productPriceBean = rsResponse.getResult();
        }
        if(null != productPriceBean && null != productPriceBean.getProductList()){
            newResultList = productPriceBean.getProductList();
            for(SO15150801PdStockListResult pdPriceResult : newResultList){
                newResult.setPrice(DecimalUtil.round(pdPriceResult.getPdBoxPrice(),NumberConst.IntDef.INT_TWO, BigDecimal.ROUND_HALF_UP));
                newResult.setPriceCyclePeriod(pdPriceResult.getPriceCycle());
                newResult.setOrderLevelName(pdPriceResult.getSellWayName());
            }
        }else {
            newResult = new SO15150801PdStockListResult();
        }
        return newResult;
    }


    /**
     * 获取产品销售区域
     *
     * @return
     */
    private String getLgcsName(SO15150801ProductStockBean productStock){
        ISO151414DistrictParam param = new ISO151414DistrictParam();
        param.setCityCode(productStock.getLgcsCode());
        RsResponse<DistrictResult> lgcsAreaBeanList = SoRestUtil.getLogisticsAreaList(param);
        List<LgcsAreaBean> districtList  = lgcsAreaBeanList.getResult().getLgcsAreaList();
        String lgcsName = districtList.get(0).getLgcsAreaName();
        return lgcsName;
    }

    /**
     * 设置产品销售区域
     *
     * @return
     */
    private List<SO15150801PdStockListResult> setLgcsName(List<SO15150801PdStockListResult> pdStockList,String lgcsName){
        for(SO15150801PdStockListResult pdStockListResult : pdStockList){
            pdStockListResult.setLgcsName(lgcsName);
        }
        return pdStockList;
    }
}
