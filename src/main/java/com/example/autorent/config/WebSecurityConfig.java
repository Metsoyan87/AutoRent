package com.example.autorent.config;

import com.example.autorent.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/view/loginPage")
//                .usernameParameter("username")
//                .passwordParameter("password")
                .loginProcessingUrl("/view/login")
                .failureUrl("/view/loginPage?error=true")
                .defaultSuccessUrl("/view/loginSuccess")
                .and()
                .logout()
                .logoutSuccessUrl("/view/")
                .and()
                .authorizeRequests()
                .antMatchers("/view/car/add").hasAuthority(Role.ADMIN.name())
                .antMatchers("/view/admin").hasAuthority(Role.ADMIN.name())
                .antMatchers("/view/car/change").hasAuthority(Role.ADMIN.name())
                .antMatchers("/view/car/delete").hasAuthority(Role.ADMIN.name())
                .antMatchers("/view/users/delete").hasAuthority(Role.ADMIN.name())
                .antMatchers("/view/users").hasAuthority(Role.ADMIN.name())
                .antMatchers("/view/user").hasAuthority(Role.USER.name())
                .anyRequest()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/view/accessDenied");


    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder);
    }
}
