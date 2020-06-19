package com.dg.main.serviceImpl.orders.service;


import com.dg.main.Entity.settings.MoneyTransToPlatformMoneyRate;
import com.dg.main.repository.setting.MoneyTransToPlatformMoneyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
@Service
public class MoneyTransToPlatformMoneyRateService {

    @Autowired
    MoneyTransToPlatformMoneyRateRepository repository;
    @PostConstruct
    public void post(){
        MoneyTransToPlatformMoneyRate moneyTransToPlatformMoneyRate=getRate();
     //   System.out.println(moneyTransToPlatformMoneyRate);
        if(moneyTransToPlatformMoneyRate==null){
            moneyTransToPlatformMoneyRate=new MoneyTransToPlatformMoneyRate();
            moneyTransToPlatformMoneyRate.setRate(10l);
            moneyTransToPlatformMoneyRate.setTypes(1);
            moneyTransToPlatformMoneyRate.setCreateTime(new Date());
            moneyTransToPlatformMoneyRate.setModifyTime(new Date());
            save(moneyTransToPlatformMoneyRate);
        }
    }

    public MoneyTransToPlatformMoneyRate findBy(Long id) {

        return repository.getOne(id.intValue());
    }

    public MoneyTransToPlatformMoneyRate getRate(){
        return repository.findByTypes(1);
    }

    public void deleteBy(Long id){
        repository.deleteById(id.intValue());
    }


    public void update(MoneyTransToPlatformMoneyRate t) {

         repository.save(t);
    }


    public void save(MoneyTransToPlatformMoneyRate t) {

         repository.save(t);
    }



    public List<MoneyTransToPlatformMoneyRate> selectAll(MoneyTransToPlatformMoneyRate t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页

//    public slzcResult selectAll(int offset, int limit) {
//
//        slzcResult result = new slzcResult();
//
//        int count = moneyTransToPlatformMoneyRateMapper.selectCount();
//
////		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
//        List<MoneyTransToPlatformMoneyRate> businesses = moneyTransToPlatformMoneyRateMapper.selectPageAll( offset,limit);
//
////		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////		for (Business business : businesses) {
////			Timestamp createtime = business.getCreatetime();
////			Timestamp modifytime = business.getModifytime();
////			format.format(createtime.getTime());
////
////		}
//        result.setRows(businesses);
//        result.setTotal(count);
//
//        return result;
//    }


    public List<MoneyTransToPlatformMoneyRate> selectPageAll(int offset, int limit) {

        return null;
    }


    public void deleteAllId(List<MoneyTransToPlatformMoneyRate> t) {

         repository.deleteInBatch(t);
    }



//    public int selectCount() {
//        // TODO Auto-generated method stub
//        return moneyTransToPlatformMoneyRateMapper.selectCount();
//    }
}
