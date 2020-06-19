package com.dg.main.serviceImpl.orders.service;

import com.dg.main.serviceImpl.logs.RedPackLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.wrapper.RedPackWrapper;
import com.dg.main.util.RedEnvelopesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RedPackCreateService {
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    private RedPackService redPackService;
    @Autowired
    private RedPackLogService redPackLogService;

//    @Transactional
//    public void save(RedPackWrapper redPackWrapper){
//        List<RedPack> redPackList=redPackService.listShop(new Long(redPackWrapper.getRedPack().getUserId()));
//        UserBalance _old=redPackWrapper.getUserBalance();
//        UserBalance _new= UserBalanceFactory.newInstance(_old);
//        if(redPackList.size()>0){
//            for(RedPack item:redPackList){
//                List<RedPackLog> redPackLogList=redPackLogService.untakeList(item.getCode());
//                Long money=0l;
//                for(RedPackLog inner:redPackLogList){
//                    money+=inner.getTakeMoney();
//                    inner.setIsTake(1);
//                    redPackLogService.update(inner);
//                    item.setUserTaked(item.getUserTaked()+1);
//                }
//                _new.setPlatformMoney(_old.getPlatformMoney()+money);
//                _new.setModifyTime(new Date());
//                _new.setOperationTimes(_old.getOperationTimes()+1);
//                userBalanceService.update(_new);
//                userMoneyStreamLogService.saveUserBalances(_old,_new,"RedPackCreateService","save");
//                redPackService.update(item);
//            }
//        }
//
//
//        RedPack redPack=redPackWrapper.getRedPack();
//        _new.setPlatformMoney(_old.getPlatformMoney()-redPack.getPlatformMoney());
//        _new.setModifyTime(new Date());
//        _new.setOperationTimes(_old.getOperationTimes()+1);
//
//        List<Long> redList=new ArrayList<>();
//        RedEnvelopesUtils dd = new RedEnvelopesUtils();
//        redList=dd.splitRedPackets(redPack.getPlatformMoney(),redPack.getTakeNumber(),1000000000l);
//        redList.forEach(i->{
//            RedPackLog redPackLog=new RedPackLog();
//            redPackLog.setTakeMoney(i);
//            redPackLog.setLeftMoney(redPack.getPlatformMoney()-i);
//            redPackLog.setRedPackCode(redPack.getCode());
//            redPackLogService.save(redPackLog);
//        });
//
//
//
//        //    userBalanceService.save(redPackWrapper.getUserBalance());
//        //  userMoneyStreamLogService.save(redPackWrapper.getUserMoneyStreamLog());
//        redPackService.save(redPack);
//        userBalanceService.update(_new);
//        userMoneyStreamLogService.saveUserBalances(_old,_new,"RedPackCreateService","save");
//    }
    @Transactional
    public void save(RedPackWrapper redPackWrapper){
        Gson gson=new Gson();
      //  List<RedPack> redPackList=redPackService.listShop(new Long(redPackWrapper.getRedPack().getUserId()));
        UserBalance _old=redPackWrapper.getUserBalance();
        UserBalance _new= UserBalanceFactory.newInstance(_old);
      //  if(redPackList.size()>0) {
      //      for (RedPack item : redPackList) {
       //         item.setUserTaked(item.getTakeNumber());
              //  Long leftMoney=item.get
            //    List<RedPackLog> redPackLogList = redPackLogService.untakeList(item.getCode());
            //    Long money = 0l;

//                for(RedPackLog inner:redPackLogList){
//                    money+=inner.getTakeMoney();
//                    inner.setIsTake(1);
//                    redPackLogService.update(inner);
//                    item.setUserTaked(item.getUserTaked()+1);
//                }
          //      _new.setPlatformMoney(_old.getPlatformMoney()+item.getLeftMoney());
          //      item.setLeftMoney(0l);
          //      _new.setModifyTime(new Date());
          //      _new.setOperationTimes(_old.getOperationTimes()+1);
          //      userBalanceService.update(_new);
          //      userMoneyStreamLogService.saveUserBalances(_old,_new,"RedPackCreateService","save");
          //      redPackService.update(item);
//            }
         //   }
      //  }


        RedPack redPack=redPackWrapper.getRedPack();
//        _new.setPlatformMoney(_new.getPlatformMoney()-redPack.getPlatformMoney());
//        _new.setModifyTime(new Date());
//        _new.setOperationTimes(_new.getOperationTimes()+1);
        _new.setPlatformMoney(_old.getPlatformMoney()-redPack.getPlatformMoney());
        _new.setModifyTime(new Date());
        _new.setOperationTimes(_old.getOperationTimes()+1);
        List<Long> redList=new ArrayList<>();
        RedEnvelopesUtils dd = new RedEnvelopesUtils();
        redList=dd.splitRedPackets(redPack.getPlatformMoney(),redPack.getTakeNumber(),1000000000l);
//        redList.forEach(i->{
//            RedPackLog redPackLog=new RedPackLog();
//            redPackLog.setTakeMoney(i);
//            redPackLog.setLeftMoney(redPack.getPlatformMoney()-i);
//            redPackLog.setRedPackCode(redPack.getCode());
//            redPackLogService.save(redPackLog);
//        });
            redPack.setGeneratedNumbers(gson.toJson(redList));

            redPack.setLeftMoney(redPack.getPlatformMoney());

        //    userBalanceService.save(redPackWrapper.getUserBalance());
        //  userMoneyStreamLogService.save(redPackWrapper.getUserMoneyStreamLog());
        redPackService.save(redPack);
        userBalanceService.update(_new);
        userMoneyStreamLogService.saveUserBalances(_old,_new,"RedPackCreateService","save", UserStreamEnum.SEND_REDPACK.getIndex(),redPack.getCode());
    }
}
