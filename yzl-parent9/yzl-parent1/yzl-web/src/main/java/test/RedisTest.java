package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.yzl.LogService.RedisUtils;

//import java.util.Set;

import com.yzl.utils.JedisPoolClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

//	private static JedisPool pool;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
//		e62ebeaf-5cd2-45a0-9f73-b6847ae20f14/20190415/上传测试123.docx,859735b1-07e8-4a97-8611-65ea814231fa/20190415/表表汇总.txt,
//		String asd = "e62ebeaf-5cd2-45a0-9f73-b6847ae20f14/20190415/上传测试123.docx,859735b1-07e8-4a97-8611-65ea814231fa/20190415/表表汇总.txt,";
//		String[] split = asd.split(",");
//		for (String string : split) {
//			System.out.println(string);
//		}
//		Jedis jedis = JedisPoolClient.getJedis();
//		for(int i=0;i<1000;i++) {
//			String string = jedis.get("123");
//			System.out.println(string+"="+i);
//		}
//		
//		JedisPoolClient.returnResource(jedis);
//		JedisPoolConfig poolConfig = new JedisPoolConfig();
//		//设置最大10个连接
//		poolConfig.setMaxTotal(10);
//		
//		pool = new JedisPool(poolConfig,"47.98.175.75");
//		
//		Jedis jedis = pool.getResource();
//		
//		jedis.set("123", "321");
//		
//		String string = jedis.get("123");
//		
//		System.out.println(string);
//		
//		jedis.close();
//		pool.close();
//		Jedis jedis = new Jedis("47.98.175.75",6379,1000000);
//		jedis.set("string2", "3");
//		String string = jedis.get("string");
//		Set<String> keys = jedis.keys("*");
//		for (String string2 : keys) {
//			Long del = jedis.del(string2);
//			System.out.println(del);
//		}
//		Long del = jedis.del("string"+"*");
//		System.out.println(del);
//		
////		System.out.println(string);
//		jedis.close();
//		ApplicationContext asd = new ClassPathXmlApplicationContext("applicationContext.xml");
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mvc.xml");
//		RedisUtils bean =(RedisUtils) applicationContext.getBean("redisUtils");
//		String object =(String) bean.get("key");
//		boolean set = bean.set("123", "123");
//		System.out.println(object);
		
//		RedisUtils redisUtils = new RedisUtils();
//		boolean set = redisUtils.set("key", "123");
//		RedisTemplate bean =(RedisTemplate) applicationContext.getBean("redisTemplate");
//		System.out.println(bean);
//		RedisUtils redisUtils = new RedisUtils();
//		boolean b = redisUtils.set("key", "123");
//		String object =(String) redisUtils.get("key");
//		System.out.println(set);
//		List<String> strings = new ArrayList<>();
//		strings.add("45");
//		strings.add("4501");
//		strings.add("4502");
//		strings.add("4504");
//		for (String string : strings) {
//			if (string.contains("4501")) {
//				boolean remove = strings.remove(string);
//				System.err.println(remove);
//			}
//		}
//		StringBuffer bf = new StringBuffer();
//		String string = bf.toString();
//		System.err.println(string);
//		
//		String string2 = new String("qq");
//		String string3 = string2.toString();
//		System.out.println(string3);
		int [] a= {2,7,11,5};
		int tar = 9;
		long millis = System.currentTimeMillis();
		int[] is = towSum(a,tar);
		long millis2 = System.currentTimeMillis();
		System.out.println(millis-millis2);
		System.out.println(is[0]);
		System.out.println(is[1]);
		
	}
	
//	给定一个整形数组和一个整数target，返回2个元素的下标，它们满足相加的和为target。 
//	你可以假定每个输入，都会恰好有一个满足条件的返回结果。
	public static int [] towSum(int [] nums,int target) {
		int [] aa = new int[2];
		
		for (int i=0;i<nums.length;i++) {
			for (int x=1;x<nums.length;x++) {
				int a = nums[i]+nums[x];
				if (a == target) {
					aa[0]=i;
					aa[1]=x;
				}
			}
		}
		
		return aa;
	}
}
