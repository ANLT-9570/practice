package com.dg.main.serviceImpl.logs;

import com.google.gson.Gson;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.logs.UserMoneyStreamLog;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.BusinessMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserMoneyStreamLogService {
    public static class Extra{
        private String involvedClassName;
        private String involvedMethodName;
        private String exeClassname;
        private String exeMethodName;
        private Integer type;
        private String code;

        public Extra() {
        }

        public String getInvolvedClassName() {
            return involvedClassName;
        }

        public void setInvolvedClassName(String involvedClassName) {
            this.involvedClassName = involvedClassName;
        }

        public String getInvolvedMethodName() {
            return involvedMethodName;
        }

        public void setInvolvedMethodName(String involvedMethodName) {
            this.involvedMethodName = involvedMethodName;
        }

        public String getExeClassname() {
            return exeClassname;
        }

        public void setExeClassname(String exeClassname) {
            this.exeClassname = exeClassname;
        }

        public String getExeMethodName() {
            return exeMethodName;
        }

        public void setExeMethodName(String exeMethodName) {
            this.exeMethodName = exeMethodName;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    @Autowired
    BusinessMapper businessMapper;

//    public UserMoneyStreamLog findBy(Long id) {
//
//        return userMoneyStreamLogMapper.findBy(id);
//    }

    @Transactional
    public void saveUserBalances(UserBalance oldBalance, UserBalance newBalance,Extra extra){
        Gson gson=new Gson();
        extra.setExeMethodName("UserMoneyStreamLogService");
        extra.setExeClassname("saveUserBalances");
        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();
        userMoneyStreamLog.setCreateTime(new Date());
        userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(oldBalance));
        userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(newBalance));
        userMoneyStreamLog.setUserId(oldBalance.getUserId());
        userMoneyStreamLog.setCode(extra.getCode());
        userMoneyStreamLog.setExeClassname(extra.exeClassname);
        userMoneyStreamLog.setInvolvedClassName(extra.involvedClassName);
        userMoneyStreamLog.setTypes(extra.type);
        userMoneyStreamLog.setExeMethodName(extra.exeMethodName);
        userMoneyStreamLog.setInvolvedMethodName(extra.involvedMethodName);
   //     userMoneyStreamLogMapper.save(userMoneyStreamLog);

    }

    @Transactional
    public void saveUserBalances(UserBalance oldBalance, UserBalance newBalance,String className,String methodName){
        Gson gson=new Gson();
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        extra.setExeMethodName("UserMoneyStreamLogService");
        extra.setExeClassname("saveUserBalances");
        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();
        userMoneyStreamLog.setCreateTime(new Date());
        userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(oldBalance));
        userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(newBalance));
        userMoneyStreamLog.setUserId(oldBalance.getUserId());
        userMoneyStreamLog.setCode(extra.getCode());
        userMoneyStreamLog.setExeClassname(extra.exeClassname);
        userMoneyStreamLog.setInvolvedClassName(extra.involvedClassName);
        userMoneyStreamLog.setTypes(extra.type);
        userMoneyStreamLog.setExeMethodName(extra.exeMethodName);
        userMoneyStreamLog.setInvolvedMethodName(extra.involvedMethodName);
     //   userMoneyStreamLogMapper.save(userMoneyStreamLog);
    }
    @Transactional
    public void saveUserBalances(UserBalance oldBalance, UserBalance newBalance,String className,String methodName,Integer type,String code){
        Gson gson=new Gson();
        UserMoneyStreamLogService.Extra extra=new UserMoneyStreamLogService.Extra();
        extra.setInvolvedClassName(className);
        extra.setInvolvedMethodName(methodName);
        extra.setExeMethodName("UserMoneyStreamLogService");
        extra.setExeClassname("saveUserBalances");
        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();
        userMoneyStreamLog.setCreateTime(new Date());
        userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(oldBalance));
        userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(newBalance));
        userMoneyStreamLog.setUserId(oldBalance.getUserId());
        userMoneyStreamLog.setCode(code);
        userMoneyStreamLog.setExeClassname(extra.exeClassname);
        userMoneyStreamLog.setInvolvedClassName(extra.involvedClassName);
        userMoneyStreamLog.setTypes(type);
        userMoneyStreamLog.setExeMethodName(extra.exeMethodName);
        userMoneyStreamLog.setInvolvedMethodName(extra.involvedMethodName);
      //  userMoneyStreamLogMapper.save(userMoneyStreamLog);
    }




    public List<UserMoneyStreamLog> selectAll(UserMoneyStreamLog t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页

    public slzcResult selectAll(int offset, int limit) {

        slzcResult result = new slzcResult();

//        int count = userMoneyStreamLogMapper.selectCount();
//        List<UserStreamLogDto> userStreamLogDtos=new ArrayList<>();
//        Gson gson=new Gson();
////		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
//        List<UserMoneyStreamLog> businesses = userMoneyStreamLogMapper.selectPageAll( offset,limit);
//        businesses.forEach(i->{
//            UserStreamLogDto userStreamLogDto=new UserStreamLogDto();
//
//            MobileUser business=businessMapper.findBy(new Long(i.getUserId()));
//            if(business!=null){
//                userStreamLogDto.setPhone(business.getPhone());
//                if(business.getRole()==2){
//                    List<RealName> realNameList= realNameMapper.selectRealNameById(new Long(i.getUserId()));
//                    if(realNameList!=null&&realNameList.size()>0){
//                        RealName realName=realNameList.get(0);
//                        userStreamLogDto.setName(realName.getName());
//                        userStreamLogDto.setShopName(realName.getShopName());
//                        userStreamLogDto.setRealityName(realName.getRealityName());
//                       // userStreamLogDto.setPhone(realName.getPhone());
//
//                    }else{
//                        userStreamLogDto.setName(business.getName());
//                    }
//                    userStreamLogDto.setRole("商家");
//                }else {
//                    userStreamLogDto.setName(business.getName());
//                    userStreamLogDto.setRole("买家");
//                }
//
//            }
//            userStreamLogDto.setTypes(i.getTypes());
//            userStreamLogDto.setCreateTime(i.getCreateTime());
//            UserBalance pre=gson.fromJson(i.getJsonOfCurrentUserBalance(),UserBalance.class);
//            UserBalance curr=gson.fromJson(i.getJsonOfPreviousUserBalance(),UserBalance.class);
//            userStreamLogDto.setPreMoney(pre.getMoney());
//            userStreamLogDto.setCurrentMoney(curr.getMoney());
//            userStreamLogDto.setPrePlatformMoney(pre.getPlatformMoney());
//            userStreamLogDto.setCurrentPlatfomrMoney(curr.getPlatformMoney());
//            userStreamLogDto.setCode(i.getCode());
//          //  userStreamLogDto.setThirdCode();
//            if(i.getTypes()!=null) {
//                switch (i.getTypes()) {
//                    case 1:
//                        userStreamLogDto.setTypeName(UserStreamEnum.WITHDRAW_MONEY.getName());
//                        break;
//                    case 2:
//                        userStreamLogDto.setTypeName(UserStreamEnum.WITHDRAW_PLATFORM_MONEY.getName());
//                        break;
//                    case 3:
//                        userStreamLogDto.setTypeName(UserStreamEnum.CANCEL_WITHDRAW_MOENY.getName());
//                        break;
//                    case 4:
//                        userStreamLogDto.setTypeName(UserStreamEnum.CANCEL_WITHDRAW_PLATFROM_MONEY.getName());
//                        break;
//                    case 5:
//                        userStreamLogDto.setTypeName(UserStreamEnum.ZHIFUBAO_DEPOSIT_PLATFORM_MONEY.getName());
//                        break;
//                    case 6:
//                        userStreamLogDto.setTypeName(UserStreamEnum.WEIXIN_DEPOSIT_PLATFORM_MONEY.getName());
//                        break;
//                    case 7:
//                        userStreamLogDto.setTypeName(UserStreamEnum.MONEY_TO_PLATFORM_MONEY.getName());
//                        break;
//                    case 8:
//                        userStreamLogDto.setTypeName(UserStreamEnum.SEND_REDPACK.getName());
//                        break;
//                    case 9:
//                        userStreamLogDto.setTypeName(UserStreamEnum.TAKE_REDPACK.getName());
//                        break;
//                    case 10:
//                        userStreamLogDto.setTypeName(UserStreamEnum.SCHEDULER_REDPACK.getName());
//                        break;
//                    case 11:
//                        userStreamLogDto.setTypeName(UserStreamEnum.BUYER_MONEY_PAY_ORDERS.getName());
//                        break;
//                    case 12:
//                        userStreamLogDto.setTypeName(UserStreamEnum.BUYER_PLATFORM_MONEY_PAY_ORDERS.getName());
//                        break;
//                    case 13:
//                        userStreamLogDto.setTypeName(UserStreamEnum.BUYER_MONEY_REFUND_ORDERS.getName());
//                        break;
//                    case 14:
//                        userStreamLogDto.setTypeName(UserStreamEnum.BUYER_PLATFORM_MONEY_REFUND_ORDERS.getName());
//                        break;
//                    case 15:
//                        userStreamLogDto.setTypeName(UserStreamEnum.SELLER_MONEY_GET_ORDERS.getName());
//                        break;
//                    case 16:
//                        userStreamLogDto.setTypeName(UserStreamEnum.SELLER_PLATFORM_FORM_GET_ORDERS.getName());
//                        break;
//                    case 17:
//                        userStreamLogDto.setTypeName(UserStreamEnum.SELLER_ZHIFUBAO_GET_ORDERS.getName());
//                        break;
//                    case 18:
//                        userStreamLogDto.setTypeName(UserStreamEnum.SELLER_WEIXIN_GET_ORDER.getName());
//                        break;
//                    case 19:
//                        userStreamLogDto.setTypeName(UserStreamEnum.SCHEDULER_SELLER_MONEY_GET_ORDERS.getName());
//                        break;
//                    case 20:
//                        userStreamLogDto.setTypeName(UserStreamEnum.SCHEDULER_SELLER_PLATFORM_FORM_GET_ORDERS.getName());
//                        break;
//                    case 21:
//                        userStreamLogDto.setTypeName(UserStreamEnum.SCHEDULER_SELLER_ZHIFUBAO_GET_ORDERS.getName());
//                        break;
//                    case 22:
//                        userStreamLogDto.setTypeName(UserStreamEnum.SCHEDULER_SELLER_WEIXIN_GET_ORDER.getName());
//                        break;
//                    case 23:
//                        userStreamLogDto.setTypeName(UserStreamEnum.WITHDRAW_MONEY.getName());
//                        break;
//                    case 24:
//                        userStreamLogDto.setTypeName(UserStreamEnum.WITHDRAW_MONEY.getName());
//                        break;
//                }
//            }
//            userStreamLogDtos.add(userStreamLogDto);
//
//        });
////		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////		for (Business business : businesses) {
////			Timestamp createtime = business.getCreatetime();
////			Timestamp modifytime = business.getModifytime();
////			format.format(createtime.getTime());
////
////		}
//        result.setRows(userStreamLogDtos);
//        result.setTotal(count);

        return result;
    }


    public List<UserMoneyStreamLog> selectPageAll(int offset, int limit) {

        return null;
    }



}
