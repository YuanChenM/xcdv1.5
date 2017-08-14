package com.hoperun.file.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoperun.file.utils.ConfigManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by mao_yejun on 2016/9/19.
 */
public class FileUploadForJspServerlet extends javax.servlet.http.HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static String fileServerPath = ConfigManager.getFileServerPath();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException {
        logger.info("客户端ip地址：" + request.getRemoteAddr());
        //为解析类提供配置信息
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析类的实例
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //设置上传文件大小的上限，-1表示无上限.此处为40m
        fileUpload.setSizeMax(40 * 1024 * 1024);
        JSONObject result = new JSONObject();
        JSONObject fids = new JSONObject();
        try {
            PrintWriter printWriter = new PrintWriter(response.getWriter());
            List items = fileUpload.parseRequest(request);
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                String fieldName = fileItem.getFieldName();
                InputStream inputStream = fileItem.getInputStream();
                String fid = post(fileServerPath, inputStream, fieldName);
                result.put("success",true);
                fids.put(fieldName, fid);
                result.put("fids",fids);
            }
            printWriter.write("<script>" +
                    "this.parent.window.postMessage(" + result + ",'*');" +
                    "</script>");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }


    private static String post(String path, InputStream inputStream, String fileName) throws IOException {
        logger.info("开始上传文件：" + fileName);
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(path);
        HttpEntity entity = MultipartEntityBuilder.create().setCharset(Charset.forName("UTF-8")).addBinaryBody(fileName, inputStream).seContentType(ContentType.MULTIPART_FORM_DATA).build();
        post.setEntity(entity);
        HttpResponse rs = client.execute(post);
        Map<?, ?> result = objectMapper.readValue(IOUtils.toString(rs.getEntity().getContent(), "UTF-8"), Map.class);

        String fid = (String) result.get("fid");
        logger.info("文件" + fileName + "上传结束，获得fid=" + fid);
        return fid;
    }
}
