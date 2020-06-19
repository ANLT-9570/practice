package com.dg.main.serviceImpl.shop;

import com.dg.main.Entity.ChatJson;
import com.dg.main.Entity.Follow;
import com.dg.main.Entity.MyCollection;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.shop.Shops;
//import com.dg.main.Entity.shop.ShowShops;
import com.dg.main.Entity.shop.Specifications;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.Entity.users.UserRealName;
import com.dg.main.exception.BaseException;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.AppCommentsRepostory;
import com.dg.main.repository.CollectionRepository;
import com.dg.main.repository.RedPackRepository;
import com.dg.main.repository.SpecificationsRepository;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.orders.OrdersRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.FollowRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.repository.users.UserRealNameRepository;
import com.dg.main.util.CheckSumBuilder;
import com.dg.main.util.Result;
import com.dg.main.util.slzcResult;
import com.dg.main.vo.CommentsCountVo;
import com.dg.main.vo.ShopsItemVo;
import com.dg.main.vo.ShopsVo;
import com.dg.main.vo.SpecificationsVo;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShopsService {

    @Autowired
    private ShopsRepository shopsRepository;
    @Autowired
    private RedPackRepository redPackRepository;
    @Autowired
    private SpecificationsRepository specificationsRepository;
    @Autowired
//    OrdersRepository ordersRepository;
    OrderItemsRepository orderItemsRepository;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    UserRealNameRepository userRealNameRepository;
    @Autowired
    private AppCommentsRepostory appCommentsRepostory;
    @Autowired
    private MobileUserRepository mobileUserRepository;
    @Autowired
    private UserRealNameRepository realNameRepository;
    @Autowired
    private CollectionRepository collectionRepository;

    public Result show(Integer offset,Integer limit){
        Date date = new Date();
        //2019-12-24 03:05:40
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        redPackRepository.findAll();
        List<ShopsVo> showShops = shopsRepository.show("%"+time+"%",offset,limit);
        return Result.successResult(showShops);
    }

    public Result storesCommodityV2(Long shopId,String condition,Integer offset, Integer limit) {
        if(shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        String token = optional.get().getChatToken();
        String registerID = optional.get().getRegisterID();
        List<SpecificationsVo> specificationsVos = Lists.newArrayList();
        List<Specifications> specifications = null;
        if (StringUtils.isNotBlank(condition)) {
            specifications = specificationsRepository.findByShopIdAndName(shopId, "%" + condition + "%", offset, limit);

        }else{
            specifications = specificationsRepository.findByShopId(shopId, offset, limit);
        }

        for(Specifications specifications1:specifications){
            SpecificationsVo specificationsVo = new SpecificationsVo();
            BeanUtils.copyProperties(specifications1,specificationsVo);
            specificationsVo.setToken(token);
            specificationsVo.setRegisterID(registerID);
            specificationsVos.add(specificationsVo);
        }
        return Result.successResult(specificationsVos);
    }

    public Result storesCommodity(Long shopId,Long userId,String condition,Integer offset, Integer limit) {

        Shops shops = shopsRepository.findByIdAndUserId(shopId,userId);
        if(shops == null){
            return Result.failureResult("43990","店铺不存在");
        }
        ShopsVo shopsVo = new ShopsVo();
        BeanUtils.copyProperties(shops, shopsVo);

        List<OrderItems> orderItems = orderItemsRepository.findByShopIdAndPhaseAndIsDeleted(shops.getId());
        shopsVo.setTotalSales(orderItems.size());
        if (condition != null) {
            List<Specifications> specifications = specificationsRepository.findByShopIdAndName(shops.getId(), "%" + condition + "%", offset, limit);
            shopsVo.setSpecifications(specifications);
            return Result.successResult(shopsVo);
        }
        List<Specifications> specifications = specificationsRepository.findByShopId(shops.getId(), offset, limit);
        shopsVo.setSpecifications(specifications);

        return Result.successResult(shopsVo);
    }
    public Result ShopParticulars(Long specificationsId) {
        if(specificationsId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        Optional<Specifications> optional = specificationsRepository.findById(specificationsId);
        SpecificationsVo specificationsVo = new SpecificationsVo();
        BeanUtils.copyProperties(optional.get(),specificationsVo);
        if(optional.isPresent()){
            Long shopId = optional.get().getShopId();
            Optional<Shops> optional1 = shopsRepository.findById(shopId);
            specificationsVo.setToken(optional1.get().getChatToken());
            specificationsVo.setRegisterID(optional1.get().getRegisterID());
        }
        return Result.successResult(specificationsVo);
    }

    public Result SuperShop(Integer offset, Integer limit) {
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }

        List<Shops> shops = shopsRepository.findAll(offset, limit);
        List<ShopsVo> shopsVos = Lists.newArrayList();
        for (Shops shop : shops) {
            ShopsVo shopsVo = new ShopsVo();
            BeanUtils.copyProperties(shop,shopsVo);
            Long money = redPackRepository.findByShopIdAndSum(shop.getId());
            shopsVo.setMoney(money);
            shopsVos.add(shopsVo);
        }
        return Result.successResult(shopsVos);
    }

    public Result storesParticulars(Long shopId) {
        if(shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);

//        Shops shops = shopsRepository.findById(shopId);
        if(optional.get() == null){
            return Result.failureResult("43990","店铺不存在");
        }
        ShopsVo shopsVo = new ShopsVo();
        RedPack redPack = redPackRepository.selectShopId(shopId);
        if(redPack != null){
            Long leftMoney = redPack.getLeftMoney();
            if(leftMoney != null && leftMoney != 0){
                shopsVo.setIsSendRedPack(1);
            }else{
                shopsVo.setIsSendRedPack(2);
            }
        }else{
            shopsVo.setIsSendRedPack(2);
        }

        Follow follow = followRepository.findByUserIdAndShopId(optional.get().getUserId(), optional.get().getId());
        if(follow==null){
            shopsVo.setStatus(2);
        }else {
            shopsVo.setStatus(1);
        }

        BeanUtils.copyProperties(optional.get(),shopsVo);
        List<OrderItems> list = orderItemsRepository.findByShopIdAndPhaseAndIsDeleted(shopId);
        shopsVo.setTotalSales(list.size());
        return Result.successResult(shopsVo);
    }

    public Result RealName(UserRealName userRealName) {

        UserRealName save = userRealNameRepository.save(userRealName);
        if (save != null){
            return Result.successResult();
        }
        return Result.failureResult("43990","添加失败");
    }

    public Result commentCount(Long specificationId) {
        if(specificationId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        Integer good=0,bad=0,normal=0,total=0;
        List<OrderItems> lists = orderItemsRepository.findBySpecificationId(specificationId);
        for (OrderItems list : lists) {
            Integer isGood = list.getIsGood();
            Integer isBad = list.getIsBad();
            Integer isNormal = list.getIsNormal();
            if(isGood != null){
                good+=isGood;
            }
            if(isBad != null){
                bad += isBad;
            }
            if(isNormal != null){
                normal += isNormal;
            }
        }
        total = good + bad + normal;
        CommentsCountVo commentsCountVo = new CommentsCountVo();
        commentsCountVo.setBad(bad);
        commentsCountVo.setGood(good);
        commentsCountVo.setNormal(normal);
        commentsCountVo.setTotal(total);
        return Result.successResult(commentsCountVo);
    }

    public Result setUpShop(Shops shops) {
        if (shops == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Long userId = shops.getUserId();
        if (userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        String name = shops.getName();
        if(StringUtils.isEmpty(name)){
            return Result.failureResult(ExceptionCode.Failure.NAME_NOT_NULL);
        }
        Shops newShops = shopsRepository.findByName(shops.getName());
        if (newShops != null){
            return Result.failureResult(ExceptionCode.Failure.IS_EXIST);
        }
        UserRealName realName = userRealNameRepository.findByUserId(shops.getUserId());
        if(realName == null || realName.getStatus() != 1){
            return Result.failureResult(ExceptionCode.Failure.NOT_AUTHENTICATION);
        }

        Long userId1 = shops.getUserId();
//        Optional<MobileUser> optional = mobileUserRepository.findById(userId1);
        List<Shops> list = shopsRepository.findByUserId(userId);
        if(list == null || list.size() == 0){
            shops.setIsDefault(1);
        }
        String registerID =shops.getName()+""+new Date().getTime();
        String md5= Hashing.md5().newHasher().putString(registerID, Charsets.UTF_8).hash().toString();
        String chart = getChart(md5, name);

        if(StringUtils.isNotEmpty(chart)){
            Gson gson = new Gson();
            ChatJson chatJson = gson.fromJson(chart, ChatJson.class);
            shops.setChatToken(chatJson.getInfo().getToken());
            shops.setRegisterID(chatJson.getInfo().getAccid());
        }
        String[] arr=shops.getCity().split("-");
        shops.setCity(arr[1]);
        shops.setProvince(arr[0]);
        Shops save = shopsRepository.save(shops);
        return Result.successResult(save);
    }

    @Transactional
    public Result delShop(Long id) {

        if (id == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        shopsRepository.deleteById(id);
        specificationsRepository.deleteByShopId(id);
        return Result.successResult();
    }

    public Result delAllShop(List<Long> ids) {
        if (ids == null || ids.size() == 0){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        ids.forEach(i->{
            shopsRepository.deleteById(i);
        });
        return Result.successResult();
    }


    public Result updateShop(Shops shops) {
        if (shops == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Optional<Shops> optional = shopsRepository.findById(shops.getId());
        if (!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_EXIST);
        }
        shopsRepository.save(shops);
        return Result.successResult();
    }


    public Result getListShops(Long userId) {
        if (userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        List<Shops> shops = shopsRepository.findByUserId(userId);

        return Result.successResult(shops);
    }

    public Result getShops(Long id) {
        if (id == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Optional<Shops> optional = shopsRepository.findById(id);

        return Result.successResult(optional.get());
    }

    @Transactional
    public Result delUserIdShop(Long userId) {
        if (userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        shopsRepository.deleteByUserId(userId);
        specificationsRepository.deleteByUserId(userId);
        return Result.successResult();
    }

    @Transactional
    public Result switchShops(Long shopId,Long userId) {
        if (shopId == null || userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if (!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        shopsRepository.updateIsDefaultAndUserId(userId);
        shopsRepository.updateByIdIsDefaultAndUserId(userId,shopId);

        return Result.successResult(optional.get());
    }

    public List<Shops> findByCityGroup() {
        List<Shops> list = shopsRepository.findByCityGroup();
        return list;
    }

    public Shops findById(Long shopId) {
        Optional<Shops> optional = shopsRepository.findById(shopId);
        return optional.get();
    }

    public Shops findByUserId(Long mobileUseId) {
        Shops shops = shopsRepository.findByUserIdAndIsDefault(mobileUseId);
        return shops;
    }

    public String getChart(String code,String phone) {
//        Chat chat = chatRepostory.findByMobileUseId(mobileUseId);
//        if(chat != null){
//            return chat.getToken();
//        }
        String chartR = null;
        try {
            chartR = CheckSumBuilder.chartR(code,phone);
            System.out.println("-----------------------");
            System.out.println(chartR.toString());
//            Gson gson = new Gson();
//            ChatJson chatJson = gson.fromJson(chartR, ChatJson.class);
//            System.out.println(chartR.toString());
//            String codes = chatJson.getCode();
//            if(codes.equals("414")){
//                System.out.println("该账户已经注册过聊天室");
//                return "0";
//            }
//            String tokens = chatJson.getInfo().getToken();

            return chartR;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public slzcResult findPageAll(int limit, int offset) {
        List<Shops> shops = shopsRepository.findByStatusPage(limit,offset);
        List<ShopsVo> shopsVos = Lists.newArrayList();
        for(Shops shops1:shops){
            Long userId = shops1.getUserId();
            Optional<MobileUser> optional = mobileUserRepository.findById(userId);
            String userName = optional.get().getName();

            ShopsVo shopsVo = new ShopsVo();
            BeanUtils.copyProperties(shops1,shopsVo);
            shopsVo.setUserName(userName);

            shopsVos.add(shopsVo);
        }
        Long count = shopsRepository.findPageCount();
        slzcResult slzcResult = new slzcResult();
        slzcResult.setTotal(count);
        slzcResult.setRows(shopsVos);
        return slzcResult;
    }

    public Result isCollection(Long specificationId, Long userId) {
        if(specificationId == null){
           return Result.failureResult(ExceptionCode.Failure.NOT_SPE);
        }
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        Optional<Specifications> optional = specificationsRepository.findById(specificationId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SPECIFICATION);
        }
        Map<String,Integer> map = Maps.newHashMap();
        MyCollection myCollection = collectionRepository.findByUserIdAndSpecificationsId(specificationId,userId);
        if(myCollection != null && myCollection.getStatus() == 2){
            map.put("status",1);
        }else{
            map.put("status",0);
        }
        return Result.successResult(map);
    }
}
