package com.dg.main.serviceImpl.notification.notification;

import com.dg.main.exception.ExceptionCode;
import com.dg.main.serviceImpl.notification.*;
import com.dg.main.util.Result;
import org.springframework.stereotype.Service;

@Service
public class JGNotificationService {
    public Result sendAndroid(JGSenderParam param){
        ISender sender=new JGAndroidSender();
        ((JGAndroidSender)sender).setParam(param);
        if(sender.send()){
            return Result.successResult();
        }else{
            return Result.failureResult(ExceptionCode.Failure.NO_CODE);
        }

    }
    public Result sendIos(JGSenderParam param){
        ISender sender=new JGIOSSender();
        ((JGIOSSender)sender).setParam(param);
        if(sender.send()){
            return Result.successResult();
        }else{
            return Result.failureResult(ExceptionCode.Failure.NO_CODE);
        }
    }
    public Result sendAll(JGSenderParam param){
        ISender sender=new JGAllSender();
        ((JGAllSender)sender).setParam(param);
        if(sender.send()){
            return Result.successResult();
        }else{
            return Result.failureResult(ExceptionCode.Failure.NO_CODE);
        }
    }
}
