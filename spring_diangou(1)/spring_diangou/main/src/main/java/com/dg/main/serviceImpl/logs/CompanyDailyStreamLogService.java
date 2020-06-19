package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.CompanyDailyStreamLog;
import com.dg.main.repository.log.CompanyDailyStreamLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyDailyStreamLogService {
    @Autowired
    CompanyDailyStreamLogRepository repository;
    public CompanyDailyStreamLog findBy(Long id) {

        return repository.getOne(id);
    }

    public void findRecord(CompanyDailyStreamLog t){
//        Integer dayOfYear=t.getDayOfyear();
//        Integer years=t.getYears();
//        CompanyDailyStreamLog temp=companyDailyStreamLogMapper.findByDayAndYear(dayOfYear,years);
//        if(temp==null){
//            temp=new CompanyDailyStreamLog();
//         //   BeanUtils.copyProperties(t,temp);
//            temp.setWeixinOutcome(t.getWeixinOutcome());
//            temp.setZhifubaoOutcome(t.getZhifubaoOutcome());
//            temp.setOutcome(t.getOutcome());
//            temp.setIncome(t.getIncome());
//            temp.setBuyOutcome(t.getBuyOutcome());
//            temp.setTypes(t.getTypes());
//            temp.setZhifubaoIncome(t.getZhifubaoIncome());
//            temp.setWeixinIncome(t.getWeixinIncome());
//            temp.setYears(t.getYears());
//            temp.setMonth(t.getMonth());
//            temp.setCreateTime(t.getCreateTime());
//            temp.setDayOfyear(t.getDayOfyear());
//
//            save(temp);
//        }else{
//            temp.setIncome(temp.getIncome()+t.getIncome());
//            temp.setOutcome(temp.getOutcome()+t.getOutcome());
//            temp.setWeixinIncome(t.getWeixinIncome()+temp.getWeixinIncome());
//            temp.setWeixinOutcome(t.getWeixinOutcome()+temp.getWeixinOutcome());
//            temp.setZhifubaoIncome(t.getZhifubaoIncome()+temp.getZhifubaoIncome());
//            temp.setZhifubaoOutcome(t.getZhifubaoOutcome()+temp.getZhifubaoOutcome());
//            temp.setBuyOutcome(t.getBuyOutcome()+temp.getBuyOutcome());
//            update(temp);
//        }
    }




    public List<CompanyDailyStreamLog> selectAll(CompanyDailyStreamLog t) {
        // TODO Auto-generated method stub
        return null;
    }



}
