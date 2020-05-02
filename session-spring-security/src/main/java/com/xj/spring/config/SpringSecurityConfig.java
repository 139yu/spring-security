package com.xj.spring.config;

import com.xj.spring.session.MySessionExpireStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MySessionExpireStrategy sessionExpireStrategy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    //使用表单认证
                .formLogin()
                //登录成功跳转页面
                .defaultSuccessUrl("/index.html")
                //登录失败跳转页面
                .and()
                .authorizeRequests() // 授权配置// 无需认证的请求路径
                .antMatchers("/session/invalid").permitAll()
                .anyRequest()  // 所有请求
                .authenticated()// 都需要认证
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredSessionStrategy(sessionExpireStrategy)
                .expiredUrl("/session/invalid")
        ;
    }
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("{noop}user")
                .roles("USER");
    }*/
}
