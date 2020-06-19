package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.RedpackAreaRankLog;
import com.dg.main.repository.log.RedpackAreaRankLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedpackAreaRankLogService {
    @Autowired
    RedpackAreaRankLogRepository redpackAreaRankLogRepository;

    public RedpackAreaRankLog save(RedpackAreaRankLog redpackAreaRankLog) {
        RedpackAreaRankLog redpackAreaRankLog1 = redpackAreaRankLogRepository.save(redpackAreaRankLog);
        return redpackAreaRankLog1;
    }
}
