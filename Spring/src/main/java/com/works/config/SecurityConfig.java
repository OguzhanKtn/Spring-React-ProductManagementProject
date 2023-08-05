package com.works.config;

import com.works.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final PasswordEncoder passwordEncoder;
    final UserService userService;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
    http
    .httpBasic()
    .and()
    .authorizeHttpRequests()
    .antMatchers("/category/**").hasRole("admin")
    .antMatchers("/product/save").hasRole("admin")
    .antMatchers("/product/update").hasRole("admin")
    .antMatchers("/product/delete/{pid}").hasRole("admin")
    .antMatchers("/product/listAllProducts").hasRole("admin")
    .antMatchers("/order/listAll").hasRole("admin")
    .antMatchers("/order/save").hasRole("user")
    .antMatchers("/order/listByUser/{uid}").hasRole("user")
    .antMatchers("/order/delete/{oid}").hasRole("user")
    .and()
    .csrf().disable().formLogin().disable();
    }
}