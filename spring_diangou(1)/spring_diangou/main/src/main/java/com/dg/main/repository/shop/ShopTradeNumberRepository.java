package com.dg.main.repository.shop;

import com.dg.main.Entity.shop.ShopTradeNumber;
import com.dg.main.Entity.shop.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShopTradeNumberRepository     extends JpaRepository<ShopTradeNumber,Long>, JpaSpecificationExecutor<ShopTradeNumber> {
}
