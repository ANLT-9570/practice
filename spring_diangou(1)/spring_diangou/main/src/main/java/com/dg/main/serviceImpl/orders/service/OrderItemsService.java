package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.users.CustomerAddress;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.dto.orders.OrderCommentDto;
import com.dg.main.dto.orders.OrderInfoDto;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.users.CustomerAddressRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.util.Result;
import org.apache.commons.compress.utils.Lists;
import org.bouncycastle.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderItemsService {
    @Autowired
    OrderItemsRepository repository;
    @Autowired
    CustomerAddressRepository customerAddressRepository;
    @Autowired
  MobileUserRepository mobileUserRepository;
  public   List<OrderItems> findByOrderId(Long id){
        return repository.findByOrdersIdAndIsDeleted(id,2);
    }
    public void updateBatch(List<OrderItems> orderItems){
      repository.saveAll(orderItems);
    }
    public OrderItems findByItemCode(String code){
      return repository.findByItemCode(code);
    }
    @Transactional
    public Result getInfo(String code){
      OrderItems orderItems=repository.findByItemCode(code);
        OrderInfoDto orderInfoDto=new OrderInfoDto();
        CustomerAddress customerAddress=customerAddressRepository.getOne(orderItems.getAddressId());
        BeanUtils.copyProperties(customerAddress,orderInfoDto);
        orderInfoDto.setLogisticsCode(orderItems.getLogisticsCode());
        orderInfoDto.setLogisticsStatus(orderItems.getLogisticsStatus());
        orderInfoDto.setLogisticsType(orderItems.getLogisticsType());

        return Result.successResult(orderInfoDto);
    }
    public void update(OrderItems item){
      repository.save(item);
    }
    @Transactional
    public Result addRank(String code,String info,Integer rank){
      OrderItems orderItems=repository.findByItemCode(code);
//      orderItems.setRanks(rank);
//      orderItems.setComment(info);
      repository.save(orderItems);
      return Result.successResult();
    }
    @Transactional
    public Result commentList(Long id){
//          List<OrderItems> orderItems=repository.findBySpecificationId(id);
//          List<OrderCommentDto> orderCommentDtos= Lists.newArrayList();
//          for(OrderItems item:orderItems){
//              OrderCommentDto orderCommentDto=new OrderCommentDto();
//              BeanUtils.copyProperties(item,orderCommentDto);
//            MobileUser mobileUser=mobileUserRepository.getOne(item.getUserId());
//            orderCommentDto.setUsername(mobileUser.getName());
//            orderCommentDtos.add(orderCommentDto);
//            List<String> thumb= Arrays.asList(Strings.split(item.getCommentThumbImg(), ','));
//            List<String> origin=Arrays.asList(Strings.split(item.getCommentOriginalImg(), ','));
//            orderCommentDto.setCommentOriginalImg(origin);
//            orderCommentDto.setCommentThumbImg(thumb);
//
//          }
          return Result.successResult();
    }
}
