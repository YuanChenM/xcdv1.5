package com.msk.print.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.msk.comm.utils.ConfigManager;
import com.msk.comm.utils.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mao_yejun on 2016/4/18.
 */
public class FileUploadUtil {

    public static final String FILE_SERVICE_URL = ConfigManager.getFileServerUpload() + ConfigManager.getFileServerUploadServices();
    private static final int SUCCESS_RESPONSE = 200;
    public static final String FILE_DOWNLOAD_URL = ConfigManager.getMskFileDownLoadServices();

    /**
     * 文件上传
     *
     * @param files
     * @return
     * @throws java.io.IOException
     */
    public static Map<String, String> uploadFiles(Map<String, File> files) throws IOException {

        Map<String, String> result = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(FILE_SERVICE_URL);
        ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), Charset.forName("UTF-8"));
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
        for (String key : files.keySet()) {

            multipartEntityBuilder.addPart(key, new FileBody(files.get(key)));

        }
        multipartEntityBuilder.seContentType(ContentType.MULTIPART_FORM_DATA);
        HttpEntity entity = multipartEntityBuilder.build();
        httpPost.setEntity(entity);
        HttpResponse response = client.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == SUCCESS_RESPONSE) {
            result = objectMapper.readValue(IOUtils.toString(response.getEntity().getContent(), "UTF-8"), Map.class);

        }
        return result;

    }

    /**
     * 文件流形式上传。
     *
     * @param streamMaps 指定的流文件 map 集。
     * @return 上传成功后的文件信息 map（key：所上传的文件流唯一标识，value：文件存储唯一标识）
     * @throws java.io.IOException
     */
    public static Map<String, String> uploadStreamFiles(Map<String, InputStream> streamMaps) throws IOException
    {
        Map<String, String> result = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(FILE_SERVICE_URL);
        ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), Charset.forName("UTF-8"));
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
        for (String key : streamMaps.keySet())
        {
            multipartEntityBuilder.addBinaryBody(key, streamMaps.get(key));
        }
        multipartEntityBuilder.seContentType(ContentType.MULTIPART_FORM_DATA);
        HttpEntity entity = multipartEntityBuilder.build();
        httpPost.setEntity(entity);
        HttpResponse response = client.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == SUCCESS_RESPONSE)
        {
            result = objectMapper.readValue(IOUtils.toString(response.getEntity().getContent(), "UTF-8"), Map.class);
        }
        return result;
    }

    /**
     * 文件下载
     *
     * @param fid      文件fid
     * @param fileName 文件名
     * @return
     */
    public static String getFile(String fid, String fileName) {
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(FILE_DOWNLOAD_URL + fid);
        InputStream in = null;
        FileOutputStream out = null;
        String path = null;
        try {
            HttpResponse httpResponse = client.execute(httpGet);
            in = httpResponse.getEntity().getContent();
            String tempPath = FileUtils.getSystemTmpDir();
            out = new FileOutputStream(tempPath +"/"+ fileName);
            byte[] b = new byte[1024];
            int n = 0;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            path = tempPath + "/"+fileName;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return path;
    }

    /**
     * 文件下载获取文件流并转化为JSON以String形式返回。
     *
     * @param fid      文件fid
     * @return
     */
    public static String getFileString(String fid) {
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(FILE_DOWNLOAD_URL + fid);
        InputStream in = null;
        String result = null;
        try
        {
            HttpResponse httpResponse = client.execute(httpGet);
            in = httpResponse.getEntity().getContent();

            String currentSys = System.getProperty("os.name");
            if (!StringUtils.isEmpty(currentSys) && currentSys.indexOf("Windows") >= 0) {
                result = IOUtils.toString(in, "GBK");
            } else {
                result = IOUtils.toString(in, "UTF-8");
            }


            //result = inputStream2String(in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    //文件输入流转String
    private static String inputStream2String(InputStream in) throws IOException
    {
        IOUtils.toString(in, "UTF-8");
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for(int n; (n = in.read(b)) != -1;)
        {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}


