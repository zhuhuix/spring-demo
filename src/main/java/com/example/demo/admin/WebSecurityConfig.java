package com.example.demo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Security配置类
 *
 * @author zhuhuix
 * @date 2020-07-08
 * @date 2020-07-10 关闭跨域请求的安全验证
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AdminService adminService;

    // 自定义用户验证
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(adminService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    // 保护web请求的安全性规则

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").permitAll()
                .and().formLogin().defaultSuccessUrl("/user")
                .and().httpBasic()
                // 禁用 CSRF
                .and().csrf().disable();
    }
}

