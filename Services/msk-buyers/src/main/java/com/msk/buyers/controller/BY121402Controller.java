package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.file.FileUtils;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121402Bean;
import com.msk.buyers.logic.BY121402Logic;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.FileUploadUtil;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByMarketTerminalBasic;
import com.msk.core.entity.ByMarketTerminalFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 批发市场报告一览
 */
@Controller
@RequestMapping("BY121402")
public class BY121402Controller extends BaseUploadController {

    /**
     * 图片文件后缀
     */
    private static String[] IMG_SUFFIXS = {"png", "jpg", "jpeg", "bmp", "gif", "PNG", "JPG", "JPEG", "BMP", "GIF"};

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121402Controller.class);

    @Autowired
    private BY121402Logic by121402Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(String marketId, Model model) {
        logger.debug("批发市场报告一览");
        ByMarketTerminalBasic terminalBasicInfo = by121402Logic.findByMarketTerminalBasic(marketId);
        String name = terminalBasicInfo.getLgcsAreaName() + terminalBasicInfo.getCityName();
        String fileServiceIp = SystemServerManager.CommonServerManager.getFileServerDownloadProxy();
        model.addAttribute("fileServiceIp", fileServiceIp);
        model.addAttribute("terminalBasicInfo", terminalBasicInfo);
        model.addAttribute("marketId", marketId);
        model.addAttribute("name", name);

        return "buyers/BY121402";
    }

    @RequestMapping(value = "search/{marketId}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121402Bean> search(@PathVariable("marketId") String marketId, BasePageParam param) {
        param.setFilter("marketId", marketId);
        DbUtils.buildLikeCondition(param, "fileName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "crtId", DbUtils.LikeMode.PARTIAL);

        PageResult<BY121402Bean> beanPageResult = by121402Logic.findPage(param, BY121402Bean.class);
        return beanPageResult;
    }

    /**
     * 文件上传
     *
     * @param testExcelFile
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "fileUpload/{marketId}/{emplNo}", method = RequestMethod.POST)
    public
    @ResponseBody
    void fileUpload(@PathVariable("marketId") String marketId, @PathVariable("emplNo") String emplNo, MultipartFile testExcelFile, HttpServletResponse response) throws IOException {
        BY121402Bean bean = new BY121402Bean();
        //String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        if (testExcelFile.getSize() > 0) {
            //文件方式上传
            String fileId = UUID.randomUUID().toString();
            String localPath = FileUtils.getSystemTmpDir();
            String fileName = testExcelFile.getOriginalFilename();
            Boolean checkFileSuffixResult = super.checkFileSuffix(testExcelFile, IMG_SUFFIXS);
            if (checkFileSuffixResult) {
                bean.setFileType("0");//图片
            } else {
                bean.setFileType("1");//文件
            }
            File textFile = new File(localPath, fileName);
            testExcelFile.transferTo(textFile);
            Map<String, File> fileMap = new HashMap<>();
            fileMap.put(fileId, textFile);
            Map<String, String> result = FileUploadUtil.uploadFiles(fileMap);
            String fId = result.get(fileId);
            if (!result.isEmpty()) {
                FileUtils.deleteFile(textFile);
            }
            bean.setMarketId(marketId);
            bean.setFileName(fileName);
            bean.setFileServerId(fId);
            //bean.setFileServerIp(fileServerIp);

            this.saveFileInfo(bean, getLoginUser().getEmplName());
            super.callBack(null, "文件上传成功", response);
        } else {
            super.callBack(null, "请选择上传文件", response);
        }
    }

    public void saveFileInfo(BY121402Bean by121402Bean, String emplNo) {
        String fileName = by121402Bean.getFileName();
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        ByMarketTerminalFileInfo fileInfo = new ByMarketTerminalFileInfo();
        fileInfo.setMarketId(by121402Bean.getMarketId());
        fileInfo.setFileName(fileName.substring(0, fileName.lastIndexOf(".")));
        fileInfo.setFileSuff(prefix);
        fileInfo.setFileServerId(by121402Bean.getFileServerId());
        fileInfo.setFileType(by121402Bean.getFileType());
        //fileInfo.setFileServerIp(by121402Bean.getFileServerIp());
        by121402Logic.saveFileInfo(fileInfo, emplNo);
        return;
    }

    /**
     * 文件下载
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "fileDownLoad", method = RequestMethod.POST)
    public void fileDownLoad(BasePageParam param, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fId = request.getParameter("fId");
        String fileName = request.getParameter("fileName");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String filePath = FileUploadUtil.getFile(fId, fileName);
            File file = new File(filePath);

            response.setContentType("application/octet-stream");

            response.setHeader("Content-disposition", "attachment;filename=\"" + new String(fileName.getBytes(), "iso-8859-1") + "\"");

            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, length);
            }
        } catch (FileNotFoundException e) {
            logger.error("下载处理失败：", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    logger.error("下载处理失败：", e);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("下载处理失败：", e);
                }
            }
        }
    }

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteFile/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    int deleteExcel(@PathVariable("id") String id) {
        logger.info("删除批发市场文件信息");
        int result = NumberConst.IntDef.INT_ZERO;
        RsRequest<BaseParam> paramRsRequest = new RsRequest<>();
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        BaseParam param = new BaseParam();
        param.setFilter("id", id);
        paramRsRequest.setParam(param);
        //String url = "http://localhost:8080/msk-buyers/api/by/marketTerminalFileInfo/update";
        String url = SystemServerManager.BuyersServerManage.getUpdateMarketTerminalFileInfo();
        RsResponse<Integer> response = RestClientUtil.post(url, paramRsRequest, new TypeReference<RsResponse<Integer>>() {
        });
        if (response.getResult() != null) {
            result = response.getResult();
            if (result > 0) {
                //删除成功
                return result;
            } else {
                //删除失败
                return result;
            }
        } else {
            //接口调用失败
            return result;
        }
    }

}
