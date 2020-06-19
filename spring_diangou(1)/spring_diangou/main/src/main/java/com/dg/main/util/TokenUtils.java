package com.dg.main.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class TokenUtils {
 
	private static Map<String,String> MAP_TOKENS = new HashMap<String,String>();
	private static final int VALID_TIME = 60*60*2; // token��Ч��(��)
	public static final String TOKEN_ERROR = "F"; // �Ƿ�
	public static final String TOKEN_OVERDUE = "G"; // ����
	public static final String TOKEN_FAILURE = "S"; // ʧЧ
	

	public static String getToken(String str) {
		String token = Token.encoded(getCurrentTime()+","+str);
		MAP_TOKENS.put(str, token);
		return token;
	}

	public static String checkToken(String token) {
		if (token == null) {
			return TOKEN_ERROR;
		}
		try{
			String[] tArr = Token.decoded(token).split(",");
			if (tArr.length != 2) {
				return TOKEN_ERROR;
			}
		
			int tokenTime = Integer.parseInt(tArr[0]);
		
			int currentTime = getCurrentTime();
			if (currentTime-tokenTime < VALID_TIME) {
				String tokenStr = tArr[1];
				String mToken = MAP_TOKENS.get(tokenStr);
				if (mToken == null) {
					return TOKEN_OVERDUE;
				} else if(!mToken.equals(token)) {
					return TOKEN_FAILURE;
				}
				return tokenStr;
			} else {
				return TOKEN_OVERDUE;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return TOKEN_ERROR;
	}
	

	public static int getCurrentTime() {
		return (int)(System.currentTimeMillis()/1000);
	}

	public static void removeInvalidToken() {
		int currentTime = getCurrentTime();
		for (Entry<String,String> entry : MAP_TOKENS.entrySet()) {
			String[] tArr = Token.decoded(entry.getValue()).split(",");
			int tokenTime = Integer.parseInt(tArr[0]);
			if(currentTime-tokenTime > VALID_TIME){
				MAP_TOKENS.remove(entry.getKey());
			}
		}
	}
	

	
}
