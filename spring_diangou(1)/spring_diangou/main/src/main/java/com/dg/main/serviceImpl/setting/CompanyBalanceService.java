package com.dg.main.serviceImpl.setting;


import com.dg.main.repository.setting.CompanyBalanceRepository;
import com.dg.main.Entity.settings.CompanyBalance;
import com.dg.main.Entity.users.UserBalance;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class CompanyBalanceService {
    @PostConstruct
    public void post(){
        System.out.println("---------------++++------------");
        CompanyBalance companyBalance=companyBalanceRepository.findByTypes(1);
        if(companyBalance==null){
            companyBalance=new CompanyBalance();
            companyBalance.setMoney(0l);
            companyBalance.setModifyTime(new Date());
            companyBalance.setCreateTime(new Date());
            companyBalance.setPlatformMoney(0l);
            save(companyBalance);
        }
    }
    @Autowired
    CompanyBalanceRepository  companyBalanceRepository;

    public CompanyBalance findBy(Long id) {

        return companyBalanceRepository.getOne(id);
    }
    public void save(CompanyBalance _old, CompanyBalance _new, UserBalance userBalance){
      //  CompanyMoneyStreamLog companyMoneyStreamLog=new CompanyMoneyStreamLog();
     //   Gson gson=new Gson();
      //  companyMoneyStreamLog.setTypes(3);
      //  companyMoneyStreamLog.setStatus(1);

   //     companyMoneyStreamLog.setTypes(1);
      //  companyMoneyStreamLog.setBuyerId(_oldOrders.getCustomerId());
       // companyMoneyStreamLog.setSellerId(_oldOrders.getBusinessId());
       // companyMoneyStreamLog.setCode(_oldOrders.getOrderCode());
     //   companyBalanceMapper.update(_new);
      //  companyMoneyStreamLogMapper.save(companyMoneyStreamLog);
    }
    public CompanyBalance getBalance(){
        return companyBalanceRepository.findByTypes(1);
    }



    public void update(CompanyBalance t) {

         companyBalanceRepository.save(t);
    }

    @Transactional
    public void save(CompanyBalance t) {

         companyBalanceRepository.save(t);
    }




}
