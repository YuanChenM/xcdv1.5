package com.msk.buyers.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.web.base.BaseUploadController;
import com.msk.buyers.bean.BY121311Bean;
import com.msk.buyers.logic.BY121304Logic;
import com.msk.buyers.logic.BY121305Logic;
import com.msk.buyers.logic.BY121306Logic;
import com.msk.buyers.logic.BY121311Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.BuyersConst;
import com.msk.core.entity.*;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *买家编码总控
 */
@Controller
@RequestMapping("BY121311")
public class BY121311Controller extends BaseUploadController
{

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121311Controller.class);

    @Autowired
    private BY121311Logic by121311Logic;
    @Autowired
    private BY121305Logic by121305Logic;
    @Autowired
    private BY121306Logic by121306Logic;
    @Autowired
    private BY121304Logic by121304Logic;
    /**
     * 初始化页面
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家编码总控");

        BY121311Bean paramBean = new BY121311Bean();
        ByBuyerBasicInfo buyerInfo = by121311Logic.findByBuyerBasicInfo(buyerId);

        //解析buyerCode
        getbuyerCodeDetail(buyerInfo,paramBean);

        DistrictParam districtParam = new DistrictParam();
        //查询所有物流区
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        //查询指定物流区中的城市
        List<CityBean> cityList = new ArrayList<CityBean>();
        if (!StringUtil.isNullOrEmpty(buyerInfo.getLgcsAreaCode())){
            districtParam.setLgcsAreaCode(buyerInfo.getLgcsAreaCode());
            districtParam.setFlag(0);
            cityList = RestCommUtil.getCityList(districtParam).getResult().getCityList();
        }
        //查询指定城市中的区县
        List<DistrictBean> districtList = new ArrayList<DistrictBean>();
        if(!StringUtil.isNullOrEmpty(buyerInfo.getCityCode())){
            districtParam = new DistrictParam();
            districtParam.setCityCode(buyerInfo.getCityCode());
            districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
        }

        model.addAttribute("buyerInfo",buyerInfo);
        model.addAttribute("paramBean",paramBean);
        model.addAttribute("logisticsAreaList",logisticsAreaList);
        model.addAttribute("cityList",cityList);
        model.addAttribute("districtList",districtList);

        return "buyers/BY121311";
    }

    /**
     * 解析买家编码
     * @param buyerInfo
     * @param paramBean
     */
    public void getbuyerCodeDetail(ByBuyerBasicInfo buyerInfo,BY121311Bean paramBean){

        getTypecode(buyerInfo,paramBean);
        getDiviSionCode(buyerInfo,paramBean);
        sequenceCode(buyerInfo,paramBean);
        getSecIdenCode(buyerInfo,paramBean);
    }

    /**
     * 解析买家分类
     * @param buyerInfo
     * @param paramBean
     */
    public void getTypecode(ByBuyerBasicInfo buyerInfo,BY121311Bean paramBean){

        //买家类型为1,2,3,8是为两位，其他为4位
        switch (buyerInfo.getSuperiorType()){
            case BuyersConst.BuyerType.Distribution://分销买家
                paramBean.setSuperiorName(buyerInfo.getSuperiorName());
                break ;
            case BuyersConst.BuyerType.Market://菜场买家
                paramBean.setSuperiorName(buyerInfo.getSuperiorName());
                break ;
            case BuyersConst.BuyerType.GroupMeals://团膳买家
                paramBean.setSuperiorName(buyerInfo.getSuperiorName());
                break ;
            case BuyersConst.BuyerType.HotPot://火锅买家
                paramBean.setSuperiorName(buyerInfo.getSuperiorName());
                break ;
            case BuyersConst.BuyerType.ChineseFood://中餐买家
                paramBean.setSuperiorName(buyerInfo.getSuperiorName()+buyerInfo.getSuperiorSubName());
                break ;
            case BuyersConst.BuyerType.Processing://加工厂买家
                String superiorName = "";
                if(buyerInfo.getIsMarketFlg().equals("1")){
                    superiorName = buyerInfo.getSuperiorName()+"菜场"+buyerInfo.getSuperiorSubName();
                }else{
                    superiorName = buyerInfo.getSuperiorName()+"非菜场"+buyerInfo.getSuperiorSubName();
                }
                paramBean.setSuperiorName(superiorName);
                break ;
        }

    }

    /**
     * 解析行政区划
     * @param buyerInfo
     * @param paramBean
     */
    public void getDiviSionCode(ByBuyerBasicInfo buyerInfo,BY121311Bean paramBean){
        switch (buyerInfo.getSuperiorType()){

            case BuyersConst.BuyerType.Distribution:
                paramBean.setLgcsAreaCode(buyerInfo.getLgcsAreaCode());
                paramBean.setCityCode(buyerInfo.getCityCode());
                paramBean.setDistrictCode(buyerInfo.getDistrictCode());
                break;
            default:
                paramBean.setLgcsAreaCode(buyerInfo.getLgcsAreaCode());
                paramBean.setCityCode(buyerInfo.getCityCode());
                paramBean.setDistrictCode(buyerInfo.getDistrictCode());
                break;
        }
    }

    /**
     * 解析顺序码
     * @param buyerInfo
     * @param paramBean
     */
    public void sequenceCode(ByBuyerBasicInfo buyerInfo,BY121311Bean paramBean){

        ByMarketFood foodInfo = null;
        String buyerCode = buyerInfo.getBuyerCode();
        String temp = buyerCode.substring(0,buyerCode.indexOf("-"));

        switch (buyerInfo.getSuperiorType()){
            case BuyersConst.BuyerType.Distribution://分销买家
                ByMarketTerminal terminalInfo = by121305Logic.findMarketTerminal(buyerInfo.getSuperiorId());
                paramBean.setTerminalName(terminalInfo.getMarketName());
                paramBean.setBuyerSeq(temp.substring(temp.length()-NumberConst.IntDef.INT_THREE));
                break;
            case BuyersConst.BuyerType.Market://菜场买家
                foodInfo = by121306Logic.findMarketFood(buyerInfo.getSuperiorId());
                paramBean.setFoodName(foodInfo.getMarketName());
                paramBean.setBuyerSeq(temp.substring(temp.length()-NumberConst.IntDef.INT_TWO));
                break;
            case BuyersConst.BuyerType.Processing://加工厂买家
                //1：是菜场买家，0：非菜场买家
                if(buyerInfo.getIsMarketFlg().equals(BuyersConst.BuyerType.isMarket)){
                    foodInfo = by121306Logic.findMarketFood(buyerInfo.getSuperiorId());
                    paramBean.setFoodName(foodInfo.getMarketName());
                    paramBean.setBuyerSeq(temp.substring(temp.length()-NumberConst.IntDef.INT_TWO));
                }else{
                    paramBean.setBuyerSeq(temp.substring(temp.length()-NumberConst.IntDef.INT_THREE));
                }
                break;
            default:
                paramBean.setBuyerSeq(temp.substring(temp.length()-NumberConst.IntDef.INT_THREE));
        }
    }

    /**
     * 解析辅码和校验码
     * @param buyerInfo
     * @param paramBean
     */
    public void getSecIdenCode(ByBuyerBasicInfo buyerInfo,BY121311Bean paramBean){
        Map<String,String> codeMasterMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        if(codeMasterMap.containsKey(buyerInfo.getMarketingsStatus())){
            paramBean.setSaleStatus(codeMasterMap.get(buyerInfo.getMarketingsStatus()));
        }

        // 买家销售产品信息
        List<ByBuyerPdCla> pdClaList = by121304Logic.buyerPdClassificationFind(buyerInfo.getBuyerId());
        Map<String,String> pdMap = new HashMap<String,String>();
        int k =1;
        for (int i = 0; i < pdClaList.size(); i++) {
            String machiningCode = pdClaList.get(i).getMachiningCode();
            if(!StringUtil.isEmpty(machiningCode)&&BuyersConst.BuyerType.Distribution.equals(buyerInfo.getSuperiorType())){
                BaseParam param=new BaseParam();
                param.setFilter("classesCode",pdClaList.get(i).getClassCode());
                //调用接口查询二级分类信息
                List<PdMachining> pdMachinCommList = by121304Logic.findPdMachining(param);
                for(int j=0;j<pdMachinCommList.size();j++){
                    if(machiningCode.indexOf(pdMachinCommList.get(j).getMachiningCode())!= -1){
                        pdMap.put(k+"",pdClaList.get(i).getClassName()+":"+pdMachinCommList.get(j).getMachiningName());
                    }
                    k++;
                }
            }else{
                pdMap.put(pdClaList.get(i).getClassCode(),pdClaList.get(i).getClassName());
            }
        }
        paramBean.setPdName(pdMap);

        String identifyCode = buyerInfo.getBuyerCode().substring(buyerInfo.getBuyerCode().lastIndexOf("-")+NumberConst.IntDef.INT_ONE);
        paramBean.setIdentifyCode(identifyCode);
    }

}
