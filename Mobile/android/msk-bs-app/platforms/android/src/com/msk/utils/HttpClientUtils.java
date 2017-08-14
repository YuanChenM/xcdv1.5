package com.msk.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public final class HttpClientUtils {

    public static JSONObject post(String url, JSONObject jsonObject){
        Log.i("URL",url);
        DataOutputStream outputStream = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        String responseBody = null;
        JSONObject resultJson = null;
        try {
            HttpURLConnection connection = getConnection(url);
            Map<String,String> requestPropertyMap = new HashMap<String,String>();
            requestPropertyMap.put("Content-Type","application/json; charset=UTF-8");
            requestPropertyMap.put("Accept", "application/json");
            requestPropertyMap.put("contentType", "UTF-8");
            setRequestProperty(connection,requestPropertyMap);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            outputStream = getOutputStream(connection);
            writeOutput(outputStream,jsonObject.toString());
            int statusCode = connection.getResponseCode();
            if (statusCode == 200) {
                inputStream = getInputStream(connection);
                baos = inputStreamToByteOutputStream(inputStream);
                responseBody = baos.toString();
                resultJson = new JSONObject(responseBody);
                resultJson.put("statusCode",200);
            }else if(statusCode == 400){
                resultJson = new JSONObject();
                resultJson.put("statusCode",400);
                resultJson.put("result","参数绑定错误");
            }else if(statusCode == 500){
                resultJson = new JSONObject();
                resultJson.put("statusCode",500);
                resultJson.put("result","系统内部错误");
            }else if(statusCode == 404){
                resultJson = new JSONObject();
                resultJson.put("statusCode",404);
                resultJson.put("result","页面找不到");
            }else {
                resultJson = new JSONObject();
                resultJson.put("statusCode",404);
                resultJson.put("result","不知明错误");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            closeOutputStream(outputStream);
            closeInputStream(inputStream);
            closeOutputStream(baos);
        }
        return resultJson;
    }

    private static void closeInputStream(InputStream inputStream){
        if(inputStream!=null){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void closeOutputStream(ByteArrayOutputStream outputStream){
        if(outputStream!=null){
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static ByteArrayOutputStream inputStreamToByteOutputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos =  new ByteArrayOutputStream();
        int i;
        while((i=inputStream.read())!=-1){
            baos.write(i);
        }
        baos.flush();
        return baos;
    }

    private static InputStream getInputStream(HttpURLConnection connection) throws IOException {
        return new BufferedInputStream(connection.getInputStream());
    }

    private static DataOutputStream getOutputStream(HttpURLConnection connection) throws IOException {
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        return wr;
    }

    private static void writeOutput(DataOutputStream outputStream,String jsonParam) throws IOException {
        Log.i("请求参数:",jsonParam);
        /*outputStream.writeBytes(jsonParam);*/
        outputStream.write(jsonParam.getBytes());
        outputStream.flush();
    }
    private static void closeOutputStream(DataOutputStream outputStream){
        if(outputStream!=null){
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }









    private static void setRequestProperty(HttpURLConnection connection, Map<String,String> propertyMap){
        if(propertyMap!=null&&propertyMap.size()!=0){
            for (Map.Entry<String,String> entry : propertyMap.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                connection.setRequestProperty(key,value);
            }
        }
    }

    private static HttpURLConnection getConnection(String url) throws IOException {
        return (HttpURLConnection)getUrl(url).openConnection();
    }


    private static URL getUrl(String url) throws MalformedURLException {
        return new URL(url);
    }
}
