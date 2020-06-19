package com.yzl.utils;

import java.util.Random;

/**
 * 鍚勭id鐢熸垚绛栫暐
 * <p>Title: IDUtils</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	鍏ヤ簯榫�
 * @date	2015骞�7鏈�22鏃ヤ笅鍗�2:32:10
 * @version 1.0
 */
public class IDUtils {

	/**
	 * 鍥剧墖鍚嶇敓鎴�
	 */
	public static String getKeyWork() {
		long millis = System.currentTimeMillis();

		//long millis = System.nanoTime();
		//鍔犱笂涓変綅闅忔満鏁�
		Random random = new Random();
		int end3 = random.nextInt(999);
		//濡傛灉涓嶈冻涓変綅鍓嶉潰琛�0
		String str = millis + String.format("%03d", end3);
		String strChar = "";
        for (int i = 0;i<1;i++){
        	strChar = strChar+ (char)(Math.random()*26+'A');//随机生成一个字母
        }
        str+=strChar;
		return str;
	}
	
	/**
	 * 鍟嗗搧id鐢熸垚
	 */
	public static long genItemId() {
		//鍙栧綋鍓嶆椂闂寸殑闀挎暣褰㈠�煎寘鍚绉�
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//鍔犱笂涓や綅闅忔満鏁�
		Random random = new Random();
		int end2 = random.nextInt(99);
		//濡傛灉涓嶈冻涓や綅鍓嶉潰琛�0
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
	
	public static void main(String[] args) {
		for(int i=0;i< 100;i++)
		System.out.println(genItemId());
	}
}
