package com.dg.main.scheduler;

import com.dg.main.Entity.logs.RedPackLog;
import com.dg.main.Entity.logs.RedpackAreaRankLog;
import com.dg.main.Entity.logs.RedpackRankLog;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.serviceImpl.logs.*;
import com.dg.main.serviceImpl.orders.service.RedPackService;
import com.dg.main.serviceImpl.shop.ShopsService;
import com.mysql.cj.log.LogFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedPackSchedule {

    @Autowired
    RedPackService redPackService;
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
    private static final String LOCK_KEY_V1 = "RED_PACK_LOCK_V1";

    //总排名
    @Transactional
    @Scheduled(cron = "0 0 1 * * ?")
    public void settleRedPackLog(){
        //获取锁
        String lock = RedisLock.getLock(LOCK_KEY_V1, 5000, 90000);
        if(StringUtils.isEmpty(lock)){
            System.out.println(Thread.currentThread().getName()+"获取锁超时....");
            return;
        }
        List<RedPackLog> redPackLogs = redPackLogService.findByShopIdGroup();

        if(redPackLogs != null && redPackLogs.size()>0){
            for(int i=1;i<redPackLogs.size();i++){
                RedPackLog redPackLog = redPackLogs.get(i);

                RedpackRankLog redpackRankLog = new RedpackRankLog();

                SimpleDateFormat sd = new SimpleDateFormat("YYY-MM-dd");
                String createTime = sd.format(new Date());
                Long shopId = redPackLog.getShopId();
                Long countMoney = redPackLogService.findByShopIdCountMoney(shopId);
                redpackRankLog.setMoney(countMoney);
                redpackRankLog.setShopId(shopId);
                redpackRankLog.setUserId(redPackLog.getUserId());
                redpackRankLog.setRanks(Long.valueOf(String.valueOf(i)));
                redpackRankLog.setCreateTime(createTime);
                redPackRankLogService.save(redpackRankLog);
                redisTemplate.opsForZSet().add("redPackRanksLog::"+shopId.toString(),redpackRankLog.toString(),new Date().getTime());
                redisTemplate.expire("redPackRanksLog::"+shopId.toString(),48, TimeUnit.HOURS);
            }
        }
    }

    //区域排名
    @Transactional
    @Scheduled(cron = "0 0 1 * * ?")
    public void settleRedPacAreaRankLog(){
        //获取锁
        String lock = RedisLock.getLock(LOCK_KEY_V1, 5000, 90000);
        if(StringUtils.isEmpty(lock)){
            System.out.println(Thread.currentThread().getName()+"获取锁超时....");
            return;
        }
        //根据店铺分组查询
        List<RedPackLog> redPackLogs = redPackLogService.findByShopIdGroup();
//
        if(redPackLogs != null && redPackLogs.size()>0){
            for(int i=1;i<redPackLogs.size();i++){
                RedPackLog redPackLog = redPackLogs.get(i);

                RedpackAreaRankLog redpackAreaRankLog = new RedpackAreaRankLog();

                SimpleDateFormat sd = new SimpleDateFormat("YYY-MM-dd");
                String createTime = sd.format(new Date());
                Long shopId = redPackLog.getShopId();
                //根据店铺统计每个店铺已经被领取了多少红包
                Long countMoney = redPackLogService.findByShopIdCountMoney(shopId);
                //根据店铺id查询
                Shops shops = shopsService.findById(shopId);

                redpackAreaRankLog.setShopId(shopId);
                redpackAreaRankLog.setCreateTime(createTime);
                redpackAreaRankLog.setMoney(countMoney);
                redpackAreaRankLog.setUserId(redPackLog.getUserId());
                redpackAreaRankLog.setCity(shops.getCity());
                redpackAreaRankLog.setProvince(shops.getProvince());

                redpackAreaRankLogService.save(redpackAreaRankLog);
                redisTemplate.opsForZSet().add("redPackAreaRanksLog::"+shopId,redpackAreaRankLog.toString(),new Date().getTime());
                redisTemplate.expire("redPackAreaRanksLog::"+shopId,48, TimeUnit.HOURS);
            }
        }
    }

}
