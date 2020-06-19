package com.dg.main.serviceImpl.notification;

import io.rong.models.push.Audience;
import io.rong.models.push.Notification;
import io.rong.models.push.PushModel;
import io.rong.models.response.PushResult;

public class RYIOSSender implements ISender {
    private RYSenderParam param;

    public void setParam(RYSenderParam param) {
        this.param = param;
    }
    @Override
    public Boolean send() {
        if(param!=null) {
            param.setContent("teststst");
            PushModel pushmodel = new PushModel();
            pushmodel.setPlatform(new String[]{"ios"});
            Audience audience = new Audience();
            audience.setUserid(param.getUserIds());
            pushmodel.setAudience(audience);
            Notification notification = new Notification();
            notification.setAlert(param.getContent());
            pushmodel.setNotification(notification);
            PushResult result = null;
            try {
                result = SenderConfig.rongCloud.push.push(pushmodel);
                System.out.println("push: " + result.toString());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
        return false;
    }
}
