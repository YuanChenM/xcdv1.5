package com.msk.br.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.br.bean.BR121405Param;
import com.msk.br.bean.BR121405Result;
import com.msk.br.bean.BR121405RsResult;
import com.msk.br.logic.IBR121405Logic;
import com.msk.br.logic.IBR121406Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.utils.FileUploadUtil;
import com.msk.core.entity.ByMarketTerminal;
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
public class IBR121405RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121405RsController.class);

    @Autowired
    private IBR121405Logic ibr121405Logic;
    @Autowired
    private IBR121406Logic ibr121406Logic;


    interface SqlId {

        static String BUYER_POOL_LIST = "getBuyerPoolList";
    }

    /**
     * 获取批发市场数据
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/marketNameByLevel/query", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BR121405RsResult> findMarketNameListByLevel(@RequestBody RsRequest<BaseParam> param) {
        BaseParam marketParam = param.getParam();
        RsResponse<BR121405RsResult> response = new RsResponse<>();
        List<ByMarketTerminal> list = ibr121405Logic.findMarketNameList(marketParam);
        BR121405RsResult terminalResult = new BR121405RsResult();
        terminalResult.setTerminalList(list);
        response.setResult(terminalResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }

    /**
     * 根据页面选择值生成报表文件
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/br/excelFile/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BaseParam> buyerPoolFileCreate(@RequestBody RsRequest<BaseParam> request) {

        RsResponse<BaseParam> response = new RsResponse<>();
        BaseParam param = request.getParam();
        if (param != null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.setCrtId(request.getLoginId());
            param.setUpdId(request.getLoginId());
            param.setActId(request.getLoginId());
            param.setUpdTime(currentDate);
            param.setCrtTime(currentDate);
            param.setActTime(currentDate);
            String fileStartTime = StringUtil.toString(param.getFilterMap().get("fileStartTime"));
            //String fileEndTime = String.valueOf(param.getFilterMap().get("fileEndTime"));
            String lgcsAreaName = StringUtil.toString(param.getFilterMap().get("lgcsAreaName"));
            String cityName = StringUtil.toString(param.getFilterMap().get("cityName"));
            String classesName = StringUtil.toString(param.getFilterMap().get("classesName"));
            String machiningName = StringUtil.toString(param.getFilterMap().get("machiningName"));
            String title = lgcsAreaName + "物流区" + cityName + "地区" + classesName + machiningName + "分销买家池买家管控表";

            BR121405Param inParam = new BR121405Param();
            inParam.setClassesCode(StringUtil.toString(param.getFilterMap().get("classesCode")));
            inParam.setBuyerType(StringUtil.toString(param.getFilterMap().get("buyerType")));
            inParam.setLgcsAreaCode(StringUtil.toString(param.getFilterMap().get("lgcsAreaCode")));
            inParam.setCityCode(StringUtil.toString(param.getFilterMap().get("cityCode")));
            inParam.setMarketId(StringUtil.toString(param.getFilterMap().get("marketId")));
            inParam.setMachiningCode(StringUtil.toString(param.getFilterMap().get("machiningCode")));
            inParam.setFileStartTime(StringUtil.toString(param.getFilterMap().get("fileStartTime")));
            List<String> marketingsStatusList = new ArrayList<>();
            if (!StringUtil.isNullOrEmpty(StringUtil.toString(param.getFilterMap().get("marketingsStatusCla")))) {
                if (!StringUtil.isNullOrEmpty(StringUtil.toString(param.getFilterMap().get("marketingsStatus")))) {
                    marketingsStatusList.add(StringUtil.toString(param.getFilterMap().get("marketingsStatus")));
                } else {
                    if ("1".equals(String.valueOf(param.getFilterMap().get("marketingsStatusCla")))) {
                        marketingsStatusList.add(BuyersConstant.MarketingsStatus.PreRegister);
                        marketingsStatusList.add(BuyersConstant.MarketingsStatus.NoMarket);
                    } else if ("2".equals(String.valueOf(param.getFilterMap().get("marketingsStatusCla")))) {
                        marketingsStatusList.add(BuyersConstant.MarketingsStatus.ActivePeriod);
                        marketingsStatusList.add(BuyersConstant.MarketingsStatus.StablePeriodCentral);
                        marketingsStatusList.add(BuyersConstant.MarketingsStatus.StablePeriodStandard);
                        marketingsStatusList.add(BuyersConstant.MarketingsStatus.EarlyWarnPeriod);
                        marketingsStatusList.add(BuyersConstant.MarketingsStatus.SalePublicBuyers);
                    } else {
                        marketingsStatusList.add(BuyersConstant.MarketingsStatus.OutBusiness);
                    }
                }
                if (StringUtil.toString(param.getFilterMap().get("marketingsStatusCla")).equals("3")) {
                    inParam.setMarketingsStatusCla(StringUtil.toString(param.getFilterMap().get("marketingsStatusCla")));
                    inParam.setMarketingsStatus(StringUtil.toString(param.getFilterMap().get("marketingsStatus")));
                } else {
                    inParam.setMarketingsStatusCla(StringUtil.toString(param.getFilterMap().get("marketingsStatusCla")));
                }
                inParam.setMarketingsStatusList(marketingsStatusList);
            }

            List<BR121405Result> buyerPoolList = ibr121405Logic.findList(SqlId.BUYER_POOL_LIST, inParam);

            if (("0").equals(param.getFilterMap().get("fileStatusFlag"))) {
                String fileId = UUID.randomUUID().toString();
                //String fileEndTime = String.valueOf(param.getFilterMap().get("fileEndTime"));
                String fileName = lgcsAreaName + "物流区" + cityName + "地区" + classesName + machiningName + "分销买家池买家管控表" + fileStartTime;
                //String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
                param.setFilter("fileId", fileId);
                param.setFilter("fileServerId", "");
                param.setFilter("fileName", fileName);
                param.setFilter("fileSuf", "xlsx");
                //param.setFilter("fileServerIp", fileServerIp);
                param.setFilter("poolType", StringUtil.toString(NumberConst.IntDef.INT_ONE));
                int res = this.ibr121406Logic.isExist(param);
/*                if (CollectionUtils.isEmpty(buyerPoolList)) {
                    param.setDelFlg("1");
                    modifyFileBuyerPoolByFileId(param);
                    response.setStatus(SystemConst.RsStatus.FAIL);
                    param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_ZERO));
                    response.setResult(param);
                    response.setMessage("根据条件未查询到数据,无法生成文件！");
                }*/
                if (!CollectionUtils.isEmpty(buyerPoolList)) {
                    if (res > NumberConst.IntDef.INT_ZERO) {
                        if (param.getFilterMap().get("flag").equals("true")) {
                            //覆盖生成新文件
                            this.ibr121406Logic.updateFileBuyerPoolByFileName(param);
                            int saveResult = saveFileBuyerPool(param);
                            if(saveResult == NumberConst.IntDef.INT_ZERO){
                                param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_ZERO));
                                response.setStatus(SystemConst.RsStatus.FAIL);
                                response.setResult(param);
                                response.setMessage("新增失败！");
                            }else {
                                param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_ONE));
                                response.setStatus(SystemConst.RsStatus.SUCCESS);
                                response.setResult(param);
                                response.setMessage("文件生成中！");
                            }

                        } else {
                            response.setStatus(SystemConst.RsStatus.SUCCESS);
                            response.setMessage("报表已存在,是否生成最新报表!");
                            if (!CollectionUtils.isEmpty(buyerPoolList)) {
                                param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_TWO));
                                response.setStatus(SystemConst.RsStatus.SUCCESS);
                                response.setMessage("文件生成中！");
                                response.setResult(param);
                            }
                        }

                    } else {

                        int saveResult = saveFileBuyerPool(param);
                        if(saveResult == NumberConst.IntDef.INT_ZERO) {
                            param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_ZERO));
                            response.setStatus(SystemConst.RsStatus.FAIL);
                            response.setResult(param);
                            response.setMessage("新增失败！");
                        }else {
                            param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_ONE));
                            response.setResult(param);
                            response.setStatus(SystemConst.RsStatus.SUCCESS);
                            response.setMessage("文件生成中！");
                        }

                    }

                }else{
                    response.setStatus(SystemConst.RsStatus.FAIL);
                    param.setFilter("count", StringUtil.toString(NumberConst.IntDef.INT_ZERO));
                    response.setResult(param);
                    response.setMessage("根据条件未查询到数据,无法生成文件！");
                }

            }
            if(("2").equals(param.getFilterMap().get("fileStatusFlag"))){
                creageMessageResult(param, title, buyerPoolList);
            }
            if(("3").equals(param.getFilterMap().get("fileStatusFlag"))){
                int count = 0;
                  count  = this.ibr121405Logic.deleteFlag(param);
                if(count == NumberConst.IntDef.INT_ZERO){
                    response.setStatus(SystemConst.RsStatus.FAIL);
                    param.setFilter("count", StringUtil.toString(count));
                    response.setResult(param);
                    response.setMessage("文件删除失败");
                }else {
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                    param.setFilter("count", StringUtil.toString(count));
                    response.setResult(param);
                    response.setMessage("文件删除成功");
                }
            }
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("传入生成条件为空，生成报表失败");
        }

        return response;
    }

    public RsResponse<String> creageMessageResult(BaseParam param, String title, List<BR121405Result> buyerPoolList) {
        RsResponse<String> response = new RsResponse<>();
        String fileId = StringUtil.toString(param.getFilterMap().get("fileId"));
        Map<String, Object> excelData = new HashMap<>();
        excelData.put("title", title);
        for (BR121405Result result : buyerPoolList) {
            result.setBuyerNameAndMainCode(result.getBuyerName() + "(" + result.getBuyerCode()+")");
            if(!StringUtil.isNullOrEmpty(result.getBossName()) && !StringUtil.isNullOrEmpty(result.getBossTel())){
                result.setBossNameAndContact(result.getBossName() + "(" + result.getBossTel() + ")");
            }else if(!StringUtil.isNullOrEmpty(result.getBossName()) && StringUtil.isNullOrEmpty(result.getBossTel())){
                result.setBossNameAndContact(result.getBossName());
            }else if(StringUtil.isNullOrEmpty(result.getBossName()) && !StringUtil.isNullOrEmpty(result.getBossTel())){
                result.setBossNameAndContact("("+result.getBossTel()+")");
            }else {
                result.setBossNameAndContact("");
            }
            if (!"3".equals(param.getFilterMap().get("marketingsStatusCla")) && (BuyersConstant.MarketingsStatus.PreRegister.equals(result.getMarketingsStatus()) || BuyersConstant.MarketingsStatus.NoMarket.equals(result.getMarketingsStatus()))) {
                result.setMarketingClass("营销期");
            } else if (BuyersConstant.MarketingsStatus.OutBusiness.equals(result.getMarketingsStatus()) || "3".equals(param.getFilterMap().get("marketingsStatusCla"))) {
                result.setMarketingClass("异常");
            } else {
                result.setMarketingClass("销售期");
            }
        }
        excelData.put("buyerPoolList", buyerPoolList);
        InputStream in = null;
        OutputStream out = null;
        String inputPath = "template/BR121405.xlsx";
        String outputPath = FileUtils.getSystemTmpDir() + "/" + fileId + ".xlsx";
        try {
            File excelFile = new File(outputPath);
            in = getClass().getClassLoader().getResourceAsStream(inputPath);
            out = new FileOutputStream(excelFile);
            JxlsExcelWrite jew = new JxlsExcelWrite(in, out);
            jew.excelWrite(excelData);
            Map<String, File> fileMap = new HashMap<>();
            fileMap.put(fileId, excelFile);
            Map<String, String> fileResult = FileUploadUtil.uploadFiles(fileMap);
            if (!StringUtil.isNullOrEmpty(fileResult.get(fileId))) {
                param.setFilter("fileCreateTime", DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYMMDD_HHMMSS, DateTimeUtil.getCustomerDate()));
                param.setFilter("fileServerId", fileResult.get(fileId));
                param.setFilter("fileStatus", "1");
                param.setDelFlg("0");
                int updateCount = modifyFileBuyerPoolByFileId(param);
                if (updateCount == NumberConst.IntDef.INT_ONE) {
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                } else {
                    ibr121405Logic.modifyFileStatus(param);
                    response.setStatus(SystemConst.RsStatus.FAIL);
                    response.setMessage("文件上传失败！");
                    response.setResult(StringUtil.toString(NumberConst.IntDef.INT_ZERO));
                    return response;

                }
                FileUtils.deleteFile(excelFile);
            } else {
                ibr121405Logic.modifyFileStatus(param);
                response.setStatus(SystemConst.RsStatus.FAIL);
                response.setMessage("文件上传失败！");
                response.setResult(StringUtil.toString(NumberConst.IntDef.INT_ZERO));
                return response;
            }
        } catch (FileNotFoundException e) {
            logger.error("模板文件不存在");
            ibr121405Logic.modifyFileStatus(param);
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("文件上传失败！");
            response.setResult(StringUtil.toString(NumberConst.IntDef.INT_ZERO));
            return response;
//            throw new SystemException("模板文件不存在", e);
        } catch (IOException e) {
            logger.error("IO读写错误");
            ibr121405Logic.modifyFileStatus(param);
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("文件上传失败！");
            response.setResult(StringUtil.toString(NumberConst.IntDef.INT_ZERO));
            return response;
//            throw new SystemException("IO读写错误");
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

        response.setMessage("文件生成成功");
        response.setResult(StringUtil.toString(NumberConst.IntDef.INT_ONE));
        return response;
    }

    public int saveFileBuyerPool(BaseParam param) {
        param.setFilter("fileCreateTime", DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYMMDD_HHMMSS, DateTimeUtil.getCustomerDate()));
        return ibr121405Logic.save(param);
    }

    public int modifyFileBuyerPoolByFileId(BaseParam param) {
        return ibr121405Logic.modify(param);
    }
}


