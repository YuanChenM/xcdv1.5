package com.msk.common.tag;

import com.hoperun.plug.spring.web.tag.BaseTag;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

/**
 * Created by mao_yejun on 2016/8/15.
 */
public class UploadFileTag extends BaseTag {

    private String fileLinkId;
    private String fileName;
    private String uploadButtonId;
    private String callbackFunction;
    private static final String fileServerUrl = SystemServerManager.CommonServerManager.getGetFlieServerUploadForJsp();
    private Boolean loadChangeEventFunction = Boolean.TRUE;
    private Boolean bindUpload = Boolean.TRUE;
    private int fileSize;
    private String fileType = "";
    private String fileSizeMessage = "";
    private String fileTypeMessage = "";
    private static Logger logger = LoggerFactory.getLogger(UploadFileTag.class);

    @Override
    protected void doTag(HttpServletRequest httpServletRequest) throws JspException, IOException {
        JspWriter out = this.getJspContext().getOut();
        out.write(this.createHtml());
        out.write("<script type='text/javascript'>");
        if (isLoadChangeEventFunction()) {
            out.write(changeEventFunction());
        }
        out.write(this.initUploadFunction());
        if (isBindUploadButtonFunction()) {
            out.write(this.bindUploadButtonFunction());
        }
        out.write("</script>");

    }

    private String changeEventFunction() {
        StringBuffer changeEventFunctionJs = new StringBuffer();
        changeEventFunctionJs.append("var changeEventFunction = function(fl, fileName){");
        changeEventFunctionJs.append("    var displayDiv = fl.siblings('.fj-box');");
        changeEventFunctionJs.append("    var fileDisplayObj = $(\"<a href='javascript:void(0);' class='fj' style='margin: 0px 0px 0px 10px; float:left;'>\" + fileName.substring(fileName.lastIndexOf('\\\\') + 1) + \"<img src='" + getRequest().getContextPath() + "/static/images/action/delete.png'></a>\");");
        changeEventFunctionJs.append("    displayDiv.empty();");
        changeEventFunctionJs.append("    displayDiv.append(fileDisplayObj);");
        changeEventFunctionJs.append("    fileDisplayObj.find('img').click(function(){");
        changeEventFunctionJs.append("              displayDiv.empty();");
        changeEventFunctionJs.append("              $.upload.reset(fl.prop('id'));");
        changeEventFunctionJs.append("    });");
        changeEventFunctionJs.append("};");
        return changeEventFunctionJs.toString();
    }

    private String initUploadFunction() {
        StringBuffer uploadCreateJs = new StringBuffer();
        uploadCreateJs.append("$.upload.create('" + this.fileLinkId + "',changeEventFunction, {name:'" + this.fileName + "'},'" + fileSize + "','" + fileType + "','" + fileSizeMessage + "','" + fileTypeMessage + "');");
        return uploadCreateJs.toString();
    }

    private String bindUploadButtonFunction() {
        StringBuffer bindUploadButtonFunctionJs = new StringBuffer();
        bindUploadButtonFunctionJs.append("$('#" + this.uploadButtonId + "').click(function(){");
        bindUploadButtonFunctionJs.append("             $.upload.process({");
        bindUploadButtonFunctionJs.append("                       url :'" + this.fileServerUrl + "',");
        bindUploadButtonFunctionJs.append("                       method: 'post',");
        bindUploadButtonFunctionJs.append("                       success:" + callbackFunction);
        bindUploadButtonFunctionJs.append("              });");
        bindUploadButtonFunctionJs.append("});");
        return bindUploadButtonFunctionJs.toString();
    }

    private String createHtml() {
        StringBuffer uploadHtml = new StringBuffer();
        uploadHtml.append("<a id = '" + this.fileLinkId + "' href = 'javascript:void(0);'> 选择文件 </a>");
        uploadHtml.append("<div class='fj-box' id='" + this.fileName + "'></div>");
        return uploadHtml.toString();
    }

    public String getFileLinkId() {
        return fileLinkId;
    }

    public void setFileLinkId(String fileLinkId) {
        this.fileLinkId = fileLinkId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadButtonId() {
        return uploadButtonId;
    }

    public void setUploadButtonId(String uploadButtonId) {
        this.uploadButtonId = uploadButtonId;
    }

    public String getCallbackFunction() {
        return callbackFunction;
    }

    public void setCallbackFunction(String callbackFunction) {
        this.callbackFunction = callbackFunction;
    }

    public Boolean isLoadChangeEventFunction() {
        return loadChangeEventFunction;
    }

    public Boolean isBindUploadButtonFunction() {
        return bindUpload;
    }

    public void setLoadChangeEventFunction(Boolean loadChangeEventFunction) {
        this.loadChangeEventFunction = loadChangeEventFunction;
    }

    public void setBindUpload(Boolean bindUpload) {
        this.bindUpload = bindUpload;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSizeMessage() {
        return fileSizeMessage;
    }

    public void setFileSizeMessage(String fileSizeMessage) {
        this.fileSizeMessage = fileSizeMessage;
    }

    public String getFileTypeMessage() {
        return fileTypeMessage;
    }

    public void setFileTypeMessage(String fileTypeMessage) {
        this.fileTypeMessage = fileTypeMessage;
    }
}
