package com.msk.utils;


import com.msk.common.config.ConfigManager;
import com.msk.common.utils.FtpUtils;

import java.io.File;
import java.io.InputStream;

/**
 * *WMS FTP 上传Utils
 * *@author jiang_nan
 * *@version 1.0
 **/
public final class WmsFtpUtils {
    /**
     * 默认构造函数
     */
    private WmsFtpUtils(){
    }
    /**
     * 上传文件或目录到FTP
     *
     * @param remotePath 上传路径
     * @param localFile 上传的文件或文件夹
     * @return boolean
     */
    public static boolean uploadXml(String remotePath, File localFile){
        FtpUtils ftpUtils = new FtpUtils(ConfigManager.getWmsFtpIp(),21,ConfigManager.getWmsFtpUser(),ConfigManager.getWmsFtpPwd());
        return ftpUtils.upload(remotePath,localFile);
    }

    public static boolean uploadXml(String remotePath, String xmlFileName, InputStream xmlInputStream){
        FtpUtils ftpUtils = new FtpUtils(ConfigManager.getWmsFtpIp(),21,ConfigManager.getWmsFtpUser(),ConfigManager.getWmsFtpPwd());
        return ftpUtils.upload(remotePath,xmlInputStream,xmlFileName);

    }


}
