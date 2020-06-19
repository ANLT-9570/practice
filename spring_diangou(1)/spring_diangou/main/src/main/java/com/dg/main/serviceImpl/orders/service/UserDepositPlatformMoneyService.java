package com.dg.main.serviceImpl.orders.service;


import com.dg.main.Entity.orders.UserDepositPlatformMoney;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.repository.UserDepositPlatformMoneyRepository;
import com.dg.main.repository.users.UserRealNameRepository;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.BusinessMapper;

import com.dg.main.dao.orders.UserDepositPlatformMoneyMapper;
import com.dg.main.dto.orders.UserDepositPlatformMoneyDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDepositPlatformMoneyService {
    @Autowired
    UserDepositPlatformMoneyMapper userDepositPlatformMoneyMapper;
    @Autowired
    BusinessMapper businessMapper;
   @Autowired
    UserRealNameRepository userRealNameRepository;
   @Autowired
    UserDepositPlatformMoneyRepository userDepositPlatformMoneyRepository;
    public UserDepositPlatformMoney findBy(Long id) {

        return userDepositPlatformMoneyMapper.findBy(id);
    }
    public UserDepositPlatformMoney findByCode(String code){
        return userDepositPlatformMoneyMapper.findByCode(code);
    }

    @Transactional
    public void deleteBy(Long id){
        userDepositPlatformMoneyMapper.deleteBy(id);
    }

    @Transactional
    public void update(UserDepositPlatformMoney t) {

       // return userDepositPlatformMoneyMapper.update(t);
        userDepositPlatformMoneyRepository.save(t);
    }

    @Transactional
    public void save(UserDepositPlatformMoney t) {

      //  return userDepositPlatformMoneyMapper.save(t);
        userDepositPlatformMoneyRepository.save(t);
    }


    @Transactional
    public List<UserDepositPlatformMoney> selectAll(UserDepositPlatformMoney t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页
    @Transactional
    public slzcResult selectAll(int offset, int limit) {

        slzcResult result = new slzcResult();

        int count = userDepositPlatformMoneyMapper.selectCount();
        List<UserDepositPlatformMoneyDao> userDepositPlatformMoneyDaos=new ArrayList<>();
//		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
        List<UserDepositPlatformMoney> businesses = userDepositPlatformMoneyMapper.selectPageAll( offset,limit);
        businesses.stream().forEach(i->{
            UserDepositPlatformMoneyDao userDepositPlatformMoneyDao=new UserDepositPlatformMoneyDao();
            BeanUtils.copyProperties(i,userDepositPlatformMoneyDao);
            MobileUser business=businessMapper.findBy(new Long(i.getUserId()));
            if(business!=null){
                userDepositPlatformMoneyDao.setPhone(business.getPhone());
                if(business.getRole()==2){
//                    List<RealName> realNameList= realNameMapper.selectRealNameById(new Long(i.getUserId()));
//                    if(realNameList!=null&&realNameList.size()>0){
//                        RealName realName=realNameList.get(0);
//                        userDepositPlatformMoneyDao.setName(realName.getName());
//                        userDepositPlatformMoneyDao.setShopName(realName.getShopName());
//                        userDepositPlatformMoneyDao.setRealName(realName.getRealityName());
//                       // userDepositPlatformMoneyDao.setPhone(realName.getPhone());
//
//                    }else{
//                        userDepositPlatformMoneyDao.setName(business.getName());
//                    }
                    userDepositPlatformMoneyDao.setRole("商家");
                }else {
                    userDepositPlatformMoneyDao.setName(business.getName());
                    userDepositPlatformMoneyDao.setRole("买家");
                }

            }
            userDepositPlatformMoneyDaos.add(userDepositPlatformMoneyDao);
        });
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//		for (Business business : businesses) {
//			Timestamp createtime = business.getCreatetime();
//			Timestamp modifytime = business.getModifytime();
//			format.format(createtime.getTime());
//
//		}
        result.setRows(userDepositPlatformMoneyDaos);
        result.setTotal(count);

        return result;
    }

    @Transactional
    public List<UserDepositPlatformMoney> selectPageAll(int offset, int limit) {

        return null;
    }

    @Transactional
    public int deleteAllId(String [] t) {

        return userDepositPlatformMoneyMapper.deleteAllId(t);
    }


    @Transactional
    public int selectCount() {
        // TODO Auto-generated method stub
        return userDepositPlatformMoneyMapper.selectCount();
    }
}
