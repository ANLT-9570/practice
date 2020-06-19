package com.dg.main.scheduler;

import com.dg.main.Entity.logs.RedPackLog;
import com.dg.main.Entity.logs.RedpackAreaRankLog;
import com.dg.main.Entity.logs.RedpackRankLog;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.serviceImpl.logs.*;
import com.dg.main.Entity.orders.*;
import com.dg.main.dao.orders.OrdersMapper;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.serviceImpl.setting.CompanyBalanceService;
import com.dg.main.serviceImpl.shop.ShopsService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class TestScheduler {
    @Autowired
    RedPackService redPackService;
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    CompanyBalanceService companyBalanceService;
    @Autowired
    OrderStreamLogService orderStreamLogService;
    @Autowired
    CompanyMoneyStreamLogService companyMoneyStreamLogService;
    @Autowired
    SellerBuyUseThirdPayLogService sellerBuyUseThirdPayLogService;
    @Autowired
    RedPackLogService redPackLogService;
    @Autowired
    RedPackRankLogService redPackRankLogService;
    @Autowired
    RedpackAreaRankLogService redpackAreaRankLogService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ShopsService shopsService;


//    @Transactional
//    @Scheduled(cron="0 */30 * * * ?")
   public void settleRedPack(){
      //  System.out.println("--------------");

    //    System.out.println( DateUtils.getSysDateTimeString());
        List<RedPack> redPackList=redPackService.schedulerList();
        if(redPackList.size()>0){
            for(RedPack item:redPackList){
                UserBalance _old=userBalanceService.findByUserId(item.getUserId());
                UserBalance _new= UserBalanceFactory.newInstance(_old);
                item.setUserTaked(item.getTakeNumber());
                _new.setPlatformMoney(_old.getPlatformMoney()+item.getLeftMoney());
                item.setLeftMoney(0l);
                _new.setModifyTime(new Date());
                _new.setOperationTimes(_old.getOperationTimes()+1);
                userBalanceService.update(_new);
                userMoneyStreamLogService.saveUserBalances(_old,_new,"TestScheduler","settleRedPack"
                        , UserStreamEnum.SCHEDULER_REDPACK.getIndex(),item.getCode());
                redPackService.update(item);
            }
        }
   }
//   @Transactional
//   @Scheduled(cron="0 */40 * * * ?")
   public void settleOrder(){
//       List<Orders> ordersList=ordersMapper.unsettleOrders();
//       Gson gson=new Gson();
//       if(ordersList.size()>0){
//           for(Orders item:ordersList){
//
//               UserBalance _oldBalance=userBalanceService.findByUserId(item.getBusinessId());
//               UserBalance _newBalance= UserBalanceFactory.newInstance(_oldBalance);
//               CompanyBalance _oldCompanyBalance=companyBalanceService.getBalance();
//               CompanyBalance _newCompanyBalance=companyBalanceService.getBalance();
//               CompanyMoneyStreamLog companyMoneyStreamLog=new CompanyMoneyStreamLog();
//               //  Orders _oldOrders=ordersDeliveryWrapper.getOrders();
//               Orders _newOrders= OrderFactory.newInstance(item);
//               companyMoneyStreamLog.setStatus(1);
//               companyMoneyStreamLog.setTypes(1);
//               companyMoneyStreamLog.setBuyerId(item.getCustomerId());
//               companyMoneyStreamLog.setSellerId(item.getBusinessId());
//               companyMoneyStreamLog.setCode(item.getOrderCode());
//               LocalDateTime localDateTime=LocalDateTime.now();
//
//               SellerBuyUseThirdPayLog sellerBuyUseThirdPayLog=new SellerBuyUseThirdPayLog();
//               sellerBuyUseThirdPayLog.setDayOfyear(localDateTime.getDayOfYear());
//               sellerBuyUseThirdPayLog.setYears(localDateTime.getYear());
//               sellerBuyUseThirdPayLog.setMonths(localDateTime.getMonthValue());
//               if(item.getThirdPlatformAction()==1){
//                   _newBalance.setMoney(_oldBalance.getMoney()+item.getTotal());
//                   _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
//                   _newBalance.setModifyTime(new Timestamp(new Date().getTime()));
//               }else if(item.getThirdPlatformAction()==2){
//
//                   _newBalance.setMoney(_oldBalance.getMoney()+item.getTotal());
//                   _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
//                   _newBalance.setModifyTime(new Timestamp(new Date().getTime()));
//
//
//                   Long platformMoney=item.getPlatformMoney()*item.getNumber();
//                   Long multiMoney=new BigDecimal(platformMoney).multiply(new BigDecimal(1.3)).longValue();
//                   _newCompanyBalance.setPlatformMoney(_oldCompanyBalance.getPlatformMoney()+multiMoney-platformMoney);
//
//                   companyMoneyStreamLog.setIncomePlatformMoney(multiMoney-platformMoney);
//                   companyMoneyStreamLog.setIncomePlatformMoney(multiMoney-platformMoney);
////
//               }else if(item.getThirdPlatformAction()==3){
//                   _newBalance.setMoney(_oldBalance.getMoney() + item.getPayMoney());
////
//                   _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
//                   _newBalance.setModifyTime(new Timestamp(new Date().getTime()));
//
//                   _newCompanyBalance.setMoney(_oldCompanyBalance.getMoney()+item.getPlatformIncome());
//                   _newCompanyBalance.setZhifubaoMoney(_oldCompanyBalance.getZhifubaoMoney()+item.getPlatformIncome());
//                   companyMoneyStreamLog.setMoney(item.getPlatformIncome());
//                   companyMoneyStreamLog.setStatus(1);
//                   sellerBuyUseThirdPayLog.setIncome(item.getPlatformIncome());
//                   sellerBuyUseThirdPayLog.setZhifubaoIncome(_newOrders.getPlatformIncome());
//                   sellerBuyUseThirdPayLogService.findRecord(sellerBuyUseThirdPayLog);
//               }else if(item.getThirdPlatformAction()==4){
//                   _newBalance.setMoney(_oldBalance.getMoney() + item.getPayMoney());
//
//                   _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
//                   _newBalance.setModifyTime(new Timestamp(new Date().getTime()));
//
//                   _newCompanyBalance.setMoney(_oldCompanyBalance.getMoney()+item.getPlatformIncome());
//                   _newCompanyBalance.setWeixinMoney(_oldCompanyBalance.getWeixinMoney()+item.getPlatformIncome());
//                   companyMoneyStreamLog.setMoney(item.getPlatformIncome());
//                   companyMoneyStreamLog.setStatus(2);
//                   sellerBuyUseThirdPayLog.setIncome(item.getPlatformIncome());
//                   sellerBuyUseThirdPayLog.setWeixinIncome(_newOrders.getPlatformIncome());
//                   sellerBuyUseThirdPayLogService.findRecord(sellerBuyUseThirdPayLog);
//               }
//               companyMoneyStreamLog.setJsonOfPreviousCompanyBalance(gson.toJson(_oldCompanyBalance));
//               companyMoneyStreamLog.setJsonOfCurrentCompanyBalance(gson.toJson(_newCompanyBalance));
//
//               _newOrders.setPhase(10);
//               _newOrders.setIsCustomerDeliveriedInPhaseThree(1);
//               _newOrders.setModifyTime(new Timestamp(new Date().getTime()));
//               ordersMapper.update(_newOrders);
//               userBalanceService.update(_newBalance);
//               companyBalanceService.update(_newCompanyBalance);
//               switch (_newOrders.getThirdPlatformAction()){
//                   case 1:
//                       userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance,"OrdersDeliveryService","action",
//                               UserStreamEnum.SCHEDULER_SELLER_MONEY_GET_ORDERS.getIndex(),_newOrders.getOrderCode());
//                       break;
//                   case 2:
//                       userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance,"OrdersDeliveryService","action",
//                               UserStreamEnum.SCHEDULER_SELLER_PLATFORM_FORM_GET_ORDERS.getIndex(),_newOrders.getOrderCode());
//                       break;
//                   case 3:
//                       userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance,"OrdersDeliveryService","action",
//                               UserStreamEnum.SCHEDULER_SELLER_ZHIFUBAO_GET_ORDERS.getIndex(),_newOrders.getOrderCode());
//                       break;
//                   case 4:
//                       userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance,"OrdersDeliveryService","action",
//                               UserStreamEnum.SCHEDULER_SELLER_WEIXIN_GET_ORDER.getIndex(),_newOrders.getOrderCode());
//                       break;
//               }
//               orderStreamLogService.saveOrders(item,_newOrders);
//               companyMoneyStreamLogService.save(companyMoneyStreamLog);
//           }
//       }
   }

   //总排名
   @Transactional
   @Scheduled(cron = "0 0 1 * * ?")
   public void settleRedPackLog(){
//       List<RedPackLog> redPackLogs = redPackLogService.findByShopIdGroup();
//
//       if(redPackLogs != null && redPackLogs.size()>0){
//           for(int i=1;i<redPackLogs.size();i++){
//               RedPackLog redPackLog = redPackLogs.get(i);
//
//               RedpackRankLog redpackRankLog = new RedpackRankLog();
//
//               SimpleDateFormat sd = new SimpleDateFormat("YYY-MM-dd");
//               String createTime = sd.format(new Date());
//               Long shopId = redPackLog.getShopId();
//               Long countMoney = redPackLogService.findByShopIdCountMoney(shopId);
//               redpackRankLog.setMoney(countMoney);
//               redpackRankLog.setShopId(shopId);
//               redpackRankLog.setUserId(redPackLog.getUserId());
//               redpackRankLog.setRanks(Long.valueOf(String.valueOf(i)));
//               redpackRankLog.setCreateTime(createTime);
//               redPackRankLogService.save(redpackRankLog);
//               redisTemplate.opsForZSet().add("redPackRanksLog::"+shopId.toString(),redpackRankLog.toString(),new Date().getTime());
//               redisTemplate.expire("redPackRanksLog::"+shopId.toString(),48, TimeUnit.HOURS);
//           }
//       }
//
//       System.out.println("执行了...1");
   }

    //区域排名
//    @Transactional
//    @Scheduled(cron = "0 0 1 * * ?")
    public void settleRedPacAreaRankLog(){
        //根据店铺分组查询
//        List<RedPackLog> redPackLogs = redPackLogService.findByShopIdGroup();
////
//        if(redPackLogs != null && redPackLogs.size()>0){
//            for(int i=1;i<redPackLogs.size();i++){
//                RedPackLog redPackLog = redPackLogs.get(i);
//
//                RedpackAreaRankLog redpackAreaRankLog = new RedpackAreaRankLog();
//
//                SimpleDateFormat sd = new SimpleDateFormat("YYY-MM-dd");
//                String createTime = sd.format(new Date());
//                Long shopId = redPackLog.getShopId();
//                //根据店铺统计每个店铺已经被领取了多少红包
//                Long countMoney = redPackLogService.findByShopIdCountMoney(shopId);
//                //根据店铺id查询
//                Shops shops = shopsService.findById(shopId);
//
//                redpackAreaRankLog.setShopId(shopId);
//                redpackAreaRankLog.setCreateTime(createTime);
//                redpackAreaRankLog.setMoney(countMoney);
//                redpackAreaRankLog.setUserId(redPackLog.getUserId());
//                redpackAreaRankLog.setCity(shops.getCity());
//                redpackAreaRankLog.setProvince(shops.getProvince());
//
//                redpackAreaRankLogService.save(redpackAreaRankLog);
//                redisTemplate.opsForZSet().add("redPackAreaRanksLog::"+shopId,redpackAreaRankLog.toString(),new Date().getTime());
//                redisTemplate.expire("redPackAreaRanksLog::"+shopId,48, TimeUnit.HOURS);
//            }
//        }
//
//        System.out.println("执行了...1");
    }

//    @Transactional
//    @Scheduled(cron = "*/5 * * * * ?")0 */1 * * * ?
    public void settleRedPackLog2(){

        Set range = redisTemplate.opsForZSet().range("1", 0, -1);
        System.out.println(range.isEmpty());
        Iterator iterator = range.iterator();
        Integer index = 1;
        if(index == 1){
            index = 2;
            while (iterator.hasNext()){
                Object next = iterator.next();System.out.println("执行了...");
                System.out.println(next.toString());
            }
        }

        System.out.println("执行了...5");
    }

    @Bean
    public RedisTemplate<String, Object> stringSerializerRedisTemplate() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }
}
