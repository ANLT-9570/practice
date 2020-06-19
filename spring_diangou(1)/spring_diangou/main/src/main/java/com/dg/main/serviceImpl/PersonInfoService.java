package com.dg.main.serviceImpl;

import com.dg.main.Entity.orders.LogisticsLog;
import com.dg.main.Entity.orders.LogisticsState;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.LogisticsStateRepository;
import com.dg.main.repository.log.LogisticsLogRepository;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.orders.OrdersRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.trans_mapper_obj.LogisticsObj;
import com.dg.main.util.LogisticsUtils;
import com.dg.main.util.Result;
import com.dg.main.vo.LogisticsLogVo;
import com.dg.main.vo.LogisticsObjVo;
import com.dg.main.vo.OrdersStatus;
import com.google.gson.Gson;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PersonInfoService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    LogisticsStateRepository logisticsStateRepository;
    @Autowired
    LogisticsLogRepository logisticsLogRepositorys;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private MobileUserRepository mobileUserRepository;

    public Result newLogistics(Integer userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(Long.valueOf(userId));
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        List<OrderItems> orderItems =orderItemsRepository.findByUserIdAndPhase(userId);
        List<LogisticsLogVo> logisticsLogsVo = Lists.newArrayList();

        orderItems.stream().forEach(_orderItems -> {
            String itemCode = _orderItems.getItemCode();
            Gson gson=new Gson();
            List<LogisticsLog> objs =(List<LogisticsLog>) redisTemplate.opsForValue().get(itemCode);

            if(objs == null){
                try {
                    String logisticsType = _orderItems.getLogisticsType();
                    String logisticsCode = _orderItems.getLogisticsCode();
                    LogisticsLogVo logisticsLogVo = check(logisticsType, logisticsCode, itemCode);
                    if(logisticsLogVo != null){
                        logisticsLogsVo.add(logisticsLogVo);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                LogisticsLogVo logisticsLogVo = new LogisticsLogVo();
                LogisticsLog logisticsLog = objs.get(objs.size() - 1);

                BeanUtils.copyProperties(logisticsLog,logisticsLogVo);
                logisticsLogVo.setOrderCode(itemCode);
                logisticsLogsVo.add(logisticsLogVo);
            }

        });

        return Result.successResult(logisticsLogsVo);
    }
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    private  void save(LogisticsObj logisticsObj, String itemCode) {
        if (StringUtils.isNotBlank(itemCode) && logisticsObj != null){
            LogisticsState logisticsState = new LogisticsState();
            logisticsState.setEBusinessID(logisticsObj.getEBusinessID());
            logisticsState.setLogisticCode(logisticsObj.getLogisticCode());
            logisticsState.setShipperCode(logisticsObj.getShipperCode());
            logisticsState.setOrderCode(itemCode);
            logisticsState.setState(logisticsObj.getState());
            LogisticsState save = logisticsStateRepository.save(logisticsState);
            if(save != null){
                List<LogisticsLog> traces = logisticsObj.getTraces();
                LogisticsLog logisticsLog = traces.get(traces.size() - 1);
                logisticsLog.setLogisticsStateId(save.getId());
                logisticsLogRepositorys.save(traces.get(traces.size()-1));
            }
        }
    }

    private LogisticsLogVo getLogisticsObj(LogisticsState logisticsState) {
        LogisticsLogVo logisticsLogsVo=null;
        if(logisticsState != null){

            LogisticsLog logisticsLogs = logisticsLogRepositorys.findByLogisticsStateId(logisticsState.getId());
            if(logisticsLogs!= null){
                logisticsLogsVo = new LogisticsLogVo();
                BeanUtils.copyProperties(logisticsLogs,logisticsLogsVo);
            }

        }
        return logisticsLogsVo;
    }

    public Result allKindsOf(Long userId) {
//        1.代付款 2.已付款 3.代发货 10.交易完成  //11。7天退货完成
        //代付款的所有订单
        Long notPayTotal = orderItemsRepository.findByUserIdAndPhaseAndCount(userId,1L);
        //代发货
        Long notShipTotal = orderItemsRepository.findByUserIdAndPhaseAndCount(userId,2L);
        //待收货
        Long notReceiptTotal = orderItemsRepository.findByUserIdAndPhaseAndCount(userId,10L);
        //待评价
        Long notEvaluateTotal = orderItemsRepository.findByUserIdAndPhaseAndRanksAndCount(userId);
        OrdersStatus ordersStatus = new OrdersStatus();

        ordersStatus.setNotEvaluateTotal(notEvaluateTotal);
        ordersStatus.setNotPayTotal(notPayTotal);
        ordersStatus.setNotReceiptTotal(notReceiptTotal);
        ordersStatus.setNotShipTotal(notShipTotal);

        return Result.successResult(ordersStatus);
    }

    public Result findByOrder(String order) {
        if(order == null){
            return Result.failureResult("43990","订单号为空");
        }
        List<LogisticsLogVo> logisticsLogsVo = Lists.newArrayList();
        List<LogisticsLog> logisticsLogs =(List<LogisticsLog>) redisTemplate.opsForValue().get(order);

        if(logisticsLogs == null){
            OrderItems items = orderItemsRepository.findByItemCode(order);
            if(items == null){
                return Result.failureResult("43990","订单号不存在");
            }
            try {
                logisticsLogsVo = checks(items,order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            OrderItems items = orderItemsRepository.findByItemCode(order);
            for (int i = 0; i < logisticsLogs.size(); i++) {
                LogisticsLogVo logisticsLogVo = new LogisticsLogVo();
                LogisticsLog logisticsLog = logisticsLogs.get(i);
                logisticsLogVo.setLogisticCode(items.getLogisticsCode());
                logisticsLogVo.setLogisticsType(items.getLogisticsType());
                logisticsLogVo.setSpecificationImg(items.getImg());
                logisticsLogVo.setSpecificationId(items.getSpecificationId());

                BeanUtils.copyProperties(logisticsLog,logisticsLogVo);
                logisticsLogsVo.add(logisticsLogVo);
            }
        }
        return Result.successResult(logisticsLogsVo);
    }

    public LogisticsLogVo check(String logisticsType,String logisticsCode,String order){
        LogisticsLogVo logisticsLogVo = null;
            Gson gson=new Gson();
            try {
                LogisticsUtils api = new LogisticsUtils();
                String result = api.getOrderTracesByJson(logisticsCode, logisticsType);
                LogisticsObj _logisticsObj = gson.fromJson(result, LogisticsObj.class);
                String state = _logisticsObj.getState();
                if("3".equals(state)||"2".equals(state)){
                    logisticsLogVo = new LogisticsLogVo();
                    List<LogisticsLog> traces = _logisticsObj.getTraces();
                    int size = traces.size();
                    LogisticsLog logisticsLog = traces.get(size - 1);

                    redisTemplate.opsForValue().set(order,traces,6, TimeUnit.HOURS);

                    BeanUtils.copyProperties(logisticsLog,logisticsLogVo);
                    logisticsLogVo.setOrderCode(order);

                    this.save(_logisticsObj,order);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return logisticsLogVo;
    }

    public List<LogisticsLogVo> checks(OrderItems items,String order){
        String logisticsType = items.getLogisticsType();
        String logisticsCode = items.getLogisticsCode();
        List<LogisticsLogVo> logisticsLogVo = Lists.newArrayList();
        Gson gson=new Gson();
        try {
            LogisticsUtils api = new LogisticsUtils();
            String result = api.getOrderTracesByJson(logisticsCode, logisticsType);
            LogisticsObj _logisticsObj = gson.fromJson(result, LogisticsObj.class);
            String state = _logisticsObj.getState();
            if("3".equals(state)||"2".equals(state)){
                List<LogisticsLog> traces = _logisticsObj.getTraces();
                for (LogisticsLog trace : traces) {
                    LogisticsLogVo newLogisticsLogVo = new LogisticsLogVo();
                    newLogisticsLogVo.setLogisticCode(logisticsCode);
                    newLogisticsLogVo.setLogisticsType(logisticsType);
                    newLogisticsLogVo.setSpecificationId(items.getSpecificationId());
                    newLogisticsLogVo.setSpecificationImg(items.getImg());

                    BeanUtils.copyProperties(trace,newLogisticsLogVo);
                    logisticsLogVo.add(newLogisticsLogVo);
                }
                redisTemplate.opsForValue().set(order,traces,6, TimeUnit.HOURS);
                this.save(_logisticsObj,order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logisticsLogVo;
    }


}