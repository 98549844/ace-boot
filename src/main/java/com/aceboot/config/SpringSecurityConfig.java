package com.aceboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                //.antMatchers("/swagger-ui.html#/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/*").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated().and().formLogin();
    }




//    @Override
//    public void configure(WebSecurity web) {
//        //allow Swagger URL to be accessed without authentication
//        web.ignoring().antMatchers("/v2/api-docs",//swagger api json
//                "/swagger-resources/configuration/ui",//用来获取支持的动作
//                "/swagger-resources",//用来获取api-docs的URI
//                "/swagger-resources/configuration/security",//安全选项
//                "/swagger-ui.html");
//    }

    //静态资源配置
    @Override
    public void configure(WebSecurity web) {
        //swagger2所需要用到的静态资源，允许访问
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html");
    }

}
