package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.OrderStreamLog;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.repository.log.OrderStreamLogRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderStreamLogService {
    @Autowired
    OrderStreamLogRepository repository;
//    public OrderStreamLog findBy(Long id) {
//
//        return repository.findBy(id);
//    }
    @Transactional
    public void saveOrders(Orders old_, Orders new_){
        Gson gson=new Gson();
     OrderStreamLog orderStreamLog=new OrderStreamLog();
     orderStreamLog.setOrderCode(old_.getOrderCode());
     orderStreamLog.setCreateTime(new Date());
    // orderStreamLog.setCurrentStatus(new_.getPhase());
     orderStreamLog.setCustomerId(old_.getCustomerId());
     orderStreamLog.setBusinessId(old_.getBusinessId());
     orderStreamLog.setJsonStateOfOrders(gson.toJson(old_));
     orderStreamLog.setJsonStateOfModifiedOrders(gson.toJson(new_));
        repository.save(orderStreamLog);
    }
//
//
//    public void deleteBy(Long id){
//        orderStreamLogMappter.deleteBy(id);
//    }
//
//
//    public int update(OrderStreamLog t) {
//
//        return orderStreamLogMappter.update(t);
//    }
//
//
//    public int save(OrderStreamLog t) {
//
//        return orderStreamLogMappter.save(t);
//    }
//
//
//
//    public List<OrderStreamLog> selectAll(OrderStreamLog t) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    //分页
//
//    public slzcResult selectAll(int offset, int limit) {
//
//        slzcResult result = new slzcResult();
//
//        int count = orderStreamLogMappter.selectCount();
//
////		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
//        List<OrderStreamLog> businesses = orderStreamLogMappter.selectPageAll( offset,limit);
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
//
//
//    public List<OrderStreamLog> selectPageAll(int offset, int limit) {
//
//        return null;
//    }
//
//
//    public int deleteAllId(String [] t) {
//
//        return orderStreamLogMappter.deleteAllId(t);
//    }
//
//
//
//    public int selectCount() {
//        // TODO Auto-generated method stub
//        return 0;
//    }
}
