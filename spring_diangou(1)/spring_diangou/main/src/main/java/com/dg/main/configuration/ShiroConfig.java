package com.dg.main.configuration;

//import com.example.demo2.shiro.CORSAuthenticationFilter;
import com.dg.main.shiro.MySessionManager;
import com.dg.main.shiro.Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    //将自己的验证方式加入容器
//    @Bean
//    public Realm myRealm(){
//        return new Realm();
//    }

    //权限管理
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();//默认的安全管理器验证
        securityManager.setRealm(new Realm());//设置自己的验证方式
        securityManager.setSessionManager(new MySessionManager());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //装过滤条件和跳转条件
        Map<String,String> map = new HashMap<>();

        map.put("/**/*.app","anon");
        map.put("/**/*.test","anon");

        //不被拦截的资源
        map.put("/swagger-ui.html", "anon");
        map.put("/webjars/**", "anon");
        map.put("/v2/**", "anon");
        map.put("/swagger-resources/**", "anon");

        map.put("/userAdmin/login","anon");

        map.put("/static/**/**", "anon");

        map.put("/logout","logout");//登出
//        map.put("/**","authc");//对所有用户认证


//        shiroFilterFactoryBean.setLoginUrl("/user/unauth");
//        shiroFilterFactoryBean.setLoginUrl("/login");//登录
        shiroFilterFactoryBean.setLoginUrl("/userAdmin/logins");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/user/unauth");
        shiroFilterFactoryBean.setUnauthorizedUrl("/userAdmin/unauthorized");//未授权跳转
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //自定义过滤器
//        Map<String, Filter> filterMap = new HashMap<>();
//        filterMap.put("corsAuthenticationFilter",new CORSAuthenticationFilter());

//        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
