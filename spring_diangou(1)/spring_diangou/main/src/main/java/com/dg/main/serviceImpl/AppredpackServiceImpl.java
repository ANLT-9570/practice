package com.dg.main.serviceImpl;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.dg.main.Entity.logs.RedPackLog;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.RedPackRepository;
import com.dg.main.repository.log.RedPackLogRepository;
import com.dg.main.repository.orders.UserBalanceRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.service.AppRedPackService;
import com.dg.main.util.Result;
import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dg.main.Entity.orders.RedPack;


@Service
public class AppredpackServiceImpl implements AppRedPackService {
	
	@Autowired
	private RedPackRepository redPackRepository;
	@Autowired
	private ShopsRepository shopsRepository;
	@Autowired
	private RedPackLogRepository redPackLogRepository;
	@Autowired
	MobileUserRepository mobileUserRepository;
	@Autowired
	UserBalanceRepository userBalanceRepository;

	@Override
	public Result selectAppRedPack() {
		long currentTimeMillis = System.currentTimeMillis();
		long time = new Date().getTime();
		System.out.println(currentTimeMillis+"时间搓");
		Timestamp timestamp = new Timestamp(time);//1568602470
		System.out.println(timestamp+"时间");
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");
//		RedPack redPack = new RedPack();
//		redPack.setSendTime(timestamp);

		List<Long> shopsId = redPackRepository.findByShopIdGroup();
		if(shopsId==null || shopsId.size() ==0){
			return Result.successResult();
		}
		List<Map> mapList = Lists.newArrayList();
		Map<String,String> map = Maps.newHashMap();

//		List<Shops> shops =	shopsRepository.findByShopsIdAndSendTime(shopsId,timestamp);
		List<RedPack> redPacks =	redPackRepository.findByShopsIdAndSendTime(shopsId,timestamp);
		for (RedPack redPack : redPacks) {
			map.put("code",redPack.getCode());
			Optional<Shops> optional = shopsRepository.findById(redPack.getShopId());
			map.put("shopName",!optional.isPresent()?"0":optional.get().getName());
			map.put("shopId",redPack.getShopId().toString());
			map.put("sendTime",redPack.getSendTime().toString());
			mapList.add(map);
		}

		return Result.successResult(mapList);
	}

	@Override
	public Result bestNewRecord(Integer userId) {
		if(userId == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
		}

		MobileUser optional = mobileUserRepository.selectMobileUse(Long.valueOf(userId));
		if(optional == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_USER);
		}
		//最新领取的一条记录
		RedPackLog redPackLog = redPackLogRepository.findByUserIdBest(userId);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String dateTime = simpleDateFormat.format(new Date());
		String day = new SimpleDateFormat("MM-dd").format(new Date());
		//今日收入
		Long todayRevenue = redPackLogRepository.findByUserIdCountAndCreateTime(userId,"%"+dateTime+"%");
		//用户的钱
		UserBalance balance = userBalanceRepository.findByUserId(Long.valueOf(userId));

		Map<String,String> map = Maps.newHashMap();
		map.put("platformMoney",balance.getPlatformMoney()!=null?balance.getPlatformMoney().toString():"0");
		map.put("newestRecord",redPackLog != null ?redPackLog.getTakeMoney().toString():"0");
		map.put("todayRevenue",todayRevenue.toString());
		map.put("day",day);

		return Result.successResult(map);
	}
}
