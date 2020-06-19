package com.dg.main.serviceImpl.orders.service.seller;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.shop.Size;
import com.dg.main.Entity.shop.Specifications;
import com.dg.main.dto.orders.OrderItemsDto;
import com.dg.main.dto.orders.OrdersDto;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.SpecificationsRepository;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.orders.OrdersRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.shop.SizeRepository;
import com.dg.main.repository.users.UserRealNameRepository;
import com.dg.main.service.SpecificationsServer;
import com.dg.main.util.Result;
import com.dg.main.vo.WaitForReceivingVo;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SellerOrdersService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private UserRealNameRepository userRealNameRepository;

    @Autowired
    ShopsRepository shopsRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    SpecificationsRepository specificationsRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Transactional
    public Result findRefundOrdersByShopId(Long  shopId,Integer offset,Integer limit){
        List<OrdersDto> ordersDtos=Lists.newArrayList();

        List<Orders> ordersList=ordersRepository.businessRefundListByShopId(shopId, offset, limit);
        for (Orders orders:ordersList) {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(orders, ordersDto);
            if (orders.getShopId()!=null){
                Shops shops=shopsRepository.getOne(orders.getShopId());
                ordersDto.setShopName(shops.getName());
            }
            List<OrderItems> orderItems1 = orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos = Lists.newArrayList();
            for (OrderItems items : orderItems1) {

                OrderItemsDto orderItemsDto = new OrderItemsDto();
                BeanUtils.copyProperties(items, orderItemsDto);
                if(items.getSpecificationId()!=null) {
                    Specifications specifications = specificationsRepository.getOne(items.getSpecificationId());
                    orderItemsDto.setSpecificationName(specifications.getName());
                }
                if (items.getSizeId() != null) {
                    Size size = sizeRepository.getOne(items.getSizeId());
                    orderItemsDto.setDescribe("颜色：" + size.getColour() + "大小：" + size.getDimensions());
                }
                orderItemsDtos.add(orderItemsDto);
            }
            ordersDto.setItems(orderItemsDtos);
            ordersDtos.add(ordersDto);
        }
            return Result.successResult(ordersDtos);
    }


    public Result findUnsendOrdersByShopId(Long shopId,Integer offset,Integer limit) {
        List<OrdersDto> ordersDtos=Lists.newArrayList();

        List<Orders> ordersList=ordersRepository.businessUnsendListByShopId(shopId, offset, limit);
        for (Orders orders:ordersList) {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(orders, ordersDto);
            if (orders.getShopId()!=null){
                Shops shops=shopsRepository.getOne(orders.getShopId());
                ordersDto.setShopName(shops.getName());
            }
            List<OrderItems> orderItems1 = orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos = Lists.newArrayList();
            for (OrderItems items : orderItems1) {

                OrderItemsDto orderItemsDto = new OrderItemsDto();
                BeanUtils.copyProperties(items, orderItemsDto);
                if(items.getSpecificationId()!=null) {
                    Specifications specifications = specificationsRepository.getOne(items.getSpecificationId());
                    orderItemsDto.setSpecificationName(specifications.getName());
                }
                if (items.getSizeId() != null) {
                    Size size = sizeRepository.getOne(items.getSizeId());
                    orderItemsDto.setDescribe("颜色：" + size.getColour() + "大小：" + size.getDimensions());
                }
                orderItemsDtos.add(orderItemsDto);
            }
            ordersDto.setItems(orderItemsDtos);
            ordersDtos.add(ordersDto);
        }
        return Result.successResult(ordersDtos);


    }
    @Transactional
    public Result findUnpayOrdersByShopId(Long shopId,Integer offset,Integer limit) {
        List<OrdersDto> ordersDtos=Lists.newArrayList();

        List<Orders> ordersList=ordersRepository.businessUnpayListByShopId(shopId, offset, limit);
        for (Orders orders:ordersList) {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(orders, ordersDto);
            if (orders.getShopId()!=null){
                Shops shops=shopsRepository.getOne(orders.getShopId());
                ordersDto.setShopName(shops.getName());
            }
            List<OrderItems> orderItems1 = orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos = Lists.newArrayList();
            for (OrderItems items : orderItems1) {

                OrderItemsDto orderItemsDto = new OrderItemsDto();
                BeanUtils.copyProperties(items, orderItemsDto);
                if(items.getSpecificationId()!=null) {
                    Specifications specifications = specificationsRepository.getOne(items.getSpecificationId());
                    orderItemsDto.setSpecificationName(specifications.getName());
                }
                if (items.getSizeId() != null) {
                    Size size = sizeRepository.getOne(items.getSizeId());
                    orderItemsDto.setDescribe("颜色：" + size.getColour() + "大小：" + size.getDimensions());
                }
                orderItemsDtos.add(orderItemsDto);
            }
            ordersDto.setItems(orderItemsDtos);
            ordersDtos.add(ordersDto);
        }
        return Result.successResult(ordersDtos);
    }

    public Result findUndeliveryOrdersByShopId(Long shopId,Integer offset,Integer limit) {
        List<OrdersDto> ordersDtos=Lists.newArrayList();

        List<Orders> ordersList=ordersRepository.businessUndeliveryListByShopId(shopId, offset, limit);
        for (Orders orders:ordersList) {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(orders, ordersDto);
            if (orders.getShopId()!=null){
                Shops shops=shopsRepository.getOne(orders.getShopId());
                ordersDto.setShopName(shops.getName());
            }
            List<OrderItems> orderItems1 = orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos = Lists.newArrayList();
            for (OrderItems items : orderItems1) {

                OrderItemsDto orderItemsDto = new OrderItemsDto();
                BeanUtils.copyProperties(items, orderItemsDto);
                if(items.getSpecificationId()!=null) {
                    Specifications specifications = specificationsRepository.getOne(items.getSpecificationId());
                    orderItemsDto.setSpecificationName(specifications.getName());
                }

                if (items.getSizeId() != null) {
                    Size size = sizeRepository.getOne(items.getSizeId());
                    orderItemsDto.setDescribe("颜色：" + size.getColour() + "大小：" + size.getDimensions());
                }
                orderItemsDtos.add(orderItemsDto);
            }
            ordersDto.setItems(orderItemsDtos);
            ordersDtos.add(ordersDto);
        }
        return Result.successResult(ordersDtos);
    }



}
