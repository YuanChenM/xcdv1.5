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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 分销买家单品目录及销售排名
 * Created by tao_zhifa on 2016/7/26.
 */
@RequestMapping("BR121406")
@Controller
public class BR121406Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = getLogger(BR121406Controller.class);

    /**
     * 分销买家单品目录及销售排名报表初始化
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.info("初始化页面");
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
        return "br/BR121406";
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
     * 分销买家单品目录及销售排名文件列表查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BR121405Bean> search(BasePageParam param) {
        PageResult<BR121405Bean> pageResult = new PageResult<>();
        RsRequest<BasePageParam> rsRequestBean = new RsRequest<>();

       /* DbUtils.buildLikeCondition(param, "fileName", DbUtils.LikeMode.PARTIAL);*/

        param.setFilter("poolType", StringUtil.toString(NumberConst.IntDef.INT_TWO));
        rsRequestBean.setSiteCode("1");
        rsRequestBean.setAuth("MSK00001");
        rsRequestBean.setLoginId("msk01");
        rsRequestBean.setParam(param);
//        String url = "http://localhost:8380/msk-br-api/api/br/fileBuyerPools/query";
        String url = SystemServerManager.BuyersReportServerManager.getQueryFileBuyerPools();
        RsResponse<BR121406RsPageResult> list = RestClientUtil.post(url, rsRequestBean, new TypeReference<RsResponse<BR121406RsPageResult>>() {
        });
        pageResult.setData(list.getResult().getBrFileBuyerPoolList());
        pageResult.setRecordsTotal(list.getResult().getTotalCount());
        return pageResult;

    }

    /**
     * 生成分销买家单品目录及销售排名Excel文件
     *
     * @param param
     */
    @RequestMapping(value = "generateBuyerPoolFileInfo", method = RequestMethod.POST)
    public @ResponseBody
    BR121406RsParam generateBuyerPoolFileInfo(BR121406RsParam param) {
        RsRequest<BR121406RsParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //String url = "http://localhost:8180/api/br/excelFiles/generateAndUpload";
        String url = SystemServerManager.BuyersReportServerManager.getGenerateAndUploadExcelFiles();
        RsResponse<BR121406RsParam> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<BR121406RsParam>>() {
        });
        if(response != null &&  response.getResult() != null ){
            return response.getResult();

        }else{
            BR121406RsParam br121406RsParam = new BR121406RsParam();
            br121406RsParam.setDataCount("0");
            return br121406RsParam;
        }
    }


    /**
     * 异步生成分销买家单品目录及销售排名Excel文件
     * @param
     * @param
     */
    @RequestMapping(value = "createExcel", method = RequestMethod.POST)
    public
    @ResponseBody
    void createExcel(BR121406RsParam param) {
        logger.info("异步生成分销买家单品目录及销售排名Excel文件");
        RsRequest<BR121406RsParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        paramRsRequest.setParam(param);
//        String url = "http://localhost:8180/api/br/excelFiles/generateAndUpload";
        String url = SystemServerManager.BuyersReportServerManager.getGenerateAndUploadExcelFiles();
        TypeReference<RestResponse<Void>> tTypeReference = new TypeReference<RestResponse<Void>>(){};
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
    BR121406RsParam deleteExcel(@PathVariable("fileId") String fileId) {
        logger.info("删除分销买家单品目录及销售排名文件信息");
        RsRequest<BR121406RsParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        BR121406RsParam param = new BR121406RsParam();
        param.setFileStatusFlag("3");
        param.setFileId(fileId);
        paramRsRequest.setParam(param)  ;
//        String url = "http://localhost:8180/api/br/excelFiles/generateAndUpload";
        String url = SystemServerManager.BuyersReportServerManager.getGenerateAndUploadExcelFiles();
        RsResponse<BR121406RsParam> response = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<BR121406RsParam>>() {
        });
        if(response != null &&  response.getResult() != null ){
            return response.getResult();

        }else{
            BR121406RsParam br121406RsParam = new BR121406RsParam();
            br121406RsParam.setDataCount("0");
            return br121406RsParam;
        }
    }

}
