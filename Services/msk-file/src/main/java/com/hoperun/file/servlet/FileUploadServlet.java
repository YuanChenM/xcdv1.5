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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;

/**
 * Created by mao_yejun on 2016/4/19.
 */
public class FileUploadServlet extends javax.servlet.http.HttpServlet {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static String fileServerPath = ConfigManager.getFileServerPath();
    private final int MB_SMALL_WIDTH = 30;
    private final int MB_SMALL_HEIGHT = 30;
    private final int MB_BIG_WIDTH = 80;
    private final int MB_BIG_HEIGHT = 80;
    private final int PC_SMALL_WIDTH = 160;
    private final int PC_SMALL_HEIGHT = 160;
    private final int PC_BIG_WIDTH = 240;
    private final int PC_BIG_HEIGHT = 240;
    private static final String JPEG_SUFFIX = "png";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        logger.info("客户端ip地址：" + request.getRemoteAddr());
        //为解析类提供配置信息
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析类的实例
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //设置上传文件大小的上限，-1表示无上限.此处为40m
        fileUpload.setSizeMax(40 * 1024 * 1024);
        List items = new ArrayList();
        JSONObject successResult = new JSONObject();


        try {
            items = fileUpload.parseRequest(request);
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                String fieldName = fileItem.getFieldName();
                String fid = null;
                InputStream inputStream = fileItem.getInputStream();

                // 确定是否是图片
                BufferedImage bi = null;
                bi = ImageIO.read(fileItem.getInputStream());
                if (bi == null) {
                    //进行非图片文件上传
                    fid = post(fileServerPath, inputStream, fieldName);
                    //上传成功并返回了fid
                    successResult.put(fieldName, fid);


                } else {
                    //进行图片上传 to be reSize
                    fid = post(fileServerPath, inputStream, fieldName);

                    successResult.put(fieldName, fid);


                }
            }

            logger.info("返回结果:" + successResult.toString());
            response.getOutputStream().write(successResult.toString().getBytes());
        } catch (FileUploadException e) {
            logger.info(e.toString());
            response.sendError(406, "Not acceptable");
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    /**
     * 改变图片大小
     *
     * @param src
     * @return
     */
    private static BufferedImage imgSizeConverter(BufferedImage src, int height, int width) {
        // 计算原始图片的宽/高
        int srcHeight = src.getHeight();
        int srcWidth = src.getWidth();

//        double scale = 1;
//        if (srcHeight > height || srcWidth > width) {
//            if (srcHeight > srcWidth) {
//                // 按高缩放
//                scale = 1.0 * height / srcHeight;
//            } else {
//                // 按宽缩放
//                scale = 1.0 * width / srcWidth;
//            }
//            srcHeight *= scale;
//            srcWidth *= scale;
//            BufferedImage buffImg = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_RGB);
//            buffImg.getGraphics().drawImage(
//                    src.getScaledInstance(srcWidth, srcHeight, Image.SCALE_SMOOTH), 0, 0, null);
//            return buffImg;
//        }
        srcHeight = height;
        srcWidth = width;
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(
                src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        return buffImg;
    }

    /**
     * 使用绘制的方式去掉图像的alpha值
     *
     * @param
     * @param
     * @return
     */
    protected static BufferedImage get24BitImage(BufferedImage img, Color bgColor) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = newImg.createGraphics();
        graphic.setColor(bgColor);
        graphic.fillRect(0, 0, w, h);
        graphic.drawRenderedImage(img, null);
        graphic.dispose();
        return newImg;
    }

    private static String post(String path, InputStream inputStream, String fileName) throws IOException {
        logger.info("开始上传文件：" + fileName);
        String fid = null;
        ObjectMapper obj = new ObjectMapper();
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(path);
        HttpEntity entity = MultipartEntityBuilder.create().setCharset(Charset.forName("UTF-8")).addBinaryBody(fileName, inputStream).seContentType(ContentType.MULTIPART_FORM_DATA).build();
        post.setEntity(entity);
        HttpResponse rs = client.execute(post);
        Map<?, ?> result = objectMapper.readValue(IOUtils.toString(rs.getEntity().getContent(), "UTF-8"), Map.class);

        fid = (String) result.get("fid");
        logger.info("文件" + fileName + "上传结束，获得fid=" + fid);
        return fid;
    }
}
