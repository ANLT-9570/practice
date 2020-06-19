package com.dg.main.serviceImpl.users;

import com.dg.main.Entity.users.UserCouponItem;
import com.dg.main.repository.users.UserCouponItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCouponItemService {
    @Autowired
    UserCouponItemRepository userCouponItemRepository;
}
