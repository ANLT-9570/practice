package com.dg.main.serviceImpl.orders.service;



import com.dg.main.Entity.users.UserThirdAccount;
import com.dg.main.Entity.orders.UserWithdrawMoney;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.repository.orders.UserWithdrawMoneyRepository;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.BusinessMapper;

import com.dg.main.dao.orders.UserThirdAccountMapper;

import com.dg.main.dto.orders.UserWithdrawMoneyDto;
import com.dg.main.dto.orders.UserWithdrawPlatformMoneyDtoV1;
import com.dg.main.trans_mapper_obj.UserWithdrawStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserWithdrawMoneyService {
    @Autowired
    UserWithdrawMoneyRepository repository;
    public void save(UserWithdrawMoney item){
        repository.save(item);
    }
    public void update(UserWithdrawMoney item){
        repository.save(item);
    }
    public UserWithdrawMoney findByCode(String code){
        return repository.findByWithdrawStreamCode(code);
    }
//    @Autowired
//    UserWithdrawMoneyMapper userWithdrawMoneyMapper;
//    @Autowired
//    UserThirdAccountMapper userThirdAccountMapper;
//    @Autowired
//    BusinessMapper businessMapper;
//    @Autowired
//    RealNameMapper realNameMapper;
//    public UserWithdrawMoney findBy(Long id) {
//
//        return userWithdrawMoneyMapper.findBy(id);
//    }
//
//    public UserWithdrawMoney findByCode(String code){
//        return userWithdrawMoneyMapper.findByCode(code);
//    }
//
//    public void deleteBy(Long id){
//        userWithdrawMoneyMapper.deleteBy(id);
//    }
//    public Long  countUserWithdraw(Integer userId){
//        return userWithdrawMoneyMapper.countUserWithdraw(userId);
//    }
//    public List<UserWithdrawStream> userWithdrawStream(Integer userId){
//        return userWithdrawMoneyMapper.userWithdrawStream(userId);
//    }
//
//    public int update(UserWithdrawMoney t) {
//
//        return userWithdrawMoneyMapper.update(t);
//    }
//
//
//    public int save(UserWithdrawMoney t) {
//
//        return userWithdrawMoneyMapper.save(t);
//    }
//
//    public slzcResult getUnPayedList(){
//        slzcResult result = new slzcResult();
//        List<UserWithdrawMoneyDto> userWithdrawMoneyDtos=new ArrayList<>();
//        userWithdrawMoneyMapper.findUnpayedList().stream().forEach(item->{
//            UserWithdrawMoneyDto userWithdrawMoneyDto=new UserWithdrawMoneyDto();
//            BeanUtils.copyProperties(item,userWithdrawMoneyDto);
//            UserThirdAccount userThirdAccount=userThirdAccountMapper.findByUserId(item.getUserId());
//            if(userThirdAccount!=null) {
//                BeanUtils.copyProperties(userThirdAccount, userWithdrawMoneyDto);
//            }
//            userWithdrawMoneyDtos.add(userWithdrawMoneyDto);
//        });
//        result.setRows(userWithdrawMoneyDtos);
//        result.setTotal(userWithdrawMoneyMapper.countByUnpayedList());
//        return result;
//    }
//
//    public List<UserWithdrawMoney> selectAll(UserWithdrawMoney t) {
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
//        int count = userWithdrawMoneyMapper.selectCount();
//        List<UserWithdrawPlatformMoneyDtoV1> userWithdrawPlatformMoneyDtoV1s=new ArrayList<>();
////		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
//        List<UserWithdrawMoney> businesses = userWithdrawMoneyMapper.selectPageAll( offset,limit);
//        businesses.forEach(i->{
//            UserWithdrawPlatformMoneyDtoV1 userWithdrawPlatformMoneyDtoV1=new UserWithdrawPlatformMoneyDtoV1();
//            BeanUtils.copyProperties(i,userWithdrawPlatformMoneyDtoV1);
//            MobileUser business=businessMapper.findBy(new Long(i.getUserId()));
//            UserThirdAccount userThirdAccount=userThirdAccountMapper.findByUserId(i.getUserId());
//            BeanUtils.copyProperties(userThirdAccount,userWithdrawPlatformMoneyDtoV1);
//            if(business!=null){
//                userWithdrawPlatformMoneyDtoV1.setPhone(business.getPhone());
//                if(business.getRole()==2){
//                    List<RealName> realNameList= realNameMapper.selectRealNameById(new Long(i.getUserId()));
//                    if(realNameList!=null&&realNameList.size()>0){
//                        RealName realName=realNameList.get(0);
//                        userWithdrawPlatformMoneyDtoV1.setName(realName.getName());
//                        userWithdrawPlatformMoneyDtoV1.setShopName(realName.getShopName());
//                        userWithdrawPlatformMoneyDtoV1.setRealName(realName.getRealityName());
//                        // userDepositPlatformMoneyDao.setPhone(realName.getPhone());
//
//                    }else{
//                        userWithdrawPlatformMoneyDtoV1.setName(business.getName());
//                    }
//                    userWithdrawPlatformMoneyDtoV1.setRole("商家");
//                }else {
//                    userWithdrawPlatformMoneyDtoV1.setName(business.getName());
//                    userWithdrawPlatformMoneyDtoV1.setRole("买家");
//                }
//
//            }
//            userWithdrawPlatformMoneyDtoV1s.add(userWithdrawPlatformMoneyDtoV1);
//        });
////		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////		for (Business business : businesses) {
////			Timestamp createtime = business.getCreatetime();
////			Timestamp modifytime = business.getModifytime();
////			format.format(createtime.getTime());
////
////		}
//        result.setRows(userWithdrawPlatformMoneyDtoV1s);
//        result.setTotal(count);
//
//        return result;
//    }
//
//
//    public List<UserWithdrawMoney> selectPageAll(int offset, int limit) {
//
//        return null;
//    }
//
//
//    public int deleteAllId(String [] t) {
//
//        return userWithdrawMoneyMapper.deleteAllId(t);
//    }
//
//
//
//    public int selectCount() {
//        // TODO Auto-generated method stub
//        return 0;
//    }
}
