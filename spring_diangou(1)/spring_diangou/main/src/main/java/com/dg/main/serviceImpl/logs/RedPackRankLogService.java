package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.RedPackLog;
import com.dg.main.Entity.logs.RedpackAreaRankLog;
import com.dg.main.Entity.logs.RedpackRankLog;
import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.RedPackRepository;
import com.dg.main.repository.log.RedPackLogRepository;
import com.dg.main.repository.log.RedpackAreaRankLogRepository;
import com.dg.main.repository.log.RedpackRankLogRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.util.Result;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RedPackRankLogService {

    @Autowired
    RedpackRankLogRepository redpackRankLogRepository;

    @Autowired
    RedPackLogRepository redPackLogRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedpackAreaRankLogRepository redpackAreaRankLogRepository;

    @Autowired
    private ShopsRepository shopsRepository;
    @Autowired
    private RedPackRepository redPackRepository;

    public  Result getShopsRank(Long shopId) {
        Result result = verify(shopId);
        if(!result.getCode().equals("10000")){
            return result;
        }
        List<RedpackRankLog> redPackRankLogs = redpackRankLogRepository.findByShopIdAndCreateTimeGroup(shopId);
        RedpackRankLog redpackRankLog = null;
        for(int i=0;i<redPackRankLogs.size();i++){
            redpackRankLog = redPackRankLogs.get(i);
            int index = 1;
            redpackRankLog.setRanks(Long.valueOf(index+i));
            if(shopId.equals(redpackRankLog.getShopId())){
                return Result.successResult(redpackRankLog);
            }
        }
        return Result.successResult(redpackRankLog);
    }

    public RedpackRankLog save(RedpackRankLog redpackRankLog) {
        return redpackRankLogRepository.save(redpackRankLog);
    }

    public Result getShopsAreaRank(Long shopId) {
        Result result = verify(shopId);
        if(!result.getCode().equals("10000")){
            return result;
        }
//        String createTime = redpackAreaRankLogRepository.findByMaxCreateTime();
//        List<RedpackAreaRankLog> redpackAreaRankLogs = redpackAreaRankLogRepository.findByShopIdAndCreateTime(shopId,"%"+createTime+"%");
        List<RedpackAreaRankLog> redpackAreaRankLogs = redpackAreaRankLogRepository.findByShopIdAndCreateTimeGorup(shopId);
        if(redpackAreaRankLogs != null && redpackAreaRankLogs.size()>0){
            for(int i=0;i<redpackAreaRankLogs.size();i++){
                RedpackAreaRankLog redpackAreaRankLog = redpackAreaRankLogs.get(i);
                if(shopId.equals(redpackAreaRankLog.getShopId())){
                    int index = 1;
                    redpackAreaRankLog.setRanks(Long.valueOf(i+index));
                    return Result.successResult(redpackAreaRankLog);
                }
            }
        }
        return result;
    }

    public Result verify(Long shopId){
        Result result = verify(shopId);
        if(!result.getCode().equals("10000")){
            return result;
        }
//        List<RedPack> redPack = redPackRepository.findByShopId(shopId);
//        if(redPack == null || redPack.size() == 0){
//            return Result.failureResult(ExceptionCode.Failure.NOT_REDPACK);
//        }
        List<RedPackLog> redPackLog = redPackLogRepository.findByShopId(shopId);
        if(redPackLog == null || redPackLog.size() == 0){
            return Result.failureResult(ExceptionCode.Failure.NOT_REDPACKLOG);
        }
        return Result.successResult();
    }

    public Result getShopsRankList() {
        List<RedpackRankLog> redPackRankLogs = redpackRankLogRepository.findByShopIdAndCreateTimeGroup(null);
        List<RedpackRankLog> _redPackRankLogs = Lists.newArrayList();
        for(int i=0;i<redPackRankLogs.size();i++){
            int index =1;
            RedpackRankLog redpackRankLog = redPackRankLogs.get(i);
            redpackRankLog.setRanks(Long.valueOf(i+index));
            _redPackRankLogs.add(redpackRankLog);
        }
        return Result.successResult(_redPackRankLogs);
    }

    public Result getShopsAreaRankList(Long shopId) {
        Result result = verify(shopId);
        if(!result.getCode().equals("10000")){
            return result;
        }
        List<RedpackAreaRankLog> redpackAreaRankLogs = redpackAreaRankLogRepository.findByShopIdAndCreateTimeGorup(shopId);
        List<RedpackAreaRankLog> _redpackAreaRankLogs = Lists.newArrayList();
        if(redpackAreaRankLogs != null && redpackAreaRankLogs.size()>0){
            for(int i=0;i<redpackAreaRankLogs.size();i++){
                int index =1;
                RedpackAreaRankLog redpackAreaRankLog = redpackAreaRankLogs.get(i);
                redpackAreaRankLog.setRanks(Long.valueOf(i+index));
                _redpackAreaRankLogs.add(redpackAreaRankLog);
            }
        }
        return Result.successResult(_redpackAreaRankLogs);
    }
}
