package com.dg.main.serviceImpl.shop;

import com.dg.main.repository.shop.ShopTradeMoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopTradeMoneyService {
    @Autowired
    ShopTradeMoneyRepository repository;
}
