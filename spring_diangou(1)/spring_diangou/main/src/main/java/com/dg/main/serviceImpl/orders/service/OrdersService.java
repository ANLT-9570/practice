package com.dg.main.serviceImpl.orders.service;


import com.dg.main.Entity.*;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.shop.Size;
import com.dg.main.Entity.shop.Specifications;
import com.dg.main.dao.orders.OrdersMapper;
import com.dg.main.dto.orders.OrderItemsDto;
import com.dg.main.dto.orders.OrdersDto;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.shop.SizeRepository;
import com.dg.main.repository.users.CustomerAddressRepository;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.orders.OrdersRepository;

import com.dg.main.service.SpecificationsServer;

import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.dg.main.serviceImpl.orders.wrapper.OrdersWrapper;
import com.dg.main.util.Result;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdersService {
    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    CustomerAddressRepository repository;

    @Autowired
    SpecificationsServer specificationsServer;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderItemsRepository orderItemsRepository;
    @Autowired
    ShopsRepository shopsRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Transactional
    public void saveOrders(OrdersWrapper ordersWrapper){
        Orders orders=ordersWrapper.getOrders();
        ordersRepository.save(orders);
        for(OrderItems items:ordersWrapper.getItems()){
            items.setOrdersId(orders.getId());
            items.setGroupByField(items.getShopId()+"_"+orders.getOrderCode());

        }
        orderItemsRepository.saveAll(ordersWrapper.getItems());
    }
    public OrdersDto findBy(Long id) {
        Orders orders=ordersRepository.getOne(id);
        List<OrderItems> orderItems=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(),2);
        OrdersDto item=new OrdersDto();
        BeanUtils.copyProperties(orders,item);
        List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
        for (OrderItems items:orderItems){
            OrderItemsDto orderItemsDto=new OrderItemsDto();
            BeanUtils.copyProperties(items,orderItemsDto);
            Specifications specifications=specificationsServer.findById(items.getSpecificationId());
            orderItemsDto.setSpecificationName(specifications.getName());
            if (items.getSizeId()!=null){
                Size size=sizeRepository.getOne(items.getSizeId());
                orderItemsDto.setDescribe("颜色："+size.getColour()+"大小："+size.getDimensions());
            }
            orderItemsDtos.add(orderItemsDto);
        }
      //  item.setShopName(shopsRepository.getOne(orders.getShopId()).getName());
        item.setItems(orderItemsDtos);
        return item;
    }
    public OrdersDto findByCode(String code){
        Orders orders=ordersRepository.findByOrderCode(code);
        List<OrderItems> orderItems=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(),2);
        OrdersDto item=new OrdersDto();
        BeanUtils.copyProperties(orders,item);
        List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
        for (OrderItems items:orderItems){
            OrderItemsDto orderItemsDto=new OrderItemsDto();
            BeanUtils.copyProperties(items,orderItemsDto);
            Specifications specifications=specificationsServer.findById(items.getSpecificationId());
            orderItemsDto.setSpecificationName(specifications.getName());
            if (items.getSizeId()!=null){
                Size size=sizeRepository.getOne(items.getSizeId());
                orderItemsDto.setDescribe("颜色："+size.getColour()+"大小："+size.getDimensions());
            }
            orderItemsDtos.add(orderItemsDto);
        }
        //  item.setShopName(shopsRepository.getOne(orders.getShopId()).getName());
        item.setItems(orderItemsDtos);
        return item;
    }
    public Orders findByCodeV1(String code){
        Orders orders=ordersRepository.findByOrderCode(code);
        List<OrderItems> orderItems=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(),2);
        OrdersDto item=new OrdersDto();
        BeanUtils.copyProperties(orders,item);
        List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
        for (OrderItems items:orderItems){
            OrderItemsDto orderItemsDto=new OrderItemsDto();
            BeanUtils.copyProperties(items,orderItemsDto);
            Specifications specifications=specificationsServer.findById(items.getSpecificationId());
            orderItemsDto.setSpecificationName(specifications.getName());
            if (items.getSizeId()!=null){
                Size size=sizeRepository.getOne(items.getSizeId());
                orderItemsDto.setDescribe("颜色："+size.getColour()+"大小："+size.getDimensions());
            }
            orderItemsDtos.add(orderItemsDto);
        }
        //  item.setShopName(shopsRepository.getOne(orders.getShopId()).getName());
        item.setItems(orderItemsDtos);
        return orders;
    }
    public List<OrderUpdateWrapper> findByDirectCode(String code){
        List<OrderUpdateWrapper> wrappers=Lists.newArrayList();
        List<Orders> ordersList=ordersRepository.findByDirectCodeAndPhase(code,1);
        for (Orders orders:ordersList){
            List<OrderItems> _old=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(),2);
            List<OrderItems> _new=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(),2);
            OrderUpdateWrapper wrapper=new OrderUpdateWrapper(_new,_old,orders);
            wrappers.add(wrapper);
        }
        return wrappers;
    }
    public Orders findByCodeV2(Long id){
        return ordersRepository.getOne(id);
    }
    public Boolean isBuyed(Integer userId,Long specificationId){
//        if(ordersMapper.findByUserIdAndSpecificationId(userId,specificationId).size()>0){
//            return true;
//        }
        return false;
    }
//    @Transactional
//    public void fakeDeletedById(Long id){
//      //  ordersMapper.fakeDeletedById(id);
//        Orders orders=ordersMapper
//    }
    public void fakeDeleteOrderItem(Long id){
        OrderItems orderItems=orderItemsRepository.getOne(id);
        orderItems.setIsDeleted(1);
        orderItemsRepository.save(orderItems);

    }
    @Transactional
    public Result getUserUnPayedOrderList(Long  userId,Integer offset,Integer limit){
     //   List<OrderItems> orderItems=orderItemsRepository.customerUnpayList(userId,offset,limit);
        List<OrdersDto> ordersDtos=Lists.newArrayList();

        List<Orders> ordersList=ordersRepository.customerUnpayList(userId, offset, limit);
        for (Orders orders:ordersList){
            OrdersDto ordersDto=new OrdersDto();
            BeanUtils.copyProperties(orders,ordersDto);
            List<OrderItems> orderItems1=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
            for (OrderItems items:orderItems1){
                Specifications specifications=specificationsServer.findById(items.getSpecificationId());
                OrderItemsDto orderItemsDto=new OrderItemsDto();
                BeanUtils.copyProperties(items,orderItemsDto);
                orderItemsDto.setSpecificationName(specifications.getName());
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
            List<OrderItems> orderItems1=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
            for (OrderItems items:orderItems1){
                Specifications specifications=specificationsServer.findById(items.getSpecificationId());
                OrderItemsDto orderItemsDto=new OrderItemsDto();
                BeanUtils.copyProperties(items,orderItemsDto);
                orderItemsDto.setSpecificationName(specifications.getName());
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
            List<OrderItems> orderItems1=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
            for (OrderItems items:orderItems1){
                Specifications specifications=specificationsServer.findById(items.getSpecificationId());
                OrderItemsDto orderItemsDto=new OrderItemsDto();
                BeanUtils.copyProperties(items,orderItemsDto);
                orderItemsDto.setSpecificationName(specifications.getName());
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
    public void fakeDeleteOrder(Long id){
        Orders orders=ordersRepository.getOne(id);
        List<OrderItems> orderItems=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(),2);
       orderItems.stream().map(i->{
           i.setIsDeleted(1);
           return i;
       }).forEach(i->{
           orderItemsRepository.save(i);
       });
        orders.setIsDelete(1);
        ordersRepository.save(orders);
    }
    @Transactional
    public Result getUserUncommentOrders(Long userId,Integer offset,Integer limit){
        List<OrdersDto> ordersDtos=Lists.newArrayList();

        List<Orders> ordersList=ordersRepository.unCommentList(userId, offset, limit);
        for (Orders orders:ordersList){
            OrdersDto ordersDto=new OrdersDto();
            BeanUtils.copyProperties(orders,ordersDto);
            List<OrderItems> orderItems1=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
            for (OrderItems items:orderItems1){
                Specifications specifications=specificationsServer.findById(items.getSpecificationId());
                OrderItemsDto orderItemsDto=new OrderItemsDto();
                BeanUtils.copyProperties(items,orderItemsDto);
                orderItemsDto.setSpecificationName(specifications.getName());
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
            List<OrderItems> orderItems1=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(), 2);
            List<OrderItemsDto> orderItemsDtos=Lists.newArrayList();
            for (OrderItems items:orderItems1){
                Specifications specifications=specificationsServer.findById(items.getSpecificationId());
                OrderItemsDto orderItemsDto=new OrderItemsDto();
                BeanUtils.copyProperties(items,orderItemsDto);
                orderItemsDto.setSpecificationName(specifications.getName());
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
    public List<OrdersDto> getBusinessAllOrders(Long userId,Pageable pageable){
    //    List<Orders> orders=ordersRepository.findByBusinessId(userId,pageable);
        final List<OrdersDto> temp= Lists.newArrayList();
//        orders.stream().forEach(i->{
//            List<OrderItems> orderItems=orderItemsRepository.findByOrdersIdAndIsDeleted(i.getId(),2);
//            OrdersDto ordersVo=new OrdersDto();
//            BeanUtils.copyProperties(i,ordersVo);
//           // ordersVo.setShopName(shopsRepository.getOne(i.getShopId()).getName());
//            ordersVo.setItems(orderItems);
//            temp.add(ordersVo);
//        });
        return temp;
    }
    @Transactional
    public void fakeDeletedByCode(List<String> code){
       for(String i:code){
           OrderItems orderItems=orderItemsRepository.findByItemCode(i);
           orderItems.setIsDeleted(1);
           orderItemsRepository.save(orderItems);
       }
//        List<OrderItems> orderItems=orderItemsRepository.findByOrdersIdAndIsDeleted(orders.getId(),2);
//        orderItems.stream().map(i->{
//            i.setIsDeleted(1);
//            return i;
//        }).forEach(i->{
//            orderItemsRepository.save(i);
//        });
//        orders.setIsDelete(1);
//        ordersRepository.save(orders);
    }
//    public List<Orders> selectByUniteCode(String code){
//        return ordersMapper.listByUniteCode(code);
//    }
    public List<OrdersDto> customerList(Integer userId,Integer type){
        List<OrdersDto> ordersDtos=new ArrayList<>();
//        List<Orders> orders=new ArrayList<>();
//        if(type==1){//未付款
//            orders= ordersMapper.customerUnpayList(userId);
//        }else if(type==2){//已付款
//            orders= ordersMapper.CustomerPayedList(userId);
//        }else if(type==3){//付款中
//            orders= ordersMapper.CustomerSendingList(userId);
//        }else if(type==10){//完成
//            orders= ordersMapper.CustomerCompletedList(userId);
//        }else if(type==11){
//            orders= ordersMapper.CustomerRefundList(userId);
//        }
//        for(Orders item:orders){
//            OrdersDto ordersDto=new OrdersDto();
//            Specifications specifications=specificationsServer.findById(item.getSpecificationsId());
//            ShoppingCart shoppingCart=shoppingCartServer.findBy(item.getShopCarId());
//            CustomerAddress customerAddress=repository.getOne(item.getAddressId());
//            Shop shop=shopServer.findBy(item.getShopId());
//
//            BeanUtils.copyProperties(item,ordersDto);
//            Long money=item.getMoney();
//            money=money/100;
//            ordersDto.setMoney(money+"");
//            ordersDto.setSpecificationsName(specifications.getName());
//            if(shop==null) {
//                ordersDto.setShopName("");
//
//            }else{
//                ordersDto.setShopName(shop.getShopName() == null ? "" : shop.getShopName());
//            }
//            if(customerAddress==null){
//                ordersDto.setAddressName("");
//
//            }else{
//                ordersDto.setAddressName(customerAddress.getName());
//            }
//            if(shoppingCart==null){
//                ordersDto.setShopCarName("");
//
//            }else{
//                ordersDto.setShopCarName(shoppingCart.getShopName());
//            }
//
//
//            ordersDtos.add(ordersDto);
//        }
        return ordersDtos;
    }
    public List<OrdersDto> businessList(Integer userId, Integer type){
        List<OrdersDto> ordersDtos=new ArrayList<>();
//        List<Orders> orders=new ArrayList<>();
//        if(type==1){
//            orders= ordersMapper.BusinessUnpayList(userId);
//        }else if(type==2){
//            orders= ordersMapper.BusinessPayedList(userId);
//        }else if(type==3){
//            orders= ordersMapper.BusinessSendingList(userId);
//        }else if(type==10){
//            orders= ordersMapper.BusinessCompletedList(userId);
//        }else if(type==11){
//            orders= ordersMapper.BusinessRefundList(userId);
//        }
//        for(Orders item:orders){
//            OrdersDto ordersDto=new OrdersDto();
//            Specifications specifications=specificationsServer.findById(item.getSpecificationsId());
//            ShoppingCart shoppingCart=shoppingCartServer.findBy(item.getShopCarId());
//            CustomerAddress customerAddress=repository.getOne(item.getAddressId());
//            Shop shop=shopServer.findBy(item.getShopId());
//
//            BeanUtils.copyProperties(item,ordersDto);
//            Long money=item.getMoney();
//            money=money/100;
//            ordersDto.setMoney(money+"");
//            ordersDto.setSpecificationsName(specifications.getName());
//            if(shop==null) {
//                ordersDto.setShopName("");
//
//            }else{
//                ordersDto.setShopName(shop.getShopName() == null ? "" : shop.getShopName());
//            }
//            if(customerAddress==null){
//                ordersDto.setAddressName("");
//
//            }else{
//                ordersDto.setAddressName(customerAddress.getName());
//            }
//            if(shoppingCart==null){
//                ordersDto.setShopCarName("");
//
//            }else{
//                ordersDto.setShopCarName(shoppingCart.getShopName());
//            }
//
//
//            ordersDtos.add(ordersDto);
//        }
        return ordersDtos;
    }
    public void deleteBy(Long id){
        ordersMapper.deleteBy(id);
    }

    @Transactional
    public void update(Orders t) {

       // return ordersMapper.update(t);
        ordersRepository.save(t);
    }

    @Transactional
    public void save(Orders t) {

       // return ordersMapper.save(t);
        ordersRepository.save(t);
    }



    public List<Orders> selectAll(Orders t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页

    public slzcResult selectAll(int offset, int limit) {

        slzcResult result = new slzcResult();

        int count = ordersMapper.selectCount();

//		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
        List<Orders> businesses = ordersMapper.selectPageAll( offset,limit);

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


    public List<Orders> selectPageAll(int offset, int limit) {

        return null;
    }


    public int deleteAllId(String [] t) {

        return ordersMapper.deleteAllId(t);
    }





}
