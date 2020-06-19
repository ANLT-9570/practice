package com.dg.main.serviceImpl.users;


import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.repository.orders.UserBalanceRepository;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserBalanceService {
//    @Autowired
//    UserBalanceMapper userBalanceMapper;
    @Autowired
UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    UserBalanceRepository userBalanceRepository;
    @Transactional
    public UserBalance findBy(Long id) {

        return userBalanceRepository.getOne(id);
    }
    @Transactional
    public UserBalance findByUserId(Long id){
        return userBalanceRepository.findByUserId(id);
    }


    @Transactional
    public void deleteBy(Long id){
        userBalanceRepository.deleteById(id);
    }
//    public UserBalance selectNoLock(Long id){
//        return  userBalanceMapper.selectOneWithNoLock(id);
//    }
//    public UserBalance findOneByUserIdNoLock(Integer id){
//        return userBalanceMapper.findByUserIdWithNoLock(id);
//    }

    @Transactional
    public void update(UserBalance t) {

         userBalanceRepository.save(t);
    }
    @Transactional
    public void createOrders(UserBalance _old, UserBalance _new, Orders order, String className, String methodName){
        System.out.println("-------------start------------user--balance------");
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setCode(order.getOrderCode());
        extra.setExeClassname("UserBalanceService");
        extra.setType(1);
        extra.setExeMethodName("createOrders");
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        userMoneyStreamLogService.saveUserBalances(_old,_new,extra);
        userBalanceRepository.save(_new);
        System.out.println("-------------end------------user--balance------");
        //return temp;
    }
    @Transactional
    public void refundOrders(UserBalance _old,UserBalance _new,Orders order,String className,String methodName){
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setCode(order.getOrderCode());
        extra.setExeClassname("UserBalanceService");
        extra.setType(1);
        extra.setExeMethodName("refundOrders");
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        System.out.println("-------------start------------user--balance------");
        userMoneyStreamLogService.saveUserBalances(_old,_new,extra);
        userBalanceRepository.save(_new);
//        System.out.println("-------------end------------user--balance------");
//        return temp;
    }
    @Transactional
    public void finishOrders(UserBalance _old,UserBalance _new ,Orders order,String className,String methodName){
        System.out.println("-------------start------------user--balance------");
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setCode(order.getOrderCode());
        extra.setExeClassname("UserBalanceService");
        extra.setType(1);
        extra.setExeMethodName("finishOrders");
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        userMoneyStreamLogService.saveUserBalances(_old,_new,extra);
        userBalanceRepository.save(_new);
    }
    @Transactional
    public void withdrawMoney(UserBalance _old,UserBalance _new,String className,String methodName,String code){
        System.out.println("-------------start------------user--balance------");
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setCode(code);
        extra.setExeClassname("UserBalanceService");
        extra.setType(2);
        extra.setExeMethodName("withdrawMoney");
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        userMoneyStreamLogService.saveUserBalances(_old,_new,extra);
        userBalanceRepository.save(_new);
    }
    public void withdrawMoneyV1(UserBalance _old,UserBalance _new,String className,String methodName,String code){
        System.out.println("-------------start------------user--balance------");
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setCode(code);
        extra.setExeClassname("UserBalanceService");
        extra.setType(2);
        extra.setExeMethodName("withdrawMoney");
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        userMoneyStreamLogService.saveUserBalances(_old,_new,extra);
        System.out.println("-------------end------------user--balance------");
    }
    public void withdrawMoneyV1(UserBalance _old,UserBalance _new,String className,String methodName,String code,Integer types){
        System.out.println("-------------start------------user--balance------");
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setCode(code);
        extra.setExeClassname("UserBalanceService");
        extra.setType(types);
        extra.setExeMethodName("withdrawMoney");
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        userMoneyStreamLogService.saveUserBalances(_old,_new,extra);
        System.out.println("-------------end------------user--balance------");
    }
    @Transactional
    public void withdrawPlatformMoney(UserBalance _old,UserBalance _new,String className,String methodName,String code){
        System.out.println("-------------start------------user--balance------");
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setCode(code);
        extra.setExeClassname("UserBalanceService");
        extra.setType(3);
        extra.setExeMethodName("withdrawPlatformMoney");
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        userMoneyStreamLogService.saveUserBalances(_old,_new,extra);
        userBalanceRepository.save(_new);
        System.out.println("-------------end------------user--balance------");
    }
    public void withdrawPlatformMoneyV1(UserBalance _old,UserBalance _new,String className,String methodName,String code){
        System.out.println("-------------start------------user--balance------");
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setCode(code);
        extra.setExeClassname("UserBalanceService");
        extra.setType(3);
        extra.setExeMethodName("withdrawPlatformMoney");
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        userMoneyStreamLogService.saveUserBalances(_old,_new,extra);
    //    userBalanceMapper.update(_new);
        System.out.println("-------------end------------user--balance------");
    }
    public void withdrawPlatformMoneyV1(UserBalance _old,UserBalance _new,String className,String methodName,String code,Integer types){
        System.out.println("-------------start------------user--balance------");
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setCode(code);
        extra.setExeClassname("UserBalanceService");
        extra.setType(types);
        extra.setExeMethodName("withdrawPlatformMoney");
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        userMoneyStreamLogService.saveUserBalances(_old,_new,extra);
        //    userBalanceMapper.update(_new);
        System.out.println("-------------end------------user--balance------");
    }

    @Transactional
    public void redPack(UserBalance _old,UserBalance _new,String className,String methodName,String code){
        System.out.println("-------------start------------user--balance------");
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setCode(code);
        extra.setExeClassname("UserBalanceService");
        extra.setType(4);
        extra.setExeMethodName("redPack");
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        userMoneyStreamLogService.saveUserBalances(_old,_new,extra);
        userBalanceRepository.save(_new);
        System.out.println("-------------end------------user--balance------");
    }
    @Transactional
    public void redPack(UserBalance _old,UserBalance _new,String className,String methodName,String code,Integer types){
        System.out.println("-------------start------------user--balance------");
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setCode(code);
        extra.setExeClassname("UserBalanceService");
        extra.setType(types);
        extra.setExeMethodName("redPack");
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        userMoneyStreamLogService.saveUserBalances(_old,_new,extra);
        userBalanceRepository.save(_new);
        System.out.println("-------------end------------user--balance------");
    }
    public void save(UserBalance t) {

         userBalanceRepository.save(t);
    }



    public List<UserBalance> selectAll(UserBalance t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页

//    public slzcResult selectAll(int offset, int limit) {
//
//        slzcResult result = new slzcResult();
//
//        Long count = userBalanceRepository.count();
//
////		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
//        List<UserBalance> businesses = userBalanceMapper.selectPageAll( offset,limit);
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
//    public List<User> topPlatformMoney(Optional<Integer> topNumber){
//      //  int number=topNumber||new Integer(0);
//        List<UserBalance> userBalances=userBalanceMapper.topPlatmoney(topNumber.orElse(new Integer(100)));
//        //userBalances.stream().map()
//        CompletableFuture<String> task=CompletableFuture.supplyAsync(new Supplier<String>() {
//            @Override
//            public String get() {
//                System.out.println("----------------"+"supplay async");
//                return "123";
//            }
//        });
//        CompletableFuture<Integer> result2=task.thenApply(number->{
//            System.out.println("----------"+"then apply1");
//            return Integer.parseInt(number);
//        });
//        CompletableFuture<Integer> result3=result2.thenApply(number->{
//            System.out.println("-------------"+"then apply2");
//            return number*2;
//        });
//        try {
//            System.out.println(result2.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public List<UserBalance> selectPageAll(int offset, int limit) {

        return null;
    }


//    public int deleteAllId(String [] t) {
//
//        return userBalanceMapper.deleteAllId(t);
//    }



    public int selectCount() {
        // TODO Auto-generated method stub
        return 0;
    }
}
