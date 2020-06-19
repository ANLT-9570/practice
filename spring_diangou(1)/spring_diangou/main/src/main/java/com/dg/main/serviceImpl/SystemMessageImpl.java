package com.dg.main.serviceImpl;

import com.dg.main.Entity.users.Coupon;
import com.dg.main.Entity.message.SystemMessage;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.orders.SystemMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemMessageImpl {
    @Autowired
    SystemMessageMapper systemMessageMapper;
    public void deleteBy(Long id){
        systemMessageMapper.deleteBy(id);
    }


    public int update(SystemMessage t) {

        return systemMessageMapper.update(t);
    }


    public int save(SystemMessage t) {

        return systemMessageMapper.save(t);
    }



    public List<Coupon> selectAll(SystemMessage t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页

    public slzcResult selectAll(int offset, int limit) {

        slzcResult result = new slzcResult();

        int count = systemMessageMapper.selectCount();

//		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
        List<SystemMessage> businesses = systemMessageMapper.selectPageAll( offset,limit);

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


    public List<SystemMessage> selectPageAll(int offset, int limit) {

        return null;
    }


    public int deleteAllId(String [] t) {

        return systemMessageMapper.deleteAllId(t);
    }



    public int selectCount() {
        // TODO Auto-generated method stub
        return systemMessageMapper.selectCount();
    }
}
