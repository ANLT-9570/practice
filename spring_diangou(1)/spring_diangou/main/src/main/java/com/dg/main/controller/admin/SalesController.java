package com.dg.main.controller.admin;

import com.dg.main.service.SaleService;
import com.dg.main.util.JedisUtil;
import com.dg.main.util.slzcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

@Controller
public class SalesController {

	@Autowired
	private SaleService saleService;
	@Autowired
	JedisUtil jedisUtil;
	//所有商家或者客户
	@RequestMapping("/SalesController/All")
	@ResponseBody
	public slzcResult All(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit, String search) {
		return saleService.selectAll(offset, limit,search);
	}
	
	//页面跳转
	@RequestMapping("/SalesController/skip")
	public String SkipPage() {
		return "Sales";
	}

	//页面跳转
	@RequestMapping("/asd")
	@ResponseBody
	public String asd() {
		Jedis jedis = jedisUtil.getJedis();
		jedis.setnx("as","00");
		jedis.expire("as",10000/1000);
		jedis.close();
		return "Sales";
	}
}
