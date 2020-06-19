package com.dg.main.repository.shop;

import com.dg.main.Entity.shop.ShopTradeMoney;
import com.dg.main.Entity.shop.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShopTradeMoneyRepository     extends JpaRepository<ShopTradeMoney,Long>, JpaSpecificationExecutor<ShopTradeMoney> {
}
