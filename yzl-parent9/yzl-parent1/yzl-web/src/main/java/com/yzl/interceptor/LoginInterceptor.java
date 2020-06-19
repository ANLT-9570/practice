package com.yzl.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yzl.planManagementController.CompletionTaskController;
import com.yzl.planManagementController.taskWorkingController;
import com.yzl.pojo.YzlUser;

///已作废 
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
	//登录拦截请求
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		//取用户登录信息
		YzlUser user = (YzlUser) request.getSession().getAttribute("user");
		if(user==null){
			System.out.println(request.getContextPath());
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		return true;
	}

}
//package com.yzl.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import com.yzl.pojo.YzlUser;
//
////
//public class LoginInterceptor implements HandlerInterceptor {
//
//	//登录拦截请求
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
//		
//		//取用户登录信息
//		YzlUser user = (YzlUser) request.getSession().getAttribute("user");
//		String url = request.getRequestURI();//获取请求的路径
//		System.out.println("请求的路径"+url);
//		
//		if (user == null || user.equals("")) {// && !url.equals("/user/login")
//			if (request.getHeader("x-requested-with")!=null) {//判断是否为ajax请求// 非ajax请求比 ajax请求少一个x-requested-with属性，所以有x-requested-with属性则为ajax请求
//				response.setHeader("sessionStatus", "timeout");//设置response头，前台依此来判断是否session过期
//				String contextPath = request.getSession().getServletContext().getContextPath();
//				System.out.println("--------------"+contextPath);
//				response.sendRedirect(request.getSession().getServletContext().getContextPath());
//				return false;
//			}
//		}
//		return true;
//	}
//		
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
//			throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
//			throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//}
