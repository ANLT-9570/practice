package com.dg.main.serviceImpl;

import com.dg.main.Entity.HomePageState;
import com.dg.main.Entity.Record;
import com.dg.main.Entity.logs.RedPackLog;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.Entity.users.UserRealName;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.RecordRepostory;
import com.dg.main.repository.RedPackRepository;
import com.dg.main.repository.log.RedPackLogRepository;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.orders.OrdersRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.repository.users.UserRealNameRepository;
import com.dg.main.util.Result;
import com.dg.main.vo.OrdersStatus;
import com.dg.main.vo.WaitForReceivingVo;
import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShopsHomePageService {

    @Autowired
    private UserRealNameRepository userRealNameRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Autowired
    ShopsRepository shopsRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    RedPackRepository redPackRepository;

    @Autowired
    RedPackLogRepository redPackLogRepository;

    @Autowired
    RecordRepostory recordRepostory;
    @Autowired
    private MobileUserRepository mobileUserRepository;


    public Result getUser(Long userId) {
        if (userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        UserRealName userRealName = userRealNameRepository.findByUserId(userId);
        if (userRealName == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        return Result.successResult(userRealName);
    }


    public Result allKindsOf(Long userId) {
        if (userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        //1.代付款 2.已付款 3.代发货 10.交易完成  //11。7天退货完成
        //代付款的所有订单
        Long notPayTotal = orderItemsRepository.findByUserIdAndPhaseAndCount(userId,1L);
        //待发货
        Long notShipTotal = orderItemsRepository.findByUserIdAndPhaseAndCount(userId,3L);
        //待收货
        Long notReceiptTotal = orderItemsRepository.findByUserIdAndPhaseAndCount(userId,10L);
        //待评价
        //Long notEvaluateTotal = orderItemsRepository.findByUserIdAndPhaseAndRanksAndCount(userId);
        //今天待发货的订单
        Long todayUnSend = orderItemsRepository.findByUserIdAndPhaseAndCountAndCreateTime(userId,3L);
        //今天收入
        Long todayRevenue = orderItemsRepository.findByUserIdAndPhaseAndCountAndCreateTime(userId,10L);
        //今天待退款
        Long todayRefund = orderItemsRepository.findByUserIdRefundTime(userId);

        OrdersStatus ordersStatus = new OrdersStatus();

        //ordersStatus.setNotEvaluateTotal(notEvaluateTotal);
        ordersStatus.setNotPayTotal(notPayTotal);
        ordersStatus.setNotReceiptTotal(notReceiptTotal);
        ordersStatus.setNotShipTotal(notShipTotal);
        ordersStatus.setTodayRevenue(todayRevenue==null?0:todayRevenue);
        ordersStatus.setTodayRefund(todayRefund);
        ordersStatus.setTodayNotShipTotal(todayUnSend==null?0:todayUnSend);

        return Result.successResult(ordersStatus);
    }

    public Result allKindsOfShopId(Long shopId) {
        if (shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        //1.代付款 2.已付款 3.代发货 10.交易完成  //11。7天退货完成
        //代付款的所有订单
        Long notPayTotal = orderItemsRepository.findByShopIdAndPhaseAndCount(shopId,1L);
        //待发货
        Long notShipTotal = orderItemsRepository.findByShopIdAndPhaseAndCount(shopId,3L);
        //待收货
        Long notReceiptTotal = orderItemsRepository.findByShopIdAndPhaseAndCount(shopId,10L);
        //待评价
        //Long notEvaluateTotal = orderItemsRepository.findByUserIdAndPhaseAndRanksAndCount(userId);
        //今天待发货的订单
        Long todayUnSend = orderItemsRepository.findByShopIdAndPhaseAndCountAndCreateTime(shopId,3L);
        //今天收入
        Long todayRevenue = orderItemsRepository.findByShopIdAndPhaseAndSumAndCreateTime(shopId,10L);
        //今天待退款
        Long todayRefund = orderItemsRepository.findByShopIdRefundTime(shopId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String now = simpleDateFormat.format(new Date());
        //今天的浏览量
        List<Record> records = recordRepostory.findByShopIdNow(shopId,"%"+now+"%");
        OrdersStatus ordersStatus = new OrdersStatus();

        //ordersStatus.setNotEvaluateTotal(notEvaluateTotal);
        ordersStatus.setNotPayTotal(notPayTotal);
        ordersStatus.setNotReceiptTotal(notReceiptTotal);
        ordersStatus.setNotShipTotal(notShipTotal);
        ordersStatus.setTodayRevenue(todayRevenue==null?0:todayRevenue);
        ordersStatus.setTodayRefund(todayRefund);
        ordersStatus.setTodayNotShipTotal(todayUnSend==null?0:todayUnSend);
        ordersStatus.setTodayRecords(Long.valueOf(records.size()));

        return Result.successResult(ordersStatus);
    }


    public Result getShopsData(Long userId) {
        if (userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        //今天待发货的订单
        Long todayUnSend = orderItemsRepository.findByUserIdAndPhaseAndCountAndCreateTime(userId,3L);
        //今天收入
        Long todayRevenue = orderItemsRepository.findByUserIdAndPhaseAndSumAndCreateTime(userId,10L);
        //今天待退款
        Long todayRefund = orderItemsRepository.findByUserIdRefundTime(userId);

        HomePageState homePageState = new HomePageState();
        homePageState.setTodayNotShipTotal(todayUnSend==null?0:todayUnSend);
        homePageState.setTodayRevenue(todayRevenue==null?0:todayRevenue);
        homePageState.setTodayRefund(todayRefund);

        return Result.successResult(homePageState);
    }


    public Result getShopsDataTime(Long userId, String dateTime) {
        if (userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        //当天待发货的订单
        Long unSend = orderItemsRepository.findByUserIdAndPhaseAndCreateTimeAndCount(userId,"%"+dateTime+"%",3l);
        //当天的收入
        Long revenue = orderItemsRepository.findByUserIdAndPhaseAndCreateTimeAndSum(userId,"%"+dateTime+"%",10L);
        //当天待退款
        Long refund = orderItemsRepository.findByUserIdRefundTimeSomeDay(userId,"%"+dateTime+"%");

        HomePageState homePageState = new HomePageState();
        homePageState.setTodayNotShipTotal(unSend==null?0:unSend);
        homePageState.setTodayRevenue(revenue==null?0:revenue);
        homePageState.setTodayRefund(refund);

        return Result.successResult(homePageState);
    }

    public Result getShopsDataTimeShops(Long shopId, String dateTime) {
        if (shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        //当天待发货的订单
        Long unSend = orderItemsRepository.findByShopIdAndPhaseAndCreateTimeAndCount(shopId,"%"+dateTime+"%",3l);
        //当天的收入
        Long revenue = orderItemsRepository.findByShopIdAndPhaseAndCreateTimeAndSum(shopId,"%"+dateTime+"%",10L);
        //当天待退款
        Long refund = orderItemsRepository.findByShopIdRefundTimeSomeDay(shopId,"%"+dateTime+"%");

        //当天点币共发多少个,共发送多少钱
        List<RedPack> redPacks = redPackRepository.findByShopsIdAndCreateTimeCount(shopId,"%"+dateTime+"%");

        //当天已领取的点币共发多少个,已领取的共发送多少钱
        List<RedPackLog> redPackLogs = redPackLogRepository.findByShopIdAndCreateTime(shopId,"%"+dateTime+"%");

        //当天的浏览记录
        List<Record> records = recordRepostory.findByShopIdNow(shopId, "%" + dateTime + "%");

        Map<String, Long> map = disposeData_v2(redPacks, redPackLogs);

        HomePageState homePageState = new HomePageState();
        homePageState.setTodayNotShipTotal(unSend==null?0:unSend);
        homePageState.setTodayRevenue(revenue==null?0:revenue);
        homePageState.setTodayRefund(refund);

        homePageState.setTotalTakeMoney(map.get("totalTakeMoney"));
        homePageState.setTotalSendMoney(map.get("totalSendMoney"));
        homePageState.setCountTakeNumber(map.get("countTakeNumber"));
        homePageState.setCountSendNumber(map.get("countSendNumber"));
        homePageState.setTotalRecords(Long.valueOf(records.size()));


        return Result.successResult(homePageState);
    }

    public Result getShopsDBDataTime(Long shopId, String dateTime) {
        if (shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        if(StringUtils.isEmpty(dateTime)){
            return Result.failureResult(ExceptionCode.Failure.NOT_DATETIME);
        }
        Map<String,Long> maps = Maps.newHashMap();
        //当天点币共发多少个,共发送多少钱
        List<RedPack> redPacks = redPackRepository.findByShopsIdAndCreateTimeCount(shopId,"%"+dateTime+"%");

        //当天已领取的点币共发多少个,已领取的共发送多少钱
        List<RedPackLog> redPackLogs = redPackLogRepository.findByShopIdAndCreateTime(shopId,"%"+dateTime+"%");

        return disposeData(redPacks, redPackLogs);
    }

    public Result getUserDBDataTime(Long userId, String dateTime) {
        if (userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        if(StringUtils.isEmpty(dateTime)){
            return Result.failureResult(ExceptionCode.Failure.NOT_DATETIME);
        }
        Map<String,Long> maps = Maps.newHashMap();
        //当天点币共发多少个,共发送多少钱
        List<RedPack> redPacks = redPackRepository.findByUserIdAndCreateTimeCount(userId,"%"+dateTime+"%");

        //当天已领取的点币共发多少个,已领取的共发送多少钱
        List<RedPackLog> redPackLogs = redPackLogRepository.findByUserIdAndCreateTime(userId,"%"+dateTime+"%");
        return disposeData(redPacks, redPackLogs);
    }

    public Result disposeData(List<RedPack> redPacks,List<RedPackLog> redPackLogs){
        Map<String,Long> maps = Maps.newHashMap();

        Long countSendNumber = 0L;
        Long totalSendMoney = 0L;
        Long countTakeNumber = Long.valueOf(redPackLogs.size());
        Long totalTakeMoney = 0L;

        for(RedPack redPack:redPacks){
            countSendNumber+=redPack.getTakeNumber();
            totalSendMoney+=redPack.getPlatformMoney();
        }

        for(RedPackLog redPackLog:redPackLogs){
            Long takeMoney = redPackLog.getTakeMoney();
            totalTakeMoney+=takeMoney;
        }
        maps.put("countSendNumber",countSendNumber);
        maps.put("totalSendMoney",totalSendMoney);
        maps.put("countTakeNumber",countTakeNumber);
        maps.put("totalTakeMoney",totalTakeMoney);
        return Result.successResult(maps);
    }

    public Map<String,Long> disposeData_v2(List<RedPack> redPacks,List<RedPackLog> redPackLogs){
        Map<String,Long> maps = Maps.newHashMap();

        Long countSendNumber = 0L;
        Long totalSendMoney = 0L;
        Long countTakeNumber = Long.valueOf(redPackLogs.size());
        Long totalTakeMoney = 0L;

        for(RedPack redPack:redPacks){
            countSendNumber+=redPack.getTakeNumber();
            totalSendMoney+=redPack.getPlatformMoney();
        }

        for(RedPackLog redPackLog:redPackLogs){
            Long takeMoney = redPackLog.getTakeMoney();
            totalTakeMoney+=takeMoney;
        }
        maps.put("countSendNumber",countSendNumber);
        maps.put("totalSendMoney",totalSendMoney);
        maps.put("countTakeNumber",countTakeNumber);
        maps.put("totalTakeMoney",totalTakeMoney);
        return maps;
    }
}
