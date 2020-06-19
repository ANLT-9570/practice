package com.dg.main.serviceImpl;

import com.dg.main.Entity.users.UserCoupons;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.orders.UserCouponsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCouponsServiceImpl {
    @Autowired
    UserCouponsMapper userCouponsMapper;


    public UserCoupons findBy(Long id) {

        return userCouponsMapper.findBy(id);
    }



    public void deleteBy(Long id){
        userCouponsMapper.deleteBy(id);
    }


    public int update(UserCoupons t) {

        return userCouponsMapper.update(t);
    }


    public int save(UserCoupons t) {

        return userCouponsMapper.save(t);
    }



    public List<UserCoupons> selectAll(UserCoupons t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页

    public slzcResult selectAll(int offset, int limit) {

        slzcResult result = new slzcResult();

        int count = userCouponsMapper.selectCount();

//		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
        List<UserCoupons> businesses = userCouponsMapper.selectPageAll( offset,limit);

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


    public List<UserCoupons> selectPageAll(int offset, int limit) {

        return null;
    }


    public int deleteAllId(String [] t) {

        return userCouponsMapper.deleteAllId(t);
    }



    public int selectCount() {
        // TODO Auto-generated method stub
        return userCouponsMapper.selectCount();
    }
}
