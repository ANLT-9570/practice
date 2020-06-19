package com.dg.main.util;

import com.github.wxpay.sdk.WXPayUtil;

import org.apache.http.client.HttpClient;
import org.springframework.http.RequestEntity;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class WebUtils {
    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
    private final static String DEFAULT_ENCODING = "UTF-8";
//    public static Map getMwebUrl(String url, String xmlParam){
//        String jsonStr = null;
//        HttpClient httpClient = new HttpClient();
//        Map map = new HashMap();
//        try {
//            PostMethod method = null;
//          //  RequestEntity reqEntity = new StringRequestEntity(xmlParam,"text/json","UTF-8");
//            method = new PostMethod(url);
//            method.setRequestHeader("Content-type", "application/json; charset=UTF-8");
//            method.setRequestHeader("Accept", "application/json; charset=UTF-8");
//          //  method.setParameter(xmlParam);
//            httpClient.executeMethod(method);
//            StringBuffer resBodyBuf = new StringBuffer();
//            byte[] responseBody = new byte[1024];
//            int readCount = 0;
//            BufferedInputStream is = new BufferedInputStream(method.getResponseBodyAsStream());
//            while((readCount = is.read(responseBody,0,responseBody.length))!=-1){
//                resBodyBuf.append(new String(responseBody,0,readCount,"utf-8"));
//            }
//            jsonStr = resBodyBuf.toString();
//            System.out.println(jsonStr);
//            map = WXPayUtil.xmlToMap(jsonStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
    public static String postData(String urlStr, String data) {
        return postData(urlStr, data, null);
    }

    public static String postData(String urlStr, String data, String contentType) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            if (contentType != null)
                conn.setRequestProperty("content-type", contentType);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
            if (data == null)
                data = "";
            writer.write(data);
            writer.flush();
            writer.close();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
            }
        }
        return null;
    }


}
