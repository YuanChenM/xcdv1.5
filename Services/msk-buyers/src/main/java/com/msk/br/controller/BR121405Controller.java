package com.msk.br.controller;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.br.bean.*;
import com.msk.buyers.utils.BuyerTypeUtil;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.AsyncPostCallBack;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrMPdClasses;
import com.msk.core.entity.ByMarketTerminal;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.ProductBeanResult;
import com.msk.sso.client.bean.RestResponse;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 分销买家池买家报表
 *
 * @author zhou_yajun
 */
@Controller
@RequestMapping("BR121405")
public class BR121405Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(BR121405Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        TreeMap<String, String> treeMapMarketLevel = new TreeMap<>();
        TreeMap<String, String> treeMapBuyerType = new TreeMap<>();
        //获取文件下载路径
        //String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        String fileServerIp = SystemServerManager.CommonServerManager.getFileServerDownloadProxy();
        //从物流区接口中获取物流区基础数据
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        //批发市场等级
        Map<String, String> marketLevel = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketLevel.TYPE);
        treeMapMarketLevel.putAll(marketLevel);
        model.addAttribute("marketLevel", treeMapMarketLevel);
        // 买家类型
        Map<String, String> buyerType = BuyerTypeUtil.getInstance().getBuyerTypeMap();
        treeMapBuyerType.putAll(buyerType);
        model.addAttribute("buyerType", treeMapBuyerType);
        // 获取产品一级分类
        RsRequest<PDInfoParam> pdRequest = new RsRequest<>();
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setType(NumberConst.IntDef.INT_ONE);
        pdRequest.setAuth("MSK00001");
        pdRequest.setLoginId("msk01");
        pdRequest.setSiteCode("1");
        pdRequest.setParam(pdInfoParam);
        String pdUrl = SystemServerManager.PdServerManager.getPdTypeName();
        RsResponse<ProductBeanResult> pdList = RestClientUtil.post(pdUrl, pdRequest, new TypeReference<RsResponse<ProductBeanResult>>() {
        });
        model.addAttribute("pdClassesList", pdList.getResult().getResult());
        model.addAttribute("fileServerIp", fileServerIp);
        return "br/BR121405";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BR121405Bean> search(BasePageParam param) {
        logger.info("开始调取文件信息表接口");
        /*DbUtils.buildLikeCondition(param, "lgcsAreaName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "cityName", DbUtils.LikeMode.PARTIAL);
         DbUtils.buildLikeCondition(param, "fileName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "marketName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "marketingsPeriodName", DbUtils.LikeMode.PARTIAL);*/

        param.setFilter("poolType", StringUtil.toString(NumberConst.IntDef.INT_ONE));
        PageResult<BR121405Bean> result = new PageResult();
        RsRequest<BasePageParam> fileInfoRequest = new RsRequest<>();
        fileInfoRequest.setSiteCode("1");
        fileInfoRequest.setAuth("MSK00001");
        fileInfoRequest.setLoginId("msk01");
        fileInfoRequest.setParam(param);
        //String url = "http://localhost:8180/api/br/fileBuyerPools/query";
        String url = SystemServerManager.BuyersReportServerManager.getQueryFileBuyerPools();
        RsResponse<BR121406RsPageResult> been = RestClientUtil.post(url, fileInfoRequest, new TypeReference<RsResponse<BR121406RsPageResult>>() {
        });
        result.setData(been.getResult().getBrFileBuyerPoolList());
        result.setRecordsTotal(been.getResult().getTotalCount());

        return result;
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
            logger.info("开始调取城市列表接口");
            RsRequest<DistrictParam> request = new RsRequest<>();

            DistrictParam districtParam = new DistrictParam();
            districtParam.setLgcsAreaCode(lgcsAreaCode);
            districtParam.setIsLoadCity(NumberConst.IntDef.INT_ZERO);
            districtParam.setFlag(NumberConst.IntDef.INT_ZERO);

            request.setAuth("MSK00001");
            request.setLoginId(districtParam.getActId());
            request.setSiteCode("1");
            request.setParam(districtParam);

            String url = SystemServerManager.DistrictServerManage.getDistrictQueryCity();
            RsResponse<DistrictResult> city = RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
            });

            cityList = city.getResult().getCityList();
            logger.info("城市列表接口调取结束");
        }
        return cityList;
    }

    /**
     * 一级分类下拉框变更，获取二级分类下拉框
     *
     * @param classesCode
     * @return
     */
    @RequestMapping(value = "classesChange/{classesCode}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<BrMPdClasses> findMachiningList(@PathVariable("classesCode") String classesCode) {
        List<BrMPdClasses> machiningList = null;
        if (!StringUtil.isEmpty(classesCode)) {
            RsRequest<IBR121307RsParam> machiningRequest = new RsRequest<>();
            //传参数
            IBR121307RsParam brMPdClasses = new IBR121307RsParam();
            brMPdClasses.setClassesCode(classesCode);
            brMPdClasses.setType(String.valueOf(NumberConst.IntDef.INT_ONE));
            machiningRequest.setAuth("MSK00001");
            machiningRequest.setLoginId("msk01");
            machiningRequest.setSiteCode("1");
            machiningRequest.setParam(brMPdClasses);
            String pdUrl = SystemServerManager.BuyersReportServerManager.getFindMachiningCodeU();
            ;
            RsResponse<IBR121307RsPageResult> response = RestClientUtil.post(pdUrl, machiningRequest, new TypeReference<RsResponse<IBR121307RsPageResult>>() {
            });
            machiningList = response.getResult().getBrMPdClassesList();
        }
        return machiningList;
    }

    /**
     * 买家上线状态一级分类变更获取买家上线状态二级分类
     *
     * @param marketingsStatusCla
     * @return
     */
    @RequestMapping(value = "marketingsStatusClaChange/{marketingsStatusCla}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    TreeMap<String, Object> marketingsStatusList(@PathVariable("marketingsStatusCla") String marketingsStatusCla) {
        TreeMap<String, Object> marketStatusMap = new TreeMap<>();
        //获取上线状态基础数据
        Map<String, String> marketStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        for (String key : marketStatus.keySet()) {
            if ("1".equals(marketingsStatusCla)) {
                if (BuyersConstant.MarketingsStatus.PreRegister.equals(key) || BuyersConstant.MarketingsStatus.NoMarket.equals(key)) {
                    marketStatusMap.put(key, marketStatus.get(key));
                }
            } else if ("2".equals(marketingsStatusCla)) {
                if (BuyersConstant.MarketingsStatus.ActivePeriod.equals(key) || BuyersConstant.MarketingsStatus.StablePeriodCentral.equals(key)
                        || BuyersConstant.MarketingsStatus.StablePeriodStandard.equals(key) || BuyersConstant.MarketingsStatus.EarlyWarnPeriod.equals(key)
                        || BuyersConstant.MarketingsStatus.SalePublicBuyers.equals(key)) {
                    marketStatusMap.put(key, marketStatus.get(key));
                }
            } else {
                if (BuyersConstant.MarketingsStatus.OutBusiness.equals(key) || BuyersConstant.MarketingsStatus.InfoError.equals(key)) {
                    marketStatusMap.put(key, marketStatus.get(key));
                }
            }
        }
        return marketStatusMap;
    }

    /**
     * 获取批发市场信息
     *
     * @param br121405Bean
     * @return
     */
    @RequestMapping(value = "findMarketInfo",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<ByMarketTerminal> findMarketInfo(BR121405Bean br121405Bean) {

        BaseParam param = new BaseParam();
        param.setFilter("marketLevel", br121405Bean.getMarketLevel());
        param.setFilter("lgcsAreaCode", br121405Bean.getLgcsAreaCode());
        param.setFilter("cityCode", br121405Bean.getCityCode());
        //传参数
        RsRequest<BaseParam> rsRequest = new RsRequest<>();
        rsRequest.setAuth("MSK00001");
        rsRequest.setLoginId("msk01");
        rsRequest.setSiteCode("1");
        rsRequest.setParam(param);

        //String url = "http://localhost:8180/api/br/marketNameByLevel/query";
        String url = SystemServerManager.BuyersReportServerManager.getFindMarketNameListByLevel();
        RsResponse<BR121405RsResult> terminalResult = RestClientUtil.post(url, rsRequest, new TypeReference<RsResponse<BR121405RsResult>>() {
        });
        return terminalResult.getResult().getTerminalList();
    }

    /**
     * 生成买家池文件信息
     *
     * @return
     */
    @RequestMapping(value = "generateBuyerPoolFileInfo/{flag}", method = RequestMethod.POST)
    public
    @ResponseBody
    RsResponse<BaseParam> generateBuyerPoolFileInfo(@PathVariable("flag") String flag,BaseParam createFileParam) {
        logger.info("生成买家池数据");
        RsRequest<BaseParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        String fileStatusFlag = "0";
        createFileParam.setFilter("fileStatusFlag", fileStatusFlag);
        createFileParam.setFilter("flag", flag);
        paramRsRequest.setParam(createFileParam);
//        String url = "http://localhost:8180/api/br/excelFile/create";
        String url = SystemServerManager.BuyersReportServerManager.getBuyerPoolFileCreate();
        RsResponse<BaseParam> createMessage = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<BaseParam>>() {
        });
        return createMessage;
    }

    /**
     * 异步生成分销买家池买家Excel文件
     *
     * @param flag
     * @param fileId
     * @param createFileParam
     */
    @RequestMapping(value = "createExcel/{flag}/{fileId}", method = RequestMethod.POST)
    public
    @ResponseBody
    void createExcel(@PathVariable("flag") String flag, @PathVariable("fileId") String fileId, BaseParam createFileParam) {
        logger.info("异步生成分销买家池买家Excel文件");
        RsRequest<BaseParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        createFileParam.setFilter("flag", flag);
        createFileParam.setFilter("fileId", fileId);
        String fileStatusFlag = "2";
        createFileParam.setFilter("fileStatusFlag", fileStatusFlag);
        paramRsRequest.setParam(createFileParam);
//        String url = "http://localhost:8180/api/br/excelFile/create";
        String url = SystemServerManager.BuyersReportServerManager.getBuyerPoolFileCreate();
        TypeReference<RestResponse<Void>> tTypeReference = new TypeReference<RestResponse<Void>>() {
        };
        RestClientUtil.asyncPost(url, paramRsRequest, new AsyncPostCallBack<RestResponse<Void>>() {
            @Override
            public void callBack(RestResponse<Void> resultClass) {
            }
        }, tTypeReference);
    }

    /**
     * 删除excel
     * @param fileId
     * @return
     */
    @RequestMapping(value = "deleteExcel/{fileId}", method = RequestMethod.POST)
    public
    @ResponseBody
    RsResponse<BaseParam> deleteExcel(@PathVariable("fileId") String fileId) {
        logger.info("删除分销买家池文件信息");
        RsRequest<BaseParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        BaseParam param = new BaseParam();
        String fileStatusFlag = "3";
        param.setFilter("fileStatusFlag", fileStatusFlag);
        param.setFilter("fileId", fileId);
        paramRsRequest.setParam(param);
//        String url = "http://localhost:8180/api/br/excelFile/create";
        String url = SystemServerManager.BuyersReportServerManager.getBuyerPoolFileCreate();
        RsResponse<BaseParam> response = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<BaseParam>>() {
        });
        return response;
    }

}
