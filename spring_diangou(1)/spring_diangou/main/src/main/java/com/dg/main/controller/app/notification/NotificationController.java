//package com.dg.main.controller.app.notification;
//
//import com.dg.main.serviceImpl.orders.notification.JGSenderParam;
//import com.dg.main.serviceImpl.orders.notification.RYSenderParam;
//import com.dg.main.serviceImpl.orders.service.notification.JGNotificationService;
//import com.dg.main.serviceImpl.orders.service.notification.NotificationService;
//import com.dg.main.util.Result;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/v1/notification")
//public class NotificationController {
//    @Autowired
//    NotificationService notificationService;
//    @Autowired
//    JGNotificationService jgNotificationService;
//    @RequestMapping("/sendIOS")
//    public Result sendIOS(@RequestParam Long userId){
//        List<String> temp=new ArrayList<>();
//
//        temp.add("user"+userId+"");
//        String[] strings=new String[temp.size()];
//        JGSenderParam param=new JGSenderParam();
//        param.setUserIds(temp.toArray(strings));
//        return jgNotificationService.sendIos(param);
//    }
//    @RequestMapping("/sendAndroid")
//    public Result sendAndroid(@RequestParam Long userId){
//        List<String> temp=new ArrayList<>();
//
//        temp.add(userId+"");
//        String[] strings=new String[temp.size()];
//        JGSenderParam param=new JGSenderParam();
//        param.setUserIds(temp.toArray(strings));
//        return jgNotificationService.sendIos(param);
//    }
//    @RequestMapping("/sendAllPlatform")
//    public Result sendAllPlatform(@RequestParam Long userId){
//        List<String> temp=new ArrayList<>();
//
//        temp.add(userId+"");
//        String[] strings=new String[temp.size()];
//        JGSenderParam param=new JGSenderParam();
//        param.setUserIds(temp.toArray(strings));
//        return jgNotificationService.sendIos(param);
//    }
//
//}
