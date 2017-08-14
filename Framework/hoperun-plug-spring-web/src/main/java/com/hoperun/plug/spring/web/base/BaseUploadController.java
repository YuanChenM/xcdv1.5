package com.hoperun.plug.spring.web.base;

import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;

/**
 * 文件上传基类Controller类
 * @author jiang_nan
 *
 */
public class BaseUploadController extends BaseController {
    /**
     * 保存文件
     * @param file 文件流
     * @param basePatch 根目录
     * @throws IllegalStateException
     * @throws IOException
     */
    public void saveFile(MultipartFile file, String basePatch) throws IllegalStateException, IOException {
        if(null == file || file.getSize() == 0L){
            return;
        }
        String fileName = this.getFileName(file);
        this.saveFile(file, basePatch,fileName);
    }

    public void saveFile(MultipartFile file,String basePatch,String newFileName) throws IllegalStateException, IOException{
        if(null == file || file.getSize() == 0L){
            return;
        }
        File saveFile = new File(basePatch, newFileName);
        FileUtils.createFile(saveFile);
        file.transferTo(saveFile);
    }
    /**
     * 保存文件到服务器上,文件命名是按照当前时间的Time。
     * @param file MultipartFile
     * @param basePatch 文件目录
     * @return 文件名称
     * @throws IOException
     * @throws IllegalStateException
     */
    public String saveFileSystemTime(MultipartFile file,String basePatch) throws IllegalStateException, IOException{
        if(null == file || file.getSize() == 0L){
            return null;
        }
        String fileName = this.getFileName(file);
        //获得文件的后缀
        String fileSuffix = FileUtils.getFileSuffix(fileName);
        Date customerDate = DateTimeUtil.getCustomerDate();
        String returFileName = customerDate.getTime() + StringConst.DOT + fileSuffix;
        this.saveFile(file, basePatch, returFileName);
        return returFileName;
    }

    /**
     * 保存文件到服务器上,文件命名是按照当前时间的格式化
     * @param file MultipartFile
     * @param basePatch 文件目录
     * @param dateFormat 时间格式化
     * @return 保存成功返回的文件名称
     * @throws IllegalStateException
     * @throws IOException
     */
    public String saveFileSystemTime(MultipartFile file,String basePatch,String dateFormat) throws IllegalStateException, IOException{
        if(null == file || file.getSize() == 0L){
            return null;
        }
        String fileName = this.getFileName(file);
        //获得文件的后缀
        String fileSuffix = FileUtils.getFileSuffix(fileName);
        Date customerDate = DateTimeUtil.getCustomerDate();
        String returFileName = DateTimeUtil.formatDate(dateFormat, customerDate) + StringConst.DOT + fileSuffix;
        this.saveFile(file, basePatch, returFileName);
        return returFileName;
    }

    public String getFileName(MultipartFile file){
        return file.getOriginalFilename();
    }

    public String getFileSuffix(MultipartFile file) {
        String fileName = this.getFileName(file);
        String fileSuffix = FileUtils.getFileSuffix(fileName);
        return fileSuffix;
    }

    public InputStream getInputStream(MultipartFile file) throws IOException{
        return file.getInputStream();
    }

    /**
     * 根据上传的文件创建Workbook
     * @param file 上传上来的文件
     * @return WorkBook
     * @throws IOException
     */
    public Workbook createWorkbook(MultipartFile file) throws IOException{
        String fileSuffix = this.getFileSuffix(file);
        InputStream is = file.getInputStream();
        Workbook workbook = this.createWorkbook(is,fileSuffix);
        return workbook;
    }

    /**
     * 根据输入流创建Workbook
     * @param inputStream 输入流
     * @param excelType Excel 类型
     * @return WorkBook
     * @throws IOException IO Exception
     */
    private Workbook createWorkbook(InputStream inputStream, String excelType) throws IOException {
        if ("xls".equals(excelType)) {
            return new HSSFWorkbook(inputStream);
        } else if ("xlsx".equals(excelType)) {
            return new XSSFWorkbook(inputStream) ;
        }
        throw new SystemException();
    }

    /**
     * 上传失败或者成功CallBack
     * @param callbackFun Call Back Function
     * @param message Call Back Message
     * @param response Http Response
     * @throws IOException The IO Exception
     */
    protected void callBack(String callbackFun, String message, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<script type='text/javascript'>");
        writer.write("window.parent.");
        if(StringUtil.isEmpty(callbackFun)){
            writer.write("callbackFun('"+message+"')");
        }else{
            writer.write(callbackFun+"(");
            if(!StringUtil.isEmpty(message)){
                writer.write("'"+message+"'");
            }
            writer.write(")");
        }
        writer.write("</script>");
    }

    /**
     * 验证文件名当前文件名是否为容许上传的文件
     * @param file 文件
     * @param fileSuffixArray 可上传的文件名列表
     * @return true代表可以上传,false不可以上传
     */
    protected boolean checkFileSuffix(MultipartFile file,String ...fileSuffixArray){
        if(file==null){
            return true;
        }
        String fileSuffix = this.getFileSuffix(file);
        return Arrays.asList(fileSuffixArray).contains(fileSuffix);
    }

    /**
     * 验证文件大小的
     * @param file 文件
     * @param size 文件大小
     * @return true代表验证成功,false验证失败
     */
    protected boolean checkFileSize(MultipartFile file,long size){
        if(file==null){
            return true;
        }
        long fileSize = file.getSize();
        return size > fileSize;
    }
}

