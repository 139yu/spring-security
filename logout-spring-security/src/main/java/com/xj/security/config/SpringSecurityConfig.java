package com.xj.security.config;

import com.xj.security.component.MyAuthenticationAccessDeniedHandler;
import com.xj.security.session.MySessionExpireStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MySessionExpireStrategy sessionExpireStrategy;

    @Autowired
    private MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .exceptionHandling()
                .accessDeniedHandler(myAuthenticationAccessDeniedHandler)
                .and()
                //使用表单认证
                .formLogin()
                //登录成功跳转页面
                .defaultSuccessUrl("/index.html")
                //登录失败跳转页面
                .and()

                .authorizeRequests() // 授权配置// 无需认证的请求路径
                .antMatchers("/session/invalid","/logout","/logout/success").permitAll()
                .anyRequest()  // 所有请求
                .authenticated()// 都需要认证
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/success")
                .deleteCookies("JSESSIONID")
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
