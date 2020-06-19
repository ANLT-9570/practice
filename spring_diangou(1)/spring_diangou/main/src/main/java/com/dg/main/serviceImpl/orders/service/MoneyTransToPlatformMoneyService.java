package com.dg.main.serviceImpl.orders.service;



import com.dg.main.Entity.orders.MoneyTransToPlatformMoney;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.BusinessMapper;


import com.dg.main.dto.orders.MoneyTransToPlatformMoneyDao;
import com.dg.main.param.orders.MoneyTransToPlatformMoneyParam;
import com.dg.main.trans_mapper_obj.CountUserDepositStream;
import com.dg.main.trans_mapper_obj.UserDepositStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoneyTransToPlatformMoneyService {
//    @Autowired
//    MoneyTransToPlatformMoneyMapper moneyTransToPlatformMoneyMapper;
//    @Autowired
//    UserMoneyTransToPlatformMoneyMapper userMoneyTransToPlatformMoneyMapper;
//    @Autowired
//    BusinessMapper businessMapper;
//    @Autowired
//    RealNameMapper realNameMapper;
//    public MoneyTransToPlatformMoney findBy(Long id) {
//
//        return moneyTransToPlatformMoneyMapper.findBy(id);
//    }
//    public List<UserDepositStream> userDepositStream(Integer userId){
//        return userMoneyTransToPlatformMoneyMapper.userDepositStream(userId);
//    }
//    public Long countUserDepositStream(Integer userId){
//        List<CountUserDepositStream> countUserDepositStreams=userMoneyTransToPlatformMoneyMapper.countUserDepositStream(userId);
//        Long total=0l;
//        for(CountUserDepositStream item:countUserDepositStreams){
//        	if(item.getMoney()!=null){
//            total=total+item.getMoney();
//        	}else{
//        		total=total+0l;
//        	}
//        }
//        return total;
//    }
//
//
//    public void deleteBy(Long id){
//        moneyTransToPlatformMoneyMapper.deleteBy(id);
//    }
//
//
//    public int update(MoneyTransToPlatformMoney t) {
//
//        return moneyTransToPlatformMoneyMapper.update(t);
//    }
//
//
//    public int save(MoneyTransToPlatformMoney t) {
//
//        return moneyTransToPlatformMoneyMapper.save(t);
//    }
//
//
//
//    public List<MoneyTransToPlatformMoney> selectAll(MoneyTransToPlatformMoney t) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    //分页
//
//    public slzcResult selectAll(int offset, int limit) {
//
//        slzcResult result = new slzcResult();
//
//        int count = moneyTransToPlatformMoneyMapper.selectCount();
//        List<MoneyTransToPlatformMoneyDao> moneyTransToPlatformMoneyDaos=new ArrayList<>();
////		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
//        List<MoneyTransToPlatformMoney> businesses = moneyTransToPlatformMoneyMapper.selectPageAll( offset,limit);
//        businesses.stream().forEach(i->{
//            MoneyTransToPlatformMoneyDao moneyTransToPlatformMoneyDao=new MoneyTransToPlatformMoneyDao();
//            BeanUtils.copyProperties(i,moneyTransToPlatformMoneyDao);
//            MobileUser business=businessMapper.findBy(new Long(i.getUserId()));
//            if(business!=null){
//                moneyTransToPlatformMoneyDao.setPhone(business.getPhone());
//                if(business.getRole()==2){
//                    List<RealName> realNameList= realNameMapper.selectRealNameById(new Long(i.getUserId()));
//                    if(realNameList!=null&&realNameList.size()>0){
//                        RealName realName=realNameList.get(0);
//                        moneyTransToPlatformMoneyDao.setName(realName.getName());
//                        moneyTransToPlatformMoneyDao.setShopName(realName.getShopName());
//                        moneyTransToPlatformMoneyDao.setRealName(realName.getRealityName());
//                        // userDepositPlatformMoneyDao.setPhone(realName.getPhone());
//
//                    }else{
//                        moneyTransToPlatformMoneyDao.setName(business.getName());
//                    }
//                    moneyTransToPlatformMoneyDao.setRole("商家");
//                }else {
//                    moneyTransToPlatformMoneyDao.setName(business.getName());
//                    moneyTransToPlatformMoneyDao.setRole("买家");
//                }
//
//            }
//            moneyTransToPlatformMoneyDaos.add(moneyTransToPlatformMoneyDao);
//        });
////		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////		for (Business business : businesses) {
////			Timestamp createtime = business.getCreatetime();
////			Timestamp modifytime = business.getModifytime();
////			format.format(createtime.getTime());
////
////		}
//        result.setRows(moneyTransToPlatformMoneyDaos);
//        result.setTotal(count);
//
//        return result;
//    }
//
//
//    public List<MoneyTransToPlatformMoney> selectPageAll(int offset, int limit) {
//
//        return null;
//    }
//
//
//    public int deleteAllId(String [] t) {
//
//        return moneyTransToPlatformMoneyMapper.deleteAllId(t);
//    }
//
//
//
//    public int selectCount() {
//        // TODO Auto-generated method stub
//        return moneyTransToPlatformMoneyMapper.selectCount();
//    }
}
