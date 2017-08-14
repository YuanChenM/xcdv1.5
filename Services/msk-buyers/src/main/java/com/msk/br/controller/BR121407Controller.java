package com.msk.br.controller;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
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
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.sso.client.bean.RestResponse;
import org.apache.commons.collections.CollectionUtils;
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
 * 分销买家池各上线状态买家列表Controller
 *
 * @author yuan_zhifei
 */
@Controller
@RequestMapping("BR121407")
public class BR121407Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(BR121407Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("分销买家池各上线状态买家页面初始化");
        //获取文件下载路径
        //String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        String fileServerIp = SystemServerManager.CommonServerManager.getFileServerDownloadProxy();
        // 买家类型
        TreeMap<String, String> treeMap = new TreeMap<>();
        Map<String, String> buyerType = BuyerTypeUtil.getInstance().getBuyerTypeMap();
        treeMap.putAll(buyerType);
        //买家上线状态
        TreeMap<String, String> marketStatusMap = new TreeMap<>();
        Map<String, String> marketStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        marketStatusMap.clear();
        for (String key : marketStatus.keySet()) {
            if (BuyersConstant.MarketingsStatus.PreRegister.equals(key) || BuyersConstant.MarketingsStatus.NoMarket.equals(key)) {
                marketStatusMap.put("", "--请选择--");
                marketStatusMap.put(key, marketStatus.get(key));
            }
        }

        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        //一级分类
        logger.info("调取产品接口开始,获取一级分类");
        List<BrMPdClasses> classList = null;
        RsRequest<IBR121307RsParam> request = new RsRequest<>();
        //传参数
        IBR121307RsParam param = new IBR121307RsParam();
        param.setType(StringUtil.toString(NumberConst.IntDef.INT_ZERO));
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String pdUrl = SystemServerManager.BuyersReportServerManager.getFindMachiningCodeU();
        RsResponse<IBR121307RsPageResult> pdList = RestClientUtil.post(pdUrl, request, new TypeReference<RsResponse<IBR121307RsPageResult>>() {
        });
        if (!CollectionUtils.isEmpty(pdList.getResult().getBrMPdClassesList())) {
            classList = pdList.getResult().getBrMPdClassesList();
        }
        model.addAttribute("marketStatusMap", marketStatusMap);
        model.addAttribute("buyerType", buyerType);
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        model.addAttribute("classList", classList);
        model.addAttribute("fileServerIp", fileServerIp);
        return "br/BR121407";
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
            RsRequest<IBR121307RsParam> request = new RsRequest<>();
            //传参数
            IBR121307RsParam param = new IBR121307RsParam();
            param.setClassesCode(classesCode);
            param.setType(StringUtil.toString(NumberConst.IntDef.INT_ONE));
            request.setAuth("MSK00001");
            request.setLoginId("msk01");
            request.setSiteCode("1");
            request.setParam(param);
            String pdUrl = SystemServerManager.BuyersReportServerManager.getFindMachiningCodeU();
            RsResponse<IBR121307RsPageResult> response = RestClientUtil.post(pdUrl, request, new TypeReference<RsResponse<IBR121307RsPageResult>>() {
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
     * 分销买家池各上线状态文件列表查询
     *
     * @param param
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BR121405Bean> search(BasePageParam param) {
        logger.info("分销买家池各上线状态文件列表查询");
        PageResult<BR121405Bean> result = new PageResult<>();
        RsRequest<BasePageParam> request = new RsRequest<>();
/*      DbUtils.buildLikeCondition(param, "fileName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "marketingsPeriodName", DbUtils.LikeMode.PARTIAL);*/
        param.setFilter("poolType", StringUtil.toString(NumberConst.IntDef.INT_THREE));
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //String url = "http://localhost:8180/api/br/fileBuyerPools/query";
        String url = SystemServerManager.BuyersReportServerManager.getQueryFileBuyerPools();
        RsResponse<BR121406RsPageResult> list = RestClientUtil.post(url, request, new TypeReference<RsResponse<BR121406RsPageResult>>() {
        });
        result.setData(list.getResult().getBrFileBuyerPoolList());
        result.setRecordsTotal(list.getResult().getTotalCount());
        return result;
    }

    /**
     * 生成买家池文件信息
     *
     * @param param
     */
    @RequestMapping(value = "generateBuyerPoolFileInfo", method = RequestMethod.POST)
    public
    @ResponseBody
    IBR121407RsParam generateBuyerPoolFileInfo(IBR121407RsParam param) {
        RsRequest<IBR121407RsParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8180/api/br/onlineExcelFiles/generateAndUpload";
        String url = SystemServerManager.BuyersReportServerManager.getGenerateAndUploadOnlineExcelFiles();
        RsResponse<IBR121407RsParam> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBR121407RsParam>>() {
        });
        if (response != null && response.getResult() != null) {
            return response.getResult();

        } else {
            IBR121407RsParam br121407RsParam = new IBR121407RsParam();
            br121407RsParam.setDataCount("0");
            return br121407RsParam;
        }
    }


    /**
     * 异步生成分销买家各上线状态买家Excel文件
     *
     * @param
     * @param
     */
    @RequestMapping(value = "createExcel", method = RequestMethod.POST)
    public
    @ResponseBody
    void createExcel(IBR121407RsParam param) {
        logger.info("异步生成分销买家各上线状态买家Excel文件");
        RsRequest<IBR121407RsParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        paramRsRequest.setParam(param);
//        String url = "http://localhost:8180/api/br/onlineExcelFiles/generateAndUpload";
        String url = SystemServerManager.BuyersReportServerManager.getGenerateAndUploadOnlineExcelFiles();
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
    IBR121407RsParam deleteExcel(@PathVariable("fileId") String fileId) {
        logger.info("删除分销买家各上线状态买家文件信息");
        RsRequest<IBR121407RsParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        IBR121407RsParam param = new IBR121407RsParam();
        param.setFileStatusFlag("3");
        param.setFileId(fileId);
        paramRsRequest.setParam(param);
//        String url = "http://localhost:8180/api/br/onlineExcelFiles/generateAndUpload";
        String url = SystemServerManager.BuyersReportServerManager.getGenerateAndUploadOnlineExcelFiles();
        RsResponse<IBR121407RsParam> response = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<IBR121407RsParam>>() {
        });
        if(response != null &&  response.getResult() != null ){
            return response.getResult();

        }else{
            IBR121407RsParam ibr121407RsParam = new IBR121407RsParam();
            ibr121407RsParam.setDataCount("0");
            return ibr121407RsParam;
        }
    }


}
