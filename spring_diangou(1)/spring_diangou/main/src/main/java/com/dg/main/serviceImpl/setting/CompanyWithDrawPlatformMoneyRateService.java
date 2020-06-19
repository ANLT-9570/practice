package com.dg.main.serviceImpl.setting;


import com.dg.main.Entity.settings.CompanyWithDrawPlatformMoneyRate;
import com.dg.main.repository.setting.CompanyWithDrawPlatformMoneyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class CompanyWithDrawPlatformMoneyRateService {
    @PostConstruct
    public void post(){
        CompanyWithDrawPlatformMoneyRate companyWithDrawPlatformMoneyRate=getRate();
       if(companyWithDrawPlatformMoneyRate==null){
           companyWithDrawPlatformMoneyRate=new CompanyWithDrawPlatformMoneyRate();
           companyWithDrawPlatformMoneyRate.setCreateTime(new Date());
           companyWithDrawPlatformMoneyRate.setRate(30l);
           companyWithDrawPlatformMoneyRate.setTypes(1);
           companyWithDrawPlatformMoneyRate.setModifyTime(new Date());
           save(companyWithDrawPlatformMoneyRate);
       }
    }
//    @Autowired
//    CompanyWithDrawPlatformMoneyRateMapper companyWithDrawPlatformMoneyRateMapper;
@Autowired
CompanyWithDrawPlatformMoneyRateRepository repository;

    public CompanyWithDrawPlatformMoneyRate findBy(Long id) {

        return repository.getOne(id);
    }


    public CompanyWithDrawPlatformMoneyRate getRate(){
        return repository.findByTypes(1);
    }
    public void deleteBy(Long id){
        repository.deleteById(id);
    }


    public void update(CompanyWithDrawPlatformMoneyRate t) {

         repository.save(t);
    }


    public void save(CompanyWithDrawPlatformMoneyRate t) {

         repository.save(t);
    }



    public List<CompanyWithDrawPlatformMoneyRate> selectAll(CompanyWithDrawPlatformMoneyRate t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页

  //  public slzcResult selectAll(int offset, int limit) {

     //   slzcResult result = new slzcResult();

     //   int count = companyWithDrawPlatformMoneyRateMapper.selectCount();

//		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
     //   List<CompanyWithDrawPlatformMoneyRate> businesses = companyWithDrawPlatformMoneyRateMapper.selectPageAll( offset,limit);

//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//		for (Business business : businesses) {
//			Timestamp createtime = business.getCreatetime();
//			Timestamp modifytime = business.getModifytime();
//			format.format(createtime.getTime());
//
//		}
     //   result.setRows(businesses);
     //   result.setTotal(count);

    //    return result;
  //  }


    public List<CompanyWithDrawPlatformMoneyRate> selectPageAll(int offset, int limit) {

        return null;
    }


    public void deleteAllId(List<CompanyWithDrawPlatformMoneyRate> t) {

         repository.deleteInBatch(t);
    }



//    public int selectCount() {
//        // TODO Auto-generated method stub
//        return companyWithDrawPlatformMoneyRateMapper.selectCount();
//    }
}
