package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.RedPackLog;
import com.dg.main.repository.log.RedPackLogRepository;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.orders.RedPackLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedPackLogService {
    @Autowired
    RedPackLogMapper redPackLogMapper;
    @Autowired
    RedPackLogRepository redPackLogRepository;
    public RedPackLog findBy(Long id) {

        return redPackLogMapper.findBy(id);
    }



    public void deleteBy(Long id){
        redPackLogMapper.deleteBy(id);
    }


    public int update(RedPackLog t) {

        return redPackLogMapper.update(t);
    }

    public List<RedPackLog> untakeList(String code){
        return redPackLogMapper.UntakedList(code);
    }
    public int save(RedPackLog t) {

        return redPackLogMapper.save(t);
    }



    public List<RedPackLog> selectAll(RedPackLog t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页

    public slzcResult selectAll(int offset, int limit) {

        slzcResult result = new slzcResult();

        int count = redPackLogMapper.selectCount();

//		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
        List<RedPackLog> businesses = redPackLogMapper.selectPageAll( offset,limit);

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


    public List<RedPackLog> selectPageAll(int offset, int limit) {

        return null;
    }


    public int deleteAllId(String [] t) {

        return redPackLogMapper.deleteAllId(t);
    }



    public int selectCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<RedPackLog> findByShopIdGroup() {
        return redPackLogRepository.findByShopIdAllGroup();
    }

    public Long findByShopIdCountMoney(Long shopId) {
        Long countMoney = redPackLogRepository.findByShopIdCountMoney(shopId);
        return countMoney;
    }
}
