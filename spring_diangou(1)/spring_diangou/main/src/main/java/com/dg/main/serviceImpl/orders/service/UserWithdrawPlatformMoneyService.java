package com.dg.main.serviceImpl.orders.service;



import com.dg.main.Entity.users.UserThirdAccount;
import com.dg.main.Entity.orders.UserWithdrawMoney;
import com.dg.main.Entity.orders.UserWithdrawPlatformMoney;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.repository.orders.UserWithdrawPlatformMoneyRepository;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.BusinessMapper;

import com.dg.main.dao.orders.UserThirdAccountMapper;

import com.dg.main.dto.orders.UserWithdrawPlatformMoneyDaoV1;
import com.dg.main.dto.orders.UserWithdrawPlatformMoneyDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserWithdrawPlatformMoneyService {
    @Autowired
    UserWithdrawPlatformMoneyRepository repository;
    public void save(UserWithdrawPlatformMoney item){
        repository.save(item);
    }
    public void update(UserWithdrawPlatformMoney item){
        repository.save(item);
    }
    public UserWithdrawPlatformMoney findByCode(String code){
        return repository.findByWithdrawStreamCode(code);
    }
//    @Autowired
//    UserWithdrawPlatformMoneyMapper userWithdrawPlatformMoneyMapper;
//    @Autowired
//    UserThirdAccountMapper userThirdAccountMapper;
//    @Autowired
//    BusinessMapper businessMapper;
//    @Autowired
//    RealNameMapper realNameMapper;
//
//    public void deleteBy(Long id){
//        userWithdrawPlatformMoneyMapper.deleteBy(id);
//    }
//
//    public UserWithdrawPlatformMoney findByCode(String code){
//        return userWithdrawPlatformMoneyMapper.findByCode(code);
//    }
//    public int update(UserWithdrawPlatformMoney t) {
//
//        return userWithdrawPlatformMoneyMapper.update(t);
//    }
//
//    public slzcResult getUnpayedList(){
//        slzcResult result = new slzcResult();
//        List<UserWithdrawPlatformMoneyDto> userWithdrawPlatformMoneyDtos=new ArrayList<>();
//        userWithdrawPlatformMoneyMapper.findUnpayedList().stream().forEach(item->{
//            UserWithdrawPlatformMoneyDto userWithdrawPlatformMoneyDto=new UserWithdrawPlatformMoneyDto();
//
//            BeanUtils.copyProperties(item,userWithdrawPlatformMoneyDto);
//            UserThirdAccount userThirdAccount=userThirdAccountMapper.findByUserId(item.getUserId());
//            BeanUtils.copyProperties(userThirdAccount,userWithdrawPlatformMoneyDto);
//            userWithdrawPlatformMoneyDtos.add(userWithdrawPlatformMoneyDto);
//        });
//        result.setRows(userWithdrawPlatformMoneyDtos);
//        result.setTotal(userWithdrawPlatformMoneyMapper.countByUnpayed());
//        return result;
//    }
//    public int save(UserWithdrawPlatformMoney t) {
//
//        return userWithdrawPlatformMoneyMapper.save(t);
//    }
//
//
//
//    public List<UserWithdrawMoney> selectAll(UserWithdrawPlatformMoney t) {
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
//        int count = userWithdrawPlatformMoneyMapper.selectCount();
//        List<UserWithdrawPlatformMoneyDaoV1> userWithdrawPlatformMoneyDaoV1s=new ArrayList<>();
////		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
//        List<UserWithdrawPlatformMoney> businesses = userWithdrawPlatformMoneyMapper.selectPageAll( offset,limit);
//        businesses.stream().forEach(i->{
//            UserWithdrawPlatformMoneyDaoV1 userWithdrawPlatformMoneyDaoV1=new UserWithdrawPlatformMoneyDaoV1();
//            BeanUtils.copyProperties(i,userWithdrawPlatformMoneyDaoV1);
//            MobileUser business=businessMapper.findBy(new Long(i.getUserId()));
//            UserThirdAccount userThirdAccount=userThirdAccountMapper.findByUserId(i.getUserId());
//            BeanUtils.copyProperties(userThirdAccount,userWithdrawPlatformMoneyDaoV1);
//            if(business!=null){
//                userWithdrawPlatformMoneyDaoV1.setPhone(business.getPhone());
//                if(business.getRole()==2){
//                    List<RealName> realNameList= realNameMapper.selectRealNameById(new Long(i.getUserId()));
//                    if(realNameList!=null&&realNameList.size()>0){
//                        RealName realName=realNameList.get(0);
//                        userWithdrawPlatformMoneyDaoV1.setName(realName.getName());
//                        userWithdrawPlatformMoneyDaoV1.setShopName(realName.getShopName());
//                        userWithdrawPlatformMoneyDaoV1.setRealName(realName.getRealityName());
//                        // userDepositPlatformMoneyDao.setPhone(realName.getPhone());
//
//                    }else{
//                        userWithdrawPlatformMoneyDaoV1.setName(business.getName());
//                    }
//                    userWithdrawPlatformMoneyDaoV1.setRole("商家");
//                }else {
//                    userWithdrawPlatformMoneyDaoV1.setName(business.getName());
//                    userWithdrawPlatformMoneyDaoV1.setRole("买家");
//                }
//
//            }
//            userWithdrawPlatformMoneyDaoV1s.add(userWithdrawPlatformMoneyDaoV1);
//        });
////		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////		for (Business business : businesses) {
////			Timestamp createtime = business.getCreatetime();
////			Timestamp modifytime = business.getModifytime();
////			format.format(createtime.getTime());
////
////		}
//        result.setRows(userWithdrawPlatformMoneyDaoV1s);
//        result.setTotal(count);
//
//        return result;
//    }
//
//
//    public List<UserWithdrawPlatformMoney> selectPageAll(int offset, int limit) {
//
//        return null;
//    }
//
//
//    public int deleteAllId(String [] t) {
//
//        return userWithdrawPlatformMoneyMapper.deleteAllId(t);
//    }
//
//
//
//    public int selectCount() {
//        // TODO Auto-generated method stub
//        return userWithdrawPlatformMoneyMapper.selectCount();
//    }
}
