package com.msk.br.controller;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.br.bean.*;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.AsyncPostCallBack;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrMPdClasses;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.sso.client.bean.RestResponse;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 专属会员报表Controller
 *
 * @author zhao_chen1
 */
@Controller
@RequestMapping("BR121410")
public class BR121410Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BR121410Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("专属会员 报表初始化");
        //获取文件下载路径
        //String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        String fileServerIp = SystemServerManager.CommonServerManager.getFileServerDownloadProxy();
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
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        model.addAttribute("classList", classList);
        model.addAttribute("fileServerIp", fileServerIp);
        return "br/BR121410";
    }

    /**
     * 专属会员管控表
     *
     * @param param
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BR121405Bean> search(BasePageParam param) {
        logger.info("专属会员管控表查询开始");
        PageResult<BR121405Bean> result = new PageResult<>();
        RsRequest<BasePageParam> request = new RsRequest<>();
        param.setFilter("poolType", StringUtil.toString(NumberConst.IntDef.INT_SIX));
        String fileName = StringUtil.toString(param.getFilterMap().get("fileName")).trim();
       /* fileName = DbUtils.buildLikeCondition(fileName, DbUtils.LikeMode.PARTIAL);*/
        param.setFilter("fileName", fileName);
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.BuyersReportServerManager.getQueryFileBuyerPools();
//        String url = "http://localhost:8081/api/br/fileBuyerPools/query";
        RsResponse<BR121406RsPageResult> list = RestClientUtil.post(url, request, new TypeReference<RsResponse<BR121406RsPageResult>>() {
        });
        result.setData(list.getResult().getBrFileBuyerPoolList());
        result.setRecordsTotal(list.getResult().getTotalCount());
        return result;
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
     * 生成买家池文件信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "generateBuyerPoolFileInfo", method = RequestMethod.POST)
    public
    @ResponseBody IBR121410RsParam generateBuyerPoolFileInfo(IBR121410RsParam param) {
        RsRequest<IBR121410RsParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8180/api/br/save/exclusiveExcelFiles";
        String url = SystemServerManager.BuyersReportServerManager.getExclusiveExcelFilesSave();
        RsResponse<IBR121410RsParam> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBR121410RsParam>>() {
        });
        if (response != null && response.getResult() != null) {
            return response.getResult();

        } else {
            IBR121410RsParam ibr121410RsParam = new IBR121410RsParam();
            ibr121410RsParam.setDataCount("0");
            return ibr121410RsParam;
        }
    }

    /**
     * 异步生成专属会员Excel文件
     * @param param
     */
    @RequestMapping(value = "createExcel",method = RequestMethod.POST)
    public
    @ResponseBody
    void  createExcel(IBR121410RsParam param){
        RsRequest<IBR121410RsParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.BuyersReportServerManager.getExclusiveExcelFilesSave();
//        String url = "http://localhost:8180/api/br/save/exclusiveExcelFiles";
        TypeReference<RestResponse<Void>> tTypeReference = new TypeReference<RestResponse<Void>>(){};
        RestClientUtil.asyncPost(url, request, new AsyncPostCallBack<RestResponse<Void>>() {
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
    IBR121410RsParam deleteExcel(@PathVariable("fileId") String fileId) {
        logger.info("删除专属会员文件信息");
        RsRequest<IBR121410RsParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        IBR121410RsParam param = new IBR121410RsParam();
        param.setFileStatusFlag("3");
        param.setFileId(fileId);
        paramRsRequest.setParam(param);
        String url = SystemServerManager.BuyersReportServerManager.getExclusiveExcelFilesSave();
//        String url = "http://localhost:8180/api/br/save/exclusiveExcelFiles";
        RsResponse<IBR121410RsParam> response = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<IBR121410RsParam>>() {
        });
        if(response != null &&  response.getResult() != null ){
            return response.getResult();

        }else{
            IBR121410RsParam ibr121410RsParam = new IBR121410RsParam();
            ibr121410RsParam.setDataCount("0");
            return ibr121410RsParam;
        }
    }
}
