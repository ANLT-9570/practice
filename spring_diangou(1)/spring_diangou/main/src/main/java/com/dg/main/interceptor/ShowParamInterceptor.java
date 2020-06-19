package com.dg.main.interceptor;//package com.dg.main.interceptor;

import com.google.gson.Gson;


import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import com.dg.main.util.DateUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Map;
@Component
public class ShowParamInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Gson gson=new Gson();
        Map<String, String[]> param= request.getParameterMap();


        String url=request.getRequestURI();
        System.out.println("time  "+ DateUtils.getSysDateTimeString() +"  url---------->"+url+"  --------request params--------->  "+gson.toJson(param));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
