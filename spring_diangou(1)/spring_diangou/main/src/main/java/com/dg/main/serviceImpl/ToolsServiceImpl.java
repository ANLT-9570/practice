package com.dg.main.serviceImpl;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.shop.Specifications;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.RedPackRepository;
import com.dg.main.repository.SpecificationsRepository;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.service.ToolsService;
import com.dg.main.util.Result;
import com.dg.main.vo.ShopsVo;
import com.dg.main.vo.SpecificationsVo;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ToolsServiceImpl implements ToolsService {

    @Autowired
    private ShopsRepository shopsRepository;
    @Autowired
    private RedPackRepository redPackRepository;
    @Autowired
    private HomePageService homePageService;
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private SpecificationsRepository specificationsRepository;

    @Override
    public Result shopUproar(Integer offset,Integer limit) {
        List<Shops> list = shopsRepository.findAll(offset, limit);
        List<ShopsVo> lisShops = homePageService.getLisShops(list);
        return Result.successResult(lisShops);
    }

    @Override
    public Result commodityUproar(Integer offset, Integer limit) {
        List<OrderItems> orderItems = orderItemsRepository.findByPhaseAndGroup(offset,limit);

        List<SpecificationsVo> specificationsVos = Lists.newArrayList();

        orderItems.stream().forEach(i -> {
            Long specificationId = i.getSpecificationId();
            Optional<Specifications> optional = specificationsRepository.findById(specificationId);
            if(optional.isPresent()){
                SpecificationsVo specificationsVo = new SpecificationsVo();
                BeanUtils.copyProperties(optional.get(),specificationsVo);
                List<OrderItems> list = orderItemsRepository.findBySpecificationIdAndPhase(optional.get().getId(),10L);
                specificationsVo.setSalesTotal(list.size());
                specificationsVos.add(specificationsVo);
            }

        });
        return Result.successResult(specificationsVos);
    }

    @Override
    public Result shopWithSpecification(Integer offset, Integer limit, Long shopId) {
        if(shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOPID);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
//        List<Specifications> list = specificationsRepository.findByShopId(shopId, offset, limit);
        List<Specifications> list = specificationsRepository.findByShopIdAndOrderByTopAsc(shopId, offset, limit);

        return Result.successResult(list);
    }

    @Override
    @Transactional
    public Result commodityForTop(Long specificationId) {
        if(specificationId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
//        specificationsRepository.updateByShopIdAndTop(shopId);
        specificationsRepository.updateByShopIdAndTopAndSpecificationId(specificationId);
        return Result.successResult();
    }


}
