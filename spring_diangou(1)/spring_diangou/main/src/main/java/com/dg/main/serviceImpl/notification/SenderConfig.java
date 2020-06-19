package com.dg.main.serviceImpl.notification;

import io.rong.RongCloud;

public class SenderConfig {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "p5tvi9dspeph4";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "UY004qt7XospMS";
    /**
     * 自定义api地址
     * */
    private static final String api = "http://api-cn.ronghub.com";
   public static RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
   public static  final  String jGMasterKey="bc109b8c341232c996c27575";
   public static final String JGKey="3ee33929ff79db4ed5051739";

    public static   String appKey1 = "p5tvi9dspeph4";
    public static   String appSecret1 = "UY004qt7XospMS";
}
