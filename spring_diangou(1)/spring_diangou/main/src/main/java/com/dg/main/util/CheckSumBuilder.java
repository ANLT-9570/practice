package com.dg.main.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;






public class CheckSumBuilder {
    // 计算并获取CheckSum
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    // 计算并获取md5值
    public static String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }

    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    
	private final static String appKey = "bfe04f7f1466e968d3999c203957f2e2";
	private final static String appSecret = "28fb643b9e49";
	private final static String nonce =  "168321"; 
    
	public  static String chartR(String name,String phone) throws Exception{

	     
	      String url = "https://api.netease.im/nimserver/user/create.action";
	      
	      
	      HttpPost httpPost = new HttpPost(url);
	      CloseableHttpClient closeableHttpClient =  HttpClients.createDefault();
	      String curTime = String.valueOf((new Date()).getTime() / 1000L);
	      String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

	      // 设置请求的header
	      httpPost.addHeader("AppKey", appKey);
	      httpPost.addHeader("Nonce", nonce);
	      httpPost.addHeader("CurTime", curTime);
	      httpPost.addHeader("CheckSum", checkSum);
	      httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

	      // 设置请求的参数
	      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	      nvps.add(new BasicNameValuePair("accid", name));
	      nvps.add(new BasicNameValuePair("name", phone));
	      httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));


	      CloseableHttpResponse response = closeableHttpClient.execute(httpPost);

	      String string = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(string);
	      return string;
	}
}
