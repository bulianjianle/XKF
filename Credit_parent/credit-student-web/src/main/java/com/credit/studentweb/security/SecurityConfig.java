package com.credit.studentweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();//关闭跨站请求伪造
        http.authorizeRequests()
                //不用登录也能访问的
                .antMatchers("/static/css/**","/static/img/**","/static/js/**","/static/plugins/**","/login","/register","/registerController/add","/errorUI")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.formLogin()
                .loginPage("/login")//指定登录页面
                .loginProcessingUrl("/login")//表单请求地址
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .failureForwardUrl("/errorUI")
                .defaultSuccessUrl("/index")
                .and()
                .headers().frameOptions().disable();//关闭保护
        
    }
}
