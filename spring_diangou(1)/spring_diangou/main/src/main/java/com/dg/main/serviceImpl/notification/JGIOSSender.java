package com.dg.main.serviceImpl.notification;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JGIOSSender  implements ISender  {
    JGSenderParam param;

    public void setParam(JGSenderParam param) {
        this.param = param;
    }

    @Override
    public Boolean send() {
        PushPayload pushPayload=PushPayload.newBuilder().setPlatform(Platform.ios())
                .setAudience(Audience.alias(param.getUserIds()))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert("alerlele")
                                .setBadge(1)
                                .setMutableContent(false)
//                                .setSound("happy")
                               // .setSound(sound)
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                .setMessage(Message.content("hahaha"))
               // .setOptions(Options.newBuilder()
                      //  .setApnsProduction(false)
                      //  .build())
                .build();
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(SenderConfig.jGMasterKey, SenderConfig.JGKey, null, clientConfig);
        try {
            PushResult result = jpushClient.sendPush(pushPayload);
            System.out.println(result);
            return true;
        } catch (APIConnectionException e) {
            e.printStackTrace();
            return false;
        } catch (APIRequestException e) {
            e.printStackTrace();
            return false;
        }
      //  return false;
    }
}
