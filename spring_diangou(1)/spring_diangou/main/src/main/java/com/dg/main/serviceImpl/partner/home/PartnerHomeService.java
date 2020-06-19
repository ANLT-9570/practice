package com.dg.main.serviceImpl.partner.home;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.Entity.users.UserRelationShip;
import com.dg.main.dto.MDTransactionDTO;
import com.dg.main.dto.MyPartnerDTO;
import com.dg.main.dto.PartnerHomeDTO;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.orders.OrdersRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.repository.users.UserRelationShipRepository;
import com.dg.main.serviceImpl.partner.earnings.EarningsHomeService;
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
public class PartnerHomeService {

    @Autowired
    private MobileUserRepository mobileUserRepository;
    @Autowired
    private UserRelationShipRepository userRelationShipRepository;
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private ShopsRepository shopsRepository;
    @Autowired
    private EarningsHomeService earningsHomeService;
    @Autowired
    private OrdersRepository ordersRepository;

//    public Result getKindOf(Long userId,PartnerHomeDTO partnerHomeDTO) {
//        MobileUser mobileUse = mobileUserRepository.selectMobileUse(userId);
//        if(mobileUse == null){
//            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
//        }
//
//        Shops shops = shopsRepository.findByUserIdAndIsDefault(userId);
//        partnerHomeDTO.setShopName(shops == null?"还没创建有商铺":shops.getName());
//        partnerHomeDTO.setImage(shops == null?"还没创建有商铺":shops.getDisplayImg());
//        partnerHomeDTO.setShopsInto("0");
//        //今日收益
//        String todayTimeDD = TimeUtils.getTodayTimeDD();
//        List<OrderItems> list = orderItemsRepository.findByUserIdAndPhaseAndIsDeletedAndCreateTime(userId, "%" + todayTimeDD + "%");
//        Long todayEarnings = 0L;
//        for(OrderItems orderItems:list){
//            Long money = orderItems.getMoney();
//            todayEarnings+=money;
//        }
//        partnerHomeDTO.setTodayEarnings(todayEarnings.toString());
//        //今日交易
//        partnerHomeDTO.setTodayTransaction(list.size()+"");
//        return Result.successResult(partnerHomeDTO);
//    }


//    public Result getAllKindOf(Long userId) {
//        if(userId == null){
//            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
//        }
//        String todayTimeDD = TimeUtils.getTodayTimeDD();
//
//        List<Map<String,String>> list = Lists.newArrayList();
//
//        //本月数据简览
//        Map<String, String> monthJane = monthJane(userId);
//        list.add(monthJane);
//
//        //今日交易数据
//        Map<String, String> todayTransaction = todayTransaction(userId, todayTimeDD);
//        list.add(todayTransaction);
//
//        //今日新增
//        Map<String, String> todayAdd = todayAdd(userId,  todayTimeDD);
//        list.add(todayAdd);
//
//        return Result.successResult(list);
//    }

    public PartnerHomeDTO getAllKindOf_v2(Long userId,PartnerHomeDTO partnerHomeDTO) {
        String todayTimeDD = TimeUtils.getTodayTimeDD();

        //本月数据简览
        partnerHomeDTO = monthJane(userId,partnerHomeDTO);

        //今日交易数据
        partnerHomeDTO = todayTransaction(userId, todayTimeDD,partnerHomeDTO);

        //今日新增
        partnerHomeDTO = todayAdd(userId,  todayTimeDD,partnerHomeDTO);

        return partnerHomeDTO;
    }

    //本月数据简览
    public PartnerHomeDTO monthJane(Long userId,PartnerHomeDTO partnerHomeDTO){
        String dateTime = TimeUtils.getMonthTimeMM();
        List<UserRelationShip> userRelationShips = userRelationShipRepository.findByUserIdAndCreateTime(userId,"%"+dateTime+"%");
        //本月数据简览
//        Map<String,String> monthMap = Maps.newHashMap();

        List<Long> shipUserIds = Lists.newArrayList();
        for(UserRelationShip userRelationShip :userRelationShips){
            Long shipUserId = userRelationShip.getShipUserId();
            shipUserIds.add(shipUserId);
        }
        List<MobileUser> mobileUsers = null;
        if(shipUserIds.size()>0){
            mobileUsers = mobileUserRepository.findByMobileUserIds(shipUserIds);
        }
        List<OrderItems> list = orderItemsRepository.findByUserIdAndPhaseAndIsDeletedAndCreateTime(userId, "%" + dateTime + "%");
        Long sum = 0l;
        for(OrderItems orderItems:list){
            sum+=orderItems.getMoney();
        }
        partnerHomeDTO.setMonthSum(sum.toString());
        partnerHomeDTO.setComradeSum(userRelationShips == null ?"0":userRelationShips.size()+"");
        partnerHomeDTO.setShopsSum(mobileUsers == null ?"0":mobileUsers.size()+"");
//        monthMap.put("monthSum","0");//本月交易额
//        monthMap.put("comradeSum",userRelationShips == null ?"0":userRelationShips.size()+"");//累计伙伴
//        monthMap.put("shopsSum",mobileUsers == null ?"0":mobileUsers.size()+"");//累计商户
        return partnerHomeDTO;
    }

    //今日交易数据
    public PartnerHomeDTO todayTransaction(Long userId,String dateTime,PartnerHomeDTO partnerHomeDTO){

        Long subSum = subSum(userId, dateTime,1);
        partnerHomeDTO.setDirectSum("0");
        partnerHomeDTO.setSubSum(subSum.toString());
        partnerHomeDTO.setCount(subSum.toString());
        return partnerHomeDTO;
    }
    //下级交易额 --type 1,是天 2,是月
    public Long subSum(Long userId,String dateTime,Integer type){
        //
        Long sumMoney = 0L;

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
        List<UserRelationShip> list = new ArrayList<>();

        if(type ==1){
            list = userRelationShipRepository.findByUserIdAndCreateTime(userId, "%" + dateTime + "%");
            for(UserRelationShip userRelationShip:list){
                Long shipUserId = userRelationShip.getShipUserId();
//            List<OrderItems> orderItems = orderItemsRepository.findByUserIdAndPhaseAndIsDeletedAndCreateTime(shipUserId,"%"+dateTime+"%");
                //今天的
                List<Orders> orders = ordersRepository.findByUserIdAndPayedYearsAndPayedMonthAndPayedDayAndPhase(shipUserId,year,month,day,2);
                for(Orders orders1:orders){
                    Long totalMoney = orders1.getTotalMoney();
                    sumMoney+=totalMoney;
                }
            }
        }else{
            list = userRelationShipRepository.findByUserIdAndCreateTime(userId, "%" + TimeUtils.getDisposeMM(dateTime) + "%");
            for(UserRelationShip userRelationShip:list){
                Long shipUserId = userRelationShip.getShipUserId();
                //这个月的
                List<Orders> orders = ordersRepository.findByUserIdAndPayedYearsAndPayedMonthAndPhase(shipUserId,year,month,2);
                for(Orders orders1:orders){
                    Long totalMoney = orders1.getTotalMoney();
                    sumMoney+=totalMoney;
                }
            }
        }

        return sumMoney;
    }
    //今日新增
    public PartnerHomeDTO todayAdd(Long userId,String dateTime,PartnerHomeDTO partnerHomeDTO){
        //今日新增的用户
        List<UserRelationShip> userRelationShips1 = userRelationShipRepository.findByUserIdAndCreateTime(userId,"%"+dateTime+"%");
        //今日下级新增
        Integer subNewAdd = subNewAdd(userRelationShips1, dateTime);
        partnerHomeDTO.setNewAddShops(userRelationShips1.size()+"");
        partnerHomeDTO.setDirectNewAdd("0");
        partnerHomeDTO.setSubNewAdd(subNewAdd.toString());
//        addMap.put("newAddShops",userRelationShips1.size()+"");//新增商户
//        addMap.put("directNewAdd","0");//直营新增
//        addMap.put("subNewAdd",subNewAdd.toString());//下级新增
        return partnerHomeDTO;
    }
    //下级新增的数据
    public Integer subNewAdd(List<UserRelationShip> userRelationShips,String dateTime){
        Integer subNewAdd = 0;
        for(UserRelationShip userRelationShip:userRelationShips){
            Long shipUserId = userRelationShip.getShipUserId();
            List<UserRelationShip> subNew = userRelationShipRepository.findByUserIdAndCreateTime(shipUserId, "%" + dateTime + "%");
            subNewAdd+=subNew.size();
        }
        return subNewAdd;
    }
    public Result getMyPartner(Long userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUse = mobileUserRepository.selectMobileUse(userId);
        if(mobileUse == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
//      用户的伙伴
        List<UserRelationShip> list = userRelationShipRepository.findByUserId(userId);
        List<MyPartnerDTO> mapList = Lists.newArrayList();
        for(UserRelationShip userRelationShip:list){
            Long shipUserId = userRelationShip.getShipUserId();
            MobileUser mobileUser = mobileUserRepository.selectMobileUse(shipUserId);

                MyPartnerDTO myPartnerDTO = new MyPartnerDTO();
                myPartnerDTO.setName(mobileUser.getName());
                myPartnerDTO.setImage(mobileUser.getImage());
                myPartnerDTO.setPhone(mobileUser.getPhone());
                myPartnerDTO.setUserId(mobileUser.getMobileUseId().toString());
                myPartnerDTO.setShiId(shipUserId.toString());
                //本月交易额
                String monthTimeMM = TimeUtils.getMonthTimeMM();
                Long monthSum = 0L;
                List<OrderItems> orderItems = orderItemsRepository.findByShopIdsAndPhase(mobileUser.getMobileUseId(),"%"+monthTimeMM+"%");
                for(OrderItems orderItems1:orderItems){
                    Long money = orderItems1.getMoney();
                    monthSum+=money;
                }
                myPartnerDTO.setMonthTransaction(monthSum.toString());
//            新增人数
                myPartnerDTO.setReferrer(mobileUse.getName()+"("+mobileUse.getPhone()+")");
                mapList.add(myPartnerDTO);

        }

        return Result.successResult(mapList);
    }


    public Result getMyMerchant(Long userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUse = mobileUserRepository.selectMobileUse(userId);
        if(mobileUse == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        String monthTimeMM = TimeUtils.getMonthTimeMM();

        List<Map<String,String>> mapList = Lists.newArrayList();

        List<UserRelationShip> shipList = userRelationShipRepository.findByUserId(userId);
        for(UserRelationShip userRelationShip:shipList){
            Long shipUserId = userRelationShip.getShipUserId();
            MobileUser mobileUser = mobileUserRepository.selectMobileUse(shipUserId);
            Map<String,String> map = Maps.newHashMap();
//                List<Shops> list = shopsRepository.findByUserId(mobileUser.getMobileUseId());
            Shops shops = shopsRepository.findByUserIdAndIsDefault(mobileUser.getMobileUseId());
                if(shops != null){
                    List<OrderItems> itemsList = orderItemsRepository.findByShopIdAndPhaseAndIsDeleted(shops.getId(),"%"+monthTimeMM+"%");
                    map.put("img",shops.getDisplayImg());
                    map.put("name",shops.getName());
                    map.put("phone",mobileUser.getPhone());
                    map.put("monthTransaction",itemsList.size()+"");//本月交易量
                    map.put("userId",mobileUser.getMobileUseId().toString());
                    map.put("shopId",shops.getId().toString());
                    map.put("shipId",shipUserId.toString());

                    Long sumMoney = 0L;
                    for(OrderItems orderItems:itemsList){
                        Long money = orderItems.getMoney();
                        sumMoney+=money;
                    }
                    map.put("sumMoney",sumMoney.toString());//交易额
                    mapList.add(map);
                }
        }
        return Result.successResult(mapList);
    }

    public Result getIndexData(Long userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if (mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        PartnerHomeDTO partnerHomeDTO = new PartnerHomeDTO();

        partnerHomeDTO = kindOf(userId,partnerHomeDTO);
        partnerHomeDTO =getAllKindOf_v2(userId,partnerHomeDTO);
        return Result.successResult(partnerHomeDTO);
    }

    public PartnerHomeDTO kindOf(Long userId,PartnerHomeDTO partnerHomeDTO){
        Shops shops = shopsRepository.findByUserIdAndIsDefault(userId);
        partnerHomeDTO.setShopName(shops == null?"还没创建有商铺":shops.getName());
        partnerHomeDTO.setImage(shops == null?"还没创建有商铺":shops.getDisplayImg());
        partnerHomeDTO.setShopsInto("0");
        //今日收益
        String todayTimeDD = TimeUtils.getTodayTimeDD();
        List<OrderItems> list = orderItemsRepository.findByUserIdAndPhaseAndIsDeletedAndCreateTime(userId, "%" + todayTimeDD + "%");
        Long todayEarnings = 0L;
        for(OrderItems orderItems:list){
            Long money = orderItems.getMoney();
            todayEarnings+=money;
        }
        partnerHomeDTO.setTodayEarnings(todayEarnings.toString());
        //今日交易
        partnerHomeDTO.setTodayTransaction(list.size()+"");
        return partnerHomeDTO;
    }

    public Result shopsInfo(Long shopId) {
        if(shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(optional.get().getUserId());
        Map<String,String>  map = new HashMap<>();
        map.put("image",optional.get().getDisplayImg());
        map.put("shopName",optional.get().getName());
        map.put("createTime",optional.get().getCreateTime().toString());
        map.put("phone",mobileUser.getPhone());

        List<OrderItems> orderItemsList = orderItemsRepository.findByShopIdAndPhaseAndIsDeleted(shopId);
        Long totalMoney = earningsHomeService.dispose(orderItemsList);
        map.put("totalMoney",totalMoney.toString());//总交易额
        String monthTimeMM = TimeUtils.getMonthTimeMM();
        List<OrderItems> itemsList = orderItemsRepository.findByShopIdsAndPhase(shopId, "%" + monthTimeMM + "%");
        Long monthSum = earningsHomeService.dispose(itemsList);
        map.put("monthSum",monthSum.toString());//本月交易额

        return Result.successResult(map);
    }


//    public Result getDMTransaction(Long shopId,String date) {
//        if(shopId == null){
//            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
//        }
////        日交易
//        MDTransactionDTO mdTransactionDTO = new MDTransactionDTO();
//        mdTransactionDTO.setDayRate("0");
//        if(date==null){
//            date = TimeUtils.getTodayTimeDD();
//        }
//        List<OrderItems> list = orderItemsRepository.findByShopIdsAndPhase(shopId, "%" + date + "%");
//        Long daySaleNumber = earningsHomeService.dispose(list);
//        mdTransactionDTO.setDaySaleNumber(daySaleNumber.toString());//销量
//        mdTransactionDTO.setDaySum(daySaleNumber.toString());//合计
//
////        月交易
//        mdTransactionDTO.setMonthRate("0");
//        if(date==null){
//            date = TimeUtils.getMonthTimeMM();
//        }
//        List<OrderItems> orderItems = orderItemsRepository.findByShopIdsAndPhase(shopId, "%" + date + "%");
//        Long monthSaleNumber = earningsHomeService.dispose(orderItems);
//        mdTransactionDTO.setMonthSaleNumber(monthSaleNumber.toString());
//        mdTransactionDTO.setMonthSum(monthSaleNumber.toString());
//        return Result.successResult(mdTransactionDTO);
//    }

//    public Result getDMRefunded(Long shopId, String date) {
//        if(shopId == null){
//            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
//        }
//        Map<String,String> map = Maps.newHashMap();
////        日退货
//        if(date == null){
//            date = TimeUtils.getTodayTimeDD();
//        }
//        List<OrderItems> orderItems = orderItemsRepository.findByShopIdRefundTime(shopId, "%" + date + "%");
//        Long dayCountMoney = earningsHomeService.dispose(orderItems);
//        map.put("dayNumber",orderItems.size()+"");//日退货数量
//        map.put("dayCountMoney",dayCountMoney.toString());//合计
////        月退货
//        if(date == null){
//            date = TimeUtils.getMonthTimeMM();
//        }
//        List<OrderItems> list = orderItemsRepository.findByShopIdRefundTime(shopId, "%" + date + "%");
//        Long monthCountMoney = earningsHomeService.dispose(list);
//        map.put("monthNumber",list.size()+"");//月退货数量
//        map.put("monthCountMoney",monthCountMoney.toString());//月合计
//
//        return Result.successResult(map);
//    }

    public Result getDMActualTransaction(Long shopId, String date,Integer type) {
        Result result = getDMTransaction(shopId, date,type);
        return result;
    }

    public Result getDMTransaction(Long shopId, String date,Integer type) {
        if(shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        if(type == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_TYPE);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
//        日交易
        Map<String,String> map = new HashMap<>();
        map.put("rate","0");
        String year = null;
        String month = null;
        String day = null;
        if(StringUtils.isEmpty(date)){
            date = TimeUtils.getTodayTimeDD();
            year = TimeUtils.getYear();
            month = TimeUtils.getMonth();
            day = TimeUtils.getDay();
        }else{
            year = TimeUtils.getDisposeYear(date);
            month = TimeUtils.getDisposeMonth(date);
            day = TimeUtils.getDisposeDay(date);
        }
        List<Orders> ordersList = new ArrayList<>();
        if(type ==1){
            ordersList = ordersRepository.findByShopIdAndPayedYearsAndPayedMonthAndPayedDayAndPhase(shopId, year, month, day, 2);
        }else {
            ordersList = ordersRepository.findByShopIdAndPayedYearsAndPayedMonthAndPhase(shopId, year, month, 2);
        }

        Long daySaleNumber = dispose(ordersList);
        map.put("saleNumber",daySaleNumber.toString());
        map.put("sum",daySaleNumber.toString());
        return Result.successResult(map);
    }
    public Long dispose(List<Orders> ordersList){
        Long sum = 0L;
        for(Orders orders:ordersList){
            Long totalMoney = orders.getTotalMoney();
            sum+=totalMoney;
        }
        return sum;
    }
    public Result getDMRefunded(Long shopId, String date,Integer type) {
        if(shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        if(type == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_TYPE);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        Map<String,String> map = Maps.newHashMap();

        String year = null;
        String month = null;
        String day = null;
        if(StringUtils.isEmpty(date)){
            date = TimeUtils.getTodayTimeDD();
            year = TimeUtils.getYear();
            month = TimeUtils.getMonth();
            day = TimeUtils.getDay();
        }else{
            year = TimeUtils.getDisposeYear(date);
            month = TimeUtils.getDisposeMonth(date);
            day = TimeUtils.getDisposeDay(date);
        }
        List<Orders> orders = new ArrayList<>();
        if(type ==1){
            orders = ordersRepository.findByShopIdAndRefundYearsAndRefundMonthAndRefundDay(shopId,year,month,day);
        }else{
            orders = ordersRepository.findByShopIdAndRefundYearsAndRefundMonth(shopId,year,month);
        }
        Long dispose = dispose(orders);
//        List<OrderItems> orderItems = orderItemsRepository.findByShopIdRefundTime(shopId, "%" + date + "%");
//        Long dayCountMoney = earningsHomeService.dispose(orderItems);
        map.put("dayNumber",orders.size()+"");//日退货数量
        map.put("dayCountMoney",dispose.toString());//合计
        return Result.successResult(map);
    }
}
