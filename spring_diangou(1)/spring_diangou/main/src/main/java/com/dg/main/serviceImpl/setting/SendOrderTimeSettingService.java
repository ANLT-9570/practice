package com.dg.main.serviceImpl.setting;

import com.dg.main.Entity.settings.SendOrderTimeSetting;
import com.dg.main.repository.setting.SendOrderTimeSettingRepository;
import com.dg.main.util.slzcResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class SendOrderTimeSettingService {
    @Autowired
    SendOrderTimeSettingRepository repository;
    @PostConstruct
    public void post(){
        SendOrderTimeSetting sendOrderTimeSetting=getRate();
   //     System.out.println(moneyTransToPlatformMoneyRate);
        if(sendOrderTimeSetting==null){
            sendOrderTimeSetting=new SendOrderTimeSetting();
            sendOrderTimeSetting.setDays(1);
            sendOrderTimeSetting.setTypes(1);
           // sendOrderTimeSetting.setCreateTime(new Date());
         //   moneyTransToPlatformMoneyRate.setModifyTime(new Date());
            save(sendOrderTimeSetting);
        }
    }

    public  SendOrderTimeSetting findBy(Long id) {

        return repository.getOne(id);
    }

    public SendOrderTimeSetting getRate(){
        return repository.findByTypes(1);
    }

    public void deleteBy(Long id){
        repository.deleteById(id);
    }


    public void update( SendOrderTimeSetting t) {

         repository.save(t);
    }


    public void save( SendOrderTimeSetting t) {

         repository.save(t);
    }




}
