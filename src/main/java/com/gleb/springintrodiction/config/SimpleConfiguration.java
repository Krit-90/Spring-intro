package com.gleb.springintrodiction.config;

import com.gleb.springintrodiction.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SimpleConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());


    }
// TODO: Вход по ролям не ограниечен, хотя с базы User подтягивается и назначаются SimpleGrantedAuthority
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/cars/**"
                ).permitAll()
                .antMatchers("owners/**")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("motor-show/**")
                .access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

    }
}
