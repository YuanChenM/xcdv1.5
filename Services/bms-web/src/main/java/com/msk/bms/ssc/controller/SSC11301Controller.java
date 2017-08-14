package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.hoperun.plug.spring.web.base.BaseUploadController;
import com.msk.bms.ssc.controller.common.ISSCBidRestUtil;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.FileUploadUtil;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.SSC1130101RsBean;
import com.msk.ssc.bean.SSC11301RsBean;
import com.msk.ssc.bean.SSC11301RsPageResult;
import com.msk.ssc.bean.SSC11301RsParam;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 中标确认书一览Controller
 *
 * @author yuan_zhifei
 */
@Controller
@RequestMapping("SSC11301")
public class SSC11301Controller extends BaseUploadController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11301Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("中标确认书一览页面初始化");

        //从redis获取中标成交确认书状态
        String key = SscConstant.BidStatus.TYPE;
        Map<String, String> bidStatusMap = CodeMasterManager.findCodeMasterMap(key);
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(bidStatusMap);
        List bisStatusList = new ArrayList(treeMap.entrySet());
        model.addAttribute("bisStatusList", bisStatusList);


        return "ssc/SSC11301";
    }
    /**
     * 初始化中标弹窗
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "initBidInfo", method = RequestMethod.POST)
    public String initBidInfo(Model model,SSC1130101RsBean ssc1130101RsBean) {
        logger.debug("中标确认书弹窗初始化");
        /*String bidStatus="0,1,2,4,5";
        String bidFlag="1";
        String  delFlg = String.valueOf(NumberConst.IntDef.INT_ZERO);
        model.addAttribute("bidStatus",bidStatus);
        model.addAttribute("bidFlag",bidFlag);*/
        model.addAttribute("ssc1130101RsBean",ssc1130101RsBean);
        return "ssc/SSC1130101";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11301RsBean> search(SSC11301RsParam ssc11301RsParam) {
        PageResult<SSC11301RsBean> result = new PageResult();


        if (!StringUtil.isEmpty((String) ssc11301RsParam.getFilterMap().get("startDate"))) {
            ssc11301RsParam.setStartDate(StringUtil.toString(ssc11301RsParam.getFilterMap().get("startDate")));
        }

        if (!StringUtil.isEmpty((String) ssc11301RsParam.getFilterMap().get("endDate"))) {
            ssc11301RsParam.setEndDate(StringUtil.toString(ssc11301RsParam.getFilterMap().get("endDate")));
        }

        String bidProjectNo = DbUtils.buildLikeCondition(String.valueOf(ssc11301RsParam.getFilterMap().get("bidProjectNo")).trim(), DbUtils.LikeMode.PARTIAL);
        String supplierName = DbUtils.buildLikeCondition(String.valueOf(ssc11301RsParam.getFilterMap().get("supplierName")).trim(), DbUtils.LikeMode.PARTIAL);
        String purchaserName = DbUtils.buildLikeCondition(String.valueOf(ssc11301RsParam.getFilterMap().get("purchaserName")).trim(), DbUtils.LikeMode.PARTIAL);
        String bidProjectName = DbUtils.buildLikeCondition(String.valueOf(ssc11301RsParam.getFilterMap().get("bidProjectName")).trim(), DbUtils.LikeMode.PARTIAL);

        ssc11301RsParam.setBidProjectNo(bidProjectNo);
        ssc11301RsParam.setSupplierName(supplierName);
        ssc11301RsParam.setPurchaserName(purchaserName);
        ssc11301RsParam.setBidProjectName(bidProjectName);

        String bidStatus =null;
         if(ssc11301RsParam.getBidStatus()!=null){
              bidStatus=ssc11301RsParam.getBidStatus();
            }else{
              bidStatus = StringUtil.toString(ssc11301RsParam.getFilterMap().get("bidStatus"));//中标状态
           }
        String[] bidStatusArr = null;
        String  delFlg = String.valueOf(NumberConst.IntDef.INT_ZERO);
        if(!StringUtil.isNullOrEmpty(bidStatus)) {
            bidStatusArr = bidStatus.split(",");
            ssc11301RsParam.setBidStatusArr(bidStatusArr);
            if(ssc11301RsParam.getDelFlg()!=null){
                delFlg=ssc11301RsParam.getDelFlg();
            }else{
                delFlg=null;
            }

        }

        ssc11301RsParam.setDelFlg(delFlg);



        Long bindId = (Long) ssc11301RsParam.getFilterMap().get("bidId");

        ssc11301RsParam.setBidId(bindId);
        SSC11301RsPageResult response = ISSCBidRestUtil.findSscBidBasicInfoList(ssc11301RsParam);
        result.setData(response.getSSC11301RsBean());
        result.setRecordsTotal(response.getTotalCount());
        return result;
    }

    /**
     * 文件上传
     *
     * @param testExcelFile
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public void fileUpload(MultipartFile testExcelFile, HttpServletResponse response) throws IOException {
        logger.debug("文件上传");
        if (testExcelFile.getSize() > 0) {
            //文件方式上传
            String localPath = FileUtils.getSystemTmpDir();
            String fileName = testExcelFile.getOriginalFilename();
            File textFile = new File(localPath, fileName);
            testExcelFile.transferTo(textFile);
            Map<String, File> fileMap = new HashMap<>();
            fileMap.put("text", textFile);
            Map<String, String> result = FileUploadUtil.uploadFiles(fileMap);
            if (!result.isEmpty()) {
                FileUtils.deleteFile(textFile);
            }
        }
        super.callBack(null, "文件上传成功", response);
    }
}
