package com.msk.br.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.br.bean.IBR121308RsParam;
import com.msk.br.bean.IBR121308RsBean;
import com.msk.br.bean.IBR121308RsPageResult;
import com.msk.br.logic.IBR121308Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询公共买家池买家信息接口
 * Created by tao_zhifa on 2016/8/19.
 */

@RestController
public class IBR121308RsController extends BaseRsController{

    private static Logger logger = LoggerFactory.getLogger(IBR121306RsController.class);
    @Autowired
    private IBR121308Logic ibr121308Logic;

    @RequestMapping(value = "/br/publiBuyerPoolInformation/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse <IBR121308RsPageResult> searchPubliBuyerPoolInformation (@RequestBody RsRequest<IBR121308RsParam> rsParam){
        RsResponse <IBR121308RsPageResult> rs = new RsResponse<>();
        IBR121308RsPageResult ibr121308RsPageResult = new IBR121308RsPageResult();
        List<IBR121308RsBean> buyerList = new ArrayList<>();
        rsParam.getParam().setPaging(true);

        rsParam.getParam().setMarketingsStatusName(DbUtils.buildLikeCondition(rsParam.getParam().getMarketingsStatusName(), DbUtils.LikeMode.PARTIAL));
        rsParam.getParam().setBuyerTypeName(DbUtils.buildLikeCondition(rsParam.getParam().getBuyerTypeName(), DbUtils.LikeMode.PARTIAL));
        rsParam.getParam().setAccountName(DbUtils.buildLikeCondition(rsParam.getParam().getAccountName(), DbUtils.LikeMode.PARTIAL));
        rsParam.getParam().setBuyerTel(DbUtils.buildLikeCondition(rsParam.getParam().getBuyerTel(), DbUtils.LikeMode.PARTIAL));
        rsParam.getParam().setBusiTel(DbUtils.buildLikeCondition(rsParam.getParam().getBusiTel(), DbUtils.LikeMode.PARTIAL));
        rsParam.getParam().setRecPerTel(DbUtils.buildLikeCondition(rsParam.getParam().getRecPerTel(), DbUtils.LikeMode.PARTIAL));
        rsParam.getParam().setBuyerName(DbUtils.buildLikeCondition(rsParam.getParam().getBuyerName(), DbUtils.LikeMode.PARTIAL));
        rsParam.getParam().setEmployeeName(DbUtils.buildLikeCondition(rsParam.getParam().getEmployeeName(), DbUtils.LikeMode.PARTIAL));
        rsParam.getParam().setBuyerCode(DbUtils.buildLikeCondition(rsParam.getParam().getBuyerCode(), DbUtils.LikeMode.PARTIAL));
        rsParam.getParam().setBuyerAddr(DbUtils.buildLikeCondition(rsParam.getParam().getBuyerAddr(), DbUtils.LikeMode.PARTIAL));
        rsParam.getParam().setBuyerShop(DbUtils.buildLikeCondition(rsParam.getParam().getBuyerShop(), DbUtils.LikeMode.PARTIAL));
        if(rsParam.getParam().getMarketingsStatus() != null && rsParam.getParam().getMarketingsStatus() != ""){
            String [] marketStatusArray = rsParam.getParam().getMarketingsStatus().split(",");
            rsParam.getParam().setMarketingsStatusArray(marketStatusArray);
        }
        buyerList = ibr121308Logic.findList(rsParam.getParam());
        int count = ibr121308Logic.getPageCount(rsParam.getParam());
        Map<String, String> saleType = CodeMasterManager.findCodeMasterMap(BuyersConstant.SalesTarget.Type);
        Map<String,String> map = new HashMap<>();
        for(String key :saleType.keySet()){
            map.put(saleType.get(key),key);
        }

        if(rsParam.getParam().getIsAll() !=null && rsParam.getParam().getIsAll()!="" && (rsParam.getParam().getIsAll().equals("1"))){

            for(int i=0;i<buyerList.size();i++){
                List<IBR121308RsBean> byBuyerPdClaList = new ArrayList<>();
                List<IBR121308RsBean> byBuyerSalestargetList = new ArrayList<>();
                IBR121308RsParam baseParam = new IBR121308RsParam();
                baseParam.setBuyerId(buyerList.get(i).getBuyerId());
                byBuyerPdClaList = ibr121308Logic.findbyBuyerPdClaList(baseParam);

                if(!StringUtil.isEmpty(buyerList.get(i).getSalestarget())){
                    String saleArray [] = buyerList.get(i).getSalestarget().split(",");
                    for(String saleName:saleArray){
                        IBR121308RsBean rsBean = new IBR121308RsBean();
                        if(!StringUtil.isEmpty(saleName)){
                            rsBean.setSalesTargetName(saleName);
                            String saleCode = map.get(saleName);
                            if(StringUtil.isEmpty(saleCode)){
                                continue;
                            }
                            rsBean.setSalesTargetType(saleCode);
                        }
                        byBuyerSalestargetList.add(rsBean);
                    }
                }

                buyerList.get(i).setByBuyerSalestargetList(byBuyerSalestargetList);
                buyerList.get(i).setByBuyerPdClaList(byBuyerPdClaList);

                String doMainName = buyerList.get(i).getProvinceName() + buyerList.get(i).getCityName() + buyerList.get(i).getDistrictName();
                buyerList.get(i).setDomainName(doMainName);
            }



        }


        ibr121308RsPageResult.setBuyerList(buyerList);
        ibr121308RsPageResult.setTotalCount(count);
        ibr121308RsPageResult.setTotalPage(count, rsParam.getParam().getPageCount());
        ibr121308RsPageResult.setPageNo(rsParam.getParam().getPageNo());
        rs.setResult(ibr121308RsPageResult);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("处理成功");
        return rs;

    }



}
