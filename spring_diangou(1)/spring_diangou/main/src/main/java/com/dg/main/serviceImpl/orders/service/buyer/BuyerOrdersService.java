package com.dg.main.serviceImpl.orders.service.buyer;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.shop.Size;
import com.dg.main.Entity.shop.Specifications;
import com.dg.main.dto.orders.OrderItemsDto;
import com.dg.main.dto.orders.OrdersDto;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.SpecificationsRepository;
import com.dg.main.repository.log.OrderItemsLogRepository;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.orders.OrdersRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.shop.SizeRepository;
import com.dg.main.service.SpecificationsServer;
import com.dg.main.serviceImpl.logs.OrderItemsLogService;
import com.dg.main.util.Result;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BuyerOrdersService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    ShopsRepository shopsRepository;

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderItemsLogRepository orderItemsLogRepository;

    @Autowired
    SpecificationsRepository specificationsRepository;
    @Autowired
    SizeRepository sizeRepository;

    public Result fakeDelete(String code){
        Orders orders=ordersRepository.findByOrderCode(code);
        if(orders==null){
            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
        }
        List<OrderItems> items=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
        for(OrderItems items1:items){
            items1.setIsDeleted(1);
        }
        orderItemsRepository.saveAll(items);
        return Result.successResult();
    }

    @Transactional
    public Result getUserUnPayedOrderList(Long  userId,Integer offset,Integer limit){
        //   List<OrderItems> orderItems=orderItemsRepository.customerUnpayList(userId,offset,limit);
        List<OrdersDto> ordersDtos=Lists.newArrayList();

        List<Orders> ordersList=ordersRepository.customerUnpayList(userId, offset, limit);
        for (Orders orders:ordersList){
            OrdersDto ordersDto=new OrdersDto();
            BeanUtils.copyProperties(orders,ordersDto);
            if (orders.getShopId()!=null){
                Shops shops=shopsRepository.getOne(orders.getShopId());
                ordersDto.setShopName(shops.getName());
            }
            List<OrderItems> orderItems1=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
            for (OrderItems items:orderItems1){

                OrderItemsDto orderItemsDto=new OrderItemsDto();
                BeanUtils.copyProperties(items,orderItemsDto);

                if(items.getSpecificationId()!=null) {
                    Specifications specifications = specificationsRepository.getOne(items.getSpecificationId());
                    orderItemsDto.setSpecificationName(specifications.getName());
                }
                if (items.getSizeId()!=null){
                    Size size=sizeRepository.getOne(items.getSizeId());
                    orderItemsDto.setDescribe("颜色："+size.getColour()+"大小："+size.getDimensions());
                }
                orderItemsDtos.add(orderItemsDto);
            }
            ordersDto.setItems(orderItemsDtos);
            ordersDtos.add(ordersDto);
        }



        return Result.successResult(ordersDtos);
    }
    @Transactional
    public Result getUserUnsendOrderList(Long  userId,Integer offset,Integer limit){
        List<OrdersDto> ordersDtos=Lists.newArrayList();

        List<Orders> ordersList=ordersRepository.customerUnsendList(userId, offset, limit);
        for (Orders orders:ordersList){
            OrdersDto ordersDto=new OrdersDto();
            BeanUtils.copyProperties(orders,ordersDto);
            if (orders.getShopId()!=null){
                Shops shops=shopsRepository.getOne(orders.getShopId());
                ordersDto.setShopName(shops.getName());
            }
            ordersDto.setPrice(orders.getTotalMoney());
            List<OrderItems> orderItems1=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
            for (OrderItems items:orderItems1){

                OrderItemsDto orderItemsDto=new OrderItemsDto();
                BeanUtils.copyProperties(items,orderItemsDto);

                if(items.getSpecificationId()!=null) {
                    Specifications specifications = specificationsRepository.getOne(items.getSpecificationId());
                    orderItemsDto.setSpecificationName(specifications.getName());
                }
                    if (items.getSizeId()!=null){
                    Size size=sizeRepository.getOne(items.getSizeId());
                    orderItemsDto.setDescribe("颜色："+size.getColour()+"大小："+size.getDimensions());
                }
                orderItemsDtos.add(orderItemsDto);
            }
            ordersDto.setItems(orderItemsDtos);
            ordersDtos.add(ordersDto);
        }



        return Result.successResult(ordersDtos);
    }
    @Transactional
    public Result getUserUndeliveryOrderList(Long  userId,Integer offset,Integer limit){
        List<OrdersDto> ordersDtos=Lists.newArrayList();

        List<Orders> ordersList=ordersRepository.customerUndeliveryList(userId, offset, limit);
        for (Orders orders:ordersList){
            OrdersDto ordersDto=new OrdersDto();
            BeanUtils.copyProperties(orders,ordersDto);
            if (orders.getShopId()!=null){
                Shops shops=shopsRepository.getOne(orders.getShopId());
                ordersDto.setShopName(shops.getName());
            }
            List<OrderItems> orderItems1=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
            for (OrderItems items:orderItems1){

                OrderItemsDto orderItemsDto=new OrderItemsDto();
                BeanUtils.copyProperties(items,orderItemsDto);
                if(items.getSpecificationId()!=null) {
                    Specifications specifications = specificationsRepository.getOne(items.getSpecificationId());
                    orderItemsDto.setSpecificationName(specifications.getName());
                }
                if (items.getSizeId()!=null){
                    Size size=sizeRepository.getOne(items.getSizeId());
                    orderItemsDto.setDescribe("颜色："+size.getColour()+"大小："+size.getDimensions());
                }
                orderItemsDtos.add(orderItemsDto);
            }
            ordersDto.setItems(orderItemsDtos);
            ordersDtos.add(ordersDto);
        }



        return Result.successResult(ordersDtos);
    }
    @Transactional
    public Result getUserUncommentOrders(Long userId,Integer offset,Integer limit){
        List<OrdersDto> ordersDtos=Lists.newArrayList();

        List<Orders> ordersList=ordersRepository.unCommentList(userId, offset, limit);
        for (Orders orders:ordersList){
            OrdersDto ordersDto=new OrdersDto();
            BeanUtils.copyProperties(orders,ordersDto);
            if (orders.getShopId()!=null){
                Shops shops=shopsRepository.getOne(orders.getShopId());
                ordersDto.setShopName(shops.getName());
            }
            List<OrderItems> orderItems1=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
            for (OrderItems items:orderItems1){

                OrderItemsDto orderItemsDto=new OrderItemsDto();
                BeanUtils.copyProperties(items,orderItemsDto);
                if(items.getSpecificationId()!=null) {
                    Specifications specifications = specificationsRepository.getOne(items.getSpecificationId());
                    orderItemsDto.setSpecificationName(specifications.getName());
                }
                if (items.getSizeId()!=null){
                    Size size=sizeRepository.getOne(items.getSizeId());
                    orderItemsDto.setDescribe("颜色："+size.getColour()+"大小："+size.getDimensions());
                }
                orderItemsDtos.add(orderItemsDto);
            }
            ordersDto.setItems(orderItemsDtos);
            ordersDtos.add(ordersDto);
        }



        return Result.successResult(ordersDtos);
    }
    @Transactional
    public Result getUserAllOrders(Long userId, Pageable pageable){
        List<OrdersDto> ordersDtos=Lists.newArrayList();

        List<Orders> ordersList=ordersRepository.findByCustomerId(userId, pageable);
        for (Orders orders:ordersList){
            OrdersDto ordersDto=new OrdersDto();
            BeanUtils.copyProperties(orders,ordersDto);
            if (orders.getShopId()!=null){
                Shops shops=shopsRepository.getOne(orders.getShopId());
                ordersDto.setShopName(shops.getName());
            }
            List<OrderItems> orderItems1=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
            for (OrderItems items:orderItems1){

                OrderItemsDto orderItemsDto=new OrderItemsDto();
                BeanUtils.copyProperties(items,orderItemsDto);
                if(items.getSpecificationId()!=null) {
                    Specifications specifications = specificationsRepository.getOne(items.getSpecificationId());
                    orderItemsDto.setSpecificationName(specifications.getName());
                }
                if (items.getSizeId()!=null){
                    Size size=sizeRepository.getOne(items.getSizeId());
                    orderItemsDto.setDescribe("颜色："+size.getColour()+"大小："+size.getDimensions());
                }
                orderItemsDtos.add(orderItemsDto);
            }
            ordersDto.setItems(orderItemsDtos);
            ordersDtos.add(ordersDto);
        }



        return Result.successResult(ordersDtos);
    }
}
