package com.bank.atm.security;

import com.bank.atm.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.userDetailsService(userDetailsService);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.httpBasic();
//        http.authorizeRequests().mvcMatchers(HttpMethod.GET,"/users","/user/{id}", "/transactions", "/transaction/{id}").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.POST, "/user/add").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.PUT, "user").hasAnyRole("ADMIN", "USER")
//                .mvcMatchers(HttpMethod.DELETE, "/user/delete/{id}").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.GET, "/me").hasAnyRole("USER", "ADMIN").and().csrf().disable();
        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        
        return new BCryptPasswordEncoder();
    }
}
