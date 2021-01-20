package com.aceboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "springsecurityconfig")
@PropertySource(value = "classpath:application.yml", encoding = "UTF-8")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private String logoutsuccessurl = "/定义一个logout页面";
    private String permitall = "/api";
    private String deniedpage = "/deny";
    //邦定的用户组才能登入url
    private String urlroles;


    //RESTful and CRSF have conflict
    //CRSF default support GET,head,trace,options,biy not support post
    //in security config, disable .and().csrf().disable()
    //refer to hankuikui/p/14024637.html
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/*").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/").permitAll()
                //open spring security
                .anyRequest().authenticated().and().formLogin()

                //close spring security
                //.anyRequest().permitAll()
                //.and().logout().permitAll()

                .and().rememberMe().and().csrf().disable();

    }

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .and().formLogin().loginPage("登陆页").permitAll()
                .and().logout().logoutSuccessUrl("返回登陆页").permitAll()
                .and().rememberMe()

    }*/

    //静态资源配置
    @Override
    public void configure(WebSecurity web) {
        //swagger2所需要用到的静态资源，允许访问
        web.ignoring()
                .antMatchers("/v2/api-docs"
                        , "/swagger-resources/configuration/ui"
                        , "/swagger-resources"
                        , "/swagger-resources/configuration/security"
                        , "/swagger-resources/**"
                        , "/swagger-ui.html");
    }

    /**
     * 设置admin 用户
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}adminpass")
                .roles("ADMIN", "USER")
                .and()
                .withUser("garlam")
                .password("{noop}garlamau")
                .roles("USER");

    }
}
