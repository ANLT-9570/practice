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
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JGAllSender  implements ISender  {
    JGSenderParam param;

    public void setParam(JGSenderParam param) {
        this.param = param;
    }
    @Override
    public Boolean send() {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag(param.getUserIds()))
                .setNotification(Notification.newBuilder()
                        .setAlert(param.getTitle())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(param.getTitle())

                                .addExtra("booleanExtra", false)
                                .addExtra("numberExtra", 1)
                               // .addExtra("jsonExtra", jsonExtra)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .setMessage(Message.content(param.getContent()))
                .build();
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(SenderConfig.jGMasterKey, SenderConfig.JGKey, null, clientConfig);
        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result.statusCode);
            System.out.println(result.sendno);
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
