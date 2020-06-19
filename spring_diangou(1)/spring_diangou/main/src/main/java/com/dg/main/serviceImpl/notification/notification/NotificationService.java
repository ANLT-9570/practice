package com.dg.main.serviceImpl.notification.notification;

import com.dg.main.exception.ExceptionCode;
import com.dg.main.serviceImpl.notification.*;
import com.dg.main.util.Result;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public Result sendAndroid(RYSenderParam param){
        ISender sender=new RYAndroidSender();
        ((RYAndroidSender)sender).setParam(param);
        if(sender.send()){
            return Result.successResult();
        }else{
            return Result.failureResult(ExceptionCode.Failure.NO_CODE);
        }

    }
    public Result sendIos(RYSenderParam param){
        ISender sender=new RYIOSSender();
        ((RYIOSSender)sender).setParam(param);
        if(sender.send()){
            return Result.successResult();
        }else{
            return Result.failureResult(ExceptionCode.Failure.NO_CODE);
        }
    }
    public Result sendAll(RYSenderParam param){
        ISender sender=new RYAllSender();
        ((RYAllSender)sender).setParam(param);
        if(sender.send()){
            return Result.successResult();
        }else{
            return Result.failureResult(ExceptionCode.Failure.NO_CODE);
        }
    }
}
