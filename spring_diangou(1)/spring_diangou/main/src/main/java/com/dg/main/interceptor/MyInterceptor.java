package com.dg.main.interceptor;//package com.dg.main.interceptor;
//
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import com.google.gson.Gson;
//import com.dg.main.util.base.TokenUtils;
//
//
//public class MyInterceptor extends HandlerInterceptorAdapter{
//
////	static Integer a= 1;
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		
//		Gson gson=new Gson();
////		System.out.println(a);
////		a++;
////		String token = TokenUtils.getToken("xxs");//自定义生成的token
////		System.out.println(token);
//		
//		//获取App Ajax请求参数
//		String appAjax = response.getHeader("Access-Control-Allow-Headers");
//		
//		//参数
//		Map<String, String[]> param = request.getParameterMap();
//		
//		String url=request.getRequestURI();
//		System.out.println("url---------->"+url+"--------request params--------->"+gson.toJson(param));
//		
//		String[] tokens = param.get("token");//获取token数组
//		
//		if (appAjax != null) {//如果不为空说明是app的ajax请求
//			
//			if (tokens != null && tokens.length > 0) {//判断是否有值
//				String tokenValue = tokens[0];//token的值
//				
//				//检测当前token的状态
////				String checkToken = TokenUtils.checkToken(token);
//				String checkToken = TokenUtils.checkToken(tokenValue);
////				System.out.println(t+"<------------------------->"+ts);
//				
//				System.out.println("路径的值："+tokenValue);
//				
//				if (TokenUtils.TOKEN_FAILURE.equals(checkToken)) {//失效
//					response.getWriter().write("707");
//					return false;
//				}else if (TokenUtils.TOKEN_ERROR.equals(checkToken)) {//非法
//					response.getWriter().write("505");
//					return false;
//				}else if (TokenUtils.TOKEN_OVERDUE.equals(checkToken)) {//过期
//					response.getWriter().write("300");
//					return false;
//				}else {
//					return true;
//				}
//				
//			}else {
//				response.getWriter().write("505");
//				return false;
//			}
//		}else {
//			return true;
//		}
////		return true;
//	}
//}
