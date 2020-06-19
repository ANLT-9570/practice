package com.dg.main.serviceImpl.partner.earnings;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.dto.EarningsDTO;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.util.Result;
import com.dg.main.util.TimeUtils;
import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EarningsHomeService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private MobileUserRepository mobileUserRepository;

    public Result earningsHome(Long userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        EarningsDTO earningsDTO = new EarningsDTO();

        List<OrderItems> list = orderItemsRepository.findByUserIdAccumulative(userId);
        Long sum = dispose(list);
        earningsDTO.setAccumulativeEarnings(sum.toString());//累计收入

        //上个月的时间
        String upMonthTime = TimeUtils.getUpMonthTime();
        List<OrderItems> orderItems = orderItemsRepository.findByUserIdAccumulativeAndTime(userId,"%"+upMonthTime+"%");
        Long upSum = dispose(orderItems);
        earningsDTO.setUpAccumulativeEarnings(upSum.toString());//上个月的累计

        String monthTimeMM = TimeUtils.getMonthTimeMM();
        List<OrderItems> monthOrderItems = orderItemsRepository.findByUserIdAndPhaseAndIsDeletedAndCreateTime(userId,"%"+monthTimeMM+"%");
        Long monthSum = dispose(monthOrderItems);
        earningsDTO.setMonthSum(monthSum.toString());//本月交易额

        List<OrderItems> refundTime = orderItemsRepository.findByUserIdRefundAndTime(userId);
        Long refundedSum = dispose(refundTime);
        earningsDTO.setMonthRefunded(refundedSum.toString());//本月退货额
        earningsDTO.setMonthActualSum("0");//本月实际成交额

        return Result.successResult(earningsDTO);
    }

    public Long dispose(List<OrderItems> orderItems){
        Long sum = 0L;
        for(OrderItems orderItems1:orderItems){
            Long money = orderItems1.getMoney();
            sum+=money;
        }
        return sum;
    }

    public Result userDetail(Long userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        List<Map<String,String>> list = Lists.newArrayList();
        List<String> orderItems = orderItemsRepository.findByUserIdAndPhaseAndIsDeletedAndGroupByCreateTime(userId);

        for(String dateTime:orderItems){
            Map<String,String> map = Maps.newHashMap();
            dateTime = TimeUtils.getDisposeDD(dateTime);
            map.put("dateTime",dateTime);
            //当天之前的数据
            List<OrderItems> itemsListAgo = orderItemsRepository.findByUserIdAndPhaseAndIsDeletedAndCreateTimeAgo(userId,dateTime);
            Long accumulativeMoneyAgo = dispose(itemsListAgo);
            map.put("sumEarnings",accumulativeMoneyAgo.toString());
            //当天的数据
            List<OrderItems> itemsList = orderItemsRepository.findByUserIdAndPhaseAndIsDeletedAndCreateTime(userId, "%" + dateTime + "%");
            Long accumulativeMoneyToday = dispose(itemsListAgo);
            map.put("todayEarnings",accumulativeMoneyToday.toString());
        }
        return Result.successResult(list);
    }

}
