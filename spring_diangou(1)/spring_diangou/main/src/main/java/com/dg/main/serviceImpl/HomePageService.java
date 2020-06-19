package com.dg.main.serviceImpl;

import com.dg.main.Entity.shop.Category;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.shop.Specifications;
import com.dg.main.repository.shop.CategoryRepository;
import com.dg.main.repository.RedPackRepository;
import com.dg.main.repository.SpecificationsRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.util.Result;
import com.dg.main.vo.ShopsVo;
import com.dg.main.vo.SpecificationsVo;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HomePageService {

    @Autowired
    private RedPackRepository redPackRepository;
    @Autowired
    private SpecificationsRepository specificationsRepository;
    @Autowired
    private ShopsRepository shopsRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    public Result random(Integer offset, Integer limit) {
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        List<Specifications> specifications = specificationsRepository.findAllRand(offset,limit);
        return Result.successResult(specifications);
    }

    public Result GoodStuffRandom(Integer offset, Integer limit, String name, String city) {
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(city)){
            List<Long> shopsIds = shopsRepository.findByCity("%"+city+"%",offset,limit);
            if(shopsIds != null){
                List<Specifications> specifications = specificationsRepository.findByShopIdAndShopName("%"+name+"%",shopsIds);
                return Result.successResult(specifications);
            }
            return Result.successResult();
        }
        if(StringUtils.isNotBlank(name)){
            List<Specifications> specifications = specificationsRepository.findByName("%"+name+"%",offset,limit);
            return Result.successResult(specifications);
        }
        if (StringUtils.isNotBlank(city)){
            List<Long> userIds = shopsRepository.findByCity("%"+city+"%",offset,limit);
            if(userIds != null){
                List<Specifications> specifications = specificationsRepository.findByShopIdList(userIds);
                return Result.successResult(specifications);
            }
            return Result.successResult();
        }
        List<Specifications> specifications = specificationsRepository.findAllRand(offset, limit);
        return Result.successResult(specifications);
    }

    public Result GoodThingRandom(Integer offset, Integer limit, String name, String city) {
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        if( StringUtils.isNotBlank(name) && StringUtils.isNotBlank(city)){
            Category category = categoryRepository.findByName(name);
            if(category!=null){
                List<Shops> shops = shopsRepository.findByCategoryIdAndCity(category.getId(),"%"+city+"%",offset,limit);
                return Result.successResult(shops);
            }
            return Result.successResult();
        }
        if(StringUtils.isNotBlank(name)){
            Category category = categoryRepository.findByName(name);
            if(category!=null){
                List<Shops> shops = shopsRepository.findByCategoryId(category.getId(),offset,limit);
                return Result.successResult(shops);
            }
            return Result.successResult();
        }
        if(StringUtils.isNotBlank(city)){
            List<Shops> shops = shopsRepository.findByCityAll("%"+city+"%", offset, limit);
            return Result.successResult(shops);
        }
        List<Shops> shops = shopsRepository.findAllRand(offset, limit);
        return Result.successResult(shops);
    }

    public Result searchList(Integer offset, Integer limit, String condition) {
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        List<Specifications> specifications = Lists.newArrayList();
        if(condition != null){
            specifications = specificationsRepository.findByName("%" + condition + "%", offset, limit);
        }else{
            specifications = specificationsRepository.findAll(offset,limit);
        }
        List<SpecificationsVo> specificationsVos = Lists.newArrayList();
        for (Specifications specification : specifications) {
            SpecificationsVo specificationsVo = new SpecificationsVo();

            Optional<Shops> optional = shopsRepository.findById(specification.getId());
            BeanUtils.copyProperties(specification,specificationsVo);
            specificationsVo.setStoreName(optional.get().getName());

            specificationsVos.add(specificationsVo);
        }
        return Result.successResult(specificationsVos);
    }

    public Result show(Integer offset, Integer limit,String times) {
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        if(times == null){
            Date date = new Date();
            //2019-12-24 03:05:40
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            times = format.format(date);
        }

        //查询所有的店铺
        List<Shops> list = shopsRepository.findAll(offset,limit);
        List<ShopsVo> shopsVos = Lists.newArrayList();
        for (Shops shops : list) {
            ShopsVo shopsVo = new ShopsVo();
            BeanUtils.copyProperties(shops,shopsVo);
            Long money = redPackRepository.findByShopIdAndSum(shops.getId(),"%"+times+"%");
            if(money == null){
                money = 0L;
            }
            shopsVo.setMoney(money);
            shopsVos.add(shopsVo);
        }
        Collections.sort(shopsVos, new Comparator<ShopsVo>() {
            @Override
            public int compare(ShopsVo o1, ShopsVo o2) {
                if(o1.getMoney() != null && o2.getMoney() != null){
                    if(o1.getMoney() > o2.getMoney()){
                        return 1;
                    }
                }
                return -1;
            }
        });
        Collections.reverse(shopsVos);
        return Result.successResult(shopsVos);
    }

    public Result citySort(String city, Integer offset, Integer limit) {
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        //这个地区的所有商铺
        List<Shops> shops = shopsRepository.findByCityAll("%"+city+"%", offset, limit);
        List<ShopsVo> lisShops = getLisShops(shops);
        return Result.successResult(lisShops);
    }

    public List<ShopsVo> getLisShops(List<Shops> shops){
        List<ShopsVo> shopsVos = Lists.newArrayList();
        for (Shops shop : shops) {
            ShopsVo shopsVo = new ShopsVo();
            BeanUtils.copyProperties(shop,shopsVo);
            //店铺总共发了多少红包
            Long money = redPackRepository.findByShopIdAndSum(shop.getId());
            if(money==null){
                shopsVo.setMoney(0L);
            }else {
                shopsVo.setMoney(money);
            }

            shopsVos.add(shopsVo);
        }
        Collections.sort(shopsVos, new Comparator<ShopsVo>() {
            @Override
            public int compare(ShopsVo o1, ShopsVo o2) {
                if(o1.getMoney()<o2.getMoney()){
                    return 1;
                }
                return -1;
            }
        });
        return shopsVos;
    }

    public Result nobleV1(Integer offset, Integer limit) {
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        List<Shops> shops = shopsRepository.findAllRand(offset, limit);
        return Result.successResult(shops);
    }
    public Result nobleV2(Integer offset, Integer limit) {
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        List<Shops> shops = shopsRepository.findAllRand(offset, limit);
        return Result.successResult(shops);
    }

    public Result GoodStuffBigV1(Integer offset, Integer limit) {
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        List<Specifications> list = specificationsRepository.findAllRand(offset, limit);
        return Result.successResult(list);
    }
    public Result GoodStuffBigV2(Integer offset, Integer limit) {
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        List<Specifications> list = specificationsRepository.findAllRand(offset, limit);
        return Result.successResult(list);
    }
}
