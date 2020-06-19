package com.dg.main.serviceImpl.shop;

import com.dg.main.Entity.shop.*;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.dto.shop.ShopCarDto;
import com.dg.main.dto.shop.ShopCarItemsDao;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.param.orders.AddShopCarParam;

import com.dg.main.repository.SpecificationsRepository;
import com.dg.main.repository.shop.ShopCarItemsRepository;
import com.dg.main.repository.shop.ShopCarRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.shop.SizeRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.util.Result;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShopCarService {
    @Autowired
    ShopCarItemsRepository shopCarItemsRepository;
    @Autowired
    ShopCarRepository shopCarRepository;
    @Autowired
    ShopsRepository shopsRepository;
    @Autowired
    SpecificationsRepository specificationsRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    private MobileUserRepository mobileUserRepository;
    @Transactional
    public Result addItems(AddShopCarParam param){
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(param.getUserId());
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        Optional<Shops> shops = shopsRepository.findById(param.getShopId());
        if(!shops.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        Optional<Specifications> optional1 = specificationsRepository.findById(param.getSpecificationId());
        if(!optional1.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SPECIFICATION);
        }
        Optional<Size> optional = sizeRepository.findById(param.getSizeId());
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SIZE);
        }
        ShopCar shopCar=shopCarRepository.findByUserIdAndShopId(param.getUserId(),param.getShopId());
        if(shopCar == null){
            shopCar=new ShopCar();
             shopCar.setUserId(param.getUserId());
             shopCar.setShopId(param.getShopId());
             shopCarRepository.save(shopCar);
             ShopCarItems shopCarItems=new ShopCarItems();
            shopCarItems.setShopCarId(shopCar.getId());
            shopCarItems.setMoney(param.getPrice());
            shopCarItems.setNumber(param.getNumber());
            shopCarItems.setPlatformMoney(param.getPrice()/10);
            shopCarItems.setSpecificationId(param.getSpecificationId());
            shopCarItems.setSkuId(param.getSizeId());
            shopCarItemsRepository.save(shopCarItems);
        }else{
            ShopCarItems shopCarItems=shopCarItemsRepository.findByShopCarIdAndSpecificationId(shopCar.getId(),param.getSpecificationId());
            if(shopCarItems==null){
                shopCarItems=new ShopCarItems();
                   shopCarItems.setShopCarId(shopCar.getId());
                   shopCarItems.setMoney(param.getPrice());
                  shopCarItems.setNumber(param.getNumber());
                   shopCarItems.setPlatformMoney(param.getPrice()/10);
                shopCarItems.setSpecificationId(param.getSpecificationId());
                shopCarItems.setSkuId(param.getSizeId());
                   shopCarItemsRepository.save(shopCarItems);
            }else{
                shopCarItems.setNumber(shopCarItems.getNumber()+1);
                shopCarItemsRepository.save(shopCarItems);
            }
        }
        return Result.successResult();
    }
    @Transactional
    public Result addNumber(Long shopCarItemId){
        ShopCarItems shopCarItems=shopCarItemsRepository.getOne(shopCarItemId);
        if(shopCarItems == null){
            return Result.failureResult("43556","没有该购物车");
        }
        shopCarItems.setNumber(shopCarItems.getNumber()+1);
        shopCarItemsRepository.save(shopCarItems);
        return Result.successResult();
    }
    @Transactional
    public Result minusNumber(Long shopCarItemId){
        if(shopCarItemId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        ShopCarItems shopCarItems=shopCarItemsRepository.getOne(shopCarItemId);
        if(shopCarItems == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOPINGCART);
        }
        if(shopCarItems.getNumber()!=0) {
            shopCarItems.setNumber(shopCarItems.getNumber() - 1);
            shopCarItemsRepository.save(shopCarItems);
        }
        return Result.successResult();
    }
    @Transactional
    public void multiDelete(List<ShopCarDto> shopCarDto){
       // List<Long> isNoChild=Lists.newArrayList();
//        List<ShopCarItems> temp=Lists.newArrayList();
//        for (ShopCarDto i : shopCarDto){
//            temp.addAll(i.getItems());
//        }
//        shopCarItemsRepository.deleteAll(temp);
//        for (ShopCarDto i :shopCarDto){
//            List<ShopCarItems> temp1=shopCarItemsRepository.findByShopCarId(i.getId());
//            if(temp1.size()==0){
//               // isNoChild.add(i.getId());
//                shopCarRepository.deleteById(i.getId());
//            }
//        }
      //  shopCarRepository.

    }
    @Transactional
    public List<ShopCarDto> userList(Long userId){
        List<ShopCar> shopCars=shopCarRepository.findByUserId(userId);
        List<ShopCarDto> shopCarDtos= Lists.newArrayList();
        shopCars.stream().forEach(i->{
            ShopCarDto shopCarDto=new ShopCarDto();
            List<ShopCarItems> shopCarItems=shopCarItemsRepository.findByShopCarId(i.getId());
            List<ShopCarItemsDao> shopCarItemsDaos=Lists.newArrayList();
            for(ShopCarItems item:shopCarItems){
                ShopCarItemsDao shopCarItemsDao=new ShopCarItemsDao();
                BeanUtils.copyProperties(item,shopCarItemsDao);
                Specifications specifications=specificationsRepository.getOne(item.getSpecificationId());
                shopCarItemsDao.setImg(specifications.getImg());
               // shopCarItemsDao.setInfo(specifications.getName());
                shopCarItemsDao.setName(specifications.getName());
                if (item.getSkuId()!=null){
                   // Size size=size
                    Size size=sizeRepository.getOne(item.getSkuId());
                    shopCarItemsDao.setInfo("颜色："+size.getColour()+"大小："+size.getDimensions());

                }
                shopCarItemsDaos.add(shopCarItemsDao);
            }
            BeanUtils.copyProperties(i,shopCarDto);
            Shops shops=shopsRepository.getOne(i.getShopId());
            shopCarDto.setShopName(shops.getName());
            shopCarDto.setItems(shopCarItemsDaos);
            shopCarDto.setSellerId(shops.getUserId());
            shopCarDtos.add(shopCarDto);

//            ShopCarDto shopCarDto=new ShopCarDto();
//            BeanUtils.copyProperties(i,shopCarDto);
//            shopCarDto.setItems(shopCarItems);
           // shopCarDtos.add(shopCarDto);
        });
        return shopCarDtos;
    }
    public Result deleteAll(Long userId){
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        List<ShopCar> shopCar=shopCarRepository.findByUserId(userId);
        shopCar.stream().forEach(i->{
            List<ShopCarItems> shopCarItems=shopCarItemsRepository.findByShopCarId(i.getId());
            shopCarItemsRepository.deleteAll(shopCarItems);
        });
        shopCarRepository.deleteAll(shopCar);
        return Result.successResult();
    }
}
