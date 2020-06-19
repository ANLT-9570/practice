package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.orders.LogisticsLog;
import com.dg.main.Entity.orders.LogisticsState;
import com.dg.main.Entity.orders.LogisticsState;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.orders.LogisticsLogMapper;
import com.dg.main.dao.orders.LogisticsStateMapper;
import com.dg.main.trans_mapper_obj.LogisticsObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogisticsStateService {
    @Autowired
    LogisticsStateMapper logisticsStateMapper;
    @Autowired
    LogisticsLogMapper logisticsLogMapper;
    public LogisticsState findBy(Long id) {

        return logisticsStateMapper.findBy(id);
    }
    public void save(LogisticsObj obj,String code){
        if(obj!=null&& !StringUtils.isEmpty(code)&&obj.getState().equals("3")){
            LogisticsState logisticsState=new LogisticsState();
            logisticsState.setEBusinessID(obj.getEBusinessID());
            logisticsState.setLogisticCode(obj.getLogisticCode());
            logisticsState.setShipperCode(obj.getShipperCode());
            logisticsState.setOrderCode(code);
            logisticsState.setState(obj.getState());
            int id=logisticsStateMapper.save(logisticsState);
            if (id!=0){
              //  List<LogisticsLog> logisticsLogs=new ArrayList<>();
                obj.getTraces().stream().forEach(i->{
                    i.setLogisticsStateId(new Long(id));
                    logisticsLogMapper.save(i);
                });
            }
        }
    }
    public void delete(List<LogisticsLog> logisticsLogs){
        for (LogisticsLog item : logisticsLogs) {
            logisticsLogMapper.delete(item);
        }
    }
    public LogisticsObj getLogisticsObj(LogisticsState item){
        LogisticsObj obj=null;
        if(isStateThree(item)){
            obj=new LogisticsObj();
            obj.setEBusinessID(item.getEBusinessID());
            obj.setLogisticCode(item.getLogisticCode());
            obj.setShipperCode(item.getShipperCode());
            obj.setState(item.getState());
            obj.setSuccess(item.getSuccess());
            List<LogisticsLog> logisticsLogs=logisticsLogMapper.findByLogisticsStateId(item.getId());
            obj.setTraces(logisticsLogs);
        }
        return obj;
    }
    public LogisticsState findByCode(String code){
        return logisticsStateMapper.findByOrderCode(code);
    }
    public Boolean isStateThree(LogisticsState item){
        return item.getState().equals("3");
    }
//    public LogisticsState getRate(){
//        return logisticsStateMapper.selectByTypes(1);
//    }

    public void deleteBy(Long id){
        logisticsStateMapper.deleteBy(id);
    }


    public int update(LogisticsState t) {

        return logisticsStateMapper.update(t);
    }


    public int save(LogisticsState t) {

        return logisticsStateMapper.save(t);
    }



    public List<LogisticsState> selectAll(LogisticsState t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页

    public slzcResult selectAll(int offset, int limit) {

        slzcResult result = new slzcResult();

        int count = logisticsStateMapper.selectCount();

//		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
        List<LogisticsState> businesses = logisticsStateMapper.selectPageAll( offset,limit);

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


    public List<LogisticsState> selectPageAll(int offset, int limit) {

        return null;
    }


    public int deleteAllId(String [] t) {

        return logisticsStateMapper.deleteAllId(t);
    }



    public int selectCount() {
        // TODO Auto-generated method stub
        return logisticsStateMapper.selectCount();
    }
}
