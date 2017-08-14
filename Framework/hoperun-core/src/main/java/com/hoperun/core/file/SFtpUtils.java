package com.hoperun.core.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 
 * SFtpUtils.
 *
 * @author jiang_nan
 */
public final class SFtpUtils {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SFtpUtils.class);
    /** ftp url */
    private String url;
    /** ftp 端口 */
    private int port;
    /** ftp 用户名 */
    private String username;
    /** ftp 密码 */
    private String pwd;
    /**
     * 构造方法
     * 
     * @param url ftp请求地址
     * @param port 端口
     * @param username 服务器用户名
     * @param pwd 服务器密码
     */
    public SFtpUtils(String url, int port, String username, String pwd) {
        this.url = url;
        this.port = port;
        this.username = username;
        this.pwd = pwd;
    }
    /**
     * 获得SFTP连接
     * @return ChannelSftp
     */
    private ChannelSftp getSFtpConnection(){
        ChannelSftp sftp = null;
        JSch jsch = new JSch();
        Session sshSession = null;
        try {
            sshSession = jsch.getSession(username, url, port);
            sshSession.setPassword(pwd);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            logger.debug("Session connected.");
            logger.debug("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            throw new SystemException("SFTP获得连接失败",e);
        }
        return sftp;
    }
    /**
     * 上传文件或目录到FTP
     * 
     * @param remotePath 上传路径
     * @param localFile 上传的文件或文件夹
     * @return boolean
     */
    public boolean upload(String remotePath, File localFile) {
        boolean result = false;
        ChannelSftp sftp= this.getSFtpConnection();
        try {
            this.cdDir(remotePath, sftp);
            sftp.put(new FileInputStream(localFile), localFile.getName());
            result = true;
        } catch (Exception e) {
            throw new BusinessException("system.error.ftpfail", e);
        } finally {
            sftp.exit();
        }
        return result;
    }
    /**
     * 进入目录，如果目录不存在创建目录
     * @param dir 目录
     * @param sftp SFTP
     */
    private void cdDir(String dir,ChannelSftp sftp){
        try {
            sftp.cd(dir);
        } catch (SftpException e) {//目录不存在异常处理创建目录
            try {
                sftp.mkdir(dir);
                sftp.cd(dir);
            } catch (SftpException e1) {
                throw new BusinessException("system.error.ftpfail", e1);
            }
            
        }
    }
}
