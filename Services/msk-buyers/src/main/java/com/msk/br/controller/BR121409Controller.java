package com.msk.br.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 销售期公众买家报表
 * Created by tao_zhifa on 2016/8/8.
 */
@Controller
@RequestMapping("BR121409")
public class BR121409Controller extends BaseController {
    private static Logger logger = getLogger(BR121409Controller.class);


    /**
     * 初始化页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("销售期公众买家报表");
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
        return "br/BR121409";
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
     * 查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BR121405Bean> search(IBR121409RsParam param) {
        param.setFilter("poolType", StringUtil.toString(NumberConst.IntDef.INT_FIVE));

        String fileName = StringUtil.toString(param.getFilterMap().get("fileName")).trim();
        /*fileName = DbUtils.buildLikeCondition(fileName, DbUtils.LikeMode.PARTIAL);*/
        param.setFilter("fileName", fileName);

        PageResult<BR121405Bean> result = new PageResult<>();
        RsRequest<BaseParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.BuyersReportServerManager.getQueryFileBuyerPools();
        //String url = "http://localhost:8180/api/br/fileBuyerPools/query";
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
    IBR121409RsBean generateBuyerPoolFileInfo(IBR121409RsParam param) {
        RsRequest<IBR121409RsParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8180/api/br/createSalesPeriodExcel/generateAndUpload";
        String url = SystemServerManager.BuyersReportServerManager.getCreateSalesPeriodExcel();
        RsResponse<IBR121409RsBean> list = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBR121409RsBean>>() {
        });
        if(list != null &&  list.getResult() != null ){
            return list.getResult();
        }else{
            IBR121409RsBean ibr121409RsBean = new IBR121409RsBean();
            ibr121409RsBean.setDataCount("0");
            return ibr121409RsBean;
        }

    }


    /**
     * 异步生成销售期公众买家Excel文件
     * @param param
     */
    @RequestMapping(value = "createExcel",method = RequestMethod.POST)
    public @ResponseBody
    void createExcel(IBR121409RsParam param){
        RsRequest<IBR121409RsParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.BuyersReportServerManager.getCreateSalesPeriodExcel();
//        String url = "http://localhost:8180/api/br/createSalesPeriodExcel/generateAndUpload";
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
    IBR121409RsParam deleteExcel(@PathVariable("fileId") String fileId) {
        logger.info("删除销售期公众买家文件信息");
        RsRequest<IBR121409RsParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        IBR121409RsParam param = new IBR121409RsParam();
        param.setFileStatusFlag("3");
        param.setFileId(fileId);
        paramRsRequest.setParam(param);
        String url = SystemServerManager.BuyersReportServerManager.getCreateSalesPeriodExcel();
//        String url = "http://localhost:8180/api/br/createSalesPeriodExcel/generateAndUpload";
        RsResponse<IBR121409RsParam> response = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<IBR121409RsParam>>() {
        });
        if(response != null &&  response.getResult() != null ){
            return response.getResult();

        }else{
            IBR121409RsParam ibr121409RsParam = new IBR121409RsParam();
            ibr121409RsParam.setDataCount("0");
            return ibr121409RsParam;
        }
    }
}
