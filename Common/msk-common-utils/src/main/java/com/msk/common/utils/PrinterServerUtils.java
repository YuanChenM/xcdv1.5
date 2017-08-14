package com.msk.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public final class PrinterServerUtils {
    private static HttpClientBuilder httpClientBuilder;
    private static int HTTP_STATUS_OK = 200;
    static {
        httpClientBuilder = HttpClients.custom();
    }

    public static InputStream loadPdf(String url, Map<String,String> paramMap) {
        HttpClient httpclient = httpClientBuilder.build();
        HttpPost method = new HttpPost(url);
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        for(Map.Entry<String,String> entry : paramMap.entrySet()){
            BasicNameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
            nameValuePairList.add(nameValuePair);
        }
        try {
            method.setEntity(new UrlEncodedFormEntity(nameValuePairList));
            HttpResponse response = httpclient.execute(method);
            if (response.getStatusLine().getStatusCode() != HTTP_STATUS_OK) {
                throw new RuntimeException("加载PDF失败");
            }
            HttpEntity entity = response.getEntity();
            return entity.getContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
