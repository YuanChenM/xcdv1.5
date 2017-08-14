package com.msk.buyers.controller;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.*;
import com.msk.buyers.controller.comm.BYCommonController;
import com.msk.buyers.logic.*;
import com.msk.buyers.utils.BuyerTypeUtil;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.BuyersConst;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.*;
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
 * 新增买家
 *
 * @author yuan_chen
 */
@Controller
@RequestMapping("BY121307")
public class BY121307Controller extends BaseUploadController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121307Controller.class);
    @Autowired
    private BY121304Logic by121304Logic;
    @Autowired
    private IBY121202Logic iby121202Logic;
    @Autowired
    private IBY121203Logic iby121203Logic;
    @Autowired
    private IBY121204Logic iby121204Logic;
    @Autowired
    private IBY121208Logic iby121208Logic;
    @Autowired
    private IBY121209Logic iby121209Logic;
    @Autowired
    private BY121310Logic by121310Logic;

    @Autowired
    private BYCommonController commonController;

    private List<PDInfoResult> pdClaCommList = new ArrayList<PDInfoResult>();

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}", method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家新增画面");
        model.addAttribute("buyerId", buyerId);
        TreeMap<String, String> treeMap = new TreeMap<>();
        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        // 买家类型
        Map<String, String> buyersMap = BuyerTypeUtil.getInstance().getBuyerTypeMap();
        // 收货时间
        /*Map<String,String> codeMasterMap = CodeMasterManager.findCodeMasterMap(BuyersConst.ReceivePeriodType.TYPE);
        treeMap.clear();
        treeMap.putAll(codeMasterMap);*/
        List<CommConstant> recTimeCommList = new ArrayList(treeMap.entrySet());
        // 销售对象
        Map<String, String> salestargetMap = CodeMasterManager.findCodeMasterMap(BuyersConst.SalesTarget.TYPE);
        treeMap.clear();
        treeMap.putAll(salestargetMap);
        List<CommConstant> salestargetCommList = new ArrayList(treeMap.entrySet());
        // 销售产品
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdClaCommList = RestCommUtil.getPdClassesList(pdInfoParam).getResult().getResult();

        List<BY121310Param> claAndMachinList = new ArrayList<BY121310Param>();


        for (int i = 0; i < pdClaCommList.size(); i++) {
            String machiningCode = null;
            Map<String, String> pdMacMap = new HashMap<>();
            BY121310Param by121310Param = new BY121310Param();
            by121310Param.setClassCode(pdClaCommList.get(i).getClassesCode());
            by121310Param.setClassName(pdClaCommList.get(i).getClassesName());
            BaseParam param = new BaseParam();
            param.setFilter("classCode", pdClaCommList.get(i).getClassesCode());
            List<PdMachining> pdMachiningCommList = new ArrayList<>();
            List<PdMachining> pdMsCommList = new ArrayList();
            //调用接口查询二级分类信息
            pdMachiningCommList = by121304Logic.findPdMachining(param);
            model.addAttribute("pdMachiningCommList", pdMachiningCommList);
            for (int k = 0; k < pdMachiningCommList.size(); k++) {
                if (pdMachiningCommList.get(k).getClassesCode().equals(pdClaCommList.get(i).getClassesCode())) {
                    PdMachining pdMachining = new PdMachining();
                    pdMachining.setMachiningCode(pdMachiningCommList.get(k).getMachiningCode());
                    pdMachining.setMachiningName(pdMachiningCommList.get(k).getMachiningName());
                    pdMsCommList.add(pdMachining);
                }

            }
            by121310Param.setPdMachiningList(pdMsCommList);
            by121310Param.setMachiningCodeU(machiningCode);
            claAndMachinList.add(by121310Param);
        }

        //查询出买家类型和营销状态
        BY121310Param parames = new BY121310Param();
        parames.setBuyerId(buyerId);
        BY121310Bean sTypeAndMStatus = new BY121310Bean();
        sTypeAndMStatus = by121310Logic.findSuperiorType(parames);
        model.addAttribute("sTypeAndMStatus", sTypeAndMStatus);
        if (sTypeAndMStatus.getMarketingsStatus().equals("01") || sTypeAndMStatus.getMarketingsStatus().equals("01")) {

        }
        model.addAttribute("claAndMachinList", claAndMachinList);
        model.addAttribute("pdClaCommList", pdClaCommList);
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        model.addAttribute("recTimeCommList", recTimeCommList);
        model.addAttribute("salestargetCommList", salestargetCommList);
        model.addAttribute("buyerTypeList", buyersMap);
        return "buyers/BY121307";
    }


    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{buyerId}", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<IBY121207RsParam> search(@PathVariable("buyerId") String buyerId, BasePageParam param) {
        logger.debug("买家员工信息查询");
        param.getFilterMap().put("buyerId", "");
        DbUtils.buildLikeCondition(param, "employeeName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "employeeTel", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "employeeQq", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "employeeWechat", DbUtils.LikeMode.PARTIAL);
        PageResult<IBY121207RsParam> result = by121304Logic.findPage(param, IBY121207RsParam.class);
        // 买家类型
        Map<String, String> EmployeeMap = CodeMasterManager.findCodeMasterMap("EmployeeType");
        for (IBY121207RsParam iby121207RsParam : result.getData()) {
            iby121207RsParam.setEmployeeTypeName(EmployeeMap.get(iby121207RsParam.getEmployeeType()));
        }
        return result;
    }

    /**
     * 新增买家信息保存
     *
     * @param by121307Bean
     * @throws java.io.IOException
     */
    @RequestMapping(value = "save/{buyerId}", method = RequestMethod.POST)
    public
    @ResponseBody
    String basicLicInfoModify(@PathVariable("buyerId") String buyerId, BY121307Bean by121307Bean) {
        logger.debug("买家基本信息更新");
        IBY121202RsParam buyerBasicInfo = by121307Bean.getBuyerBasicInfo();
        buyerBasicInfo.setBuyerPdMac(by121307Bean.getBuyerPdMac());
        buyerBasicInfo.setBuyerPdCla(by121307Bean.getBuyerPdCla());
        if (buyerBasicInfo != null) {
            buyerBasicInfo.setBuyerId(buyerId);
            //查询买家名称是否在
            int buyerNameIsExist = iby121202Logic.findBuyerNameIsExist(buyerBasicInfo);
            //查询买家名称是否和其他的买家账号相同
            int accountNameIsExist = iby121202Logic.findAccountNameIsExist(buyerBasicInfo);
            if (buyerNameIsExist > NumberConst.IntDef.INT_ZERO) {
                return "该买家名称已存在，不可新增！";
            } else if (accountNameIsExist > NumberConst.IntDef.INT_ZERO) {
                return "该买家名称和其他账号相同，不可新增！";
            } else {
                //更新买家基本信息
                byBasicInfoSave(buyerBasicInfo, buyerId);
                logger.debug("买家收货信息更新");
                // 更新买家收货地址
                List<ByBuyerRecAddr> recAddrList = by121307Bean.getBuyerRecAddrList();
                byBuyerRecAddrSave(recAddrList, buyerId);
                // 更新买家收货时间
                String[] receiveTime = by121307Bean.getBuyerRecTime();
                byBuyerRecTimeSave(receiveTime, buyerId);
                // 更新买家销售对象
                String[] salestarget = by121307Bean.getBuyerSalestarget();
                byBuyerSalesTargetSave(salestarget, buyerId);
                // 更新买家销售产品

                String[] pdCla = by121307Bean.getBuyerPdCla();
                String[] MacCla = by121307Bean.getBuyerPdMac();
                byBuyerPdClaSave(pdCla, MacCla, buyerBasicInfo.getSuperiorType(), buyerId);


                BY121310Param by121310Param = new BY121310Param();
                String pdClas = null;
                String macClas = null;
                if (pdCla == null) {
                    pdClas = MacCla[0].split("_")[1];
                    macClas = MacCla[0].split("_")[0];
                } else {
                    pdClas = pdCla[0];
                }
                by121310Param.setBuyerCode(buyerBasicInfo.getBuyerCode());
                by121310Param.setMachiningCodeU(macClas);
                by121310Param.setClassCode(pdClas);
                by121310Param.setBuyerId(buyerId);
                by121310Param.setCityCode(buyerBasicInfo.getCityCode());
                by121310Param.setLgcsAreaCode(buyerBasicInfo.getLgcsAreaCode());
                by121310Param.setSuperiorType(buyerBasicInfo.getSuperiorType());
                this.UpdateBrBuyerPoolRelationship(by121310Param);
                //更新买家池买家信息更新买家经营产品信息
                IBY121202RsBean iby121202RsBean = new IBY121202RsBean();
                IBY121202RsParam param = new IBY121202RsParam();
                param.setBuyerId(buyerId);
                BrOBuyerInfo brOBuyerInfo = new BrOBuyerInfo();
                List<BrOBuyerPdCla> brOBuyerPdClaList = new ArrayList<>();
                //查询买家系统买家基本信息
                brOBuyerInfo = iby121202Logic.findBaseBuyerInfo(param);
                //查询买家经营产品信息
                brOBuyerPdClaList = iby121202Logic.findBuyerPdCla(param);
                iby121202RsBean.setBrOBuyerInfo(brOBuyerInfo);
                iby121202RsBean.setBrOBuyerPdClaList(brOBuyerPdClaList);
                RsRequest<IBY121202RsBean> request = new RsRequest<>();
                request.setSiteCode("1");
                request.setAuth("MSK00001");
                request.setLoginId("msk01");
                request.setParam(iby121202RsBean);
//          String url = "http://localhost:8180/api/br/buyerReportInfo/_update";
                String url = SystemServerManager.BuyersReportServerManager.getUpdateBuyerReportInfo();
                RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
                });
                logger.debug("买家证照信息更新");
                //更新买家证照信息
                BuyerLicenceBean buyerLicence = by121307Bean.getBuyerLicence();
                return byBuyerLicenceSave(buyerLicence, buyerId);
            }
        } else {
            return SystemConst.RsStatus.FAIL;
        }
    }

    /**
     * 文件上传保存
     *
     * @param picInfoParam
     * @return
     */
    @RequestMapping(value = "saveFileServerId",
            method = RequestMethod.POST)
    public String basicLicInfoModify(IBY121206RsParam picInfoParam) {
        logger.debug("买家证照图片更新");
        super.setCommonParam(picInfoParam);
        Date currentDate = DateTimeUtil.getCustomerDate();
        picInfoParam.setActTime(currentDate);
        picInfoParam.setCrtTime(currentDate);
        picInfoParam.setUpdTime(currentDate);

        //买家证照图片更新
        int updatePicCount = by121304Logic.buyerLicencePicModify(picInfoParam);
        return super.forward("/BY121303/init");
    }


    /**
     * 查询一级产品下的二级产品
     *
     * @param classesCode
     * @param model
     * @param machiningCode
     * @return
     */
    @RequestMapping(value = "searchPdMachin/{editType}",
            method = RequestMethod.POST)
    public String searchPdMachin(@PathVariable("editType") String editType,
                                 String classesCode, String machiningCode, Model model) {
        logger.debug("一级下二级产品信息查询");

        List<BY121304Bean> pdMacShowList = new ArrayList<>();
        List<PdMachining> pdMachinCommList = new ArrayList<PdMachining>();

        BaseParam param = new BaseParam();
        param.setFilter("classesCode", classesCode);
        //调用接口查询二级分类信息
        pdMachinCommList = by121304Logic.findPdMachining(param);

        String flag = "true";

        if (editType.equals("add")) {
            if (pdMachinCommList != null && pdMachinCommList.size() >= 1) {
                for (int i = 0; i < pdMachinCommList.size(); i++) {
                    if (pdMachinCommList.get(i).getClassesCode().equals(classesCode)) {
                        BY121304Bean pdMac = new BY121304Bean();
                        pdMac.setClassCode(pdMachinCommList.get(i).getClassesCode());
                        pdMac.setMachiningCode(pdMachinCommList.get(i).getMachiningCode());
                        pdMac.setMachiningName(pdMachinCommList.get(i).getMachiningName());
                        pdMac.setIsChecked("1");
                        pdMacShowList.add(pdMac);
                    }
                }
            } else {
                flag = "false";
            }
        } else {
            //买家类型为分销类型
            String temp = "";
            if (!StringUtil.isEmpty(machiningCode)) {

                for (int j = 0; j < machiningCode.length(); j++) {
                    temp += machiningCode.substring(j, j + 1) + ",";
                }
                machiningCode = temp.substring(0, temp.length() - 1);
            }

            for (int i = 0; i < pdMachinCommList.size(); i++) {

                BY121304Bean pdMac = new BY121304Bean();
                pdMac.setClassCode(pdMachinCommList.get(i).getClassesCode());
                pdMac.setMachiningCode(pdMachinCommList.get(i).getMachiningCode());
                pdMac.setMachiningName(pdMachinCommList.get(i).getMachiningName());
                pdMac.setIsChecked("0");
                if (machiningCode.indexOf(pdMachinCommList.get(i).getMachiningCode()) != -1) {
                    pdMac.setIsChecked("1");
                }
                pdMacShowList.add(pdMac);
            }
        }

        model.addAttribute("pdMacShowList", pdMacShowList);
        model.addAttribute("classesCode", classesCode);
        //判断是否有产品信息
        model.addAttribute("flag", flag);

        return "buyers/BY12130701";
    }

    /**
     * 买家基本信息保存
     *
     * @param buyerBasicInfo
     * @param buyerId
     */
    public void byBasicInfoSave(IBY121202RsParam buyerBasicInfo, String buyerId) {
        if (null != buyerBasicInfo) {
            buyerBasicInfo.setBuyerId(buyerId);

            //后台系统注册方式
            /*buyerBasicInfo.setRegisterSource(BuyersConst.BuyerRegisterWay.SYSTEMREGIST);*/

            //如果不是分销买家且不是菜场买家
            if (!BuyersConst.BuyerType.Market.equals(buyerBasicInfo.getSuperiorType()) && !BuyersConst.BuyerType.Distribution.equals(buyerBasicInfo.getSuperiorType())
                    && !BuyersConst.BuyerType.Processing.equals(buyerBasicInfo.getSuperiorType())) {

                buyerBasicInfo.setSuperiorId("");
                buyerBasicInfo.setSuperiorQua("");
            }
            //如果是加工厂买家，非菜场熟食
            if (BuyersConst.BuyerType.Processing.equals(buyerBasicInfo.getSuperiorType())) {
                if (StringUtil.isNullOrEmpty(buyerBasicInfo.getSuperiorSubType()) || !buyerBasicInfo.getSuperiorSubType().equals("01")) {
                    buyerBasicInfo.setSuperiorId("");
                    buyerBasicInfo.setSuperiorQua("");
                    buyerBasicInfo.setIsMarketFlg("0");
                }
            }
            if (buyerBasicInfo.getSuperiorSubName().equals("请选择")) {
                buyerBasicInfo.setSuperiorSubName("无");
            }

            setCommonFiled(buyerBasicInfo);

            // 判断微信和QQ是否修改
            IBY121202RsParam oldBuyerBasicInfo = new IBY121202RsParam();
            oldBuyerBasicInfo = iby121202Logic.findBuyerById(buyerBasicInfo.getBuyerId());
            int updateCount = iby121202Logic.buyerInfoModify(buyerBasicInfo);
            if (updateCount > 0) {
                commonController.SendEmail(oldBuyerBasicInfo, buyerBasicInfo);
            }


            BrBuyerMarketingStatusHistory bean = new BrBuyerMarketingStatusHistory();
            bean.setBuyerId(buyerBasicInfo.getBuyerId());
            bean.setNewStatusClass("1");
            bean.setNewStatusClassName("营销期");
            if (buyerBasicInfo.getBuyerQq().equals("") && buyerBasicInfo.getBuyerSingleWechat().equals("")) {
                bean.setNewStatusBreed("02");
                bean.setNewStatusBreedName("未营销成功买家");
            } else {
                bean.setNewStatusBreed("01");
                bean.setNewStatusBreedName("预注册买家");
            }
            RsRequest<BrBuyerMarketingStatusHistory> request = new RsRequest<>();
            request.setSiteCode("1");
            request.setAuth("MSK00001");
            request.setLoginId("msk01");
            request.setParam(bean);
            //String url = "http://localhost:8082/msk-br-api/api/br/brBuyerMarketingStatusHistory/update";
            String url = SystemServerManager.BuyersReportServerManager.getUpdateBuyerMarketingStatusHistory();
            RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
            });
        }
    }

    /**
     * 买家收货地址保存
     *
     * @param recAddrList
     * @param buyerId
     */
    public void byBuyerRecAddrSave(List<ByBuyerRecAddr> recAddrList, String buyerId) {
        if (!CollectionUtils.isEmpty(recAddrList)) {
            for (int i = 0; i < recAddrList.size(); i++) {
                setCommonFiled(recAddrList.get(i));
                recAddrList.get(i).setBuyerId(buyerId);
            }
            int updateRecAddrCount = iby121208Logic.buyerReceiveAddrModify(recAddrList, new ArrayList<ByBuyerRecAddr>());
        }
    }

    /**
     * 买家收货时间保存
     *
     * @param receiveTime
     * @param buyerId
     */
    public void byBuyerRecTimeSave(String[] receiveTime, String buyerId) {
        List<ByBuyerRecTime> recTimeList = new ArrayList<>();
        //Map<String,String> codeMasterMap = CodeMasterManager.findCodeMasterMap(BuyersConst.ReceivePeriodType.TYPE);
        if (null != receiveTime) {
            for (int i = 0; i < receiveTime.length; i++) {
                ByBuyerRecTime byBuyerRecTime = new ByBuyerRecTime();
                setCommonFiled(byBuyerRecTime);
                byBuyerRecTime.setBuyerId(buyerId);
                byBuyerRecTime.setRecPerType(receiveTime[i]);
                /*for (String key :codeMasterMap.keySet()){
                    if(receiveTime[i].equals(key)){
                        byBuyerRecTime.setTimeDescribe(codeMasterMap.get(receiveTime[i]));
                        break;
                    }
                }*/
                recTimeList.add(byBuyerRecTime);
            }
            int updateRecTimeCount = iby121209Logic.buyerReceiveTimeModify(recTimeList);
        }
    }

    /**
     * 买家销售对象保存
     *
     * @param salestarget
     * @param buyerId
     */
    public void byBuyerSalesTargetSave(String[] salestarget, String buyerId) {
        List<ByBuyerSalestarget> salestargetList = new ArrayList<>();
        Map<String, String> codeMasterMap = CodeMasterManager.findCodeMasterMap(BuyersConst.SalesTarget.TYPE);
        if (null != salestarget) {
            for (int i = 0; i < salestarget.length; i++) {
                ByBuyerSalestarget byBuyerSalestarget = new ByBuyerSalestarget();
                setCommonFiled(byBuyerSalestarget);
                byBuyerSalestarget.setBuyerId(buyerId);
                byBuyerSalestarget.setSalesTargetType(salestarget[i]);
                for (String key : codeMasterMap.keySet()) {
                    if (salestarget[i].equals(key)) {
                        byBuyerSalestarget.setSalesTargetName(codeMasterMap.get(key));
                        break;
                    }
                }
                salestargetList.add(byBuyerSalestarget);
            }
            int salesTargetCount = iby121204Logic.buyerSalesTargetModify(salestargetList);
        }
    }

    /**
     * 买家销售产品保存
     *
     * @param pdCla
     * @param buyerId
     */
    public void byBuyerPdClaSave(String[] pdCla, String[] MacCla, String SuperiorType, String buyerId) {
        List<ByBuyerPdCla> pdClaList = new ArrayList<>();
        if (pdCla == null) {
            String pdClas = MacCla[0].split("_")[1];
            String macClas = MacCla[0].split("_")[0];
            for (int j = 0; j < pdClaCommList.size(); j++) {
                if (pdClas.equals(pdClaCommList.get(j).getClassesCode())) {
                    ByBuyerPdCla buyerPdCla = new ByBuyerPdCla();
                    setCommonFiled(buyerPdCla);

                    buyerPdCla.setBuyerId(buyerId);
                    buyerPdCla.setClassCode(pdClas);
                    buyerPdCla.setClassName(pdClaCommList.get(j).getClassesName());
                    buyerPdCla.setMachiningCode(macClas);
                    pdClaList.add(buyerPdCla);
                    break;
                }
            }
            iby121203Logic.buyerPdClassificationModify(pdClaList);
        }
        if (pdCla != null) {
            for (int i = 0; i < pdCla.length; i++) {
                for (int j = 0; j < pdClaCommList.size(); j++) {
                    if ((pdCla[i].equals(pdClaCommList.get(j).getClassesCode()))) {
                        ByBuyerPdCla buyerPdCla = new ByBuyerPdCla();
                        setCommonFiled(buyerPdCla);
                        buyerPdCla.setBuyerId(buyerId);
                        buyerPdCla.setClassCode(pdCla[i]);
                        buyerPdCla.setClassName(pdClaCommList.get(j).getClassesName());

                        if (SuperiorType.equals(BuyersConst.BuyerType.Distribution)) {
                            buyerPdCla.setMachiningCode(MacCla[i]);
                        }
                        pdClaList.add(buyerPdCla);
                        break;
                    }
                }
            }
            iby121203Logic.buyerPdClassificationModify(pdClaList);
        }

    }

    /**
     * 更新买家产品后更新买家买家池
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "UpdateMarketingPeriod", method = RequestMethod.POST)
    public
    @ResponseBody
    void UpdateBrBuyerPoolRelationship(BY121310Param param) {

        //查询出买家类型和营销状态
        BY121310Bean by121310Bean = new BY121310Bean();
        by121310Bean = by121310Logic.findSuperiorType(param);
        param.setBuyerCode(by121310Bean.getBuyerCode());
        param.setLgcsAreaCode(by121310Bean.getLgcsAreaCode());
        param.setCityCode(by121310Bean.getCityCode());
        param.setSuperiorType(by121310Bean.getSuperiorType());
        RsRequest<BY121310Param> request = new RsRequest<>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
//        String url = "http://localhost:8081/api/br/brBuyerPoolRelationship/updateMarketingPeriod";
        String url = SystemServerManager.BuyersReportServerManager.getUpdateMarketingAndSalePeriod();
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
    }

    /**
     * 买家证照信息保存
     *
     * @param buyerLicence
     * @param buyerId
     */
    public String byBuyerLicenceSave(BuyerLicenceBean buyerLicence, String buyerId) {
        if (buyerLicence != null) {
            //买家证照信息更新
            buyerLicence.setBuyerId(buyerId);
            setCommonFiled(buyerLicence);
            int updateCount = by121304Logic.buyerLicenceModify(buyerLicence);
            if (updateCount == NumberConst.IntDef.INT_ONE) {
                return SystemConst.RsStatus.SUCCESS;
            } else {
                return SystemConst.RsStatus.FAIL;
            }
        } else {
            return SystemConst.RsStatus.FAIL;
        }
    }

    public void setCommonFiled(BaseEntity entity) {
        super.setCommonParam(entity);
        Date currentDate = DateTimeUtil.getCustomerDate();
        entity.setCrtTime(currentDate);
        entity.setUpdTime(currentDate);
        entity.setActTime(currentDate);

    }

}
