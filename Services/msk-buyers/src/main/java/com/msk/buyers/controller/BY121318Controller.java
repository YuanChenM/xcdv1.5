package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.hoperun.plug.spring.web.base.BaseUploadController;
import com.msk.buyers.bean.BY121318Bean;
import com.msk.buyers.bean.IBY121318RsResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.AsyncPostCallBack;
import com.msk.common.utils.FileUploadUtil;
import com.msk.common.utils.RestClientUtil;
import com.msk.sso.client.bean.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 *
 */
@Controller
@RequestMapping("BY121318")
public class BY121318Controller extends BaseUploadController {


    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121318Controller.class);
    
    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家报表信息列表");
        String fileServerIp = SystemServerManager.CommonServerManager.getFileServerDownloadProxy();
        model.addAttribute("fileServerIp", fileServerIp);
        model.addAttribute("buyerId",buyerId);
        return "buyers/BY121318";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{buyerId}", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121318Bean> search(@PathVariable("buyerId") String buyerId,BasePageParam param) {
        logger.info("买家报表信息列表");
        /*DbUtils.buildLikeCondition(param, "fileName", DbUtils.LikeMode.PARTIAL);*/

        param.setFilter("buyerId",buyerId);
        PageResult<BY121318Bean> result = new  PageResult<>();
        RsRequest<BasePageParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        paramRsRequest.setParam(param);
        String url = SystemServerManager.BuyersServerManage.getByReportManageQuery();
//        String url = "http://localhost:8480/msk-buyers/api/by/byReport/query";
        RsResponse<IBY121318RsResult> pageResult = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<IBY121318RsResult>>() {
        });
        result.setRecordsTotal(pageResult.getResult().getTotalCount());
        result.setData(pageResult.getResult().getBuyerReportManagerList());
        return result;
    }

    /**
     * 生成数据
     * @return
     */
    @RequestMapping(value = "generateBuyerReportManagerFileInfo/{flag}/{buyerId}",method = RequestMethod.POST)
    public @ResponseBody RsResponse<BaseParam> generateBuyerReportManagerFileInfo(@PathVariable("flag") String flag,@PathVariable("buyerId") String buyerId,BaseParam param){
        logger.info("生成数据");
        RsRequest<BaseParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        String fileStatusFlag = "0";
        param.setFilter("fileStatusFlag", fileStatusFlag);
        param.setFilter("flag", flag);
        param.setFilter("buyerId",buyerId);
        paramRsRequest.setParam(param);
        String url = SystemServerManager.BuyersServerManage.getExcelFileCreate();
//        String url = "http://localhost:8480/msk-buyers/api/by/file/create";
        RsResponse<BaseParam> createMessage = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<BaseParam>>() {
        });
        return createMessage;
    }



    /**
     * 生成excel文件
     * @return
     */
    @RequestMapping(value = "createExcel/{flag}/{fileId}/{buyerId}",method = RequestMethod.POST)
    public @ResponseBody void createXml(@PathVariable("flag") String flag, @PathVariable("fileId") String fileId,@PathVariable("buyerId") String buyerId,BaseParam param){
        logger.info("生成Xml文件");
        RsRequest<BaseParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        param.setFilter("flag", flag);
        param.setFilter("fileId", fileId);
        param.setFilter("buyerId",buyerId);
        String fileStatusFlag = "2";
        param.setFilter("fileStatusFlag", fileStatusFlag);
        paramRsRequest.setParam(param);
        String url = SystemServerManager.BuyersServerManage.getExcelFileCreate();
//        String url = "http://localhost:8480/msk-buyers/api/by/file/create";

        TypeReference<RestResponse<Void>> tTypeReference = new TypeReference<RestResponse<Void>>() {
        };
        RestClientUtil.asyncPost(url, paramRsRequest, new AsyncPostCallBack<RestResponse<Void>>() {
            @Override
            public void callBack(RestResponse<Void> resultClass) {
            }
        }, tTypeReference);
    }

    /**
     * 删除文件
     * @param fileId
     * @return
     */
    @RequestMapping(value = "reportFileDelete/{fileId}", method = RequestMethod.POST)
    public @ResponseBody String deleteReportFile(@PathVariable("fileId") String fileId){
        RsRequest<BaseParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        BaseParam param = new BaseParam();
        param.setFilter("fileId",fileId);
        paramRsRequest.setParam(param);
        String url = SystemServerManager.BuyersServerManage.getByReportDelete();
        RsResponse<String> createMessage = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<String>>() {
        });
        return createMessage.getStatus();
    }
}
