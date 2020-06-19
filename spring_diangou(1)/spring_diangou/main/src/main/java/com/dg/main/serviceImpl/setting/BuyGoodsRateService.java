package com.dg.main.serviceImpl.setting;


import com.dg.main.Entity.settings.BuyGoodsRate;
import com.dg.main.repository.setting.BuyGoodsRateRepository;
//import com.dg.main.repository.orders.BuyGoodsRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class BuyGoodsRateService {
//    @Autowired
   // BuyGoodsRateMapper buyGoodsRateMapper;
    @Autowired
BuyGoodsRateRepository buyGoodsRateRepository;
    @PostConstruct
    public void post(){
        BuyGoodsRate buyGoodsRate=getRate();
        if(buyGoodsRate==null){
            buyGoodsRate=new BuyGoodsRate();
            buyGoodsRate.setCreateTime(new Date());
            buyGoodsRate.setRate(30l);
            buyGoodsRate.setTypes(1);
         //   buyGoodsRate.setModifyTime(new Date());
            save(buyGoodsRate);
        }
    }

    public BuyGoodsRate findBy(Long id) {

        return buyGoodsRateRepository.getOne(id.intValue());
    }

    public BuyGoodsRate getRate(){
        return buyGoodsRateRepository.findByTypes(1);
    }

  public void deleteBy(Long id){
      buyGoodsRateRepository.deleteById(id.intValue());
  }

    public void update(BuyGoodsRate t) {

        buyGoodsRateRepository.save(t);
    }

    public void save(BuyGoodsRate t) {

        buyGoodsRateRepository.save(t);
    }

    public List<BuyGoodsRate> selectAll(BuyGoodsRate t) {
        // TODO Auto-generated method stub
        return null;
    }

    //分页result

    public void deleteAllId(List<BuyGoodsRate> t) {

        buyGoodsRateRepository.deleteInBatch(t);
    }

}
