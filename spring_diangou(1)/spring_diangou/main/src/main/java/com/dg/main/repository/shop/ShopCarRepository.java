package com.dg.main.repository.shop;


import com.dg.main.Entity.shop.ShopCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShopCarRepository   extends JpaRepository<ShopCar,Long>, JpaSpecificationExecutor<ShopCar> {
    List<ShopCar> findByUserId(Long userId);
    ShopCar findByUserIdAndShopId(Long userId,Long shopId);

}
