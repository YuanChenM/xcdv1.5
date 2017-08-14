package com.msk.buyers.controller.comm;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.BY121304Bean;
import com.msk.buyers.bean.IBY121202RsBean;
import com.msk.buyers.bean.IBY121202RsParam;
import com.msk.buyers.logic.BY121304Logic;
import com.msk.buyers.logic.IBY121202Logic;
import com.msk.buyers.logic.IBY121203Logic;
import com.msk.buyers.utils.BuyerTypeUtil;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.BuyersConst;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.*;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 买家基本信息画面
 *
 * @author yuan_chen
 */
@Controller
@RequestMapping("by/baseBuyerBasicInfo")
public class BYBaseBuyerBasicController extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BYBaseBuyerBasicController.class);

    @Autowired
    private BY121304Logic by121304Logic;
    @Autowired
    private IBY121202Logic iby121202Logic;
    @Autowired
    private IBY121203Logic iby121203Logic;
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private BYCommonController commonController;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
            method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家基本信息画面");
        model.addAttribute("buyerId", buyerId);
        //买家基本信息
        IBY121202RsParam buyerDetail = by121304Logic.findBuyerDetail(buyerId);
        TreeMap<String, String> treeMap = new TreeMap<>();
        //批发市场
        List<ByMarketTerminal> marketTerminalList = null;
        if (BuyersConst.BuyerType.Distribution.equals(buyerDetail.getSuperiorType())) {
            marketTerminalList = by121304Logic.findBuyerMarketTerminalList(buyerDetail.getLgcsAreaCode(), buyerDetail.getCityCode(), null);
            if (!CollectionUtils.isEmpty(marketTerminalList)) {
                for (int i = 0; i < marketTerminalList.size(); i++) {
                    if (buyerDetail.getSuperiorId().equals(marketTerminalList.get(i).getTerMarketId())) {
                        buyerDetail.setSuperiorIdName(marketTerminalList.get(i).getMarketName());
                        break;
                    }
                }
            }
        }
        //菜场数据
        List<ByMarketFood> marketFoodList = null;
        if (BuyersConst.BuyerType.Market.equals(buyerDetail.getSuperiorType())
                || BuyersConst.BuyerType.Processing.equals(buyerDetail.getSuperiorType())) {
            marketFoodList = by121304Logic.findBuyerMarketFoodList(buyerDetail.getLgcsAreaCode(), buyerDetail.getCityCode(), buyerDetail.getDistrictCode());
            if (!CollectionUtils.isEmpty(marketFoodList)) {
                for (int i = 0; i < marketFoodList.size(); i++) {
                    if (buyerDetail.getSuperiorId().equals(marketFoodList.get(i).getFodMarketId())) {
                        buyerDetail.setSuperiorIdName(marketFoodList.get(i).getMarketName());
                        break;
                    }
                }
            }
        }
        //从物流区接口中获取物流区基础数据
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        //查询指定物流区中的城市
        List<CityBean> cityList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(buyerDetail.getLgcsAreaCode())) {
            districtParam.setLgcsAreaCode(buyerDetail.getLgcsAreaCode());
            districtParam.setFlag(0);
            cityList = RestCommUtil.getCityList(districtParam).getResult().getCityList();
        }
        //查询指定城市中的区县
        List<DistrictBean> districtList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(buyerDetail.getCityCode())) {
            districtParam = new DistrictParam();
            districtParam.setCityCode(buyerDetail.getCityCode());
            districtParam.setFlag(0);
            districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
        }
        //习惯支付方式
        Map<String, String> payTypeMap = CodeMasterManager.findCodeMasterMap("PaymentMethod");
        treeMap.putAll(payTypeMap);
        List<CommConstant> payTypeList = new ArrayList(treeMap.entrySet());
        //上线状态
        Map<String, String> marketingStatusMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        treeMap.clear();
        treeMap.putAll(marketingStatusMap);
        List<CommConstant> marketingStatusList = new ArrayList(treeMap.entrySet());
        // 买家类型
        Map<String, String> buyersMap = BuyerTypeUtil.getInstance().getBuyerTypeMap();
        // 买家销售产品信息
        List<ByBuyerPdCla> pdClaList = by121304Logic.buyerPdClassificationFind(buyerId);
        model.addAttribute("pdClaList", pdClaList);
        // 销售产品
        PDInfoParam pdInfoParam = new PDInfoParam();
        List<PDInfoResult> pdClaCommList = RestCommUtil.getPdClassesList(pdInfoParam).getResult().getResult();
        List<BY121304Bean> pdClaShowList = new ArrayList<>();
        for (int i = 0; i < pdClaCommList.size(); i++) {
            BY121304Bean pdCla = new BY121304Bean();
            pdCla.setClassCode(pdClaCommList.get(i).getClassesCode());
            pdCla.setClassName(pdClaCommList.get(i).getClassesName());
            pdCla.setIsChecked("0");
            for (int j = 0; j < pdClaList.size(); j++) {
                if (pdClaCommList.get(i).getClassesCode().equals(pdClaList.get(j).getClassCode())) {
                    pdCla.setIsChecked("1");
                    break;
                }
            }
            pdClaShowList.add(pdCla);
        }
        model.addAttribute("pdClaShowList", pdClaShowList);

        List<String> pdMacList = new ArrayList<String>();
        //买家类型为分销类型
        if (buyerDetail.getSuperiorType().equals(BuyersConst.BuyerType.Distribution)) {
            for (int i = 0; i < pdClaList.size(); i++) {
                String machiningCode = pdClaList.get(i).getMachiningCode();
                String temp = "";
                if (!StringUtil.isEmpty(machiningCode)) {
                    BaseParam param = new BaseParam();
                    param.setFilter("classesCode", pdClaList.get(i).getClassCode());
                    //调用接口查询二级分类信息
                    List<PdMachining> pdMachinCommList = by121304Logic.findPdMachining(param);

                    for (int j = 0; j < pdMachinCommList.size(); j++) {
                        if (machiningCode.indexOf(pdMachinCommList.get(j).getMachiningCode()) != -1) {
                            pdMacList.add(pdMachinCommList.get(j).getMachiningName());
                        }
                    }
                }
            }
        }
        model.addAttribute("pdMacList", pdMacList);

        model.addAttribute("buyerDetail", buyerDetail);
        model.addAttribute("marketTerminalList", marketTerminalList);
        model.addAttribute("marketFoodList", marketFoodList);
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        model.addAttribute("cityList", cityList);
        model.addAttribute("districtList", districtList);
        model.addAttribute("payTypeList", payTypeList);
        model.addAttribute("marketingStatusList", marketingStatusList);
        model.addAttribute("buyerTypeList", buyersMap);

        return "buyers/comm/BASE_BUYER_BASIC_INFO";
    }

    /**
     * 买家基本信息更新
     *
     * @param basicInfo
     * @param model
     * @return
     */
    @RequestMapping(value = "buyerTypeUpdate",
            method = RequestMethod.POST)
    public String basicTypeModify(IBY121202RsParam basicInfo, Model model) {
        logger.debug("买家类型信息更新");

        super.setCommonParam(basicInfo);
        Date currentDate = DateTimeUtil.getCustomerDate();
        basicInfo.setCrtTime(currentDate);
        basicInfo.setUpdTime(currentDate);
        basicInfo.setActTime(currentDate);

        if (!StringUtil.isNullOrEmpty(basicInfo.getSuperiorType())) {
            // 买家类型
            Map<String, String> buyerTypeMap = BuyerTypeUtil.getInstance().getBuyerTypeMap();
            //Map<String,String> buyerTypeMap = CodeMasterManager.findCodeMasterMap("BuyerType");
            for (String key : buyerTypeMap.keySet()) {
                if (basicInfo.getSuperiorType().equals(key)) {
                    basicInfo.setSuperiorName(buyerTypeMap.get(key));
                    break;
                }
            }
            //如果不是分销买家且不是菜场买家
            if (!BuyersConst.BuyerType.Market.equals(basicInfo.getSuperiorType()) && !BuyersConst.BuyerType.Distribution.equals(basicInfo.getSuperiorType())
                    && !BuyersConst.BuyerType.Processing.equals(basicInfo.getSuperiorType())) {

                basicInfo.setSuperiorId("");
                basicInfo.setSuperiorQua("");
            }
            //如果是加工厂买家，非菜场熟食
            if (BuyersConst.BuyerType.Processing.equals(basicInfo.getSuperiorType())) {
                if (StringUtil.isNullOrEmpty(basicInfo.getSuperiorSubType()) || !basicInfo.getSuperiorSubType().equals("01")) {
                    basicInfo.setSuperiorId("");
                    basicInfo.setSuperiorQua("");
                    basicInfo.setIsMarketFlg("0");
                }
            }

            if (basicInfo.getSuperiorSubName().equals("请选择")) {
                basicInfo.setSuperiorSubName("无");
            }
        }
        int updateCount = iby121202Logic.buyerTypeModify(basicInfo);
        //更新买家池买家信息更新买家经营产品信息
        IBY121202RsBean iby121202RsBean = new IBY121202RsBean();
        IBY121202RsParam param = new IBY121202RsParam();
        param.setBuyerId(basicInfo.getBuyerId());
        BrOBuyerInfo brOBuyerInfo = new BrOBuyerInfo();
        List<BrOBuyerPdCla> brOBuyerPdClaList = new ArrayList<>();
        //查询买家系统买家基本信息
        brOBuyerInfo = iby121202Logic.findBaseBuyerInfo(param);
        //查询买家经营产品信息
        brOBuyerPdClaList = iby121202Logic.findBuyerPdCla(param);
        iby121202RsBean.setIsModify("true");
        iby121202RsBean.setBrOBuyerInfo(brOBuyerInfo);
        iby121202RsBean.setBrOBuyerPdClaList(brOBuyerPdClaList);
        RsRequest<IBY121202RsBean> request = new RsRequest<>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(iby121202RsBean);
//        String url = "http://localhost:8180/api/br/buyerReportInfo/_update";
        String url = SystemServerManager.BuyersReportServerManager.getUpdateBuyerReportInfo();
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        return super.forward("/BY121304/init/" + basicInfo.getBuyerId() + "");
    }

    /**
     * 买家基本信息更新
     *
     * @param basicInfo
     * @param model
     * @return
     */
    @RequestMapping(value = "buyerBasicUpdate",
            method = RequestMethod.POST)
    @ResponseBody
    public String basicInfoModify(IBY121202RsParam basicInfo, Model model) {
        logger.debug("买家基本信息更新");
        //查询买家名称是否在
        int buyerNameIsExist = iby121202Logic.findBuyerNameIsExist(basicInfo);
        //查询买家名称是否和其他的买家账号相同
        int accountNameIsExist = iby121202Logic.findAccountNameIsExist(basicInfo);
        if (buyerNameIsExist > NumberConst.IntDef.INT_ZERO) {
            return SystemConst.RsStatus.FAIL;
        } else if (accountNameIsExist > NumberConst.IntDef.INT_ZERO) {
            return "T";
        } else {
            super.setCommonParam(basicInfo);
            Date currentDate = DateTimeUtil.getCustomerDate();
            basicInfo.setCrtTime(currentDate);
            basicInfo.setUpdTime(currentDate);
            basicInfo.setActTime(currentDate);


            // 判断微信和QQ是否修改
            IBY121202RsParam oldBasicInfo = new IBY121202RsParam();
            oldBasicInfo = iby121202Logic.findBuyerById(basicInfo.getBuyerId());
            int updateCount = iby121202Logic.buyerBasicInfoModify(basicInfo);
            if (updateCount > 0) {
                commonController.SendEmail(oldBasicInfo, basicInfo);
            }
            //更新买家池买家信息更新买家经营产品信息
            IBY121202RsBean iby121202RsBean = new IBY121202RsBean();
            IBY121202RsParam param = new IBY121202RsParam();
            param.setBuyerId(basicInfo.getBuyerId());
            BrOBuyerInfo brOBuyerInfo = new BrOBuyerInfo();
            List<BrOBuyerPdCla> brOBuyerPdClaList = new ArrayList<>();
            //查询买家系统买家基本信息
            brOBuyerInfo = iby121202Logic.findBaseBuyerInfo(param);
            //查询买家经营产品信息
            brOBuyerPdClaList = iby121202Logic.findBuyerPdCla(param);
            iby121202RsBean.setIsModify("true");
            iby121202RsBean.setBrOBuyerInfo(brOBuyerInfo);
            iby121202RsBean.setBrOBuyerPdClaList(brOBuyerPdClaList);
            RsRequest<IBY121202RsBean> request = new RsRequest<>();
            request.setSiteCode("1");
            request.setAuth("MSK00001");
            request.setLoginId("msk01");
            request.setParam(iby121202RsBean);
//        String url = "http://localhost:8180/api/br/buyerReportInfo/_update";
            String url = SystemServerManager.BuyersReportServerManager.getUpdateBuyerReportInfo();
            RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
            });
            return super.forward("/BY121304/init/" + basicInfo.getBuyerId() + "");
        }
    }


    /**
     * 物流区变更，获取城市下拉框数据
     *
     * @param lgcsAreaCode
     * @return
     */
    @RequestMapping(value = "lgcsAreaChange/{lgcsAreaCode}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<CityBean> findCityList(@PathVariable("lgcsAreaCode") String lgcsAreaCode) {
        List<CityBean> cityList = null;
        if (!StringUtil.isNullOrEmpty(lgcsAreaCode)) {
            DistrictParam districtParam = new DistrictParam();
            districtParam.setLgcsAreaCode(lgcsAreaCode);
            districtParam.setIsLoadCity(0);
            districtParam.setFlag(0);
            cityList = RestCommUtil.getCityList(districtParam).getResult().getCityList();
        }
        return cityList;
    }

    /**
     * 城市变更，获取区县下拉框数据
     *
     * @param cityCode
     * @return
     */
    @RequestMapping(value = "cityChange/{cityCode}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<DistrictBean> findDistrictList(@PathVariable("cityCode") String cityCode) {
        List<DistrictBean> districtList = null;
        if (!StringUtil.isNullOrEmpty(cityCode)) {
            DistrictParam districtParam = new DistrictParam();
            districtParam.setCityCode(cityCode);
            districtParam.setIsLoadDistrict(0);
            districtParam.setFlag(0);
            districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
        }
        return districtList;
    }

    /**
     * 买家类型变更,分销买家获取批发市场数据
     *
     * @return
     */
    @RequestMapping(value = "findMarketTermialList",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<ByMarketTerminal> findMarketTerminalList(IBY121202RsParam iby121202RsParam) {
        List<ByMarketTerminal> marketTerminalList = by121304Logic.findBuyerMarketTerminalList(iby121202RsParam.getLgcsAreaCode(), iby121202RsParam.getCityCode(), iby121202RsParam.getDistrictCode());
        return marketTerminalList;
    }

    /**
     * 买家类型变更,菜场买家获取批发市场数据
     *
     * @return
     */
    @RequestMapping(value = "findMarketFoodList",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<ByMarketFood> findMarketFoodList(IBY121202RsParam iby121202RsParam) {
        List<ByMarketFood> marketFoodList = by121304Logic.findBuyerMarketFoodList(iby121202RsParam.getLgcsAreaCode(), iby121202RsParam.getCityCode(), iby121202RsParam.getDistrictCode());
        return marketFoodList;
    }

    /**
     * 批发市场等级信息获取
     *
     * @return
     */
    @RequestMapping(value = "findMarketTermial/{superiorId}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    ByMarketTerminal findMarketTerminal(@PathVariable("superiorId") String superiorId) {
        ByMarketTerminal marketTerminal = by121304Logic.findBuyerMarketTerminal(superiorId);
        return marketTerminal;
    }

    /**
     * 菜场等级信息获取
     *
     * @return
     */
    @RequestMapping(value = "findMarketFood/{superiorId}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    ByMarketFood findMarketFood(@PathVariable("superiorId") String superiorId) {
        ByMarketFood marketFood = by121304Logic.findByMarketFood(superiorId);
        return marketFood;
    }

    /**
     * 买家类型变更,获取类型下二级类型数据
     *
     * @return
     */
    @RequestMapping(value = "findBuyerList/{buyerType}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, String> findBuyerList(@PathVariable("buyerType") String buyerType) {
        Map<String, Map<String, String>> buyerSubTypeMap = BuyerTypeUtil.getInstance().getBuyerSubTypeMap();
        Map<String, String> subMap = buyerSubTypeMap.get(buyerType);
        return subMap;
    }

    /**
     * 重置账号和密码
     *
     * @return
     */
    @RequestMapping(value = "reset/accountPwd",
            method = RequestMethod.POST)
    public
    @ResponseBody
    int resetAccountPwd(ByBuyerAccount byBuyerAccount) {
        super.setCommonParam(byBuyerAccount);
        Date currentDate = DateTimeUtil.getCustomerDate();
        byBuyerAccount.setUpdTime(currentDate);
        byBuyerAccount.setCrtTime(currentDate);
        byBuyerAccount.setActTime(currentDate);
        return by121304Logic.resetAccountPwd(byBuyerAccount);
    }

    /**
     * 初始化重置账号密码页面
     *
     * @param model
     * @param byBuyerAccount
     * @return
     */
    @RequestMapping(value = "initAccountPwd", method = RequestMethod.POST)
    public String initAccountPwd(Model model, ByBuyerAccount byBuyerAccount) {
        model.addAttribute("buyerId", byBuyerAccount.getBuyerId());
        model.addAttribute("telNo", byBuyerAccount.getTelNo());
        model.addAttribute("accountName", byBuyerAccount.getAccountName());
        model.addAttribute("accountPass", byBuyerAccount.getAccountPass());
        return "buyers/comm/ResetAccountPwd";
    }
}
