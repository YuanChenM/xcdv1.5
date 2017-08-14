package com.msk.seller.bean;

import java.io.*;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.file.FtpUtils;
import com.msk.common.config.ConfigManager;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;

/**
 * Created by Administrator on 2016/2/16.
 */
public class SLUploadFile extends BaseUploadController {

    private static Logger logger = LoggerFactory.getLogger(SLUploadFile.class);

    /** 图片文件后缀 */
    private static String[] IMG_SUFFIXS = { "png", "jpg", "jpeg", "bmp", "gif", "PNG", "JPG", "JPEG", "BMP", "GIF" };

    /** 图片文件后缀 */
    private static long IMG_SIZE = 2 * 1024 * 1024 ;

    // ftpIp地址
    String url = ConfigManager.getFtpIp();
    // ftp登陆密码
    String password = ConfigManager.getFtpPwd();
    // ftp登陆名称
    String userName = ConfigManager.getFtpUser();
    // 端口号
    int port = NumberConst.IntDef.INT_TWENTY_ONE;

    /**
     * 卖家图片上传文件检验及上传
     * 
     * @param file 被上传的文件
     * @param uploadFilePath 上传文件的文件路径
     * @param uploadFileName 上传文件后的文件名(不带后缀名)
     * @return
     */
    public void saveUploadFile(MultipartFile file, String uploadFilePath, String uploadFileName) {
        // 1.检查文件是否是图片和图片的大小
        Boolean checkFileSuffixResult = super.checkFileSuffix(file, IMG_SUFFIXS);
        if (!checkFileSuffixResult) {
            throw new BusinessException("上传文件格式不正确");
        }
        Boolean checkFileSizeResult = super.checkFileSize(file, IMG_SIZE);
        if (!checkFileSizeResult) {
            throw new BusinessException("上传文件过大");
        }
        this.deleteFileFromFtp(uploadFilePath, uploadFileName);
        // 取被上传文件的后缀
        String fileSuffix = FileUtils.getFileSuffix(file.getOriginalFilename());
        // 4.文件不存在
        // 4.1 上传文件到本地
        String path = System.getProperty("java.io.tmpdir"); // TODO
        String fileName = uploadFileName + StringConst.DOT + fileSuffix;
        try {
            this.saveFile(file, path, fileName);
        } catch (IOException e) {
            logger.error("FTP上传文件失败", e);
        }
        FtpUtils ftpUtils = new FtpUtils(url, port, userName, password);
        File temp = new File(path, fileName);
        String uploadPath = uploadFilePath;
        ftpUtils.upload(uploadPath, temp);
        temp.delete();
    }

    /**
     * @param uploadFilePath 要删除文件的文件路径
     * @param uploadFileName 要删除文件的文件名(不带后缀名)
     */
    public void deleteFileFromFtp(String uploadFilePath, String uploadFileName) {
        try {
            FTPClient ftp = new FTPClient();
            ftp.connect(url, port);// 连接FTP服务器
            ftp.login(userName, password);
            ftp.enterLocalPassiveMode();
            for (String fileSuffix : IMG_SUFFIXS) {
                try {
                    String filePath = StringUtil.formatMessage("/{0}{1}.{2}", uploadFilePath, uploadFileName,fileSuffix);
                    logger.debug("ftp.listFiles:{}", filePath);
                    FTPFile[] a = ftp.listFiles(filePath);
                    int b = a.length;
                    logger.debug("file number:{}", b);
                    if (b > 0) {
                        logger.debug("ftp.deleteFile:{}", filePath);
                        boolean result = ftp.deleteFile(filePath);
                        logger.debug("ftp.deleteFile {}", result);
                        break;
                    }
                }catch(Exception e){
                    logger.error("FTP删除文件失败", e);
                }
            }
        } catch (IOException e) {
            logger.error("FTP删除文件失败", e);
        }
    }

    /**
     * 查询用户图片是否存在，如果存在，将地址给javaBean，如果没有存在，则不操作
     * 
     * @param uploadFilePath 上传后文件的路径
     * @param uploadFileName 上传后文件的名称
     */
    public String getSuffixIfExist(String uploadFilePath, String uploadFileName) {
        String suffix = "";
        try {
            FTPClient ftp = new FTPClient();
            ftp.connect(url, port);// 连接FTP服务器
            ftp.login(userName, password);
            for (String fileSuffix : IMG_SUFFIXS) {
                FTPFile[] a = ftp.listFiles(uploadFilePath + uploadFileName + StringConst.DOT + fileSuffix);
                int b = a.length;
                if (b > 0) {
                    suffix = fileSuffix;
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("FTP获取文件失败", e);
        }
        return suffix;
    }

    /**
     * 创建分销章程txt文件并上传到ftp
     * 
     * @param uploadFilePath 上传路径
     * @param uploadFileName 上传文件名(没有后缀)
     * @param content 章程内容
     * @author gyh
     */
    public void saveSlDistReguChapToFtp(String uploadFilePath, String uploadFileName, String content) {
        // 判断文件是否存在,存在删除
        this.deleteSlDistReguChapByFtp(uploadFilePath, uploadFileName);
        // 文件不存在了，现在开始创建文件并上传
        // 创建文件到本地
        String path = System.getProperty("java.io.tmpdir");
        String fileName = uploadFileName + StringConst.DOT + "txt";
        File file = new File(path + "\\" + fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(content);
            output.close();
        } catch (Exception e1) {
            logger.error("FTP上传文件失败", e1);
        }
        FtpUtils ftpUtils = new FtpUtils(url, port, userName, password);
        String uploadPath = uploadFilePath;
        ftpUtils.upload(uploadPath, file);
        file.delete();
    }

    /**
     * 判断ftp中分销章程内容文件是否存在,存在即返回nei
     * 
     * @param uploadFilePath 文件所在路径
     * @param uploadFileName 文件名(没有后缀)
     * @return string
     * @author gyh
     */
    public String findSlDistReguChapByFtp(String uploadFilePath, String uploadFileName) {
        // 判断文件在ftp是否存在
        int b = 0;
        try {
            FTPClient ftp = new FTPClient();
            ftp.connect(url, port);// 连接FTP服务器
            ftp.login(userName, password);
            FTPFile[] a = ftp.listFiles(uploadFilePath + "/" + uploadFileName + StringConst.DOT + "txt");
            b = a.length;
            // 存在则返回
            if (b > 0) {
                InputStream ins = null;
                StringBuilder builder = null;
                try {
                    // 从服务器上读取指定的文件
                    ins = ftp.retrieveFileStream(uploadFilePath + "/" + uploadFileName + StringConst.DOT + "txt");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "gbk"));
                    String line;
                    builder = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    reader.close();
                    if (ins != null) {
                        ins.close();
                    }
                    // 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
                    ftp.getReply();
                } catch (IOException e) {
                    logger.error("FTP获取文件失败", e);
                }
                return builder.toString();
            }
        } catch (IOException e) {
            logger.error("FTP获取文件失败", e);
        }
        return null;
    }

    /**
     * 判断ftp中分销章程内容文件是否存在,存在即删除
     * 
     * @param uploadFilePath 文件路径
     * @param uploadFileName 文件名(没有后缀)
     * @author gyh
     */
    public void deleteSlDistReguChapByFtp(String uploadFilePath, String uploadFileName) {
        // 判断文件在ftp是否存在
        int b = 0;
        try {
            FTPClient ftp = new FTPClient();
            ftp.connect(url, port);// 连接FTP服务器
            ftp.login(userName, password);
            FTPFile[] a = ftp.listFiles(uploadFilePath + "/" + uploadFileName + StringConst.DOT + "txt");
            b = a.length;
            // 存在则删除，不存在再上传
            if (b > 0) {
                ftp.deleteFile(uploadFilePath + "/" + uploadFileName + StringConst.DOT + "txt");
            }
        } catch (IOException e) {
            logger.error("FTP删除文件失败", e);
        }
    }
}
