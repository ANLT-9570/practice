package com.dg.main.repository.shop;

import com.dg.main.Entity.shop.ShopCarItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShopCarItemsRepository extends JpaRepository<ShopCarItems,Long>, JpaSpecificationExecutor<ShopCarItems> {
    List<ShopCarItems> findByShopCarId(Long shopCarId);
    ShopCarItems findByShopCarIdAndSpecificationId(Long shopCarId,Long specificationId);

}
