package com.dg.main.serviceImpl.partner.data;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.Entity.users.UserRelationShip;
import com.dg.main.dto.KindOfDTO;
import com.dg.main.dto.ShopsDataDTO;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.orders.OrdersRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.repository.users.UserRelationShipRepository;
import com.dg.main.serviceImpl.partner.home.PartnerHomeService;
import com.dg.main.util.Result;
import com.dg.main.util.TimeUtils;
import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DataService {

    @Autowired
    private MobileUserRepository mobileUserRepository;
    @Autowired
    private UserRelationShipRepository userRelationShipRepository;
    @Autowired
    private ShopsRepository shopsRepository;
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private PartnerHomeService partnerHomeService;
    @Autowired
    private OrdersRepository ordersRepository;

    public Result dataHome(Long userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        Map<String,String> map = Maps.newHashMap();

        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        List<UserRelationShip> shipList = userRelationShipRepository.findByUserId(userId);//所有伙伴
        map.put("userName",mobileUser.getName());//用户名
        map.put("myPartner",shipList.size()+"");//我的伙伴数量
        map.put("userId",userId+"");

        Shops shops = shopsRepository.findByUserIdAndIsDefault(userId);
        map.put("shopName",shops==null?"没开启店铺":shops.getName());//店铺名称
        map.put("createTime",shops==null?"没开启店铺":shops.getCreateTime().toString());
        map.put("shopId",shops==null?"没开启店铺":shops.getId().toString());

        String todayTimeDD = TimeUtils.getTodayTimeDD();
        String day = TimeUtils.getDay();
        String month = TimeUtils.getMonth();
        String year = TimeUtils.getYear();
//        List<OrderItems> itemsList = orderItemsRepository.findByShopIdAndPhaseAndIsDeleted(shops.getId(), "%" + dateTime + "%");
        List<Orders> orders = ordersRepository.findByShopIdAndPayedYearsAndPayedMonthAndPayedDayAndPhase(shops.getId(),year,month,day,2);
        map.put("todayTransaction",orders.size()+"");//今日交易
        map.put("actualTransaction","0");//实际交易

//        今日新增
        List<UserRelationShip> relationShipList = userRelationShipRepository.findByUserIdAndCreateTime(userId, "%" + todayTimeDD + "%");

        map.put("newPartner",relationShipList.size()+"");//新增伙伴
        Long aLong = 0L;
        if(shops!=null){
            aLong = orderItemsRepository.findByShopIdRefundTime(shops.getId());//and is_deleted=2 and is_refunding=1
        }
        map.put("todayReturn",aLong.toString());//今日退货
        return Result.successResult(map);
    }


    public Result dataAllKindOf(Long userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        //今天的时间
        String todayTimeDD = TimeUtils.getTodayTimeDD();
        //用户的伙伴
        List<UserRelationShip> relationShipList = userRelationShipRepository.findByUserIdAndCreateTime(userId, "%"+todayTimeDD+"%");
        KindOfDTO kindOfDTO = new KindOfDTO();
        //交易  ----  今日交易数据
        kindOfDTO = todayTransactionData(userId, todayTimeDD,kindOfDTO);
        //商户 ----  今日新增商户
        kindOfDTO = todayNewShops(todayTimeDD, relationShipList,kindOfDTO);
        //退货 ---- 今日退货数据
        kindOfDTO = todayRefunded(userId, todayTimeDD, relationShipList,kindOfDTO);
        return Result.successResult(kindOfDTO);
    }
    //今日交易数据
    public KindOfDTO todayTransactionData(Long userId,String dateTime,KindOfDTO kindOfDTO){
        //下级交易额
        Long subSum = partnerHomeService.subSum(userId, dateTime,1);
        kindOfDTO.setDirectSum("0");
        kindOfDTO.setSubSum(subSum.toString());
        kindOfDTO.setCount(subSum.toString());
        return kindOfDTO;
    }
    //今日新增商户
    public KindOfDTO todayNewShops(String dateTime,List<UserRelationShip> relationShipList,KindOfDTO kindOfDTO){
        //下级新增
        Integer subNewAdd = partnerHomeService.subNewAdd(relationShipList, dateTime);//下级新增的数据
        kindOfDTO.setSubNewAdd(subNewAdd.toString());
        kindOfDTO.setNewAdd(relationShipList.size()+"");
        kindOfDTO.setActualNewAdd(relationShipList.size()+"");
        return kindOfDTO;
    }
    //今日退货数据
    public KindOfDTO todayRefunded(Long userId ,String dateTime,List<UserRelationShip> relationShipList,KindOfDTO kindOfDTO){
        //新增退货
//        Long aLong = orderItemsRepository.findByUserIdRefundTimeSomeDay(userId, "%" + dateTime + "%");
        String year = TimeUtils.getYear();
        String month = TimeUtils.getMonth();
        String day = TimeUtils.getDay();

        List<Orders> list = ordersRepository.findByBusinessIdAndRefundYearsAndRefundMonthAndRefundDay(userId,year,month,day);
        kindOfDTO.setNewRefunded(list.size()+"");
        kindOfDTO.setDirectRefunded("0");
        //下级退货
        Long subCount = subRefundedDay(relationShipList, dateTime,1);
        kindOfDTO.setSubRefunded(subCount.toString());
        return kindOfDTO;
    }
    //下级退货-- type 1,天 2，月
    public Long subRefundedDay(List<UserRelationShip> relationShipList,String dateTime,Integer type){
        Long subCount = 0L;
        String year = null;
        String month = null;
        String day = null;
        if(StringUtils.isEmpty(dateTime)){
            year = TimeUtils.getYear();
            month = TimeUtils.getMonth();
            day = TimeUtils.getDay();
        }else{
            year = TimeUtils.getDisposeYear(dateTime);
            month = TimeUtils.getDisposeMonth(dateTime);
            day = TimeUtils.getDisposeDay(dateTime);
        }
        if(type == 1){
            for(UserRelationShip userRelationShip:relationShipList){
                Long subId = userRelationShip.getShipUserId();
                List<Orders> list = ordersRepository.findByBusinessIdAndRefundYearsAndRefundMonthAndRefundDay(subId, year, month, day);
                subCount+=list.size();
            }
        }else{
            for(UserRelationShip userRelationShip:relationShipList){
                Long subId = userRelationShip.getShipUserId();
                List<Orders> list = ordersRepository.findByBusinessIdAndRefundYearsAndRefundMonth(subId, year, month);
                subCount+=list.size();
            }
        }

        return subCount;
    }

//    public Result getAllKindOfTransaction(Long userId) {
//        if(userId == null){
//            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
//        }
//        MobileUser mobileUse = mobileUserRepository.selectMobileUse(userId);
//        if(mobileUse == null){
//            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
//        }
//        List<Map<String,String>> list = Lists.newArrayList();
//
//        //今天的日期
//        String todayTimeDD = TimeUtils.getTodayTimeDD();
//        //    日交易数据
//        Map<String, String> daySum = daySum(userId, todayTimeDD);
//        list.add(daySum);
//
//        //月交易数据
//        String monthTimeMM = TimeUtils.getMonthTimeMM();
//        Map<String, String> monthSum = monthSum(userId, monthTimeMM);
//        list.add(monthSum);
//
//        //    日实际成交额
//        Map<String, String> dayActualSum = dayActualSum(userId, todayTimeDD);
//        list.add(dayActualSum);
//
//        //    月实际成交额
//        Map<String, String> monthActualSum = monthActualSum(userId, monthTimeMM);
//        list.add(monthActualSum);
//
////        日退货
//        List<UserRelationShip> dayRelationShipList = userRelationShipRepository.findByUserIdAndCreateTime(userId, todayTimeDD);
//        Map<String, String> dayRefunded = dayRefunded(dayRelationShipList, todayTimeDD);
//        list.add(dayRefunded);
//
////        月退货
//        List<UserRelationShip> monthRelationShipList = userRelationShipRepository.findByUserIdAndCreateTime(userId, monthTimeMM);
//        Map<String, String> monthRefunded = monthRefunded(monthRelationShipList, todayTimeDD);
//        list.add(monthRefunded);
//
//        return Result.successResult(list);
//    }

//    日交易数据
//    public Map<String,String> daySum(Long userId,String dataTime){
////        Map<String, String> map = todayTransactionData(userId, dataTime);
//        return null;
//    }
//    //月交易数据
//    public Map<String,String> monthSum(Long userId,String dataTime){
////        Map<String, String> map = todayTransactionData(userId, dataTime);
//        Map<String,String> map = Maps.newHashMap();
//        map.put("monthDirectSum","0");//直营交易额
//        Long subSum = partnerHomeService.subSum(userId, dataTime);
//        map.put("monthSubSum",subSum.toString());//下级交易额
//        map.put("monthCount",subSum.toString());//下级交易额
//        return map;
//    }
////    日实际成交额
//    public Map<String,String> dayActualSum(Long userId,String dataTime){
////        Map<String, String> map = todayTransactionData(userId, dataTime);
//        Map<String,String> map = Maps.newHashMap();
//        map.put("directActualSum","0");//直营交易额
//        Long subSum = partnerHomeService.subSum(userId, dataTime);
//        map.put("subActualSum",subSum.toString());//下级交易额
//        map.put("countActual",subSum.toString());//下级交易额
//        return map;
//    }
////    月实际成交额
//    public Map<String,String> monthActualSum(Long userId,String dataTime){
////        Map<String, String> map = todayTransactionData(userId, dataTime);
//        Map<String,String> map = Maps.newHashMap();
//        map.put("monthDirectActualSum","0");//直营交易额
//        Long subSum = partnerHomeService.subSum(userId, dataTime);
//        map.put("monthSubActualSum",subSum.toString());//下级交易额
//        map.put("monthCountActual",subSum.toString());//下级交易额
//        return map;
//    }
////    日退货
//    public Map<String,String> dayRefunded(List<UserRelationShip> relationShipList,String dateTime){
//        Map<String,String> map = Maps.newHashMap();
//        map.put("directRefunded","0");//直营退货
////        下级退货
//        Long subRefunded = subRefunded(relationShipList, dateTime);
//        map.put("subRefunded",subRefunded.toString());//直营退货
//        map.put("count",subRefunded.toString());//合计
//        return map;
//    }
////    月退货
//    public Map<String,String> monthRefunded(List<UserRelationShip> relationShipList,String dateTime){
////        Map<String, String> map = dayRefunded(relationShipList, dateTime);
//        Map<String,String> map = Maps.newHashMap();
//        map.put("monthDirectRefunded","0");//直营退货
////        下级退货
//        Long subRefunded = subRefunded(relationShipList, dateTime);
//        map.put("monthSubRefunded",subRefunded.toString());//直营退货
//        map.put("monthCount",subRefunded.toString());//合计
//
//        return map;
//    }

//    public Result getDOrMTransaction(Long userId) {
//        if(userId == null){
//            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
//        }
//        MobileUser mobileUse = mobileUserRepository.selectMobileUse(userId);
//        if(mobileUse == null){
//            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
//        }
//        //交易数据
//        TransactionDataDTO transactionDataDTO = new TransactionDataDTO();
//        String todayTimeDD = TimeUtils.getTodayTimeDD();
//        //日交易数据
//        transactionDataDTO.setDayDirectSum("0");
//            //下级交易额
//        Long subSum = partnerHomeService.subSum(userId, todayTimeDD);
//        transactionDataDTO.setDaySubSum(subSum.toString());
//        transactionDataDTO.setDayCount(subSum.toString());
//
//        //月交易数据
//        String monthTimeMM = TimeUtils.getMonthTimeMM();
//        Long mSubSum = partnerHomeService.subSum(userId, monthTimeMM);
//        transactionDataDTO.setMonthDirectSum("0");
//        transactionDataDTO.setMonthSubSum(mSubSum.toString());
//        transactionDataDTO.setMonthCount(mSubSum.toString());
//
//        return Result.successResult(transactionDataDTO);
//    }

    public Result getDOrMActualTransaction(Long userId,String dateTime,Integer type) {
        return getDOrMTransaction(userId,dateTime,type);
    }

    public Result getDOrMRefunded(Long userId,String dateTime,Integer type) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        if(type == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_TYPE);
        }
        MobileUser mobileUse = mobileUserRepository.selectMobileUse(userId);
        if(mobileUse == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }

//        日退货

        HashMap<String, String> map = refundedDTOs(dateTime, userId,type);

        return Result.successResult(map);
    }
//    日or月退货
    public HashMap<String,String>  refundedDTOs(String dateTime ,Long userId,Integer type){
        HashMap<String,String> map = new HashMap<>();

        //
        Long sum = 0L;
        String year = null;
        String month = null;
        String day = null;
        if(StringUtils.isEmpty(dateTime)){
            dateTime = TimeUtils.getTodayTimeDD();
            year = TimeUtils.getYear();
            month = TimeUtils.getMonth();
            day = TimeUtils.getDay();
        }else{
            year = TimeUtils.getDisposeYear(dateTime);
            month = TimeUtils.getDisposeMonth(dateTime);
            day = TimeUtils.getDisposeDay(dateTime);
        }
        List<UserRelationShip> dayRelationShipList = new ArrayList<>();
        if(type ==1){
            dayRelationShipList = userRelationShipRepository.findByUserIdAndCreateTime(userId, "%"+dateTime+"%");
            for(UserRelationShip userRelationShip:dayRelationShipList){
                Long shipUserId = userRelationShip.getShipUserId();
//                List<OrderItems> list = orderItemsRepository.findByUserIdAndPhaseAndIsDeletedAndCreateTime(shipUserId, "%" + dateTime + "%");
                List<Orders> list = ordersRepository.findByBusinessIdAndRefundYearsAndRefundMonthAndRefundDay(shipUserId, year, month, day);
                for(Orders orders:list){
                    Long totalMoney = orders.getTotalMoney();
                    sum+=totalMoney;
                }
            }
        }else{
            dayRelationShipList = userRelationShipRepository.findByUserIdAndCreateTime(userId, "%"+TimeUtils.getDisposeMM(dateTime)+"%");
            for(UserRelationShip userRelationShip:dayRelationShipList){
                Long shipUserId = userRelationShip.getShipUserId();
                List<Orders> list = ordersRepository.findByBusinessIdAndRefundYearsAndRefundMonth(shipUserId,year,month);
                for(Orders orders:list){
                    Long totalMoney = orders.getTotalMoney();
                    sum+=totalMoney;
                }
            }
        }
        //下级退货
        Long subRefunded = subRefundedDay(dayRelationShipList, dateTime,type);
        map.put("directRefunded","0");//直营退货
        map.put("subRefunded",subRefunded.toString());//下级退货(件)
        map.put("count",sum.toString());//统计
        return map;
    }

    public Result getShopsData(Long userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUse = mobileUserRepository.selectMobileUse(userId);
        if(mobileUse == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        ShopsDataDTO shopsDataDTO = new ShopsDataDTO();
        shopsDataDTO.setUserName(mobileUse.getName());
        shopsDataDTO.setCreateTime(mobileUse.getCreatetime().toString());
        //我的伙伴
        List<UserRelationShip> list = userRelationShipRepository.findByUserId(userId);
        shopsDataDTO.setShopNum(list.size()+"");//商户数量
//        日新增
        shopsDataDTO.setDayDirectNewAdd("0");
            //下级新增
        String todayTimeDD = TimeUtils.getTodayTimeDD();
        List<UserRelationShip> dayUs = userRelationShipRepository.findByUserIdAndCreateTime(userId, "%" + todayTimeDD + "%");
        Integer subNewAdd = partnerHomeService.subNewAdd(dayUs, todayTimeDD);
        shopsDataDTO.setDaySubNewAdd(subNewAdd.toString());
        shopsDataDTO.setDayCount(subNewAdd.toString());

//      月新增
        shopsDataDTO.setMonthDirectNewAdd("0");
            //下级新增
        String monthTimeMM = TimeUtils.getMonthTimeMM();
        List<UserRelationShip> monUs = userRelationShipRepository.findByUserIdAndCreateTime(userId, "%" + monthTimeMM + "%");
        Integer mSubNewAdd = partnerHomeService.subNewAdd(monUs, monthTimeMM);
        shopsDataDTO.setMonthSubNewAdd(mSubNewAdd.toString());
        shopsDataDTO.setMonthCount(mSubNewAdd.toString());
        return Result.successResult(shopsDataDTO);
    }

    public Result getDOrMTransaction(Long userId,String dateTime,Integer type) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }

        if(type == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_TYPE);
        }

        MobileUser mobileUse = mobileUserRepository.selectMobileUse(userId);
        if(mobileUse == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
//
        //交易数据
        HashMap<String,String> map = new HashMap<>();
        //日交易数据
        map.put("directSum","0");

            //下级交易额
        Long subSum = partnerHomeService.subSum(userId, dateTime,type);
        map.put("subSum",subSum.toString());
        map.put("count",subSum.toString());//合计
        return Result.successResult(map);
    }

    public Result getDOrMTransactionShops(Long shopId, String dateTime, Integer type) {
        if(shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        if(type == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_TYPE);
        }
        //交易数据
        HashMap<String,String> map = new HashMap<>();
        //日交易数据
        map.put("directSum","0");
        Long userId = optional.get().getUserId();

        //下级交易额
        Long subSum = partnerHomeService.subSum(userId, dateTime,type);
        map.put("subSum",subSum.toString());
        map.put("count",subSum.toString());//合计
        return Result.successResult(map);
    }

    public Long shopsSubSum(Long shopId,String dateTime){
        Long sumMoney=0L;
        List<OrderItems> list = orderItemsRepository.findByShopIdAndPhaseAndIsDeleted(shopId, "%" + dateTime + "%");
        for(OrderItems orderItems:list){
            Long money = orderItems.getMoney();
            sumMoney+=money;
        }
        return sumMoney;
    }

    public Result getDOrMActualTransactionShops(Long shopId, String dateTime, Integer type) {
        Result result = getDOrMTransactionShops(shopId, dateTime, type);
        return result;
    }


    public Result getDOrMRefundedShops(Long shopId, String dateTime, Integer type) {
        if(shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        if(type == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_TYPE);
        }
        Long userId = optional.get().getUserId();

        HashMap<String,String> map = Maps.newHashMap();

        String year = null;
        String month = null;
        String day = null;
        if(StringUtils.isEmpty(dateTime)){
            dateTime = TimeUtils.getTodayTimeDD();
            year = TimeUtils.getYear();
            month = TimeUtils.getMonth();
            day = TimeUtils.getDay();
        }else{
            year = TimeUtils.getDisposeYear(dateTime);
            month = TimeUtils.getDisposeMonth(dateTime);
            day = TimeUtils.getDisposeDay(dateTime);
        }
        List<UserRelationShip> list = Lists.newArrayList();
        if(type ==1 ){
            list = userRelationShipRepository.findByUserIdAndCreateTime(userId, "%" + dateTime + "%");
        }else{
            list = userRelationShipRepository.findByUserIdAndCreateTime(userId, "%" + TimeUtils.getDisposeMM(dateTime) + "%");
        }
        //下级退货
        Long subCount = 0L;
        for(UserRelationShip userRelationShip:list){
            Long subId = userRelationShip.getShipUserId();
            List<Shops> shopsList = shopsRepository.findByUserId(subId);
            for(Shops shops:shopsList){
//                List<OrderItems> orderItemsList = orderItemsRepository.findByShopIdRefundTime(shops.getId(), "%" + dateTime + "%");
                List<Orders> list1 = Lists.newArrayList();
                if(type == 1){
                    list1 = ordersRepository.findByShopIdAndRefundYearsAndRefundMonthAndRefundDay(shops.getId(), year, month, day);
                }else{
                    list1 = ordersRepository.findByShopIdAndRefundYearsAndRefundMonth(shops.getId(), year, month);
                }
                    subCount+=list1.size();
            }
        }
        map.put("directRefunded","0");//直营退货件
        map.put("subRefunded",subCount.toString());//下级退货件
        map.put("count",subCount.toString());
        return Result.successResult(map);
    }
}
