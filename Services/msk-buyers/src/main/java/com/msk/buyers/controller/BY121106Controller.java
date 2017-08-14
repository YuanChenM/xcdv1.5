package com.msk.buyers.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.web.base.BaseUploadController;
import com.msk.buyers.bean.BY121106Bean;
import com.msk.buyers.logic.BY121106Logic;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.BuyersConst;
import com.msk.common.utils.FileUploadUtil;
import com.msk.common.utils.FtpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * BY121101Controller
 *
 * @author pxg
 *
 */
@Controller
@RequestMapping("BY121106")
public class BY121106Controller extends BaseUploadController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121106Controller.class);

    @Autowired
    private BY121106Logic by121106Logic;
    /**
     * 前台图片上传到ftp临时文件服务器
     * @param buyerId
     * @param file
     * @param response
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(String buyerId,String picFlg,MultipartFile file, HttpServletResponse response) throws IOException {
        picFileUpload(buyerId,file);
        return null;
    }
    /**
     * 图片上传
     * @param buyerId
     * @param file
     * @throws java.io.IOException
     */
    public void picFileUpload(String buyerId,String picFlg,MultipartFile file) throws IOException{
        if(file.getSize() > 0) {
            //文件方式上传
            String fileId = UUID.randomUUID().toString();
            String localPath = FileUtils.getSystemTmpDir();
            String fileName = this.getFileName(file);
            String fileSuffix = FileUtils.getFileSuffix(fileName);
            String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
            File textFile = new File(localPath, fileName);
            file.transferTo(textFile);
            Map<String, File> fileMap = new HashMap<>();
            fileMap.put(fileId, textFile);
            Map<String, String> result = FileUploadUtil.uploadFiles(fileMap);
            String fileServerId = result.get(fileId);
            BY121106Bean byLic = new BY121106Bean();
            if(!StringUtil.isNullOrEmpty(fileServerId)){
                fileServerId.replace(StringConst.COMMA,"/");
                if("1".equals(picFlg)){
                    byLic.setBusLicPicServerId(fileServerId);
                    byLic.setBusLicSuf(fileSuffix);
                    byLic.setBusLicPic(fileServerIp + fileServerId);
                }else if("2".equals(picFlg)){
                    byLic.setOrgCertificatePicServerId(fileServerId);
                    byLic.setOrgCertificatePic(fileServerIp + fileServerId);
                    byLic.setOrgCertificateSuf(fileSuffix);
                }else if("3".equals(picFlg)){
                    byLic.setTaxCertificatePicServerId(fileServerId);
                    byLic.setTaxCertificatePic(fileServerIp + fileServerId);
                    byLic.setTaxCertificateSuf(fileSuffix);
                }else if("4".equals(picFlg)){
                    byLic.setFoodCertificatePicServerId(fileServerId);
                    byLic.setFoodCertificatePic(fileServerIp + fileServerId);
                    byLic.setFoodCertificateSuf(fileSuffix);
                }else{
                    byLic.setLegalCertificatePicServerId(fileServerId);
                    byLic.setLegalCertificatePic(fileServerIp + fileServerId);
                    byLic.setLegalCertificateSuf(fileSuffix);
                }
                byLic.setBuyerId(buyerId);
                by121106Logic.modifyByLicPicture(byLic);
            }
            if (!result.isEmpty()) {
                FileUtils.deleteFile(textFile);
            }
        }
    }

    /**
     * 图片上传
     * @param buyerId
     * @param file
     * @throws java.io.IOException
     */
    public void picFileUpload(String buyerId,MultipartFile file) throws IOException{
        //ftpIp地址
        String url = ConfigManager.getFtpIp();
        //ftp登陆密码
        String password=ConfigManager.getFtpPwd();
        //ftp登陆名称
        String userName = ConfigManager.getFtpUser();
        //端口号
        int port= NumberConst.IntDef.INT_TWENTY_ONE;
        FtpUtils ftpUtils = new FtpUtils(url,port,userName,password);
        //上传文件名称
        String fileName = this.getFileName(file);
        //本地文件路径
        String localPath = System.getProperty("java.io.tmpdir");
        //保存到本地
        this.saveFile(file,localPath);
        //图片上传到临时服务器路径
        String uploadFilePath = ConfigManager.getFtpImageRootPath() + BuyersConst.BYPath.TEMPIMAGEPATH + "/" + buyerId +"/";
        //读取本地存储的文件
        File uploadTempFile = new File(localPath,fileName);
        //上传到ftp临时文件服务器
        ftpUtils.upload(uploadFilePath,uploadTempFile);
        //删除本地文件
        uploadTempFile.delete();
    }
}
