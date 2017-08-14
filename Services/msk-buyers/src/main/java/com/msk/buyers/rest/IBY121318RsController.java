package com.msk.buyers.rest;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121318Bean;
import com.msk.buyers.bean.IBY121318RsResult;
import com.msk.buyers.logic.IBY121318Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.utils.FileUploadUtil;
import com.msk.core.entity.ByBuyerReportManager;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;

@RestController
public class IBY121318RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121318RsController.class);

    @Autowired
    private IBY121318Logic iby121318Logic;

    interface SqlId {
        static String BY_FILE_DELETE = "byFileDelete";
    }

    /**
     * 买家报表管理
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/by/byReport/query", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBY121318RsResult> byReportManageQuery(@RequestBody RsRequest<BasePageParam> request) {
        RsResponse<IBY121318RsResult> response = new RsResponse<>();
        DbUtils.buildLikeCondition(request.getParam(), "fileName", DbUtils.LikeMode.PARTIAL);
        BasePageParam param = request.getParam();
        IBY121318RsResult iby121318RsResult = new IBY121318RsResult();
        String currentPeriod = StringUtil.toSafeString(param.getFilterMap().get("currentPeriod"));
        if (!StringUtil.isNullOrEmpty(currentPeriod)) {
            String[] currentPeriods = currentPeriod.split(",");
            param.getFilterMap().put("currentPeriods", currentPeriods);
        }
        List<BY121318Bean> reportManagerList = iby121318Logic.findPageList(param, BY121318Bean.class);
        int totalCount = iby121318Logic.getPageCount(param);
        iby121318RsResult.setTotalCount(totalCount);
        iby121318RsResult.setBuyerReportManagerList(reportManagerList);
        response.setResult(iby121318RsResult);
        return response;
    }

    /**
     * 生成Excel文件接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/by/file/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BaseParam> excelFileCreate(@RequestBody RsRequest<BaseParam> request) {
        RsResponse<BaseParam> response = new RsResponse<>();
        BaseParam param = request.getParam();
        Date currentDate = DateTimeUtil.getCustomerDate();
        //插入一条记录到报表表里
        ByBuyerReportManager reportManager = new ByBuyerReportManager();
        param.setCrtId(request.getLoginId());
        param.setCrtTime(currentDate);
        param.setUpdId(request.getLoginId());
        param.setUpdTime(currentDate);
        param.setActId(request.getLoginId());
        param.setActTime(currentDate);
        reportManager.setCrtId(request.getLoginId());
        reportManager.setCrtTime(currentDate);
        reportManager.setUpdId(request.getLoginId());
        reportManager.setUpdTime(currentDate);
        reportManager.setActId(request.getLoginId());
        reportManager.setActTime(currentDate);
        if (("0").equals(param.getFilterMap().get("fileStatusFlag"))) {
            String fileId = UUID.randomUUID().toString();
            reportManager.setFileId(fileId);
            reportManager.setFileServerId("");
            reportManager.setFileName("分销分类买家池买家产品池与标准产品池对比管控表");
            reportManager.setFileSuf("xlsx");
            reportManager.setBuyerId(String.valueOf(param.getFilterMap().get("buyerId")));
            if (null == param.getFilterMap().get("currentPeriod")) {
                reportManager.setCurrentPeriod(null);
            } else {
                reportManager.setCurrentPeriod(String.valueOf(param.getFilterMap().get("currentPeriod")));
            }
            reportManager.setPeriodStart(DateTimeUtil.parseDate(String.valueOf(param.getFilterMap().get("periodStart")), DateTimeUtil.FORMAT_DATE_YYYYMMDD));
            reportManager.setPeriodEnd(DateTimeUtil.parseDate(String.valueOf(param.getFilterMap().get("periodEnd")), DateTimeUtil.FORMAT_DATE_YYYYMMDD));
            reportManager.setFileStatus("2");
            reportManager.setCrtId("by");
            reportManager.setCrtTime(DateTimeUtil.getCustomerDate());
            reportManager.setActId("by");
            reportManager.setActTime(DateTimeUtil.getCustomerDate());
            reportManager.setUpdId("by");
            reportManager.setUpdTime(DateTimeUtil.getCustomerDate());
            String start = DateTimeUtil.formatDate("yyyy-MM-dd", reportManager.getPeriodStart()) + " 00:00:00";
            String end = DateTimeUtil.formatDate("yyyy-MM-dd", reportManager.getPeriodEnd()) + " 23:59:59";
            Date startDate = DateTimeUtil.parseDate(start, "yyyy-MM-dd HH:mm:ss");
            Date endDate = DateTimeUtil.parseDate(end, "yyyy-MM-dd HH:mm:ss");
            reportManager.setPeriodStart(startDate);
            reportManager.setPeriodEnd(endDate);
            reportManager.setFileCreateTime(currentDate);

            int res = this.iby121318Logic.isExist(reportManager);
            if (res > NumberConst.IntDef.INT_ZERO) {
                if (param.getFilterMap().get("flag").equals("true")) {
                    //覆盖生成新文件
                    this.iby121318Logic.updateByBuyerReportManager(reportManager);
                    int saveResult = iby121318Logic.save(reportManager);
                    if(saveResult == NumberConst.IntDef.INT_ZERO){
                        param.setFilter("fileId",fileId);
                        param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_ZERO));
                        response.setStatus(SystemConst.RsStatus.FAIL);
                        response.setResult(param);
                        response.setMessage("新增失败！");
                    }else {
                        param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_ONE));
                        param.setFilter("fileId",fileId);
                        response.setStatus(SystemConst.RsStatus.SUCCESS);
                        response.setResult(param);
                        response.setMessage("文件生成中！");
                    }

                } else {
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                    response.setMessage("报表已存在,是否生成最新报表!");
                    param.setFilter("fileId",fileId);
                    param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_TWO));
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                    response.setMessage("文件生成中！");
                    response.setResult(param);

                }

            } else {

                int saveResult = iby121318Logic.save(reportManager);
                if(saveResult == NumberConst.IntDef.INT_ZERO) {
                    param.setFilter("fileId",fileId);
                    param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_ZERO));
                    response.setStatus(SystemConst.RsStatus.FAIL);
                    response.setResult(param);
                    response.setMessage("新增失败！");
                }else {
                    param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_ONE));
                    param.setFilter("fileId",fileId);
                    response.setResult(param);
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                    response.setMessage("文件生成中！");
                }

            }

        }
        if (("2").equals(param.getFilterMap().get("fileStatusFlag"))) {
            createExcel(param,reportManager.getFileName());
        }

        return response;
    }

    public void createExcel(BaseParam param,String title){
        String fileId = StringUtil.toString(param.getFilterMap().get("fileId"));
            //拼装数据
            Map<String, Object> map = new HashMap<>();
            /** 假数据*/
            List<BY121318Bean> pdList = new ArrayList<>();
            BY121318Bean pdInfo = new BY121318Bean();
            pdInfo.setSaleName("");
            pdInfo.setStandardName("");
            pdInfo.setLocalName("");
            pdInfo.setPdFeature("");
            pdInfo.setPdLevel("");
            pdInfo.setPdOnlineTime("");
            pdList.add(pdInfo);
            map.put("pdList", pdList);

            map.put("title", title);
            InputStream in = null;
            OutputStream out = null;
            String inputPath = "excel/template/BY121318.xlsx";
            String outputPath = FileUtils.getSystemTmpDir() + "/" + fileId + ".xlsx";

            try {
                File excelFile = new File(outputPath);
                in = getClass().getClassLoader().getResourceAsStream(inputPath);
                out = new FileOutputStream(excelFile);
                JxlsExcelWrite jew = new JxlsExcelWrite(in, out);
                jew.excelWrite(map);
                Map<String, File> fileMap = new HashMap<>();
                fileMap.put(fileId, excelFile);
                Map<String, String> fileResult = FileUploadUtil.uploadFiles(fileMap);
                if (!StringUtil.isNullOrEmpty(fileResult.get(fileId))) {
                    param.setFilter("fileCreateTime", DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYMMDD_HHMMSS, DateTimeUtil.getCustomerDate()));
                    param.setFilter("fileServerId", fileResult.get(fileId));
                    param.setFilter("fileStatus", "1");

                    int updateCount = iby121318Logic.modify(param);
                    if (updateCount == NumberConst.IntDef.INT_ZERO) {
                        iby121318Logic.modifyFileStatus(param);
                        return;
                    }
                    FileUtils.deleteFile(excelFile);
                } else {
                    iby121318Logic.modifyFileStatus(param);
                    return;
                }
            } catch (FileNotFoundException e) {
                logger.error("模板文件不存在");
                iby121318Logic.modifyFileStatus(param);
                return;
//                throw new SystemException("模板文件不存在", e);
            } catch (IOException e) {
                logger.error("IO读写错误");
                iby121318Logic.modifyFileStatus(param);
                return;
//                throw new SystemException("IO读写错误");
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        logger.error("输入流关闭错误");
                        throw new SystemException("输入流关闭错误");
                    }
                }
                if (out != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        logger.error("输出流关闭错误");
                        throw new SystemException("输出流关闭错误");
                    }
                }
            }
        }




    /**
     * 删除文件
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/by/file/delete", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> byReportDelete(@RequestBody RsRequest<BaseParam> request) {
        RsResponse<String> response = new RsResponse<>();
        BaseParam param = request.getParam();
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setCrtId(request.getLoginId());
        param.setCrtTime(currentDate);
        param.setUpdId(request.getLoginId());
        param.setUpdTime(currentDate);
        param.setActId(request.getLoginId());
        param.setActTime(currentDate);
        int deleteResult = iby121318Logic.modify(SqlId.BY_FILE_DELETE, param);
        if (deleteResult == NumberConst.IntDef.INT_ZERO) {
            response.setStatus(SystemConst.RsStatus.FAIL);
        } else {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        }
        return response;
    }
}
