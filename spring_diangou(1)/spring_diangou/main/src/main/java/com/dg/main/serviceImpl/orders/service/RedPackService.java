package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.users.MobileUser;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.log.RedPackLogRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.vo.RedPackVo;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.logs.RedPackLog;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.util.slzcResult;
;
import com.dg.main.dao.orders.RedPackLogMapper;
import com.dg.main.dao.orders.RedPackMapper;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.util.Result;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.dg.main.exception.ExceptionCode.Failure.*;


@Service
public class RedPackService {
    @Autowired
    RedPackMapper redPackMapper;
    @Autowired
    RedPackLogMapper redPackLogMapper;
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    RedPackLogRepository redPackLogRepository;
    @Autowired
    MobileUserRepository mobileUserRepository;

    public Result countUserMoney(Long userId){
        Map<String,String> map=new HashMap<>();
        map.put("total",redPackMapper.countUserMoney(userId)+"");
        map.put("today",redPackMapper.countTodaySendRedpack(userId)+"");
        return Result.successResult(map);
    }
    public RedPack findBy(Long id) {

        return redPackMapper.findBy(id);
    }
    public List<RedPack> schedulerList(){
        return redPackMapper.schedulerList();
    }
    public List<RedPack> isSend(Long userId){
        return redPackMapper.isSend(userId);
    }
    public List<RedPack> isSendv1(Long userId){
        return redPackMapper.isSendv1(userId);
    }
//    @Transactional
//    public Result takeRed(Integer userId, Long businessId ){
////        RedPack redPack=redPackMapper.findBy(redId);
////    	RedPack redPack=redPackMapper.findBy(businessId);
//    	RedPack redPack=redPackMapper.findByUid(businessId);
//        if(redPack!=null){
////            Business business=mobileUserMapper.findBy(redPack.getUserId());
////            if(business.getMobileUseId()==new Long(userId).longValue()){
////                return Result.failureResult(SELLER_CANNOT_TAKE_RED_PACK);
////            }
//            if(redPack.getUserId()==userId){
//                return Result.failureResult(SELLER_CANNOT_TAKE_RED_PACK);
//            }
//            RedPackLog redPackLog1=redPackLogMapper.findByCodeAndUserId(userId,redPack.getCode());
//            if(redPackLog1!=null){
//                return Result.failureResult(USER_TAKED_RED_PACK);
//            }
//            RedPackLog rand=redPackLogMapper.randRedPackLog(redPack.getCode());
//            if(rand!=null){
//                rand.setUserId(userId);
//                rand.setIsTake(1);
//                rand.setTakeTime(new Timestamp(new Date().getTime()));
//
//                redPackLogMapper.update(rand);
//                redPack.setIsTaked(redPack.getUserTaked()+1);
//                redPackMapper.update(redPack);
//                UserBalance _old=userBalanceService.findByUserId(userId);
//                UserBalance _new= UserBalanceFactory.newInstance(_old);
//                _new.setOperationTimes(_old.getOperationTimes()+1);
//                _new.setPlatformMoney(_old.getPlatformMoney()+rand.getTakeMoney());
//                _new.setModifyTime(new Timestamp(new Date().getTime()));
//                userBalanceService.redPack(_old,_new,"RedPackService","takeRed",redPack.getCode());
//                return Result.successResult(rand.getTakeMoney());
//            }else{
//                return Result.failureResult(REDPACK_DONE);
//            }
////            if(redPack.getPlatformMoney()>0) {
////                ThreadLocalRandom random = ThreadLocalRandom.current();
////                Long money = redPack.getPlatformMoney();
////                Long next = random.nextLong(money) + 1;
////                Long left = money - next;
//////            if(next>money/2){
//////
//////            }
////                redPack.setPlatformMoney(left);
////                redPack.setVersion(redPack.getVersion() + 1);
////                RedPackLog redPackLog = new RedPackLog();
////                redPackLog.setLeftMoney(left);
////                redPackLog.setRedPackCode(redPack.getCode());
////                redPackLog.setTakeMoney(next);
////                redPackLog.setUserId(userId);
////                UserBalance _old=userBalanceService.findByUserId(userId);
////                UserBalance _new= UserBalanceFactory.newInstance(_old);
////                _new.setOperationTimes(_old.getOperationTimes()+1);
////                _new.setPlatformMoney(_old.getPlatformMoney()+next);
////                _new.setModifyTime(new Timestamp(new Date().getTime()));
////                userBalanceService.redPack(_old,_new,"RedPackService","takeRed",redPack.getCode());
////                redPackMapper.update(redPack);
////                redPackLogMapper.save(redPackLog);
////                return Result.successResult();
////            }else{
////                return Result.failureResult(REDPACK_DONE);
////            }
//        }
//        return Result.failureResult(REDPACK_NOT_EXISTS);
//    }

    @Transactional
    public Result takeRed(Long userId, Long businessId ){
//        RedPack redPack=redPackMapper.findBy(redId);
//    	RedPack redPack=redPackMapper.findBy(businessId);
        RedPack redPack=redPackMapper.findByUid(businessId);
        Gson gson=new Gson();
        if(redPack!=null){
//            Business business=mobileUserMapper.findBy(redPack.getUserId());
//            if(business.getMobileUseId()==new Long(userId).longValue()){
//                return Result.failureResult(SELLER_CANNOT_TAKE_RED_PACK);
//            }
            if(redPack.getUserId()==userId){
                return Result.failureResult(SELLER_CANNOT_TAKE_RED_PACK);
            }
            RedPackLog redPackLog1=redPackLogMapper.findByCodeAndUserId(userId,redPack.getCode());
            if(redPackLog1!=null){
                return Result.failureResult(USER_TAKED_RED_PACK);
            }
            if(redPack.getLeftMoney()>0){
                RedPackLog rand=new RedPackLog();
               UserBalance _old=userBalanceService.findByUserId(userId);
                UserBalance _new= UserBalanceFactory.newInstance(_old);
                List<Long> numbers=gson.fromJson(redPack.getGeneratedNumbers(), new TypeToken<List<Long>>() {
                }.getType());
                rand.setUserId(userId);
                rand.setIsTake(1);
                rand.setTakeTime(new Timestamp(new Date().getTime()));
                rand.setTakeMoney(numbers.get(0));
                rand.setRedPackCode(redPack.getCode());

                rand.setLeftMoney(redPack.getLeftMoney()-numbers.get(0));
                redPack.setLeftMoney(redPack.getLeftMoney()-numbers.get(0));
                rand.setRedPackCode(redPack.getCode());
                numbers.remove(0);
                redPack.setGeneratedNumbers(gson.toJson(numbers));
                redPack.setUserTaked(redPack.getUserTaked()+1);
                redPack.setVersion(redPack.getVersion()+1);
                _new.setOperationTimes(_old.getOperationTimes()+1);
                _new.setPlatformMoney(_old.getPlatformMoney()+rand.getTakeMoney());
                _new.setModifyTime(new Timestamp(new Date().getTime()));
                redPackMapper.update(redPack);
                redPackLogMapper.save(rand);
                userBalanceService.redPack(_old,_new,"RedPackService","takeRed"
                        ,redPack.getCode(), UserStreamEnum.TAKE_REDPACK.getIndex());
                return Result.successResult(rand.getTakeMoney());
            }else {
                return Result.failureResult(REDPACK_DONE);
            }
//            RedPackLog rand=redPackLogMapper.randRedPackLog(redPack.getCode());
//            if(rand!=null){
//                rand.setUserId(userId);
//                rand.setIsTake(1);
//                rand.setTakeTime(new Timestamp(new Date().getTime()));
//
//                redPackLogMapper.update(rand);
//                redPack.setIsTaked(redPack.getUserTaked()+1);
//                redPackMapper.update(redPack);
//                UserBalance _old=userBalanceService.findByUserId(userId);
//                UserBalance _new= UserBalanceFactory.newInstance(_old);
//                _new.setOperationTimes(_old.getOperationTimes()+1);
//                _new.setPlatformMoney(_old.getPlatformMoney()+rand.getTakeMoney());
//                _new.setModifyTime(new Timestamp(new Date().getTime()));
//                userBalanceService.redPack(_old,_new,"RedPackService","takeRed",redPack.getCode());
//                return Result.successResult(rand.getTakeMoney());
//            }else{
//                return Result.failureResult(REDPACK_DONE);
//            }
//            if(redPack.getPlatformMoney()>0) {
//                ThreadLocalRandom random = ThreadLocalRandom.current();
//                Long money = redPack.getPlatformMoney();
//                Long next = random.nextLong(money) + 1;
//                Long left = money - next;
////            if(next>money/2){
////
////            }
//                redPack.setPlatformMoney(left);
//                redPack.setVersion(redPack.getVersion() + 1);
//                RedPackLog redPackLog = new RedPackLog();
//                redPackLog.setLeftMoney(left);
//                redPackLog.setRedPackCode(redPack.getCode());
//                redPackLog.setTakeMoney(next);
//                redPackLog.setUserId(userId);
//                UserBalance _old=userBalanceService.findByUserId(userId);
//                UserBalance _new= UserBalanceFactory.newInstance(_old);
//                _new.setOperationTimes(_old.getOperationTimes()+1);
//                _new.setPlatformMoney(_old.getPlatformMoney()+next);
//                _new.setModifyTime(new Timestamp(new Date().getTime()));
//                userBalanceService.redPack(_old,_new,"RedPackService","takeRed",redPack.getCode());
//                redPackMapper.update(redPack);
//                redPackLogMapper.save(redPackLog);
//                return Result.successResult();
//            }else{
//                return Result.failureResult(REDPACK_DONE);
//            }
        }
        return Result.failureResult(REDPACK_NOT_EXISTS);
    }
    public List<RedPack> listShop(Long shopId){
        return redPackMapper.shopList(shopId);
    }
    public void deleteBy(Long id){
        redPackMapper.deleteBy(id);
    }


    public int update(RedPack t) {

        return redPackMapper.update(t);
    }


    public int save(RedPack t) {

        return redPackMapper.save(t);
    }



    public List<RedPack> selectAll(RedPack t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页

    public slzcResult selectAll(int offset, int limit) {

        slzcResult result = new slzcResult();

        int count = redPackMapper.selectCount();

//		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
        List<RedPack> businesses = redPackMapper.selectPageAll( offset,limit);

//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//		for (Business business : businesses) {
//			Timestamp createtime = business.getCreatetime();
//			Timestamp modifytime = business.getModifytime();
//			format.format(createtime.getTime());
//
//		}
        result.setRows(businesses);
        result.setTotal(count);

        return result;
    }


    public List<RedPack> selectPageAll(int offset, int limit) {

        return null;
    }


    public int deleteAllId(String [] t) {

        return redPackMapper.deleteAllId(t);
    }



    public int selectCount() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    
	public List<RedPackVo> select() {
		
		
		
		List<RedPackVo> redPacks = redPackMapper.tokizaneRedPack();
//		HashSet hashSet = guilei(redPacks,redPacks);
		
//		for (RedPackVo redPack : redPacks) {
////			System.out.println(redPack);
//			hashSet.add(redPack.getShopId());
//		}
//		
//		for (Long long1 : hashSet) {
//			List<RedPackVo> list = new ArrayList<RedPackVo>();
//			
//			for (RedPackVo redPack : redPacks) {
//				if (long1.equals(redPack.getShopId())) {
//					list.add(redPack);
//				}
//			}
//			
//			Collections.sort(list,new Comparator<RedPackVo>() {
//				@Override
//				public int compare(RedPackVo s1, RedPackVo s2) {
//					long a = s1.getShopId() - s2.getShopId();
//					if (a == 0) {
//						
//					}
//					return Integer.valueOf(String.valueOf(a));
//				}
//			});
//			
//			for (RedPackVo redPackVo : redPacks) {
//				System.out.println(redPackVo);
//			}
////			RedPackVo ruleSort = RuleSort(list);
//			
//		}
		
		
//		System.out.println(hashSet);
		return null;
	}
	
	//按照规则排序
	public RedPackVo RuleSort(List<RedPackVo> list) {
		
//		Collections.sort(list,new Comparator<RedPackVo>() {
//
//			@Override
//			public int compare(RedPackVo r1, RedPackVo r2) {
//
//				Timestamp time1 = r1.getCreateTime();
//				Timestamp time2 = r2.getCreateTime();
//
//				Long t1 = r1.getTakeMoney();
//				Long t2 = r1.getTakeMoney();
//
//				//				//如果为true说明time1的时间比2的早反之
//				boolean before = time1.before(time2);
//				return t1 - t2 > 0 ? 1 : 0;
////				if (time1.equals(time2)) {
////					System.out.println(time1);
////					System.out.println(time2);
////					System.out.println("《---------------------相同----------------》");
////					Long t3 = t1-t2;
////					if (t3 > 0) {
////						return 1;
////					}else {
////						return 0;
////					}
//////					return t3 > 0 ? 1:0;
////					//return Integer.valueOf(String.valueOf());
////				}else {
////					System.out.println(time1);
////					System.out.println(time2);
////					System.out.println("《============不同==============》");
////					if (before) {
////						return 1;
////					}else {
////						return 0;
////					}
////				}
//
//			}
//		});
		for (RedPackVo redPack : list) {
			System.out.println(redPack);
		}
		RedPackVo RedPackVo = list.get(0);
		if (RedPackVo != null) {
			return RedPackVo;
		}
		return null;
	}
	
	//归类
	public HashSet guilei(List<RedPackVo> redPacks) {
		HashSet<Long> hashSet = new HashSet<Long>();
		for (RedPackVo redPack : redPacks) {
//			System.out.println(redPack);
			hashSet.add(redPack.getShopId());
		}
		return hashSet;
	}
	
	//获取list金币最大值
	public RedPackVo max(HashSet<Long> hashSet, List<RedPackVo> redPacks) {
		//最大的数据
		RedPackVo RedPackVo = new RedPackVo();
		for (Long long1 : hashSet) {
				List<RedPackVo> list = new ArrayList<RedPackVo>();
				for (RedPackVo redPack : redPacks) {
					if (long1.equals(redPack.getShopId())) {
						list.add(redPack);
					}
				}
			
		}
		return  RedPackVo;
	}

    public Result findByShopIdRecord(Long shopId,String dateTime) {
        if(shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        if(StringUtils.isEmpty(dateTime)){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            dateTime = df.format(new Date());
        }
        List<Map<String,String>> lists = Lists.newArrayList();

        List<RedPackLog> list = redPackLogRepository.findByShopIdAndCreateTime(shopId, "%" + dateTime + "%");
        for(RedPackLog redPackLog:list){
            Map<String,String> map = Maps.newHashMap();
            Long userId = redPackLog.getUserId();
            Optional<MobileUser> optional = mobileUserRepository.findById(userId);
            map.put("userName",optional.get().getName());
            map.put("db",redPackLog.getTakeMoney().toString());
            lists.add(map);
        }
        return Result.successResult(lists);
    }
}
